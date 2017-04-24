package com.yuxin.wx.controller.user;


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

import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.user.UserHistory;

/**
 * Controller of UserHistory
 * @author chopin
 * @date 2016-9-27
 */
@Controller
@RequestMapping("/userHistory")
public class UserHistoryController {
	
	@Autowired
	private com.yuxin.wx.api.user.IUserHistoryService userHistoryServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, UserHistory search){
		if (search == null) {
			search = new UserHistory();
			// search.setPageSize(20);
		}
		model.addAttribute("list", userHistoryServiceImpl.findUserHistoryByPage(search));
		return "userHistory/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(UserHistory UserHistory) {
		userHistoryServiceImpl.insert(UserHistory);
		return "redirect:/userHistory";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(UserHistory UserHistory) {
		userHistoryServiceImpl.update(UserHistory);
		return "redirect:/userHistory";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		userHistoryServiceImpl.deleteUserHistoryById(id);
		return "redirect:/userHistory";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public UserHistory getJson(Model model, @PathVariable Integer id){
		return userHistoryServiceImpl.findUserHistoryById(id);
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
