package com.yuxin.wx.scheduled;

import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.model.system.Task;
import com.yuxin.wx.util.officeConvert.OfficeThreadPool;
import com.yuxin.wx.util.officeConvert.OpenOfficeManagement;

/**
 * 通用文件转换类
 * 如果要增加一种新的文件转换，只需增加一个实现类
 * @author chopin
 *
 */
public class OfficeConvert2PDFTask implements Convert2PDFTask {
	Log log =LogFactory.getLog("OfficeConvert2PDFTask");
	private static OfficeConvert2PDFTask converPool;
	private static ThreadPoolExecutor executer;
	public  Vector<Future<?>> results;
	private Task task;
	private static OfficeThreadPool office;
	private static Thread monitor;
	
	private OfficeConvert2PDFTask() {
		executer=new ThreadPoolExecutor(coreSize,maxSize,keepAliveTime,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queueSize));
		results=new Vector<Future<?>>();
		monitor=new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					log.info("---------------------定时清理已完成线程，开始执行--------------");
					Thread.sleep(10000);
					cleanResults();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		monitor.start();
	}
	
	public static OfficeConvert2PDFTask newInstance(){
		if(executer!=null){
			return converPool;
		}
		converPool=new OfficeConvert2PDFTask();
		office=OfficeThreadPool.getNewInstance();
		return converPool;
	}
	@Override
	public synchronized void execute(Task task){
		this.task=task;
//		new Thread(new SyncRunner()).start();
//		OpenOfficeManagement manage=office.getThread();
		log.info("************************执行****************************");
		ConvertJob job =new ConvertJob(task);
		results.add(executer.submit(job));
//		cleanResults();
		log.info("************************执行结束****************************");
	}
	
	public boolean isEmpty(){
		return results.size()==0;
	}
	
	public  int size(){
		return results.size();
	}
	
	private void cleanResults(){
		log.info("清除result");
		Vector<Future<?>> temp=new Vector<Future<?>>(results);
			for(Future<?> t: temp){
				log.info("["+t+"]");
				if(t==null || t.isDone()){
					results.remove(t);
				}
			}
	}
	
	public class SyncRunner implements Runnable{
		@Override
		public void run() {
			
		}
	}


}
