drop table if exists `system_user`;
create table `system_user`
(
    `id`             bigint unsigned auto_increment not null comment '用户编号',
    `username`       varchar(100)                   not null comment '用户登录名',
    `secret`         varchar(100)                   not null comment '用户登录密码',
    `secret_expired` tinyint(1) unsigned            not null default 0 comment '密码是否过期',
    `secret_locked`  tinyint(1) unsigned            not null default 0 comment '密码是否锁定',
    `enabled`        tinyint(1) unsigned            not null default 1 comment '账户是否可用',
    `create_time`    timestamp                      null comment '创建时间',
    `modify_time`    timestamp                      null comment '修改时间',
    primary key (`id`),
    unique uniq_username (`username`)
);
alter table `system_user`
    comment '系统用户';

drop table if exists `system_role`;
create table `system_role`
(
    `id`          bigint unsigned auto_increment not null comment '角色编号',
    `name`        varchar(20)                    not null comment '角色名称',
    `description` varchar(40)                    not null comment '角色描述',
    `create_time` timestamp                      null comment '创建时间',
    `modify_time` timestamp                      null comment '修改时间',
    primary key (`id`)
);
alter table `system_role`
    comment '系统角色';

drop table if exists `system_resource`;
create table `system_resource`
(
    `id`          bigint unsigned auto_increment not null comment '资源编号',
    `code`        varchar(200)                   not null comment '资源代码',
    `name`        varchar(40)                    not null comment '资源名称',
    `weight`      int                            not null default 0 comment '资源权重，越大越靠前',
    `type`        char(2)                        not null comment '资源类型(FM:前端菜单、BS:后台服务)',
    `url`         varchar(200)                            default null comment '资源地址',
    `parent_id`   BIGINT UNSIGNED                NULL COMMENT '所属上级资源id',
    `has_child`   TINYINT UNSIGNED               NOT NULL DEFAULT 0 COMMENT '是否有子节点',
    `create_time` timestamp                      null comment '创建时间',
    `modify_time` timestamp                      null comment '修改时间',
    primary key (`id`),
    INDEX idx_parent_id (`parent_id`)
);
alter table `system_resource`
    comment '系统资源表';

drop table if exists `system_user_role`;
create table `system_user_role`
(
    `id`          bigint unsigned auto_increment not null comment '系统用户角色关系编号',
    `user_id`     bigint unsigned                not null comment '用户编号',
    `role_id`     bigint unsigned                not null comment '角色代码',
    `create_time` timestamp                      null comment '创建时间',
    `modify_time` timestamp                      null comment '修改时间',
    primary key (`id`),
    UNIQUE uniq_user_role (`user_id`, `role_id`)
);
alter table `system_user_role`
    comment '系统用户角色关系表';

drop table if exists `system_role_resource`;
create table `system_role_resource`
(
    `id`          bigint unsigned auto_increment not null comment '系统角色资源权限关系编号',
    `role_id`     bigint unsigned                not null comment '角色代码',
    `resource_id` bigint unsigned                not null comment '资源编号',
    `create_time` timestamp                      null comment '创建时间',
    `modify_time` timestamp                      null comment '修改时间',
    primary key (`id`),
    UNIQUE uniq_role_auth (`role_id`, `resource_id`)
);
alter table `system_role_resource`
    comment '系统角色资源权限表';
