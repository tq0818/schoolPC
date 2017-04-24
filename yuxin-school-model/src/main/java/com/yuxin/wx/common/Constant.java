package com.yuxin.wx.common;

public interface Constant {
	
	/**
	 * 删除标识符 1 没有删除 0已删除
	 */
	public static final short NO_DELTE_FLAG = 1;
	public static final short HAS_DELTE_FLAG = 0;
	
	public static final String MODULE_NO_CREATED = "MODULE_NO_CREATED";  //班号状态，新创建
	
	public static final String PERSON_TEACHER = "PERSON_TEACHER";   //老师类型：老师
	
	public static final String TEACHER_USERD = "TEACHER_USERD"; //老师状态: 正常
	
	public static final String PERSON_ASSISTANT = "PERSON_ASSISTANT"; //教务
	
	public static final String COURSE_VIDEO_UNPUBLISHED = "COURSE_VIDEO_UNPUBLISHED";  //录播课状态：未发布
	
	public static final String COURSE_VIDEO_DISABLE = "COURSE_VIDEO_DISABLE";  //录播课状态：已停用
	
	// 课程分类, 依次为：  面授、直播、视频
	public static final String TEACH_METHOD_FACE = "TEACH_METHOD_FACE";
	
	public static final String TEACH_METHOD_LIVE = "TEACH_METHOD_LIVE";
	
	public static final String TEACH_METHOD_VIDEO = "TEACH_METHOD_VIDEO";
	
	/** 授课方式--远程  */
	public static final String TEACH_METHOD_REMOTE = "TEACH_METHOD_REMOTE";
	
	//视频上传的状态
	public static final String VIDEO_PROCESS_NOMAL = "VIDEO_PROCESS_NOMAL";   //正常状态
	
	public static final String VIDEO_PROCESS_INHAND = "VIDEO_PROCESS_INHAND";  //处理中
	
	public static final String VIDEO_PROCESS_UPLOAD = "VIDEO_PROCESS_UPLOAD";  //上传中
	
	public static final String VIDEO_PROCESS_FAIL = "VIDEO_PROCESS_FAIL";  //处理失败
	
	public static final String VIDEO_PROCESS_DELETE = "VIDEO_PROCESS_DELETE";  //屏蔽
	
	/**订单状态--未支付**/
	public static final String PAY_STATUS_NON = "PAY_NON";
	/**订单状态--支付成功**/
	public static final String PAY_STATUS_SUCCESS = "PAY_SUCCESS";
	/**订单状态--支付失败**/
	public static final String PAY_STATUS_FAIL = "PAY_FAIL";
	/**支付类型**/
	public static final String PAY_TYPE_ZFB = "PAY_TYPE_ZFB";
	/** 支付宝担保交易*/
	public static final String PAY_TYPE_GRDB = "PAY_TYPE_GRDB";
	/** 个人转账--支付宝 */
	public static final String PAY_TYPE_ZFB_ZZ = "PAY_TYPE_ZFB_ZZ";
	/** 个人转账--微信 */
	public static final String PAY_TYPE_WX_PERSON = "PAY_TYPE_WX_PERSON";
	/** 个人转账--银行转账 */
	public static final String PAY_TYPE_REMIT = "PAY_TYPE_REMIT";
	
	/** 订单业务类型--新报名   */
	public static final String ORDER_BIZ_NEW_ORDER = "ORDER_BIZ_NEW_ORDER";
	/** 订单业务类型--转班型   */
	public static final String ORDER_BIZ_CHANGE_PRODUCT = "ORDER_BIZ_CHANGE_PRODUCT";
	/** 订单业务类型--转人   */
	public static final String ORDER_BIZ_CHANGE_STU = "ORDER_BIZ_CHANGE_STU";
	/** 订单渠道--后台报名   */
	public static final String CHANNEL_OFFLINE = "CHANNEL_OFFLINE";
	/** 订单渠道--在线报名   */
	public static final String CHANNEL_ONLINE = "CHANNEL_ONLINE";
	
	/** 主订单状态--已创建 */
	public static final String ORDER_CREATED = "ORDER_CREATED";
	/** 主订单状态--部分支付 */
	public static final String ORDER_PART_PAY = "ORDER_PART_PAY";
	/** 主订单状态--已支付 */
	public static final String ORDER_PAID = "ORDER_PAID";
	/** 主订单状态--在线支付未排课 */
	public static final String ORDER_PAID_NO_COURSE = "ORDER_PAID_NO_COURSE";
	
	/** 子订单状态--已创建  */
	public static final String SUB_ORDER_CREATED = "SUB_ORDER_CREATED";
	/** 子订单状态--已作废 */
	public static final String SUB_ORDER_DELTEd = "SUB_ORDER_DELTEd";
	/** 子订单状态--已完成  */
	public static final String SUB_ORDER_FINISHED = "SUB_ORDER_FINISHED";
	
	/** 付款方式--全款   */
	public static final String PAY_TYPE_ALL = "PAY_TYPE_ALL";
	/** 付款方式--贷款   */
	public static final String PAY_TYPE_CREDIT = "PAY_TYPE_CREDIT";
	/** 付款方式--分期付款   */
	public static final String PAY_TYPE_STAGE = "PAY_TYPE_STAGE";
	
	/** E课堂模板有在线人数  */
	public static final String EKETANG_TEMPALATE_TAOBAO ="EKETANG_TEMPALATE_TAOBAO";
	/** E课堂模板无在线人数  */
	public static final String EKETANG_TEMPALATE_SOOONER = "EKETANG_TEMPALATE_SOOONER";
	
	/**
	 * e课堂收费账号
	 */
	public static final String CHARGE_EKETANG_USER = "yxkj";
	/**
	 * e课堂免费账号
	 */
	public static final String FREE_EKETANG_USER = "yxkj1";
	/**
	 * e课堂收费账号的secretKey
	 */
	public static final String CHARGE_EKETANG_SECRETKEY = "9bb8237effe1edbe71662104f83c0c70";
	/**
	 * e课堂免费账号的secretKey
	 */
	public static final String FREE_EKETANG_SECRETKEY = "7d09a20a466b93a0e8353b5ae457980b";
	/**
	 * e课堂创建课程服务地址
	 */
	public static final String EKETANG_SERVER_ADDRESS = "http://openapi.live.soooner.com";
	
	/** 初始化数据公司id */
	public static final Integer COMPANY_INIT_DATA = 17358;
	
}