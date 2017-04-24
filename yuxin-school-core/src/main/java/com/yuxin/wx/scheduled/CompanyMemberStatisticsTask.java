package com.yuxin.wx.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.company.ICompanyMemberConfigService;
import com.yuxin.wx.api.company.ICompanyStudentMessageService;
import com.yuxin.wx.api.system.ISysSmsLogService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.model.company.CompanyMemberConfig;
import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.system.SysSmsLog;
import com.yuxin.wx.model.user.UserMessage;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.util.DateUtil;
import com.yuxin.wx.util.smsClient.interfacej.SmsClientSend;
import com.yuxin.wx.vo.user.UsersStudentInfo;

@Component
public class CompanyMemberStatisticsTask {
	
	private Log log = LogFactory.getLog("log");
	/*
	@Autowired
	private ISysSmsLogService sysSmsLogServiceimpl;*/

	@Autowired
	private ICompanyMemberConfigService companyMemberConfigServiceImpl;

	@Autowired
	private ICompanyStudentMessageService companyStudentMessageServiceImpl;
	
	@Autowired
	private IUsersFrontService usersFrontServiceImpl;
	
	public void UserVipNotice() throws Exception{
		Date date = new Date();
		Date dates = new Date();
		String time = new SimpleDateFormat("yyyy-MM-dd").format(date);
		date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
		//获得已开通的公司配置
		List<CompanyMemberConfig> cmclist = companyMemberConfigServiceImpl
				.findCompanyMemberConfigta();
		Map<String, Object> param = new HashMap<String, Object>();
		for (CompanyMemberConfig c : cmclist) {
			Integer day = c.getRemindBeforeDay();
			Integer companyId = c.getCompanyId();
			String content = c.getRemindContent();
			log.info("公司，" + companyId+",提前天提醒，"+day+",内容，"+content);
			param.clear();
			param.put("companyId", companyId);
			param.put("date", day);
			List<UsersStudentInfo> userlist = usersFrontServiceImpl.findUserMemberByCompanyId(param);
			log.info("查询用户");
			for (UsersStudentInfo u : userlist) {
				Integer times = DateUtil.diffDate(u.getMemberEndTime(),date);
				if(times.equals(day)){
					/*log.info("发送信息,"+u.getMobile()+","+companyId+","+content);
					addSmslog(u.getMobile(),content, companyId);*/
					String name = "";
					if(u.getStuname() != null){
						name = u.getStuname();
					}else if(u.getNickName() != null){
						name = u.getNickName();
					}else{
						name = u.getMobile();
					}
					content = content.replaceAll("【username】", name);
					content = content.replaceAll("【yyyy-MM-dd】"
							, new SimpleDateFormat("yyyy-MM-dd")
								.format(u.getMemberEndTime()));
					sendNotice(companyId, content, dates, u.getId(),1);
				}
				if(times.equals(0)){
					String name = "";
					content = "亲爱的【username】,您的会员今天到期！";
					if(u.getStuname() != null){
						name = u.getStuname();
					}else if(u.getNickName() != null){
						name = u.getNickName();
					}else{
						name = u.getMobile();
					}
					content = content.replaceAll("【username】", name);
					content = content.replaceAll("【yyyy-MM-dd】"
							, new SimpleDateFormat("yyyy-MM-dd")
								.format(u.getMemberEndTime()));
					sendNotice(companyId, content, dates, u.getId(),0);
				}
			}
		}
	}
	/*private void addSmslog(String mobile,String content,Integer companyId){
		String result = SmsClientSend.sendSmsTwo(mobile,
				content);
		SysSmsLog ssl = new SysSmsLog();
		try {
			ssl.setContent(content);
			ssl.setSendTime(new Date());
			ssl.setBusinessType("sys-notic");
			ssl.setMobile(mobile);
			ssl.setCompanyId(companyId);
			
			String sendStatus = result.substring(result.indexOf("<message>"),result.indexOf("</message>"));
			sendStatus = sendStatus.substring(sendStatus.indexOf(">") + 1);
			if(sendStatus.equals("ok")){
				sendStatus = "发送成功";
			}
			
			ssl.setSendStatus(sendStatus);
			ssl.setCompanyId(companyId);
			sysSmsLogServiceimpl.insert(ssl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("添加日志错误，" + e.getMessage());
		}
	}*/
	
	private void sendNotice(Integer companyId,String content,Date dates
			,Integer userId,Integer sendNum){
		CompanyStudentMessage csm = new CompanyStudentMessage();
		csm.setCompanyId(companyId);
		csm.setContent(content);
		csm.setTitle("会员即将到期通知");
		csm.setMessageType("STUDENT_MESSAGE_OTHER");
		csm.setCreateTime(dates);
		csm.setSendNum(sendNum);
		csm.setMessageStatus("STUDENT_MESSAGE_FINISH");
		companyStudentMessageServiceImpl.insert(csm);
		log.info("添加站内信，" + csm.getId());
		
		UserMessage um = new UserMessage();
		um.setUserId(userId);
		um.setMessageId(csm.getId());
		um.setReadFlag(0);
		companyStudentMessageServiceImpl.insertUserMessage(um);
		log.info("关联用户信息");
	}
}
