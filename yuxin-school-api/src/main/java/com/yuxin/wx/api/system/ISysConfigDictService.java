package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.classes.EduMasterClass;
import com.yuxin.wx.model.system.SysConfigDict;
/**
 * Service Interface:SysConfigDict
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ISysConfigDictService  {
	/**
	 * 
	* @Title: saveSysConfigDict
	* @Description: 新增SysConfigDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(SysConfigDict sysConfigDict);
	void addEduStepSchool(SysConfigDict sysConfigDict);
	
	/**
	 * 
	* @Title: batchSaveSysConfigDict 
	* @Description: 批量新增SysConfigDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigDict> sysConfigDict);
	
	/**
	 * 
	* @Title: updateSysConfigDict 
	* @Description: 编辑SysConfigDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(SysConfigDict sysConfigDict);
	void updateSchoolProperty(SysConfigDict sysConfigDict);
	void updateOthserSchoolProperty(SysConfigDict sysConfigDict);
	
	/**
	 * 
	* @Title: deleteSysConfigDictById 
	* @Description: 根据id删除SysConfigDict
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigDictById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigDictByIds 
	* @Description: 根据id批量删除SysConfigDict
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigDictByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigDictById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	SysConfigDict findSysConfigDictById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigDictById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	SysConfigDict findSysConfigDictByCode(SysConfigDict dict);
	
	/**
	 * 
	* @Title: findSysConfigDictByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigDict>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<SysConfigDict> findSysConfigDictByPage(SysConfigDict search);

	List<SysConfigDict> findSysConfigDictList();
	List<SysConfigDict> queryAllSchools(SysConfigDict search);
	List<SysConfigDict> findNameAndComId(Integer id);
	
	/**
	 * 
	 * Class Name: ISysConfigDictService.java
	 * @Description: 查询教室相关 字典表
	 * @author 周文斌
	 * @date 2015-5-6 下午2:23:57
	 * @version 1.0
	 * @return
	 */
	List<SysConfigDict> findDictByClassroom();
	/**
	 * 
	 * Class Name: ISysConfigDictService.java
	 * @Description: 根据dict_code查询
	 * @author 权飞虎
	 * @date 2015年5月12日 下午7:05:42
	 * @modifier
	 * @modify-date 2015年5月12日 下午7:05:42
	 * @version 1.0
	 * @param code
	 * @return
	 */
	List<SysConfigDict> findByDicCode(String code);
	
	List<SysConfigDict> findConfigDictList();
	/**
	 * 
	 * Class Name: ISysConfigDictService.java
	 * @Description: 根据公司Id查询
	 * @author ycl
	 * @date 2015-5-22 下午2:31:20
	 * @modifier
	 * @modify-date 2015-5-22 下午2:31:20
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<SysConfigDict> findByCompanyId(Integer companyId);
	
    List<SysConfigDict> findAll();
    
    List<SysConfigDict> queryConfigDictListByDictCode(SysConfigDict sysConfigDict);
    List<SysConfigDict> querySchoolByArea(SysConfigDict sysConfigDict);
    List<SysConfigDict> queryAreaBySchool(String str);
 
	SysConfigDict queryConfigDictValue(SysConfigDict sysConfigDict);

    List<SysConfigDict> querySchoolListByStepCode(SysConfigDict areaDict);

    List<SysConfigDict> findSchoolBySchoolType(Map<String, Object> map);
    
  //获取服务类型及服务名称
  	List<SysConfigDict> querSysConfigDictList(Map<String, Object> map);
  	Integer querSysConfigDictCount(Integer companyId);
  	Integer findId(SysConfigDict areaDict);
  	Integer checkCodeAndName(SysConfigDict areaDict);
  	Integer queryAllSchoolsCount(SysConfigDict areaDict);
  	 /**
  	  * 
  	  * @author jishangyang 2017年12月17日 下午4:09:11
  	  * @Method: queryEduMasterClass 
  	  * @Description: 查询学段，学年，班级
  	  * @param ems
  	  * @return 
  	  * @throws
  	  */
    List<EduMasterClass> queryEduMasterClass(EduMasterClass ems);
}