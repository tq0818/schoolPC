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

import com.alibaba.fastjson.JSONArray;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysConfigSeoService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysConfigSeo;
import com.yuxin.wx.model.system.SysPageHeadFoot;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.SysPageHeadFootVo;

/**
 * Controller of SysConfigSeo
 * @author chopin
 * @date 2015-12-3
 */
@Controller
@RequestMapping("/sysConfigSeo")
public class SysConfigSeoController {
	
	@Autowired
	private ISysConfigSeoService sysConfigSeoServiceImpl;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	/**
	 * 
	 * Class Name: SysConfigSeoController.java
	 * @Description: 进入seo配置页面
	 * @author zhang.zx
	 * @date 2015年12月3日 下午4:36:45
	 * @modifier
	 * @modify-date 2015年12月3日 下午4:36:45
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/configSeo/{type}")
	public String configSeo(@PathVariable String type, Model model){
		SysConfigSeo search=new SysConfigSeo();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setType(type);
		List<SysConfigSeo> list=sysConfigSeoServiceImpl.queryAllSeoContents(search);
		if(null!=list&&list.size()>0){
			SysConfigSeo seo=list.get(0);
			model.addAttribute("seo", seo);
		}
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		model.addAttribute("type", type);
		return "system/systemCompanySeo";
	}
	
	/**
	 * 
	 * Class Name: SysConfigSeoController.java
	 * @Description: 加载数据
	 * @author zhang.zx
	 * @date 2015年12月3日 下午4:38:06
	 * @modifier
	 * @modify-date 2015年12月3日 下午4:38:06
	 * @version 1.0
	 * @param model
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryData")
	public List<SysConfigSeo> list(SysConfigSeo search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<SysConfigSeo> list=sysConfigSeoServiceImpl.queryAllSeoContents(search);
		return list;
	}
	
	/**
	 * 
	 * Class Name: SysConfigSeoController.java
	 * @Description: 添加或修改seo
	 * @author zhang.zx
	 * @date 2015年12月28日 上午11:40:01
	 * @modifier
	 * @modify-date 2015年12月28日 上午11:40:01
	 * @version 1.0
	 * @param seo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addSeo")
	public String addSeoSet(SysConfigSeo seo){
		seo.setCompanyId(WebUtils.getCurrentCompanyId());
		seo.setCreateTime(new Date());
		seo.setUpdator(WebUtils.getCurrentUser().getId());
		seo.setUpdateTime(new Date());
		List<SysConfigSeo> list=sysConfigSeoServiceImpl.queryAllSeoContents(seo);
		if(null!=list&&list.size()>0){
			seo.setId(list.get(0).getId());
			sysConfigSeoServiceImpl.update(seo);
		}else{
			sysConfigSeoServiceImpl.insert(seo);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/add")
	public Integer add(SysConfigSeo sysConfigSeo) {
		sysConfigSeo.setCompanyId(WebUtils.getCurrentCompanyId());
		sysConfigSeo.setCreateTime(new Date());
		if(null!=sysConfigSeo&&null!=sysConfigSeo.getType()&&!"".equals(sysConfigSeo.getType())){
			if("SEO_DESCRIPTION".equals(sysConfigSeo.getType())){
				List<SysConfigSeo> list=sysConfigSeoServiceImpl.queryAllSeoContents(sysConfigSeo);
				if(null!=list&&list.size()>0){
					sysConfigSeo.setId(list.get(0).getId());
					sysConfigSeoServiceImpl.update(sysConfigSeo);
				}else{
					sysConfigSeoServiceImpl.insert(sysConfigSeo);
				}
				return 0;
			}
		}
		sysConfigSeoServiceImpl.insert(sysConfigSeo);
		return sysConfigSeo.getId();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(SysConfigSeo SysConfigSeo) {
		sysConfigSeoServiceImpl.update(SysConfigSeo);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/del/{id}")
	public String del(Model model, @PathVariable Integer id) {
		sysConfigSeoServiceImpl.deleteSysConfigSeoById(id);
		return "success";
	}
	
	//排序
	@ResponseBody
	@RequestMapping(value="/orderSortSeo")
	public String orderSortSeo(HttpServletRequest request){
		List<SysConfigSeo> arr=JSONArray.parseArray(request.getParameter("list"),SysConfigSeo.class);
		for(SysConfigSeo seo:arr){
			SysConfigSeo s=new SysConfigSeo();
			s.setId(seo.getId());
			sysConfigSeoServiceImpl.update(s);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigSeo getJson(Model model, @PathVariable Integer id){
		return sysConfigSeoServiceImpl.findSysConfigSeoById(id);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/editSeoConfig")
	public String editSeoConfig(SysConfigSeo sysConfigSeo, Model model){
		sysConfigSeo.setCompanyId(WebUtils.getCurrentCompanyId());
		sysConfigSeo.setCreateTime(new Date());
		List<SysConfigSeo> list=sysConfigSeoServiceImpl.queryAllSeoContents(sysConfigSeo);
		if(null!=list&&list.size()>0){
			sysConfigSeo.setId(list.get(0).getId());
			sysConfigSeoServiceImpl.update(sysConfigSeo);
		}else{
			sysConfigSeoServiceImpl.insert(sysConfigSeo);
		}
		return "";
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
