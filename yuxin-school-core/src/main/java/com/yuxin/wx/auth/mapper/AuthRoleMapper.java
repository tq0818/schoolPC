package com.yuxin.wx.auth.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.vo.privilege.AreaSchoolInfoVo;
import com.yuxin.wx.vo.privilege.ClassInfoVo;
import com.yuxin.wx.vo.privilege.EduSubjectClassTeacherInfo;
import com.yuxin.wx.vo.privilege.SubjectInfoVo;
import com.yuxin.wx.vo.privilege.UserRolesListVo;
import com.yuxin.wx.vo.user.UsersAreaRelation;
/**
 * Service Interface:Users
 * @author wang.zx
 * @date 2014-12-5
 */
public interface AuthRoleMapper extends BaseMapper<AuthRole> {
    List<AuthRole> findListByUser(Integer userId);
    void deleteAll();
    AuthRole findById(String roleUid);
    void deleteById(String roleUid);
    void deleteByIds(String[] roleUids);
    
    List<UserRolesListVo> queryAllUser(UserRolesListVo search);
    List<UserRolesListVo> queryNewAllUser(UserRolesListVo search);
    
    int queryAllUserCount(UserRolesListVo search);
    int queryNewAllUserCount(UserRolesListVo search);
    
    List<AuthRole> findAuthRoleListByUser(Integer userId);
    
    List<AuthRole> findByCompanyId(Integer companyId);
    
    /*List<AuthRole> queryAll();*/
    
    List<AuthRole> queryRolesByCondition(AuthRole search);
    
    List<AuthRole> queryRolesByConditionInfo(AuthRole search);
    
    List<AuthRole> queryRolesByUid(String rUids);
    
    List<AreaSchoolInfoVo> queryAreaSchoolInfo();
    
    List<UsersAreaRelation> queryUsersAreaRelationByUserId(String userId);
    
    List<SubjectInfoVo> queryAllSubjectInfo(String companyId);
    
    List<ClassInfoVo> queryAllGradeInfos(Map<String,Object> params);
    
    List<EduSubjectClassTeacherInfo>queryEduSubjectClassTeacherInfo(Map<String,Object> params);
}