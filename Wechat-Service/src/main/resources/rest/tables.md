# 表结构设计

库名称： wechat_sys

```SQL
CREATE DATABASE wechat_sys;
USE wechat_sys;
```
## 配置管理

### Access Token 配置表
由于微信平台接口调用次数限制，考虑后期的扩展性，需要一个单独的代理服务(Proxy-Service)去维护Access Token,
由该代理服务按不同的公众号主体去请求相应的Access Token,将请求的Access Token 存储起来，并对外提供Access Token获取服务接口，
所有需要Access Token的地方都去请求该服务接口, 当Access Token要过期时，该服务重新从微信接口获取新的Access Token并刷新存储。

```sql
CREATE TABLE `access_token_conf` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '开发者ID(AppID)',
  `app_secret` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '开发者密码(AppSecret)',
  `token` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '令牌',
  `access_token` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '全局唯一接口调用凭据Access Token',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY(`id`),
   UNIQUE KEY(`app_id`),
   KEY `idx_create_time` (`create_time`),
   KEY `idx_update_time` (`update_time`),
   KEY `idx_app_id_secret` (`app_id`,`app_secret`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Access Token 配置表';
```

## 数据管理
