package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.system.SysConfigDivision;
import com.yuxin.wx.vo.company.ProvinceVo;
/**
 * Service Interface:SysConfigDivision
 * @author wang.zx
 * @date 2016-7-14
 */
public interface ISysConfigDivisionService  {
	/**
	 * 
	* @Title: saveSysConfigDivision
	* @Description: 新增SysConfigDivision
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-14
	* @user by wangzx
	 */
	void insert(SysConfigDivision entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigDivision 
	* @Description: 批量新增SysConfigDivision
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-14
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigDivision> list);
	
	/**
	 * 
	* @Title: updateSysConfigDivision 
	* @Description: 编辑SysConfigDivision
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-14
	* @user by wangzx
	 */
	void update(SysConfigDivision entity);
	
	/**
	 * 
	* @Title: deleteSysConfigDivisionById 
	* @Description: 根据id删除SysConfigDivision
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-14
	* @user by wangzx
	 */
	void deleteSysConfigDivisionById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigDivisionByIds 
	* @Description: 根据id批量删除SysConfigDivision
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-14
	* @user by wangzx
	 */
	void deleteSysConfigDivisionByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigDivisionById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-14
	* @user by wangzx
	 */
	SysConfigDivision findSysConfigDivisionById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigDivisionByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigDivision>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-14
	* @user by wangzx
	 */
	List<SysConfigDivision> findSysConfigDivisionByPage(SysConfigDivision search);
	
	/**
	 * 
	 * Class Name: ISysConfigDivisionService.java
	 * @Description: 查询全部
	 * @author 周文斌
	 * @date 2016-7-14 下午5:19:48
	 * @version 1.0
	 * @return
	 */
	List<ProvinceVo> queryAlls();
	
	/**
	 * 
	 * Class Name: ISysConfigDivisionService.java
	 * @Description: 查询代码
	 * @author 周文斌
	 * @date 2016-7-14 下午7:09:33
	 * @version 1.0
	 * @param name
	 * @return
	 */
	String findXcode(Map<String, Object> param);
	
	/**
	 * 
	 * Class Name: ISysConfigDivisionService.java
	 * @Description: 查询实体
	 * @author 周文斌
	 * @date 2016-7-14 下午7:17:46
	 * @version 1.0
	 * @param code
	 * @return
	 */
	SysConfigDivision findEntity(String code);
	
	/**
	 * 
	 * Class Name: ISysConfigDivisionService.java
	 * @Description: 查父实体
	 * @author 周文斌
	 * @date 2016-7-14 下午7:33:33
	 * @version 1.0
	 * @param parentId
	 * @return
	 */
	SysConfigDivision findParentEntity(Integer parentId);
	
	List<SysConfigDivision> secLiandong(Integer parentId);
	
	List<SysConfigDivision> firstLiandong();
	
}