package com.yuxin.wx.auth.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.auth.AuthRolePrivilege;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.privilege.PrivilegeVo;
import com.yuxin.wx.vo.privilege.UserRoleVo;
/**
 * Service Interface:Users
 * @author wang.zx
 * @date 2014-12-5
 */
public interface AuthRolePrivilegeMapper extends BaseMapper<AuthRolePrivilege> {
	    List<AuthRolePrivilege> findByRoleId(String roleId);
	    void deleteByRoleId(String roleId);
	    List<PrivilegeVo> findUserPrivileges(String roleId);
}