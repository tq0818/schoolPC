package com.yuxin.wx.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.FutureTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.yuxin.wx.common.JsonMsg;

@Component
public class FileUtil {
	static Log log=LogFactory.getLog("log");

	private static Resource resource = new ClassPathResource("config.properties");
	private static Properties props=null ;
	private static OSSClient client=null;
	private static String BUCKET;

	private static String imageServiceRealPath = "";
	static {
		try{
			props=PropertiesLoaderUtils.loadProperties(resource);
			imageServiceRealPath=props.getProperty("imageServiceRealPath");

		}catch(Exception e){
			log.error("OSS-client初始化失败，原因："+e);
		}
	}
	public interface Module{
		/**
		 * 课程封面
		 */
		String COURSE="course";
		/**
		 * 轮播图
		 */
		String CYCLLEPIC="cycllepic";
		/**
		 * 新闻公告
		 */
		String NEWS="news";
		/**
		 * 头像
		 */
		String HEADPIC="headpic";
		/**
		 * 公司相关
		 */
		String COMPANY="company";
		/**
		 * 临时文件
		 */
		String TEMP="temp";
		/**
		 * 课程资料
		 */
		String COURSEFILE="coursefile";
		/**
		 * 编辑器
		 */
		String EDITOR="editor";
		/**
		 * 老师
		 */
		String TEACHER="teacher";
		/**
		 * scrom课件
		 */
		String SCROM="scrom";

		/**
		 * scorm
		 */
		String SCORM="scorm";

		/**
		 * swf
		 */
		String SWF="swf";

	}

	private static void init(){

	}

	/**
	 * 获取文件夹
	 * @param company
	 * @return
	 */
	private static String getPath(String module,String company,String fileName){
		String folder="temp";
		if(StringUtils.isNotBlank(module)){
			if(Module.COMPANY.equals(module)
					||Module.COURSE.equals(module)
					||Module.CYCLLEPIC.equals(module)
					||Module.EDITOR.equals(module)
					||Module.HEADPIC.equals(module)
					||Module.NEWS.equals(module)
					||Module.TEMP.equals(module)
					||Module.COURSEFILE.equals(module)
					||Module.TEACHER.equals(module)
					||Module.SCORM.equals(module)
					){
				folder=module;
			}
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String date=sdf.format(new Date());
		if(StringUtils.isNotBlank(company)){
			folder+="/"+company+"/"+date+"/";
		}else{
			folder+="/public/"+date+"/";
		}
		folder+=fileName;
		return folder;
	}

	private static void inputstreamtofile(InputStream ins, File file) {
		OutputStream os=null;
		try{
			os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		}catch(Exception e){
			log.error("inputstreamtofile 过程异常,原因："+e,e);
			e.printStackTrace();
		}finally{
			try{
				os.close();
				ins.close();
			}catch(Exception ex){
				log.error("inputstreamtofile 过程异常,原因："+ex.getStackTrace(),ex);
				ex.printStackTrace();
			}
		}

	}
	/**
	 * 判断是否为图像
	 * @param path
	 * @return
	 */
	private static Boolean isImage(String name){
		String[] type={"jpg","png","bmp","gif","jpeg"};
		String suffixName=name.substring(name.lastIndexOf(".")).toLowerCase();
		boolean isImage=false;
		for(int i=0;i<type.length;i++){
			if(suffixName.equals(type[i])){
				isImage=true;
			}
		}
		return isImage;
	}



	/**
	 * 删除单个文件
	 * @param   sPath    被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		Boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 *  根据路径删除指定的目录或文件，无论存在与否
	 *@param sPath  要删除的目录或文件
	 *@return 删除成功返回 true，否则返回 false。
	 */
	public static boolean DeleteFolder(String sPath) {
		Boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) {  // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) {  // 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else {  // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}


	/**
	 * 删除单个文件
	 * @param   sPath    被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteOssFile(String sPath) {
		return false;
	}


	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * @param   sPath 被删除目录的文件路径
	 * @return  目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		//如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		//如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		Boolean flag = true;
		//删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			//删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) break;
			} //删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) break;
			}
		}
		if (!flag) return false;
		//删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *
	 * @param filePath 原始文件路径
	 * @param module 模块,必须是Module里的值，如果不是则会存到temp中
	 * @param company  公司id，如果是公用的，可以为空
	 * @return
	 * @throws IOException
	 */
	public static String upload(MultipartFile mFile,String module,String company) throws Exception{
		// 初始化OSSClient
		init();
		String fileName=generateFileName(mFile.getOriginalFilename());
		String path=getPath(module,company,fileName);
		String realPath = imageServiceRealPath+path;
		if (!mFile.isEmpty()) {
			try {
				FileUtils.copyInputStreamToFile(mFile.getInputStream(), new File(realPath));
				System.out.println(realPath);
				return path;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 *
	 * @param filePath 原始文件路径
	 * @param module 模块,必须是Module里的值，如果不是则会存到temp中
	 * @param company  公司id，如果是公用的，可以为空
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String upload(String filePath,String module,String company) throws Exception{
		// 初始化OSSClient
		init();
		String fileName=generateFileName(filePath);
		String path=getPath(module, company, fileName);
		String realPath = imageServiceRealPath+path;
		File file = new File(filePath);
		InputStream content = new FileInputStream(file);
		try {
			FileUtils.copyInputStreamToFile(content, new File(realPath));
			return path;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 *
	 * @param filePath 原始文件路径
	 * @param module 模块,必须是Module里的值，如果不是则会存到temp中
	 * @param company  公司id，如果是公用的，可以为空
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String upload(File file,String module,String company) throws FileNotFoundException{
		// 初始化OSSClient
		init();
		String fileName=generateFileName(file.getName());
		String path=getPath(module, company, fileName);
		// 构造ListObjectsRequest请求
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest(BUCKET);
		// "/" 为文件夹的分隔符
		listObjectsRequest.setDelimiter("/");
		ObjectListing listing = client.listObjects(listObjectsRequest);
		String[] folders=path.split("/");
//    	for(int i=0;i<folders.length-1;i++){
//    		if(StringUtils.isNotBlank(folders[i])){
//    			Boolean hasFolder=false;
//        		for (String commonPrefix : listing.getCommonPrefixes()) {
//        			if(commonPrefix.equals(folders[i])){
//            			hasFolder=true;
//            		}
//        		}
//        		//如果没有就新建
//            	if(!hasFolder){
//            		createFolder(module,"",folders[i]);
//            	}
//    		}
//
//		}

		// 获取指定文件的输入流
		InputStream content = new FileInputStream(file);
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		if(isImage(fileName)){
			meta.setContentType("image/jpeg");
		}
		// 必须设置ContentLength
		meta.setContentLength(file.length());
		// 上传Object.
		PutObjectResult result = client.putObject(BUCKET, path, content, meta);
		// 打印ETag
		System.out.println(result.getETag());
		if(isImage(fileName)){
			return getImageURL(fileName);
		}else{
			return path;
		}
	}

	/**
	 *
	 * @param filePath 原始文件路径
	 * @param module 模块,必须是Module里的值，如果不是则会存到temp中
	 * @param company  公司id，如果是公用的，可以为空
	 * @return 文件名称
	 * @throws FileNotFoundException
	 */
	public static String upload(InputStream file,String module,String company) throws FileNotFoundException{
		// 初始化OSSClient
		init();
		String fileName=generateFileName("");
		String path=getPath(module, company, fileName);
		// 构造ListObjectsRequest请求
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest(BUCKET);
		// "/" 为文件夹的分隔符
		listObjectsRequest.setDelimiter("/");
		ObjectListing listing = client.listObjects(listObjectsRequest);
		String[] folders=path.split("/");
//    	for(int i=0;i<folders.length-1;i++){
//    		if(StringUtils.isNotBlank(folders[i])){
//    			Boolean hasFolder=false;
//        		for (String commonPrefix : listing.getCommonPrefixes()) {
//        			if(commonPrefix.equals(folders[i])){
//            			hasFolder=true;
//            		}
//        		}
//        		//如果没有就新建
//            	if(!hasFolder){
//            		createFolder(module,"",folders[i]);
//            	}
//    		}
//
//		}

		// 获取指定文件的输入流
		InputStream content = file;
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		if(isImage(fileName)){
			meta.setContentType("image/jpeg");
		}
		// 必须设置ContentLength
		meta.setContentLength(toByteArray(file).length);
		// 上传Object.
		PutObjectResult result = client.putObject(BUCKET, path, content, meta);
		// 打印ETag
		System.out.println(result.getETag());
		if(isImage(fileName)){
			return getImageURL(fileName);
		}else{
			return path;
		}
	}

	public static Long uploadProcess(MultipartFile mFile,String module,String company){
		init();
		String fileName=generateFileName(mFile.getOriginalFilename());
		String path=getPath(module, company, fileName);
		try{
			ObjectMetadata tmpObjectMeta= client.getObjectMetadata(BUCKET, path);
			return tmpObjectMeta.getContentLength()/mFile.getSize();
		}catch(Exception e){
			return null;
		}
	}

	public static InputStream download(String module,String fileName){
		// 初始化OSSClient
		init();
		// 获取Object，返回结果为OSSObject对象
		OSSObject object = client.getObject(BUCKET, fileName);
		// 获取Object的输入流
		return object.getObjectContent();
	}

	public static void download(String module,String fileName,String path){

		try{
			System.out.println(imageServiceRealPath+fileName);
			File file = new File(imageServiceRealPath+fileName);
			File outFile = new File(path);
			FileUtils.copyFile(file,outFile);
			// 获取Object的输入流
			InputStream in= new FileInputStream(file);


		}catch(IOException e){
			log.error("保存原图到临时磁盘失败"+e);
		}
	}


	public static String getImageURL(String fileName){
		String path=fileName+"@1wh.jpg";
		return path;
	}

	public static void createFolder(String module,String parent,String name) {
		// 初始化OSSClient
		init();
		name=name+"/";//oss规定要以/结尾
		ObjectMetadata objectMeta = new ObjectMetadata();
    	/*这里的size为0,注意OSS本身没有文件夹的概念,这里创建的文件夹本质上是一个size为0的Object,dataStream仍然可以有数据*/
		byte[] buffer = new byte[0];
		ByteArrayInputStream in = new ByteArrayInputStream(buffer);
		objectMeta.setContentLength(0);
		try {
			client.putObject(BUCKET, name, in, objectMeta);
		} finally {
			try{
				in.close();
			}catch(IOException e){
				log.error("创建文件夹时释放资源异常，原因:"+e,e);
				e.printStackTrace();
			}
		}

	}

	public static String generateFileName(String path){
		String suffix = "";
		if(path.lastIndexOf(".")!=-1){
			suffix=path.substring(path.lastIndexOf("."));
		}
		return UUID.randomUUID().toString() + suffix;
	}

	public static String swfFileName(String path){
		return UUID.randomUUID().toString() + ".swf";
	}

	public static byte[] toByteArray(InputStream input){
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try{
			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}
		}catch(Exception e){
			log.error(e,e);
			e.printStackTrace();
		}
		return output.toByteArray();
	}

	/**
	 *
	 * Class Name: FileUtil.java
	 * @Description: 上传文档
	 * @author 周文斌
	 * @date 2015-8-11 上午11:18:13
	 * @version 1.0
	 * @param mFile
	 * @param module
	 * @param company
	 * @return
	 * @throws Exception
	 */
	public static JSONObject uploadDocument(File file,String module,String company) throws Exception{
		JSONObject json = new JSONObject();
		String fileName=generateFileName(file.getName());
		String path=getPath(module,company,fileName);
		String realPath = imageServiceRealPath+path;
		Long size = file.length();
		try {
			InputStream in = new FileInputStream(file);
			FileUtils.copyInputStreamToFile(in, new File(realPath));
			System.out.println(realPath);
			in.close();
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
			json.put("filePath", path);
			json.put("size", size);
			return json;
		} catch (IOException e) {
			e.printStackTrace();
		}
		json.put(JsonMsg.MSG, JsonMsg.ERROR);
		return json;
	}

	/**
	 *
	 * Class Name: FileUtil.java
	 * @Description: 判断是否为文档
	 * @author 周文斌
	 * @date 2015-8-11 上午11:19:56
	 * @version 1.0
	 * @param name
	 * @return
	 */
	private static Boolean isDocument(String name){
		String[] type={"doc","pdf","xls","ppt","docx","pptx","xlsx","wps"};
		String suffixName=name.substring(name.lastIndexOf(".")).toLowerCase();
		boolean isdoc=false;
		for(int i=0;i<type.length;i++){
			if(suffixName.equals(type[i])){
				isdoc=true;
			}
		}
		return isdoc;
	}

	public static String convertFileSize(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;
		DecimalFormat df = new DecimalFormat("#.00");
		if (size >= gb) {
			return df.format((double) size / gb) + "GB";
		} else if (size >= mb) {
			return df.format((double) size / mb) + "MB";
		} else if (size >= kb) {
			return df.format((double) size / kb) + "KB";
		} else
			return df.format((double) size) + "B";
	}

	/**
	 *
	 * Class Name: FileUtil.java
	 * @Description: 上传zip文件并解压存放目录
	 * @author zhang.zx
	 * @date 2016年6月27日 上午10:51:41
	 * @modifier
	 * @modify-date 2016年6月27日 上午10:51:41
	 * @version 1.0
	 * @param zipFile
	 * @param module
	 * @param company
	 * @return
	 * @throws IOException
	 */
	public static String unZip(String filepath,MultipartFile file,String module
			,String company,ThreadPoolTaskExecutor threadPoolTaskExecutor) throws IOException{
		// 初始化OSSClient
		init();
		String fileName=generateFileName(file.getOriginalFilename());
		String fna = file.getOriginalFilename();
		fna = fna.substring(0,fna.indexOf("."));
		fna = fna.replaceAll(" ", "");
		String path = module + "/" + company +"/";

		String name = filepath + fileName;
		try {
			File f = new File(name);
			file.transferTo(f);

			log.info("地址：" + name);
			ZipFile zip1File = new ZipFile(f);
			Enumeration<? extends ZipEntry> entryEnum = zip1File.entries();
			ZipEntry entry = null;
			String oname = fna + "/";
			List<String> li = new ArrayList<String>();
			Map<String, Map<String, Object>> map = new HashMap<String, Map<String,Object>>();
			fna = (UUID.randomUUID().toString() + "/");
			while (entryEnum.hasMoreElements()) {
				entry = entryEnum.nextElement();
				String ename = entry.getName();
				ename = ename.replaceAll(" ", "");
				ename = ename.replace(oname, fna);
				String fn = path + fna + ename;
				File destFile = new File(fn);
				if (entry.isDirectory()) {
					destFile.mkdirs();
				} else {
					li.add(fn);
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("destFile", destFile);
					param.put("entry", entry);
					map.put(fn, param);
				}
			}

			log.info("文件总数，" + li.size());

			new Thread(new StartTaskThread(threadPoolTaskExecutor,map
					,client,BUCKET,zip1File,li))
					.start();

			FutureTask<String> futureTasks = new FutureTask<String>(
					new ThreadPoolTasks(threadPoolTaskExecutor));
			new Thread(futureTasks).start();

			String res = null;

			try {
				res = futureTasks.get();
				log.info("结果，" + res);
			} catch (Exception e) {
				log.error("获取 list结果异常," + e.getMessage(),e);
				e.printStackTrace();
			}

			zip1File.close();
			log.info("删除," + name);
			f.delete();

			if(res.equals(JsonMsg.SUCCESS)){
				return path + fna;
			}else{
				return res;
			}
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			return "socket";
		} catch (ConnectException e) {
			e.printStackTrace();
			return "conn";
		} catch (ZipException e) {
			e.printStackTrace();
			return "zip";
		} catch (OSSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "OSS";
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Client";
		} catch (IllegalStateException e){
			e.printStackTrace();
			return "Illegal";
		} catch (IOException e){
			e.printStackTrace();
			return "ioe";
		} catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}


	public static boolean deleteOssObject(String key){
		try {
			client.deleteObject(BUCKET, key);
			return true;
		} catch (Exception e) {
			log.error("删除oss obj 异常," + e.getMessage(),e);
			e.printStackTrace();
			return false;
		}
	}
	public static String upload2(String suffix,InputStream file,String module,String company) throws FileNotFoundException{
		// 初始化OSSClient
		String fileName=generateFileName(suffix);
		String path=getPath(module, company, fileName);

		String realPath = imageServiceRealPath+path;
		try {
			FileUtils.copyInputStreamToFile(file, new File(realPath));
			System.out.println(realPath);
			return path;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static JSONObject uploadSwf(String swfpath,String path){
		// 初始化OSSClient
		init();
		// 构造ListObjectsRequest请求
		JSONObject json = new JSONObject();

		File file = new File(swfpath);

		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		meta.setContentLength(file.length());

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			// 获取指定文件的输入流
			// 创建上传Object的Metadata
			// 上传Object.
			PutObjectResult result = client.putObject(BUCKET, path, fis, meta);

			log.info(result.getETag());

			String size = FileUtil.convertFileSize(file.length());

			fis.close();
			file.delete();
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
			json.put(JsonMsg.URL, path);
			json.put("size", size);
			return json;
		} catch (FileNotFoundException e) {
			log.error("文件没有发现，" + e.getMessage(),e);
			e.printStackTrace();
			json.put(JsonMsg.MSG, "文件没有发现");
			return json;
		} catch (IOException e) {
			log.error("文件读取异常，" + e.getMessage(),e);
			e.printStackTrace();
			json.put(JsonMsg.MSG, "文件读取异常");
			return json;
		}
	}

}
