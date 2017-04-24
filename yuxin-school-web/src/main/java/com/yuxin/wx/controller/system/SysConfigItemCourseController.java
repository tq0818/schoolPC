package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.system.ISysConfigItemCourseService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemCourse;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of SysConfigItemCourse
 * @author wang.zx
 * @date 2015-9-23
 */
@Controller
@RequestMapping("/sysConfigItemCourse")
public class SysConfigItemCourseController {
	
	@Autowired
	private ISysConfigItemCourseService sysConfigItemCourseServiceImpl;
	
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigItemCourse search){
		if (search == null) {
			search = new SysConfigItemCourse();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysConfigItemCourseServiceImpl.findSysConfigItemCourseByPage(search));
		return "sysConfigItemCourse/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigItemCourse SysConfigItemCourse) {
		sysConfigItemCourseServiceImpl.insert(SysConfigItemCourse);
		return "redirect:/sysConfigItemCourse";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigItemCourse SysConfigItemCourse) {
		sysConfigItemCourseServiceImpl.update(SysConfigItemCourse);
		return "redirect:/sysConfigItemCourse";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigItemCourseServiceImpl.deleteSysConfigItemCourseById(id);
		return "redirect:/sysConfigItemCourse";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigItemCourse getJson(Model model, @PathVariable Integer id){
		return sysConfigItemCourseServiceImpl.findSysConfigItemCourseById(id);
	}
	/**
	 * 
	 * Class Name: SysConfigItemCourseController.java
	 * @Description: 链接到课程设置页面
	 * @author yuchanglong
	 * @date 2015年9月24日 下午12:40:52
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="/toClassSet")
	public String toClassSet(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyFunctionSet search = new CompanyFunctionSet();
		search.setCompanyId(companyId);
		List<CompanyFunctionSet> sets = companyFunctionSetServiceImpl.findCompanyFunctionSet(search);
		if(sets.size()>0){
			for (CompanyFunctionSet ss : sets) {
				String code = ss.getFunctionCode();
				String status = ss.getStatus();
				if("COURSE_VALIDITY_COMPANY_LEVEL".equals(code)){
					model.addAttribute("comLS",status);
				}
				if("COURSE_VALIDITY_ITEM_LEVEL".equals(code)){
					model.addAttribute("itemLS",status);
				}
				if("COURSE_VALIDITY_COURSE_LEVEL".equals(code)){
					model.addAttribute("claLS",status);
				}
			}
		}
		
		
		return "classes/classSet/classSet";
	}
	/**
	 * 
	 * Class Name: SysConfigItemCourseController.java
	 * @Description: 加载公司级别信息
	 * @author yuchanglong
	 * @date 2015年9月24日 下午5:05:28
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loadCom")
	public String loadCom(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		comLevelData(companyId,"COURSE_VALIDITY_DAY",model);
		comLevelData(companyId,"VIDEO_WATCH_TIMES",model);
		comLevelData(companyId,"LIVE_WATCH_TIMES",model);
		return "classes/classSet/comAjax";
	}
	/**
	 * 
	 * Class Name: SysConfigItemCourseController.java
	 * @Description: 初始化课程设置时插入公司级别数据
	 * @author yuchanglong
	 * @date 2015年9月24日 下午4:46:59
	 * @version 1.0
	 */
	private void comLevelData(Integer companyId,String code,Model model){
		CompanyFunctionSet searchO= new CompanyFunctionSet();
		searchO.setCompanyId(companyId);
		searchO.setFunctionCode(code);
		
		CompanyFunctionSet setData = companyFunctionSetServiceImpl.findCompanyUseCourse(searchO);
		if(setData == null){
			searchO.setColumn1("0");
			companyFunctionSetServiceImpl.insert(searchO);
			if("COURSE_VALIDITY_DAY".equals(code)){
				model.addAttribute("comDay","0");
			}
			if("VIDEO_WATCH_TIMES".equals(code)){
				model.addAttribute("comVideo","0");
			}
			if("LIVE_WATCH_TIMES".equals(code)){
				model.addAttribute("comLive","0");
			}
		}else{
			String col = setData.getColumn1();
			if("COURSE_VALIDITY_DAY".equals(code)){
				model.addAttribute("comDay",col);
			}
			if("VIDEO_WATCH_TIMES".equals(code)){
				model.addAttribute("comVideo",col);
			}
			if("LIVE_WATCH_TIMES".equals(code)){
				model.addAttribute("comLive",col);
			}
		}
	}
	/**
	 * 
	 * Class Name: SysConfigItemCourseController.java
	 * @Description: 异步加载学科信息
	 * @author yuchanglong
	 * @date 2015年9月24日 下午12:40:26
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loadAjax")
	public String loadAjax(Model model){
		Integer comId = WebUtils.getCurrentCompanyId();
		SysConfigItemCourse course = new SysConfigItemCourse();
		course.setCompanyId(comId);
		course.setDelFlag(0);
		List<SysConfigItemCourse> itemCourses = sysConfigItemCourseServiceImpl.findByItem(course);
		if(itemCourses != null && itemCourses.size()>0){
			for (SysConfigItemCourse sic : itemCourses) {
				Integer itemId = sic.getConfigItemId();
				SysConfigItem item = sysConfigItemServiceImpl.findSysConfigItemById(itemId);
				String itemT = item.getItemType();
				Integer delFalg = item.getDelFlag();
				if(itemT.equals("1")){
					if(item != null && item.getItemName() != null && delFalg.equals(0)){
						sic.setItemName(item.getItemName());
					}else{
						itemCourses.remove(sic);
					}
				}
			}
		}
		model.addAttribute("itemCourses",itemCourses);
		return "classes/classSet/subAjax";
	}
	
	/**
	 * 
	 * Class Name: SysConfigItemCourseController.java
	 * @Description: 根据学科id修改学科信息
	 * @author yuchanglong
	 * @date 2015年9月24日 下午12:40:04
	 * @version 1.0
	 * @param course
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateByItemId")
	public String updateByItemId(SysConfigItemCourse course){
		Integer comId = WebUtils.getCurrentCompanyId();
		Date date = new Date();
		course.setCompanyId(comId);
		Integer day = course.getValidityDay();
		if(day!=null){
			Date addDate = DateUtil.addDate(date, day);
			course.setValidityDate(addDate);
		}
		sysConfigItemCourseServiceImpl.updateByItemId(course);
		return "success";
	}
	
	/**
	 * 
	 * Class Name: SysConfigItemCourseController.java
	 * @Description: 开启状态查询公司一级项目
	 * @author yuchanglong
	 * @date 2015年9月24日 下午12:38:51
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findOneItemByComId")
	public String findOneItemByComId(){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentSchoolId();
		
		SysConfigItem item = new SysConfigItem();
		item.setCompanyId(companyId);
		item.setSchoolId(schoolId);
		item.setItemType("1");
		item.setDelFlag(0);
		
		List<SysConfigItem> configItems = sysConfigItemServiceImpl.findStatus(item);
		if(configItems.size()>0){
			for (SysConfigItem sci : configItems) {
				SysConfigItemCourse course = new SysConfigItemCourse();
				course.setCompanyId(companyId);
				course.setConfigItemId(sci.getId());
				course.setDelFlag(0);
				course.setLiveWatchCount(0);
				course.setValidityDay(0);
				course.setVideoWatchCount(0);
				SysConfigItemCourse checkCourse = sysConfigItemCourseServiceImpl.findByItemIdAndComId(course);
				if(checkCourse == null){
					sysConfigItemCourseServiceImpl.insert(course);
				}
			}
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/updateComFuncSet")
	public String updateComFuncSet(CompanyFunctionSet functionSet){
		Integer companyId = WebUtils.getCurrentCompanyId();
		functionSet.setCompanyId(companyId);
		companyFunctionSetServiceImpl.updateByComIdAndCode(functionSet);
		return "success";
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
