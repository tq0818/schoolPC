package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyPayConfig;


/**
 * Service Interface:CompanyPayConfig
 * @author ycl
 * @date 2015-4-27
 */
public interface ICompanyPayConfigService  {
	/**
	 * 
	 * Class Name: ICompanyPayConfig.java
	 * @Description: 插入数据
	 * @author ycl
	 * @date 2015-4-27 上午11:31:50
	 * @modifier
	 * @modify-date 2015-4-27 上午11:31:50
	 * @version 1.0
	 * @param companyPayConfig
	 */
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
	/**
	 *
	 * Class Name: ICompanyPayConfigService.java
	 * @Description: 获取所有的乐视tv
	 * @version 1.0
	 * @return
	 */
	List<CompanyPayConfig> findAllLetv();
	/**
	 * 
	 * Class Name: ICompanyPayConfigService.java
	 * @Description: 根据公司Id查询
	 * @author ycl
	 * @date 2015-5-28 下午5:11:53
	 * @modifier
	 * @modify-date 2015-5-28 下午5:11:53
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	CompanyPayConfig findByCompanyId(Integer companyId);
	
	List<Integer> findAllCompanyId();
	
	/**
	 * 
	 * Class Name: ICompanyPayConfigService.java
	 * @Description: 根据公司ID和支付类型查询
	 * @author yuchanglong
	 * @date 2015年8月10日 上午11:27:08
	 * @version 1.0
	 * @param config
	 * @return
	 */
	CompanyPayConfig findByComIdAndPayType(CompanyPayConfig config);
	
	/**
	 * 
	 * Class Name: ICompanyPayConfigService.java
	 * @Description: 根据公司Id更新
	 * @author ycl
	 * @date 2015-5-28 下午5:37:30
	 * @modifier
	 * @modify-date 2015-5-28 下午5:37:30
	 * @version 1.0
	 * @param companyId
	 */
	void updateByCompanyId(CompanyPayConfig config);
	
	/**
	 * 
	 * Class Name: ICompanyPayConfigService.java
	 * @Description: 查询该公司是否存在
	 * @author ycl
	 * @date 2015-5-28 下午6:49:22
	 * @modifier
	 * @modify-date 2015-5-28 下午6:49:22
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	Integer findCountByCompanyId(Integer companyId);
}
