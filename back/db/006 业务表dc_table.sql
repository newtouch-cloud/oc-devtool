INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000000, 'org_company', 'BASE TABLE', 1010000000000000000, 'Company', '公司', '', '1', '0', '', '0', '1', '{''columnName'':''tenant_id'', ''queryType'': ''='', ''value'': currentUser.tenant.id},{''columnName'':''id'', ''queryType'': ''='', ''value'': currentUser.company.id==''0'' ? '''': currentUser.company.id}', '[{"name":"code","comments":"已存在相同的公司编码","constraintColumn":"code","remarks":"","_XID":"row_79"}]', NULL, '', '超级管理员(super)', '2022-12-07 17:23:41', '超级管理员(super)', '2023-03-03 15:36:23', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000001, 'sys_router', 'BASE TABLE', 1010000000000000001, 'Router', '路由', '', '1', '0', '', '0', '1', '', '[]', 'a.code ASC', '', '超级管理员(super)', '2022-12-08 10:19:50', '超级管理员(super)', '2023-01-06 11:03:33', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000003, 'sys_dict_type', 'BASE TABLE', 1010000000000000003, 'DictType', '字典类型', '', '1', '0', '', '0', '1', '', '[]', 'a.update_date DESC,a.code ASC', '', '超级管理员(super)', '2022-12-09 15:50:00', '超级管理员(super)', '2023-02-14 15:08:41', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000004, 'sys_dict_item', 'BASE TABLE', 1010000000000000003, 'DictItem', '字典项', 'dict_type_id', '0', '0', 'dict_type_id', '0', '1', '', '[]', 'a.update_date DESC', '', '超级管理员(super)', '2022-12-09 15:50:00', '超级管理员(super)', '2023-02-14 15:08:41', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000005, 'user_grid', 'BASE TABLE', 1050000000000000000, 'UserGrid', '自定义布局', '', '1', '0', '', '0', '1', '', '[{"name":"用户布局唯一","comments":"当前用户已自定义布局","constraintColumn":"user_id,router_id","remarks":""}]', NULL, '', '超级管理员(super)', '2022-12-12 14:03:54', '超级管理员(super)', '2022-12-22 17:28:04', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000006, 'sys_action', 'BASE TABLE', 1010000000000000005, 'Action', '操作日志', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2022-12-12 14:11:37', '超级管理员(super)', '2023-02-14 15:04:12', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000007, 'sys_action_recycle', 'BASE TABLE', 1010000000000000005, 'ActionRecycle', '回收站', 'action_id', '0', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2022-12-12 14:11:37', '超级管理员(super)', '2023-02-14 15:04:12', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000008, 'schedule_job', 'BASE TABLE', 1010000000000000006, 'ScheduleJob', '定时任务', '', '1', '0', '', '0', '1', '', '[]', 'a.update_date DESC,a.id ASC', '', '超级管理员(super)', '2022-12-12 15:15:26', '超级管理员(super)', '2023-01-06 12:15:46', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000012, 'sys_theme', 'BASE TABLE', 1010000000000000009, 'Theme', '系统主题', '', '1', '0', '', '0', '1', '', '[{"name":"名称已存在","comments":"\\t 主题名称已存在","constraintColumn":"name,del_flag","remarks":"","_XID":"row_51"}]', NULL, '', '超级管理员(super)', '2023-01-05 18:53:27', '超级管理员(super)', '2023-01-09 17:55:45', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000013, 'org_department', 'BASE TABLE', 1010000000000000010, 'Department', '部门', '', '1', '0', '', '0', '1', '{''columnName'':''company.tenant_id'', ''queryType'': ''='', ''value'': currentUser.tenant.id}', '[{"name":"code","comments":"同一个公司下已存在相同的部门编码","constraintColumn":"company_id,code","remarks":"","_XID":"row_57"}]', 'a.sort ASC,a.code ASC', '', '超级管理员(super)', '2023-01-05 19:17:30', '超级管理员(super)', '2023-03-02 16:34:35', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000014, 'sys_resource', 'BASE TABLE', 1010000000000000011, 'Resource', '资源', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-05 19:03:01', '超级管理员(super)', '2023-01-06 10:13:12', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000015, 'sys_role', 'BASE TABLE', 1010000000000000012, 'Role', '角色', '', '1', '0', '', '0', '1', '', '[{"name":" 角色编号唯一","comments":"角色编号唯一","constraintColumn":"code,del_flag","remarks":"","_XID":"row_223"}]', 'a.code DESC,a.name ASC', '', '超级管理员(super)', '2023-01-06 10:46:33', '超级管理员(super)', '2023-02-24 13:01:37', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000016, 'user_grid', 'BASE TABLE', 1050000000000000001, 'UserGrid', '自定义布局', '', '1', '0', '', '0', '1', '', '[{"name":"唯一布局验证","comments":"当前用户已自定布局","constraintColumn":"user_id,router_id,del_flag","remarks":"","_XID":"row_119"}]', NULL, '', '超级管理员(super)', '2023-01-06 12:50:04', '超级管理员(super)', '2023-01-09 17:55:27', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000017, 'sys_seting', 'BASE TABLE', 1050000000000000002, 'SysSeting', '系统设置', '', '1', '0', '', '0', '0', '', '[]', NULL, '', '超级管理员(super)', '2023-01-06 14:02:50', '超级管理员(super)', '2023-01-06 18:33:03', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000018, 'sys_personal_theme', 'BASE TABLE', 1050000000000000003, 'PersonalTheme', '自定义主题', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-06 13:58:50', '超级管理员(super)', '2023-01-09 17:55:05', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000023, 'dc_condition', 'BASE TABLE', 1050000000000000005, 'DcCondition', '低代码-查询条件', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-11 15:23:17', '超级管理员(super)', '2023-01-11 15:29:05', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000024, 'dc_column_view', 'BASE TABLE', 1050000000000000006, 'DcColumnView', '低代码-查询列', '', '1', '0', '', '0', '0', '', '[]', NULL, '', '超级管理员(super)', '2023-01-11 15:38:05', '超级管理员(super)', '2023-03-22 14:06:54', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000026, 'ten_tenant', 'BASE TABLE', 1010000000000000022, 'Tenant', '租户', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-02-03 15:13:22', '超级管理员(super)', '2023-03-02 16:33:39', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000027, 'ten_tenant_admin', 'BASE TABLE', 1010000000000000022, 'TenantAdmin', '租户管理员', 'tenant_id', '0', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-02-03 15:13:22', '超级管理员(super)', '2023-03-02 16:33:39', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000028, 'org_company_admin', 'BASE TABLE', 1010000000000000000, 'CompanyAdmin', '公司管理员', 'company_id', '0', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-02-06 11:20:10', '超级管理员(super)', '2023-03-03 15:36:23', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000031, 'ten_tenant_app', 'BASE TABLE', 1010000000000000024, 'TenantApp', '租户应用', '', '1', '0', '', '0', '1', '{''columnName'':''tenant_id'', ''queryType'': ''='', ''value'': currentUser.tenant.id==0?'''':currentUser.tenant.id}', '[{"name":"租户已经引用该应用","comments":"","constraintColumn":"tenant_id,app_version_id","remarks":"","_XID":"row_126"}]', NULL, '', '超级管理员(super)', '2023-02-15 10:41:56', '超级管理员(super)', '2023-03-02 16:30:21', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1020000000000000032, 'gen_company_app', 'BASE TABLE', 1010000000000000025, 'CompanyApp', '公司应用版本', '', '1', '0', '', '0', '1', '{''columnName'':''company.tenant_id'', ''queryType'': ''='', ''value'': currentUser.tenant.id},{''columnName'':''compnay.id'', ''queryType'': ''='', ''value'': currentUser.company.id==''0'' ? '''': currentUser.company.id}', '[{"name":"use-again","comments":"公司已经引用该应用","constraintColumn":"company_id,tenant_app_version_id","remarks":"","_XID":"row_157"}]', NULL, '', '超级管理员(super)', '2023-02-16 10:27:15', '超级管理员(super)', '2023-03-02 16:31:31', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1289036439920697347, 'sys_router', 'BASE TABLE', 1289036439920697345, 'Router', '路由', '', '1', '0', '', '0', '1', '', '[]', 'a.code ASC', '', '超级管理员(super)', '2022-12-08 10:19:50', '超级管理员(super)', '2023-01-06 11:03:33', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1289059804542828547, 'sys_user', 'BASE TABLE', 1289059804542828545, 'User', '开发者', '', '1', '0', '', '0', '1', '', '[{"name":"邮箱唯一检查","comments":"邮箱已经被使用","constraintColumn":"email","remarks":"","_XID":"row_542"}]', 'a.id ASC', '', '超级管理员(super)', '2022-12-08 10:39:02', '超级管理员(super)', '2023-02-23 11:16:01', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1290867985774919712, 'sys_dict_type', 'BASE TABLE', 1290867985774919710, 'DictType', '字典类型', '', '1', '0', '', '0', '1', '', '[]', 'a.update_date DESC,a.code ASC', '', '超级管理员(super)', '2022-12-09 15:50:00', '超级管理员(super)', '2023-02-09 15:31:45', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1290867985774919713, 'sys_dict_item', 'BASE TABLE', 1290867985774919710, 'DictItem', '字典项', 'dict_type_id', '0', '0', 'dict_type_id', '0', '1', '', '[]', 'a.update_date DESC', '', '超级管理员(super)', '2022-12-09 15:50:00', '超级管理员(super)', '2023-02-09 15:31:45', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1295200473905373198, 'sys_action', 'BASE TABLE', 1295200473905373196, 'Action', '操作日志', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2022-12-12 14:11:37', '超级管理员(super)', '2023-02-09 16:15:51', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1295200473905373199, 'sys_action_recycle', 'BASE TABLE', 1295200473905373196, 'ActionRecycle', '回收站', 'action_id', '0', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2022-12-12 14:11:37', '超级管理员(super)', '2023-02-09 16:15:51', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1295275790451941378, 'schedule_job', 'BASE TABLE', 1295275790451941376, 'ScheduleJob', '定时任务', '', '1', '0', '', '0', '1', '', '[]', 'a.update_date DESC,a.id ASC', '', '超级管理员(super)', '2022-12-12 15:15:26', '超级管理员(super)', '2023-01-06 12:15:46', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1331132582623264772, 'sys_theme', 'BASE TABLE', 1331132582623264770, 'Theme', '系统主题', '', '1', '0', '', '0', '1', '', '[{"name":"名称已存在","comments":"\\t 主题名称已存在","constraintColumn":"name,del_flag","remarks":"","_XID":"row_51"}]', NULL, '', '超级管理员(super)', '2023-01-05 18:53:27', '超级管理员(super)', '2023-01-09 17:55:45', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1331145570604408856, 'sys_resource', 'BASE TABLE', 1331145570604408854, 'Resource', '资源', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-05 19:03:01', '超级管理员(super)', '2023-01-06 10:13:12', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1332095359672590389, 'sys_role', 'BASE TABLE', 1332095359672590387, 'Role', '角色', '', '1', '0', '', '0', '1', '', '[{"name":" 角色编号唯一","comments":"\\t 角色编号唯一","constraintColumn":"company_id,code","remarks":"","_XID":"row_223"}]', 'a.code DESC,a.name ASC', '', '超级管理员(super)', '2023-01-06 10:46:33', '超级管理员(super)', '2023-02-09 11:10:15', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1332231183718506511, 'user_grid', 'BASE TABLE', 1332231183718506509, 'UserGrid', '自定义布局', '', '1', '0', '', '0', '1', '', '[{"name":"唯一布局验证","comments":"当前用户已自定布局","constraintColumn":"user_id,router_id,del_flag","remarks":"","_XID":"row_119"}]', NULL, '', '超级管理员(super)', '2023-01-06 12:50:04', '超级管理员(super)', '2023-01-09 17:55:27', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1332267278623703084, 'sys_seting', 'BASE TABLE', 1332267278623703082, 'SysSeting', '系统设置', '', '1', '0', '', '0', '0', '', '[]', NULL, '', '超级管理员(super)', '2023-01-06 14:02:50', '超级管理员(super)', '2023-01-06 18:33:03', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1332275284442751011, 'sys_personal_theme', 'BASE TABLE', 1332275284442751009, 'PersonalTheme', '自定义主题', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-06 13:58:50', '超级管理员(super)', '2023-01-09 17:55:05', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1338205620666941461, 'dc_template', 'BASE TABLE', 1338205620666941459, 'DcTemplate', '低代码-模板', '', '1', '0', '', '0', '1', '{ columnName: ''type'', queryType: ''='', value: this.$route.params.typeParam }', '[]', NULL, '', '超级管理员(super)', '2023-01-10 13:14:17', '超级管理员(super)', '2023-01-11 13:36:19', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1339746483134570552, 'dc_scheme', 'BASE TABLE', 1339746483134570550, 'DcScheme', '低代码-代码方案', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-11 14:14:38', '超级管理员(super)', '2023-03-16 16:11:03', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1339746483134570553, 'dc_table', 'BASE TABLE', 1339746483134570550, 'DcTable', '低代码-业务表', 'scheme_id', '0', '0', '', '0', '1', '', '[]', 'a.is_main_table DESC', '', '超级管理员(super)', '2023-01-11 14:14:38', '超级管理员(super)', '2023-03-16 16:11:03', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1339770311613145255, 'dc_table_column', 'BASE TABLE', 1339770311613145253, 'DcTableColumn', '低代码-业务表字段', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-11 14:36:19', '超级管理员(super)', '2023-03-21 14:59:51', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1339823929984901123, 'dc_condition', 'BASE TABLE', 1339823929984901121, 'DcCondition', '低代码-查询条件', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-11 15:23:17', '超级管理员(super)', '2023-01-11 15:29:05', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1339835182799224838, 'dc_column_view', 'BASE TABLE', 1339835182799224836, 'DcColumnView', '低代码-查询列', '', '1', '0', '', '0', '0', '', '[]', NULL, '', '超级管理员(super)', '2023-01-11 15:38:05', '超级管理员(super)', '2023-01-13 13:23:57', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1340971493706891267, 'dc_clien_scheme', 'BASE TABLE', 1340971493706891265, 'DcClienScheme', '前端-代码方案', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-12 09:58:34', '超级管理员(super)', '2023-01-30 10:48:59', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1351544466239864850, 'prj_project', 'BASE TABLE', 1351544466239864848, 'Project', '项目', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-19 13:03:31', '超级管理员(super)', '2023-03-16 11:12:25', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1351544466239864851, 'prj_member', 'BASE TABLE', 1351544466239864848, 'Member', '项目成员', 'project_id', '0', '0', '', '0', '1', '{''columnName'':''user.id'', ''queryType'': ''='', ''value'': currentUser.id}', '[]', NULL, '', '超级管理员(super)', '2023-01-19 13:03:31', '超级管理员(super)', '2023-03-16 11:12:25', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1368089934395490306, 'micro_app', 'BASE TABLE', 1368089934395490304, 'MicroApp', '应用', '', '1', '0', '', '0', '1', '', '[{"name":"appName不能重复","comments":"appName已被使用，请重新输入。","constraintColumn":"app_key,del_flag","remarks":"","_XID":"row_121"}]', NULL, '', '超级管理员(super)', '2023-01-30 16:25:29', '超级管理员(super)', '2023-01-31 13:25:11', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1368089934395490323, 'micro_version', 'BASE TABLE', 1368089934395490321, 'MicroVersion', '应用版本', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-30 16:34:32', '超级管理员(super)', '2023-02-02 16:32:22', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1371119793305198596, 'sys_db_type', 'BASE TABLE', 1371119793305198594, 'SysDbType', '数据库类型', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-02-01 18:20:51', '超级管理员(super)', '2023-02-01 18:24:49', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1411245301922013500, 'sys_user', 'BASE TABLE', 1411245301922013498, 'User', '用户', '', '1', '0', '', '0', '1', '{''columnName'':''tenant.id, queryType: ''='' value'': currentUser.tenant.id}', '[]', 'a.id ASC', '', '超级管理员(super)', '2023-03-02 16:56:49', '超级管理员(super)', '2023-03-02 16:57:16', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1411245301922013501, 'sys_user_role', 'BASE TABLE', 1411245301922013498, 'UserRole', '用户角色', 'user_id', '0', '1', 'role_id', '0', '1', '{''columnName'':''role.company.id, queryType: ''='' value'': currentUser.company.id}', '[]', NULL, '', '超级管理员(super)', '2023-03-02 16:56:49', '超级管理员(super)', '2023-03-02 16:57:16', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1440433057862000819, 'dc_mobile_scheme', 'BASE TABLE', 1440433057862000817, 'DcMobileScheme', '移动端方案', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-03-20 10:40:48', '超级管理员(super)', '2023-03-20 10:48:08', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000000, 'org_company', 'BASE TABLE', 1210000000000000000, 'Company', '公司', '', '1', '0', '', NULL, '1', '{''columnName'':''id'', ''queryType'': ''='', ''value'': currentUser.company.id==''0'' ? '''': currentUser.company.id}', '[{"name":"code","comments":"已存在相同的公司编码","constraintColumn":"code","remarks":"","_XID":"row_79"}]', NULL, '', '超级管理员(super)', '2022-12-07 17:23:41', '超级管理员(super)', '2023-03-23 14:42:34', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000001, 'sys_router', 'BASE TABLE', 1210000000000000001, 'Router', '路由', '', '1', '0', '', NULL, '1', '', '[]', 'a.code ASC', '', '超级管理员(super)', '2022-12-08 10:19:50', '超级管理员(super)', '2023-03-23 14:34:33', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000002, 'sys_user', 'BASE TABLE', 1210000000000000002, 'User', '用户', '', '1', '0', '', NULL, '1', '', '[{"name":"用户登录名唯一检查","comments":"已经存在一个相同的登录名。","constraintColumn":"login_name","remarks":"","_XID":"row_25"},{"name":"手机号唯一检查","comments":"手机号重复！","constraintColumn":"phone","remarks":"","_XID":"row_26"}]', 'a.id ASC', '', '超级管理员(super)', '2022-12-08 10:39:02', '超级管理员(super)', '2023-03-23 14:45:57', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000003, 'sys_dict_type', 'BASE TABLE', 1210000000000000003, 'DictType', '字典类型', '', '1', '0', '', NULL, '1', '', '[]', 'a.update_date DESC,a.code ASC', '', '超级管理员(super)', '2022-12-09 15:50:00', '超级管理员(super)', '2023-03-23 14:43:14', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000004, 'sys_dict_item', 'BASE TABLE', 1210000000000000003, 'DictItem', '字典项', 'dict_type_id', '0', '0', 'dict_type_id', '0', '1', '', '[]', 'a.update_date DESC', '', '超级管理员(super)', '2022-12-09 15:50:00', '超级管理员(super)', '2023-03-23 14:43:14', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000005, 'sys_action', 'BASE TABLE', 1210000000000000004, 'Action', '操作日志', '', '1', '0', '', NULL, '1', '', '[]', NULL, '', '超级管理员(super)', '2022-12-12 14:11:37', '超级管理员(super)', '2023-03-23 14:34:15', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000006, 'sys_action_recycle', 'BASE TABLE', 1210000000000000004, 'ActionRecycle', '回收站', 'action_id', '0', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2022-12-12 14:11:37', '超级管理员(super)', '2023-03-23 14:34:15', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000007, 'schedule_job', 'BASE TABLE', 1210000000000000005, 'ScheduleJob', '定时任务', '', '1', '0', '', NULL, '1', '', '[]', 'a.update_date DESC,a.id ASC', '', '超级管理员(super)', '2022-12-12 15:15:26', '超级管理员(super)', '2023-03-23 14:34:50', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000009, 'sys_user_role', 'BASE TABLE', 1210000000000000002, 'UserRole', '用户角色', 'user_id', '0', '1', 'role_id', NULL, '1', '{''columnName'':''role.company_id'', ''queryType'': ''='', ''value'': currentUser.company.id}', '[]', NULL, '', '超级管理员(super)', '2022-12-12 16:50:30', '超级管理员(super)', '2023-03-23 14:45:57', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000011, 'sys_property_set', 'BASE TABLE', 1210000000000000008, 'PropertySet', '属性集', '', '1', '0', '', NULL, '1', '', '[]', NULL, '', '超级管理员(super)', '2022-12-19 14:55:24', '超级管理员(super)', '2023-03-23 14:34:02', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000012, 'sys_code_rule', 'BASE TABLE', 1210000000000000009, 'CodeRule', '系统编号规则', '', '1', '0', '', NULL, '1', '', '[{"name":"编码重复验证","comments":"已存在相同编码","constraintColumn":"code,del_flag","remarks":"","_XID":"row_96"}]', NULL, '', '超级管理员(super)', '2023-01-05 18:51:39', '超级管理员(super)', '2023-03-23 14:39:00', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000013, 'sys_theme', 'BASE TABLE', 1210000000000000010, 'Theme', '系统主题', '', '1', '0', '', NULL, '1', '', '[{"name":"名称已存在","comments":"\\t 主题名称已存在","constraintColumn":"name,del_flag","remarks":"","_XID":"row_51"}]', NULL, '', '超级管理员(super)', '2023-01-05 18:53:27', '超级管理员(super)', '2023-03-23 14:38:24', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000014, 'org_department', 'BASE TABLE', 1210000000000000011, 'Department', '部门', '', '1', '0', '', NULL, '1', '', '[{"name":"code","comments":"同一个公司下已存在相同的部门编码","constraintColumn":"company_id,code","remarks":"","_XID":"row_57"}]', 'a.sort ASC,a.code ASC', '', '超级管理员(super)', '2023-01-05 19:17:30', '超级管理员(super)', '2023-03-23 14:41:42', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000015, 'sys_resource', 'BASE TABLE', 1210000000000000012, 'Resource', '资源', '', '1', '0', '', NULL, '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-05 19:03:01', '超级管理员(super)', '2023-03-23 14:35:35', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000016, 'sys_company_code_rule', 'BASE TABLE', 1210000000000000013, 'CompanyCodeRule', '公司编号规则', '', '1', '0', '', NULL, '1', '{''columnName'':''company_id'', ''queryType'': ''='', ''value'': currentUser.company.id === ''0'' ? '''':currentUser.company.id}', '[{"name":"公司，编码验证","comments":"已存在相同的数据（公司，编码）","constraintColumn":"company_id,code,del_flag","remarks":"","_XID":"row_63"}]', NULL, '', '超级管理员(super)', '2023-01-06 10:21:52', '超级管理员(super)', '2023-03-23 14:37:54', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000017, 'sys_role', 'BASE TABLE', 1210000000000000014, 'Role', '角色', '', '1', '0', '', NULL, '1', '{''columnName'':''company_id'', ''queryType'': ''='', ''value'': currentUser.company.id === ''0'' ? '''':currentUser.company.id}', '[{"name":" 角色编号唯一","comments":"\\t 角色编号唯一","constraintColumn":"company_id,code","remarks":"","_XID":"row_223"}]', 'a.code DESC,a.name ASC', '', '超级管理员(super)', '2023-01-06 10:46:33', '超级管理员(super)', '2023-03-23 14:40:29', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000018, 'user_grid', 'BASE TABLE', 1210000000000000015, 'UserGrid', '自定义布局', '', '1', '0', '', NULL, '1', '', '[{"name":"唯一布局验证","comments":"当前用户已自定布局","constraintColumn":"user_id,router_id,del_flag","remarks":"","_XID":"row_119"}]', NULL, '', '超级管理员(super)', '2023-01-06 12:50:04', '超级管理员(super)', '2023-03-23 14:36:44', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000019, 'sys_seting', 'BASE TABLE', 1210000000000000016, 'SysSeting', '系统设置', '', '1', '0', '', NULL, '0', '', '[]', NULL, '', '超级管理员(super)', '2023-01-06 14:02:50', '超级管理员(super)', '2023-03-23 14:35:23', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000020, 'sys_file', 'BASE TABLE', 1210000000000000017, 'SysFile', '附件表', '', '1', '0', '', NULL, '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-06 14:05:29', '超级管理员(super)', '2023-03-23 14:35:09', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000021, 'sys_file_content', 'BASE TABLE', 1210000000000000017, 'SysFileContent', '附件存储', 'id', '0', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-06 14:11:45', '超级管理员(super)', '2023-03-23 14:35:09', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000022, 'sys_personal_theme', 'BASE TABLE', 1210000000000000018, 'PersonalTheme', '自定义主题', '', '1', '0', '', NULL, '1', '', '[]', NULL, '', '超级管理员(super)', '2023-01-06 13:58:50', '超级管理员(super)', '2023-03-23 14:36:25', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000027, 'test_leave', 'BASE TABLE', 1210000000000000022, 'Leave', '请假', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-02-22 11:01:42', '超级管理员(super)', '2023-03-23 14:44:02', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000028, 'test_expense', 'BASE TABLE', 1210000000000000023, 'Expense', '费用申请', '', '1', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-02-22 11:02:59', '超级管理员(super)', '2023-03-23 14:45:31', '0');
INSERT INTO dc_table
(id, name, source, scheme_id, class_name, comments, parent_table_fk, is_main_table, is_association, association_field, is_one_to_one, check_version, `filter`, constraint_json, order_columns, remarks, create_by, create_date, update_by, update_date, del_flag)
VALUES(1220000000000000029, 'test_expense_detail', 'BASE TABLE', 1210000000000000023, 'ExpenseDetail', '报销明细', 'expense_id', '0', '0', '', '0', '1', '', '[]', NULL, '', '超级管理员(super)', '2023-02-22 11:02:59', '超级管理员(super)', '2023-03-23 14:45:31', '0');
