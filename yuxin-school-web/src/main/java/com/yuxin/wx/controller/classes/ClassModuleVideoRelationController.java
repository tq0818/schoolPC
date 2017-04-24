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

import com.yuxin.wx.model.classes.ClassModuleVideoRelation;
import com.yuxin.wx.api.classes.IClassModuleVideoRelationService;

/**
 * Controller of ClassModuleVideoRelation
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/classModuleVideoRelation")
public class ClassModuleVideoRelationController {
	
	@Autowired
	private IClassModuleVideoRelationService classModuleVideoRelationServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ClassModuleVideoRelation search){
		if (search == null) {
			search = new ClassModuleVideoRelation();
			// search.setPageSize(20);
		}
		model.addAttribute("list", classModuleVideoRelationServiceImpl.findClassModuleVideoRelationByPage(search));
		return "classModuleVideoRelation/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(ClassModuleVideoRelation classModuleVideoRelation) {
		classModuleVideoRelationServiceImpl.insert(classModuleVideoRelation);
		return "redirect:/classModuleVideoRelation";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(ClassModuleVideoRelation classModuleVideoRelation) {
		classModuleVideoRelationServiceImpl.update(classModuleVideoRelation);
		return "redirect:/classModuleVideoRelation";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		classModuleVideoRelationServiceImpl.deleteClassModuleVideoRelationById(id);
		return "redirect:/classModuleVideoRelation";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public ClassModuleVideoRelation getJson(Model model, @PathVariable Integer id){
		return classModuleVideoRelationServiceImpl.findClassModuleVideoRelationById(id);
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
