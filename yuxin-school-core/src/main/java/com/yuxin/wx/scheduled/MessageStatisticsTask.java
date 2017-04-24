package com.yuxin.wx.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.company.ICompanyMessageHistoryService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticDayService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.model.company.CompanyMessageHistory;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;
import com.yuxin.wx.util.DateUtil;

/**
 * 
 * @ClassName: MessageStatisticsTask
 * @Description: TODO(短信定时统计)
 * @author 1
 * @date 2015-5-22 下午4:45:27
 * @version 1.0
 */
@Component
public class MessageStatisticsTask {
	
	@Autowired
	private ICompanyMessageHistoryService companyMessageHistoryServiceImpl;
	
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	@Autowired
	private ICompanyServiceStaticDayService companyServiceStaticDayService;
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	public void messageStatistics() throws Exception{
		//获得公司id
		List<Integer> companyIds = companyServiceImpl.findCompanyId(null);
		//根据公司id 查询 今天  发送的短信条数
		Date date = new Date();
		date = new SimpleDateFormat("yyyy-MM-dd").parse((date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());
		//开始时间
		Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((date.getYear() +1900) + "-" + (date.getMonth() +1) + "-" + date.getDate() + " 00:00:00");
		//结束时间
		Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((date.getYear() +1900) + "-" + (date.getMonth() +1) + "-" + date.getDate() + " 23:59:59");
		
		CompanyServiceStaticDay cssd = null;
		for (Integer i : companyIds) {
			CompanyMessageHistory cmh = new CompanyMessageHistory();
			cmh.setCompanyId(i);
			cmh.setStartTime(startTime);
			cmh.setEndTime(endTime);
			Integer count = companyMessageHistoryServiceImpl.findMessageByDateAndCompanyId(cmh);
			
			//根据 日期 和公司id 查询 今天服务使用情况
			cssd = new CompanyServiceStaticDay();
			cssd.setCompanyId(i);
			cssd.setUseDate(date);
			CompanyServiceStaticDay service = companyServiceStaticDayService.findByDateAndCompanyId(cssd);
			
			//旧使用量
			Integer oldMessage = 0 ;
			
			if(service == null){
				service = new CompanyServiceStaticDay();
			}
			if(service.getMessageNum() != null){
				oldMessage = service.getMessageNum();
			}
			service.setCompanyId(i);
			service.setUseDate(date);
			service.setMessageNum(count);
			
			if(service.getId() == null){
				companyServiceStaticDayService.insert(service);
			}else{
				companyServiceStaticDayService.update(service);
			}
			
			//查询 公司服务统计
			CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(i);
			
			//新使用 
			Integer useMessage = (count - oldMessage);
			
			if(css == null){
				css = new CompanyServiceStatic();
				css.setMessageSend(count);
				css.setCompanyId(i);
				css.setLiveConcurrent(0);
				css.setVideoFlow(0.0);
				css.setVideoStorage(0.0);
				css.setEmailSend(0);
				css.setFaceStudent(0);
				css.setSchoolNum(0);
				companyServiceStaticServiceImpl.insert(css);
			}else{
				if(css.getMessageSend() != null){
					css.setMessageSend(css.getMessageSend() + useMessage);
				}else{
					css.setMessageSend(useMessage);
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
				if(css.getEmailSend() == null){
					css.setEmailSend(0);
				}
				if(css.getFaceStudent() == null){
					css.setFaceStudent(0);
				}
				if(css.getSchoolNum() == null){
					css.setSchoolNum(0);
				}
				companyServiceStaticServiceImpl.update(css);
			}
		}
	}
}
