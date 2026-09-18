# 更新日志 / Changelog

本文件所有记录遵循 [Keep a Changelog](https://keepachangelog.com/zh-CN/) 约定，
版本号遵循 [语义化版本](https://semver.org/lang/zh-CN/)。

## [Unreleased]

### 计划 / Planned
- 将 FTP 参数（端口、账号、密码、根目录）外部化为 `application.yml`，支持环境变量覆盖
- 完善 `POST /upload` 接口（当前返回 `null`）
- 支持 FTPS（SSL/TLS）加密传输
- 修复 `Dockerfile` 中 `jar` 名称与 Maven 构建产物不一致的问题
- 参数化 `FtpClientUtils` 演示代码，移除硬编码路径

## [1.0.0] - 2021-06-07

### 新增 / Added
- 基于 Spring Boot 2.1.1 + Apache FtpServer (`ftpserver-core` 1.1.0) 的嵌入式 FTP 服务
- 通过 `ServletContextListener` 随应用启动 / 停止 FTP 服务
- 跨平台文件根目录自动识别（Windows / macOS / Linux）
- 被动模式端口区间配置（`10000–10500`）
- 默认可写 FTP 账号 `ftp-3d-user`
- HTTP 健康检查端点 `GET /`
- 基于 `commons-net` 的 FTP 客户端上传工具类（演示）
- `Dockerfile` 与 `docker-compose.yml`（含 Nginx 反代）部署方案

[Unreleased]: https://github.com/javaeer/springboot-ftp-server/compare/v1.0.0...master
[1.0.0]: https://github.com/javaeer/springboot-ftp-server/releases/tag/v1.0.0
