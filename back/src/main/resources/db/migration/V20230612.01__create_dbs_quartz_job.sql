CREATE TABLE `dbs_quartz_job` (
  `id` bigint(21) unsigned NOT NULL COMMENT '标识',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `job_class` varchar(64) NOT NULL COMMENT 'Class全限定类名',
  `cron_expression` varchar(32) NOT NULL COMMENT '七子表达式',
  `tenant_id` bigint(21) unsigned NOT NULL  COMMENT '租户id',
  `source_app_id` bigint(21) unsigned NOT NULL  COMMENT '源app',
  `target_app_id` bigint(21) unsigned NOT NULL  COMMENT '目标app',
  `data_type_id` bigint(21) unsigned NOT NULL  COMMENT '主数据',
  `data_select_interface`  varchar(64) NOT NULL COMMENT '数据查询接口',
  `status` varchar(1) NOT NULL COMMENT '状态;0 停止 1 正常',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` varchar(24) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` varchar(24) NOT NULL COMMENT '更新时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='定时任务';