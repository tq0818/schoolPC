package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.model.system.LongitudinalTableData;

/**
 * Service Interface:LongitudinalTableData
 * @author wang.zx
 * @date 2016-1-13
 */
public interface ILongitudinalTableDataService  {
	/**
	 * 
	* @Title: saveLongitudinalTableData
	* @Description: 新增LongitudinalTableData
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	void insert(LongitudinalTableData entity);
	
	/**
	 * 
	* @Title: batchSaveLongitudinalTableData 
	* @Description: 批量新增LongitudinalTableData
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	void batchInsert(List<LongitudinalTableData> entity);
	
	/**
	 * 
	* @Title: updateLongitudinalTableData 
	* @Description: 编辑LongitudinalTableData
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	void update(LongitudinalTableData entity);
	
	/**
	 * 
	* @Title: deleteLongitudinalTableDataById 
	* @Description: 根据id删除LongitudinalTableData
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	void deleteLongitudinalTableDataById(Integer id);
	
	/**
	 * 
	* @Title: deleteLongitudinalTableDataByIds 
	* @Description: 根据id批量删除LongitudinalTableData
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	void deleteLongitudinalTableDataByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findLongitudinalTableDataById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	LongitudinalTableData findLongitudinalTableDataById(Integer id);
	
	/**
	 * 
	* @Title: findLongitudinalTableDataByPage 
	* @Description: 分页查询
	* @return
	* @return List<LongitudinalTableData>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by wangzx
	 */
	List<LongitudinalTableData> findLongitudinalTableDataByPage(LongitudinalTableData search);
	/**
	 * 
	* @Title: findLongitudinalTableDataByPage 
	* @Description: 查询某公司的纵向表数据
	* @return
	* @return List<LongitudinalTableData>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by chopin
	 */
	public List<LongitudinalTableData> findByCompany(Integer companyId);
	
	/**
	 * 
	* @Title: findLongitudinalTableDataByPage 
	* @Description: 查询某公司的纵向表数据-自定义条件
	* @return
	* @return List<LongitudinalTableData>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by chopin
	 */
	public List<LongitudinalTableData> query(LongitudinalTableData search);
	
	/**
	 * 
	* @Title: findLongitudinalTableDataByPage 
	* @Description: 查询当前行数
	* @return
	* @return List<LongitudinalTableData>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by chopin
	 */
	public int getCurrtRow(LongitudinalTableData search);
	
	/**
	 * 
	* @Title: findLongitudinalTableDataByPage 
	* @Description: 查询当前主键累加值
	* @return
	* @return List<LongitudinalTableData>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-1-13
	* @user by chopin
	 */
	public int getSequence();
}