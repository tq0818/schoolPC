package com.yuxin.wx.controller.company;

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

import com.yuxin.wx.api.company.ICompanyInvitConfigTeacherService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.model.company.CompanyInvitConfigTeacher;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyInvitConfigTeacher
 * @author chopin
 * @date 2017-3-13
 */
@Controller
@RequestMapping("/companyInvitConfigTeacher")
public class CompanyInvitConfigTeacherController {
	
	@Autowired
	private ICompanyInvitConfigTeacherService companyInvitConfigTeacherServiceImpl;
	
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyInvitConfigTeacher search){
		if (search == null) {
			search = new CompanyInvitConfigTeacher();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyInvitConfigTeacherServiceImpl.findCompanyInvitConfigTeacherByPage(search));
		return "companyInvitConfigTeacher/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyInvitConfigTeacher CompanyInvitConfigTeacher) {
		companyInvitConfigTeacherServiceImpl.insert(CompanyInvitConfigTeacher);
		return "redirect:/companyInvitConfigTeacher";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyInvitConfigTeacher CompanyInvitConfigTeacher) {
		companyInvitConfigTeacherServiceImpl.update(CompanyInvitConfigTeacher);
		return "redirect:/companyInvitConfigTeacher";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyInvitConfigTeacherServiceImpl.deleteCompanyInvitConfigTeacherById(id);
		return "redirect:/companyInvitConfigTeacher";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public CompanyInvitConfigTeacher getJson(Model model, @PathVariable Integer id){
		return companyInvitConfigTeacherServiceImpl.findCompanyInvitConfigTeacherById(id);
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
	
	@RequestMapping(value="/toConfigTeacherInvitePage")
	public String toConfigTeacherInvitePage(HttpServletRequest request,Model model){
		CompanyInvitConfigTeacher search = new CompanyInvitConfigTeacher();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		List<CompanyInvitConfigTeacher> list = companyInvitConfigTeacherServiceImpl.findCompanyInvitConfigTeacherByPage(search);
		if(list !=null && list.size()>0){
			model.addAttribute("teacherConfig", list.get(0));
			return "company/companyTeacherInviteUpdate";
		}
		return "company/companyTeacherInviteAdd";
	}
	
	@ResponseBody
	@RequestMapping(value="/ajaxCheck",method=RequestMethod.POST)
	public Object ajaxCheck(HttpServletRequest request){
		CompanyInvitConfigTeacher search = new CompanyInvitConfigTeacher();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<CompanyInvitConfigTeacher> list = companyInvitConfigTeacherServiceImpl.findCompanyInvitConfigTeacherByPage(search);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(value="/saveOrUpdateCompanyInvitConfigTeacher",method=RequestMethod.POST)
	public String saveOrUpdateCompanyInvitConfigTeacher(CompanyInvitConfigTeacher teacher,Model model){
		CompanyInvitConfigTeacher search = new CompanyInvitConfigTeacher();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<CompanyInvitConfigTeacher> list = companyInvitConfigTeacherServiceImpl.findCompanyInvitConfigTeacherByPage(search);
		if(list != null && list.size()>0){
			teacher.setId(list.get(0).getId());
			companyInvitConfigTeacherServiceImpl.update(teacher);
			return "success";
		}
		teacher.setCompanyId(WebUtils.getCurrentCompanyId());
		companyInvitConfigTeacherServiceImpl.insert(teacher);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/updateBtn",method=RequestMethod.POST)
	public String updateBtn(HttpServletRequest request,String flag){
		CompanyInvitConfigTeacher search = new CompanyInvitConfigTeacher();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<CompanyInvitConfigTeacher> list = companyInvitConfigTeacherServiceImpl.findCompanyInvitConfigTeacherByPage(search);
		if(list != null && list.size()>0){
			CompanyInvitConfigTeacher configTeacher = list.get(0);
			configTeacher.setOpenFlag(Integer.parseInt(flag));
			companyInvitConfigTeacherServiceImpl.update(configTeacher);
		}
		return "sucesss";
	}
}
