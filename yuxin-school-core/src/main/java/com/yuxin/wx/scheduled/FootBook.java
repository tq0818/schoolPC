package com.yuxin.wx.scheduled;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.company.mapper.CompanyPayConfigMapper;
import com.yuxin.wx.company.mapper.CompanyServiceStaticDayMapper;
import com.yuxin.wx.company.mapper.CompanyServiceStaticMapper;
import com.yuxin.wx.company.mapper.CompanyVideoStaticCcMapper;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;
import com.yuxin.wx.model.company.CompanyVideoStaticCc;

@Component
@Transactional
public class FootBook {

	private Log log = LogFactory.getLog("log");

	@Autowired
	private CompanyServiceStaticMapper companyServiceStaticMapper;

	@Autowired
	private CompanyServiceStaticDayMapper companyServiceStaticDayMapper;
	
	@Autowired
	private CompanyVideoStaticCcMapper companyVideoStaticCcMapper;

	@Autowired
	private CompanyPayConfigMapper payConfigMapper;
	
	/**
	 * 
	 * Class Name: FootBook.java
	 * @Description: 数据修改
	 * @author 周文斌
	 * @date 2015-12-8 下午8:00:51
	 * @version 1.0
	 */
	public void up(Integer companyId){
		if(companyId != null && companyId > 0){
			//查询
			updateStaticDay(companyId);
		}else{
			//获得所有公司id
			List<CompanyPayConfig> payList = payConfigMapper.findAllCC();
			for (CompanyPayConfig p : payList) {
				updateStaticDay(p.getCompanyId());
			}
		}
	}
	
	
	public void updateStaticDay(Integer companyId){
		//循环查询每个公司所有记录
		Double prevUseFlow = null;
		Double prevUseSto = null;
		List<CompanyVideoStaticCc> cclist = companyVideoStaticCcMapper.findByCompanyId(companyId);
		if(cclist != null && cclist.size() > 0){
			for (int i = 0; i < cclist.size(); i++) {
				CompanyVideoStaticCc cvs = cclist.get(i);
				//根据公司 和日期 修改
				CompanyServiceStaticDay cssd = new CompanyServiceStaticDay();
				cssd.setCompanyId(companyId);
				cssd.setUseDate(cvs.getStaticDate());
				boolean flag = false;
				if(i == 0 || cvs.getStaticDate().getDate() == 1){
					flag = true;
				}
				
				if(flag){
					cssd.setVideoTotalFlow(new BigDecimal(cvs.getUsedFlow()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
				}else{
					prevUseFlow = cclist.get(i - 1).getUsedFlow();
					cssd.setVideoTotalFlow(new BigDecimal(cvs.getUsedFlow() - prevUseFlow).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
					log.info("流量,"+cssd.getVideoTotalFlow());
				}
				if(i == 0){
					cssd.setVideoStorageNum(new BigDecimal(cvs.getUsedStorage()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
				}else{
					prevUseSto = cclist.get(i - 1).getUsedStorage();
					cssd.setVideoStorageNum(new BigDecimal(cvs.getUsedStorage() - prevUseSto).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
					log.info("空间,"+cssd.getVideoStorageNum());
				}
				companyServiceStaticDayMapper.updateByCompanyIdDate(cssd);
			}
		}
		//求总和
		Double sum = companyServiceStaticDayMapper.findFlowSum(companyId);
		if(sum == null){
			sum = 0.0;
		}
		//总使用量更新
		CompanyServiceStatic css = companyServiceStaticMapper.findByCompanyId(companyId);
		css.setCompanyId(companyId);
		css.setVideoFlow(new BigDecimal(sum).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
		companyServiceStaticMapper.update(css);
	}

}
