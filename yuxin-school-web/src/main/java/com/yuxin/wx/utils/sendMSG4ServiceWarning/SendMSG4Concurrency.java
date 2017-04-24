package com.yuxin.wx.utils.sendMSG4ServiceWarning;

import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.model.system.SysUseRecordNoticTask;

/**
 * 
 * @ClassName: SendMSG4Concurrency
 * @Description: 并发
 * @author dongshuai
 * @date 2016年12月1日 下午3:27:23
 * @modifier
 * @modify-date 2016年12月1日 下午3:27:23
 * @version 1.0
 */
public class SendMSG4Concurrency extends AbstractSendMSG{
	
	Log log = LogFactory.getLog("log");
	
	private SysUseRecordNoticTask sysUseRecordNoticTask;
	
	public SendMSG4Concurrency(SysUseRecordNoticTask sysUseRecordNoticTask){
		this.sysUseRecordNoticTask = sysUseRecordNoticTask;
	}
	
	@SuppressWarnings("finally")
	public boolean sendSms4Warn(){
		boolean b = true;
		try {
			String msg = "尊敬的客户您好，您的网校"+(sysUseRecordNoticTask.getRecordDateMonth()%100)+"月的最大并发数为"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getTotalNum())+"个，目前已达到"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getCurrtNum())+"个，为了不影响您的正常使用，请尽快购买并发数。";
			b = SendMSGUtil.sendSms(sysUseRecordNoticTask.getCompanyId(), msg);
		} catch (Exception e) {
			log.error("并发发送短信预警出错",e);
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
			String msg = "尊敬的客户您好，您的网校并发服务将在"+new SimpleDateFormat("yyyy-MM-dd").format(sysUseRecordNoticTask.getDeadDate())+"日到期，为了不影响您的正常使用，请尽快续费并发服务。";
			b = SendMSGUtil.sendSms(sysUseRecordNoticTask.getCompanyId(), msg);
		} catch (Exception e) {
			log.error("并发发送短信到期提醒出错",e);
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
			String msg = "尊敬的客户您好，您的网校"+(sysUseRecordNoticTask.getRecordDateMonth()%100)+"月最大并发数为"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getTotalNum())+"个，目前已达到"+SendMSGUtil.doubleTrans(sysUseRecordNoticTask.getCurrtNum())+"个，为了不影响您的正常使用，请尽快购买并发数。<a href='http://manage.yunduoketang.com/companyMemberService/buyFlow'>点此购买并发数</a>";
			b = SendMSGUtil.sendMsg(sysUseRecordNoticTask.getCompanyId(), msg, "并发数量提醒");
		} catch (Exception e) {
			log.error("并发发送站内信预警出错",e);
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
			String msg = "尊敬的客户您好，您的网校并发服务将在"+new SimpleDateFormat("yyyy-MM-dd").format(sysUseRecordNoticTask.getDeadDate())+"日到期，为了不影响您的正常使用，请尽快续费并发服务。<a href='http://manage.yunduoketang.com/companyMemberService/buyFlow'>点此续费并发服务</a>";
			b = SendMSGUtil.sendMsg(sysUseRecordNoticTask.getCompanyId(), msg, "并发服务到期提醒");
		} catch (Exception e) {
			log.error("并发发送站内信到期提醒出错",e);
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
