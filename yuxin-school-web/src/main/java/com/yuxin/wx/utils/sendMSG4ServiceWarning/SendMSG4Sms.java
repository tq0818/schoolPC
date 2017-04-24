package com.yuxin.wx.utils.sendMSG4ServiceWarning;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.model.system.SysUseRecordNoticTask;

/**
 * 
 * @ClassName: SendMSG4Sms
 * @Description: 短信
 * @author dongshuai
 * @date 2016年12月2日 上午10:49:31
 * @modifier
 * @modify-date 2016年12月2日 上午10:49:31
 * @version 1.0
 */
public class SendMSG4Sms extends AbstractSendMSG{
	
	Log log = LogFactory.getLog("log");
	
	private SysUseRecordNoticTask sysUseRecordNoticTask;
	
	public SendMSG4Sms(SysUseRecordNoticTask sysUseRecordNoticTask){
		this.sysUseRecordNoticTask = sysUseRecordNoticTask;
	}
	
	@SuppressWarnings("finally")
	public boolean sendSms4Warn(){
		boolean b = true;
		try {
			String msg = "尊敬的客户您好，到目前为止，您的网校短信已使用"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getCurrtNum())+"条剩余"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getLastNum())+"条，为了不影响您的正常使用，请尽快购买短信。";
			b = SendMSGUtil.sendSms(sysUseRecordNoticTask.getCompanyId(), msg);
		} catch (Exception e) {
			log.error("短信发送短信预警出错",e);
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
			String msg = "尊敬的客户您好，到目前为止，您的网校短信已使用"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getCurrtNum())+"条剩余"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getLastNum())+"条，为了不影响您的正常使用，请尽快购买短信。<a href='http://manage.yunduoketang.com/companyMemberService/buyMessage'>点此购买短信</a>";
			b = SendMSGUtil.sendMsg(sysUseRecordNoticTask.getCompanyId(), msg, "短信数量剩余提醒");
		} catch (Exception e) {
			log.error("短信发送站内信预警出错",e);
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
