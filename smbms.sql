/*
 Navicat Premium Data Transfer

 Source Server         : lanqiao
 Source Server Type    : MySQL
 Source Server Version : 50703
 Source Host           : localhost:3306
 Source Schema         : smbms

 Target Server Type    : MySQL
 Target Server Version : 50703
 File Encoding         : 65001

 Date: 25/03/2020 22:35:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pc_user
-- ----------------------------
DROP TABLE IF EXISTS `pc_user`;
CREATE TABLE `pc_user`  (
  `id` int(11) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pc_user
-- ----------------------------
INSERT INTO `pc_user` VALUES (1, 'admin', '888888');

-- ----------------------------
-- Table structure for sm_advice
-- ----------------------------
DROP TABLE IF EXISTS `sm_advice`;
CREATE TABLE `sm_advice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'code',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sm_advice
-- ----------------------------
INSERT INTO `sm_advice` VALUES (1, '2020-03-14 23:08:37', 'Y', '阿萨德', '阿萨德', '阿萨德阿萨德啊', '1', 'asd', '125454654');
INSERT INTO `sm_advice` VALUES (2, '2020-03-15 00:31:39', 'Y', 'aasd', '1#,3单元,102', 'asdasd', NULL, 'asd', '125454654');
INSERT INTO `sm_advice` VALUES (3, '2020-03-15 00:38:42', 'Y', 'aasd', '1#,3单元,102', 'sdfsdf', NULL, 'asd', '125454654');
INSERT INTO `sm_advice` VALUES (4, '2020-03-15 00:38:49', 'Y', 'aasd', '1#,3单元,102', 'sdfssdf', NULL, 'asd', '125454654');
INSERT INTO `sm_advice` VALUES (5, '2020-03-15 00:43:30', 'Y', 'aasd', '1#,3单元,102', 'asdasd', NULL, 'asd', 'asd');
INSERT INTO `sm_advice` VALUES (6, '2020-03-15 00:44:38', 'Y', 'aasd', '1#,3单元,102', 'asdasd', NULL, 'asd', 'asd');
INSERT INTO `sm_advice` VALUES (7, '2020-03-22 13:16:27', 'Y', '用用', '1#,1单元,102', 'fdssd', NULL, 'asd1', '18536251474');
INSERT INTO `sm_advice` VALUES (8, '2020-03-22 14:00:57', 'Y', '用用', '1#,1单元,102', 'dfsdfsdfsd', NULL, 'asd1', '18536251474');
INSERT INTO `sm_advice` VALUES (9, '2020-03-25 22:03:59', 'Y', '用用', '1#,1单元,102', '水电费水电费', NULL, 'asd1', '18536251474');

-- ----------------------------
-- Table structure for sm_car
-- ----------------------------
DROP TABLE IF EXISTS `sm_car`;
CREATE TABLE `sm_car`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `id_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '车位时间',
  `money` double(255, 0) NULL DEFAULT NULL COMMENT '钱',
  `money_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否交钱',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sm_check
-- ----------------------------
DROP TABLE IF EXISTS `sm_check`;
CREATE TABLE `sm_check`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `org` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `work` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `late_num` int(11) NULL DEFAULT NULL COMMENT '累计迟到次数',
  `early_num` int(11) NULL DEFAULT NULL COMMENT '早退次数',
  `absent_num` int(11) NULL DEFAULT NULL COMMENT '旷工次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sm_check
-- ----------------------------
INSERT INTO `sm_check` VALUES (1, 'asd', 'asd', '财务部', 'asd', '2020-03-15 00:52:47', 'Y', 2, 25, 20);
INSERT INTO `sm_check` VALUES (2, '小草', NULL, '后勤部', '维修工', '2020-03-21 14:42:22', 'Y', 20, 5, 51);
INSERT INTO `sm_check` VALUES (3, '小草1', NULL, '后勤部', '维修工', '2020-03-21 14:46:03', 'Y', 20, 5, 51);
INSERT INTO `sm_check` VALUES (4, '小草2', NULL, '后勤部', '维修工', '2020-03-21 14:46:03', 'Y', 20, 5, 51);
INSERT INTO `sm_check` VALUES (5, '小草1', NULL, '后勤部', '维修工', '2020-03-21 14:46:58', 'Y', 20, 5, 51);
INSERT INTO `sm_check` VALUES (6, '小草2', NULL, '后勤部', '维修工', '2020-03-21 14:46:58', 'Y', 20, 5, 51);
INSERT INTO `sm_check` VALUES (7, '小草1', NULL, '后勤部', '维修工', '2020-03-21 14:47:10', 'Y', 20, 5, 51);
INSERT INTO `sm_check` VALUES (8, '小草2', NULL, '后勤部', '维修工', '2020-03-21 14:47:10', 'Y', 20, 5, 51);
INSERT INTO `sm_check` VALUES (9, '老王', NULL, '后勤部', '阿萨德', '2020-03-21 14:51:45', 'Y', 20, 800, 5);
INSERT INTO `sm_check` VALUES (10, '老王', NULL, '后勤部', '阿萨德', '2020-03-21 15:11:39', 'Y', 20, 80, 5);
INSERT INTO `sm_check` VALUES (11, '天津软件', NULL, '后勤部', '啦啦', '2020-03-22 13:51:23', 'Y', 1, 2, 3);
INSERT INTO `sm_check` VALUES (12, '天津软件', NULL, '后勤部', '啦啦', '2020-03-22 13:52:18', 'Y', 1, 2, 3);
INSERT INTO `sm_check` VALUES (13, '物业', NULL, '后勤', 'as大声道', '2020-03-22 13:55:31', 'Y', 20, 3, 4);
INSERT INTO `sm_check` VALUES (14, '老王', NULL, '后勤部', '阿萨德', '2020-03-25 21:20:22', 'Y', 20, 80, 5);
INSERT INTO `sm_check` VALUES (15, '阿萨德', NULL, '阿萨德', 'as', '2020-03-25 21:54:42', 'Y', 1, 2, 30);

-- ----------------------------
-- Table structure for sm_clean
-- ----------------------------
DROP TABLE IF EXISTS `sm_clean`;
CREATE TABLE `sm_clean`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `org` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `work` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位',
  `work_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作时间',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `end_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sm_clean
-- ----------------------------
INSERT INTO `sm_clean` VALUES (1, 'asd', NULL, 'asd', 'asd', '2020-03-04', '2020-03-15 01:24:47', 'Y', 'asdasd', '2020-03-27');
INSERT INTO `sm_clean` VALUES (2, 'sdf', NULL, 'sdf', 'sdf', '2020-03-12', '2020-03-15 01:30:56', 'Y', 'sdf', '2020-03-28');
INSERT INTO `sm_clean` VALUES (3, 'sdf', NULL, 'sdf', 'sd', '2020-03-04', '2020-03-15 01:32:06', 'Y', 'sdfsdfdfg', '2020-03-27');
INSERT INTO `sm_clean` VALUES (4, 'wer', NULL, 'wer', 'wer', '2020-03-03', '2020-03-15 01:47:39', 'Y', 'werwer', '2020-03-25');
INSERT INTO `sm_clean` VALUES (5, 'asdasd', NULL, 'asd', 'asd', '2020-03-02', '2020-03-15 01:57:54', 'Y', 'asdasdasd', '2020-03-19');
INSERT INTO `sm_clean` VALUES (6, '物业', NULL, '后勤部', '维修', '2020-03-02', '2020-03-22 13:58:17', 'Y', '山西', '2020-03-19');
INSERT INTO `sm_clean` VALUES (7, 'SADFSDFSDF', NULL, 'SDF', 'SDF', '2020-02-25', '2020-03-25 21:49:32', 'Y', 'SDFSDF', '2020-04-01');

-- ----------------------------
-- Table structure for sm_login
-- ----------------------------
DROP TABLE IF EXISTS `sm_login`;
CREATE TABLE `sm_login`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `id_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `work` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位',
  `org` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `profee` double(255, 0) NULL DEFAULT NULL COMMENT '物业费',
  `waterfee` double(255, 0) NULL DEFAULT NULL COMMENT '水电费',
  `heatfee` double(255, 0) NULL DEFAULT NULL COMMENT '取暖费',
  `fee_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物业缴费状态',
  `car_money` double(255, 0) NULL DEFAULT NULL COMMENT '车位费',
  `car_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车位状态',
  `end_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结束日期',
  `all_num` double(255, 0) NULL DEFAULT NULL COMMENT '物业总计',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sm_login
-- ----------------------------
INSERT INTO `sm_login` VALUES (2, 'lisi', '888888', '3', '2020-03-16 21:09:58', 'Y', '李四', '18535218596', '1564456456789798', 'asd', '1#,1单元,102', '维修123456', '后勤部', 2500, 1800, 2200, 'N', 100000, 'N', '2020', 6500);
INSERT INTO `sm_login` VALUES (6, 'admin', '888888', '1', '2020-03-16 21:10:23', 'Y', '阿萨德', '18336524569', '156445645678', 'asd', '1#,3单元,102', '维修', NULL, 2500, 1800, 2200, 'N', 100000, 'N', '2020', 6500);
INSERT INTO `sm_login` VALUES (7, 'yong', '888888', '2', '2020-03-16 21:10:27', 'Y', '用用', '18536251474', '156445645699999456', 'qwe', '1#,1单元,102', '维修', NULL, 2500, 1800, 2200, 'Y', 100000, 'N', '2020', 6500);

-- ----------------------------
-- Table structure for sm_money
-- ----------------------------
DROP TABLE IF EXISTS `sm_money`;
CREATE TABLE `sm_money`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `id_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `money` double(255, 0) NULL DEFAULT NULL COMMENT '总计',
  `money_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缴费状态',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `profee` double(255, 0) NULL DEFAULT NULL COMMENT '物业费',
  `waterfee` double(255, 0) NULL DEFAULT NULL COMMENT '水电费',
  `heatfee` double(255, 0) NULL DEFAULT NULL COMMENT '取暖费',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sm_reper
-- ----------------------------
DROP TABLE IF EXISTS `sm_reper`;
CREATE TABLE `sm_reper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品',
  `num` int(255) NULL DEFAULT NULL COMMENT '数量',
  `money` double(255, 0) NULL DEFAULT NULL COMMENT '钱数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sm_reper
-- ----------------------------
INSERT INTO `sm_reper` VALUES (1, '120V空气开关', 45, 456);
INSERT INTO `sm_reper` VALUES (2, '50水管', 99, 750);
INSERT INTO `sm_reper` VALUES (4, '50水龙头', 18, 60);
INSERT INTO `sm_reper` VALUES (5, '防烟报警阀', 100, 80);

-- ----------------------------
-- Table structure for sm_serve
-- ----------------------------
DROP TABLE IF EXISTS `sm_serve`;
CREATE TABLE `sm_serve`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `serve_man` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修人',
  `serve_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修人手机',
  `thing` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配件',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `money` int(255) NULL DEFAULT NULL COMMENT '钱数',
  `serve_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修状态',
  `money_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缴费状态',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业主code',
  `up_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修人code',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业主电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sm_serve
-- ----------------------------
INSERT INTO `sm_serve` VALUES (15, '用用', '1#,1单元,102', '李四', '18535218596', '50水龙头', 1, 60, '完成报修', '待支付', '2020-03-25 22:14:23', 'yong', 'lisi', '18536251474');

-- ----------------------------
-- Table structure for sm_user
-- ----------------------------
DROP TABLE IF EXISTS `sm_user`;
CREATE TABLE `sm_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
