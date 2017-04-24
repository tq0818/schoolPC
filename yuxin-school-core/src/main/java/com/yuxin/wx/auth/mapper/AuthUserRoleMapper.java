package com.yuxin.wx.auth.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.privilege.RoleVo;
import com.yuxin.wx.vo.privilege.UserRoleVo;
/**
 * Service Interface:Users
 * @author wang.zx
 * @date 2014-12-5
 */
public interface AuthUserRoleMapper extends BaseMapper<AuthUserRole> {
    
    AuthUserRole findOne(AuthUserRole authUserRole);
    void deleteByUser(Integer userId);
    List<AuthUserRole> findListByUserId(Integer userId);
    List<AuthUserRole> findByRoleId(String roleId);
    List<RoleVo> findUserRoles(Integer userId);
} 