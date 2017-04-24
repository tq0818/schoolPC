package com.yuxin.wx.resource.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.resource.ResourceList;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface ResourceListMapper extends BaseMapper<ResourceList> {
	
	/**
	 * 
	 * Class Name: IResourceListService.java
	 * @Description: 查询七牛存储空间每天使用情况
	 * @author 周文斌
	 * @date 2016-9-2 上午10:54:58
	 * @modify	2016-9-2 上午10:54:58
	 * @version 
	 * @param id
	 * @return
	 */
	String findUseSumStroage(Map<String, Object> param);
	
	/**
	 * 
	 * Class Name: IResourceListService.java
	 * @Description: 查询filepath部位null 的
	 * @author 周文斌
	 * @date 2016-11-2 上午11:40:10
	 * @modify	2016-11-2 上午11:40:10
	 * @version 
	 * @param search
	 * @return
	 */
	List<ResourceList> pageByFilePath(ResourceList search);
	
	Integer pageByFilePathCount(ResourceList search);

	ResourceList findByIdd(ResourceList resourceId);
}