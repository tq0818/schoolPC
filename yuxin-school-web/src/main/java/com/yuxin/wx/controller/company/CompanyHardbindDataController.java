package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyHardbindDataService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyHardbindData;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyHardbindData
 * @author chopin
 * @date 2016-9-1
 */
@Controller
@RequestMapping("/companyHardbindData")
public class CompanyHardbindDataController {
	
	@Autowired
	private ICompanyHardbindDataService companyHardbindDataServiceImpl;
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyHardbindData search){
		if (search == null) {
			search = new CompanyHardbindData();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyHardbindDataServiceImpl.findCompanyHardbindDataByPage(search));
		return "companyHardbindData/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyHardbindData CompanyHardbindData) {
		companyHardbindDataServiceImpl.insert(CompanyHardbindData);
		return "redirect:/companyHardbindData";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyHardbindData CompanyHardbindData) {
		companyHardbindDataServiceImpl.update(CompanyHardbindData);
		return "redirect:/companyHardbindData";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyHardbindDataServiceImpl.deleteCompanyHardbindDataById(id);
		return "redirect:/companyHardbindData";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyHardbindData getJson(Model model, @PathVariable Integer id){
		return companyHardbindDataServiceImpl.findCompanyHardbindDataById(id);
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
	
	@RequestMapping("/toList")
	public String toListPage(Model model,HttpServletRequest request){
		//获取公司配置
		int companyId=WebUtils.getCurrentCompanyId();
		Company company=companyServiceImpl.findCompanyById(companyId);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("company", company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
				
		//-----2016-9-20  查询学生max 是否开启 (1 开启 0 关闭)
		CompanyFunctionSet cfs = new CompanyFunctionSet();
		cfs.setCompanyId(WebUtils.getCurrentCompanyId());
		cfs.setStatus("1");
		cfs.setFunctionCode("COMPANY_STUDENT_BIND");
		cfs = companyFunctionSetServiceImpl.getCompanyFunctionSet(cfs);
		if(cfs != null){
				model.addAttribute("bind_status","1");
		}else{
			model.addAttribute("bind_status","0");
		}
		return "company/companyHardbindDataList2";
	}
	
	@ResponseBody
	@RequestMapping(value="/queryList",method=RequestMethod.POST)
	public PageFinder<CompanyHardbindData> queryList(Model model,CompanyHardbindData search){
		Integer currentCompanyId = WebUtils.getCurrentCompanyId();
		search.setCompanyId(currentCompanyId);
		List<CompanyHardbindData> list = companyHardbindDataServiceImpl.findCompanyHardbindDataByPage(search);
		Integer count = companyHardbindDataServiceImpl.findCompanyHardbindDataCountByPage(search);
		PageFinder<CompanyHardbindData> pageFinder = new PageFinder<CompanyHardbindData>(search.getPage(), search.getPageSize(), count, list);
		return pageFinder;
	}
	
	@ResponseBody
	@RequestMapping(value="/findById",method=RequestMethod.POST)
	public  CompanyHardbindData findById(Model model,CompanyHardbindData search){
		Integer currentCompanyId = WebUtils.getCurrentCompanyId();
		search.setCompanyId(currentCompanyId);
		return companyHardbindDataServiceImpl.findCompanyHardbindDataById(search.getId());
		
	}
	
	@ResponseBody
	@RequestMapping(value="/updateItem",method=RequestMethod.POST)
	public  String updateItem(Model model,CompanyHardbindData search){
		
		companyHardbindDataServiceImpl.update(search);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/insertItem",method=RequestMethod.POST)
	public  String insertItem(Model model,CompanyHardbindData search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		companyHardbindDataServiceImpl.insert(search);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteItem",method=RequestMethod.POST)
	public  String deleteItem(Model model,CompanyHardbindData search){
		companyHardbindDataServiceImpl.deleteCompanyHardbindDataById(search.getId());
		return "success";
	}
}
