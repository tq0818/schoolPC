package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysNewsType;
import com.yuxin.wx.vo.system.SysNewsTypeVo;
/**
 * Service Interface:SysNewsType
 * @author chopin
 * @date 2015-12-3
 */
public interface ISysNewsTypeService  {
	/**
	 * 
	* @Title: saveSysNewsType
	* @Description: 新增SysNewsType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by wangzx
	 */
	void insert(SysNewsType entity);
	
	/**
	 * 
	* @Title: batchSaveSysNewsType 
	* @Description: 批量新增SysNewsType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by wangzx
	 */
	void batchInsert(List<SysNewsType> list);
	
	/**
	 * 
	* @Title: updateSysNewsType 
	* @Description: 编辑SysNewsType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by wangzx
	 */
	void update(SysNewsType entity);
	
	/**
	 * 
	* @Title: deleteSysNewsTypeById 
	* @Description: 根据id删除SysNewsType
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by wangzx
	 */
	void deleteSysNewsTypeById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysNewsTypeByIds 
	* @Description: 根据id批量删除SysNewsType
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by wangzx
	 */
	void deleteSysNewsTypeByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysNewsTypeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by wangzx
	 */
	SysNewsType findSysNewsTypeById(Integer id);
	
	/**
	 * 
	* @Title: findSysNewsTypeByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysNewsType>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-3
	* @user by wangzx
	 */
	List<SysNewsType> findSysNewsTypeByPage(SysNewsType search);
	/**
	 * 
	 * @fileName : ISysNewsTypeService.java
	 * @date : 2015年12月3日 上午11:59:03
	 * @author :　杨延博
	 * @description :根据姓名查询
	 */
	SysNewsType findByName(SysNewsType sysNewsType);

	List<SysNewsTypeVo> queryNewsType(SysNewsType sysNewsType);
}