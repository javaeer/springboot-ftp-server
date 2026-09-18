# 安全策略 / Security Policy

## 🛡 受支持的版本 / Supported Versions

| 版本 | 是否维护 |
| --- | --- |
| `1.0.0` | ✅ 维护中（最新稳定版） |

## 🔒 漏洞上报 / Reporting a Vulnerability

请**不要**在公开 Issue 中披露安全漏洞。可通过以下方式私下上报：

- 使用 GitHub 仓库的 **Security → Report a vulnerability**（安全通告）
- 或邮件联系维护者：**javaeer@aliyun.com**

请尽量提供：

1. 漏洞类型与影响范围
2. 复现步骤（环境、配置、触发方式）
3. 可能的修复建议（如有）

我们会在收到后尽快确认并回应处理进展。

## ⚠️ 已知安全风险 / Known Security Risks

本项目当前为示例性质，存在以下**已知**安全问题，使用前请务必知悉：

### 1. FTP 凭据硬编码
默认账号密码写在源码中（`FtpServerListener` 与 `FtpClientUtils`）：

- 用户名：`ftp-3d-user`
- 密码：`[ftp@3d@user]`

**建议**：生产环境务必将凭据改为从**环境变量 / 配置中心**读取，并定期更换。相关外部化工作已列入 [路线图](CHANGELOG.md#unreleased)。

### 2. 明文 FTP（非加密传输）
当前为纯 FTP（控制端口 `2221`），**未启用 FTPS / SSL**，账号密码与文件内容均以明文在网络中传输。

**建议**：仅在可信内网使用；如需对外暴露，请叠加 FTPS（SSL/TLS）或改用 SFTP / SSH 通道。

### 3. 根目录可写且路径固定
FTP 用户对任意连接者拥有写权限，home 目录（如 `/home/resource`）落盘路径固定。

**建议**：通过操作系统权限、容器挂载（`docker-compose.yml` 中 `/opt/resource:/home/resource`）控制实际落盘位置与可见范围，避免越权访问宿主机其他目录。

### 4. 演示代码残留
`FtpClientUtils` 含硬编码本地路径，属于演示代码，请勿直接用于生产。

---

感谢你帮助提升本项目的安全性！🙏
