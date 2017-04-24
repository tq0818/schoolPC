package com.yuxin.wx.controller.task;

import com.yuxin.wx.api.company.ICompanyLiveConcurrentService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysTaskLogService;
import com.yuxin.wx.api.system.ISysUseRecordNoticTaskService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyLiveConcurrent;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysTaskLog;
import com.yuxin.wx.model.system.SysUseRecordNoticTask;
import com.yuxin.wx.util.FileQNUtils;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TimerTaskCompanyServices {
	
	private Log log=LogFactory.getLog("log");

	@Autowired
	private ISysTaskLogService sysTaskLogServiceImpl;
	@Autowired
	private ISysUseRecordNoticTaskService sysUseRecordNoticTaskServiceImpl;
	@Autowired
	private ICompanyLiveConcurrentService companyLiveConcurrentServiceImpl;
	@Autowired
	private ICompanyMemberServiceService cmsImpl;
	@Autowired
	private ICompanyServiceStaticService cssImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private PropertiesUtil properties;
	
	@Scheduled(cron = "0 13 9-20 * * ?") //4小时(参数分别为:秒、分、时、日期、月份、星期、年)0 0 0/4 * * ?
	public void sendMsgToCompany(){
		if(properties.getCompanyServiceMsg().equals("off")){
			return;
		}
		Date date = new Date();
		//获得公司id 已付费
		List<Integer> companyIds = companyServiceImpl.findCompanyId(null);
		log.info("定时任务开始执行------------------------"+new Date());
		String s = "";
		SysTaskLog stl = new SysTaskLog();
		stl.setTaskName("公司预警通知");
		stl.setExecuteDate(date);
		stl.setStartTime(date);
		stl.setOperator(0);
		stl.setOperateTime(date);
		boolean b = false;
		for (Integer i : companyIds) {
			try {
				CompanyMemberService cms = cmsImpl.findByCompanyId(i);
				CompanyServiceStatic css = cssImpl.findByCompanyId(i);
				if(cms != null && css != null){
					Company com = companyServiceImpl.findCompanyById(i);
					//用量预警
					userEarlyWarning(com, cms, css);
					//到期提醒
					expirationReminder(com, cms, css);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				log.info("公司"+i+",预警通知异常"+e.getMessage()+";");
				s += "公司"+i+",预警通知异常"+e.getMessage()+";";
				b = true;
			}
		}
		if(b){
			stl.setErrorLog(s);
			stl.setResult("预警部分异常");
		}else {
			stl.setErrorLog("无错误");
			stl.setResult("完成");
		}
		stl.setEndTime(new Date());
		sysTaskLogServiceImpl.insert(stl);
		log.info("定时任务执行完毕------------------------"+new Date());
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 用量预警
	 * @author 周文斌
	 * @date 2016-12-1 下午5:57:05
	 * @modify	2016-12-1 下午5:57:05
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param css
	 * @throws Exception
	 */
	private void userEarlyWarning(Company com,CompanyMemberService cms,
			CompanyServiceStatic css)
		throws Exception{
		Date date = new Date();
		Integer currMonth = Integer.parseInt((date.getYear() + 1900)
				+ "" + ((date.getMonth() + 1) < 10 ?
						"0" + (date.getMonth() + 1) :
						(date.getMonth() + 1)));
		//并发预警
		liveWarning(com.getId(), css.getLiveConcurrent(), date , currMonth);
		//空间预警
		spaceWarning(com.getId(), cms, css, date, currMonth);
		//流量预警
		flowWarning(com.getId(), cms, css, date, currMonth);
		//短信预警
		msgWarning(com.getId(), cms, css, date, currMonth);
		//邮件预警
		emailWarning(com.getId(), cms, css, date, currMonth);
		if("ONLINE_COUNT".equals(com.getServiceVersion())){
			//在线预警
			onlineWarning(com.getId(), cms, css, date, currMonth);
		}else{
			//学员预警
			studentWarning(com.getId(), cms, css, date, currMonth);
		}
	}
	
	private void expirationReminder(Company com,CompanyMemberService cms,
			CompanyServiceStatic css) throws Exception {
		Date date = new Date();
		Integer currMonth = Integer.parseInt((date.getYear() + 1900)
				+ "" + ((date.getMonth() + 1) < 10 ?
						"0" + (date.getMonth() + 1) :
						(date.getMonth() + 1)));
		//并发到期
		liveReminder(com.getId(), date, currMonth);
		//空间到期
		spaceReminder(com.getId(), cms, currMonth, date);
		//服务到期
		faceReminder(com, currMonth, date);
	}
	
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 直播并发预警
	 * @author 周文斌
	 * @date 2016-12-1 下午5:56:45
	 * @modify	2016-12-1 下午5:56:45
	 * @version 
	 * @param companyId
	 * @param use
	 * @throws Exception
	 */
	private void liveWarning(Integer companyId,double use,Date date,Integer currMonth)
		throws Exception{
		log.info("并发预警,使用,"+use+",公司," + companyId);
		//通过公司查询本月并发
		Map<String, Object> comtime = new HashMap<String, Object>();
		comtime.clear();
		comtime.put("companyId", companyId);
		comtime.put("concurrentMonth", currMonth);
		CompanyLiveConcurrent livec =  companyLiveConcurrentServiceImpl
				.findLiveByComidAndDate(comtime);
		if(livec == null){
			return;
		}
		Integer max = livec.getConcurrentMax();
		SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
		entity.setCompanyId(companyId);
		entity.setNoticType(0);
		entity.setRecordDateMonth(currMonth);
		entity.setBussinessType(0);
		entity.setRecordDateTime(date);
		entity.setRecordStatus(0);
		if((max - use) <= 0){
			//100%预警查询
			entity.setRecordData("100/100预警"+max+"总并发");
			insertWarning(entity, (max+0.0), use, (max - use));
		}else if((max - use) < ((int)(max * 0.1)+1)){
			entity.setRecordData("90/100预警"+max+"总并发");
			insertWarning(entity, (max+0.0), use, (max - use));
		}else{
			return;
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 空间预警
	 * @author 周文斌
	 * @date 2016-12-1 下午6:44:26
	 * @modify	2016-12-1 下午6:44:26
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param css
	 * @param date
	 * @param currMonth
	 * @throws Exception
	 */
	private void spaceWarning(Integer companyId,CompanyMemberService cms,
			CompanyServiceStatic css, Date date,Integer currMonth) throws Exception{
		double total = ((cms.getVideoStorage() != null ? cms.getVideoStorage() : 0)
				+ (cms.getGiveVideoStorage() != null ? cms.getGiveVideoStorage() : 0) + 0.0);

		double cssvs = (css.getVideoStorage() != null ?
						css.getVideoStorage() : 0.0);
		double use = (cssvs);
		//剩余
		double surplus = (total - use);
		
		SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
		entity.setCompanyId(companyId);
		entity.setNoticType(0);
		entity.setRecordDateMonth(currMonth);
		entity.setBussinessType(1);
		entity.setRecordDateTime(date);
		entity.setRecordStatus(0);
		if(surplus <= 0){
			entity.setRecordData("100/100预警"+total+"总空间");
			insertWarning(entity, total, use, surplus);
		}else if(surplus <= (total * 0.1)){
			entity.setRecordData("90/100预警"+total+"总空间");
			insertWarning(entity, total, use, surplus);
		}else{
			return;
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 流量预警
	 * @author 周文斌
	 * @date 2016-12-1 下午7:05:27
	 * @modify	2016-12-1 下午7:05:27
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param css
	 * @param date
	 * @param currMonth
	 * @throws Exception
	 */
	private void flowWarning(Integer companyId,CompanyMemberService cms,
			CompanyServiceStatic css, Date date,Integer currMonth) throws Exception{
		double total = ((cms.getVideoFlow() != null ? cms.getVideoFlow() : 0)
				+ (cms.getGiveVideoFlow() != null ? cms.getGiveVideoFlow() : 0) + 0.0);
		long crf = Long.parseLong(css.getResourceFlow() != null ?
						css.getResourceFlow() : "0");
		double cssrf = FileQNUtils.convertFileSize(crf);
		double cssvf = (css.getVideoFlow() != null ?
						css.getVideoFlow() : 0.0);
		double use = (cssrf + cssvf);
		//剩余
		double surplus = (total - use);
		
		SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
		entity.setCompanyId(companyId);
		entity.setNoticType(0);
		entity.setRecordDateMonth(currMonth);
		entity.setBussinessType(2);
		entity.setRecordDateTime(date);
		entity.setRecordStatus(0);
		if(surplus <= 0){
			entity.setRecordData("100/100预警"+total+"总流量");
			insertWarning(entity, total, use, surplus);
		}else if(surplus <= (total * 0.1)){
			entity.setRecordData("90/100预警"+total+"总流量");
			insertWarning(entity, total, use, surplus);
		}else{
			return;
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 短信预警
	 * @author 周文斌
	 * @date 2016-12-1 下午7:11:27
	 * @modify	2016-12-1 下午7:11:27
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param css
	 * @param date
	 * @param currMonth
	 * @throws Exception
	 */
	private void msgWarning(Integer companyId,CompanyMemberService cms,
			CompanyServiceStatic css, Date date,Integer currMonth) throws Exception{
		double total = ((cms.getMessageTotal() != null ? cms.getMessageTotal() : 0)
				+ (cms.getGiveMessage() != null ? cms.getGiveMessage() : 0) + 0.0);
		double use = (css.getMessageSend() != null ?
				css.getMessageSend() : 0.0);
		//剩余
		double surplus = (total - use);
		
		SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
		entity.setCompanyId(companyId);
		entity.setNoticType(0);
		entity.setRecordDateMonth(currMonth);
		entity.setBussinessType(3);
		entity.setRecordDateTime(date);
		entity.setRecordStatus(0);
		if(surplus <= 0){
			entity.setRecordData("100/100预警"+total+"总短信");
			insertWarning(entity, total, use, surplus);
		}else if(surplus <= ((int)(total * 0.1)+1)){
			entity.setRecordData("90/100预警"+total+"总短信");
			insertWarning(entity, total, use, surplus);
		}else{
			return;
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 邮件预警
	 * @author 周文斌
	 * @date 2016-12-1 下午7:21:23
	 * @modify	2016-12-1 下午7:21:23
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param css
	 * @param date
	 * @param currMonth
	 * @throws Exception
	 */
	private void emailWarning(Integer companyId,CompanyMemberService cms,
			CompanyServiceStatic css, Date date,Integer currMonth) throws Exception{
		double total = ((cms.getEmailTotal() != null ? cms.getEmailTotal() : 0)
				+ (cms.getGiveEmail() != null ? cms.getGiveEmail() : 0) + 0.0);
		double use = (css.getEmailSend() != null ?
				css.getEmailSend() : 0.0);
		//剩余
		double surplus = (total - use);
		
		SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
		entity.setCompanyId(companyId);
		entity.setNoticType(0);
		entity.setRecordDateMonth(currMonth);
		entity.setBussinessType(4);
		entity.setRecordDateTime(date);
		entity.setRecordStatus(0);
		if(surplus <= 0){
			entity.setRecordData("100/100预警"+total+"总邮件");
			insertWarning(entity, total, use, surplus);
		}else if(surplus <= ((int)(total * 0.1)+1)){
			entity.setRecordData("90/100预警"+total+"总邮件");
			insertWarning(entity, total, use, surplus);
		}else{
			return;
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 学员预警
	 * @author 周文斌
	 * @date 2016-12-1 下午7:23:52
	 * @modify	2016-12-1 下午7:23:52
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param css
	 * @param date
	 * @param currMonth
	 * @throws Exception
	 */
	private void studentWarning(Integer companyId,CompanyMemberService cms,
			CompanyServiceStatic css, Date date,Integer currMonth) throws Exception {
		double total = ((cms.getFaceStudentAll() != null ? cms.getFaceStudentAll() : 0) + 0.0);
		double use = (css.getFaceStudent() != null ?
				css.getFaceStudent() : 0.0);
		//剩余
		double surplus = (total - use);
		
		SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
		entity.setCompanyId(companyId);
		entity.setNoticType(0);
		entity.setRecordDateMonth(currMonth);
		entity.setBussinessType(6);
		entity.setRecordDateTime(date);
		entity.setRecordStatus(0);
		if(surplus <= 0){
			entity.setRecordData("100/100预警"+total+"总学员");
			insertWarning(entity, total, use, surplus);
		}else if(surplus <= ((int)(total * 0.1)+1)){
			entity.setRecordData("90/100预警"+total+"总学员");
			insertWarning(entity, total, use, surplus);
		}else{
			return;
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 在线预警
	 * @author 周文斌
	 * @date 2016-12-1 下午7:25:50
	 * @modify	2016-12-1 下午7:25:50
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param css
	 * @param date
	 * @param currMonth
	 * @throws Exception
	 */
	private void onlineWarning(Integer companyId,CompanyMemberService cms,
			CompanyServiceStatic css, Date date,Integer currMonth) throws Exception {
		double total = ((cms.getFaceStudentAll() != null ? cms.getFaceStudentAll() : 0) + 0.0);
		double use = (css.getOnlineStudent() != null ?
				css.getOnlineStudent() : 0.0);
		//剩余
		double surplus = (total - use);
		
		SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
		entity.setCompanyId(companyId);
		entity.setNoticType(0);
		entity.setRecordDateMonth(currMonth);
		entity.setBussinessType(7);
		entity.setRecordDateTime(date);
		entity.setRecordStatus(0);
		if(surplus <= 0){
			entity.setRecordData("100/100预警"+total+"总在线");
			insertWarning(entity, total, use, surplus);
		}else if(surplus <= ((int)(total * 0.1)+1)){
			entity.setRecordData("90/100预警"+total+"总在线");
			insertWarning(entity, total, use, surplus);
		}else{
			return;
		}
	}
	
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 并发到期提醒
	 * @author 周文斌
	 * @date 2016-12-2 上午11:15:13
	 * @modify	2016-12-2 上午11:15:13
	 * @version 
	 * @param companyId
	 * @param date
	 * @param currMonth
	 * @throws Exception
	 */
	private void liveReminder(Integer companyId,Date date ,Integer currMonth)
		throws Exception{
		//下月是否有并发
		Date next = DateUtil.addMonthsToDate(date, 1);
		Integer nextMonth = Integer.parseInt((next.getYear() + 1900)
				+ "" + ((next.getMonth() + 1) < 10 ?
						"0" + (next.getMonth() + 1) :
						(next.getMonth() + 1)));
		Map<String, Object> comtime = new HashMap<String, Object>();
		comtime.clear();
		comtime.put("companyId", companyId);
		comtime.put("concurrentMonth", currMonth);
		CompanyLiveConcurrent livec =  companyLiveConcurrentServiceImpl
				.findLiveByComidAndDate(comtime);
		if(livec == null){
			return;
		}
		comtime.clear();
		comtime.put("companyId", companyId);
		comtime.put("concurrentMonth", nextMonth);
		CompanyLiveConcurrent nextc =  companyLiveConcurrentServiceImpl
				.findLiveByComidAndDate(comtime);

		//获得本月最后一天的年月日()
		String last = DateUtil.getLastDayOfCurMonth();
		Date lastd = new SimpleDateFormat("yyyy-MM-dd").parse(last);
		Date time = DateUtil.parseDate(DateUtil.format(date, "yyyy-MM-dd"), "yyyy-MM-dd");
		int days = DateUtil.diffDate(lastd, time);
		if(nextc == null){
			SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
			entity.setCompanyId(companyId);
			entity.setNoticType(1);
			entity.setRecordDateMonth(currMonth);
			entity.setBussinessType(0);
			entity.setRecordDateTime(date);
			entity.setRecordStatus(0);
			if(days == 7){
				entity.setRecordData("7天到期"+last+"并发到期");
				entity.setDeadDate(lastd);
				insertReminder(entity);
			}/*else if(days == 30){
				entity.setRecordData("30天到期"+last+"并发到期");
				entity.setDeadDate(lastd);
				insertReminder(entity);
			}*/else {
				return;
			}
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 空间到期提醒
	 * @author 周文斌
	 * @date 2016-12-2 上午11:18:17
	 * @modify	2016-12-2 上午11:18:17
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param currMonth
	 * @throws Exception
	 */
	private void spaceReminder(Integer companyId,CompanyMemberService cms
			,Integer currMonth,Date date)throws Exception {
		Date lastd = (cms.getVideoEndDate() != null ? cms.getVideoEndDate()
				: cms.getGiveVideoStorageDate());
		Date time = DateUtil.parseDate(DateUtil.format(date, "yyyy-MM-dd"), "yyyy-MM-dd");
		int days = DateUtil.diffDate(lastd, time);
		if(days == 7){
			SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
			entity.setCompanyId(companyId);
			entity.setNoticType(1);
			entity.setRecordDateMonth(currMonth);
			entity.setBussinessType(1);
			entity.setRecordDateTime(date);
			entity.setRecordStatus(0);
			entity.setRecordData("7天到期" + new SimpleDateFormat("yyyy-MM-dd").format(lastd) 
					+ "空间到期");
			entity.setDeadDate(lastd);
			insertReminder(entity);
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 服务到期
	 * @author 周文斌
	 * @date 2016-12-2 上午11:30:49
	 * @modify	2016-12-2 上午11:30:49
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param currMonth
	 * @param date
	 * @throws Exception
	 */
	private void faceReminder(Company com,Integer currMonth,Date date) throws Exception{
		Date lastd = (com.getMemberEndDate() != null ? com.getMemberEndDate()
				: null);
		Date time = DateUtil.parseDate(DateUtil.format(date, "yyyy-MM-dd"), "yyyy-MM-dd");
		if(lastd != null){
			int days = DateUtil.diffDate(lastd, time);
			SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
			entity.setCompanyId(com.getId());
			entity.setNoticType(1);
			entity.setRecordDateMonth(currMonth);
			entity.setBussinessType(5);
			entity.setRecordDateTime(date);
			entity.setRecordStatus(0);
			if(days == 7){
				entity.setRecordData("7天到期" + new SimpleDateFormat("yyyy-MM-dd").format(lastd) 
						+ "服务到期");
				entity.setDeadDate(lastd);
				insertReminder(entity);
			}else if(days == 30){
				entity.setRecordData("30天到期" + new SimpleDateFormat("yyyy-MM-dd").format(lastd) 
						+ "服务到期");
				entity.setDeadDate(lastd);
				insertReminder(entity);
			}
		}
	}
	
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 预警插入表数据
	 * @author 周文斌
	 * @date 2016-12-1 下午6:55:28
	 * @modify	2016-12-1 下午6:55:28
	 * @version 
	 * @param entity
	 * @param total
	 * @param use
	 * @param surplus
	 * @throws Exception
	 */
	private void insertWarning(SysUseRecordNoticTask entity,Double total,
			Double use,Double surplus)
		throws Exception{
		SysUseRecordNoticTask su = sysUseRecordNoticTaskServiceImpl
				.findByCompanyId(entity);
		if(su == null){
			entity.setTotalNum(total);
			entity.setCurrtNum(use);
			entity.setLastNum(surplus);
			sysUseRecordNoticTaskServiceImpl.insert(entity);
		}
	}
	
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 到期插入
	 * @author 周文斌
	 * @date 2016-12-2 上午11:14:18
	 * @modify	2016-12-2 上午11:14:18
	 * @version 
	 * @param entity
	 * @throws Exception
	 */
	private void insertReminder(SysUseRecordNoticTask entity) throws Exception {
		SysUseRecordNoticTask su = sysUseRecordNoticTaskServiceImpl
				.findByCompanyId(entity);
		if(su == null){
			sysUseRecordNoticTaskServiceImpl.insert(entity);
		}
	}
}
