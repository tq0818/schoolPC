package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.OrganLeaveMessage;
import com.yuxin.wx.vo.user.OrganLeaveMessageVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface OrganLeaveMessageMapper extends BaseMapper<OrganLeaveMessage> {
	
	public List<OrganLeaveMessage> findAll();
	
	List<OrganLeaveMessage> queryOrganLeaveMessageList(OrganLeaveMessageVo olm);
	
	int queryOrganLeaveMessageListCount(OrganLeaveMessageVo olm);
	
	List<OrganLeaveMessage> queryOrganLeaveMessageListForExport(OrganLeaveMessageVo olm);
	
	int queryByIp(OrganLeaveMessage olm);
	
	List<OrganLeaveMessage> queryOrganLeaveMessageListByMobile(OrganLeaveMessage olm);
} 