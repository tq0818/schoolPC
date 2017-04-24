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

import com.yuxin.wx.api.system.ISysUseRecordNoticTaskService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.system.SysUseRecordNoticTask;

/**
 * Controller of SysUseRecordNoticTask
 * @author wang.zx
 * @date 2016-12-1
 */
@Controller
@RequestMapping("/sysUseRecordNoticTask")
public class SysUseRecordNoticTaskController {
	
	@Autowired
	private ISysUseRecordNoticTaskService sysUseRecordNoticTaskServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysUseRecordNoticTask search){
		if (search == null) {
			search = new SysUseRecordNoticTask();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysUseRecordNoticTaskServiceImpl.findSysUseRecordNoticTaskByPage(search));
		return "sysUseRecordNoticTask/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysUseRecordNoticTask SysUseRecordNoticTask) {
		sysUseRecordNoticTaskServiceImpl.insert(SysUseRecordNoticTask);
		return "redirect:/sysUseRecordNoticTask";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysUseRecordNoticTask SysUseRecordNoticTask) {
		sysUseRecordNoticTaskServiceImpl.update(SysUseRecordNoticTask);
		return "redirect:/sysUseRecordNoticTask";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysUseRecordNoticTaskServiceImpl.deleteSysUseRecordNoticTaskById(id);
		return "redirect:/sysUseRecordNoticTask";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public SysUseRecordNoticTask getJson(Model model, @PathVariable Integer id){
		return sysUseRecordNoticTaskServiceImpl.findSysUseRecordNoticTaskById(id);
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
