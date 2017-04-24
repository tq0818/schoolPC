package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

import com.yuxin.wx.api.system.ISysPageHeadFooterTemplateService;
import com.yuxin.wx.model.system.SysPageHeadFooterTemplate;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of SysPageHeadFooterTemplate
 * @author chopin
 * @date 2016-2-29
 */
@Controller
@RequestMapping("/sysPageHeadFooterTemplate")
public class SysPageHeadFooterTemplateController {
	
	@Autowired
	private ISysPageHeadFooterTemplateService sysPageHeadFooterTemplateServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysPageHeadFooterTemplate search){
		if (search == null) {
			search = new SysPageHeadFooterTemplate();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysPageHeadFooterTemplateServiceImpl.findSysPageHeadFooterTemplateByPage(search));
		return "sysPageHeadFooterTemplate/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysPageHeadFooterTemplate SysPageHeadFooterTemplate) {
		sysPageHeadFooterTemplateServiceImpl.insert(SysPageHeadFooterTemplate);
		return "redirect:/sysPageHeadFooterTemplate";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysPageHeadFooterTemplate SysPageHeadFooterTemplate) {
		sysPageHeadFooterTemplateServiceImpl.update(SysPageHeadFooterTemplate);
		return "redirect:/sysPageHeadFooterTemplate";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysPageHeadFooterTemplateServiceImpl.deleteSysPageHeadFooterTemplateById(id);
		return "redirect:/sysPageHeadFooterTemplate";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysPageHeadFooterTemplate getJson(Model model, @PathVariable Integer id){
		return sysPageHeadFooterTemplateServiceImpl.findSysPageHeadFooterTemplateById(id);
	}
	
	/**
	 * 
	 * Class Name: SysPageHeadFooterTemplateController.java
	 * @Description: 查询模板下有效导航链接数
	 * @author zhang.zx
	 * @date 2016年4月22日 下午2:23:30
	 * @modifier
	 * @modify-date 2016年4月22日 下午2:23:30
	 * @version 1.0
	 * @param configId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryIsUseingLink")
	public Integer queryIsUseingLink(Integer configId){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("companyId", WebUtils.getCurrentCompanyId());
		map.put("configId", configId);
		return sysPageHeadFooterTemplateServiceImpl.queryIsUseingLink(map);
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
