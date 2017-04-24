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

import com.yuxin.wx.api.system.ISysCcAccountService;
import com.yuxin.wx.model.system.SysCcAccount;

/**
 * Controller of SysCcAccount
 * @author wang.zx
 * @date 2015-6-17
 */
@Controller
@RequestMapping("/sysCcAccount")
public class SysCcAccountController {
	
	@Autowired
	private ISysCcAccountService sysCcAccountServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysCcAccount search){
		if (search == null) {
			search = new SysCcAccount();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysCcAccountServiceImpl.findSysCcAccountByPage(search));
		return "sysCcAccount/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysCcAccount SysCcAccount) {
		sysCcAccountServiceImpl.insert(SysCcAccount);
		return "redirect:/sysCcAccount";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysCcAccount SysCcAccount) {
		sysCcAccountServiceImpl.update(SysCcAccount);
		return "redirect:/sysCcAccount";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysCcAccountServiceImpl.deleteSysCcAccountById(id);
		return "redirect:/sysCcAccount";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysCcAccount getJson(Model model, @PathVariable Integer id){
		return sysCcAccountServiceImpl.findSysCcAccountById(id);
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
