package com.yuxin.wx.api.user;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.user.UserLoginSession;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.privilege.RoleVo;
import com.yuxin.wx.vo.privilege.UserRoleVo;
import com.yuxin.wx.vo.user.InitDataVo;
import com.yuxin.wx.vo.user.UsersAreaRelation;

/**
 * Service Interface:Users
 * @author wang.zx
 * @date 2014-12-5
 */
public interface IUsersService  {
	/**
	 * 
	* @Title: saveUsers
	* @Description: 新增Users
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(Users users);
	
	/**
	 * 
	* @Title: batchSaveUsers 
	* @Description: 批量新增Users
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<Users> users);
	
	/**
	 * 
	* @Title: updateUsers 
	* @Description: 编辑Users
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(Users users);
	
	/**
	 * 
	* @Title: deleteUsersById 
	* @Description: 根据id删除Users
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteUsersById(Integer id);
	
	/**
	 * 
	* @Title: deleteUsersByIds 
	* @Description: 根据id批量删除Users
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteUsersByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findUsersById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	Users findUsersById(Map<String, Object> param);

	Users findUsersById(Integer id);
	
	/**
	 * 
	* @Title: findUsersByPage 
	* @Description: 分页查询
	* @return
	* @return List<Users>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<Users> findUsersByPage(Users search);
	
	/**
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @author wang.zx 
	 * @date 2014-12-11 下午4:08:45
	 * @version 1.0
	 * @param userName
	 * @return
	 */
	Users queryUserByName(String userName);
	/**
	 * 通过用户名称和companyId查询用户
	 * @param userName 用户名称
	 * @param companyId 机构标识号
	 * @return 用户
	 */
	Users queryUserByCondition(String userName,Integer companyId);
	
	/**
	 * 
	 * Class Name: IUsersService.java
	 * @Description: 检查用户有效性
	 * @author Chopin
	 * @date 2015年1月25日
	 * @version 1.0
	 * @param user 
	 */
	Boolean checkUserValid(Users user);
	
	/**
  * 
  * Class Name: IUsersService.java
  * @Description: 查询用户列表
  * @author Chopin
  * @date 2015年1月25日
  * @version 1.0
  * @param user 
  */
	PageFinder<UserRoleVo> findUserList(UserRoleVo userRoleVo );
	
	/**
	 * 
	 * Class Name: IUsersService.java
	 * @Description: 新增机构用户
	 * @author Chopin
	 * @date 2015年1月28日
	 * @version 1.0
	 * @param vo
	 * @param currtUser
	 * @return
	 */
 String addOrganUser(UserRoleVo vo,Integer currtUser);
 /**
  * 
  * Class Name: IUsersService.java
  * @Description: 修改机构用户
  * @author Chopin
  * @date 2015年1月28日
  * @version 1.0
  * @param vo
  * @param currtUser
  * @return
  */
 String editOrganUser(UserRoleVo vo,Integer currtUser);
 
 /**
  * 
  * Class Name: IUsersService.java
  * @Description: 检查用户是否已经存在
  * @author Chopin
  * @date 2015年1月29日
  * @version 1.0
  * @param user
  * @return
  */
 Boolean isExists(String key,String value);
 
 List<Users> findUsersByRoleId(RoleVo roleVo);
 /**
  * 
  * Class Name: IUsersService.java
  * @Description: 根据公司ID和用户类型查询机构用户
  * @author ycl
  * @date 2015-4-24 上午9:43:26
  * @modifier
  * @modify-date 2015-4-24 上午9:43:26
  * @version 1.0
  * @param users
  * @return
  */
 Users findUserByCompanyIdAndUserType(Integer companyId);
/**
 * 
 * Class Name: IUsersService.java
 * @Description: 根据公司ID和用户类型修改机构用户
 * @author ycl
 * @date 2015-4-24 上午9:48:10
 * @modifier
 * @modify-date 2015-4-24 上午9:48:10
 * @version 1.0
 * @param users
 */
 void updateUserByCompanyIdAndUserType(Users users);
 
 /**
  * 
  * Class Name: IUsersService.java
  * @Description: 条件查询用户
  * @author zhang.zx
  * @date 2015-4-24 上午9:48:10
  * @modifier
  * @modify-date 2015-4-24 上午9:48:10
  * @version 1.0
  * @param users
  */
  List<Users> findUserByCondition(Users users);
  /**
   * 
   * Class Name: IUsersService.java
   * @Description: 查询该公司的员工数量
   * @author 权飞虎
   * @date 2015年5月19日 下午8:07:52
   * @modifier
   * @modify-date 2015年5月19日 下午8:07:52
   * @version 1.0
   * @param currentCompanyId
   * @return
   */
  int selectCount(Integer currentCompanyId);

  
  /**
   * 
   * Class Name: IUsersService.java
   * @Description:改变用户状态
   * @author zhang.zx
   * @date 2015年5月19日 上午11:56:37
   * @modifier
   * @modify-date 2015年5月19日 上午11:56:37
   * @version 1.0
   * @param users
   */
  void updateStatus(Users users);
  
  /**
   * 
   * Class Name: IUsersService.java
   * @Description: 根据公司id和当前用户类型查询
   * @author ycl
   * @date 2015-5-22 下午3:56:36
   * @modifier
   * @modify-date 2015-5-22 下午3:56:36
   * @version 1.0
   * @param users
   * @return
   */
  Users findByUserTypeAndCompanyId(Users users);
  
  /**
   * 
   * Class Name: IUsersService.java
   * @Description: 用户注册
   * @author zhang.zx
   * @date 2015年5月22日 上午9:53:37
   * @modifier
   * @modify-date 2015年5月22日 上午9:53:37
   * @version 1.0
   * @param user
   * @param company
   * @param school
   * @return
   */
  void userDefaultRegister(Users user,Company company,SysConfigSchool school);
  
  /**
   * 
   * Class Name: IUsersService.java
   * @Description: 忘记密码时得验证
   * @author yuchanglong
   * @date 2015年7月1日 下午7:30:09
   * @version 1.0
   * @param domain
   * @param userName
   * @param mobile
   * @return
   */
  Integer isExixits(Users users);
  
  /**
   * 
   * Class Name: IUsersService.java
   * @Description: 用户登录人数统计
   * @author zhang.zx
   * @date 2015年7月28日 上午10:54:51
   * @modifier
   * @modify-date 2015年7月28日 上午10:54:51
   * @version 1.0
   * @param u
   * @return
   */
  PageFinder<UserLoginSession> findUserTotalList(UserLoginSession u);
  
  /**
   * 
   * Class Name: IUsersService.java
   * @Description: 查询用户统计图表
   * @author zhang.zx
   * @date 2015年7月28日 上午11:16:40
   * @modifier
   * @modify-date 2015年7月28日 上午11:16:40
   * @version 1.0
   * @param u
   * @return
   */
  List<UserLoginSession> queryUserChar(UserLoginSession u);
  
  /**
   * 
   * Class Name: IUsersService.java
   * @Description: 查询公司id根据手机号
   * @author 周文斌
   * @date 2015-8-4 上午11:36:54
   * @version 1.0
   * @param mobile
   * @return
   */
  Integer findCompanyByMobile(Map<String, Object> param);
  
  /**
   * 
   * Class Name: IUsersService.java
   * @Description: 根据id查询真名
   * @author 周文斌
   * @date 2015-7-23 下午6:07:08
   * @version 1.0
   * @param id
   * @return
   */
  String findRealNameByid(Integer id);
  
  /**
   * 
   * Class Name: IUsersService.java
   * @Description: 条件查询用户信息
   * @author zhang.zx
   * @date 2015年8月31日 上午11:04:40
   * @modifier
   * @modify-date 2015年8月31日 上午11:04:40
   * @version 1.0
   * @param user
   * @return
   */
  Users findUsersByConfition(Users user);
	/**
	 * @caption 初始化用户数据
	 * @author chopin
	 * @param user
	 * @param company
	 * @param school
	 * @return
	 */
  InitDataVo initUserData(Integer userId, Integer companyId, Integer schoolId);
  
  /**
   * 
   * Class Name: IUsersService.java
   * @Description: 查询用户是否存在
   * @author zhang.zx
   * @date 2015年11月5日 下午6:00:21
   * @modifier
   * @modify-date 2015年11月5日 下午6:00:21
   * @version 1.0
   * @param user
   * @return
   */
  List<Users> queryuserIsExist(Users user);
  
  /**
   * 
   * Class Name: IUsersService.java
   * @Description: 根据用户名或者手机号查询用户
   * @author zhang.zx
   * @date 2015年11月5日 下午6:00:21
   * @modifier
   * @modify-date 2015年11月5日 下午6:00:21
   * @version 1.0
   * @param user
   * @return
   */
  List<Users> queryuserByUserNameOrMobile(Users user);
  /**
   * 授权当前用户
   * @param id 用户标识号
   * @param companyId 机构标识号
   */
  void grantUserInCompany(String id,Integer companyId);

	/**
	 * 根据用户名获取对应区域信息
	 * @param id
	 * @return
	 */
    UsersAreaRelation findUsersAreaRelation(Integer id);
    /**
     * 
     * @author jishangyang 2017年12月27日 下午9:27:53
     * @Method: findUsersAreaRelationT 
     * @Description: 根据用户名获取对应区域信息(班主任)
     * @param id
     * @return 
     * @throws
     */
    UsersAreaRelation findUsersAreaRelationT(Integer id);
    /**
     * 
     * @author jishangyang 2017年12月27日 下午9:27:53
     * @Method: findUsersAreaRelationT 
     * @Description: 根据用户名获取对应区域信息(任课教师)
     * @param id
     * @return 
     * @throws
     */
    UsersAreaRelation findUsersAreaRelationR(Integer id);
    /**
     * 用户标识号
     * @param userId 用户标识号
     * @param companyId 机构标识号
     */
    void insertUserCompanyRalation(Integer userId,Integer companyId);

    void deleteByUserId(Integer userId, Integer companyId,String[]roleUid);
    
}
