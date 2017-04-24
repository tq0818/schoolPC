package com.yuxin.wx.system.convert;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigCampus;

/**
 * 
 * @ClassName: SysConfigClassroomConvert
 * @Description: 教室转换类
 * @author liuxindong
 * @date 2014-12-21 下午2:40:35
 * @version 1.0
 */
public class SysConfigClassroomConvert {
	
	/**
	 * 
	 * Class Name: SysConfigHelper.java
	 * @Description: 从校区列表中根据校区id获取校区名称
	 * @author liuxindong
	 * @date 2014-12-21 下午2:56:47
	 * @version 1.0
	 * @param campuses
	 * @param campusId
	 * @return
	 */
	public static String getCampusName(List<SysConfigCampus> campuses,Integer campusId){
		if (campuses == null || campuses.size() < 1) {
			return "";
		}
		for (SysConfigCampus campus : campuses) {
			if (campus != null && campus.getId().equals(campusId)) {
				return campus.getCampusName();
			}
		}
		return "";
	}
	
}
