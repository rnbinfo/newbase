/************************************/
/* 系统参数 */
truncate table `system_parameter`;
INSERT INTO `system_parameter` (`type`, `code`, `description`, `key`, `value`, `create_time`) VALUES ('100', '00001', '用户默认参数-默认头像', 'http://localhost/avatar.jpg', '头像地址', now()); /*样例*/

/************************************/
/* 数据字典 */
truncate table `system_data_dictionary`;
INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('10001', '用户状态', '001', '正常', '1', '001', now());
INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('10001', '用户状态', '090', '冻结', '1', '001', now());
INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('10001', '用户状态', '099', '销户', '1', '001', now());

INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('20001', '能源渠道', 'TUA', '团油', '1', '001', now());

INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('21001', '站点状态', '001', '正常', '1', '001', now());
INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('21001', '站点状态', '090', '关闭', '1', '001', now());

INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('21002', '站点类型', 'OIL', '油站', '1', '001', now());

INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('30001', '能源类型', 'PET', '汽油', '1', '001', now());
INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('30001', '能源类型', 'DIE', '柴油', '1', '001', now());
INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('30001', '能源类型', 'LNG', '天然气', '1', '001', now());

INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('70001', '接入方操作员状态', '001', '正常', '1', '001', now());
INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('70001', '接入方操作员状态', '090', '冻结', '1', '001', now());
INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('70001', '接入方操作员状态', '099', '销户', '1', '001', now());

INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('80001', '操作员状态', '001', '正常', '1', '001', now());
INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('80001', '操作员状态', '090', '冻结', '1', '001', now());
INSERT INTO `system_data_dictionary` (`type`, `description`, `key`, `value`, `system`, `status`, `create_time`) VALUES ('80001', '操作员状态', '099', '销户', '1', '001', now());
