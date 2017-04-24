package com.yuxin.wx.util;

import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.company.mapper.CompanyServiceStaticMapper;
import com.yuxin.wx.model.system.SysFileConvertTask;
import com.yuxin.wx.scheduled.ConvertPDFTask;
import com.yuxin.wx.system.mapper.SysFileConvertTaskMapper;

public class ThreadConvertPDF implements Runnable {
	
	private Log log = LogFactory.getLog("log");
	private SysFileConvertTaskMapper convert;
	private CompanyServiceStaticMapper css;
	private Integer i;
	
	public ThreadConvertPDF(SysFileConvertTaskMapper convert,
			CompanyServiceStaticMapper css,Integer i){
		this.convert = convert;
		this.css = css;
		this.i = i;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		SysFileConvertTask o = null;
		try {
			while (true) {
				log.info("准备执行执行,线程"+i);
				o = ConvertPDFTask.queue.take();
				Future<String> ft = ConvertPDFTask.executor.submit(
						new CallConvertPDF(o,convert,css),"success");
				log.info("等待执行,线程"+i);
				String res = "";
				try {
					Long s = System.currentTimeMillis(); 
					res = ft.get(30, TimeUnit.MINUTES);
					log.info("线程"+i+",任务执行时间," + (System.currentTimeMillis() - s));
				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					log.info("等待超时,线程"+i);
					ft.cancel(true);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("线程"+i+",运行期间其他异常,本线程终止" + e.getMessage());
			//当前线程异常关闭后重新开启一个线程
			if(o.getVersion().equals(1)){
				o.setStatus(3);
			}else{
				o.setStatus(4);
			}
			o.setExcepExcuteTime(new Date());
			o.setExcepReason("线程"+i+",运行期间其他异常,本线程终止" + e.getMessage());
			convert.update(o);
			new Thread(new ThreadConvertPDF(convert,css,i)).start();
			return;
		}
	}

}
