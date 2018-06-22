/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : summer-mall

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2018-06-22 11:37:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for back_category
-- ----------------------------
DROP TABLE IF EXISTS `back_category`;
CREATE TABLE `back_category` (
  `id` bigint(20) NOT NULL auto_increment COMMENT 'id',
  `parent_id` bigint(20) default NULL COMMENT '父类目id',
  `title` varchar(200) default NULL COMMENT '标题',
  `image` varchar(200) default NULL COMMENT '图片',
  `summary` varchar(200) default NULL COMMENT '简介',
  `rank` int(11) default NULL COMMENT '等级',
  `status` int(11) default '0' COMMENT '状态，0：下架中，1：上架中',
  `is_delete` int(11) default '0' COMMENT '是否删除，0：未删除，1：已删除',
  `create_time` bigint(20) default NULL COMMENT '创建时间',
  `update_time` bigint(20) default NULL COMMENT '更新时间',
  `version` bigint(20) default '1' COMMENT '版本号',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='后端类目';

-- ----------------------------
-- Records of back_category
-- ----------------------------
INSERT INTO `back_category` VALUES ('1', null, '美食', '5a41849ac1cd45e4a11157bcad924ac3.jpg', null, null, '1', '0', '1528887929852', '1529560886460', '1');
INSERT INTO `back_category` VALUES ('2', null, '男装', '7fa5c72c9ebc4e56b8addfd3e888846d.jpg', null, null, '1', '0', '1529489837568', '1529577814791', '1');

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel` (
  `id` bigint(20) NOT NULL auto_increment COMMENT 'id',
  `type` int(11) default NULL COMMENT '类型',
  `title` varchar(200) default NULL COMMENT '标题',
  `image` varchar(200) default NULL COMMENT '图片',
  `link` varchar(200) default NULL COMMENT '链接',
  `data` varchar(200) default NULL COMMENT '数据',
  `status` varchar(200) default '0' COMMENT '状态，0：下架中，1：上架中',
  `is_delete` int(11) default '0' COMMENT '是否删除，0：未删除，1：已删除',
  `create_time` bigint(20) default NULL COMMENT '创建时间',
  `update_time` bigint(20) default NULL COMMENT '更新时间',
  `version` bigint(20) default '1' COMMENT '版本号',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='轮播图';

-- ----------------------------
-- Records of carousel
-- ----------------------------
INSERT INTO `carousel` VALUES ('1', null, '美食', 'ba779a5b84284569bdd66ee18138bd5b.jpg', '美食', '', '1', '0', '1528887704154', '1529580159241', '1');
INSERT INTO `carousel` VALUES ('2', null, '箱包', '565d0e2fd63e4dea9c8d4744039b72f0.jpg', '箱包', '箱包', '1', '0', '1529487590780', '1529580162140', '1');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL auto_increment COMMENT 'id',
  `merchant_id` bigint(20) default NULL COMMENT '商家id',
  `shop_id` bigint(20) default NULL COMMENT '店铺id',
  `category_id` bigint(20) default NULL COMMENT '类别id',
  `title` varchar(200) default NULL COMMENT '标题',
  `sub_title` varchar(200) default NULL COMMENT '副标题',
  `summary` varchar(200) default NULL COMMENT '简介',
  `main_image` varchar(200) default NULL COMMENT '主图',
  `sub_images` varchar(800) default NULL COMMENT '子图，多张',
  `number` varchar(200) default NULL COMMENT '编号',
  `original_price` bigint(20) default NULL COMMENT '原价',
  `current_price` bigint(20) default NULL COMMENT '现价',
  `view_quantity` bigint(20) default NULL COMMENT '查看量',
  `stock_quantity` bigint(20) default NULL COMMENT '库存',
  `sale_quantity` bigint(20) default NULL COMMENT '销量',
  `status` int(11) default '0' COMMENT '状态，0：下架中，1：上架中',
  `is_delete` int(11) default '0' COMMENT '是否删除，0：未删除，1：已删除',
  `create_time` bigint(20) default NULL COMMENT '创建时间',
  `update_time` bigint(20) default NULL COMMENT '更新时间',
  `version` bigint(20) default '1' COMMENT '版本号',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='产品';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', null, null, '1', '小面包', '小面包', '小面包', '378da7e0ad2e44c5b5c3d8fa777760c0.jpg', '378da7e0ad2e44c5b5c3d8fa777760c0.jpg', '111', '10000', '8000', null, '100', null, '1', '0', '1528888025441', '1529573585543', '10');
INSERT INTO `product` VALUES ('2', null, null, '1', '小面包', '小面包', '小面包', '378da7e0ad2e44c5b5c3d8fa777760c0.jpg', '378da7e0ad2e44c5b5c3d8fa777760c0.jpg', '111', '10000', '8000', null, '100', null, '1', '0', '1528888025441', '1529560535175', '1');

-- ----------------------------
-- Table structure for product_detail
-- ----------------------------
DROP TABLE IF EXISTS `product_detail`;
CREATE TABLE `product_detail` (
  `id` bigint(20) NOT NULL auto_increment COMMENT 'id',
  `product_id` bigint(20) default NULL COMMENT '产品id',
  `detail` text COMMENT '详情',
  `is_delete` int(11) default '0' COMMENT '是否删除，0：未删除，1：已删除',
  `create_time` bigint(20) default NULL COMMENT '创建时间',
  `update_time` bigint(20) default NULL COMMENT '更新时间',
  `version` bigint(20) default '1' COMMENT '版本号',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='产品详情';

-- ----------------------------
-- Records of product_detail
-- ----------------------------
INSERT INTO `product_detail` VALUES ('1', '1', '<img src=\"http://img1.nextyu.com/f695d6e0dc3d481487efa967438c592c.jpg\" alt=\"undefined\">', '0', '1528888025517', '1529573585546', '3');
INSERT INTO `product_detail` VALUES ('2', '2', '<img src=\"http://img1.nextyu.com/f695d6e0dc3d481487efa967438c592c.jpg\" alt=\"undefined\">', '0', '1528888025517', null, '1');
