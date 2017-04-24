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
import com.yuxin.wx.api.company.ITeacherInviteRewardRecordService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.common.ExcelSheetEntity;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.teacher.TeacherInviteRewardRecord;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.TeacherInviteRecordDetailListVo;
import com.yuxin.wx.vo.company.TeacherInviteRecordListVo;

/**
 * Controller of TeacherInviteRewardRecord
 * @author chopin
 * @date 2017-3-16
 */
@Controller
@RequestMapping("/teacherInviteRewardRecord")
public class TeacherInviteRewardRecordController {
	
	@Autowired
	private ITeacherInviteRewardRecordService teacherInviteRewardRecordServiceImpl;
	
	@Autowired
	private ISysConfigTeacherService sysConfigTeacherServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, TeacherInviteRewardRecord search){
		if (search == null) {
			search = new TeacherInviteRewardRecord();
			// search.setPageSize(20);
		}
		model.addAttribute("list", teacherInviteRewardRecordServiceImpl.findTeacherInviteRewardRecordByPage(search));
		return "teacherInviteRewardRecord/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(TeacherInviteRewardRecord TeacherInviteRewardRecord) {
		teacherInviteRewardRecordServiceImpl.insert(TeacherInviteRewardRecord);
		return "redirect:/teacherInviteRewardRecord";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(TeacherInviteRewardRecord TeacherInviteRewardRecord) {
		teacherInviteRewardRecordServiceImpl.update(TeacherInviteRewardRecord);
		return "redirect:/teacherInviteRewardRecord";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		teacherInviteRewardRecordServiceImpl.deleteTeacherInviteRewardRecordById(id);
		return "redirect:/teacherInviteRewardRecord";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public TeacherInviteRewardRecord getJson(Model model, @PathVariable Integer id){
		return teacherInviteRewardRecordServiceImpl.findTeacherInviteRewardRecordById(id);
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
	
	@RequestMapping(value="/toTeacherInviteRecordList")
	public String toTeacherInviteRecordList(HttpServletRequest request,Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		model.addAttribute("companyId", companyId);
		model.addAttribute("totalInviteNumber", teacherInviteRewardRecordServiceImpl.getTotalInviteNumber(companyId));
		model.addAttribute("totalConsumeTimes", teacherInviteRewardRecordServiceImpl.getTotalConsumerTimes(companyId));
		model.addAttribute("totalConsumeBalance", teacherInviteRewardRecordServiceImpl.getTotalConsumeBalance(companyId));
		model.addAttribute("totalRewardBanlance", teacherInviteRewardRecordServiceImpl.getTotalRewardBalance(companyId));
		return "/company/teacherInviteRecordList";
	}
	
	@ResponseBody
	@RequestMapping(value="/queryRewardsRecordList",method=RequestMethod.POST)
	public PageFinder<TeacherInviteRecordListVo> queryRewardsRecordList(HttpServletRequest request,TeacherInviteRecordListVo search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<TeacherInviteRecordListVo> list = teacherInviteRewardRecordServiceImpl.queryRewardsRecordList(search);
		int count = teacherInviteRewardRecordServiceImpl.queryRewardsRecordListCount(search);
		return new PageFinder<TeacherInviteRecordListVo>(search.getPage(), search.getPageSize(), count, list);
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(HttpServletRequest request){
		Integer companyId = WebUtils.getCurrentCompanyId();
		TeacherInviteRecordListVo search = new TeacherInviteRecordListVo();
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
		List<TeacherInviteRecordListVo> list = teacherInviteRewardRecordServiceImpl.exportRewardsRecordList(search);
		ViewFiles excel = new ViewFiles(); 
		Map<String,Object> map = new HashMap<String,Object>();  
		ExcelSheetEntity entity=ExcelSheetEntity.newInstance("老师姓名:name,手机号:mobile,邀请码:inviteCode,邀请人数:inviteNumber,消费次数:consumeTimes,消费金额:consumeBalance,提成奖励金额:rewardBalance",list);
		map.put("entity", entity);
		map.put("fileName", "老师邀请记录表.xls");
		return new ModelAndView(excel,map);
	}
	
	@ResponseBody
	@RequestMapping(value="/todetaillistpage")
	public JSONObject todetaillistpage(HttpServletRequest request,TeacherInviteRecordDetailListVo search){
		JSONObject jsonObject = new JSONObject();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		SysConfigTeacher teacher = sysConfigTeacherServiceImpl.findSysConfigTeacherById(search.getTeacherId());
		int inviteNumber = teacherInviteRewardRecordServiceImpl.getTeacherInviteNumber(search);
		int consumerTimes = teacherInviteRewardRecordServiceImpl.getTeacherConsumerTimes(search);
		double consumeBalance = teacherInviteRewardRecordServiceImpl.getTeacherConsumeBalance(search);
		double rewardBalance = teacherInviteRewardRecordServiceImpl.getTeacherRewardBalance(search);
		jsonObject.put("inviteNumber", inviteNumber);
		jsonObject.put("consumerTimes", consumerTimes);
		jsonObject.put("consumeBalance", consumeBalance);
		jsonObject.put("rewardBalance", rewardBalance);
		jsonObject.put("teacher", teacher);
		return jsonObject;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryRewardsRecordDetailList",method=RequestMethod.POST)
	public PageFinder<TeacherInviteRecordDetailListVo> queryRewardsRecordDetailList(HttpServletRequest request,TeacherInviteRecordDetailListVo search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setPageSize(10);
		List<TeacherInviteRecordDetailListVo> list = teacherInviteRewardRecordServiceImpl.queryRewardsRecordDetailList(search);
		int count = teacherInviteRewardRecordServiceImpl.queryRewardsRecordDetailListCount(search);
		PageFinder<TeacherInviteRecordDetailListVo> pageFinder = new PageFinder<TeacherInviteRecordDetailListVo>(search.getPage(), search.getPageSize(), count, list);
		return pageFinder;
	}
}
