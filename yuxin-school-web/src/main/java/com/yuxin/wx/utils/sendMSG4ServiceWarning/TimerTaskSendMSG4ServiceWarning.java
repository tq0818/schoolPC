package com.yuxin.wx.utils.sendMSG4ServiceWarning;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.system.ISysUseRecordNoticTaskService;
import com.yuxin.wx.model.system.SysUseRecordNoticTask;
import com.yuxin.wx.utils.PropertiesUtil;

@Component
public class TimerTaskSendMSG4ServiceWarning {
	
	static Log log = LogFactory.getLog("log");
	
	
	private static ISysUseRecordNoticTaskService sysUseRecordNoticTaskServiceImpl;
	@Autowired
	public void setSysUseRecordNoticTaskServiceImpl(ISysUseRecordNoticTaskService sysUseRecordNoticTaskServiceImpl) {
		TimerTaskSendMSG4ServiceWarning.sysUseRecordNoticTaskServiceImpl = sysUseRecordNoticTaskServiceImpl;
	}
	
	@Autowired
	private PropertiesUtil properties;
	
	@Scheduled(cron = "0 0 9-17/1 * * ?")
	public void sendMSG4ServiceWarning(){
		if(!properties.getServiceWarningSend().equals("on")){
			return;
		}
		/*
		 * 0：定时执行 1：手动执行
		 */
		serviceWarning(0);
	}
	
	@SuppressWarnings("finally")
	public static JSONObject serviceWarning(Integer flag){
		log.info("定时任务 服务预警("+flag+") 开始");
		JSONObject json = new JSONObject();
		boolean b = true;
		try {
			SendMSG sendMSG = null;
			
			List<SysUseRecordNoticTask> sendList = querySendList();
			if(null == sendList){
				log.error("定时任务 服务预警：查询待发列表失败");
				b = false;
				json.put("result", b);
				json.put("msg", "定时任务 服务预警：查询待发列表失败");
				return json;
			}
			if(sendList.size()<=0){
				log.info("定时任务 服务预警：待发列表数量为0");
				b = false;
				json.put("result", b);
				json.put("msg", "定时任务 服务预警：待发列表数量为0");
				return json;
			}
			for (SysUseRecordNoticTask send : sendList) {
				/*	0	并发			0   预警
					1	空间			1   到期提醒
					2	流量
					3	短信
					4	邮件
					5	网校服务期
					6	学员账号
					7	在线人数 				*/
				boolean msgResult = false;	//站内信发送状态初始化
				boolean smsResult = false;	//短信发送状态初始化
				
				//判断
				switch(send.getBussinessType()){
					case 0:
						sendMSG = SendMSGFactory.createSendMSGConcurrency(send);
						break;
					case 1:
						sendMSG = SendMSGFactory.createSendMSG4Space(send);
						break;
					case 2:
						sendMSG = SendMSGFactory.createSendMSG4Flow(send);
						break;
					case 3:
						sendMSG = SendMSGFactory.createSendMSG4Sms(send);
						break;
					case 4:
						sendMSG = SendMSGFactory.createSendMSG4Mail(send);
						break;
					case 5:
						sendMSG = SendMSGFactory.createSendMSG4ServerTime(send);
						break;
					case 6:
						sendMSG = SendMSGFactory.createSendMSG4StudentNum(send);
						break;
					case 7:
						sendMSG = SendMSGFactory.createSendMSG4OnlineNum(send);
						break;
					default:
						sendMSG = null;
				}
				if(null == sendMSG){
					log.error("定时任务 服务预警：创建信息失败");
					b = false;
					json.put("result", b);
					json.put("msg", "定时任务 服务预警：创建信息失败");
					return json;
				}
				//发送
				switch (send.getNoticType()) {
					case 0:
						msgResult = sendMSG.sendMsg4Warn();
						smsResult = sendMSG.sendSms4Warn();
						break;
					case 1:
						msgResult = sendMSG.sendMsg4Due();
						smsResult = sendMSG.sendSms4Due();
						break;
					default:
						break;
				}
				
				//更新状态
				updateSendResult(send,msgResult,smsResult);
				
				json.put("result", b);
				json.put("msg", "执行成功");
			}
		} catch (Exception e) {
			b = false;
			log.error("定时任务 服务预警出错",e);
			json.put("result", b);
			json.put("msg", "定时任务 服务预警出错");
			e.printStackTrace();
		} finally {
			log.info("定时任务 服务预警("+flag+") 结束");
			return json;
		}
	}
	
	private static void updateSendResult(SysUseRecordNoticTask send, boolean msgResult, boolean smsResult) {
		
		//异常信息
		String excepDetail = "";
		
		/* 站内信短信 两者全部失败则记为失败  */
		if(!msgResult && !smsResult){
			//更新状态
			if(2 != (send.getRecordStatus())){
				send.setRecordStatus(2);
			}else{
				/* 处理失败则记为再次失败 */
				send.setRecordStatus(3);
			}
		} else {
			send.setRecordStatus(1);
		}
		
		//记录异常信息
		if(null != send.getExcepDetail()){
			excepDetail += send.getExcepDetail();
		}
		send.setExcepDetail(excepDetail + "【记录时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 短信发送状态：" + smsResult + " 站内信发送状态：" + msgResult + "】" );
		send.setLastExecTime(new Date());
		
		sysUseRecordNoticTaskServiceImpl.update(send);
	}

	/**
	 * 
	 * Class Name: TimerTaskSendMSG4ServiceWarning.java
	 * @Description: 待发列表
	 * @author dongshuai
	 * @date 2016年12月2日 下午1:31:37
	 * @modifier
	 * @modify-date 2016年12月2日 下午1:31:37
	 * @version 1.0
	 * @return
	 */
	private static List<SysUseRecordNoticTask> querySendList(){
		return sysUseRecordNoticTaskServiceImpl.queryNeedSendList();
	}
}
