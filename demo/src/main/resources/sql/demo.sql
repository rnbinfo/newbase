use test1;
create table test1.test1_user(
  `id` int,
  `name` varchar(255)
);

create table test1.sys_user(
  `id` int,
  `username` varchar(255),
  `password` varchar(255)
);

create table test1.sys_resource(
  `id` int,
  `resource_url` varchar(255)
);

create table test1.sys_role (
  `id` int,
  `role_code` varchar(255)
);

create table test1.sys_role_resource (
    `id` int,
    `role_id` int,
    `resource_id` int
);

create table test1.sys_user_role (
    `id` int,
    `user_id` int,
    `role_id` int
);

insert into sys_role values (1, 'anonymous');

insert into sys_resource values (1, '/test-aop');

insert into sys_role_resource values (1, 1, 1);

use test2;
create table test2.test2_user(
  `id` int,
  `name` varchar(255)
);