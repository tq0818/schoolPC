package com.yuxin.wx.api.classes;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.classes.ClassTypeResource;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.vo.classes.ClassTypeResourceVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;

/**
 * 分校课程管理
 * @author cxl
 *
 */
public interface IClassTypeOfBranchSchoolService  {

	List<SysConfigDict> findByDicCode(String code);
	

	List<SysConfigDict> findByParentId(String parentId);
	/**
	 * 查询分校课程
	 */
	List<ClassTypeVo> queryClassTypeOfBranchSchool(Map<String, Object> param);

	/**
	 * 查询分校课程
	 */
	int queryCountClassTypeOfBranchSchool(Map<String, Object> param);
	
    List<SysConfigItemRelation> findItemFront(Map<String, Object> param);
   
    /**
     * 课程批量上架/下架
     */
    void battchUpOrDown(Map<String, Object> param);
    
    ClassTypeVo findClassTypeDetail(Map<String, String> param);
    
    void setCddsRecommendFlag(Map<String, Object> param);
    
    void setSaleOrNoSale(Map<String, Object> param);
    
	List<ClassTypeResourceVo> findResBy(ClassTypeResource res);
	
	Integer findResCountBy(ClassTypeResource res);
	
	String copyClassTypeToTargetCompany(Map<String, String> param);
}