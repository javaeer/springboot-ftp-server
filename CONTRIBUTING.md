# 贡献指南 / Contributing

感谢你对 `springboot-ftp-server` 的关注！本项目欢迎所有形式的贡献：Bug 反馈、功能建议、文档完善、代码提交。

## 🌿 分支模型 / Branching

| 分支 | 用途 |
| --- | --- |
| `master` | 稳定主分支，可直接部署 / 发版 |
| `dev` | 日常集成分支（可选） |
| `feature/xxx` | 新功能开发 |
| `fix/xxx` | 缺陷修复 |

> 请基于 `master`（或 `dev`）创建 `feature/*` / `fix/*` 分支，再发起 Pull Request。

## 📝 提交规范 / Commit Messages

请使用 [Conventional Commits](https://www.conventionalcommits.org/) 风格：

| 类型 | 含义 |
| --- | --- |
| `feat` | 新功能 |
| `fix` | 缺陷修复 |
| `docs` | 文档变更 |
| `refactor` | 重构（无功能变化） |
| `test` | 测试相关 |
| `chore` | 构建 / 工具链变动 |

示例：

```bash
git commit -m "feat: 将 FTP 参数外部化为 application.yml 配置"
git commit -m "fix: 修复 Dockerfile 中 jar 名称与产物不一致"
```

## 💻 本地开发 / Local Development

本项目使用 Java 8 与 Maven。仓库已附带 Maven 包装器：

```bash
# 编译
./mvnw clean compile

# 运行测试
./mvnw test

# 打包
./mvnw clean package
```

运行后 FTP 服务随应用启动，控制端口 `2221`，HTTP 端口 `80`（`application.yml`）。

## ⚠️ 安全约定 / Security

- **请勿在代码中提交任何真实凭据、密钥或生产配置**。当前默认 FTP 密码为硬编码示例值，生产化时请改为环境变量 / 配置中心读取。
- 如涉及安全相关改动，请先阅读 [SECURITY.md](SECURITY.md)。

## 💬 讨论 / Discussions

功能探讨、使用问题欢迎在 GitHub **Discussions**（或 Issues）中交流。提交 PR 前如不确定方案，先开 Issue 讨论可避免返工。

再次感谢你的贡献！🎉
