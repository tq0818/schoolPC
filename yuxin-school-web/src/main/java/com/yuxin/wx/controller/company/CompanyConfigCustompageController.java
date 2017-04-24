package com.yuxin.wx.controller.company;

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
import com.yuxin.wx.api.company.ICompanyConfigCustompageService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyConfigCustompage;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysPageHeadFoot;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.SysPageHeadFootVo;

/**
 * Controller of CompanyConfigCustompage
 * @author chopin
 * @date 2016-4-25
 */
@Controller
@RequestMapping("/companyConfigCustompage")
public class CompanyConfigCustompageController {
	
	@Autowired
	private ICompanyConfigCustompageService companyConfigCustompageServiceImpl;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageController.java
	 * @Description: 自定义页面
	 * @author zhang.zx
	 * @date 2016年4月25日 下午3:50:47
	 * @modifier
	 * @modify-date 2016年4月25日 下午3:50:47
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/customPage")
	public String customPage(Model model){
		Integer companyId=WebUtils.getCurrentCompanyId();
		Company company=companyService.findCompanyById(companyId);
		if(company == null || "".equals(company)){
			return null;
		}
		model.addAttribute("company", company);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "system/customPage";
	}
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageController.java
	 * @Description: 自定义页面管理
	 * @author zhang.zx
	 * @date 2016年4月25日 下午4:13:35
	 * @modifier
	 * @modify-date 2016年4月25日 下午4:13:35
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/customList")
	public PageFinder<CompanyConfigCustompage> queryCustomList(CompanyConfigCustompage search){
		Integer companyId=WebUtils.getCurrentCompanyId();
		search.setCompanyId(companyId);
		PageFinder<CompanyConfigCustompage> pageFinder=companyConfigCustompageServiceImpl.queryCompanyCustomList(search);
		return pageFinder;
	}
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageController.java
	 * @Description: 进入管理页
	 * @author zhang.zx
	 * @date 2016年4月25日 下午4:03:56
	 * @modifier
	 * @modify-date 2016年4月25日 下午4:03:56
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/managePage/{id}")
	public String manageCustomPage(@PathVariable Integer id,Model model){
		//编辑
		if(null!=id && id>0){
			CompanyConfigCustompage customPage=companyConfigCustompageServiceImpl.findCompanyConfigCustompageById(id);
			model.addAttribute("customPage", customPage);
		}
		Integer companyId=WebUtils.getCurrentCompanyId();
		Company company=companyService.findCompanyById(companyId);
		if(company == null || "".equals(company)){
			return null;
		}
		model.addAttribute("company", company);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "system/customPageManage";
	}
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageController.java
	 * @Description: 添加自定义
	 * @author zhang.zx
	 * @date 2016年4月26日 下午2:44:35
	 * @modifier
	 * @modify-date 2016年4月26日 下午2:44:35
	 * @version 1.0
	 * @param CompanyConfigCustompage
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addCustomPage", method = RequestMethod.POST)
	public CompanyConfigCustompage add(CompanyConfigCustompage customPage,HttpServletRequest request) {
		customPage.setCompanyId(WebUtils.getCurrentCompanyId());
		customPage.setCreateTime(new Date());
		customPage.setCreater(WebUtils.getCurrentUserId(request));
		customPage.setUpdateTime(new Date());
		customPage.setUpdator(WebUtils.getCurrentUserId(request));
		companyConfigCustompageServiceImpl.insert(customPage);
		return companyConfigCustompageServiceImpl.findCompanyConfigCustompageById(customPage.getId());
	}
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageController.java
	 * @Description: 修改自定义页面
	 * @author zhang.zx
	 * @date 2016年4月26日 下午2:46:14
	 * @modifier
	 * @modify-date 2016年4月26日 下午2:46:14
	 * @version 1.0
	 * @param CompanyConfigCustompage
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateCustomPage", method = RequestMethod.POST)
	public CompanyConfigCustompage update(CompanyConfigCustompage customPage,HttpServletRequest request) {
		customPage.setCompanyId(WebUtils.getCurrentCompanyId());
		customPage.setUpdateTime(new Date());
		customPage.setUpdator(WebUtils.getCurrentUserId(request));
		companyConfigCustompageServiceImpl.update(customPage);
		return companyConfigCustompageServiceImpl.findCompanyConfigCustompageById(customPage.getId());
	}
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageController.java
	 * @Description: 编辑自定义页面
	 * @author zhang.zx
	 * @date 2016年4月27日 下午4:58:54
	 * @modifier
	 * @modify-date 2016年4月27日 下午4:58:54
	 * @version 1.0
	 * @param customPage
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editCustom_page")
	public CompanyConfigCustompage manageCustomPage(CompanyConfigCustompage customPage,HttpServletRequest request){
		CompanyConfigCustompage customContent=companyConfigCustompageServiceImpl.findCompanyConfigCustompageById(customPage.getId());
		customPage.setCompanyId(WebUtils.getCurrentCompanyId());
		customPage.setStatus(1);
		customPage.setUpdateTime(new Date());
		customPage.setUpdator(WebUtils.getCurrentUserId(request));
		if(null!=customContent){
			customPage.setId(customContent.getId());
			companyConfigCustompageServiceImpl.update(customPage);
		}else{
			//查询当前模板的数量
			Integer count=companyConfigCustompageServiceImpl.templeteCount(customPage);
			customPage.setSort(count++);
			customPage.setCreateTime(new Date());
			customPage.setCreater(WebUtils.getCurrentUserId(request));
			companyConfigCustompageServiceImpl.insert(customPage);
		}
		
		//如果为分组模板，更新顺序
		List<CompanyConfigCustompage> arr=JSONArray.parseArray(request.getParameter("list"),CompanyConfigCustompage.class);
		if(null!=arr && arr.size()>0){
			for(CompanyConfigCustompage cus:arr){
				companyConfigCustompageServiceImpl.update(cus);
			}
		}
		return companyConfigCustompageServiceImpl.findCompanyConfigCustompageById(customPage.getId());
	}
	
	@ResponseBody
	@RequestMapping(value="/delCustomPage/{id}")
	public String del(Model model, @PathVariable Integer id) {
		companyConfigCustompageServiceImpl.deleteCompanyConfigCustompageById(id);
		return "success";
	}
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageController.java
	 * @Description: 自定义页面排序
	 * @author zhang.zx
	 * @date 2016年4月26日 下午2:52:37
	 * @modifier
	 * @modify-date 2016年4月26日 下午2:52:37
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/orderCustomPage")
	public String orderCustomPage(HttpServletRequest request){
		List<CompanyConfigCustompage> arr=JSONArray.parseArray(request.getParameter("list"),CompanyConfigCustompage.class);
		for(CompanyConfigCustompage cus:arr){
			CompanyConfigCustompage s=new CompanyConfigCustompage();
			s.setId(cus.getId());
			s.setSort(cus.getSort());
			companyConfigCustompageServiceImpl.update(s);
		}
		return "success";
	}
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageController.java
	 * @Description: 分组查询页面列表
	 * @author zhang.zx
	 * @date 2016年4月27日 下午8:56:37
	 * @modifier
	 * @modify-date 2016年4月27日 下午8:56:37
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryCustomPageListById")
	public List<CompanyConfigCustompage> queryCustomPageData(CompanyConfigCustompage search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setStatus(1);
		return companyConfigCustompageServiceImpl.queryCompanyCustomListByCondition(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyConfigCustompage getJson(Model model, @PathVariable Integer id){
		return companyConfigCustompageServiceImpl.findCompanyConfigCustompageById(id);
	}
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageController.java
	 * @Description: 自定义预览
	 * @author zhang.zx
	 * @date 2016年4月27日 上午11:28:50
	 * @modifier
	 * @modify-date 2016年4月27日 上午11:28:50
	 * @version 1.0
	 * @param model
	 * @param name
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/page/{name}")
	public String page(Model model,@PathVariable String name, HttpServletRequest request){
		Company company=companyService.findCompanyById(WebUtils.getCurrentCompanyId());
		model.addAttribute("company", company);
		if("showTemplate2".equals(name)){
			return "system/page/customPage2";
		}
		return "system/page/customPage";
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
