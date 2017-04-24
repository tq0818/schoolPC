package com.yuxin.wx.controller.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of UsersFront
 * @author chopin
 * @date 2015-5-9
 */
@Controller
@RequestMapping("/usersFront")
public class UsersFrontController {
	
	@Autowired
	private IUsersFrontService usersFrontServiceImpl;
	
	/**
	 * 
	 * Class Name: UsersFrontController.java
	 * @Description: 条件查询用户数量
	 * @author zhang.zx
	 * @date 2016年6月21日 下午4:14:17
	 * @modifier
	 * @modify-date 2016年6月21日 下午4:14:17
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryNumByType")
	public Integer queryUsersNumByType(UsersFront search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setStatus(1);
		List<UsersFront> list=usersFrontServiceImpl.findConponsUsersByCondition(search);
		if(null!=list && list.size()>0){
			return list.size();
		}
		return 0;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, UsersFront search){
		if (search == null) {
			search = new UsersFront();
			// search.setPageSize(20);
		}
		model.addAttribute("list", usersFrontServiceImpl.findUsersFrontByPage(search));
		return "usersFront/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(UsersFront UsersFront) {
		usersFrontServiceImpl.insert(UsersFront);
		return "redirect:/usersFront";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(UsersFront UsersFront) {
		usersFrontServiceImpl.update(UsersFront);
		return "redirect:/usersFront";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		usersFrontServiceImpl.deleteUsersFrontById(id);
		return "redirect:/usersFront";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public UsersFront getJson(Model model, @PathVariable Integer id){
		return usersFrontServiceImpl.findUsersFrontById(id);
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
