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

import com.yuxin.wx.api.system.ISysConfigIndexClasstypeService;
import com.yuxin.wx.model.system.SysConfigIndexClasstype;

/**
 * Controller of SysConfigIndexClasstype
 * @author chopin
 * @date 2015-3-17
 */
@Controller
@RequestMapping("/sysConfigIndexClasstype")
public class SysConfigIndexClasstypeController {
	
	@Autowired
	private ISysConfigIndexClasstypeService sysConfigIndexClasstypeServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigIndexClasstype search){
		if (search == null) {
			search = new SysConfigIndexClasstype();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysConfigIndexClasstypeServiceImpl.findSysConfigIndexClasstypeByPage(search));
		return "sysConfigIndexClasstype/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigIndexClasstype SysConfigIndexClasstype) {
		sysConfigIndexClasstypeServiceImpl.insert(SysConfigIndexClasstype);
		return "redirect:/sysConfigIndexClasstype";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigIndexClasstype SysConfigIndexClasstype) {
		sysConfigIndexClasstypeServiceImpl.update(SysConfigIndexClasstype);
		return "redirect:/sysConfigIndexClasstype";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigIndexClasstypeServiceImpl.deleteSysConfigIndexClasstypeById(id);
		return "redirect:/sysConfigIndexClasstype";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigIndexClasstype getJson(Model model, @PathVariable Integer id){
		return sysConfigIndexClasstypeServiceImpl.findSysConfigIndexClasstypeById(id);
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
