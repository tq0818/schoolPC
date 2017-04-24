package com.yuxin.wx.system.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigDivision;
import com.yuxin.wx.vo.company.ProvinceVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysConfigDivisionMapper extends BaseMapper<SysConfigDivision> {

	/**
	 * 
	 * Class Name: ISysConfigDivisionService.java
	 * @Description: 查询全部
	 * @author 周文斌
	 * @date 2016-7-14 下午5:19:48
	 * @version 1.0
	 * @return
	 */
	List<ProvinceVo> queryAlls();

	/**
	 * 
	 * Class Name: ISysConfigDivisionService.java
	 * @Description: 查询代码
	 * @author 周文斌
	 * @date 2016-7-14 下午7:09:33
	 * @version 1.0
	 * @param name
	 * @return
	 */
	String findXcode(Map<String, Object> param);

	/**
	 * 
	 * Class Name: ISysConfigDivisionService.java
	 * @Description: 查询实体
	 * @author 周文斌
	 * @date 2016-7-14 下午7:17:46
	 * @version 1.0
	 * @param code
	 * @return
	 */
	SysConfigDivision findEntity(String code);

	/**
	 * 
	 * Class Name: ISysConfigDivisionService.java
	 * @Description: 查父实体
	 * @author 周文斌
	 * @date 2016-7-14 下午7:33:33
	 * @version 1.0
	 * @param parentId
	 * @return
	 */
	SysConfigDivision findParentEntity(Integer parentId);
	
	List<SysConfigDivision> secLiandong(Integer parentId);
	
	List<SysConfigDivision> firstLiandong();
}