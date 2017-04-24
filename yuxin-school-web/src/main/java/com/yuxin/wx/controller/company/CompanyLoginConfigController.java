package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.nntp.NewGroupsOrNewsQuery;
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
import com.yuxin.wx.api.company.ICompanyLoginConfigService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.company.CompanyLoginConfig;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyLoginConfig
 * @author chopin
 * @date 2016-7-4
 */
@Controller
@RequestMapping("/companyLoginConfig")
public class CompanyLoginConfigController {
	
	@Autowired
	private ICompanyLoginConfigService companyLoginConfigServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyLoginConfig search){
		if (search == null) {
			search = new CompanyLoginConfig();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyLoginConfigServiceImpl.findCompanyLoginConfigByPage(search));
		return "companyLoginConfig/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyLoginConfig CompanyLoginConfig) {
		companyLoginConfigServiceImpl.insert(CompanyLoginConfig);
		return "redirect:/companyLoginConfig";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyLoginConfig CompanyLoginConfig) {
		companyLoginConfigServiceImpl.update(CompanyLoginConfig);
		return "redirect:/companyLoginConfig";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyLoginConfigServiceImpl.deleteCompanyLoginConfigById(id);
		return "redirect:/companyLoginConfig";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyLoginConfig getJson(Model model, @PathVariable Integer id){
		return companyLoginConfigServiceImpl.findCompanyLoginConfigById(id);
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
	 * Class Name: CompanyLoginConfigController.java
	 * @Description: 第三方登录开关
	 * @author dongshuai
	 * @date 2016年7月5日 下午2:57:50
	 * @modifier
	 * @modify-date 2016年7月5日 下午2:57:50
	 * @version 1.0
	 * @param clc
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveThirdLoginStatus", method = RequestMethod.POST)
	public JSONObject saveThirdLoginStatus(CompanyLoginConfig clc){
		JSONObject json=new JSONObject();
		if(null!=clc && null!=clc.getMultiOnline() && null!=clc.getThirdLoginFlag() && null!=clc.getBingMobile()){
			CompanyLoginConfig search=new CompanyLoginConfig();
			search.setCompanyId(WebUtils.getCurrentCompanyId());
			search=companyLoginConfigServiceImpl.queryByCompanyId(search);
			
			search.setMultiOnline(clc.getMultiOnline());
			search.setThirdLoginFlag(clc.getThirdLoginFlag());
			search.setBingMobile(clc.getBingMobile());
			search.setUseLoginPage(clc.getUseLoginPage());
			
			companyLoginConfigServiceImpl.update(search);
			
			json.put(JsonMsg.MSG,JsonMsg.SUCCESS);
		}else{
			json.put(JsonMsg.MSG,JsonMsg.ERROR);
			return json;
		}
		return json;
	}
	
	/**
	 * 
	 * Class Name: CompanyLoginConfigController.java
	 * @Description: 第三方登录信息配置
	 * @author dongshuai
	 * @date 2016年7月5日 下午2:58:14
	 * @modifier
	 * @modify-date 2016年7月5日 下午2:58:14
	 * @version 1.0
	 * @param clc
	 * @param saveId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveThirdLoginList/{saveId}", method = RequestMethod.POST)
	public JSONObject saveThirdLoginList(CompanyLoginConfig clc,@PathVariable String saveId){
		JSONObject json=new JSONObject();
		CompanyLoginConfig search=new CompanyLoginConfig();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search=companyLoginConfigServiceImpl.queryByCompanyId(search);
		if("qq".equals(saveId)){
			if(null!=clc.getQqAppid() && null!=clc.getQqKey()){
				search.setQqAppid(clc.getQqAppid());
				search.setQqKey(clc.getQqKey());
				if(null!=clc.getQqValidateCode()){
					search.setQqValidateCode(clc.getQqValidateCode());
				}
				search.setQqLogin(1);
			}else{
				json.put(JsonMsg.MSG, JsonMsg.ERROR);
				return json;
			}
		}else if("wx".equals(saveId)){
			if(null!=clc.getWechatAppid() && null!=clc.getWechatKey()){
				search.setWechatAppid(clc.getWechatAppid());
				search.setWechatKey(clc.getWechatKey());
				search.setWechatLogin(1);
			}else{
				json.put(JsonMsg.MSG, JsonMsg.ERROR);
				return json;
			}
			if(StringUtils.isNotBlank(clc.getWechatOpenAppid()))
				search.setWechatOpenAppid(clc.getWechatOpenAppid());
			if(StringUtils.isNotBlank(clc.getWechatOpenSecret()))
				search.setWechatOpenSecret(clc.getWechatOpenSecret());
		}else if("wb".equals(saveId)){
			if(null!=clc.getWeiboSercet() && null!=clc.getWeiboKey() && null!=clc.getWeiboValidateCode()){
				search.setWeiboSercet(clc.getWeiboSercet());
				search.setWeiboKey(clc.getWeiboKey());
				search.setWeiboValidateCode(clc.getWeiboValidateCode());
				search.setWeiboLogin(1);
			}else{
				json.put(JsonMsg.MSG, JsonMsg.ERROR);
				return json;
			}
		}
		companyLoginConfigServiceImpl.update(search);
		json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/delThirdLoginStatus", method = RequestMethod.POST)
	public JSONObject delThirdLoginStatus(String delName){
		JSONObject json=new JSONObject();
		CompanyLoginConfig search=new CompanyLoginConfig();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search=companyLoginConfigServiceImpl.queryByCompanyId(search);
		if("qq".equals(delName)){
			search.setQqLogin(0);
		}else if("wx".equals(delName)){
			search.setWechatLogin(0);
		}else if("wb".equals(delName)){
			search.setWeiboLogin(0);
		}
		companyLoginConfigServiceImpl.update(search);
		json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
		return json;
	}
}
