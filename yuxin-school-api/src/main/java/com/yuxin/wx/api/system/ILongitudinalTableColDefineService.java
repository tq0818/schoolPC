package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.LongitudinalTableColDefine;
import com.yuxin.wx.model.system.LongitudinalTableData;

/**
 * Service Interface:LongitudinalTableColDefine
 * @author wang.zx
 * @date 2016-1-13
 */
public interface ILongitudinalTableColDefineService  {
	/**
	 * 
	* @Title: saveLongitudinalTableColDefine
	* @Description: 新增LongitudinalTableColDefine
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	void insert(LongitudinalTableColDefine T);
	
	/**
	 * 
	* @Title: batchSaveLongitudinalTableColDefine 
	* @Description: 批量新增LongitudinalTableColDefine
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	void batchInsert(List<LongitudinalTableColDefine> T);
	
	/**
	 * 
	* @Title: updateLongitudinalTableColDefine 
	* @Description: 编辑LongitudinalTableColDefine
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	void update(LongitudinalTableColDefine T);
	
	/**
	 * 
	* @Title: deleteLongitudinalTableColDefineById 
	* @Description: 根据id删除LongitudinalTableColDefine
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	void deleteLongitudinalTableColDefineById(Integer id);
	
	/**
	 * 
	* @Title: deleteLongitudinalTableColDefineByIds 
	* @Description: 根据id批量删除LongitudinalTableColDefine
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	void deleteLongitudinalTableColDefineByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findLongitudinalTableColDefineById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	LongitudinalTableColDefine findLongitudinalTableColDefineById(Integer id);
	
	/**
	 * 
	* @Title: findLongitudinalTableColDefineByPage 
	* @Description: 分页查询
	* @return
	* @return List<LongitudinalTableColDefine>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	List<LongitudinalTableColDefine> findLongitudinalTableColDefineByPage(LongitudinalTableColDefine search);
	
	/**
	 * 
	* @Title: findLongitudinalTableColDefineByPage 
	* @Description: 查询公司配置
	* @return
	* @return List<LongitudinalTableColDefine>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	public List<LongitudinalTableColDefine> findByCompany(Integer companyId,String tableName);
	

}