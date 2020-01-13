
/*Table structure for table `test_user` */

DROP TABLE IF EXISTS `test_user`;

CREATE TABLE `test_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `title` varchar(32) DEFAULT NULL COMMENT '职称职别',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `gender` varchar(32) DEFAULT NULL COMMENT '性别',
  `date_of_birth` date DEFAULT NULL COMMENT '出生时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '1:已删除,0:未删除',
  `sys_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sys_create_user` varchar(255) DEFAULT NULL,
  `sys_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sys_update_user` varchar(255) DEFAULT NULL,
  `record_version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7013 DEFAULT CHARSET=utf8;

/*Data for the table `test_user` */

insert  into `test_user`(`id`,`name`,`phone`,`title`,`email`,`gender`,`date_of_birth`,`deleted`,`sys_create_time`,`sys_create_user`,`sys_update_time`,`sys_update_user`,`record_version`) values (3520,'刘**','13800000000',NULL,NULL,'1','1984-09-25',0,'2019-03-28 14:53:44','admin','2019-05-31 11:17:55','admin',0),(6999,'张*','13800000000','TEST','test@qq.com','1','1990-06-10',0,'2019-03-28 14:53:44','admin','2019-05-31 11:17:55','admin',0),(7003,'test_add1','13800000000','TEST1','test1@qq.com','1','1947-11-01',0,'2019-06-04 23:17:48',NULL,'2019-11-07 10:40:43',NULL,0),(7004,'test_add2','13800000000','test2','test2-dsa@qq.com','1','1958-07-01',0,'2019-06-04 23:17:48',NULL,'2019-11-07 11:13:06',NULL,0);

