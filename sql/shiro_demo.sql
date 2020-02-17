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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户表';
insert  into `admin_user`(`id`,`email`,`last_login_ip`,`last_login_time`,`mobile_phone`,`password`,`role_id`,`status`,`username`,`department_id`,`create_time`,`remark`) values (1,NULL,'127.0.0.1','2019-11-14 11:54:29','15194607793','47943eeeab5ed28f8a93f7fb77ec5111',1,0,'root',1,'2019-11-13 18:25:19',NULL);


CREATE TABLE `admin_role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '角色描述',
  `role` VARCHAR(255) NOT NULL COMMENT '角色标识',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '角色表';
insert  into `admin_role`(`id`,`description`,`role`) values (1,'系统管理员','系统管理员'),(61,'客服','客服'),(62,'技术','技术'),(84,'测试','测试');


CREATE TABLE `admin_permission` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` VARCHAR(255) DEFAULT NULL COMMENT '权限标题',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '权限名称字符串',
  `parent_id` BIGINT(20) DEFAULT NULL COMMENT '父级id',
  `sort` INT(11) DEFAULT NULL COMMENT '排序',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '权限配置表';
insert  into `admin_permission`(`id`,`title`,`name`,`parent_id`,`sort`,`description`) values (1,'测试权限','admin',0,0,NULL),(2,'角色管理','system:role:all',8,0,'角色管理'),(3,'用户管理','system:employee:page-query',8,0,'用户管理'),(4,'部门管理','system:department:all',8,0,'部门管理'),(6,'菜单管理','system:role:permission:all',8,0,'菜单管理'),(7,'系统日志','system:access-log:page-query',8,0,'系统日志'),(8,'系统管理','system-------',0,6,'系统管理'),(27,'创建修改角色','system:role:merge',8,0,'创建修改角色'),(28,'更改角色权限','system:role:permission:update',8,0,'更改角色权限'),(29,'角色拥有权限','system:role:permission',8,0,'角色拥有权限'),(30,'全部权限树','system:role:permission:all',8,0,'全部权限树'),(31,'创建更改后台用户','system:employee:merge',8,0,'创建更改后台用户'),(32,'后台用户详情','system:employee:detail',8,0,'后台用户详情'),(34,'系统日志详情','system:access-log:detail',8,0,'系统日志详情'),(86,'部门详情','system:department:detail',8,0,'部门详情'),(109,'删除部门','system:department:deletes',8,0,'删除部门'),(110,'增加/修改权限','system:permission:merge',8,0,'增加权限'),(111,'权限管理','system:permission:page-query',8,0,'分页查询权限'),(112,'权限详情','system:permission:details',8,0,'权限详情'),(113,'权限删除','system:permission:deletes',8,0,'权限删除'),(118,'分页查询','system:vote:page-query',8,0,'分页查询'),(119,'admin更改密码','system:employee:update-password',8,0,'admin更改密码'),(139,'删除用户','system:employee:deletes',8,0,'批量删除用户'),(147,'系统信息维护','system:data-dictionary',8,0,'系统信息管理'),(156,'测试权限','测试名称',18,0,'描述'),(176,'角色删除','system:role:deletes',8,0,'角色删除'),(213,'用户管理','--------user',0,0,'用户管理'),(214,'查询用户','user:page-query',213,0,'查询用户'),(228,'系统参数配置','system:data-dictionary',8,0,'系统参数配置'),(234,'数据统计','data:statistics',0,0,'数据统计'),(235,'账单统计','data:account',234,0,'项目总入账出账统计'),(237,'账单详情','data:detail',234,0,'项目账单详情');


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
insert  into `department`(`id`,`create_time`,`leader_id`,`name`,`remark`,`update_time`) values (1,'2019-11-13 18:27:38',0,'开发部','自立开发部',NULL);


CREATE TABLE `admin_role_permission` (
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `rule_id` bigint(20) NOT NULL COMMENT '权限id',
  UNIQUE KEY `UKplesprlvm1sob8nl9yc5rgh3m` (`role_id`,`rule_id`),
  KEY `FK52rddd3qje4p49iubt08gplb5` (`role_id`) USING BTREE,
  KEY `FKqf3fhgl5mjqqb0jeupx7yafh0` (`rule_id`) USING BTREE,
  CONSTRAINT `admin_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `admin_role` (`id`),
  CONSTRAINT `admin_role_permission_ibfk_2` FOREIGN KEY (`rule_id`) REFERENCES `admin_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色权限中间表';
insert  into `admin_role_permission`(`role_id`,`rule_id`) values (84,2),(84,3),(84,4),(84,6),(84,7),(84,8),(84,27),(84,28),(84,29),(84,30),(84,31),(84,32),(84,34),(84,86),(84,109),(84,110),(84,111),(84,112),(84,113),(84,118),(84,119),(84,139),(84,147),(84,176),(84,213),(84,214),(84,228),(84,234),(84,235);