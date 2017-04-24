package com.yuxin.wx.classes.mapper;

import com.yuxin.wx.model.classes.ClassModuleVideoRelation;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:ClassModuleVideoRelation
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ClassModuleVideoRelationMapper extends BaseMapper<ClassModuleVideoRelation> {
	/**
	 * 
	 * Class Name: ClassModuleVideoRelationMapper.java
	 * @Description:根据模块id删除相关的模块视频关系信息
	 * @author Keyn
	 * @date 2015-1-6 下午2:20:04
	 * @version 1.0
	 * @param id
	 */
	public void deleteByModuleId(String id);
}