/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50629
Source Host           : 127.0.0.1:3306
Source Database       : lake

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-09-02 13:35:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbb_area`
-- ----------------------------
DROP TABLE IF EXISTS `tbb_area`;
CREATE TABLE `tbb_area` (
  `id` char(16) NOT NULL,
  `area_name` varchar(50) NOT NULL,
  `en_name` varchar(100) NOT NULL,
  `word_index` char(1) NOT NULL,
  `parent_id` char(10) NOT NULL,
  `sort_no` int(11) NOT NULL,
  `area_level` int(11) NOT NULL,
  `is_city` char(1) NOT NULL COMMENT '0:否 1:是',
  `region` varchar(2) DEFAULT NULL COMMENT '华东，华南等 字典配置',
  `level_area` varchar(100) NOT NULL,
  `state` char(1) DEFAULT NULL COMMENT '0： 1：',
  `post_code` varchar(20) DEFAULT NULL,
  `xz_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tbb_area
-- ----------------------------
INSERT INTO `tbb_area` VALUES ('1110101001', '东华门街道', 'donghuamenjiedao', 'D', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101001');
INSERT INTO `tbb_area` VALUES ('1110101002', '景山街道', 'jingshanjiedao', 'J', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101002');
INSERT INTO `tbb_area` VALUES ('1110101003', '交道口街道', 'jiaodaokoujiedao', 'J', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101003');
INSERT INTO `tbb_area` VALUES ('1110101004', '安定门街道', 'andingmenjiedao', 'A', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101004');
INSERT INTO `tbb_area` VALUES ('1110101005', '北新桥街道', 'beixinqiaojiedao', 'B', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101005');
INSERT INTO `tbb_area` VALUES ('1110101006', '东四街道', 'dongsijiedao', 'D', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101006');
INSERT INTO `tbb_area` VALUES ('1110101007', '朝阳门街道', 'chaoyangmenjiedao', 'C', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101007');
INSERT INTO `tbb_area` VALUES ('1110101008', '建国门街道', 'jianguomenjiedao', 'J', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101008');
INSERT INTO `tbb_area` VALUES ('1110101009', '东直门街道', 'dongzhimenjiedao', 'D', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101009');
INSERT INTO `tbb_area` VALUES ('1110101010', '和平里街道', 'hepinglijiedao', 'H', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101010');
INSERT INTO `tbb_area` VALUES ('1110101011', '前门街道', 'qianmenjiedao', 'Q', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101011');
INSERT INTO `tbb_area` VALUES ('1110101012', '崇文门外街道', 'chongwenmenwaijiedao', 'C', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101012');
INSERT INTO `tbb_area` VALUES ('1110101013', '东花市街道', 'donghuashijiedao', 'D', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101013');
INSERT INTO `tbb_area` VALUES ('1110101014', '龙潭街道', 'longtanjiedao', 'L', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101014');
INSERT INTO `tbb_area` VALUES ('1110101015', '体育馆路街道', 'tiyuguanlujiedao', 'T', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101015');
INSERT INTO `tbb_area` VALUES ('1110101016', '天坛街道', 'tiantanjiedao', 'T', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101016');
INSERT INTO `tbb_area` VALUES ('1110101017', '永定门外街道', 'yongdingmenwaijiedao', 'Y', '1111110101', '0', '3', '0', '0', '0', '1', null, '110101017');
INSERT INTO `tbb_area` VALUES ('1110102001', '西长安街街道', 'xizhanganjiejiedao', 'X', '1111110102', '0', '3', '0', '0', '0', '1', null, '110102001');
INSERT INTO `tbb_area` VALUES ('1110102003', '新街口街道', 'xinjiekoujiedao', 'X', '1111110102', '0', '3', '0', '0', '0', '1', null, '110102003');
INSERT INTO `tbb_area` VALUES ('1110102007', '月坛街道', 'yuetanjiedao', 'Y', '1111110102', '0', '3', '0', '0', '0', '1', null, '110102007');
INSERT INTO `tbb_area` VALUES ('1110102009', '展览路街道', 'zhanlanlujiedao', 'Z', '1111110102', '0', '3', '0', '0', '0', '1', null, '110102009');
INSERT INTO `tbb_area` VALUES ('1110102010', '德胜街道', 'deshengjiedao', 'D', '1111110102', '0', '3', '0', '0', '0', '1', null, '110102010');
INSERT INTO `tbb_area` VALUES ('1110102011', '金融街街道', 'jinrongjiejiedao', 'J', '1111110102', '0', '3', '0', '0', '0', '1', null, '110102011');
