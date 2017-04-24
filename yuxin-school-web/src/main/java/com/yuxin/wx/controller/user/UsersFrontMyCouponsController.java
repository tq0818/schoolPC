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

import com.yuxin.wx.api.user.IUsersFrontMyCouponsService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.user.UsersFrontMyCoupons;

/**
 * Controller of UsersFrontMyCoupons
 * @author chopin
 * @date 2016-6-20
 */
@Controller
@RequestMapping("/usersFrontMyCoupons")
public class UsersFrontMyCouponsController {
	
	@Autowired
	private IUsersFrontMyCouponsService usersFrontMyCouponsServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, UsersFrontMyCoupons search){
		if (search == null) {
			search = new UsersFrontMyCoupons();
			// search.setPageSize(20);
		}
		model.addAttribute("list", usersFrontMyCouponsServiceImpl.findUsersFrontMyCouponsByPage(search));
		return "usersFrontMyCoupons/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(UsersFrontMyCoupons UsersFrontMyCoupons) {
		usersFrontMyCouponsServiceImpl.insert(UsersFrontMyCoupons);
		return "redirect:/usersFrontMyCoupons";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(UsersFrontMyCoupons UsersFrontMyCoupons) {
		usersFrontMyCouponsServiceImpl.update(UsersFrontMyCoupons);
		return "redirect:/usersFrontMyCoupons";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		usersFrontMyCouponsServiceImpl.deleteUsersFrontMyCouponsById(id);
		return "redirect:/usersFrontMyCoupons";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public UsersFrontMyCoupons getJson(Model model, @PathVariable Integer id){
		return usersFrontMyCouponsServiceImpl.findUsersFrontMyCouponsById(id);
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
