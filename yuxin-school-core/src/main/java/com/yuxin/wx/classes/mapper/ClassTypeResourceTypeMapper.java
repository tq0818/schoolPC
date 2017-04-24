package com.yuxin.wx.classes.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassTypeResourceType;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface ClassTypeResourceTypeMapper extends BaseMapper<ClassTypeResourceType> {
	
	/**
	 * 
	 * Class Name: IClassTypeResourceTypeService.java
	 * @Description: 根据公司id查询资源类型
	 * @author 周文斌
	 * @date 2015-8-11 下午5:49:56
	 * @version 1.0
	 * @param compnayId
	 * @return
	 */
	List<ClassTypeResourceType> findResourceTypeByCompanpyId(Integer companyId);

	/**
	 * 
	 * Class Name: IClassTypeResourceTypeService.java
	 * @Description: 查询是否存在
	 * @author 周文斌
	 * @date 2015-8-12 上午10:44:43
	 * @version 1.0
	 * @param ctrt
	 * @return
	 */
	ClassTypeResourceType findResourceTypeBy(ClassTypeResourceType ctrt);
}