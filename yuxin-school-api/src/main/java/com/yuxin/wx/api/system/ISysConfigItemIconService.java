package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.system.SysConfigItemIcon;

public interface ISysConfigItemIconService {
	
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
	
	/**
	 * 
	 * Class Name: ISysConfigItemIconService.java
	 * @Description: 根据id查询
	 * @author yuchanglong
	 * @date 2015年9月16日 下午12:19:53
	 * @version 1.0
	 * @param id
	 * @return
	 */
	SysConfigItemIcon findUrlById(Integer id);
}
