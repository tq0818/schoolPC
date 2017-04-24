package com.yuxin.wx.controller.classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassTypeModuleRelationService;
import com.yuxin.wx.api.classes.IClassTypeRemoteRelationService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyPicsService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.course.ICoursePotocolBindHistoryService;
import com.yuxin.wx.api.course.ICourseRemoteService;
import com.yuxin.wx.api.course.ICourseVideoChapterService;
import com.yuxin.wx.api.system.ISysConfigCampusService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigItemTagService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.course.mapper.CourseVideoLectureMapper;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeModuleRelation;
import com.yuxin.wx.model.classes.ClassTypeRemoteRelation;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.course.CoursePotocolBindHistory;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemTag;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.user.Users;
//import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassModuleNoListVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.course.ChapterAndLectureListVo;

/**
 * 课程编辑
 * @author DELL.COM
 *
 */
@Controller
@RequestMapping("/editClass")
public class editClassTypeController {

	private Log log=LogFactory.getLog("log");
	
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
	private ICoursePotocolBindHistoryService coursePotocolBindHistoryServiceImpl;
	
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
		model.addAttribute("ct", classType);
 		model.addAttribute("type", "update");
		model.addAttribute("lable", lable);
		if(lable.contains(",")){
 			String[] strs=lable.split(",");
 	 		String fla=strs[0];
 	 		if(strs.length>1&&!"remote".equals(fla)){
 	 			model.addAttribute("lable", "togther");
 	 			return "classType/editClass/addClassTypeTogtherMessage_1";
 	 		}else if("face".equals(fla)||"live".equals(fla)){
 	 			model.addAttribute("lable", fla);
 	 			return "classType/editClass/addClassTypeFaceOrLiveMessage";
 	 		}else if("video".equals(fla)){
 	 			ClassModule m=classModuleServiceImpl.queryModuleByClasstypeId(classType.getId());
 	 			if(m!=null){
 	 				model.addAttribute("courseNum", m.getTotalClassHour());
 	 			}else{
 	 				model.addAttribute("courseNum", 0);
 	 			}
 	 			model.addAttribute("lable", fla);
 	 			return "classType/editClass/addClassTypeVideoMessage";
 	 		}else{
 	 			model.addAttribute("lable", "other");
 	 			return "classType/editClass/addClassTypeOtherMessage";
 	 		}
 		}else{
 			model.addAttribute("lable", lable);
 			//根据所属标签跳转不同页面
 			if("face".equals(lable)||"live".equals(lable)){
 			
 	 			return "classType/editClass/addClassTypeFaceOrLiveMessage";
 			}else if("video".equals(lable)){
 				ClassModule m=classModuleServiceImpl.queryModuleByClasstypeId(classType.getId());
 				if(m!=null){
 	 				model.addAttribute("courseNum", m.getTotalClassHour());
 	 			}else{
 	 				model.addAttribute("courseNum", 0);
 	 			}
 				return "classType/editClass/addClassTypeVideoMessage";
 			}else if("togther".equals(lable)){
 				
 				return "classType/editClass/addClassTypeTogtherMessage_1";
 			}else{
 				return "classType/editClass/addClassTypeOtherMessage";
 			}
 		}
		
	}
	
	@RequestMapping(value="/editClassBaseInfo/{id}/{lable}")
	public String updateClassTypeMessage(Model model,@PathVariable Integer id,@PathVariable String lable){
		//根据班型id查询详情
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + id);
		ClassTypeVo classType=classTypeServiceImpl.findClassTypeDetail(map);
		model.addAttribute("classType", classType);
		model.addAttribute("ct", classType);
 		model.addAttribute("type", "update");
		model.addAttribute("lable", lable);
		//根据所属标签跳转不同页面
		if("face".equals(lable)||"live".equals(lable)){
		
 			return "classType/editClass/addClassTypeFaceOrLiveMessage";
		}else if("video".equals(lable)){
			ClassModule m=classModuleServiceImpl.queryModuleByClasstypeId(classType.getId());
			if(m!=null){
 				model.addAttribute("courseNum", m.getTotalClassHour());
 			}else{
 				model.addAttribute("courseNum", 0);
 			}
			return "classType/editClass/addClassTypeVideoMessage";
		}else if("togther".equals(lable)){
			
			return "classType/editClass/addClassTypeTogtherMessage_1";
		}else{
			return "classType/editClass/addClassTypeOtherMessage";
		}
	}

	
	@RequestMapping(value="/editClassModule/{id}/{lable}")
	public String forwardClassModule(Model model,@PathVariable Integer id,@PathVariable String lable){
		ClassType cs=classTypeServiceImpl.findClassTypeById(id);
		SysConfigItem item=sysConfigItemServiceImpl.findSysConfigItemById(cs.getItemOneId());
	    SysConfigItem item2=sysConfigItemServiceImpl.findSysConfigItemById(cs.getItemSecondId());
	    if(item!=null){
	    	cs.setItemOneName(item.getItemName());
	    }
	    if(item2!=null){
	    	cs.setItemSecondName(item2.getItemName());
	    }
	    model.addAttribute("type2", "update");
		model.addAttribute("ct", cs);
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
				if(cs!=null && cs.getTeacherId() !=null){
					SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cs.getTeacherId()));
					if(configTeacher.getId() == teacher.getId()){
						model.addAttribute("configTeacher", configTeacher);
					}else{
						model.addAttribute("configTeacher", teacher);
					}
				}else{
					model.addAttribute("configTeacher", configTeacher);
				}
			}
		}
		//根据所属标签跳转不同页面
		if("face".equals(lable)||"live".equals(lable)){
			model.addAttribute("itemOneName", cs.getItemOneName());
			//普通模块
			List<ClassModule> classModule=classModuleServiceImpl.findModulesByClassTypeId(cs.getId());
			model.addAttribute("classModuleList", classModule);
			return "classType/editClass/addClassTypeFaceOrLiveMessage_2";
		}else if("video".equals(lable)){
			//查询班型下的模块信息
			ClassModule m=classModuleServiceImpl.queryModuleByClasstypeId(cs.getId());
			model.addAttribute("classTypeId", cs.getId());
  			model.addAttribute("moduleName", cs.getName());
  		    model.addAttribute("itemOneId",cs.getItemOneId());
  		    model.addAttribute("itemSecondId", cs.getItemSecondId());
  		    if(m!=null){
  		    	model.addAttribute("moduleId", m.getId());
  		    }
			model.addAttribute("id", cs.getId());
			return "classType/editClass/addClassTypeVideoMessage";
		}else if("togther".equals(lable)){
			model.addAttribute("itemOneName", cs.getItemOneName());
			//普通模块
			List<ClassModule> classModule=classModuleServiceImpl.findModulesByClassTypeId(id);
			model.addAttribute("classModuleList", classModule);
			return "classType/editClass/addClassTypeTogtherMessage_2";
		}else{
  			ClassType classType=classTypeServiceImpl.findClassTypeById(id);
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
		    CommodityProductRealtion comm=commodityProductRealtionServiceImpl.findByClassTypeId(cs.getId()+"");
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
			return "classType/editClass/addClassTypeOtherMessage_2";
		}
	}
	
	@RequestMapping(value="/editClassVideoInfo/{id}/{lable}")
	public String editClassVideoInfo(Model model,@PathVariable Integer id,@PathVariable String lable){
	    model.addAttribute("type2", "update");
	    model.addAttribute("lable", lable);
	    Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + id);
		ClassTypeVo classType=classTypeServiceImpl.findClassTypeDetail(map);
		boolean flag = companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
		Subject subject = SecurityUtils.getSubject();
		Users currentUser = WebUtils.getCurrentUser();
		if(flag && subject.hasRole("排课老师")&& !(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营"))){
			model.addAttribute("isFuShengPaikeTeac",true);
			List<SysConfigTeacher> teacherList = sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
			if(teacherList!=null && teacherList.size()>0)
			{
				SysConfigTeacher configTeacher = teacherList.get(0);
				if(classType!=null && classType.getTeacherId()!=null){
					SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(classType.getTeacherId()));
					if(configTeacher.getId() == teacher.getId()){
						model.addAttribute("configTeacher", configTeacher);
					}else{
						model.addAttribute("configTeacher", teacher);
					}
				}else{
					model.addAttribute("configTeacher", configTeacher);
				}
			}
		}
	    //根据所属标签跳转不同页面
  		if("face".equals(lable)||"live".equals(lable)){
  			 //根据班型id查询商品信息id
  		    CommodityProductRealtion comm=commodityProductRealtionServiceImpl.findByClassTypeId(id+"");
  			Integer cId=comm.getComId();
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
  			model.addAttribute("itemOneid", classType.getItemOneId());
  			model.addAttribute("classTypeId", id);
			
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
  			return "classType/editClass/addClassTypeFaceOrLiveMessage_3";
  		}else if("video".equals(lable)){
  		//查询班型下的模块信息
			ClassModule m=classModuleServiceImpl.queryModuleByClasstypeId(classType.getId());
			model.addAttribute("classTypeId", classType.getId());
  			model.addAttribute("moduleName", classType.getName());
  		    model.addAttribute("itemOneId",classType.getItemOneId());
  		    model.addAttribute("itemSecondId", classType.getItemSecondId());
  		    if(m!=null){
  		    	model.addAttribute("moduleId", m.getId());
  		    }
			model.addAttribute("id", classType.getId());
			model.addAttribute("ct", classType);
			return "classType/editClass/addClassTypeVideoMessage_2";
  		}else if("togther".equals(lable)){
  			ClassModule m=classModuleServiceImpl.queryModuleByClasstypeId(classType.getId());
  			model.addAttribute("id", id);
  			if(m!=null){
  				model.addAttribute("moduleId", m.getId());
  			}
  			model.addAttribute("classTypeId", classType.getId());
  			model.addAttribute("moduleName", classType.getName());
  		    model.addAttribute("itemOneId",classType.getItemOneId() );
  		    model.addAttribute("itemSecondId", classType.getItemSecondId());
  		    model.addAttribute("ct", classType);
  			return "classType/editClass/addClassTypeTogtherMessage_3";
  		}
  		return "404";
	}
	
	//混合课程添加直播
	@RequestMapping(value="/editClassModuleLive/{id}/{lable}")
	public String forwardClassModuleType(Model model,@PathVariable Integer id,@PathVariable String lable){
		ClassType cs=classTypeServiceImpl.findClassTypeById(id);
		SysConfigItem item=sysConfigItemServiceImpl.findSysConfigItemById(cs.getItemOneId());
	    SysConfigItem item2=sysConfigItemServiceImpl.findSysConfigItemById(cs.getItemSecondId());
	    if(item!=null){
	    	cs.setItemOneName(item.getItemName());
	    }
	    if(item2!=null){
	    	cs.setItemSecondName(item2.getItemName());
	    }
	    model.addAttribute("type2", "update");
		model.addAttribute("ct", cs);
		model.addAttribute("lable", lable);
		model.addAttribute("moduleType", "live");
		model.addAttribute("itemOneName", cs.getItemOneName());
		//普通模块
		List<ClassModule> classModule=classModuleServiceImpl.findModulesByClassTypeId(cs.getId());
		model.addAttribute("classModuleList", classModule);
		return "classType/editClass/addClassTypeLiveMessage_togther";
	}
	
	//混合课程添加面授
	@RequestMapping(value="/editClassModuleFace/{id}/{lable}")
	public String forwardClassModuleFace(Model model,@PathVariable Integer id,@PathVariable String lable){
		ClassType cs=classTypeServiceImpl.findClassTypeById(id);
		SysConfigItem item=sysConfigItemServiceImpl.findSysConfigItemById(cs.getItemOneId());
	    SysConfigItem item2=sysConfigItemServiceImpl.findSysConfigItemById(cs.getItemSecondId());
	    if(item!=null){
	    	cs.setItemOneName(item.getItemName());
	    }
	    if(item2!=null){
	    	cs.setItemSecondName(item2.getItemName());
	    }
	    model.addAttribute("type2", "update");
		model.addAttribute("ct", cs);
		model.addAttribute("lable", lable);
		model.addAttribute("moduleType", "face");
		model.addAttribute("itemOneName", cs.getItemOneName());
		//普通模块
		List<ClassModule> classModule=classModuleServiceImpl.findModulesByClassTypeId(cs.getId());
		model.addAttribute("classModuleList", classModule);
		return "classType/editClass/addClassTypeFaceMessage_togther";
	}
	
	@RequestMapping(value="/editCourseDetail/{id}/{lable}")
	public String showClassDetail(@PathVariable Integer id,@PathVariable String lable,Model model){
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
				ClassType cs=classTypeServiceImpl.findClassTypeById(id);
				if(cs!=null  && cs.getTeacherId()!=null){
					SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cs.getTeacherId()));
					if(configTeacher.getId() == teacher.getId()){
						model.addAttribute("configTeacher", configTeacher);
					}else{
						model.addAttribute("configTeacher", teacher);
					}
				}else{
					model.addAttribute("configTeacher", configTeacher);
				}
			}
		}
		 //根据所属标签跳转不同页面
  		if("face".equals(lable)||"live".equals(lable)){
  			
  			return "404";
  		}else if("video".equals(lable)){
  			ClassType classType=classTypeServiceImpl.findClassTypeById(id);

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
  			return "classType/editClass/addClassTypeVideoMessage_3";
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
  			return "classType/editClass/addClassTypeTogtherMessage_4";
  		}else{
  			return "404";
  		}
	}
	
	@RequestMapping(value="/editCourseList/{id}/{lable}")
	public String editCourseList(Model model,@PathVariable Integer id,@PathVariable String lable){
	    List<ChapterAndLectureListVo> chapterList=new ArrayList<ChapterAndLectureListVo>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + id);
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
		List<ClassModule> modulesVoList=classModuleServiceImpl.findModulesByClassTypeId(ct.getId());
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
		boolean flag = companyFunctionSetServiceImpl.isCurrentFuSheng(WebUtils.getCurrentCompanyId());
		Subject subject = SecurityUtils.getSubject();
		Users currentUser = WebUtils.getCurrentUser();
		if(flag && subject.hasRole("排课老师")&& !(subject.hasRole("机构管理员")||subject.hasRole("分校管理员")||subject.hasRole("运营"))){
			model.addAttribute("isFuShengPaikeTeac",true);
			List<SysConfigTeacher> teacherList = sysConfigTeacherServiceImpl.findTeachersByUserId(currentUser.getId());
			if(teacherList!=null && teacherList.size()>0)
			{
				SysConfigTeacher configTeacher = teacherList.get(0);
				ClassType cs=classTypeServiceImpl.findClassTypeById(id);
				if(cs!=null && cs.getTeacherId()!=null){
					SysConfigTeacher teacher=sysConfigTeacherServiceImpl.findSysConfigTeacherById(Integer.parseInt(cs.getTeacherId()));
					if(configTeacher.getId() == teacher.getId()){
						model.addAttribute("configTeacher", configTeacher);
					}else{
						model.addAttribute("configTeacher", teacher);
					}
				}else{
					model.addAttribute("configTeacher", configTeacher);
				}
			}
		}
		if("face".equals(lable)||"live".equals(lable)){
			return "classType/editClass/addClassTypeFaceOrLiveMessage_4";
		}
		return "classType/editClass/addClassTypeTogtherMessage_5";
	}
	
	//-------------------------------新加内容上--------------------------------------------------
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
	@ResponseBody
	@RequestMapping(value="/updateClassTypeMessage",method=RequestMethod.POST)
	public String updateBaseMessage(Model model,HttpServletRequest request,Integer oneId,Integer twoId,String type1,ClassType ct,String mark,String lable,Integer courseNum){
		
		//协议id有变化时更新协议记录表
		ClassType oldCt = classTypeServiceImpl.findClassTypeById(ct.getId());
		Integer oldProtocolId = oldCt.getProtocolId();
		Integer protocolId = null;
		
		//课程绑定协议插入协议绑定历史表
		if(ct.getProtocolId() != null && (int)ct.getProtocolId() != 0){
			if(oldProtocolId != null && (int)oldProtocolId != 0 && (int)oldProtocolId != (int)ct.getProtocolId()){
				protocolId = oldProtocolId;
			}
		}else{
			if(oldProtocolId != null && (int)oldProtocolId != 0){
				protocolId = oldProtocolId;
			}
		}
		if(protocolId != null){
			CoursePotocolBindHistory cbh = new CoursePotocolBindHistory();
			cbh.setCourseId(ct.getId());
			cbh.setBindDate(new Date());
			cbh.setBinder(WebUtils.getCurrentUserId(request));
			cbh.setCompanyId(WebUtils.getCurrentCompanyId());
			cbh.setPotocolId(protocolId);
			coursePotocolBindHistoryServiceImpl.insert(cbh);
		}
		
		//更新班型信息
		ct.setItemOneId(oneId);
		ct.setItemSecondId(twoId);
		ct.setUpdateTime(new Date());
		ct.setUpdator(WebUtils.getCurrentUserId(request));

		//如果开启标签库则将标签存库
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
		classTypeServiceImpl.update(ct);
		//更新总课时
		try {
			ClassModule cm=classModuleServiceImpl.queryModuleByClasstypeId(ct.getId());
			if(cm != null) {
				ClassModule module=new ClassModule();
				module.setId(cm.getId());
				module.setTotalClassHour(courseNum);
				classModuleServiceImpl.update(module);
			}
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
		return "success";
	}

	/**
	 * 
	 * Class Name: editClassTypeController.java
	 * @Description: 添加标签库
	 * @author zhang.zx
	 * @date 2016年5月17日 下午3:00:02
	 * @modifier
	 * @modify-date 2016年5月17日 下午3:00:02
	 * @version 1.0
	 * @param request
	 * @param ct
	 */
	private void addCourseLable(HttpServletRequest request, ClassType ct) {
			//添加标签库
			if(null!=ct.getTagName()&&!"".equals(ct.getTagName())){
				try {
					String tagId="";
					String str[]=ct.getTagName().split(",");
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
								search.setItemOneId(ct.getItemOneId());
								search.setItemSecondId(ct.getItemSecondId());
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
						ct.setItemTag(tagId);
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
	@ResponseBody
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
	   return "success"; 
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
			model.addAttribute("itemOneName", cst.getItemOneName());
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
			model.addAttribute("itemOneName", cst.getItemOneName());
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
			return "classType/addClassTypeOtherMessage_2";
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
  	        	teacher2.setUserId(user.getId());
  	        	teacher2.setCompanyId(WebUtils.getCurrentCompanyId());
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
	 * 后台接收Date转换
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
}
