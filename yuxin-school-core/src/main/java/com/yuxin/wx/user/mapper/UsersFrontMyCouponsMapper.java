package com.yuxin.wx.user.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.user.UsersFrontMyCoupons;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface UsersFrontMyCouponsMapper extends BaseMapper<UsersFrontMyCoupons> {
	
	List<UsersFrontMyCoupons> findUsersFrontMyCouponsNoPage(UsersFrontMyCoupons search);
	
}