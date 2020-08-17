DROP TABLE IF EXISTS `operator_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operator_info` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT,
    `account` varchar(20) NOT NULL COMMENT '登录账号',
    `name` varchar(20) NOT NULL COMMENT '操作员姓名',
    `mobile` varchar(20) DEFAULT NULL COMMENT '操作员手机',
    `email` varchar(200) DEFAULT NULL COMMENT '操作员邮箱',
    `status` char(3) NOT NULL COMMENT '状态',
    `system_user_id` bigint unsigned DEFAULT NULL COMMENT '对应用户id',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_user` (`system_user_id`),
    KEY `idx_mobile` (`mobile`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `system_operate_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_operate_log` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT,
    `operator_id` bigint unsigned DEFAULT NULL COMMENT '操作员id',
    `uri` varchar(100) NOT NULL COMMENT '请求uri',
    `request` text COMMENT '请求内容',
    `response` text COMMENT '返回内容',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_operate` (`operator_id`),
    KEY `idx_uri` (`uri`),
    KEY `idx_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统操作日志';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `system_data_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_data_dictionary` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '',
    `type` char(5) NOT NULL COMMENT '参数类型',
    `description` varchar(20) NOT NULL COMMENT '参数类型描述',
    `key` char(3) NOT NULL COMMENT '参数值',
    `value` varchar(20) NOT NULL COMMENT '参数意义',
    `system` tinyint(1) unsigned NOT NULL COMMENT '是否系统使用',
    `status` char(3) NOT NULL COMMENT '状态',
    `create_time` timestamp DEFAULT NULL COMMENT '创建时间',
    `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_type_key` (`type`,`key`),
    KEY `idx_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统数据字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `system_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_parameter` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT,
    `type` char(3) NOT NULL COMMENT '参数类型',
    `code` char(5) NOT NULL COMMENT '参数代码',
    `description` varchar(50) NOT NULL COMMENT '参数类型描述',
    `key` varchar(50) NOT NULL COMMENT '参数值',
    `value` varchar(50) NOT NULL COMMENT '参数意义',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_type_code` (`type`,`code`),
    KEY `idx_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统参数表';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `system_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_resource` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '资源编号',
    `code` varchar(200) NOT NULL COMMENT '资源代码',
    `name` varchar(40) NOT NULL COMMENT '资源名称',
    `weight` int NOT NULL DEFAULT '0' COMMENT '资源权重，越大越靠前',
    `type` char(2) NOT NULL COMMENT '资源类型(FM:前端菜单、BS:后台服务)',
    `url` varchar(200) DEFAULT NULL COMMENT '资源地址',
    `parent_id` bigint unsigned DEFAULT NULL COMMENT '所属上级资源id',
    `has_child` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否有子节点',
    `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
    `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_code` (`code`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `system_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_role` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '角色编号',
    `name` varchar(20) NOT NULL COMMENT '角色名称',
    `description` varchar(40) NOT NULL COMMENT '角色描述',
    `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
    `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `system_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_role_resource` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '系统角色资源权限关系编号',
    `role_id` bigint unsigned NOT NULL COMMENT '角色代码',
    `resource_id` bigint unsigned NOT NULL COMMENT '资源编号',
    `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
    `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_role_auth` (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色资源权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户编号',
    `username` varchar(100) NOT NULL COMMENT '用户登录名',
    `secret` varchar(100) DEFAULT NULL COMMENT '用户登录密码',
    `secret_expired` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '密码是否过期',
    `secret_locked` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '密码是否锁定',
    `enabled` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '账户是否可用',
    `last_login_time` datetime NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '最近登录时间',
    `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
    `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `system_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user_role` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '系统用户角色关系编号',
    `user_id` bigint unsigned NOT NULL COMMENT '用户编号',
    `role_id` bigint unsigned NOT NULL COMMENT '角色代码',
    `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
    `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_user_role` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `system_user_weixin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user_weixin` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` bigint unsigned NOT NULL COMMENT '对应用户编号',
    `open_id` varchar(200) NOT NULL COMMENT '微信openid',
    `access_token` varchar(200) NOT NULL COMMENT '微信access_token',
    `refresh_token` varchar(200) NOT NULL COMMENT '微信refresh_token',
    `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
    `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户微信信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
