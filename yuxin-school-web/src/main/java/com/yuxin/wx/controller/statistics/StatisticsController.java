package com.yuxin.wx.controller.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @ClassName: StatisticsController
 * @Description: 直播统计
 * @author xukaiqiang
 * @date 2016年5月18日 下午8:31:21
 * @modifier
 * @modify-date 2016年5月18日 下午8:31:21
 * @version 1.0
 */
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuxin.wx.api.statistics.IStatisticsService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.statistics.QueryLessonByClassTypeVo;
import com.yuxin.wx.model.statistics.Statistics;
import com.yuxin.wx.model.statistics.Statistics2;
import com.yuxin.wx.utils.ExcelUtil;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	private static Log log = LogFactory.getLog("log");
	@Autowired
	private IStatisticsService iStatisticsService;

	// 跳转到统计直播页面中
	@RequestMapping(value = "/statisticsList/{classTypeId}")
	public String statisticsList(Model model, @PathVariable Integer classTypeId) {
		log.info("进入直播统计查询中了.......");

		// 通过课程编号查询当前课程下的所有课次
		List<QueryLessonByClassTypeVo> list = iStatisticsService.queryLessonByClassTypeId(2579);
		model.addAttribute("list", list);
		model.addAttribute("lable", "live");
		// 参课人数查询
		// List list = iStatisticsService.statisticsList(classLessionId);

		// //根据班型id查询详情
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("classId", "" + id);
		// ClassTypeVo classType=classTypeServiceImpl.findClassTypeDetail(map);
		// model.addAttribute("classType", classType);
		// model.addAttribute("ct", classType);
		// model.addAttribute("type", "update");
		// model.addAttribute("lable", lable);
		// CompanyFunctionSet search=new CompanyFunctionSet();
		// search.setFunctionCode("COMPANY_FUNCTION_COURSE");
		// search.setCompanyId(WebUtils.getCurrentCompanyId());
		// CompanyFunctionSet
		// cfs=companyFunctionSetServiceImpl.findCompanyUseCourse(search);
		// //查看该机构是否有删除学员的功能
		// search.setFunctionCode("COMPANY_REMOVE_PAYMASTER");
		// CompanyFunctionSet
		// isDelete=companyFunctionSetServiceImpl.findCompanyUseCourse(search);
		// if(null!=isDelete && "1".equals(isDelete.getStatus())){
		// model.addAttribute("isDelete", isDelete.getStatus());
		// }else{
		// model.addAttribute("isDelete", 0);
		// }
		// if(cfs!=null && "1".equals(cfs.getStatus())){
		// return "classType/manageStu/studentList-full";
		// }
		return "classes/live-class-count";
	}

	@ResponseBody
	@RequestMapping(value = "/queryall")
	public PageFinder<Statistics> queryAll(Model model,  Statistics statistics) {
		log.info("进入直播统计查询中了.......获取的参数为classLessonId"+statistics.getClassLessionId()+"-----mobile----"+statistics.getMobile());
		// 通过课程编号查询当前课程下的所有课次
		// List<Statistics> list = iStatisticsService.queryAll(classLessonId);
		PageFinder<Statistics> pageFinder = iStatisticsService.queryAll(statistics);
		
		// 参课人数查询
		// List list = iStatisticsService.statisticsList(classLessionId);

		// //根据班型id查询详情
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("classId", "" + id);
		// ClassTypeVo classType=classTypeServiceImpl.findClassTypeDetail(map);
		// model.addAttribute("classType", classType);
		// model.addAttribute("ct", classType);
		// model.addAttribute("type", "update");
		// model.addAttribute("lable", lable);
		// CompanyFunctionSet search=new CompanyFunctionSet();
		// search.setFunctionCode("COMPANY_FUNCTION_COURSE");
		// search.setCompanyId(WebUtils.getCurrentCompanyId());
		// CompanyFunctionSet
		// cfs=companyFunctionSetServiceImpl.findCompanyUseCourse(search);
		// //查看该机构是否有删除学员的功能
		// search.setFunctionCode("COMPANY_REMOVE_PAYMASTER");
		// CompanyFunctionSet
		// isDelete=companyFunctionSetServiceImpl.findCompanyUseCourse(search);
		// if(null!=isDelete && "1".equals(isDelete.getStatus())){
		// model.addAttribute("isDelete", isDelete.getStatus());
		// }else{
		// model.addAttribute("isDelete", 0);
		// }
		// if(cfs!=null && "1".equals(cfs.getStatus())){
		// return "classType/manageStu/studentList-full";
		// }
		return pageFinder;
	}

	
	@ResponseBody
	@RequestMapping(value = "/queryall2")
	public PageFinder<Statistics2> queryAll2(Model model,  Statistics statistics) {
		log.info("进入直播统计查询中了.......获取的参数为classLessonId"+statistics.getClassLessionId()+"-----mobile----"+statistics.getMobile());
		// 通过课程编号查询当前课程下的所有课次
		// List<Statistics> list = iStatisticsService.queryAll(classLessonId);
		PageFinder<Statistics2> pageFinder2 = iStatisticsService.queryAll2(statistics);
		log.info("查询到"+pageFinder2.getData().size()+"条数据。。。。。。。。。");
		
		// 参课人数查询
		// List list = iStatisticsService.statisticsList(classLessionId);

		// //根据班型id查询详情
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("classId", "" + id);
		// ClassTypeVo classType=classTypeServiceImpl.findClassTypeDetail(map);
		// model.addAttribute("classType", classType);
		// model.addAttribute("ct", classType);
		// model.addAttribute("type", "update");
		// model.addAttribute("lable", lable);
		// CompanyFunctionSet search=new CompanyFunctionSet();
		// search.setFunctionCode("COMPANY_FUNCTION_COURSE");
		// search.setCompanyId(WebUtils.getCurrentCompanyId());
		// CompanyFunctionSet
		// cfs=companyFunctionSetServiceImpl.findCompanyUseCourse(search);
		// //查看该机构是否有删除学员的功能
		// search.setFunctionCode("COMPANY_REMOVE_PAYMASTER");
		// CompanyFunctionSet
		// isDelete=companyFunctionSetServiceImpl.findCompanyUseCourse(search);
		// if(null!=isDelete && "1".equals(isDelete.getStatus())){
		// model.addAttribute("isDelete", isDelete.getStatus());
		// }else{
		// model.addAttribute("isDelete", 0);
		// }
		// if(cfs!=null && "1".equals(cfs.getStatus())){
		// return "classType/manageStu/studentList-full";
		// }
		return pageFinder2;
	}
	
	
	
	/**
	 * Class Name: StatisticsController.java
	 * 
	 * @Description: 导出直播统计结果
	 * @author xukaiqiang
	 * @date 2016年5月19日 下午7:09:01
	 * @modifier
	 * @modify-date 2016年5月19日 下午7:09:01
	 * @version 1.0
	 * @param model
	 * @param statistics
	 * @return
	 */
	@RequestMapping(value = "/exportStatistics")
	public ModelAndView exportStatistics(Model model,  Statistics statistics) {
		log.info("开始导出直播上课统计。。。。。。。。。。。。获取classLessonId为：" + statistics.getClassLessionId());
//		Statistics statistics = new Statistics();
//		statistics.setClassLessionId(2666);
		log.info("开始查询了。。。。。");
		PageFinder<Statistics> pageFinder = iStatisticsService.queryAll(statistics);
		log.info("查询到" + pageFinder.getData().size() + "条数据。。。。。。。。。");
		log.info("开始设置表头。。。。。。。。。");
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for (Statistics s : pageFinder.getData()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", s.getUserName());
			map.put("nickName", s.getNickName());
			map.put("name", s.getName());
			map.put("email", s.getEmail());
			map.put("login_time", s.getLogin_time());
			lists.add(map);
		}
		log.info("开始导出表格。。。。。。");
		StringBuffer title = new StringBuffer(
				"学员账号:userName,昵称:nickName,实名:name,邮箱:email,登录时间:login_time");
		ViewFiles excel = new ViewFiles();
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
		} catch (Exception ex) {

		}
		Map map = new HashMap();
		map.put("workbook", wb);
		map.put("fileName", "直播上课统计.xls");
		return new ModelAndView(excel, map);
	}

	
	
	/**
	 * Class Name: StatisticsController.java
	 * 
	 * @Description: 导出直播统计结果
	 * @author xukaiqiang
	 * @date 2016年5月19日 下午7:09:01
	 * @modifier
	 * @modify-date 2016年5月19日 下午7:09:01
	 * @version 1.0
	 * @param model
	 * @param statistics
	 * @return
	 */
	@RequestMapping(value = "/exportStatistics2")
	public ModelAndView exportStatistics2(Model model,  Statistics statistics) {
		log.info("开始导出直播上课统计。。。。。。。。。。。。获取classLessonId为：" + statistics.getClassLessionId());
//		Statistics statistics = new Statistics();
//		statistics.setClassLessionId(2666);
		log.info("开始查询了。。。。。");
		PageFinder<Statistics2> pageFinder = iStatisticsService.queryAll2(statistics);
		log.info("查询到" + pageFinder.getData().size() + "条数据。。。。。。。。。");
		log.info("开始设置表头。。。。。。。。。");
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for (Statistics2 s : pageFinder.getData()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("lesson_name", s.getLesson_name());
			map.put("login_time", s.getLogin_time());
			map.put("userName", s.getUserName());
			map.put("nickName", s.getNickName());
			map.put("name", s.getName());
			map.put("email", s.getEmail());
			lists.add(map);
		}
		log.info("开始导出表格。。。。。。");
		StringBuffer title = new StringBuffer(
				"课次名称:lesson_name,登录时间:login_time,账号名:userName,昵称:nickName,实名:name,邮箱:email");
		ViewFiles excel = new ViewFiles();
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
		} catch (Exception ex) {

		}
		Map map = new HashMap();
		map.put("workbook", wb);
		map.put("fileName", "直播上课统计.xls");
		return new ModelAndView(excel, map);
	}
	
}
