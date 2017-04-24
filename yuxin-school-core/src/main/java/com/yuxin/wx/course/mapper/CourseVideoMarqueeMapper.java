package com.yuxin.wx.course.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.course.CourseVideoMarquee;
import com.yuxin.wx.vo.course.CourseVideoMarqueeVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CourseVideoMarqueeMapper extends BaseMapper<CourseVideoMarquee> {

	/**
	 * 
	 * Class Name: ICourseVideoMarqueeService.java
	 * @Description: 根据公司id查询
	 * @author 周文斌
	 * @date 2015-10-12 下午12:44:29
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	CourseVideoMarquee findMarqueeByCompanyId(CourseVideoMarquee cvm);
	
	/**
	 * @Description: 根据公司ID获取该公司配置的相关跑马灯的设置
	 * @author zx.wang
	 * @date 2015-10-12 下午12:24:04
	 * @version 2.0
	 * @param companyId
	 * @return
	 */
	CourseVideoMarqueeVo findCourseVideoMarqueeVoByCompanyId(Integer companyId);
	
}