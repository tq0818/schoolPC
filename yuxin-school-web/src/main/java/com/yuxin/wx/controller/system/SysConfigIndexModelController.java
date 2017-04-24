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

import com.yuxin.wx.api.system.ISysConfigIndexModelService;
import com.yuxin.wx.model.system.SysConfigIndexModel;

/**
 * Controller of SysConfigIndexModel
 * @author chopin
 * @date 2015-3-17
 */
@Controller
@RequestMapping("/sysConfigIndexModel")
public class SysConfigIndexModelController {
	
	@Autowired
	private ISysConfigIndexModelService sysConfigIndexModelServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigIndexModel search){
		if (search == null) {
			search = new SysConfigIndexModel();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysConfigIndexModelServiceImpl.findSysConfigIndexModelByPage(search));
		return "sysConfigIndexModel/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigIndexModel SysConfigIndexModel) {
		sysConfigIndexModelServiceImpl.insert(SysConfigIndexModel);
		return "redirect:/sysConfigIndexModel";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigIndexModel SysConfigIndexModel) {
		sysConfigIndexModelServiceImpl.update(SysConfigIndexModel);
		return "redirect:/sysConfigIndexModel";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigIndexModelServiceImpl.deleteSysConfigIndexModelById(id);
		return "redirect:/sysConfigIndexModel";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigIndexModel getJson(Model model, @PathVariable Integer id){
		return sysConfigIndexModelServiceImpl.findSysConfigIndexModelById(id);
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
