package com.yuxin.wx.common;

public interface payConstant {
	/**订单状态--未支付**/
	public static final String PAY_STATUS_NON = "PAY_NON";
	/**订单状态--支付成功**/
	public static final String PAY_STATUS_SUCCESS = "PAY_SUCCESS";
	/**订单状态--支付失败**/
	public static final String PAY_STATUS_FAIL = "PAY_FAIL";
	/**支付类型**/
	public static final String PAY_TYPE_ZFB = "PAY_TYPE_ZFB";
	
	/** 订单业务类型--新报名   *//*
	public static final String ORDER_BIZ_NEW_ORDER = "ORDER_BIZ_NEW_ORDER";
	*//** 订单业务类型--转班型   *//*
	public static final String ORDER_BIZ_CHANGE_PRODUCT = "ORDER_BIZ_CHANGE_PRODUCT";
	*//** 订单业务类型--转人   *//*
	public static final String ORDER_BIZ_CHANGE_STU = "ORDER_BIZ_CHANGE_STU";
	*//** 订单渠道--后台报名   *//*
	public static final String CHANNEL_OFFLINE = "CHANNEL_OFFLINE";
	*//** 订单渠道--在线报名   *//*
	public static final String CHANNEL_ONLINE = "CHANNEL_ONLINE";
	
	*//** 付款方式--全款   *//*
	public static final String PAY_TYPE_ALL = "PAY_TYPE_ALL";
	*//** 付款方式--贷款   *//*
	public static final String PAY_TYPE_CREDIT = "PAY_TYPE_CREDIT";
	*//** 付款方式--分期付款   *//*
	public static final String PAY_TYPE_STAGE = "PAY_TYPE_STAGE";
	
	*//** 授课方式--面授  *//*
	public static final String TEACH_METHOD_FACE = "TEACH_METHOD_FACE";
	*//** 授课方式--直播  *//*
	public static final String TEACH_METHOD_LIVE = "TEACH_METHOD_LIVE";
	*//** 授课方式--视频  *//*
	public static final String TEACH_METHOD_VIDEO = "TEACH_METHOD_VIDEO";
	*//** 授课方式--远程  *//*
	public static final String TEACH_METHOD_REMOTE = "TEACH_METHOD_REMOTE";
	
	*//** 子订单状态--已创建  *//*
	public static final String SUB_ORDER_CREATED = "SUB_ORDER_CREATED";
	*//** 子订单状态--已作废 *//*
	public static final String SUB_ORDER_DELTEd = "SUB_ORDER_DELTEd";
	*//** 子订单状态--已完成  *//*
	public static final String SUB_ORDER_FINISHED = "SUB_ORDER_FINISHED";
	
	*//** 主订单状态--已创建 *//*
	public static final String ORDER_CREATED = "ORDER_CREATED";
	*//** 主订单状态--部分支付 *//*
	public static final String ORDER_PART_PAY = "ORDER_PART_PAY";
	*//** 主订单状态--已支付 *//*
	public static final String ORDER_PAID = "ORDER_PAID";
	
	
	*//**session检查例外列表**//*
	final static String[] SESSION_FILTER_EXCEPTION={
		
	};
	final static String[] RESOURCE_TYPE={
		".png",
		".jpg",
		".jpeg",
		".gif",
		".bmp",
		".ico",
		".tff",
		".css",
		".js",
		".html",
		".pdf"
	};*/
	
}
