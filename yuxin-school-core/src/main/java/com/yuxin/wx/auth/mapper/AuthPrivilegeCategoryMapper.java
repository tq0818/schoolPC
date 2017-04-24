package com.yuxin.wx.auth.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.auth.AuthPrivilege;
import com.yuxin.wx.model.auth.AuthPrivilegeCategory;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.privilege.PrivilegeListVo;
import com.yuxin.wx.vo.privilege.UserRoleVo;
/**
 * Service Interface:Users
 * @author wang.zx
 * @date 2014-12-5
 */
public interface AuthPrivilegeCategoryMapper extends BaseMapper<AuthPrivilegeCategory> {
	
	List<PrivilegeListVo> queryOnePrivilege(PrivilegeListVo privilege);
	List<AuthPrivilege> queryTwoPrivilege(String parentId);
	List<AuthPrivilegeCategory> findByCompanyId(Integer companyId);
	
	List<PrivilegeListVo> queryOnePrivilegeByCompanys(PrivilegeListVo privilege);
}