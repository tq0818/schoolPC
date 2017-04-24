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

import com.yuxin.wx.api.system.ISysConfigPageRedirectService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.system.SysConfigPageRedirect;

/**
 * Controller of SysConfigPageRedirect
 * @author wang.zx
 * @date 2015-10-9
 */
@Controller
@RequestMapping("/sysConfigPageRedirect")
public class SysConfigPageRedirectController {
	
	@Autowired
	private ISysConfigPageRedirectService sysConfigPageRedirectServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigPageRedirect search){
		if (search == null) {
			search = new SysConfigPageRedirect();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysConfigPageRedirectServiceImpl.findSysConfigPageRedirectByPage(search));
		return "sysConfigPageRedirect/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigPageRedirect SysConfigPageRedirect) {
		sysConfigPageRedirectServiceImpl.insert(SysConfigPageRedirect);
		return "redirect:/sysConfigPageRedirect";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigPageRedirect SysConfigPageRedirect) {
		sysConfigPageRedirectServiceImpl.update(SysConfigPageRedirect);
		return "redirect:/sysConfigPageRedirect";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigPageRedirectServiceImpl.deleteSysConfigPageRedirectById(id);
		return "redirect:/sysConfigPageRedirect";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigPageRedirect getJson(Model model, @PathVariable Integer id){
		return sysConfigPageRedirectServiceImpl.findSysConfigPageRedirectById(id);
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
