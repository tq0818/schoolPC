package com.yuxin.wx.api.weixin;
import java.io.InputStream;

import com.alibaba.fastjson.JSONObject;

public interface IWeiXinService {

	/**
	 * 微信获取TOKE
	 * @return
	 */
	public String wxGetToken(String weixinBaseUrl,String weixinAppId,String weixinSecret);
	
	
	/**
	 * 微信发送模版消息
	 * @param token
	 * @param template
	 * @return
	 */
	public String wxSendTemplate(String token,String openId,String template,JSONObject paramsJson,String weixinBaseUrl)  throws Exception ;
	
    /**
     * 接收其他消息
     * @param is
     * @return
     * @throws Exception
     */
	public String wxReceiveMsg(InputStream is)  throws Exception;
	
	/**
	 * 微信TOKEN验证
	 * @return
	 */
	public String wxValidateToken(String signature,String timestamp,String nonce,String echostr);
}
