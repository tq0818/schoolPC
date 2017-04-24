package com.yuxin.wx.controller.classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.alibaba.fastjson.JSONArray;
import com.yuxin.wx.api.classes.IClassTypeMemberDiscountService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassTypeMemberDiscount;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.SysPageHeadFootVo;

/**
 * Controller of ClassTypeMemberDiscount
 * @author chopin
 * @date 2016-5-17
 */
@Controller
@RequestMapping("/classTypeMemberDiscount")
public class ClassTypeMemberDiscountController {
	
	@Autowired
	private IClassTypeMemberDiscountService classTypeMemberDiscountServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ClassTypeMemberDiscount search){
		if (search == null) {
			search = new ClassTypeMemberDiscount();
			// search.setPageSize(20);
		}
		model.addAttribute("list", classTypeMemberDiscountServiceImpl.findClassTypeMemberDiscountByPage(search));
		return "classTypeMemberDiscount/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(ClassTypeMemberDiscount ClassTypeMemberDiscount) {
		classTypeMemberDiscountServiceImpl.insert(ClassTypeMemberDiscount);
		return "redirect:/classTypeMemberDiscount";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(ClassTypeMemberDiscount ClassTypeMemberDiscount) {
		classTypeMemberDiscountServiceImpl.update(ClassTypeMemberDiscount);
		return "redirect:/classTypeMemberDiscount";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		classTypeMemberDiscountServiceImpl.deleteClassTypeMemberDiscountById(id);
		return "redirect:/classTypeMemberDiscount";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public ClassTypeMemberDiscount getJson(Model model, @PathVariable Integer id){
		return classTypeMemberDiscountServiceImpl.findClassTypeMemberDiscountById(id);
	}
	
	/**
	 * 添加会员级别
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addMemberLevel")
	public String addCourseMemberLevel(HttpServletRequest request,Integer classTypeId){
		Integer companyId=WebUtils.getCurrentCompanyId();
		List<ClassTypeMemberDiscount> list=JSONArray.parseArray(request.getParameter("list"),ClassTypeMemberDiscount.class);
		return classTypeMemberDiscountServiceImpl.updateClassTypeMember(companyId, classTypeId, list);
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
