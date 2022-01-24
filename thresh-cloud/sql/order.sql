/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : febs_cloud_base

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 11/07/2021 14:27:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` bigint NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `cancel_time` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
INSERT INTO `order` VALUES (1, '1', '2021-01-01 13:05:01', '2021-01-01 13:05:02');
INSERT INTO `order` VALUES (2, '1', '2021-01-01 15:00:38', '2021-01-01 15:05:55');
INSERT INTO `order` VALUES (3, '1', '2021-01-01 18:25:33', NULL);
INSERT INTO `order` VALUES (4, '2', '2021-01-02 09:00:15', '2021-01-02 09:05:26');
INSERT INTO `order` VALUES (5, '2', '2021-01-03 10:00:46', '2021-01-03 10:05:00');
INSERT INTO `order` VALUES (6, '2', '2021-01-03 12:44:23', '2021-01-03 12:48:38');
INSERT INTO `order` VALUES (7, '1', '2021-01-02 13:05:01', '2021-01-01 13:05:02');
INSERT INTO `order` VALUES (8, '1', '2021-01-02 15:00:38', '2021-01-01 15:05:55');
INSERT INTO `order` VALUES (9, '1', '2021-01-02 18:25:33', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
