package com.yuxin.wx.controller.classes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassTypeMemberDiscountService;
import com.yuxin.wx.api.classes.IClassTypeModuleRelationService;
import com.yuxin.wx.api.classes.IClassTypeRemoteRelationService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyLiveConfigService;
import com.yuxin.wx.api.company.ICompanyMemberConfigService;
import com.yuxin.wx.api.company.ICompanyMemberLevelConfigService;
import com.yuxin.wx.api.company.ICompanyPicsService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.course.ICourseProtocolConfigService;
import com.yuxin.wx.api.course.ICourseRemoteService;
import com.yuxin.wx.api.course.ICourseVideoChapterService;
import com.yuxin.wx.api.system.ISysConfigCampusService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigItemTagService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.common.CCLiveInterface;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.course.mapper.CourseVideoLectureMapper;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeMemberDiscount;
import com.yuxin.wx.model.classes.ClassTypeModuleRelation;
import com.yuxin.wx.model.classes.ClassTypeRemoteRelation;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.company.CompanyMemberLevelConfig;
import com.yuxin.wx.model.company.CompanyPics;
import com.yuxin.wx.model.course.CourseProtocolConfig;
import com.yuxin.wx.model.course.CourseRemote;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.model.course.CourseVideoLecture;
import com.yuxin.wx.model.system.SysConfigCampus;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemTag;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.APIServiceFunction;
import com.yuxin.wx.util.HttpPostRequest;
import com.yuxin.wx.util.ImageUtils;
import com.yuxin.wx.util.TalkfunUtils;
import com.yuxin.wx.utils.EntityUtil;
import com.yuxin.wx.utils.FileUtil;
//import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassModuleNoListVo;
import com.yuxin.wx.vo.classes.ClassModuleVo;
import com.yuxin.wx.vo.classes.ClassPackageConditionVo;
import com.yuxin.wx.vo.classes.ClassTypeMemberDiscountVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.classes.ClassmoduleNoOnsaleVo;
import com.yuxin.wx.vo.company.CompanyPicsVo;
import com.yuxin.wx.vo.company.MemberLevelAndClassTypeVo;
import com.yuxin.wx.vo.company.MemberLevelVo;
import com.yuxin.wx.vo.course.ChapterAndLectureListVo;

import sun.misc.BASE64Decoder;

/**
 * Controller of ClassType
 * 
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/classType")
public class ClassTypeController {

	private Log log=LogFactory.getLog("log");

	@Autowired
	private ICompanyLiveConfigService companyLiveConfigServiceImpl;
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	@Autowired
	private IClassTypeModuleRelationService classTypeModuleRelationServiceImpl;
	@Autowired
	private IClassTypeRemoteRelationService classTypeRemoteRelationServiceImpl;
	@Autowired
	private IClassModuleService classModuleServiceImpl;
	@Autowired
	private ICourseRemoteService courseRemoteServiceImpl;
	@Autowired
	private ISysConfigSchoolService sysConfigSchoolServiceImpl;
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private ISysConfigTeacherService sysConfigTeacherServiceImpl;
	@Autowired
	private ICommodityService commodityServiceImpl;
	@Autowired
	private ICommodityProductRealtionService commodityProductRealtionServiceImpl;
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	@Autowired
	private IClassModuleNoService classModuleNoServiceImpl;
	@Autowired
	private ICompanyPicsService companyPicsServiceImpl;
	@Autowired
	private ISysConfigCampusService sysConfigCampusServiceImpl;
	@Autowired
	private ICourseVideoChapterService courseVideoChapterServiceImpl;
	@Autowired
	private CourseVideoLectureMapper courseVideoLectureMapper;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	@Autowired
	private ISysConfigItemTagService sysConfigItemTagServiceImpl;
	@Autowired
	private IClassModuleLessonService classModuleLessonServiceImpl;
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;
	@Autowired
	private ICompanyMemberLevelConfigService companyMemberLevelConfigServiceImpl;
	@Autowired
	private IClassTypeMemberDiscountService classTypeMemberDiscountServiceImpl;
	@Autowired
	private ICompanyMemberConfigService companyMemberConfigServiceImpl;
	@Autowired
	private IClassTypeService classTypeImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private ICourseProtocolConfigService courseProtocolConfigServiceImpl;
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 跳转到班型页面
	 * @author zhang.zx
	 * @date 2015年5月4日 下午5:41:19
	 * @modifier
	 * @modify-date 2015年5月4日 下午5:41:19
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showClassTypePage",method=RequestMethod.GET)
	public String showClassTypePage(Model model){
		List<SysConfigItem> firstItems = sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
		model.addAttribute("firstItems", firstItems);
		return "classType/classIndex";
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 显示班型页面
	 * @author zhang.zx
	 * @date 2015年5月5日 下午3:54:22
	 * @modifier
	 * @modify-date 2015年5月5日 下午3:54:22
	 * @version 1.0
	 * @param search
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showAllclassType",method=RequestMethod.POST)
	public String showAllProjectProduct(ClassType search,Model model){
		Users currentUser = WebUtils.getCurrentUser();
		model.addAttribute("itemOneId", search.getItemOneId());
		if(currentUser!=null)
		search.setUserId(currentUser.getId());
		search.setPageSize(7);
		search.setCreateSchoolId(WebUtils.getCurrentSchoolId());
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		PageFinder<ClassTypeVo> pageFinder = null;
		String commodityPicUrl="http://"+propertiesUtil.getProjectImageUrl()+"/";
		model.addAttribute("commodityPicUrl", commodityPicUrl);
		boolean flag = companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
		model.addAttribute("isFuSheng", flag);
		Subject subject = SecurityUtils.getSubject();
		if((subject.hasRole("排课老师") && subject.hasRole("直播老师")) && !(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营"))){
			List<SysConfigTeacher> teacherList = sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
			if(teacherList!=null && teacherList.size()>0)
			{
				SysConfigTeacher configTeacher = teacherList.get(0);
				search.setTeacherId(configTeacher.getId()+"");
			}
			pageFinder=classTypeServiceImpl.findClassTypesByPage(search);
		}else{
			pageFinder=classTypeServiceImpl.findClassTypesByPage(search);
		}
		model.addAttribute("pageFinder", pageFinder);
		model.addAttribute("searchName", search.getName());
		return "classType/classIndexAjaxList";
	} 
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 跳转到添加班型页面
	 * @author zhang.zx
	 * @date 2015年5月5日 下午8:32:48
	 * @modifier
	 * @modify-date 2015年5月5日 下午8:32:48
	 * @version 1.0
	 * @param model
	 * @param classType
	 * @return
	 */
	@RequestMapping(value="/addClassType",method=RequestMethod.POST)
	public String addClassTypeMessage(Model model,ClassType classType,String lable){
		List<SysConfigItem> firstItems = sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
		model.addAttribute("firstItems", firstItems);
		model.addAttribute("itemOneId", classType.getItemOneId());
		model.addAttribute("itemSecondId", classType.getItemSecondId());
		model.addAttribute("lable", lable);
		//面授和直播
		if("face".equals(lable)||"live".equals(lable)){
			return "classType/addClassTypeFaceOrLiveMessage";
		}else if("video".equals(lable)){
			return "classType/addClassTypeVideoMessage";
		}else if("togther".equals(lable)){
			return "classType/addClassTypeTogtherMessage_1";
		}else{
			return "classType/addClassTypeOtherMessage";
		}
	}
	/**
	 * 获取课程协议
	 * @author licong
	 * @date 2016年10月31日 下午4:50:53
	 * @param  
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCtPotocolSet")
	public Object getCtPotocolSet(Integer type,String functionCode){
		Map<String,Object> result = new HashMap<String,Object>();
		//课程协议
		CompanyFunctionSet conditon=new CompanyFunctionSet();
		conditon.setCompanyId(WebUtils.getCurrentCompanyId());
		conditon.setFunctionCode(functionCode);
		conditon.setStatus("1");
		CompanyFunctionSet sets=companyFunctionSetServiceImpl.findCompanyUseCourse(conditon);
		if(sets == null){
			result.put("open", false);
			return result;
		}
		CourseProtocolConfig config = new CourseProtocolConfig();
		config.setCompanyId(WebUtils.getCurrentCompanyId());
		config.setType(type);
		config.setStatus(1);
		config.setDelFlag(0);
		List<CourseProtocolConfig> courseProtocolConfigs = courseProtocolConfigServiceImpl.findBySearch(config);
		result.put("open", true);
		result.put("courseProtocolConfig", courseProtocolConfigs);
		return result;
	}
	/**
	 * 编辑班型
	 * @param model
	 * @param request
	 * @param ct
	 * @return
	 */
	@RequestMapping(value="/editClassTypeMessage",method=RequestMethod.POST)
	public String updateClassTypeMessage(Model model,HttpServletRequest request,String type,ClassType ct,String lable){
		//根据班型id查询详情
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + ct.getId());
		ClassTypeVo classType=classTypeServiceImpl.findClassTypeDetail(map);
		model.addAttribute("classType", classType);
 		model.addAttribute("type", type);
 		if(lable.contains(",")){
 			String[] strs=lable.split(",");
 	 		String fla=strs[0];
 	 		if(strs.length>1&&!"remote".equals(fla)){
 	 			model.addAttribute("lable", "togther");
 	 			return "classType/addClassTypeTogtherMessage_1";
 	 		}else if("face".equals(fla)||"live".equals(fla)){
 	 			model.addAttribute("lable", fla);
 	 			return "classType/addClassTypeFaceOrLiveMessage";
 	 		}else if("video".equals(fla)){
// 	 			ClassModule c=new ClassModule();
// 	 			c.setName(classType.getName());
// 	 			c.setItemOneId(classType.getItemOneId());
// 	 			c.setItemSecondId(classType.getItemSecondId());
//	 			ClassModule m=classModuleServiceImpl.queryModuleByName(c);
 	 			ClassModule m=classModuleServiceImpl.queryModuleByClasstypeId(classType.getId());
 	 			if(m!=null){
 	 				model.addAttribute("courseNum", m.getTotalClassHour());
 	 			}else{
 	 				model.addAttribute("courseNum", 0);
 	 			}
 	 			model.addAttribute("lable", fla);
 	 			return "classType/addClassTypeVideoMessage";
 	 		}else{
 	 			model.addAttribute("lable", "other");
 	 			return "classType/addClassTypeOtherMessage";
 	 		}
 		}else{
 			model.addAttribute("lable", lable);
 			//根据所属标签跳转不同页面
 			if("face".equals(lable)||"live".equals(lable)){
 			
 	 			return "classType/addClassTypeFaceOrLiveMessage";
 			}else if("video".equals(lable)){
// 				ClassModule c=new ClassModule();
// 	 			c.setName(classType.getName());
// 	 			c.setItemOneId(classType.getItemOneId());
// 	 			c.setItemSecondId(classType.getItemSecondId());
// 	 			ClassModule m=classModuleServiceImpl.queryModuleByName(c);
 				ClassModule m=classModuleServiceImpl.queryModuleByClasstypeId(classType.getId());
 				if(m!=null){
 	 				model.addAttribute("courseNum", m.getTotalClassHour());
 	 			}else{
 	 				model.addAttribute("courseNum", 0);
 	 			}
 				return "classType/addClassTypeVideoMessage";
 			}else if("togther".equals(lable)){
 				
 				return "classType/addClassTypeTogtherMessage_1";
 			}else{
 				return "classType/addClassTypeOtherMessage";
 			}
 		}
	}

	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 修改班型基本信息并跳转到打包页面
	 * @author zhang.zx
	 * @date 2015年5月9日 下午3:31:22
	 * @modifier
	 * @modify-date 2015年5月9日 下午3:31:22
	 * @version 1.0
	 * @param model
	 * @param request
	 * @param ct
	 * @return
	 */
	@RequestMapping(value="/updateClassTypeMessage",method=RequestMethod.POST)
	public String updateBaseMessage(Model model,HttpServletRequest request,Integer oneId,Integer twoId,String type1,ClassType ct,String mark,String lable,Integer courseNum){
		ct.setItemOneId(oneId);
		ct.setItemSecondId(twoId);
		ct.setUpdateTime(new Date());
		ct.setUpdator(WebUtils.getCurrentUserId(request));
		
		CompanyFunctionSet conditon=new CompanyFunctionSet();
		conditon.setCompanyId(WebUtils.getCurrentCompanyId());
		conditon.setFunctionCode("COURSE_THIRD_CATEGORY");
		conditon.setStatus("1");
		CompanyFunctionSet sets=companyFunctionSetServiceImpl.findCompanyUseCourse(conditon);
		conditon.setFunctionCode("COURSE_SECOND_CATEGORY");
		CompanyFunctionSet sets1=companyFunctionSetServiceImpl.findCompanyUseCourse(conditon);
		if(null==sets && null==sets1){
			ct.setItemTag("");
		}
		//更新班型信息
		classTypeServiceImpl.update(ct);
		
		//更新总课时
		try {
			ClassModule cm=classModuleServiceImpl.queryModuleByClasstypeId(ct.getId());
			ClassModule module=new ClassModule();
			module.setId(cm.getId());
			module.setTotalClassHour(courseNum);
			classModuleServiceImpl.update(module);
		} catch (Exception e) {
			log.error("修改总课时失败",e);
			e.printStackTrace();
		}
		
		//根据班型id查询商品信息
		CommodityProductRealtion commodityProductRealtion = new CommodityProductRealtion();
		commodityProductRealtion.setProductId(ct.getId());

		commodityProductRealtion = commodityProductRealtionServiceImpl
				.findByProduyctId(commodityProductRealtion);
		//更新商品信息
		Commodity commodity = new Commodity();
		commodity.setId(commodityProductRealtion.getComId());
		commodity.setUpdator(WebUtils.getCurrentUserId(request));
		commodity.setUpdateTime(new Date());
		commodity.setBaseNum(ct.getBaseNum());
		commodity.setOriginalPrice(ct.getOriginalPrice());
		commodity.setRealPrice(ct.getRealPrice());
		commodity.setName(ct.getName());
		commodity.setRecommendFlag(ct.getRecommendFlag());
		if(null!=sets || null!=sets1){
			commodity.setItemTag(ct.getItemTag());
		}
		commodity.setIntegralFlag(ct.getIntegralFlag());
		commodity.setMemberFlag(ct.getMemberFlag());
		commodityServiceImpl.update(commodity);
		//保存并退出
		if("saveandtui".equals(mark)){
			return "redirect:showClassTypePage";
		}
		ClassType cs=classTypeServiceImpl.findClassTypeById(ct.getId());
		SysConfigItem item=sysConfigItemServiceImpl.findSysConfigItemById(cs.getItemOneId());
	    SysConfigItem item2=sysConfigItemServiceImpl.findSysConfigItemById(cs.getItemSecondId());
	    if(item!=null){
	    	cs.setItemOneName(item.getItemName());
	    }
	    if(item2!=null){
	    	cs.setItemSecondName(item2.getItemName());
	    }
	    model.addAttribute("type2", type1);
		model.addAttribute("ct", cs);
		model.addAttribute("lable", lable);
		//根据所属标签跳转不同页面
		if("face".equals(lable)||"live".equals(lable)){
			//SysConfigItem item= sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemOneId());
			model.addAttribute("itemOneName", ct.getItemOneName());
			//普通模块
			List<ClassModule> classModule=classModuleServiceImpl.findModulesByClassTypeId(ct.getId());
			model.addAttribute("classModuleList", classModule);
			return "classType/addClassTypeFaceOrLiveMessage_2";
		}else if("video".equals(lable)){
			//查询班型下的模块信息
			ClassModule m=classModuleServiceImpl.queryModuleByClasstypeId(ct.getId());
			model.addAttribute("classTypeId", ct.getId());
  			model.addAttribute("moduleName", ct.getName());
  		    model.addAttribute("itemOneId",ct.getItemOneId());
  		    model.addAttribute("itemSecondId", ct.getItemSecondId());
  		    if(m!=null){
  		    	model.addAttribute("moduleId", m.getId());
  		    }
			model.addAttribute("id", ct.getId());
			return "classType/addClassTypeVideoMessage_2";
		}else if("togther".equals(lable)){
			//SysConfigItem item= sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemOneId());
			model.addAttribute("itemOneName", ct.getItemOneName());
			//普通模块
			List<ClassModule> classModule=classModuleServiceImpl.findModulesByClassTypeId(ct.getId());
			model.addAttribute("classModuleList", classModule);
			return "classType/addClassTypeTogtherMessage_2";
		}else{
  			ClassType classType=classTypeServiceImpl.findClassTypeById(ct.getId());
			if(classType.getCover()!=null&&!"".equals(classType.getCover())){
   		    	String url="http://"+propertiesUtil.getProjectImageUrl()+"/";
   		  	    classType.setCover(url+classType.getCover());
   		    }else{
   		    	classType.setCover("");
   		    }
			SysConfigItem item3=sysConfigItemServiceImpl.findSysConfigItemById(classType.getItemOneId());
		    SysConfigItem item4=sysConfigItemServiceImpl.findSysConfigItemById(classType.getItemSecondId());
		    if(item!=null){
		    	classType.setItemOneName(item3.getItemName());
		    }
		    if(item2!=null){
		    	classType.setItemSecondName(item4.getItemName());
		    }
			model.addAttribute("ct", classType);
  			model.addAttribute("ctype", classType);
  			if(classType.getTeacherId()!=null){
  	  			SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(classType.getTeacherId()));
  		 	    model.addAttribute("teacher", teacher);
  			}
		 	model.addAttribute("classTypeId", classType.getId());
		 	 //根据班型id查询商品信息id
		    CommodityProductRealtion comm=commodityProductRealtionServiceImpl.findByClassTypeId(ct.getId()+"");
			Integer cId=comm.getComId();
  		    model.addAttribute("cId", cId);
  		    model.addAttribute("itemOneid", classType.getItemOneId());
  		    
  			Map<String, String> teacherMap = new HashMap<String, String>();
  			teacherMap.put("companyId",WebUtils.getCurrentCompanyId()+"");
  			teacherMap.put("schoolId", WebUtils.getCurrentSchoolId() + "");
  			Subject subject = SecurityUtils.getSubject();
  	        if((subject.hasRole("直播老师")&&subject.hasRole("排课老师"))&&!(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营")))
  	        {
  	        	Users user = WebUtils.getCurrentUser();
  	        	SysConfigTeacher teacher2 = new SysConfigTeacher();
  	        	teacher2.setCompanyId(WebUtils.getCurrentCompanyId());
  	        	teacher2.setUserId(user.getId());
  	        	 List<SysConfigTeacher> list = this.sysConfigTeacherServiceImpl.findTeacherByUserId(teacher2);
  	        	 if(list != null && list.size()>0){
  	        		 teacherMap.put("id", list.get(0).getId()+"");
  	        	 }
  	        }
  			model.addAttribute("teachers",sysConfigTeacherServiceImpl.findTeachers(teacherMap));
			return "classType/addClassTypeOtherMessage_2";
		}
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 修改班型打包信息并跳转到班型详情页面
	 * @author zhang.zx
	 * @date 2015年5月9日 下午3:35:43
	 * @modifier
	 * @modify-date 2015年5月9日 下午3:35:43
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updatePackage",method=RequestMethod.POST)
	public String editClassTypePackage(Model model,HttpServletRequest request,String type2,String moduleId,Integer id,String mark,String lable,Integer faceFlag,Integer liveFlag,Integer videoFlag){
		if(moduleId!=null&&!"".equals(moduleId)){
			String modulesId=moduleId.substring(0, moduleId.length()-1);
		    String[] mId=modulesId.split(","); 
		    ClassType classTypeModule=classTypeServiceImpl.findClassTypeById(id);
		    
		    //保存模块
		    if(classTypeModule.getRemoteFlag()==1){
		    	//清除之前模块
		    	classTypeRemoteRelationServiceImpl.deleteClassTypeRemoteRelationById(classTypeModule.getId());
		    	for(int i=0;i<mId.length;i++){
		    		 ClassTypeRemoteRelation ctrmr=new ClassTypeRemoteRelation();
		    		 ctrmr.setRemoteId(Integer.parseInt(mId[i]));
		    		 ctrmr.setClassTypeId(id);
		    		 classTypeRemoteRelationServiceImpl.insert(ctrmr);
				}
		    }else{
		    	//清除之前模块
		    	classTypeModuleRelationServiceImpl.deleteClassTypeModuleRelationById(classTypeModule.getId());
			    for(int i=0;i<mId.length;i++){
			    	ClassTypeModuleRelation ctmr=new ClassTypeModuleRelation();
			    	ctmr.setModuleId(Integer.parseInt(mId[i]));
			    	ctmr.setClassTypeId(id);
			    	classTypeModuleRelationServiceImpl.insert(ctmr);
			    }
		    }
		}
	    if("togther".equals(lable)){
		 //更新班型所属标签
 			ClassType c=new ClassType();
 			c.setId(id);
 			c.setFaceFlag(faceFlag);
 			c.setLiveFlag(liveFlag);
 			c.setVideoFlag(videoFlag);
 			c.setUpdateTime(new Date());
 			c.setUpdator(WebUtils.getCurrentUserId(request));
 			classTypeServiceImpl.update(c);
 			
 			 //根据班型id查询商品信息id
 		    CommodityProductRealtion comm=commodityProductRealtionServiceImpl.findByClassTypeId(id+"");
 			Integer cId=comm.getComId();
 			
 			Commodity commodity = new Commodity();
 			commodity.setId(cId);
 			commodity.setFaceFlag(c.getFaceFlag());
 			commodity.setLiveFlag(c.getLiveFlag());
 			commodity.setVideoFlag(c.getVideoFlag());
 			commodity.setUpdateTime(new Date());
 			commodity.setUpdator(WebUtils.getCurrentUserId(request));
 			commodityServiceImpl.update(commodity);
	    }
	    //保存并退出
	    if("saveandtui".equals(mark)){
	    	return "redirect:showClassTypePage";
	    }
	    model.addAttribute("type2", type2);
	    model.addAttribute("lable", lable);
	    //根据所属标签跳转不同页面
  		if("face".equals(lable)||"live".equals(lable)){
  			 //根据班型id查询商品信息id
  		    CommodityProductRealtion comm=commodityProductRealtionServiceImpl.findByClassTypeId(id+"");
  			Integer cId=comm.getComId();
  			Map<String, String> map = new HashMap<String, String>();
  			map.put("classId", "" + id);
  			ClassTypeVo classType=classTypeServiceImpl.findClassTypeDetail(map);
  			//编辑
  			if("update".equals(type2)){
  				if(classType.getCover()!=null&&!"".equals(classType.getCover())){
  	   		    	String url="http://"+propertiesUtil.getProjectImageUrl()+"/";
  	   		  	    classType.setCover(url+classType.getCover());
  	   		    }else{
  	   		    	classType.setCover("");
  	   		    }
  				model.addAttribute("ct", classType);
  	  			model.addAttribute("ctype", classType);
  	  			if(classType.getTeacherId()!=null){
	  	  			SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(classType.getTeacherId()));
	  		 	    model.addAttribute("teacher", teacher);
  	  			}
  			}
  			model.addAttribute("itemOneid", classType.getItemOneId());
  			model.addAttribute("classTypeId", id);
			
  			Map<String, String> teacherMap = new HashMap<String, String>();
  			teacherMap.put("companyId",WebUtils.getCurrentCompanyId()+"");
  			teacherMap.put("schoolId", WebUtils.getCurrentSchoolId() + "");
  			Subject subject = SecurityUtils.getSubject();
  	        if((subject.hasRole("直播老师")&&subject.hasRole("排课老师"))&&!(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营")))
  	        {
  	        	Users user = WebUtils.getCurrentUser();
  	        	SysConfigTeacher teacher2 = new SysConfigTeacher();
  	        	teacher2.setUserId(user.getId());
  	        	teacher2.setCompanyId(WebUtils.getCurrentCompanyId());
  	        	 List<SysConfigTeacher> list = this.sysConfigTeacherServiceImpl.findTeacherByUserId(teacher2);
  	        	 if(list != null && list.size()>0){
  	        		 teacherMap.put("id", list.get(0).getId()+"");
  	        	 }
  	        }
  			model.addAttribute("teachers",sysConfigTeacherServiceImpl.findTeachers(teacherMap));
  			model.addAttribute("cId", cId);
  			return "classType/addClassTypeFaceOrLiveMessage_3";
  		}else if("video".equals(lable)){
  			
  			return "404";
  		}else if("togther".equals(lable)){
  			//根据班型id查询信息
  			Map<String, String> map = new HashMap<String, String>();
  			map.put("classId", "" + id);
  			ClassTypeVo classTypes=classTypeServiceImpl.findClassTypeDetail(map);
  			ClassModule m=classModuleServiceImpl.queryModuleByClasstypeId(classTypes.getId());
  			model.addAttribute("id", id);
  			if(m!=null){
  				model.addAttribute("moduleId", m.getId());
  			}
  			model.addAttribute("classTypeId", classTypes.getId());
  			model.addAttribute("moduleName", classTypes.getName());
  		    model.addAttribute("itemOneId",classTypes.getItemOneId() );
  		    model.addAttribute("itemSecondId", classTypes.getItemSecondId());
  		    model.addAttribute("ct", classTypes);
  			return "classType/addClassTypeTogtherMessage_3";
  		}else{
  			return "";
  		}
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 班型打包
	 * @author zhang.zx
	 * @date 2015年5月5日 下午9:23:50
	 * @modifier
	 * @modify-date 2015年5月5日 下午9:23:50
	 * @version 1.0
	 * @param model
	 * @param classType
	 * @return
	 */
	@RequestMapping(value="/addClassPackage",method=RequestMethod.POST)
	public String addClassTypePackage(Model model,HttpServletRequest request,ClassType classType,String mark,String lable,Integer moduleId){
		//保存并退出
		if("saveandtui".equals(mark)){
			return "redirect:showClassTypePage";
		}
		
		boolean flag = companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
		Subject subject = SecurityUtils.getSubject();
		Users currentUser = WebUtils.getCurrentUser();
		if(flag && subject.hasRole("排课老师")&& !(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营"))){
			model.addAttribute("isFuShengPaikeTeac",true);
			List<SysConfigTeacher> teacherList = sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
			if(teacherList!=null && teacherList.size()>0)
			{
				SysConfigTeacher configTeacher = teacherList.get(0);
				model.addAttribute("configTeacher", configTeacher);
			}
		}
		ClassType cst=classTypeServiceImpl.findClassTypeById(classType.getId());
		SysConfigItem item=sysConfigItemServiceImpl.findSysConfigItemById(cst.getItemOneId());
	    SysConfigItem item2=sysConfigItemServiceImpl.findSysConfigItemById(cst.getItemSecondId());
	    if(item!=null){
	    	 cst.setItemOneName(item.getItemName());
	    }
	    if(item2!=null){
	    	cst.setItemSecondName(item2.getItemName());
	    }
		model.addAttribute("ct", cst);
		model.addAttribute("lable", lable);
		//根据所属标签跳转不同页面
		if("face".equals(lable)||"live".equals(lable)){
			//SysConfigItem item= sysConfigItemServiceImpl.findSysConfigItemById(classType.getItemOneId());
			model.addAttribute("itemOneName", cst.getItemOneName());
			//model.addAttribute("ct", classType);
			return "classType/addClassTypeFaceOrLiveMessage_2";
		}else if("video".equals(lable)){
  			model.addAttribute("classTypeId", classType.getId());
  			model.addAttribute("moduleName", classType.getName());
  		    model.addAttribute("itemOneId",classType.getItemOneId() );
  		    model.addAttribute("itemSecondId", classType.getItemSecondId());
			model.addAttribute("moduleId", moduleId);
			model.addAttribute("id", classType.getId());
			return "classType/addClassTypeVideoMessage_2";
		}else if("togther".equals(lable)){
			//SysConfigItem item= sysConfigItemServiceImpl.findSysConfigItemById(classType.getItemOneId());
			model.addAttribute("itemOneName", cst.getItemOneName());
			//model.addAttribute("ct", classType);
			return "classType/addClassTypeTogtherMessage_2";
		}else{
		 	model.addAttribute("classTypeId", classType.getId());
		 	 //根据班型id查询商品信息id
		    CommodityProductRealtion comm=commodityProductRealtionServiceImpl.findByClassTypeId(classType.getId()+"");
			Integer cId=comm.getComId();
  		    model.addAttribute("cId", cId);
  		    model.addAttribute("itemOneid", classType.getItemOneId());
  		    
  			Map<String, String> teacherMap = new HashMap<String, String>();
  			teacherMap.put("companyId",WebUtils.getCurrentCompanyId()+"");
  			teacherMap.put("schoolId", WebUtils.getCurrentSchoolId() + "");
  	        if((subject.hasRole("直播老师")&&subject.hasRole("排课老师"))&&!(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营")))
  	        {
  	        	Users user = WebUtils.getCurrentUser();
  	        	SysConfigTeacher teacher2 = new SysConfigTeacher();
  	        	teacher2.setUserId(user.getId());
  	        	teacher2.setCompanyId(WebUtils.getCurrentCompanyId());
  	        	 List<SysConfigTeacher> list = this.sysConfigTeacherServiceImpl.findTeacherByUserId(teacher2);
  	        	 if(list != null && list.size()>0){
  	        		 teacherMap.put("id", list.get(0).getId()+"");
  	        	 }
  	        }
  			model.addAttribute("teachers",sysConfigTeacherServiceImpl.findTeachers(teacherMap));
			return "classType/addClassTypeOtherMessage_2";
		}
	}
	
	//添加班型基本信息
	@ResponseBody
	@RequestMapping(value="/addBaseInfo",method=RequestMethod.POST)
	public ClassType addClassTypeBaseInfo(HttpServletRequest request,ClassType classType,String lable,Integer courseNum,String mark){
		//给所属标签赋值
		if("face".equals(lable)||"live".equals(lable)){
			if("face".equals(lable)){
				classType.setFaceFlag(1);
				classType.setLiveFlag(0);
				classType.setVideoFlag(0);
				classType.setRemoteFlag(0);
			}else{
				classType.setFaceFlag(0);
				classType.setLiveFlag(1);
				classType.setVideoFlag(0);
				classType.setRemoteFlag(0);
			}
		}else if("video".equals(lable)){
			classType.setFaceFlag(0);
			classType.setLiveFlag(0);
			classType.setVideoFlag(1);
			classType.setRemoteFlag(0);
		}else if("togther".equals(lable)){
			classType.setFaceFlag(1);
			classType.setLiveFlag(1);
			classType.setVideoFlag(1);
			classType.setRemoteFlag(0);
		}else{
			classType.setFaceFlag(0);
			classType.setLiveFlag(0);
			classType.setVideoFlag(0);
			classType.setRemoteFlag(1);
		}
		ClassType classTypes=addClassTypeCommMethod(request, classType,lable,courseNum);
		return classTypes;
	}
	
	//添加班型公共方法
	private ClassType addClassTypeCommMethod(HttpServletRequest request,ClassType classType,String lable,Integer courseNum){
		Integer fla=0;
		CompanyFunctionSet conditon=new CompanyFunctionSet();
		conditon.setCompanyId(WebUtils.getCurrentCompanyId());
		conditon.setFunctionCode("COURSE_THIRD_CATEGORY");
		conditon.setStatus("1");
		CompanyFunctionSet sets=companyFunctionSetServiceImpl.findCompanyUseCourse(conditon);
		conditon.setFunctionCode("COURSE_SECOND_CATEGORY");
		CompanyFunctionSet sets1=companyFunctionSetServiceImpl.findCompanyUseCourse(conditon);
		if(null==sets && null==sets1){
			classType.setItemTag("");
		}
		if(classType.getId()==null||"".equals(classType.getId())){
			classType.setCreateTime(new Date());
			classType.setUpdateTime(new Date());
			classType.setCreator(WebUtils.getCurrentUserId(request));
			classType.setUpdator(WebUtils.getCurrentUserId(request));
			classType.setPublishStatus("CLASS_UNPUBLISHED");
			classType.setTypeCode("CLASS_TYPE_NOMAL");
			classType.setCompanyId(WebUtils.getCurrentCompanyId());
			classType.setDelFlag(0);
			classType.setCreateSchoolId(WebUtils.getCurrentSchoolId());
			if(classType.getFaceFlag()!=null){
				classType.setFaceFlag(classType.getFaceFlag());
			}else{
				classType.setFaceFlag(classType.getFaceFlag());
			}
			if(classType.getVideoFlag()!=null){
				classType.setVideoFlag(classType.getVideoFlag());
			}else{
				classType.setVideoFlag(classType.getVideoFlag());
			}
			if(classType.getLiveFlag()!=null){
				classType.setLiveFlag(classType.getLiveFlag());
			}else{
				classType.setLiveFlag(classType.getLiveFlag());
			}
			if(classType.getRemoteFlag()!=null&&classType.getRemoteFlag().equals(1)){
				classType.setRemoteFlag(classType.getRemoteFlag());
				classType.setFaceFlag(classType.getFaceFlag());
				classType.setVideoFlag(classType.getVideoFlag());
				classType.setLiveFlag(classType.getLiveFlag());
				classType.setTypeCode("CLASS_TYPE_REMOTE");
			}else{
				classType.setRemoteFlag(classType.getRemoteFlag());
			}
//			if(null!=classType.getValidityDay()&&!"".equals(classType.getValidityDay())){
//				classType.setValidityDate(DateUtil.addDate(new Date(), classType.getValidityDay()));
//			}
			classTypeServiceImpl.insert(classType);
			
			Commodity commodity = new Commodity();

			commodity.setCerateTime(new Date());
			commodity.setCompanyId(WebUtils.getCurrentCompanyId());
			commodity.setItemOneId(classType.getItemOneId());
			commodity.setItemSecondId(classType.getItemSecondId());
			commodity.setType("COMMODITY_CLASS");
			commodity.setUpdator(WebUtils.getCurrentUserId(request));
			commodity.setSchoolId(WebUtils.getCurrentSchoolId());
			commodity.setBaseNum(classType.getBaseNum());
			commodity.setStatus(0 + "");
			commodity.setRemoteFlag(classType.getRecommendFlag());
			commodity.setOriginalPrice(Double.parseDouble(classType
					.getOriginalPrice() + ""));
			commodity.setRealPrice(classType.getRealPrice());
			commodity.setName(classType.getName());
			commodity.setUpdateTime(new Date());
			commodity.setCreator(WebUtils.getCurrentUserId(request));
			commodity.setFaceFlag(classType.getFaceFlag());
			commodity.setLiveFlag(classType.getLiveFlag());
			commodity.setVideoFlag(classType.getVideoFlag());
			commodity.setRemoteFlag(classType.getRemoteFlag());
			commodity.setRecommendFlag(classType.getRecommendFlag());
			commodity.setBuyNum(0);
			if(null!=sets || null!=sets1){
				commodity.setItemTag(classType.getItemTag());
			}
			commodity.setIntegralFlag(classType.getIntegralFlag());
			commodity.setMemberFlag(classType.getMemberFlag());
			commodityServiceImpl.insert(commodity);

			CommodityProductRealtion commodityProductRealtion = new CommodityProductRealtion();
			commodityProductRealtion.setProductId(classType.getId());
			commodityProductRealtion.setProductType(1 + "");
			commodityProductRealtion.setComId(commodity.getId());
			commodityProductRealtionServiceImpl.insert(commodityProductRealtion);
		}
		if("video".equals(lable)){
			ClassModule cmodule=classModuleServiceImpl.queryModuleByClasstypeId(classType.getId());
			if(null!=cmodule){
				fla=cmodule.getId();
			}else{
				ClassModule module=new ClassModule();
				module.setName(classType.getName());
				module.setTeachMethod("TEACH_METHOD_VIDEO");
				module.setCreateTime(new Date());
				module.setCreator(WebUtils.getCurrentUserId(request));
				module.setItemOneId(classType.getItemOneId());
				module.setItemSecondId(classType.getItemSecondId());
				module.setTotalClassHour(courseNum);
				module.setDelFlag(0);
				module.setSchoolId(WebUtils.getCurrentSchoolId());
				module.setCompanyId(WebUtils.getCurrentCompanyId());
				classModuleServiceImpl.insert(module);
				ClassTypeModuleRelation ctmr=new ClassTypeModuleRelation();
		    	ctmr.setModuleId(module.getId());
		    	ctmr.setClassTypeId(classType.getId());
		    	classTypeModuleRelationServiceImpl.insert(ctmr);
				fla=module.getId();
			}
		}
		if(fla>0){
			//模块id
			classType.setDelFlag(fla);
		}
		classType.setId(classType.getId());
		return classType;
	}

	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 添加标签库
	 * @author zhang.zx
	 * @date 2016年5月17日 下午3:01:10
	 * @modifier
	 * @modify-date 2016年5月17日 下午3:01:10
	 * @version 1.0
	 * @param request
	 * @param classType
	 */
	private void addCourseLable(HttpServletRequest request, ClassType classType) {
		//添加标签库
			if(null!=classType.getTagName()&&!"".equals(classType.getTagName())){
				try {
					String tagId="";
					String str[]=classType.getTagName().split(",");
					for(int i=0;i<str.length;i++){
						if(null!=str[i] && !"null".equals(str[i])){
							SysConfigItemTag search=new SysConfigItemTag();
							search.setCompanyId(WebUtils.getCurrentCompanyId());
							search.setSchoolId(WebUtils.getCurrentSchoolId());
							search.setTagName(str[i]);
							if(i==0){
								search.setLevel(1);
							}else{
								search.setLevel(2);
							}
							List<SysConfigItemTag> arr=sysConfigItemTagServiceImpl.queryTagsBycondition(search);
							if(arr.size()>0){
								tagId+=arr.get(0).getId()+",";
							}else{
								search.setItemOneId(classType.getItemOneId());
								search.setItemSecondId(classType.getItemSecondId());
								search.setCreateTime(new Date());
								search.setUpdateTime(new Date());
								search.setCreator(WebUtils.getCurrentUserId(request));
								search.setUpdator(WebUtils.getCurrentUserId(request));
								sysConfigItemTagServiceImpl.insert(search);
								tagId+=search.getId()+",";
							}
						}else{
							tagId+="null"+",";
						}
					}
					if(null!=tagId && !"".equals(tagId) && !"null,null,".equals(tagId)){
						tagId=tagId.substring(0, tagId.length()-1);
						classType.setItemTag(tagId);
					}
				} catch (Exception e) {
					log.error("添加标签库失败",e);
					e.printStackTrace();
				}
			}
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 版型上架(添加班型模块信息)
	 * @author zhang.zx
	 * @date 2015年5月5日 下午9:23:50
	 * @modifier
	 * @modify-date 2015年5月5日 下午9:23:50
	 * @version 1.0
	 * @param model
	 * @param classType
	 * @return
	 */
	@RequestMapping(value="/classTypeOnsal",method=RequestMethod.POST)
	public String classTypeOnsale(Model model,HttpServletRequest request,String moduleId,Integer id,String mark,String lable,Integer faceFlag,Integer liveFlag,Integer videoFlag){
		String modulesId=moduleId.substring(0, moduleId.length()-1);
	    String[] mId=modulesId.split(","); 
	    ClassType classTypeModule=classTypeServiceImpl.findClassTypeById(id);
	    SysConfigItem item=sysConfigItemServiceImpl.findSysConfigItemById(classTypeModule.getItemOneId());
	    SysConfigItem item2=sysConfigItemServiceImpl.findSysConfigItemById(classTypeModule.getItemSecondId());
	    if(item!=null){
	    	 classTypeModule.setItemOneName(item.getItemName());
	    }
	    if(item2!=null){
	    	 classTypeModule.setItemSecondName(item2.getItemName());
	    }
	    model.addAttribute("ctype", classTypeModule);
	    model.addAttribute("ct", classTypeModule);
	    //保存模块
	    if(classTypeModule!=null){
	    	 if(classTypeModule.getRemoteFlag()==1){
	 	    	for(int i=0;i<mId.length;i++){
	 	    		 ClassTypeRemoteRelation ctrmr=new ClassTypeRemoteRelation();
	 	    		 ctrmr.setRemoteId(Integer.parseInt(mId[i]));
	 	    		 ctrmr.setClassTypeId(id);
	 	    		 classTypeRemoteRelationServiceImpl.insert(ctrmr);
	 			}
	 	    }else{
	 	    	for(int i=0;i<mId.length;i++){
	 	  	    	ClassTypeModuleRelation ctmr=new ClassTypeModuleRelation();
	 	  	    	ctmr.setModuleId(Integer.parseInt(mId[i]));
	 	  	    	ctmr.setClassTypeId(id);
	 	  	    	classTypeModuleRelationServiceImpl.insert(ctmr);	
	 	  	    }
	 	    }
	    }
	    
	    if("togther".equals(lable)){
	    	ClassType c=new ClassType();
			c.setId(id);
			c.setFaceFlag(faceFlag);
			c.setLiveFlag(liveFlag);
			c.setVideoFlag(videoFlag);
			c.setUpdateTime(new Date());
			c.setUpdator(WebUtils.getCurrentUserId(request));
			classTypeServiceImpl.update(c);
			
		    //根据班型id查询商品信息id
		    CommodityProductRealtion comm=commodityProductRealtionServiceImpl.findByClassTypeId(id+"");
			Integer cId=comm.getComId();
			
			Commodity commodity = new Commodity();
			commodity.setId(cId);
			commodity.setFaceFlag(c.getFaceFlag());
			commodity.setLiveFlag(c.getLiveFlag());
			commodity.setVideoFlag(c.getVideoFlag());
			commodity.setUpdateTime(new Date());
			commodity.setUpdator(WebUtils.getCurrentUserId(request));
			commodityServiceImpl.update(commodity);
	    }
	    
	    //保存并退出
	    if("saveandtui".equals(mark)){
	    	return "redirect:showClassTypePage";
	    }
	    model.addAttribute("lable", lable);
	    boolean flag = companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
		Subject subject = SecurityUtils.getSubject();
		Users currentUser = WebUtils.getCurrentUser();
		if(flag && subject.hasRole("排课老师")&& !(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营"))){
			model.addAttribute("isFuShengPaikeTeac",true);
			List<SysConfigTeacher> teacherList = sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
			if(teacherList!=null && teacherList.size()>0)
			{
				SysConfigTeacher configTeacher = teacherList.get(0);
				model.addAttribute("configTeacher", configTeacher);
			}
		}
	    //根据所属标签跳转不同页面
  		if("face".equals(lable)||"live".equals(lable)){
  			Map<String, String> map = new HashMap<String, String>();
  			map.put("classId", "" + id);
  			ClassTypeVo classType=classTypeServiceImpl.findClassTypeDetail(map);

  			model.addAttribute("itemOneid", classType.getItemOneId());
  			model.addAttribute("classTypeId", id);
  			 //根据班型id查询商品信息id
		    CommodityProductRealtion comm=commodityProductRealtionServiceImpl.findByClassTypeId(classType.getId()+"");
			Integer cId=comm.getComId();
			
  			Map<String, String> teacherMap = new HashMap<String, String>();
  			teacherMap.put("companyId",WebUtils.getCurrentCompanyId()+"");
  			teacherMap.put("schoolId", WebUtils.getCurrentSchoolId() + "");
  	        if((subject.hasRole("直播老师")&&subject.hasRole("排课老师"))&&!(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营")))
  	        {
  	        	Users user = WebUtils.getCurrentUser();
  	        	SysConfigTeacher teacher2 = new SysConfigTeacher();
  	        	teacher2.setUserId(user.getId());
  	        	teacher2.setCompanyId(WebUtils.getCurrentCompanyId());
  	        	 List<SysConfigTeacher> list = this.sysConfigTeacherServiceImpl.findTeacherByUserId(teacher2);
  	        	 if(list != null && list.size()>0){
  	        		 teacherMap.put("id", list.get(0).getId()+"");
  	        	 }
  	        }
  			model.addAttribute("teachers",sysConfigTeacherServiceImpl.findTeachers(teacherMap));
  			model.addAttribute("cId", cId);
  			return "classType/addClassTypeFaceOrLiveMessage_3";
  		}else if("video".equals(lable)){
  			
  			return "404";
  		}else if("togther".equals(lable)){
  			//根据班型id查询信息
  			Map<String, String> map = new HashMap<String, String>();
  			map.put("classId", "" + id);
  			ClassTypeVo classTypes=classTypeServiceImpl.findClassTypeDetail(map);
  			model.addAttribute("classTypeId", classTypes.getId());
  			model.addAttribute("moduleName", classTypes.getName());
  			ClassModule m=classModuleServiceImpl.queryModuleByClasstypeId(classTypes.getId());
  			if(m!=null){
  				model.addAttribute("moduleId", m.getId());
  			}
  		    model.addAttribute("itemOneId",classTypes.getItemOneId());
  		    model.addAttribute("itemSecondId", classTypes.getItemSecondId());
  			model.addAttribute("id", id);
  			model.addAttribute("type2", "update");
  			return "classType/addClassTypeTogtherMessage_3";
  		}else{
  		    model.addAttribute("classTypeId", id);
  		    model.addAttribute("itemOneid", classTypeModule.getItemOneId());
  		    
  			Map<String, String> teacherMap = new HashMap<String, String>();
  			teacherMap.put("companyId",WebUtils.getCurrentCompanyId()+"");
  			teacherMap.put("schoolId", WebUtils.getCurrentSchoolId() + "");
  	        if((subject.hasRole("直播老师")&&subject.hasRole("排课老师"))&&!(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营")))
  	        {
  	        	Users user = WebUtils.getCurrentUser();
  	        	SysConfigTeacher teacher2 = new SysConfigTeacher();
  	        	teacher2.setUserId(user.getId());
  	        	teacher2.setCompanyId(WebUtils.getCurrentCompanyId());
  	        	 List<SysConfigTeacher> list = this.sysConfigTeacherServiceImpl.findTeacherByUserId(teacher2);
  	        	 if(list != null && list.size()>0){
  	        		 teacherMap.put("id", list.get(0).getId()+"");
  	        	 }
  	        }
  			model.addAttribute("teachers",sysConfigTeacherServiceImpl.findTeachers(teacherMap));
  			return "404";
  		}
	}
	
	@RequestMapping(value="/showClass",method=RequestMethod.POST)
	public String showClassDetails(Integer id,String lable,String type,Model model){
		model.addAttribute("lable", lable);
		boolean flag = companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
		Subject subject = SecurityUtils.getSubject();
		Users currentUser = WebUtils.getCurrentUser();
		if(flag && subject.hasRole("排课老师")&& !(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营"))){
			model.addAttribute("isFuShengPaikeTeac",true);
			List<SysConfigTeacher> teacherList = sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
			if(teacherList!=null && teacherList.size()>0)
			{
				SysConfigTeacher configTeacher = teacherList.get(0);
				model.addAttribute("configTeacher", configTeacher);
			}
		}
		 //根据所属标签跳转不同页面
  		if("face".equals(lable)||"live".equals(lable)){
  			
  			return "404";
  		}else if("video".equals(lable)){
  			ClassType classType=classTypeServiceImpl.findClassTypeById(id);
  			//编辑
  			if("update".equals(type)){
  				if(classType.getCover()!=null&&!"".equals(classType.getCover())){
  	   		    	String url="http://"+propertiesUtil.getProjectImageUrl()+"/";
  	   		  	    classType.setCover(url+classType.getCover());
  	   		    }else{
  	   		    	classType.setCover("");
  	   		    }
  				SysConfigItem item=sysConfigItemServiceImpl.findSysConfigItemById(classType.getItemOneId());
  			    SysConfigItem item2=sysConfigItemServiceImpl.findSysConfigItemById(classType.getItemSecondId());
  			    if(item!=null){
  			    	classType.setItemOneName(item.getItemName());
  			    }
  			    if(item2!=null){
  			    	classType.setItemSecondName(item2.getItemName());
  			    }
  				model.addAttribute("ct", classType);
  	  			model.addAttribute("ctype", classType);
  	  			
  	  			if(classType.getTeacherId()!=null){
	  	  			SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(classType.getTeacherId()));
	  				model.addAttribute("teacher", teacher);
  	  			}
  			}
  			model.addAttribute("itemOneid", classType.getItemOneId());
  			model.addAttribute("classTypeId", id);
  			 //根据班型id查询商品信息id
		    CommodityProductRealtion comm=commodityProductRealtionServiceImpl.findByClassTypeId(classType.getId()+"");
			Integer cId=comm.getComId();
			
  			Map<String, String> teacherMap = new HashMap<String, String>();
  			teacherMap.put("companyId",WebUtils.getCurrentCompanyId()+"");
  			teacherMap.put("schoolId", WebUtils.getCurrentSchoolId() + "");
  	        if((subject.hasRole("直播老师")&&subject.hasRole("排课老师"))&&!(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营")))
  	        {
  	        	Users user = WebUtils.getCurrentUser();
  	        	SysConfigTeacher teacher2 = new SysConfigTeacher();
  	        	teacher2.setUserId(user.getId());
  	        	teacher2.setCompanyId(WebUtils.getCurrentCompanyId());
  	        	 List<SysConfigTeacher> list = this.sysConfigTeacherServiceImpl.findTeacherByUserId(teacher2);
  	        	 if(list != null && list.size()>0){
  	        		 teacherMap.put("id", list.get(0).getId()+"");
  	        	 }
  	        }
  			model.addAttribute("teachers",sysConfigTeacherServiceImpl.findTeachers(teacherMap));
  			model.addAttribute("cId", cId);
  			return "classType/addClassTypeVideoMessage_3";
  		}else if("togther".equals(lable)){
  			ClassType classType=classTypeServiceImpl.findClassTypeById(id);
  			SysConfigItem item=sysConfigItemServiceImpl.findSysConfigItemById(classType.getItemOneId());
		    SysConfigItem item2=sysConfigItemServiceImpl.findSysConfigItemById(classType.getItemSecondId());
		    if(item!=null){
		    	classType.setItemOneName(item.getItemName());
		    }
		    if(item2!=null){
		    	classType.setItemSecondName(item2.getItemName());
		    }
		    model.addAttribute("ct", classType);
  			//编辑
  			if("update".equals(type)){
  				if(classType.getCover()!=null&&!"".equals(classType.getCover())){
  	   		    	String url="http://"+propertiesUtil.getProjectImageUrl()+"/";
  	   		  	    classType.setCover(url+classType.getCover());
  	   		    }else{
  	   		    	classType.setCover("");
  	   		    }
  	  			model.addAttribute("ctype", classType);
  	  			
  	  		if(classType.getTeacherId()!=null){
  	  			SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(classType.getTeacherId()));
  				model.addAttribute("teacher", teacher);
	  		}
  			}
  			model.addAttribute("itemOneid", classType.getItemOneId());
  			model.addAttribute("classTypeId", id);
  			 //根据班型id查询商品信息id
		    CommodityProductRealtion comm=commodityProductRealtionServiceImpl.findByClassTypeId(classType.getId()+"");
			Integer cId=comm.getComId();
			
  			Map<String, String> teacherMap = new HashMap<String, String>();
  			teacherMap.put("companyId",WebUtils.getCurrentCompanyId()+"");
  			teacherMap.put("schoolId", WebUtils.getCurrentSchoolId() + "");
  	        if((subject.hasRole("直播老师")&&subject.hasRole("排课老师"))&&!(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营")))
  	        {
  	        	Users user = WebUtils.getCurrentUser();
  	        	SysConfigTeacher teacher2 = new SysConfigTeacher();
  	        	teacher2.setCompanyId(WebUtils.getCurrentCompanyId());
  	        	teacher2.setUserId(user.getId());
  	        	 List<SysConfigTeacher> list = this.sysConfigTeacherServiceImpl.findTeacherByUserId(teacher2);
  	        	 if(list != null && list.size()>0){
  	        		 teacherMap.put("id", list.get(0).getId()+"");
  	        	 }
  	        }
  			model.addAttribute("teachers",sysConfigTeacherServiceImpl.findTeachers(teacherMap));
  			model.addAttribute("cId", cId);
  			return "classType/addClassTypeTogtherMessage_4";
  		}else{
  			return "404";
  		}
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 新增班型信息
	 * @author zhang.zx
	 * @date 2015年5月5日 下午9:23:50
	 * @modifier
	 * @modify-date 2015年5月5日 下午9:23:50
	 * @version 1.0
	 * @param model
	 * @param classType
	 * @param multiPartRquest
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addClassSuccess",method=RequestMethod.POST)
	public String addClassComplete(Model model,ClassType classType,Integer cId,HttpServletRequest req){
		classType.setUpdateTime(new Date());
		classType.setUpdator(WebUtils.getCurrentUserId(req));
		classTypeServiceImpl.update(classType);
		
		Commodity commodity = new Commodity();
		commodity.setId(cId);
		commodity.setCoverUrl(classType.getCover());
		commodity.setOverview(classType.getDescription());
		commodity.setUpdateTime(new Date());
		commodity.setUpdator(WebUtils.getCurrentUserId(req));
		commodityServiceImpl.update(commodity);
		return "success";
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 招生
	 * @author zhang.zx
	 * @date 2015年5月9日 下午5:13:55
	 * @modifier
	 * @modify-date 2015年5月9日 下午5:13:55
	 * @version 1.0
	 * @return
	 */
	@RequestMapping("/showClassTypeStu")
	public String showClassTypeStu(Model model,ClassType classType,HttpServletRequest request,String mark,String lable){
		 //保存并退出
	    if("saveandtui".equals(mark)){
	    	return "redirect:showClassTypePage";
	    }
	    List<ChapterAndLectureListVo> chapterList=new ArrayList<ChapterAndLectureListVo>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + classType.getId());
		map.put("schoolId", "" + WebUtils.getCurrentSchoolId());
		ClassTypeVo ct = classTypeServiceImpl.findClassTypeDetail(map);
		//视频的章信息
		List<CourseVideoChapter> arr=ct.getVideos();
		for(CourseVideoChapter cou:arr){
			ChapterAndLectureListVo chapVo=new ChapterAndLectureListVo();
			chapVo.setId(cou.getId());
			chapVo.setChapterOrder(cou.getChapterOrder());
			chapVo.setChapterName(cou.getChapterName());
			chapterList.add(chapVo);
		}
		model.addAttribute("chapterList", chapterList);
    	//根据班型查询对应的模块
		List<ClassModule> modulesVoList=classModuleServiceImpl.findModulesByClassTypeId(classType.getId());
		//根据模块查询对应的班号
		List<ClassModuleNoListVo> moduleNoListVo=null;
		for(ClassModule module:modulesVoList){
			if(module.getTeachMethod()!="TEACH_METHOD_VIDEO"&&!"".equals(module.getTeachMethod())){
				moduleNoListVo=classModuleNoServiceImpl.findModuleNoListByClassType(module.getId());
				module.setClassModules(moduleNoListVo);
			}
		}
		model.addAttribute("modulesVoList", modulesVoList);
		model.addAttribute("ct", ct);
		model.addAttribute("lable", lable);
		if("face".equals(lable)||"live".equals(lable)){
			return "classType/addClassTypeFaceOrLiveMessage_4";
		}
		return "classType/addClassTypeTogtherMessage_5";
	}
	
	/**
	 * 保存图片
	 * @param multiPartRquest
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/savePic",method=RequestMethod.POST)
	public String queryPic(MultipartRequest multiPartRquest,HttpServletRequest req){
		MultipartFile multipartFile = multiPartRquest.getFile("imgData");
		String realPath=null;
		try {
			realPath = FileUtil.upload(multipartFile, "temp", WebUtils.getCurrentCompanyId()+"");
		} catch (Exception e) {
			log.error("文件上传失败!",e);
			e.printStackTrace();
		}
		req.getSession().setAttribute("imgData", multipartFile);
		String url="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
		return "{\"url\":\""+url+"\"}";
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, ClassType classType,
			String ralationsIdVal, String type) {
		int currentUserId = WebUtils.getCurrentUserId(request);
		classType.setCreateTime(new Date());
		classType.setUpdateTime(new Date());
		classType.setCreator(currentUserId);
		classType.setUpdator(currentUserId);
		classType.setPublishStatus("CLASS_UNPUBLISHED");
		classType.setCompanyId(WebUtils.getCurrentCompanyId());
		classType.setDelFlag(0);
		classType.setFaceFlag(0);
		classType.setLiveFlag(0);
		classType.setVideoFlag(0);
		classType.setRemoteFlag(0);
		if (classType.getLableType() != null || classType.getLableType() != "") {

			if (classType.getLableType().indexOf(",") > 0) {
				String[] lts = classType.getLableType().split(",");
				for (int i = 0; i < lts.length; i++) {
					if (lts[i].equals("面授")) {
						classType.setFaceFlag(1);
					} else if (lts[i].equals("直播")) {
						classType.setLiveFlag(1);
					} else if (lts[i].equals("视频")) {
						classType.setVideoFlag(1);
					} else if (lts[i].equals("远程")) {
						classType.setRemoteFlag(1);
					}
				}
			} else {
				if (classType.getLableType().equals("面授")) {
					classType.setFaceFlag(1);
				} else if (classType.getLableType().equals("直播")) {
					classType.setLiveFlag(1);
				} else if (classType.getLableType().equals("视频")) {
					classType.setVideoFlag(1);
				} else if (classType.getLableType().equals("远程")) {
					classType.setRemoteFlag(1);
				}
			}
		}

		classTypeServiceImpl.insert(classType);
		
		Commodity commodity = new Commodity();

		commodity.setCerateTime(new Date());
		commodity.setCompanyId(WebUtils.getCurrentCompanyId());
		commodity.setItemOneId(classType.getItemOneId());
		commodity.setItemSecondId(classType.getItemSecondId());
		commodity.setType("COMMODITY_CLASS");
		commodity.setUpdator(WebUtils.getCurrentUserId(request));
		commodity.setSchoolId(WebUtils.getCurrentSchoolId());
		commodity.setLableType(classType.getLableType());
		commodity.setBaseNum(classType.getBaseNum());
		commodity.setStatus(0 + "");
		commodity.setOriginalPrice(Double.parseDouble(classType
				.getOriginalPrice() + ""));
		commodity.setRealPrice(classType.getRealPrice());
		commodity.setName(classType.getName());
		commodity.setUpdateTime(new Date());
		commodity.setCreator(WebUtils.getCurrentUserId(request));
		commodity.setFaceFlag(classType.getFaceFlag());
		commodity.setLiveFlag(classType.getLiveFlag());
		commodity.setVideoFlag(classType.getVideoFlag());
		commodity.setRemoteFlag(classType.getRemoteFlag());
		commodityServiceImpl.insert(commodity);

		CommodityProductRealtion commodityProductRealtion = new CommodityProductRealtion();
		commodityProductRealtion.setProductId(classType.getId());
		commodityProductRealtion.setProductType(1 + "");
		commodityProductRealtion.setComId(commodity.getId());
		commodityProductRealtionServiceImpl.insert(commodityProductRealtion);
		
		
		classTypeServiceImpl.updateClassTypeRalation(classType,ralationsIdVal);
		return classType.getId().toString();
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, ClassType classType,
			String ralationsIdVal, String imagesdata) {

		CommodityProductRealtion commodityProductRealtion = new CommodityProductRealtion();
		commodityProductRealtion.setProductId(classType.getId());

		commodityProductRealtion = commodityProductRealtionServiceImpl
				.findByProduyctId(commodityProductRealtion);

		Commodity commodity = new Commodity();
		commodity.setId(commodityProductRealtion.getComId());
		if(classType.getSubTitle() != null && !classType.getSubTitle().equals("")){
			commodity.setOverview(classType.getSubTitle());
		}
		if (classType.getDetailDesc() != null
				&& !classType.getDetailDesc().equals("")) {
			String desc = classType.getDetailDesc();
			desc = desc.replaceAll("\n", "");
			classType.setDetailDesc(desc);
		}

		if (imagesdata != null && !imagesdata.equals("")) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");// 只有时分秒
			String imgName = sdf.format(date) + ".jpg";
			imagesdata = imagesdata.substring(imagesdata.indexOf(",") + 1);
			imagesdata = imagesdata.replace(" ", "+");
			BASE64Decoder decoder = new BASE64Decoder();
			
			try {
				// Base64解码
				byte[] b = decoder.decodeBuffer(imagesdata);
				// 生成jpeg图片 
				String imgFilePath = propertiesUtil.getImageServiceRealPath()
						+ propertiesUtil.getClassTypePath();// 新生成的图片
				File file = new File(imgFilePath);
				if (!file.exists()) {
					file.mkdirs();
				}
				OutputStream out;
				out = new FileOutputStream(imgFilePath + imgName);
				out.write(b);
				out.flush();
				out.close();
				classType.setCover(propertiesUtil.getClassTypePath() + imgName);
				commodity.setCoverUrl(propertiesUtil.getClassTypePath()
						+ imgName);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (ralationsIdVal != null && !ralationsIdVal.equals("")) {
			if (classType.getTypeCode().equals("CLASS_TYPE_NOMAL")) {
				classTypeModuleRelationServiceImpl
						.deleteByClassTypeId(classType.getId().toString());
			} else if (classType.getTypeCode().equals("CLASS_TYPE_REMOTE")) {
				classTypeRemoteRelationServiceImpl
						.deleteByClassTypeId(classType.getId().toString());
			}
			classTypeServiceImpl.updateClassTypeRalation(classType,
					ralationsIdVal);
		}
		if (classType.getPublishStatus() != null
				&& classType.getPublishStatus().equals("CLASS_ON_SALE")) {
			classType.setPublishTime(new Date());
			commodity.setStatus("1");
		}else{
			commodity.setStatus("0");
		}

		commodityServiceImpl.update(commodity);
		
		int currentUserId = WebUtils.getCurrentUserId(request);
		classType.setUpdateTime(new Date());
		classType.setUpdator(currentUserId);
		classTypeServiceImpl.update(classType);
		
		return classType.getId().toString();
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		classTypeServiceImpl.deleteClassTypeById(id);
		return "redirect:/classType";
	}

	@ResponseBody
	@RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
	public ClassType getJson(Model model, @PathVariable Integer id) {
		return classTypeServiceImpl.findClassTypeById(id);
	}

	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * 
	 * @Description: 分页查询班型信息到主页
	 * @author Kevin
	 * @date 2014年12月29日
	 * @version 1.0
	 * @param model
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/classTypeIndex", method = RequestMethod.GET)
	public String classTypeIndex(Model model, ClassType search) {
		PageFinder<ClassTypeVo> pageFinder = classTypeServiceImpl
				.queryClassTypeByKeysForPage(search);
		model.addAttribute("pageFinder", pageFinder);
		return "classType/classTypeIndex";
	}

	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * 
	 * @Description:异步加载分页班型信息
	 * @author Kevin
	 * @date 2014年12月29日
	 * @version 1.0
	 * @param model
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/classTypeAjaxPage", method = RequestMethod.POST)
	public String classTypeAjaxPage(Model model, ClassType search) {
		PageFinder<ClassTypeVo> pageFinder = classTypeServiceImpl
				.queryClassTypeByKeysForPage(search);
		model.addAttribute("pageFinder", pageFinder);
		return "classType/classTypeAjaxList";
	}

	@RequestMapping(value = "/classTypeUpdate", method = RequestMethod.GET)
	public String classTypeUpdate(Model model, ClassType classType) {
		List<SysConfigSchool> schools = sysConfigSchoolServiceImpl
				.findAllSysConfigSchool(WebUtils.getCurrentSchoolId());
		ClassTypeVo ct = new ClassTypeVo();
		if (classType.getId() != null && !classType.getId().equals("")) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("classId", "" + classType.getId());
			map.put("schoolId", "" + WebUtils.getCurrentSchoolId());
			ct = classTypeServiceImpl.findClassTypeDetail(map);
			if (ct.getTypeCode().equals("CLASS_TYPE_NOMAL")) {
				List<ClassModule> listModule = classModuleServiceImpl
						.findModulesByClassTypeId(ct.getId());
				model.addAttribute("listModule", listModule);
			} else if (ct.getTypeCode().equals("CLASS_TYPE_REMOTE")) {
				List<CourseRemote> listRemote = courseRemoteServiceImpl
						.findRemotesByClassTypeId(ct.getId());
				model.addAttribute("listRemote", listRemote);
			}
			model.addAttribute("forMethod", "update");
		} else {
			model.addAttribute("forMethod", "add");
		}
		model.addAttribute("ct", ct);
		model.addAttribute("schools", schools);
		return "classType/classTypeUpdate";
	}

	@RequestMapping(value = "/addClassTypeDetail", method = RequestMethod.GET)
	public String addClassTypeDetail(Model model, ClassType classType) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + classType.getId());
		map.put("companyId", "" + WebUtils.getCurrentCompanyId());
		ClassTypeVo ct = classTypeServiceImpl.findClassTypeDetail(map);

		Map<String, String> teacherMap = new HashMap<String, String>();
		teacherMap.put("schoolId", WebUtils.getCurrentSchoolId() + "");
		teacherMap.put("itemOneId", ct.getItemOneId() + "");
		teacherMap.put("itemSecondId", ct.getItemSecondId() + "");

		model.addAttribute("ct", ct);
		Subject subject = SecurityUtils.getSubject();
	        if((subject.hasRole("直播老师")&&subject.hasRole("排课老师"))&&!(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营")))
	        {
	        	Users user = WebUtils.getCurrentUser();
	        	SysConfigTeacher teacher2 = new SysConfigTeacher();
  	        	teacher2.setUserId(user.getId());
  	        	teacher2.setCompanyId(WebUtils.getCurrentCompanyId());
  	        	 List<SysConfigTeacher> list = this.sysConfigTeacherServiceImpl.findTeacherByUserId(teacher2);
  	        	 if(list != null && list.size()>0){
  	        		 teacherMap.put("id", list.get(0).getId()+"");
  	        	 }
	        }
		model.addAttribute("teachers",
				sysConfigTeacherServiceImpl.queryForClassType(teacherMap));
		model.addAttribute("imgPath", "http://"+propertiesUtil.getProjectImageUrl()+"/");
		return "classType/addClassTypeDetail";
	}

	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * 
	 * @Description: 展示班型管理详细信息
	 * @author Kevin
	 * @date 2014年12月31日
	 * @version 1.0
	 * @param model
	 * @param classType
	 * @return
	 */
	@RequestMapping(value = "/showClassTypeDetail", method = RequestMethod.POST)
	public String showClassTypeDetail(Model model, ClassType classType) {
		List<ChapterAndLectureListVo> chapterList=new ArrayList<ChapterAndLectureListVo>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + classType.getId());
		map.put("schoolId", "" + WebUtils.getCurrentSchoolId());
		ClassTypeVo ct = classTypeServiceImpl.findClassTypeDetail(map);
		model.addAttribute("type",ct.getVideoFlag());
		CommodityProductRealtion cpr = commodityProductRealtionServiceImpl.findByClassTypeId(ct.getId()+"");
		model.addAttribute("cpr",cpr);
		List<ClassModuleVo> listModule=classModuleServiceImpl.queryAllModules(ct.getId().toString());
		Company company = companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
		model.addAttribute("company",company);
		//根据校区id查询校区名称
		for(ClassModuleVo classModuleVo:listModule){
			SysConfigCampus campus=sysConfigCampusServiceImpl.findSysConfigCampusById(classModuleVo.getCampusId());
			if(campus!=null){
				classModuleVo.setCampusName(campus.getCampusName());
			}
		}
		//视频的章和节信息
		List<CourseVideoChapter> arr=ct.getVideos();
		for(CourseVideoChapter cou:arr){
			ChapterAndLectureListVo chapVo=new ChapterAndLectureListVo();
			chapVo.setId(cou.getId());
			chapVo.setChapterOrder(cou.getChapterOrder());
			chapVo.setChapterName(cou.getChapterName());
			chapterList.add(chapVo);
		}
		for(ChapterAndLectureListVo cp:chapterList){
			List<CourseVideoLecture> lectures=courseVideoLectureMapper.findCourseVideoLectureByChapterId(cp.getId());
			cp.setLectures(lectures);
		}
		
		CompanyFunctionSet search=new CompanyFunctionSet();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setFunctionCode("COMPANY_COURSE_SET");
		search.setStatus("1");
		List<CompanyFunctionSet> sets=companyFunctionSetServiceImpl.findCompanyFunctions(search);
		if(sets!=null && sets.size()>0){
			if(null!=sets.get(0).getColumn1()&&!"".equals(sets.get(0).getColumn1())){
				model.addAttribute("chapterName", sets.get(0).getColumn1());
			}else{
				model.addAttribute("chapterName", "章");
			}
			if(null!=sets.get(0).getColumn2()&&!"".equals(sets.get(0).getColumn2())){
				model.addAttribute("lectureName", sets.get(0).getColumn2());
			}else{
				model.addAttribute("lectureName", "节");
			}
		}else{
			model.addAttribute("chapterName", "章");
			model.addAttribute("lectureName", "节");
		}
		model.addAttribute("chapterList", chapterList);
		model.addAttribute("listModule", listModule);
		model.addAttribute("ct", ct);
		return "classType/showClassTypeDetail";
	}

	@RequestMapping(value = "/addCourseRamoteToClassType", method = RequestMethod.GET)
	public String addCourseRamoteToClassType(Model model, ClassType classType) {
		// classTypeServiceImpl.insert(classType);
		return "classType/addCourseRamoteToClassType";
	}

	@ResponseBody
	@RequestMapping(value = "/detail/{classId}", method = RequestMethod.POST)
	public ClassTypeVo query(@PathVariable Integer classId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + classId);
		map.put("schoolId", "" + WebUtils.getCurrentSchoolId());
		ClassTypeVo ct=classTypeServiceImpl.findClassTypeDetail(map);
		return ct;

	}

	@ResponseBody
	@RequestMapping(value = "/findList", method = RequestMethod.POST)
	public List<ClassType> findList(ClassType classType) {
		List<ClassType> al = new ArrayList<ClassType>();
		classType.setCompanyId(WebUtils.getCurrentCompanyId());
		classType.setCreateSchoolId(WebUtils.getCurrentSchoolId());
		if (EntityUtil.isNotBlank(classType)) {
			al = classTypeServiceImpl.findClassTypeList(classType);
		}
		return al;
	}

	@ResponseBody
	@RequestMapping(value = "/findList2", method = RequestMethod.POST)
	public List<ClassType> findList2(ClassType classType) {
		List<ClassType> al = new ArrayList<ClassType>();
		classType.setCompanyId(WebUtils.getCurrentCompanyId());
		if (EntityUtil.isNotBlank(classType)) {
			al = classTypeServiceImpl.findClassTypeList2(classType);
		}
		return al;
	}
	
	@ResponseBody
	@RequestMapping(value = "/findList3", method = RequestMethod.POST)
	public List<ClassType> findList3(ClassType classType) {
		List<ClassType> al = new ArrayList<ClassType>();
		classType.setCompanyId(WebUtils.getCurrentCompanyId());
		if (EntityUtil.isNotBlank(classType)) {
			al = classTypeServiceImpl.findClassTypeList(classType);
		}
		return al;
	}

	@ResponseBody
	@RequestMapping(value="/queryList",method=RequestMethod.POST)
	public List<ClassType> queryClassTypeByCondition(ClassType classType){
		classType.setCompanyId(WebUtils.getCurrentCompanyId());
		return classTypeServiceImpl.findClassTypeByCondition(classType);
	}
	
	@ResponseBody
	@RequestMapping(value = "/editorUploadImg", method = RequestMethod.POST)
	public String editorUploadImg(HttpServletRequest request,HttpServletResponse response)
			throws IOException {
		PrintWriter out=response.getWriter();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile = multipartRequest.getFile("upload");	
		if(imgFile == null){
			return null;
		}
		log.info("文件开始上传：");
		String realPath=null;
		try {
			realPath = FileUtil.upload(imgFile, "editor", WebUtils.getCurrentCompanyId()+"");
		} catch (Exception e) {
			log.error("文件上传失败!",e);
			e.printStackTrace();
		}
		log.info("文件上传后返回的路径："+realPath);
		
		//图片回显路径
		String callUrl="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
		// 将上传的图片的url返回给ckeditor
        String callback = request.getParameter("CKEditorFuncNum");
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction("
                + callback + ",'" + callUrl + "',''" + ")");
        out.println("</script>");
		return null;
	}

	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 商品下架
	 * @author zhang.zx
	 * @date 2015年5月5日 下午2:00:14
	 * @modifier
	 * @modify-date 2015年5月5日 下午2:00:14
	 * @version 1.0
	 * @param classtype
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/StopSale",method=RequestMethod.POST)
	public ClassType commodityStopOnsale(ClassType classType,HttpServletRequest request){
		CommodityProductRealtion commodityProductRealtion = new CommodityProductRealtion();
		commodityProductRealtion.setProductId(classType.getId());

		commodityProductRealtion = commodityProductRealtionServiceImpl
				.findByProduyctId(commodityProductRealtion);
		Commodity commodity = new Commodity();
		commodity.setId(commodityProductRealtion.getComId());
		commodity.setUpdateTime(new Date());
		commodity.setUpdator(WebUtils.getCurrentUserId(request));
		commodity.setStatus("0");
		
		commodityServiceImpl.update(commodity);
		
		classType.setIsSale(0);
		classType.setUpdateTime(new Date());
		classType.setUpdator(WebUtils.getCurrentUserId(request));
		classTypeServiceImpl.update(classType);
		return classTypeServiceImpl.findClassTypeById(classType.getId());
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 商品上架
	 * @author zhang.zx
	 * @date 2015年5月5日 下午2:00:14
	 * @modifier
	 * @modify-date 2015年5月5日 下午2:00:14
	 * @version 1.0
	 * @param classtype
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/onSale",method=RequestMethod.POST)
	public void commodityOnsale(ClassType classType,HttpServletRequest request,Model model,HttpServletResponse response) throws IOException{
		//根据班型查询对应的模块
		List<ClassModule> modulesVoList=classModuleServiceImpl.findModulesByClassTypeId(classType.getId());
		//根据模块查询对应的班号
		List<ClassModuleNoListVo> moduleNoListVo=null;
		for(ClassModule module:modulesVoList){
			moduleNoListVo=classModuleNoServiceImpl.findModuleNoListByClassType(module.getId());
			module.setClassModules(moduleNoListVo);
		}
		
		CommodityProductRealtion commodityProductRealtion = new CommodityProductRealtion();
		commodityProductRealtion.setProductId(classType.getId());

		commodityProductRealtion = commodityProductRealtionServiceImpl
				.findByProduyctId(commodityProductRealtion);
		if(commodityProductRealtion!=null){
			Commodity commodity = new Commodity();
			commodity.setId(commodityProductRealtion.getComId());
			commodity.setUpdateTime(new Date());
			commodity.setUpdator(WebUtils.getCurrentUserId(request));
			commodity.setStatus("1");
			
			commodityServiceImpl.update(commodity);
		}
		
		classType.setIsSale(1);
		classType.setPublishTime(new Date());
		classType.setUpdateTime(new Date());
		classType.setUpdator(WebUtils.getCurrentUserId(request));
		classTypeServiceImpl.update(classType);
		
		ClassType c=classTypeServiceImpl.findClassTypeById(classType.getId());
		model.addAttribute("itemOneId", c.getItemOneId());
		model.addAttribute("itemSecondId", c.getItemSecondId());
		
		List<SysConfigItem> firstItems = sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
		model.addAttribute("firstItems", firstItems);
		response.sendRedirect("showClassTypePage");
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 保存切图
	 * @author chopin
	 * @date 2015年5月5日 下午2:00:14
	 * @modifier
	 * @modify-date 2015年5月5日 下午2:00:14
	 * @version 1.0
	 * @param classtype
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveCutPic")
	public CompanyPicsVo saveCutPic(HttpServletRequest request){
		log.info("初始化截图开始：");
		int itemOneid=Integer.parseInt(request.getParameter("itemOneid"));
		Resource resource = new ClassPathResource("config.properties");
		Properties props=null;
		try{
			props=PropertiesLoaderUtils.loadProperties(resource);
		}catch(Exception e){
			log.error(e,e);
		}
		String path=request.getParameter("path");
		String fileName=path.substring(path.lastIndexOf("/")+1);
		String tempPath=props.getProperty("server.imageupload.tempPath")+"/source/"+fileName;
		String target=props.getProperty("server.imageupload.tempPath")+"/target/"+fileName;
		String header="http://"+props.getProperty("yunduoketang.oss.imagedomain")+"/";
		
		path=path.replace(header, "");
		System.out.println("oss临时文件路径["+path+"]=====本地磁盘临时文件路径["+tempPath+"]======切图后临时文件路径["+target+"]");
		FileUtil.download("temp", path,tempPath);
		//选中尺寸
		double x=Double.parseDouble(request.getParameter("x"));
		double y=Double.parseDouble(request.getParameter("y"));
		double w=Double.parseDouble(request.getParameter("w"));
		double h=Double.parseDouble(request.getParameter("h"));
		BufferedImage img =null;
		try{
			 img = ImageIO.read(new File(tempPath));
		}catch(Exception e){
			log.error("读取图片失败:"+e,e);
		}
		//原图尺寸
		double realW=img.getWidth();
		double realH=img.getHeight();
		//示例图尺寸
		double slW=0;
		double slH=0;
		if(realW/realH>516.00/282.00){
			//过宽
			slH=516 * realH/realW;
			slW=516;
		}else{
			//过高
			slH=282;
			slW=282 * realW/realH;
		}
		//原图所选中位置和区域
		
		int xx=(new   Double(x*realW/slW)).intValue();	
		int yy=(new   Double(y*realH/slH)).intValue();
		int ww=(new   Double(w*realW/slW)).intValue();
		int hh=(new   Double(h*realH/slH)).intValue();
		System.out.println("选中区域:["+x+","+y+","+w+","+h+"]----原图选中区域:["+xx+","+yy+","+ww+","+hh+"]");
		//在原图中切图
		String cutImgPath=ImageUtils.cutImage(tempPath,target,xx,yy,ww,hh);
		//切好的图缩放到规定比例
		ImageUtils.resize(target,target,446);
		log.info("截图完成：");
		log.info("上传图片开始：");
		String realPath=null;
		try {
			realPath=FileUtil.upload(cutImgPath,"course", WebUtils.getCurrentCompanyId()+"");
		} catch (Exception e) {
			log.error("上传文件失败",e);
			e.printStackTrace();
		}
		log.info("上传图片后路径："+realPath);
		FileUtil.deleteFile(target);
		FileUtil.deleteFile(cutImgPath);
		CompanyPics pic=new CompanyPics();
		pic.setItemOneId(itemOneid);
		pic.setCompanyId(WebUtils.getCurrentCompanyId());
		pic.setPicName("");
		pic.setPicType("classtype");
		pic.setPicOriginalUrl(realPath);
		//存库
		companyPicsServiceImpl.insert(pic);
		
		String picUrl="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
		log.info("图片回显路径==============="+picUrl);
		CompanyPicsVo pics=new CompanyPicsVo();
		pics.setPicOriginalUrl(picUrl);
		pics.setRealPath(realPath);
		
		return pics;
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 保存切图
	 * @author chopin
	 * @date 2015年5月5日 下午2:00:14
	 * @modifier
	 * @modify-date 2015年5月5日 下午2:00:14
	 * @version 1.0
	 * @param classtype
	 * @return
	 */
	@RequestMapping(value="/showVideoAddBase",method=RequestMethod.POST)
	public String showVideoAddBase(){
		
		return "";
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 根据ID查找班型
	 * @author chopin
	 * @date 2015年5月5日 下午2:00:14
	 * @modifier
	 * @modify-date 2015年5月5日 下午2:00:14
	 * @version 1.0
	 * @param classtype
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findClassType/{id}",method=RequestMethod.POST)
	public ClassType findClassType(@PathVariable Integer id){
		 ClassType clazz=classTypeServiceImpl.findClassTypeById(id);
		 if(clazz==null){
			 return new ClassType();
		 }else{
			 return clazz;
		 }
		
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 根据一二级项目查找班型
	 * @author chopin
	 * @date 2015年5月5日 下午2:00:14
	 * @modifier
	 * @modify-date 2015年5月5日 下午2:00:14
	 * @version 1.0
	 * @param classtype
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findByItem/{itemOneId}/{itemSecondId}",method=RequestMethod.POST)
	public List<ClassType> findClassType(@PathVariable Integer itemOneId,@PathVariable Integer itemSecondId){
		return classTypeServiceImpl.findByItem(WebUtils.getCurrentCompanyId(),WebUtils.getCurrentSchoolId(),itemOneId, itemSecondId);
	}
	
	
	/**
	 * 后台接收Date转换
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	//改变班型收藏状态
	@ResponseBody
	@RequestMapping(value="/changClassTypeCollect",method=RequestMethod.POST)
	public ClassType changCollectClassTypeStatus(ClassType classType,HttpServletRequest request){
		classTypeServiceImpl.changClassTypeCollectStatus(classType);
		 CommodityProductRealtion comm=commodityProductRealtionServiceImpl.findByClassTypeId(classType.getId()+"");
	     Integer cId=comm.getComId();
	     Commodity c=new Commodity();
	     c.setId(cId);
	     c.setRecommendFlag(classType.getRecommendFlag());
	    commodityServiceImpl.update(c);
		return classTypeServiceImpl.findClassTypeById(classType.getId());
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 条件查询模块信息
	 * @author zhang.zx
	 * @date 2015年5月28日 下午5:19:37
	 * @modifier
	 * @modify-date 2015年5月28日 下午5:19:37
	 * @version 1.0
	 * @param ct
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryModuleByCon",method=RequestMethod.POST)
	public List<ClassModuleVo> queryModuleByClassType(ClassModule classModule1){
		classModule1.setCompanyId(WebUtils.getCurrentCompanyId());
		classModule1.setSchoolId(WebUtils.getCurrentSchoolId());
		return classModuleServiceImpl.findAllClassModule(classModule1);	
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 校验班型名称
	 * @author zhang.zx
	 * @date 2015年5月29日 上午10:15:41
	 * @modifier
	 * @modify-date 2015年5月29日 上午10:15:41
	 * @version 1.0
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkClassTypeName",method=RequestMethod.POST)
	public boolean checkedClassTypeName(String name){
		ClassType classType=new ClassType();
		classType.setCompanyId(WebUtils.getCurrentCompanyId());
		classType.setCreateSchoolId(WebUtils.getCurrentSchoolId());
		classType.setName(name);
		List<ClassType> arr=classTypeServiceImpl.findClassTypeExits(classType);
		if(arr!=null&&arr.size()>0){
			return false;
		}
		return true;
	}
	
	@ResponseBody
	@RequestMapping(value="/checkUpdateClassTypeName",method=RequestMethod.POST)
	public boolean checkedUpdateClassTypeName(String name,Integer id){
		ClassType c=classTypeServiceImpl.findClassTypeById(id);
		if(name.equals(c.getName())){
			return true;
		}else{
			ClassType classType=new ClassType();
			classType.setCompanyId(WebUtils.getCurrentCompanyId());
			classType.setCreateSchoolId(WebUtils.getCurrentSchoolId());
			classType.setName(name);
			List<ClassType> arr=classTypeServiceImpl.findClassTypeExits(classType);
			if(arr!=null&&arr.size()>0){
				return false;
			}
		}
		return true;
	}
	
	//排课表相关信息查询
	@RequestMapping(value = "/{id}/showClassCourseDetail", method = RequestMethod.GET)
	public String showClassCourseDetail(@PathVariable Integer id,Model model) {
		ClassType ct= classTypeServiceImpl.findClassTypeById(id);
		 SysConfigItem item=sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemOneId());
	    SysConfigItem item2=sysConfigItemServiceImpl.findSysConfigItemById(ct.getItemSecondId());
	    if(item!=null){
	    	ct.setItemOneName(item.getItemName());
	    }
	    if(item2!=null){
	    	ct.setItemSecondName(item2.getItemName());
	    }
		model.addAttribute("ct", ct);
    	//根据班型查询对应的模块
		List<ClassModule> modulesVoList=classModuleServiceImpl.findModulesByClassTypeId(id);
		//根据模块查询对应的班号
		List<ClassModuleNoListVo> moduleNoListVo=null;
		for(ClassModule module:modulesVoList){
			if(module.getTeachMethod()!="TEACH_METHOD_VIDEO"&&!"".equals(module.getTeachMethod())){
				moduleNoListVo=classModuleNoServiceImpl.findModuleNoListByClassType(module.getId());
				module.setClassModules(moduleNoListVo);
			}
		}

		model.addAttribute("modulesVoList", modulesVoList);
		
		List<ChapterAndLectureListVo> chapterList=new ArrayList<ChapterAndLectureListVo>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + ct.getId());
		map.put("schoolId", "" + WebUtils.getCurrentSchoolId());
		ClassTypeVo cts = classTypeServiceImpl.findClassTypeDetail(map);
		//视频的章信息
		List<CourseVideoChapter> arr=cts.getVideos();
		for(CourseVideoChapter cou:arr){
			ChapterAndLectureListVo chapVo=new ChapterAndLectureListVo();
			chapVo.setId(cou.getId());
			chapVo.setChapterOrder(cou.getChapterOrder());
			chapVo.setChapterName(cou.getChapterName());
			chapterList.add(chapVo);
		}
		model.addAttribute("chapterList", chapterList);
		return "classType/ClassTypeCourseDetail";
	}
	
	/**
	 *添加在售模块班号
	 *zhang.zx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertClassNoOnsale",method=RequestMethod.POST)
	public Integer insertClassModuleNoOnsale(ClassmoduleNoOnsaleVo c){
		classModuleServiceImpl.insertClassModuleOnsale(c);
		return c.getId();
	}
	
	@ResponseBody
	@RequestMapping(value="/insertClassNoSale")
	public String insertClassNoSale(HttpServletRequest request){
		List<ClassmoduleNoOnsaleVo> classNosale=JSONObject.parseArray(request.getParameter("classNosale"),com.yuxin.wx.vo.classes.ClassmoduleNoOnsaleVo.class);
		//校验重复
		for(ClassmoduleNoOnsaleVo cmos:classNosale){
			System.out.println(cmos.getClasstypeId()+"--"+cmos.getModuleId()+"--"+cmos.getModuleNoId()+"--"+cmos.getDefaultFlag());
		}
		classModuleServiceImpl.insertClassModuleOnsale(classNosale);
		return "success";
	}
	
	/**
	 *删除在售模块班号
	 *zhang.zx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteClassNoOnsale",method=RequestMethod.POST)
	public String deleteClassModuleNoOnsale(ClassmoduleNoOnsaleVo c){
		classModuleServiceImpl.deleteClassModuleOnsaleById(c);
		return "success";
	}
	
	//删除班型
	@RequestMapping(value="/deleteClassType/{id}",method=RequestMethod.GET)
	public String deleteClassType(@PathVariable Integer id,Model model,HttpServletRequest
			 request){
		ClassType classType=new ClassType();
		classType.setId(id);
		classType.setPublishStatus("CLASS_STOP_SALE");
		classType.setDelFlag(1);
		classTypeServiceImpl.update(classType);
		 CommodityProductRealtion comm=commodityProductRealtionServiceImpl.findByClassTypeId(classType.getId()+"");
	     Integer cId=comm.getComId();
	     Commodity c=new Commodity();
	     c.setId(cId);
	     c.setStatus(0+"");
	    commodityServiceImpl.update(c);
	    try {
			CompanyLiveConfig clc = companyLiveConfigServiceImpl
					.findByCompanyId(WebUtils.getCurrentCompanyId());
			String openId = null;
			String openToken = null;
			boolean clct = false;
			if(clc != null && clc.getLiveType().equals(3)){
				openId = clc.getLoginName();
				openToken = clc.getPassword();
			}
			if(clc != null && clc.getLiveType().equals(4)){
				clct = true;
			}
			String res = "";
	    	//根据班型查询模块
	    	List<ClassModule> listModule=classModuleServiceImpl.findModulesByClassTypeId(classType.getId());
	    	for(ClassModule moudules:listModule){
	    		//根据模块查询班号
				List<ClassModuleNo> listNo=classModuleNoServiceImpl.queryClassModuleNoById(moudules.getId());
				for(ClassModuleNo nos:listNo){
					List<ClassModuleLesson>  listLesson=classModuleLessonServiceImpl.findClassModuleLessonByModuleNoId(nos.getId());
					for(ClassModuleLesson lessons:listLesson){
						classModuleLessonServiceImpl.deleteClassModuleLessonById(lessons.getId());
						if("ht".equals(lessons.getLiveCompanyType())){
							Map<Object, Object> param = new HashMap<Object,Object>();
							param.put("course_id", lessons.getLiveroomIdGh());
							res = TalkfunUtils.getRsult(param, "course.delete",openId,openToken);
							log.debug("删除课次，"+res);
						}
						if("cc".equals(lessons.getLiveCompanyType())){
							Map<String, String> map = new HashMap<String,String>();
							map.put("roomid", lessons.getLiveroomIdGh());
							map.put("userid", (clct ? clc.getLoginName()
									: CCLiveInterface.USER_ID));

							long timestimp = System.currentTimeMillis();
							String thqs = APIServiceFunction.createHashedQueryString(map,timestimp, 
									clct ? clc.getPassword() : CCLiveInterface.API_KEY);
							String url = CCLiveInterface.CLOSE + thqs;
							try {
								res = HttpPostRequest.get(url);
								log.debug("删除cc直播 信息," + res);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								log.debug("cc直播间关闭失败,"+e.getMessage());
							}
						}
					}
				}
	    	}
		} catch (Exception e) {
			log.info("删除课次失败",e);
			e.printStackTrace();
		}
	    ClassType comms=classTypeServiceImpl.findClassTypeById(classType.getId());
	    if(null!=comms){
	    	model.addAttribute("itemOneId", comms.getItemOneId());
			model.addAttribute("itemSecondId", comms.getItemSecondId());
	    }
		List<SysConfigItem> firstItems = sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
		model.addAttribute("firstItems", firstItems);
		return "classType/classIndex";
		//return "redirect:../showClassTypePage";
	}
	
	//下架后班型上架
	@ResponseBody
	@RequestMapping(value="/classTypeonSale",method=RequestMethod.POST)
	public ClassType classTypeOnsale(ClassType classType,HttpServletRequest request,Model model){
		
		CommodityProductRealtion commodityProductRealtion = new CommodityProductRealtion();
		commodityProductRealtion.setProductId(classType.getId());

		commodityProductRealtion = commodityProductRealtionServiceImpl
				.findByProduyctId(commodityProductRealtion);
		if(commodityProductRealtion!=null){
			Commodity commodity = new Commodity();
			commodity.setId(commodityProductRealtion.getComId());
			commodity.setUpdateTime(new Date());
			commodity.setUpdator(WebUtils.getCurrentUserId(request));
			commodity.setStatus("1");
			commodityServiceImpl.update(commodity);
		}
		
		classType.setIsSale(1);
		classType.setUpdateTime(new Date());
		classType.setPublishTime(new Date());
		classType.setUpdator(WebUtils.getCurrentUserId(request));
		classTypeServiceImpl.update(classType);
		return classTypeServiceImpl.findClassTypeById(classType.getId());
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 条件查询课程并分页
	 * @author zhang.zx
	 * @date 2016年3月24日 上午11:00:51
	 * @modifier
	 * @modify-date 2016年3月24日 上午11:00:51
	 * @version 1.0
	 * @param search
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryClassDataList",method=RequestMethod.POST)
	public PageFinder<ClassTypeVo> queryClassDataList(ClassType search,String searchName,Integer classPackageId){
		StringBuffer buffer=new StringBuffer();
		Integer companyId=WebUtils.getCurrentCompanyId();
		Integer schoolId=WebUtils.getCurrentSchoolId();
		//查询老师
		if(null!=searchName && !"".equals(searchName)){
			Map<String, String> map=new HashMap<String, String>();
			map.put("companyId", companyId+"");
			map.put("schoolId", schoolId+"");
			map.put("teacherName", searchName);
			List<SysConfigTeacher> arr=sysConfigTeacherServiceImpl.findSysConfigTeacherByName(map);
			if(null!=arr){
				for(SysConfigTeacher t:arr){
					if(null!=t){
						buffer.append(t.getId());
						buffer.append(",");
					}
				}
			}
			
		}
		search.setPageSize(3);
		search.setCreateSchoolId(schoolId);
		search.setCompanyId(companyId);
		search.setName(searchName);
		if(null!=buffer && buffer.length()>0){
			search.setTeachersId(buffer.substring(0, buffer.length()-1).toString());
		}
		String url="http://"+propertiesUtil.getProjectImageUrl()+"/";
		PageFinder<ClassTypeVo> pageFinder=classTypeServiceImpl.findClassTypesByPage(search);
		List<ClassTypeVo> data=pageFinder.getData();
		
		ClassPackageConditionVo con=new ClassPackageConditionVo();
		con.setCompanyId(WebUtils.getCurrentCompanyId());
		con.setSchoolId(WebUtils.getCurrentSchoolId());
		con.setId(classPackageId);
		List<ClassTypeVo> cpClass=classTypeServiceImpl.queryIsExistByClassPackage(con);
		
		for(ClassTypeVo ct:data){
			if(null!=ct && null!=ct.getCover()){
				ct.setCover(url+ct.getCover());
			}
			if(null!=cpClass && cpClass.size()>0){
				for(ClassTypeVo v1:cpClass){
					if(null!=ct && null!=v1){
						if(ct.getId().equals(v1.getId())){
							ct.setClassPackageCourseId(1);
						}
					}
				}
			}
		}
		return pageFinder;
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 查询在售班号个数
	 * @author zhang.zx
	 * @date 2016年5月26日 下午9:41:28
	 * @modifier
	 * @modify-date 2016年5月26日 下午9:41:28
	 * @version 1.0
	 * @param classTypeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryHasOnSaleNo/{id}")
	public String queryOnsaleList(@PathVariable Integer id){
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + id);
		ClassTypeVo classType=classTypeServiceImpl.findClassTypeDetail(map);
		if(null!=classType && classType.getLiveFlag()==1){
			List<ClassmoduleNoOnsaleVo> arr=classModuleServiceImpl.queryModuleNoByClasstypeId(id);
			if(null!=arr && arr.size()>0){
				return "success";
			}
			return "error";
		}else{
			return "success";
		}
	}
	/**
	 * 
	 * @Description:会员课程
	 * @author: dongshuai
	 * @date: 2016年5月25日
	 * @param model
	 * @param request
	 * @return
	 *
	 */
	@RequestMapping("/memberClass")
	public String memberClass(HttpServletRequest request,ModelMap model){
		return "classes/myMemberClass";
	}
	
	/**
	 * 
	 * @Description: 查询会员等级列表
	 * @author: dongshuai
	 * @date: 2016年5月25日
	 * @param request
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/memberLevelsList",method=RequestMethod.POST)
	public List<MemberLevelVo>  memberLevelsList(HttpServletRequest request){
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("companyId", WebUtils.getCurrentCompanyId());
		List<MemberLevelVo> list = companyMemberConfigServiceImpl.findMemberLevelByCompanyId(param);
		return list;
	}
	/**
	 * 
	 * @Description: 查询会员等级对应课程列表
	 * @author: dongshuai
	 * @date: 2016年5月25日
	 * @param request
	 * @return
	 *
	 */
	@RequestMapping(value="/searchMemberClass/{memberId}")
	public String  searchMemberClass(HttpServletRequest request,@PathVariable String memberId,ModelMap model,ClassTypeMemberDiscountVo search,ClassType classType){
		//查询已添加的课程
		if(null!=memberId&&!"".equals(memberId)&&!"null".equals(memberId)){
			search.setCompanyId(WebUtils.getCurrentCompanyId());
			search.setMemberId(Integer.parseInt(memberId));
			List<MemberLevelAndClassTypeVo> list = classTypeMemberDiscountServiceImpl.queryMemberLevelAndClassTypeVoListByMemberId(search);
			model.put("list", list);
			CompanyMemberLevelConfig cmlc = companyMemberLevelConfigServiceImpl.findCompanyMemberLevelConfigById(Integer.parseInt(memberId));
			model.put("cmlc", cmlc);
		}
		return "classes/searchMemberClass";
	}
	
	/**
	 * 
	 * @Description: 查询会员等级对应课程列表2
	 * @author: dongshuai
	 * @date: 2016年5月27日
	 * @param search
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/queryMemberLevelAndClassType",method=RequestMethod.POST)
	public PageFinder<MemberLevelAndClassTypeVo> queryMemberLevelAndClassType(String memberId,String page){
		ClassTypeMemberDiscount search=new ClassTypeMemberDiscount();
		search.setPage(Integer.parseInt(page));
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setMemberId(Integer.parseInt(memberId));
		search.setPageSize(3);
		PageFinder<MemberLevelAndClassTypeVo> pageFinder=classTypeMemberDiscountServiceImpl.queryMemberLevelAndClassTypeVoListByMemberIdPage(search);
		String url="http://"+propertiesUtil.getProjectImageUrl()+"/";
		List<MemberLevelAndClassTypeVo> data = pageFinder.getData();
		if(null!=data && data.size()>0){
			for(MemberLevelAndClassTypeVo ct:data){
				if(null!=ct){
					if(null!=ct.getCover()){
						ct.setCover(url+ct.getCover());
					}
				}
			}
		}
		return pageFinder;
	}
	
	/**
	 * 
	 * @Description: 查询可以添加的会员课程
	 * @author: dongshuai
	 * @date: 2016年5月25日
	 * @param search
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/queryClassTypeNotMember",method=RequestMethod.POST)
	public PageFinder<ClassTypeVo> queryClassTypeNotMember(MemberLevelAndClassTypeVo mlactVo){
		mlactVo.setCompanyId(WebUtils.getCurrentCompanyId());
		mlactVo.setPageSize(3);
		PageFinder<ClassTypeVo> pageFinder=classTypeServiceImpl.queryClassTypeForMember(mlactVo);
		String url="http://"+propertiesUtil.getProjectImageUrl()+"/";
		List<ClassTypeVo> data = pageFinder.getData();
		if(null!=data && data.size()>0){
			for(ClassTypeVo ct:data){
				if(null!=ct){
					if(null!=ct.getCover()){
						ct.setCover(url+ct.getCover());
					}
				}
			}
		}
		return pageFinder;
		
	} 

	/**
	 * 
	 * @Description: 添加会员课程
	 * @author: dongshuai
	 * @date: 2016年5月26日
	 * @param ids
	 * @param memberId
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/saveClassTypeForMember",method=RequestMethod.POST)
	public String saveClassTypeForMember(String ids,String memberId){
		int companyId=WebUtils.getCurrentCompanyId();
		boolean b=classTypeMemberDiscountServiceImpl.saveClassType(memberId,ids,companyId);
		return b+"";
	}
	/**
	 * 
	 * @Description: 删除会员课程
	 * @author: dongshuai
	 * @date: 2016年5月26日
	 * @param id
	 * @param memberId
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/delClassTypeForMember",method=RequestMethod.POST)
	public String delClassTypeForMember(String id,String memberId){
		ClassTypeMemberDiscount ctmd = classTypeMemberDiscountServiceImpl.findClassTypeMemberDiscountById(Integer.parseInt(id));
		Integer classTypeId = ctmd.getClassTypeId();
		classTypeMemberDiscountServiceImpl.deleteClassTypeMemberDiscountById(Integer.parseInt(id));
		int count = classTypeMemberDiscountServiceImpl.selectClassTypeCount(classTypeId);
		if(count==0){//同时不存在其他会员课程时移除会员课程
			CommodityProductRealtion cpr=commodityProductRealtionServiceImpl.findByClassTypeId(classTypeId.toString());
			if(null!=cpr && null!=cpr.getComId()){
				Commodity commodity=commodityServiceImpl.findCommodityById(cpr.getComId());
				if(null!=commodity){
					commodity.setMemberFlag(0);
					commodityServiceImpl.update(commodity);
				}
			}
			ClassType classType = classTypeServiceImpl.findClassTypeById(classTypeId);
			classType.setMemberFlag(0);
			classTypeServiceImpl.update(classType);
		}
		return "true";
	}
	
	/**
	 * 
	 * @Description: 根据课程Id得到会员信息以及课程信息
	 * @author: dongshuai
	 * @date: 2016年5月27日
	 * @param classTypeId
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/queryMemberLevelAndClassTypeVoByClassId",method=RequestMethod.POST)
	public MemberLevelAndClassTypeVo queryMemberLevelAndClassTypeVoByClassId(String classTypeId,String memberId){
		ClassTypeMemberDiscount ctmd=new ClassTypeMemberDiscount();
		ctmd.setCompanyId(WebUtils.getCurrentCompanyId());
		ctmd.setClassTypeId(Integer.parseInt(classTypeId));
		ctmd.setMemberId(Integer.parseInt(memberId));
		MemberLevelAndClassTypeVo mlactv=classTypeMemberDiscountServiceImpl.queryMemberLevelAndClassTypeVoByClassId(ctmd);
		return mlactv;
	}
	
	/**
	 * 
	 * @Description:修改积分折扣
	 * @author: dongshuai
	 * @date: 2016年5月27日
	 * @param classTypeId
	 * @param discount
	 * @param memberId
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/changeDiscount",method=RequestMethod.POST)
	public String changeDiscount(String classTypeId,String discount,String memberId){
		ClassTypeMemberDiscount ctmd=new ClassTypeMemberDiscount();
		ctmd.setClassTypeId(Integer.parseInt(classTypeId));
		ctmd.setCompanyId(WebUtils.getCurrentCompanyId());
		ctmd.setMemberId(Integer.parseInt(memberId));
		ctmd=classTypeMemberDiscountServiceImpl.findDiscountByMemberIdAndClassTypeId(ctmd);
		if(null!=ctmd&&!"".equals(ctmd)){
			ctmd.setMemberDiscount(Double.parseDouble(discount));
			if(Double.parseDouble(discount)==0){
				ctmd.setDiscountType(0);
			}else{
				ctmd.setDiscountType(1);
			}
			classTypeMemberDiscountServiceImpl.update(ctmd);
		}else {
			return "false";
		}
		return "true";
	}
	
	@ResponseBody
	@RequestMapping(value="/checkIsFuShengPaikeOrCommonPaike",method=RequestMethod.GET)
	public String checkIsFuShengPaikeOrCommonPaike(HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		if( subject.hasRole("排课老师")&& !(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营"))){
			return "yes";
		}
		return "no";
	}
	

}
