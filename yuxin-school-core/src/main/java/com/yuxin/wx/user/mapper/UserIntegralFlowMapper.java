package com.yuxin.wx.user.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.user.UserIntegralFlow;
import com.yuxin.wx.vo.user.UserIntegralFlowVO;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface UserIntegralFlowMapper extends BaseMapper<UserIntegralFlow> {
	
	List<UserIntegralFlow> queryIntegralFlowByWhere(UserIntegralFlowVO search);
}