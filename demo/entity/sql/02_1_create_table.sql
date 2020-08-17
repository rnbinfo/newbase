/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+08:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
                             `id` bigint(20) unsigned  NOT NULL AUTO_INCREMENT COMMENT 'ID',
                             `account` varchar(20) NOT NULL COMMENT '登录账号',
                             `nickname` varchar(100)  NOT NULL COMMENT '昵称',
                             `mobile` char(11) NOT NULL COMMENT '手机号',
                             `avatar` varchar(255) NOT NULL COMMENT '头像',
                             `status` char(3) DEFAULT NULL COMMENT '状态,001-正常,090-冻结,099-销户',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uniq_provider_user` (`account`),
                             KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `management_noauth_operator`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `management_noauth_operator` (
                                     `id` bigint(20) unsigned  NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                     `account` varchar(50) NOT NULL COMMENT '操作员登录账号',
                                     `name` varchar(20) NOT NULL COMMENT '操作员姓名',
                                     `system_user_id` bigint(20) unsigned  NOT NULL COMMENT '关联系统用户id',
                                     `status` char(3) NOT NULL COMMENT '状态:000-正常,090-关闭',
                                     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                     `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                     PRIMARY KEY (`id`),
                                     UNIQUE KEY `idx_account` (`account`),
                                     KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='无授权操作员';
/*!40101 SET character_set_client = @saved_cs_client */;