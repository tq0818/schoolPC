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

import com.yuxin.wx.api.user.IUserIntegralFlowService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.user.UserIntegralFlow;

/**
 * Controller of UserIntegralFlow
 * @author chopin
 * @date 2016-5-17
 */
@Controller
@RequestMapping("/userIntegralFlow")
public class UserIntegralFlowController {
	
	@Autowired
	private IUserIntegralFlowService userIntegralFlowServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, UserIntegralFlow search){
		if (search == null) {
			search = new UserIntegralFlow();
			// search.setPageSize(20);
		}
		model.addAttribute("list", userIntegralFlowServiceImpl.findUserIntegralFlowByPage(search));
		return "userIntegralFlow/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(UserIntegralFlow UserIntegralFlow) {
		userIntegralFlowServiceImpl.insert(UserIntegralFlow);
		return "redirect:/userIntegralFlow";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(UserIntegralFlow UserIntegralFlow) {
		userIntegralFlowServiceImpl.update(UserIntegralFlow);
		return "redirect:/userIntegralFlow";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		userIntegralFlowServiceImpl.deleteUserIntegralFlowById(id);
		return "redirect:/userIntegralFlow";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public UserIntegralFlow getJson(Model model, @PathVariable Integer id){
		return userIntegralFlowServiceImpl.findUserIntegralFlowById(id);
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
