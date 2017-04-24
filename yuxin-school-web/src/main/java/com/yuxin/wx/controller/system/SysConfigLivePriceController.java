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

import com.yuxin.wx.api.system.ISysConfigLivePriceService;
import com.yuxin.wx.model.system.SysConfigLivePrice;

/**
 * Controller of SysConfigLivePrice
 * @author wang.zx
 * @date 2015-4-28
 */
@Controller
@RequestMapping("/sysConfigLivePrice")
public class SysConfigLivePriceController {
	
	@Autowired
	private ISysConfigLivePriceService sysConfigLivePriceServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigLivePrice search){
		if (search == null) {
			search = new SysConfigLivePrice();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysConfigLivePriceServiceImpl.findSysConfigLivePriceByPage(search));
		return "sysConfigLivePrice/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigLivePrice SysConfigLivePrice) {
		sysConfigLivePriceServiceImpl.insert(SysConfigLivePrice);
		return "redirect:/sysConfigLivePrice";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigLivePrice SysConfigLivePrice) {
		sysConfigLivePriceServiceImpl.update(SysConfigLivePrice);
		return "redirect:/sysConfigLivePrice";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigLivePriceServiceImpl.deleteSysConfigLivePriceById(id);
		return "redirect:/sysConfigLivePrice";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigLivePrice getJson(Model model, @PathVariable Integer id){
		return sysConfigLivePriceServiceImpl.findSysConfigLivePriceById(id);
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
