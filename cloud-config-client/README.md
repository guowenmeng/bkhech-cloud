# Getting Started

### Attention
1. 获取配置信息：
curl http://localhost:8005/config/userInfo
2. sc config client 提供了在以下两种情况下的配置类，才能自动刷新
- @ConfigurationProperties
- @RefreshScope + @Value
