package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ILongitudinalTableColDefineService;
import com.yuxin.wx.model.system.LongitudinalTableColDefine;
import com.yuxin.wx.system.mapper.LongitudinalTableColDefineMapper;

/**
 * Service Implementation:LongitudinalTableColDefine
 * @author wang.zx
 * @date 2016-1-13
 */
@Service
@Transactional
public class LongitudinalTableColDefineServiceImpl extends BaseServiceImpl implements ILongitudinalTableColDefineService {

	@Autowired
	private LongitudinalTableColDefineMapper longitudinalTableColDefineMapper;
	
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
	@Override
	public void insert(LongitudinalTableColDefine entity){
		longitudinalTableColDefineMapper.insert(entity);
	}
	
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
	@Override
	public void batchInsert(List<LongitudinalTableColDefine> entity){
		longitudinalTableColDefineMapper.batchInsert(entity);
	}
	
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
	@Override
	public void update(LongitudinalTableColDefine entity){
		longitudinalTableColDefineMapper.update(entity);
	}
	
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
	@Override
	public void deleteLongitudinalTableColDefineById(Integer id){
		longitudinalTableColDefineMapper.deleteById(id);
	}
	
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
	@Override
	public void deleteLongitudinalTableColDefineByIds(Integer[] ids){
		longitudinalTableColDefineMapper.deleteByIds(ids);
	}
	
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
	@Override
	public LongitudinalTableColDefine findLongitudinalTableColDefineById(Integer id){
		return longitudinalTableColDefineMapper.findById(id);
	}
	
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
	@Override
	public List<LongitudinalTableColDefine> findLongitudinalTableColDefineByPage(LongitudinalTableColDefine search){
		return longitudinalTableColDefineMapper.page(search);
	}
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
	@Override
	public List<LongitudinalTableColDefine> findByCompany(Integer companyId,String tableName){
		return longitudinalTableColDefineMapper.findByCompany(companyId, tableName);
	}
	
}
