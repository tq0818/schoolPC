package com.yuxin.wx.common;

public interface QNInterface {
	
	/**
	 * https post<br>
	 * 七牛接口域名<br>
	 * acc.qbox.me<br>
	 */
	static final String QN_CREATE_DOMAIN = "https://acc.qbox.me";
	/**
	 * http get<br>
	 * 七牛查询信息接口<br>
	 * api.qiniu.com<br>
	 */
	static final String QN_SELECT_DOMAIN = "http://api.qiniu.com";
	/**
	 * https get<br>
	 * 账户月度使用信息<br>
	 * /stat/info<br>
	 * uid 用户id<br>
	 * month 月份 格式为 201608
	 */
	static final String QN_STAT_INFO = "/stat/info";
	/**
	 * https post<br>
	 * 用户信息<br>
	 * /user/info<br>
	 * AccessToken 前面生成
	 */
	static final String QN_USER_INFO = "/user/info";
	/**
	 * https post<br>
	 * 创建子账号<br>
	 * /user/create_child<br>
	 * email 子账号邮箱<br>
	 * password 密码 
	 */
	static final String QN_USER_CREATE_CHILD = "/user/create_child";
	/**
	 * https post<br>
	 * 获取access_token<br>
	 * /oauth2/token<br>
	 * grant_type 固定  password<br>
	 * username 父账号 邮箱<br>
	 * password 密码
	 */
	static final String QN_AUTH_TOKEN = "/oauth2/token";
	/**
	 * https post<br>
	 * 禁用子账号<br>
	 * /user/disable_child<br>
	 * uid 子账号id<br>
	 * reason 禁用理由
	 */
	static final String QN_USER_DISABLE_CHILD = "/user/disable_child";
	/**
	 * https post<br>
	 * 启用子账号<br>
	 * /user/enable_child<br>
	 * uid 子账号id
	 */
	static final String QN_USER_ENABLE_CHILD = "/user/enable_child";
	/**
	 * 创建bucket<br>
	 * http://rs.qiniu.com/mkbucket2/{bucketName}/public/{0,1} <br>
	 * bucketName <br>
	 * public  0:私有  1:共有  (可省略,默认私有)
	 */
	static final String QN_CREATE_BUCKET = "http://rs.qiniu.com/mkbucket2";
	/**
	 * 查询子账号秘钥<br>
	 * /user/child_key<br>
	 * https get<br>
	 * uid 子账户id
	 */
	static final String QN_USER_CHILD_KEY = "/user/child_key";
	/**
	 * 查询bucket域名<br>
	 * /v6/domain/list<br>
	 * http get<br>
	 * tbl bucketName
	 */
	static final String QN_BUCKET_DOMAIN = "/v6/domain/list";
	
	static final String QN_BUCKET_LIST = "http://rs.qbox.me/buckets";
}
