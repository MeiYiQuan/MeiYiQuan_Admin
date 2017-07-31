package com.neiquan.meiyiquan.util;

/**
 * 作者：齐潮
 * 创建日期：2016年12月6日
 * 类说明：存储项目下的静态量
 */
public class Statics {

	/**
	 * 用于信息发送时表示userId的字段名字
	 */
	public final static String SIGN_USER_ID_NAME = "userId";
	
	/**
	 * 用于信息发送时表示token的字段名字
	 */
	public final static String SEND_USER_TOKEN_NAME = "token";
	
	/**
	 * 用于信息发送时表示签名的字段名字
	 */
	public final static String SEND_SIGN_NAME = "sign";
	
	/**
	 * 用于信息发送时表示时间戳的字段名字
	 */
	public final static String SEND_TIMESTAMP_NAME = "timestamp";
	
	/**
	 * 生成签名时，如果没有查到用户的token，则默认使用的token(保证了生成签名时一定有值)
	 */
	public final static String SIGN_USER_DEFAULT_TOKEN = "token";
	
	/**
	 * 生成签名时，如果不需要用户id的签名时使用这个默认的userId
	 */
	public final static String SIGN_USER_DEFAULT_ID = "userDefaultId";
	
	/**
	 * 与app端约定好的双方的通信秘钥，用于DES加密(长度必须是8的倍数)
	 */
	public final static String SEND_DES_KEY = "dfgrgfdsf12dfg41";
	
	/**
	 * 生成签名时双方约定的key
	 */
	public final static String SIGN_HEAD = "ghtrcvbdfh";
	
	/**
	 * 发送信息的存储字段
	 */
	public final static String SEND_HEAD = "content";
	
	/**
	 * 从app端发送过来的信息有效期，单位为毫秒
	 */
	public final static int SEND_MAX_TIME = 30 * 1000;
	
	/**
	 * 后台管理系统专用的reids里的用于存储token的key
	 */
	public final static String CMS_REDIS_TOKEN_NAME = "cmstoken";
	
	/**
	 * 用于表示父级菜单的parentId值，某个菜单的parentId如果不等于这个值，则表示是子菜单
	 */
	public final static String TOPMENU_PARENTID = "top";
	
	/**
	 * 排序规则：不使用排序(为null或者这个值的时候不使用排序)
	 */
	public final static String ORDER_NOORDER = "0";
	
	/**
	 * 排序规则：升序排序
	 */
	public final static String ORDER_ASC = "1";
	
	/**
	 * 排序规则：降序排序
	 */
	public final static String ORDER_DESC = "2";
	
	/**
	 * 页面上默认显示数据的条数
	 */
	public final static int EACHROWS_DEFAULT = 10;
	
	/**
	 * 含义：是，已经做了的，启用，已读
	 */
	public final static int YES_INT = 1;
	/**
	 * 含义：否，没有做了的，禁用，未读
	 */
	public final static int NO_INT = 2;
	
	/**
	 * 系统常量类型：图片
	 */
	public static final int SYSTEM_TYPE_PHOTO = 1;
	
	/**
	 * 系统常量类型：数值
	 */
	public static final int SYSTEM_TYPE_NUMBER = 2;
	
	/**
	 * 系统常量key值：推送完毕生成系统信息时的图片地址
	 */
	public static final String SYSTEM_KEY_PUSH_SYSPHOTO = "push_systeminfo_photo";
	
	/**
	 * 系统常量key值：讲师从订单收益中抽取的百分比。当讲师没有设置抽取百分比时(对应字段值为-1表示没有设置)，选用这个默认字段
	 */
	public static final String SYSTEM_KEY_TEACHER_DFAULT_PERCENT = "teacher_order_default_percent";
	
	/**
	 * 系统常量key值：当后台管理系统没有给视频设置免费观看时长时，免费观看时长取下面的字段
	 */
	public static final String SYSTEM_KEY_VIDEO_FREESHOW_DEFAULTTIME = "video_freeshow_defaulttime";
	/**
	 * 系统常量key值：用于表示求课程背景图片的key字段
	 */
	public static final String SYSTEM_KEY_REQUEPICURL = "user_video_request_pic_url";
	/**
	 * 系统常量key值：用于表示添加求课程时背景图片的url
	 */
	public static final String SYSTEM_KEY_REQUEST_BACKPIC = "request_background_pic";

	
	/**
	 * 一级区域
	 */
	public final static int DISTRICT_TYPE_ONE =0;
	/**
	 * 二级区域
	 */
	public final static int DISTRICT_TYPE_TWO =2;
	/**
	 * 三级区域
	 */
	public final static int DISTRICT_TYPE_THERE =3;
	/**
	 * 反馈类别：视频相关
	 */
	public final static int SUGGESTION_VOID= 1;
	/**
	 * 反馈类别：活动相关
	 */
	public final static int SUGGESTION_ACTIVE= 2;
	/**
	 * 反馈类别：软件bug相关
	 */
	public final static int SUGGESTION_BUG= 3;
	/**
	 * 反馈类别：讲师相关
	 */
	public final static int SUGGESTION_TEACHER= 4;
	/**
	 * 反馈类别：普通用户相关
	 */
	public final static int SUGGESTION_PEOPLE= 5;
	/**
	 * 反馈类别：支付相关
	 */
	public final static int SUGGESTION_PAY= 6;
	/**
	 * 反馈类别： 其他相关
	 */
	public final static int SUGGESTION_OTHER= 7;


	/**
	 * 用户点击我的收藏：活动
	 */
	public final static int COLLECT_TYPE_HD = 1;
	
	/**
	 * 用户点击我的收藏：名人大咖
	 */
	public final static int COLLECT_TYPE_MRDK = 2;
	
	/**
	 * 用户点击我的收藏：视频
	 */
	public final static int COLLECT_TYPE_SP = 3;
	
	/**
	 * 表示点倒赞
	 */
	public final static int LIKE_NOT = 1;
	
	/**
	 * 表示点正赞
	 */
	public final static int LIKE_YES = 0;
	
	/**
	 * 统计类型：课程
	 */
	public final static int STATICS_TYPE_KC = 1;
	
	/**
	 * 统计类型：活动
	 */
	public final static int STATICS_TYPE_HD = 2;
	
	/**
	 * 统计类型：视频
	 */
	public final static int STATICS_TYPE_SP = 3;
	
	/**
	 * 统计类型：讲师
	 */
	public final static int STATICS_TYPE_JS = 4;
	
	/**
	 * 活动宣传类型：图片
	 */
	public final static int ACTIVITY_SHOWTYPE_PIC = 0;
	
	/**
	 * 活动宣传类型：视频
	 */
	public final static int ACTIVITY_SHOWTYPE_VIDEO = 1;
	
	/**
	 * 课程宣传类型：图片
	 */
	public final static int COURSE_SHOWTYPE_PIC = 0;
	
	/**
	 * 课程宣传类型：视频
	 */
	public final static int COURSE_SHOWTYPE_VIDEO = 1;
	
	/**
	 * 请求活动类型：全国
	 */
	public final static int ACTIVITY_REQUEST_ALL = 1;
	
	/**
	 * 请求活动类型：地区
	 */
	public final static int ACTIVITY_REQUEST_DISTRICT = 2;
	
	/**
	 * 请求活动类型：已结束
	 */
	public final static int ACTIVITY_REQUEST_ENDED = 3;
	
	/**
	 * 正在热播：热播榜
	 */
	public final static int COURSE_BEINGHIT_LOOKING = 1;
	
	/**
	 * 正在热播：热销榜
	 */
	public final static int COURSE_BEINGHIT_BUY = 2;
	
	/**
	 * 正在热播：热评榜
	 */
	public final static int COURSE_BEINGHIT_COMMENT = 3;
	
	/**
	 * 推送类型：即时(通过数据库查出push信息，信息灵活性低)
	 */
	public final static int PUSH_TYPE_FORTHWITH_TABLE = 1;
	
	/**
	 * 推送类型：即时(项目中自定义的推送信息，不需要查询数据库，信息灵活性高)
	 */
	public final static int PUSH_TYPE_FORTHWITH_NOTABLE = 2;
	
	/**
	 * 推送类型：后台人为推送(只能通过push对象来推送，查询数据库)
	 */
	public final static int PUSH_TYPE_MANMADE = 3;
	
	/**
	 * 推送id：表示视频点赞时通知该视频的讲师时需要发送的推送消息
	 */
	public final static String PUSH_ID_VIDEO_DIANZAN = "push_video_dianzan";
	
	/**
	 * 推送id：表示视频购买成功时向视频发布者推送的购买提醒
	 */
	public final static String PUSH_ID_VIDEO_BUY = "push_video_buy";
	
	/**
	 * 推送标题：当推送类型为PUSH_TYPE_FORTHWITH_NOTABLE时，生成系统信息时的标题
	 */
	public final static String PUSH_TITLE_DEFAULT = "系统自定义推送";
	
	/**
	 * 首页推荐类型：推荐视频
	 */
	public final static int HOMEPAGE_TYPE_VIDEO = 1;
	
	/**
	 * 首页推荐类型：推荐名人大佬
	 */
	public final static int HOMEPAGE_TYPE_CELEBRITY = 2;
	
	/**
	 * 首页推荐类型：推荐开店创业
	 */
	public final static int HOMEPAGE_TYPE_SHOP = 3;
	
	/**
	 * 首页推荐类型：推荐潮流技术
	 */
	public final static int HOMEPAGE_TYPE_TECHNOLOGY = 4;
	
	/**
	 * 轮播图关联类型：讲师
	 */
	public final static int BANNER_TYPE_TEACHER = 1;
	
	/**
	 * 轮播图关联类型：视频
	 */
	public final static int BANNER_TYPE_VIDEO = 0;
	
	/**
	 * 轮播图关联类型：活动
	 */
	public final static int BANNER_TYPE_ACTIVITY = 2;
	
	/**
	 * 轮播图展示位置：首页
	 */
	public final static int BANNER_SHOWTYPE_HOME = 1;
	
	/**
	 * 轮播图展示位置：发现
	 */
	public final static int BANNER_SHOWTYPE_FIND = 2;
	
	/**
	 * 课程所属频道：创业开店
	 */
	public final static int COURSE_CHANNEL_CYKD = 1;
	
	/**
	 * 课程所属频道：学潮流技术
	 */
	public final static int COURSE_CHANNEL_XCLJS = 2;
	
	/**
	 * 课程所属频道：不放入频道
	 */
	public final static int COURSE_CHANNEL_NONE = 0;
	
	/**
	 * 课程类型：平台直接制作-即将上映
	 */
	public final static int COURSE_PLAYING_WILLSHOW = 0;
	
	/**
	 * 课程类型：平台直接制作-已经上映
	 */
	public final static int COURSE_PLAYING_SHOW = 1;
	
	/**
	 * 课程类型：由求课程转变而来-已经上映
	 */
	public final static int COURSE_PLAYING_REQUESTTYPE = 2;
	
	/**
	 * 参与活动的身份：学员，普通
	 */
	public final static int ACTIVITY_USER_TYPE_STUDENT = 0;
	
	/**
	 * 参与活动的身份：讲师
	 */
	public final static int ACTIVITY_USER_TYPE_TEACHER = 1;
	
	/**
	 * 参与活动的身份：嘉宾
	 */
	public final static int ACTIVITY_USER_TYPE_TOP = 2;
	
	/**
	 * 优惠券发放类型：系统自动发放
	 */
	public final static int COUPON_PROVIDE_TYPE_SYSTEM = 1;
	
	/**
	 * 优惠券发放类型：通过后台管理系统人为发放
	 */
	public final static int COUPON_PROVIDE_TYPE_ADMIN = 0;
	
	/**
	 * 优惠券类型：抵用券
	 */
	public final static int COUPON_TYPE_VOUCHER = 0;
	
	/**
	 * 优惠券类型：打折券
	 */
	public final static int COUPON_TYPE_DISCOUNT = 1;
	
	/**
	 * 优惠券有效期类型：按月计算
	 */
	public final static int COUPON_EXPIRE_TYPE_MONTH = 1;
	
	/**
	 * 优惠券有效期类型：按天计算
	 */
	public final static int COUPON_EXPIRE_TYPE_DAY = 2;
	
	/**
	 * 优惠券有效期类型：永久
	 */
	public final static int COUPON_EXPIRE_TYPE_FOREVER = 3;
	
	/**
	 * redis中用于表示存储app端用户token的hash值
	 */
	public final static String REDIS_TOKEN_HASH = "apptokens";
	
	/**
	 * 评论级别：一级
	 */
	public final static int COMMENT_LEVEL_ONE = 1;
	
	/**
	 * 评论级别：二级
	 */
	public final static int COMMENT_LEVEL_TWO = 2;
	
	/**
	 * 评论类型：视频
	 */
	public final static int COMMENT_TYPE_VIDEO = 1;
	
	/**
	 * 评论类型：活动
	 */
	public final static int COMMENT_TYPE_ACTIVITY = 2;
	
	/**
	 * 评论类型：求课程
	 */
	public final static int COMMENT_TYPE_REQUEST = 3;
	
	/**
	 * 评论类型：讲师
	 */
	public final static int COMMENT_TYPE_TEACHER = 4;
	
	/**
	 * 评论类型：课程(即将上映)
	 */
	public final static int COMMENT_TYPE_COURSE = 5;
	
	/**
	 * 点赞类型：讲师
	 */
	public final static int LIKE_TYPE_TEACHER = 4;
	
	/**
	 * 点赞类型：评论
	 */
	public final static int LIKE_TYPE_COMMENT = 2;
	
	/**
	 * 点赞类型：活动
	 */
	public final static int LIKE_TYPE_ACTIVITY = 3;
	
	/**
	 * 点赞类型：课程
	 */
	public final static int LIKE_TYPE_COURSE = 1;
	
	/**
	 * 订单类型：视频
	 */
	public final static int ORDER_TYPE_VIDEO = 1;
	
	/**
	 * 订单类型：活动
	 */
	public final static int ORDER_TYPE_ACTIVITY = 2;
	
	/**
	 * 订单：活动类型的订单的有效时长，单位毫秒，主要是charge中要用，对活动查询的时候也需要用
	 */
	public final static long ORDER_ACTIVITY_EXTIME = 1000 * 60 * 1;
	
	/**
	 * 订单：活动类型的订单的有效时长缓冲时间，单位毫秒。本项目的有效时长比ping++的有效时长多出来的时间
	 */
	public final static long ORDER_ACTIVITY_MOREEXTIME = 1000 * 30;
	
	/**
	 * 将要发送优惠券的状态：刚申请
	 */
	public final static int COUPON_WILLSEND_TYPE_WILL = 1;
	
	/**
	 * 将要发送优惠券的状态：审核通过
	 */
	public final static int COUPON_WILLSEND_TYPE_YES = 2;
	
	/**
	 * 将要发送优惠券的状态：审核不通过
	 */
	public final static int COUPON_WILLSEND_TYPE_NO = 3;
	
	/**
	 * 讲师收益表：表示使用平台默认的订单收益抽取比例
	 */
	public final static int TEACHER_ORDER_PERCENT_TYPE_DEFAULT = 2;
	
	/**
	 * 讲师收益表：表示使用讲师自定义的订单收益抽取比例
	 */
	public final static int TEACHER_ORDER_PERCENT_TYPE_CUSTOM = 1;
	
	/**
	 * 讲师提现表：提现状态，刚刚申请，审核中
	 */
	public final static int TEACHER_SEND_STATUS_WILL = 1;
	
	/**
	 * 讲师提现表：提现状态，通过
	 */
	public final static int TEACHER_SEND_STATUS_OK = 2;
	
	/**
	 * 讲师提现表：提现状态，驳回
	 */
	public final static int TEACHER_SEND_STATUS_NO = 3;
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 统计数据——连续签到天数
	 */
	public final static String STATISTICS_SIGNDAYS = "signdays";
	/**
	 * 统计数据——优惠券数量
	 */
	public final static String STATISTICS_COUPON_COUNT = "coupon_count";
	/**
	 * 统计数据——点击量
	 */
	public final static String STATISTICS_CLICK_COUNT = "click_count";
	/**
	 * 统计数据——收藏量
	 */
	public final static String STATISTICS_COLLECT_COUNT = "collect_count";
	/**
	 * 统计数据——点赞量
	 */
	public final static String STATISTICS_LIKE_COUNT = "like_count";
	/**
	 * 统计数据——倒赞量
	 */
	public final static String STATISTICS_DISLIKE_COUNT = "dislike_count";
	
	
	
	/**
	 * 统计数据——评论量
	 */
	public final static String STATISTICS_COMMENT_COUNT = "comment_count";
	/**
	 * 统计数据——分享量
	 */
	public final static String STATISTICS_SHARE_COUNT = "share_count";
	/**
	 * 统计数据——播放量
	 */
	public final static String STATISTICS_PLAY_COUNT = "play_count";
	/**
	 * 统计数据——关注量
	 */
	public final static String STATISTICS_FOLLOW_COUNT = "follow_count";
	/**
	 * 求教程来源首页
	 */
	public final static int COURSE_QUESTION_HOMEPAGE=1;
	/**
	 * 求教程来源视频
	 */
	public final static int COURSE_QUESTION_VOID=2;
	/**
	 * 求教程来源讲师
	 */
	public final static int COURSE_QUESTION_TEACHER=3;
	/**
	 * 系统常量key值：用于表示关于我们的介绍信息
	 */
	public static final String SYSTEM_KEY_ABOUTUS = "system_about_us";
	/**
	 * 系统常量类型：文本类型
	 */
	public static final int SYSTEM_TYPE_TEXT = 4;
	/**
	 * 星座静态常量：摩羯座 1
	 */
	public static final int ZODIAC_CAP=1;
	/**
	 * 星座静态常量：水瓶座 2
	 */
	public static final int ZODIAC_AGR=2;
	/**
	 * 星座静态常量：双鱼座 3
	 */
	public static final int ZODIAC_PSC=3;
	/**
	 * 星座静态常量：白羊座 4
	 */
	public static final int ZODIAC_ARI=4;
	/**
	 * 星座静态常量：金牛座 5
	 */
	public static final int ZODIAC_TAU=5;
	/**
	 * 星座静态常量：双子座 6
	 */
	public static final int ZODIAC_GEM=6;
	/**
	 * 星座静态常量：巨蟹座 7
	 */
	public static final int ZODIAC_CNC=7;
	/**
	 * 星座静态常量：狮子座 8
	 */
	public static final int ZODIAC_LEO=8;
	/**
	 * 星座静态常量：处女座 9
	 */
	public static final int ZODIAC_VIR=9;
	/**
	 * 星座静态常量：天平座 10
	 */
	public static final int ZODIAC_LIB=10;
	/**
	 * 星座静态常量：天蝎座 11
	 */
	public static final int ZODIAC_SCO=11;
	/**
	 * 星座静态常量：射手座 12
	 */
	public static final int ZODIAC_SGR=12;
	/**
	 * job表类型：1 职业
	 */
	public static final int JOB_TYPE_JOB=1;
	/**
	 * job表类型：2 讲师等级
	 */
	public static final int JOB_TYPE_TEACHER=2;
	/**
	 * job表类型：3 年龄
	 */
	public static final int JOB_TYPE_AGE=3;
	/**
	 * 系统常量key值：用于表示苹果内购方式的税率，是个小于1的数
	 */
	public static final String SYSTEM_KEY_IOS_BUY_TAX = "order_tax_ios";
	
	/**
	 * 系统常量key值：用于表示普通购买方式的税率，是个小于1的数
	 */
	public static final String SYSTEM_KEY_BUY_TAX = "order_tax";
	
	/**
	 * 系统常量key值：用于表示苹果内购方式苹果官方扣除比例，是个小于1的数
	 */
	public static final String SYSTEM_KEY_IOS_BUY = "order_ios";
	/**
	 * 播放节点类型  1 一分钟内
	 */
	public static final String PLAYRECORD_TYPE_ONE="1";
	/**
	 * 播放节点类型  2 一到五分钟
	 */
	public static final String PLAYRECORD_TYPE_TWO="2";
	/**
	 * 播放节点类型  3 五到十分钟
	 */
	public static final String PLAYRECORD_TYPE_THE="3";
	/**
	 * 播放节点类型  4十到二十分钟
	 */
	public static final String PLAYRECORD_TYPE_FOR="4";
	/**
	 * 播放节点类型  5二十分钟以上
	 */
	public static final String PLAYRECORD_TYPE_FIV="5";

			 
	
	
}
