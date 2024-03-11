/*
 Navicat Premium Data Transfer

 Source Server         : 47.96.166.60
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 47.96.166.60:3306
 Source Schema         : lb_demo

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 10/03/2024 23:14:18
*/
CREATE DATABASE IF NOT EXISTS lb_demo;
use lb_demo;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (2, 'ceshi');
INSERT INTO `product` VALUES (21, '被造太示领然');
INSERT INTO `product` VALUES (47, '果数总了');
INSERT INTO `product` VALUES (65, '增名华种阶');
INSERT INTO `product` VALUES (76, '了须社山放打');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `code` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '资源编码\n规则：\n链接：\n数据列：\n按钮：',
  `name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '接口名称',
  `menu_id` bigint NULL DEFAULT NULL COMMENT '菜单ID\n#c_auth_menu',
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `describe_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '接口描述',
  `application_id` bigint NULL DEFAULT NULL COMMENT '应用id',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint NULL DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UN_CODE`(`code` ASC) USING BTREE COMMENT '编码唯一'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (643445116756821697, 'product:add', '添加', 603982713849908801, 'POST', '/product', '', 1, 3, '2024-03-09 13:41:11', 3, '2024-03-09 13:41:11');
INSERT INTO `resource` VALUES (643445162915137313, 'product:update', '修改', 603982713849908801, 'PUT', '/product', '', 1, 3, '2024-03-09 13:41:11', 3, '2024-03-09 13:41:11');
INSERT INTO `resource` VALUES (643445197954353025, 'product:delete', '删除', 603982713849908801, 'DELETE', '/product', '', 1, 3, '2024-03-09 13:41:11', 3, '2024-03-09 13:41:11');
INSERT INTO `resource` VALUES (643445229575210977, 'product:page', '分页', 603982713849908801, 'GET', '/product/list', '', 1, 3, '2024-03-09 13:41:11', 3, '2024-03-09 13:41:11');
INSERT INTO `resource` VALUES (643445229575210978, 'role/findAllRoles', '查询所有角色', NULL, 'GET', '/role/findAllRoles', '', 1, 3, '2024-03-10 22:50:32', 3, '2024-03-09 13:41:11');
INSERT INTO `resource` VALUES (643445229575210979, 'role/findRoleByUserId', '查询用户角色', NULL, 'GET', '/role/findRoleByUserId', '', 1, 3, '2024-03-10 22:50:32', 3, '2024-03-09 13:41:11');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '角色编码',
  `describe_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '功能描述',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '状态',
  `readonly` bit(1) NULL DEFAULT b'0' COMMENT '是否内置角色',
  `ds_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'SELF' COMMENT '数据权限类型\n#DataScopeType{ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人;}',
  `repel` bigint NULL DEFAULT NULL COMMENT '互斥角色',
  `application_id` bigint NULL DEFAULT NULL COMMENT '应用id',
  `create_user` bigint NULL DEFAULT 0 COMMENT '创建人id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint NULL DEFAULT 0 COMMENT '更新人id',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UN_CODE`(`code` ASC, `application_id` ASC) USING BTREE,
  UNIQUE INDEX `PAR_NAME`(`name` ASC, `application_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (100, '管理员', 'PT_ADMIN', '平台内置管理员', b'1', b'1', 'ALL', 834004562678405921, 1, 1, '2024-03-09 01:18:41', 1, '2024-03-09 01:18:41');
INSERT INTO `role` VALUES (643779012732130273, 'USER', 'BASE_USER', '只有最基本的权限', b'1', b'0', 'CUSTOMIZE', 834004562678405921, 1, 3, '2024-03-09 01:18:41', 1, '2024-03-09 01:18:41');
INSERT INTO `role` VALUES (645198153556958497, 'EDITOR', 'DEPT_MANAGER', '管理本级以及子级用户', b'1', b'0', 'THIS_LEVEL_CHILDREN', 834004562678405921, 1, 3, '2024-03-09 01:18:41', 1, '2024-03-09 01:18:41');
INSERT INTO `role` VALUES (830513015051781377, 'PRODUCT_ADMIN', 'BJUMUZBD', 'PRODUCT_ADMIN', b'1', b'0', 'ALL', NULL, 819611999649398913, 1, '2024-03-09 01:18:41', 1, '2024-03-09 01:18:41');

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority`  (
  `id` bigint NOT NULL COMMENT '主键',
  `authority_id` bigint NOT NULL COMMENT '资源id\n#c_auth_resource\n#c_auth_menu',
  `authority_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'MENU' COMMENT '权限类型\n#AuthorizeType{MENU:菜单;RESOURCE:资源;}',
  `role_id` bigint NOT NULL COMMENT '角色id\n#c_auth_role',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint NULL DEFAULT 0 COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_KEY`(`role_id` ASC, `authority_type` ASC, `authority_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色的资源' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of role_authority
-- ----------------------------
INSERT INTO `role_authority` VALUES (831120082259157217, 643445229575210977, 'RESOURCE', 643779012732130273, '2024-03-09 13:41:11', 1);
INSERT INTO `role_authority` VALUES (831120082271740161, 643445116756821697, 'RESOURCE', 645198153556958497, '2024-03-09 13:41:11', 1);
INSERT INTO `role_authority` VALUES (831120082271740193, 643445162915137313, 'RESOURCE', 645198153556958497, '2024-03-09 13:41:11', 1);
INSERT INTO `role_authority` VALUES (831120082271740225, 643445197954353025, 'RESOURCE', 645198153556958497, '2024-03-09 13:41:11', 1);
INSERT INTO `role_authority` VALUES (831120082275934561, 643445229575210977, 'RESOURCE', 645198153556958497, '2024-03-09 13:41:11', 1);
INSERT INTO `role_authority` VALUES (831120082275934593, 643445116756821697, 'RESOURCE', 830513015051781377, '2024-03-09 13:41:11', 1);
INSERT INTO `role_authority` VALUES (831120082280128929, 643445162915137313, 'RESOURCE', 830513015051781377, '2024-03-09 13:41:11', 1);
INSERT INTO `role_authority` VALUES (831120082280128961, 643445197954353025, 'RESOURCE', 830513015051781377, '2024-03-09 13:41:11', 1);
INSERT INTO `role_authority` VALUES (831120082280128962, 643445229575210978, 'RESOURCE', 830513015051781377, '2024-03-09 13:41:11', 1);
INSERT INTO `role_authority` VALUES (831120082280128963, 643445229575210979, 'RESOURCE', 830513015051781377, '2024-03-09 13:41:11', 1);
INSERT INTO `role_authority` VALUES (831120082284323297, 643445229575210977, 'RESOURCE', 830513015051781377, '2024-03-09 13:41:11', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `account` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `org_id` bigint NULL DEFAULT NULL COMMENT '组织ID\n#c_core_org',
  `station_id` bigint NULL DEFAULT NULL COMMENT '岗位ID\n#c_core_station',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机',
  `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '性别\n#Sex{W:女;M:男;N:未知}',
  `status` bit(1) NULL DEFAULT b'0' COMMENT '启用状态 1启用 0禁用',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像',
  `work_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '工作描述\r\n比如：  市长、管理员、局长等等   用于登陆展示',
  `password_error_last_time` datetime NULL DEFAULT NULL COMMENT '最后一次输错密码时间',
  `password_error_num` int NULL DEFAULT 0 COMMENT '密码错误次数',
  `password_expire_time` datetime NULL DEFAULT NULL COMMENT '密码过期时间',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `ldap_uid` bigint NULL DEFAULT NULL COMMENT 'ldap对应的uidNumber',
  `superior` bigint NULL DEFAULT NULL COMMENT '上级领导',
  `create_user` bigint NULL DEFAULT 0 COMMENT '创建人id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint NULL DEFAULT 0 COMMENT '更新人id',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UN_ACCOUNT`(`account` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '平台管理员', 860164099101696673, 857940157460957537, '', '13756644565', 'M', b'1', 'http://oss-file-services.oss-cn-zhangjiakou.aliyuncs.com/pd_files/2021/04/ffdc9d9b-287f-4c5c-beb5-8487132898d3.jpg', '超级管理员', '2024-01-31 00:13:43', 0, NULL, 'e10adc3949ba59abbe56e057f20f883e', '2024-03-09 01:18:41', NULL, NULL, 1, '2024-03-09 01:18:41', 1, '2021-08-09 11:00:28');
INSERT INTO `user` VALUES (856959053190927169, 'user_1', 'user_1', 856959818055818209, 856959193226155201, NULL, '18510971101', 'M', b'1', '', '', '2021-08-27 02:41:10', 0, NULL, '3f49044c1469c6990a665f46ec6c0a41', '2024-03-09 01:18:41', 1000, NULL, 1, '2024-03-09 01:18:41', 1, '2021-08-30 15:32:51');
INSERT INTO `user` VALUES (856961019472258305, 'editor_1', 'editor_1', 856959868358106209, 856960124659441089, NULL, '18510971102', 'M', b'1', '', '', '2021-08-11 07:37:10', 0, NULL, '12ca3a0d3aa45bbcbc13d1cb4c9c6467', '2024-03-09 01:18:41', 1001, 857949190481772609, 856959053190927169, '2024-03-09 01:18:41', 1, '2021-08-30 15:32:18');
INSERT INTO `user` VALUES (857945286130066209, 'adm_1', 'adm_1', 860164099101696673, 857940157460957537, NULL, '17610012001', 'M', b'1', '', '', '2021-08-30 06:41:27', 0, NULL, 'b3ac4c6a9c24ca82afcc1321ed94c4e4', '2024-03-09 01:18:41', 1002, 857945286130066209, 856959053190927169, '2024-03-09 01:18:41', 1, '2021-08-30 15:32:07');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint NOT NULL,
  `role_id` bigint NOT NULL DEFAULT 0 COMMENT '角色ID\n#c_auth_role',
  `user_id` bigint NOT NULL DEFAULT 0 COMMENT '用户ID\n#c_core_accou',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0用户 1 用户组',
  `application_id` bigint NULL DEFAULT NULL COMMENT '应用id',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_KEY`(`role_id` ASC, `user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色分配\r\n账号角色绑定' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (872881535085183489, 643779012732130273, 856959053190927169, 0, 856958443553031905, 856959053190927169, '2024-03-09 01:18:41');
INSERT INTO `user_role` VALUES (872906209529696481, 645198153556958497, 856961019472258305, 0, 856958443553031905, 1, '2024-03-09 01:18:41');
INSERT INTO `user_role` VALUES (873256177813685601, 830513015051781377, 857945286130066209, 0, 856958443553031905, 1, '2024-03-09 01:18:41');

SET FOREIGN_KEY_CHECKS = 1;
