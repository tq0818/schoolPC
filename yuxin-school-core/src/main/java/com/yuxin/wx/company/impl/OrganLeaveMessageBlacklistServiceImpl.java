package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.IOrganLeaveMessageBlacklistService;
import com.yuxin.wx.company.mapper.OrganLeaveMessageBlacklistMapper;
import com.yuxin.wx.model.company.OrganLeaveMessageBlacklist;


/**
 * Service Implementation:OrganLeaveMessageBlacklist
 * @author chopin
 * @date 2016-8-5
 */
@Service
@Transactional
public class OrganLeaveMessageBlacklistServiceImpl extends BaseServiceImpl implements IOrganLeaveMessageBlacklistService {

	@Autowired
	private OrganLeaveMessageBlacklistMapper organLeaveMessageBlacklistMapper;
	
	/**
	 * 
	* @Title: saveOrganLeaveMessageBlacklist
	* @Description: 新增OrganLeaveMessageBlacklist
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-5
	* @user by chopin
	 */
	@Override
	public void insert(OrganLeaveMessageBlacklist entity){
		organLeaveMessageBlacklistMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveOrganLeaveMessageBlacklist 
	* @Description: 批量新增OrganLeaveMessageBlacklist
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-5
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<OrganLeaveMessageBlacklist> entity){
		organLeaveMessageBlacklistMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateOrganLeaveMessageBlacklist 
	* @Description: 编辑OrganLeaveMessageBlacklist
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-5
	* @user by chopin
	 */
	@Override
	public void update(OrganLeaveMessageBlacklist entity){
		organLeaveMessageBlacklistMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteOrganLeaveMessageBlacklistById 
	* @Description: 根据id删除OrganLeaveMessageBlacklist
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-5
	* @user by chopin
	 */
	 @Override
	public void deleteOrganLeaveMessageBlacklistById(Integer id){
		organLeaveMessageBlacklistMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteOrganLeaveMessageBlacklistByIds 
	* @Description: 根据id批量删除OrganLeaveMessageBlacklist
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-5
	* @user by chopin
	 */
	@Override
	public void deleteOrganLeaveMessageBlacklistByIds(Integer[] ids){
		organLeaveMessageBlacklistMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findOrganLeaveMessageBlacklistById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-5
	* @user by chopin
	 */
	@Override
	public OrganLeaveMessageBlacklist findOrganLeaveMessageBlacklistById(Integer id){
		return organLeaveMessageBlacklistMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findOrganLeaveMessageBlacklistByPage 
	* @Description: 分页查询
	* @return
	* @return List<OrganLeaveMessageBlacklist>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-5
	* @user by chopin
	 */
	@Override
	public List<OrganLeaveMessageBlacklist> findOrganLeaveMessageBlacklistByPage(OrganLeaveMessageBlacklist search){
		return organLeaveMessageBlacklistMapper.page(search);
	}

	@Override
	public OrganLeaveMessageBlacklist queryByIp(OrganLeaveMessageBlacklist search) {
		return organLeaveMessageBlacklistMapper.queryByIp(search);
	}

	@Override
	public List<String> queryList() {
		return organLeaveMessageBlacklistMapper.queryList();
	};
}
