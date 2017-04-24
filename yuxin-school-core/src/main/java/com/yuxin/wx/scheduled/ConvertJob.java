package com.yuxin.wx.scheduled;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.company.mapper.CompanyServiceStaticMapper;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.resource.ResourceList;
import com.yuxin.wx.model.system.SysFileConvertTask;
import com.yuxin.wx.model.system.Task;
import com.yuxin.wx.system.mapper.SysFileConvertTaskMapper;
import com.yuxin.wx.util.officeConvert.OfficeThreadPool;
import com.yuxin.wx.util.officeConvert.OpenOfficeHandler;

@Component
@Transactional
public class ConvertJob implements Runnable{
	private Log log = LogFactory.getLog("log");
	
	private SysFileConvertTask job;
	@Autowired
	private SysFileConvertTaskMapper convert;
	@Autowired
	private CompanyServiceStaticMapper cssm;
	private OfficeThreadPool pool;
	private OpenOfficeHandler office;
	
	public ConvertJob(){

	}

	public ConvertJob (Task job){
		try{
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			convert=(SysFileConvertTaskMapper)wac.getBean(SysFileConvertTaskMapper.class);
			cssm=(CompanyServiceStaticMapper)wac.getBean(CompanyServiceStaticMapper.class);
			this.job=(SysFileConvertTask)job;
			this.pool=OfficeThreadPool.getNewInstance();
			this.office=pool.getThread();
		}catch(Exception ex){
			throw new RuntimeException("构造方法的参数必须是[com.yuxin.wx.model.system.SysFileConvertTask]类型",ex);
		}
	}
	
	@Override
	public void run() {
		try {
//			log.info("**********************转换开始["+System.currentTimeMillis()+"]*************************");
//			log.info("=============>>>>>>>>>转换文件["+job.getFilePath()+"]");
			CompanyServiceStatic css = cssm.findByCompanyId(job.getCompanyId());
			long crs = Long.parseLong(css.getResourceStorage() != null ? css.getResourceStorage() : "0");
			job.setExcuteTime(new Date());
			convert.update(job);
			String oldpath = job.getFilePath();
			String suffix = job.getFilePath().substring(
					job.getFilePath().lastIndexOf(".")+1);
			String dir = "";
			if(suffix.equals("ppt") || suffix.equals("pptx")){
				dir = "ppt/";
			}else{
				dir = "docs/";
			}
			
			Map<String, String> map = office.transformPdf(oldpath,job.getCompanyId() + "/" + dir);
			String msg = map.get("msg").toString();
			String pdfpath = map.get("pdf").toString();
			File pdffile = new File(pdfpath);
			if (msg.equals(JsonMsg.SUCCESS)) {
				log.info("===========>>>>>>>>>done! [" + pdfpath+"]");
				ResourceList rl = new ResourceList();
				rl.setId(job.getFileId());
				rl.setFilePath(map.get("filePath"));
				rl.setFileSize(map.get("size"));
				rl.setSourcePath(map.get("sourcePath"));
				convert.updateResourceList(rl);
				job.setStatus(2);
				job.setCompleteTime(new Date());
				job.setExcepReason("成功");
				convert.update(job);
				
				long fs = Long.parseLong(rl.getFileSize() != null 
						? rl.getFileSize() : "0");
				
				css.setResourceStorage((crs + fs )+"");
				new File(".~lock."+pdffile.getName()+"#").delete();
				//pdffile.delete();
			} else {
				if(job.getVersion().equals(1)){
					job.setStatus(3);
				}else{
					job.setStatus(4);
				}
				job.setExcepExcuteTime(new Date());
				job.setExcepReason(msg);
				convert.update(job);
				new File(".~lock."+pdffile.getName()+"#").delete();
				pdffile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(job.getVersion().equals(1)){
				job.setStatus(3);
			}else{
				job.setStatus(4);
			}
			job.setExcepExcuteTime(new Date());
			job.setExcepReason("任务线程其他异常，被动终止任务----"+e.getMessage()+getExceptionMessage(e));
			convert.update(job);
			log.error("任务线程其他异常，被动终止任务",e);
		}
		log.info("**********************转换完成,用时["+System.currentTimeMillis()+"]*************************");
	}
	
	private String getExceptionMessage(Exception e){
		StringBuilder builder=new StringBuilder();
		e.printStackTrace();
		
		for(StackTraceElement ele: e.getStackTrace()){
			builder.append(" ["+ele.getClassName()+"."+ele.getMethodName()+"]"+"("+ele.getLineNumber()+")");
		}
		return builder.toString();
	}

}
