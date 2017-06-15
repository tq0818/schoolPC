package com.yuxin.wx.api.tool;

import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.user.Users;

import java.util.List;

/**
 * Service Interface:Users
 */
public interface IToolService {

	/**
	 * 生成对应非直属校的机构账号
	 * @param orgList
	 * @return
	 */
	public String createUsersByOrg(List<Users> userList, List<SysConfigDict> orgListNew);
}