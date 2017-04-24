package com.yuxin.wx.course.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.course.CourseVideoLookAuth;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CourseVideoLookAuthMapper extends BaseMapper<CourseVideoLookAuth> {

	/**
	 * 
	 * Class Name: ICourseVideoLookAuthService.java
	 * @Description: 查看权限根据公司id
	 * @author 周文斌
	 * @date 2016-3-7 下午12:03:54
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	CourseVideoLookAuth findAuthByCompanyId(Integer companyId);
	
}