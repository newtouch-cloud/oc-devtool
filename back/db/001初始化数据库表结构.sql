SET SESSION FOREIGN_KEY_CHECKS=0;


/* Create Tables */

CREATE TABLE app_master_data_type
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 主数据类型id
	master_data_type_id bigint(21) unsigned NOT NULL COMMENT '主数据类型id',
	-- 应用
	app_id bigint(21) unsigned COMMENT '应用',
	-- 默认工作模式（消费者:0  生产者：1）
	default_work_model varchar(8) NOT NULL COMMENT '默认工作模式（消费者:0  生产者：1）',
	-- 是否支持生产者工作模式（支持：1 不支持：0）
	produce_work_model varchar(8) COMMENT '是否支持生产者工作模式（支持：1 不支持：0）',
	-- 是否支持消费者模式（支持：1 不支持：0）
	consumer_work_model varchar(8) COMMENT '是否支持消费者模式（支持：1 不支持：0）',
	level varchar(2) DEFAULT '0',
	-- 审核状态
	audit_status varchar(8) DEFAULT '0' COMMENT '审核状态',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE app_standard_field
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 应用
	app_id bigint(21) unsigned NOT NULL COMMENT '应用',
	-- 主数据类型id
	master_data_type_id bigint(21) unsigned NOT NULL COMMENT '主数据类型id',
	-- 主数据标准字段id
	standard_field_id bigint(21) unsigned NOT NULL COMMENT '主数据标准字段id',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE dc_clien_scheme
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 项目
	project_id bigint(21) unsigned NOT NULL COMMENT '项目',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 后端方案
	scheme_id bigint(21) unsigned NOT NULL COMMENT '后端方案',
	-- 模板
	template_id bigint(21) unsigned COMMENT '模板',
	-- 模板json
	template_json longtext COMMENT '模板json',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE dc_column_view
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 业务表
	dc_table_id bigint(21) unsigned NOT NULL COMMENT '业务表',
	-- 用户
	user_id bigint(21) unsigned NOT NULL COMMENT '用户',
	-- 路由
	router_id bigint(21) unsigned NOT NULL COMMENT '路由',
	-- 列参数
	json longtext COMMENT '列参数',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE dc_condition
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 用户
	user_id bigint(21) unsigned NOT NULL COMMENT '用户',
	-- 路由
	router_id bigint(21) unsigned NOT NULL COMMENT '路由',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 排序
	sort bigint(21) unsigned NOT NULL COMMENT '排序',
	-- 条件
	conditions text NOT NULL COMMENT '条件',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE dc_mobile_scheme
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 项目
	project_id bigint(21) unsigned NOT NULL COMMENT '项目',
	-- 名称
	name varchar(128) COMMENT '名称',
	-- 后端方案
	scheme_id bigint(21) unsigned NOT NULL COMMENT '后端方案',
	-- 模板
	template_id bigint(21) unsigned COMMENT '模板',
	-- 模板json
	template_json longtext COMMENT '模板json',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE dc_scheme
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 项目
	project_id bigint(21) unsigned NOT NULL COMMENT '项目',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 生成包路径
	package_name varchar(512) NOT NULL COMMENT '生成包路径',
	-- 生成模块名
	module_name varchar(128) NOT NULL COMMENT '生成模块名',
	-- 生成子模块名
	sub_module_name varchar(128) COMMENT '生成子模块名',
	-- 生成功能名
	function_name varchar(128) NOT NULL COMMENT '生成功能名',
	-- 模板
	template_id bigint(21) unsigned COMMENT '模板',
	-- 模板json
	template_json longtext COMMENT '模板json',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE dc_table
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 表来源
	source varchar(128) NOT NULL COMMENT '表来源',
	-- 代码方案
	scheme_id bigint(21) unsigned NOT NULL COMMENT '代码方案',
	-- 实体类名称
	class_name varchar(128) NOT NULL COMMENT '实体类名称',
	comments varchar(64),
	-- 关联父表外键
	parent_table_fk varchar(64) COMMENT '关联父表外键',
	-- 0：否，1：是
	is_main_table varchar(1) DEFAULT '1' NOT NULL COMMENT '0：否，1：是',
	-- 0：否，1：是
	is_association varchar(1) DEFAULT '0' COMMENT '0：否，1：是',
	association_field varchar(64),
	-- （0：否，1：是）
	is_one_to_one varchar(1) DEFAULT '0' COMMENT '（0：否，1：是）',
	check_version varchar(1) DEFAULT '0' NOT NULL,
	-- 筛选条件
	filter varchar(2048) COMMENT '筛选条件',
	-- 表约束
	constraint_json text COMMENT '表约束',
	-- 排序字段
	order_columns varchar(2048) COMMENT '排序字段',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE dc_table_column
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 业务表
	dc_table_id bigint(21) unsigned NOT NULL COMMENT '业务表',
	-- 描述
	comments varchar(64) NOT NULL COMMENT '描述',
	-- 列字段类型
	jdbc_type varchar(64) NOT NULL COMMENT '列字段类型',
	-- JAVA类型
	java_type varchar(64) NOT NULL COMMENT 'JAVA类型',
	-- 自定义类型
	custom_type varchar(64) COMMENT '自定义类型',
	-- 本方案
	current_scheme_table varchar(64) COMMENT '本方案',
	-- JAVA属性名
	java_field varchar(512) NOT NULL COMMENT 'JAVA属性名',
	-- 子对象属性名
	child_fields longtext COMMENT '子对象属性名',
	-- 关联字段
	associated_field varchar(64) COMMENT '关联字段',
	-- 关联条件
	associated_filter varchar(2000) COMMENT '关联条件',
	-- 默认值
	def_val varchar(2048) COMMENT '默认值',
	-- 是否主键
	is_pk varchar(1) NOT NULL COMMENT '是否主键',
	-- 是否可为空
	is_null varchar(1) NOT NULL COMMENT '是否可为空',
	-- 是否可插入
	is_insert varchar(1) NOT NULL COMMENT '是否可插入',
	-- 是否可编辑
	is_edit varchar(2) NOT NULL COMMENT '是否可编辑',
	-- 其他设置（扩展属性）
	settings varchar(2048) COMMENT '其他设置（扩展属性）',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE dc_template
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 类别
	type varchar(1) NOT NULL COMMENT '类别',
	-- 模板json
	json longtext COMMENT '模板json',
	-- 详情图
	file_id bigint(21) unsigned COMMENT '详情图',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE master_data_batch_records
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 主数据批记录
	data_batch varchar(64) COMMENT '主数据批记录',
	-- 租户id
	tenant_id bigint(21) unsigned NOT NULL COMMENT '租户id',
	-- 主数据类型id
	master_data_type_id bigint(21) unsigned NOT NULL COMMENT '主数据类型id',
	-- 应用来源
	source_app_id bigint(21) unsigned NOT NULL COMMENT '应用来源',
	-- 接收时间
	receive_date datetime COMMENT '接收时间',
	-- 需要发送到应的应用
	target_app_id text NOT NULL COMMENT '需要发送到应的应用',
	-- 推送状态
	push_status varchar(8) COMMENT '推送状态',
	-- 最后发送时间
	last_send_time datetime COMMENT '最后发送时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE master_data_detail
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 主数据记录表id
	master_data_records_id bigint(21) unsigned NOT NULL COMMENT '主数据记录表id',
	-- 租户id
	tenant_id bigint(21) unsigned NOT NULL COMMENT '租户id',
	-- 主数据类型id
	master_data_type_id bigint(21) unsigned COMMENT '主数据类型id',
	-- 主数据key
	data_key varchar(64) NOT NULL COMMENT '主数据key',
	-- 数据版本
	data_version varchar(16) NOT NULL COMMENT '数据版本',
	-- standard_field表中id字段
	standard_field_id bigint(21) unsigned NOT NULL COMMENT 'standard_field表中id字段',
	-- 主数据详情字段
	master_data_info_filed varchar(32) NOT NULL COMMENT '主数据详情字段',
	-- 字段对应值
	master_data_info_value varchar(128) COMMENT '字段对应值',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE master_data_json
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 主数据批记录表id
	master_data_batch_records_id bigint(21) unsigned NOT NULL COMMENT '主数据批记录表id',
	-- json数据
	data_json text COMMENT 'json数据',
	-- 是否校验 1：已经校验   0：未校验
	analysis varchar(4) DEFAULT '0' NOT NULL COMMENT '是否校验 1：已经校验   0：未校验',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE master_data_push_interface_records
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 主数据推送记录表id
	master_data_push_records_id bigint(21) unsigned NOT NULL COMMENT '主数据推送记录表id',
	-- 推送时间
	push_date datetime NOT NULL COMMENT '推送时间',
	push_data text,
	-- 推送状态
	push_status varchar(8) COMMENT '推送状态',
	-- 推送失败信息
	push_fail_info text COMMENT '推送失败信息',
	-- 返回数据
	return_data

 text COMMENT '返回数据',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE master_data_push_records
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 主数据批记录表id
	master_data_batch_records_id bigint(21) unsigned NOT NULL COMMENT '主数据批记录表id',
	-- 租户id
	tenant_id bigint(21) unsigned NOT NULL COMMENT '租户id',
	-- 主数据类型id
	master_data_type_id bigint(21) unsigned NOT NULL COMMENT '主数据类型id',
	-- 主数据key
	data_key varchar(64) NOT NULL COMMENT '主数据key',
	-- 数据版本
	data_version varchar(16) COMMENT '数据版本',
	-- 接收应用
	target_app_id bigint(21) unsigned NOT NULL COMMENT '接收应用',
	-- 推送状态
	push_status varchar(8) COMMENT '推送状态',
	-- 最后发送时间
	last_send_time datetime COMMENT '最后发送时间',
	-- 推送失败信息
	push_fail_info text COMMENT '推送失败信息',
	-- 推送失败次数
	push_fail_size int(4) COMMENT '推送失败次数',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE master_data_records
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 主数据批记录表id
	master_data_batch_records_id bigint(21) unsigned NOT NULL COMMENT '主数据批记录表id',
	-- 序号
	order_number int(4) NOT NULL COMMENT '序号',
	-- 租户id
	tenant_id bigint(21) unsigned NOT NULL COMMENT '租户id',
	-- 主数据类型id
	master_data_type_id bigint(21) unsigned COMMENT '主数据类型id',
	-- 主数据key
	data_key varchar(64) NOT NULL COMMENT '主数据key',
	-- 数据版本
	data_version varchar(16) NOT NULL COMMENT '数据版本',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE master_data_type
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 主数据code
	code varchar(16) NOT NULL COMMENT '主数据code',
	-- 主数据名称
	name varchar(32) NOT NULL COMMENT '主数据名称',
	-- 是否为系统主数据 0：不是 1：是
	is_system tinyint(1) NOT NULL COMMENT '是否为系统主数据 0：不是 1：是',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


-- 应用
CREATE TABLE micro_app
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 开发者
	developer_id bigint(21) unsigned NOT NULL COMMENT '开发者',
	-- AppKey
	app_key varchar(20) NOT NULL COMMENT 'AppKey',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 全称
	full_name varchar(256) NOT NULL COMMENT '全称',
	-- 应用图标
	icon_id bigint NOT NULL COMMENT '应用图标',
	-- 禁用
	is_locked varchar(1) DEFAULT '0' NOT NULL COMMENT '禁用',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '应用' DEFAULT CHARACTER SET utf8;


-- 应用版本
CREATE TABLE micro_version
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 应用
	app_id bigint(21) unsigned NOT NULL COMMENT '应用',
	-- 版本类型
	type_id bigint(21) unsigned NOT NULL COMMENT '版本类型',
	-- 数据库类型
	db_type_id bigint(21) unsigned NOT NULL COMMENT '数据库类型',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	url varchar(1024) NOT NULL,
	-- 移动端地址
	mobil_url varchar(1024) NOT NULL COMMENT '移动端地址',
	-- 禁用
	is_locked varchar(1) DEFAULT '0' NOT NULL COMMENT '禁用',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '应用版本' DEFAULT CHARACTER SET utf8;


CREATE TABLE notice_receive_site
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 标识
	send_id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 消息接收者id
	receive_by bigint(21) unsigned NOT NULL COMMENT '消息接收者id',
	-- 已读
	read_status varchar(1) COMMENT '已读',
	-- 读取时间
	read_time varchar(24) COMMENT '读取时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE notice_record_email
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 消息接收者
	receiver bigint(20) unsigned NOT NULL COMMENT '消息接收者',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE notice_send
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 发送人id
	sender_id bigint(21) unsigned NOT NULL COMMENT '发送人id',
	-- 标题
	title varchar(50) NOT NULL COMMENT '标题',
	-- 是否发送站内
	is_site varchar(1) NOT NULL COMMENT '是否发送站内',
	-- 是否发送微信
	is_wechat varchar(1) NOT NULL COMMENT '是否发送微信',
	-- 是否发送邮件
	is_email varchar(1) NOT NULL COMMENT '是否发送邮件',
	-- 是否发送短信
	is_sms varchar(1) NOT NULL COMMENT '是否发送短信',
	-- 站内内容
	site_content longtext COMMENT '站内内容',
	-- 邮件内容
	email_content longtext COMMENT '邮件内容',
	-- 微信内容
	wechat_content varchar(500) COMMENT '微信内容',
	-- 短信内容
	sms_content varchar(500) COMMENT '短信内容',
	-- 接收人
	received_by longtext NOT NULL COMMENT '接收人',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE notice_send_timer
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE notice_unsubscribe
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 用户
	user_id bigint(21) unsigned NOT NULL COMMENT '用户',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- 项目成员
CREATE TABLE prj_member
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 项目
	project_id bigint(21) unsigned NOT NULL COMMENT '项目',
	-- 团队成员
	user_id bigint(21) unsigned NOT NULL COMMENT '团队成员',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '项目成员' DEFAULT CHARACTER SET utf8;


-- 项目
CREATE TABLE prj_project
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 项目负责人
	user_id bigint(21) unsigned NOT NULL COMMENT '项目负责人',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 数据库用户名
	db_username varchar(64) COMMENT '数据库用户名',
	-- 数据库密码
	db_password varchar(64) COMMENT '数据库密码',
	-- 数据库地址
	db_url varchar(128) COMMENT '数据库地址',
	-- 数据库类型
	db_type_id bigint(21) unsigned NOT NULL COMMENT '数据库类型',
	-- 数据库IP
	db_ip varchar(64) COMMENT '数据库IP',
	-- 数据库端口
	db_port varchar(10) COMMENT '数据库端口',
	-- 数据库名称
	db_name varchar(64) COMMENT '数据库名称',
	-- 服务名
	cloud_name varchar(64) NOT NULL COMMENT '服务名',
	-- 状态
	status varchar(1) DEFAULT '0' NOT NULL COMMENT '状态',
	-- （0：否，1：是）
	multil_ten varchar(1) DEFAULT '0' NOT NULL COMMENT '（0：否，1：是）',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '项目' DEFAULT CHARACTER SET utf8;


CREATE TABLE production_relation
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 租户id
	tenant_id bigint(21) unsigned NOT NULL COMMENT '租户id',
	-- 主数据类型id
	master_data_type_id bigint(21) unsigned NOT NULL COMMENT '主数据类型id',
	-- 应用
	app_id bigint(21) unsigned NOT NULL COMMENT '应用',
	-- 模式 定义枚举（消费  生产） 为消费时，租户消费对接数据
	data_model varchar(16) NOT NULL COMMENT '模式 定义枚举（消费  生产） 为消费时，租户消费对接数据',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE schedule_job
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 名称
	name varchar(128) COMMENT '名称',
	-- 编号
	code varchar(64) COMMENT '编号',
	-- 任务表达式
	cron varchar(128) COMMENT '任务表达式',
	-- 任务状态
	status varchar(64) COMMENT '任务状态',
	-- 是否禁用
	is_lock varchar(1) COMMENT '是否禁用',
	-- 定时任务类全路径
	java_class varchar(150) NOT NULL COMMENT '定时任务类全路径',
	-- 定时任务方法名称
	java_method varchar(30) NOT NULL COMMENT '定时任务方法名称',
	-- 定时任务方法入参
	params varchar(100) COMMENT '定时任务方法入参',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE standard_field
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 主数据类型id
	master_data_type_id bigint(21) unsigned NOT NULL COMMENT '主数据类型id',
	-- 字段code
	filed_code varchar(16) NOT NULL COMMENT '字段code',
	-- 字段名称
	filed_name varchar(32) COMMENT '字段名称',
	-- 字段类型
	filed_type varchar(16) COMMENT '字段类型',
	-- 字段长度
	filed_size varchar(4) NOT NULL COMMENT '字段长度',
	-- 字段精度
	filed_precision varchar(4) COMMENT '字段精度',
	-- 是否必填 1必填  0非必填 默认必填
	required tinyint(1) DEFAULT 1 NOT NULL COMMENT '是否必填 1必填  0非必填 默认必填',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	-- 1为主键 0不为
	data_key tinyint(1) DEFAULT 0 COMMENT '1为主键 0不为',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


-- 操作日志
CREATE TABLE sys_action
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 操作类型
	action_type varchar(128) NOT NULL COMMENT '操作类型',
	-- 对象类型
	object_type bigint(21) unsigned NOT NULL COMMENT '对象类型',
	-- 业务对象
	object_id bigint(21) unsigned NOT NULL COMMENT '业务对象',
	-- 对象名称
	object_name varchar(128) COMMENT '对象名称',
	-- 已还原
	status varchar(1) DEFAULT '0' NOT NULL COMMENT '已还原',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '操作日志' DEFAULT CHARACTER SET utf8;


-- 回收站
CREATE TABLE sys_action_recycle
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 日志
	action_id bigint(21) unsigned NOT NULL COMMENT '日志',
	-- 表名
	table_name varchar(128) NOT NULL COMMENT '表名',
	-- 业务对象
	object_id bigint(21) unsigned NOT NULL COMMENT '业务对象',
	-- 对象名称
	object_name varchar(128) COMMENT '对象名称',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '回收站' DEFAULT CHARACTER SET utf8;


CREATE TABLE sys_code_rule
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 编号
	code varchar(64) NOT NULL COMMENT '编号',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 规则
	rule_def varchar(512) NOT NULL COMMENT '规则',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


-- 功能收藏夹
CREATE TABLE sys_collect
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 用户
	user_id bigint(21) unsigned COMMENT '用户',
	-- 路由
	router_id bigint(21) unsigned NOT NULL COMMENT '路由',
	-- 排序
	sort bigint(21) unsigned DEFAULT 0 NOT NULL COMMENT '排序',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '功能收藏夹' DEFAULT CHARACTER SET utf8;


CREATE TABLE sys_company_code_rule
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 公司
	company_id bigint(21) unsigned NOT NULL COMMENT '公司',
	-- 系统编号规则
	sys_rule_id bigint(21) unsigned NOT NULL COMMENT '系统编号规则',
	-- 编号
	code varchar(64) COMMENT '编号',
	-- 名称
	name varchar(128) COMMENT '名称',
	-- 规则
	rule_def varchar(512) COMMENT '规则',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


-- 数据权限
CREATE TABLE sys_data_permission
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 路由
	router_id bigint(21) unsigned NOT NULL COMMENT '路由',
	-- 角色
	role_id bigint(21) unsigned NOT NULL COMMENT '角色',
	-- 元数据
	meta_id bigint(21) unsigned NOT NULL COMMENT '元数据',
	-- 权限条件
	conditions text COMMENT '权限条件',
	PRIMARY KEY (id)
) COMMENT = '数据权限' DEFAULT CHARACTER SET utf8;


-- 数据库类型
CREATE TABLE sys_db_type
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 驱动
	driver varchar(128) NOT NULL COMMENT '驱动',
	-- 连接地址规则
	rule varchar(128) NOT NULL COMMENT '连接地址规则',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '数据库类型' DEFAULT CHARACTER SET utf8;


-- 字典项
CREATE TABLE sys_dict_item
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 字典类
	dict_type_id bigint(21) unsigned NOT NULL COMMENT '字典类',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 字典项值
	value varchar(128) NOT NULL COMMENT '字典项值',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '字典项' DEFAULT CHARACTER SET utf8;


-- 字典类型
CREATE TABLE sys_dict_type
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 编号
	code varchar(64) NOT NULL COMMENT '编号',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 是否系统级
	is_system varchar(1) DEFAULT '0' NOT NULL COMMENT '是否系统级',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '字典类型' DEFAULT CHARACTER SET utf8;


-- 附件表
CREATE TABLE sys_file
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 业务表
	object_id bigint(21) unsigned NOT NULL COMMENT '业务表',
	-- 名称
	name varchar(128) COMMENT '名称',
	-- 文件类型
	file_type varchar(64) COMMENT '文件类型',
	-- 文件大小
	file_size bigint(21) unsigned COMMENT '文件大小',
	-- 文件路径
	file_url varchar(255) COMMENT '文件路径',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id),
	UNIQUE (id)
) COMMENT = '附件表' DEFAULT CHARACTER SET utf8;


-- 附件存储
CREATE TABLE sys_file_content
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 图片字节
	byte longblob COMMENT '图片字节',
	-- 文件类型
	file_type varchar(64) COMMENT '文件类型',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id),
	UNIQUE (id)
) COMMENT = '附件存储' DEFAULT CHARACTER SET utf8;


-- 自定义主题
CREATE TABLE sys_personal_theme
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 用户
	user_id bigint(21) unsigned NOT NULL COMMENT '用户',
	-- 主题
	theme text NOT NULL COMMENT '主题',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id),
	UNIQUE (user_id)
) COMMENT = '自定义主题' DEFAULT CHARACTER SET utf8;


-- 资源
CREATE TABLE sys_resource
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 编号
	code varchar(64) NOT NULL COMMENT '编号',
	-- URL
	url varchar(128) NOT NULL COMMENT 'URL',
	-- 权限
	permission varchar(32) COMMENT '权限',
	-- 可以授权
	can_permission varchar(1) DEFAULT '1' NOT NULL COMMENT '可以授权',
	-- 默认资源
	is_default varchar(1) DEFAULT '0' NOT NULL COMMENT '默认资源',
	-- 禁用
	is_locked varchar(1) DEFAULT '0' COMMENT '禁用',
	-- 路由
	router_id bigint(21) unsigned NOT NULL COMMENT '路由',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	-- 扩展属性
	properties varchar(1024) COMMENT '扩展属性',
	PRIMARY KEY (id)
) COMMENT = '资源' DEFAULT CHARACTER SET utf8;


-- 角色资源
CREATE TABLE sys_resource_permission
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 角色
	role_id bigint(21) unsigned NOT NULL COMMENT '角色',
	-- 资源
	resource_id bigint(21) unsigned NOT NULL COMMENT '资源',
	PRIMARY KEY (id)
) COMMENT = '角色资源' DEFAULT CHARACTER SET utf8;


-- 角色
CREATE TABLE sys_role
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 编号
	code varchar(64) NOT NULL COMMENT '编号',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 禁用
	is_locked varchar(1) DEFAULT '0' NOT NULL COMMENT '禁用',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '角色' DEFAULT CHARACTER SET utf8;


-- 路由
CREATE TABLE sys_router
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 编号
	code varchar(64) COMMENT '编号',
	-- URL
	url varchar(128) COMMENT 'URL',
	-- 父级
	parent_id bigint(21) unsigned NOT NULL COMMENT '父级',
	-- 全标识
	ids varchar(2048) NOT NULL COMMENT '全标识',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 排序
	sort bigint(21) unsigned DEFAULT 0 NOT NULL COMMENT '排序',
	-- 可以授权
	can_permission varchar(1) DEFAULT '1' NOT NULL COMMENT '可以授权',
	-- 禁用
	is_locked varchar(1) DEFAULT '0' NOT NULL COMMENT '禁用',
	-- 扩展属性
	properties varchar(1024) NOT NULL COMMENT '扩展属性',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	-- 系统标识
	system_code varchar(64) NOT NULL COMMENT '系统标识',
	-- 图片地址
	icon_id bigint(21) unsigned COMMENT '图片地址',
	PRIMARY KEY (id)
) COMMENT = '路由' DEFAULT CHARACTER SET utf8;


-- 角色路由
CREATE TABLE sys_router_permission
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 角色
	role_id bigint(21) unsigned NOT NULL COMMENT '角色',
	-- 路由
	router_id bigint(21) unsigned NOT NULL COMMENT '路由',
	PRIMARY KEY (id)
) COMMENT = '角色路由' DEFAULT CHARACTER SET utf8;


CREATE TABLE sys_seting
(
	-- 标识
	id bigint(21) unsigned NOT NULL AUTO_INCREMENT COMMENT '标识',
	sys_name varchar(255),
	sys_abbrname varchar(128),
	-- 登录背景色
	login_bgcolor varchar(20) COMMENT '登录背景色',
	-- 登陆布局
	login_layout varchar(20) COMMENT '登陆布局',
	-- 项目标志
	project_logo bigint(21) unsigned COMMENT '项目标志',
	-- 登录标志
	login_logo bigint(21) unsigned COMMENT '登录标志',
	-- 系统标志
	sys_logo bigint(21) unsigned COMMENT '系统标志',
	-- 系统图标
	favicon bigint(21) unsigned COMMENT '系统图标',
	-- 登录背景图
	login_bg bigint(21) unsigned COMMENT '登录背景图',
	-- 登录配图
	login_graph bigint(21) unsigned COMMENT '登录配图',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id),
	UNIQUE (id)
) DEFAULT CHARACTER SET utf8;


-- 系统主题
CREATE TABLE sys_theme
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 主题
	theme text NOT NULL COMMENT '主题',
	-- 默认
	is_default varchar(1) DEFAULT '1' COMMENT '默认',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '系统主题' DEFAULT CHARACTER SET utf8;


-- 用户
CREATE TABLE sys_user
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 角色
	role_id bigint(21) unsigned NOT NULL COMMENT '角色',
	-- 名称
	name varchar(128) NOT NULL COMMENT '名称',
	-- 组织机构
	organ varchar(1024) NOT NULL COMMENT '组织机构',
	-- 登录账号
	login_name varchar(10) NOT NULL COMMENT '登录账号',
	-- 密码
	login_password varchar(64) COMMENT '密码',
	-- 禁用
	is_locked varchar(1) DEFAULT '0' NOT NULL COMMENT '禁用',
	-- 邮箱地址
	email varchar(36) NOT NULL COMMENT '邮箱地址',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '用户' DEFAULT CHARACTER SET utf8;


CREATE TABLE user_grid
(
	-- 标识
	id bigint(21) unsigned NOT NULL COMMENT '标识',
	-- 用户id
	user_id bigint(21) unsigned NOT NULL COMMENT '用户id',
	-- 路由id
	router_id bigint(21) unsigned NOT NULL COMMENT '路由id',
	grid_info varchar(1000),
	-- 选中展示的模块id
	default_check varchar(200) COMMENT '选中展示的模块id',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date varchar(24) NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date varchar(24) NOT NULL COMMENT '更新时间',
	-- 删除标记（0：正常；1：删除）
	del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;


CREATE TABLE worker_node
(
	-- 标识
	id bigint(21) unsigned NOT NULL AUTO_INCREMENT COMMENT '标识',
	-- host name
	HOST_NAME varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'host name',
	-- port
	PORT varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'port',
	-- node type: ACTUAL or CONTAINER
	TYPE int NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
	-- launch date
	LAUNCH_DATE date NOT NULL COMMENT 'launch date',
	-- modified time
	MODIFIED timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT 'modified time',
	-- created time
	CREATED timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT 'created time',
	PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;



/* Create Foreign Keys */

ALTER TABLE dc_clien_scheme
	ADD FOREIGN KEY (scheme_id)
	REFERENCES dc_scheme (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dc_mobile_scheme
	ADD FOREIGN KEY (scheme_id)
	REFERENCES dc_scheme (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dc_table
	ADD FOREIGN KEY (scheme_id)
	REFERENCES dc_scheme (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dc_column_view
	ADD FOREIGN KEY (dc_table_id)
	REFERENCES dc_table (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dc_table_column
	ADD FOREIGN KEY (dc_table_id)
	REFERENCES dc_table (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dc_clien_scheme
	ADD FOREIGN KEY (template_id)
	REFERENCES dc_template (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dc_mobile_scheme
	ADD FOREIGN KEY (template_id)
	REFERENCES dc_template (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dc_scheme
	ADD FOREIGN KEY (template_id)
	REFERENCES dc_template (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE master_data_json
	ADD FOREIGN KEY (master_data_batch_records_id)
	REFERENCES master_data_batch_records (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE master_data_push_records
	ADD FOREIGN KEY (master_data_batch_records_id)
	REFERENCES master_data_batch_records (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE master_data_records
	ADD FOREIGN KEY (master_data_batch_records_id)
	REFERENCES master_data_batch_records (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE master_data_push_interface_records
	ADD FOREIGN KEY (master_data_push_records_id)
	REFERENCES master_data_push_records (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE app_master_data_type
	ADD FOREIGN KEY (master_data_type_id)
	REFERENCES master_data_type (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE app_standard_field
	ADD FOREIGN KEY (master_data_type_id)
	REFERENCES master_data_type (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE master_data_batch_records
	ADD FOREIGN KEY (master_data_type_id)
	REFERENCES master_data_type (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE master_data_push_records
	ADD FOREIGN KEY (master_data_type_id)
	REFERENCES master_data_type (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE production_relation
	ADD FOREIGN KEY (master_data_type_id)
	REFERENCES master_data_type (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE standard_field
	ADD FOREIGN KEY (master_data_type_id)
	REFERENCES master_data_type (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE micro_version
	ADD FOREIGN KEY (app_id)
	REFERENCES micro_app (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE notice_receive_site
	ADD CONSTRAINT notice_receive_site_ibfk_1 FOREIGN KEY (send_id)
	REFERENCES notice_send (id)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE dc_clien_scheme
	ADD FOREIGN KEY (project_id)
	REFERENCES prj_project (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dc_mobile_scheme
	ADD FOREIGN KEY (project_id)
	REFERENCES prj_project (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dc_scheme
	ADD FOREIGN KEY (project_id)
	REFERENCES prj_project (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE prj_member
	ADD FOREIGN KEY (project_id)
	REFERENCES prj_project (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE app_standard_field
	ADD FOREIGN KEY (standard_field_id)
	REFERENCES standard_field (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_action_recycle
	ADD FOREIGN KEY (action_id)
	REFERENCES sys_action (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE micro_version
	ADD FOREIGN KEY (db_type_id)
	REFERENCES sys_db_type (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE prj_project
	ADD FOREIGN KEY (db_type_id)
	REFERENCES sys_db_type (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE micro_version
	ADD FOREIGN KEY (type_id)
	REFERENCES sys_dict_item (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_dict_item
	ADD FOREIGN KEY (dict_type_id)
	REFERENCES sys_dict_type (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_file_content
	ADD FOREIGN KEY (id)
	REFERENCES sys_file (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_resource_permission
	ADD FOREIGN KEY (resource_id)
	REFERENCES sys_resource (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_data_permission
	ADD FOREIGN KEY (role_id)
	REFERENCES sys_role (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_resource_permission
	ADD FOREIGN KEY (role_id)
	REFERENCES sys_role (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_router_permission
	ADD FOREIGN KEY (role_id)
	REFERENCES sys_role (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_user
	ADD FOREIGN KEY (role_id)
	REFERENCES sys_role (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_data_permission
	ADD FOREIGN KEY (router_id)
	REFERENCES sys_router (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_resource
	ADD FOREIGN KEY (router_id)
	REFERENCES sys_router (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_router_permission
	ADD FOREIGN KEY (router_id)
	REFERENCES sys_router (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE micro_app
	ADD FOREIGN KEY (developer_id)
	REFERENCES sys_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE prj_member
	ADD FOREIGN KEY (user_id)
	REFERENCES sys_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE prj_project
	ADD FOREIGN KEY (user_id)
	REFERENCES sys_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



/* Create Indexes */

CREATE INDEX send_id USING BTREE ON notice_receive_site (send_id ASC);
CREATE INDEX receive_by USING BTREE ON notice_receive_site (receive_by ASC);
CREATE INDEX receiver USING BTREE ON notice_record_email (receiver ASC);
CREATE INDEX sender_id USING BTREE ON notice_send (sender_id ASC);
CREATE INDEX user USING BTREE ON notice_unsubscribe (user_id ASC);
CREATE INDEX sys_collect_user_id ON sys_collect (user_id ASC);
CREATE INDEX sys_user_login_name ON sys_user (login_name ASC);



