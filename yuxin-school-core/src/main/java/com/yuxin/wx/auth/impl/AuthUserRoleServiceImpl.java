package com.yuxin.wx.auth.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.auth.IAuthUserRoleService;
import com.yuxin.wx.auth.mapper.AuthRolePrivilegeMapper;
import com.yuxin.wx.auth.mapper.AuthUserRoleMapper;
import com.yuxin.wx.model.auth.AuthRolePrivilege;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.system.SysConfigServiceGroup;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.system.mapper.SysConfigServiceGroupMapper;
import com.yuxin.wx.system.mapper.SysConfigServiceMapper;
import com.yuxin.wx.user.mapper.UsersMapper;
import com.yuxin.wx.vo.privilege.PrivilegeVo;
import com.yuxin.wx.vo.privilege.RoleVo;
import com.yuxin.wx.vo.privilege.UserRoleVo;
import com.yuxin.wx.vo.system.SysConfigServiceVo;

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
	public void  deleteAuthUserRoleById(Integer id){
	    authUserRoleMapper.deleteByUser(id);
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
	public void deleteByRoleId(Integer id) {
		authUserRoleMapper.deleteById(id);
	}
	
}
