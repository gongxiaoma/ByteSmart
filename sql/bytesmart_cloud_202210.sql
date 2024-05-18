-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.24 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 bytesmart-cloud 的数据库结构
CREATE DATABASE IF NOT EXISTS `bytesmart-cloud` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bytesmart-cloud`;

-- 导出  表 bytesmart-cloud.bytesmart_config 结构
CREATE TABLE IF NOT EXISTS `bytesmart_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_type` varchar(500) DEFAULT '' COMMENT '参数键值',
  `create_by` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `config_value` varchar(500) DEFAULT '',
  `update_by` varchar(64) DEFAULT '',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参数配置表';

-- 正在导出表  bytesmart-cloud.bytesmart_config 的数据：~0 rows (大约)

-- 导出  表 bytesmart-cloud.bytesmart_dept 结构
CREATE TABLE IF NOT EXISTS `bytesmart_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父部门id',
  `ancestors` varchar(30) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) NOT NULL COMMENT '部门名称',
  `dept_weight` int(4) DEFAULT NULL COMMENT '部门权重',
  `dept_leader` varchar(30) DEFAULT '' COMMENT '负责人',
  `dept_mobile` varchar(11) DEFAULT '' COMMENT '联系电话',
  `dept_email` varchar(50) DEFAULT '' COMMENT '用地址',
  `dept_status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT '',
  PRIMARY KEY (`dept_id`),
  UNIQUE KEY `dept_name` (`dept_name`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- 正在导出表  bytesmart-cloud.bytesmart_dept 的数据：~6 rows (大约)
REPLACE INTO `bytesmart_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `dept_weight`, `dept_leader`, `dept_mobile`, `dept_email`, `dept_status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(100, 0, '0', 'bytesmart', 1, '', '', '', '0', '0', 'admin', '2024-01-08 10:13:06', 'admin', '2024-01-08 10:04:59', ''),
	(101, 100, '0,100', '东莞分公司', 1, '', '', '', '0', '0', 'admin', '2024-01-07 23:45:37', 'admin', '2024-01-08 10:11:28', ''),
	(103, 101, '0,100,101', '人事部', 1, '', '', '', '0', '0', 'admin', '2024-01-08 10:05:57', 'admin', '2024-01-08 10:11:34', ''),
	(104, 100, '0,100', '深圳分公司', 2, '', '', '', '0', '0', 'admin', '2024-01-08 10:06:40', 'admin', '2024-01-08 10:13:17', ''),
	(105, 104, '0,100,104', '研发部', 1, '', '', '', '0', '0', 'admin', '2024-01-08 10:07:11', 'admin', '2024-01-08 10:11:38', ''),
	(108, 101, '0,100,101', '财务部', 2, '', '', '', '0', '0', 'admin', '2024-01-08 10:15:55', '', NULL, '');

-- 导出  表 bytesmart-cloud.bytesmart_dict_data 结构
CREATE TABLE IF NOT EXISTS `bytesmart_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `STATUS` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- 正在导出表  bytesmart-cloud.bytesmart_dict_data 的数据：~0 rows (大约)

-- 导出  表 bytesmart-cloud.bytesmart_dict_type 结构
CREATE TABLE IF NOT EXISTS `bytesmart_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE KEY `dict_type` (`dict_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- 正在导出表  bytesmart-cloud.bytesmart_dict_type 的数据：~0 rows (大约)

-- 导出  表 bytesmart-cloud.bytesmart_employee 结构
CREATE TABLE IF NOT EXISTS `bytesmart_employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `username` varchar(30) NOT NULL COMMENT '用户登录账号',
  `password` varchar(100) DEFAULT '' COMMENT '用户登录密码',
  `employee_name` varchar(30) NOT NULL COMMENT '用户名字',
  `employee_english_name` varchar(50) DEFAULT '' COMMENT '英文名字',
  `employee_gender` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `employee_weight` int(4) DEFAULT NULL COMMENT '用户权重',
  `employee_ismarital` char(1) DEFAULT '0' COMMENT '是否结婚（0是 1否 2未知）',
  `employee_no` int(20) DEFAULT NULL COMMENT '用户工号',
  `employee_birthdate` date DEFAULT NULL COMMENT '生日',
  `employment_date` date DEFAULT NULL COMMENT '入职时间',
  `employee_age` int(3) unsigned DEFAULT NULL COMMENT '年龄',
  `employee_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `employee_avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `office_phone` varchar(11) DEFAULT '' COMMENT '办公电话',
  `employee_mobile` varchar(11) DEFAULT '' COMMENT '用户联系电话',
  `employee_email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `employee_address` varchar(50) DEFAULT '' COMMENT '用户地址',
  `employee_status` char(1) DEFAULT '0' COMMENT '用户状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '用户最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '用户最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`employee_id`),
  KEY `fk_employee_dept` (`dept_id`),
  CONSTRAINT `fk_employee_dept` FOREIGN KEY (`dept_id`) REFERENCES `bytesmart_dept` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  bytesmart-cloud.bytesmart_employee 的数据：~4 rows (大约)
REPLACE INTO `bytesmart_employee` (`employee_id`, `dept_id`, `username`, `password`, `employee_name`, `employee_english_name`, `employee_gender`, `employee_weight`, `employee_ismarital`, `employee_no`, `employee_birthdate`, `employment_date`, `employee_age`, `employee_type`, `employee_avatar`, `office_phone`, `employee_mobile`, `employee_email`, `employee_address`, `employee_status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, 103, 'zhangsan', '$2a$10$FK2R7wPe0Tz8OYJE/7NWAOo2r.HBYIRtYjHp6B77DV5lJG0UH8p3G', '张三', 'zhangsan', '0', NULL, '0', NULL, NULL, NULL, NULL, '00', '', '', '15012450807', 'zhangsan@163.com', '东莞市', '0', '0', '', '2024-03-21 23:43:11', '', '2024-01-07 23:53:25', '', '2024-02-26 18:32:59', ''),
	(23, 103, 'liudehua', '$2a$10$6xq4pGJ6XrGr95KqPArGTuUisQS19hUlH6isHODt1b.2xAXWOlueS', '刘德华', '刘德华', '0', NULL, '0', NULL, NULL, NULL, NULL, '00', '', '', '16897556363', 'liudehua@163.com', '', '0', '0', '', '2024-03-21 23:43:02', 'admin', '2024-03-12 10:30:10', 'admin', '2024-03-16 15:08:17', ''),
	(24, 108, 'chenhuilin', '$2a$10$U4eTz.I8QUvApFQsXMU0.uqwi8uTqIKKvMMVKw00rvpk4csWZ4bcS', '陈慧琳', '陈慧琳', '1', NULL, '0', NULL, NULL, NULL, NULL, '00', '', '', '15678996969', 'chenhuilin@163.com', '', '0', '0', '', '2024-03-21 23:42:51', 'admin', '2024-03-12 10:30:52', 'admin', '2024-03-24 16:04:00', ''),
	(25, 105, 'wangfei', '$2a$10$PhKH.l0pgbY2Jg7dI/T4Gu2pTHD7f6HHxTeEvDYXFngDdHY0PYEaC', '王菲', 'wangfei', '1', NULL, '0', NULL, NULL, NULL, NULL, '00', '', '', '', '', '', '0', '0', '', '2024-03-21 23:43:10', 'admin', '2024-03-12 17:02:55', '', NULL, '');

-- 导出  表 bytesmart-cloud.bytesmart_employee_post 结构
CREATE TABLE IF NOT EXISTS `bytesmart_employee_post` (
  `employee_id` int(11) NOT NULL COMMENT '用户ID',
  `post_id` int(11) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`employee_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

-- 正在导出表  bytesmart-cloud.bytesmart_employee_post 的数据：~4 rows (大约)
REPLACE INTO `bytesmart_employee_post` (`employee_id`, `post_id`) VALUES
	(1, 11),
	(23, 12),
	(24, 13),
	(25, 14);

-- 导出  表 bytesmart-cloud.bytesmart_employee_role 结构
CREATE TABLE IF NOT EXISTS `bytesmart_employee_role` (
  `employee_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`employee_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色关联表';

-- 正在导出表  bytesmart-cloud.bytesmart_employee_role 的数据：~4 rows (大约)
REPLACE INTO `bytesmart_employee_role` (`employee_id`, `role_id`) VALUES
	(1, 1),
	(23, 13),
	(24, 13),
	(25, 13);

-- 导出  表 bytesmart-cloud.bytesmart_logininfor 结构
CREATE TABLE IF NOT EXISTS `bytesmart_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `username` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示信息',
  `access_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE,
  KEY `idx_sys_logininfor_s` (`status`) USING BTREE,
  KEY `idx_sys_logininfor_lt` (`access_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- 正在导出表  bytesmart-cloud.bytesmart_logininfor 的数据：~0 rows (大约)

-- 导出  表 bytesmart-cloud.bytesmart_menu 结构
CREATE TABLE IF NOT EXISTS `bytesmart_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` int(11) DEFAULT '0' COMMENT '父菜单ID',
  `menu_name` varchar(30) NOT NULL COMMENT '菜单名称',
  `menu_weight` int(4) DEFAULT NULL COMMENT '显示顺序',
  `route_address` varchar(255) DEFAULT '' COMMENT '路由地址',
  `component_path` varchar(255) DEFAULT '' COMMENT '组件路径',
  `route_parameter` varchar(255) DEFAULT '' COMMENT '路由参数',
  `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `menu_display` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `menu_status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `menu_perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- 正在导出表  bytesmart-cloud.bytesmart_menu 的数据：~6 rows (大约)
REPLACE INTO `bytesmart_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_weight`, `route_address`, `component_path`, `route_parameter`, `is_frame`, `is_cache`, `menu_type`, `menu_display`, `menu_status`, `menu_perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(115, 0, '任务管理', 1, 'task', '', '', 1, 0, 'M', '0', '0', '', 'build', 'admin', '2024-03-09 14:06:19', 'admin', '2024-03-16 11:30:09', ''),
	(125, 115, '我发起的任务', 1, 'initiator', 'task/initiator/index', '', 1, 0, 'C', '0', '0', 'task:initiator:list', 'edit', 'admin', '2024-03-18 14:56:56', 'zhangsan', '2024-03-18 15:02:28', ''),
	(126, 125, '任务查询', 1, '', '', '', 1, 0, 'F', '0', '0', 'task:initiator:query', '#', 'zhangsan', '2024-03-18 15:04:01', 'zhangsan', '2024-03-18 15:05:10', ''),
	(127, 125, '新增任务', 2, '', '', '', 1, 0, 'F', '0', '0', 'task:initiator:add', 'edit', 'zhangsan', '2024-03-18 15:04:57', '', NULL, ''),
	(128, 125, '修改任务', 3, '', '', '', 1, 0, 'F', '0', '0', 'task:initiator:edit', '#', 'chenhuilin', '2024-03-18 18:01:52', '', NULL, ''),
	(129, 125, '删除任务', 4, '', '', '', 1, 0, 'F', '0', '0', 'task:initiator:remove', '#', 'chenhuilin', '2024-03-18 18:02:23', '', NULL, '');

-- 导出  表 bytesmart-cloud.bytesmart_notice 结构
CREATE TABLE IF NOT EXISTS `bytesmart_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- 正在导出表  bytesmart-cloud.bytesmart_notice 的数据：~0 rows (大约)

-- 导出  表 bytesmart-cloud.bytesmart_oper_log 结构
CREATE TABLE IF NOT EXISTS `bytesmart_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint(20) DEFAULT '0' COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`) USING BTREE,
  KEY `idx_sys_oper_log_bt` (`business_type`) USING BTREE,
  KEY `idx_sys_oper_log_s` (`status`) USING BTREE,
  KEY `idx_sys_oper_log_ot` (`oper_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- 正在导出表  bytesmart-cloud.bytesmart_oper_log 的数据：~0 rows (大约)

-- 导出  表 bytesmart-cloud.bytesmart_post 结构
CREATE TABLE IF NOT EXISTS `bytesmart_post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_name` varchar(30) NOT NULL COMMENT '岗位名称',
  `post_weight` int(4) DEFAULT NULL COMMENT '岗位权重',
  `post_type` varchar(11) DEFAULT '' COMMENT '岗位英文名',
  `post_status` char(1) DEFAULT '0' COMMENT '岗位状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='岗位表';

-- 正在导出表  bytesmart-cloud.bytesmart_post 的数据：~7 rows (大约)
REPLACE INTO `bytesmart_post` (`post_id`, `post_name`, `post_weight`, `post_type`, `post_status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(11, '人事专员', 2, 'renshi', '0', 'admin', '2024-01-07 22:59:06', 'admin', '2024-03-12 10:28:55', '普通员工1'),
	(12, '人事主管', 1, 'rs-manger', '0', 'admin', '2024-01-07 22:59:49', '', '2024-01-08 10:13:32', ''),
	(13, '财务专员', 3, 'caiwu', '0', 'admin', '2024-01-07 23:00:15', 'admin', '2024-01-07 23:03:11', ''),
	(14, '研发组长', 1, 'manger', '0', 'zhangsan', '2024-01-17 15:10:20', '', NULL, ''),
	(15, '工程师', 2, 'common', '0', 'zhangsan', '2024-01-17 15:11:02', '', NULL, ''),
	(23, '88', 0, '88', '0', 'admin', '2024-03-06 00:53:02', '', NULL, '88'),
	(24, '99', 0, '99', '0', 'admin', '2024-03-06 00:53:07', '', NULL, '99');

-- 导出  表 bytesmart-cloud.bytesmart_role 结构
CREATE TABLE IF NOT EXISTS `bytesmart_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(50) NOT NULL COMMENT '角色权限字符串',
  `role_weight` int(4) DEFAULT NULL COMMENT '角色权重',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `role_status` char(1) DEFAULT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 正在导出表  bytesmart-cloud.bytesmart_role 的数据：~6 rows (大约)
REPLACE INTO `bytesmart_role` (`role_id`, `role_name`, `role_key`, `role_weight`, `data_scope`, `menu_check_strictly`, `dept_check_strictly`, `role_status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, '超级管理员', 'admin', 0, '1', 1, 1, '0', '0', 'admin', '2024-01-09 08:43:56', 'admin', '2024-01-09 09:38:49', ''),
	(9, '普通管理员', 'common', 1, '1', 1, 1, '0', '2', 'admin', '2024-01-07 23:48:36', 'zhangsan', '2024-02-28 14:46:55', ''),
	(10, '资源管理员', 'putong', 3, '1', 1, 1, '0', '2', 'admin', '2024-02-29 16:34:14', '', NULL, ''),
	(11, '存储管理员', 'save', 3, '1', 1, 1, '0', '2', 'admin', '2024-02-29 18:16:46', 'admin', '2024-02-29 18:51:23', ''),
	(12, '主管', 'zhuguan', 4, '1', 1, 1, '0', '2', 'admin', '2024-03-09 14:21:23', 'admin', '2024-03-09 14:26:37', ''),
	(13, '经理', 'managent', 1, '1', 1, 1, '0', '0', 'admin', '2024-03-09 14:31:21', 'admin', '2024-05-18 21:49:32', '');

-- 导出  表 bytesmart-cloud.bytesmart_role_dept 结构
CREATE TABLE IF NOT EXISTS `bytesmart_role_dept` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `dept_id` int(11) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与部门关联表';

-- 正在导出表  bytesmart-cloud.bytesmart_role_dept 的数据：~0 rows (大约)

-- 导出  表 bytesmart-cloud.bytesmart_role_menu 结构
CREATE TABLE IF NOT EXISTS `bytesmart_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单关联表';

-- 正在导出表  bytesmart-cloud.bytesmart_role_menu 的数据：~4 rows (大约)
REPLACE INTO `bytesmart_role_menu` (`role_id`, `menu_id`) VALUES
	(13, 115),
	(13, 125),
	(13, 126),
	(13, 127);

-- 导出  表 bytesmart-cloud.bytesmart_tasks 结构
CREATE TABLE IF NOT EXISTS `bytesmart_tasks` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `task_title` varchar(50) NOT NULL COMMENT '任务主题',
  `task_descr` varchar(255) NOT NULL COMMENT '任务内容',
  `task_progress` int(11) DEFAULT NULL COMMENT '任务进度情况',
  `enddate` date NOT NULL COMMENT '预计完成日期',
  `startdate` date DEFAULT NULL COMMENT '实际开始日期',
  `task_af` date DEFAULT NULL COMMENT '实际完成日期',
  `status` int(11) DEFAULT NULL COMMENT '任务状态0未完成  1完成）',
  `initiator_id` int(11) NOT NULL COMMENT '发起人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='任务表';

-- 正在导出表  bytesmart-cloud.bytesmart_tasks 的数据：~4 rows (大约)
REPLACE INTO `bytesmart_tasks` (`task_id`, `task_title`, `task_descr`, `task_progress`, `enddate`, `startdate`, `task_af`, `status`, `initiator_id`, `create_time`, `update_time`, `remark`) VALUES
	(1, '英语第一课', '完成英语第一课', 50, '2024-03-21', '2024-03-15', '2024-03-20', 2, 1, '2024-03-13 00:00:00', NULL, '123'),
	(2, '数学学习', '完成数学', 20, '2024-03-22', '2024-03-16', NULL, 3, 25, '2024-03-16 00:00:00', NULL, '123'),
	(3, '语文第二课', '学习语文古诗', 70, '2024-03-16', '2024-03-10', NULL, 2, 25, '2024-03-15 00:00:00', NULL, '123待定'),
	(4, '美术学习', '学习画画', 0, '2024-03-25', '2024-03-20', NULL, 1, 1, '2024-03-18 00:00:00', NULL, '258');

-- 导出  表 bytesmart-cloud.bytesmart_tasks_assigned 结构
CREATE TABLE IF NOT EXISTS `bytesmart_tasks_assigned` (
  `tid` int(11) NOT NULL COMMENT '任务ID',
  `assigned_id` int(11) NOT NULL COMMENT '被分配人ID',
  PRIMARY KEY (`tid`,`assigned_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表与被指派人表关联表';

-- 正在导出表  bytesmart-cloud.bytesmart_tasks_assigned 的数据：~6 rows (大约)
REPLACE INTO `bytesmart_tasks_assigned` (`tid`, `assigned_id`) VALUES
	(1, 23),
	(1, 24),
	(2, 23),
	(3, 23),
	(3, 24),
	(4, 24);

-- 导出  表 bytesmart-cloud.gen_table 结构
CREATE TABLE IF NOT EXISTS `gen_table` (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成业务表';

-- 正在导出表  bytesmart-cloud.gen_table 的数据：~0 rows (大约)

-- 导出  表 bytesmart-cloud.gen_table_column 结构
CREATE TABLE IF NOT EXISTS `gen_table_column` (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` bigint(20) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成业务表字段';

-- 正在导出表  bytesmart-cloud.gen_table_column 的数据：~0 rows (大约)

-- 导出  表 bytesmart-cloud.sys_config 结构
CREATE TABLE IF NOT EXISTS `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

-- 正在导出表  bytesmart-cloud.sys_config 的数据：~5 rows (大约)
REPLACE INTO `sys_config` (`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-red', 'Y', 'admin', '2023-11-05 19:26:26', 'admin', '2023-11-11 22:55:55', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),
	(2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2023-11-05 19:26:26', '', NULL, '初始化密码 123456'),
	(3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-light', 'Y', 'admin', '2023-11-05 19:26:26', 'admin', '2023-11-11 22:56:16', '深色主题theme-dark，浅色主题theme-light'),
	(4, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2023-11-05 19:26:26', '', NULL, '是否开启注册用户功能（true开启，false关闭）'),
	(5, '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', 'admin', '2023-11-05 19:26:26', '', NULL, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');

-- 导出  表 bytesmart-cloud.sys_dept 结构
CREATE TABLE IF NOT EXISTS `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- 正在导出表  bytesmart-cloud.sys_dept 的数据：~10 rows (大约)
REPLACE INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-11-05 19:26:25', '', NULL),
	(101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-11-05 19:26:25', '', NULL),
	(102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-11-05 19:26:25', '', NULL),
	(103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-11-05 19:26:25', '', NULL),
	(104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-11-05 19:26:25', '', NULL),
	(105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-11-05 19:26:25', '', NULL),
	(106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-11-05 19:26:25', '', NULL),
	(107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-11-05 19:26:25', '', NULL),
	(108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-11-05 19:26:25', '', NULL),
	(109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2023-11-05 19:26:25', '', NULL);

-- 导出  表 bytesmart-cloud.sys_dict_data 结构
CREATE TABLE IF NOT EXISTS `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- 正在导出表  bytesmart-cloud.sys_dict_data 的数据：~29 rows (大约)
REPLACE INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '性别男'),
	(2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '性别女'),
	(3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '性别未知'),
	(4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '显示菜单'),
	(5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '隐藏菜单'),
	(6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '正常状态'),
	(7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '停用状态'),
	(8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '正常状态'),
	(9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '停用状态'),
	(10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '默认分组'),
	(11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '系统分组'),
	(12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '系统默认是'),
	(13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '系统默认否'),
	(14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '通知'),
	(15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '公告'),
	(16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '正常状态'),
	(17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '关闭状态'),
	(18, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '其他操作'),
	(19, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '新增操作'),
	(20, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '修改操作'),
	(21, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '删除操作'),
	(22, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '授权操作'),
	(23, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '导出操作'),
	(24, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '导入操作'),
	(25, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '强退操作'),
	(26, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '生成操作'),
	(27, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '清空操作'),
	(28, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '正常状态'),
	(29, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '停用状态');

-- 导出  表 bytesmart-cloud.sys_dict_type 结构
CREATE TABLE IF NOT EXISTS `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- 正在导出表  bytesmart-cloud.sys_dict_type 的数据：~10 rows (大约)
REPLACE INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, '用户性别', 'sys_user_sex', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '用户性别列表'),
	(2, '菜单状态', 'sys_show_hide', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '菜单状态列表'),
	(3, '系统开关', 'sys_normal_disable', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '系统开关列表'),
	(4, '任务状态', 'sys_job_status', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '任务状态列表'),
	(5, '任务分组', 'sys_job_group', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '任务分组列表'),
	(6, '系统是否', 'sys_yes_no', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '系统是否列表'),
	(7, '通知类型', 'sys_notice_type', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '通知类型列表'),
	(8, '通知状态', 'sys_notice_status', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '通知状态列表'),
	(9, '操作类型', 'sys_oper_type', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '操作类型列表'),
	(10, '系统状态', 'sys_common_status', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '登录状态列表');

-- 导出  表 bytesmart-cloud.sys_job 结构
CREATE TABLE IF NOT EXISTS `sys_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

-- 正在导出表  bytesmart-cloud.sys_job 的数据：~3 rows (大约)
REPLACE INTO `sys_job` (`job_id`, `job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2023-11-05 19:26:26', '', NULL, '');

-- 导出  表 bytesmart-cloud.sys_job_log 结构
CREATE TABLE IF NOT EXISTS `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

-- 正在导出表  bytesmart-cloud.sys_job_log 的数据：~0 rows (大约)

-- 导出  表 bytesmart-cloud.sys_logininfor 结构
CREATE TABLE IF NOT EXISTS `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示信息',
  `access_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`),
  KEY `idx_sys_logininfor_s` (`status`),
  KEY `idx_sys_logininfor_lt` (`access_time`)
) ENGINE=InnoDB AUTO_INCREMENT=379 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- 正在导出表  bytesmart-cloud.sys_logininfor 的数据：~279 rows (大约)
REPLACE INTO `sys_logininfor` (`info_id`, `user_name`, `ipaddr`, `status`, `msg`, `access_time`) VALUES
	(100, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-05 19:45:42'),
	(101, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-06 10:15:18'),
	(102, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-06 10:15:23'),
	(103, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-11 01:04:26'),
	(104, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-11 11:08:38'),
	(105, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-11 22:56:24'),
	(106, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-11 22:56:28'),
	(107, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-11 23:34:04'),
	(108, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-11 23:34:10'),
	(109, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-12 20:23:51'),
	(110, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-13 10:46:23'),
	(111, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-13 23:23:51'),
	(112, 'test', '127.0.0.1', '0', '登录成功', '2023-11-13 23:24:03'),
	(113, 'test', '127.0.0.1', '0', '退出成功', '2023-11-13 23:24:09'),
	(114, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-13 23:24:12'),
	(115, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-13 23:24:51'),
	(116, 'test', '127.0.0.1', '0', '登录成功', '2023-11-13 23:25:01'),
	(117, 'test', '127.0.0.1', '0', '退出成功', '2023-11-14 11:01:39'),
	(118, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-14 11:01:43'),
	(119, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-15 10:57:27'),
	(120, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-15 10:57:33'),
	(121, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-16 21:05:00'),
	(122, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-16 21:05:08'),
	(123, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-17 10:09:27'),
	(124, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-17 10:09:32'),
	(125, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-18 10:32:06'),
	(126, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-18 10:32:13'),
	(127, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-19 15:52:03'),
	(128, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-19 15:52:07'),
	(129, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-20 08:21:13'),
	(130, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-20 08:21:17'),
	(131, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-21 10:23:30'),
	(132, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-21 11:03:31'),
	(133, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-21 23:58:50'),
	(134, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-21 23:58:55'),
	(135, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-26 11:35:18'),
	(136, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-26 11:37:43'),
	(137, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-26 11:37:49'),
	(138, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-27 11:11:54'),
	(139, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-27 11:12:05'),
	(140, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-30 00:39:20'),
	(141, 'admin', '127.0.0.1', '0', '登录成功', '2023-11-30 00:39:36'),
	(142, 'admin', '127.0.0.1', '0', '退出成功', '2023-11-30 00:52:13'),
	(143, 'gongxiaoma', '127.0.0.1', '0', '登录成功', '2023-11-30 00:52:32'),
	(144, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2023-12-02 20:59:09'),
	(145, 'admin', '127.0.0.1', '0', '登录成功', '2023-12-02 20:59:20'),
	(146, 'admin', '127.0.0.1', '0', '退出成功', '2023-12-04 21:47:39'),
	(147, 'admin', '127.0.0.1', '0', '登录成功', '2023-12-04 21:47:46'),
	(148, 'admin', '127.0.0.1', '0', '退出成功', '2023-12-04 23:47:17'),
	(149, 'admin', '127.0.0.1', '0', '登录成功', '2023-12-05 08:41:55'),
	(150, 'admin', '127.0.0.1', '0', '退出成功', '2023-12-06 02:10:00'),
	(151, 'admin', '127.0.0.1', '0', '退出成功', '2023-12-06 02:10:12'),
	(152, 'zhangsan', '127.0.0.1', '0', '登录成功', '2023-12-11 10:48:18'),
	(153, 'admin', '127.0.0.1', '0', '登录成功', '2023-12-27 10:36:37'),
	(154, 'admin', '127.0.0.1', '0', '退出成功', '2023-12-29 07:40:49'),
	(155, 'admin', '127.0.0.1', '0', '登录成功', '2023-12-29 07:40:55'),
	(156, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-01 13:15:30'),
	(157, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-01 13:15:35'),
	(158, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-02 17:22:34'),
	(159, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-02 17:22:38'),
	(160, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-03 11:45:05'),
	(161, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-03 11:45:18'),
	(162, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-05 08:34:38'),
	(163, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-05 08:34:43'),
	(164, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-06 22:14:29'),
	(165, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-06 22:14:36'),
	(166, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-07 10:45:25'),
	(167, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-07 10:45:41'),
	(168, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-07 12:23:50'),
	(169, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-07 12:23:54'),
	(170, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-07 12:24:22'),
	(171, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-07 12:24:45'),
	(172, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-08 10:45:30'),
	(173, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-08 10:45:30'),
	(174, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-08 10:45:42'),
	(175, 'zhangsan', '127.0.0.1', '1', '用户/密码必须填写', '2024-01-08 11:04:34'),
	(176, 'zhangsan', '127.0.0.1', '1', '用户/密码必须填写', '2024-01-08 11:05:23'),
	(177, 'zhangsan', '127.0.0.1', '1', '用户/密码必须填写', '2024-01-08 11:06:24'),
	(178, 'zhangsan', '127.0.0.1', '1', '用户/密码必须填写', '2024-01-08 11:07:02'),
	(179, 'zhangsan', '127.0.0.1', '1', '用户/密码必须填写', '2024-01-08 11:58:02'),
	(180, 'zhangsan', '127.0.0.1', '1', '用户/密码必须填写', '2024-01-08 17:17:42'),
	(181, 'zhangsan', '127.0.0.1', '1', '用户/密码必须填写', '2024-01-08 17:18:11'),
	(182, 'zhangsan', '127.0.0.1', '1', '用户/密码必须填写', '2024-01-08 17:20:24'),
	(183, 'zhangsan', '127.0.0.1', '1', '用户/密码必须填写', '2024-01-08 17:20:30'),
	(184, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-08 23:42:14'),
	(185, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-08 23:42:18'),
	(186, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-09 00:14:58'),
	(187, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-09 08:43:16'),
	(188, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-01-11 08:29:48'),
	(189, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-11 08:30:12'),
	(190, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-11 08:33:42'),
	(191, 'gongxiaoma', '127.0.0.1', '1', '密码输入错误1次', '2024-01-11 08:33:55'),
	(192, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-11 08:34:09'),
	(193, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-01-11 11:00:40'),
	(194, 'admin', '127.0.0.1', '1', '密码输入错误1次', '2024-01-12 00:36:55'),
	(195, 'admin', '127.0.0.1', '1', '密码输入错误2次', '2024-01-12 00:37:06'),
	(196, 'admin', '127.0.0.1', '1', '密码输入错误3次', '2024-01-12 00:37:17'),
	(197, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-12 00:37:43'),
	(198, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-01-12 10:28:35'),
	(199, 'zhangsan', '127.0.0.1', '1', '登录用户不存在', '2024-01-12 10:28:59'),
	(200, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-01-12 10:29:35'),
	(201, 'zhangsan', '127.0.0.1', '1', '登录用户不存在', '2024-01-12 10:30:02'),
	(202, 'admin', '127.0.0.1', '1', '密码输入错误1次', '2024-01-12 10:31:56'),
	(203, 'admin', '127.0.0.1', '1', '密码输入错误2次', '2024-01-12 10:32:19'),
	(204, 'admin', '127.0.0.1', '1', '密码输入错误3次', '2024-01-12 10:32:47'),
	(205, 'admin', '127.0.0.1', '1', '密码输入错误4次', '2024-01-12 10:33:17'),
	(206, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-12 10:34:13'),
	(207, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-12 10:35:52'),
	(208, 'admin', '127.0.0.1', '1', '密码输入错误1次', '2024-01-12 11:02:06'),
	(209, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-12 11:02:17'),
	(210, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-12 15:05:33'),
	(211, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-12 15:15:21'),
	(212, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-12 15:22:41'),
	(213, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-12 19:12:01'),
	(214, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-12 22:26:02'),
	(215, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-13 09:35:26'),
	(216, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-13 16:09:01'),
	(217, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-13 16:09:06'),
	(218, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-13 21:39:15'),
	(219, 'kotann', '127.0.0.1', '0', '退出成功', '2024-01-13 23:24:16'),
	(220, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-13 23:24:23'),
	(221, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-14 16:36:07'),
	(222, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-14 16:36:12'),
	(223, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-01-15 08:48:17'),
	(224, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-15 08:48:22'),
	(225, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-01-17 11:10:01'),
	(226, 'zhangsan', '127.0.0.1', '1', '登录用户不存在', '2024-01-17 11:10:15'),
	(227, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-17 11:41:32'),
	(228, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-17 11:42:27'),
	(229, 'zhangsan', '127.0.0.1', '1', '登录用户不存在', '2024-01-17 11:42:38'),
	(230, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-01-17 13:12:58'),
	(231, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-17 13:13:13'),
	(232, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-01-17 15:22:18'),
	(233, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-17 15:22:25'),
	(234, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-01-17 16:04:31'),
	(235, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-17 16:04:36'),
	(236, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-17 22:17:12'),
	(237, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-01-17 22:46:55'),
	(238, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-17 22:47:00'),
	(239, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-01-18 09:25:13'),
	(240, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-18 09:25:29'),
	(241, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-01-18 15:34:19'),
	(242, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-18 15:34:25'),
	(243, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-01-18 15:40:46'),
	(244, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-18 21:10:06'),
	(245, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-23 16:39:38'),
	(246, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-23 16:41:34'),
	(247, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-23 16:41:46'),
	(248, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-27 22:14:39'),
	(249, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-27 22:14:51'),
	(250, 'admin', '127.0.0.1', '0', '退出成功', '2024-01-28 22:24:20'),
	(251, 'admin', '127.0.0.1', '1', '密码输入错误1次', '2024-01-28 22:24:33'),
	(252, 'admin', '127.0.0.1', '0', '登录成功', '2024-01-28 22:24:44'),
	(253, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-15 16:04:56'),
	(254, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-02-26 23:28:38'),
	(255, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-26 23:28:54'),
	(256, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-02-27 16:18:31'),
	(257, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-27 16:18:40'),
	(258, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-02-27 18:20:49'),
	(259, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-27 18:20:57'),
	(260, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-02-27 19:15:11'),
	(261, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-27 23:04:56'),
	(262, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-02-28 10:10:57'),
	(263, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 10:11:09'),
	(264, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-02-28 10:17:55'),
	(265, 'gongxiaoma', '127.0.0.1', '1', '密码输入错误1次', '2024-02-28 10:18:16'),
	(266, 'gongxiaoma', '127.0.0.1', '1', '密码输入错误2次', '2024-02-28 10:18:28'),
	(267, 'gongxiaoma', '127.0.0.1', '1', '密码输入错误3次', '2024-02-28 10:18:43'),
	(268, 'amdmin', '127.0.0.1', '1', '登录用户不存在', '2024-02-28 10:19:09'),
	(269, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 10:19:23'),
	(270, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-02-28 11:04:20'),
	(271, 'admin', '127.0.0.1', '1', '密码输入错误1次', '2024-02-28 11:04:28'),
	(272, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 11:04:45'),
	(273, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-02-28 11:22:58'),
	(274, 'admin', '127.0.0.1', '1', '密码输入错误1次', '2024-02-28 11:23:10'),
	(275, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 11:23:22'),
	(276, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-02-28 11:31:29'),
	(277, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 11:31:43'),
	(278, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-02-28 11:39:39'),
	(279, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 11:39:56'),
	(280, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-02-28 12:11:50'),
	(281, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 12:12:09'),
	(282, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-02-28 14:52:17'),
	(283, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 14:52:33'),
	(284, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-02-28 16:54:50'),
	(285, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 16:55:03'),
	(286, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-02-28 17:31:09'),
	(287, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 17:31:29'),
	(288, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-02-28 18:13:21'),
	(289, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 18:13:39'),
	(290, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-02-28 18:15:50'),
	(291, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 18:16:02'),
	(292, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 23:12:25'),
	(293, 'admin', '127.0.0.1', '0', '退出成功', '2024-02-28 23:12:32'),
	(294, 'kotann', '127.0.0.1', '1', '登录用户不存在', '2024-02-28 23:12:48'),
	(295, 'zhangsan', '127.0.0.1', '1', '登录用户不存在', '2024-02-28 23:13:05'),
	(296, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 23:17:50'),
	(297, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-02-28 23:22:04'),
	(298, 'bytesmart', '127.0.0.1', '1', '登录用户不存在', '2024-02-28 23:22:27'),
	(299, 'ruoyi', '127.0.0.1', '0', '登录成功', '2024-02-28 23:23:21'),
	(300, 'bytesmart', '127.0.0.1', '0', '退出成功', '2024-02-28 23:33:39'),
	(301, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-28 23:33:45'),
	(302, 'bytesmart', '127.0.0.1', '0', '退出成功', '2024-02-29 08:53:52'),
	(303, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-29 08:53:59'),
	(304, 'admin', '127.0.0.1', '0', '登录成功', '2024-02-29 16:19:38'),
	(305, 'kotann', '127.0.0.1', '0', '退出成功', '2024-02-29 16:40:26'),
	(306, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-01 11:33:19'),
	(307, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-01 11:33:25'),
	(308, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-03-02 15:10:23'),
	(309, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-02 15:10:28'),
	(310, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-02 15:18:00'),
	(311, 'gongxiaoma', '127.0.0.1', '1', '密码输入错误1次', '2024-03-02 15:18:23'),
	(312, 'gongxiaoma', '127.0.0.1', '1', '密码输入错误2次', '2024-03-02 15:18:38'),
	(313, 'zhangsan', '127.0.0.1', '1', '登录用户不存在', '2024-03-02 15:18:49'),
	(314, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-02 15:19:00'),
	(315, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-02 15:19:35'),
	(316, 'gongxiaoma', '127.0.0.1', '0', '登录成功', '2024-03-02 15:19:47'),
	(317, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-03-02 16:22:32'),
	(318, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-02 16:22:37'),
	(319, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-05 12:19:15'),
	(320, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-06 00:52:06'),
	(321, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-06 00:52:11'),
	(322, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-09 13:52:49'),
	(323, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-09 13:52:54'),
	(324, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-10 21:35:40'),
	(325, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-10 21:35:50'),
	(326, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-12 10:27:51'),
	(327, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-12 10:28:00'),
	(328, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-12 10:28:22'),
	(329, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-13 08:56:25'),
	(330, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-13 08:57:06'),
	(331, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-13 08:57:18'),
	(332, 'chenhuilin', '127.0.0.1', '0', '退出成功', '2024-03-16 11:28:32'),
	(333, 'admin', '127.0.0.1', '1', '密码输入错误1次', '2024-03-16 11:28:51'),
	(334, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-16 11:29:24'),
	(335, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-16 15:07:40'),
	(336, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-16 15:07:44'),
	(337, 'chenhuilin', '127.0.0.1', '0', '退出成功', '2024-03-16 16:50:25'),
	(338, 'admin', '127.0.0.1', '1', '密码输入错误1次', '2024-03-16 16:50:46'),
	(339, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-16 16:50:54'),
	(340, 'chenhuilin', '127.0.0.1', '0', '退出成功', '2024-03-16 23:27:05'),
	(341, 'zhangsan', '127.0.0.1', '1', '登录用户不存在', '2024-03-16 23:27:15'),
	(342, 'admin', '127.0.0.1', '1', '密码输入错误1次', '2024-03-16 23:27:26'),
	(343, 'gongxiaoma', '127.0.0.1', '0', '登录成功', '2024-03-16 23:27:43'),
	(344, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-03-16 23:30:00'),
	(345, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-16 23:33:22'),
	(346, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-16 23:35:25'),
	(347, 'gongxiaoma', '127.0.0.1', '0', '登录成功', '2024-03-16 23:35:35'),
	(348, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-03-16 23:38:31'),
	(349, 'gongxiaoma', '127.0.0.1', '0', '登录成功', '2024-03-16 23:38:40'),
	(350, 'gongxiaoma', '127.0.0.1', '0', '退出成功', '2024-03-16 23:38:49'),
	(351, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-16 23:39:07'),
	(352, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-17 15:54:35'),
	(353, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-17 15:54:38'),
	(354, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-18 11:29:54'),
	(355, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-18 11:54:11'),
	(356, 'zhangsan', '127.0.0.1', '0', '退出成功', '2024-03-18 17:21:38'),
	(357, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-18 17:21:55'),
	(358, 'chenhuilin', '127.0.0.1', '0', '退出成功', '2024-03-18 17:27:32'),
	(359, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-18 17:27:47'),
	(360, 'chenhuilin', '127.0.0.1', '0', '退出成功', '2024-03-18 17:36:41'),
	(361, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-18 17:36:59'),
	(362, 'chenhuilin', '127.0.0.1', '0', '退出成功', '2024-03-18 18:02:27'),
	(363, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-18 18:04:19'),
	(364, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-20 17:25:39'),
	(365, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-20 17:25:39'),
	(366, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-20 17:25:50'),
	(367, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-21 16:44:05'),
	(368, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-21 16:44:11'),
	(369, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-22 12:08:44'),
	(370, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-22 12:08:49'),
	(371, 'admin', '127.0.0.1', '0', '退出成功', '2024-03-24 15:39:43'),
	(372, 'admin', '127.0.0.1', '0', '登录成功', '2024-03-24 15:39:47'),
	(373, 'admin', '127.0.0.1', '0', '退出成功', '2024-04-07 18:40:36'),
	(374, 'admin', '127.0.0.1', '0', '登录成功', '2024-04-07 18:40:46'),
	(375, 'admin', '127.0.0.1', '0', '退出成功', '2024-04-08 08:58:26'),
	(376, 'admin', '127.0.0.1', '0', '登录成功', '2024-04-08 08:58:59'),
	(377, 'admin', '127.0.0.1', '0', '登录成功', '2024-05-18 21:41:50'),
	(378, 'admin', '127.0.0.1', '0', '登录成功', '2024-05-18 21:48:32');

-- 导出  表 bytesmart-cloud.sys_menu 结构
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1094 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- 正在导出表  bytesmart-cloud.sys_menu 的数据：~112 rows (大约)
REPLACE INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, '系统管理', 0, 1, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'checkbox', 'admin', '2023-11-05 19:26:26', 'admin', '2024-02-28 16:58:14', '系统管理目录'),
	(2, '系统监控', 0, 2, 'monitor', NULL, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2023-11-05 19:26:26', '', NULL, '系统监控目录'),
	(3, '系统工具', 0, 3, 'tool', NULL, '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2023-11-05 19:26:26', '', NULL, '系统工具目录'),
	(100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'github', 'admin', '2023-11-05 19:26:26', 'admin', '2024-02-28 16:56:30', '用户管理菜单'),
	(101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2023-11-05 19:26:26', '', NULL, '角色管理菜单'),
	(102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2023-11-05 19:26:26', '', NULL, '菜单管理菜单'),
	(103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2023-11-05 19:26:26', '', NULL, '部门管理菜单'),
	(104, '岗位管理', 1, 5, 'post', 'system/post/index', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2023-11-05 19:26:26', '', NULL, '岗位管理菜单'),
	(105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2023-11-05 19:26:26', '', NULL, '字典管理菜单'),
	(106, '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2023-11-05 19:26:26', '', NULL, '参数设置菜单'),
	(107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2023-11-05 19:26:26', '', NULL, '通知公告菜单'),
	(108, '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2023-11-05 19:26:26', '', NULL, '日志管理菜单'),
	(109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2023-11-05 19:26:26', '', NULL, '在线用户菜单'),
	(110, '定时任务', 2, 2, 'job', 'monitor/job/index', '', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2023-11-05 19:26:26', '', NULL, '定时任务菜单'),
	(111, 'Sentinel控制台', 2, 3, 'http://localhost:8718', '', '', 0, 0, 'C', '0', '0', 'monitor:sentinel:list', 'sentinel', 'admin', '2023-11-05 19:26:26', '', NULL, '流量控制菜单'),
	(112, 'Nacos控制台', 2, 4, 'http://localhost:8848/nacos', '', '', 0, 0, 'C', '0', '0', 'monitor:nacos:list', 'nacos', 'admin', '2023-11-05 19:26:26', '', NULL, '服务治理菜单'),
	(113, 'Admin控制台', 2, 5, 'http://localhost:9100/login', '', '', 0, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2023-11-05 19:26:26', '', NULL, '服务监控菜单'),
	(114, '表单构建', 3, 1, 'build', 'tool/build/index', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2023-11-05 19:26:26', '', NULL, '表单构建菜单'),
	(115, '代码生成', 3, 2, 'gen', 'tool/gen/index', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2023-11-05 19:26:26', '', NULL, '代码生成菜单'),
	(116, '系统接口', 3, 3, 'http://localhost:8080/swagger-ui/index.html', '', '', 0, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2023-11-05 19:26:26', '', NULL, '系统接口菜单'),
	(500, '操作日志', 108, 1, 'operlog', 'system/operlog/index', '', 1, 0, 'C', '0', '0', 'system:operlog:list', 'build', 'admin', '2023-11-05 19:26:26', 'admin', '2024-02-28 17:04:20', '操作日志菜单'),
	(501, '登录日志', 108, 2, 'logininfor', 'system/logininfor/index', '', 1, 0, 'C', '0', '0', 'system:logininfor:list', 'logininfor', 'admin', '2023-11-05 19:26:26', '', NULL, '登录日志菜单'),
	(1000, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1001, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1002, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1003, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1004, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1005, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1006, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1007, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1008, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1009, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1010, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1011, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1012, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1013, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1014, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1015, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1016, '部门查询', 103, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1017, '部门新增', 103, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1018, '部门修改', 103, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1019, '部门删除', 103, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1020, '岗位查询', 104, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1021, '岗位新增', 104, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1022, '岗位修改', 104, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1023, '岗位删除', 104, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1024, '岗位导出', 104, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1025, '字典查询', 105, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1026, '字典新增', 105, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1027, '字典修改', 105, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1028, '字典删除', 105, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1029, '字典导出', 105, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1030, '参数查询', 106, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1031, '参数新增', 106, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1032, '参数修改', 106, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1033, '参数删除', 106, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1034, '参数导出', 106, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1035, '公告查询', 107, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1036, '公告新增', 107, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1037, '公告修改', 107, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1038, '公告删除', 107, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1039, '操作查询', 500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:operlog:query', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1040, '操作删除', 500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:operlog:remove', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1041, '日志导出', 500, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:operlog:export', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1042, '登录查询', 501, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:query', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1043, '登录删除', 501, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:remove', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1044, '日志导出', 501, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:export', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1045, '账户解锁', 501, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:logininfor:unlock', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1046, '在线查询', 109, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1047, '批量强退', 109, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1048, '单条强退', 109, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1049, '任务查询', 110, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1050, '任务新增', 110, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1051, '任务修改', 110, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1052, '任务删除', 110, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1053, '状态修改', 110, 5, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1054, '任务导出', 110, 6, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1055, '生成查询', 115, 1, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1056, '生成修改', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1057, '生成删除', 115, 3, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1058, '导入代码', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1059, '预览代码', 115, 4, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1060, '生成代码', 115, 5, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2023-11-05 19:26:26', '', NULL, ''),
	(1065, '前台管理', 0, 1, 'webadmin', NULL, NULL, 1, 0, 'M', '0', '0', '', 'dashboard', 'admin', '2023-12-27 11:29:12', 'admin', '2024-02-28 18:17:04', ''),
	(1066, '岗位管理', 1065, 4, 'webadminpost', 'webadmin/post/index', NULL, 1, 0, 'C', '0', '0', 'webadmin:post:list', 'post', 'admin', '2023-12-27 11:36:21', 'admin', '2024-03-02 15:17:37', ''),
	(1067, '岗位查询', 1066, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:post:query', '#', 'admin', '2023-12-27 11:37:56', 'admin', '2023-12-27 16:12:17', ''),
	(1068, '岗位新增', 1066, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:post:add', '#', 'admin', '2023-12-27 11:38:54', 'admin', '2023-12-27 16:12:23', ''),
	(1069, '岗位修改', 1066, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:post:edit', '#', 'admin', '2023-12-27 11:44:13', 'admin', '2023-12-27 16:12:29', ''),
	(1070, '岗位删除', 1066, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:post:remove', '#', 'admin', '2023-12-27 11:44:42', 'admin', '2023-12-27 16:12:35', ''),
	(1071, '部门管理', 1065, 3, 'webadmindept', 'webadmin/dept/index', NULL, 1, 0, 'C', '0', '0', 'webadmin:dept:list', 'guide', 'admin', '2023-12-27 17:39:44', 'admin', '2024-03-02 15:17:32', ''),
	(1072, '角色管理', 1065, 2, 'webadminrole', 'webadmin/role/index', NULL, 1, 0, 'C', '0', '0', 'webadmin:role:list', 'dict', 'admin', '2023-12-27 17:41:05', 'admin', '2024-03-02 15:17:28', ''),
	(1073, '菜单管理', 1065, 5, 'webadminmenu', 'webadmin/menu/index', NULL, 1, 0, 'C', '0', '0', 'webadmin:munu:list', 'cascader', 'admin', '2023-12-27 17:42:24', 'admin', '2024-03-02 15:17:41', ''),
	(1074, '用户管理', 1065, 1, 'employee', 'webadmin/employee/index', NULL, 1, 0, 'C', '0', '0', 'webadmin:employee:list', 'peoples', 'admin', '2023-12-27 17:44:38', 'admin', '2024-01-02 19:23:47', ''),
	(1075, '用户查询', 1074, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:employee:query', '#', 'admin', '2023-12-27 17:46:13', '', NULL, ''),
	(1076, '用户新增', 1074, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:employee:add', '#', 'admin', '2023-12-27 17:46:54', '', NULL, ''),
	(1077, '用户修改', 1074, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:employee:edit', '#', 'admin', '2023-12-27 17:47:42', '', NULL, ''),
	(1078, '用户删除', 1074, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:employee:remove', '#', 'admin', '2023-12-27 17:48:13', '', NULL, ''),
	(1079, '角色查询', 1072, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:role:query', '#', 'admin', '2023-12-27 17:49:02', '', NULL, ''),
	(1080, '角色新增', 1072, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:role:add', '#', 'admin', '2023-12-27 17:49:25', '', NULL, ''),
	(1081, '角色修改', 1072, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:role:edit', '#', 'admin', '2023-12-27 17:49:50', '', NULL, ''),
	(1082, '角色删除', 1072, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:role:remove', '#', 'admin', '2023-12-27 17:50:18', '', NULL, ''),
	(1083, '部门查询', 1071, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:dept:query', '#', 'admin', '2023-12-27 17:50:48', '', NULL, ''),
	(1084, '部门新增', 1071, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:dept:add', '#', 'admin', '2023-12-27 17:51:11', '', NULL, ''),
	(1085, '部门修改', 1071, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:dept:edit', '#', 'admin', '2023-12-27 17:51:32', '', NULL, ''),
	(1086, '部门删除', 1071, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:dept:remove', '#', 'admin', '2023-12-27 17:51:53', '', NULL, ''),
	(1087, '菜单查询', 1073, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:menu:query', '#', 'admin', '2023-12-27 17:52:52', '', NULL, ''),
	(1088, '菜单新增', 1073, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:menu:add', '#', 'admin', '2023-12-27 17:53:24', '', NULL, ''),
	(1089, '菜单修改', 1073, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:menu:edit', '#', 'admin', '2023-12-27 17:53:41', '', NULL, ''),
	(1090, '菜单删除', 1073, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:menu:remove', '#', 'admin', '2023-12-27 17:53:59', 'admin', '2023-12-27 17:54:16', ''),
	(1091, '用户导出', 1074, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:employee:export', '#', 'admin', '2024-01-02 18:27:30', '', NULL, ''),
	(1092, '用户导入', 1074, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:employee:import', '#', 'admin', '2024-01-02 18:28:22', '', NULL, ''),
	(1093, '重置密码', 1074, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'webadmin:employee:resetPwd', '#', 'admin', '2024-01-02 18:29:15', '', NULL, '');

-- 导出  表 bytesmart-cloud.sys_notice 结构
CREATE TABLE IF NOT EXISTS `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- 正在导出表  bytesmart-cloud.sys_notice 的数据：~2 rows (大约)
REPLACE INTO `sys_notice` (`notice_id`, `notice_title`, `notice_type`, `notice_content`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', _binary 0xe696b0e78988e69cace58685e5aeb9, '0', 'admin', '2023-11-05 19:26:26', '', NULL, '管理员'),
	(2, '维护通知：2018-07-01 若依系统凌晨维护', '1', _binary 0xe7bbb4e68aa4e58685e5aeb9, '0', 'admin', '2023-11-05 19:26:26', '', NULL, '管理员');

-- 导出  表 bytesmart-cloud.sys_oper_log 结构
CREATE TABLE IF NOT EXISTS `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint(20) DEFAULT '0' COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`),
  KEY `idx_sys_oper_log_bt` (`business_type`),
  KEY `idx_sys_oper_log_s` (`status`),
  KEY `idx_sys_oper_log_ot` (`oper_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- 正在导出表  bytesmart-cloud.sys_oper_log 的数据：~0 rows (大约)

-- 导出  表 bytesmart-cloud.sys_post 结构
CREATE TABLE IF NOT EXISTS `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

-- 正在导出表  bytesmart-cloud.sys_post 的数据：~13 rows (大约)
REPLACE INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, 'ceo', '董事长99', 1, '0', 'admin', '2023-11-05 19:26:25', '', '2023-11-07 11:31:53', ''),
	(2, 'se', '项目经理', 2, '0', 'admin', '2023-11-05 19:26:25', '', NULL, ''),
	(3, 'hr', '人力资源', 3, '0', 'admin', '2023-11-05 19:26:25', '', NULL, ''),
	(4, 'user', '普通员工', 4, '0', 'admin', '2023-11-05 19:26:25', '', NULL, ''),
	(5, '444', '发光飞碟', 1, '0', 'admin', '2023-11-19 17:35:13', '', NULL, '1111'),
	(6, '555', '555', 0, '0', 'admin', '2024-03-06 00:54:26', '', NULL, '555'),
	(7, '666', '666', 0, '0', 'admin', '2024-03-06 00:54:33', '', NULL, '666'),
	(8, '777', '777', 0, '0', 'admin', '2024-03-06 00:54:37', '', NULL, '777'),
	(9, '888', '888', 0, '0', 'admin', '2024-03-06 00:54:43', '', NULL, '888'),
	(10, '999', '999', 0, '0', 'admin', '2024-03-06 00:54:48', '', NULL, '999'),
	(11, '1000', '1000', 0, '0', 'admin', '2024-03-06 00:54:57', '', NULL, '1000'),
	(12, '2000', '2000', 0, '0', 'admin', '2024-03-06 00:55:04', '', NULL, '2000'),
	(13, '3000', '3000', 0, '0', 'admin', '2024-03-06 00:55:12', '', NULL, '3000');

-- 导出  表 bytesmart-cloud.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- 正在导出表  bytesmart-cloud.sys_role 的数据：~5 rows (大约)
REPLACE INTO `sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `menu_check_strictly`, `dept_check_strictly`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2023-11-05 19:26:26', '', NULL, '超级管理员'),
	(2, '普通角色', 'common', 2, '2', 0, 0, '0', '0', 'admin', '2023-11-05 19:26:26', 'admin', '2024-01-07 16:12:35', '普通角色'),
	(3, '测试角色', 'test', 0, '1', 0, 0, '0', '0', 'admin', '2023-11-13 23:17:04', 'admin', '2024-01-07 16:12:33', NULL),
	(101, '普通角色2', '88', 3, '1', 1, 1, '0', '0', 'test', '2023-11-14 11:00:04', 'admin', '2024-01-06 22:21:00', NULL),
	(102, '研发管理', 'yanfa', 3, '1', 1, 1, '0', '0', 'admin', '2023-11-14 11:04:09', 'admin', '2024-01-06 22:21:18', NULL);

-- 导出  表 bytesmart-cloud.sys_role_dept 结构
CREATE TABLE IF NOT EXISTS `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';

-- 正在导出表  bytesmart-cloud.sys_role_dept 的数据：~3 rows (大约)
REPLACE INTO `sys_role_dept` (`role_id`, `dept_id`) VALUES
	(2, 100),
	(2, 101),
	(2, 105);

-- 导出  表 bytesmart-cloud.sys_role_menu 结构
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- 正在导出表  bytesmart-cloud.sys_role_menu 的数据：~278 rows (大约)
REPLACE INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
	(2, 1),
	(2, 100),
	(2, 101),
	(2, 102),
	(2, 103),
	(2, 104),
	(2, 105),
	(2, 106),
	(2, 107),
	(2, 108),
	(2, 500),
	(2, 501),
	(2, 1000),
	(2, 1001),
	(2, 1002),
	(2, 1003),
	(2, 1004),
	(2, 1005),
	(2, 1006),
	(2, 1007),
	(2, 1008),
	(2, 1009),
	(2, 1010),
	(2, 1011),
	(2, 1012),
	(2, 1013),
	(2, 1014),
	(2, 1015),
	(2, 1016),
	(2, 1017),
	(2, 1018),
	(2, 1019),
	(2, 1020),
	(2, 1021),
	(2, 1022),
	(2, 1023),
	(2, 1024),
	(2, 1025),
	(2, 1026),
	(2, 1027),
	(2, 1028),
	(2, 1029),
	(2, 1030),
	(2, 1031),
	(2, 1032),
	(2, 1033),
	(2, 1034),
	(2, 1035),
	(2, 1036),
	(2, 1037),
	(2, 1038),
	(2, 1039),
	(2, 1040),
	(2, 1041),
	(2, 1042),
	(2, 1043),
	(2, 1044),
	(2, 1045),
	(6, 1),
	(6, 105),
	(100, 1),
	(100, 100),
	(100, 101),
	(100, 102),
	(100, 103),
	(100, 104),
	(100, 105),
	(100, 106),
	(100, 107),
	(100, 108),
	(100, 500),
	(100, 501),
	(100, 1000),
	(100, 1001),
	(100, 1002),
	(100, 1003),
	(100, 1004),
	(100, 1005),
	(100, 1006),
	(100, 1007),
	(100, 1008),
	(100, 1009),
	(100, 1010),
	(100, 1011),
	(100, 1012),
	(100, 1013),
	(100, 1014),
	(100, 1015),
	(100, 1016),
	(100, 1017),
	(100, 1018),
	(100, 1019),
	(100, 1020),
	(100, 1021),
	(100, 1022),
	(100, 1023),
	(100, 1024),
	(100, 1025),
	(100, 1026),
	(100, 1027),
	(100, 1028),
	(100, 1029),
	(100, 1030),
	(100, 1031),
	(100, 1032),
	(100, 1033),
	(100, 1034),
	(100, 1035),
	(100, 1036),
	(100, 1037),
	(100, 1038),
	(100, 1039),
	(100, 1040),
	(100, 1041),
	(100, 1042),
	(100, 1043),
	(100, 1044),
	(100, 1045),
	(101, 1),
	(101, 100),
	(101, 101),
	(101, 102),
	(101, 103),
	(101, 104),
	(101, 105),
	(101, 106),
	(101, 107),
	(101, 108),
	(101, 500),
	(101, 501),
	(101, 1000),
	(101, 1001),
	(101, 1002),
	(101, 1003),
	(101, 1004),
	(101, 1005),
	(101, 1006),
	(101, 1007),
	(101, 1008),
	(101, 1009),
	(101, 1010),
	(101, 1011),
	(101, 1012),
	(101, 1013),
	(101, 1014),
	(101, 1015),
	(101, 1016),
	(101, 1017),
	(101, 1018),
	(101, 1019),
	(101, 1020),
	(101, 1021),
	(101, 1022),
	(101, 1023),
	(101, 1024),
	(101, 1025),
	(101, 1026),
	(101, 1027),
	(101, 1028),
	(101, 1029),
	(101, 1030),
	(101, 1031),
	(101, 1032),
	(101, 1033),
	(101, 1034),
	(101, 1035),
	(101, 1036),
	(101, 1037),
	(101, 1038),
	(101, 1039),
	(101, 1040),
	(101, 1041),
	(101, 1042),
	(101, 1043),
	(101, 1044),
	(101, 1045),
	(101, 1065),
	(101, 1066),
	(101, 1067),
	(101, 1068),
	(101, 1069),
	(101, 1070),
	(101, 1071),
	(101, 1072),
	(101, 1073),
	(101, 1074),
	(101, 1075),
	(101, 1076),
	(101, 1077),
	(101, 1078),
	(101, 1079),
	(101, 1080),
	(101, 1081),
	(101, 1082),
	(101, 1083),
	(101, 1084),
	(101, 1085),
	(101, 1086),
	(101, 1087),
	(101, 1088),
	(101, 1089),
	(101, 1090),
	(101, 1091),
	(101, 1092),
	(101, 1093),
	(102, 1),
	(102, 2),
	(102, 100),
	(102, 101),
	(102, 102),
	(102, 103),
	(102, 104),
	(102, 105),
	(102, 106),
	(102, 107),
	(102, 108),
	(102, 109),
	(102, 110),
	(102, 111),
	(102, 112),
	(102, 113),
	(102, 500),
	(102, 501),
	(102, 1000),
	(102, 1001),
	(102, 1002),
	(102, 1003),
	(102, 1004),
	(102, 1005),
	(102, 1006),
	(102, 1007),
	(102, 1008),
	(102, 1009),
	(102, 1010),
	(102, 1011),
	(102, 1012),
	(102, 1013),
	(102, 1014),
	(102, 1015),
	(102, 1016),
	(102, 1017),
	(102, 1018),
	(102, 1019),
	(102, 1020),
	(102, 1021),
	(102, 1022),
	(102, 1023),
	(102, 1024),
	(102, 1025),
	(102, 1026),
	(102, 1027),
	(102, 1028),
	(102, 1029),
	(102, 1030),
	(102, 1031),
	(102, 1032),
	(102, 1033),
	(102, 1034),
	(102, 1035),
	(102, 1036),
	(102, 1037),
	(102, 1038),
	(102, 1039),
	(102, 1040),
	(102, 1041),
	(102, 1042),
	(102, 1043),
	(102, 1044),
	(102, 1045),
	(102, 1046),
	(102, 1047),
	(102, 1048),
	(102, 1049),
	(102, 1050),
	(102, 1051),
	(102, 1052),
	(102, 1053),
	(102, 1054);

-- 导出  表 bytesmart-cloud.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- 正在导出表  bytesmart-cloud.sys_user 的数据：~12 rows (大约)
REPLACE INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888881', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-11-05 19:26:25', 'admin', '2023-11-05 19:26:25', '', '2024-02-28 10:11:38', '管理员'),
	(2, 105, 'ry', '若依员工1', '00', 'ry@qq.com', '15666666666', '1', '1223', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-11-05 19:26:25', 'admin', '2023-11-05 19:26:25', 'admin', '2023-11-18 10:39:28', '测试员'),
	(100, 100, 'test', 'test', '00', '', '13567890987', '0', '', '$2a$10$BrNe6ZaOTN/jgaNjz.DwKOhs2yIPOYC9kl33y44bdK8zRlifEB6xK', '0', '2', '', NULL, 'admin', '2023-11-13 23:23:46', 'admin', '2023-11-18 10:47:41', NULL),
	(101, 103, 'ruoyi3', '若依三号', '00', '', '', '0', '', '', '0', '2', '', NULL, 'admin', '2023-11-14 11:03:16', 'admin', '2023-11-14 11:04:35', NULL),
	(102, 102, 'test1', '测试1', '00', '', '', '1', '', '', '0', '0', '', NULL, '', NULL, 'admin', '2023-11-18 10:48:10', NULL),
	(103, 101, 'test2', 'test2', '00', '', '', '0', '', '', '0', '0', '', '2023-11-17 11:11:13', '', '2023-11-17 11:11:22', 'admin', '2023-11-18 10:48:21', NULL),
	(104, 100, 'bytesmart1', 'bytesmart员工1', '00', '', '', '0', '', '$2a$10$UI9bkXOWN8qr7ldyx9B70um5TKhy1ujmB5D5PpDqET/XYdkAGEbAa', '0', '2', '', NULL, '', '2023-11-18 10:56:46', '', '2023-11-18 11:32:00', NULL),
	(105, 105, 'bytesmart2', 'bytesmart员工2', '00', '', '', '1', '', '$2a$10$lkx.ZVR39Y.X688x97TBg.w9mnC/FcfpbYdGetAJqgw7sugxvYelG', '0', '2', '', NULL, '', '2023-11-18 10:58:16', '', NULL, NULL),
	(106, 105, 'bytesmart3', 'bytesmart员工3', '00', '', '', '1', '', '$2a$10$wpOMxNNFjLECClNgjkjPaeMyclKqdL72CYcOq1cxJ.2QEl/YU6SbK', '0', '2', '', NULL, '', '2023-11-18 11:08:47', '', NULL, NULL),
	(107, 108, '888', '888', '00', '', '', '0', '', '$2a$10$7Dg607KSzLE7YDSq9oLwneakVVeI2WNPb4jNEz0CVAZ7zzjrS8vge', '0', '0', '', NULL, 'admin', '2023-11-22 00:01:07', 'admin', '2024-03-13 12:17:09', NULL),
	(108, 100, 'gongxiaoma', 'gongxiaoma', '00', '', '', '0', '', '$2a$10$XJxJKKaqewGUYQYChmzgX.MddcLJvqD4l/5aggiVtUzzXBUfn29aK', '0', '0', '', NULL, 'admin', '2023-11-30 00:51:23', 'admin', '2024-03-02 15:19:29', NULL),
	(109, 103, 'ruoyi', 'ruoyi', '00', '', '', '0', '', '$2a$10$h6O0E890iTHrnaACqdqRK.oP2TOk9d6qaNfxFmW9VGbaOiMVa0/ga', '0', '0', '', NULL, 'gongxiaoma', '2024-02-28 23:21:57', '', NULL, NULL);

-- 导出  表 bytesmart-cloud.sys_user_post 结构
CREATE TABLE IF NOT EXISTS `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

-- 正在导出表  bytesmart-cloud.sys_user_post 的数据：~10 rows (大约)
REPLACE INTO `sys_user_post` (`user_id`, `post_id`) VALUES
	(1, 1),
	(2, 1),
	(2, 2),
	(102, 3),
	(102, 4),
	(103, 1),
	(107, 2),
	(108, 2),
	(108, 3),
	(109, 1);

-- 导出  表 bytesmart-cloud.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- 正在导出表  bytesmart-cloud.sys_user_role 的数据：~8 rows (大约)
REPLACE INTO `sys_user_role` (`user_id`, `role_id`) VALUES
	(1, 1),
	(2, 2),
	(102, 100),
	(102, 102),
	(103, 102),
	(108, 2),
	(108, 100),
	(109, 3);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
