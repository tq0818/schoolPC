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

import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.api.system.ISysConfigDictService;

/**
 * Controller of SysConfigDict
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/sysConfigDict")
public class SysConfigDictController {
	
	@Autowired
	private ISysConfigDictService sysConfigDictServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigDict search){
		if (search == null) {
			search = new SysConfigDict();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysConfigDictServiceImpl.findSysConfigDictByPage(search));
		return "sysConfigDict/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigDict sysConfigDict) {
		sysConfigDictServiceImpl.insert(sysConfigDict);
		return "redirect:/sysConfigDict";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigDict sysConfigDict) {
		sysConfigDictServiceImpl.update(sysConfigDict);
		return "redirect:/sysConfigDict";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigDictServiceImpl.deleteSysConfigDictById(id);
		return "redirect:/sysConfigDict";
	}
	
	/**
	 * 
	 * Class Name: SysConfigDictController.java
	 * @Description: 查字典
	 * @author Chopin
	 * @date 2014年12月11日 上午12:15:03
	 * @version 1.0
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigDict getJson(Model model, @PathVariable Integer id){
		return sysConfigDictServiceImpl.findSysConfigDictById(id);
	}
	
	/**
	 * 
	 * Class Name: SysConfigDictController.java
	 * @Description: 查字典
	 * @author Chopin
	 * @date 2014年12月11日 上午12:15:03
	 * @version 1.0
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/transe", method = RequestMethod.GET)
	public SysConfigDict transeDict(Model model, SysConfigDict dict){
		SysConfigDict d= sysConfigDictServiceImpl.findSysConfigDictByCode(dict);
		return d;
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
