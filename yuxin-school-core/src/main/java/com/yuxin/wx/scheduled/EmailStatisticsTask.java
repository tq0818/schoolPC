package com.yuxin.wx.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.company.ICompanyEmailHistoryService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticDayService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.resource.IResourceListService;
import com.yuxin.wx.model.company.CompanyEmailHistory;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;
import com.yuxin.wx.util.DateUtil;

/**
 * 
 * @ClassName: EmailStatisticsTask
 * @Description: 邮件定时任务
 * @author 周文斌
 * @date 2015-5-21 下午12:44:43
 * @version 1.0
 */
@Component
public class EmailStatisticsTask {
	
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	@Autowired
	private ICompanyServiceStaticDayService companyServiceStaticDayService;
	
	@Autowired
	private ICompanyEmailHistoryService companyEmailHistoryServiceImpl;
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	public void emailStatistics() throws Exception{
		//获得公司id
		List<Integer> companyIds = companyServiceImpl.findCompanyId(null);
		CompanyServiceStaticDay cssd = null;
		//根据公司id 查询 今天  发送的邮件数量
		Date date = new Date();
		date = new SimpleDateFormat("yyyy-MM-dd").parse((date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());

		//开始时间
		Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((date.getYear() +1900) + "-" + (date.getMonth() +1) + "-" + date.getDate() + " 00:00:00");
		//结束时间
		Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((date.getYear() +1900) + "-" + (date.getMonth() +1) + "-" + date.getDate() + " 23:59:59");
			
		for (Integer i : companyIds) {
			CompanyEmailHistory email = new CompanyEmailHistory();
			email.setCompanyId(i);
			email.setStartTime(startTime);
			email.setEndTime(endTime);
			Integer emailCount = companyEmailHistoryServiceImpl.findEmailCount(email);
			if(emailCount == null){
				emailCount = 0;
			}
			//根据 日期 和公司id 查询 今天服务使用情况
			cssd = new CompanyServiceStaticDay();
			cssd.setCompanyId(i);
			cssd.setUseDate(date);
			CompanyServiceStaticDay service = companyServiceStaticDayService.findByDateAndCompanyId(cssd);
			
			//旧使用条数
			Integer oldEmail = 0;
			if(service == null){
				service = new CompanyServiceStaticDay();
			}
			if(service.getEmailNum() != null){
				oldEmail = service.getEmailNum();
			}
			service.setCompanyId(i);
			service.setUseDate(date);
			service.setEmailNum(emailCount);
			
			if(service.getId() == null){
				companyServiceStaticDayService.insert(service);
			}else{
				companyServiceStaticDayService.update(service);
			}
			
			//查询 公司服务统计
			CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(i);
			
			//使用的量
			Integer useEmail = (emailCount - oldEmail);
			if(css == null){
				css = new CompanyServiceStatic();
				css.setEmailSend(emailCount);
				css.setCompanyId(i);
				css.setLiveConcurrent(0);
				css.setMessageSend(0);
				css.setFaceStudent(0);
				css.setVideoFlow(0.0);
				css.setVideoStorage(0.0);
				css.setSchoolNum(0);
				companyServiceStaticServiceImpl.insert(css);
			}else{
				if(css.getEmailSend() != null){
					css.setEmailSend(css.getEmailSend() + useEmail);
				}else{
					css.setEmailSend(useEmail);
				}
				if(css.getLiveConcurrent() == null){
					css.setLiveConcurrent(0);
				}
				if(css.getVideoFlow() == null){
					css.setVideoFlow(0.0);
				}
				if(css.getVideoStorage() == null){
					css.setVideoStorage(0.0);
				}
				if(css.getFaceStudent() == null){
					css.setFaceStudent(0);
				}
				if(css.getSchoolNum() == null){
					css.setSchoolNum(0);
				}
				if(css.getMessageSend() == null){
					css.setMessageSend(0);
				}
				companyServiceStaticServiceImpl.update(css);
			}
		}
	}
	
}
