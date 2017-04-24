package com.yuxin.wx.common;

import java.util.Map;
import java.util.TreeMap;

import com.yuxin.wx.util.Md5Encrypt;

public class CCLiveUtils {
	
	public static String createHashedQueryString(Map<String, String> queryMap, String salt) {

	    Map<String, String> map = new TreeMap<String, String>(queryMap);
	    String qs = getParamStr(map); //生成queryString方法可自己编写
	    if (qs == null) {
	        return null;
	    }
	    long time = System.currentTimeMillis() / 1000;
	    String hash = Md5Encrypt.md5(String.format("%s&time=%d&salt=%s", qs, time, salt));
	    hash = hash.toLowerCase();
	    String thqs = String.format("%s&time=%d&hash=%s", qs, time, hash);
	    
	    return thqs.replaceAll(" ", "");
	}
	
	 /**
     * 将参数转为路径字符串
     * @param params
     *             参数
     * @return
     */
    private static String getParamStr(Map<String, String> paramMap){
        if(null==paramMap || paramMap.isEmpty()){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(String key : paramMap.keySet() ){
            builder.append("&")
                   .append(key).append("=").append(paramMap.get(key));
        }
        return builder.deleteCharAt(0).toString();
    }
}
