/*
 Navicat Premium Data Transfer

 Source Server         : 123
 Source Server Type    : MySQL
 Source Server Version : 100138
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 100138
 File Encoding         : 65001

 Date: 29/05/2023 16:00:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES (1, '3206016002', 'lhc', 'e10adc3949ba59abbe56e057f20f883e', 'fujian', '17185962596');
INSERT INTO `admins` VALUES (2, '3206016039', 'sky', '202cb962ac59075b964b07152d234b70', 'fujian', '13459649596');
INSERT INTO `admins` VALUES (3, '1234', 'cs', 'abcd', 'fafu', '123');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `goodId` int(10) NULL DEFAULT NULL,
  `userId` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `goodId`(`goodId`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (2, 8, 2);
INSERT INTO `cart` VALUES (34, 8, 7);
INSERT INTO `cart` VALUES (35, 24, 1);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ownerId` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ownerId`(`ownerId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (2, '鞋子', 'https://img2.baidu.com/it/u=3776985339,4095529751&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=334', '一双鞋子', '服装服饰', '50.00', 1);
INSERT INTO `goods` VALUES (3, '面包', 'https://img1.baidu.com/it/u=4052895819,3595123029&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=713', '一个面包', '食品类', '10.00', 1);
INSERT INTO `goods` VALUES (4, '三国志', 'https://img0.baidu.com/it/u=3378699822,2738172075&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500', '一本三国志', '书籍类', '25.00', 1);
INSERT INTO `goods` VALUES (6, '手机', 'https://img0.baidu.com/it/u=3114803984,2770099099&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=797', '一把手机', '电子产品', '5000.00', 1);
INSERT INTO `goods` VALUES (7, '笔记本电脑', 'https://img2.baidu.com/it/u=2794728133,3501905325&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=333', '一台电脑', '电子产品', '3000.00', 1);
INSERT INTO `goods` VALUES (8, '眼镜', 'https://img1.baidu.com/it/u=3296190125,2355464419&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500', '一双眼镜', '日常用品', '500.00', 2);
INSERT INTO `goods` VALUES (9, '咖啡', '../upload/咖啡.png', '一杯咖啡', '食品类', '21', 2);
INSERT INTO `goods` VALUES (18, '涛涛', '../upload/咖啡15.png', '测试一下', '日常用品', '122', 1);
INSERT INTO `goods` VALUES (21, 'sky', '../upload/咖啡13.png', '程序猿', '电子产品', '100', 1);
INSERT INTO `goods` VALUES (23, '宁皓彬', '../upload/咖啡16.png', '一只皓彬', '书籍类', '250.00', 1);
INSERT INTO `goods` VALUES (24, 'git', '../upload/图标4.png', 'git', '电子产品', '100.00', 1);
INSERT INTO `goods` VALUES (25, '123', '../upload/图标5.png', '132', '书籍类', '12.00', 1);

-- ----------------------------
-- Table structure for shopping
-- ----------------------------
DROP TABLE IF EXISTS `shopping`;
CREATE TABLE `shopping`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `ownerId` int(10) NULL DEFAULT NULL,
  `userId` int(10) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shopping
-- ----------------------------
INSERT INTO `shopping` VALUES (1, 2, 1, '鞋子', 'https://img2.baidu.com/it/u=3776985339,4095529751&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=334', '一双鞋子', '服装服饰', '50.00', '2023-05-21 11:53:35');
INSERT INTO `shopping` VALUES (2, 2, 1, '面包', 'https://img1.baidu.com/it/u=4052895819,3595123029&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=713', '一个面包', '食品类', '10.00', '2023-05-21 11:53:35');
INSERT INTO `shopping` VALUES (3, 2, 1, '三国志', 'https://img0.baidu.com/it/u=3378699822,2738172075&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500', '一本三国志', '书籍类', '25.00', '2023-05-21 11:53:35');
INSERT INTO `shopping` VALUES (5, 2, 2, '图标', NULL, NULL, '服装服饰', NULL, '2023-05-24 19:59:18');
INSERT INTO `shopping` VALUES (7, 1, 1, 'sky', '../upload/咖啡8.png', '一只sky', '电子产品', '100', '2023-05-22 00:56:16');
INSERT INTO `shopping` VALUES (9, 1, 1, 'sky', '../upload/咖啡8.png', '一只sky', '电子产品', '100', '2023-05-24 00:56:06');
INSERT INTO `shopping` VALUES (11, 1, 1, '时间', '../upload/咖啡11.png', '2023年5月24日01:24:10', '电子产品', '23', '2023-05-24 01:24:27');
INSERT INTO `shopping` VALUES (13, 1, 1, '手表', 'https://img0.baidu.com/it/u=284240339,1166649937&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=774', '一个手表', '日常用品', '70.00', '2023-05-24 14:04:50');
INSERT INTO `shopping` VALUES (14, 1, 2, 'github', '../upload/图标3.png', 'cs', '电子产品', '1', '2023-05-26 15:08:40');
INSERT INTO `shopping` VALUES (17, 1, 7, 'git', '../upload/咖啡3.png', 'git', '电子产品', '20', '2023-05-29 14:10:03');
INSERT INTO `shopping` VALUES (18, 1, 7, 'lyt', '../upload/咖啡14.png', '程序媛', '书籍类', '100', '2023-05-29 14:10:11');
INSERT INTO `shopping` VALUES (19, 1, 1, '书包', '../upload/咖啡1.png', '一个小书包', '文具类', '40.00', '2023-05-29 15:01:34');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '3206016039', 'sky', 'e10adc3949ba59abbe56e057f20f883e', 'fujian', '17185962596');
INSERT INTO `users` VALUES (2, '123', 'lyt', 'e10adc3949ba59abbe56e057f20f883e', '123', '滴答');
INSERT INTO `users` VALUES (3, 'zs', 'zs', 'e10adc3949ba59abbe56e057f20f883e', 'fujian', NULL);
INSERT INTO `users` VALUES (5, '1234', '123', '81dc9bdb52d04dc20036dbd8313ed055', '123', '17185962596');
INSERT INTO `users` VALUES (6, '12345', '12345', 'e10adc3949ba59abbe56e057f20f883e', '11', '12345678901');
INSERT INTO `users` VALUES (7, 'admin', 'lhc', '98eb470b2b60482e259d28648895d9e1', '@qq.com', '10086121212');

SET FOREIGN_KEY_CHECKS = 1;
