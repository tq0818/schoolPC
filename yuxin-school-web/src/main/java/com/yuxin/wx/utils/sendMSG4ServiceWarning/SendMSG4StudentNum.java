package com.yuxin.wx.utils.sendMSG4ServiceWarning;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.model.system.SysUseRecordNoticTask;

/**
 * 
 * @ClassName: SendMSG4StudentNum
 * @Description: 学员账号
 * @author dongshuai
 * @date 2016年12月2日 上午11:00:17
 * @modifier
 * @modify-date 2016年12月2日 上午11:00:17
 * @version 1.0
 */
public class SendMSG4StudentNum extends AbstractSendMSG{
	
	Log log = LogFactory.getLog("log");
	
	private SysUseRecordNoticTask sysUseRecordNoticTask;
	
	public SendMSG4StudentNum(SysUseRecordNoticTask sysUseRecordNoticTask){
		this.sysUseRecordNoticTask = sysUseRecordNoticTask;
	}
	
	@SuppressWarnings("finally")
	public boolean sendSms4Warn(){
		boolean b = true;
		try {
			String msg = "尊敬的客户您好，到目前为止，您的网校已招收了"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getCurrtNum())+"名学员，最多可招收"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getTotalNum())+"名学员，为了不影响您的正常使用，请尽快联系在线客服升级网校服务。";
			b = SendMSGUtil.sendSms(sysUseRecordNoticTask.getCompanyId(), msg);
		} catch (Exception e) {
			log.error("学员账号发送短信预警出错",e);
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
			String msg = "尊敬的客户您好，到目前为止，您的网校已招收了"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getCurrtNum())+"名学员，最多可招收"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getTotalNum())+"学员，为了不影响您的正常使用，请尽快联系在线客服升级网校服务";
			b = SendMSGUtil.sendMsg(sysUseRecordNoticTask.getCompanyId(), msg, "招收学员数量提醒");
		} catch (Exception e) {
			log.error("学员账号发送站内信预警出错",e);
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
