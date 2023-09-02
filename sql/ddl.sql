drop table if exists biz_employer;
create table biz_employer
(
    id          bigint(20) not null auto_increment comment '雇员编号',
    name        varchar(50) default null comment '雇主姓名',
    leader      varchar(20) default null comment '负责人',
    phone       varchar(11) default null comment '联系电话',
    email       varchar(50) default null comment '邮箱',
    status      int(1)      default 0 comment '状态（0-正常 1-停用）',
    deleted     int(1)      default 0 comment '删除标志（0-存在 1-删除）',
    create_by   varchar(64) default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64) default '' comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 200 comment = '雇主表';
