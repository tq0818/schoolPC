package com.yuxin.wx.api.auth;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.privilege.AreaInfoVo;
import com.yuxin.wx.vo.privilege.EduSubjectClassTeacherInfo;
import com.yuxin.wx.vo.privilege.GradeInfoVo;
import com.yuxin.wx.vo.privilege.SubjectInfoVo;
import com.yuxin.wx.vo.privilege.TreeNode;
import com.yuxin.wx.vo.privilege.UserRolesListVo;

/**
 * Service Interface:AuthRole
 * @author wang.zx
 * @date 2015-1-26
 */
public interface IAuthRoleService  {
	/**
	 * 
	* @Title: saveAuthRole
	* @Description: 新增AuthRole
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void insert(AuthRole entity);
	
	/**
	 * 
	* @Title: batchSaveAuthRole 
	* @Description: 批量新增AuthRole
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void batchInsert(List<AuthRole> entity);
	
	/**
	 * 
	* @Title: updateAuthRole 
	* @Description: 编辑AuthRole
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void update(AuthRole entity);
	
	/**
	 * 
	* @Title: deleteAuthRoleById 
	* @Description: 根据id删除AuthRole
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void deleteAuthRoleById(String id);
	
	/**
	 * 
	* @Title: deleteAuthRoleByIds 
	* @Description: 根据id批量删除AuthRole
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	void deleteAuthRoleByIds(String[] ids);
	
	/**
	 * 
	* @Title: findAuthRoleById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	AuthRole findAuthRoleById(String id);
	
	/**
	 * 
	* @Title: findAuthRoleByPage 
	* @Description: 分页查询
	* @return
	* @return List<AuthRole>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-26
	* @user by wangzx
	 */
	List<AuthRole> findAuthRoleByPage(AuthRole search);
	/**
	 * 
	 * Class Name: IAuthRoleService.java
	 * @Description: 获取所有权限列表
	 * @author Chopin
	 * @date 2015年1月27日
	 * @version 1.0
	 * @return
	 */
	List<AuthRole> findAllAuthRole();
	
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
 public String saveRoles(List<TreeNode> nodes,Users loginUser);
 
 
 /**
  * 
  * Class Name: IAuthRoleService.java
  * @Description: 查询用户角色
  * @author zhang.zx
  * @date 2015年5月18日 下午9:05:18
  * @modifier
  * @modify-date 2015年5月18日 下午9:05:18
  * @version 1.0
  * @param search
  * @return
  */
 PageFinder<UserRolesListVo>  queryAllUser(UserRolesListVo search);
 PageFinder<UserRolesListVo>  queryNewAllUser(UserRolesListVo search);
 
 /**
  * 
  * Class Name: IAuthRoleService.java
  * @Description: 根据用户id查询角色
  * @author zhang.zx
  * @date 2015年5月20日 下午2:35:53
  * @modifier
  * @modify-date 2015年5月20日 下午2:35:53
  * @version 1.0
  * @param userId
  * @return
  */
 List<AuthRole> queryAuthRoleListByUser(Integer userId);
 /**
  * 
  * Class Name: IAuthRoleService.java
  * @Description: 根据公司Id查询
  * @author ycl
  * @date 2015-5-22 下午6:37:35
  * @modifier
  * @modify-date 2015-5-22 下午6:37:35
  * @version 1.0
  * @param companyId
  * @return
  */
 List<AuthRole> findByCompanyId(Integer companyId);
 
 /**
  * 
  * Class Name: IAuthRoleService.java
  * @Description: 查询所有用户角色
  * @author zhang.zx
  * @date 2015年6月3日 下午3:52:45
  * @modifier
  * @modify-date 2015年6月3日 下午3:52:45
  * @version 1.0
  * @return
  */
 List<AuthRole> findAll();

 /**
  * 
  * Class Name: IAuthRoleService.java
  * @Description: 条件查询角色信息
  * @author zhang.zx
  * @date 2015年6月17日 下午12:01:55
  * @modifier
  * @modify-date 2015年6月17日 下午12:01:55
  * @version 1.0
  * @param search
  * @return
  */
 List<AuthRole> queryRolesByCondition(AuthRole search);
 
 /**
  * 
  * Class Name: IAuthRoleService.java
  * @Description: 条件查询角色信息
  * @author zhang.zx
  * @date 2015年6月17日 下午12:01:55
  * @modifier
  * @modify-date 2015年6月17日 下午12:01:55
  * @version 1.0
  * @param search
  * @return
  */
 List<AuthRole> queryRolesByConditionInfo(AuthRole search);
 
 /**
  * 
  * Class Name: IAuthRoleService.java
  * @Description: 查询所有的区，学校
  * @author zhang.zx
  * @date 2015年6月17日 下午12:01:55
  * @modifier
  * @modify-date 2015年6月17日 下午12:01:55
  * @version 1.0
  * @param userId 用户标识号，isArea 当前校所属的行政级别，0数校，1区，2学校，areaCode 当前学校机构代码
  * @return
  */
 List<AreaInfoVo> queryAreaSchoolInfo(String userId,String isArea,String areaCode,String roleName);
 
 /**
  * 
  * Class Name: IAuthRoleService.java
  * @Description: 自定义角色菜单
  * @author zhang.zx
  * @date 2016年1月11日 下午5:35:12
  * @modifier
  * @modify-date 2016年1月11日 下午5:35:12
  * @version 1.0
  * @param companyId
  * @param name
  * @param privlageIds
  * @return
  */
 boolean addAuthRoleandMenu(Integer companyId,String name,String privlageIds,Integer roleFlag);
 
 /**
  * 
  * Class Name: IAuthRoleService.java
  * @Description:查询角色
  * @author zhang.zx
  * @date 2016年1月11日 下午6:24:18
  * @modifier
  * @modify-date 2016年1月11日 下午6:24:18
  * @version 1.0
  * @param rUids
  * @return
  */
 List<AuthRole> queryRolesByUid(String rUids);
 
 	boolean hasRoleFlag(Integer userId);
 	
 	/**
 	 * 
 	 * Class Name: IAuthRoleService.java
 	 * @Description: 判断用户是否拥有某个权限
 	 * @author zhang.zx
 	 * @date 2016年1月26日 下午2:59:45
 	 * @modifier
 	 * @modify-date 2016年1月26日 下午2:59:45
 	 * @version 1.0
 	 * @param userId 当前登录用户Id
 	 * @param privilegeCode 权限名称
 	 * @return true:有此权限，false:无此权限
 	 */
 	boolean checkUserHasPrivilege(Integer userId,String privilegeCode);
 	/**
 	 * 查询该分校所有的科目
 	 * @return
 	 */
 	List<SubjectInfoVo> queryAllSubjectInfo(String companyId);
 	
 	List<GradeInfoVo> queryAllGradeInfos(String areaCode,String userId,String roleName);
 	/**
 	 * 查询用户任课班级
 	 * @param areaCode
 	 * @param userId
 	 * @return
 	 */
 	List<EduSubjectClassTeacherInfo> queryEduSubjectClassTeacherInfo(String areaCode,String userId);
}