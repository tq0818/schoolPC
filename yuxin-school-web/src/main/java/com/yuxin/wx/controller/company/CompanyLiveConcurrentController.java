package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyLiveConcurrentService;
import com.yuxin.wx.api.company.ICompanyMemberServiceChangelogService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.company.CompanyLiveConcurrent;
import com.yuxin.wx.model.company.CompanyMemberServiceChangelog;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyLiveConcurrent
 * @author wang.zx
 * @date 2016-3-28
 */
@Controller
@RequestMapping("/companyLiveConcurrent")
public class CompanyLiveConcurrentController {
	
	@Autowired
	private ICompanyMemberServiceChangelogService companyMemberServiceChangelogServiceImpl;
	
	@Autowired
	private ICompanyLiveConcurrentService companyLiveConcurrentServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyLiveConcurrent search){
		if (search == null) {
			search = new CompanyLiveConcurrent();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyLiveConcurrentServiceImpl.findCompanyLiveConcurrentByPage(search));
		return "companyLiveConcurrent/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyLiveConcurrent CompanyLiveConcurrent) {
		companyLiveConcurrentServiceImpl.insert(CompanyLiveConcurrent);
		return "redirect:/companyLiveConcurrent";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyLiveConcurrent CompanyLiveConcurrent) {
		companyLiveConcurrentServiceImpl.update(CompanyLiveConcurrent);
		return "redirect:/companyLiveConcurrent";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyLiveConcurrentServiceImpl.deleteCompanyLiveConcurrentById(id);
		return "redirect:/companyLiveConcurrent";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyLiveConcurrent getJson(Model model, @PathVariable Integer id){
		return companyLiveConcurrentServiceImpl.findCompanyLiveConcurrentById(id);
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
	 * Class Name: CompanyLiveConcurrentController.java
	 * @Description: 修改直播并发数
	 * @author 周文斌
	 * @date 2016-3-29 下午3:48:40
	 * @version 1.0
	 * @param request
	 * @param companyId
	 * @param jsonstr
	 * @param basislive
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updatelive")
	public JSONObject updatelive(HttpServletRequest request,Integer companyId,
			String jsonstr,Integer basislive,String liveRemark){
		JSONObject json = new JSONObject();
		Integer userid = WebUtils.getCurrentUserId(request);
		//字符串list化
		List<CompanyLiveConcurrent> clist = null;
		if(!jsonstr.equals("[")){
			clist = JSONObject.parseArray(jsonstr, CompanyLiveConcurrent.class);
		}
		Map<String, Object> param = new HashMap<String, Object>();
		Date date = new Date();
		try {
			/*if(basislive != null){
				//修改基础并发
				param.clear();
				param.put("companyId", companyId);
				param.put("concurrentMax", basislive);
				companyLiveConcurrentServiceImpl.updatelive(param);
				
				CompanyMemberServiceChangelog cmsc = new CompanyMemberServiceChangelog();
				cmsc.setChangeAfter("修改基础并发为:" + basislive);
				cmsc.setTableName("company_live_concurrent");
				cmsc.setColumnName("concurrent_max");
				cmsc.setChangeReason(liveRemark);
				cmsc.setCompanyId(companyId);
				cmsc.setUpdator(userid);
				cmsc.setChangeTime(date);
				companyMemberServiceChangelogServiceImpl.insert(cmsc);
			}*/
			if(clist != null && clist.size() > 0){
				for (CompanyLiveConcurrent c : clist) {
					CompanyLiveConcurrent clc = companyLiveConcurrentServiceImpl.findCompanyLiveConcurrentById(c.getId());
					
					companyLiveConcurrentServiceImpl.update(c);
					
					CompanyMemberServiceChangelog cmsc = new CompanyMemberServiceChangelog();
					cmsc.setChangeBefore("修改:" + clc.getConcurrentMonth() 
							+ ",并发:" + clc.getConcurrentMax());
					cmsc.setChangeAfter("修改临时并发为:" + c.getConcurrentMax());
					cmsc.setTableName("company_live_concurrent");
					cmsc.setColumnName("concurrent_max");
					cmsc.setChangeReason("修改并发数:" + liveRemark);
					cmsc.setCompanyId(companyId);
					cmsc.setUpdator(userid);
					cmsc.setChangeTime(date);
					companyMemberServiceChangelogServiceImpl.insert(cmsc);
				}
			}
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
			return json;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put(JsonMsg.MSG, "修改出现错误:"+e.getMessage());
			return json;
		}
	}
}
