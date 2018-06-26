/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : summer-mall

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2018-06-26 19:42:53
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='后端类目';

-- ----------------------------
-- Records of back_category
-- ----------------------------
INSERT INTO `back_category` VALUES ('3', null, '阿芙佳朵', '', null, null, '1', '0', '1529984372710', '1529984461842', '1');
INSERT INTO `back_category` VALUES ('4', null, '告尔多', '', null, null, '1', '0', '1529984399525', '1529984443983', '1');
INSERT INTO `back_category` VALUES ('5', null, '拿铁', '', null, null, '1', '0', '1529984411451', '1529984430626', '1');
INSERT INTO `back_category` VALUES ('6', null, '玛奇朵', '', null, null, '1', '0', '1529984421975', null, '1');
INSERT INTO `back_category` VALUES ('7', null, '爱尔兰', '', null, null, '1', '0', '1529984478471', null, '1');
INSERT INTO `back_category` VALUES ('8', null, '卡布奇洛', '', null, null, '1', '0', '1529984491990', null, '1');
INSERT INTO `back_category` VALUES ('9', null, '维也纳', '', null, null, '1', '0', '1529984535659', null, '1');
INSERT INTO `back_category` VALUES ('10', null, '摩卡', '', null, null, '1', '0', '1529984547513', '1529984727390', '1');

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
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_id` bigint(20) default NULL COMMENT '用户id',
  `product_id` bigint(20) default NULL COMMENT '产品id',
  `product_name` varchar(100) default NULL COMMENT '产品名称',
  `product_quantity` int(11) default NULL COMMENT '产品数量',
  `product_price` bigint(20) default NULL COMMENT '产品单价',
  `total_price` bigint(20) default NULL COMMENT '总价',
  `order_status` int(11) default '1' COMMENT '订单状态，1：待付款，2：待发货，3：待收货，4：已完成，5.已取消',
  `create_time` bigint(20) default NULL COMMENT '创建时间',
  `update_time` bigint(20) default NULL COMMENT '更新时间',
  `version` bigint(20) default '1' COMMENT '版本号',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单';

-- ----------------------------
-- Records of order
-- ----------------------------

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
  `address` varchar(100) default NULL COMMENT '地址',
  `hours` varchar(100) default NULL COMMENT '营业时间',
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='产品';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', null, null, '1', '小面包', '小面包', '小面包', null, null, '378da7e0ad2e44c5b5c3d8fa777760c0.jpg', '378da7e0ad2e44c5b5c3d8fa777760c0.jpg', '111', '10000', '8000', null, '100', null, '1', '0', '1528888025441', '1529573585543', '10');
INSERT INTO `product` VALUES ('2', null, null, '1', '小面包', '小面包', '小面包', null, null, '378da7e0ad2e44c5b5c3d8fa777760c0.jpg', '378da7e0ad2e44c5b5c3d8fa777760c0.jpg', '111', '10000', '8000', null, '100', null, '1', '0', '1528888025441', '1529654582046', '5');
INSERT INTO `product` VALUES ('3', null, null, '3', '星巴克阿芙佳朵', '星巴克阿芙佳朵', '星巴克阿芙佳朵', '北京蓝色港湾第二咖啡店', '08:30 am - 21:30 pm', null, '', '', '10000', '10000', null, '100', null, '1', '0', '1530009770999', '1530010679233', '5');
INSERT INTO `product` VALUES ('4', null, null, '3', '星巴克臻选™ 卢旺达淑利咖啡豆', '星巴克臻选™ 卢旺达淑利咖啡豆', '星巴克臻选™ 卢旺达淑利咖啡豆', '北京蓝色港湾圣拉娜店', '08:30 am - 21:30 pm', null, '', '', '2000', '2000', null, '100', null, '1', '0', '1530010122618', null, '1');
INSERT INTO `product` VALUES ('5', null, null, '3', '星巴克臻选™ 巴布亚新几内亚洛卡咖啡豆', '星巴克臻选™ 巴布亚新几内亚洛卡咖啡豆', '星巴克臻选™ 巴布亚新几内亚洛卡咖啡豆', '北京东方东路二店', '08:30 am - 21:30 pm', null, '', '', '10000', '10000', null, '100', null, '1', '0', '1530010212288', null, '1');
INSERT INTO `product` VALUES ('6', null, null, '4', '星巴克臻选™ 哥斯达黎加 阿尔萨西亚', '星巴克臻选™ 哥斯达黎加 阿尔萨西亚', '星巴克臻选™ 哥斯达黎加 阿尔萨西亚', '北京新源南路咖啡店', '08:30 am - 21:30 pm', null, '', '', '10000', '10000', null, '100', null, '1', '0', '1530010271861', null, '1');
INSERT INTO `product` VALUES ('7', null, null, '5', 'casta拿铁', 'casta拿铁', 'casta拿铁', '北京国航大厦店', '09:30 am - 21:30 pm', null, '', '', '5000', '5000', null, '100', null, '1', '0', '1530010537165', null, '1');
INSERT INTO `product` VALUES ('8', null, null, '6', 'casta玛奇朵', 'casta玛奇朵', 'casta玛奇朵', '北京霄云路咖啡店', '09:30 am - 21:30 pm', null, '', '', '3000', '3000', null, '100', null, '1', '0', '1530010615601', null, '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='产品详情';

-- ----------------------------
-- Records of product_detail
-- ----------------------------
INSERT INTO `product_detail` VALUES ('1', '1', '<img src=\"http://img1.nextyu.com/f695d6e0dc3d481487efa967438c592c.jpg\" alt=\"undefined\">', '0', '1528888025517', '1529573585546', '3');
INSERT INTO `product_detail` VALUES ('2', '2', '<img src=\"http://img1.nextyu.com/f695d6e0dc3d481487efa967438c592c.jpg\" alt=\"undefined\">', '0', '1528888025517', null, '1');
INSERT INTO `product_detail` VALUES ('3', '3', '', '0', '1530009771058', '1530010679247', '5');
INSERT INTO `product_detail` VALUES ('4', '4', '', '0', '1530010122624', null, '1');
INSERT INTO `product_detail` VALUES ('5', '5', '', '0', '1530010212295', null, '1');
INSERT INTO `product_detail` VALUES ('6', '6', '', '0', '1530010271870', null, '1');
INSERT INTO `product_detail` VALUES ('7', '7', '', '0', '1530010537182', null, '1');
INSERT INTO `product_detail` VALUES ('8', '8', '', '0', '1530010615609', null, '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL auto_increment,
  `nickname` varchar(200) default NULL COMMENT '昵称',
  `phone` varchar(100) default NULL COMMENT '手机号',
  `password` varchar(100) default NULL COMMENT '密码',
  `avatar` varchar(100) default NULL COMMENT '头像',
  `create_time` bigint(20) default NULL COMMENT '创建时间',
  `update_time` bigint(20) default NULL COMMENT '更新时间',
  `version` bigint(20) default '1' COMMENT '版本号',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '小明', '15500000000', '123', '378da7e0ad2e44c5b5c3d8fa777760c0.jpg', '1529660605000', null, '1');
