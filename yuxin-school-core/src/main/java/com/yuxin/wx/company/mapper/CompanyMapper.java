
package com.yuxin.wx.company.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.auth.AuthRolePrivilege;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.classes.EduMasterClass;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyAppAuth;
import com.yuxin.wx.model.company.CompanyFootInfo;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyHeadFootConfig;
import com.yuxin.wx.model.company.CompanyLiveConcurrent;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.company.CompanyLoginConfig;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyNewStep;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.company.CompanyPics;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.NewCompanyVo;
import com.yuxin.wx.model.system.SysConfigCampus;
import com.yuxin.wx.model.system.SysConfigIndexPageTemplate;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigPageRedirect;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.system.SysLogManagerOption;
import com.yuxin.wx.model.system.SysPageHeadFoot;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.company.CompanyOrgMessageReadVo;
import com.yuxin.wx.vo.company.CompanyOrgMessageVo;
import com.yuxin.wx.vo.company.CompanyPicsVo;
import com.yuxin.wx.vo.company.CompanyTotalVo;
import com.yuxin.wx.vo.company.CompanyVo;
import com.yuxin.wx.vo.company.companySpecialDomain;
import com.yuxin.wx.vo.query.RegisterInfoVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyMapper extends BaseMapper<Company> {
	Integer findCompanyLevel(Integer companyId);
	
	List<CompanyPicsVo> queryCompanyPic(CompanyPicsVo companyPicsVo);
	
	int queryCompanyPicCount(CompanyPicsVo companyPicsVo);
	
	List<CompanyVo> findCompanyVoByPage(Company search);
	Integer findTotalCount(Company search);
	
	List<CompanyPicsVo> queryCompanyPrivatePic(CompanyPicsVo companyPicsVo);
	int queryCompanyPrivatePicCount(CompanyPicsVo companyPicsVo);
	
	List<CompanyPicsVo> queryOneCondtion();
	
	Company queryCompanyByCopanyCondition(Company company);
	
	void insertCompany(Company company);

	String findStatus(Integer companyId);

	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 定时任务   获得所有公司的id
	 * @author 周文斌
	 * @date 2015-5-21 下午12:59:19
	 * @version 1.0
	 * @return
	 */
	List<Integer> findCompanyId(@Param("types") String types);

	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 定时任务 查询所有收费 公司 id 和等级
	 * @author 周文斌
	 * @date 2015-5-21 下午2:15:18
	 * @version 1.0
	 * @return
	 */
	List<Company> findCompanyIdAndLevel();
	
	void memberLevelUp(Company CEntity,CompanyMemberService CMSEntity);
	
	List<companySpecialDomain> querySpecialName(String domainName);
	
	List<CompanyOrgMessageVo> queryMessageList(CompanyOrgMessageVo search);
	
	int queryMessageCount(CompanyOrgMessageVo search);
	
	CompanyOrgMessageVo queryMessageById(Integer id);
	
	void insertMsg(CompanyOrgMessageVo c);	
	void updateMsg(CompanyOrgMessageVo c);
	void deleteMsg(Integer id);
	List<Company> queryAllCompany();
	List<CompanyOrgMessageReadVo> queryMessageCenterList(CompanyOrgMessageReadVo c);
	int queryMessageCenterCount(CompanyOrgMessageReadVo c);
	void insertCompanyReadDetail(CompanyOrgMessageReadVo c);
	void updateMsgReadFlag(CompanyOrgMessageReadVo c);
	CompanyOrgMessageReadVo querymessageCenterById(Map<String, Integer> map);
	Integer queryCompanyNotReadNum(CompanyOrgMessageReadVo c);
	List<CompanyTotalVo> querySourceList(CompanyTotalVo c);
	List<CompanyTotalVo>  queryRegistNumList(CompanyTotalVo c);
	int queryRegistNumListCount(CompanyTotalVo c);
	List<CompanyTotalVo> queryRegistNumChar(CompanyTotalVo c);
	
	List<Company> queryCompanyBuyFlag(Company search);
	
	List<Company> queryRegisterCompany(Map map);
	
	List<Company> checkCompanyDomain(String search);
	
	List<Company> checkCompanyName(String search);

	List<CompanyPicsVo> queryPrivatePic(CompanyPicsVo companyPicsVo);

	int queryPrivatePicCount(CompanyPicsVo companyPicsVo);

	List<CompanyPicsVo> queryPublicPic(CompanyPicsVo companyPicsVo);

	int queryPublicPicCount(CompanyPicsVo companyPicsVo);

	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询域名
	 * @author 周文斌
	 * @date 2016-7-26 下午6:32:27
	 * @version 1.0
	 * @param id
	 * @return
	 */
	String findDomainById(Integer id);
	
	List<RegisterInfoVo> queryRegisterInfoList(RegisterInfoVo riv);
	Integer queryRegisterInfoCount(RegisterInfoVo riv);
	
	List<RegisterInfoVo> queryRegisterInfoExport(RegisterInfoVo riv);

	List<CompanyOrgMessageVo> queryMessageServiceList(CompanyOrgMessageVo search);
	
	Integer queryServiceOpenFlag(Map<String, Object> map);

	int getBerkeleySchoolListCount(CompanyVo companyVo);

	CompanyVo getIsAreaByCompanyId(Integer companyId);
	
	/**
	 * 分校首页学校列表
	 * @param search 查询条件
	 * @return 分校结果集
	 */
	public List<com.yuxin.wx.model.company.CompanyVo> queryCompanyVoListByCondition(com.yuxin.wx.model.company.CompanyVo search);
	
	
	/**
	 * 分校首页学校数据
	 * @param search 查询条件
	 * @return 分校结果数据
	 */
	public Integer queryCompanyVoListByConditionCount(com.yuxin.wx.model.company.CompanyVo search);
	/**
	 * 通过查询条件查询对应的机构
	 * @param params
	 * @return
	 */
	public com.yuxin.wx.model.company.CompanyVo queryCompanyVoByCondition(Map<String,Object> params);
	/**
	 * 
	 * @author jishangyang 2017年12月7日 上午9:48:46
	 * @Method: addBerkeley 
	 * @Description: 添加分校
	 * @param search 
	 * @throws
	 */
	public Integer searchCompany();
	public Integer addBerkeley(com.yuxin.wx.model.company.CompanyVo search);
	public void addCompanyMemberService(CompanyMemberService cms);
	public void companyLiveConfig(CompanyLiveConfig clc);
	public void addSysConfigService(String companyId);
	public void addSysConfigItem(SysConfigItem sci);
	public void addTwoSysConfigItem(SysConfigItem sci);
	public void addSysConfigAndSchool(SysConfigItem sci);
	public void addAuthRole(AuthRole rol);
	public void addAuth2Role(AuthRole rol);
	public void updateAuthRole();
	public void insertHeadmasterRole(AuthRole rol);
	public void insertTeacherRole(AuthRole rol);
	public void addSchool(SysConfigSchool school);
	public void addAuthPrivilegeCategory(AuthRolePrivilege arp);
	public void addUser(Users user);
	public void addUserCompany(Users user);
	public void addAuthUserRole( AuthUserRole aur);
	public void addCompanyAppAuth(CompanyAppAuth caa);
	public void addCompanyFootInfo(CompanyFootInfo cfi);
	public void addCompanyFunctionSet(CompanyFunctionSet cfs);
	public void addCompanyHeadFootConfig(CompanyHeadFootConfig chfc);
	public void addCompanyLiveConcurrent(CompanyLiveConcurrent clct);
	public void addCompanyLoginConfig(CompanyLoginConfig clcf);
	public void addCompanyNewStep(CompanyNewStep cns);
	public void addCompanyPayConfig(CompanyPayConfig cpc);
	public void addCompanyPics(CompanyPics cp);
	public void addCompanyRegisterConfig(CompanyRegisterConfig crc);
	public void addSysConfigCampus(SysConfigCampus scfc);
	public void addSysConfigIndexPageTemplate(SysConfigIndexPageTemplate scipt);
	public void addCompanyRegisterConfig(SysConfigIndexPageTemplate scipt);
	public void addSysLogManagerOption(SysLogManagerOption smo);
	public void addSysPageHeadFoot(SysPageHeadFoot sphf);
	public void addCompanyServiceStatic(CompanyServiceStatic csc);
	public void addSysConfigPageRedirect(SysConfigPageRedirect scpr);
	
	/**
	 * 
	 * @author jishangyang 2017年12月7日 下午6:49:33
	 * @Method: findCompanyVoById 
	 * @Description: 根据ID查询分校
	 * @param id
	 * @return 
	 * @throws
	 */
	public NewCompanyVo findCompanyVoById(Integer id);
	
	public CompanyLiveConfig findCompanyLiveConfigById(Integer id);
	/**
	 * 
	 * @author jishangyang 2017年12月8日 上午12:14:39
	 * @Method: eidtBerkeley 
	 * @Description: 修改分校
	 * @param search
	 * @return 
	 * @throws
	 */
	public void eidtBerkeley(com.yuxin.wx.model.company.CompanyVo search);
	public void editCompanyMemberService(CompanyMemberService cms);
	public void editcompanyLiveConfig(CompanyLiveConfig clc);
	/**
	 * 
	 * @author jishangyang 2017年12月13日 下午5:05:26
	 * @Method: findClassByEduAreaSchool 
	 * @Description: 通过学校字典码 查询班级信息
	 * @param eduAreaSchool
	 * @return 
	 * @throws
	 */
	public List<EduMasterClass> findClassByEduAreaSchool(String  eduAreaSchool);
	/**
	 * 
	 * @author jishangyang 2017年12月14日 下午12:05:50
	 * @Method: findIsUsedClassByEduAreaSchool 
	 * @Description: 查询可用班级
	 * @param eduAreaSchool
	 * @return 
	 * @throws
	 */
	public List<EduMasterClass> findIsUsedClassByEduAreaSchool(String  eduAreaSchool);
	/**
	 * 
	 * @author jishangyang 2017年12月13日 下午5:05:51
	 * @Method: addClass 
	 * @Description:添加班级信息
	 * @param saveList
	 * @return 
	 * @throws
	 */
	public void addClass(List<EduMasterClass> saveList);
	/**
	 * 
	 * @author jishangyang 2017年12月14日 下午2:36:47
	 * @Method: editClass 
	 * @Description: 修改班级信息
	 * @param updateList 
	 * @throws
	 */
	public void editClass(List<EduMasterClass> updateList);
	/**
	 * 
	 * @author jishangyang 2017年12月13日 下午5:20:46
	 * @Method: findEduAreaByeduAreaSchool 
	 * @Description: 通过学校字典码获取 区信息
	 * @param saveList
	 * @return 
	 * @throws
	 */
	public String findEduAreaByeduAreaSchool(String  eduAreaSchool);

	Integer findComanyIdByRootPath(String rootPath);
	
	Integer findSchoolIdByCompanyId(Integer companyId);
}
