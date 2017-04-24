package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyConfigProxyOrgService;
import com.yuxin.wx.api.company.IOrganInviteRewardRecordService;
import com.yuxin.wx.common.ExcelSheetEntity;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.company.CompanyConfigProxyOrg;
import com.yuxin.wx.model.company.OrganInviteRewardRecord;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.OrgInviteRecordDetailListVo;
import com.yuxin.wx.vo.company.OrgInviteRecordListVo;
import com.yuxin.wx.vo.company.TeacherInviteRecordDetailListVo;
import com.yuxin.wx.vo.company.TeacherInviteRecordListVo;

/**
 * Controller of OrganInviteRewardRecord
 * @author chopin
 * @date 2017-3-16
 */
@Controller
@RequestMapping("/organInviteRewardRecord")
public class OrganInviteRewardRecordController {
	
	@Autowired
	private IOrganInviteRewardRecordService organInviteRewardRecordServiceImpl;
	
	@Autowired
	private ICompanyConfigProxyOrgService companyConfigProxyOrgServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, OrganInviteRewardRecord search){
		if (search == null) {
			search = new OrganInviteRewardRecord();
			// search.setPageSize(20);
		}
		model.addAttribute("list", organInviteRewardRecordServiceImpl.findOrganInviteRewardRecordByPage(search));
		return "organInviteRewardRecord/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(OrganInviteRewardRecord OrganInviteRewardRecord) {
		organInviteRewardRecordServiceImpl.insert(OrganInviteRewardRecord);
		return "redirect:/organInviteRewardRecord";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(OrganInviteRewardRecord OrganInviteRewardRecord) {
		organInviteRewardRecordServiceImpl.update(OrganInviteRewardRecord);
		return "redirect:/organInviteRewardRecord";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		organInviteRewardRecordServiceImpl.deleteOrganInviteRewardRecordById(id);
		return "redirect:/organInviteRewardRecord";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public OrganInviteRewardRecord getJson(Model model, @PathVariable Integer id){
		return organInviteRewardRecordServiceImpl.findOrganInviteRewardRecordById(id);
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
	
	@RequestMapping(value="/toOrgInviteRecordList")
	public String toOrgInviteRecordList(HttpServletRequest request,Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		model.addAttribute("companyId", companyId);
		model.addAttribute("totalInviteNumber", organInviteRewardRecordServiceImpl.getTotalInviteNumber(companyId));
		model.addAttribute("totalConsumeTimes", organInviteRewardRecordServiceImpl.getTotalConsumerTimes(companyId));
		model.addAttribute("totalConsumeBalance", organInviteRewardRecordServiceImpl.getTotalConsumeBalance(companyId));
		model.addAttribute("totalRewardBanlance", organInviteRewardRecordServiceImpl.getTotalRewardBalance(companyId));
		return "/company/orgInviteRecordList";
	}
	
	@ResponseBody
	@RequestMapping(value="/queryRewardsRecordList",method=RequestMethod.POST)
	public PageFinder<OrgInviteRecordListVo> queryRewardsRecordList(HttpServletRequest request,OrgInviteRecordListVo search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<OrgInviteRecordListVo> list = organInviteRewardRecordServiceImpl.queryRewardsRecordList(search);
		int count = organInviteRewardRecordServiceImpl.queryRewardsRecordListCount(search);
		return new PageFinder<OrgInviteRecordListVo>(search.getPage(), search.getPageSize(), count, list);
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(HttpServletRequest request){
		Integer companyId = WebUtils.getCurrentCompanyId();
		OrgInviteRecordListVo search = new OrgInviteRecordListVo();
		search.setCompanyId(companyId);
		String name = request.getParameter("name");
		if(!StringUtils.isEmpty(name))
			search.setName(name);
		String date = request.getParameter("date");
		if(!StringUtils.isEmpty(date))
			search.setSearchDateType(Integer.parseInt(date));
		String page = request.getParameter("page");
		if(!StringUtils.isEmpty(page))
			search.setPage(Integer.parseInt(page));
		String startTime = request.getParameter("startTime");
		if(!StringUtils.isEmpty(startTime))
			search.setStartTime(startTime);
		String endTime = request.getParameter("endTime");
		if(!StringUtils.isEmpty(endTime))
			search.setEndTime(endTime);
		String sortType = request.getParameter("sortType");
		if(!StringUtils.isEmpty(sortType))
			search.setSortType(sortType);
		String sort = request.getParameter("sort");
		if(!StringUtils.isEmpty(sort))
			search.setSort(sort);
		List<OrgInviteRecordListVo> list = organInviteRewardRecordServiceImpl.exportRewardsRecordList(search);
		ViewFiles excel = new ViewFiles(); 
		Map<String,Object> map = new HashMap<String,Object>();  
		ExcelSheetEntity entity=ExcelSheetEntity.newInstance("代理机构名称:name,创建时间:createDate,邀请人数:inviteNumber,消费次数:consumeTimes,消费金额:consumeBalance,提成奖励金额:rewardBalance",list);
		map.put("entity", entity);
		map.put("fileName", "代理邀请记录表.xls");
		return new ModelAndView(excel,map);
	}
	
	@ResponseBody
	@RequestMapping(value="/todetaillistpage")
	public JSONObject todetaillistpage(HttpServletRequest request,OrgInviteRecordDetailListVo search){
		JSONObject jsonObject = new JSONObject();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		CompanyConfigProxyOrg configProxyOrg = companyConfigProxyOrgServiceImpl.findCompanyConfigProxyOrgById(search.getProxyOrganId());
		int inviteNumber = organInviteRewardRecordServiceImpl.getProxyDetailInviteNumber(search);
		int consumerTimes = organInviteRewardRecordServiceImpl.getProxyDetailConsumerTimes(search);
		double consumeBalance = organInviteRewardRecordServiceImpl.getProxyDetailConsumeBalance(search);
		double rewardBalance = organInviteRewardRecordServiceImpl.getProxyDetailRewardBalance(search);
		jsonObject.put("inviteNumber", inviteNumber);
		jsonObject.put("consumerTimes", consumerTimes);
		jsonObject.put("consumeBalance", consumeBalance);
		jsonObject.put("rewardBalance", rewardBalance);
		jsonObject.put("proxy", configProxyOrg);
		return jsonObject;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryRewardsRecordDetailList",method=RequestMethod.POST)
	public PageFinder<OrgInviteRecordDetailListVo> queryRewardsRecordDetailList(HttpServletRequest request,OrgInviteRecordDetailListVo search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setPageSize(10);
		List<OrgInviteRecordDetailListVo> list = organInviteRewardRecordServiceImpl.queryRewardsRecordDetailList(search);
		int count = organInviteRewardRecordServiceImpl.queryRewardsRecordDetailListCount(search);
		PageFinder<OrgInviteRecordDetailListVo> pageFinder = new PageFinder<OrgInviteRecordDetailListVo>(search.getPage(), search.getPageSize(), count, list);
		return pageFinder;
	}
}
