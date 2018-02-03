/*
Navicat MySQL Data Transfer

Source Server         : zpz2
Source Server Version : 50716
Source Host           : 106.14.5.35:3306
Source Database       : shareschool

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-02-03 13:06:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `db_collect`
-- ----------------------------
DROP TABLE IF EXISTS `db_collect`;
CREATE TABLE `db_collect` (
  `collect_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned zerofill NOT NULL,
  `task_id` int(11) unsigned zerofill NOT NULL,
  `collect_state` int(1) DEFAULT '0' COMMENT '0正被收藏，1曾经收藏',
  `collect_remarks` varchar(255) DEFAULT NULL,
  `collect_createtime` timestamp NULL DEFAULT NULL,
  `collect_changetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`collect_id`),
  KEY `collect_user_id` (`user_id`),
  KEY `collect_task_id` (`task_id`),
  CONSTRAINT `collect_task_id` FOREIGN KEY (`task_id`) REFERENCES `db_task` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `collect_user_id` FOREIGN KEY (`user_id`) REFERENCES `db_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_collect
-- ----------------------------

-- ----------------------------
-- Table structure for `db_comment`
-- ----------------------------
DROP TABLE IF EXISTS `db_comment`;
CREATE TABLE `db_comment` (
  `comment_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned zerofill NOT NULL,
  `comment_to_user_id` int(11) unsigned zerofill DEFAULT NULL,
  `task_id` int(11) unsigned zerofill NOT NULL,
  `comment_content` varchar(255) NOT NULL,
  `comment_state` int(1) DEFAULT '0' COMMENT '是否被删除，0没有，1是',
  `comment_createtime` timestamp NULL DEFAULT NULL,
  `comment_changetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comment_user_id` (`user_id`),
  KEY `comment_to__user_id` (`comment_to_user_id`),
  KEY `comment_task_id` (`task_id`),
  CONSTRAINT `comment_task_id` FOREIGN KEY (`task_id`) REFERENCES `db_task` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_to__user_id` FOREIGN KEY (`comment_to_user_id`) REFERENCES `db_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `db_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `db_follow`
-- ----------------------------
DROP TABLE IF EXISTS `db_follow`;
CREATE TABLE `db_follow` (
  `follow_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned zerofill NOT NULL,
  `follow_user_id` int(11) unsigned zerofill NOT NULL,
  `follow_state` int(1) DEFAULT '0' COMMENT '0关注，1曾经关注',
  `follow_createtime` timestamp NULL DEFAULT NULL,
  `follow_changetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`follow_id`),
  KEY `follow_user_id` (`user_id`),
  KEY `follow_to_user_id` (`follow_user_id`),
  CONSTRAINT `follow_to_user_id` FOREIGN KEY (`follow_user_id`) REFERENCES `db_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `follow_user_id` FOREIGN KEY (`user_id`) REFERENCES `db_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_follow
-- ----------------------------

-- ----------------------------
-- Table structure for `db_honesty`
-- ----------------------------
DROP TABLE IF EXISTS `db_honesty`;
CREATE TABLE `db_honesty` (
  `honesty_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned zerofill NOT NULL,
  `honesty_score` int(11) DEFAULT '500' COMMENT '0~1000分',
  `honesty_createtime` timestamp NULL DEFAULT NULL,
  `honesty_changetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`honesty_id`),
  KEY `honesty_user_id` (`user_id`),
  CONSTRAINT `honesty_user_id` FOREIGN KEY (`user_id`) REFERENCES `db_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_honesty
-- ----------------------------

-- ----------------------------
-- Table structure for `db_information`
-- ----------------------------
DROP TABLE IF EXISTS `db_information`;
CREATE TABLE `db_information` (
  `info_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned zerofill NOT NULL,
  `info_sex` int(11) DEFAULT '0' COMMENT '0未知，1男，2女',
  `info_realname` varchar(255) DEFAULT '未知',
  `info_iconimg` varchar(255) DEFAULT NULL,
  `info_school_id` int(11) unsigned DEFAULT NULL,
  `info_introduce` varchar(255) DEFAULT NULL,
  `info_nickname` varchar(255) DEFAULT NULL,
  `info_birthday` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `info_createtime` timestamp NULL DEFAULT NULL,
  `info_changetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`info_id`),
  KEY `info_user_id` (`user_id`),
  KEY `info_school_id` (`info_school_id`),
  CONSTRAINT `info_school_id` FOREIGN KEY (`info_school_id`) REFERENCES `db_school` (`school_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `info_user_id` FOREIGN KEY (`user_id`) REFERENCES `db_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_information
-- ----------------------------

-- ----------------------------
-- Table structure for `db_message`
-- ----------------------------
DROP TABLE IF EXISTS `db_message`;
CREATE TABLE `db_message` (
  `message_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `message_send_user_id` int(11) unsigned zerofill NOT NULL,
  `message_receive_user_id` int(11) unsigned zerofill NOT NULL,
  `message_content` varchar(255) NOT NULL,
  `message_img` varchar(255) DEFAULT NULL COMMENT 'imageid，用‘,’隔开',
  `message_createtime` timestamp NULL DEFAULT NULL,
  `message_type` int(1) DEFAULT '0' COMMENT '0普通消息，1系统消息，2订单消息',
  `message_receive_state` int(11) DEFAULT '0' COMMENT '未发送到接受方0，已发送1',
  PRIMARY KEY (`message_id`),
  KEY `message_send_user_id` (`message_send_user_id`),
  KEY `message_receive_user_id` (`message_receive_user_id`),
  CONSTRAINT `message_receive_user_id` FOREIGN KEY (`message_receive_user_id`) REFERENCES `db_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `message_send_user_id` FOREIGN KEY (`message_send_user_id`) REFERENCES `db_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_message
-- ----------------------------

-- ----------------------------
-- Table structure for `db_order`
-- ----------------------------
DROP TABLE IF EXISTS `db_order`;
CREATE TABLE `db_order` (
  `order_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `order_launch_user_id` int(11) unsigned zerofill NOT NULL COMMENT '订单支付方',
  `order_receive_user_id` int(11) unsigned zerofill NOT NULL COMMENT '订单接受方',
  `order_launch_schedule` int(1) DEFAULT '0' COMMENT '支付方确认完成1，未确认0，中止2',
  `order_receive_schedule` int(1) DEFAULT '0' COMMENT '接受方确认完成1，未确认0，中止2',
  `order_task_id` int(11) unsigned zerofill NOT NULL,
  `order_describe` varchar(255) DEFAULT NULL,
  `order_launch_evaluate_state` int(1) DEFAULT '0' COMMENT '待评价0，已评价1，已追评2',
  `order_receive_evaluate_state` int(1) DEFAULT NULL COMMENT '待评价0，已评价1，已追评2',
  `order_price` double DEFAULT '0',
  `order_state` int(1) DEFAULT '0' COMMENT '未完成0，已完成1，中止2',
  `order_launch_state` int(1) DEFAULT '0' COMMENT '支付方删除1，未删除0',
  `order_receive_state` int(1) DEFAULT '0' COMMENT '接受方删除1，未删除0',
  `order_createtime` timestamp NULL DEFAULT NULL,
  `order_complete_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `order_task_id` (`order_task_id`),
  KEY `order_launch_user_id` (`order_launch_user_id`),
  KEY `order_receive_user_id` (`order_receive_user_id`),
  CONSTRAINT `order_launch_user_id` FOREIGN KEY (`order_launch_user_id`) REFERENCES `db_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_receive_user_id` FOREIGN KEY (`order_receive_user_id`) REFERENCES `db_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_task_id` FOREIGN KEY (`order_task_id`) REFERENCES `db_task` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_order
-- ----------------------------

-- ----------------------------
-- Table structure for `db_power`
-- ----------------------------
DROP TABLE IF EXISTS `db_power`;
CREATE TABLE `db_power` (
  `power_id` int(11) NOT NULL,
  `power_key` varchar(255) NOT NULL,
  `user_id` int(10) unsigned zerofill NOT NULL,
  `power_createtime` timestamp NULL DEFAULT NULL,
  `power_endtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`power_id`),
  KEY `power_user_id` (`user_id`),
  CONSTRAINT `power_user_id` FOREIGN KEY (`user_id`) REFERENCES `db_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_power
-- ----------------------------

-- ----------------------------
-- Table structure for `db_school`
-- ----------------------------
DROP TABLE IF EXISTS `db_school`;
CREATE TABLE `db_school` (
  `school_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `school_name` varchar(255) NOT NULL,
  `school_is_hot` int(11) DEFAULT '0',
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_school
-- ----------------------------

-- ----------------------------
-- Table structure for `db_task`
-- ----------------------------
DROP TABLE IF EXISTS `db_task`;
CREATE TABLE `db_task` (
  `task_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `task_title` varchar(255) DEFAULT NULL,
  `user_id` int(11) unsigned zerofill NOT NULL,
  `task_user_name` varchar(255) DEFAULT NULL,
  `task_schedule` int(11) DEFAULT '0' COMMENT '0未开始，1正在进行，2已完成',
  `task_state` int(1) DEFAULT '0' COMMENT '0未被删除，1被删除',
  `task_is_delete` int(1) DEFAULT '0' COMMENT '是否被用户删除0没有，1是',
  `task_type` int(11) NOT NULL,
  `task_content` varchar(255) NOT NULL,
  `task_price` double DEFAULT '0' COMMENT '价格默认为0',
  `task_counts` int(11) DEFAULT '1' COMMENT '任务最大接受人数，默认为1',
  `task_restcounts` int(11) DEFAULT NULL COMMENT '剩余可接受任务人数',
  `task_img` varchar(255) DEFAULT NULL COMMENT '图片id，用'',''隔开，最大图片6张',
  `task_createtime` timestamp NULL DEFAULT NULL,
  `task_endtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `task_user_id` (`user_id`),
  CONSTRAINT `task_user_id` FOREIGN KEY (`user_id`) REFERENCES `db_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_task
-- ----------------------------

-- ----------------------------
-- Table structure for `db_user`
-- ----------------------------
DROP TABLE IF EXISTS `db_user`;
CREATE TABLE `db_user` (
  `user_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_phone` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_level` int(1) NOT NULL DEFAULT '1' COMMENT '1普通用户，2大咖用户，3管理员用户',
  `user_createtime` timestamp NULL DEFAULT NULL,
  `user_lastlogintime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_user
-- ----------------------------
INSERT INTO `db_user` VALUES ('00000000001', '10000', '10000', '3', '2018-02-02 06:46:42', null);

-- ----------------------------
-- Table structure for `db_wallet`
-- ----------------------------
DROP TABLE IF EXISTS `db_wallet`;
CREATE TABLE `db_wallet` (
  `wallet_id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned zerofill NOT NULL,
  `wallet_rest` double unsigned NOT NULL,
  `wallet_createtime` timestamp NULL DEFAULT NULL,
  `wallet_changetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`wallet_id`),
  KEY `wallet_user_id` (`user_id`),
  CONSTRAINT `wallet_user_id` FOREIGN KEY (`user_id`) REFERENCES `db_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_wallet
-- ----------------------------
