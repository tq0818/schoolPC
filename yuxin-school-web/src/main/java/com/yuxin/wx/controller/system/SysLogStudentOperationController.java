package com.yuxin.wx.controller.system;


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

import com.yuxin.wx.api.system.ISysLogStudentOperationService;
import com.yuxin.wx.model.system.SysLogStudentOperation;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.SysLogStudentOperationVo;

/**
 * Controller of SysLogStudentOperation
 * @author chopin
 * @date 2017-3-24
 */
@Controller
@RequestMapping("/sysLogStudentOperation")
public class SysLogStudentOperationController {
	
	@Autowired
	private ISysLogStudentOperationService sysLogStudentOperationServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysLogStudentOperation search){
		if (search == null) {
			search = new SysLogStudentOperation();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysLogStudentOperationServiceImpl.findSysLogStudentOperationByPage(search));
		return "sysLogStudentOperation/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysLogStudentOperation SysLogStudentOperation) {
		sysLogStudentOperationServiceImpl.insert(SysLogStudentOperation);
		return "redirect:/sysLogStudentOperation";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysLogStudentOperation SysLogStudentOperation) {
		sysLogStudentOperationServiceImpl.update(SysLogStudentOperation);
		return "redirect:/sysLogStudentOperation";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysLogStudentOperationServiceImpl.deleteSysLogStudentOperationById(id);
		return "redirect:/sysLogStudentOperation";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public SysLogStudentOperation getJson(Model model, @PathVariable Integer id){
		return sysLogStudentOperationServiceImpl.findSysLogStudentOperationById(id);
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
	
	
	
	
	@ResponseBody
	@RequestMapping("/getJson")
	public Object getJsons(SysLogStudentOperationVo search,HttpServletRequest request){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<SysLogStudentOperationVo> list = sysLogStudentOperationServiceImpl.findBySearch(search);
		for (SysLogStudentOperationVo sysLogStudentOperationVo : list) {
			long time = sysLogStudentOperationVo.getOperaTime().getTime();
			sysLogStudentOperationVo.setOperaTimemis(time);
		}
		return list;
	}
}
