package com.yuxin.wx.utils;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.yuxin.wx.common.JsonMsg;

@SuppressWarnings("serial")
public class ThreadPoolTasks implements Callable<String>, Serializable {

	private Log log = LogFactory.getLog("log");
	
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	public ThreadPoolTasks(ThreadPoolTaskExecutor threadPoolTaskExecutor
			) {
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
	}

	@Override
	public String call(){
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
			int count = threadPoolTaskExecutor.getActiveCount();
			while (count > 0) {
				count = threadPoolTaskExecutor.getActiveCount();
				//log.info("当前活动数,"+count);
				Thread.sleep(1000);
			}
			log.info("成功上传");
			return JsonMsg.SUCCESS;
		} catch (Exception e) {
			log.error("检查出现错误,"+e.getMessage(),e);
			e.printStackTrace();
			return JsonMsg.ERROR;
		}
	}

}
