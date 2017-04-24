package com.yuxin.wx.controller.classes;

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

import com.yuxin.wx.api.classes.IClassTypeResourceTypeService;
import com.yuxin.wx.model.classes.ClassTypeResourceType;

/**
 * Controller of ClassTypeResourceType
 * @author wang.zx
 * @date 2015-8-11
 */
@Controller
@RequestMapping("/classTypeResourceType")
public class ClassTypeResourceTypeController {
	
	@Autowired
	private IClassTypeResourceTypeService classTypeResourceTypeServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ClassTypeResourceType search){
		if (search == null) {
			search = new ClassTypeResourceType();
			// search.setPageSize(20);
		}
		model.addAttribute("list", classTypeResourceTypeServiceImpl.findClassTypeResourceTypeByPage(search));
		return "classTypeResourceType/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(ClassTypeResourceType ClassTypeResourceType) {
		classTypeResourceTypeServiceImpl.insert(ClassTypeResourceType);
		return "redirect:/classTypeResourceType";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(ClassTypeResourceType ClassTypeResourceType) {
		classTypeResourceTypeServiceImpl.update(ClassTypeResourceType);
		return "redirect:/classTypeResourceType";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		classTypeResourceTypeServiceImpl.deleteClassTypeResourceTypeById(id);
		return "redirect:/classTypeResourceType";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public ClassTypeResourceType getJson(Model model, @PathVariable Integer id){
		return classTypeResourceTypeServiceImpl.findClassTypeResourceTypeById(id);
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
