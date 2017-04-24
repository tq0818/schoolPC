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

import com.yuxin.wx.api.system.ISysRegisterRequestLogService;
import com.yuxin.wx.model.system.SysRegisterRequestLog;

/**
 * Controller of SysRegisterRequestLog
 * @author chopin
 * @date 2015-11-3
 */
@Controller
@RequestMapping("/sysRegisterRequestLog")
public class SysRegisterRequestLogController {
	
	@Autowired
	private ISysRegisterRequestLogService sysRegisterRequestLogServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysRegisterRequestLog search){
		if (search == null) {
			search = new SysRegisterRequestLog();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysRegisterRequestLogServiceImpl.findSysRegisterRequestLogByPage(search));
		return "sysRegisterRequestLog/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysRegisterRequestLog SysRegisterRequestLog) {
		sysRegisterRequestLogServiceImpl.insert(SysRegisterRequestLog);
		return "redirect:/sysRegisterRequestLog";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysRegisterRequestLog SysRegisterRequestLog) {
		sysRegisterRequestLogServiceImpl.update(SysRegisterRequestLog);
		return "redirect:/sysRegisterRequestLog";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysRegisterRequestLogServiceImpl.deleteSysRegisterRequestLogById(id);
		return "redirect:/sysRegisterRequestLog";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysRegisterRequestLog getJson(Model model, @PathVariable Integer id){
		return sysRegisterRequestLogServiceImpl.findSysRegisterRequestLogById(id);
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
