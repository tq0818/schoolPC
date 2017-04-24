package com.yuxin.wx.auth.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.auth.IAuthPrivilegeService;
import com.yuxin.wx.auth.mapper.AuthPrivilegeMapper;
import com.yuxin.wx.model.auth.AuthPrivilege;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.privilege.TreeNode;

/**
 * Service Implementation:AuthPrivilege
 * @author wang.zx
 * @date 2015-1-27
 */
@Service
@Transactional
public class AuthPrivilegeServiceImpl extends BaseServiceImpl implements IAuthPrivilegeService {

	@Autowired
	private AuthPrivilegeMapper authPrivilegeMapper;
	
	/**
	 * 
	* @Title: saveAuthPrivilege
	* @Description: 新增AuthPrivilege
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	public void  insert(AuthPrivilege authPrivilege){
	    authPrivilegeMapper.insert(authPrivilege);
	};
	
	/**
	 * 
	* @Title: batchSaveAuthPrivilege 
	* @Description: 批量新增AuthPrivilege
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	public void  batchInsert(List<AuthPrivilege> T){
	    authPrivilegeMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateAuthPrivilege 
	* @Description: 编辑AuthPrivilege
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	public void  update(AuthPrivilege T){
	    authPrivilegeMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteAuthPrivilegeById 
	* @Description: 根据id删除AuthPrivilege
	* @param id
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	public void  deleteAuthPrivilegeById(Integer id){
	    authPrivilegeMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteAuthPrivilegeByIds 
	* @Description: 根据id批量删除AuthPrivilege
	* @param ids
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	public void  deleteAuthPrivilegeByIds(Integer[] ids){
	    authPrivilegeMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findAuthPrivilegeById 
	* @Description: 根据id查询
	* @param id
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	public AuthPrivilege findAuthPrivilegeById(Integer id){
		return authPrivilegeMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findAuthPrivilegeByPage 
	* @Description: 分页查询
	* @return
	* @return List<AuthPrivilege>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	public List<AuthPrivilege> findAuthPrivilegeByPage(AuthPrivilege search){
		return authPrivilegeMapper.page(search);
	}
	
	public List<AuthPrivilege> findAuthPrivilegeByCategory(Integer categoryId){
	    return authPrivilegeMapper.findListByCategory(categoryId);
	}

	@Override
	public List<AuthPrivilege> findByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return authPrivilegeMapper.findByCompanyId(companyId);
	}

	@Override
	public List<String> findNameById(List<Integer> list) {
		// TODO Auto-generated method stub
		return authPrivilegeMapper.findNameById(list);
	}
	
	
}
