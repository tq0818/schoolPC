package com.yuxin.wx.resource.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.resource.IResourceUseRecordService;
import com.yuxin.wx.model.resource.ResourceUseRecord;
import com.yuxin.wx.resource.mapper.ResourceUseRecordMapper;

/**
 * Service Implementation:ResourceUseRecord
 * @author wang.zx
 * @date 2016-9-1
 */
@Service
@Transactional
public class ResourceUseRecordServiceImpl extends BaseServiceImpl implements IResourceUseRecordService {

	@Autowired
	private ResourceUseRecordMapper resourceUseRecordMapper;
	
	/**
	 * 
	* @Title: saveResourceUseRecord
	* @Description: 新增ResourceUseRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void insert(ResourceUseRecord entity){
		resourceUseRecordMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveResourceUseRecord 
	* @Description: 批量新增ResourceUseRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<ResourceUseRecord> T){
		resourceUseRecordMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateResourceUseRecord 
	* @Description: 编辑ResourceUseRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void update(ResourceUseRecord T){
		resourceUseRecordMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteResourceUseRecordById 
	* @Description: 根据id删除ResourceUseRecord
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	 @Override
	public void deleteResourceUseRecordById(Integer id){
		resourceUseRecordMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteResourceUseRecordByIds 
	* @Description: 根据id批量删除ResourceUseRecord
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void deleteResourceUseRecordByIds(Integer[] ids){
		resourceUseRecordMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findResourceUseRecordById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public ResourceUseRecord findResourceUseRecordById(Integer id){
		return resourceUseRecordMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findResourceUseRecordByPage 
	* @Description: 分页查询
	* @return
	* @return List<ResourceUseRecord>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public List<ResourceUseRecord> findResourceUseRecordByPage(ResourceUseRecord search){
		return resourceUseRecordMapper.page(search);
	}

	@Override
	public String findUseSumFlow(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return resourceUseRecordMapper.findUseSumFlow(param);
	};
}
