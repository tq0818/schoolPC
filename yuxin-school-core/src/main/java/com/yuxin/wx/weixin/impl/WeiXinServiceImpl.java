package com.yuxin.wx.weixin.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.weixin.IWeiXinService;
import com.yuxin.wx.common.SignUtils;
import com.yuxin.wx.util.JedisUtil;
import com.yuxin.wx.vo.weichat.WeichatAccessToken;

@Service("weiXinService")
public class WeiXinServiceImpl implements IWeiXinService{

	private Log log = LogFactory.getLog("WeiXinServiceImpl");

	
	
	@Override
	public String wxGetToken(String weixinBaseUrl,String weixinAppId,String weixinSecret) {
		 String url = weixinBaseUrl + "/token";
		 	String grantType = "client_credential";
		 	String token = "";
		 	String redisToken = JedisUtil.getString("weChatAccessToken");
		    if(StringUtils.isNotBlank(redisToken) && StringUtils.isNotBlank(JSONObject.parseObject(redisToken,WeichatAccessToken.class ).getAccess_token())){
		    	token = JSONObject.parseObject(redisToken,WeichatAccessToken.class ).getAccess_token();
		    }else{
		    	MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
				param.add("grant_type", grantType);
				param.add("appid", weixinAppId);
				param.add("secret", weixinSecret);
				String json =  new RestTemplate().postForObject(url,param,String.class);
				WeichatAccessToken accessToken = JSONObject.parseObject(json,WeichatAccessToken.class);
	            JedisUtil.put("weChatAccessToken", JSONObject.toJSONString(accessToken),3600);
		    }
		    log.info("weixin request token :"+token);
			return token;
	}

	@Override
	public String wxSendTemplate(String token, String openId, String template,JSONObject paramsJson,String weixinBaseUrl) throws Exception {
			
		String result = "failed";
		String url = weixinBaseUrl +"/message/template/send?access_token="+token;
		JSONObject msgJson = JSONObject.parseObject(template);
		String redirectUrl = paramsJson.getString("url");
		paramsJson.remove("url");
		msgJson.put("url", redirectUrl);
		msgJson.put("touser", openId);
		JSONObject dataJson = msgJson.getJSONObject("data");
		Iterator it = dataJson.keySet().iterator();
		while(it.hasNext()){
			 String key = it.next().toString();
			 JSONObject json = dataJson.getJSONObject(key);
			 String value = paramsJson.getString(key);
			 if(StringUtils.isNotBlank(value)){
				 json.put("value", value); 
			 }
						 
		}
		String msg = new String(msgJson.toString().getBytes("UTF-8"),"iso-8859-1");
		log.info("weixin send template msg content  iso-8859-1:"+msg);
		String json =  new RestTemplate().postForObject(url,msg,String.class);
	    if(StringUtils.isNotBlank(json)){
	       JSONObject jsonResult = JSONObject.parseObject(json);
	       if(StringUtils.equals(jsonResult.getString("errcode"),"0")){
	    	   result = "success";
	       }
	    }
		return result;
	}

	@Override
	public String wxReceiveMsg(InputStream is) throws Exception {
		String result = "failed";
		SAXBuilder sb = new SAXBuilder();  
	    Document doc = sb.build(is);  
	    Element root = doc.getRootElement();
	    String msgType = root.getChildText("MsgType");
	    if(StringUtils.equals(msgType, "event")){//事件消息
	    	String eventType = root.getChildText("Event");
	    	if(StringUtils.equals(eventType, "subscribe")){//订阅
	    		log.info("subscribe FromUserName  is:"+root.getChildText("FromUserName"));	
	    	}else if(StringUtils.equals(eventType, "unsubscribe")){//取消息订阅
	    		log.info("unsubscribe FromUserName  is:"+root.getChildText("FromUserName"));	
	    	}else if(StringUtils.equals(eventType, "CLICK")){//自定义菜单
	    		log.info("EventKey  is:"+root.getChildText("EventKey"));
	    	}
	    }else{//其他消息
	    	 if(StringUtils.equals(msgType, "text")){//文本
	    		 log.info("text content :"+root.getChildText("Content"));
	    	 }else if(StringUtils.equals(msgType, "image")){//图片
	    		 log.info("pic url  :"+root.getChildText("PicUrl")); 
	    	 }
	    }
	    result = "success";
		return result;
	}

	@Override
	public String wxValidateToken(String signature, String timestamp,
			String nonce, String echostr) {
		boolean result = false;
		result = SignUtils.checkSignature(signature, timestamp, nonce);
		if(!result){
			echostr = "";
		}
		return echostr;
	}

}
