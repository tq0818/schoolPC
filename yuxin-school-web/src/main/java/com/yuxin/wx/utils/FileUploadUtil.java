package com.yuxin.wx.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.AccessControlList;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.yuxin.wx.controller.system.SysConfigNewsController;

/**
 * @Description:(文件上传工具类)
 * @author wang.zx
 * @date 2014-12-20 下午10:47:37
 * @version 1.0
 */
public class FileUploadUtil {

	private static FileUploadUtil fileUploadUtil;

	// 图片的自动编号
	private int picNo = 0;

	// 单例
	public static synchronized FileUploadUtil getlnstance() {
		if (null == fileUploadUtil) {
			fileUploadUtil = new FileUploadUtil();
		}

		return fileUploadUtil;
	}

	/**
	 * 
	 * @Title: getNewFileName
	 * @Description: (上传后的文件名)
	 * @param @param fileName
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String getNewFileName(String fileName) {
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		return UUID.randomUUID().toString() + suffix;
	}

	/**
	 * 
	 * 〈获取文件格式eg:0001.jpg--->jpg〉
	 * 
	 * @param [@param fileName
	 * @param [@return] [参数1说明]&#13;
	 * @param [@param fileName
	 * @param [@return] [参数2说明]&#13;
	 * @return String
	 * @Modify [方勇]
	 */
	public String getExtention(String fileName) {
		String uploadName = "";
		if (!"".equals(fileName) && null != fileName) {
			int pos = fileName.lastIndexOf(".");
			uploadName = fileName.substring(pos);
		}
		return uploadName;
	}

	// public static final String DEFAULT_FILE_UPLOAD_DIR = "fileupload/files";

	// /**
	// *
	// * @功能模块：
	// * @方法说明(MethodsIntro)： 保存上传的文件到指定的目录。并给文件随机生成一个新文件名，并返回这个文件保存成功之后的相对路径。
	// * @param mFile
	// * @param relativeDir
	// * 文件要保存的路径。相对于webroot
	// * @return
	// *
	// */
	// public static String saveFileUpload(MultipartFile mFile, String
	// relativeDir) {
	// return saveFileUpload(mFile, relativeDir, UUID.randomUUID().toString());
	// }
	//
	/**
	 * springmvc专用, 保存上传的文件。
	 * 
	 * @param mFile
	 * @param dir
	 *            文件要保存的路径。相对于webroot
	 * @param newfileName
	 *            新文件名,可以为空。定义保存后，承现的文件名。如果不带有后缀名，将使用上传源文件的后缀名。 返回文件的相对路径
	 */
	public String saveFileUpload(MultipartFile mFile, String relativeDir,
			String newfileName) {
		String url = "";
		try {

			if (newfileName != null) {
				if (newfileName != "") {
					newfileName = newfileName.concat(getExtention(mFile
							.getOriginalFilename()));
				}
			} else {
				newfileName = mFile.getOriginalFilename();
			}
			String name=SysConfigNewsController.getFileNameNoEx(newfileName);
			// String realPath =
			// ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
			url = relativeDir + "/" + name;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			mFile.transferTo(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	
	
	 /** 
     * @param args 
	 * @throws IOException  
     * @throws ClientProtocolException  
     */  
    public static void main(String[] args) throws Exception  
    {  
    	String accessKeyId = "6GvaDrKKU8n7Xgr0";
    	String accessKeySecret = "ZtvyKnynNaMupFISgtSPoAKYZQLUyY"; 
//    	String endpoint = "http://yunduoketang.img-cn-beijing.aliyuncs.com"; // 初始化一个OSSClient
//    	String endpoint ="http://images.yuuxin.com";
    	String endpoint ="http://oss-cn-beijing.aliyuncs.com";
    	
    	String bucketName="yunduoketang";
    	
    	OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
    	
//    	client.createBucket("yuuuuuuuxintest");
    	
//    	System.out.println(client.doesBucketExist(bucketName));
//    	File file = new File("/Users/chopin/Pictures/IMGP0001.jpg");
//    	InputStream content = new FileInputStream(file);
//    	// 创建上传Object的Metadata
//    	ObjectMetadata meta = new ObjectMetadata(); // 必须设置ContentLength
//    	meta.setContentLength(file.length());
    	
    	// 构造ListObjectsRequest请求
//    	ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
    	
    	AccessControlList accessControlList = client.getBucketAcl(bucketName);
    	System.out.println(accessControlList.toString());
    	// List Objects
    	ObjectListing listing = client.listObjects(bucketName);
    	// 遍历所有Object
    	System.out.println("Objects:");
    	for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
    		System.out.println(objectSummary.getKey());
    	}
    	
//    	// 设置URL过期时间为1小时
//    	Date expiration = new Date(new Date().getTime() + 3600 * 1000);    	
//    	URL url = client.generatePresignedUrl(bucketName, "1.jpg", expiration);
//    	System.out.println(url.getContent());
    	// 上传Object.
//    	PutObjectResult result = client.putObject("yunduoketang", "test.jpg", content, meta);
    	// 打印ETag
//    	System.out.println(result.getETag());
    }  
}
