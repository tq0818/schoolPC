package com.yuxin.wx.auth.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.auth.IAuthRolePrivilegeService;
import com.yuxin.wx.auth.mapper.AuthRolePrivilegeMapper;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.auth.AuthRolePrivilege;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.privilege.PrivilegeVo;
import com.yuxin.wx.vo.privilege.TreeNode;

/**
 * Service Implementation:AuthRolePrivilege
 * @author wang.zx
 * @date 2015-1-27
 */
@Service
@Transactional
public class AuthRolePrivilegeServiceImpl extends BaseServiceImpl implements IAuthRolePrivilegeService {

	@Autowired
	private AuthRolePrivilegeMapper authRolePrivilegeMapper;
	
	/**
	 * 
	* @Title: saveAuthRolePrivilege
	* @Description: 新增AuthRolePrivilege
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  insert(AuthRolePrivilege authRolePrivilege){
	    authRolePrivilegeMapper.insert(authRolePrivilege);
	};
	
	/**
	 * 
	* @Title: batchSaveAuthRolePrivilege 
	* @Description: 批量新增AuthRolePrivilege
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  batchInsert(List<AuthRolePrivilege> T){
	    authRolePrivilegeMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateAuthRolePrivilege 
	* @Description: 编辑AuthRolePrivilege
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  update(AuthRolePrivilege T){
	    authRolePrivilegeMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteAuthRolePrivilegeById 
	* @Description: 根据id删除AuthRolePrivilege
	* @param id
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  deleteAuthRolePrivilegeById(Integer id){
	    authRolePrivilegeMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteAuthRolePrivilegeByIds 
	* @Description: 根据id批量删除AuthRolePrivilege
	* @param ids
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  deleteAuthRolePrivilegeByIds(Integer[] ids){
	    authRolePrivilegeMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findAuthRolePrivilegeById 
	* @Description: 根据id查询
	* @param id
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public AuthRolePrivilege findAuthRolePrivilegeById(Integer id){
		return authRolePrivilegeMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findAuthRolePrivilegeByPage 
	* @Description: 分页查询
	* @return
	* @return List<AuthRolePrivilege>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public List<AuthRolePrivilege> findAuthRolePrivilegeByPage(AuthRolePrivilege search){
		return authRolePrivilegeMapper.page(search);
	};
	
 /**
  * 
 * @Title: findAuthRolePrivilegeByPage 
 * @Description: 分页查询
 * @return
 * @return List<AuthRolePrivilege>    返回类型 
 * @throws 
 * @exception 
 * @date 2015-1-27
 * @user by wangzx
  */
	@Override
	public List<AuthRolePrivilege> findPrivilegeByRole(String roleId){
	    return authRolePrivilegeMapper.findByRoleId(roleId);
	}
	
 @Override
 public String saveRolePrivilege(List<TreeNode> nodes,String roleId,Users loginUser){
     authRolePrivilegeMapper.deleteByRoleId(roleId);
     List<AuthRolePrivilege> rolePrivileges=new ArrayList<AuthRolePrivilege>();
     for(TreeNode node : nodes){
         System.out.println("===>"+node.getId());
         if(node.getId().contains("s")){
             AuthRolePrivilege rolePrivilege =new AuthRolePrivilege();
             rolePrivilege.setId(null);
             rolePrivilege.setRoleUid(roleId);
             rolePrivilege.setPrivilegeId(Integer.parseInt(node.getId().substring(1)));
             rolePrivilege.setCreateTime(new Date());
             rolePrivilege.setCreator(loginUser.getUsername());
             rolePrivilege.setUpdateTime(new Date());
             rolePrivilege.setUpdator(loginUser.getUsername());
             rolePrivileges.add(rolePrivilege);
         }
     }
     authRolePrivilegeMapper.batchInsert(rolePrivileges);
     return "success";
 }

	@Override
	public List<PrivilegeVo> findUserPrivileges(String roleId) {
		// TODO Auto-generated method stub
		return authRolePrivilegeMapper.findUserPrivileges(roleId);
	}
 
	
}
