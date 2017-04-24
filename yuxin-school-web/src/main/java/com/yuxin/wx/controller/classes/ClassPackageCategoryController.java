package com.yuxin.wx.controller.classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.alibaba.fastjson.JSONArray;
import com.yuxin.wx.api.classes.IClassPackageCategoryService;
import com.yuxin.wx.api.classes.IClassPackageService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyNewStepService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.model.classes.ClassPackage;
import com.yuxin.wx.model.classes.ClassPackageCategory;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysPageHeadFoot;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.SysPageHeadFootVo;

/**
 * Controller of ClassPackageCategory
 * @author chopin
 * @date 2016-3-21
 */
@Controller
@RequestMapping("/classPackageCategory")
public class ClassPackageCategoryController {
	
	@Autowired
	private IClassPackageCategoryService classPackageCategoryServiceImpl;
	@Autowired
	private IClassPackageService classPackageServiceImpl;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ClassPackageCategory search){
		if (search == null) {
			search = new ClassPackageCategory();
			// search.setPageSize(20);
		}
		model.addAttribute("list", classPackageCategoryServiceImpl.findClassPackageCategoryByPage(search));
		return "classPackageCategory/list";
	}
	
	/**
	 * 
	 * Class Name: ClassPackageCategoryController.java
	 * @Description: 课程包分类
	 * @author zhang.zx
	 * @date 2016年3月22日 下午4:51:58
	 * @modifier
	 * @modify-date 2016年3月22日 下午4:51:58
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addClassPackageCategory")
	public String forwardClassPackageCategory(Model model){
		Company company = companyService.findCompanyById(WebUtils.getCurrentCompanyId());
		model.addAttribute("company", company);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "coursePackage/manageCourseCategory";
	}
	
	/**
	 * 
	 * Class Name: ClassPackageCategoryController.java
	 * @Description: 课程包分类设置
	 * @author zhang.zx
	 * @date 2016年3月22日 下午4:52:35
	 * @modifier
	 * @modify-date 2016年3月22日 下午4:52:35
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/SetClassPackageCategory")
	public String forwardClassPackageCategorySet(Model model){
		Company company = companyService.findCompanyById(WebUtils.getCurrentCompanyId());
		model.addAttribute("company", company);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "coursePackage/manageCourseCategorySet";
	}
	
	/**
	 * 
	 * Class Name: ClassPackageCategoryController.java
	 * @Description: 添加分类
	 * @author zhang.zx
	 * @date 2016年3月21日 下午5:37:37
	 * @modifier
	 * @modify-date 2016年3月21日 下午5:37:37
	 * @version 1.0
	 * @param classPackageCategory
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addClassPackage", method = RequestMethod.POST)
	public ClassPackageCategory add(ClassPackageCategory classPackageCategory,HttpServletRequest request) {
		if(null==classPackageCategory.getParentId() || "".equals(classPackageCategory.getParentId())){
			classPackageCategory.setParentId(0);
		}
		classPackageCategory.setCode(productCode(classPackageCategory.getParentId(), classPackageCategory.getCode()));
		classPackageCategory.setCompanyId(WebUtils.getCurrentCompanyId());
		classPackageCategory.setSchoolId(WebUtils.getCurrentSchoolId());
		classPackageCategory.setCreator(WebUtils.getCurrentUserId(request));
		classPackageCategory.setCreateTime(new Date());
		classPackageCategory.setUpdator(WebUtils.getCurrentUserId(request));
		classPackageCategory.setUpdateTime(new Date());
		classPackageCategory.setDelFlag(0);
		ClassPackageCategory cpc=new ClassPackageCategory();
		cpc.setCompanyId(WebUtils.getCurrentCompanyId());
		cpc.setCode(classPackageCategory.getCode());
		List<ClassPackageCategory> data=classPackageCategoryServiceImpl.queryClassCategoryCodeByWhere(cpc);
		if(null!=data && data.size()>0){
			classPackageCategory.setId(data.get(0).getId());
			classPackageCategory.setDelFlag(0);
			classPackageCategoryServiceImpl.update(classPackageCategory);
		}else{
			classPackageCategoryServiceImpl.insert(classPackageCategory);
		}
		return  classPackageCategoryServiceImpl.findClassPackageCategoryById(classPackageCategory.getId());
	}
	
	/**
	 * 
	 * Class Name: ClassPackageCategoryController.java
	 * @Description: 修改分类
	 * @author zhang.zx
	 * @date 2016年3月21日 下午6:18:33
	 * @modifier
	 * @modify-date 2016年3月21日 下午6:18:33
	 * @version 1.0
	 * @param classPackageCategory
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateClassPackage", method = RequestMethod.POST)
	public Integer update(ClassPackageCategory classPackageCategory,HttpServletRequest request) {
		classPackageCategory.setUpdator(WebUtils.getCurrentUserId(request));
		classPackageCategory.setUpdateTime(new Date());
		classPackageCategoryServiceImpl.update(classPackageCategory);
		return classPackageCategory.getId();
	}
	
	/**
	 * 
	 * Class Name: ClassPackageCategoryController.java
	 * @Description: 删除课程包分类
	 * @author zhang.zx
	 * @date 2016年3月21日 下午7:19:02
	 * @modifier
	 * @modify-date 2016年3月21日 下午7:19:02
	 * @version 1.0
	 * @param id
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delCategory")
	public String del(Integer id,String code,HttpServletRequest request) {
		ClassPackage search=new ClassPackage();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		search.setCategoryCode(code);
		Integer count=classPackageCategoryServiceImpl.queryIsExistClassPackage(search);
		if(null!=count && count>0){
			//课程包分类下存在课程包
			return "existPackage";
		}
		ClassPackageCategory cpc=new ClassPackageCategory();
		cpc.setCompanyId(WebUtils.getCurrentCompanyId());
		cpc.setSchoolId(WebUtils.getCurrentSchoolId());
		cpc.setCode(code);
		//判断是否存在子分类
		List<ClassPackageCategory> data=classPackageCategoryServiceImpl.queryClassCategoryLists(cpc);
		if(null!=data && data.size()>1){
			return "existChild";
		}
		ClassPackageCategory category=new ClassPackageCategory();
		category.setId(id);
		category.setDelFlag(1);
		category.setUpdateTime(new Date());
		category.setUpdator(WebUtils.getCurrentUserId(request));
		classPackageCategoryServiceImpl.update(category);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}")
	public ClassPackageCategory getJson(Model model, @PathVariable Integer id){
		return classPackageCategoryServiceImpl.findClassPackageCategoryById(id);
	}
	
	/**
	 * 
	 * Class Name: ClassPackageCategoryController.java
	 * @Description: 分类排序
	 * @author zhang.zx
	 * @date 2016年3月21日 下午6:25:17
	 * @modifier
	 * @modify-date 2016年3月21日 下午6:25:17
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/orderCategory")
	public String orderClassPackageCategory(HttpServletRequest request){
		List<ClassPackageCategory> arr=JSONArray.parseArray(request.getParameter("list"),ClassPackageCategory.class);
		for(ClassPackageCategory cpc:arr){
			ClassPackageCategory cp=new ClassPackageCategory();
			cp.setId(cpc.getId());
			cp.setSort(cp.getSort());
			classPackageCategoryServiceImpl.update(cp);
		}
		return "success";
	}
	
	/**
	 * 
	 * Class Name: ClassPackageCategoryController.java
	 * @Description: 条件查询分类列表
	 * @author zhang.zx
	 * @date 2016年3月21日 下午5:31:06
	 * @modifier
	 * @modify-date 2016年3月21日 下午5:31:06
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryCategoryList")
	public List<ClassPackageCategory> queryClassCategoryByCondition(ClassPackageCategory search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		return classPackageCategoryServiceImpl.queryClassCategoryLists(search);
	}
	
	/**
	 * 
	 * Class Name: ClassPackageCategoryController.java
	 * @Description: 生成code级别
	 * @author zhang.zx
	 * @date 2016年3月21日 下午6:14:36
	 * @modifier
	 * @modify-date 2016年3月21日 下午6:14:36
	 * @version 1.0
	 * @param parentId
	 * @param parentCode
	 * @return
	 */
	private String productCode(Integer parentId,String parentCode){
		ClassPackageCategory search=new ClassPackageCategory();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		if(null!=parentId){
			search.setParentId(parentId);
		}else{
			search.setParentId(0);
		}
		Integer maxId=classPackageCategoryServiceImpl.queryMaxIdByCondition(search);
		String code="001";
		if(parentCode!=null){
			if(null!=maxId){
				maxId++;
				if(maxId>9){
					code=parentCode+"0"+maxId;
				}else if(maxId>99){
					code=parentCode+maxId;
				}else{
					code=parentCode+"00"+maxId;
				}
			}else{
				code=parentCode+"001";
			}
		}else{
			if(null!=maxId){
				maxId++;
				if(maxId>9){
					code="0"+maxId;
				}else if(maxId>99){
					code=""+maxId;
				}else{
					code="00"+maxId;
				}
			}else{
				code="001";
			}
		}
		return code;
	}
	/**
	 * 
	 * Class Name: ClassPackageCategoryController.java
	 * @Description: 条件查询分类列表
	 * @author chopin.sun
	 * @date 2016年3月21日 下午5:31:06
	 * @modifier
	 * @modify-date 2016年3月21日 下午5:31:06
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryAll")
	public List<ClassPackageCategory> queryAll(){
		return classPackageCategoryServiceImpl.findAll(WebUtils.getCurrentCompanyId());
	}
	
	/**
	 * 
	 * Class Name: ClassPackageCategoryController.java
	 * @Description: 检验分类名称
	 * @author zhang.zx
	 * @date 2016年4月6日 上午10:05:12
	 * @modifier
	 * @modify-date 2016年4月6日 上午10:05:12
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkName")
	public boolean checkClassCategoryName(String name,String mark,Integer id,String code,Integer parentId){
		boolean flag=true;
		ClassPackageCategory search=new ClassPackageCategory();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setName(name);
		search.setParentId(parentId);
		if("add".equals(mark)){
			if(null!=code && !"".equals(code)){
				search.setCode(code);
				if(code.length()==3){
					search.setLeavl("second");
				}else if(code.length()==6){
					search.setLeavl("third");
				}
			}
		}else{
			if(null!=code && !"".equals(code)){
				if(code.length()==3){
					search.setLeavl("first");
				}else if(code.length()==6){
					search.setLeavl("second");
				}else if(code.length()==9){
					search.setLeavl("third");
				}
			}
		}
		List<ClassPackageCategory> data=classPackageCategoryServiceImpl.queryClassCategoryLists(search);
		if(null!=data && data.size()>0){
			if("add".equals(mark)){
				flag=false;
			}else{
				ClassPackageCategory category=classPackageCategoryServiceImpl.findClassPackageCategoryById(id);
				if(name.equals(category.getName())){
					flag=true;
				}else{
					flag=false;
				}
			}
		}
		return flag;
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
