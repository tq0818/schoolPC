package com.yuxin.wx.classes.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassPackageRelation;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface ClassPackageRelationMapper extends BaseMapper<ClassPackageRelation> {
	
	List<ClassPackageRelation> queryClassPackageStageRelation(ClassPackageRelation search);
	List<ClassPackageRelation> findClassPackageRelations(ClassPackageRelation search);
}