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

import com.yuxin.wx.model.classes.ClassTypeRemoteRelation;
import com.yuxin.wx.api.classes.IClassTypeRemoteRelationService;

/**
 * Controller of ClassTypeRemoteRelation
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/classTypeRemoteRelation")
public class ClassTypeRemoteRelationController {
	
	@Autowired
	private IClassTypeRemoteRelationService classTypeRemoteRelationServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ClassTypeRemoteRelation search){
		if (search == null) {
			search = new ClassTypeRemoteRelation();
			// search.setPageSize(20);
		}
		model.addAttribute("list", classTypeRemoteRelationServiceImpl.findClassTypeRemoteRelationByPage(search));
		return "classTypeRemoteRelation/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(ClassTypeRemoteRelation classTypeRemoteRelation) {
		classTypeRemoteRelationServiceImpl.insert(classTypeRemoteRelation);
		return "redirect:/classTypeRemoteRelation";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(ClassTypeRemoteRelation classTypeRemoteRelation) {
		classTypeRemoteRelationServiceImpl.update(classTypeRemoteRelation);
		return "redirect:/classTypeRemoteRelation";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		classTypeRemoteRelationServiceImpl.deleteClassTypeRemoteRelationById(id);
		return "redirect:/classTypeRemoteRelation";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public ClassTypeRemoteRelation getJson(Model model, @PathVariable Integer id){
		return classTypeRemoteRelationServiceImpl.findClassTypeRemoteRelationById(id);
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
