package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.system.SysConfigSchool;
/**
 * Service Interface:SysConfigSchool
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ISysConfigSchoolService  {
	/**
	 * 
	* @Title: saveSysConfigSchool
	* @Description: 新增SysConfigSchool
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(SysConfigSchool sysConfigSchool);
	
	/**
	 * 
	* @Title: batchSaveSysConfigSchool 
	* @Description: 批量新增SysConfigSchool
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigSchool> sysConfigSchool);
	
	/**
	 * 
	* @Title: updateSysConfigSchool 
	* @Description: 编辑SysConfigSchool
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(SysConfigSchool sysConfigSchool);
	
	/**
	 * 
	* @Title: deleteSysConfigSchoolById 
	* @Description: 根据id删除SysConfigSchool
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigSchoolById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigSchoolByIds 
	* @Description: 根据id批量删除SysConfigSchool
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigSchoolByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigSchoolById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	SysConfigSchool findSysConfigSchoolById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigSchoolByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigSchool>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<SysConfigSchool> findSysConfigSchoolByPage(SysConfigSchool search);
	
	/**
	 * 
	 * Class Name: ISysConfigSchoolService.java
	 * @Description: 获取所有分校
	 * @author liuxindong
	 * @date 2015-1-8 下午11:29:35
	 * @version 1.0
	 * @return
	 */
	List<SysConfigSchool> findAllSysConfigSchool(Integer company);
	
	/**
	 * 
	 * Class Name: ISysConfigSchoolService.java
	 * @Description: 根据公司查询分校
	 * @author zhang.zx
	 * @date 2015-4-23
	 * @version 1.0
	 * @return List<SysConfigSchool>
	 */
	List<SysConfigSchool> findSysConfigSchoolByCompanyId(Integer companyId);
	
	Integer findDefaultSchool(Integer companyId);
	
	/**
	 * 
	 * Class Name: ISysConfigSchoolService.java
	 * @Description: 根据公司id和学校id 插叙
	 * @author 周文斌
	 * @date 2015-5-13 下午12:33:31
	 * @version 1.0
	 * @param param
	 * @return
	 */
	SysConfigSchool findSchoolByMap(Map<String,Object> param);
	
	/**
	 * 
	 * Class Name: ISysConfigSchoolService.java
	 * @Description: 根据学校id 查询 学校名字 唯一性
	 * @author 周文斌
	 * @date 2015-5-13 下午4:11:17
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findUnquieSchool(Map<String,Object> param);
	
	/**
	 * 
	 * Class Name: ISysConfigSchoolService.java
	 * @Description: 查询已开通分校数
	 * @author 周文斌
	 * @date 2015-6-25 上午10:49:06
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	Integer findSchoolCountByCompanyId(Integer companyId);
	
	List<SysConfigSchool> queryAllByCompanyId(Integer companyId);
	
	/**
	 * 
	 * Class Name: ISysConfigSchoolService.java
	 * @Description: 查询分校域名是否存在
	 * @author 周文斌
	 * @date 2016-7-28 下午3:49:26
	 * @version 1.0
	 * @param scs
	 * @return
	 */
	List<Integer> findExistBySuffix(SysConfigSchool scs);
}