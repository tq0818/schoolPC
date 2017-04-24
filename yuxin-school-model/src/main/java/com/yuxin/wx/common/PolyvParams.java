package com.yuxin.wx.common;

public interface PolyvParams {
	
	/**
	 * 保利userid
	 */
	public static final String USER_ID = "0d86c4994e";
	/**
	 * 保利writeToken
	 */
	public static final String WRITE_TOKEN = "7091e39b-274a-4c74-8ed4-7ae8132b495f";
	/**
	 * 保利secretKey
	 */
	public static final String SECRET_KEY = "mcgOAlviN1";
	/**
	 * 读取钥匙
	 */
	public static final String READ_TOKEN = "a18d6fab-4b4e-461a-95a9-508c11f6da10";
	/**
	 * 查询使用情况
	 */
	public static final String GET_USE_INFO = "http://api.polyv.net/v2/user/{userid}/main";
	/**
	 * 查询单个视频信息
	 */
	public static final String GET_SINGLE_VIDEO = "http://v.polyv.net/uc/services/rest?method=getById";
	/**
	 * 获取视频流量大小
	 */
	public static final String GET_VIDEO_LOG = "http://api.polyv.net/v2/data/{userid}/viewlog";
	/**
	 * 删除视频
	 */
	public static final String DELETE_VIDEO = "http://v.polyv.net/uc/services/rest?method=delVideoById";
}
