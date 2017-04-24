package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

import com.yuxin.wx.api.system.ISysConfigIndexPrivatepageService;
import com.yuxin.wx.model.system.SysConfigIndexPrivatepage;
import com.yuxin.wx.vo.commodity.CommodityVo;

/**
 * Controller of SysConfigIndexPrivatepage
 * @author chopin
 * @date 2015-3-17
 */
@Controller
@RequestMapping("/sysConfigIndexPrivatepage")
public class SysConfigIndexPrivatepageController {
	
	@Autowired
	private ISysConfigIndexPrivatepageService sysConfigIndexPrivatepageServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigIndexPrivatepage search){
		if (search == null) {
			search = new SysConfigIndexPrivatepage();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysConfigIndexPrivatepageServiceImpl.findSysConfigIndexPrivatepageByPage(search));
		return "sysConfigIndexPrivatepage/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigIndexPrivatepage SysConfigIndexPrivatepage) {
		sysConfigIndexPrivatepageServiceImpl.insert(SysConfigIndexPrivatepage);
		return "redirect:/sysConfigIndexPrivatepage";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigIndexPrivatepage SysConfigIndexPrivatepage) {
		sysConfigIndexPrivatepageServiceImpl.update(SysConfigIndexPrivatepage);
		return "redirect:/sysConfigIndexPrivatepage";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigIndexPrivatepageServiceImpl.deleteSysConfigIndexPrivatepageById(id);
		return "redirect:/sysConfigIndexPrivatepage";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigIndexPrivatepage getJson(Model model, @PathVariable Integer id){
		return sysConfigIndexPrivatepageServiceImpl.findSysConfigIndexPrivatepageById(id);
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
