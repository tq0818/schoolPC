package com.yuxin.wx.utils.sendMSG4ServiceWarning;

import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.model.system.SysUseRecordNoticTask;

/**
 * 
 * @ClassName: SendMSG4Space
 * @Description: 空间
 * @author dongshuai
 * @date 2016年12月1日 下午3:55:54
 * @modifier
 * @modify-date 2016年12月1日 下午3:55:54
 * @version 1.0
 */
public class SendMSG4Space extends AbstractSendMSG{
	
	Log log = LogFactory.getLog("log");
	
	private SysUseRecordNoticTask sysUseRecordNoticTask;
	
	public SendMSG4Space(SysUseRecordNoticTask sysUseRecordNoticTask){
		this.sysUseRecordNoticTask = sysUseRecordNoticTask;
	}
	
	@SuppressWarnings("finally")
	public boolean sendSms4Warn(){
		boolean b = true;
		try {
			String msg = "尊敬的客户您好，到目前为止，您的网校空间已使用"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getCurrtNum())+"G剩余"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getLastNum())+"G，为了不影响您的正常使用，请尽快提升空间。";
			b = SendMSGUtil.sendSms(sysUseRecordNoticTask.getCompanyId(), msg);
		} catch (Exception e) {
			log.error("空间发送短信预警出错",e);
			e.printStackTrace();
			b = false;
		} finally {
			return b;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean sendSms4Due(){
		boolean b = true;
		try {
			String msg = "尊敬的客户您好，您的网校空间服务将在"+new SimpleDateFormat("yyyy-MM-dd").format(sysUseRecordNoticTask.getDeadDate())+"日到期，为了不影响您的正常使用，请尽快续费空间服务。";
			b = SendMSGUtil.sendSms(sysUseRecordNoticTask.getCompanyId(), msg);
		} catch (Exception e) {
			log.error("空间发送短信到期提醒出错",e);
			e.printStackTrace();
			b = false;
		} finally {
			return b;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean sendMsg4Warn(){
		boolean b = true;
		try {
			String msg = "尊敬的客户您好，到目前为止，您的网校空间已使用"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getCurrtNum())+"G剩余"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getLastNum())+"G，为了不影响您的正常使用，请尽快提升空间。<a href='http://manage.yunduoketang.com/companyMemberService/showByVideo'>点此购买空间</a>";
			b = SendMSGUtil.sendMsg(sysUseRecordNoticTask.getCompanyId(), msg, "空间数量剩余提醒");
		} catch (Exception e) {
			log.error("空间发送站内信预警出错",e);
			e.printStackTrace();
			b = false;
		} finally {
			return b;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean sendMsg4Due(){
		boolean b = true;
		try {
			String msg = "尊敬的客户您好，您的网校空间服务将在"+new SimpleDateFormat("yyyy-MM-dd").format(sysUseRecordNoticTask.getDeadDate())+"日到期，为了不影响您的正常使用，请尽快续费空间服务。<a href='http://manage.yunduoketang.com/companyMemberService/showByVideo'>点此续费空间服务</a>";
			b = SendMSGUtil.sendMsg(sysUseRecordNoticTask.getCompanyId(), msg, "空间服务到期提醒");
		} catch (Exception e) {
			log.error("空间发送站内信预警出错",e);
			e.printStackTrace();
			b = false;
		} finally {
			return b;
		}
	}

	public SysUseRecordNoticTask getSysUseRecordNoticTask() {
		return sysUseRecordNoticTask;
	}

	public void setSysUseRecordNoticTask(SysUseRecordNoticTask sysUseRecordNoticTask) {
		this.sysUseRecordNoticTask = sysUseRecordNoticTask;
	}
}
