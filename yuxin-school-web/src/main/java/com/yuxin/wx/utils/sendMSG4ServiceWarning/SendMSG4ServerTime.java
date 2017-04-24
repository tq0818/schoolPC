package com.yuxin.wx.utils.sendMSG4ServiceWarning;

import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.model.system.SysUseRecordNoticTask;

/**
 * 
 * @ClassName: SendMSG4ServerTime
 * @Description: 网校服务期
 * @author dongshuai
 * @date 2016年12月2日 上午10:56:59
 * @modifier
 * @modify-date 2016年12月2日 上午10:56:59
 * @version 1.0
 */
public class SendMSG4ServerTime extends AbstractSendMSG{
	
	Log log = LogFactory.getLog("log");
	
	private SysUseRecordNoticTask sysUseRecordNoticTask;
	
	public SendMSG4ServerTime(SysUseRecordNoticTask sysUseRecordNoticTask){
		this.sysUseRecordNoticTask = sysUseRecordNoticTask;
	}
	
	@SuppressWarnings("finally")
	public boolean sendSms4Due(){
		boolean b = true;
		try {
			String msg = "尊敬的客户您好，您的网校将在"+new SimpleDateFormat("yyyy-MM-dd").format(sysUseRecordNoticTask.getDeadDate())+"日到期，为了不影响您的正常使用，请尽快联系在线客服续费网校服务。";
			b = SendMSGUtil.sendSms(sysUseRecordNoticTask.getCompanyId(), msg);
		} catch (Exception e) {
			log.error("网校服务期发送短信到期提醒出错",e);
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
			String msg = "尊敬的客户您好，您的网校将在"+new SimpleDateFormat("yyyy-MM-dd").format(sysUseRecordNoticTask.getDeadDate())+"日到期，为了不影响您的正常使用，请尽快联系在线客服续费网校服务。";
			b = SendMSGUtil.sendMsg(sysUseRecordNoticTask.getCompanyId(), msg, "网校服务到期提醒");
		} catch (Exception e) {
			log.error("网校服务期发送站内信到期提醒出错",e);
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
