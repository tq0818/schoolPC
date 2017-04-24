package com.yuxin.wx.utils.sendMSG4ServiceWarning;

import com.yuxin.wx.model.system.SysUseRecordNoticTask;

public class SendMSGFactory {
	
	/* 并发 */
	public static SendMSG createSendMSGConcurrency(SysUseRecordNoticTask send){
		return new SendMSG4Concurrency(send);
	}
	
	/* 空间 */
	public static SendMSG createSendMSG4Space(SysUseRecordNoticTask send){
		return new SendMSG4Space(send);
	}
	
	/* 流量 */
	public static SendMSG createSendMSG4Flow(SysUseRecordNoticTask send){
		return new SendMSG4Flow(send);
	}
	
	/* 短信 */
	public static SendMSG createSendMSG4Sms(SysUseRecordNoticTask send){
		return new SendMSG4Sms(send);
	}
	
	/* 邮件 */
	public static SendMSG createSendMSG4Mail(SysUseRecordNoticTask send){
		return new SendMSG4Mail(send);
	}
	
	/* 网校服务期 */
	public static SendMSG createSendMSG4ServerTime(SysUseRecordNoticTask send){
		return new SendMSG4ServerTime(send);
	}
	
	/* 学员账号 */
	public static SendMSG createSendMSG4StudentNum(SysUseRecordNoticTask send){
		return new SendMSG4StudentNum(send);
	}
	
	/* 在线人数 */
	public static SendMSG createSendMSG4OnlineNum(SysUseRecordNoticTask send){
		return new SendMSG4OnlineNum(send);
	}
}
