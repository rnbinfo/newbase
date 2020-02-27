create table `sys_user` (
  `id` bigint unsigned  auto_increment not null comment '用户编号',
  `username` varchar(100) not null comment '用户登录名',
  `password` varchar(100) not null comment '用户登录密码',
  `create_time` timestamp null comment '创建时间',
  `modify_time` timestamp null comment '修改时间',
  primary key (`id`)
);
alter table `sys_user` comment '系统用户';

create table `sys_role` (
  `id` bigint unsigned  auto_increment not null comment '角色编号',
  `role_code` varchar(20) not null comment '角色代码',
  `role_name` varchar(40) not null comment '角色名称',
  `create_time` timestamp null comment '创建时间',
  `modify_time` timestamp null comment '修改时间',
  primary key (`id`)
);
alter table `sys_role` comment '系统角色';

create table `sys_resource` (
  `id` bigint unsigned  auto_increment not null comment'资源编号',
  `resource_code` varchar(200) not null comment '资源代码',
  `resource_name` varchar(40) not null comment '资源名称',
  `resource_group` varchar(32) default null comment '资源组编号',
  `resource_weight` int not null default 0 comment '资源权重，越大越靠前',
  `resource_type` char(1) not null comment '资源类型(M:菜单、A:非菜单)',
  `resource_url` varchar(200) default null comment '资源地址',
  `parent_id` BIGINT UNSIGNED NULL COMMENT '所属上级资源id',
  `create_time` timestamp null comment '创建时间',
  `modify_time` timestamp null comment '修改时间',
  primary key (`id`)
);
alter table `sys_resource` comment '系统资源表';

create table `sys_user_role` (
  `id` bigint unsigned  auto_increment not null comment '系统用户角色关系编号',
  `user_id` bigint unsigned not null comment '用户编号',
  `role_id` bigint unsigned not null comment '角色代码',
  `create_time` timestamp null comment '创建时间',
  `modify_time` timestamp null comment '修改时间',
  primary key (`id`)
);
alter table `sys_user_role` comment '系统用户角色关系表';

create table `sys_role_resource` (
  `id` bigint unsigned  auto_increment not null comment '系统角色资源权限关系编号',
  `role_id` bigint unsigned not null comment '角色代码',
  `resource_id` bigint unsigned not null comment '资源编号',
  `create_time` timestamp null comment '创建时间',
  `modify_time` timestamp null comment '修改时间',
  primary key (`id`)
);
alter table `sys_role_resource` comment '系统角色资源权限表';
