package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.util.JSONPObject;
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

import com.yuxin.wx.api.system.ISysNewsService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.system.SysNews;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.commodity.CommodityVo;
import com.yuxin.wx.vo.company.CompanyFunctionSetVo;
import com.yuxin.wx.vo.system.SysNewsVo;

/**
 * Controller of SysNews
 * @author wang.zx
 * @date 2015-3-14
 */
@Controller
@RequestMapping("/sysNews")
public class SysNewsController {
	
	@Autowired
	private ISysNewsService sysNewsServiceImpl;
	
	
	@RequestMapping(value="/systemSet")
	public String showSystemSet(Model model){
		
		return "system/systemConfigSet";
	}
	
	//查询公司配置功能
	@ResponseBody
	@RequestMapping(value="/queryFunctions",method=RequestMethod.POST)
	public List<CompanyFunctionSetVo> queryCompanyFunctionSet(){
		Integer companyId=WebUtils.getCurrentCompanyId();
		return sysNewsServiceImpl.queryCompanyFunctionById(companyId);
	}
	
	@ResponseBody
	@RequestMapping(value="/editCompanyFunctions")
	public String editCompanyFunctionsSet(CompanyFunctionSetVo fun){
		Integer companyId=WebUtils.getCurrentCompanyId();
		fun.setCompanyId(companyId);
		sysNewsServiceImpl.editCompanyFunctions(fun);
		return "success";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysNews sysNews){
		if (sysNews == null) {
			sysNews = new SysNews();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysNewsServiceImpl.findSysNewsByPage(sysNews));
		return "sysNews/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysNews sysNews) {
		sysNewsServiceImpl.insert(sysNews);
		return "redirect:/sysNews";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysNews SysNews) {
		sysNewsServiceImpl.update(SysNews);
		return "redirect:/sysNews";
	}
	
	@ResponseBody
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysNewsServiceImpl.deleteSysNewsById(id);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysNews getJson(Model model, @PathVariable Integer id){
		return sysNewsServiceImpl.findSysNewsById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/loadData", method = RequestMethod.POST)
	public List<SysNewsVo> loadData(HttpServletRequest request,SysNewsVo search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		if(StringUtils.isBlank(search.getCusorder())) {
			search.setCusorder("sn.publish_time desc");
		}else {
			search.setCusorder(search.getCusorder() + ",sn.publish_time desc");
		}
		return sysNewsServiceImpl.loadData(search);
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
	@RequestMapping(value="/yunduoNews",method=RequestMethod.GET)
	public JSONPObject yunduoNews(String pageNo,String pageSize,String companyId,String orderType,String recommendFlag,String newsType,HttpServletRequest request,String isPage){
		SysNewsVo search = new SysNewsVo();
		String callback = request.getParameter("callback");
		if(!StringUtils.isEmpty(companyId)){
			search.setCompanyId(Integer.parseInt(companyId));
		}
		if(!StringUtils.isEmpty(pageNo)){
			search.setPage(Integer.parseInt(pageNo));
		}
		if(!StringUtils.isEmpty(pageSize)){
			search.setPageSize(Integer.parseInt(pageSize));
		}
		if(!StringUtils.isEmpty(orderType)&&!"undefined".equals(orderType)){
			search.setOrderType(orderType);
		}
		if(!StringUtils.isEmpty(recommendFlag)&&!"undefined".equals(recommendFlag)){
			search.setRecommendFlag(Integer.parseInt(recommendFlag));
		}
		if(!StringUtils.isEmpty(newsType)&&!"undefined".equals(newsType)){
			search.setNewsType(newsType);
		}
		if(!StringUtils.isEmpty(isPage)&& isPage.equals("1")){
			List<SysNewsVo> list2 = sysNewsServiceImpl.guanwangNewsList2(search);
			return new JSONPObject(callback, list2);
		}
		List<SysNewsVo> list = sysNewsServiceImpl.guanwangNewsList(search);
		int count = sysNewsServiceImpl.guanwangNewsListCount(search);
		JSONPObject jsonpObject = new JSONPObject(callback, new PageFinder<SysNewsVo>(search.getPage(), search.getPageSize(), count, list));
		return jsonpObject;
		//return new JSONPObject(callback, new PageFinder<SysNewsVo>(search.getPage(), search.getPageSize(), count, list));
		//return new PageFinder<SysNewsVo>(search.getPage(), search.getPageSize(), count, list);
	}
	
	@ResponseBody
	@RequestMapping(value="/yunduoNewsDetail",method=RequestMethod.GET)
	public JSONPObject yunduoNewsDetail(String id,HttpServletRequest request){
		String callback = request.getParameter("callback");
		SysNews news = sysNewsServiceImpl.findSysNewsById(Integer.parseInt(id));
		return new JSONPObject(callback, news);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getJosns", method = RequestMethod.POST)
	public List<SysNews> getJosns(HttpServletRequest request ,SysNewsVo search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		return sysNewsServiceImpl.findSysNewsList(search);
	}
}
