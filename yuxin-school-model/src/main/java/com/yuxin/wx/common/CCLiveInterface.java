package com.yuxin.wx.common;

public interface CCLiveInterface {
	/**
	 * 直播创建接口
	 */
	public static final String CREATE = "http://api.csslcloud.net/api/room/create?";
	/**
	 * 直播修改接口
	 */
	public static final String UPDATE = "http://api.csslcloud.net/api/room/update?";
	/**
	 * 直播间直播信息
	 */
	public static final String LIVE_INFO = "http://api.csslcloud.net/api/live/info?";
	/**
	 * 聊天信息
	 */
	public static final String CHAT_MSG= "http://api.csslcloud.net/api/live/chatmsg?";
	/**
	 * cc直播 userid
	 */
	public static final String USER_ID = "E0A30CADDE5B67D1";
	/**
	 * cc直播 api_key
	 */
	public static final String API_KEY = "RA6KHIa9foNJEEX5aYAZzYOyCVLjfqqs";
	/**
	 * cc直播 进入地址
	 */
	public static final String LIVE_ADDRESS = "http://api.csslcloud.net/api/room/code?";
	/**
	 * 查询并发
	 */
	public static final String CONNECTIONS = "http://api.csslcloud.net/api/statis/connections?";
	/**
	 * 关闭直播间
	 */
	public static final String CLOSE = "http://api.csslcloud.net/api/room/close?";
	/**
	 * c老师登录密码
	 */
	public static final String TEACHER_PASS = "teacher_950620";
	/**
	 * 助教登录密码
	 */
	public static final String ASSISTANT_PASS = "assistant_950620";
	/**
	 * 学员登录密码
	 */
	public static final String STUDENT_PASS = "student_950620";
}
