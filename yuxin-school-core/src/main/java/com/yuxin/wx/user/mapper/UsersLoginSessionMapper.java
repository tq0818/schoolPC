package com.yuxin.wx.user.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.user.UsersLoginSession;
import com.yuxin.wx.vo.company.CompanyManageLoginHistoryVo;


/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface UsersLoginSessionMapper extends BaseMapper<UsersLoginSession> {
	
	UsersLoginSession findHistoryByUserId(String userId);
	void insertManageLoginHistory(CompanyManageLoginHistoryVo comlogin);
	
	String queryLastLogintime(Integer userId);
}