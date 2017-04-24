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

import com.yuxin.wx.api.classes.IClassModuleNoOnsaleService;
import com.yuxin.wx.model.classes.ClassModuleNoOnsale;

/**
 * Controller of ClassModuleNoOnsale
 * @author wang.zx
 * @date 2015-8-14
 */
@Controller
@RequestMapping("/classModuleNoOnsale")
public class ClassModuleNoOnsaleController {
	
	@Autowired
	private IClassModuleNoOnsaleService classModuleNoOnsaleServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ClassModuleNoOnsale search){
		if (search == null) {
			search = new ClassModuleNoOnsale();
			// search.setPageSize(20);
		}
		model.addAttribute("list", classModuleNoOnsaleServiceImpl.findClassModuleNoOnsaleByPage(search));
		return "classModuleNoOnsale/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(ClassModuleNoOnsale ClassModuleNoOnsale) {
		classModuleNoOnsaleServiceImpl.insert(ClassModuleNoOnsale);
		return "redirect:/classModuleNoOnsale";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(ClassModuleNoOnsale ClassModuleNoOnsale) {
		classModuleNoOnsaleServiceImpl.update(ClassModuleNoOnsale);
		return "redirect:/classModuleNoOnsale";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		classModuleNoOnsaleServiceImpl.deleteClassModuleNoOnsaleById(id);
		return "redirect:/classModuleNoOnsale";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public ClassModuleNoOnsale getJson(Model model, @PathVariable Integer id){
		return classModuleNoOnsaleServiceImpl.findClassModuleNoOnsaleById(id);
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
