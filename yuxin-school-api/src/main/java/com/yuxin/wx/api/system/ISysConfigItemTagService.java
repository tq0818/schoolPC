package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemTag;
/**
 * Service Interface:SysConfigItemTag
 * @author chopin
 * @date 2015-9-23
 */
public interface ISysConfigItemTagService  {
	/**
	 * 
	* @Title: saveSysConfigItemTag
	* @Description: 新增SysConfigItemTag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by wangzx
	 */
	void insert(SysConfigItemTag entity);
	
	/**
	 * 
	* @Title: batchSaveSysConfigItemTag 
	* @Description: 批量新增SysConfigItemTag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigItemTag> list);
	
	/**
	 * 
	* @Title: updateSysConfigItemTag 
	* @Description: 编辑SysConfigItemTag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by wangzx
	 */
	void update(SysConfigItemTag entity);
	
	/**
	 * 
	* @Title: deleteSysConfigItemTagById 
	* @Description: 根据id删除SysConfigItemTag
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by wangzx
	 */
	void deleteSysConfigItemTagById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigItemTagByIds 
	* @Description: 根据id批量删除SysConfigItemTag
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by wangzx
	 */
	void deleteSysConfigItemTagByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigItemTagById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by wangzx
	 */
	SysConfigItemTag findSysConfigItemTagById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigItemTagByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigItemTag>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by wangzx
	 */
	List<SysConfigItemTag> findSysConfigItemTagByPage(SysConfigItemTag search);
	
	/**
	 * 
	 * Class Name: ISysConfigItemTagService.java
	 * @Description: 条件查询标签信息
	 * @author zhang.zx
	 * @date 2015年9月23日 下午7:39:08
	 * @modifier
	 * @modify-date 2015年9月23日 下午7:39:08
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigItemTag> queryTagsBycondition(SysConfigItemTag search);
	
	
	/**
	 * 
	 * Class Name: ISysConfigItemTagService.java
	 * @Description: 查询标签(支持全部，以及特定级标签查询)
	 * @author dongshuai
	 * @date 2016年7月26日 上午10:33:49
	 * @modifier
	 * @modify-date 2016年7月26日 上午10:33:49
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigItemTag> queryTags(SysConfigItemTag search);
	
	/**
	 * 
	 * Class Name: ISysConfigItemTagService.java
	 * @Description: 检查标签是否存在
	 * @author dongshuai
	 * @date 2016年7月26日 上午11:11:17
	 * @modifier
	 * @modify-date 2016年7月26日 上午11:11:17
	 * @version 1.0
	 * @param search
	 * @return
	 */
	Integer checkTag(SysConfigItemTag search);
	
	/**
	 * 
	 * Class Name: ISysConfigItemTagService.java
	 * @Description: 查询学科小类
	 * @author dongshuai
	 * @date 2016年7月26日 下午2:34:51
	 * @modifier
	 * @modify-date 2016年7月26日 下午2:34:51
	 * @version 1.0
	 * @param map
	 * @return
	 */
	List<SysConfigItem> queryItemSecondIdList(Map<String, Integer> map);
}