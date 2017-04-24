package com.yuxin.wx.utils.sendMSG4ServiceWarning;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.model.system.SysUseRecordNoticTask;

/**
 * 
 * @ClassName: SendMSG4Mail
 * @Description: 邮件
 * @author dongshuai
 * @date 2016年12月2日 上午10:53:19
 * @modifier
 * @modify-date 2016年12月2日 上午10:53:19
 * @version 1.0
 */
public class SendMSG4Mail extends AbstractSendMSG{
	
	Log log = LogFactory.getLog("log");
	
	private SysUseRecordNoticTask sysUseRecordNoticTask;
	
	public SendMSG4Mail(SysUseRecordNoticTask sysUseRecordNoticTask){
		this.sysUseRecordNoticTask = sysUseRecordNoticTask;
	}
	
	@SuppressWarnings("finally")
	public boolean sendSms4Warn(){
		boolean b = true;
		try {
			String msg = "尊敬的客户您好，到目前为止，您的网校邮件已使用"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getCurrtNum())+"封剩余"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getLastNum())+"封，为了不影响您的正常使用，请尽快购买邮件";
			b = SendMSGUtil.sendSms(sysUseRecordNoticTask.getCompanyId(), msg);
		} catch (Exception e) {
			log.error("邮件发送短信预警出错",e);
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
			String msg = "尊敬的客户您好，到目前为止，您的网校邮件已使用"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getCurrtNum())+"条剩余"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getLastNum())+"封，为了不影响您的正常使用，请尽快购买邮件。<a href='http://manage.yunduoketang.com/companyMemberService/buyEmail'>点此购买邮件</a>";
			b = SendMSGUtil.sendMsg(sysUseRecordNoticTask.getCompanyId(), msg, "邮件数量剩余提醒");
		} catch (Exception e) {
			log.error("邮件发送站内信预警出错",e);
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
