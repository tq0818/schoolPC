package com.yuxin.wx.classes.mapper;

import java.util.HashMap;
import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeModuleRelation;
import com.yuxin.wx.vo.classes.ClassTypeModuleRelationVo;
/**
 * Service Interface:ClassTypeModuleRelation
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ClassTypeModuleRelationMapper extends BaseMapper<ClassTypeModuleRelation> {
	/**
	 * 
	 * Class Name: ClassTypeModuleRelationMapper.java
	 * @Description: 根据班型id删除售卖班型
	 * @author Keyn
	 * @date 2015-1-5 下午3:36:57
	 * @version 1.0
	 * @param classTypeId
	 */
	void deleteByClassTypeId(String classTypeId);
	

	/**
	 * 
	 * Class Name: IClassTypeModuleRelationService.java
	 * @Description: 根据班型id 查询模块id
	 * @author 周文斌
	 * @date 2015-5-4 下午5:44:49
	 * @version 1.0
	 * @param classTypeId
	 * @return
	 */
	List<Integer> findModelIdByClassTypeId(List<ClassType> classTypes);
	
	void updateModuleByClassTypeId(ClassTypeModuleRelation classTypeModuleRelation);
	
	List<ClassTypeModuleRelation> querymoduleIdBycId(Integer id);
	
	List<ClassTypeModuleRelationVo> findClassModuleRelationByClassTypeId(HashMap map);
	
	List<ClassTypeModuleRelationVo> findClassModuleRelationByPackageId(HashMap map);
	
	List<Integer> findClassModuleIdsByClassTypeId(Integer id);
}