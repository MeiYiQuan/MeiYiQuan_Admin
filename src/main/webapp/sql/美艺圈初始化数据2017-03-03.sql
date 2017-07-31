/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50132
Source Host           : localhost:3306
Source Database       : salon3

Target Server Type    : MYSQL
Target Server Version : 50132
File Encoding         : 65001

Date: 2017-03-03 14:02:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_activity
-- ----------------------------
DROP TABLE IF EXISTS `tb_activity`;
CREATE TABLE `tb_activity` (
  `id` varchar(32) NOT NULL,
  `activity_status` int(2) DEFAULT '0' COMMENT '活动状态(0 结束,1 正在进行,作废，具体状态由tb_activity_status表里的数据推算得出)',
  `activity_time` bigint(13) DEFAULT '0' COMMENT '活动时间',
  `address` varchar(500) DEFAULT '' COMMENT '详细地址',
  `appearance` decimal(9,2) DEFAULT '0.00' COMMENT '讲师嘉宾出场费(单位:元)，作废',
  `canUseCoupon` int(1) DEFAULT '2' COMMENT '是否能使用优惠券(1 是 ，2 否 )',
  `cost` decimal(9,2) DEFAULT '0.00' COMMENT '运营成本(单位:元)',
  `create_time` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `creater_id` varchar(32) DEFAULT '' COMMENT '创建人ID',
  `description` varchar(150) DEFAULT '' COMMENT '简要介绍',
  `district` varchar(20) DEFAULT '' COMMENT '区域(省市等)',
  `most_man` int(10) DEFAULT '0' COMMENT '活动最大人数',
  `organiser` varchar(50) DEFAULT '' COMMENT '主办方',
  `part_num` int(10) DEFAULT '0' COMMENT '活动的参与人数',
  `price` decimal(9,2) DEFAULT '0.00' COMMENT '价格(单位:元)',
  `remark` varchar(1500) DEFAULT '' COMMENT '详细介绍',
  `share_url` varchar(300) DEFAULT '' COMMENT '分享链接地址',
  `show_pic_url` varchar(300) DEFAULT '' COMMENT '宣传图片访问地址',
  `show_type` int(2) DEFAULT '0' COMMENT '宣传类型(0 图片,1 视频)',
  `show_video_picurl` varchar(300) DEFAULT '' COMMENT '如果宣传类型是视频，则应该给一个视频封面地址',
  `show_video_url` varchar(300) DEFAULT '' COMMENT '宣传视频访问地址',
  `status` int(2) DEFAULT '0' COMMENT '状态(012345)，已经作废，具体状态由tb_activity_status表里的数据推算得出',
  `teacher_id` varchar(32) DEFAULT '' COMMENT '关联讲师ID',
  `title` varchar(50) DEFAULT '' COMMENT '标题',
  `update_time` bigint(13) DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_activity
-- ----------------------------

-- ----------------------------
-- Table structure for tb_activity_status
-- ----------------------------
DROP TABLE IF EXISTS `tb_activity_status`;
CREATE TABLE `tb_activity_status` (
  `id` varchar(32) NOT NULL,
  `activity_end_time` bigint(13) DEFAULT '0' COMMENT '活动结束时间',
  `activity_id` varchar(32) DEFAULT '' COMMENT '活动ID',
  `activity_start_time` bigint(13) DEFAULT '0' COMMENT '活动开始时间',
  `cancel_time` bigint(13) DEFAULT '0' COMMENT '取消活动时间',
  `create_time` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `enroll_begin_time` bigint(13) DEFAULT '0' COMMENT '报名开始时间',
  `enroll_end_time` bigint(13) DEFAULT '0' COMMENT '报名结束时间',
  `isCancel` int(1) DEFAULT '2' COMMENT '是否已经取消活动，1--是，2--否',
  `prepare_end_time` bigint(13) DEFAULT '0' COMMENT '准备结束时间',
  `prepare_start_time` bigint(13) DEFAULT '0' COMMENT '准备开始时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_activity_status
-- ----------------------------

-- ----------------------------
-- Table structure for tb_activity_user_relationship
-- ----------------------------
DROP TABLE IF EXISTS `tb_activity_user_relationship`;
CREATE TABLE `tb_activity_user_relationship` (
  `id` varchar(32) NOT NULL,
  `activity_id` varchar(32) DEFAULT '' COMMENT '活动ID',
  `appearance` decimal(9,2) DEFAULT '0.00' COMMENT '讲师嘉宾出场费(单位:元)，当时普通用户的话，表示用户参加这个活动所花的钱(除去优惠券以后的)',
  `enter_time` bigint(13) DEFAULT '0' COMMENT '报名时间',
  `man_type` int(2) DEFAULT '0' COMMENT '参与者类型,普通用户和讲师的状态码直接去拿user里的码即可(0普通 1讲师 2嘉宾)',
  `status` int(1) DEFAULT '0' COMMENT '状态 (2 不可用,1可用),此字段已废除，不再使用',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_activity_user_relationship
-- ----------------------------

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `id` varchar(32) NOT NULL,
  `headUrl` varchar(300) NOT NULL DEFAULT '' COMMENT '头像图片地址',
  `loginname` varchar(20) NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(20) NOT NULL DEFAULT '' COMMENT '密码',
  `roleId` varchar(32) NOT NULL DEFAULT '' COMMENT '角色id',
  `updateTime` bigint(13) DEFAULT '0' COMMENT '最后更新时间，一般为修改密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_avrweig7b4mvk0vm4er2qwvhf` (`loginname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('402858815a7f097e015a7f0b811f0000', '', 'qc', 'qc', '', '1488190144797');
INSERT INTO `tb_admin` VALUES ('4028812a5a6019ca015a601c3b3b0000', '', '2', '2', 'id1', '1487671147302');
INSERT INTO `tb_admin` VALUES ('id1', '', '1', '1', 'id1', '1487748240570');
INSERT INTO `tb_admin` VALUES ('id2', '', '3', '3', 'id1', '1487748247440');

-- ----------------------------
-- Table structure for tb_banner
-- ----------------------------
DROP TABLE IF EXISTS `tb_banner`;
CREATE TABLE `tb_banner` (
  `id` varchar(32) NOT NULL,
  `create_time` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `jump_id` varchar(32) DEFAULT '' COMMENT '轮播图关联类型ID',
  `jump_type` int(2) DEFAULT '0' COMMENT '轮播图关联类型(0 课程,1 讲师,2 活动)',
  `name` varchar(50) DEFAULT '' COMMENT '轮播图名称',
  `order_num` int(8) DEFAULT '0' COMMENT '录播图播放顺序',
  `pic_redirect_url` varchar(300) DEFAULT '' COMMENT '轮播图跳转地址，已作废',
  `pic_save_url` varchar(300) DEFAULT '' COMMENT '轮播图存放访问地址',
  `remark` varchar(500) DEFAULT '' COMMENT '标注',
  `showtype` int(2) DEFAULT '0' COMMENT '轮播图所在页面(1首页 2发现页面)',
  `status` int(1) DEFAULT '1' COMMENT '状态(2为禁用,1为启用)',
  `update_time` bigint(13) DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_banner
-- ----------------------------

-- ----------------------------
-- Table structure for tb_channel
-- ----------------------------
DROP TABLE IF EXISTS `tb_channel`;
CREATE TABLE `tb_channel` (
  `id` varchar(32) NOT NULL,
  `create_time` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `enable` int(1) DEFAULT '1' COMMENT '是否启用(2不启用,1 启用)',
  `logo_url` varchar(300) DEFAULT '' COMMENT 'logo图片的访问地址',
  `name` varchar(20) DEFAULT '' COMMENT '名称',
  `pid` varchar(32) DEFAULT '' COMMENT 'PID',
  `update_admin_id` varchar(32) DEFAULT '' COMMENT '修改人ID',
  `update_time` bigint(13) DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_channel
-- ----------------------------
INSERT INTO `tb_channel` VALUES ('0da9797823224ee4ac5ee1981c17f872', '1488512356393', '1', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/image/1488512357534.png', '美发+互联网', '3c8b145ef03d4523b1eac0cbaa63843c', 'id2', '1488512356393');
INSERT INTO `tb_channel` VALUES ('1d87e8d8aff845f88c49cf43215aeb9f', '1488512400963', '1', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/image/1488512410444.png', '地址', '9391893d4b974e46849c3f5f04cf1ebd', 'id2', '1488512400963');
INSERT INTO `tb_channel` VALUES ('27a867d5a379469bb8930ea19332c780', '1488512283686', '1', '', '开店创业第一年', '67850e9f91524c6d9ac8c8b6ce3533ce', 'id2', '1488512283686');
INSERT INTO `tb_channel` VALUES ('30db3cb21e2a4e9b975834d924d2f7ff', '1488512453472', '1', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/image/1488512461774.png', '团队执行力', '27a867d5a379469bb8930ea19332c780', 'id2', '1488512453472');
INSERT INTO `tb_channel` VALUES ('368b1fb910b741aca4dc0520ba83f901', '1488359338087', '1', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/image/1488359341471.png', '免费视频', '3c8b145ef03d4523b1eac0cbaa63843c', 'id2', '1488512308347');
INSERT INTO `tb_channel` VALUES ('3c8b145ef03d4523b1eac0cbaa63843c', '1488512261463', '1', '', '精彩栏目', '67850e9f91524c6d9ac8c8b6ce3533ce', 'id2', '1488512261463');
INSERT INTO `tb_channel` VALUES ('67850e9f91524c6d9ac8c8b6ce3533ce', '1484548184786', '1', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/test/images/channel/gift.jpg', '开店创业', '0', '', '1484548184786');
INSERT INTO `tb_channel` VALUES ('68b650dd1d284909b4082f816fab4475', '1488512386380', '1', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/image/1488512391628.png', '找团队', '9391893d4b974e46849c3f5f04cf1ebd', 'id2', '1488512386380');
INSERT INTO `tb_channel` VALUES ('9391893d4b974e46849c3f5f04cf1ebd', '1488359272053', '1', '', '开店创业第一步', '67850e9f91524c6d9ac8c8b6ce3533ce', 'id2', '1488359272053');
INSERT INTO `tb_channel` VALUES ('a117735fc0b54a1987a029ae7553f418', '1488512432310', '1', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/image/1488512437640.png', '服务流程', '27a867d5a379469bb8930ea19332c780', 'id2', '1488512432310');
INSERT INTO `tb_channel` VALUES ('e493a55641b349fca5f4d633f9a04b99', '1488512641149', '1', '', '学美发潮流技术', 'fe82dcb588314eaa94282f8b88b9d9b1', 'id2', '1488512641149');
INSERT INTO `tb_channel` VALUES ('fe82dcb588314eaa94282f8b88b9d9b1', '1484548258810', '1', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/test/images/channel/gift.jpg', '潮流技术', '0', '', '1484548258810');

-- ----------------------------
-- Table structure for tb_channel_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_channel_course`;
CREATE TABLE `tb_channel_course` (
  `id` varchar(32) NOT NULL,
  `belong_type` int(1) DEFAULT '0' COMMENT '所属频道的类型(0 开店创业1 学潮流技术)',
  `channel_id` varchar(32) DEFAULT '' COMMENT '频道ID',
  `course_id` varchar(32) DEFAULT '' COMMENT '课程ID',
  `homepage_show` int(1) DEFAULT '1' COMMENT '是否显示到首页开店创业或潮流技术(2 不显示,1 显示)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_channel_course
-- ----------------------------

-- ----------------------------
-- Table structure for tb_collect
-- ----------------------------
DROP TABLE IF EXISTS `tb_collect`;
CREATE TABLE `tb_collect` (
  `id` varchar(32) NOT NULL,
  `collect_time` bigint(13) DEFAULT '0' COMMENT '收藏时间',
  `collect_type` int(2) DEFAULT '0' COMMENT '被关注的类型(1活动,2讲师,3视频)',
  `collect_type_id` varchar(32) DEFAULT '' COMMENT '被关注的ID',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_collect
-- ----------------------------

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `id` varchar(32) NOT NULL,
  `comm_content` varchar(500) DEFAULT '' COMMENT '评论内容',
  `comm_content_id` varchar(32) DEFAULT '' COMMENT '被评论的内容ID',
  `comm_time` bigint(13) DEFAULT '0' COMMENT '评论时间',
  `commed_id` varchar(32) DEFAULT '' COMMENT '父ID',
  `commed_type` int(1) DEFAULT '1' COMMENT '评论级别(1.一级评论  2.二级评论)',
  `status` int(2) DEFAULT '2' COMMENT '是否拉黑 1是 2否',
  `type` int(2) DEFAULT '0' COMMENT '评论类型(见静态量)',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_coupon
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon`;
CREATE TABLE `tb_coupon` (
  `id` varchar(32) NOT NULL,
  `admin_id` varchar(32) DEFAULT '' COMMENT '最后编辑人ID',
  `background_pic_url` varchar(300) DEFAULT '' COMMENT '优惠券背景图',
  `coupon_provide_type` int(1) DEFAULT '0' COMMENT '发放类型(0平台发放,1系统自动发放)',
  `coupon_type` int(1) DEFAULT '0' COMMENT '优惠券属性类型(0抵用券,1打折券)',
  `denomination` decimal(9,2) DEFAULT '0.00' COMMENT '优惠券面额，如果是抵用券，表示抵用金额，如果是打折券，则是一个0-1之间的小数',
  `expire_count` int(9) DEFAULT '0' COMMENT '到期数量，作废',
  `expire_time` int(3) DEFAULT '0' COMMENT '优惠券有效期：数值，要根据上面的类型来取。当是永久时这个字段作废',
  `expire_type` int(2) DEFAULT '1' COMMENT '优惠券有效期类型：1---按月计算，2---按天计算，3---永久',
  `introduction` varchar(50) DEFAULT '' COMMENT '优惠券描述',
  `latest_update_time` bigint(13) DEFAULT '0' COMMENT '最后编辑时间',
  `name` varchar(50) DEFAULT '' COMMENT '优惠券名称',
  `number` bigint(11) DEFAULT '0' COMMENT '优惠券号',
  `status` int(1) DEFAULT '1' COMMENT '状态(2禁用,1开启)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupon
-- ----------------------------
INSERT INTO `tb_coupon` VALUES ('006667f250e242d0a698380f10e04ca8', '', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/test/images/coupon/coupon.jpg', '1', '1', '0.60', '0', '3', '1', '用于系统自动发放', '1487919210770', '美艺圈优惠券', '10000000008', '1');
INSERT INTO `tb_coupon` VALUES ('4594249df07e45c894d97d1521a294b4', '', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/test/images/coupon/coupon.jpg', '1', '0', '50.00', '0', '3', '1', '用于系统自动发放', '1484546735980', '美艺圈优惠券', '10000000006', '1');
INSERT INTO `tb_coupon` VALUES ('4aba791682ce40f8913999549a086896', '', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/test/images/coupon/coupon.jpg', '1', '0', '20.00', '0', '3', '1', '用于系统自动发放', '1484546638323', '美艺圈优惠券', '10000000004', '1');
INSERT INTO `tb_coupon` VALUES ('782c03db23b34f00bf092c0eb080c141', '', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/test/images/coupon/coupon.jpg', '1', '0', '100.00', '0', '3', '1', '用于系统自动发放', '1484546778751', '美艺圈优惠券', '10000000007', '1');
INSERT INTO `tb_coupon` VALUES ('a19672d230fa46f9825a92096647d4a4', '', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/test/images/coupon/coupon.jpg', '1', '0', '1.00', '0', '3', '1', '用于系统自动发放', '1484545829876', '美艺圈优惠券', '10000000001', '1');
INSERT INTO `tb_coupon` VALUES ('a2c4d4d0c54846ac929fe09f656f73a9', '', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/test/images/coupon/coupon.jpg', '1', '0', '5.00', '0', '3', '1', '用于系统自动发放', '1484546520716', '美艺圈优惠券', '10000000002', '1');
INSERT INTO `tb_coupon` VALUES ('ac0772671f0143bc9fd9b498e9184073', '', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/test/images/coupon/coupon.jpg', '1', '0', '30.00', '0', '3', '1', '用于系统自动发放', '1487823615022', '美艺圈优惠券', '10000000005', '1');
INSERT INTO `tb_coupon` VALUES ('c532190d3fcc4800822bd2d740b46d84', '', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/test/images/coupon/coupon.jpg', '1', '0', '10.00', '0', '7', '1', '用于系统自动发放', '1487579486257', '美艺圈优惠券', '10000000003', '1');

-- ----------------------------
-- Table structure for tb_coupon_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon_user`;
CREATE TABLE `tb_coupon_user` (
  `id` varchar(32) NOT NULL,
  `coupon_id` varchar(32) DEFAULT '' COMMENT '优惠券ID',
  `coupon_provide_type` int(1) DEFAULT '0' COMMENT '发放类型(0平台发放,1系统自动发放)',
  `coupon_type` int(1) DEFAULT '0' COMMENT '优惠券类型：0抵用券,1打折券',
  `denomination` decimal(9,2) DEFAULT '0.00' COMMENT '优惠券面额，如果是抵用券，表示抵用金额，如果是打折券，则是一个0-1之间的小数',
  `expire_time` bigint(13) DEFAULT '0' COMMENT '优惠券到期时间',
  `get_time` bigint(13) DEFAULT '0' COMMENT '优惠券领取时间',
  `get_type` int(2) DEFAULT '0' COMMENT '具体获得类型(1 课程点赞...)',
  `get_type_id` varchar(32) DEFAULT '' COMMENT '获得优惠券途径具体ID，作废',
  `isForever` int(1) DEFAULT '2' COMMENT '该优惠券是否为永久，1---是，2---否',
  `number` varchar(32) DEFAULT '' COMMENT '优惠券号',
  `status` int(1) DEFAULT '1' COMMENT '优惠券是否可用，1---可用，2---不可用，当优惠券被使用掉以后状态变为不可用',
  `use_time` bigint(13) DEFAULT '0' COMMENT '优惠券使用时间',
  `use_type` int(2) DEFAULT '0' COMMENT '优惠券可应用于哪种类型(0.购买所有,1.购买课程,2.购买活动)，作废',
  `use_type_id` varchar(32) DEFAULT '' COMMENT '优惠券使用时的订单ID',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户ID',
  `warn_time` bigint(13) DEFAULT '0' COMMENT '优惠券到期提前提醒时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupon_user
-- ----------------------------

-- ----------------------------
-- Table structure for tb_coupon_willsend_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon_willsend_user`;
CREATE TABLE `tb_coupon_willsend_user` (
  `id` varchar(50) NOT NULL,
  `coupon_id` varchar(32) DEFAULT '' COMMENT '优惠券ID',
  `req_admin_id` varchar(32) DEFAULT '' COMMENT '申请人ID，这里是指是由谁发起的申请，admin表的id',
  `req_time` bigint(13) DEFAULT '0' COMMENT '发起申请的时间',
  `resp_admin_id` varchar(32) DEFAULT '' COMMENT '审批人ID，这里是指是由谁审批，admin表的id，在审批之前这里为空，审批时记录管理员id',
  `resp_time` bigint(13) DEFAULT '0' COMMENT '审核的时间',
  `use_type` int(1) DEFAULT '0' COMMENT '状态：1--刚申请，2--审核通过，3--审核不通过',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupon_willsend_user
-- ----------------------------

-- ----------------------------
-- Table structure for tb_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course` (
  `id` varchar(32) NOT NULL,
  `belong_type` int(1) DEFAULT '0' COMMENT '所属频道分类 0不放入频道,1 创业开店,2潮流技术',
  `channel_id` varchar(32) DEFAULT '-1' COMMENT '视频对应的频道ID(不放入频道此处对应-1)',
  `cost` decimal(9,2) DEFAULT '0.00' COMMENT '总成本',
  `course_compaign_type` int(1) DEFAULT '0' COMMENT '课程宣传(课程详情页)封面类型(0图片,1视频)',
  `course_compaign_video_url` varchar(300) DEFAULT '' COMMENT '课程宣传(课程详情页)视频访问地址',
  `create_time` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `creater_id` varchar(32) DEFAULT '' COMMENT '创建人ID',
  `description` varchar(50) DEFAULT '' COMMENT '视频简要介绍',
  `homepage_show` int(1) DEFAULT '1' COMMENT '是否显示到首页开店创业或潮流技术(2不显示,1 显示)',
  `pic_big_url` varchar(300) DEFAULT '' COMMENT '课程宣传大图',
  `pic_small_url` varchar(300) DEFAULT '' COMMENT '课程宣传小图',
  `playing` int(1) DEFAULT '0' COMMENT '课程类型(0：平台制作-即将上映，1：平台制作-已上映，2：由求课程转变而来-已经上映)',
  `playing_time` bigint(13) DEFAULT '0' COMMENT '课程上映时间',
  `remark` varchar(500) DEFAULT '' COMMENT '描述',
  `share_url` varchar(300) DEFAULT '' COMMENT '分享地址',
  `show_big` int(1) DEFAULT '1' COMMENT '是否在首页开店创业或潮流技术显示大图(2不显示,1 显示)，作废',
  `status` int(1) DEFAULT '1' COMMENT '状态(2 禁用,1 启用)',
  `subject_id` varchar(32) DEFAULT '' COMMENT '对应的专题ID',
  `teacher_id` varchar(32) DEFAULT '' COMMENT '讲师ID',
  `title` varchar(50) DEFAULT '' COMMENT '标题',
  `to_home` int(1) DEFAULT '1' COMMENT '是否推荐到首页(2否,1 是)',
  `update_time` bigint(13) DEFAULT '0' COMMENT '修改时间',
  `video_id` varchar(32) DEFAULT '' COMMENT '相关推荐视频ID，已作废',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_course
-- ----------------------------

-- ----------------------------
-- Table structure for tb_course_recommend
-- ----------------------------
DROP TABLE IF EXISTS `tb_course_recommend`;
CREATE TABLE `tb_course_recommend` (
  `id` varchar(32) NOT NULL,
  `course_id` varchar(32) DEFAULT '' COMMENT '本位课程ID',
  `recommend_course_id` varchar(32) DEFAULT '' COMMENT '相关推荐ID',
  `recommend_time` bigint(13) DEFAULT '0' COMMENT '关联时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_course_recommend
-- ----------------------------

-- ----------------------------
-- Table structure for tb_district
-- ----------------------------
DROP TABLE IF EXISTS `tb_district`;
CREATE TABLE `tb_district` (
  `id` varchar(32) NOT NULL,
  `create_time` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `district_grade` int(1) DEFAULT '0' COMMENT '区域等级(1省,2市,3县)',
  `name` varchar(20) DEFAULT '' COMMENT '名称',
  `pid` varchar(32) DEFAULT '' COMMENT 'PID',
  `status` int(1) DEFAULT '1' COMMENT '是否启用(2禁用,1启用)',
  `update_admin_id` varchar(32) DEFAULT '' COMMENT '修改人ID',
  `update_time` bigint(13) DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_district
-- ----------------------------
INSERT INTO `tb_district` VALUES ('1', '19710101000000', '1', '北京市', '0', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('10', '19710101000000', '3', '王府井', '5', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('11', '19710101000000', '3', '地安门', '5', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('12', '19710101000000', '3', '中关村', '4', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('13', '19710101000000', '3', '万柳', '4', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('14', '19710101000000', '3', '魏公村', '4', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('15', '19710101000000', '3', '世纪城', '4', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('16', '19710101000000', '3', '沙河', '3', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('17', '19710101000000', '1', '河北省', '0', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('18', '19710101000000', '2', '石家庄', '17', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('19', '19710101000000', '2', '保定', '17', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('2', '19710101000000', '2', '朝阳区', '1', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('20', '19710101000000', '2', '秦皇岛', '17', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('21', '19710101000000', '2', '唐山', '17', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('22', '19710101000000', '3', '正定', '18', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('23', '19710101000000', '3', '新乐', '18', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('24', '19710101000000', '3', '藁城', '18', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('25', '19710101000000', '3', '定州', '19', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('26', '19710101000000', '3', '清苑', '19', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('27', '19710101000000', '3', '博野', '19', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('28', '19710101000000', '3', '海港', '20', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('29', '19710101000000', '3', '北戴河', '20', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('3', '19710101000000', '2', '昌平区', '1', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('30', '19710101000000', '3', '山海关', '20', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('31', '19710101000000', '3', '卢龙', '20', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('32', '19710101000000', '1', '天津', '0', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('33', '19710101000000', '2', '红桥区', '32', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('34', '19710101000000', '2', '南开区', '32', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('35', '19710101000000', '2', '和平区', '32', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('36', '19710101000000', '3', '本溪路', '33', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('37', '19710101000000', '3', '丁字沽', '33', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('38', '19710101000000', '3', '西沽街', '33', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('39', '19710101000000', '1', '万兴街', '34', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('4', '19710101000000', '2', '海淀区', '1', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('40', '19710101000000', '3', '学府街', '34', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('41', '19710101000000', '3', '兴南街', '34', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('42', '19710101000000', '3', '小白楼', '35', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('43', '19710101000000', '3', '大直沽', '35', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('44', '19710101000000', '3', '尖山', '35', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('45', '19710101000000', '1', '上海市', '0', '0', 'id1', '1487931235581');
INSERT INTO `tb_district` VALUES ('46', '19710101000000', '2', '浦东新区', '45', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('47', '19710101000000', '2', '虹口区', '45', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('48', '19710101000000', '2', '徐汇区', '45', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('49', '19710101000000', '2', '长宁区', '45', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('5', '19710101000000', '2', '东城区', '1', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('6', '19710101000000', '3', '国贸', '2', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('7', '19710101000000', '3', '大望路', '2', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('8', '19710101000000', '3', '三里屯', '2', '1', '', '19710101000000');
INSERT INTO `tb_district` VALUES ('9', '19710101000000', '3', '东四', '5', '1', '', '19710101000000');

-- ----------------------------
-- Table structure for tb_follow
-- ----------------------------
DROP TABLE IF EXISTS `tb_follow`;
CREATE TABLE `tb_follow` (
  `id` varchar(32) NOT NULL,
  `follow_time` bigint(13) DEFAULT '0' COMMENT '关注时间',
  `follow_type` int(2) DEFAULT '0' COMMENT '关注的类型(0课程,1课程,2活动,3讲师,4...)',
  `follow_type_id` varchar(32) DEFAULT '' COMMENT '被关注的ID',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_follow
-- ----------------------------

-- ----------------------------
-- Table structure for tb_guidpic
-- ----------------------------
DROP TABLE IF EXISTS `tb_guidpic`;
CREATE TABLE `tb_guidpic` (
  `id` varchar(32) NOT NULL,
  `createTime` bigint(13) DEFAULT '0' COMMENT '创建图片时间',
  `inde` int(2) DEFAULT '0' COMMENT '图片展示顺序(搜索规则：inde in (1,2,3) order by index asc )',
  `url` varchar(300) DEFAULT '' COMMENT '图片url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_guidpic
-- ----------------------------

-- ----------------------------
-- Table structure for tb_homepage
-- ----------------------------
DROP TABLE IF EXISTS `tb_homepage`;
CREATE TABLE `tb_homepage` (
  `id` varchar(32) NOT NULL,
  `creatTime` bigint(13) DEFAULT '0' COMMENT '添加时间',
  `name` varchar(50) DEFAULT '' COMMENT '首页名称',
  `pic_url` varchar(300) DEFAULT '' COMMENT '首页图片',
  `relation_id` varchar(32) DEFAULT '' COMMENT '关联id',
  `status` int(1) DEFAULT '1' COMMENT '首页启用（1是/2否）',
  `top_num` int(8) DEFAULT '0' COMMENT '置顶数值',
  `type` int(2) DEFAULT '0' COMMENT '推荐类型',
  `updateTime` bigint(13) DEFAULT '0' COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_homepage
-- ----------------------------

-- ----------------------------
-- Table structure for tb_ios_coin
-- ----------------------------
DROP TABLE IF EXISTS `tb_ios_coin`;
CREATE TABLE `tb_ios_coin` (
  `id` varchar(32) NOT NULL,
  `create_time` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `iosAmount` decimal(9,2) DEFAULT '0.00' COMMENT '用户购买之后获取到的ios币数量',
  `isDoing` int(2) DEFAULT '1' COMMENT '是否可以使用(1--是，2--否)',
  `multiple` decimal(5,3) DEFAULT '0.000' COMMENT '赠送百分比，当平台有活动(如xx月xx日-yy月yy日期间购买ios币即送30%，这个值就是0.3)时，只需要修改这个值即可，这个值是个大于0的数',
  `payAmount` decimal(9,2) DEFAULT '0.00' COMMENT '用户购买ios币需要支付的金额，即真实金额，人民币',
  `update_time` bigint(13) DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_ios_coin
-- ----------------------------
INSERT INTO `tb_ios_coin` VALUES ('ios1', '0', '10.00', '1', '0.000', '1.00', '0');
INSERT INTO `tb_ios_coin` VALUES ('ios2', '0', '50.00', '1', '0.300', '5.00', '0');
INSERT INTO `tb_ios_coin` VALUES ('ios3', '0', '100.00', '1', '0.000', '10.00', '0');
INSERT INTO `tb_ios_coin` VALUES ('ios4', '0', '200.00', '1', '0.000', '20.00', '0');
INSERT INTO `tb_ios_coin` VALUES ('ios5', '0', '500.00', '1', '0.000', '50.00', '0');
INSERT INTO `tb_ios_coin` VALUES ('ios6', '0', '1000.00', '1', '0.000', '100.00', '0');
INSERT INTO `tb_ios_coin` VALUES ('ios7', '0', '2000.00', '1', '0.000', '200.00', '0');
INSERT INTO `tb_ios_coin` VALUES ('ios8', '0', '5000.00', '1', '0.000', '500.00', '0');
INSERT INTO `tb_ios_coin` VALUES ('ios9', '0', '10000.00', '1', '0.000', '1000.00', '0');

-- ----------------------------
-- Table structure for tb_job
-- ----------------------------
DROP TABLE IF EXISTS `tb_job`;
CREATE TABLE `tb_job` (
  `id` varchar(32) NOT NULL,
  `create_time` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `job_name` varchar(50) DEFAULT '' COMMENT '职业',
  `orderInt` int(8) DEFAULT '0' COMMENT '排序规则，前段展示从小到大排序',
  `type` int(2) DEFAULT '0' COMMENT '类型 ：1职业 2讲师等级3年龄',
  `update_time` bigint(13) DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_job
-- ----------------------------

-- ----------------------------
-- Table structure for tb_like
-- ----------------------------
DROP TABLE IF EXISTS `tb_like`;
CREATE TABLE `tb_like` (
  `id` varchar(32) NOT NULL,
  `like_dislike` int(1) DEFAULT '0' COMMENT '0 赞,1 倒赞',
  `like_time` bigint(13) DEFAULT '0' COMMENT '点赞时间',
  `like_type` int(2) DEFAULT '0' COMMENT '被点赞类型(1课程,3活动,4讲师,2评论)',
  `like_type_id` varchar(32) DEFAULT '' COMMENT '被点赞的关联ID',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_like
-- ----------------------------

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` varchar(32) NOT NULL,
  `createTime` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `menuName` varchar(20) DEFAULT '' COMMENT '菜单名称',
  `parentId` varchar(32) DEFAULT '' COMMENT '父级id，父级菜单的parentId为静态量TOPMENU_PARENTID',
  `updateTime` bigint(13) DEFAULT '0' COMMENT '修改时间',
  `url` varchar(300) DEFAULT '' COMMENT '菜单的链接地址，当是父菜单时，这里为空',
  `urlId` varchar(32) DEFAULT '' COMMENT 'url的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('id1', '0', '主页管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id14', '0', '频道统计', 'id3', '0', '/mei_yi_quan_admin/channel/tongji.do', 'id14');
INSERT INTO `tb_menu` VALUES ('id15', '0', '课程管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id16', '0', '课程列表', 'id15', '0', '/mei_yi_quan_admin/course/getCourseList', 'id16');
INSERT INTO `tb_menu` VALUES ('id18', '0', '滞销管理', 'id15', '0', '/mei_yi_quan_admin/course/getStandardList', 'id18');
INSERT INTO `tb_menu` VALUES ('id2', '0', 'banner管理', 'id1', '0', '/mei_yi_quan_admin/banner/toBanners.do', 'id1');
INSERT INTO `tb_menu` VALUES ('id20', '0', '评论管理', 'id15', '0', '/mei_yi_quan_admin/course/commentPage', 'id20');
INSERT INTO `tb_menu` VALUES ('id21', '0', '数据统计', 'id15', '0', '/mei_yi_quan_admin/course/courseCount.do', 'id21');
INSERT INTO `tb_menu` VALUES ('id22', '0', '精彩专题管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id23', '0', '专题列表', 'id22', '0', '/mei_yi_quan_admin/special/special.do', 'id23');
INSERT INTO `tb_menu` VALUES ('id25', '0', '专题数据管理', 'id22', '0', '/mei_yi_quan_admin/special/tongji.do', 'id25');
INSERT INTO `tb_menu` VALUES ('id26', '0', '即将上映管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id27', '0', '课程列表', 'id26', '0', '/mei_yi_quan_admin/sooncourse/sooncourse.do', 'id27');
INSERT INTO `tb_menu` VALUES ('id3', '0', '频道管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id30', '0', '评论管理', 'id26', '0', '/mei_yi_quan_admin/sooncourse/commentReplay.do', 'id30');
INSERT INTO `tb_menu` VALUES ('id32', '0', '求教程管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id33', '0', '求教程已通过列表', 'id32', '0', '/mei_yi_quan_admin/askcourse/getAskList.do', 'id33');
INSERT INTO `tb_menu` VALUES ('id34', '0', '求教程全部列表', 'id32', '0', '/mei_yi_quan_admin/askcourse/getVoteList.do', 'id34');
INSERT INTO `tb_menu` VALUES ('id35', '0', '求教程榜首列表', 'id32', '0', '/mei_yi_quan_admin/askcourse/getTopList.do', 'id35');
INSERT INTO `tb_menu` VALUES ('id36', '0', '评价管理', 'id32', '0', '/mei_yi_quan_admin/askcourse/commentlist.do', 'id36');
INSERT INTO `tb_menu` VALUES ('id37', '0', '数据统计', 'id32', '0', '/mei_yi_quan_admin/askcourse/typeTongji.do', 'id37');
INSERT INTO `tb_menu` VALUES ('id38', '0', '（名人大咖）讲师管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id39', '0', '讲师列表', 'id38', '0', '/mei_yi_quan_admin/teacher/getTeacherList', 'id39');
INSERT INTO `tb_menu` VALUES ('id4', '0', '频道列表', 'id3', '0', '/mei_yi_quan_admin/channel/toChannels.do', 'id3');
INSERT INTO `tb_menu` VALUES ('id41', '0', '讲师评论管理', 'id38', '0', '/mei_yi_quan_admin/teacher/commentPage', 'id41');
INSERT INTO `tb_menu` VALUES ('id42', '0', '讲师财务管理', 'id38', '0', '/mei_yi_quan_admin/teachermoney/tmList.do', 'id42');
INSERT INTO `tb_menu` VALUES ('id43', '0', '数据统计', 'id38', '0', '/mei_yi_quan_admin/teacher/countTeacher.do', 'id43');
INSERT INTO `tb_menu` VALUES ('id44', '0', '积分管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id45', '0', '积分规则管理', 'id44', '0', '/mei_yi_quan_admin/point/getPointList', 'id45');
INSERT INTO `tb_menu` VALUES ('id46', '0', '积分任务列表', 'id44', '0', '/mei_yi_quan_admin', 'id46');
INSERT INTO `tb_menu` VALUES ('id47', '0', '优惠券管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id48', '0', '优惠券列表', 'id47', '0', '/mei_yi_quan_admin/coupon/getCoupons.do', 'id48');
INSERT INTO `tb_menu` VALUES ('id53', '0', '线下活动管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id54', '0', '线下活动列表', 'id53', '0', '/mei_yi_quan_admin/active/activelist.do', 'id54');
INSERT INTO `tb_menu` VALUES ('id57', '0', '活动收益列表', 'id53', '0', '/mei_yi_quan_admin/active/incomeList.do', 'id57');
INSERT INTO `tb_menu` VALUES ('id59', '0', '评价管理', 'id53', '0', '/mei_yi_quan_admin/active/commentlist.do', 'id59');
INSERT INTO `tb_menu` VALUES ('id61', '0', '会员管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id62', '0', '会员列表', 'id61', '0', '/mei_yi_quan_admin/user/getList', 'id62');
INSERT INTO `tb_menu` VALUES ('id67', '0', '会员调查管理', 'id61', '0', '/mei_yi_quan_admin/user/getSurveyList', 'id67');
INSERT INTO `tb_menu` VALUES ('id69', '0', '数据统计统计', 'id61', '0', '/mei_yi_quan_admin/user/userCount.do', 'id69');
INSERT INTO `tb_menu` VALUES ('id70', '0', '全国区域管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id71', '0', '区域列表', 'id70', '0', '/mei_yi_quan_admin/district/getDistrictListTypeOne', 'id71');
INSERT INTO `tb_menu` VALUES ('id73', '0', '客服投诉管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id74', '0', '意见反馈列表', 'id73', '0', '/mei_yi_quan_admin/suggestion/suggestionlist.do', 'id74');
INSERT INTO `tb_menu` VALUES ('id77', '0', '数据统计', 'id73', '0', '/mei_yi_quan_admin/suggestion/zongtopngji.do', 'id77');
INSERT INTO `tb_menu` VALUES ('id78', '0', '分享管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id79', '0', '分享记录列表', 'id78', '0', '/mei_yi_quan_admin/share/shareList.do', 'id79');
INSERT INTO `tb_menu` VALUES ('id80', '0', '消息管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id81', '0', '推送', 'id80', '0', '/mei_yi_quan_admin/push/newsPush.do', 'id81');
INSERT INTO `tb_menu` VALUES ('id82', '0', '公共设置', 'top', '0', '#', 'id82');
INSERT INTO `tb_menu` VALUES ('id83', '0', '财务审计管理', 'top', '0', '#', '#');
INSERT INTO `tb_menu` VALUES ('id84', '0', '第三方支付列表', 'id83', '0', '/mei_yi_quan_admin/money/moneyList.do', 'id84');
INSERT INTO `tb_menu` VALUES ('id85', '0', '税务订单列表', 'id83', '0', '/mei_yi_quan_admin/money/orderList.do', 'id85');
INSERT INTO `tb_menu` VALUES ('id87', '0', '优惠券发放审核', 'id83', '0', '/mei_yi_quan_admin/money/shenqingList.do', 'id87');
INSERT INTO `tb_menu` VALUES ('id88', '0', '总部收益统计', 'id83', '0', '/mei_yi_quan_admin/money/gainsList.do', 'id88');
INSERT INTO `tb_menu` VALUES ('id90', '0', '推荐管理', 'id1', '0', '/mei_yi_quan_admin/homepage/getHomePages.do', 'id90');
INSERT INTO `tb_menu` VALUES ('id91', '0', '公共设施管理', 'id82', '0', '/mei_yi_quan_admin/message/publicList.do', 'id82');
INSERT INTO `tb_menu` VALUES ('id92', '0', '管理员管理', 'id82', '0', '/mei_yi_quan_admin/message/adminList.do', 'id82');
INSERT INTO `tb_menu` VALUES ('id93', '0', '角色管理', 'id82', '0', '/mei_yi_quan_admin/role/getRoles.do', 'id82');
INSERT INTO `tb_menu` VALUES ('id94', '0', '讲师提现审核', 'id83', '0', '/mei_yi_quan_admin/message/tmList.do', 'id83');

-- ----------------------------
-- Table structure for tb_menu_urls
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu_urls`;
CREATE TABLE `tb_menu_urls` (
  `id` varchar(32) NOT NULL,
  `createTime` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `menuId` varchar(32) DEFAULT '' COMMENT '菜单ID',
  `urlId` varchar(32) DEFAULT '' COMMENT 'url的ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu_urls
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` varchar(32) NOT NULL,
  `achieve` decimal(9,2) DEFAULT '0.00' COMMENT '平台获得金额，单位:元',
  `create_time` bigint(13) DEFAULT '0' COMMENT '下单时间',
  `ios_deduct` decimal(9,2) DEFAULT '0.00' COMMENT 'IOS内购时，这个字段表示在苹果平台充值时扣除比例，是个小于1的数',
  `isUseCoupon` int(1) DEFAULT '0' COMMENT '是否使用了优惠券，1---是，2---否',
  `oldPrice` decimal(9,2) DEFAULT '0.00' COMMENT '商品原价，去除优惠券金额之前(单位:元)',
  `order_num` varchar(20) DEFAULT '' COMMENT '订单号',
  `pay_time` bigint(13) DEFAULT '0' COMMENT '支付时间',
  `pay_type` int(2) DEFAULT '0' COMMENT '支付类型(1ping++支付宝,2ping++微信,4IOS内购)',
  `price` decimal(9,2) DEFAULT '0.00' COMMENT '结算金额(单位:元)，当是内购时，这个字段表示点券数额',
  `status` int(1) DEFAULT '0' COMMENT '状态(0未支付,1已支付,2已发送,已发送已作废)',
  `tax` decimal(9,2) DEFAULT '0.00' COMMENT '税率，是个小于1的数。当是苹果内购时，这个表示充值时的税率',
  `type` int(2) DEFAULT '0' COMMENT '订单类型：1---视频订单，2---活动订单',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户ID',
  `video_id` varchar(32) DEFAULT '' COMMENT '视频ID或者活动id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_playrecord
-- ----------------------------
DROP TABLE IF EXISTS `tb_playrecord`;
CREATE TABLE `tb_playrecord` (
  `id` varchar(32) NOT NULL,
  `continue_time` varchar(20) DEFAULT '' COMMENT '上次播放的位置(原样接收，原样返回)',
  `course_id` varchar(32) DEFAULT '' COMMENT '课程ID',
  `play_time` bigint(13) DEFAULT '0' COMMENT '播放时间',
  `time_long` int(6) DEFAULT '0' COMMENT '播放时长，秒',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户ID',
  `video_id` varchar(32) DEFAULT '' COMMENT '播放视频ID',
  `video_title` varchar(50) DEFAULT '' COMMENT '视频标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_playrecord
-- ----------------------------

-- ----------------------------
-- Table structure for tb_point
-- ----------------------------
DROP TABLE IF EXISTS `tb_point`;
CREATE TABLE `tb_point` (
  `id` varchar(32) NOT NULL,
  `expire_time` bigint(13) DEFAULT '0' COMMENT '积分到期时间',
  `get_time` bigint(13) DEFAULT '0' COMMENT '积分领取时间',
  `point_quantum` int(4) DEFAULT '0' COMMENT '本次获得积分数量',
  `point_unused` int(4) DEFAULT '0' COMMENT '积分的剩余量',
  `point_used` int(4) DEFAULT '0' COMMENT '积分的使用量',
  `point_way_id` varchar(32) DEFAULT '' COMMENT '获得积分途径ID',
  `status` int(1) DEFAULT '1' COMMENT '积分状态(2禁用,1可用)',
  `use_time` bigint(13) DEFAULT '0' COMMENT '积分的最后一次使用时间',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户ID',
  `warn_time` bigint(13) DEFAULT '0' COMMENT '积分到期提前提醒时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_point
-- ----------------------------

-- ----------------------------
-- Table structure for tb_point_day
-- ----------------------------
DROP TABLE IF EXISTS `tb_point_day`;
CREATE TABLE `tb_point_day` (
  `id` varchar(32) NOT NULL,
  `day` int(2) DEFAULT '0' COMMENT '连续签到天数',
  `day_point` int(3) DEFAULT '0' COMMENT '连续签到天数对应获得的积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_point_day
-- ----------------------------
INSERT INTO `tb_point_day` VALUES ('1', '1', '10');
INSERT INTO `tb_point_day` VALUES ('10', '10', '10');
INSERT INTO `tb_point_day` VALUES ('11', '11', '10');
INSERT INTO `tb_point_day` VALUES ('12', '12', '10');
INSERT INTO `tb_point_day` VALUES ('13', '13', '10');
INSERT INTO `tb_point_day` VALUES ('14', '14', '10');
INSERT INTO `tb_point_day` VALUES ('15', '15', '50');
INSERT INTO `tb_point_day` VALUES ('16', '16', '10');
INSERT INTO `tb_point_day` VALUES ('17', '17', '10');
INSERT INTO `tb_point_day` VALUES ('18', '18', '10');
INSERT INTO `tb_point_day` VALUES ('19', '19', '10');
INSERT INTO `tb_point_day` VALUES ('2', '2', '10');
INSERT INTO `tb_point_day` VALUES ('20', '20', '10');
INSERT INTO `tb_point_day` VALUES ('21', '21', '10');
INSERT INTO `tb_point_day` VALUES ('22', '22', '10');
INSERT INTO `tb_point_day` VALUES ('23', '23', '10');
INSERT INTO `tb_point_day` VALUES ('24', '24', '10');
INSERT INTO `tb_point_day` VALUES ('25', '25', '10');
INSERT INTO `tb_point_day` VALUES ('26', '26', '10');
INSERT INTO `tb_point_day` VALUES ('27', '27', '10');
INSERT INTO `tb_point_day` VALUES ('28', '28', '10');
INSERT INTO `tb_point_day` VALUES ('29', '29', '10');
INSERT INTO `tb_point_day` VALUES ('3', '3', '15');
INSERT INTO `tb_point_day` VALUES ('30', '30', '100');
INSERT INTO `tb_point_day` VALUES ('4', '4', '10');
INSERT INTO `tb_point_day` VALUES ('5', '5', '10');
INSERT INTO `tb_point_day` VALUES ('6', '6', '10');
INSERT INTO `tb_point_day` VALUES ('7', '7', '20');
INSERT INTO `tb_point_day` VALUES ('8', '8', '10');
INSERT INTO `tb_point_day` VALUES ('9', '9', '10');

-- ----------------------------
-- Table structure for tb_point_way_most
-- ----------------------------
DROP TABLE IF EXISTS `tb_point_way_most`;
CREATE TABLE `tb_point_way_most` (
  `id` varchar(32) NOT NULL,
  `remark` varchar(500) DEFAULT '' COMMENT '途径的说明',
  `way_most` int(4) DEFAULT '0' COMMENT '单日获取积分的上限',
  `way_name` varchar(50) DEFAULT '' COMMENT '获取积分途径名称',
  `way_single` int(4) DEFAULT '0' COMMENT '单次获得积分数量',
  `way_type` int(2) DEFAULT '0' COMMENT '获取积分途径类型，这个类型在项目静态量里有专门的对应关系',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_point_way_most
-- ----------------------------
INSERT INTO `tb_point_way_most` VALUES ('1', '根据连续签到天数确定赠送积分量', '0', '登录签到', '0', '6');
INSERT INTO `tb_point_way_most` VALUES ('10', '每日', '300', '参与投票', '11', '12');
INSERT INTO `tb_point_way_most` VALUES ('2', '仅一次', '500', '注册', '0', '1');
INSERT INTO `tb_point_way_most` VALUES ('3', '仅一次', '20', '上传头像', '0', '3');
INSERT INTO `tb_point_way_most` VALUES ('4', '仅一次', '80', '完善资料(姓名 性别 身高 生日)', '0', '5');
INSERT INTO `tb_point_way_most` VALUES ('5', '仅一次', '100', '手机验证', '0', '4');
INSERT INTO `tb_point_way_most` VALUES ('6', '无上限', '0', '现金消费', '100', '2');
INSERT INTO `tb_point_way_most` VALUES ('7', '每日', '50', '课程评论', '5', '7');
INSERT INTO `tb_point_way_most` VALUES ('8', '无上限', '0', '邀请好友注册,并通过手机验证', '500', '8');
INSERT INTO `tb_point_way_most` VALUES ('9', '无上限', '0', '收藏(课程 产品 活动 讲师)', '10', '11');

-- ----------------------------
-- Table structure for tb_public
-- ----------------------------
DROP TABLE IF EXISTS `tb_public`;
CREATE TABLE `tb_public` (
  `id` varchar(32) NOT NULL,
  `content` varchar(100) DEFAULT '' COMMENT '参数内容',
  `parameter_name` varchar(32) DEFAULT '' COMMENT '参数名称',
  `parameter_num` int(3) DEFAULT '0' COMMENT '参数编号',
  `pic_url` longtext COMMENT '图片url',
  `type` int(1) DEFAULT '0' COMMENT '类型1文字0图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_public
-- ----------------------------

-- ----------------------------
-- Table structure for tb_push
-- ----------------------------
DROP TABLE IF EXISTS `tb_push`;
CREATE TABLE `tb_push` (
  `id` varchar(32) NOT NULL,
  `content` varchar(1500) DEFAULT '' COMMENT '内容',
  `createTime` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `mapjson` varchar(1000) DEFAULT '{}' COMMENT '推送中携带的map，是json的格式',
  `status` int(1) DEFAULT '1' COMMENT '本推送信息是否启用，1--启用，2--不启用，当不启用时，不能通过后台管理系统人为推送，即时推送也会失效',
  `title` varchar(50) DEFAULT '' COMMENT '标题',
  `type` int(2) DEFAULT '0' COMMENT '本推送信息的类型，分为即时推送和后台推送。人为推送：2，即时推送：1',
  `updateTime` bigint(13) DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_push
-- ----------------------------
INSERT INTO `tb_push` VALUES ('push_video_buy', '有人购买了您的视频', '0', '{}', '1', '视频购买成功后向讲师推送', '1', '0');
INSERT INTO `tb_push` VALUES ('push_video_dianzan', '有人给您的视频点赞了', '0', '{}', '1', '视频点赞发送的推送', '1', '0');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` varchar(32) NOT NULL,
  `roleName` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rrby1eqjhfom1o9f5awycob0q` (`roleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('0', '无权限');
INSERT INTO `tb_role` VALUES ('402858815a63cebd015a63d4a8520000', '测试角色');
INSERT INTO `tb_role` VALUES ('id1', '超级管理员');

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `id` varchar(32) NOT NULL,
  `createTime` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `menuId` varchar(32) DEFAULT '' COMMENT '菜单ID',
  `roleId` varchar(32) DEFAULT '' COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES ('0493dd077b5d4cc6ad9c49eba933fc05', '1487748170082', 'id76', '0');
INSERT INTO `tb_role_menu` VALUES ('07536d7bda0346fa990597aa772aea90', '1487748170082', 'id59', '0');
INSERT INTO `tb_role_menu` VALUES ('07dde0804dc74292bce4daa8c2767dfd', '1487748170082', 'id47', '0');
INSERT INTO `tb_role_menu` VALUES ('10decbedc3c04e2c8793a5df4b65246d', '1487748170082', 'id30', '0');
INSERT INTO `tb_role_menu` VALUES ('1266edd04e16456e9862109428427ceb', '1487734210111', 'id88', 'id2');
INSERT INTO `tb_role_menu` VALUES ('130d684b28354a02b73095194eb163b4', '1487733581896', 'id75', '402858815a63cebd015a63d4a8520000');
INSERT INTO `tb_role_menu` VALUES ('1425c315e7d942b5b0459e54a9dc4fd7', '1487748170082', 'id75', '0');
INSERT INTO `tb_role_menu` VALUES ('16759a8249154a3391656354e6b634ab', '1487748170082', 'id80', '0');
INSERT INTO `tb_role_menu` VALUES ('195ed0cb6ba94d91bb622b188efb8443', '1487733581896', 'id70', '402858815a63cebd015a63d4a8520000');
INSERT INTO `tb_role_menu` VALUES ('1d8411bf274d446b99e4625e98139a8a', '1487748170082', 'id36', '0');
INSERT INTO `tb_role_menu` VALUES ('1d903f8a7b434bb6b0339671c4b6d4a6', '1487748170082', 'id48', '0');
INSERT INTO `tb_role_menu` VALUES ('1d9a951b4a8249049c3c826305f6873b', '1487733581896', 'id71', '402858815a63cebd015a63d4a8520000');
INSERT INTO `tb_role_menu` VALUES ('1f8bf28dc783409181e4d15f0fa41b1e', '1487748170082', 'id40', '0');
INSERT INTO `tb_role_menu` VALUES ('266dc898809c4398bb5f5638e7c06b16', '1487748170082', 'id34', '0');
INSERT INTO `tb_role_menu` VALUES ('26d5d487db9e49a6b441bf5ee9f0f08e', '1487734210111', 'id74', 'id2');
INSERT INTO `tb_role_menu` VALUES ('2c5265cdec344671b225b7342a2fa100', '1487734210111', 'id80', 'id2');
INSERT INTO `tb_role_menu` VALUES ('3389ef944df6482cbf5fb30661d3318d', '1487748170082', 'id55', '0');
INSERT INTO `tb_role_menu` VALUES ('3516bf19ba484c149a26259a1d32596d', '1487734210111', 'id92', 'id2');
INSERT INTO `tb_role_menu` VALUES ('35892411c0004cf4bcab1d6ff22633e8', '1487748170082', 'id74', '0');
INSERT INTO `tb_role_menu` VALUES ('367e46c614034e848361d5b8d49501ab', '1487748170082', 'id41', '0');
INSERT INTO `tb_role_menu` VALUES ('36a6d0811978435bad6120baefa6edcf', '1487748170082', 'id43', '0');
INSERT INTO `tb_role_menu` VALUES ('36ba5e7bc58446c4935874538a798f74', '1487748170082', 'id53', '0');
INSERT INTO `tb_role_menu` VALUES ('44cebced36604314ace6f1a66b31889c', '1487733581896', 'id77', '402858815a63cebd015a63d4a8520000');
INSERT INTO `tb_role_menu` VALUES ('476c6cb2495d485ca444e5d87c1c259d', '1487748170082', 'id29', '0');
INSERT INTO `tb_role_menu` VALUES ('4810550dc8684d3c91217e2e4456a82f', '1487748170082', 'id67', '0');
INSERT INTO `tb_role_menu` VALUES ('567317b85f5f4131934e029d1b379e27', '1487748170082', 'id3', '0');
INSERT INTO `tb_role_menu` VALUES ('569329f9104d4cf5ae4551208f047a67', '1487748170082', 'id78', '0');
INSERT INTO `tb_role_menu` VALUES ('5e81df4c893d492e8a20b762585c2ca5', '1487734210111', 'id75', 'id2');
INSERT INTO `tb_role_menu` VALUES ('5f6994a0db54480ea4ec7b8559abefa4', '1487748170082', 'id56', '0');
INSERT INTO `tb_role_menu` VALUES ('679eb7aaa92b46cd928df5f4daf67df0', '1487748170082', 'id77', '0');
INSERT INTO `tb_role_menu` VALUES ('6f94e6743c144a78a1cb0a2205bd4889', '1487748170082', 'id73', '0');
INSERT INTO `tb_role_menu` VALUES ('6ff869e1360a4da3a497d169bc18cd5a', '1487748170082', 'id32', '0');
INSERT INTO `tb_role_menu` VALUES ('734229e81f6a4b94b0d0d5994229f95b', '1487748170082', 'id4', '0');
INSERT INTO `tb_role_menu` VALUES ('750aab2a7e8848f6b924b91fa77a7bf8', '1487733581896', 'id72', '402858815a63cebd015a63d4a8520000');
INSERT INTO `tb_role_menu` VALUES ('766873f8ce72458f8e51eed633f36d68', '1487733581896', 'id69', '402858815a63cebd015a63d4a8520000');
INSERT INTO `tb_role_menu` VALUES ('78ede722508d4a4fa236243530bd8a56', '1487734210111', 'id83', 'id2');
INSERT INTO `tb_role_menu` VALUES ('7faf16738805471090cfe38ef481fae4', '1487748170082', 'id35', '0');
INSERT INTO `tb_role_menu` VALUES ('8017ff64042a4e25a0f8d72444fad226', '1487734210111', 'id87', 'id2');
INSERT INTO `tb_role_menu` VALUES ('81067ac51b6c4c59aaf70dde1af29e95', '1487748170082', 'id37', '0');
INSERT INTO `tb_role_menu` VALUES ('8750ba4afeb743a4a248ba47c7c9d730', '1487734210111', 'id85', 'id2');
INSERT INTO `tb_role_menu` VALUES ('8851c97e19e74ba18eaa26092ce498f7', '1487734210111', 'id77', 'id2');
INSERT INTO `tb_role_menu` VALUES ('8a94cc3ae2404031a0c99fd5209c7243', '1487734210111', 'id91', 'id2');
INSERT INTO `tb_role_menu` VALUES ('8bec62fa927741dbbc365f94fdc0300e', '1487748170082', 'id38', '0');
INSERT INTO `tb_role_menu` VALUES ('8c79c1da86e844c8aab0f1df5a040143', '1487748170082', 'id45', '0');
INSERT INTO `tb_role_menu` VALUES ('8f94603fd68744da804be47011d9a1a6', '1487748170082', 'id54', '0');
INSERT INTO `tb_role_menu` VALUES ('90f833a31aa344ad918a013ffa4183e8', '1487748170082', 'id71', '0');
INSERT INTO `tb_role_menu` VALUES ('96ef8e1ec7124f64b93678a9a7441c40', '1487748170082', 'id31', '0');
INSERT INTO `tb_role_menu` VALUES ('993a1a6801734a8fb7f5de2ad0ffbe60', '1487733581896', 'id73', '402858815a63cebd015a63d4a8520000');
INSERT INTO `tb_role_menu` VALUES ('9b46712438ad4df6809f75627a884cd7', '1487734210111', 'id82', 'id2');
INSERT INTO `tb_role_menu` VALUES ('9b75262d80c6436aa406a21b2815af04', '1487748170082', 'id14', '0');
INSERT INTO `tb_role_menu` VALUES ('9dcfab4fbf234fe698f8c6b5d5689948', '1487748170082', 'id42', '0');
INSERT INTO `tb_role_menu` VALUES ('9ff4e957ea094e0099071a82c13a6c32', '1487748170082', 'id69', '0');
INSERT INTO `tb_role_menu` VALUES ('a25aa6642b964fb7b5f1da783ceb1333', '1487748170082', 'id72', '0');
INSERT INTO `tb_role_menu` VALUES ('a3216c83870e43e7b0bad72e2fa62630', '1487733581896', 'id76', '402858815a63cebd015a63d4a8520000');
INSERT INTO `tb_role_menu` VALUES ('a36fc8af50ac464eb43579c04e36a6f7', '1487748170082', 'id33', '0');
INSERT INTO `tb_role_menu` VALUES ('aac6d78f3dce4c899fe9e343b6af0eed', '1487748170082', 'id70', '0');
INSERT INTO `tb_role_menu` VALUES ('ae4897daf89f451987f04b0d4cc27474', '1487748170082', 'id81', '0');
INSERT INTO `tb_role_menu` VALUES ('ae71df3f5f464caf827e3b4224d378c8', '1487748170082', 'id28', '0');
INSERT INTO `tb_role_menu` VALUES ('b0130e553d2b40f89a11ef8f38e7e012', '1487748170082', 'id57', '0');
INSERT INTO `tb_role_menu` VALUES ('b0a6d6a727fd4c4eab623df631d626c3', '1487733581896', 'id74', '402858815a63cebd015a63d4a8520000');
INSERT INTO `tb_role_menu` VALUES ('b8dd4e587560464da29237b9c9bbd8fb', '1487733581896', 'id61', '402858815a63cebd015a63d4a8520000');
INSERT INTO `tb_role_menu` VALUES ('c5127aeed67046f490aa1b0fcdbeef6e', '1487748170082', 'id39', '0');
INSERT INTO `tb_role_menu` VALUES ('c5882d9e2e884133b718816d3c822973', '1487734210111', 'id84', 'id2');
INSERT INTO `tb_role_menu` VALUES ('c60161977508429082b0d4a0aa754504', '1487748170082', 'id26', '0');
INSERT INTO `tb_role_menu` VALUES ('c6962297ab0b4f08ab42a80ee7d71489', '1487734210111', 'id93', 'id2');
INSERT INTO `tb_role_menu` VALUES ('c9ba3aa865b940509cf1b3929f960136', '1487748170082', 'id58', '0');
INSERT INTO `tb_role_menu` VALUES ('cf45d70c2c1940549c1e5fe1eaf3129a', '1487748170082', 'id46', '0');
INSERT INTO `tb_role_menu` VALUES ('daf5369ad37b4ffcad6f5213cb7f1100', '1487748170082', 'id62', '0');
INSERT INTO `tb_role_menu` VALUES ('dc3dab0a4c9c4c148c6bb8d9470874c7', '1487748170082', 'id61', '0');
INSERT INTO `tb_role_menu` VALUES ('de4c9f16d4364c66a77a134a49ff9dc5', '1487748170082', 'id27', '0');
INSERT INTO `tb_role_menu` VALUES ('e4259eb13b3e49149ebfa9bb3dce875a', '1487734210111', 'id73', 'id2');
INSERT INTO `tb_role_menu` VALUES ('e6558a6b71f047138952f3d4958e8a39', '1487734210111', 'id79', 'id2');
INSERT INTO `tb_role_menu` VALUES ('f0cc4c784af24f0db69e6ba2f5b01a68', '1487733581896', 'id62', '402858815a63cebd015a63d4a8520000');
INSERT INTO `tb_role_menu` VALUES ('f22dc4f4fc97449d83023788bc82c878', '1487748170082', 'id79', '0');
INSERT INTO `tb_role_menu` VALUES ('f5a9dd3f51aa4527b849ef048c3abcfd', '1487734210111', 'id78', 'id2');
INSERT INTO `tb_role_menu` VALUES ('f779345e4d874c50b7d31122abe52d9f', '1487734210111', 'id81', 'id2');
INSERT INTO `tb_role_menu` VALUES ('f77fe73c66f049089b965d24825d6b94', '1487748170082', 'id44', '0');
INSERT INTO `tb_role_menu` VALUES ('fd334371fb7f47618d239e7a5cf821df', '1487748170082', 'id60', '0');
INSERT INTO `tb_role_menu` VALUES ('fdeba1d24e404badbe145c7d7843188c', '1487733581896', 'id67', '402858815a63cebd015a63d4a8520000');
INSERT INTO `tb_role_menu` VALUES ('fe4542d85d1742d49876ac5d9667b2a3', '1487734210111', 'id76', 'id2');
INSERT INTO `tb_role_menu` VALUES ('id1', '0', 'id1', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id10', '0', 'id10', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id11', '0', 'id11', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id12', '0', 'id12', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id13', '0', 'id13', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id14', '0', 'id14', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id15', '0', 'id15', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id16', '0', 'id16', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id17', '0', 'id17', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id18', '0', 'id18', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id19', '0', 'id19', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id2', '0', 'id2', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id20', '0', 'id20', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id21', '0', 'id21', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id22', '0', 'id22', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id23', '0', 'id23', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id24', '0', 'id24', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id25', '0', 'id25', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id26', '0', 'id26', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id27', '0', 'id27', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id28', '0', 'id28', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id29', '0', 'id29', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id3', '0', 'id3', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id30', '0', 'id30', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id31', '0', 'id31', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id32', '0', 'id32', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id33', '0', 'id33', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id34', '0', 'id34', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id35', '0', 'id35', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id36', '0', 'id36', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id37', '0', 'id37', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id38', '0', 'id38', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id39', '0', 'id39', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id4', '0', 'id4', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id40', '0', 'id40', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id41', '0', 'id41', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id42', '0', 'id42', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id43', '0', 'id43', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id44', '0', 'id44', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id45', '0', 'id45', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id46', '0', 'id46', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id47', '0', 'id47', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id48', '0', 'id48', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id49', '0', 'id49', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id5', '0', 'id5', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id50', '0', 'id50', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id51', '0', 'id51', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id52', '0', 'id52', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id53', '0', 'id53', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id54', '0', 'id54', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id55', '0', 'id55', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id56', '0', 'id56', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id57', '0', 'id57', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id58', '0', 'id58', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id59', '0', 'id59', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id6', '0', 'id6', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id60', '0', 'id60', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id61', '0', 'id61', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id62', '0', 'id62', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id63', '0', 'id63', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id64', '0', 'id64', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id65', '0', 'id65', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id66', '0', 'id66', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id67', '0', 'id67', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id68', '0', 'id68', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id69', '0', 'id69', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id7', '0', 'id7', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id70', '0', 'id70', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id71', '0', 'id71', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id72', '0', 'id72', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id73', '0', 'id73', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id74', '0', 'id74', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id75', '0', 'id75', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id76', '0', 'id76', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id77', '0', 'id77', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id78', '0', 'id78', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id79', '0', 'id79', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id8', '0', 'id8', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id80', '0', 'id80', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id81', '0', 'id81', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id82', '0', 'id82', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id83', '0', 'id83', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id84', '0', 'id84', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id85', '0', 'id85', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id86', '0', 'id86', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id87', '0', 'id87', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id88', '0', 'id88', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id89', '0', 'id89', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id9', '0', 'id9', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id90', '0', 'id90', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id91', '0', 'id91', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id92', '0', 'id92', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id93', '0', 'id93', 'id1');
INSERT INTO `tb_role_menu` VALUES ('id94', '0', 'id94', 'id1');

-- ----------------------------
-- Table structure for tb_shared
-- ----------------------------
DROP TABLE IF EXISTS `tb_shared`;
CREATE TABLE `tb_shared` (
  `id` varchar(32) NOT NULL,
  `district` int(2) DEFAULT '0' COMMENT '分享区域：1(QQ好友)，2(QQ空间)，3(微信好友)，4(微信朋友圈)，5(新浪微博)',
  `shareId` varchar(32) DEFAULT '' COMMENT '分享内容ID，如视频id或者活动id，当分享的内容没有id时(如分享app链接)，这个字段可以不用',
  `share_time` bigint(13) DEFAULT '0' COMMENT '分享时间',
  `type` int(2) DEFAULT '0' COMMENT '分享类型：3(视频)，4(活动)，7(课程)，8(求课程)',
  `url` varchar(300) DEFAULT '' COMMENT '分享地址',
  `userId` varchar(32) DEFAULT '' COMMENT '分享人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shared
-- ----------------------------

-- ----------------------------
-- Table structure for tb_statistics
-- ----------------------------
DROP TABLE IF EXISTS `tb_statistics`;
CREATE TABLE `tb_statistics` (
  `id` varchar(32) NOT NULL,
  `click_count` int(9) DEFAULT '0' COMMENT '点击量',
  `click_expect_count` int(9) DEFAULT '0' COMMENT '点击虚拟量',
  `collect_count` int(9) DEFAULT '0' COMMENT '收藏量',
  `collect_expect_count` int(9) DEFAULT '0' COMMENT '收藏虚拟量',
  `comment_count` int(9) DEFAULT '0' COMMENT '评论量',
  `comment_expect_count` int(9) DEFAULT '0' COMMENT '评论虚拟量',
  `coupon_count` int(9) DEFAULT '0' COMMENT '优惠券数量',
  `dislike_count` int(9) DEFAULT '0' COMMENT '点倒赞量',
  `dislike_expect_count` int(9) DEFAULT '0' COMMENT '倒赞虚拟量',
  `follow_count` int(9) DEFAULT '0' COMMENT '关注量',
  `follow_expect_count` int(9) DEFAULT '0' COMMENT '关注虚拟量',
  `like_count` int(9) DEFAULT '0' COMMENT '点赞量',
  `like_expect_count` int(9) DEFAULT '0' COMMENT '点赞虚拟量',
  `play_count` int(9) DEFAULT '0' COMMENT '播放量',
  `play_expect_count` int(9) DEFAULT '0' COMMENT '播放虚拟量',
  `share_count` int(9) DEFAULT '0' COMMENT '分享量',
  `share_expect_count` int(9) DEFAULT '0' COMMENT '分享虚拟量',
  `signdays` int(2) DEFAULT '0' COMMENT '连续签到天数',
  `type` int(2) DEFAULT '0' COMMENT '关联类型(1课程,2活动,3视频,4.讲师)',
  `type_id` varchar(32) DEFAULT '' COMMENT '关联ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for tb_subject
-- ----------------------------
DROP TABLE IF EXISTS `tb_subject`;
CREATE TABLE `tb_subject` (
  `id` varchar(32) NOT NULL,
  `cover_pic_url` varchar(300) DEFAULT '' COMMENT '宣传封面(图片)访问地址',
  `cover_type` int(1) DEFAULT '0' COMMENT '宣传封面类型 0图片,1视频',
  `cover_video_url` varchar(300) DEFAULT '' COMMENT '宣传封面视频访问地址(封面为图片时没有)',
  `create_admin_id` varchar(32) DEFAULT '' COMMENT '创建人ID',
  `create_time` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `introduction` varchar(50) DEFAULT '' COMMENT '简介',
  `name` varchar(20) DEFAULT '' COMMENT '名称',
  `remark` varchar(500) DEFAULT '' COMMENT '详细信息',
  `sort_num` int(8) DEFAULT '0' COMMENT '排列顺序',
  `status` int(1) DEFAULT '1' COMMENT '2禁用,1启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_subject
-- ----------------------------

-- ----------------------------
-- Table structure for tb_suggestion
-- ----------------------------
DROP TABLE IF EXISTS `tb_suggestion`;
CREATE TABLE `tb_suggestion` (
  `id` varchar(50) NOT NULL,
  `back_time` bigint(13) DEFAULT '0' COMMENT '反馈时间',
  `content` varchar(1000) DEFAULT '' COMMENT '内容',
  `genre` int(2) DEFAULT '0' COMMENT '分类类别 1 视频相关2活动相关3软件bug相关4讲师相关5普通用户相关6支付相关7其他',
  `phone_num` bigint(15) NOT NULL DEFAULT '0' COMMENT '手机号',
  `recount_id` varchar(50) DEFAULT '' COMMENT '回复消息ID',
  `status` int(1) DEFAULT '0' COMMENT '状态 0未处理 1已处理',
  `user_id` varchar(50) DEFAULT '' COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_suggestion
-- ----------------------------

-- ----------------------------
-- Table structure for tb_suggestion_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_suggestion_type`;
CREATE TABLE `tb_suggestion_type` (
  `id` varchar(50) NOT NULL,
  `genre` int(2) DEFAULT '0' COMMENT '分类类别 1 视频相关2活动相关3软件bug相关4讲师相关5普通用户相关6支付相关7其他',
  `name` varchar(50) DEFAULT '' COMMENT '名字',
  `status` int(2) DEFAULT '1' COMMENT '状态 1启用2禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_suggestion_type
-- ----------------------------
INSERT INTO `tb_suggestion_type` VALUES ('1', '1', '视频相关', '1');
INSERT INTO `tb_suggestion_type` VALUES ('2', '2', '活动相关', '1');
INSERT INTO `tb_suggestion_type` VALUES ('3', '3', '软件bug相关', '1');
INSERT INTO `tb_suggestion_type` VALUES ('4', '4', '讲师相关', '1');
INSERT INTO `tb_suggestion_type` VALUES ('5', '5', '普通用户相关', '1');
INSERT INTO `tb_suggestion_type` VALUES ('6', '6', '支付相关', '1');
INSERT INTO `tb_suggestion_type` VALUES ('7', '7', '其他', '1');

-- ----------------------------
-- Table structure for tb_system
-- ----------------------------
DROP TABLE IF EXISTS `tb_system`;
CREATE TABLE `tb_system` (
  `id` varchar(32) NOT NULL,
  `status` int(1) DEFAULT '1' COMMENT '是否启用，1--启用，2--不启用',
  `sys_key` varchar(50) DEFAULT '' COMMENT '存储的key',
  `sys_value` varchar(500) DEFAULT '' COMMENT '存储的value',
  `title` varchar(500) DEFAULT '' COMMENT '标题',
  `type` int(2) DEFAULT '0' COMMENT '类型，具体见静态量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_system
-- ----------------------------
INSERT INTO `tb_system` VALUES ('1', '1', 'user_video_request_pic_url', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/test2/img/banner/20325aad54e71aea9c84d569c50883a5b6b6e9d5294ed-uRKbzJ_fw658.png', '用于表示求课程背景图片的key字段', '1');
INSERT INTO `tb_system` VALUES ('10', '1', 'system_about_us', '关于我们', '关于我们', '4');
INSERT INTO `tb_system` VALUES ('11', '1', 'getcoupons_share_course', '{\"c532190d3fcc4800822bd2d740b46d84\":1}', '分享课程赠送优惠券', '3');
INSERT INTO `tb_system` VALUES ('12', '1', 'getcoupons_share_request', '{\"c532190d3fcc4800822bd2d740b46d84\":1}', '分享求课程赠送优惠券', '3');
INSERT INTO `tb_system` VALUES ('13', '1', 'order_tax_ios', '0.02', '用于表示苹果内购方式的税率，是个小于1的数', '2');
INSERT INTO `tb_system` VALUES ('14', '1', 'order_tax', '0.05', '用于表示普通购买方式的税率，是个小于1的数', '2');
INSERT INTO `tb_system` VALUES ('15', '1', 'order_ios', '0.3', '用于表示苹果内购方式苹果官方扣除比例，是个小于1的数', '2');
INSERT INTO `tb_system` VALUES ('16', '1', 'teacher_order_default_percent', '0.8', '讲师从订单收益中抽取的百分比。当讲师没有设置抽取百分比时(对应字段值为-1表示没有设置)，选用这个默认字段', '2');
INSERT INTO `tb_system` VALUES ('17', '1', 'request_background_pic', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/test2/img/banner/20325aad54e71aea9c84d569c50883a5b6b6e9d5294ed-uRKbzJ_fw658.png', '点击添加求课程时的banner栏的背景图', '1');
INSERT INTO `tb_system` VALUES ('2', '1', 'push_systeminfo_photo', 'http://meifaxuetang.oss-cn-beijing.aliyuncs.com/test/images/head/man.jpg', '推送完毕生成系统信息时的图片地址', '1');
INSERT INTO `tb_system` VALUES ('3', '1', 'video_freeshow_defaulttime', '3000', '收费视频免费观看时长的默认值，单位是毫秒', '2');
INSERT INTO `tb_system` VALUES ('4', '1', 'getcoupons_share_client_url', '{\"c532190d3fcc4800822bd2d740b46d84\":3,\"4aba791682ce40f8913999549a086896\":1,\"a2c4d4d0c54846ac929fe09f656f73a9\":10}', '分享客户端活动优惠券', '3');
INSERT INTO `tb_system` VALUES ('5', '1', 'getcoupons_user_request_pass', '{\"4594249df07e45c894d97d1521a294b4\":1}', '用户发起求教程申请，审核通过后赠送的优惠券', '3');
INSERT INTO `tb_system` VALUES ('6', '1', 'getcoupons_share_video', '{\"c532190d3fcc4800822bd2d740b46d84\":1}', '分享视频赠送优惠券', '3');
INSERT INTO `tb_system` VALUES ('7', '1', 'getcoupons_share_activity', '{\"c532190d3fcc4800822bd2d740b46d84\":1}', '分享活动赠送优惠券', '3');
INSERT INTO `tb_system` VALUES ('8', '1', 'getcoupons_like_or_dislike', '{\"a19672d230fa46f9825a92096647d4a4\":1}', '即将上映课程，点击喜欢或不喜欢活动优惠券', '3');
INSERT INTO `tb_system` VALUES ('9', '1', 'getcoupons_buy_activity', '', '参与线下活动支付的金额，赠送一个优惠价', '3');

-- ----------------------------
-- Table structure for tb_system_information
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_information`;
CREATE TABLE `tb_system_information` (
  `id` varchar(32) NOT NULL,
  `content` varchar(5000) DEFAULT '' COMMENT '系统消息内容',
  `get_info_time` bigint(13) DEFAULT '0' COMMENT '系统消息推送消息时间',
  `mapjson` varchar(1000) DEFAULT '{}' COMMENT '推送中携带的map，是json的格式',
  `pushId` varchar(32) DEFAULT '' COMMENT '本推送信息的id。后台人为推送时是通过后台来选取相应的pushId，即时推送时是通过静态量里已经设置好的几个固定的id来推送的',
  `status` int(1) DEFAULT '2' COMMENT '是否已经被阅读(2未读,1已读)',
  `system_pic_url` varchar(300) DEFAULT '' COMMENT '系统头像访问地址',
  `title` varchar(50) DEFAULT '' COMMENT '标题',
  `type` int(2) DEFAULT '0' COMMENT '推送类型，分为即时推送和后台人为推送，即时推送还分为两种，以需不需要查询数据库为准',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_system_information
-- ----------------------------

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher` (
  `id` varchar(32) NOT NULL,
  `create_time` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `creater_id` varchar(32) DEFAULT '' COMMENT '创建人ID',
  `first_word` char(1) DEFAULT '' COMMENT '讲师昵称首字母',
  `head_url` varchar(300) DEFAULT '' COMMENT '讲师头像存储访问路径',
  `introduction` varchar(150) DEFAULT '' COMMENT '讲师简介',
  `level` varchar(10) DEFAULT '' COMMENT '讲师等级',
  `level_id` varchar(32) DEFAULT '' COMMENT '讲师等级ID，关联job表',
  `make_account` varchar(100) DEFAULT '' COMMENT '讲师收款账号',
  `make_type` int(2) DEFAULT '3' COMMENT '讲师收款工具类型：1--微信，2--QQ，3--支付宝，4--银行卡，5--京东钱包。默认3',
  `name` varchar(10) DEFAULT '' COMMENT '讲师姓名',
  `percent` decimal(9,2) DEFAULT '0.00' COMMENT '讲师提成，是个0-1之间的数。如果使用平台默认的讲师提成，则填-1',
  `put_home` int(1) DEFAULT '1' COMMENT '是否推荐到首页(2否 1是)',
  `remark` varchar(500) DEFAULT '' COMMENT '讲师详细介绍',
  `status` int(1) DEFAULT '1' COMMENT '状态(2 禁用,1 启用)',
  `teacher_id` varchar(32) DEFAULT '' COMMENT '讲师ID',
  `top_pic_url` varchar(300) DEFAULT '' COMMENT '讲师宣传图片访问地址(讲师详情)',
  `top_type` int(1) DEFAULT '0' COMMENT '讲师宣传图类型(0 图片,1 视频)(名人详情页)',
  `top_video_url` varchar(300) DEFAULT '' COMMENT '对应讲师宣传视频访问地址',
  `update_time` bigint(13) DEFAULT '0' COMMENT '修改时间',
  `worth` decimal(9,2) DEFAULT '0.00' COMMENT '讲师身价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for tb_teacher_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher_order`;
CREATE TABLE `tb_teacher_order` (
  `id` varchar(32) NOT NULL,
  `create_time` bigint(13) DEFAULT '0' COMMENT '创建时间，与订单的支付时间对应(不是创建时间，而是支付时间)',
  `order_number` varchar(20) DEFAULT '' COMMENT '本条收益信息所对应的订单号',
  `percent_type` int(1) DEFAULT '1' COMMENT '表示本订单中的讲师提成比例是使用了讲师自定义比例还是平台默认比例，1--讲师自定义比例，2--平台默认比例。可以反应购买时讲师有没有设置提成比例',
  `server_achieve` decimal(9,2) DEFAULT '0.00' COMMENT '本订单中平台的收益，单位：元',
  `status` int(1) DEFAULT '1' COMMENT '是否可用，1--可用，2--不可用。在计算讲师收益时需要附件这个条件判断，可以将非法订单的收益不计入讲师收益。备用字段，但是查询语句里需要这个判断',
  `sum_money` decimal(9,2) DEFAULT '0.00' COMMENT '本订单的总收益，即平台收益与讲师收益的和，对应了订单表的   平台获得金额 achieve 字段',
  `teacher_achieve` decimal(9,2) DEFAULT '0.00' COMMENT '本订单中讲师的收益，单位：元',
  `teacher_id` varchar(32) DEFAULT '' COMMENT '讲师ID，对应的是讲师表里的 teacher_id 字段',
  `teacher_percent` decimal(9,2) DEFAULT '0.00' COMMENT '本订单中讲师的提成比例，是个0-1之间数。',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_teacher_order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_teacher_send
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher_send`;
CREATE TABLE `tb_teacher_send` (
  `id` varchar(32) NOT NULL,
  `apply_admin_id` varchar(32) DEFAULT '' COMMENT '申请管理员的id',
  `apply_time` bigint(13) DEFAULT '0' COMMENT '申请时间',
  `send_money` decimal(9,2) DEFAULT '0.00' COMMENT '本次提现金额，单位：元',
  `shengyu` decimal(9,2) DEFAULT '0.00' COMMENT '本次提现后剩余的金额(这个只是一个参考值，表示发起申请时的数据信息，当审核时真正的值可能会比这个值大，因为在申请期间可能会产生新的订单)，单位：元',
  `status` int(1) DEFAULT '1' COMMENT '该申请信息的状态：1--审核中(刚刚申请)，2--通过(已经向讲师汇款完毕)，3--驳回(没有向讲师汇款)',
  `teacher_id` varchar(32) DEFAULT '' COMMENT '收款讲师的id',
  `verify_admin_id` varchar(32) DEFAULT '' COMMENT '审核管理员的id',
  `verify_time` bigint(13) DEFAULT '0' COMMENT '审核时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_teacher_send
-- ----------------------------

-- ----------------------------
-- Table structure for tb_urls
-- ----------------------------
DROP TABLE IF EXISTS `tb_urls`;
CREATE TABLE `tb_urls` (
  `id` varchar(32) NOT NULL,
  `title` varchar(500) DEFAULT '' COMMENT '接口标题',
  `url` varchar(300) DEFAULT '' COMMENT '接口地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_urls
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(32) NOT NULL,
  `ageId` varchar(32) DEFAULT '' COMMENT '年龄段ID，对应job表',
  `birthday` bigint(13) DEFAULT '0' COMMENT '生日',
  `buy_count` int(9) DEFAULT '0' COMMENT '购买视频次数',
  `continue_day` int(2) DEFAULT '0' COMMENT '连续签到天数',
  `create_time` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `district` varchar(50) NOT NULL DEFAULT '' COMMENT '注册时填写的地址，如果是第三方，则需要app端去获取用户在第三方软件上的地址，以省份开头',
  `equi_type` int(1) DEFAULT '0' COMMENT '用户最后登录设备类型(0 Android,1 IOS)',
  `gender` int(1) DEFAULT '0' COMMENT '性别 0未知,1女,2男',
  `iosCoints` decimal(9,2) DEFAULT '0.00' COMMENT 'ios币',
  `isComment` int(1) DEFAULT '1' COMMENT '1 可用,2不可评论',
  `job` varchar(20) DEFAULT '' COMMENT '职业',
  `jobId` varchar(32) DEFAULT '' COMMENT '职业ID，对应job表，方便将来批量修改职业名称',
  `latest_login_time` bigint(13) DEFAULT '0' COMMENT '最后一次登录时间(每日首次登录自动签到)',
  `latest_sign_time` bigint(13) DEFAULT '0' COMMENT '最后一次签到时间',
  `password` varchar(50) DEFAULT '' COMMENT '用户密码',
  `phone` bigint(15) NOT NULL DEFAULT '0' COMMENT '手机号',
  `pic_save_url` varchar(300) DEFAULT '' COMMENT '头像存储访问地址',
  `push_token` varchar(150) DEFAULT '' COMMENT '推送令牌',
  `ssoId` varchar(100) DEFAULT '' COMMENT '第三方登陆的唯一标识，如微信的openid',
  `update_time` bigint(13) DEFAULT '0' COMMENT '修改时间',
  `user_state` int(1) DEFAULT '1' COMMENT '用户状态(1 可用,2 拉黑)',
  `user_type` int(1) DEFAULT '0' COMMENT '用户类型(0学员,1讲师)',
  `username` varchar(50) DEFAULT '' COMMENT '用户名',
  `vchat_iden` int(1) DEFAULT '0' COMMENT 'openID(0 普通登录用户,1 微信登录用户)',
  `zodiac` varchar(5) DEFAULT '' COMMENT '星座，app不再使用，app直接用生日推算出星座',
  `zodiacIndex` int(2) DEFAULT '-1' COMMENT '星座的数字对应关系，详见静态量，没有设置生日此处为-1，表示没有星座',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_buyvideos
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_buyvideos`;
CREATE TABLE `tb_user_buyvideos` (
  `id` varchar(32) NOT NULL,
  `buyTime` bigint(13) DEFAULT '0' COMMENT '购买时间',
  `isShow` int(1) DEFAULT '1' COMMENT '是否展现在用户已购买列表(app端)',
  `orderNum` varchar(20) DEFAULT '' COMMENT '订单编号',
  `userId` varchar(32) DEFAULT '' COMMENT '用户ID',
  `videoId` varchar(32) DEFAULT '' COMMENT '视频ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_buyvideos
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_ioscoin
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_ioscoin`;
CREATE TABLE `tb_user_ioscoin` (
  `id` varchar(32) NOT NULL,
  `buyTime` bigint(13) DEFAULT '0' COMMENT '充值时间',
  `coin` decimal(9,2) DEFAULT '0.00' COMMENT '用户本次获取到的ios币数量，充值失败这里为0',
  `coinId` varchar(32) DEFAULT '' COMMENT 'IOS点券ID',
  `defeatMessage` varchar(50) DEFAULT '' COMMENT '充值失败原因，充值成功可以忽略本字段',
  `state` int(1) DEFAULT '1' COMMENT '1成功，2失败',
  `userId` varchar(32) DEFAULT '' COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_ioscoin
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_request
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_request`;
CREATE TABLE `tb_user_request` (
  `id` varchar(32) NOT NULL,
  `createTime` bigint(13) DEFAULT '0' COMMENT '创建时间：投票时间',
  `requestId` varchar(32) DEFAULT '' COMMENT '求课程表的id',
  `userId` varchar(32) DEFAULT '' COMMENT '投票者的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_request
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_survey
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_survey`;
CREATE TABLE `tb_user_survey` (
  `id` varchar(50) NOT NULL,
  `adminId` varchar(50) DEFAULT '' COMMENT '管理员名册',
  `create_time` bigint(15) DEFAULT '0' COMMENT '创建时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `userId` varchar(50) DEFAULT '' COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_survey
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_video_request
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_video_request`;
CREATE TABLE `tb_user_video_request` (
  `id` varchar(32) NOT NULL,
  `add_type` int(2) DEFAULT '2' COMMENT '是否是平台添加',
  `channel_id` varchar(32) DEFAULT '' COMMENT '频道ID，这里是指具体频道的id',
  `course_name` varchar(20) DEFAULT '' COMMENT '课程名称',
  `createdTime` bigint(13) DEFAULT '0' COMMENT '该课程制作完成时间',
  `feedback` varchar(1000) DEFAULT '' COMMENT '客服反馈',
  `feedback_status` int(2) DEFAULT '0' COMMENT '反馈状态，具体见enums里的RequestStatus',
  `feedback_time` bigint(13) DEFAULT '0' COMMENT '反馈时间',
  `from_type` int(2) DEFAULT '0' COMMENT '来源：1首页  2视频3 讲师',
  `pic_url` varchar(300) DEFAULT '' COMMENT '该求课程的封面图片地址',
  `question` longtext COMMENT '学员困惑',
  `request_time` bigint(13) DEFAULT '0' COMMENT '发起视频请求的时间',
  `share_url` varchar(300) DEFAULT '' COMMENT '分享地址',
  `teacher_id` varchar(32) DEFAULT '' COMMENT '请求讲师ID',
  `top_channel_id` varchar(32) DEFAULT '' COMMENT '频道ID，这里是指顶级频道的id',
  `user_id` varchar(32) DEFAULT '' COMMENT '用户ID',
  `virtualvote` int(9) DEFAULT '0' COMMENT '虚拟被请求教程的投票数',
  `vote` int(9) DEFAULT '0' COMMENT '被请求教程的投票数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_video_request
-- ----------------------------

-- ----------------------------
-- Table structure for tb_video
-- ----------------------------
DROP TABLE IF EXISTS `tb_video`;
CREATE TABLE `tb_video` (
  `id` varchar(32) NOT NULL,
  `canUseCoupon` int(1) DEFAULT '1' COMMENT '是否能使用优惠券(1 是 ，2 否 )',
  `course_id` varchar(32) DEFAULT '' COMMENT '关联课程详情ID',
  `create_time` bigint(13) DEFAULT '0' COMMENT '创建时间',
  `creater_id` varchar(32) DEFAULT '' COMMENT '创建人ID',
  `free` int(1) DEFAULT '2' COMMENT '是否免费(2不免费1 免费)',
  `freeTime` int(9) DEFAULT '0' COMMENT '免费观看时间，以毫秒为单位',
  `order_num` int(8) DEFAULT '0' COMMENT '视频排序(0放入课程详情页面顶部)，从大到小排序',
  `per_cost` decimal(9,2) DEFAULT '0.00' COMMENT '价格(单位:元)',
  `play_time` bigint(13) DEFAULT '0' COMMENT '上映时间',
  `remark` varchar(1000) DEFAULT '' COMMENT '视频的详细介绍，作废',
  `share_url` varchar(300) DEFAULT '' COMMENT '分享地址',
  `status` int(1) DEFAULT '0' COMMENT '是否启用(0 未上映1 即将上映2 过期的 )，已经作废',
  `time_long` int(9) DEFAULT '0' COMMENT '时长(单位:秒)',
  `title` varchar(50) DEFAULT '' COMMENT '标题',
  `update_time` bigint(13) DEFAULT '0' COMMENT '修改时间',
  `video_pic_url` varchar(300) DEFAULT '' COMMENT '课程详情页封面图片访问地址(每个视频对应各自的一个)',
  `video_save_url` varchar(300) DEFAULT '' COMMENT '视频存储访问路径',
  `video_size` varchar(100) DEFAULT '0' COMMENT '视频大小',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_video
-- ----------------------------

-- ----------------------------
-- Table structure for tb_video_standard
-- ----------------------------
DROP TABLE IF EXISTS `tb_video_standard`;
CREATE TABLE `tb_video_standard` (
  `id` varchar(32) NOT NULL,
  `correlation_id` varchar(32) DEFAULT '' COMMENT '关联id',
  `finish_time` bigint(13) DEFAULT '0' COMMENT '预计完成时间',
  `standard` int(11) DEFAULT '0' COMMENT '目标量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_video_standard
-- ----------------------------
