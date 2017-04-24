package com.yuxin.wx.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.util.qiniuVideo.MarqueeConf;
import com.yuxin.wx.util.qiniuVideo.MarqueeManage;
import com.yuxin.wx.util.qiniuVideo.UploadRes;
import com.yuxin.wx.util.qiniuVideo.UptokenRet;
import com.yuxin.wx.util.qiniuVideo.VideoManage;
import com.yuxin.wx.util.qiniuVideo.XVod;

@Component
public class FileQNUtils {
	private static Log log = LogFactory.getLog("log");
	private static Resource resource;
	private static Properties props;
	
	private static final String QNVD_CALLBACK_URL="http://manage.yunduoketang.cn/classModule/QnVideoCallback";
	// bucket
	private static String bucket;
	// 密钥配置
	private static Auth auth;
	
	public static String ak;
	public static String sk;
	// 设置转码操作参数
	private static String fops;
	//bucket域名
	private static String domain;

	static{
		try {
			log.info("开始初始化");
			resource = new ClassPathResource("config.properties");
			props = PropertiesLoaderUtils.loadProperties(resource);

//			ak = props.getProperty("yunduoketang.qn.accessKeyId");
//			sk = props.getProperty("yunduoketang.qn.accessKeySecret");
//			bucket = props.getProperty("yunduoketang.qn.bucket");
//			domain = props.getProperty("yunduoketang.qn.domain");
//			auth = Auth.create(ak, sk);
//			fops="yifangyun_preview";
//			log.info("初始化 成功");
		} catch (QiniuException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static JSONObject deleteFile(String keys) {
		JSONObject json = new JSONObject();
		Configuration cfg = new Configuration(Zone.zone0());
		BucketManager bucketManager = new BucketManager(auth, cfg);
		log.info("创建对象");
		try {
			// 调用delete方法移动文件
			bucketManager.delete(bucket, keys);
			log.info("删除");
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
		} catch (QiniuException e) {
			// 捕获异常信息
			Response r = e.response;
			log.info(r.toString());
			json.put(JsonMsg.MSG, "删除时出错，" + r.toString());
		}
		return json;
	}
	// 覆盖上传
	private static String getCoverUpToken(String keys) {
		// <bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
		System.out.println(bucket + "," + keys);
		return auth.uploadToken(bucket, keys);
	}
	
	/**
	 * 
	 * Class Name: FileQNUtils.java
	 * @Description: 七牛上传视频
	 * @author 周文斌
	 * @date 2017-3-13 下午2:42:46
	 * @modify	2017-3-13 下午2:42:46
	 * @version 
	 * @param key	唯一键
	 * @param path	文件路径
	 * @return
	 * @throws Exception 
	 */
	public static String QiniuVideoUpload(String bucket,int deadline,String fileType,String aks,String sks,String key,String path) throws Exception{
		
		UploadRes up = new UploadRes(aks,sks);
        //UptokenRet ur = up.getVodUptokenV2("ydkt", 3600, type);
		XVod xVod=new XVod(QNVD_CALLBACK_URL);
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        String vodStr=gson.toJson(xVod);
        System.out.println(vodStr);
        
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("x:vod",vodStr);
		params.put("key", key);
        Map<String, Object> map = up.uploadResource(bucket, deadline, fileType, path,params);
        
        String rekey = (String) map.get("key");
        return rekey;
	}

	/**
	 * 
	 * Class Name: FileQNUtil.java
	 * 
	 * @Description: 上传
	 * @author 周文斌
	 * @date 2016-8-30 下午4:24:56
	 * @modify 2016-8-30 下午4:24:56
	 * @version
	 * @param path
	 * @param bucket
	 * @param uid
	 * @throws IOException
	 */
	public static JSONObject upload(File f, String dir)
			throws Exception {
		JSONObject json = new JSONObject();
		Configuration cfg = new Configuration(Zone.zone0());
		UploadManager uploadManager = new UploadManager(cfg);

		try {
			String keys =  dir + f.getName();
			log.info("调用put方法上传");
			// 调用put方法上传
			Response res = uploadManager.put(f, keys, getCoverUpToken(keys));
			log.info("打印返回的信息");
			// 打印返回的信息
			String r = res.bodyString();
			log.info(r);
			json = JSONObject.parseObject(r);
			if (json.getString("key").equals(keys)) {
				json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
				json.put("size", f.length() + "");
				json.put("filePath", keys);
			} else {
				json.put(JsonMsg.MSG, "上传失败");
			}
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			log.info(r.toString());
			try {
				// 响应的文本信息
				log.info(r.bodyString());
				json.put(JsonMsg.MSG, r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		} catch (Exception e){
			e.printStackTrace();
			json.put(JsonMsg.MSG, "上传失败");
		}
		return json;
	}

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	private static String getSimpleUpToken() {
		return auth.uploadToken(bucket);
	}
	
	public static String returnUptoken(){
		return getSimpleUpToken();
	}

	public static String download(String keys) {
		// 调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
		String url = domain + keys;
		String downloadRUL = auth.privateDownloadUrl(url, 120);
		log.info(downloadRUL);
		return downloadRUL;
	}

	public static Double convertFileSize(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;
		Double s = (double) size / gb;
		return new BigDecimal(s).setScale(3, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	public static String convertFile(String sourcePath){
		Configuration cfg = new Configuration(Zone.zone0());
		OperationManager manager=new OperationManager(auth,cfg);
		String result="";
		try {
			StringMap params=new StringMap();
			params.put("pipeline", "yunduoOffice");
//			params.put("persistentNotifyUrl", "http://www.yunduoketang.com");
			result=manager.pfop(bucket, sourcePath, fops,params);
		} catch (QiniuException e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
		
	}

	public static void main(String[] args) {
		try {
			//FileQNUtils.QiniuVideoUpload("", "");

			VideoManage vm = new VideoManage("501ilj1uLOgQqkkoPtUEdWNc2JBAr7Gke9SB-7IK","nCsL5Wkjiay60oNsDfxrj2QKF1YYCh0KM7ZC5Rkg");
			String ret = vm.getVideoInfo("ydkt", "17394/video/4fee86c9-3323-44ef-ad0e-9913d1e2b9aa.mp4");
			System.out.println(ret);
			JSONObject json = JSONObject.parseObject(ret);
			if(json.getIntValue("code") == 200){
				JSONObject msg = JSONObject.parseObject(json.getString("msg"));
				JSONArray transcoding = msg.getJSONArray("transcoding");
				if(transcoding.size() > 0){
					JSONObject v = transcoding.getJSONObject(0);
					System.out.println(v.getString("url"));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String secToTime(int time) {  
        String timeStr = null;  
        int hour = 0;  
        int minute = 0;  
        int second = 0;  
        if (time <= 0)  
            return "00:00";  
        else {  
            minute = time / 60;  
            if (minute < 60) {  
                second = time % 60;  
                timeStr = unitFormat(minute) + ":" + unitFormat(second);  
            } else {  
                hour = minute / 60;  
                if (hour > 99)  
                    return "99:59:59";  
                minute = minute % 60;  
                second = time - hour * 3600 - minute * 60;  
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);  
            }  
        }  
        return timeStr;  
    }  
	
	/**
	 * 查询七牛跑马灯配置
	 */
	public static MarqueeConf getMarqueeConf(String ak,String sk,String hub){
		MarqueeManage mm = new MarqueeManage(ak, sk);
		String str = mm.getMarqueeConf(hub);
		MarqueeConf mc = JSONObject.parseObject(str, MarqueeConf.class);
		return mc;
	}
	/**
	 * 创建 or 修改 七牛 跑马灯配置
	 */
	public static boolean createMarqueeConf(MarqueeConf marquee,String ak,String sk,String hub){
		MarqueeManage mm = new MarqueeManage(ak, sk);
		return mm.createMarqueeConf(marquee, hub);
	}
	/**
	 * 启用 禁用    七牛 跑马灯 
	 */
	public static boolean enableMarqueeConf(String ak,String sk,String hub,int enabled){
		MarqueeManage mm = new MarqueeManage(ak, sk);
		return mm.enableMarqueeConf(hub, enabled);
	}
	
	public static boolean deleteQNVideo(String ak,String sk,String hub,String key){
		try {
			VideoManage vm = new VideoManage(ak,sk);
			vm.deleteOneVideo(hub, key);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("删除七牛视频失败," + e.getMessage());
			return false;
		}
	}
  
    private static String unitFormat(int i) {  
        String retStr = null;  
        if (i >= 0 && i < 10)  
            retStr = "0" + Integer.toString(i);  
        else  
            retStr = "" + i;  
        return retStr;  
    }  
}
