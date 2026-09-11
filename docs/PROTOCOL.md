# AEE Mokacam 通信协议（逆向恢复）

来源：`AEE Mokacam_1.08.apk`（versionCode 8），jadx 反编译
`com.aee.zone.service.*`、`com.aee.zone.bean.*`、`com.aee.zone.constants.AeeConstants`。

## 1. 相机 JSON 命令协议（TCP 7878）

- 地址：`192.168.42.1:7878`（相机 AP 网关，SSID 以 Mokacam 命名）
- 传输：一条长连接，UTF-8 JSON 文本流，无帧定界（粘包按「最后出现的 `{`」切分，
  与原版 `service.o` 行为一致）
- 会话：首个命令必须是 `msg_id=257` 取 token；之后所有命令 JSON 的 `token` 字段
  携带该值（原版 `SendMsg.toJson()` 无条件写入 `AeeApplication.e`）

### 请求格式

```json
{"msg_id": 257, "token": 0}
{"msg_id": 9,   "token": 823561242, "param": "video_resolution"}
{"msg_id": 2,   "token": 823561242, "param": "1920x1080 60P 16:9", "type": "video_resolution"}
```

### 响应格式

```json
{"msg_id": 257, "rval": 0, "param": 823561242, "type": "token"}
{"msg_id": 1283, "rval": 0, "listing": [{"IMG_0001.JPG": "a 121234 2017-01-01 10:00:00"}]}
```

### msg_id 速查

| msg_id | 含义 | param | type | 备注 |
|---|---|---|---|---|
| 1 | 查询整机状态 | — | `app_status` | 返回 `record`/`vf`/`idle` |
| 2 | 设置参数 | 值 | 参数名 | 见下方参数表 |
| 3 | 查询（口令类） | — | — | 原版对返回 param 做 MD5 存 `AeeApplication.q` |
| 4 | 观察到的命令 | `"C"` | — | 用途未确认 |
| 5 | SD 剩余空间 | — | `free` | |
| 8 | 格式化 SD 卡 | — | — | rval=0 成功 |
| 9 | 读/写相机设置 | 键名（读） | null（读） | 写：param=值, type=键名 |
| 13 | 电量 | — | `video_bat` | |
| 257 | 取 token | — | — | 返回 param=token(int) |
| 260 | 取消拍摄 | — | — | |
| 261 | 发送文件 | 本地路径 | `TCP` | 配合 8787 通道 |
| 513 | 开始录像 | — | — | 原版 `InputDeviceCompat.SOURCE_DPAD`(=513) |
| 514 | 停止录像 | — | — | |
| 515 | 录像时长 | — | `video_time` | |
| 769 | 拍照 | `none_force`/`force` | — | 异步： rval 延迟返回（拍后 2.8s/7s/延迟秒数） |
| 1281 | 删除文件 | 相机绝对路径 | — | |
| 1282 | 单文件操作 | 相机绝对路径 | — | |
| 1283 | 列目录 | 相机绝对路径 | — | 返回 `listing` 数组 |
| 1286 | 固件上传声明 | `firmware.bin` | — | 附加 `offset/size/md5sum` |
| 1798/1799 | 扩展事件推送 | — | — | 原版写入 `AeeApplication.k` |

### 参数键（msg 2 / 9）

`video_resolution`、`photo_size`、`photo_shot_mode`、`photo_tlm`、`photo_delay`、
`photo_stamp`、`video_stamp`、`loop_back`、`Beep`、`Status_LED`、`TV_Mode`、
`Language`、`camera_clock`（`yyyy-MM-dd HH:mm:ss`）、`wifi_ssid`、`wifi_password`、
`Switch_mode`（录像/拍照模式切换，值 `nil`）、`default_setting`（`on`=恢复出厂）。

### rval 错误码

| rval | 含义 |
|---|---|
| 0 | 成功 |
| -17 | SD 卡满 |
| -30 | 无 SD 卡 |

### 异步通知

相机主动推送：769（录像状态）、513（存储事件）、514（拍照完成）、
1798/1799（扩展事件）。原版 `service.u` 常驻读线程分发到
`AeeApplication.m/i/j/k` 标志位；重写版通过 `CameraSession.Listener` 回调。

## 2. 文件通道（TCP 8787）

`service.o.b(String, long)`：另开一条 socket 到 `192.168.42.1:8787`，
先 `sleep 200ms`，然后裸流发送文件字节；等待回应中出现
`"put_file_complete"`，并比对返回的 `md5sum`（原版上限 100s）。

固件升级（`service.a.a(Handler)` + `service.g`）：

1. 发 msg 1286：`{"msg_id":1286,"token":T,"param":"firmware.bin","offset":0,"size":N,"md5sum":"..."}`
2. 用 8787 通道上传 `firmware.bin`
3. 等待确认（原版 5.8s 后置位），相机重启进升级流程

## 3. HTTP 文件服务

| URL | 用途 |
|---|---|
| `http://192.168.42.1/SD/moka/<name>` | 相机文件（下载） |
| `http://192.168.42.1/SD/moka/<name>@@22@@<attrs>` | 缩略图（attrs 原样拼回） |
| `http://192.168.42.1/DCIM/100MEDIA/` | DCIM 目录（100MEDIA / 101MEDIA） |

列目录返回的 attrs 形如 `a 121234 2017-01-01 10:00:00`
（flags、字节大小、修改时间）；`_thm.mp4`/`_thm.MP4` 后缀的流缩略图在 UI 中隐藏。

## 4. RTSP 实时预览

- Mokacam：`rtsp://192.168.42.1/live`
- 另一型号（MK10，原版常量保留）：`rtsp://192.168.3.60:8554/mk10`

原版通过 `libAeePlayer.so` 的
`AeePlayerStart(String)/AeePlayerStop()/AeePlayerRender()/AeePlayerSetScreen(int,int)`
在 GLSurfaceView 上渲染；重写版使用 Media3 RTSP。

## 5. 无人机飞行控制链路（TCP 8888）

`service.ac/ae/af` + `bean.i`：

- 地址 `192.168.1.1:8888`，`TCP_NODELAY` + `keepAlive`，连接超时 5s
- 控制帧 8 字节，发送周期 **30ms**（兼作心跳），连续失败 >20 次判定掉线：

```
[0]=0xCC  [1..4]=四通道(0x80=中位)  [5]=flags  [6]=CRC(异或 b1..b5)  [7]=0x33
```

- 32 位状态字（`AeeApplication.a(long)`）：bit29=GPS 定位、bit30=视觉/光流、
  bit31=普通；bit21..28 对应 ARMED/TAKEOFF/LANDING/RTL/GUIDED/CIRCLE/
  SIMPLE/SUPER_SIMPLE（原版 `DroneAction`/`STATUS_WORD` 枚举）
- SSID 判定：`AEE_CONDOR`（机型 2）、`AEE_RC_CON`（遥控链路，机型 3）

注：1.08 版 APK 的飞行器/云台 UI Activity 已从 DEX 裁剪（manifest 中为残留声明），
但上述协议代码完整存在。本工程的 `drone` 包按协议原样重写，便于接入自建 UI。
