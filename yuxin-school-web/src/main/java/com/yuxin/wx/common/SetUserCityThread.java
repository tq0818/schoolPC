package com.yuxin.wx.common;

import javax.servlet.http.HttpServletRequest;

import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.WebUtils;

public class SetUserCityThread implements Runnable {
	private HttpServletRequest request;
	private  Users users;
	
	public SetUserCityThread(HttpServletRequest request, Users users) {
		super();
		this.request = request;
		this.users = users;
	}

	@Override
	public void run() {
		WebUtils.setUserCity(request, users);
	}

}
