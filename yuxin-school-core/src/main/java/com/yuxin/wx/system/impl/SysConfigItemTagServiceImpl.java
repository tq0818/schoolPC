package com.yuxin.wx.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigItemTagService;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemTag;
import com.yuxin.wx.system.mapper.SysConfigItemTagMapper;

/**
 * Service Implementation:SysConfigItemTag
 * @author chopin
 * @date 2015-9-23
 */
@Service
@Transactional
public class SysConfigItemTagServiceImpl extends BaseServiceImpl implements ISysConfigItemTagService {

	@Autowired
	private SysConfigItemTagMapper sysConfigItemTagMapper;
	
	/**
	 * 
	* @Title: saveSysConfigItemTag
	* @Description: 新增SysConfigItemTag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigItemTag entity){
		sysConfigItemTagMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigItemTag 
	* @Description: 批量新增SysConfigItemTag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigItemTag> entity){
		sysConfigItemTagMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysConfigItemTag 
	* @Description: 编辑SysConfigItemTag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by chopin
	 */
	@Override
	public void update(SysConfigItemTag entity){
		sysConfigItemTagMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigItemTagById 
	* @Description: 根据id删除SysConfigItemTag
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigItemTagById(Integer id){
		sysConfigItemTagMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigItemTagByIds 
	* @Description: 根据id批量删除SysConfigItemTag
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigItemTagByIds(Integer[] ids){
		sysConfigItemTagMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigItemTagById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by chopin
	 */
	@Override
	public SysConfigItemTag findSysConfigItemTagById(Integer id){
		return sysConfigItemTagMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigItemTagByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigItemTag>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-23
	* @user by chopin
	 */
	@Override
	public List<SysConfigItemTag> findSysConfigItemTagByPage(SysConfigItemTag search){
		return sysConfigItemTagMapper.page(search);
	}

	@Override
	public List<SysConfigItemTag> queryTagsBycondition(SysConfigItemTag search) {
		// TODO Auto-generated method stub
		return sysConfigItemTagMapper.queryTagsBycondition(search);
	}

	@Override
	public List<SysConfigItemTag> queryTags(SysConfigItemTag search) {
		return sysConfigItemTagMapper.queryTags(search);
	}

	@Override
	public Integer checkTag(SysConfigItemTag search) {
		return sysConfigItemTagMapper.checkTag(search);
	}

	@Override
	public List<SysConfigItem> queryItemSecondIdList(Map<String, Integer> map) {
		return sysConfigItemTagMapper.queryItemSecondIdList(map);
	};
}
