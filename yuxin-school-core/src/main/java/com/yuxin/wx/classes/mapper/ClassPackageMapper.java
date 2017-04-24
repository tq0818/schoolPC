package com.yuxin.wx.classes.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassPackage;
import com.yuxin.wx.vo.classes.ClasspackageVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface ClassPackageMapper extends BaseMapper<ClassPackage> {
	
	List<ClassPackage> queryClassPackageByCondition(ClassPackage search);
	Integer queryClassPackageCount(ClassPackage search);
	List<ClassPackage> queryClassPackageByWhere(ClassPackage search);
	ClasspackageVo queryByCustomWhere(Map search);
	ClasspackageVo findVoById(Integer id);
	List<Integer> findClassPackageAllClasses(Integer classPackageId);
	
	List<ClassPackage> queryClassPackageHasCountsByCondition(ClassPackage search);
	Integer queryClassPackageHasCountsCountByCondition(ClassPackage search);
	List<ClasspackageVo> findByCondition(ClasspackageVo search);
	Integer findByConditionCount(ClasspackageVo search);
	
	List<ClassPackage> queryCommodityByClassPackage(ClassPackage search);
	
	ClassPackage queryClassPackageByComId(Integer id);
}