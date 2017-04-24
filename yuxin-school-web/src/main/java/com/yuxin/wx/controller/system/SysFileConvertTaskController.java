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

import com.yuxin.wx.api.system.ISysFileConvertTaskService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.system.SysFileConvertTask;

/**
 * Controller of SysFileConvertTask
 * @author wang.zx
 * @date 2016-10-31
 */
@Controller
@RequestMapping("/sysFileConvertTask")
public class SysFileConvertTaskController {
	
	@Autowired
	private ISysFileConvertTaskService sysFileConvertTaskServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysFileConvertTask search){
		if (search == null) {
			search = new SysFileConvertTask();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysFileConvertTaskServiceImpl.findSysFileConvertTaskByPage(search));
		return "sysFileConvertTask/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysFileConvertTask SysFileConvertTask) {
		sysFileConvertTaskServiceImpl.insert(SysFileConvertTask);
		return "redirect:/sysFileConvertTask";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysFileConvertTask SysFileConvertTask) {
		sysFileConvertTaskServiceImpl.update(SysFileConvertTask);
		return "redirect:/sysFileConvertTask";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysFileConvertTaskServiceImpl.deleteSysFileConvertTaskById(id);
		return "redirect:/sysFileConvertTask";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysFileConvertTask getJson(Model model, @PathVariable Integer id){
		return sysFileConvertTaskServiceImpl.findSysFileConvertTaskById(id);
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
