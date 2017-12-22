package com.yuxin.wx.auth.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Array;
import com.yuxin.wx.api.auth.IAuthUserRoleService;
import com.yuxin.wx.auth.mapper.AuthRolePrivilegeMapper;
import com.yuxin.wx.auth.mapper.AuthUserRoleMapper;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.system.SysConfigServiceGroup;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.system.mapper.SysConfigServiceGroupMapper;
import com.yuxin.wx.system.mapper.SysConfigServiceMapper;
import com.yuxin.wx.user.mapper.UsersMapper;
import com.yuxin.wx.vo.privilege.EduMasterClassVo;
import com.yuxin.wx.vo.privilege.PrivilegeVo;
import com.yuxin.wx.vo.privilege.RoleVo;

/**
 * Service Implementation:AuthUserRole
 * @author wang.zx
 * @date 2015-1-27
 */
@Service
@Transactional
public class AuthUserRoleServiceImpl extends BaseServiceImpl implements IAuthUserRoleService {

	@Autowired
	private AuthUserRoleMapper authUserRoleMapper;
	@Autowired
	private AuthRolePrivilegeMapper authRolePrivilegeMapper; 
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private SysConfigServiceMapper sysConfigServiceMapper;
	
	@Autowired
	private SysConfigServiceGroupMapper sysConfigServiceGroupMapper;
	
	/**
	 * 
	* @Title: saveAuthUserRole
	* @Description: 新增AuthUserRole
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  insert(AuthUserRole T){
	    authUserRoleMapper.insert(T);
	};
	
	/**
	 * 
	* @Title: batchSaveAuthUserRole 
	* @Description: 批量新增AuthUserRole
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  batchInsert(List<AuthUserRole> T){
	    authUserRoleMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateAuthUserRole 
	* @Description: 编辑AuthUserRole
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  update(AuthUserRole T){
	    authUserRoleMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteAuthUserRoleById 
	* @Description: 根据id删除AuthUserRole
	* @param id
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  deleteAuthUserRoleById(AuthUserRole role){
	    authUserRoleMapper.deleteByUsers(role);
	};
	
	/**
	 * 
	* @Title: deleteAuthUserRoleByIds 
	* @Description: 根据id批量删除AuthUserRole
	* @param ids
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  deleteAuthUserRoleByIds(Integer[] ids){
	    authUserRoleMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findAuthUserRoleById 
	* @Description: 根据id查询
	* @param id
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public AuthUserRole findAuthUserRoleById(Integer id){
		return authUserRoleMapper.findById(id);
	};
	
 @Override
	public List<AuthUserRole> findByRoleId(String id){
	    return authUserRoleMapper.findByRoleId(id);
	}
	
	/**
	 * 
	* @Title: findAuthUserRoleByPage 
	* @Description: 分页查询
	* @return
	* @return List<AuthUserRole>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public List<AuthUserRole> findAuthUserRoleByPage(AuthUserRole search){
		return authUserRoleMapper.page(search);
	};
	
	/**
	 * 
	 * Class Name: AuthUserRoleServiceImpl.java
	 * @Description: 根据用户查询权限列表结果是String  1,2,3,4,5
	 * @author Chopin
	 * @date 2015年2月1日
	 * @version 1.0
	 * @param userId
	 * @return
	 */
	@Override
	public String findUserRoles(Integer userId){
	    String roles="";
	    List<AuthUserRole> al=authUserRoleMapper.findListByUserId(userId);
	    if(al!=null && !al.isEmpty()){
	    	for(AuthUserRole role: al){
		        roles+=role.getRoleUid()+",";
		    }
		    roles=roles.substring(0,roles.length()-1);
	    }
	    return roles;
	}
 @Override
 public Set<String> queryUserRoles(String userName){
	 Set<String> roles= new HashSet<String>();
	 Integer userId=usersMapper.findIdByName(userName);
	 List<RoleVo> al=authUserRoleMapper.findUserRoles(userId);
	 for(RoleVo role : al){
		 roles.add(role.getRoleName());
	 }
	 return roles;
 }
 
 @Override
public Set<String> queryUserRoles(String userName, Integer companyId) {
	 Set<String> roles= new HashSet<String>();
	 Integer userId=usersMapper.findIdByName(userName);
	 List<RoleVo> al=authUserRoleMapper.findUserRoles(userId);
	 for(RoleVo role : al){
		 if(role.getCompanyId().intValue()!=companyId.intValue()){
			 continue;
		 }
		 roles.add(role.getRoleName());
	 }
	 return roles;
}
 @Override
 public List<RoleVo> queryRolesByConttion(Integer userId, Integer companyId) {
 	 List<RoleVo> roles= new ArrayList<RoleVo>();
 	 List<RoleVo> al=authUserRoleMapper.findUserRoles(userId);
 	 for(RoleVo role : al){
 		 if(role.getCompanyId().intValue()!=companyId.intValue()){
 			 continue;
 		 }
 		 roles.add(role);
 	 }
 	 return roles;
 }
@Override
 public Set<String> findUserPermissions(String userName){
	 	Set<String> permissions=new HashSet<String>();
	 	Set<String> filter=new HashSet<String>();
	 	Users user=usersMapper.findUserByName(userName);
	 	List<RoleVo> roles=authUserRoleMapper.findUserRoles(user.getId());
	 	List<SysConfigService> services=sysConfigServiceMapper.findServiceByCompanyId(user.getCompanyId());
	 	List<String> codes=new ArrayList<String>();
	 	String tempCode="";
	 	for(SysConfigService service :services){
	 		if(service.getGroupSequence()==0){
	 			codes.add(service.getGroupCode());
	 		}else{
	 			tempCode+=(StringUtils.isNotBlank(tempCode)?"_"+service.getGroupCode():service.getGroupCode());
	 		}
	 	}
	 	if(StringUtils.isNotBlank(tempCode)){
	 		codes.add(tempCode);
	 	}
	 	List<SysConfigServiceGroup> privileges=new ArrayList<SysConfigServiceGroup>();
	 	for(String code: codes){
	 		List<SysConfigServiceGroup> list=sysConfigServiceGroupMapper.findByCode(code);
	 		privileges.addAll(list);
	 	}
	 	
	 	SysConfigService search=new SysConfigService();
	 	search.setCompanyId(user.getCompanyId());

	 	for(RoleVo role : roles){
	 		List<PrivilegeVo>  ps=authRolePrivilegeMapper.findUserPrivileges(role.getRoleUid());
	 		for(PrivilegeVo p : ps){
	 			if(p!=null){
	 				permissions.add(p.getPrivilegeName());
	 			}
	 		}
	 	}
	 	//去掉禁用的
	 	Set<String> sets=new HashSet<String>();
			sets.addAll(permissions);
		 	for(String permission : sets){
		 		for(SysConfigServiceGroup p: privileges){
			 		if(permission.equals(p.getPrivilegeName())){
			 			permissions.remove(permission);
			 		}
			 	}
		 	}

	 	return permissions;
 }

	@Override
	public Set<String> findUserPermissions(String userName, Integer companyId) {
		Set<String> permissions=new HashSet<String>();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userName",userName);
		params.put("companyId",companyId);
	 	Users user=usersMapper.queryUserByCondition(params);
	 	List<RoleVo> roles=queryRolesByConttion(user.getId(),companyId);
	 	List<SysConfigService> services=sysConfigServiceMapper.findServiceByCompanyId(user.getCompanyId());
	 	List<String> codes=new ArrayList<String>();
	 	String tempCode="";
	 	for(SysConfigService service :services){
	 		if(service.getGroupSequence()==0){
	 			codes.add(service.getGroupCode());
	 		}else{
	 			tempCode+=(StringUtils.isNotBlank(tempCode)?"_"+service.getGroupCode():service.getGroupCode());
	 		}
	 	}
	 	if(StringUtils.isNotBlank(tempCode)){
	 		codes.add(tempCode);
	 	}
	 	List<SysConfigServiceGroup> privileges=new ArrayList<SysConfigServiceGroup>();
	 	for(String code: codes){
	 		List<SysConfigServiceGroup> list=sysConfigServiceGroupMapper.findByCode(code);
	 		privileges.addAll(list);
	 	}
	 	
	 	SysConfigService search=new SysConfigService();
	 	search.setCompanyId(user.getCompanyId());

	 	for(RoleVo role : roles){
	 		List<PrivilegeVo>  ps=authRolePrivilegeMapper.findUserPrivileges(role.getRoleUid());
	 		for(PrivilegeVo p : ps){
	 			if(p!=null){
	 				permissions.add(p.getPrivilegeName());
	 			}
	 		}
	 	}
	 	//去掉禁用的
	 	Set<String> sets=new HashSet<String>();
			sets.addAll(permissions);
		 	for(String permission : sets){
		 		for(SysConfigServiceGroup p: privileges){
			 		if(permission.equals(p.getPrivilegeName())){
			 			permissions.remove(permission);
			 		}
			 	}
		 	}

	 	return permissions;
	}

	@Override
	public void deleteByRoleId(Integer id) {
		authUserRoleMapper.deleteById(id);
	}

	@Override
	public void insertOrUpdateAreaInfo(String areaCompanyCode,String earaCode, String schoolAaraCode,
			String schoolCode, String gradeCode, String classCode,
			String subjectCode, String[] subJectGradeCode,
			String[] subjectClassCode, String roles,Integer userId) {
		if(StringUtils.isEmpty(roles)) return;
		//1、查询角色名称
		List<AuthRole> authUserRoles=authUserRoleMapper.findRoleName(Arrays.asList(roles.split(",")));
		List<String> roleNames=new ArrayList<String>();
		if(authUserRoles!=null&&authUserRoles.size()>0){
			for(AuthRole role:authUserRoles)
				roleNames.add(role.getRoleName());
		}
		if(roleNames!=null&&roleNames.size()>0){
			//2、如果是区县负责人，则处理区县负责人管辖区域
			if(roleNames.contains("区县负责人")){
				//1、先删除之前的管辖区域
				authUserRoleMapper.deleteUserAreaRalation(userId);
				//2、再插入
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("userId",userId);
				params.put("earaCode",earaCode);
				authUserRoleMapper.insertUserAreaRalation(params);
				
			}
			//3、如果是学校负责人，则处理学校负责人管辖区域
			if(roleNames.contains("学校负责人")){
				//1、先删除之前的管辖区域
				authUserRoleMapper.deleteUserSchoolRalation(userId);
				//2、再插入
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("userId",userId);
				params.put("earaCode",schoolAaraCode);
				params.put("eduSchool",schoolCode);
				authUserRoleMapper.insertUserAreaRalation(params);
			}
			//4、如果是班主任，则处理班主任负责区域
			if(roleNames.contains("班主任")&&StringUtils.isNotEmpty(gradeCode)){
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("userId",userId);
				params.put("areaCompanyCode",areaCompanyCode);
				String levelCode=null;
				String levelName=gradeCode.substring(0,1);
				if("小".equals(levelName)){
					levelCode="STEP_01";
				}else if("初".equals(levelName)){
					levelCode="STEP_02";
				}else if("高".equals(levelName)){
					levelCode="STEP_03";
				}
				params.put("gradeCode",gradeCode.substring(1));
				params.put("classCode",classCode);
				params.put("levelCode",levelCode);
				//1、先将之前在本校的班主任置为空（一个老师只有在一个班当班主任）
				authUserRoleMapper.updateUserClassInvalid(params);
				//2、更新当前班的班主任
				authUserRoleMapper.updateUserClassValid(params);
			}
			//5、如果是任课老师，则处理任课老师负责区域
			if(roleNames.contains("任课老师")){
				//1、删除该老师在本校之前的授课班级
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("userId",userId);
				params.put("areaCompanyCode",areaCompanyCode);
				authUserRoleMapper.deleteTeacherSubject(params);
				//2、执行插入操作
				if(subJectGradeCode!=null&&subJectGradeCode.length>0){
					for(int i=0;i< subJectGradeCode.length;i++){
						String graCode=subJectGradeCode[i];
						EduMasterClassVo vo=new EduMasterClassVo();
						String levelCode=null;
						String levelName=graCode.substring(0,1);
						if("小".equals(levelName)){
							levelCode="STEP_01";
						}else if("初".equals(levelName)){
							levelCode="STEP_02";
						}else if("高".equals(levelName)){
							levelCode="STEP_03";
						}
						vo.setSubjectCode(subjectCode);
						vo.setUserId(userId+"");
						vo.setEduStep(levelCode);
						vo.setEduYear(graCode.substring(1));
						vo.setEduClass(subjectClassCode[i]);
						vo.setEduSchool(areaCompanyCode);
						authUserRoleMapper.insertTeacherSubject(vo);
					}
				}
			}
		}
	}
	
}
