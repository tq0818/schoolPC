package com.yuxin.wx.controller.system;

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

import com.alibaba.fastjson.JSON;
import com.yuxin.wx.api.system.ISysConfigIndexPageTemplateService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.model.system.SysConfigIndexPageTemplate;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of SysConfigIndexPageTemplate
 * @author chopin
 * @date 2017-3-24
 */
@Controller
@RequestMapping("/sysConfigIndexPageTemplate")
public class SysConfigIndexPageTemplateController {
	
	@Autowired
	private ISysConfigIndexPageTemplateService sysConfigIndexPageTemplateServiceImpl;
	
	@Autowired
	private ISysConfigServiceService sysConfigServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigIndexPageTemplate search){
		if (search == null) {
			search = new SysConfigIndexPageTemplate();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysConfigIndexPageTemplateServiceImpl.findSysConfigIndexPageTemplateByPage(search));
		return "sysConfigIndexPageTemplate/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigIndexPageTemplate SysConfigIndexPageTemplate) {
		sysConfigIndexPageTemplateServiceImpl.insert(SysConfigIndexPageTemplate);
		return "redirect:/sysConfigIndexPageTemplate";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigIndexPageTemplate SysConfigIndexPageTemplate) {
		sysConfigIndexPageTemplateServiceImpl.update(SysConfigIndexPageTemplate);
		return "redirect:/sysConfigIndexPageTemplate";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigIndexPageTemplateServiceImpl.deleteSysConfigIndexPageTemplateById(id);
		return "redirect:/sysConfigIndexPageTemplate";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public SysConfigIndexPageTemplate getJson(Model model, @PathVariable Integer id){
		return sysConfigIndexPageTemplateServiceImpl.findSysConfigIndexPageTemplateById(id);
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
	
	
	@RequestMapping(value="/indexPageConfig")
	public String indexPageConfig(Model model,SysConfigIndexPageTemplate search) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = search.getSchoolId();
		if(schoolId == null) {
			return "404";
		}
		search.setCompanyId(companyId);
		List<SysConfigIndexPageTemplate> list = sysConfigIndexPageTemplateServiceImpl.findBySearch(search);
		if(list.size() == 0) {
			search = new SysConfigIndexPageTemplate();
			search.setCompanyId(0);
			search.setSchoolId(null);
			list = sysConfigIndexPageTemplateServiceImpl.findBySearch(search);
			for (SysConfigIndexPageTemplate scpt : list) {
				scpt.setId(null);
				scpt.setCompanyId(companyId);
				scpt.setSchoolId(schoolId);
				sysConfigIndexPageTemplateServiceImpl.insert(scpt);
			}
		}
		
		model.addAttribute("schoolId", schoolId);
		return "system/indexPageConfig";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getJosnData")
	public Object getJosnData(SysConfigIndexPageTemplate search){
		Map<String,Object> result = new HashMap<String,Object>();
		Integer companyId = WebUtils.getCurrentCompanyId();
		SysConfigService scs = new SysConfigService();
		scs.setCompanyId(companyId);
		scs.setDelFlag(1);
		List<SysConfigService> scses = sysConfigServiceImpl.findSysConfigServiceByCompany(scs);
		
		search.setCompanyId(companyId);
		List<SysConfigIndexPageTemplate> list = sysConfigIndexPageTemplateServiceImpl.findBySearch(search);
		result.put("scses", scses);
		result.put("scpts", list);
		
		return result;
	}
	/**
	 * 重置
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/reset")
	public Object reset(SysConfigIndexPageTemplate search){
		Map<String,Object> result = new HashMap<String,Object>();
		
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = search.getSchoolId();
		search.setCompanyId(companyId);
		List<SysConfigIndexPageTemplate> list = sysConfigIndexPageTemplateServiceImpl.findBySearch(search);
		for (SysConfigIndexPageTemplate s : list) {
			sysConfigIndexPageTemplateServiceImpl.deleteSysConfigIndexPageTemplateById(s.getId());
		}
		
		search = new SysConfigIndexPageTemplate();
		search.setCompanyId(0);
		search.setSchoolId(null);
		list = sysConfigIndexPageTemplateServiceImpl.findBySearch(search);
		for (SysConfigIndexPageTemplate scpt : list) {
			scpt.setId(null);
			scpt.setCompanyId(companyId);
			scpt.setSchoolId(schoolId);
			sysConfigIndexPageTemplateServiceImpl.insert(scpt);
		}
		result.put("flag", true);
		
		return result;
	}
	
	/**
	 * 保存更新
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateSysConfigIndexPage")
	public Object updateSysConfigIndexPage(HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		List<SysConfigIndexPageTemplate> list = JSON.parseArray(request.getParameter("scipts"), SysConfigIndexPageTemplate.class);
		String strs = request.getParameter("deleAdv");
		if(StringUtils.isNotBlank(strs)) {
			String[] ids = strs.split(",");
			for (String s : ids) {
				sysConfigIndexPageTemplateServiceImpl.deleteSysConfigIndexPageTemplateById(Integer.parseInt(s));
			}
		}
		for (SysConfigIndexPageTemplate scipt : list) {
			if(scipt.getId() != null) {
				sysConfigIndexPageTemplateServiceImpl.update(scipt);
			}else {
				scipt.setCompanyId(WebUtils.getCurrentCompanyId());
				sysConfigIndexPageTemplateServiceImpl.insert(scipt);
			}
			
		}
		result.put("flag", true);
		return result;
	}
	
	
	@RequestMapping(value="/preview")
	public Object preview(HttpServletRequest request){
		
		return "system/indexPreview";
	}
	
}
