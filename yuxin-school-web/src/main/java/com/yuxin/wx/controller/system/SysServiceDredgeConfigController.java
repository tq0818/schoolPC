package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.yuxin.wx.api.system.ISysServiceDredgeConfigService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.system.SysServiceDredgeConfig;

/**
 * Controller of SysServiceDredgeConfig
 * @author wang.zx
 * @date 2016-12-12
 */
@Controller
@RequestMapping("/sysServiceDredgeConfig")
public class SysServiceDredgeConfigController {
	
	@Autowired
	private ISysServiceDredgeConfigService sysServiceDredgeConfigServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysServiceDredgeConfig search){
		if (search == null) {
			search = new SysServiceDredgeConfig();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysServiceDredgeConfigServiceImpl.findSysServiceDredgeConfigByPage(search));
		return "sysServiceDredgeConfig/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysServiceDredgeConfig SysServiceDredgeConfig) {
		sysServiceDredgeConfigServiceImpl.insert(SysServiceDredgeConfig);
		return "redirect:/sysServiceDredgeConfig";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysServiceDredgeConfig SysServiceDredgeConfig) {
		sysServiceDredgeConfigServiceImpl.update(SysServiceDredgeConfig);
		return "redirect:/sysServiceDredgeConfig";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysServiceDredgeConfigServiceImpl.deleteSysServiceDredgeConfigById(id);
		return "redirect:/sysServiceDredgeConfig";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public SysServiceDredgeConfig getJson(Model model, @PathVariable Integer id){
		return sysServiceDredgeConfigServiceImpl.findSysServiceDredgeConfigById(id);
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
