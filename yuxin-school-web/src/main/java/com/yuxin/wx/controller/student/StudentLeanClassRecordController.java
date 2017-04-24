package com.yuxin.wx.controller.student;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.controller.classes.ClassModuleController;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.student.StudentClassLeanDetailVo;
import com.yuxin.wx.vo.student.StudentClassLeanRecordVo;
import com.yuxin.wx.vo.student.StudentLessTimeVo;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.student.StudentPayMasterVo4;
import com.yuxin.wx.vo.student.StudentTiKuExcipseVo;
import com.yuxin.wx.vo.student.StudentTiKuOrSubjectVo;


@Controller
@RequestMapping("/student_detail")
public class StudentLeanClassRecordController {
	
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	@Autowired
	private IStudentService	studentServiceImpl;
	/*@Autowired
	private IUserHistoryService userHistoryServiceImpl	;*/
//	@Autowired
//	private ICompanyFunctionSetService companyFunctionSetServiceImpl;
	@Autowired
	private IStudentPayMasterService studentPayMasterServiceImpl;
	
	private Log log = LogFactory.getLog("log");
	/**
	 * 打开学员的学习进度页面
	 */
	@RequestMapping(value="/classLeanRecord/{stuId}/{classTypeId}/{lable}")
	public String studenRecord(Model model,@PathVariable Integer stuId,@PathVariable Integer classTypeId,@PathVariable String lable){
		Map<String, String> map = new HashMap<String, String>();
		map.put("classId", "" + classTypeId);
		ClassTypeVo classType=classTypeServiceImpl.findClassTypeDetail(map);
		model.addAttribute("classType", classType);
		model.addAttribute("ct", classType);
 		model.addAttribute("type", "update");
 		model.addAttribute("lable", lable);
 		
		//一、展示学员在该课程的上课情况：包含学习进度百分比、注册时间、报名时间、提问次数、评论次数
 		StudentClassLeanRecordVo pageData = studentServiceImpl.queryStudentLeanRecord(stuId, classTypeId);
		
 		StudentClassLeanDetailVo data = new StudentClassLeanDetailVo();
 		data.setStuId(stuId);
 		data.setClassTypeId(classTypeId);
 		data.setCompanyId(WebUtils.getCurrentCompanyId());
 		data.setSchoolId(WebUtils.getCurrentSchoolId());
 		pageData.setPrecent(leanPercent(data));
 		
 		model.addAttribute("userId", studentServiceImpl.findStudentById(stuId).getUserId());
 		
 		model.addAttribute("leanRec", pageData);
 		model.addAttribute("stuId", stuId);
 		model.addAttribute("classTypeId", classTypeId);
		return "student/student-leanClass-record";
	}
	/**
	 * 查看视频 && 直播课的学习记录统计
	 */
	@ResponseBody
	@RequestMapping(value="/studentLessionHistory", method = RequestMethod.POST)
	public Object studentLessionHistory(StudentClassLeanDetailVo search){
//		String companyType = "one";//是否开启多班号
//		//判断学校是否开启多班号
//		Map<String, Object> param = new HashMap<String,Object>();
//		param.put("companyId", WebUtils.getCurrentCompanyId());
//		param.put("functionCode", "COMPANY_FUNCTION_COURSE");
//		CompanyFunctionSet set = companyFunctionSetServiceImpl.findSetByCom(param);
//		if(null != set && "1".equals(set.getStatus()))
//			companyType = "more";
//		
//		search.setCompanyId(WebUtils.getCurrentCompanyId());
//		search.setSchoolId(WebUtils.getCurrentSchoolId());
//		PageFinder<StudentClassLeanDetailVo>  obj = null;
//		if("one".equals(companyType))
//			obj = studentServiceImpl.queryStudentClassLeanRecord(search);
//		else
//			obj = studentServiceImpl.queryStudentMoreClassLeanRecord(search);
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		PageFinder<StudentClassLeanDetailVo>  obj =  studentServiceImpl.queryStudentCtOrCpLeanRecord(search);
		return obj;
	}
	
	
	/**
	 * 查看视频课程||直播学习详情
	 */
	@ResponseBody
	@RequestMapping(value="/queryStudentLecOrLesStudyDetail", method = RequestMethod.POST)
	public Object queryStudentLecOrLesStudyDetail(StudentClassLeanDetailVo search){
		PageFinder<StudentClassLeanDetailVo>  obj = null;
		if("lecture".equals(search.getType())){
			 obj = studentServiceImpl.queryStudentLecLeanDetail(search);
		}else{
			obj = studentServiceImpl.queryStudentLessLeanDetail(search);
		}
		return obj;
	}
	
	/**
	 * 查询公司所有的题库
	 */
	@ResponseBody
	@RequestMapping(value="/studentTiKuList", method = RequestMethod.POST)
	public Object studentTiKuList(StudentTiKuOrSubjectVo search){
		if(search.getType()==0){
	 		Integer companyId=WebUtils.getCurrentCompanyId();
	 		search.setId(companyId);
			List<StudentTiKuOrSubjectVo> tiLuList = studentServiceImpl.queryCommpanyTiku(search);
			return tiLuList;
		}else{
			List<StudentTiKuOrSubjectVo> subjectList = studentServiceImpl.queryCommpanySubject(search);
			return subjectList;
		}
		
	}
	/**
	 * 查询学生做题记录
	 */
	@ResponseBody
	@RequestMapping(value="/studentTiKuRecord", method = RequestMethod.POST)
	public Object studentTiKuRecord(StudentTiKuExcipseVo StudentTiKuExcipseVo){
		StudentTiKuExcipseVo.setCompanyId(WebUtils.getCurrentCompanyId());
		PageFinder<StudentTiKuExcipseVo> pagefinder = studentServiceImpl.queryStudentTikuExperise(StudentTiKuExcipseVo);
		return pagefinder;
	}
	
	/**
	 * 获取学习进度的百分比
	 */
	public String leanPercent(StudentClassLeanDetailVo data){
		/**
		 * 思路：获取到课程下所有的节，包含视频、直播和面授。
		 * 		统计出用户在视频课次中所学习过的课次，其中user_history表中study_status值为3的记录
		 * 		获取所有的直播和面授的课次的结束时间，遍历集合跟当前时间做比较，其中小于当前时间的为用户已经学习完成的视频和直播课程
		 */
		Integer lecCount = totoalLecture(data);
		Integer lecFinishCount = lectuceFinishCount(data);
		List<StudentLessTimeVo> lessionEndTimeList = totalLessonVo(data);
		int lessonFinishCount = lessonFinishCount(lessionEndTimeList);
		
		return formatePercent(lecCount, lecFinishCount, lessionEndTimeList, lessonFinishCount);
	}
	//格式化百分比进度
	private String formatePercent(Integer lecCount, Integer lecFinishCount, List<StudentLessTimeVo> lessionEndTimeList, int lessonFinishCount) {
		DecimalFormat df = new DecimalFormat("######0.00");   
		double finish = lecFinishCount+lessonFinishCount;
		double sumCount = lecCount+lessionEndTimeList.size();
		if(sumCount == 0)
			return "0.00";
		String f = df.format(finish/sumCount*100);
		return f;
	}
	//直播课程完成数量
	private int lessonFinishCount(List<StudentLessTimeVo> lessionEndTimeList) {
		int lessonFinishCount = 0;
		if(lessionEndTimeList != null && lessionEndTimeList.size()>0){
			Calendar calOld = Calendar.getInstance();
			Calendar calNow = Calendar.getInstance();
			calNow.setTime(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			try {
				for (StudentLessTimeVo studentLessTimeVo : lessionEndTimeList) {
					String dateStr = sdf.format(studentLessTimeVo.getLessonDate());
					String year = dateStr.substring(0, 4);
					String month = dateStr.substring(4, 6);
					String day = dateStr.substring(6, 8);
					String endTime = studentLessTimeVo.getHour();
					String[] hourAndMin = endTime.split(":");
					calOld.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day),Integer.parseInt(hourAndMin[0]), Integer.parseInt(hourAndMin[1]));
					int result = calOld.compareTo(calNow);
					System.out.println(sdf.format(calOld.getTime()));
					if(result<0){	//当前时间大于直播的结束时间则为学习完成该视频
						lessonFinishCount++;
					}
				}
			} catch (NumberFormatException e) {
				log.error("直播课次的结束时间转换异常：数据库数据不完整",e);
				e.printStackTrace();
			}
		}
		return lessonFinishCount;
	}
	
	//直播课程完成数量
		private int lessonFinishCount2(List<StudentLessTimeVo> lessionEndTimeList) {
			int lessonFinishCount = 0;
			if(lessionEndTimeList != null && lessionEndTimeList.size()>0){
				Calendar calOld = Calendar.getInstance();
				Calendar calNow = Calendar.getInstance();
				calNow.setTime(new Date());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				try {
					for (StudentLessTimeVo studentLessTimeVo : lessionEndTimeList) {
						String dateStr = sdf.format(studentLessTimeVo.getLessonDate());
						String year = dateStr.substring(0, 4);
						String month = dateStr.substring(4, 6);
						String day = dateStr.substring(6, 8);
						String endTime = studentLessTimeVo.getHour();
						String[] hourAndMin = endTime.split(":");
						calOld.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day),Integer.parseInt(hourAndMin[0]), Integer.parseInt(hourAndMin[1]));
						int result = calOld.compareTo(calNow);
						System.out.println(sdf.format(calOld.getTime()));
						if(result<0){	//当前时间大于直播的结束时间则为学习完成该视频
							if("TEACH_METHOD_FACE".equals(studentLessTimeVo.getTeachMethod())){
								lessonFinishCount++;
							}else{
								if(studentLessTimeVo.getUserId() != null)
									lessonFinishCount++;
							}
						}
					}
				} catch (NumberFormatException e) {
					log.error("直播课次的结束时间转换异常：数据库数据不完整",e);
					e.printStackTrace();
				}
			}
			return lessonFinishCount;
		}
	//直播课程旷课的数量
	private int truantCount(List<StudentLessTimeVo> lessionEndTimeList) {
		int truantCount = 0;
		if(lessionEndTimeList != null && lessionEndTimeList.size()>0){
			Calendar calOld = Calendar.getInstance();
			Calendar calNow = Calendar.getInstance();
			calNow.setTime(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			try {
				for (StudentLessTimeVo studentLessTimeVo : lessionEndTimeList) {
					String dateStr = sdf.format(studentLessTimeVo.getLessonDate());
					String year = dateStr.substring(0, 4);
					String month = dateStr.substring(4, 6);
					String day = dateStr.substring(6, 8);
					String endTime = studentLessTimeVo.getHour();
					String[] hourAndMin = endTime.split(":");
					calOld.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day),Integer.parseInt(hourAndMin[0]), Integer.parseInt(hourAndMin[1]));
					int result = calOld.compareTo(calNow);
					System.out.println(sdf.format(calOld.getTime()));
					if(result<0 && studentLessTimeVo.getUserId()==null && "TEACH_METHOD_LIVE".equals(studentLessTimeVo.getTeachMethod())){	//当前时间大于直播的结束时间则为学习完成该视频
							truantCount++;
					}
				}
			} catch (NumberFormatException e) {
				log.error("直播课次的结束时间转换异常：数据库数据不完整",e);
				e.printStackTrace();
			}
		}
		return truantCount;
	}
	//所有的直播课次数量（包含课程开始时间）
	private List<StudentLessTimeVo> totalLessonVo(StudentClassLeanDetailVo data) {
		List<StudentLessTimeVo> lessionEndTimeList = studentServiceImpl.queryLessionCount(data);
		return lessionEndTimeList;
	}
	//视频课次完成数量
	private Integer lectuceFinishCount(StudentClassLeanDetailVo data) {
		Integer lecFinishCount = studentServiceImpl.queryLecFinishCount(data);
		return lecFinishCount;
	}
	//所有的视频课次
	private Integer totoalLecture(StudentClassLeanDetailVo data) {
		Integer lecCount = studentServiceImpl.queryLecCount(data);
		return lecCount;
	}
	/**
	 * 打开学生所有的学习课程
	 * @author licong
	 * @date 2016年5月9日 下午7:37:43
	 * @param  
	 * @return
	 */
	@RequestMapping("/openStdentAllCl")
	public String openStdentAllCl(StudentClassLeanDetailVo search,Model model){
		Student stu = studentServiceImpl.findStudentById(search.getStuId());
		model.addAttribute("stu", stu);
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setPageSize(100);
		List<StudentPayMasterVo4> classTypes = studentPayMasterServiceImpl.queryStuHasByClass(search);
		model.addAttribute("classes", classTypes);
		//多个课程的学习报名和学习进度
		if(classTypes != null && classTypes.size()>0){
			List<StudentClassLeanRecordVo> list = new ArrayList<StudentClassLeanRecordVo>();
			for (StudentPayMasterVo4 studentPayMasterVo4 : classTypes) {
				Integer classTypeId = studentPayMasterVo4.getCommdityId();
				Integer stuId = search.getStuId();
				StudentClassLeanRecordVo vo = studentServiceImpl.queryStudentQuestionCount1(stuId,classTypeId);
				vo.setName(studentPayMasterVo4.getClassTypeName());
				vo.setApplyTime(studentPayMasterVo4.getApplyTime());
				StudentClassLeanDetailVo data = new StudentClassLeanDetailVo();
		 		data.setStuId(stuId);
		 		data.setClassTypeId(classTypeId);
		 		data.setCompanyId(WebUtils.getCurrentCompanyId());
		 		data.setSchoolId(WebUtils.getCurrentSchoolId());
		 		vo.setPrecent(leanPercent(data));
				list.add(vo);
			}
			model.addAttribute("list", list);
		}
		model.addAttribute("stuId", search.getStuId());
		return "student/studentLeanRecord";
	}
	/**
	 * 按照课程id查询课程学习记录
	 * @author licong
	 * @date 2016年5月9日 下午11:02:40
	 * @param  
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryClassRecordByClassTypeId")
	public Object queryClassRecordByClassTypeId(StudentClassLeanDetailVo search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		search.setCommodityType("COMMODITY_CLASS");
		PageFinder<StudentClassLeanDetailVo>  obj =  studentServiceImpl.queryStudentCtOrCpLeanRecord(search);
		return obj;
	}
	
	/**
	 * 打开学生所有的做题记录页面
	 * @author licong
	 * @date 2016年5月9日 下午7:37:43
	 * @param  
	 * @return
	 */
	@RequestMapping("/openStdentAllExt")
	public String openStdentAllExt(StudentTiKuExcipseVo StudentTiKuExcipseVo,Model model){
		Student stu = studentServiceImpl.findStudentById(StudentTiKuExcipseVo.getStuId());
		model.addAttribute("stu", stu);
		return "student/studentExerciseRecord";
	}
	
	
	/**
	 * @Description: 导出学生数据（包含课程学习进度百分比、旷课情况）
	 * @author licong
	 * @date 2016年4月28日 下午2:25:47
	 * @param  
	 * @return
	 */
	@RequestMapping("/exportStdent")
	public ModelAndView exportStdent(StudentListVo search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setPageSize(100000);
		PageFinder<StudentListVo> pageFinder=studentServiceImpl.queryStudentsListByClassTypeId(search);
		List<StudentListVo> arr=pageFinder.getData();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (StudentListVo stu : arr) {
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put("name", stu.getName());
			dataMap.put("mobile", stu.getMobile());
			dataMap.put("email", stu.getEmail());
			Date signUpTime = stu.getSignUpTime();
			Date lastLoginTime = stu.getLastLoginTime();
			dataMap.put("signUpTime", signUpTime==null?"":sdf.format(signUpTime));
			dataMap.put("lastLoginTime", lastLoginTime==null?"":sdf.format(lastLoginTime));
			dataMap.put("status", stu.getStatus()==1?"启用":"禁用");
			//学习记录查询
			StudentClassLeanDetailVo data = new StudentClassLeanDetailVo();
			data.setStuId(stu.getId());
			data.setClassTypeId(search.getClassTypeId());
			Integer lecCount = totoalLecture(data);
			Integer lecFinishCount = lectuceFinishCount(data);
			List<StudentLessTimeVo> lessionEndTimeList = totalLessonVo(data);
			int lessonFinishCountNomar = lessonFinishCount(lessionEndTimeList);
			int lessonFinishCount = lessonFinishCount2(lessionEndTimeList);
			int truantCount = truantCount(lessionEndTimeList);
			
			dataMap.put("percent", formatePercent(lecCount, lecFinishCount, lessionEndTimeList, lessonFinishCountNomar)+"%");
			dataMap.put("finishCount", lecFinishCount+lessonFinishCount+"/"+(lecCount+lessionEndTimeList.size()));
			dataMap.put("truantCount", truantCount==0?"-":truantCount);
			
			dataList.add(dataMap);
			dataMap = null;data = null;
		}
		ClassType classType = classTypeServiceImpl.findClassTypeById(search.getClassTypeId());
		String classTypeName = "课程名称："+classType.getName();
		StringBuffer title = new StringBuffer("姓名:name,手机号:mobile,邮箱:email,报名时间:signUpTime,最后登录时间:lastLoginTime,账号状态:status,学习进度:percent,已完成:finishCount,旷课:truantCount");
		ViewFiles excel = new ViewFiles(); 
		HSSFWorkbook wb=new HSSFWorkbook();
		try{
			wb=ExcelUtil.newWorkbook(dataList, "sheet1", title.toString(),classTypeName);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Map<String,Object> view = new HashMap<String,Object>();
		view.put("workbook", wb);
		view.put("fileName", "学员学习记录报表.xls");
		return new ModelAndView(excel,view);
	}
	/**
	 * 学习记录详情报表导出
	 * @author licong
	 * @date 2016年4月28日 下午7:17:25
	 * @param  
	 * @param search
	 * @return
	 */
	@RequestMapping("/exportStuLeanRecord")
	public ModelAndView exproteLeanRecord(StudentClassLeanDetailVo search){
		search.setPageSize(10000);
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		search.setCommodityType("COMMODITY_CLASS");
		PageFinder<StudentClassLeanDetailVo>  obj =  studentServiceImpl.queryStudentCtOrCpLeanRecord(search);
		List<StudentClassLeanDetailVo> list = obj.getData();
		Student stu = studentServiceImpl.findStudentById(search.getStuId());
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		
	
		for (StudentClassLeanDetailVo leanDetail : list) {
			leanDetail = formateLeanRecord(leanDetail);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("classTypeName", leanDetail.getClassTypeName());
			map.put("lectureName", leanDetail.getLectureName());
			map.put("teachMethod", leanDetail.getTeachMethod());
			map.put("lastStudyTime", leanDetail.getStudyTime());
			map.put("studyStatus", leanDetail.getStudyStatus());
			map.put("lastTimeLone", leanDetail.getLastTimeLone());
			dataList.add(map);
			map = null;
		}
		String stuName = (stu.getName()==null||"".equals(stu.getName()))?stu.getMobile():stu.getName();
		String first = "学员："+stuName+","+"手机号："+getStringMoblie(stu.getMobile());
		StringBuffer title = new StringBuffer("课程名称:classTypeName,课次名称:lectureName,教学形式:teachMethod,最后上课时间:lastStudyTime,完成情况:studyStatus,最长上课时间:lastTimeLone");
		ViewFiles excel = new ViewFiles(); 
		HSSFWorkbook wb=new HSSFWorkbook();
		try{
			wb=ExcelUtil.newWorkbook(dataList, "sheet1", title.toString(),first);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Map<String,Object> view = new HashMap<String,Object>();
		view.put("workbook", wb);
		view.put("fileName", stuName+"学习记录报表.xls");
		return new ModelAndView(excel,view);
	}
	
	
	public StudentClassLeanDetailVo formateLeanRecord(StudentClassLeanDetailVo leanDetail){
		if(leanDetail == null)
			return null;
		String studyTime = leanDetail.getStudyTime();
		String studyStatus = leanDetail.getStudyStatus();
		String studyLength = leanDetail.getLastTimeLone();
		if(leanDetail.getType()!=null && "lecture".equals(leanDetail.getType())){
			if("0".equals(studyTime)){
				leanDetail.setStudyTime("未学习");
			}
			
			if("0".equals(studyStatus) || null==studyStatus){
				leanDetail.setStudyStatus("未学习");
			}else if("3".equals(studyStatus)){
				leanDetail.setStudyStatus("已完成");
			}else{
				leanDetail.setStudyStatus("未完成");
			}
			int ssLength = Integer.parseInt(studyLength);
					
			if(0 == ssLength){
				leanDetail.setLastTimeLone("0秒");
			}
			
			if(ssLength/(60*60) > 0){
				int hour = ssLength/(60*60);
				int min = (ssLength%(60*60))/60 ;
				int ss = (ssLength%(60*60))%60;
				leanDetail.setLastTimeLone(hour+"小时"+min+"分钟"+ss+"秒");
			}
			if(ssLength/(60*60) == 0 && (ssLength%(60*60))/60 >0){
				int min = (ssLength%(60*60))/60 ;
				int ss = (ssLength%(60*60))%60;
				leanDetail.setLastTimeLone(min+"分钟"+ss+"秒");
			}
			if(ssLength/(60*60) == 0 && (ssLength%(60*60))/60 == 0){
				int ss = (ssLength%(60*60))%60;
				leanDetail.setLastTimeLone(ss+"秒");
			}
		}else{
			if("0".equals(studyTime) || null == studyTime){
				leanDetail.setStudyTime("直播或面授未开始");
			}
			if(studyLength != null){
				if(studyLength.indexOf(".") != -1){
					String[] temp = studyLength.split(".");
					leanDetail.setLastTimeLone(temp[0]+"小时"+(Double.parseDouble(temp[1])*60)+"分钟");
					temp = null;
				}else{
					leanDetail.setLastTimeLone(studyLength+"小时");
				}
			}
		}
		return leanDetail;
	}
	/**
	 * 学员做题记录导出
	 * @author licong
	 * @date 2016年4月28日 下午11:21:32
	 * @param  
	 * @param search
	 * @return
	 */
	@RequestMapping("/exportExerciseRecord")
	public ModelAndView exportExerciseRecord(StudentTiKuExcipseVo StudentTiKuExcipseVo){
		StudentTiKuExcipseVo.setPageSize(10000);
		StudentTiKuExcipseVo.setCompanyId(WebUtils.getCurrentCompanyId());
		if(StudentTiKuExcipseVo.getStartTime() != null && "做题时间".equals(StudentTiKuExcipseVo.getStartTime())){
			StudentTiKuExcipseVo.setStartTime("");
		}
		PageFinder<StudentTiKuExcipseVo> pagefinder = studentServiceImpl.queryStudentTikuExperise(StudentTiKuExcipseVo);
		List<StudentTiKuExcipseVo> list = pagefinder.getData();
		Student stu = studentServiceImpl.findStudentById(StudentTiKuExcipseVo.getStuId());
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
	
		for (StudentTiKuExcipseVo ske : list) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("startTime", ske.getStartTime());
			map.put("tiKuName", ske.getTiKuName());
			map.put("subjectName", ske.getSubjectName());
			map.put("exerciseType", ske.getExerciseType());
			
			double totalCount = ske.getTotalTopic()==null?0:ske.getTotalTopic();
			double rigCount = ske.getRigCount()==null?0:ske.getRigCount();
			double errorCount = ske.getErrorCount()==null?0:ske.getErrorCount();
			double finishCount = rigCount + errorCount;
			DecimalFormat df = new DecimalFormat("######0.00");   
			String f = totalCount==0?"0.00":df.format(rigCount/totalCount*100);
			map.put("totalTopic", (int)finishCount+"/"+(int)totalCount);
			map.put("score", (null == ske.getExerciseScore() ? 0 : ske.getExerciseScore()));
			map.put("rigPercent",  f+"%");
			map.put("errorCount", "正确"+(int)rigCount+"  错误"+(int)errorCount);
			
			dataList.add(map);
			map = null;
		}
		String stuName = (stu.getName()==null||"".equals(stu.getName()))?stu.getMobile():stu.getName();
		String first = "学员："+stuName+","+"手机号："+getStringMoblie(stu.getMobile());
		StringBuffer title = new StringBuffer("做题时间:startTime,题库:tiKuName,科目:subjectName,类型:exerciseType,做题数:totalTopic,得分:score,正确率:rigPercent,错题数:errorCount");
		ViewFiles excel = new ViewFiles(); 
		HSSFWorkbook wb=new HSSFWorkbook();
		try{
			wb=ExcelUtil.newWorkbook(dataList, "sheet1", title.toString(),first);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Map<String,Object> view = new HashMap<String,Object>();
		view.put("workbook", wb);
		view.put("fileName", stuName+"做题记录报表.xls");
		return new ModelAndView(excel,view);
	}
	
	public String getStringMoblie(String mobile){
		if(StringUtils.isBlank(mobile)){
			mobile = "";
		}
		return mobile;
	}
	
}
