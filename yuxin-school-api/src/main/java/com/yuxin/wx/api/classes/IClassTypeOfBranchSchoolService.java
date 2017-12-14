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
	 * 查询分校公开课程
	 */
	List<ClassTypeVo> queryClassTypeOfBranchSchool(Map<String, Object> param);

	/**
	 * 查询分校公开课程
	 */
	int queryCountClassTypeOfBranchSchool(Map<String, Object> param);
	
	/**
	 * 查询分校分享课程
	 */
	List<ClassTypeVo> queryClassTypeOfOtherSchool(Map<String, Object> param);

	/**
	 * 查询分校分享课程
	 */
	int queryCountClassTypeOfOtherSchool(Map<String, Object> param);
	
	/**
	 * 分类/学段/学科
	 * @param param
	 * @return
	 */
    List<SysConfigItemRelation> findItemFront(Map<String, Object> param);
       
    /**
     * 课程批量上架/下架:数字学校状态
     */
    void battchUpOrDown(Map<String, Object> param);
    
    /**
     * 课程批量上架/下架
     */
    void battchSaleOrStopSale(Map<String, Object> param);
    
    ClassTypeVo findClassTypeDetail(Map<String, String> param);
    

    ClassTypeVo findClassTypeDetail1(Map<String, String> param);
    
    void setCddsRecommendFlag(Map<String, Object> param);
    
    void setSaleOrNoSale(Map<String, Object> param);
    
	List<ClassTypeResourceVo> findResBy(ClassTypeResource res);
	
	Integer findResCountBy(ClassTypeResource res);
	
	Integer findSchoolShareClassType(Map<String, String> param);
	
	String copyClassTypeToTargetCompany(Map<String, String> param) throws Exception;
}