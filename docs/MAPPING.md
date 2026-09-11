# 原 APK 类 → 本工程类 映射表

反编译输出：`jadx`（Java 参考）+ `apktool`（Manifest / 资源）。
原包名 `com.aee.mokacam`（业务代码在 `com.aee.zone.*`）→ 重写统一使用 `com.aee.mokacam.*`。

## Application / 基础

| 原始类 | 重写类 | 说明 |
|---|---|---|
| `com.aee.zone.AeeApplication` | `MokacamApp` | 保留连接状态/Activity 栈，协议状态移入 Session |
| `com.aee.zone.activity.BaseActivity` | `ui.BaseActivity` | AppCompat 化 |
| `com.aee.zone.constants.AeeConstants` | `constants.AeeConstants` | 全部协议常量、路径、URL |
| `com.aee.zone.utils.p`（崩溃日志） | `util.LogUtils` | |
| `com.aee.zone.utils.m`（调试日志） | `util.LogUtils` | |
| `com.aee.zone.utils.a`（路径/MD5） | `util.FileUtils` / `util.Md5Utils` | |
| `com.aee.zone.utils.o`（MD5/补零） | `util.Md5Utils` | |
| `com.aee.zone.utils.k`（hex/字节序） | （协议文档化，未单列） | |
| `com.aee.zone.utils.aa` / `service.al`（Wi-Fi） | `util.WifiUtils` | |
| `com.aee.zone.utils.w`（Toast） | `util.ToastUtils` | |
| Universal Image Loader 初始化 | `util.ImageLoader` | LRU + 4 线程 |

## 相机协议层

| 原始类 | 重写类 | 说明 |
|---|---|---|
| `bean.SendMsg` | `camera.CameraMessage` | 增加 1286 固件扩展字段 |
| `bean.ReceiveMsg` + `utils.ResolveJson` | `camera.CameraResponse` | |
| `service.o`（7878 会话）+ `service.q/r/u`（收发线程） | `camera.CameraSession` | 通知改 Listener 回调 |
| `service.a`（门面）+ 其全部匿名 Runnable（b..x） | `camera.CameraClient` | 一个方法一条命令 |
| `bean.g`（目录条目） | `camera.CameraFile` | |
| `bean.h` + `service.o.b(String,long)`（8787 上传） | `camera.FirmwareUploader` | |
| `service.o` 8787 部分 + `service.g`（固件） | `camera.FirmwareUploader` | |
| `service.UpdateManager`（自升级） | `update.AppUpdateChecker` | 厂商服务器已下线，容错 |

## 无人机协议层

| 原始类 | 重写类 |
|---|---|
| `service.ac` + `service.ae/af`（TCP 8888 + 心跳） | `drone.DroneLink` |
| `bean.i`（CC 帧 + CRC） | `drone.DronePacket` |
| `AeeApplication.a(long)` + `STATUS_WORD/DroneState/DroneAction` | `drone.DroneState` |
| `service.x / v / w`（数据/控制包装） | （并入 `DroneLink`，见 PROTOCOL.md §5） |

## 下载

| 原始类 | 重写类 |
|---|---|
| `activity.DownLoadActivity` + xUtils http + 通知 | `download.DownloadManager` + `ui.library.DownloadActivity` |

## UI（Activity）

| 原始类（com.aee.zone.activity） | 重写类（com.aee.mokacam.ui.*） |
|---|---|
| `SplashActivity` | `ui.SplashActivity` |
| `MainActivity` | `ui.MainActivity` |
| `AeeCameraActivty` | `ui.camera.CameraLiveActivity` |
| `AeeCameraSettingActivity`（含 A10 版） | `ui.camera.CameraSettingsActivity` |
| `AeeCameraWifiConfigActivity` | `ui.camera.WifiConfigActivity` |
| `AeeCameraPlaybackActivity` | `ui.camera.CameraPlaybackActivity` |
| `SelectLibraryActivity` / `SelectSimpleActivity` | `ui.library.SelectLibraryActivity` |
| `LibraryActivity` | `ui.library.LibraryActivity` |
| `ShowPicOrVideoActivity` | `ui.library.ShowMediaActivity` |
| `DownLoadActivity` | `ui.library.DownloadActivity` |
| `AeeAppSettingActivity` | `ui.settings.AppSettingsActivity` |
| `WifiPswChangeActivity` | `ui.settings.WifiPswChangeActivity` |
| `ProductActivity` | `ui.info.ProductActivity` |
| `ProductParamsActivity` | `ui.info.ProductParamsActivity` |
| `SupportActivity` / `ProductInSupportActivity` / `DroneInSupportActivity` | `ui.info.SupportActivity`（FAQ 数据合并） |
| `LegalActivity` | `ui.info.LegalActivity` |
| `RegisterActivity` | `ui.info.RegisterActivity` |

## Widget

| 原始类（com.aee.zone.widget） | 重写类 |
|---|---|
| `g`（loading 弹窗） | `view.LoadingDialog` |
| `PinchImageView` / `ZoomImageView` | `view.PinchImageView` |
| `SlidingMenuView` | `MainActivity` 内置面板（FrameLayout 切换） |
| `AppSingleRocker` / `NoTouchSeekBar` / `VerticalSeekBar` 等 | 无人机/云台 UI 被原版裁剪，未重写（协议层已保留） |

## 未重写（原版即为残留/裁剪）

- Manifest 中声明的 `com.icatch.wcmapp3.*`、`AeeDroneActivity*`、`AeeGimbalsActivity*`、
  `FindAeeDrone`、`DownLoadOfflineMaps`、`ShopActivity` 等在 1.08 DEX 中 **不存在**（声明残留）
- 科大讯飞语音（assets 的 asr/bnf/grammar）：无入口 UI
- Mob ShareSDK 及微信/QQ/微博/YiXin 回调 Activity：以系统 ShareSheet 替代
