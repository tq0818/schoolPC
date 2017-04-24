package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.IOrganLeaveMessageService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.OrganLeaveMessageMapper;
import com.yuxin.wx.model.company.OrganLeaveMessage;
import com.yuxin.wx.model.user.UserLoginSession;
import com.yuxin.wx.vo.user.OrganLeaveMessageVo;

/**
 * Service Implementation:OrganLeaveMessage
 * @author chopin
 * @date 2016-2-18
 */
@Service
@Transactional
public class OrganLeaveMessageServiceImpl extends BaseServiceImpl implements IOrganLeaveMessageService {

	@Autowired
	private OrganLeaveMessageMapper organLeaveMessageMapper;
	
	/**
	 * 
	* @Title: saveOrganLeaveMessage
	* @Description: 新增OrganLeaveMessage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by chopin
	 */
	@Override
	public void insert(OrganLeaveMessage entity){
		organLeaveMessageMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveOrganLeaveMessage 
	* @Description: 批量新增OrganLeaveMessage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<OrganLeaveMessage> entity){
		organLeaveMessageMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateOrganLeaveMessage 
	* @Description: 编辑OrganLeaveMessage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by chopin
	 */
	@Override
	public void update(OrganLeaveMessage entity){
		organLeaveMessageMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteOrganLeaveMessageById 
	* @Description: 根据id删除OrganLeaveMessage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by chopin
	 */
	 @Override
	public void deleteOrganLeaveMessageById(Integer id){
		organLeaveMessageMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteOrganLeaveMessageByIds 
	* @Description: 根据id批量删除OrganLeaveMessage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by chopin
	 */
	@Override
	public void deleteOrganLeaveMessageByIds(Integer[] ids){
		organLeaveMessageMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findOrganLeaveMessageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by chopin
	 */
	@Override
	public OrganLeaveMessage findOrganLeaveMessageById(Integer id){
		return organLeaveMessageMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findOrganLeaveMessageByPage 
	* @Description: 分页查询
	* @return
	* @return List<OrganLeaveMessage>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by chopin
	 */
	@Override
	public List<OrganLeaveMessage> findOrganLeaveMessageByPage(OrganLeaveMessage search){
		return organLeaveMessageMapper.page(search);
	};
	/**
	 * 
	* @Title: findOrganLeaveMessageByPage 
	* @Description: 查询所有数据
	* @return
	* @return List<OrganLeaveMessage>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by chopin
	 */
	@Override
	public List<OrganLeaveMessage> findAll(){
		return organLeaveMessageMapper.findAll();
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public PageFinder<OrganLeaveMessage> queryOrganLeaveMessageList(
			OrganLeaveMessageVo olm) {
		List<OrganLeaveMessage> data=organLeaveMessageMapper.queryOrganLeaveMessageList(olm);
		int count=organLeaveMessageMapper.queryOrganLeaveMessageListCount(olm);
		PageFinder<OrganLeaveMessage> pageFinder=new PageFinder<OrganLeaveMessage>(olm.getPage(), olm.getPageSize(), count, data);
		return pageFinder;
	}

	@Override
	public List<OrganLeaveMessage> queryOrganLeaveMessageListForExport(
			OrganLeaveMessageVo olm) {
		return organLeaveMessageMapper.queryOrganLeaveMessageListForExport(olm);
	}

	@Override
	public int queryByIp(OrganLeaveMessage olm) {
		return organLeaveMessageMapper.queryByIp(olm);
	}

	@Override
	public List<OrganLeaveMessage> queryOrganLeaveMessageListByMobile(OrganLeaveMessage olm) {
		return organLeaveMessageMapper.queryOrganLeaveMessageListByMobile(olm);
	}
}
