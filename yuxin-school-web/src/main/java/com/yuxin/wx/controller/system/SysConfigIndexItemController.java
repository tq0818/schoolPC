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

import com.yuxin.wx.api.system.ISysConfigIndexItemService;
import com.yuxin.wx.model.system.SysConfigIndexItem;

/**
 * Controller of SysConfigIndexItem
 * @author chopin
 * @date 2015-3-17
 */
@Controller
@RequestMapping("/sysConfigIndexItem")
public class SysConfigIndexItemController {
	
	@Autowired
	private ISysConfigIndexItemService sysConfigIndexItemServiceImpl;
	
//	@RequestMapping(method = RequestMethod.GET)
//	public String list(Model model, SysConfigIndexItem search){
//		if (search == null) {
//			search = new SysConfigIndexItem();
//			// search.setPageSize(20);
//		}
//		model.addAttribute("list", sysConfigIndexItemServiceImpl.findSysConfigIndexItemByPage(search));
//		return "sysConfigIndexItem/list";
//	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigIndexItem SysConfigIndexItem) {
		sysConfigIndexItemServiceImpl.insert(SysConfigIndexItem);
		return "redirect:/sysConfigIndexItem";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigIndexItem SysConfigIndexItem) {
		sysConfigIndexItemServiceImpl.update(SysConfigIndexItem);
		return "redirect:/sysConfigIndexItem";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigIndexItemServiceImpl.deleteSysConfigIndexItemById(id);
		return "redirect:/sysConfigIndexItem";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigIndexItem getJson(Model model, @PathVariable Integer id){
		return sysConfigIndexItemServiceImpl.findSysConfigIndexItemById(id);
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
