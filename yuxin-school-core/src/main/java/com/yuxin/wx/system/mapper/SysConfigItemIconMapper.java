package com.yuxin.wx.system.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigItemIcon;

public interface SysConfigItemIconMapper extends BaseMapper<SysConfigItemIcon> {

	/**
	 * 
	 * Class Name: ISysConfigItemIconService.java
	 * @Description: 根据公司 查询 项目图标
	 * @author 周文斌
	 * @date 2015-5-6 下午6:37:37
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<SysConfigItemIcon> findByPage(Map<String, Object> param);

	/**
	 * 
	 * Class Name: ISysConfigItemIconService.java
	 * @Description: 根据公司 查询 项目图标总数
	 * @author 周文斌
	 * @date 2015-5-6 下午6:37:37
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	Integer findByPageCount(Map<String, Object> param);
	
	SysConfigItemIcon findUrlById(Integer id);
}
