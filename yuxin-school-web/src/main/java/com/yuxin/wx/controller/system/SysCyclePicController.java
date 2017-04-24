package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysCyclePicService;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.system.SysCyclePic;
import com.yuxin.wx.utils.FileUploadUtil;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of SysCyclePic
 * @author chopin
 * @date 2015-4-11
 */
@Controller
@RequestMapping("/sysCyclePic")
public class SysCyclePicController {
	private Log log=LogFactory.getLog("log");
	@Autowired
	private ISysCyclePicService sysCyclePicServiceImpl;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	@Autowired
	private ISysConfigSchoolService sysConfigSchoolServiceImpl;
	
	@Autowired
	private IAuthRoleService authRoleServiceImpl;
	
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	
	/**
	 * 
	 * Class Name: SysCyclePicController.java
	 * @Description: 上传轮播图
	 * @author zhang.zx
	 * @date 2015年6月2日 下午2:35:50
	 * @modifier
	 * @modify-date 2015年6月2日 下午2:35:50
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/UploadCycles",method=RequestMethod.POST)
	public String UploadCycles(MultipartRequest multiPartRquest,HttpServletRequest req){
		log.info(req.getServerName()+"==============="+req.getSession().getId());
		Subject subject = SecurityUtils.getSubject();
		String realPath=null;
		String picPath=null;
		MultipartFile	multipartFile = multiPartRquest.getFile("imgData");
		subject.getSession().setAttribute("imgData", multipartFile);
		String name=multipartFile.getOriginalFilename();
		if(name!=null&&!"".equals(name)){
			try {
				realPath = FileUtil.upload(multipartFile, "cycllepic", WebUtils.getCurrentCompanyId()+"");
			} catch (Exception e) {
				log.error("文件上传失败",e);
				e.printStackTrace();
			}
		}
		picPath="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
		return "{\"url\":\""+picPath+"\",\"picPath\":\""+realPath+"\"}";
	}
	
	
	/**
	 * 
	 * Class Name: SysCyclePicController.java
	 * @Description: 查询轮播图
	 * @author zhang.zx
	 * @date 2015-4-13
	 * @modifier
	 * @modify-date 2015-4-13
	 * @version 1.0
	 * @param sysCyclePic
	 * @return
	 */
	@RequestMapping(value="/showPic",method=RequestMethod.GET)
	public String querySysCyclePics(HttpServletRequest request,Model model){
		Integer companyId=WebUtils.getCurrentCompanyId();
		Integer schoolId=WebUtils.getCurrentSchoolId();
		
		Integer userId=WebUtils.getCurrentUserId(request);
		if(authRoleServiceImpl.hasRoleFlag(userId)){
			List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
			model.addAttribute("schoolId", schoolId);
			model.addAttribute("schoolList", schoolList);
		}else{
			SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(schoolId);
			model.addAttribute("school",school);
		}
//		Subject subject = SecurityUtils.getSubject();
//		if(subject.hasRole("机构管理员")){
//			List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
//			model.addAttribute("schoolId", schoolId);
//			model.addAttribute("schoolList", schoolList);
//		}else if(subject.hasRole("分校管理员")){
//			SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(schoolId);
//			model.addAttribute("school",school);
//		}
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		//return "system/configCycle";
		return "system/systemCycle";
	}
	
	/**
	 * 
	 * Class Name: SysCyclePicController.java
	 * @Description: 进入管理app轮播图
	 * @author zhang.zx
	 * @date 2016年5月9日 下午2:33:12
	 * @modifier
	 * @modify-date 2016年5月9日 下午2:33:12
	 * @version 1.0
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showAppBannerPic",method=RequestMethod.GET)
	public String showBannerPic(HttpServletRequest request,Model model ){
		Integer companyId=WebUtils.getCurrentCompanyId();
		Integer schoolId=WebUtils.getCurrentSchoolId();
		
		if(authRoleServiceImpl.hasRoleFlag(WebUtils.getCurrentUser().getId())){
			List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
			model.addAttribute("schoolId", schoolId);
			model.addAttribute("schoolList", schoolList);
		}else{
			SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(schoolId);
			model.addAttribute("school",school);
		}
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "app/appCycle";
	}
	
	/**
	 * 
	 * Class Name: SysCyclePicController.java
	 * @Description: 查询轮播图
	 * @author zhang.zx
	 * @date 2015-4-13
	 * @modifier
	 * @modify-date 2015-4-13
	 * @version 1.0
	 * @param sysCyclePic
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/showPic2",method=RequestMethod.POST)
	public List<SysCyclePic> querySysCyclePics2(SysCyclePic sysCyclePic,HttpServletRequest request,Model model){
		sysCyclePic.setCompanyId(WebUtils.getCurrentCompanyId());
		sysCyclePic.setSchoolId(WebUtils.getCurrentSchoolId());		
		List<SysCyclePic> sysCyclePicList=sysCyclePicServiceImpl.findSysCyclePic(sysCyclePic);
		for(SysCyclePic syscle:sysCyclePicList){
			syscle.setPicUrl("http://"+propertiesUtil.getProjectImageUrl()+"/"+syscle.getPicUrl());
		}
		return sysCyclePicList;
	}
	
	/**
	 * 
	 * Class Name: SysCyclePicController.java
	 * @Description: 异步加载轮播图数据
	 * @author zhang.zx
	 * @date 2015年4月25日 下午4:42:34
	 * @modifier
	 * @modify-date 2015年4月25日 下午4:42:34
	 * @version 1.0
	 * @param sysCyclePic
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/queryCycleDatas",method=RequestMethod.POST)
	public String queryCycleDatas(SysCyclePic sysCyclePic, Model model){
		sysCyclePic.setCompanyId(WebUtils.getCurrentCompanyId());
		List<SysCyclePic> sysCyclePicList=sysCyclePicServiceImpl.findSysCyclePic(sysCyclePic);
		model.addAttribute("sysCyclePicList", sysCyclePicList);
		model.addAttribute("cyclePicUrl", "http://"+propertiesUtil.getProjectImageUrl()+"/");
		model.addAttribute("picType", sysCyclePic.getPicType());
		return "system/sysCycleAjaxList";
	}
	
	/**
	 * 
	 * Class Name: SysCyclePicController.java
	 * @Description: 查询app轮播图数据
	 * @author zhang.zx
	 * @date 2016年5月9日 下午3:34:58
	 * @modifier
	 * @modify-date 2016年5月9日 下午3:34:58
	 * @version 1.0
	 * @param sysCyclePic
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/queryAppCycleList",method=RequestMethod.POST)
	public String queryAppCycleList(SysCyclePic sysCyclePic, Model model){
		sysCyclePic.setCompanyId(WebUtils.getCurrentCompanyId());
		List<SysCyclePic> sysCyclePicList=sysCyclePicServiceImpl.findSysCyclePic(sysCyclePic);
		if(null!=sysCyclePicList && sysCyclePicList.size()>0){
			for(SysCyclePic list:sysCyclePicList){
				if(null!=list && null!=list.getClickUrl() && !"".equals(list.getClickUrl())){
					try {
						if(list.getClickUrl().contains("selectDetail")){
							Integer courseId=Integer.parseInt(list.getClickUrl().substring(list.getClickUrl().lastIndexOf("/")+1, list.getClickUrl().length()));
							ClassType ct=classTypeServiceImpl.findClassTypeById(courseId);
							if(null!=ct){
								list.setTypeName(ct.getName());
							}
						}else{
							Integer itemId=Integer.parseInt(list.getClickUrl().substring(list.getClickUrl().lastIndexOf("/")+1, list.getClickUrl().length()));
							SysConfigItem item=sysConfigItemServiceImpl.findSysConfigItemById(itemId);
							if(null!=item){
								list.setTypeName(item.getItemName());
							}
						}
					} catch (Exception e) {
						log.error("截取id出错",e);
						e.printStackTrace();
					}
				}
			}
		}
		model.addAttribute("sysCyclePicList", sysCyclePicList);
		model.addAttribute("cyclePicUrl", "http://"+propertiesUtil.getProjectImageUrl()+"/");
		model.addAttribute("picType", sysCyclePic.getPicType());
		return "app/appCycleAjaxList";
	}
	
	/**
	 * 
	 * Class Name: SysCyclePicController.java
	 * @Description: 添加轮播图
	 * @author zhang.zx
	 * @date 2015年4月25日 下午5:30:50
	 * @modifier
	 * @modify-date 2015年4月25日 下午5:30:50
	 * @version 1.0
	 * @param sysCyclePic
	 * @param multiPartRquest
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addCycles")
	public String addSysCyclePic(SysCyclePic sysCyclePic,HttpServletRequest req){
		//String status="success";
		sysCyclePic.setCompanyId(WebUtils.getCurrentCompanyId());
//		sysCyclePic.setSchoolId(WebUtils.getCurrentSchoolId());
		sysCyclePic.setCreateTime(new Date());
		sysCyclePic.setCreator(WebUtils.getCurrentUserId(req));
		SysCyclePic s=new SysCyclePic();
		s.setPicType(sysCyclePic.getPicType());
		s.setCompanyId(WebUtils.getCurrentCompanyId());
		s.setSchoolId(sysCyclePic.getSchoolId());
		List<SysCyclePic> sysCycleList=sysCyclePicServiceImpl.findSysCyclePic(s);
		for(SysCyclePic cycle:sysCycleList){
			SysCyclePic entity=new SysCyclePic();
			entity.setId(cycle.getId());
			if(cycle.getPicSequence()==null) 
				cycle.setPicSequence(0);
			entity.setPicSequence(cycle.getPicSequence()+1);
			sysCyclePicServiceImpl.updateSort(entity);
		}
//		MultipartFile multipartFile = multiPartRquest.getFile("imgData");
//		String realPath=null;
//		try {
//			realPath = FileUtil.upload(multipartFile, "cycllepic", WebUtils.getCurrentCompanyId()+"");
//		} catch (Exception e) {
//			log.info("文件上传失败");
//			log.info(e.getStackTrace());
//			e.printStackTrace();
//			status="error";
//		}
//		sysCyclePic.setPicUrl(realPath);
		sysCyclePic.setPicSequence(1);
		sysCyclePicServiceImpl.addSysCyclePic(sysCyclePic);
		//return "{\"status\":\""+status+"\"}";
		return "success";
	}
	
	/**
	 * 
	 * Class Name: SysCyclePicController.java
	 * @Description: 轮播图位置交换
	 * @author zhang.zx
	 * @date 2015年4月24日 下午1:57:41
	 * @modifier
	 * @modify-date 2015年4月24日 下午1:57:41
	 * @version 1.0
	 * @param id1
	 * @param sortOne
	 * @param liTwo
	 * @param liTwoSort
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/change",method=RequestMethod.POST)
	public String changeStatus(Integer id1,Integer sortOne,Integer liTwo,Integer liTwoSort){
		SysCyclePic s1=new SysCyclePic();
		s1.setId(id1);
		s1.setPicSequence(liTwoSort);
		sysCyclePicServiceImpl.updateSort(s1);
		SysCyclePic s2=new SysCyclePic();
		s2.setId(liTwo);
		s2.setPicSequence(sortOne);
		sysCyclePicServiceImpl.updateSort(s2);
		return "success";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysCyclePic search){
		if (search == null) {
			search = new SysCyclePic();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysCyclePicServiceImpl.findSysCyclePicByPage(search));
		return "sysCyclePic/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysCyclePic SysCyclePic) {
		sysCyclePicServiceImpl.insert(SysCyclePic);
		return "redirect:/sysCyclePic";
	}
	
	/**
	 * 
	 * Class Name: SysCyclePicController.java
	 * @Description: 修改轮播图
	 * @author zhang.zx
	 * @date 2015年4月25日 下午7:08:10
	 * @modifier
	 * @modify-date 2015年4月25日 下午7:08:10
	 * @version 1.0
	 * @param SysCyclePic
	 * @param multiPartRquest
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateSysCycles", method = RequestMethod.POST)
	public String update(SysCyclePic sysCyclePic,HttpServletRequest req) {
		sysCyclePic.setUpdateTime(new Date());
		sysCyclePic.setUpdator(WebUtils.getCurrentUserId(req));
//		MultipartFile multipartFile = multiPartRquest.getFile("imgData");
//		String realPath=null;
//		try {
//			realPath = FileUtil.upload(multipartFile, "cycllepic", WebUtils.getCurrentCompanyId()+"");
//		} catch (Exception e) {
//			log.info("文件上传失败");
//			log.info(e.getStackTrace());
//			e.printStackTrace();
//		}
//		sysCyclePic.setPicUrl(realPath);
		sysCyclePicServiceImpl.update(sysCyclePic);
		return "success";
	}
	
	/**
	 * 
	 * Class Name: SysCyclePicController.java
	 * @Description: 删除轮播图
	 * @author zhang.zx
	 * @date 2015年4月25日 下午6:36:06
	 * @modifier
	 * @modify-date 2015年4月25日 下午6:36:06
	 * @version 1.0
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delSysCycle", method = RequestMethod.POST)
	public String del(Integer id) {
		sysCyclePicServiceImpl.deleteSysCyclePicById(id);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysCyclePic getJson(Model model, @PathVariable Integer id){
		return sysCyclePicServiceImpl.findSysCyclePicById(id);
	}
	
	/**
	 * 后台接收Date转换
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
