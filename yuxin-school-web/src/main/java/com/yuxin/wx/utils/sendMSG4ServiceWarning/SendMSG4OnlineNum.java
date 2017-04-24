package com.yuxin.wx.utils.sendMSG4ServiceWarning;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.model.system.SysUseRecordNoticTask;

/**
 * 
 * @ClassName: SendMSG4OnlineNum
 * @Description: 在线人数
 * @author dongshuai
 * @date 2016年12月2日 上午11:04:53
 * @modifier
 * @modify-date 2016年12月2日 上午11:04:53
 * @version 1.0
 */
public class SendMSG4OnlineNum extends AbstractSendMSG{
	
	Log log = LogFactory.getLog("log");
	
	private SysUseRecordNoticTask sysUseRecordNoticTask;
	
	public SendMSG4OnlineNum(SysUseRecordNoticTask sysUseRecordNoticTask){
		this.sysUseRecordNoticTask = sysUseRecordNoticTask;
	}
	
	@SuppressWarnings("finally")
	public boolean sendSms4Warn(){
		boolean b = true;
		try {
			String msg = "尊敬的客户您好，您的网校目前同时在线学员数已达到"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getCurrtNum())+"，最多可支持"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getTotalNum())+"名学员同时在线，为了不影响您的正常使用，请尽快联系在线客服升级网校服务。";
			b = SendMSGUtil.sendSms(sysUseRecordNoticTask.getCompanyId(), msg);
		} catch (Exception e) {
			log.error("在线人数发送短信预警出错",e);
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
			String msg = "尊敬的客户您好，到目前为止，您的网校同时在线学员数已达到"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getCurrtNum())+"，最多可支持"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getTotalNum())+"学员同时在线，为了不影响您的正常使用，请尽快联系在线客服升级网校服务。";
			b = SendMSGUtil.sendMsg(sysUseRecordNoticTask.getCompanyId(), msg, "学员在线人数提醒");
		} catch (Exception e) {
			log.error("在线人数发送站内信预警出错",e);
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
