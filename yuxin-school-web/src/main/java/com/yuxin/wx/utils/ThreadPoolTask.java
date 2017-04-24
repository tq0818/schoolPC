package com.yuxin.wx.utils;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.yuxin.wx.common.JsonMsg;

@SuppressWarnings("serial")
public class ThreadPoolTask implements Callable<String>, Serializable {
	private Log log = LogFactory.getLog("log");
	// 保存任务所需要的数据
	private File destFile;
	private ZipEntry entry;
	private OSSClient client;
	private String bucket;
	private ZipFile zip1File;
	private String fn;
	
	public ThreadPoolTask(File destFile,ZipEntry entry, OSSClient client
			,String bucket,ZipFile zip1File,String fn){
		this.destFile = destFile;
		this.entry = entry;
		this.client = client;
		this.bucket = bucket;
		this.zip1File = zip1File;
		this.fn = fn;
	}

	@Override
	public String call(){
		try {
			// TODO Auto-generated method stub
			destFile.getParentFile().mkdirs();
			InputStream eis = zip1File.getInputStream(entry);
			CompressFileUtil.write(eis, destFile);
			// 创建上传Object的Metadata
			
			ObjectMetadata meta = new ObjectMetadata();
			// 必须设置ContentLength
			meta.setContentLength(destFile.length());

			// 上传Object.
			PutObjectResult result = client.putObject(bucket, fn, destFile,
					meta);
			//li.remove(fn);
			//log.info("success");
			return JsonMsg.SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("上传出现异常，" + e.getMessage(),e);
			e.printStackTrace();
			//li.add(fn + "," + JsonMsg.ERROR);
			return JsonMsg.ERROR;
		}
	}

}
