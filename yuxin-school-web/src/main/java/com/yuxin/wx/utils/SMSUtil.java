package com.yuxin.wx.utils;

import com.yuxin.wx.api.system.ISysBlackListService;
import com.yuxin.wx.api.system.ISysSmsLogService;
import com.yuxin.wx.model.system.SysBlackList;
import com.yuxin.wx.model.system.SysSmsLog;
import com.yuxin.wx.util.JedisUtil;
import com.yuxin.wx.util.smsClient.interfacej.SmsClientSend;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class SMSUtil {
	private static Log _log =LogFactory.getLog("log");
	/**
	 * 注册短信模板
	 */
	public static final String SMS_TEMPLETE_REGISTER="21590";
	/**
	 * 找回密码短信模板
	 */
	public static final String SMS_TEMPLETE_FORGET="21589";
	/**
	 * 重置手机号
	 */
	public static final String SMS_TEMPLETE_RESETMOBILE="21588";

	/**
	 * 设置支付信息
	 */
	public static final String SMS_TEMPLETE_SETPAYINFO="92606";

	private static ISysSmsLogService sysSmsLogServiceimpl;

	private static ISysBlackListService sysBlackListServiceImpl;

	@Autowired
	public  void setSysSmsLogServiceimpl(ISysSmsLogService sysSmsLogServiceimpl) {
		SMSUtil.sysSmsLogServiceimpl = sysSmsLogServiceimpl;
	}

	@Autowired
	public void setSysBlackListServiceImpl(ISysBlackListService sysBlackListServiceImpl) {
		SMSUtil.sysBlackListServiceImpl = sysBlackListServiceImpl;
	}



	private SMSUtil() {

	}

	/**
	 * 校验验证码
	 * @param session
	 * @param paramCode
	 * @return
	 */
	public static Boolean validateCode(HttpSession session,String paramCode){
		String code=JedisUtil.getString("sms_code_"+session.getId());
		_log.info("msg:///code="+code);
		_log.info("msg:///paramCode="+paramCode);
		if(StringUtils.isNotBlank(code)){
			String c=code.split(",")[0];
			String n=code.split(",")[1];
			if(c.equals(paramCode)){
				//一个验证码能用两次，ajax一次，submit一次
				if("0".equals(n)){
					JedisUtil.put("sms_code_"+session.getId(), c+",1",600);
				}
				if("1".equals(n)){
					JedisUtil.deleteByKey("sms_code_"+session.getId());
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 生成短信验证码
	 * @return  验证码
	 * @author chopin
	 * @date 2015-4-7
	 */
	public static String generate(){
		//验证码的长度,默认6位
		final int pwd_len = 6;
		//35是因为数组是从0开始的，26个字母+10个数字
		final int  maxNum = 36;
		int i;  //生成的随机数
		int count = 0; //生成的密码的长度
		char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while(count < pwd_len){
			//生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum));  //生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count ++;
			}
		}

		return pwd.toString();
	}

	/**
	 * 发送短信验证码
	 * @param request
	 * @param phoneNum
	 * @throws Exception
	 */
	public static void sendSMSCode(HttpServletRequest request,String phoneNum,String templeteId)throws Exception{
		HttpSession session=request.getSession();
		String businessType="";
		if("21590".equals(businessType)){
			businessType="register";
		}
		if("21589".equals(businessType)){
			businessType="rspwd";
		}
		if("21588".equals(businessType)){
			businessType="rsmobile";
		}
		if("92606".equals(businessType)){
			businessType="setpayinfo";
		}
		_log.info("msg:///businessType="+businessType);
		Integer userId=WebUtils.getCurrentUserId(request);
		String ip=WebUtils.getIpAddr(request);
		//生成一个验证码
		String code=generate();
		/*
		//检查是否恶意发送短信,规则如下：
		Set<String> ww=JedisUtil.getKeysByPattern("sms_brecord_*"+phoneNum+"*");
		Set<String> xx=JedisUtil.getKeysByPattern("sms_brecord_*"+session.getId()+"*");
		Set<String> yy=JedisUtil.getKeysByPattern("sms_brecord_*"+userId+"*");
		if(userId==null){
			yy=new HashSet<String>();
		}
		Set<String> zz=JedisUtil.getKeysByPattern("sms_brecord_*"+ip+"*");
		Set<String> keys=new HashSet<String>();
		keys.addAll(ww);
		keys.addAll(xx);
		keys.addAll(yy);
		keys.addAll(zz);
		//10分钟内只能发送3条
		//1分钟内只能发送1条
		////①  24小时内只能发送100条
		if(keys.size()>=100){
			//记黑名单 4个维度记，彻底封杀
//			addBlackList(session.getId(),phoneNum,businessType,ip,""+userId);
//			return;
		}
		Date lastDate=null;
		List lastThreeRecords=new ArrayList();
		for(String key : keys){
			List records=JedisUtil.getList(key);
			if(records.size()>=100){
				//记黑名单 4个维度记，彻底封杀
				addBlackList(session.getId(),phoneNum,businessType,ip,""+userId);
				return;
			}
			if(records.size()>0){
				SysSmsLog l=(SysSmsLog)records.get(records.size()-1);
				if(lastDate==null){
					lastDate=l.getSendTime();
				}
				if(l.getSendTime().after(lastDate)){
					lastDate=l.getSendTime();
				}
				if((new Date()).getTime()-l.getSendTime().getTime()<=1000*60*10){
					lastThreeRecords.add(l);
				}
			}else{
				lastDate=new Date();
			}

		}

		///②   10分钟内只能发送3条
		if(lastThreeRecords.size()>=3){
			return;
		}

		////③  1分钟内只能发送1条
//		if(lastDate!=null && System.currentTimeMillis()-lastDate.getTime()<=1000*60){
//			return;
//		}
		*/
		//redis记录sessionID、验证码
		//验证码10分钟后失效
		JedisUtil.put("sms_code_"+session.getId(),code+",0",600);

		//记录发送历史
		SysSmsLog log=new SysSmsLog();
		log.setBusinessType(businessType);
		log.setIp(ip);
		log.setMobile(phoneNum);
		log.setSendTime(new Date());
		log.setUserId(userId);
		if(templeteId.equals(SMS_TEMPLETE_REGISTER)){
			log.setContent("【在线网校】您正在进行注册操作，本次操作的验证码是"+code+"。");
		}else if(templeteId.equals(SMS_TEMPLETE_FORGET)){
			log.setContent("【在线网校】您正在进行密码找回操作，本次操作的验证码是"+code+"。");
		}else if(templeteId.equals(SMS_TEMPLETE_SETPAYINFO)){
			log.setContent("【在线网校】设置支付信息验证码是："+code+"，请您尽快填写。如非本人操作，请忽略本条短信");
		}else{
			log.setContent("【在线网校】您正在进行重置登录账号操作，本次操作的验证码是"+code+"。");
		}

		try{
			//SMSHandler.send(phoneNum, templeteId, new String[]{code});
			SmsClientSend.sendSms(phoneNum,log.getContent());
			log.setSendStatus("1");
		}catch(Exception ex){
			log.setSendStatus("0");
			_log.error("短信发送失败",ex);
			throw new Exception(ex);
		}finally{
			//redis记录用户发送短信历史,保留24小时
			List list=JedisUtil.getList("sms_brecord_"+session.getId()+"_"+phoneNum+"_"+userId+"_"+ip);
			if(list==null){
				list=new ArrayList();
			}
			list.add(log);
			JedisUtil.put("sms_brecord_"+session.getId()+"_"+phoneNum+"_"+userId+"_"+ip, list,3600*24);
			// db记录用户发送短信历史
			try{
				sysSmsLogServiceimpl.insert(log);
			}catch(Exception e){
				_log.error("短信发送记录入库失败",e);
				e.printStackTrace();
			}

			String code1=JedisUtil.getString("sms_code_"+session.getId());
			_log.info("msg:///发送code="+code1);
		}

	}

	private static void addBlackList(String sessionId,String phoneNum,String businessType,String ip,String userId){
		SysBlackList bl=new SysBlackList();
		bl.setTagKey("sessionId");
		bl.setTagValue(sessionId);
		bl.setFlag(1);
		if("register".equals(businessType)){
			bl.setRecordReason("10201001");
		}
		if("rspwd".equals(businessType)){
			bl.setRecordReason("10201002");
		}
		if("rsmobile".equals(businessType)){
			bl.setRecordReason("10201003");
		}
		sysBlackListServiceImpl.insert(bl);

		bl.setTagKey("ip");
		bl.setTagValue(ip);
		bl.setFlag(1);
		if("register".equals(businessType)){
			bl.setRecordReason("10201001");
		}
		if("rspwd".equals(businessType)){
			bl.setRecordReason("10201002");
		}
		if("rsmobile".equals(businessType)){
			bl.setRecordReason("10201003");
		}
		sysBlackListServiceImpl.insert(bl);

		bl.setTagKey("phoneNum");
		bl.setTagValue(phoneNum);
		bl.setFlag(1);
		if("register".equals(businessType)){
			bl.setRecordReason("10201001");
		}
		if("rspwd".equals(businessType)){
			bl.setRecordReason("10201002");
		}
		if("rsmobile".equals(businessType)){
			bl.setRecordReason("10201003");
		}
		sysBlackListServiceImpl.insert(bl);

		bl.setTagKey("userId");
		bl.setTagValue(String.valueOf(userId));
		bl.setFlag(1);
		if("register".equals(businessType)){
			bl.setRecordReason("10201001");
		}
		if("rspwd".equals(businessType)){
			bl.setRecordReason("10201002");
		}
		if("rsmobile".equals(businessType)){
			bl.setRecordReason("10201003");
		}
		sysBlackListServiceImpl.insert(bl);
	}

	/**
	 * 发送自定义短信
	 * @param request
	 * @param phoneNum
	 * @throws Exception
	 */
//		 public void sendSMS(HttpServletRequest request,String phoneNum,Templete templete)throws Exception{
//			 SMSHandler.send(phoneNum, templete.getId(), templete.getParameters());
//		 }

}
