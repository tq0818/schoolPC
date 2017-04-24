package com.yuxin.wx.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.system.ISysSmsLogService;
import com.yuxin.wx.model.system.SysSmsLog;

@Component
public class SmslogUtil {
	
	private static ISysSmsLogService sysSmsLogServiceimpl;
	
	private static Log log = LogFactory.getLog("log");
	
	@Autowired
	public SmslogUtil(ISysSmsLogService sysSmsLogServiceimpl){
		this.sysSmsLogServiceimpl = sysSmsLogServiceimpl;
	}
	
	public SmslogUtil(){}
	
	@Async
	public static void setSmsLog(HttpServletRequest request,String mobile
			,String content,String businessType,String sendStatus,Integer uid
			){
		SysSmsLog ssl = new SysSmsLog();
		try {
			if(uid != 0){
				ssl.setUserId(uid);
			}
			ssl.setContent(content);
			ssl.setSendTime(new Date());
			ssl.setBusinessType(businessType);
			ssl.setMobile(mobile);
			
			if(request != null){
				String ip  = IpAddressUtil.getIpAddr(request);
				if(ip.indexOf(",")!=-1){
					String[] ips =ip.split(",");
					ip=ips[0];
				}
				ssl.setIp(ip);
			}
			ssl.setSendStatus(sendStatus);
			ssl.setCompanyId(WebUtils.getCurrentCompanyId()==null?0:WebUtils.getCurrentCompanyId());
			sysSmsLogServiceimpl.insert(ssl);
		} catch (Exception e) {
			log.error("添加日志错误，" + e.getMessage(),e);
			e.printStackTrace();
		}
	}

	public static ISysSmsLogService getSysSmsLogServiceimpl() {
		return sysSmsLogServiceimpl;
	}

	public static void setSysSmsLogServiceimpl(
			ISysSmsLogService sysSmsLogServiceimpl) {
		SmslogUtil.sysSmsLogServiceimpl = sysSmsLogServiceimpl;
	}
}
