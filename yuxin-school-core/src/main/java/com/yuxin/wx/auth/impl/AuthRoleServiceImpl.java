package com.yuxin.wx.auth.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.auth.mapper.AuthPrivilegeMapper;
import com.yuxin.wx.auth.mapper.AuthRoleMapper;
import com.yuxin.wx.auth.mapper.AuthRolePrivilegeMapper;
import com.yuxin.wx.auth.mapper.AuthUserRoleMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.auth.AuthRolePrivilege;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.privilege.PrivilegeVo;
import com.yuxin.wx.vo.privilege.TreeNode;
import com.yuxin.wx.vo.privilege.UserRolesListVo;

/**
 * Service Implementation:AuthRole
 * @author wang.zx
 * @date 2015-1-27
 */
@Service
@Transactional
public class AuthRoleServiceImpl extends BaseServiceImpl implements IAuthRoleService {

	@Autowired
	private AuthRoleMapper authRoleMapper;
	
	@Autowired
	private AuthRolePrivilegeMapper authRolePrivilegeMapper;
	
	/**
	 * 
	* @Title: saveAuthRole
	* @Description: 新增AuthRole
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  insert(AuthRole authRole){
		authRoleMapper.insert(authRole);
	};
	
	/**
	 * 
	* @Title: batchSaveAuthRole 
	* @Description: 批量新增AuthRole
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  batchInsert(List<AuthRole> entity){
		authRoleMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateAuthRole 
	* @Description: 编辑AuthRole
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  update(AuthRole entity){
		authRoleMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteAuthRoleById 
	* @Description: 根据id删除AuthRole
	* @param id
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  deleteAuthRoleById(String id){
	    authRoleMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteAuthRoleByIds 
	* @Description: 根据id批量删除AuthRole
	* @param ids
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  deleteAuthRoleByIds(String[] ids){
		authRoleMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findAuthRoleById 
	* @Description: 根据id查询
	* @param id
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public AuthRole findAuthRoleById(String id){
		return authRoleMapper.findById(id);
	};
	
 /**
  * 
 * @Title: findAuthRoleById 
 * @Description: 根据id查询
 * @param id
 * @return public void     返回类型 
 * @throws 
 * @exception 
 * @date 2015-1-27
 * @user by wangzx
  */
 @Override
 public List<AuthRole> findAllAuthRole(){
  return authRoleMapper.queryAll();
 };
	
	/**
	 * 
	* @Title: findAuthRoleByPage 
	* @Description: 分页查询
	* @return
	* @return List<AuthRole>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx	 */
	@Override
	public List<AuthRole> findAuthRoleByPage(AuthRole search){
		return authRoleMapper.page(search);
	};
	
 /**
  * 
 * @Title: saveRoles 
 * @Description: 保存权限树
 * @return
 * @return List<AuthRole>    返回类型 
 * @throws 
 * @exception 
 * @date 2015-1-27
 * @user by chopin  */
 @Override
	public String saveRoles(List<TreeNode> nodes,Users loginUser){
	    authRoleMapper.deleteAll();
	    List<AuthRole> roles=new ArrayList<AuthRole>();
	    for(TreeNode node : nodes){
	        AuthRole role =new AuthRole();
	        role.setRoleName(node.getName());
	        role.setParentId(node.getPId());
	        role.setDescription("");
	        role.setCompanyId(loginUser.getCompanyId());
	        role.setCreateTime(new Date());
	        role.setCreator(loginUser.getUsername());
	        role.setUpdateTime(new Date());
	        role.setUpdator(loginUser.getUsername());
	        roles.add(role);
	    }
     authRoleMapper.batchInsert(roles);
	    return "success";
	}

	@Override
	public PageFinder<UserRolesListVo> queryAllUser(UserRolesListVo search) {
		List<AuthRole> arr=null;
		List<UserRolesListVo> data=authRoleMapper.queryAllUser(search);
		for(UserRolesListVo user:data){
			arr=authRoleMapper.findAuthRoleListByUser(user.getUserId());
			user.setArr(arr);
		}
		int rowCount=authRoleMapper.queryAllUserCount(search);
		PageFinder<UserRolesListVo> pageFinder=new PageFinder<UserRolesListVo>(search.getPage(), search.getPageSize(), rowCount, data);
		return pageFinder;
	}

	@Override
	public List<AuthRole> queryAuthRoleListByUser(Integer userId) {
		
		return authRoleMapper.findAuthRoleListByUser(userId);
	}

	@Override
	public List<AuthRole> findByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return authRoleMapper.findByCompanyId(companyId);
	}

	@Override
	public List<AuthRole> findAll() {
		// TODO Auto-generated method stub
		return authRoleMapper.queryAll();
	}

	@Override
	public List<AuthRole> queryRolesByCondition(AuthRole search) {
		// TODO Auto-generated method stub
		return authRoleMapper.queryRolesByCondition(search);
	}

	@Override
	public boolean addAuthRoleandMenu(Integer companyId, String name,
			String privlageIds,Integer roleFlag) {
		boolean flag=false;
		try {
			AuthRole role=new AuthRole();
			role.setRoleName(name);
			role.setDescription(name);
			role.setCreateTime(new Date());
			role.setCompanyId(companyId);
			role.setRoleFlag(roleFlag);
			authRoleMapper.insert(role);
			role.setRoleUid(role.getId()+"");
			authRoleMapper.update(role);
			String[] priIds=privlageIds.split(",");
			for(int i=0;i<priIds.length;i++){
				AuthRolePrivilege privali=new AuthRolePrivilege();
				privali.setPrivilegeId(Integer.parseInt(priIds[i]));
				privali.setRoleUid(role.getId()+"");
				privali.setCreateTime(new Date());
				authRolePrivilegeMapper.insert(privali);
			}
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			flag=false;
		}
		
		return flag;
	}

	@Override
	public List<AuthRole> queryRolesByUid(String rUids) {
		// TODO Auto-generated method stub
		return authRoleMapper.queryRolesByUid(rUids);
	}

	/**
	 * 根据用户id查询用户角色，根据角色标记判断当前用户是否可跨分校
	 * true:当前可跨分校，false:当前不可跨分校
	 */
	@Override
	public boolean hasRoleFlag(Integer userId) {
		List<AuthRole> list=authRoleMapper.findAuthRoleListByUser(userId);
		if(null!=list){
			for(AuthRole auth:list){
				if(null!=auth && null!=auth.getRoleFlag() && auth.getRoleFlag() ==1){
					return true;
				}
			}
		}
		return false;
	}	

	@Override
	public boolean checkUserHasPrivilege(Integer userId, String privilegeCode) {
		List<AuthRole> roleArr=authRoleMapper.findAuthRoleListByUser(userId);
		if(null!=roleArr){
			for(AuthRole role:roleArr){
				if(null!=role){
					List<PrivilegeVo> prisArr=authRolePrivilegeMapper.findUserPrivileges(role.getRoleUid());
					if(null!=prisArr){
						for(PrivilegeVo prves:prisArr){
							if(null!=prves && privilegeCode.equals(prves.getPrivilegeName())){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	

}
