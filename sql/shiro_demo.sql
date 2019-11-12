CREATE TABLE `admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `last_login_ip` varchar(255) DEFAULT NULL COMMENT '最后登陆ip',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `mobile_phone` varchar(255) NOT NULL COMMENT '用户手机号',
  `password` varchar(255) DEFAULT NULL COMMENT '登陆密码',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `status` int(2) DEFAULT NULL COMMENT '用户状态 0-正常 1-非法',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `department_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_username` (`username`),
  KEY `FK_department_id` (`department_id`),
  CONSTRAINT `FK_department_id` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户表'


CREATE TABLE `admin_role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '角色描述',
  `role` VARCHAR(255) NOT NULL COMMENT '角色标识',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '角色表';


CREATE TABLE `admin_permission` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` VARCHAR(255) DEFAULT NULL COMMENT '权限标题',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '权限名称字符串',
  `parent_id` BIGINT(20) DEFAULT NULL COMMENT '父级id',
  `sort` INT(11) DEFAULT NULL COMMENT '排序',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '权限配置表';

CREATE TABLE `department` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `leader_id` BIGINT(20) DEFAULT NULL COMMENT '部门领导id',
  `name` VARCHAR(255) NOT NULL COMMENT '部门名称',
  `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_department_name` (`name`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '部门配置表';