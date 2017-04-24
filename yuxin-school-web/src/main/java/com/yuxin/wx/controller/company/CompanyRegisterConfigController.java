package com.yuxin.wx.controller.company;

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

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyRegisterConfigService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyRegisterConfig
 * @author chopin
 * @date 2016-7-4
 */
@Controller
@RequestMapping("/companyRegisterConfig")
public class CompanyRegisterConfigController {
	
	@Autowired
	private ICompanyRegisterConfigService companyRegisterConfigServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyRegisterConfig search){
		if (search == null) {
			search = new CompanyRegisterConfig();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyRegisterConfigServiceImpl.findCompanyRegisterConfigByPage(search));
		return "companyRegisterConfig/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyRegisterConfig CompanyRegisterConfig) {
		companyRegisterConfigServiceImpl.insert(CompanyRegisterConfig);
		return "redirect:/companyRegisterConfig";
	}
	@ResponseBody
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public Object update(CompanyRegisterConfig CompanyRegisterConfig) {
		CompanyRegisterConfig.setCompanyId(WebUtils.getCurrentCompanyId());
		companyRegisterConfigServiceImpl.update(CompanyRegisterConfig);
		return true;
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyRegisterConfigServiceImpl.deleteCompanyRegisterConfigById(id);
		return "redirect:/companyRegisterConfig";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyRegisterConfig getJson(Model model, @PathVariable Integer id){
		return companyRegisterConfigServiceImpl.findCompanyRegisterConfigById(id);
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
	
	/**
	 * 
	 * Class Name: CompanyRegisterConfigController.java
	 * @Description: 更新注册配置信息
	 * @author dongshuai
	 * @date 2016年7月6日 下午5:51:55
	 * @modifier
	 * @modify-date 2016年7月6日 下午5:51:55
	 * @version 1.0
	 * @param crc
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveRegisterConfig",method=RequestMethod.POST)
	public JSONObject saveRegisterConfig(CompanyRegisterConfig crc){
		JSONObject json=new JSONObject();
		if(null!=crc && null!=crc.getMobileFlag() && null!=crc.getUsernameFlag() && null!=crc.getCloseFlag()){
			CompanyRegisterConfig search=new CompanyRegisterConfig();
			search.setCompanyId(WebUtils.getCurrentCompanyId());
			search=companyRegisterConfigServiceImpl.queryByCompanyId(search);

			search.setMobileFlag(crc.getMobileFlag());
			search.setUsernameFlag(crc.getUsernameFlag());
			search.setCloseFlag(crc.getCloseFlag());
			
			companyRegisterConfigServiceImpl.update(search);
		}else{
			json.put(JsonMsg.MSG, JsonMsg.ERROR);
			return json;
		}
		json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
		return json;
	}
	
	/**
	 * wz
	 * 注册协议修改协议相关操作
	 * @param crc
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateRegisterConfig",method=RequestMethod.POST)
	public String updateRegisterConfig(CompanyRegisterConfig crc){
		try{
			CompanyRegisterConfig search=new CompanyRegisterConfig();
			if(null!=crc ){
				search.setCompanyId(WebUtils.getCurrentCompanyId());
				search.setRegisterAgreement(crc.getRegisterAgreement());
				search.setRegisterAgreementFlag(crc.getRegisterAgreementFlag());
				companyRegisterConfigServiceImpl.update(search);
			}else{
				CompanyRegisterConfig search1=new CompanyRegisterConfig();
				search.setCompanyId(0);
				search=companyRegisterConfigServiceImpl.queryByCompanyId(search);
				search1.setRegisterAgreement(search.getRegisterAgreement());
				search1.setRegisterAgreementFlag(1);
				search1.setCompanyId(WebUtils.getCurrentCompanyId());
				companyRegisterConfigServiceImpl.update(search1);
			}
		}catch(Exception e){
			return "false";
		}
		return "success";
	}
	/**
	 * wz
	 * 注册协议开启 关闭 相关操作
	 * @param crc
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateRegisterConfigflag",method=RequestMethod.POST)
	public String updateRegisterConfigflag(CompanyRegisterConfig crc){
		try{
			CompanyRegisterConfig search=new CompanyRegisterConfig();
			if(null!=crc ){
				search.setCompanyId(WebUtils.getCurrentCompanyId());
				search.setRegisterAgreementFlag(crc.getRegisterAgreementFlag());
				companyRegisterConfigServiceImpl.update(search);
			}else{
				search=companyRegisterConfigServiceImpl.queryByCompanyId(search);
				search.setRegisterAgreementFlag(1);
				search.setCompanyId(WebUtils.getCurrentCompanyId());
				companyRegisterConfigServiceImpl.update(search);
			}
		}catch(Exception e){
			return "false";
		}
		return "success";
	}
}
