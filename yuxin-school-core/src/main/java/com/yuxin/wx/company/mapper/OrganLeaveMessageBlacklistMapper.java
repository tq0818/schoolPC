package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.OrganLeaveMessageBlacklist;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface OrganLeaveMessageBlacklistMapper extends BaseMapper<OrganLeaveMessageBlacklist> {
	
	OrganLeaveMessageBlacklist queryByIp(OrganLeaveMessageBlacklist search);
	
	List<String> queryList();
}