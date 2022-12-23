/*
 Navicat Premium Data Transfer

 Source Server         : book
 Source Server Type    : MySQL
 Source Server Version : 50650 (5.6.50-log)
 Source Host           : 106.52.76.86:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 50650 (5.6.50-log)
 File Encoding         : 65001

 Date: 23/12/2022 13:09:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `bookNo` int(4) NOT NULL COMMENT '书号',
  `bookName` varchar(16) NOT NULL COMMENT '书名',
  `bookAuthor` varchar(32) NOT NULL COMMENT '作者',
  `bookQuantity` int(8) NOT NULL COMMENT '库存数',
  `bookBorrowNum` int(8) NOT NULL DEFAULT '0' COMMENT '借阅人数',
  `bookCover` varchar(255) NOT NULL COMMENT '图书封面链接',
  `bookCategoryId` int(1) unsigned zerofill NOT NULL COMMENT '图书分类ID',
  `created_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '入库时间',
  `bookContent` longtext NOT NULL COMMENT '图书内容',
  `bookDescription` varchar(255) NOT NULL COMMENT '图书描述',
  PRIMARY KEY (`id`,`bookNo`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_borrow
-- ----------------------------
DROP TABLE IF EXISTS `t_borrow`;
CREATE TABLE `t_borrow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `bookNo` int(11) NOT NULL COMMENT '借阅的图书ID',
  `created_time` datetime NOT NULL COMMENT '借阅时间',
  `back_time` datetime DEFAULT NULL COMMENT '归还时间',
  `userId` int(11) NOT NULL COMMENT '借阅人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` int(1) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(255) NOT NULL COMMENT '分类名',
  `created_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_manager_system_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_manager_system_notice`;
CREATE TABLE `t_manager_system_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '通知ID',
  `title` varchar(255) NOT NULL COMMENT '通知标题',
  `content` text NOT NULL COMMENT '通知内容',
  `type` varchar(255) NOT NULL COMMENT '发给哪些用户：单用户 single；全体用户 all ,多用户 multi',
  `state` int(11) NOT NULL COMMENT '是否已被拉取过，如果已经拉取过，就无需再次拉取',
  `recipient_id` varchar(255) NOT NULL COMMENT '接受通知的用户的 ID，如果 type 为单用户，那么 recipient 为该用户的 ID;否则 recipient 为 0, 如果为多用户 逗号分隔',
  `manager_id` int(11) NOT NULL COMMENT '发布通知的管理员 ID\n\n',
  `created_time` datetime NOT NULL COMMENT '发布时间\n',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_user_log
-- ----------------------------
DROP TABLE IF EXISTS `t_user_log`;
CREATE TABLE `t_user_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `created_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `type` varchar(255) NOT NULL COMMENT '操作',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_user_system_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_user_system_notice`;
CREATE TABLE `t_user_system_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `state` int(11) NOT NULL COMMENT '是否已读',
  `system_notice_id` int(11) NOT NULL COMMENT '系统通知的 ID\n\n',
  `recipient_id` int(11) NOT NULL COMMENT '接受通知的用户的 ID',
  `created_time` datetime NOT NULL COMMENT '拉取通知的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `studentId` varchar(255) NOT NULL COMMENT '学生学号',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '学生账号状态 [true启用,false禁用]',
  `avatar` varchar(255) DEFAULT NULL COMMENT '学生头像',
  `isAdmin` int(255) NOT NULL DEFAULT '0' COMMENT '是否是管理员',
  `created_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '学生注册时间',
  `borrowNum` int(11) NOT NULL DEFAULT '0' COMMENT '借阅次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
