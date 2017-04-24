package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyLiveCoursewareZs;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyLiveCoursewareZsMapper extends BaseMapper<CompanyLiveCoursewareZs> {
	

	/**
	 * 
	 * Class Name: ICompanyLiveCoursewareZsService.java
	 * @Description: 查询插拨件
	 * @author 周文斌
	 * @date 2015-12-4 下午3:07:09
	 * @version 1.0
	 * @param zs
	 * @return
	 */
	List<CompanyLiveCoursewareZs> findCourse(CompanyLiveCoursewareZs zs);
	
	/**
	 * 
	 * Class Name: ICompanyLiveCoursewareZsService.java
	 * @Description: 查询插拨件总数
	 * @author 周文斌
	 * @date 2015-12-4 下午3:07:09
	 * @version 1.0
	 * @param zs
	 * @return
	 */
	Integer findCountCourse(CompanyLiveCoursewareZs zs);
}