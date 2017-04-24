package com.yuxin.wx.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.util.HttpPostRequest;

public class TalkfunUtils {
	private static Log log = LogFactory.getLog("log");
	public static final String OPEN_ID = "";
	private static final String OPENTOKEN = "";
	private static final String APIURL = "http://api.talk-fun.com/portal.php?";
	private static final String FORMAT = "json";
	private static final String VER = "1.0";
	String timestamp = System.currentTimeMillis()+"";

	public static String getRsult(Map<Object, Object> map,String cmd) throws Exception{
		String param = URLEncoder.encode(net.sf.json.JSONObject
				.fromObject(map).toString(),"UTF-8");
		log.info("传入创建or修改参数,"+map.toString());
		String timestamp = System.currentTimeMillis()+"";

		Map<Object, Object> params = new HashMap<Object,Object>();
		params.put("openID", OPEN_ID);
		params.put("timestamp", timestamp);
		params.put("ver", VER);
		params.put("format", FORMAT);
		params.put("cmd", cmd);
		params.put("params", param);

		String sign = generateSign(params);
		params.put("sign", sign);

		String url = APIURL + mapToQueryString(params);
		String res = HttpPostRequest.get(url);
		return res;
	}
	private static String generateSign(Map<Object, Object> params) throws Exception {
		params.remove("sign");
		Object[] array = params.keySet().toArray();
		java.util.Arrays.sort(array);
		String keyStr = "";
		for (int i = 0; i < array.length; i++) {
			String key = array[i].toString();
			keyStr += key + params.get(key);
		}
		keyStr += OPENTOKEN;
		return MD5Talkfun.md5(keyStr);
	}
	public static String getRsult(Map<Object, Object> map,String cmd,String openId,String openToken) throws Exception{
		String param = URLEncoder.encode(net.sf.json.JSONObject
				.fromObject(map).toString(),"UTF-8");
		log.info("传入创建or修改参数,"+map.toString());
		String timestamp = System.currentTimeMillis()+"";
		
		Map<Object, Object> params = new HashMap<Object,Object>();
		if(openId != null){
			params.put("openID", openId);
		}else{
			params.put("openID", OPEN_ID);
		}
		params.put("timestamp", timestamp);
		params.put("ver", VER);
		params.put("format", FORMAT);
		params.put("cmd", cmd);
		params.put("params", param);

		String sign = generateSign(params,openToken);
		params.put("sign", sign);
		
		String url = APIURL + mapToQueryString(params);
		String res = HttpPostRequest.get(url);
		return res;
	}
	
	private static String generateSign(Map<Object, Object> params,String openToken) throws Exception {
		params.remove("sign");
		Object[] array = params.keySet().toArray();
		java.util.Arrays.sort(array);
		String keyStr = "";
		for (int i = 0; i < array.length; i++) {
			String key = array[i].toString();
			keyStr += key + params.get(key);
		}
		if(openToken != null){
			keyStr += openToken;
		}else{
			keyStr += OPENTOKEN;
		}
		return MD5Talkfun.md5(keyStr);
	}
	
	private static String mapToQueryString(Map<Object, Object> params) {
		Object[] array = params.keySet().toArray();

		java.util.Arrays.sort(array);
		String str = "";
		for (int i = 0; i < array.length; i++) {
			String key = array[i].toString();
			try {
				if (i != array.length - 1) {

					str += key
							+ "="
							+ URLEncoder.encode((String) params.get(key),
									"UTF-8") + "&";
				} else {
					str += key
							+ "="
							+ URLEncoder.encode((String) params.get(key),
									"UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}
}

