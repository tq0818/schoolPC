package com.yuxin.wx.classes.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassTypeResource;
import com.yuxin.wx.vo.classes.ClassTypeResourceVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface ClassTypeResourceMapper extends BaseMapper<ClassTypeResource> {

	/**
	 * 
	 * Class Name: IClassTypeResourceService.java
	 * @Description: 分页查询列表
	 * @author 周文斌
	 * @date 2015-8-11 下午6:29:39
	 * @version 1.0
	 * @param res
	 * @return
	 */
	List<ClassTypeResourceVo> findResBy(ClassTypeResource res);

	/**
	 * 
	 * Class Name: IClassTypeResourceService.java
	 * @Description: 查询总数
	 * @author 周文斌
	 * @date 2015-8-11 下午6:32:49
	 * @version 1.0
	 * @param res
	 * @return
	 */
	Integer findResCountBy(ClassTypeResource res);
	
	
	List<ClassTypeResourceVo> findResByCondition(ClassTypeResource res);
}