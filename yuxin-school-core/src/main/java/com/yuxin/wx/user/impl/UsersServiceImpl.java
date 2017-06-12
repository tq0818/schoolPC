package com.yuxin.wx.user.impl;

import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.auth.mapper.AuthRoleMapper;
import com.yuxin.wx.auth.mapper.AuthUserRoleMapper;
import com.yuxin.wx.classes.mapper.*;
import com.yuxin.wx.commodity.mapper.CommodityMapper;
import com.yuxin.wx.commodity.mapper.CommodityProductRealtionMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyMapper;
import com.yuxin.wx.company.mapper.CompanyMemberServiceMapper;
import com.yuxin.wx.company.mapper.CompanyPayConfigMapper;
import com.yuxin.wx.company.mapper.CompanyServiceStaticMapper;
import com.yuxin.wx.course.mapper.CourseVideoChapterMapper;
import com.yuxin.wx.course.mapper.CourseVideoLectureMapper;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeModuleRelation;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.model.course.CourseVideoLecture;
import com.yuxin.wx.model.system.*;
import com.yuxin.wx.model.user.UserLoginSession;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.system.mapper.*;
import com.yuxin.wx.user.mapper.UsersMapper;
import com.yuxin.wx.vo.privilege.RoleVo;
import com.yuxin.wx.vo.privilege.UserRoleVo;
import com.yuxin.wx.vo.user.InitDataVo;
import com.yuxin.wx.vo.user.UsersAreaRelation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service Implementation:Users
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class UsersServiceImpl extends BaseServiceImpl implements IUsersService {
	private Log log=LogFactory.getLog("log");
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private AuthRoleMapper authRoleMapper;
	@Autowired
	private AuthUserRoleMapper authUserRoleMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private SysConfigSchoolMapper sysConfigSchoolMapper;
	@Autowired
	private SysConfigCampusMapper sysConfigCampusMapper;
	@Autowired
	private SysConfigItemMapper sysConfigItemMapper;
	@Autowired
	private SysCyclePicMapper sysCyclePicMapper;
	@Autowired
	private SysPageHeadFootMapper sysPageHeadFootMapper;
	@Autowired
	private SysNewsMapper sysNewsMapper;
	@Autowired
	private ClassTypeMapper classTypeMapper;
	@Autowired
	private ClassModuleMapper classModuleMapper;
	@Autowired
	private ClassTypeModuleRelationMapper classTypeModuleRelationMapper;
	@Autowired
	private CommodityMapper commodityMapper;
	@Autowired
	private  CommodityProductRealtionMapper  commodityProductRealtionMapper;
	@Autowired
	private ClassModuleNoMapper classModuleNoMapper;
	@Autowired
	private CourseVideoChapterMapper courseVideoChapterMapper;
	@Autowired
	private CourseVideoLectureMapper courseVideoLectureMapper;
	@Autowired
	private ClassModuleLessonMapper classModuleLessonMapper;
	@Autowired
	private CompanyPayConfigMapper companyPayConfigMapper;
	@Autowired
	private CompanyMemberServiceMapper companyMemberServiceMapper;
	@Autowired
	private CompanyServiceStaticMapper companyServiceStaticMapper;	
	@Autowired
	private SysCcAccountMapper ccAccountMapper;
	@Autowired
	private SysConfigTeacherMapper sysConfigTeacherMapper;
	
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
	@Override
	public void insert(Users users){
		usersMapper.insert(users);
	}
	
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
	@Override
	public void batchInsert(List<Users> userss){
		if(userss != null && !userss.isEmpty()){
			usersMapper.batchInsert(userss);
		}
	}
	
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
	@Override
	public void update(Users users){
		usersMapper.update(users);
	}
	
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
	@Override
	public void deleteUsersById(Integer id){
		usersMapper.deleteById(id);
	}
	
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
	@Override
	public void deleteUsersByIds(Integer[] ids){
		usersMapper.deleteByIds(ids);
	}
	
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
	@Override
	public Users findUsersById(Integer id){
		return usersMapper.findById(id);
	}
	
 /**
  * 
 * @Title: findUsersByRoleId 
 * @Description: 根据id查询
 * @param id
 * @return void    返回类型 
 * @throws 
 * @exception 
 * @date 2014-12-5
 * @user by wangzx
  */
 @Override
 public List<Users> findUsersByRoleId(RoleVo roleVo){
  return usersMapper.findUsersByRoleId(roleVo);
 }
	
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
	@Override
	public List<Users> findUsersByPage(Users search){
		Integer totalRecords = usersMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return usersMapper.page(search);
	}
	
	@Override
	public Users queryUserByName(String userName) {
		Users user=usersMapper.queryByName(userName);
		return user;
	}
	@Override
	public Boolean checkUserValid(Users user){
	    Integer isValid=usersMapper.checkUser(user);
	    if(isValid>0){
	        return true;
	    }else{
	        return false;
	    }
	}
 @Override
	public Boolean isExists(String key,String value){
     Integer isExists=0;
     Users user=new Users();
     if("username".equals(key)){
         user.setUsername(value);
     }
     if("mobile".equals(key)){
         user.setMobile(value);
     }
     if("identityId".equals(key)){
         user.setIdentityId(value);
     }
     if("email".equals(key)){
         user.setEmail(value);
     }
     isExists=usersMapper.checkValid(user);
     
	    if(isExists>0){
	        return true;
	    }else{
	        return false;
	    }
	}
	
	@Override
	public PageFinder<UserRoleVo> findUserList(UserRoleVo search ){
	    List<UserRoleVo> data=usersMapper.findUserList(search);
	    Integer pageCount=usersMapper.pageCount2(search);
	    PageFinder<UserRoleVo> pf=new PageFinder<UserRoleVo>(search.getPage(),search.getPageSize(),pageCount,data);
	    return pf;
	}
	@Override
	public String addOrganUser(UserRoleVo vo,Integer currtUser){
	    Users user=new Users();
	    user.setId(vo.getUserId());
	    user.setAge(vo.getAge());
	    user.setBirthday(vo.getBirthday());
	    user.setCompanyId(vo.getCompanyId());
	    user.setEducationCode(vo.getEducationCode());
	    user.setEmail(vo.getEmail());
	    user.setHomePhone(vo.getHomePhone());
	    user.setIdentityId(vo.getIdentityId());
	    user.setIdentityTypeCode(vo.getIdentityTypeCode());
	    user.setMobile(vo.getMobile());
	    user.setOfficePhone(vo.getOfficePhone());
	    user.setPassword(vo.getPassword());
	    user.setPhone(vo.getPhone());
	    user.setQqNo(vo.getQqNo());
	    user.setRealName(vo.getRealName());
	    user.setSchoolId(vo.getSchoolId());
	    user.setSex(vo.getSex());
	    user.setStatus(vo.getStatus());
	    user.setUsername(vo.getUsername());
	    user.setUserRole(vo.getUserRole());
	    user.setUserType(vo.getUserType());
	    user.setWeixinId(vo.getWeixinId());
	    //新增用户
	    usersMapper.insert(user);
	    //新增用户权限
	    String[] roles=vo.getRoleIds().split(",");
	    for(String role: roles){
	        AuthUserRole userrole=new AuthUserRole();
	        userrole.setRoleUid(role);
	        userrole.setUserId(vo.getUserId());
	        userrole.setUpdator(currtUser+"");
         userrole.setUpdateTime(new Date());
         userrole.setCreateTime(new Date());
         userrole.setCreator(currtUser+"");
         authUserRoleMapper.insert(userrole);
	    }
	    return "success";
	}
	@Override
 public String editOrganUser(UserRoleVo vo,Integer currtUser){
     Users user=new Users();
     user.setId(vo.getId());
     user.setAge(vo.getAge());
     user.setBirthday(vo.getBirthday());
     user.setCompanyId(vo.getCompanyId());
     user.setEducationCode(vo.getEducationCode());
     user.setEmail(vo.getEmail());
     user.setHomePhone(vo.getHomePhone());
     user.setIdentityId(vo.getIdentityId());
     user.setIdentityTypeCode(vo.getIdentityTypeCode());
     user.setMobile(vo.getMobile());
     user.setOfficePhone(vo.getOfficePhone());
     user.setPassword(vo.getPassword());
     user.setPhone(vo.getPhone());
     user.setQqNo(vo.getQqNo());
     user.setRealName(vo.getRealName());
     user.setSchoolId(vo.getSchoolId());
     user.setSex(vo.getSex());
     user.setStatus(vo.getStatus());
     user.setUsername(vo.getUsername());
     user.setUserRole(vo.getUserRole());
     user.setUserType(vo.getUserType());
     user.setWeixinId(vo.getWeixinId());
     usersMapper.update(user);
     if(vo.getRoleIds()!=null){
    	//删除掉用户以前的权限配置
         authUserRoleMapper.deleteByUser(vo.getId());
         //重新配置用户权限
         String[] roles=vo.getRoleIds().split(",");
         for(String role: roles){
             AuthUserRole userrole=new AuthUserRole();
             userrole.setRoleUid(role);
             userrole.setUserId(vo.getId());
             userrole.setUpdator(currtUser+"");
             userrole.setUpdateTime(new Date());
             userrole.setCreateTime(new Date());
             userrole.setCreator(currtUser+"");
             authUserRoleMapper.insert(userrole);
         }
     }
     
     return "success";
 }

	@Override
	public Users findUserByCompanyIdAndUserType(Integer companyId) {
		// TODO Auto-generated method stub
		return usersMapper.findUserByCompanyIdAndUserType(companyId);
	}

	@Override
	public void updateUserByCompanyIdAndUserType(Users users) {
		// TODO Auto-generated method stub
		usersMapper.updateUserByCompanyIdAndUserType(users);
	}

	@Override
	public List<Users> findUserByCondition(Users users) {
		
		return usersMapper.findUserByCondition(users);
	}

	@Override
	public int selectCount(Integer companyId) {
		return usersMapper.selectCount(companyId);
	}

	@Override
	public void updateStatus(Users users) {
		// TODO Auto-generated method stub
		usersMapper.updateStatus(users);
	}
	
	/**
	 * @caption 初始化用户数据
	 * @author chopin
	 * @param user
	 * @param company
	 * @param school
	 * @return
	 */
	@Override
	public void userDefaultRegister(Users user, Company company, SysConfigSchool school) {
		//建立公司
		if(company!=null){
			companyMapper.insert(company);
		}
		//建立默认分校
		if(school!=null){
			school.setCompanyId(company.getId());
			school.setCreator(user.getId());
			sysConfigSchoolMapper.insert(school);
		}
		//建立用户
		if(user!=null){
			user.setCompanyId(company.getId());
			user.setSchoolId(school.getId());
			usersMapper.insert(user);
		}
		//赋予机构管理员权限
		AuthUserRole t=new AuthUserRole();
		t.setUserId(user.getId());
		t.setRoleUid(1+"");
		t.setCreateTime(new Date());
		t.setCreator(user.getId()+"");
		t.setUpdateTime(new Date());
		t.setUpdator(user.getId()+"");
		authUserRoleMapper.insert(t);
	}
	
	/**
	 * @caption 初始化用户数据
	 * @author chopin
	 * @param user
	 * @param company
	 * @param school
	 * @return
	 */
	@Override
	public InitDataVo initUserData(Integer userId, Integer companyId, Integer schoolId) {
		Users user=usersMapper.findById(userId);
		//添加校区
		SysConfigCampus campus=new SysConfigCampus();
		campus.setCampusNo("A");
		campus.setCampusName("第一校区");
		campus.setStatus(1);
		campus.setSchoolId(schoolId);
		campus.setCompanyId(companyId);
		campus.setDelFlag(0);
		campus.setCreateTime(new Date());
		campus.setCreator(userId);
		sysConfigCampusMapper.insert(campus);
		//添加学科
		SysConfigItem item=new SysConfigItem();
		item.setItemName("默认学科");
		item.setCompanyId(companyId);
		item.setSchoolId(schoolId);
		item.setItemType("1");
		item.setDelFlag(0);
		item.setItemPic("icons/kuaiji.png");
		item.setItemBackPic("icons/jisuanji-0.png");
		item.setStatus("1");
		item.setCreateTime(new Date());
		item.setCreator(userId);
		item.setUpdateTime(new Date());
		item.setUpdator(userId);
		sysConfigItemMapper.insert(item);
		//关联分校
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("schoolId", schoolId);
		params.put("itemId", item.getId());
		params.put("delFlag", 0);
		params.put("trueDelFlag", 0);
		params.put("creator",userId);
		params.put("createTime", new Date());
		params.put("updator",userId);
		params.put("updateTime", new Date());
		sysConfigItemMapper.insertRelation(params);
		//新增学科小类
		SysConfigItem item1=new SysConfigItem();
		item1.setItemName("默认学科小类");
		item1.setCompanyId(companyId);
		item1.setSchoolId(schoolId);
		item1.setParentId(item.getId());
		item1.setItemType("2");
		item1.setDelFlag(0);
		item1.setStatus("1");
		item1.setCreateTime(new Date());
		item1.setCreator(userId);
		item1.setUpdateTime(new Date());
		item1.setUpdator(userId);
		sysConfigItemMapper.insert(item1);
		//关联分校2
		Map<String,Object> params1 = new HashMap<String, Object>();
		params1.put("schoolId", schoolId);
		params1.put("itemId", item1.getId());
		params1.put("delFlag", 0);
		params1.put("trueDelFlag", 0);
		params1.put("creator",userId);
		params1.put("createTime", new Date());
		params1.put("updator",userId);
		params1.put("updateTime", new Date());
		sysConfigItemMapper.insertRelation(params1);

		InitDataVo data=new InitDataVo();
		try {
			data.setCompanyId(companyId);
			data.setUserId(userId);
			data.setSchoolId(schoolId);
			data.setItemOneId(item.getId());
			data.setItemSecondId(item1.getId());
			data.setCampusId(campus.getId());
		} catch (Exception e) {
			log.error("封装数据失败",e);
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public Users findByUserTypeAndCompanyId(Users users) {
		// TODO Auto-generated method stub
		return usersMapper.findByUserTypeAndCompanyId(users);
	}

	@Override
	public Integer isExixits(Users users) {
		// TODO Auto-generated method stub
		return usersMapper.isExixits(users);
	}

	@Override
	public PageFinder<UserLoginSession> findUserTotalList(UserLoginSession u) {
		List<UserLoginSession> data=usersMapper.queryUserTotalList(u);
		int count=usersMapper.queryUserTotalCount(u);
		PageFinder<UserLoginSession> pageFinder=new PageFinder<UserLoginSession>(u.getPage(), u.getPageSize(), count, data);
		return pageFinder;
	}

	@Override
	public List<UserLoginSession> queryUserChar(UserLoginSession u) {
		// TODO Auto-generated method stub
		return usersMapper.queryUserChar(u);
	}

	@Override
	public Integer findCompanyByMobile(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return usersMapper.findCompanyByMobile(param);
	}

	@Override
	public String findRealNameByid(Integer id) {
		// TODO Auto-generated method stub
		return usersMapper.findRealNameByid(id);
	}

	@Override
	public Users findUsersByConfition(Users user) {
		// TODO Auto-generated method stub
		return usersMapper.findUsersByConfition(user);
	}

	@Override
	public List<Users> queryuserIsExist(Users user) {
		// TODO Auto-generated method stub
		return usersMapper.queryuserIsExist(user);
	}

	@Override
	public UsersAreaRelation findUsersAreaRelation(Integer id) {
		return usersMapper.findUsersAreaRelation(id);
	}
}
