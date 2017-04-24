package com.yuxin.wx.user.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.user.UserHistory;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface UserHistoryMapper extends BaseMapper<UserHistory> {
	
	int queryCountByClassTypeIdAndUserId(UserHistory search);
}