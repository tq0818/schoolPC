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

import com.yuxin.wx.api.system.ISysServiceDredgeDetailService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.system.SysServiceDredgeDetail;

/**
 * Controller of SysServiceDredgeDetail
 * @author wang.zx
 * @date 2016-12-12
 */
@Controller
@RequestMapping("/sysServiceDredgeDetail")
public class SysServiceDredgeDetailController {
	
	@Autowired
	private ISysServiceDredgeDetailService sysServiceDredgeDetailServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysServiceDredgeDetail search){
		if (search == null) {
			search = new SysServiceDredgeDetail();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysServiceDredgeDetailServiceImpl.findSysServiceDredgeDetailByPage(search));
		return "sysServiceDredgeDetail/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysServiceDredgeDetail SysServiceDredgeDetail) {
		sysServiceDredgeDetailServiceImpl.insert(SysServiceDredgeDetail);
		return "redirect:/sysServiceDredgeDetail";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysServiceDredgeDetail SysServiceDredgeDetail) {
		sysServiceDredgeDetailServiceImpl.update(SysServiceDredgeDetail);
		return "redirect:/sysServiceDredgeDetail";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysServiceDredgeDetailServiceImpl.deleteSysServiceDredgeDetailById(id);
		return "redirect:/sysServiceDredgeDetail";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public SysServiceDredgeDetail getJson(Model model, @PathVariable Integer id){
		return sysServiceDredgeDetailServiceImpl.findSysServiceDredgeDetailById(id);
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
