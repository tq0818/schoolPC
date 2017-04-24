package com.yuxin.wx.user.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.user.UserLoginSession;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.privilege.RoleVo;
import com.yuxin.wx.vo.privilege.UserRoleVo;
import com.yuxin.wx.vo.user.UsersVo;
/**
 * Service Interface:Users
 * @author wang.zx
 * @date 2014-12-5
 */
public interface UsersMapper extends BaseMapper<Users> {
	/**
	 * Class Name: IUserMapper.java
	 * @Description: TODO(根据用户名获取用户)
	 * @author Chopin
	 * @date 2014年12月10日 下午9:20:28
	 * @version 1.0
	 * @param userName
	 * @return
	 */
	public Users queryByName(String userName);
	
	/**
	 * 
	 * Class Name: UsersMapper.java
	 * @Description: 检查用户是否存在
	 * @author Chopin
	 * @date 2015年1月25日
	 * @version 1.0
	 * @param user
	 * @return
	 */
	Integer checkUser(Users user);
	
	/**
  * 
  * Class Name: UsersMapper.java
  * @Description: 查询用户列表
  * @author Chopin
  * @date 2015年1月25日
  * @version 1.0
  * @param user
  * @return
  */
	List<UserRoleVo> findUserList(UserRoleVo userRoleVo );
	
	Integer pageCount2(UserRoleVo userRoleVo);
	
	
	Integer checkValid(Users user);
	
	List<Users> findUsersByRoleId (RoleVo roleVo);
	
	Integer findIdByName(String userName);
	
	Users findUserByName(String userName);
	
	Users findUserByCompanyIdAndUserType(Integer companyId);
	
	void updateUserByCompanyIdAndUserType(Users users);
	
	List<Users> findUserByCondition(Users users);

	public int selectCount(Integer companyId);
	
	void updateStatus(Users users);
	
	 Users findByUserTypeAndCompanyId(Users users);
	 
	 Integer isExixits(Users users);
	 
	 List<UserLoginSession> queryUserTotalList(UserLoginSession u);
	 
	 int queryUserTotalCount(UserLoginSession u);
	 
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
	   * Class Name: UsersMapper.java
	   * @Description: 条件查询用户
	   * @author zhang.zx
	   * @date 2015年8月31日 上午11:04:05
	   * @modifier
	   * @modify-date 2015年8月31日 上午11:04:05
	   * @version 1.0
	   * @param user
	   * @return
	   */
	  Users findUsersByConfition(Users user);
	  
	  List<Users> queryuserIsExist(Users user);
}