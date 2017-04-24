package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigTerm;
import com.yuxin.wx.vo.system.ConfigTermVo;
/**
 * Service Interface:SysConfigTerm
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ISysConfigTermService  {
	/**
	 * 
	* @Title: saveSysConfigTerm
	* @Description: 新增SysConfigTerm
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(SysConfigTerm sysConfigTerm);
	
	/**
	 * 
	* @Title: batchSaveSysConfigTerm 
	* @Description: 批量新增SysConfigTerm
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigTerm> sysConfigTerm);
	
	/**
	 * 
	* @Title: updateSysConfigTerm 
	* @Description: 编辑SysConfigTerm
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(SysConfigTerm sysConfigTerm);
	
	/**
	 * 
	* @Title: deleteSysConfigTermById 
	* @Description: 根据id删除SysConfigTerm
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigTermById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigTermByIds 
	* @Description: 根据id批量删除SysConfigTerm
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigTermByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigTermById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	SysConfigTerm findSysConfigTermById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigTermById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by sunxb
	 */
	List<SysConfigTerm> findSysConfigTermList(Integer itemOneId);
	
	/**
	 * 
	* @Title: findSysConfigTermById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by sunxb
	 */
	List<SysConfigTerm> findSysConfigTermList(SysConfigTerm search);
	
	/**
	 * 
	* @Title: findSysConfigTermByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigTerm>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<ConfigTermVo> findSysConfigTermByPage(SysConfigTerm search);
	
	/**
	 * 
	 * Class Name: ISysConfigTermService.java
	 * @Description: 根据条件查询考期，不分页
	 * @author liuxindong
	 * @date 2014-12-14 下午8:45:34
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigTerm> findTerm(SysConfigTerm search);
	
	/**
	 * @Description:(根据一级项目获取对应的目标考期, 注意不包含校区)
	 * @author wang.zx 
	 * @date 2014-12-26 上午11:56:56
	 * @version 1.0
	 * @param itemOneId
	 * @param companyId 
	 * @return
	 */
	List<SysConfigTerm> getTermByItemOne(Integer itemOneId, Integer companyId);
	
	//ycl-start
		/**
		 * 
		 * Class Name: ISysConfigTermService.java
		 * @Description: 根据公司ID和校区ID查询考期
		 * @author ycl
		 * @date 2015-4-30 下午2:40:56
		 * @modifier
		 * @modify-date 2015-4-30 下午2:40:56
		 * @version 1.0
		 * @param companyId
		 * @return
		 */
		List<ConfigTermVo> findByCompandyId(SysConfigTerm configTerm);
	//ycl-start
	
	/**
	 * 
	 * Class Name: ISysConfigTermService.java
	 * @Description: 根据一级项目 查询 考期
	 * @author 周文斌
	 * @date 2015-5-15 下午12:27:07
	 * @version 1.0
	 * @param oneItemId
	 * @return
	 */
	List<SysConfigTerm> findTermByOneItemId(Integer oneItemId);
	/**
	 * 
	 * Class Name: ISysConfigTermService.java
	 * @Description: 根据一级项目 查询 考期
	 * @author chopin
	 * @date 2015-5-15 下午12:27:07
	 * @version 1.0
	 * @param oneItemId
	 * @return
	 */
	List<SysConfigTerm> findTermByName(SysConfigTerm search);
}