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

import com.yuxin.wx.api.system.ISysConfigIndexCustomService;
import com.yuxin.wx.model.system.SysConfigIndexCustom;

/**
 * Controller of SysConfigIndexCustom
 * @author chopin
 * @date 2015-3-17
 */
@Controller
@RequestMapping("/sysConfigIndexCustom")
public class SysConfigIndexCustomController {
	
	@Autowired
	private ISysConfigIndexCustomService sysConfigIndexCustomServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigIndexCustom search){
		if (search == null) {
			search = new SysConfigIndexCustom();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysConfigIndexCustomServiceImpl.findSysConfigIndexCustomByPage(search));
		return "sysConfigIndexCustom/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigIndexCustom SysConfigIndexCustom) {
		sysConfigIndexCustomServiceImpl.insert(SysConfigIndexCustom);
		return "redirect:/sysConfigIndexCustom";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigIndexCustom SysConfigIndexCustom) {
		sysConfigIndexCustomServiceImpl.update(SysConfigIndexCustom);
		return "redirect:/sysConfigIndexCustom";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigIndexCustomServiceImpl.deleteSysConfigIndexCustomById(id);
		return "redirect:/sysConfigIndexCustom";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigIndexCustom getJson(Model model, @PathVariable Integer id){
		return sysConfigIndexCustomServiceImpl.findSysConfigIndexCustomById(id);
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
