package com.yuxin.wx.auth.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.auth.IAuthPrivilegeCategoryService;
import com.yuxin.wx.auth.mapper.AuthPrivilegeCategoryMapper;
import com.yuxin.wx.model.auth.AuthPrivilege;
import com.yuxin.wx.model.auth.AuthPrivilegeCategory;
import com.yuxin.wx.vo.privilege.PrivilegeListVo;

/**
 * Service Implementation:AuthPrivilegeCategory
 * @author wang.zx
 * @date 2015-1-27
 */
@Service
@Transactional
public class AuthPrivilegeCategoryServiceImpl extends BaseServiceImpl implements IAuthPrivilegeCategoryService {

	@Autowired
	private AuthPrivilegeCategoryMapper authPrivilegeCategoryMapper;
	
	/**
	 * 
	* @Title: saveAuthPrivilegeCategory
	* @Description: 新增AuthPrivilegeCategory
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  insert(AuthPrivilegeCategory authPrivilegeCategory){
		authPrivilegeCategoryMapper.insert(authPrivilegeCategory);
	};
	
	/**
	 * 
	* @Title: batchSaveAuthPrivilegeCategory 
	* @Description: 批量新增AuthPrivilegeCategory
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  batchInsert(List<AuthPrivilegeCategory> T){
	    authPrivilegeCategoryMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateAuthPrivilegeCategory 
	* @Description: 编辑AuthPrivilegeCategory
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  update(AuthPrivilegeCategory T){
	    authPrivilegeCategoryMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteAuthPrivilegeCategoryById 
	* @Description: 根据id删除AuthPrivilegeCategory
	* @param id
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  deleteAuthPrivilegeCategoryById(Integer id){
	    authPrivilegeCategoryMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteAuthPrivilegeCategoryByIds 
	* @Description: 根据id批量删除AuthPrivilegeCategory
	* @param ids
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  deleteAuthPrivilegeCategoryByIds(Integer[] ids){
	    authPrivilegeCategoryMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findAuthPrivilegeCategoryById 
	* @Description: 根据id查询
	* @param id
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public AuthPrivilegeCategory findAuthPrivilegeCategoryById(Integer id){
		return authPrivilegeCategoryMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findAuthPrivilegeCategoryByPage 
	* @Description: 分页查询
	* @return
	* @return List<AuthPrivilegeCategory>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public List<AuthPrivilegeCategory> findAuthPrivilegeCategoryByPage(AuthPrivilegeCategory search){
		return authPrivilegeCategoryMapper.page(search);
	}
	
 /**
  * 
 * @Title: findAuthPrivilegeCategoryByPage 
 * @Description: 查询所有信息
 * @return
 * @return List<AuthPrivilegeCategory>    返回类型 
 * @throws 
 * @exception 
 * @date 2015-1-27
 * @user by chopin
  */
 @Override
	public List<AuthPrivilegeCategory> finAll(){
	    return authPrivilegeCategoryMapper.queryAll();
	}

@Override
public List<PrivilegeListVo> queryOnePrivilege(PrivilegeListVo privilege) {
	List<AuthPrivilege> arr1=null;
	List<PrivilegeListVo> arr=authPrivilegeCategoryMapper.queryOnePrivilege(privilege);
	for(PrivilegeListVo p:arr){
		arr1=authPrivilegeCategoryMapper.queryTwoPrivilege(p.getId().toString());
		p.setArr(arr1);
	}
	return arr;
}

@Override
public List<AuthPrivilegeCategory> findByCompanyId(Integer companyId) {
	// TODO Auto-generated method stub
	return authPrivilegeCategoryMapper.findByCompanyId(companyId);
}

@Override
public List<PrivilegeListVo> queryOnePrivilegeByCompanys(
		PrivilegeListVo privilege) {
	List<AuthPrivilege> arr1=null;
	List<PrivilegeListVo> arr=authPrivilegeCategoryMapper.queryOnePrivilegeByCompanys(privilege);
	for(PrivilegeListVo p:arr){
		arr1=authPrivilegeCategoryMapper.queryTwoPrivilege(p.getId().toString());
		p.setArr(arr1);
	}
	return arr;
}
	
}
