package com.yuxin.wx.controller.company;

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

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyFootInfoService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyHeadFootConfigService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyNewStepService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysPageHeadFooterTemplateService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFootInfo;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyHeadFootConfig;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysPageHeadFooterTemplate;
import com.yuxin.wx.system.impl.SysPageHeadFooterTemplateServiceImpl;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyHeadFootConfig
 * @author chopin
 * @date 2016-2-29
 */
@Controller
@RequestMapping("/companyHeadFootConfig")
public class CompanyHeadFootConfigController {
	
	@Autowired
	private ICompanyHeadFootConfigService companyHeadFootConfigServiceImpl;
	@Autowired
	private ISysPageHeadFooterTemplateService sysPageHeadFooterTemplateServiceImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	@Autowired
	private ICompanyFootInfoService companyFootInfoServiceImpl;
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private ICompanyFunctionSetService CompanyFunctionSetServiceImpl;
	
	//后台页尾模板
	@RequestMapping(value="/showFootTemplete")
	public String showFootTemplete(Model model){
		Company company=companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		model.addAttribute("company", company);
		//查询所有页尾模板
		List<SysPageHeadFooterTemplate> footTempletes=sysPageHeadFooterTemplateServiceImpl.findFootTempletes("foot");
		model.addAttribute("templetes", footTempletes);
		//查询当前公司的默认模板
		CompanyHeadFootConfig search=new CompanyHeadFootConfig();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setStatus(1);
		CompanyHeadFootConfig companyTemplete=companyHeadFootConfigServiceImpl.findFootConfigByCompany(search);
		model.addAttribute("templete", companyTemplete);
		
		return "system/chooseFootTemplete";
	}
	
	//进入设置模板页面
	@RequestMapping(value="/setTemplate/{id}")
	public String setFootTemplate(@PathVariable Integer id, Model model){
		String url="http://"+propertiesUtil.getProjectImageUrl()+"/";
		Company company=companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		model.addAttribute("company", company);
		SysPageHeadFooterTemplate foot=sysPageHeadFooterTemplateServiceImpl.findSysPageHeadFooterTemplateById(id);
		model.addAttribute("configId", id);
		CompanyFootInfo companyInfo = new CompanyFootInfo();
		companyInfo.setCompanyId(WebUtils.getCurrentCompanyId());
		CompanyFootInfo companyInfo1=companyFootInfoServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		//新增 并给前台设置默认值
		if(null==companyInfo1){								
			companyInfo.setThemes("footer");
			companyFootInfoServiceImpl.insert(companyInfo);
		}else{
			if(null!=companyInfo1.getLogoPic() && !"".equals(companyInfo1.getLogoPic())){
				companyInfo1.setLogoPic(url+companyInfo.getLogoPic());
			}
			if(StringUtils.isNotBlank(companyInfo1.getSecurityIco())){
				companyInfo1.setIcoPath(url+companyInfo1.getSecurityIco());
			}
			//修改主题颜色
			if(companyInfo1.getThemes()==null||"".equals(companyInfo1.getThemes())){
				companyInfo.setThemes("footer");
				companyFootInfoServiceImpl.updateByCompanyId(companyInfo);
			}
			companyInfo=companyInfo1;
		}
		//获取页头主题配置
		CompanyFunctionSet set = new CompanyFunctionSet();
		set.setCompanyId(WebUtils.getCurrentCompanyId());
		set.setFunctionCode("HEAD_THEMES");
		CompanyFunctionSet set1 = CompanyFunctionSetServiceImpl.findCompanyUseCourse(set);
		if(set1==null){
			set.setContent("header-default");
			CompanyFunctionSetServiceImpl.updateByComIdAndCode(set);
		}else{
			if(set1.getContent()==null||"".equals(set1.getContent())){
				set.setContent("header-default");
				CompanyFunctionSetServiceImpl.updateByComIdAndCode(set);
			}
			set=set1;
		}
		model.addAttribute("set",set);
		//查看公司的级别信息
		Integer serviceLeverl=company.getMemberLevel();
		if(serviceLeverl!=null&&serviceLeverl>=40){
			model.addAttribute("companyLevel", "yes");
		}else{
			model.addAttribute("companyLevel", "no");
		}
		model.addAttribute("companyInfo", companyInfo);
		if(null!=foot){
			return foot.getConfigPage();
		}
		return "system/setFootTemplete_sys";
	}

	
	/**
	 * 选择模板信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/chooseTemplate")
	public String chooseCompanyTemplate(HttpServletRequest request){
		List<CompanyHeadFootConfig> temps=JSONObject.parseArray(request.getParameter("temps"),com.yuxin.wx.model.company.CompanyHeadFootConfig.class);
		List<CompanyHeadFootConfig> arr=companyHeadFootConfigServiceImpl.findTemplatesById(WebUtils.getCurrentCompanyId());
		if(null!=arr && arr.size()>0){
			for(CompanyHeadFootConfig foots:arr){
				for(CompanyHeadFootConfig foot:temps){
					if(foots.getTempleteId().equals(foot.getTempleteId()) || foots.getTempleteId()==foot.getTempleteId()){
						foot.setCompanyId(WebUtils.getCurrentCompanyId());
						foot.setId(foots.getId());
						companyHeadFootConfigServiceImpl.update(foot);
					}
				}
			}
		}else{
//			for(CompanyHeadFootConfig foot:temps){
//				foot.setCompanyId(WebUtils.getCurrentCompanyId());
//				companyHeadFootConfigServiceImpl.insert(foot);
//			}
		}
		return "success";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyHeadFootConfig search){
		if (search == null) {
			search = new CompanyHeadFootConfig();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyHeadFootConfigServiceImpl.findCompanyHeadFootConfigByPage(search));
		return "companyHeadFootConfig/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyHeadFootConfig CompanyHeadFootConfig) {
		companyHeadFootConfigServiceImpl.insert(CompanyHeadFootConfig);
		return "redirect:/companyHeadFootConfig";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyHeadFootConfig CompanyHeadFootConfig) {
		companyHeadFootConfigServiceImpl.update(CompanyHeadFootConfig);
		return "redirect:/companyHeadFootConfig";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyHeadFootConfigServiceImpl.deleteCompanyHeadFootConfigById(id);
		return "redirect:/companyHeadFootConfig";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyHeadFootConfig getJson(Model model, @PathVariable Integer id){
		return companyHeadFootConfigServiceImpl.findCompanyHeadFootConfigById(id);
	}
	
	/**
	 * wz
	 * 主题颜色修改
	 * @param content
	 * @return
	 */
	@SuppressWarnings("null")
	@ResponseBody
	@RequestMapping(value="/updateThemes")
	public String updateThemes(String themes){
		CompanyFootInfo companyInfo = new CompanyFootInfo();
		companyInfo.setCompanyId(WebUtils.getCurrentCompanyId());
		CompanyFootInfo companyInfo1=companyFootInfoServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		//新增 并给前台设置默认值
		if(null==companyInfo1){								
			companyInfo.setThemes("footer");
			companyFootInfoServiceImpl.insert(companyInfo);
		}else{
			companyInfo.setId(companyInfo1.getId());
			companyInfo.setSecurityIco(companyInfo1.getSecurityIco());
			companyInfo.setSecurityRegno(companyInfo1.getSecurityRegno());
			companyInfo.setSecurityLink(companyInfo1.getSecurityLink());
			//修改主题颜色
			if(companyInfo1.getThemes()==null||"".equals(companyInfo1.getThemes())){
				companyInfo.setThemes("footer");
				companyFootInfoServiceImpl.updateByCompanyId(companyInfo);
			}else{
				companyInfo.setThemes(themes);
				companyFootInfoServiceImpl.updateByCompanyId(companyInfo);
			}
		}
		return "success";
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
	
	@ResponseBody
	@RequestMapping("/updateHtml")
	public Object updateHtml(){
		
		return null;
	}
}
