package com.yuxin.wx.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.aliyun.oss.OSSClient;
import com.yuxin.wx.common.JsonMsg;

public class StartTaskThread implements Runnable {

	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	private Map<String, Map<String, Object>> map;
	private OSSClient client;
	private String bucket;
	private ZipFile zip1File;
	private List<String> li;
	
	public StartTaskThread(ThreadPoolTaskExecutor threadPoolTaskExecutor,
			Map<String, Map<String, Object>> map, OSSClient client, String bucket, ZipFile zip1File,List<String> li){
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
		this.map = map;
		this.client = client;
		this.bucket = bucket;
		this.zip1File = zip1File;
		this.li = li;
	} 

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for (String fn : li) {
			Map<String, Object> param = map.get(fn);
			File destFile = (File) param.get("destFile");
			ZipEntry entry = (ZipEntry) param.get("entry");

			FutureTask<String> futureTask = new FutureTask<String>(
					                new ThreadPoolTask(destFile,entry,client,bucket
					                		,zip1File,fn));
			threadPoolTaskExecutor.execute(futureTask);
			
		}/*
		
		String result = null;
		try {
			// 取得结果，同时设置超时执行时间为1秒。同样可以用future.get()，不设置执行超时时间取得
			result = futureTask.get();
			log.info(result);
			if(result.equals(JsonMsg.SUCCESS)){
				li.remove(fn);
			}else{
				li.add(fn + "," + JsonMsg.ERROR);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("取得线程执行结果 异常," + e.getMessage());
			li.add(fn + "," + JsonMsg.ERROR);
		}*/
	}

}
