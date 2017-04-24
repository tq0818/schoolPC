package com.yuxin.wx.api.classes;

import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.course.LiveOpenCourse;

public interface ILiveRoomService {
	
	/**
	 * @Description: 创建直播教室
	 * @author zx.wang
	 * @date 2015-11-10 下午3:31:55
	 * @version 2.0
	 * @param o
	 * @return
	 */
	String createLiveRoom(Object o, Integer companyId);
	
	/**
	 * @Description:更新直播教室
	 * @author zx.wang
	 * @date 2015-11-10 下午3:33:07
	 * @version 2.0
	 * @param o
	 * @return
	 */
	String updateLiveRoom(Object o, Integer companyId);
	
	/**
	 * @Description: 删除直播教室
	 * @author zx.wang
	 * @date 2015-11-10 下午3:33:37
	 * @version 2.0
	 * @param o
	 * @return
	 */
	String deleteLiveRoom(Object o);
	
	/**
	 * @Description: 将课程转变为E课堂/展示互动， 要传递的对象
	 * @author zx.wang
	 * @date 2015-11-11 下午4:04:35
	 * @version 2.0
	 * @param cml
	 * @param customer
	 * @param key
	 * @return
	 */
	Object lessonConvertLiveRoom(ClassModuleLesson cml, Integer companyId);
	
	/**
	 * @Description: 将公开课实体转化为E课堂/展示互动， 要传递的对象
	 * @author zx.wang
	 * @date 2015-11-17 上午11:26:46
	 * @version 2.0
	 * @param open
	 * @param customer
	 * @param key
	 * @return
	 */
	Object openConvertLiveRoom(LiveOpenCourse open, Integer companyId);
	
	Object lessonConvertLiveRoom(ClassModuleLesson cml, CompanyLiveConfig config);
	
	Object openConvertLiveRoom(LiveOpenCourse open, CompanyLiveConfig config);
	
}
