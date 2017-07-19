package com.yuxin.wx.common;

public interface LiveRoomConstant {
	/**
	 * E课堂创建直播教室接口
	 */
	public static final String CREATELIVEROOM = "/openApi/createLiveRoom";
	/**
	 * E课堂修改直播教室接口
	 */
	public static final String UPDATE_LIVEROOM = "/openApi/updateLiveRoom";

	public static final String LIVEDETAIL = "/openApi/statistics/total";

	public static final String LIVE_DYNAMICINFO = "/openApi/getLiveDynamicInfo";

	public static final String DELETE_LIVEROOM = "/openApi/deleteLiveRoom";

	/**
	 * 展示互动--大讲堂
	 */
	public static final String LIVE_BIG_CLASS_ROOM = "LIVE_BIG_CLASS_ROOM";

	/**
	 * 展示互动--小班课
	 */
	public static final String LIVE_SMALL_CLASS_ROOM = "LIVE_SMALL_CLASS_ROOM";

	/**
	 * 进入展示互动老师密钥
	 */
	public static final String TEACHER_TOKEN = "zs_t_secret";
	/**
	 * 进入展示互动学生密钥（web端）
	 */
	public static final String STUDENT_TOKEN_WEB = "zs_s_secret_w";//zs_s_secret_w
	/**
	 * 进入展示互动学生密钥（客户端）
	 */
	public static final String STUDENT_TOKEN_CLINT = "zs_s_secret_c";
	/**
	 * 进入展示互动助教密钥
	 */
	public static final String ASSISTANT_TOKEN = "zs_a_secret";
	/**
	 * 展示互动登陆账号
	 */
	public static final String LOGIN_NAME = "admin@winshare-edu.com";
	/**
	 * 展示互动密码
	 */
	public static final String PASSWORD = "888888";
	/**
	 * 展示互动访问路径
	 */
	public static final String DOMIN_NAME = "http://winshare-edu.gensee.com";
	/**
	 * 创建展示互动直播课程
	 */
	public static final String CREATE_ZS_LIVE_ROOM = "/integration/site/training/room/created";
	/**
	 * 修改展示互动直播课程
	 */
	public static final String MODIFY_ZS_LIVE_ROOM = "/integration/site/training/room/modify";
	/**
	 * 展视互动获得回放
	 */
	public static final String COURSEWARE = "/integration/site/training/courseware/list";
	/**
	 * 展视互动添加插播件
	 */
	public static final String CHOOSE_ADD = "/integration/site/training/record/choose";
	/**
	 * 展视互动分页同步录制件数据
	 */
	public static final String RECORD = "/integration/site/training/record/syn";
	/**
	 * 展视互动删除插播件
	 */
	public static final String RECORD_REMOVE = "/integration/site/training/record/remove";
	/**
	 * 展视互动下载聊天
	 */
	public static final String EXPORT_CHAT = "/integration/site/training/export/chat";
}
