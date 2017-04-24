package com.yuxin.wx.scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.model.system.SysFileConvertTask;
import com.yuxin.wx.system.mapper.SysFileConvertTaskMapper;

public class ConvertPDFTask {
	
	private static Log log = LogFactory.getLog("log");
	
	public static final LinkedBlockingQueue<SysFileConvertTask> queue =  new LinkedBlockingQueue<SysFileConvertTask>(2000);

	public static final ExecutorService executor = Executors.newFixedThreadPool(10);
	
	public static void convertPDF(String serverName,SysFileConvertTaskMapper convert){
		try {
			log.info("查询待执行任务");
			List<SysFileConvertTask> zclist = convert.pageThreeHundred(0);
			log.info("正常处理任务,"+zclist);
			log.info("添加到队列");
			for (SysFileConvertTask o : zclist) {
				o.setStatus(1);
				o.setVersion(1);
				o.setServerId(serverName);
				Integer c = convert.updateReturn(o);
				if(c != null && !c.equals(0)){
					queue.put(o);
				}
			}

			log.info("查询待执行任务");
			List<SysFileConvertTask> yclist = convert.pageThreeHundred(1);
			log.info("异常处理任务,"+yclist);
			log.info("添加到队列");
			for (SysFileConvertTask o : yclist) {
				o.setStatus(1);
				o.setVersion(2);
				o.setServerId(serverName);
				Integer c = convert.updateReturn(o);
				if(c != null && !c.equals(0)){
					queue.put(o);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		List<Object> list = new ArrayList<Object>();
		try {
			for (int i = 0; i < 1; i++) {
				list.add(i);
			}
			for (Object i : list) {
				//ConvertPDFTask.queue.put(new CallConvertPDF(i));
			}
				//new Thread(new ThreadConvertPDF()).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
