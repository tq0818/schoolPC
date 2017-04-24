
package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyPayConfig;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyPayConfigMapper extends BaseMapper<Company> {
	
	void insert(CompanyPayConfig companyPayConfig);

	/**
	 * 
	 * Class Name: ICompanyPayConfigService.java
	 * @Description: 定时任务 查询ccuserid 和 cc key
	 * @author 周文斌
	 * @date 2015-5-27 下午4:14:57
	 * @version 1.0
	 * @return
	 */
	List<CompanyPayConfig> findAllCC();

	List<CompanyPayConfig> findAllLetv();

	CompanyPayConfig findByCompanyId(Integer companyId);
	
	void updateByCompanyId(CompanyPayConfig config);
	
	Integer findCountByCompanyId(Integer companyId);
	
	CompanyPayConfig findByComIdAndPayType(CompanyPayConfig config);

	List<CompanyPayConfig> findPublicCC();
}