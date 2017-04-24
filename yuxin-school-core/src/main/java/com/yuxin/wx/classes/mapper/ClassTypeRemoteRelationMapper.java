package com.yuxin.wx.classes.mapper;

import java.util.List;

import com.yuxin.wx.model.classes.ClassTypeRemoteRelation;
import com.yuxin.wx.vo.classes.ModuleRemoteVo;
import com.yuxin.wx.vo.course.CourseRemoteVo;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:ClassTypeRemoteRelation
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ClassTypeRemoteRelationMapper extends BaseMapper<ClassTypeRemoteRelation> {
	 void deleteByClassTypeId(String classTypeId);
	 
	 List<ModuleRemoteVo> queryRomoteListByClassTypeId(Integer classTypeId);
}