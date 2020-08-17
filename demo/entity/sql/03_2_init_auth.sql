/* 新增角色 */
INSERT INTO `system_role` (`id`, `name`, `description`, `create_time`) VALUES (1, 'administrator', '系统管理员，管理端管理人员', now());
/* 新增初始管理员 */
INSERT INTO `operator_info` (`account`,`name`, `mobile`, `status`, `system_user_id`, `create_time`) VALUES ('admin', '初始管理员', '13811110001', '00', '1', now());
INSERT INTO `system_user` (`id`, `username`, `secret`, `last_login_time`, `create_time`) VALUES (1, 'admin@manage', '$2a$10$93dh0M.Tvzyu8g6KQZcLwuWkjRBgKU47usqHogSkMlSgELZjFaRv.', '2000-01-01 00:00:00', now());
INSERT INTO `system_user_role`(`user_id`, `role_id`, `create_time`) VALUES(1, 1, now());

/****************************/
/* 重置权限 */
truncate table `system_resource`;
truncate table `system_role_resource`;
/*  新增 management 平台资源  */
/* 任务管理 */
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `has_child`,`create_time`)
VALUES ('TMFM1000', '任务管理', '0', 'FM', '/traffic-management/config/', 1, now());
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM1001', '任务查询', '0', 'FM', '/traffic-management/config/', `id`, 1, now() FROM `system_resource` WHERE `code`='TMFM1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1001001', '查询流量任务列表', '0', 'BS', '/traffic-management/task/queryTaskTraffics', `id`, now() FROM `system_resource` WHERE `code`='TMFM1001';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1001002', '查询流量任务详情', '5', 'BS', '/traffic-management/task/queryTaskTraffic', `id`, now() FROM `system_resource` WHERE `code`='TMFM1001';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1001003', '完成流量任务', '10', 'BS', '/traffic-management/task/completeTaskTraffic', `id`, now() FROM `system_resource` WHERE `code`='TMFM1001';

INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM1002', '任务配置', '5', 'FM', '/traffic-management/config/', `id`, 1, now() FROM `system_resource` WHERE `code`='TMFM1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1002001', '查询流量任务配置列表', '0', 'BS', '/traffic-management/config/queryTaskTraffics', `id`, now() FROM `system_resource` WHERE `code`='TMFM1002';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1002002', '更新流量任务配置', '5', 'BS', '/traffic-management/config/updateTaskTraffic', `id`, now() FROM `system_resource` WHERE `code`='TMFM1002';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1002003', '新建流量任务配置', '10', 'BS', '/traffic-management/config/createTaskTraffic', `id`, now() FROM `system_resource` WHERE `code`='TMFM1002';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1002004', '查询流量任务配置详情', '15', 'BS', '/traffic-management/config/queryTaskTraffic', `id`, now() FROM `system_resource` WHERE `code`='TMFM1002';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1002005', '更新流量任务操作指引接口', '20', 'BS', '/traffic-management/config/updateTaskTrafficGuide', `id`, now() FROM `system_resource` WHERE `code`='TMFM1002';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1002006', '上传指引图片接口', '25', 'BS', '/traffic-management/config/uploadGuideImage', `id`, now() FROM `system_resource` WHERE `code`='TMFM1002';

INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM1003', '方案配置', '10', 'FM', '/traffic-management/config/', `id`, 1, now() FROM `system_resource` WHERE `code`='TMFM1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1003001', '查询方案配置列表', '0', 'BS', '/traffic-management/config/querySolutions', `id`, now() FROM `system_resource` WHERE `code`='TMFM1003';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1003002', '更新方案配置', '5', 'BS', '/traffic-management/config/updateSolution', `id`, now() FROM `system_resource` WHERE `code`='TMFM1003';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1003003', '新建方案配置', '10', 'BS', '/traffic-management/config/createSolution', `id`, now() FROM `system_resource` WHERE `code`='TMFM1003';

INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM1004', '平台配置', '15', 'FM', '/traffic-management/config/', `id`, 1, now() FROM `system_resource` WHERE `code`='TMFM1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1004001', '查询平台配置列表', '0', 'BS', '/traffic-management/config/queryPlatforms', `id`, now() FROM `system_resource` WHERE `code`='TMFM1004';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1004002', '查询平台配置Map', '5', 'BS', '/traffic-management/config/queryPlatformsMap', `id`, now() FROM `system_resource` WHERE `code`='TMFM1004';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1004003', '更新方案配置', '10', 'BS', '/traffic-management/config/updatePlatform', `id`, now() FROM `system_resource` WHERE `code`='TMFM1004';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1004004', '新建方案配置', '15', 'BS', '/traffic-management/config/createPlatform', `id`, now() FROM `system_resource` WHERE `code`='TMFM1004';

INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM1005', '任务包管理', '20', 'FM', '/traffic-management/taskPack/', `id`, 1, now() FROM `system_resource` WHERE `code`='TMFM1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1005001', '分面查询任务包列表', '0', 'BS', '/traffic-management/taskPack/queryTaskPacks', `id`, now() FROM `system_resource` WHERE `code`='TMFM1005';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1005002', '查询任务包详情', '5', 'BS', '/traffic-management/taskPack/queryTaskPack', `id`, now() FROM `system_resource` WHERE `code`='TMFM1005';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1005003', '查询平台配置列表', '10', 'BS', '/traffic-management/taskPack/createTaskPack', `id`, now() FROM `system_resource` WHERE `code`='TMFM1005';

INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM1006', '领取管理', '25', 'FM', '/traffic-management/taskPickup/', `id`, 1, now() FROM `system_resource` WHERE `code`='TMFM1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1006001', '分页查询任务包接取列表', '0', 'BS', '/traffic-management/taskPickup/queryTaskPackPickups', `id`, now() FROM `system_resource` WHERE `code`='TMFM1006';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1006002', '分页查询接取流量任务列表接口', '5', 'BS', '/traffic-management/taskPickup/queryTaskTrafficPickups', `id`, now() FROM `system_resource` WHERE `code`='TMFM1006';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1006003', '分页查询待审核接取流量任务列表接口', '10', 'BS', '/traffic-management/taskPickup/queryTaskTrafficPickupsReviewing', `id`, now() FROM `system_resource` WHERE `code`='TMFM1006';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1006004', '审核接取流量任务结果接口', '15', 'BS', '/traffic-management/taskPickup/reviewTaskTrafficPickup', `id`, now() FROM `system_resource` WHERE `code`='TMFM1006';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1006005', '查询任务包接取详情接口', '20', 'BS', '/traffic-management/taskPickup/queryTaskPackPickup', `id`, now() FROM `system_resource` WHERE `code`='TMFM1006';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS1006006', '查询流量任务接取详情接口', '25', 'BS', '/traffic-management/taskPickup/queryTaskTrafficPickup', `id`, now() FROM `system_resource` WHERE `code`='TMFM1006';


/* 用户类 */
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `has_child`,`create_time`)
VALUES ('TMFM2000', '用户管理', '0', 'FM', '/traffic-management/config/', 1, now());
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM2001', '用户查询', '0', 'FM', '/traffic-management/config/', `id`, 1, now() FROM `system_resource` WHERE `code`='TMFM2000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS2001001', '查询用户列表', '0', 'BS', '/traffic-management/user/queryUsers', `id`, now() FROM `system_resource` WHERE `code`='TMFM2001';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS2001002', '冻结用户', '5', 'BS', '/traffic-management/user/freezeUser', `id`, now() FROM `system_resource` WHERE `code`='TMFM2001';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS2001003', '解冻用户', '10', 'BS', '/traffic-management/user/unfreezeUser', `id`, now() FROM `system_resource` WHERE `code`='TMFM2001';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS2001004', '查询用户积分', '15', 'BS', '/traffic-management/user/queryUserPoint', `id`, now() FROM `system_resource` WHERE `code`='TMFM2001';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS2001005', '查询用户钱包', '20', 'BS', '/traffic-management/user/queryUserWallet', `id`, now() FROM `system_resource` WHERE `code`='TMFM2001';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS2001006', '查询用户钱包明细', '25', 'BS', '/traffic-management/user/queryUserWalletDetails', `id`, now() FROM `system_resource` WHERE `code`='TMFM2001';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS2001007', '查询用户详情', '30', 'BS', '/traffic-management/user/queryUserInfo', `id`, now() FROM `system_resource` WHERE `code`='TMFM2001';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS2001008', '查询用户积分明细列表', '35', 'BS', '/traffic-management/user/queryUserPointDetails', `id`, now() FROM `system_resource` WHERE `code`='TMFM2001';

INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM2002', '用户充值查询', '5', 'FM', '/traffic-management/config/', `id`, 1, now() FROM `system_resource` WHERE `code`='TMFM2000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMFM2002001', '查询用户充值列表', '0', 'BS', '/traffic-management/user/queryUserDeposits', `id`, now() FROM `system_resource` WHERE `code`='TMFM2002';

INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM2003', '充值套餐配置', '10', 'FM', '/traffic-management/config/', `id`, 1, now() FROM `system_resource` WHERE `code`='TMFM2000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS2003001', '查询充值套餐列表', '0', 'BS', '/traffic-management/config/queryDepositSets', `id`, now() FROM `system_resource` WHERE `code`='TMFM2003';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS2003002', '更新充值套餐', '10', 'BS', '/traffic-management/config/updateDepositSet', `id`, now() FROM `system_resource` WHERE `code`='TMFM2003';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS2003003', '新建充值套餐', '15', 'BS', '/traffic-management/config/createDepositSet', `id`, now() FROM `system_resource` WHERE `code`='TMFM2003';

INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM2004', '提现管理', '15', 'FM', '/traffic-management/user/', `id`, 1, now() FROM `system_resource` WHERE `code`='TMFM2000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS2004001', '分页查询用户提现申请', '0', 'BS', '/traffic-management/user/queryUserWithdraws', `id`, now() FROM `system_resource` WHERE `code`='TMFM2004';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS2004002', '查询用户提现申请详情', '5', 'BS', '/traffic-management/user/queryUserWithdraw', `id`, now() FROM `system_resource` WHERE `code`='TMFM2004';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS2004003', '审核用户提现申请', '10', 'BS', '/traffic-management/user/adjustUserWithdraw', `id`, now() FROM `system_resource` WHERE `code`='TMFM2004';

/* 系统权限类 */
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `has_child`,`create_time`)
VALUES ('TMFM9000', '系统管理', '40', 'FM', '/traffic-management/operator/', 1, now());
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM9001', '操作员管理', '0', 'FM', '/traffic-management/operator/', `id`, 1, now() FROM `system_resource` WHERE `code`='TMFM9000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS9001001', '查询操作员列表', '0', 'BS', '/traffic-management/operator/queryOperators', `id`, now() FROM `system_resource` WHERE `code`='TMFM9001';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS9001002', '更新操作员', '5', 'BS', '/traffic-management/operator/updateOperator', `id`, now() FROM `system_resource` WHERE `code`='TMFM9001';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS9001003', '新增操作员', '10', 'BS', '/traffic-management/operator/createOperator', `id`, now() FROM `system_resource` WHERE `code`='TMFM9001';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS9001004', '重置操作员密码', '15', 'BS', '/traffic-management/operator/resetPassword', `id`, now() FROM `system_resource` WHERE `code`='TMFM9001';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `create_time`)
SELECT 'TMBS9001005', '修改本操作员密码', '20', 'BS', '/traffic-management/operator/updatePassword', `id`, now() FROM `system_resource` WHERE `code`='TMFM9001';

INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM9002', '角色管理', '5', 'FM', '/traffic-management/operator/', `id`, 1, now() FROM `system_resource` WHERE `code`='TMFM9000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM9003', '权限管理', '10', 'FM', '/traffic-management/operate/', `id`, 1, now() FROM `system_resource` WHERE `code`='TMFM9000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM9003001', '权限树查询', '0', 'FM', '/traffic-management/system/getResources', `id`, 0, now() FROM `system_resource` WHERE `code`='TMFM9003';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM9003002', '权限更新', '5', 'BS', '/traffic-management/system/updateResource', `id`, 0, now() FROM `system_resource` WHERE `code`='TMFM9003';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM9003003', '权限新增', '10', 'BS', '/traffic-management/system/createResource', `id`, 0, now() FROM `system_resource` WHERE `code`='TMFM9003';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM9003004', '权限删除', '15', 'BS', '/traffic-management/system/deleteResource', `id`, 0, now() FROM `system_resource` WHERE `code`='TMFM9003';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM9003005', '用户权限查询', '50', 'BS', '/traffic-management/system/getAuthorization', `id`, 0, now() FROM `system_resource` WHERE `code`='TMFM9003';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)

SELECT 'TMFM9004', '参数管理', '15', 'FM', '/traffic-management/system/', `id`, 1, now() FROM `system_resource` WHERE `code`='TMFM9000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM9004001', '参数查询', '0', 'BS', '/traffic-management/system/queryParameters', `id`, 0, now() FROM `system_resource` WHERE `code`='TMFM9004';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM9004002', '管理端参数查询', '5', 'BS', '/traffic-management/system/queryParametersForManagement', `id`, 0, now() FROM `system_resource` WHERE `code`='TMFM9004';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM9004003', '参数更新', '10', 'BS', '/traffic-management/system/updateParameter', `id`, 0, now() FROM `system_resource` WHERE `code`='TMFM9004';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TMFM9004004', '参数新境', '15', 'BS', '/traffic-management/system/createParameter', `id`, 0, now() FROM `system_resource` WHERE `code`='TMFM9004';

/* 绑定管理员与资源 */
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM1000';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM1001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1001001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1001002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1001003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM1002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1002001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1002002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1002003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1002004';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1002005';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1002006';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM1003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1003001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1003002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1003003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM1004';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1004001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1004002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1004003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1004004';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM1005';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1005001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1005002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1005003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1005004';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM1006';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1006001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1006002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1006003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1006004';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1006005';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS1006006';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM2000';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM2001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS2001001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS2001002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS2001003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS2001004';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS2001005';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS2001006';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS2001007';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS2001008';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM2002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM2002001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM2003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS2003001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS2003002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS2003003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM2004';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS2004001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS2004002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS2004003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM9000';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM9001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS9001001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS9001002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS9001003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS9001004';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMBS9001005';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM9002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM9003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM9003001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM9003002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM9003003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM9003004';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM9003005';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM9004';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM9004001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM9004002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM9004003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 1, id, now() FROM `system_resource` WHERE `code`='TMFM9004004';

/****************************/
/*  新增 publisher 平台资源  */
/* 配置类 */
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `has_child`,`create_time`)
VALUES ('TPBS1000', '配置读取', '0', 'BS', '/traffic-publisher/config/', 1, now());
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TPBS100001', '读取充值套餐', '0', 'BS', '/traffic-publisher/config/queryDepositSets', `id`, 0, now() FROM `system_resource` WHERE `code`='TPBS1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TPBS100002', '读取任务平台', '10', 'BS', '/traffic-publisher/config/queryTaskPlatforms', `id`, 0, now() FROM `system_resource` WHERE `code`='TPBS1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TPBS100003', '读取任务配置', '15', 'BS', '/traffic-publisher/config/queryTaskTrafficsWithSolutions', `id`, 0, now() FROM `system_resource` WHERE `code`='TPBS1000';
/* 用户类 */
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `has_child`,`create_time`)
VALUES ('TPBS2000', '用户操作', '0', 'BS', '/traffic-publisher/user/', 1, now());
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TPBS200001', '用户余额查询', '0', 'BS', '/traffic-publisher/user/queryUserPoint', `id`, 0, now() FROM `system_resource` WHERE `code`='TPBS2000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TPBS200002', '用户充值查询列表', '5', 'BS', '/traffic-publisher/user/queryUserDeposits', `id`, 0, now() FROM `system_resource` WHERE `code`='TPBS2000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TPBS200003', '用户充值', '10', 'BS', '/traffic-publisher/user/createDeposit', `id`, 0, now() FROM `system_resource` WHERE `code`='TPBS2000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TPBS200004', '用户充值查询详情', '15', 'BS', '/traffic-publisher/user/queryUserDeposit', `id`, 0, now() FROM `system_resource` WHERE `code`='TPBS2000';
/* 任务类 */
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `has_child`,`create_time`)
VALUES ('TPBS3000', '任务操作', '0', 'BS', '/traffic-publisher/task/', 1, now());
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TPBS300001', '查询任务汇总统计', '0', 'BS', '/traffic-publisher/task/queryTaskStatistic', `id`, 0, now() FROM `system_resource` WHERE `code`='TPBS3000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TPBS300002', '查询任务列表', '5', 'BS', '/traffic-publisher/task/queryTaskTraffics', `id`, 0, now() FROM `system_resource` WHERE `code`='TPBS3000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TPBS300003', '创建新任务', '10', 'BS', '/traffic-publisher/task/createTaskTraffic', `id`, 0, now() FROM `system_resource` WHERE `code`='TPBS3000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TPBS300004', '计算任务总消费', '15', 'BS', '/traffic-publisher/task/calcConsumeFee', `id`, 0, now() FROM `system_resource` WHERE `code`='TPBS3000';

/* 绑定用户与资源 */
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TPBS1000';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TPBS100001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TPBS100002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TPBS100003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TPBS2000';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TPBS200001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TPBS200002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TPBS200003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TPBS200004';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TPBS3000';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TPBS300001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TPBS300002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TPBS300003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TPBS300004';

/************************/
/*  新增 pickup 平台资源  */
/* 用户类 */
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `has_child`,`create_time`)
VALUES ('TUBS1000', '用户操作', '0', 'BS', '/traffic-pickup/user/', 1, now());
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS1000001', '查询用户详情信息接口', '0', 'BS', '/traffic-pickup/user/queryUserInfo', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS1000002', '绑定手机号接口', '5', 'BS', '/traffic-pickup/user/bindMobile', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS1000003', '发送绑定手机号验证码接口', '10', 'BS', '/traffic-pickup/user/sendBindMobileVerifyCode', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS1000004', '查询用户钱包信息', '15', 'BS', '/traffic-pickup/user/queryWallet', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS1000005', '分页查询用户钱包明细列表', '20', 'BS', '/traffic-pickup/user/queryWalletDetails', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS1000006', '分页查询用户提现列表', '25', 'BS', '/traffic-pickup/user/queryWithdraws', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS1000007', '用户提现申请', '30', 'BS', '/traffic-pickup/user/applyWithdraw', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS1000008', '查询用户提现账号', '35', 'BS', '/traffic-pickup/user/queryUserWithdrawAccounts', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS1000009', '新增提现账号', '40', 'BS', '/traffic-pickup/user/createUserWithdrawAccount', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS1000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS1000010', '更新用户提现账号', '35', 'BS', '/traffic-pickup/user/updateUserWithdrawAccount', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS1000';
/* 任务类 */
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `has_child`,`create_time`)
VALUES ('TUBS2000', '任务操作', '0', 'BS', '/traffic-pickup/task/', 1, now());
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS2000001', '分页查询已接取任务包列表接口', '0', 'BS', '/traffic-pickup/task/queryPickedUpTaskPacks', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS2000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS2000002', '查询已接取任务包详情接口', '5', 'BS', '/traffic-pickup/task/queryPickedUpTaskPack', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS2000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS2000003', '接取任务包接口', '10', 'BS', '/traffic-pickup/task/pickUpTaskPack', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS2000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS2000004', '提交审核接口', '15', 'BS', '/traffic-pickup/task/updateReview', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS2000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS2000005', '上传审核图片接口', '20', 'BS', '/traffic-pickup/task/uploadReviewImage', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS2000';
INSERT INTO `system_resource` (`code`, `name`, `weight`, `type`, `url`, `parent_id`, `has_child`, `create_time`)
SELECT 'TUBS2000006', '查询已接取流量任务详情接口', '25', 'BS', '/traffic-pickup/task/queryPickedUpTaskTraffic', `id`, 0, now() FROM `system_resource` WHERE `code`='TUBS2000';

/* 绑定用户与资源 */
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS1000';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS1000001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS1000002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS1000003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS1000004';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS1000005';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS1000006';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS1000007';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS1000008';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS1000009';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS1000010';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS2000001';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS2000002';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS2000003';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS2000004';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS2000005';
INSERT INTO `system_role_resource` (`role_id`, `resource_id`, `create_time`)
SELECT 3, id, now() FROM `system_resource` WHERE `code`='TUBS2000006';
