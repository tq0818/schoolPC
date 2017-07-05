package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyLiveStaticDetailService;
import com.yuxin.wx.api.system.ILongitudinalTableColDefineService;
import com.yuxin.wx.api.system.ILongitudinalTableDataService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyLiveStaticDetail;
import com.yuxin.wx.model.statistics.QueryLessonByClassTypeVo;
import com.yuxin.wx.model.system.LongitudinalTableColDefine;
import com.yuxin.wx.model.system.LongitudinalTableData;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.company.CompanyLiveStaticDetailVo;

/**
 * Controller of CompanyLiveStaticDetail
 * 
 * @author chopin
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/companyLiveStaticDetail")
public class CompanyLiveStaticDetailController {
	private static Log log = LogFactory.getLog("log");
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	@Autowired
	private ICompanyLiveStaticDetailService companyLiveStaticDetailServiceImpl;
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;
	@Autowired
	private ILongitudinalTableDataService longitudinalTableDataServiceImpl;
	@Autowired
	private ILongitudinalTableColDefineService longitudinalTableColDefineServiceImpl;
	@Autowired
	private ISysConfigDictService sysConfigDictServiceImpl;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyLiveStaticDetail search) {
		if (search == null) {
			search = new CompanyLiveStaticDetail();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyLiveStaticDetailServiceImpl.findCompanyLiveStaticDetailByPage(search));
		return "companyLiveStaticDetail/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(CompanyLiveStaticDetail CompanyLiveStaticDetail) {
		companyLiveStaticDetailServiceImpl.insert(CompanyLiveStaticDetail);
		return "redirect:/companyLiveStaticDetail";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(CompanyLiveStaticDetail CompanyLiveStaticDetail) {
		companyLiveStaticDetailServiceImpl.update(CompanyLiveStaticDetail);
		return "redirect:/companyLiveStaticDetail";
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyLiveStaticDetailServiceImpl.deleteCompanyLiveStaticDetailById(id);
		return "redirect:/companyLiveStaticDetail";
	}

	@ResponseBody
	@RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
	public CompanyLiveStaticDetail getJson(Model model, @PathVariable Integer id) {
		return companyLiveStaticDetailServiceImpl.findCompanyLiveStaticDetailById(id);
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

	/***
	 * 
	 * Class Name: CompanyLiveStaticDetailController.java
	 * 
	 * @Description: 跳转到直播上课统计页面中
	 * @author xukaiqiang
	 * @date 2016年5月23日 下午3:41:08
	 * @modifier
	 * @modify-date 2016年5月23日 下午3:41:08
	 * @version 1.0
	 * @param model
	 * @param classTypeId
	 * @return
	 */
	@RequestMapping(value = "/companyLiveStaticDetailList/{classTypeId}/{lable}")
	public String statisticsList(Model model, @PathVariable Integer classTypeId, @PathVariable String lable) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + classTypeId);
		ClassTypeVo classType = classTypeServiceImpl.findClassTypeDetail(map);
		model.addAttribute("classType", classType);
		model.addAttribute("ct", classType);
		model.addAttribute("type", "update");
		model.addAttribute("lable", lable);

		log.info("进入直播统计查询中了,classTypeId=" + classTypeId);
		// 通过课程编号查询当前课程下的所有课次
		List<QueryLessonByClassTypeVo> list = companyLiveStaticDetailServiceImpl.queryLessonByClassTypeId(classTypeId);
		log.info("查询出" + list.size() + "个课次.........");
		model.addAttribute("list", list);
		model.addAttribute("classLessonCount", list.size());

		// 判断是单班号还是多班号
		CompanyFunctionSet search = new CompanyFunctionSet();
		search.setFunctionCode("COMPANY_FUNCTION_COURSE");
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		CompanyFunctionSet cfs = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
//		// 查看该机构是否有删除学员的功能
//		search.setFunctionCode("COMPANY_REMOVE_PAYMASTER");
//		CompanyFunctionSet isDelete = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
//		if (null != isDelete && "1".equals(isDelete.getStatus())) {
//			model.addAttribute("isDelete", isDelete.getStatus());
//		} else {
//			model.addAttribute("isDelete", 0);
//		}
//		if (cfs != null && "1".equals(cfs.getStatus())) {
//			// 跳转到多班号
//			return "classes/liveClassCountMuticlass";
//		}
		model.addAttribute("plus", cfs != null && "1".equals(cfs.getStatus()));

		//查询学校所在区域
		SysConfigDict areaDict = new SysConfigDict();
		areaDict.setDictCode("EDU_SCHOOL_AREA");
		List<SysConfigDict> areas = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
		model.addAttribute("areas", areas);
		//学段
		areaDict.setDictCode("EDU_STEP");
		List<SysConfigDict> steps = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
		model.addAttribute("steps", steps);
		//年份列表
		List<Integer> years = new ArrayList<Integer>();
		int curYear = DateUtil.getCurYear();
		for(int year = 0;year<12;year++){
			years.add(curYear-year);
		}
		model.addAttribute( "years", years);
		// 跳转到单班号
		return "classes/liveClassCount";
	}
	
	/**
	 * Class Name: CompanyLiveStaticDetailController.java
	 * 
	 * @Description: 查询直播上课统计列表-学员手机号-课次
	 * @author xukaiqiang
	 * @date 2016年5月23日 下午2:54:35
	 * @modifier
	 * @modify-date 2016年5月23日 下午2:54:35
	 * @version 1.0
	 * @param model
	 * @param statistics
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryall")
	public PageFinder<CompanyLiveStaticDetailVo> queryAll(Model model,
			CompanyLiveStaticDetailVo companyLiveStaticDetailVo) {
		companyLiveStaticDetailVo.setPageSize(10);
		log.info("进入直播统计查询中了,获取的参数为classLessonId：" + companyLiveStaticDetailVo.getClassLessionId()
				+ "-----mobile----" + companyLiveStaticDetailVo.getMobile());
		companyLiveStaticDetailVo.setCompanyId(WebUtils.getCurrentCompanyId());
		// 通过课程编号查询当前课程下的所有课次
		PageFinder<CompanyLiveStaticDetailVo> pageFinder = companyLiveStaticDetailServiceImpl
				.queryAllCompanyLiveStaticDetail(companyLiveStaticDetailVo);
		return pageFinder;
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
	public ModelAndView exportStatistics(Model model, CompanyLiveStaticDetailVo companyLiveStaticDetailVo) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		companyLiveStaticDetailVo.setCompanyId(WebUtils.getCurrentCompanyId());
		companyLiveStaticDetailVo.setPageSize(1000);
		log.info("开始导出直播上课统计。。。。。。。。。。。。获取classLessonId为：" + companyLiveStaticDetailVo.getClassLessionId());
		log.info("开始查询了");
		PageFinder<CompanyLiveStaticDetailVo> pageFinder = companyLiveStaticDetailServiceImpl
				.queryAllCompanyLiveStaticDetail(companyLiveStaticDetailVo);
		log.info("查询到" + pageFinder.getData().size() + "条数据。。。。。。。。。");
		log.info("开始设置表头");
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		Date inTime = null;
		
		Map<Integer,Map<String, Object>> customInfo = customStuInfo("stu_id");
		
		for (CompanyLiveStaticDetailVo s : pageFinder.getData()) {
			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("mobile", s.getMobile());
			map.put("userName", s.getUserName());
			map.put("nickName", s.getNickName());
			map.put("name", s.getName());
//			map.put("email", s.getEmail());
			map.put("lessonName", s.getLessonName());
			map.put("eduArea", s.getEduArea());
			map.put("eduSchool", s.getEduSchool());
			map.put("eduStep", s.getEduStep());
			map.put("eduYear", s.getEduYear());
			Integer watchType = s.getWatchType();
			String type = "";
			if(watchType != null){
				if((int)watchType == 0){
					type = "看直播";
				}
				if((int)watchType == 1){
					type = "看回放";
				}
			}
			map.put("watchType", type);
			inTime = s.getInTime();
			map.put("inTime", inTime==null?"":sdf.format(inTime));
			Map<String, Object> definedDataMap = customInfo.get(s.getStuId());
			if(definedDataMap != null)
				map.putAll(definedDataMap);
			lists.add(map);
		}
		log.info("开始导出表格。。。。。。");
		StringBuffer title = new StringBuffer("课次:lessonName,学习方式:watchType,用户名:userName,学员名称:name,区域:eduArea,学校:eduSchool,学段:eduStep,年份:eduYear");
		
		List<LongitudinalTableColDefine> coldefine=longitudinalTableColDefineServiceImpl.findByCompany(WebUtils.getCurrentCompanyId(), "student");
		if(coldefine!=null && coldefine.size()>0){
			for(LongitudinalTableColDefine d:coldefine){
				if(!"id".equals(d.getColName()))
					title.append(","+d.getColComment()+":"+d.getColName());
			}
		}
		
		ViewFiles excel = new ViewFiles();
		HSSFWorkbook wb = new HSSFWorkbook();

		// 查询课程名称
		Map map2 = new HashMap();
		map2.put("classId", "" + companyLiveStaticDetailVo.getClassTypeId());
		ClassTypeVo classType = classTypeServiceImpl.findClassTypeDetail(map2);
		String firstStr = classType.getName() + "," + "总共上课人数:"
				+ companyLiveStaticDetailVo.getCountClassPeoples();
		try {
			wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString(), firstStr);
		} catch (Exception ex) {

		}
		Map map = new HashMap();
		map.put("workbook", wb);
		map.put("fileName", "直播上课统计.xls");
		return new ModelAndView(excel, map);
	}
	/**
	 * 
	 * @author licong
	 * @date 2016年8月15日 下午6:19:39
	 * @param  
	 * @param key 自定义表中必然会出现的列名
	 * @return
	 */
	public Map<Integer,Map<String, Object>> customStuInfo(String key) {
		Map<Integer,Map<String, Object>> resultMap = new HashMap<Integer,Map<String, Object>>();
		LongitudinalTableData sql=new LongitudinalTableData();
		sql.setCompanyId(WebUtils.getCurrentCompanyId());
		sql.setSchoolId(WebUtils.getCurrentSchoolId());
		sql.setTableName("student");
		List<LongitudinalTableData> ns=longitudinalTableDataServiceImpl.query(sql);
		if(ns==null || ns.size()==0)
			return resultMap;
		
		//键值对为rowId-dataInfo
		Map<Integer,Map<String, Object>> rowToDataMap = new HashMap<Integer,Map<String, Object>>();
		
		for (LongitudinalTableData longitudinalTableData : ns) {
			Integer row =  longitudinalTableData.getRowId();
			Map<String,Object> dataMap = null;
			if(!rowToDataMap.containsKey(row)){
				dataMap = new HashMap<String,Object>();
				rowToDataMap.put(row, dataMap);
			}else{
				dataMap = (Map<String, Object>) rowToDataMap.get(row);
			}
			//过滤掉名称为id的列
			if(!"id".equals(longitudinalTableData.getColName()))
				dataMap.put(longitudinalTableData.getColName(), longitudinalTableData.getColValue());
		}
		//删除列名为key的键值对,转换键值对为stuId-dataInfo
		for (Map.Entry<Integer, Map<String, Object>> entry: rowToDataMap.entrySet()) {
			Map<String, Object> data = entry.getValue();
			String stuId = (String)data.get(key);
			if(StringUtils.isNotBlank(stuId)){
				Integer value = Integer.parseInt(stuId);
				data.remove(key);
				resultMap.put(value, data);
			}
		}
		
		return resultMap;
	}
	/**
	 * Class Name: CompanyLiveStaticDetailController.java
	 * 
	 * @Description: 查询直播上课统计列表-学员手机号-学员名称
	 * @author xukaiqiang
	 * @date 2016年5月23日 下午2:54:35
	 * @modifier
	 * @modify-date 2016年5月23日 下午2:54:35
	 * @version 1.0
	 * @param model
	 * @param statistics
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryAllCompanyLiveStaticDetailByStudentNamePhone")
	public Object queryAllCompanyLiveStaticDetailByStudentNamePhone(Model model,
			CompanyLiveStaticDetailVo companyLiveStaticDetailVo) {
		Map<String,Object> map = new HashMap<String,Object>();
		companyLiveStaticDetailVo.setPageSize(10);
		log.info("进入直播统计查询中了.......获取的参数为classLessonId" + companyLiveStaticDetailVo.getClassLessionId()
				+ "-----mobile----" + companyLiveStaticDetailVo.getMobile());
		companyLiveStaticDetailVo.setCompanyId(WebUtils.getCurrentCompanyId());
		log.info("进入直播统计查询中了.......获取的参数为classLessonId" + companyLiveStaticDetailVo.getClassLessionId()
				+ "-----mobile----" + companyLiveStaticDetailVo.getMobile());
		// 查询合计上课次数
		if (companyLiveStaticDetailVo.getMobile() != null && !companyLiveStaticDetailVo.getMobile().equals("")
				|| companyLiveStaticDetailVo.getName() != null && !companyLiveStaticDetailVo.getName().equals("")) {
				// 通过课程编号查询当前课程下的所有课次
				PageFinder<CompanyLiveStaticDetailVo> pageFinder = companyLiveStaticDetailServiceImpl
						.queryAllCompanyLiveStaticDetailByStudentNamePhone(companyLiveStaticDetailVo);
				
			int sumCountClass = companyLiveStaticDetailServiceImpl.sumCountClass(companyLiveStaticDetailVo);
			map.put("learnCount", pageFinder.getRowCount());
			map.put("sumCountClass", sumCountClass);
			map.put("pageFinder", pageFinder);
		}
		return map;
	}

	/**
	 * Class Name: StatisticsController.java
	 * 
	 * @Description: 导出直播统计结果-学员姓名-手机号
	 * @author xukaiqiang
	 * @date 2016年5月19日 下午7:09:01
	 * @modifier
	 * @modify-date 2016年5月19日 下午7:09:01
	 * @version 1.0
	 * @param model
	 * @param statistics
	 * @return
	 */
	@RequestMapping(value = "/exportCompanyLiveStaticDetailByStudentNamePhone")
	public ModelAndView exportCompanyLiveStaticDetailByStudentNamePhone(Model model,
			CompanyLiveStaticDetailVo companyLiveStaticDetailVo) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		companyLiveStaticDetailVo.setPageSize(100);
		log.info("开始导出直播上课统计,获取classLessonId为：" + companyLiveStaticDetailVo.getClassLessionId());
		log.info("开始查询了");
		companyLiveStaticDetailVo.setCompanyId(WebUtils.getCurrentCompanyId());
		PageFinder<CompanyLiveStaticDetailVo> pageFinder = companyLiveStaticDetailServiceImpl
				.queryAllCompanyLiveStaticDetailByStudentNamePhone(companyLiveStaticDetailVo);
		log.info("查询到" + pageFinder.getData().size() + "条数据");
		log.info("开始设置表头");
		String  nameE="";
		
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < pageFinder.getData().size(); i++) {
			CompanyLiveStaticDetailVo s=pageFinder.getData().get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("i",i+1);
			map.put("mobile", s.getMobile());
			map.put("userName", s.getUserName());
			map.put("nickName", s.getNickName());
			map.put("name", s.getName());
			map.put("email", s.getEmail());
			map.put("lessonName", s.getLessonName());
			Integer watchType = s.getWatchType();
			String type = "";
			if(watchType != null){
				if((int)watchType == 0){
					type = "看直播";
				}
				if((int)watchType == 1){
					type = "看回放";
				}
			}
			map.put("watchType", type);
			Date inTime = s.getInTime();
			map.put("inTime", inTime==null?"":sdf.format(inTime));
			nameE=s.getName();
			lists.add(map);
		}
			log.info("开始导出表格");
		StringBuffer title = new StringBuffer(
				"序号:i,课次名称:lesson_name,上课时间:inTime,手机号:mobile,用户名:userName,昵称:nickName,学员名称:name,邮箱:email,学习方式:watchType");
		ViewFiles excel = new ViewFiles();
		HSSFWorkbook wb = new HSSFWorkbook();
		// 查询课程名称
		Map map2 = new HashMap();
		map2.put("classId", "" + companyLiveStaticDetailVo.getClassTypeId());
		ClassTypeVo classType = classTypeServiceImpl.findClassTypeDetail(map2);
		String firstStr = nameE+","+classType.getName();
		try {
			wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString(), firstStr);
		} catch (Exception ex) {

		}
		Map map = new HashMap();
		map.put("workbook", wb);
		map.put("fileName", "直播上课统计.xls");
		return new ModelAndView(excel, map);
	}
}
