package com.yuxin.wx.api.company;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.company.CompanyEmailHistory;
import com.yuxin.wx.model.company.CompanyVideoStaticCc;

public interface ICompanyVideoStaticCcService {
	
	void update(CompanyVideoStaticCc companyVideoStaticCc);

	void insert(CompanyVideoStaticCc companyVideoStaticCc);
	
	/**
	 * 
	 * Class Name: ICompanyVideoStaticCcService.java
	 * @Description: 定时任务 查询
	 * @author 周文斌
	 * @date 2015-5-27 下午4:46:38
	 * @version 1.0
	 * @param param
	 * @return
	 */
	CompanyVideoStaticCc findByDate(Map<String,Object> param);
	
	/**
	 * 
	 * Class Name: ICompanyVideoStaticCcService.java
	 * @Description: 查询上一次的统计信息
	 * @author 周文斌
	 * @date 2015-12-8 下午5:33:41
	 * @version 1.0
	 * @param param
	 * @return
	 */
	CompanyVideoStaticCc findByUpDate(Map<String,Object> param);
	
	/**
	 * 
	 * Class Name: ICompanyVideoStaticCcService.java
	 * @Description: 查询当前公司所有cc使用记录
	 * @author 周文斌
	 * @date 2015-12-8 下午8:06:48
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<CompanyVideoStaticCc> findByCompanyId(Integer companyId);
}
