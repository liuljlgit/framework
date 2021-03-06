/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : framework

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-07-23 00:06:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for com_form
-- ----------------------------
DROP TABLE IF EXISTS `com_form`;
CREATE TABLE `com_form` (
  `form_id` bigint(20) NOT NULL COMMENT '主键',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `form_name` varchar(50) DEFAULT NULL COMMENT '表单名称',
  `descp` varchar(50) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`form_id`),
  UNIQUE KEY `IDX_FORM_NAME` (`form_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='8、表单权限管理表';

-- ----------------------------
-- Records of com_form
-- ----------------------------
INSERT INTO `com_form` VALUES ('1000001', '1000001', 'userEditForm03', '编辑表单');
INSERT INTO `com_form` VALUES ('1000002', '1000001', 'roleEditForm03', '角色表单');
INSERT INTO `com_form` VALUES ('1000003', '1000001', 'roleAuthForm03', '角色权限');
INSERT INTO `com_form` VALUES ('1000004', '1000002', 'deptEditForm03', '部门表单');
INSERT INTO `com_form` VALUES ('1000005', '1000002', 'menuEditForm01', '菜单表单');
INSERT INTO `com_form` VALUES ('1000006', '1000002', 'myCustomer', '我的客户');
INSERT INTO `com_form` VALUES ('1000007', '1000003', 'publicCustomer', '客户公海');
INSERT INTO `com_form` VALUES ('1000008', '1000003', 'contact', '联系人');

-- ----------------------------
-- Table structure for com_menu
-- ----------------------------
DROP TABLE IF EXISTS `com_menu`;
CREATE TABLE `com_menu` (
  `menu_id` bigint(20) NOT NULL COMMENT '主键',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名',
  `par_menu_id` bigint(20) DEFAULT NULL COMMENT '父级菜单id',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `weight` tinyint(3) DEFAULT NULL COMMENT '排序权重',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_enable` tinyint(3) DEFAULT '1' COMMENT '状态：正常/已删除',
  PRIMARY KEY (`menu_id`),
  KEY `com_menu_index` (`par_menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='3、菜单表';

-- ----------------------------
-- Records of com_menu
-- ----------------------------
INSERT INTO `com_menu` VALUES ('1000001', '总览', '0', '', 'overView', '1', '2019-11-04 11:16:41', '1');
INSERT INTO `com_menu` VALUES ('1000002', '数据概览', '1000001', '/index', '', '1', '2019-11-04 11:16:52', '1');
INSERT INTO `com_menu` VALUES ('1000003', '交易决策-现货版', '0', '', 'decisionSpot', '2', '2019-11-04 11:17:02', '1');
INSERT INTO `com_menu` VALUES ('1000004', '交易决策-电量版', '0', '', 'decisionElec', '3', '2019-11-04 11:17:44', '1');
INSERT INTO `com_menu` VALUES ('1000005', '数据管理', '0', '', 'dataManage', '4', '2019-11-04 11:17:53', '1');
INSERT INTO `com_menu` VALUES ('1000006', '交易测算', '1000003', '/transactionDecisionSpot/calculate', '', '1', '2019-11-04 11:18:03', '1');
INSERT INTO `com_menu` VALUES ('1000007', '方案列表', '1000003', '/transactionDecisionSpot/list', '', '2', '2019-11-04 11:18:13', '1');
INSERT INTO `com_menu` VALUES ('1000008', '方案对比', '1000003', '/transactionDecisionSpot/comparison', '', '3', '2019-11-04 11:18:48', '1');
INSERT INTO `com_menu` VALUES ('1000009', '交易测算', '1000004', '/transactionDecisionElec/calculate', '', '1', '2019-11-04 11:18:58', '1');
INSERT INTO `com_menu` VALUES ('1000010', '方案列表', '1000004', '/transactionDecisionElec/list', '', '2', '2019-11-04 11:19:07', '1');
INSERT INTO `com_menu` VALUES ('1000011', '方案对比', '1000004', '/transactionDecisionElec/comparison', '', '3', '2019-11-04 11:19:17', '1');
INSERT INTO `com_menu` VALUES ('1000012', '机组调频数据', '1000005', '/dataManage/genFrm', '', '1', '2019-11-04 11:19:26', '1');
INSERT INTO `com_menu` VALUES ('1000013', '电量市场数据', '1000005', '/dataManage/elecMarket', '', '2', '2019-11-04 11:19:34', '1');
INSERT INTO `com_menu` VALUES ('1000014', '现货市场数据', '1000005', '/dataManage/spotMarket', '', '3', '2019-11-04 11:19:42', '1');
INSERT INTO `com_menu` VALUES ('1000015', '调频市场数据', '1000005', '/dataManage/frmMarket', '', '4', '2019-11-04 11:19:53', '1');
INSERT INTO `com_menu` VALUES ('1000016', '调频结算数据', '1000005', '/dataManage/settlementMarket', '', '5', '2019-11-04 11:20:02', '1');

-- ----------------------------
-- Table structure for com_role
-- ----------------------------
DROP TABLE IF EXISTS `com_role`;
CREATE TABLE `com_role` (
  `role_id` bigint(20) NOT NULL COMMENT '主键',
  `role_code` varchar(50) DEFAULT NULL,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `permit_urls` varchar(1000) DEFAULT NULL,
  `forbid_urls` varchar(1000) DEFAULT NULL,
  `menu_ids` varchar(1000) DEFAULT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_enable` tinyint(3) DEFAULT '1' COMMENT '状态：正常/已删除',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `IDX_ROLE_NAME` (`role_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='2、角色表';

-- ----------------------------
-- Records of com_role
-- ----------------------------
INSERT INTO `com_role` VALUES ('1000001', 'ADMIN', '超级管理员', '/**', '', null, '2019-10-17 11:37:30', '1');
INSERT INTO `com_role` VALUES ('1000002', 'NORMAL', '普通角色', '/**', '', null, null, '1');

-- ----------------------------
-- Table structure for com_user
-- ----------------------------
DROP TABLE IF EXISTS `com_user`;
CREATE TABLE `com_user` (
  `user_id` bigint(20) NOT NULL COMMENT '主键',
  `account` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '账号（手机号）',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码（bcrypt加密）',
  `role_ids` varchar(1000) DEFAULT NULL COMMENT '角色id,逗号分隔',
  `enabled` tinyint(4) DEFAULT NULL COMMENT '可用性 :true:1 false:0',
  `account_non_expired` tinyint(4) DEFAULT NULL COMMENT '过期性 :true:1 false:0',
  `credentials_non_expired` tinyint(4) DEFAULT NULL COMMENT '有效性 :true:1 false:0',
  `account_non_locked` tinyint(4) DEFAULT NULL COMMENT '锁定性 :true:1 false:0',
  `status` tinyint(3) DEFAULT '1' COMMENT '状态：正常/禁用',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='1、用户表';

-- ----------------------------
-- Records of com_user
-- ----------------------------
INSERT INTO `com_user` VALUES ('1000001', 'admin', 'admin', '$2a$10$LFKYVqSeWLwuRzSK57Ef5.x2KZVhVogg8u9ZOLUtDVhPvgduRLJYO', '', '1', '1', '1', '1', '1', '2020-07-22 22:16:44');
INSERT INTO `com_user` VALUES ('1000048', '13427538901', '13427538901', '$2a$10$niDEoOUOFm5ilJ3n.M5WqOI.rDYckVILvsv6UAXynQkI/DmbIZW7y', '1000001', '1', '1', '1', '1', '1', '2020-07-22 22:16:44');
INSERT INTO `com_user` VALUES ('1000049', '13711425321', '海雷', '$2a$10$qbhW6PneWj1rSJsO2Ndqj.j94KiDcO7P0xpIM2h5t/mHtBJFzFZ3u', '1000001', '1', '1', '1', '1', '1', '2020-07-22 22:16:45');
INSERT INTO `com_user` VALUES ('1000050', '13800000001', '李贤惠', '$2a$10$jfbwMIdkK8k0HDQ9NHqc/uuVCR2xEI4viDc1SdwcklecCI6DW9FQu', '1000001', '1', '1', '1', '1', '1', '2020-07-22 22:16:46');
INSERT INTO `com_user` VALUES ('1000051', '158182196931', 'JOE', '$2a$10$zTNxGbxpYOhhkgOnMpsBuuIlfx6jWNFMXH8CrVBcBg4MILXbjfpEq', '1000001', '1', '1', '1', '1', '1', '2020-07-22 22:16:46');
INSERT INTO `com_user` VALUES ('1000052', '18079101905', '许丹', '$2a$10$GkpSjupugG4QjqbFCkXapuu3RG/oaAJ96TUflc4Q/1O6W7s3IXfuy', '1000001', '1', '1', '1', '1', '1', '2020-07-22 22:16:47');
INSERT INTO `com_user` VALUES ('1000053', '18889102887', '吴育飞', '$2a$10$Hxocw/aII30DPJvx8fzUkuybYhmnp72QLMplFTVh3FrWuI8cqtcOa', '1000001', '1', '1', '1', '1', '1', '2020-07-22 22:16:47');
INSERT INTO `com_user` VALUES ('1000054', 'liulijun', 'liulijun', '$2a$10$mJiczmhOlYCDYH528l7IzewJesK79Vc/FiLS2Akfi598fFzazsdMe', '1000001', '1', '1', '1', '1', '1', '2020-07-22 22:56:30');
INSERT INTO `com_user` VALUES ('1000055', '13168745249', '荣权', '$2a$10$t/4sbzF0h36NUw4mJXgBgu2UnTK.bAZSe2WD8VQm4jFsWOghFjIg6', '1000001,1000002', '1', '1', '1', '1', '1', '2020-07-22 22:16:49');
INSERT INTO `com_user` VALUES ('1000056', '1380000001', 'joe', '$2a$10$lxkc3nC23oyCVUYFGuygB.jYAFXGGGEFtw7LCNSf4/fjUqmxBzSs2', '1000001', '1', '1', '1', '1', '1', '2020-07-22 22:16:49');
INSERT INTO `com_user` VALUES ('1000057', '13400000001', 'abc', '$2a$10$0hQ1tULCtqWH9R.mZInKUO9Cx6OPDpeVXeW12sfQq2UYNyRYbUiR2', '1000001', '1', '1', '1', '1', '1', '2020-07-22 22:16:50');
INSERT INTO `com_user` VALUES ('1000058', '18826222492', 'test1', '$2a$10$Pb3oiXXCi5kMXbt53Z/o/.CKs7yUtmfKEdHFHLv/Rklx4nIY0M1ea', '1000001', '1', '1', '1', '1', '1', '2020-07-22 22:16:50');
INSERT INTO `com_user` VALUES ('1000059', '18826222493', 'test2', '$2a$10$J4TRqiizgUqpd.bCXx.EOOHXyCJ3NeAKloOHGlnalsPZo5WPoR.jm', '1000001', '1', '1', '1', '1', '1', '2020-07-22 22:16:55');

-- ----------------------------
-- Table structure for gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `gateway_route`;
CREATE TABLE `gateway_route` (
  `gr_id` bigint(20) NOT NULL,
  `route_id` varchar(50) NOT NULL COMMENT '路由ID',
  `reource_id` varchar(50) DEFAULT NULL COMMENT '资源服务器配置的资源id',
  `instance_id` varchar(50) NOT NULL COMMENT 'eureka中注册的id',
  `predicates` varchar(200) NOT NULL COMMENT '路由规则，多个的时候用逗号划分',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `modify_time` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `regexp_url` varchar(50) NOT NULL COMMENT '网关请求过来的url前缀',
  `status` decimal(1,0) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`gr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网关路由规则配置';

-- ----------------------------
-- Records of gateway_route
-- ----------------------------
INSERT INTO `gateway_route` VALUES ('1', 'frame-auth-server', 'auth-server', 'lb://frame-auth-server', '/app/auth/**', '鉴权服务', '2019-10-17 11:35:21.000000', null, '/app/auth', '1');
INSERT INTO `gateway_route` VALUES ('2', 'frame-resource-server', 'resource-server', 'lb://frame-resource-server', '/app/resource1/**', '资源服务', '2019-10-17 11:37:30.000000', null, '/app/resource1', '1');
INSERT INTO `gateway_route` VALUES ('3', 'frame-gateway-server', null, 'lb://frame-gateway-server', '/app/gateway/**', '网关服务', '2019-11-07 11:24:34.000000', null, '/app/gateway', '1');

-- ----------------------------
-- Table structure for holiday
-- ----------------------------
DROP TABLE IF EXISTS `holiday`;
CREATE TABLE `holiday` (
  `h_id` bigint(20) NOT NULL,
  `year` int(10) DEFAULT NULL COMMENT '年份',
  `count` tinyint(4) DEFAULT NULL COMMENT '天数',
  `holiday_type` tinyint(4) DEFAULT NULL COMMENT '节假日类型:1-元旦；2-春节；3-清明节; 4-劳动节;5-端午节;6-中秋节;7-国庆节;8-其它;9-检修计划;10-停产计划;11-增加容量;12-减少容量;13-调班;14-周末加班',
  `descript` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 (0) 弃用 （1）正常',
  `create_time` datetime DEFAULT NULL COMMENT ']创建时间',
  `status_time` datetime DEFAULT NULL COMMENT '状态时间',
  PRIMARY KEY (`h_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='调频-数据管理：1-4、节假日主表（数据来自汇电）';

-- ----------------------------
-- Records of holiday
-- ----------------------------
INSERT INTO `holiday` VALUES ('1000001', '2018', '1', '1', '元旦', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000002', '2018', '7', '2', '春节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000003', '2018', '3', '3', '清明节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000004', '2018', '3', '4', '劳动节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000005', '2018', '3', '5', '端午节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000006', '2018', '3', '6', '中秋节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000007', '2018', '7', '7', '国庆节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000008', '2017', '1', '1', '元旦', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000009', '2017', '7', '2', '春节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000010', '2017', '3', '3', '清明节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000011', '2017', '3', '4', '劳动节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000012', '2017', '3', '5', '端午节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000013', '2017', '3', '6', '中秋节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000014', '2017', '7', '7', '国庆节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000015', '2019', '3', '1', '元旦', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000016', '2019', '7', '2', '春节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000017', '2019', '3', '3', '清明', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000018', '2019', '1', '4', '劳动节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000019', '2019', '3', '5', '端午节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000020', '2019', '3', '6', '中秋节', null, '2019-11-05 01:00:02', null);
INSERT INTO `holiday` VALUES ('1000021', '2019', '7', '7', '国庆节', null, '2019-11-05 01:00:02', null);

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client_1', '', '{bcrypt}$2a$10$u7v0.1Xd3pYf3Stqd3MrXOUXWX1s70rppAOuCwNKrp/RRxFJwwwBO', 'server', 'refresh_token,password', null, '', '86400', '100000', '{}', '');
INSERT INTO `oauth_client_details` VALUES ('gateway_client', null, '{bcrypt}$2a$10$SakEfSbN7x4NSWaZq60T7ePn3ZCmFAJ94npOf/Q2NJzx.KkYnlK16', 'all', 'password,refresh_token', null, null, '86400', '100000', null, null);
