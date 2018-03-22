package com.yuxin.wx.utils.sendMSG4ServiceWarning;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.system.ISysSmsLogService;
import com.yuxin.wx.api.system.ISysUseRecordNoticTaskService;
import com.yuxin.wx.model.system.SysSmsLog;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.smsClient.interfacej.SmsClientSend;
import com.yuxin.wx.vo.company.CompanyOrgMessageReadVo;
import com.yuxin.wx.vo.company.CompanyOrgMessageVo;

@Component
public class SendMSGUtil {
	
	private static Log log = LogFactory.getLog("log");
	
	private static ICompanyService companyServiceImpl;
	@Autowired
	public void setCompanyServiceImpl(ICompanyService companyServiceImpl) {
		SendMSGUtil.companyServiceImpl = companyServiceImpl;
	}
	
	private static ISysUseRecordNoticTaskService sysUseRecordNoticTaskServiceImpl;
	@Autowired
	public void setSysUseRecordNoticTaskServiceImpl(ISysUseRecordNoticTaskService sysUseRecordNoticTaskServiceImpl) {
		SendMSGUtil.sysUseRecordNoticTaskServiceImpl = sysUseRecordNoticTaskServiceImpl;
	}
	
	private static ISysSmsLogService sysSmsLogServiceimpl;
	@Autowired
	public void setSysSmsLogServiceimpl(ISysSmsLogService sysSmsLogServiceimpl) {
		SendMSGUtil.sysSmsLogServiceimpl = sysSmsLogServiceimpl;

	}

	/**
	 * 
	 * Class Name: SendMSGUtil.java
	 * @Description: 发送短信接口
	 * @author dongshuai
	 * @date 2016年12月1日 下午7:38:13
	 * @modifier
	 * @modify-date 2016年12月1日 下午7:38:13
	 * @version 1.0
	 * @param companyId
	 * @param msg
	 * @return
	 */
	@SuppressWarnings("finally")
	public static boolean sendSms(Integer companyId, String msg){
		boolean b = true;
		try {
			Integer errorCount = 0;
			//获取用户list
			List<Users> usersList = getUsersList(companyId);
			for (Users user : usersList) {
				boolean sendStatus = sendSmsFunction(companyId,user,msg);
				if(!sendStatus){
					errorCount++;
				}
			}
			//全部发送失败则记为失败
			if(errorCount.equals(usersList.size())){
				b = false;
			}
		} catch (Exception e) {
			log.error("服务提醒，发送短信失败",e);
			e.printStackTrace();
			b = false;
		} finally {
			return b;
		}
	}
	
	/**
	 * 
	 * Class Name: SendMSGUtil.java
	 * @Description: 发送站内信接口
	 * @author dongshuai
	 * @date 2016年12月1日 下午7:38:35
	 * @modifier
	 * @modify-date 2016年12月1日 下午7:38:35
	 * @version 1.0
	 * @param companyId
	 * @param msg
	 * @return
	 */
	@SuppressWarnings("finally")
	public static boolean sendMsg(Integer companyId, String msg, String title){
		boolean b = true;
		try {
			CompanyOrgMessageVo message = new CompanyOrgMessageVo();
			@SuppressWarnings("deprecation")
			String str=URLEncoder.encode(msg);
			message.setContent(str);
			message.setCompanyId(companyId);
			message.setTitle(title);
			message.setMessageType("COMPANY_MESSAGE_SERVICE");
			message.setStatus(1);
			message.setSendTime(new Date());
			companyServiceImpl.insertMsg(message);
			
			CompanyOrgMessageReadVo c=new CompanyOrgMessageReadVo();
			c.setOrgMessageId(message.getId());
			c.setDelFlag(0);
			c.setReadFlag(0);
			c.setCompanyId(companyId);
			companyServiceImpl.insertCompanyReadDetail(c);
		} catch (Exception e) {
			log.error("服务提醒，发送站内信失败",e);
			e.printStackTrace();
			b = false;
		} finally {
			return b;
		}
	}
	
	/**
	 * 
	 * Class Name: SendMSGUtil.java
	 * @Description: 获取用户list
	 * @author dongshuai
	 * @date 2016年12月1日 下午7:05:08
	 * @modifier
	 * @modify-date 2016年12月1日 下午7:05:08
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	private static List<Users> getUsersList(Integer companyId){
		return sysUseRecordNoticTaskServiceImpl.queryCompanyManageUsers(companyId);
	}
	
	/**
	 * 
	 * Class Name: SendMSGUtil.java
	 * @Description: 发送短信
	 * @author dongshuai
	 * @date 2016年12月1日 下午7:00:32
	 * @modifier
	 * @modify-date 2016年12月1日 下午7:00:32
	 * @version 1.0
	 * @param user
	 * @param msg
	 * @return
	 */
	private static boolean sendSmsFunction(Integer companyId, Users user, String msg){
		//手机为空判断
		if(null == user.getMobile() || "".equals(user.getMobile())){
			return false;
		}
		
		//发送短信
		msg = msg + "【在线网校】";
		String result = SmsClientSend.sendSmsTwo(user.getMobile().toString(),msg);
		
		SysSmsLog ssl = new SysSmsLog();
		ssl.setContent(msg);
		ssl.setSendTime(new Date());
		ssl.setBusinessType("sys-notic");
		ssl.setMobile(user.getMobile());
		
		String sendStatus = result.substring(result.indexOf("<message>"),result.indexOf("</message>"));
		sendStatus = sendStatus.substring(sendStatus.indexOf(">") + 1);
		if(sendStatus.equals("ok")){
			sendStatus = "发送成功";
		}
		
		ssl.setSendStatus(sendStatus);
		ssl.setCompanyId(companyId);
		sysSmsLogServiceimpl.insert(ssl);
		
		return "发送成功".equals(sendStatus)?true:false;
	}
	
	/**
	 * 
	 * Class Name: SendMSGUtil.java
	 * @Description: Double小数转换
	 * @author dongshuai
	 * @date 2016年12月5日 下午5:25:10
	 * @modifier
	 * @modify-date 2016年12月5日 下午5:25:10
	 * @version 1.0
	 * @param d
	 * @return
	 */
	public static String doubleTrans(Double d){
	    if(Math.round(d)-d==0){
	    	return String.valueOf((long)d.doubleValue());
	    }
	    return String.valueOf(d);
	}
}
