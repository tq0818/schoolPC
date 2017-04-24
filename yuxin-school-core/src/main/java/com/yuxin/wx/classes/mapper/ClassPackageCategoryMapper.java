package com.yuxin.wx.classes.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassPackageCategory;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface ClassPackageCategoryMapper extends BaseMapper<ClassPackageCategory> {
	
	List<ClassPackageCategory> queryClassCategoryLists(ClassPackageCategory search);
	Integer queryMaxIdByCompanyId(ClassPackageCategory search);
	List<ClassPackageCategory> queryAll(Integer companyId);
	List<ClassPackageCategory> queryClassCategoryCodeByWhere(ClassPackageCategory search);
	
	ClassPackageCategory queryClassPackageCategoryByCode(ClassPackageCategory search);
}