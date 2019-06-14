/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : localhost:3306
 Source Schema         : data_center

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 05/06/2019 16:09:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alarm_config
-- ----------------------------
DROP TABLE IF EXISTS `alarm_config`;
CREATE TABLE `alarm_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `app_name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '应用名称',
  `create_id` int(11) NOT NULL COMMENT '创建人userid',
  `create_name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '创建人username',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人userid',
  `update_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人username',
  `threshold` int(5) NOT NULL COMMENT '阈值',
  `rule` int(1) NOT NULL COMMENT '告警类型 1慢查询 2错误',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `user_group_id` int(11) NOT NULL COMMENT '用户组id',
  `user_group_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '用户组名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index` (`app_id`,`rule`,`user_group_id`) USING BTREE COMMENT '同一应用和用户组只能有一个告警类型'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for alarm_history_0
-- ----------------------------
DROP TABLE IF EXISTS `alarm_history_0`;
CREATE TABLE `alarm_history_0` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `app_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '应用名称',
  `threshold` int(5) NOT NULL COMMENT '阈值',
  `alarm_rule` int(1) NOT NULL COMMENT '告警规则 1.慢查询 2.错误',
  `actual_num` int(5) NOT NULL COMMENT '实际数值',
  `create_time` datetime NOT NULL COMMENT '生成时间',
  `receiver_id` int(11) NOT NULL COMMENT '用户id',
  `receiver_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名称',
  `alarm_message` varchar(2000) COLLATE utf8mb4_bin NOT NULL COMMENT '告警信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for alarm_history_1
-- ----------------------------
DROP TABLE IF EXISTS `alarm_history_1`;
CREATE TABLE `alarm_history_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `app_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '应用名称',
  `threshold` int(5) NOT NULL COMMENT '阈值',
  `alarm_rule` int(1) NOT NULL COMMENT '告警规则 1.慢查询 2.错误',
  `actual_num` int(5) NOT NULL COMMENT '实际数值',
  `create_time` datetime NOT NULL COMMENT '生成时间',
  `receiver_id` int(11) NOT NULL COMMENT '用户id',
  `receiver_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名称',
  `alarm_message` varchar(2000) COLLATE utf8mb4_bin NOT NULL COMMENT '告警信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for alarm_history_2
-- ----------------------------
DROP TABLE IF EXISTS `alarm_history_2`;
CREATE TABLE `alarm_history_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `app_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '应用名称',
  `threshold` int(5) NOT NULL COMMENT '阈值',
  `alarm_rule` int(1) NOT NULL COMMENT '告警规则 1.慢查询 2.错误',
  `actual_num` int(5) NOT NULL COMMENT '实际数值',
  `create_time` datetime NOT NULL COMMENT '生成时间',
  `receiver_id` int(11) NOT NULL COMMENT '用户id',
  `receiver_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名称',
  `alarm_message` varchar(2000) COLLATE utf8mb4_bin NOT NULL COMMENT '告警信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for alarm_history_3
-- ----------------------------
DROP TABLE IF EXISTS `alarm_history_3`;
CREATE TABLE `alarm_history_3` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `app_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '应用名称',
  `threshold` int(5) NOT NULL COMMENT '阈值',
  `alarm_rule` int(1) NOT NULL COMMENT '告警规则 1.慢查询 2.错误',
  `actual_num` int(5) NOT NULL COMMENT '实际数值',
  `create_time` datetime NOT NULL COMMENT '生成时间',
  `receiver_id` int(11) NOT NULL COMMENT '用户id',
  `receiver_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名称',
  `alarm_message` varchar(2000) COLLATE utf8mb4_bin NOT NULL COMMENT '告警信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for alarm_history_4
-- ----------------------------
DROP TABLE IF EXISTS `alarm_history_4`;
CREATE TABLE `alarm_history_4` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `app_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '应用名称',
  `threshold` int(5) NOT NULL COMMENT '阈值',
  `alarm_rule` int(1) NOT NULL COMMENT '告警规则 1.慢查询 2.错误',
  `actual_num` int(5) NOT NULL COMMENT '实际数值',
  `create_time` datetime NOT NULL COMMENT '生成时间',
  `receiver_id` int(11) NOT NULL COMMENT '用户id',
  `receiver_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名称',
  `alarm_message` varchar(2000) COLLATE utf8mb4_bin NOT NULL COMMENT '告警信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for api_invoke_record_0
-- ----------------------------
DROP TABLE IF EXISTS `api_invoke_record_0`;
CREATE TABLE `api_invoke_record_0` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `app_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '应用名称',
  `sql_id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT 'sql id',
  `sqltext` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT 'sql模板内容',
  `request_time` datetime NOT NULL COMMENT '请求时间',
  `request_param` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求参数',
  `response_time` datetime NOT NULL COMMENT '响应时间',
  `cost` int(11) NOT NULL COMMENT '耗费时间',
  `success` int(1) NOT NULL COMMENT '是否成功',
  `processed` int(1) NOT NULL COMMENT '是否已经轮询处理',
  `processed_time` datetime DEFAULT NULL COMMENT '轮询处理时间',
  `error_detail` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '错误详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for api_invoke_record_1
-- ----------------------------
DROP TABLE IF EXISTS `api_invoke_record_1`;
CREATE TABLE `api_invoke_record_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `app_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '应用名称',
  `sql_id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT 'sql id',
  `sqltext` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT 'sql模板内容',
  `request_time` datetime NOT NULL COMMENT '请求时间',
  `request_param` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求参数',
  `response_time` datetime NOT NULL COMMENT '响应时间',
  `cost` int(11) NOT NULL COMMENT '耗费时间',
  `success` int(1) NOT NULL COMMENT '是否成功',
  `processed` int(1) DEFAULT NULL COMMENT '是否已经轮询处理',
  `processed_time` datetime DEFAULT NULL COMMENT '轮询处理时间',
  `error_detail` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '错误详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for api_invoke_record_2
-- ----------------------------
DROP TABLE IF EXISTS `api_invoke_record_2`;
CREATE TABLE `api_invoke_record_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `app_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '应用名称',
  `sql_id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT 'sql id',
  `sqltext` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT 'sql模板内容',
  `request_time` datetime NOT NULL COMMENT '请求时间',
  `request_param` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求参数',
  `response_time` datetime NOT NULL COMMENT '响应时间',
  `cost` int(11) NOT NULL COMMENT '耗费时间',
  `success` int(1) NOT NULL COMMENT '是否成功',
  `processed` int(1) NOT NULL COMMENT '是否已经轮询处理',
  `processed_time` datetime DEFAULT NULL COMMENT '轮询处理时间',
  `error_detail` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '错误详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for api_invoke_record_3
-- ----------------------------
DROP TABLE IF EXISTS `api_invoke_record_3`;
CREATE TABLE `api_invoke_record_3` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `app_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '应用名称',
  `sql_id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT 'sql id',
  `sqltext` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT 'sql模板内容',
  `request_time` datetime NOT NULL COMMENT '请求时间',
  `request_param` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求参数',
  `response_time` datetime NOT NULL COMMENT '响应时间',
  `cost` int(11) NOT NULL COMMENT '耗费时间',
  `success` int(1) NOT NULL COMMENT '是否成功',
  `processed` int(1) NOT NULL COMMENT '是否已经轮询处理',
  `processed_time` datetime DEFAULT NULL COMMENT '轮询处理时间',
  `error_detail` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '错误详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for api_invoke_record_4
-- ----------------------------
DROP TABLE IF EXISTS `api_invoke_record_4`;
CREATE TABLE `api_invoke_record_4` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `app_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '应用名称',
  `sql_id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT 'sql id',
  `sqltext` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT 'sql模板内容',
  `request_time` datetime NOT NULL COMMENT '请求时间',
  `request_param` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求参数',
  `response_time` datetime NOT NULL COMMENT '响应时间',
  `cost` int(11) NOT NULL COMMENT '耗费时间',
  `success` int(1) NOT NULL COMMENT '是否成功',
  `processed` int(1) NOT NULL COMMENT '是否已经轮询处理',
  `processed_time` datetime DEFAULT NULL COMMENT '轮询处理时间',
  `error_detail` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '错误详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for app_info
-- ----------------------------
DROP TABLE IF EXISTS `app_info`;
CREATE TABLE `app_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '应用名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_index` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for app_user_group_mapping
-- ----------------------------
DROP TABLE IF EXISTS `app_user_group_mapping`;
CREATE TABLE `app_user_group_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `app_name` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '应用名称',
  `user_group_id` int(11) NOT NULL COMMENT '用户组id',
  `user_group_name` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '用户组名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for sql_operation_record
-- ----------------------------
DROP TABLE IF EXISTS `sql_operation_record`;
CREATE TABLE `sql_operation_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sql_id` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `operator_id` int(11) NOT NULL COMMENT '操作人id',
  `operator_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '操作人名称',
  `type` int(1) NOT NULL COMMENT '类型',
  `create_time` datetime NOT NULL COMMENT '时间',
  `sqltext_before` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改之前的sql模板',
  `sqltext_after` varchar(2000) COLLATE utf8mb4_bin NOT NULL COMMENT '修改之后的sql模板',
  `page_before` int(1) DEFAULT NULL COMMENT '修改前分页',
  `page_after` int(1) NOT NULL COMMENT '修改后分页',
  `parameter_mapping_before` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改前表字段映射',
  `parameter_mapping_after` varchar(2000) COLLATE utf8mb4_bin NOT NULL COMMENT '修改后表字段映射',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=292 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for sql_template
-- ----------------------------
DROP TABLE IF EXISTS `sql_template`;
CREATE TABLE `sql_template` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '对sql描述',
  `sqltext` varchar(2000) COLLATE utf8mb4_bin NOT NULL COMMENT 'sql模板',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
  `create_id` int(11) NOT NULL COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_id` int(11) DEFAULT NULL COMMENT '修改人id',
  `update_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `app_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '应用名称',
  `enable_status` int(1) NOT NULL COMMENT '状态0 正常 -1 禁用',
  `paging` int(1) NOT NULL COMMENT '是否分页',
  `parameter_mapping` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '出参映射',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户组名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for user_group_mapping
-- ----------------------------
DROP TABLE IF EXISTS `user_group_mapping`;
CREATE TABLE `user_group_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index` (`user_id`,`user_group_id`) USING BTREE COMMENT '唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '姓名邮箱账号',
  `email` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '邮箱地址',
  `last_login` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `role_type` int(1) DEFAULT NULL COMMENT '用户类型0普通用户，1管理员',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_index` (`name`) USING BTREE COMMENT '账号唯一'
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES (1, 'yejq', 'netyjq@gmail.com', '2019-06-05 16:02:27', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
