package com.yuxin.wx.controller.course;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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

import com.yuxin.wx.api.classes.IClassPackageService;
import com.yuxin.wx.api.classes.IClassTypeMemberDiscountService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.course.ICourseProtocolConfigService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.BaseWebController;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassPackage;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeMemberDiscount;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.course.CourseProtocolConfig;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.privilege.UserRolesListVo;
import com.yuxin.wx.vo.user.UsersFrontVo;

/**
 * Controller of CourseProtocolConfig
 * @author chopin
 * @date 2016-11-1
 */
@Controller
@RequestMapping("/courseProtocolConfig")
public class CourseProtocolConfigController extends BaseWebController{
	
	@Autowired
	private ICourseProtocolConfigService courseProtocolConfigServiceImpl;
	
	@Autowired
	private IUsersService userServiceImpl;
	
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;
	
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	
	@Autowired
	private IClassPackageService classPackageServiceImpl;
	
	@Autowired
	private IStudentService studentServiceImpl;
	
	@Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;
	
	@Autowired
    private ISysConfigServiceService sysConfigServiceServiceImpl;

    @Autowired
    private IClassTypeMemberDiscountService classTypeMemberDiscountServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CourseProtocolConfig search){
		if (search == null) {
			search = new CourseProtocolConfig();
			// search.setPageSize(20);
		}
		model.addAttribute("list", courseProtocolConfigServiceImpl.findCourseProtocolConfigByPage(search));
		return "courseProtocolConfig/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CourseProtocolConfig CourseProtocolConfig) {
		courseProtocolConfigServiceImpl.insert(CourseProtocolConfig);
		return "redirect:/courseProtocolConfig";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CourseProtocolConfig CourseProtocolConfig) {
		courseProtocolConfigServiceImpl.update(CourseProtocolConfig);
		return "redirect:/courseProtocolConfig";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		courseProtocolConfigServiceImpl.deleteCourseProtocolConfigById(id);
		return "redirect:/courseProtocolConfig";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CourseProtocolConfig getJson(Model model, @PathVariable Integer id){
		return courseProtocolConfigServiceImpl.findCourseProtocolConfigById(id);
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
	
	@RequestMapping(value="/toProtocolList")
	public String toProtocolList(HttpServletRequest request,Model model){
		
		return "resource/protocol/protocolList";
	}
	
	/**
	 * 
	 * Class Name: AuthPrivilegeController.java
	 * @Description:查询用户角色并分页
	 * @author zhang.zx
	 * @date 2015年5月18日 下午9:15:36
	 * @modifier
	 * @modify-date 2015年5月18日 下午9:15:36
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="/queryProtocolList",method=RequestMethod.POST)
	public String queryUserRoleListsByPage(Model model,CourseProtocolConfig search,HttpServletRequest request){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setPageSize(10);
		List<CourseProtocolConfig> list = courseProtocolConfigServiceImpl.findCourseProtocolConfigByPage(search);
		if(list != null && list.size()>0){
			for (CourseProtocolConfig cpc : list) {
				Integer creator = cpc.getCreator();
				if(null != creator ){
					Users user = userServiceImpl.findUsersById(creator);
					if(user !=null && !StringUtils.isEmpty(user.getUsername())){
						cpc.setCreateUserName(user.getUsername());
					}else{
						cpc.setCreateUserName("");
					}
				}else{
					cpc.setCreateUserName("");
				}
				if(cpc.getCreateTime() != null){
					String format = new SimpleDateFormat("yyyy-MM-dd").format(cpc.getCreateTime());
					cpc.setCreateTimeStr(format);
				}
			}
		}
		Integer count = courseProtocolConfigServiceImpl.pageCount(search);
		PageFinder<CourseProtocolConfig> finder = new PageFinder<CourseProtocolConfig>(search.getPage(), search.getPageSize(), count, list);
		model.addAttribute("pageFinder", finder);
		return "/resource/protocol/ajaxProtocolList";
		
	}
	
	@RequestMapping(value="/addProtocol")
	public String addProtocol(HttpServletRequest request,Model model){
		String protocolIdStr = request.getParameter("protocolId");
		if(!StringUtils.isEmpty(protocolIdStr)){
			Integer protocolId = Integer.parseInt(protocolIdStr);
			CourseProtocolConfig courseProtocolConfig = courseProtocolConfigServiceImpl.findCourseProtocolConfigById(protocolId);
			model.addAttribute("protocol", courseProtocolConfig);
		}
		CompanyFunctionSet cfs = new CompanyFunctionSet();
		cfs.setFunctionCode("CLASS_POTOCOL_SET");
		cfs.setCompanyId(WebUtils.getCurrentCompanyId());
		cfs.setStatus("1");
		List<CompanyFunctionSet> list = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(cfs);
		model.addAttribute("courseOpen", list.size()>0?"success":"fail");
		cfs.setFunctionCode("CLASSPACKAGE_POTOCOL_SET");
		list = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(cfs);
		model.addAttribute("packageOpen", list.size()>0?"success":"fail");
		return "/resource/protocol/addProtocol";
	}
	
	@ResponseBody
	@RequestMapping(value="/saveOrUpdateProtocol",method=RequestMethod.POST)
	public String saveOrUpdateProtocol(CourseProtocolConfig item,HttpServletRequest request){
		
		Integer id = item.getId();
		try {
			if(id != null){
				CourseProtocolConfig cpc = courseProtocolConfigServiceImpl.findCourseProtocolConfigById(id);
				if(cpc!=null){
					courseProtocolConfigServiceImpl.update(item);
				}
			}else{
				item.setCompanyId(WebUtils.getCurrentCompanyId());
				item.setCreateTime(new Date());
				item.setCreator(WebUtils.getCurrentUserId(request));
				item.setStatus(1);
				item.setDelFlag(0);
				courseProtocolConfigServiceImpl.insert(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping(value="/toProtocolDetail")
	public String toProtocolDetail(HttpServletRequest request,Model model){
		String protocolIdStr = request.getParameter("protocolId");
		String classTypeId = request.getParameter("classTypeId");
		String classPackageId = request.getParameter("classPackageId");
		String stuId = request.getParameter("stuId");
		
		if(!StringUtils.isEmpty(protocolIdStr)){
			int protocolId = Integer.parseInt(protocolIdStr);
			CourseProtocolConfig courseProtocolConfig = courseProtocolConfigServiceImpl.findCourseProtocolConfigById(protocolId);
			if(courseProtocolConfig != null && courseProtocolConfig.getCreateTime()!= null){
				courseProtocolConfig.setCreateTimeStr(new SimpleDateFormat("yyyy-MM-dd").format(courseProtocolConfig.getCreateTime()));
			}else{
				courseProtocolConfig.setCreateTimeStr("");
			}
			model.addAttribute("protocol", courseProtocolConfig);
			
			ClassType findClassTypeById = null;
			 SysConfigItem itemOne = null;
		     SysConfigItem itemSecond = null;
			if(!StringUtils.isEmpty(classTypeId)){
				Integer cId = Integer.parseInt(classTypeId);
				findClassTypeById = classTypeServiceImpl.findClassTypeById(cId);
				itemOne = this.sysConfigItemServiceImpl.findSysConfigItemById(findClassTypeById.getItemOneId());
				itemSecond = this.sysConfigItemServiceImpl.findSysConfigItemById(findClassTypeById.getItemSecondId());
				model.addAttribute("itemOne",itemOne);
				model.addAttribute("itemSecond",itemSecond);
				model.addAttribute("classType",findClassTypeById);
			}
			if(!StringUtils.isEmpty(classPackageId)){
				Integer cpId = Integer.parseInt(classPackageId);
				ClassPackage findClassPackageById = classPackageServiceImpl.findClassPackageById(cpId);
				model.addAttribute("classPackage",findClassPackageById);
			}
			if(!StringUtils.isEmpty(stuId)){
				Integer sId = Integer.parseInt(stuId);
				UsersFrontVo vo =  studentServiceImpl.findUserFrontvoByStuId(sId);
				model.addAttribute("user",vo);
				if(findClassTypeById != null){
					double disc = 10;
			        double realPrice = findClassTypeById.getRealPrice();
			        if (null != vo && null != vo.getMemberId()) {
			            DecimalFormat df = new DecimalFormat("######0.00");
			            // 查询会员服务
			            Map<String, Object> paramCon = new HashMap<String, Object>();
			            paramCon.put("companyId", findClassTypeById.getCompanyId());
			            paramCon.put("groupCode", "SERVICE_MEMBER");
			            SysConfigService sevice = this.sysConfigServiceServiceImpl.findByCodeId(paramCon);
			            if (null != sevice && null != sevice.getDelFlag() && sevice.getDelFlag() == 1) {
			                // 查询课程下是否开启会员
			                if (null != findClassTypeById.getMemberFlag() && findClassTypeById.getMemberFlag() == 1) {
			                    // 如果开启，则查出所有会员列表的价格
			                    ClassTypeMemberDiscount lm = new ClassTypeMemberDiscount();
			                    lm.setCompanyId(findClassTypeById.getCompanyId());
			                    lm.setClassTypeId(findClassTypeById.getId());
			                    List<ClassTypeMemberDiscount> lmList = this.classTypeMemberDiscountServiceImpl.findClassLevelList(lm);
			                    if (null != lmList && lmList.size() > 0) {
			                        for (ClassTypeMemberDiscount discount : lmList) {
			                            if (null != vo && null != vo.getMemberId() && discount.getMemberId() == vo.getMemberId()) {
			                                disc = discount.getMemberDiscount();
			                                break;
			                            }
			                        }
			                        // 设置当前用户会员级别所购买的课程价格
			                        double pri = Double.parseDouble(df.format(realPrice * (disc * 0.1)));
			                        model.addAttribute("userRealPrice", pri);
			                    }
			                }
			            }
			        }
				}
			}
		}
		return "/resource/protocol/protocolDetail";
	}
	
	@ResponseBody
	@RequestMapping(value="/checkNameExsit",method=RequestMethod.POST)
	public String checkNameExsit(CourseProtocolConfig search){
			search.setCompanyId(WebUtils.getCurrentCompanyId());
			return courseProtocolConfigServiceImpl.checkNameExist(search)>0?"fail":"success";
	}
}
