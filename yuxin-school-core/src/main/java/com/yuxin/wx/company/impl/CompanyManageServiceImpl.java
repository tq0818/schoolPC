package com.yuxin.wx.company.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuxin.wx.system.mapper.SysPageHeadFootMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.company.ICompanyManageService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.company.mapper.CompanyMapper;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.auth.AuthRolePrivilege;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.classes.EduMasterClass;
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
import com.yuxin.wx.model.company.CompanyVo;
import com.yuxin.wx.model.system.SysConfigCampus;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigIndexPageTemplate;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigPageRedirect;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.system.SysPageHeadFoot;
import com.yuxin.wx.model.tiku.TikuSet;
import com.yuxin.wx.model.user.Users;
import sun.org.mozilla.javascript.internal.NativeArray;

@Service
@Transactional
public class CompanyManageServiceImpl extends BaseServiceImpl implements
		ICompanyManageService {
	@Autowired
	private CompanyMapper companyMapper;

	@Override
	public PageFinder2<CompanyVo> queryCompanyVoListByCondition(CompanyVo search) {
		
		if(search==null) return null;
		//由于笼统查询分校，太慢，故先查询所有的分校中的区县代码和isAear字段
		List<CompanyVo> allCompanyVoList=companyMapper.queryReCompanyVoByCondition(search);
		List<CompanyVo> schoolCompanyList=new ArrayList<CompanyVo>();
		CompanyVo companyVoEmpty=new CompanyVo();
		companyVoEmpty.setEduAreaSchool("-1");
		schoolCompanyList.add(companyVoEmpty);
		List<CompanyVo> areaCompanyList=new ArrayList<CompanyVo>();
		areaCompanyList.add(companyVoEmpty);
		Integer counts=0;
		List<CompanyVo> companyVoList=new ArrayList<CompanyVo>();
		if(allCompanyVoList!=null){
			for(CompanyVo companyVo:allCompanyVoList){
				if("1".equals(companyVo.getIsArea())){
					areaCompanyList.add(companyVo);
				}else if("2".equals(companyVo.getIsArea())){
					schoolCompanyList.add(companyVo);
				}
			}
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("search",search);
			params.put("schoolCompanyList",schoolCompanyList);
			params.put("areaCompanyList",areaCompanyList);
			//查询结果集
			companyVoList=companyMapper.queryReCompanyVoListByCondition(params);
			//查询结果集总数
			counts=companyMapper.queryCompanyVoListByConditionCount(search);
		}
		return new PageFinder2<CompanyVo>(
				search.getPage(), search.getPageSize(),counts,companyVoList);
	}
	//查询学校所在区域
	public List<SysConfigDict> queryCompanyVoListByQuyu(){
		List<SysConfigDict> listScd =new ArrayList<>();
		List<CompanyVo> listCv =companyMapper.queryCompanyVoListByQuyu();
		for (CompanyVo cv:listCv) {
			SysConfigDict scd =new SysConfigDict();
			scd.setItemValue(cv.getEduArea());
			scd.setItemCode(cv.getEduAreaSchool());
			listScd.add(scd);
		}
		return listScd ;
	}
	@Override
	public Integer checkDomain(CompanyVo search) {
		
		return companyMapper.checkDomain(search);
	}
	@Override
	public CompanyVo queryCompanyVoByCondition(String brachCode) {
		if(brachCode==null||brachCode=="") return null;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("brachCode",brachCode);
		return companyMapper.queryCompanyVoByCondition(map);
	}
	@Override
    public void addBerkeley(CompanyVo search, CompanyMemberService cms, CompanyLiveConfig clc,CompanyPayConfig cpc,Integer userId) {
		 int zhuCompanyId= companyMapper.searchCompany();
		 String schoolProperty = search.getSchoolProperty();
		 //判断
		 if(schoolProperty.equals("小学")){
			 search.setSchoolProperty("EDU_STEP_NEW_01");
		 }
		 if(schoolProperty.equals("完全中学")){
			 search.setSchoolProperty("EDU_STEP_NEW_02");
		 }
		 if(schoolProperty.equals("十二年制")){
			 search.setSchoolProperty("EDU_STEP_NEW_03");
		 }
		 if(schoolProperty.equals("九年制")){
			 search.setSchoolProperty("EDU_STEP_NEW_04");
		 }
		 if(schoolProperty.equals("高级中学")){
			 search.setSchoolProperty("EDU_STEP_NEW_05");
		 }
		 if(schoolProperty.equals("初级中学")){
			 search.setSchoolProperty("EDU_STEP_NEW_06");
		 }
		 companyMapper.addBerkeley(search);
		 int ids=search.getId();
		//分配流量。分配存储空间
		 cms.setCompanyId(String.valueOf(ids));
		 cms.setZhuCompanyId(zhuCompanyId);
		 companyMapper.addCompanyMemberService(cms);
		//添加展示互动表
		 clc.setCompanyId(ids);
		 clc.setLiveType(1);
		 companyMapper.companyLiveConfig(clc);
		
		 //添加服务信息
		 companyMapper.addSysConfigService(String.valueOf(ids));
		
		 //添加学校
		 SysConfigSchool school = new SysConfigSchool();
		 school.setCompanyId(ids);
		 school.setSchoolName(search.getCompanyName());
		 school.setSchoolDesc(search.getSchoolSummary());
		 school.setCreator(userId);
		 school.setZhuCompanyId(String.valueOf(zhuCompanyId));
		 companyMapper.addSchool(school);
		//添加科目
		 SysConfigItem sci=new SysConfigItem();
		 sci.setCompanyId(ids);
		 sci.setCreateTime(new Date());
		 sci.setCreator(userId);
		 sci.setZhuCompanyId(String.valueOf(zhuCompanyId));
		 companyMapper.addSysConfigItem(sci);
		 companyMapper.addTwoSysConfigItem(sci);
		 sci.setSchoolId(school.getId());
		 companyMapper.addSysConfigAndSchool(sci);
       
		 //添加角色
		 AuthRole rol= new AuthRole();
		 rol.setCreator(String.valueOf(userId));
		 rol.setCompanyId(ids);
		 rol.setZhuCompanyId(String.valueOf(zhuCompanyId));
		 if("1".equals(search.getIsArea())){
			 companyMapper.addAuthRole(rol);
		 }
		 
		 if("2".equals(search.getIsArea())){
			 companyMapper.addAuth2Role(rol);
			 companyMapper.insertHeadmasterRole(rol);
			 companyMapper.insertTeacherRole(rol);
		 }
		 companyMapper.updateAuthRole();
		
		 //auth_role_privilege 添加角色对应菜单
		 AuthRolePrivilege arp=new AuthRolePrivilege();
		 arp.setCompanyId(ids);
		 arp.setZhuCompanyId(zhuCompanyId);
		 companyMapper.addAuthPrivilegeCategory(arp); 
		 //添加用户
		 Users user =new Users();
		 user.setUsername(search.getEduAreaSchool()+"111111");
		 user.setPassword(new Md5Hash("111111", ByteSource.Util.bytes(search.getEduAreaSchool()+"111111" + "salt")).toHex());
		 user.setRealName("机构管理员");
		 user.setUserType("USER_TYPE_ORG");
		 user.setStatus(1);
		 user.setEduAreaSchool(search.getEduAreaSchool());
		 user.setIsArea(search.getIsArea());
		 companyMapper.addUser(user); 
		 //auth_user_role 添加用户角色关系
		 AuthUserRole aur=new AuthUserRole();
		 aur.setCreator(String.valueOf(userId));
		 aur.setUserId(user.getId());
		 aur.setCompanyId(search.getId());
		 companyMapper.addAuthUserRole(aur);
		//
		 user.setCompanyId(ids);
		 companyMapper.addUserCompany(user);
		 //company_app_auth
		 CompanyAppAuth caa = new CompanyAppAuth();
		 caa.setCompanyId(ids);
		 caa.setZhuCompanyId(String.valueOf(zhuCompanyId));
		 companyMapper.addCompanyAppAuth(caa);
		 //company_foot_info
		 CompanyFootInfo cfi = new CompanyFootInfo();
		 cfi.setCompanyId(ids);
		 cfi.setZhuCompanyId(String.valueOf(zhuCompanyId));
		 companyMapper.addCompanyFootInfo(cfi);
		 //company_function_set
		 CompanyFunctionSet cfs =new CompanyFunctionSet();
		 cfs.setCompanyId(ids);
		 cfs.setZhuCompanyId(String.valueOf(zhuCompanyId));
		 companyMapper.addCompanyFunctionSet(cfs);
		 //company_head_foot_config 有点问题
		 CompanyHeadFootConfig chfc =new CompanyHeadFootConfig();
		 chfc.setCompanyId(ids);
		 chfc.setZhuCompanyId(zhuCompanyId);
		 companyMapper.addCompanyHeadFootConfig(chfc);
		 //company_live_concurrent
		 CompanyLiveConcurrent clct=new CompanyLiveConcurrent();
		 clct.setCompanyId(ids);
		 clct.setZhuCompanyId(zhuCompanyId);
		 companyMapper.addCompanyLiveConcurrent(clct);
		 //company_login_config
		 CompanyLoginConfig clcf=new CompanyLoginConfig();
		 clcf.setCompanyId(ids);
		 clcf.setZhuCompanyId(zhuCompanyId);
		 companyMapper.addCompanyLoginConfig(clcf);
		 //company_new_step
		 CompanyNewStep cns =new CompanyNewStep();
		 cns.setCompanyId(ids);
		 cns.setZhuCompanyId(zhuCompanyId);
		 companyMapper.addCompanyNewStep(cns);
		 //company_pay_config cc账号
		 cpc.setCompanyId(ids);
		 cpc.setZhuCompanyId(zhuCompanyId);
		 companyMapper.addCompanyPayConfig(cpc);
		 //company_pics
		 CompanyPics cp =new CompanyPics();
		 cp.setCompanyId(ids);
		 cp.setZhuCompanyId(zhuCompanyId);
		 companyMapper.addCompanyPics(cp);
		 //company_register_config
		 CompanyRegisterConfig crc=new CompanyRegisterConfig();
		 crc.setCompanyId(ids);
		 crc.setZhuCompanyId(zhuCompanyId);
		 companyMapper.addCompanyRegisterConfig(crc);
		 //sys_config_campus
		 SysConfigCampus scfc=new SysConfigCampus();
		 scfc.setCompanyId(ids);
		 scfc.setSchoolId(school.getId());
		 scfc.setZhuCompanyId(zhuCompanyId);
		 companyMapper.addSysConfigCampus(scfc);
		 //sys_config_index_page_template
		 SysConfigIndexPageTemplate scipt=new SysConfigIndexPageTemplate();
		 scipt.setCompanyId(ids);
		 scipt.setSchoolId(school.getId());
		 scipt.setZhuCompanyId(zhuCompanyId);
		 companyMapper.addSysConfigIndexPageTemplate(scipt);
//		 //sys_log_manager_option
//		 SysLogManagerOption smo=new SysLogManagerOption();
//		 smo.setCompanyId(ids);
//		 smo.setUserId(user.getId());
//		 smo.setZhuCompanyId(zhuCompanyId);
//		 companyMapper.addSysLogManagerOption(smo);
		 //sys_page_head_footSysPageHeadFoot SysPageHeadFoot
		 SysPageHeadFoot sphf=new SysPageHeadFoot();
		 sphf.setCompanyId(ids);
		 sphf.setSchoolId(school.getId());
		 sphf.setZhuCompanyId(zhuCompanyId);
		 List<SysPageHeadFoot> list =companyMapper.selectAllUrl();
		for (SysPageHeadFoot sphfnew:list) {
			String[] url1=sphfnew.getUrl().split("\\/");
			String url2 ="";
			if (url1.length>3){
				for (int i =0 ;i<url1.length;i++) {
					if(i==2){
						url2=url2+"/"+search.getDomain();
					}else if(i==0){
						url2 =(url1[i])+"/";
					}else {
						url2 =url2+"/"+(url1[i]);
					}
				}
				sphfnew.setUrl(url2);
			}
			companyMapper.addSysPageHeadFootAll(sphfnew);
		}

//		 companyMapper.addSysPageHeadFoot(sphf);
		 //company_service_static
		 CompanyServiceStatic csc=new CompanyServiceStatic();
		 csc.setCompanyId(ids);
		 csc.setZhuCompanyId(zhuCompanyId);
		 companyMapper.addCompanyServiceStatic(csc);
		 //sys_config_page_redirect
		 SysConfigPageRedirect scpr =new SysConfigPageRedirect();
		 scpr.setCompanyId(ids);
		 scpr.setZhuCompanyId(zhuCompanyId);
		 scpr.setSchoolId(school.getId());
		 companyMapper.addSysConfigPageRedirect(scpr);
		 //tiku_set
		 TikuSet tikuSet=new TikuSet();
		 tikuSet.setCompanyId(ids);
		 tikuSet.setZhuCompanyId(zhuCompanyId);
		 companyMapper.addTiKuSet(tikuSet);
    }
	@Override
    public void eidtBerkeley(CompanyVo search, CompanyMemberService cms, CompanyLiveConfig clc, CompanyPayConfig cpc) {
		companyMapper.eidtBerkeley(search);
//		if(cms.getVideoFlow() != null && cms.getVideoStorage()!=null && cms.getVideoFlow().equals("") && cms.getVideoStorage().equals("")){
			if(cms.getVideoFlow()!=0 || cms.getVideoStorage()!=0){
				companyMapper.editCompanyMemberService(cms);
			}
//		}
		companyMapper.editcompanyLiveConfig(clc);
		companyMapper.editCompanyPayConfig(cpc);
    }

	@Override
	public List<EduMasterClass> findClassByEduAreaSchool(String  eduAreaSchool){
		
		return companyMapper.findClassByEduAreaSchool(eduAreaSchool);
	}
	@Override
	public List<EduMasterClass> findIsUsedClassByEduAreaSchool(String  eduAreaSchool){
		
		return companyMapper.findIsUsedClassByEduAreaSchool(eduAreaSchool);
	}
	@Override
	public void addClass(List<EduMasterClass> saveList) {
		
		companyMapper.addClass(saveList);
	}
	@Override
	public void editClass(List<EduMasterClass> saveList,List<EduMasterClass> updateList) {
		
		if(null!=saveList && saveList.size()>0){
			companyMapper.addClass(saveList);
		}
		if(null!=updateList && updateList.size()>0){
			companyMapper.editClass(updateList);
		}
	}
	@Override
	public String findEduAreaByeduAreaSchool(String  eduAreaSchool) {
		
		return companyMapper.findEduAreaByeduAreaSchool(eduAreaSchool);
	}
	
}
