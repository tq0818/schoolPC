package com.yuxin.wx.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.company.ICompanyLiveStaticDayDetailService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticDayService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.model.company.CompanyLiveStaticDayDetail;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;
import com.yuxin.wx.util.DateUtil;


@Component
public class LiveStatisticsTask {

	@Autowired
	private ICompanyServiceStaticDayService companyServiceStaticDayService;
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	@Autowired
	private ICompanyLiveStaticDayDetailService companyLiveStaticDayDetailServiceImpl;
	
	public void liveStatistics() throws Exception {
		//获得公司id
		List<Integer> companyIds = companyServiceImpl.findCompanyId(null);
		CompanyServiceStaticDay cssd = null;
		
		Map<String,Object> param = new HashMap<String, Object>();
		Date date = new Date();
		date = new SimpleDateFormat("yyyy-MM-dd").parse((date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate());

		Integer year = (date.getYear() + 1900);
		Integer month = (date.getMonth() + 1);
		//根据今天日期 获得最大 并发
		CompanyLiveStaticDayDetail clsdd = new CompanyLiveStaticDayDetail();
		clsdd.setAddDate(date);
		for (Integer companyId : companyIds) {
			clsdd.setCompanyId(companyId);
			Integer live = companyLiveStaticDayDetailServiceImpl.findByCompanyId(clsdd);
			if(live == null ){
				live = 0;
			}
			//查询 今日日服务表
			//根据 日期 和公司id 查询 今日天服务使用情况
			cssd = new CompanyServiceStaticDay();
			cssd.setCompanyId(companyId);
			cssd.setUseDate(date);
			CompanyServiceStaticDay service = companyServiceStaticDayService.findByDateAndCompanyId(cssd);
			
			if(service == null){
				service = new CompanyServiceStaticDay();
			}
			service.setCompanyId(companyId);
			service.setUseDate(date);
			service.setLiveNum(live);
			
			if(service.getId() == null){
				companyServiceStaticDayService.insert(service);
			}else{
				companyServiceStaticDayService.update(service);
			}

			//查询本月最大并发
			param.clear();
			param.put("year", year);
			param.put("month", month);
			param.put("companyId", companyId);
			
			Integer nowLive = companyLiveStaticDayDetailServiceImpl.findByDateAndCompanyId(param);
			
			if(nowLive == null){
				nowLive = 0;
			}
			//查询已使用服务
			CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
			
			if(css == null){
				css = new CompanyServiceStatic();
				css.setCompanyId(companyId);
				css.setLiveConcurrent(nowLive);
				css.setVideoFlow(0.0);
				css.setVideoStorage(0.0);
				css.setFaceStudent(0);
				css.setMessageSend(0);
				css.setEmailSend(0);
				css.setSchoolNum(0);
				companyServiceStaticServiceImpl.insert(css);
			}else{
				css.setLiveConcurrent(nowLive);
				if(css.getVideoFlow() == null){
					css.setVideoFlow(0.0);
				}
				if(css.getVideoStorage() == null){
					css.setVideoStorage(0.0);
				}
				if(css.getMessageSend() == null){
					css.setMessageSend(0);
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
