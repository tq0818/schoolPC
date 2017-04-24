package com.yuxin.wx.company.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyLiveConcurrent;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyLiveConcurrentMapper extends BaseMapper<CompanyLiveConcurrent> {

	/**
	 * 
	 * Class Name: ICompanyLiveConcurrentService.java
	 * @Description: 查询本月并发
	 * @author 周文斌
	 * @date 2016-3-28 下午4:02:12
	 * @version 1.0
	 * @param param
	 * @return
	 */
	CompanyLiveConcurrent findLiveByComidAndDate(Map<String, Object> param);

	/**
	 * 
	 * Class Name: ICompanyLiveConcurrentService.java
	 * @Description: 查询日期以后的集合
	 * @author 周文斌
	 * @date 2016-3-29 上午11:07:40
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<CompanyLiveConcurrent> findMoreByComidAndDate(Map<String, Object> param);

	/**
	 * 
	 * Class Name: ICompanyLiveConcurrentService.java
	 * @Description: 修改基础并发 
	 * @author 周文斌
	 * @date 2016-3-29 下午4:07:14
	 * @version 1.0
	 * @param param
	 */
	void updatelive(Map<String, Object> param);
}