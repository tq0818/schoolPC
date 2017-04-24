package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ILongitudinalTableDataService;
import com.yuxin.wx.model.system.LongitudinalTableData;
import com.yuxin.wx.system.mapper.LongitudinalTableDataMapper;

/**
 * Service Implementation:LongitudinalTableData
 * @author wang.zx
 * @date 2016-1-13
 */
@Service
@Transactional
public class LongitudinalTableDataServiceImpl extends BaseServiceImpl implements ILongitudinalTableDataService {

	@Autowired
	private LongitudinalTableDataMapper longitudinalTableDataMapper;
	
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
	@Override
	public void insert(LongitudinalTableData entity){
		longitudinalTableDataMapper.insert(entity);
	}
	
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
	@Override
	public void batchInsert(List<LongitudinalTableData> entity){
		longitudinalTableDataMapper.batchInsert(entity);
	}
	
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
	@Override
	public void update(LongitudinalTableData entity){
		longitudinalTableDataMapper.update(entity);
	}
	
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
	@Override
	public void deleteLongitudinalTableDataById(Integer id){
		longitudinalTableDataMapper.deleteById(id);
	}
	
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
	@Override
	public void deleteLongitudinalTableDataByIds(Integer[] ids){
		longitudinalTableDataMapper.deleteByIds(ids);
	}
	
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
	@Override
	public LongitudinalTableData findLongitudinalTableDataById(Integer id){
		return longitudinalTableDataMapper.findById(id);
	}
	
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
	@Override
	public List<LongitudinalTableData> findLongitudinalTableDataByPage(LongitudinalTableData search){
		return longitudinalTableDataMapper.page(search);
	}
	
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
	@Override
	public List<LongitudinalTableData> findByCompany(Integer companyId){
		return longitudinalTableDataMapper.findByCompany(companyId);
	}
	
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
	@Override
	public List<LongitudinalTableData> query(LongitudinalTableData search){
		return longitudinalTableDataMapper.query(search);
	}
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
	@Override
	public int getCurrtRow(LongitudinalTableData search){
		Integer row=longitudinalTableDataMapper.getCurrtRow(search);
		return row!=null?row+1:1;
	}
	
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
	@Override
	public int getSequence(){
		Integer sequence=longitudinalTableDataMapper.getSequence();
		
		return sequence!=null?sequence+1:1;
	}
	
	
}
