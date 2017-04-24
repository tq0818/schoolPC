package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigTableService;
import com.yuxin.wx.model.system.SysConfigTable;
import com.yuxin.wx.system.mapper.SysConfigTableMapper;

/**
 * Service Implementation:SysConfigTable
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class SysConfigTableServiceImpl extends BaseServiceImpl implements ISysConfigTableService {

	@Autowired
	private SysConfigTableMapper sysConfigTableMapper;
	
	/**
	 * 
	* @Title: saveSysConfigTable
	* @Description: 新增SysConfigTable
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(SysConfigTable sysConfigTable){
		sysConfigTableMapper.insert(sysConfigTable);
	}
	
	/**
	 * 
	* @Title: batchSaveSysConfigTable 
	* @Description: 批量新增SysConfigTable
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<SysConfigTable> sysConfigTables){
		if(sysConfigTables != null && !sysConfigTables.isEmpty()){
			sysConfigTableMapper.batchInsert(sysConfigTables);
		}
	}
	
	/**
	 * 
	* @Title: updateSysConfigTable 
	* @Description: 编辑SysConfigTable
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(SysConfigTable sysConfigTable){
		sysConfigTableMapper.update(sysConfigTable);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigTableById 
	* @Description: 根据id删除SysConfigTable
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigTableByTableNameAndColumn(String tableName, String columnName){
		sysConfigTableMapper.deleteSysConfigTableByTableNameAndColumn(tableName, columnName);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigTableByIds 
	* @Description: 根据id批量删除SysConfigTable
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigTableByIds(Integer[] ids){
		sysConfigTableMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findSysConfigTableById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public SysConfigTable findSysConfigTableById(Integer id){
		return sysConfigTableMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findSysConfigTableByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigTable>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<SysConfigTable> findSysConfigTableByPage(SysConfigTable search){
		Integer totalRecords = sysConfigTableMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return sysConfigTableMapper.page(search);
	}

	@Override
	public List<SysConfigTable> queryAll() {
		List<SysConfigTable> configList = sysConfigTableMapper.queryAll();
		return configList;
	}

	@Override
	public SysConfigTable findSysConfigTableByTableNameAndColumn(String tableName, String columnName) {
		SysConfigTable sysConfigTable = sysConfigTableMapper.findSysConfigTableByTableNameAndColumn(tableName, columnName);
		return sysConfigTable;
	}
	
	
}
