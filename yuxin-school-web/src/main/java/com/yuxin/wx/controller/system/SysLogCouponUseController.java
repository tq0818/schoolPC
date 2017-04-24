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

import com.yuxin.wx.api.system.ISysLogCouponUseService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.system.SysLogCouponUse;

/**
 * Controller of SysLogCouponUse
 * @author chopin
 * @date 2016-6-28
 */
@Controller
@RequestMapping("/sysLogCouponUse")
public class SysLogCouponUseController {
	
	@Autowired
	private ISysLogCouponUseService sysLogCouponUseServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysLogCouponUse search){
		if (search == null) {
			search = new SysLogCouponUse();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysLogCouponUseServiceImpl.findSysLogCouponUseByPage(search));
		return "sysLogCouponUse/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysLogCouponUse SysLogCouponUse) {
		sysLogCouponUseServiceImpl.insert(SysLogCouponUse);
		return "redirect:/sysLogCouponUse";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysLogCouponUse SysLogCouponUse) {
		sysLogCouponUseServiceImpl.update(SysLogCouponUse);
		return "redirect:/sysLogCouponUse";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysLogCouponUseServiceImpl.deleteSysLogCouponUseById(id);
		return "redirect:/sysLogCouponUse";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysLogCouponUse getJson(Model model, @PathVariable Integer id){
		return sysLogCouponUseServiceImpl.findSysLogCouponUseById(id);
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
