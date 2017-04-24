package com.yuxin.wx.classes.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassPackageStage;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface ClassPackageStageMapper extends BaseMapper<ClassPackageStage> {
	
	List<ClassPackageStage> queryClassPackageStages(ClassPackageStage search);
	List<ClassPackageStage> queryAll(Integer companyId);
}