# MokacamOpen — AEE Mokacam 配套 App 开源重写

> 从 `AEE Mokacam_1.08.apk`（package `com.aee.mokacam`，versionCode 8）逆向分析后，
> 用现代 Android 技术栈 **完整重写** 的源码工程。
> 本仓库 **只包含源码，不发布 APK，也不在本地编译**——用 Android Studio 打开即可构建。

## 项目背景

原版 AEE Mokacam App 是 AEE（臻迪系航拍品牌）运动相机 Mokacam 的官方配套应用，
发布于 2016 年左右（minSdk 17 / targetSdk 21）。本工程通过 jadx + apktool 对
原始 APK 做了完整的静态逆向，恢复了：

- 相机 JSON 命令协议（TCP 192.168.42.1:7878 / 8787 文件通道）
- RTSP 实时预览（原版为闭源 `libAeePlayer.so` JNI 播放器）
- 相机 HTTP 文件服务（缩略图 + 下载）
- 无人机飞行控制链路（TCP 192.168.1.1:8888，CC-帧 + 30ms 心跳）
- 全部 UI 流程与文案（中英双语）

详细的协议字段与原始类 → 重写类的对应关系见：

- [docs/PROTOCOL.md](docs/PROTOCOL.md) — 逆向出的完整通信协议
- [docs/MAPPING.md](docs/MAPPING.md) — 原 APK 类 → 本工程类映射表

## 功能清单（与原版对齐）

| 模块 | 功能 | 原始实现 | 本工程实现 |
|---|---|---|---|
| 连接 | 检测相机 Wi-Fi（192.168.42.x）→ TCP 握手 → token | `MainActivity`/`service.o` | `MainActivity` + `CameraSession` |
| 实时预览 | RTSP `rtsp://192.168.42.1/live` | 闭源 `libAeePlayer.so` + GLSurfaceView | **Media3 ExoPlayer RTSP 模块** |
| 录像 | 开始/停止（msg 513/514）+ 时长计时 | `AeeCameraActivty` | `CameraLiveActivity` |
| 拍照 | 单拍/快拍/延迟（msg 259），SD 满/无卡错误提示 | `AeeCameraActivty` | `CameraLiveActivity` |
| 相机设置 | 分辨率、快拍、提示音、指示灯、电视制式、语言（msg 9/2） | `AeeCameraSettingActivity` | `CameraSettingsActivity` |
| 维护 | 时间同步、格式化 SD（msg 8）、恢复出厂、固件升级（msg 1286 + 8787 上传） | `AeeCameraSettingActivity`/`service.a` | `CameraSettingsActivity` + `FirmwareUploader` |
| 相机 Wi-Fi | SSID/密码修改（msg 2 / msg 2049 `ssid$password`） | `WifiPswChangeActivity`/`AeeCameraWifiConfigActivity` | `WifiPswChangeActivity` + `WifiConfigActivity` |
| 相册 | 相机相册（msg 1283 列目录 + HTTP 缩略图）/ 本地相册、多选、下载、删除、分享 | `LibraryActivity`/`SelectLibraryActivity` | `LibraryActivity` + `SelectLibraryActivity` |
| 文件下载 | HTTP 下载 + 通知栏进度 + 单任务队列 | `DownLoadActivity` + xUtils | `DownloadManager`（HttpURLConnection） |
| 查看器 | 图片捏合缩放 / 视频播放（原版 Vitamio） | `ShowPicOrVideoActivity` | `ShowMediaActivity`（Media3）+ `PinchImageView` |
| 应用设置 | 清缓存、Wi-Fi 密码、固件升级、版本 | `AeeAppSettingActivity` | `AppSettingsActivity` |
| 自升级 | aee.com 版本 XML 检查 | `UpdateManager` | `AppUpdateChecker`（厂商服务器已下线，容错处理） |
| 产品/参数 | 产品介绍、规格表 | `ProductActivity`/`ProductParamsActivity` | 同名 Activity |
| 支持 | FAQ（相机/Sparrow/Condor）+ 三地热线 + 注册向导 | `SupportActivity`/`RegisterActivity` | 同名 Activity |
| 法务 | 相机/无人机免责声明（assets 双语文本） | `LegalActivity` | `LegalActivity`（同一 assets 文件名） |
| 无人机协议 | 飞控 TCP 链路、CC 帧心跳/通道、状态字解码 | `service.ac`/`bean.i`（UI 在该版本被裁剪） | `drone.DroneLink`/`DronePacket`/`DroneState` |
| 崩溃日志 | 未捕获异常落盘 | `utils.p` | `LogUtils` |

原版依赖的闭源/私有 SDK 在重写中的替代：

| 原依赖 | 替代方案 |
|---|---|
| `libAeePlayer.so`（JNI RTSP 播放） | `androidx.media3:media3-exoplayer-rtsp` |
| Vitamio（视频播放 + 全套 .so） | `androidx.media3:media3-exoplayer` |
| xUtils（HTTP/下载） | `HttpURLConnection` |
| Universal Image Loader | 自写 LRU `ImageLoader` |
| ShareSDK（微信/QQ/微博） | 系统 ShareSheet（`ACTION_SEND`） |
| Mob/微博/微信/YiXin 回调 Activity | 无需（ShareSheet 覆盖） |
| 科大讯飞语音资源（assets 中 asr/bnf） | 原版 1.08 已无入口 UI，未重写 |

## 构建要求

- Android Studio（任意近期版本，会自动按 `gradle-wrapper.properties` 拉取 Gradle 8.7）
- AGP 8.5.2 / compileSdk 34 / minSdk 21 / targetSdk 34
- 一台 Mokacam 相机 + 手机连接相机 Wi-Fi 后即可全功能联调

## 快速使用

1. 用 Android Studio 打开本目录，等待 Sync；
2. 连接相机 Wi-Fi（网络为 192.168.42.x）；
3. 主界面点「连接相机」→ 自动进入实时预览。

## 免责声明

本项目为 **互操作性研究** 性质的开源重写：

- 未复制原 APK 的任何二进制资源（图片/so/字体均为重绘或矢量重做）；
- UI 文案与 FAQ/法务文本为功能等价的重写版本；
- 原厂（AEE）服务器相关的自升级/注册功能已按「失败容错」处理；
- 请勿将本项目用于任何侵权用途。

## License

Apache-2.0
