package com.yuxin.wx.controller.classes;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.course.ICourseVideoLookAuthService;
import com.yuxin.wx.api.system.ISysConfigPageRedirectService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.course.CourseVideoLookAuth;
import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.model.system.SysConfigPageRedirect;
import com.yuxin.wx.utils.WebUtils;


/**
 * 
 * @ClassName: ClassTypeNewController
 * @Description: 课程管理
 * @author zhang.zx
 * @date 2015年12月18日 上午10:34:16
 * @modifier
 * @modify-date 2015年12月18日 上午10:34:16
 * @version 1.0
 */
@Controller
@RequestMapping("/classManage")
public class ClassManageController {

	private Log log=LogFactory.getLog("log");
	
	@Autowired
	private ICourseVideoLookAuthService courseVideoLookAuthServiceImpl;
	
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	@Autowired
	private ISysConfigPageRedirectService sysConfigPageRedirectServiceImpl;

	/**
	 * 
	 * Class Name: ClassManageController.java
	 * @Description: 报名通知
	 * @author zhang.zx
	 * @date 2015年12月18日 上午10:38:55
	 * @modifier
	 * @modify-date 2015年12月18日 上午10:38:55
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="/signup_news")
	public String signUpManage(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "classType/signUpNotice";
	}
	
	/**
	 * 
	 * Class Name: ClassManageController.java
	 * @Description: 课程多班号管理
	 * @author zhang.zx
	 * @date 2015年12月18日 上午10:41:15
	 * @modifier
	 * @modify-date 2015年12月18日 上午10:41:15
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/manage_classno")
	public String manyclassNoManage(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "classType/manageClassNo";
	}
	
	/**
	 * 
	 * Class Name: ClassManageController.java
	 * @Description: 管理直播、面授课程列表
	 * @author zhang.zx
	 * @date 2015年12月21日 下午4:08:38
	 * @modifier
	 * @modify-date 2015年12月21日 下午4:08:38
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/manage_lesson")
	public String managelessonlist(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "classType/manageLessonList";
	}
	
	
	/**
	 * 
	 * Class Name: ClassManageController.java
	 * @Description: 课程评论
	 * @author zhang.zx
	 * @date 2015年12月28日 下午4:20:41
	 * @modifier
	 * @modify-date 2015年12月28日 下午4:20:41
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/manage_classpl")
	public String manageCoursePL(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "classType/manageCoursePL";
	}
	
	/**
	 * 
	 * Class Name: ClassManageController.java
	 * @Description: 课程有效期
	 * @author zhang.zx
	 * @date 2015年12月28日 下午4:35:01
	 * @modifier
	 * @modify-date 2015年12月28日 下午4:35:01
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/manageCourseValidaty")
	public String manageCourseValidate(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyFunctionSet search = new CompanyFunctionSet();
		search.setCompanyId(companyId);
		List<CompanyFunctionSet> sets = companyFunctionSetServiceImpl.findCompanyFunctionSet(search);
		if(sets.size()>0){
			for (CompanyFunctionSet ss : sets) {
				String code = ss.getFunctionCode();
				String status = ss.getStatus();
				if("COURSE_VALIDITY_COMPANY_LEVEL".equals(code)){
					model.addAttribute("comLS",status);
				}
				if("COURSE_VALIDITY_ITEM_LEVEL".equals(code)){
					model.addAttribute("itemLS",status);
				}
				if("COURSE_VALIDITY_COURSE_LEVEL".equals(code)){
					model.addAttribute("claLS",status);
				}
			}
		}
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "classType/manageCourse_validity";
	}
	
	/**
	 * 
	 * Class Name: ClassManageController.java
	 * @Description: 管理课程章节
	 * @author zhang.zx
	 * @date 2015年12月30日 上午10:11:19
	 * @modifier
	 * @modify-date 2015年12月30日 上午10:11:19
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/manage_chapOrlecName")
	public String manageChapOrlecName(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "classType/manageChapterAndLecture";
	}
	
	/**
	 * 
	 * Class Name: ClassManageController.java
	 * @Description: 查询课表设置内容
	 * @author zhang.zx
	 * @date 2015年12月25日 下午3:16:36
	 * @modifier
	 * @modify-date 2015年12月25日 下午3:16:36
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryShowCourse")
	public List<CompanyFunctionSet> queryShowClassList(CompanyFunctionSet search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setFunctionCode("COURSE_LIST_SHOW");
		List<CompanyFunctionSet> arr=companyFunctionSetServiceImpl.findCompanyFunctions(search);
		return arr;
	}

	/**
	 * 
	 * Class Name: ClassManageController.java
	 * @Description: 视频课程设置
	 * @author 周文斌
	 * @date 2016-3-10 下午6:01:15
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("manage_courseVideo")
	public String manageCourseVideo(Model model,HttpServletRequest request){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		
	//	log.info("查询设置");
		CourseVideoLookAuth cv = courseVideoLookAuthServiceImpl.findAuthByCompanyId(companyId);
		if(cv == null){
			cv = new CourseVideoLookAuth();
			cv.setCompanyId(companyId);
			cv.setCreateTime(new Date());
			cv.setCreator(WebUtils.getCurrentUserId(request));
			cv.setNamedNum(0);
			cv.setNamedTime(0);
			cv.setOverFlowInfo("该视频属于收费视频，如想继续收看请您购买课程");
			cv.setOverFlowTime(0);
			cv.setSetPointName("LOOK_VIDEO_NOT_NAMED");
			cv.setTestListenAuth("ALL_USERS");
			cv.setUserSeeAuth("ALL_USERS");
			courseVideoLookAuthServiceImpl.insert(cv);
		}
		
		model.addAttribute("cv", cv);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "classType/manageCourseVideo";
	}
	
	/**
	 * 
	 * Class Name: ClassManageController.java
	 * @Description: 添加课程列表查看功能
	 * @author zhang.zx
	 * @date 2015年12月25日 下午4:36:18
	 * @modifier
	 * @modify-date 2015年12月25日 下午4:36:18
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addcourseFunction")
	public String addCourseCompanyFunction(HttpServletRequest request){
		List<CompanyFunctionSet> functionlists=JSONObject.parseArray(request.getParameter("functionset"),com.yuxin.wx.model.company.CompanyFunctionSet.class);
		CompanyFunctionSet search=new CompanyFunctionSet();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setFunctionCode("COURSE_LIST_SHOW");
		List<CompanyFunctionSet> arr=companyFunctionSetServiceImpl.findCompanyFunctions(search);
		if(null!=arr && arr.size()>0){
			for(CompanyFunctionSet set:arr){
				for(CompanyFunctionSet set1:functionlists){
					if(set.getContent().equals(set1.getContent())){
						set1.setCompanyId(WebUtils.getCurrentCompanyId());
						set1.setId(set.getId());
						companyFunctionSetServiceImpl.update(set1);
					}
				}
			}
		}else{
			for(CompanyFunctionSet set:functionlists){
				set.setCompanyId(WebUtils.getCurrentCompanyId());
				companyFunctionSetServiceImpl.insert(set);
			}
		}
		return "success";
	}
	
	/**
	 * 
	 * Class Name: ClassManageController.java
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author DELL.COM
	 * @date 2016年5月24日 下午12:11:13
	 * @modifier
	 * @modify-date 2016年5月24日 下午12:11:13
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/manage_courseLable")
	public String manageCourseLable(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "classType/manageCourseLable";
	}
	
	/**
	 * 
	 * Class Name: ClassManageController.java
	 * @Description: 课程购买限制
	 * @author zhang.zx
	 * @date 2016年8月10日 上午11:58:09
	 * @modifier
	 * @modify-date 2016年8月10日 上午11:58:09
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/manage_courseBuy")
	public String manageCourseBuy(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "classType/manageCourseBuy";
	}
	
	/**
	 * 
	 * Class Name: ClassManageController.java
	 * @Description: 加载seo设置
	 * @author zhang.zx
	 * @date 2015年12月28日 上午10:50:18
	 * @modifier
	 * @modify-date 2015年12月28日 上午10:50:18
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/loadSeoList")
	public List<CompanyFunctionSet> querySeoList(CompanyFunctionSet search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setFunctionCode("COMPANY_SEO");
		List<CompanyFunctionSet> arr=companyFunctionSetServiceImpl.findCompanyFunctions(search);
		return arr;
	}
	
	@RequestMapping(value="/manage_protocol")
	public String manageProtocol(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "classType/manageProtocol";
	}
	
	@RequestMapping(value="/manage_homework_inform")
	public String manageHomeworkInform(Model model,HttpServletRequest request){
		
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "classType/manageHomeworkInform";
	}
	
	@RequestMapping(value="/courseDetailSet")
	public String courseDetail(Model model,HttpServletRequest request){
		
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		SysConfigPageRedirect condition = new SysConfigPageRedirect();
		condition.setCompanyId(companyId);
		condition.setLink("courseDetail");
		condition.setBussinessType("REDIRECT_FRONT_COURSEDETAIL");
		
		condition = sysConfigPageRedirectServiceImpl.findPageRedirect(condition);
		if(condition == null) {
			condition = new SysConfigPageRedirect();
			condition.setCompanyId(companyId);
			condition.setLink("courseDetail");
			condition.setBussinessType("REDIRECT_FRONT_COURSEDETAIL");
			condition.setStatus(1);
			condition.setTemplateId(1);
			condition.setSysType(0);
			sysConfigPageRedirectServiceImpl.insert(condition);
		}
		model.addAttribute("cd", condition);
		
		return "classType/courseDetailSet";
	}
	@ResponseBody
	@RequestMapping(value="/courseDetailSetConfig")
	public Object courseDetailSetConfig(Model model,HttpServletRequest request,SysConfigPageRedirect condition){
		sysConfigPageRedirectServiceImpl.update(condition);
		return true;
	}
}
