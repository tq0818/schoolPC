package com.yuxin.wx.auth.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.vo.privilege.EduMasterClassVo;
import com.yuxin.wx.vo.privilege.RoleVo;
/**
 * Service Interface:Users
 * @author wang.zx
 * @date 2014-12-5
 */
public interface AuthUserRoleMapper extends BaseMapper<AuthUserRole> {
    
    AuthUserRole findOne(AuthUserRole authUserRole);
    void deleteByUser(Integer userId);
    void deleteByUsers(AuthUserRole role);
    List<AuthUserRole> findListByUserId(Integer userId);
    List<AuthUserRole> findByRoleId(String roleId);
    List<RoleVo> findUserRoles(Integer userId);
    List<AuthRole> findRoleName(List<String> roleIds);
    
    void deleteUserAreaRalation(Integer userId);
    
    void deleteUserSchoolRalation(Integer userId);
    
    void insertUserAreaRalation(Map<String,Object> params);
    
    void updateUserClassInvalid(Map<String,Object> params);
    
    void updateUserClassValid(Map<String,Object> params);
    
    void deleteTeacherSubject(Map<String,Object> params);
    
    void insertTeacherSubject(EduMasterClassVo eduMasterClassVos);
} 