package com.yuxin.wx.util;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.company.mapper.CompanyServiceStaticMapper;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.resource.ResourceList;
import com.yuxin.wx.model.system.SysFileConvertTask;
import com.yuxin.wx.system.mapper.SysFileConvertTaskMapper;
import com.yuxin.wx.util.officeConvert.OfficeTransform;

public class CallConvertPDF implements Runnable {
	
	private Log log = LogFactory.getLog("log");
	
	private SysFileConvertTask o;
	private SysFileConvertTaskMapper convert;
	private CompanyServiceStaticMapper cssm;
	
	public CallConvertPDF(SysFileConvertTask o
			,SysFileConvertTaskMapper convert,CompanyServiceStaticMapper cssm){
		this.o = o;
		this.convert = convert;
		this.cssm = cssm;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			CompanyServiceStatic css = cssm.findByCompanyId(o.getCompanyId());
			long crs = Long.parseLong(css.getResourceStorage() != null ? css.getResourceStorage() : "0");
			o.setExcuteTime(new Date());
			convert.update(o);
			log.info("转换成pdf");
			String oldpath = o.getFilePath();
			String suffix = oldpath.substring(
					o.getFilePath().lastIndexOf(".")+1);
			
			String dir = "";
			if(suffix.equals("ppt") || suffix.equals("pptx")){
				dir = "ppt/";
			}else{
				dir = "docs/";
			}
			Map<String, String> map = OfficeTransform.transformPdf(oldpath,o.getCompanyId() + "/" + dir);
			String msg = map.get("msg").toString();
			if (msg.equals(JsonMsg.SUCCESS)) {
				// 转换为swf
				if(isEchang(2)){
					return;
				}
				if(isEchang(3)){
					return;
				}
				ResourceList rl = new ResourceList();
				rl.setId(o.getFileId());
				rl.setFilePath(map.get("filePath"));
				rl.setFileSize(map.get("size"));
				convert.updateResourceList(rl);
				o.setStatus(2);
				o.setCompleteTime(new Date());
				o.setExcepReason("成功");
				convert.update(o);
				
				long fs = Long.parseLong(rl.getFileSize() != null 
						? rl.getFileSize() : "0");
				
				css.setResourceStorage((crs + fs )+"");
			} else {
				if(o.getVersion().equals(1)){
					o.setStatus(3);
				}else{
					o.setStatus(4);
				}
				o.setExcepExcuteTime(new Date());
				o.setExcepReason(msg);
				convert.update(o);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(o.getVersion().equals(1)){
				o.setStatus(3);
			}else{
				o.setStatus(4);
			}
			o.setExcepExcuteTime(new Date());
			o.setExcepReason("任务线程其他异常，终止被动终止任务----"+e.getMessage());
			convert.update(o);
			log.error("任务线程其他异常，终止被动终止任务----" + e.getMessage());
		}
	}
	
	private boolean isEchang(int types){
		log.info("判断是否终止线程");
		if(Thread.interrupted()){
			if(o.getVersion().equals(1)){
				o.setStatus(3);
			}else{
				o.setStatus(4);
			}
			o.setExcepExcuteTime(new Date());
			String msg = "";
			if(types == 2){
				msg = "pdf转换超时";
			}else if(types == 3){
				msg = "上传超时";
			}
			o.setExcepReason("任务超时被终止，" + msg);
			convert.update(o);
			return true;
		}else{
			return false;
		}
	}
}
