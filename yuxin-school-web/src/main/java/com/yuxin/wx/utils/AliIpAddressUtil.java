package com.yuxin.wx.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.annotation.Async;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuxin.wx.utils.smsClient.interfacej.SmsClientSend;
import com.yuxin.wx.vo.address.Address;
import com.yuxin.wx.vo.address.Result;

public class AliIpAddressUtil {
	private static Resource resource;
	private static Properties props;
	public static Result<Address> getAddress(String ip){
//		System.out.println("开始等待15s");
//        try {
//        	Thread.sleep(15000);
//		} catch (InterruptedException e) {
////			e.printStackTrace();
//			System.out.println("等待异常");
//		}
//        System.out.println("等待15s结束");
//        return new Result<Address>();
		return httpGet(ip);
	}
	
	public static Result<Address> httpGet(String ip){
		String host = "https://dm-81.data.aliyun.com";
	    String path = "/rest/160601/ip/getIpInfo.json";
	    String method = "GET";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//	    headers.put("Authorization", "APPCODE b1375518546b4279b66032753bed7b18");
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("ip", ip);
	    Result<Address> address = null;

	    try {
	    	resource = new ClassPathResource(
					"config.properties");
			props = PropertiesLoaderUtils.loadProperties(resource);
			String appcode = props.getProperty("yunduoketang.ali.ip.appcode");
			String tip = props.getProperty("yunduoketang.ali.ip.usedtip");
			headers.put("Authorization", "APPCODE " + appcode);
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());
	    	//获取response的body
//	    	System.out.println(EntityUtils.toString(response.getEntity()));
//	    	{"status":"0","msg":"ok","result":{"ip":"122.224.186.100","area":"浙江省杭州市","type":"电信","country":"中国","province":"浙江","city":"杭州","town":""}} 
	    	String result = EntityUtils.toString(response.getEntity());
	    	if(StringUtils.isBlank(result) && "true".equals(tip)) {
	    		SmsClientSend.sendSmsTwo(null,"15801051908", "阿里Ip地址查询数量已用完【在线网校】",0,"zifei-notice");
	    	}else {
	    		Gson gson = new Gson();
		    	address = gson.fromJson(result,new TypeToken<Result<Address>>() {}.getType());
	    	}
	    	return address;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return new Result<Address>();
	}
	
	
	public static void main(String[] args) {
//		System.out.println(httpGet("122.224.186.100"));
		String result = "{\"status\":\"0\",\"msg\":\"ok\",\"result\":{\"ip\":\"122.224.186.100\",\"area\":\"浙江省杭州市\",\"type\":\"电信\",\"country\":\"中国\",\"province\":\"浙江\",\"city\":\"杭州\",\"town\":\"\"}}";
    	Gson gson = new Gson();
    	Result<Address> address = gson.fromJson(result,new TypeToken<Result<Address>>() {}.getType());
    	System.out.println(address.toString());
	}
}
