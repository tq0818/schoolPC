package com.yuxin.wx.classes.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeResource;
import com.yuxin.wx.model.course.CourseRemote;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.vo.classes.ClassTypeResourceVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.common.BaseMapper;

/**
 * 分校课程
 */
public interface ClassTypeOfBranchSchoolMapper extends BaseMapper<ClassType> {
	
	/**
	 * 查询分校课程(分校课程)
	 */
	List<ClassTypeVo> queryClassTypeOfBranchSchool(Map<String, Object> param);

	/**
	 * 查询分校课程(分校课程)
	 */
	int queryCountClassTypeOfBranchSchool(Map<String, Object> param);
	
    /**
     * 查询分类/学段/学科
     */
    List<SysConfigItemRelation> findItemFront(Map<String, Object> param);
    
    /**
     * 批量上架/下架
     * @param param
     */
    void battchUpOrDown(Map<String, Object> param);
    /**
     * 查询课型
     */
    ClassTypeVo findDetailById(Map<String, String> param);
    
    /**
     * 查询课程单元
     */
	List<ClassModule> findListByClassId(Map<String,String> map);
	
	List<CourseVideoChapter> findByClassId(Map<String, String> map);
	
	List<CourseRemote> findRemotesByClassTypeId(Map<String, String> map);

    void setCddsRecommendFlag(Map<String, Object> param);
    
    void setSaleOrNoSale(Map<String, Object> param);
	
    List<ClassTypeResourceVo> findResBy(ClassTypeResource res);
	
	Integer findResCountBy(ClassTypeResource res);
}