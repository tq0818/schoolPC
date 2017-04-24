package com.yuxin.wx.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Encoder;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.StringMap;
import com.yuxin.wx.vo.user.ReturnValueVo;


public class FileManage {
	/*static Log log=LogFactory.getLog("log");
	
	private static UploadManager uploadManager = new UploadManager();
	
	*//**
	 * 
	 * Class Name: FileManage.java
	 * @Description: 上传文件并返回参数
	 * @author zhang.zx
	 * @date 2015年8月25日 下午7:08:31
	 * @modifier
	 * @modify-date 2015年8月25日 下午7:08:31
	 * @version 1.0
	 * @param filepath
	 * @param fileName
	 * @param token
	 * @return map
	 *//*
	public static ReturnValueVo uploadAndReturn(String filePath, String fileName, String token){
		ReturnValueVo value=new ReturnValueVo();
		try {
			Response r= uploadManager.put(filePath, fileName, token);
			log.info("response返回值====="+r.toString());
        	value= r.jsonToObject(ReturnValueVo.class);
        } catch (QiniuException e) {
        	Response res=e.response;
        	try{
        		String body=res.bodyString();
        		System.out.println(body);
        	}catch(Exception ex){
        		ex.printStackTrace();
        	}
            log.error("上传文件失败");
        } 
        return value;
	}
	
	public static ReturnValueVo uploadAndReturn(File file, String fileName, String token){
		ReturnValueVo value=new ReturnValueVo();
		try {
			Response r= uploadManager.put(file, fileName, token);
        	value= r.jsonToObject(ReturnValueVo.class);
        	log.info("response返回值====="+r.toString());
        } catch (Exception e) {
        	log.error("上传文件失败",e);
            e.printStackTrace();
        }
        return value;
	}
	
	public static ReturnValueVo uploadAndReturn(MultipartFile mFile, String fileName, String token){
		ReturnValueVo value=new ReturnValueVo();
		try {
			Response r= uploadManager.put(mFile.getBytes(), fileName, token);
			log.info("上传文件成功response返回值====="+r.toString());
        	value= r.jsonToObject(ReturnValueVo.class);
        } catch (QiniuException e) {
        	Response res=e.response;
        	log.error("上传文件失败response返回值====="+res.toString());
            log.error("上传文件失败",e);
            try {
                log.error("上传失败res.bodyString信息----------------"+res.bodyString());
            } catch (QiniuException e1) {
            
            }
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return value;
	}
	
	*//**
	 * 
	 * Class Name: FileManage.java
	 * @Description: 自定义参数上传文件
	 * @author zhang.zx
	 * @date 2015年8月27日 上午11:56:04
	 * @modifier
	 * @modify-date 2015年8月27日 上午11:56:04
	 * @version 1.0
	 * @param mFile
	 * @param fileName
	 * @param token
	 * @return
	 *//*
	public static ReturnValueVo uploadAndReturn(MultipartFile mFile, String fileName, String token,StringMap params){
		ReturnValueVo value=new ReturnValueVo();
		try {
			Response r = uploadManager.put(mFile.getBytes(), fileName, token, params, null, false);
			log.info("response返回值====="+r.toString());
        	value= r.jsonToObject(ReturnValueVo.class);
        } catch (QiniuException e) {
        	Response res=e.response;
        	log.error("错误日志==="+res.toString());
            log.error("上传文件失败",e);
        } catch (IOException e) {
			e.printStackTrace();
		}
        return value;
	}
	
	*//**
	 * 
	 * Class Name: FileManage.java
	 * @Description: 自定义参数文件上传
	 * @author zhang.zx
	 * @date 2015年8月27日 下午4:13:37
	 * @modifier
	 * @modify-date 2015年8月27日 下午4:13:37
	 * @version 1.0
	 * @param filePath
	 * @param fileName
	 * @param token
	 * @param params
	 * @return
	 *//*
	public static ReturnValueVo uploadAndReturn(String filePath, String fileName, String token,StringMap params){
		ReturnValueVo value=new ReturnValueVo();
		try {
			Response r= uploadManager.put(filePath, fileName, token, params, null, false);
			log.info("response返回值====="+r.toString());
        	value= r.jsonToObject(ReturnValueVo.class);
        } catch (QiniuException e) {
        	Response res=e.response;
        	log.error("错误日志==="+res.toString());
            log.error("上传文件失败",e);
            try {
                log.error(res.bodyString());
            } catch (QiniuException e1) {
            
            }
        }
        return value;
	}
	
	public static void main(String[] args) {
		//map.put("x:updateTime", +new Date());
		//ReturnValueVo ret=uploadAndReturn("E:/a/h.mp4", "ats.mp4", token, map);
		//System.out.println(ret.getCreator()+"----"+ret.getUpdateTime()+"----"+ret.getKey());
		
		//ret = self.get_argument('upload_ret');
		//	获得的结果是unicode类型，直接拿来urlsafe_b64decode会报错。因此要:
		//decoded_ret = urlsafe_b64decode(str(ret))
//		ReturnValueVo ret=uploadAndReturn("E:/a/ddr.mp4", "t68.mp4", token);
//		StringMap map=new StringMap();
//		map.put("x:value", "55");
//		ReturnValueVo ret=uploadAndReturn("E:/a/Bootstrap.mp4", "ats.mp4", token, map);
//		System.out.println(ret.getValue1());
//		BASE64Encoder encode=new BASE64Encoder();
//		String saveas=encode.encode("yunduoketang:Bootstrap.mp4".getBytes());
//		String callbackUrl="";
//		String callbackHost="";
//		String persistentOps="avthumb/flv|saveas/"+saveas;//avthumb/flv|saveas/cWJ1Y2tldDpxa2V5
//		String persistentNotifyUrl="";
//		String persistentPipeline="";
//		String callbackBody = "{\"name\": $(fname),\"bucket\":$(bucket),\"etag\":$(etag),\"mimeType\":$(mimeType),\"persistentId\":$(persistentId),\"fsize\": \"$(fsize)\",\"width\": \"$(imageInfo.width)\",\"height\": \"$(imageInfo.height)\",\"key\":$(key)}";
//		StringMap map=new StringMap();
//        map.put("callbackUrl", callbackUrl);
//        map.putNotEmpty("callbackHost", callbackHost);
//        map.put("returnBody", callbackBody);
//        map.putNotEmpty("persistentOps", persistentOps);
//        map.putNotEmpty("persistentNotifyUrl", persistentNotifyUrl);
//        map.putNotEmpty("persistentPipeline", persistentPipeline);
		
//		Auth auth=Auth.create("501ilj1uLOgQqkkoPtUEdWNc2JBAr7Gke9SB-7IK", "nCsL5Wkjiay60oNsDfxrj2QKF1YYCh0KM7ZC5Rkg");
//		String token=auth.uploadToken("yunduoketang", "", 3600, map, true);
//		uploadAndReturn("E:/a/Bootstrap.mp4", "Bootstrap1.mp4", token);
	}*/
	
}
