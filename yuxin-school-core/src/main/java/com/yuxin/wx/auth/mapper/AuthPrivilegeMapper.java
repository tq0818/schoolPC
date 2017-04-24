package com.yuxin.wx.auth.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.auth.AuthPrivilege;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.privilege.UserRoleVo;
/**
 * Service Interface:Users
 * @author wang.zx
 * @date 2014-12-5
 */
public interface AuthPrivilegeMapper extends BaseMapper<AuthPrivilege> {
    List<AuthPrivilege> findListByCategory(Integer categoryId);
    void deleteAll();
    List<AuthPrivilege> findByCompanyId(Integer companyId);

	/**
	 * 
	 * Class Name: IAuthPrivilegeService.java
	 * @Description: 根据id查询权限名
	 * @author 周文斌
	 * @date 2015-8-20 下午1:52:07
	 * @version 1.0
	 * @param list
	 * @return
	 */
	List<String> findNameById(List<Integer> list);
}