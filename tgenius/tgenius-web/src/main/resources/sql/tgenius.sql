/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 100000
Source Host           : localhost:3306
Source Database       : tgenius

Target Server Type    : MYSQL
Target Server Version : 100000
File Encoding         : 65001

Date: 2012-12-10 22:16:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `weibo_app_keys`
-- ----------------------------
DROP TABLE IF EXISTS `weibo_app_keys`;
CREATE TABLE `weibo_app_keys` (
  `api_key` varchar(32) NOT NULL,
  `api_secret` varchar(32) NOT NULL,
  `callback_url` text,
  `access_token` varchar(32) DEFAULT NULL,
  `authorization_url` text NOT NULL,
  `refresh_access_token_url` text NOT NULL,
  `expires_in` bigint(20) NOT NULL,
  `other_params` text,
  PRIMARY KEY (`api_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weibo_app_keys
-- ----------------------------
INSERT INTO `weibo_app_keys` VALUES ('801280579', '3bc3dc8d9aa6a0260dafe8136b3f770f', 'http://loudyn.diandian.com', '9fa9c4c85089b09eb822726576023174', 'https://open.t.qq.com/cgi-bin/oauth2/authorize?client_id=801280579&response_type=token&redirect_uri=http://loudyn.diandian.com', 'https://open.t.qq.com/cgi-bin/oauth2/access_token?client_id=801280579&grant_type=refresh_token&refresh_token=8f49c2000fe486dd3dc5dc42617196b4', '604800', '{\r\n\"oauth_version\":\"2.a\",\r\n\"openid\":\"FEF92FDDE9607E6DE9B6C54830160484\",\r\n\"scope\":\"all\"\r\n}');

-- ----------------------------
-- Table structure for `weibo_career`
-- ----------------------------
DROP TABLE IF EXISTS `weibo_career`;
CREATE TABLE `weibo_career` (
  `id` int(11) NOT NULL,
  `father_id` int(11) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `father_id` (`father_id`),
  CONSTRAINT `father_id` FOREIGN KEY (`father_id`) REFERENCES `weibo_career` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of weibo_career
-- ----------------------------
INSERT INTO `weibo_career` VALUES ('1', null, '在校学生');
INSERT INTO `weibo_career` VALUES ('2', null, '已工作');
INSERT INTO `weibo_career` VALUES ('3', null, '自由职业者');
INSERT INTO `weibo_career` VALUES ('4', null, '待业/无业/失业');
INSERT INTO `weibo_career` VALUES ('5', null, '退休');
INSERT INTO `weibo_career` VALUES ('6', null, '家庭主妇和其他在家做家务的人');
INSERT INTO `weibo_career` VALUES ('7', null, '其他');
INSERT INTO `weibo_career` VALUES ('1001', '1', '幼儿园小朋友');
INSERT INTO `weibo_career` VALUES ('1002', '1', '小学生');
INSERT INTO `weibo_career` VALUES ('1003', '1', '初中生');
INSERT INTO `weibo_career` VALUES ('1004', '1', '高中生/中专学生/技校学生');
INSERT INTO `weibo_career` VALUES ('1005', '1', '大学生(大专、本科)');
INSERT INTO `weibo_career` VALUES ('1006', '1', '硕士生、博士生及以上');
INSERT INTO `weibo_career` VALUES ('1007', '1', '在校学生');
INSERT INTO `weibo_career` VALUES ('1008', '1', '其他');
INSERT INTO `weibo_career` VALUES ('2001', '2', '已工作(暂时无法分类)');
INSERT INTO `weibo_career` VALUES ('2002', '2', '计算机/网络/技术类');
INSERT INTO `weibo_career` VALUES ('2003', '2', '经营管理类');
INSERT INTO `weibo_career` VALUES ('2004', '2', '文体工作者');
INSERT INTO `weibo_career` VALUES ('2005', '2', '销售类');
INSERT INTO `weibo_career` VALUES ('2006', '2', '医疗卫生类');
INSERT INTO `weibo_career` VALUES ('2007', '2', '农林牧渔劳动者');
INSERT INTO `weibo_career` VALUES ('2008', '2', '酒店/餐饮/旅游/其他服务类');
INSERT INTO `weibo_career` VALUES ('2009', '2', '美术/设计/创意类');
INSERT INTO `weibo_career` VALUES ('2010', '2', '电子/电器/通信技术类');
INSERT INTO `weibo_career` VALUES ('2011', '2', '农村外出务工人员');
INSERT INTO `weibo_career` VALUES ('2012', '2', '贸易/物流/采购/运输类');
INSERT INTO `weibo_career` VALUES ('2013', '2', '建筑/房地产/装饰装修/物业管理类');
INSERT INTO `weibo_career` VALUES ('2014', '2', '财务/审计/统计类');
INSERT INTO `weibo_career` VALUES ('2015', '2', '电气/能源/动力类');
INSERT INTO `weibo_career` VALUES ('2016', '2', '个体经营/商业零售类');
INSERT INTO `weibo_career` VALUES ('2017', '2', '军人警察类');
INSERT INTO `weibo_career` VALUES ('2018', '2', '美容保健类');
INSERT INTO `weibo_career` VALUES ('2019', '2', '行政/培训类');
INSERT INTO `weibo_career` VALUES ('2020', '2', '教育/培训类');
INSERT INTO `weibo_career` VALUES ('2021', '2', '党政机关事业党委工作者（公务员类）');
INSERT INTO `weibo_career` VALUES ('2022', '2', '市场/公关/咨询/媒介类');
INSERT INTO `weibo_career` VALUES ('2023', '2', '技工类');
INSERT INTO `weibo_career` VALUES ('2024', '2', '工厂生产类');
INSERT INTO `weibo_career` VALUES ('2025', '2', '宗教/神职人员类');
INSERT INTO `weibo_career` VALUES ('2026', '2', '工程师类');
INSERT INTO `weibo_career` VALUES ('2027', '2', '新闻出版/文化工作类');
INSERT INTO `weibo_career` VALUES ('2028', '2', '金融类');
INSERT INTO `weibo_career` VALUES ('2029', '2', '人力资源类');
INSERT INTO `weibo_career` VALUES ('2030', '2', '保险类');
INSERT INTO `weibo_career` VALUES ('2031', '2', '法律类');
INSERT INTO `weibo_career` VALUES ('2032', '2', '翻译类');
INSERT INTO `weibo_career` VALUES ('2033', '2', '其他类');
INSERT INTO `weibo_career` VALUES ('3001', '3', '自由职业者');
INSERT INTO `weibo_career` VALUES ('4001', '4', '待业/无业/失业');
INSERT INTO `weibo_career` VALUES ('5001', '5', '退休');
INSERT INTO `weibo_career` VALUES ('6001', '6', '家庭主妇和其他在家做家务的人');
INSERT INTO `weibo_career` VALUES ('7001', '7', '其他');

-- ----------------------------
-- Table structure for `weibo_task`
-- ----------------------------
DROP TABLE IF EXISTS `weibo_task`;
CREATE TABLE `weibo_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`),
  KEY `name_create_time` (`name`,`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weibo_task
-- ----------------------------

-- ----------------------------
-- Table structure for `weibo_task_handle_event`
-- ----------------------------
DROP TABLE IF EXISTS `weibo_task_handle_event`;
CREATE TABLE `weibo_task_handle_event` (
  `task_id` int(11) NOT NULL,
  `message` varchar(255) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  KEY `task_id_create_time` (`task_id`,`create_time`),
  CONSTRAINT `weibo_task_handle_event_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `weibo_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weibo_task_handle_event
-- ----------------------------

-- ----------------------------
-- Table structure for `weibo_user`
-- ----------------------------
DROP TABLE IF EXISTS `weibo_user`;
CREATE TABLE `weibo_user` (
  `name` varchar(32) NOT NULL,
  `nickname` varchar(32) NOT NULL,
  `location` varchar(32) DEFAULT NULL,
  `homepage` varchar(255) DEFAULT NULL,
  `careercode` int(11) DEFAULT NULL,
  `careername` varchar(32) DEFAULT NULL,
  `sex` tinyint(4) NOT NULL,
  `birth_year` int(11) DEFAULT NULL,
  `fans_count` int(11) NOT NULL,
  `is_vip` bit(1) NOT NULL,
  `verify_info` text,
  `is_enterprise` bit(1) NOT NULL,
  `is_realname` bit(1) NOT NULL,
  `json` text NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `name` (`name`),
  KEY `nickname` (`nickname`),
  KEY `location` (`location`),
  KEY `sex_vip_fans` (`sex`,`is_vip`,`fans_count`),
  KEY `sex_vip_name_fans` (`sex`,`is_vip`,`name`,`fans_count`),
  KEY `sex_vip_loc_fans` (`sex`,`is_vip`,`location`,`fans_count`),
  KEY `sex_vip_loc_name_fans` (`sex`,`is_vip`,`location`,`name`,`fans_count`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weibo_user
-- ----------------------------
