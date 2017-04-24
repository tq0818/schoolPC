package com.yuxin.wx.utils.sendMSG4ServiceWarning;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.model.system.SysUseRecordNoticTask;

/**
 * 
 * @ClassName: SendMSG4Flow
 * @Description: 流量
 * @author dongshuai
 * @date 2016年12月1日 下午4:46:56
 * @modifier
 * @modify-date 2016年12月1日 下午4:46:56
 * @version 1.0
 */
public class SendMSG4Flow extends AbstractSendMSG{
	
	Log log = LogFactory.getLog("log");
	
	private SysUseRecordNoticTask sysUseRecordNoticTask;
	
	public SendMSG4Flow(SysUseRecordNoticTask sysUseRecordNoticTask){
		this.sysUseRecordNoticTask = sysUseRecordNoticTask;
	}
	
	@SuppressWarnings("finally")
	public boolean sendSms4Warn(){
		boolean b = true;
		try {
			String msg = "尊敬的客户您好，到目前为止，您的网校流量已使用"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getCurrtNum())+"G剩余"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getLastNum())+"G，为了不影响您的正常使用，请尽快购买网校流量。";
			b = SendMSGUtil.sendSms(sysUseRecordNoticTask.getCompanyId(), msg);
		} catch (Exception e) {
			log.error("流量发送短信预警出错",e);
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
			String msg = "尊敬的客户您好，到目前为止，您的网校流量已使用"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getCurrtNum())+"G剩余"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getLastNum())+"G，为了不影响您的正常使用，请尽快购买网校流量。<a href='http://manage.yunduoketang.com/companyMemberService/showByVideoTwo'>点此购买流量</a>";
			b = SendMSGUtil.sendMsg(sysUseRecordNoticTask.getCompanyId(), msg, "流量剩余提醒");
		} catch (Exception e) {
			log.error("流量发送站内信预警出错",e);
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
