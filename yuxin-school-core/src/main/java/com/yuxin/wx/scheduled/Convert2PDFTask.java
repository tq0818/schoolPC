package com.yuxin.wx.scheduled;


import com.yuxin.wx.model.system.Task;

public interface Convert2PDFTask{
	   int maxSize=50;
	   int coreSize=20;
	   long keepAliveTime=1800;
	   int queueSize=30;
	   public void execute(Task task);
	
}