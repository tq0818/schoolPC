package com.yuxin.wx.resource.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.resource.IResourceListService;
import com.yuxin.wx.model.resource.ResourceList;
import com.yuxin.wx.resource.mapper.ResourceListMapper;

/**
 * Service Implementation:ResourceList
 * @author wang.zx
 * @date 2016-9-1
 */
@Service
@Transactional
public class ResourceListServiceImpl extends BaseServiceImpl implements IResourceListService {

	@Autowired
	private ResourceListMapper resourceListMapper;
	
	/**
	 * 
	* @Title: saveResourceList
	* @Description: 新增ResourceList
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void insert(ResourceList entity){
		resourceListMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveResourceList 
	* @Description: 批量新增ResourceList
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<ResourceList> T){
		resourceListMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateResourceList 
	* @Description: 编辑ResourceList
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void update(ResourceList T){
		resourceListMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteResourceListById 
	* @Description: 根据id删除ResourceList
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	 @Override
	public void deleteResourceListById(Integer id){
		resourceListMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteResourceListByIds 
	* @Description: 根据id批量删除ResourceList
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public void deleteResourceListByIds(Integer[] ids){
		resourceListMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findResourceListById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public ResourceList findResourceListById(Integer id){
		return resourceListMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findResourceListByPage 
	* @Description: 分页查询
	* @return
	* @return List<ResourceList>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-1
	* @user by chopin
	 */
	@Override
	public List<ResourceList> findResourceListByPage(ResourceList search){
		return resourceListMapper.page(search);
	}

	@Override
	public String findUseSumStroage(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return resourceListMapper.findUseSumStroage(param);
	}

	@Override
	public Integer totalCount(ResourceList search) {
		// TODO Auto-generated method stub
		return resourceListMapper.pageCount(search);
	}

	@Override
	public List<ResourceList> pageByFilePath(ResourceList search) {
		// TODO Auto-generated method stub
		return resourceListMapper.pageByFilePath(search);
	}

	@Override
	public Integer pageByFilePathCount(ResourceList search) {
		// TODO Auto-generated method stub
		return resourceListMapper.pageByFilePathCount(search);
	}

	@Override
	public ResourceList findById(ResourceList resourceId) {
		return resourceListMapper.findByIdd(resourceId);
	};
}
