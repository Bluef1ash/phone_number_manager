-- 添加基础权限
INSERT INTO `phone_number`.`pm_privilege`
(`id`, `name`, `constraint_auth`, `uri`, `parent_id`, `icon_name`, `orders`, `is_display`, `create_time`, `update_time`)
VALUES (1, '社区居民相关', 'communityResidentTitle', '', 0, '', 0, 1, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(2, '社区相关', 'communityTitle', '', 0, '', 0, 1, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(3, '街道相关', 'subdistrictTitle', '', 0, '', 0, 1, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(4, '系统相关', 'systemTitle', '', 0, '', 0, 1, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(5, '社区居民管理', 'communityResidentAction', '', 1, 'fa fa-phone', 0, 1, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(6, '社区楼长管理', 'dormitoryManagerAction', '', 1, 'fa fa-university', 0, 1, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(7, '社区管理', 'communityAction', '', 2, 'fa fa-building', 0, 1, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(8, '街道管理', 'subdistrictAction', '', 3, 'fa fa-laptop', 0, 1, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(9, '用户和角色管理', 'userAndRoleAction', '', 4, 'fa fa-user', 0, 1, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(10, '系统管理', 'systemAction', '', 4, 'fa fa-cog', 0, 1, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(11, '居民电话列表', 'communityResidentList', '/resident', 5, 'fa fa-list', 0, 1, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(12, '添加居民信息', 'createCommunityResident', '/resident/create', 5, 'fa fa-plus', 0, 1, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(13, '修改居民信息', 'editCommunityResident', '/resident/edit', 5, 'fa fa-pencil-square-o', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(14, '添加、修改居民信息处理', 'communityResidentCreateOrEditHandle', '/resident', 5, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(15, '通过AJAX技术删除居民信息', 'deleteCommunityResidentForAjax', '/resident', 5, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(16, '导入居民信息进系统', 'communityResidentImportAsSystem', '/resident/import', 5, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(17, '导出居民信息到Excel', 'communityResidentSaveAsExcel', '/resident/download', 5, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(18, '使用AJAX技术加载社区居民列表', 'findCommunityResidentsForAjax', '/resident/list', 5, 'fa fa-list', 0, 0,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(19, '楼长信息列表', 'dormitoryManagerList', '/dormitory', 6, 'fa fa-list', 0, 1, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(20, '添加楼长信息', 'createDormitoryManager', '/dormitory/create', 6, 'fa fa-plus', 0, 1, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(21, '修改楼长信息', 'editDormitoryManager', '/dormitory/edit', 6, 'fa fa-pencil-square-o', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(22, '添加、修改楼长信息处理', 'dormitoryManagerCreateOrEditHandle', '/dormitory', 6, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(23, '通过AJAX技术删除楼长信息', 'deleteDormitoryManagerForAjax', '/dormitory', 6, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(24, '导入楼长信息进系统', 'dormitoryManagerImportAsSystem', '/dormitory/import', 6, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(25, '导出楼长信息到Excel', 'dormitoryManagerSaveAsExcel', '/dormitory/download', 6, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(26, '使用AJAX技术加载社区楼长列表', 'findDormitoryManagersForAjax', '/dormitory/list', 6, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(27, '社区列表', 'communityList', '/community', 7, 'fa fa-list', 0, 1, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(28, '添加社区信息', 'createCommunity', '/community/create', 7, 'fa fa-plus', 0, 1, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(29, '修改社区信息', 'editCommunity', '/community/edit', 7, 'fa fa-pencil-square-o', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(30, '添加、修改社区信息处理', 'communityCreateOrEditHandle', '/community', 7, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(31, '通过AJAX技术删除社区信息', 'deleteCommunityForAjax', '/community', 7, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(32, '通过街道编号使用AJAX技术列出社区居委会', 'findCommunitiesForAjax', '/community/select', 7, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(33, '更改社区是否允许更删改信息', 'chooseSubmitForAjax', '/community/choose_submit', 7, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(34, '通过社区编号使用Ajax技术加载社区及所属街道', 'loadCommunityForAjax', '/community/load', 7, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(35, '社区分包人列表', 'subcontractorList', '/community/subcontractor', 7, 'fa fa-list', 0, 1, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(36, '添加社区分包人信息', 'createSubcontractor', '/community/subcontractor/create', 7, 'fa fa-plus', 0, 1,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(37, '修改社区分包人信息', 'editSubcontractor', '/community/subcontractor/edit', 7, 'fa fa-pencil-square-o', 0, 0,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(38, '添加、修改社区分包人信息处理', 'subcontractorCreateOrEditHandle', '/community/subcontractor', 7, '', 0, 0,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(39, '通过AJAX技术删除社区分包人信息', 'deleteSubcontractorForAjax', '/community/subcontractor', 7, '', 0, 0,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(40, '街道列表', 'subdistrictList', '/subdistrict', 8, 'fa fa-list', 0, 1, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(41, '添加街道信息', 'createSubdistrict', '/subdistrict/create', 8, 'fa fa-plus', 0, 1, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(42, '修改街道信息', 'editSubdistrict', '/subdistrict/edit', 8, 'fa fa-pencil-square-o', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(43, '添加、修改街道信息处理', 'subdistrictCreateOrEditHandle', '/subdistrict', 8, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(44, '通过AJAX技术删除街道信息', 'deleteSubdistrictForAjax', '/subdistrict', 8, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(45, '通过Ajax技术获取街道信息', 'getSubdistrictForAjax', '/subdistrict/load', 8, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(46, '系统用户列表', 'systemUserList', '/system/user_role/user', 9, 'fa fa-list', 0, 1, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(47, '系统用户锁定与解锁', 'systemUserLockedForAjax', '/system/user_role/user/lock', 9, '', 0,
 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(48, '添加系统用户', 'createSystemUser', '/system/user_role/user/create', 9, 'fa fa-plus', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(49, '修改系统用户', 'editSystemUser', '/system/user_role/user/edit', 9, 'fa fa-pencil-square-o', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(50, '添加与修改处理系统用户', 'systemUserAddOrEditHandle', '/system/user_role/user', 9, '', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(51, '通过AJAX技术删除系统用户', 'deleteSystemUserForAjax', '/system/user_role/user', 9, '', 0, 0,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(52, '使用Ajax技术获取所有系统用户', 'getSystemUsersForAjax', '/system/user_role/user/load', 9, 'fa fa-list', 0, 0,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(53, '系统用户角色列表', 'systemUserRoleList', '/system/user_role/role', 9, 'fa fa-list', 0, 1, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(54, '添加系统用户角色', 'createSystemUserRole', '/system/user_role/role/create', 9, 'fa fa-plus', 0, 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(55, '修改系统用户角色', 'editSystemUserRole', '/system/user_role/user/edit', 9, 'fa fa-pencil-square-o', 0, 0,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(56, '添加与修改处理系统用户角色', 'systemUserRoleCreateOrEditHandle', '/system/user_role/role', 9, '', 0, 0,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(57, '通过AJAX技术删除系统用户角色', 'deleteSystemUserRoleForAjax', '/system/user_role/role', 9, '', 0, 0,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(58, '系统用户权限列表', 'systemUserPrivilegeList', '/system/user_role/privilege', 9, 'fa fa-list', 0, 1,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(59, '添加系统用户权限', 'createSystemUserPrivilege', '/system/user_role/privilege/create', 9, 'fa fa-plus', 0, 0,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(60, '修改系统用户权限', 'editSystemUserPrivilege', '/system/user_role/privilege/edit', 9, 'fa fa-pencil-square-o', 0, 0,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(61, '添加与修改处理系统用户权限', 'systemUserPrivilegeCreateOrEditHandle', '/system/user_role/privilege', 9, '', 0, 0,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(62, '通过AJAX技术删除系统用户权限', 'deleteSystemUserPrivilegeForAjax', '/system/user_role/privilege', 9, '', 0, 0,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(63, '通过AJAX技术获取系统用户角色拥有的权限', 'getPrivilegesByRoleIdForAjax', '/system/user_role/privilege/load', 10,
 'fa fa-list', 0, 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(64, '系统配置列表', 'systemConfigurationList', '/system/configuration', 10, 'fa fa-list', 0, 1, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
(65, '添加系统配置', 'createConfiguration', '/system/configuration/create', 10, 'fa fa-plus', 0,
 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(66, '修改系统配置', 'editConfiguration', '/system/configuration/edit', 10, 'fa fa-pencil-square-o', 0,
 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(67, '添加与修改处理系统配置', 'systemConfigurationCreateOrEditHandle', '/system/configuration', 10,
 '', 0, 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(68, '通过AJAX技术删除系统配置', 'deleteConfigurationForAjax', '/system/configuration', 10,
 '', 0, 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00');
-- 添加基础角色
INSERT INTO `phone_number`.`pm_role`
(`id`, `name`, `description`, `parent_id`, `create_time`, `update_time`)
VALUES (1, 'ROLE_system', '系统管理员', 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(2, 'ROLE_community', '社区管理员', 3, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
(3, 'ROLE_subdistrict', '街道管理员', 1, '1000-01-01 00:00:00', '1000-01-01 00:00:00');
-- 添加基础系统用户
INSERT INTO `phone_number`.`pm_system_user`
(`id`, `username`, `password`, `login_time`, `role_id`, `create_time`, `update_time`)
VALUES (1, 'admin', '$2a$10$qv5eCH9zENJNURAIZGl8MuwY4MTmyhv5TVviOmVwfZUUBuh30OfPi', '1000-01-01 00:00:00', 1,
        '1000-01-01 00:00:00', '1000-01-01 00:00:00');
-- 添加系统配置
INSERT INTO `phone_number`.`pm_configuration`
(`key`, `type`, `value`, `description`, `key_is_changed`, `create_time`, `update_time`)
VALUES ('system_administrator_id', 3, '1', '系统管理员编号', 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
('system_company_type', 3, '0', '系统用户角色编号', 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
('community_company_type', 3, '1', '社区用户角色编号', 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
('subdistrict_company_type', 3, '2', '街道用户角色编号', 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
('excel_resident_title_up', 2, '附件2', '电话库Excel表标题上文字', 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
('excel_resident_title', 2, '“评社区”活动电话库登记表', '电话库Excel表标题', 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
('read_resident_excel_start_row_number', 3, '4', '读取电话库Excel表开始行号，从内容开始的行数加，包含开始行数', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_resident_subdistrict_name_cell_number', 3, '0', '电话库Excel表中“街道”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_resident_community_name_cell_number', 3, '1', '电话库Excel表中“社区”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_resident_name_cell_number', 3, '2', '电话库Excel表中“户主姓名”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_resident_address_cell_number', 3, '3', '电话库Excel表中“家庭住址”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_resident_phone1_cell_number', 3, '4', '电话库Excel表中“联系方式一”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_resident_phone2_cell_number', 3, '5', '电话库Excel表中“联系方式二”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_resident_phone3_cell_number', 3, '6', '电话库Excel表中“联系方式三”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_resident_subcontractor_name_cell_number', 3, '7', '电话库Excel表中“分包人”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_title', 2, '${subdistrictName}街道（园区）社区楼片长花名册', '楼长Excel表标题', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_title_up', 2, '附件1', '楼长Excel表标题上文字', 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
('read_dormitory_excel_start_row_number', 3, '6', '读取社区楼长Excel表开始行号，从内容开始的行数加，包含开始行数', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_community_name_cell_number', 3, '1', '社区楼长Excel表中“社区”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_id_cell_number', 3, '2', '社区楼长Excel表中“编号”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_name_cell_number', 3, '3', '社区楼长Excel表中“楼片长姓名”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_gender_cell_number', 3, '4', '社区楼长Excel表中“性别”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_birth_cell_number', 3, '5', '社区楼长Excel表中“出生年月”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_political_status_cell_number', 3, '6', '社区楼长Excel表中“政治面貌”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_work_status_cell_number', 3, '7', '社区楼长Excel表中“工作状况”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_education_cell_number', 3, '8', '社区楼长Excel表中“学历”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_address_cell_number', 3, '9', '社区楼长Excel表中“家庭住址”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_manager_address_cell_number', 3, '10', '社区楼长Excel表中“管理的地址”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_manager_count_cell_number', 3, '11', '社区楼长Excel表中“管理的户数”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_telephone_cell_number', 3, '12', '社区楼长Excel表中“手机号码”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_landline_cell_number', 3, '13', '社区楼长Excel表中“座机号码”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_subcontractor_name_cell_number', 3, '14', '社区楼长Excel表中“分包人姓名”所在列的位置序号，从0开始', 0, '1000-01-01 00:00:00',
 '1000-01-01 00:00:00'),
('excel_dormitory_subcontractor_telephone_cell_number', 3, '15', '社区楼长Excel表中“分包人手机号码”所在列的位置序号，从0开始', 0,
 '1000-01-01 00:00:00', '1000-01-01 00:00:00'),
('system_is_active', 1, '1', '系统是否开启', 0, '1000-01-01 00:00:00', '1000-01-01 00:00:00');
