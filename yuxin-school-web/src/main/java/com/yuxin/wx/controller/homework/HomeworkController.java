package com.yuxin.wx.controller.homework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleNoOnsaleService;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.course.ICourseVideoChapterService;
import com.yuxin.wx.api.course.ICourseVideoLectureService;
import com.yuxin.wx.api.homework.IHomeworkService;
import com.yuxin.wx.api.homework.IHomeworkStudentCompleteService;
import com.yuxin.wx.api.resource.IResourceListService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.api.tiku.ITikuPaperService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.course.CourseVideoLecture;
import com.yuxin.wx.model.homework.Homework;
import com.yuxin.wx.model.homework.HomeworkStudentComplete;
import com.yuxin.wx.model.resource.ResourceList;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.homework.HomeWorkVo;
import com.yuxin.wx.vo.homework.StudentHomeWorkVo;

/**
 * Controller of Homework
 * @author chopin
 * @date 2016-12-15
 */
@Controller
@RequestMapping("/homework")
public class HomeworkController {
	
	@Autowired
	private IHomeworkService homeworkServiceImpl;
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	@Autowired
	private ISysConfigTeacherService sysConfigTeacherServiceImpl;
	@Autowired
	private IStudentPayMasterService studetPayMasterServiecImpl;
	@Autowired
	private IClassModuleService classModuleServiceImpl;
	@Autowired
	private IClassModuleNoService classModuleNoServiecImpl;
	@Autowired
	private IClassModuleNoOnsaleService classModuleNoOnsaleServceImpl;
	@Autowired
	private IClassModuleLessonService classModuleLessonServiecImpl;
	@Autowired
	private ICourseVideoChapterService courseVideoChaperServiecImpl;
	@Autowired
	private ICourseVideoLectureService courseVideoLectureServiceImpl;
	@Autowired
	private IHomeworkStudentCompleteService homeworkStudentCompleteServiceImpl;
	@Autowired
	private ITikuPaperService tikuPaperServiecImpl;
	@Autowired
	private IResourceListService resourceListServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Homework search){
		if (search == null) {
			search = new Homework();
			// search.setPageSize(20);
		}
		model.addAttribute("list", homeworkServiceImpl.findHomeworkByPage(search));
		return "homework/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(Homework Homework) {
		homeworkServiceImpl.insert(Homework);
		return "redirect:/homework";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(Homework Homework) {
		homeworkServiceImpl.update(Homework);
		return "redirect:/homework";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		homeworkServiceImpl.deleteHomeworkById(id);
		return "redirect:/homework";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Homework getJson(Model model, @PathVariable Integer id){
		return homeworkServiceImpl.findHomeworkById(id);
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
	 * 课后作业列表页面
	 * @author licong
	 * @date 2016年12月15日 下午4:58:29
	 * @param  
	 * @param request
	 * @return
	 */
	@RequestMapping("/toHomeWorkIndex")
	public String toHomeWorkIndex(HttpServletRequest request,Model model){
		model.addAttribute("companyId", WebUtils.getCurrentCompanyId());
		return "homework/classwork";
	}
	
	/**
	 * 查询机构所有的课程
	 * @author licong
	 * @date 2016年12月19日 上午10:55:58
	 * @param  
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllClass")
	public Object findAllClass(HttpServletRequest request){
		ClassType cp = new ClassType();
		cp.setCompanyId(WebUtils.getCurrentCompanyId());
		Subject subject = SecurityUtils.getSubject();
//		if(!subject.hasRole("机构管理员")){
//			cp.setSchoolsId(WebUtils.getCurrentSchoolId()+"");
//		}
		List<ClassType> list = classTypeServiceImpl.findAllclassType(cp);
		return list;
	}
	
	/**
	 * 查询一个课程下面的所有课次（包括录播、直播、面授）
	 * @author licong
	 * @date 2016年12月15日 下午5:47:23
	 * @param  
	 * @param classTypeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findClassTypeLesson")
	public Object findClassTypeLesson(HomeWorkVo params,HttpServletRequest request){
		if(params.getCourseId() == null){
			PageFinder<HomeWorkVo> pf = new PageFinder<HomeWorkVo>(params.getPage(),params.getPageSize(), 0, new ArrayList<HomeWorkVo>());
			return pf;
		}
		
		//判断当前登录人的权限,课程名师/作业批阅教师可以查看该课程的所有课次，并留作业
		String role = null;
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole("作业老师")){
			role = "作业老师";
		}
		Integer userId = WebUtils.getCurrentUserId(request);
		SysConfigTeacher userTeacher = sysConfigTeacherServiceImpl.findByUserId(userId);
		
		List<HomeWorkVo> lessonAndHomeworks = new ArrayList<HomeWorkVo>();
		//视频课次
		List<CourseVideoLecture> lectures = new ArrayList<CourseVideoLecture>();
		//直播和面授课次
		List<ClassModuleLesson> lessons = new ArrayList<ClassModuleLesson>();
		
		ClassTypeVo classTypeVo = classTypeServiceImpl.findClassTypeVoByClassTypeId(params.getCourseId());
		String teacherId = classTypeVo.getTeacherId();
		if(role == null && teacherId != null && userTeacher != null && (int)Integer.parseInt(teacherId) == (int)userTeacher.getId()){
			role = "课程名师";
		}
//		Integer buyNum = studetPayMasterServiecImpl.selectStuNumByComId(classTypeVo.getCommodityId());
//		buyNum = buyNum==null?0:buyNum;
		Integer videoFlag = classTypeVo.getVideoFlag();
		Integer liveFlag = classTypeVo.getLiveFlag();
		Integer faceFlag = classTypeVo.getFaceFlag();
		Integer remoteFlag = classTypeVo.getRemoteFlag();
		
		List<ClassModule> classModules = classModuleServiceImpl.findByClassTypeId(params.getCourseId());
		//查询每个模块购买的学员数量
		Map<String,Integer> countMap = new HashMap<String,Integer>();
		HomeWorkVo searchStuNum = new HomeWorkVo();
		searchStuNum.setCourseId(classTypeVo.getId());
		
		for (ClassModule classModule : classModules) {
			
			Integer moduleId = classModule.getId();
			searchStuNum.setModuleId(moduleId);
			
			//视频课次
			if("TEACH_METHOD_VIDEO".equals(classModule.getTeachMethod()) && ("作业老师".equals(role) || "课程名师".equals(role))){
				searchStuNum.setMorl(moduleId);
				Integer lecStuCount = homeworkServiceImpl.findLessonStuByCount(searchStuNum);
				countMap.put("lec"+moduleId+moduleId, lecStuCount);
				
				List<Integer> courseVideoChapters = courseVideoChaperServiecImpl.findIdByModuleId(moduleId);
				for (Integer chapterId : courseVideoChapters) {
					List<CourseVideoLecture> courseVideoLectures = courseVideoLectureServiceImpl.findVideoLectureByChapterId(chapterId);
					for (CourseVideoLecture courseVideoLecture : courseVideoLectures) {
						courseVideoLecture.setModuleId(classModule.getId());
					}
					lectures.addAll(courseVideoLectures);
				}
			}
			
			//直播的在售班号的课次
			if("TEACH_METHOD_LIVE".equals(classModule.getTeachMethod())){
				List<ClassModuleNo> classMoudleNos = classModuleNoServiecImpl.findModuleNoOnSaleByModuleId(moduleId);
				for (ClassModuleNo classModuleNo : classMoudleNos) {
					Integer moduleNoId = classModuleNo.getId();
					searchStuNum.setMorl(moduleNoId);
					Integer lesStuCount = homeworkServiceImpl.findLessonStuByCount(searchStuNum);
					countMap.put("les"+moduleId+moduleNoId, lesStuCount);
					
					List<ClassModuleLesson> classModuleLessons = classModuleLessonServiecImpl.findcmlByModuleNoId(classModuleNo.getId());
					for (ClassModuleLesson classModuleLesson : classModuleLessons) {
						String lessonTeacher = classModuleLesson.getTeachers();
						
						classModuleLesson.setModuleId(classModule.getId());
						classModuleLesson.setModuleNoId(classModuleNo.getId());
						classModuleLesson.setModuleNoName(classModuleNo.getName());
						if("作业老师".equals(role) || "课程名师".equals(role) || (lessonTeacher != null && userTeacher != null && Integer.parseInt(lessonTeacher) == (int)userTeacher.getId())){
							lessons.add(classModuleLesson);
						}
						
					}
				}
//				ClassModuleNoOnsale classModuleNoOnSale = classModuleNoOnsaleServceImpl.queryModuleOnSaleModuleNo(classModule.getId());
//				ClassModuleNo classModuleNo = classModuleNoServiecImpl.findClassModuleNoById(classModuleNoOnSale.getModuleNoId());
//				List<ClassModuleLesson> classModuleLessons = classModuleLessonServiecImpl.findcmlByModuleNoId(classModuleNoOnSale.getModuleNoId());
				
			}
		}
		
		//直播和面授的课次按照日期正序排序
		Collections.sort(lessons, new Comparator<ClassModuleLesson>(){
			@Override
			public int compare(ClassModuleLesson a0, ClassModuleLesson a1) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				long time0 =0;
				long time1 =0;
				try {
					String format0 = sdf.format(a0.getLessonDate());
					String lessonTimeStart0 = a0.getLessonTimeStart();
					String aa = format0 + " "+ lessonTimeStart0 + ":00";
					time0 = sdf.parse(aa).getTime();
					a0.setDateTime(time0);
					
					String format1 = sdf.format(a1.getLessonDate());
					String lessonTimeStart1 = a1.getLessonTimeStart();
					String aa1 = format1 + " " + lessonTimeStart1 + ":00";
					time1 = sdf.parse(aa1).getTime();
					a1.setDateTime(time1);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(time0 == time1){
					return a0.getId() - a1.getId();
				}
				return (int)(time0 - time1);
			}
		});
		
		for (ClassModuleLesson lesson : lessons) {
			HomeWorkVo hw = new HomeWorkVo();
			hw.setLessonId(lesson.getId());
			hw.setLessonName(lesson.getLessonName());
			hw.setModuleId(lesson.getModuleId());
			hw.setClassModuleNoName(lesson.getModuleNoName());
			hw.setMorl(lesson.getModuleNoId());
			hw.setLessonType(2);
			lessonAndHomeworks.add(hw);
		}
		for (CourseVideoLecture lectrue : lectures) {
			HomeWorkVo hw = new HomeWorkVo();
			hw.setLectureId(lectrue.getId());
			hw.setLessonName(lectrue.getLectureName());
			hw.setModuleId(lectrue.getModuleId());
			hw.setMorl(lectrue.getModuleId());
			hw.setLessonType(1);
			lessonAndHomeworks.add(hw);
		}
		
		List<HomeWorkVo> searchs = new ArrayList<HomeWorkVo>();
		StudentHomeWorkVo shw = new StudentHomeWorkVo();
		shw.setCourseId(classTypeVo.getId());
		
		for (HomeWorkVo hw : lessonAndHomeworks) {
			hw.setCompanyId(WebUtils.getCurrentCompanyId());
			hw.setCourseId(classTypeVo.getId());
			hw.setCourseName(classTypeVo.getName());
			if(videoFlag == 1 && liveFlag == 0 && faceFlag == 0){
				hw.setClassType("录播");
			}else if(videoFlag == 0 && liveFlag == 1 && faceFlag == 0){
				hw.setClassType("直播");
			}else if(videoFlag == 0 && liveFlag == 0 && faceFlag == 1){
				hw.setClassType("面授");
			}else if(remoteFlag != null && (int)remoteFlag == 1){
				hw.setClassType("其他");
			}else{
				hw.setClassType("混合");
			}
//			Integer buyNum = homeworkServiceImpl.findLessonStuByCount(hw);
			Integer buyNum = null;
			if((int)hw.getLessonType() == 1){
				buyNum = countMap.get("lec"+hw.getModuleId()+hw.getMorl());
			}else{
				buyNum = countMap.get("les"+hw.getModuleId()+hw.getMorl());
			}
			hw.setStuNum(buyNum);				//学员数量
			
			Homework homework = homeworkServiceImpl.findHomeworkByLessonId(hw);
			if(homework != null){
				//基础数据
				hw.setId(homework.getId());
//				hw.setDesciption(homework.getDesciption()); 这是一个坑，别问为什么
				hw.setType(homework.getType());
				hw.setPaperId(homework.getPaperId());
				hw.setResourceId(homework.getResourceId());
				hw.setStatus(homework.getStatus());
				hw.setTeacherId(homework.getTeacherId());
				
				shw.setHomeworkId(homework.getId());
				List<HomeworkStudentComplete> hwscs = homeworkStudentCompleteServiceImpl.findByHomeworkId(shw);
				hw.setCommitedHomeWork(hwscs.size()); //交作业数量
				int num = buyNum - hwscs.size();
				hw.setNotCommitedHomeWork(num<0?0:num);//未交作业数量
				int readedNum = 0;
				for (HomeworkStudentComplete homeworkStudentComplete : hwscs) {
					Integer hscStatus = homeworkStudentComplete.getStatus();
					if(hscStatus != null && ((int)hscStatus == 3 || (int)hscStatus == 2)){
						readedNum++;
					}
				}
				hw.setReadedHomeWork(readedNum);//已批阅
				
				
				if((int)buyNum == 0){
					hw.setHomeworkStatus(1);//没人购买（已批阅完作业）
					hw.setNotReadedHomeWork(0);
				}else if(hwscs.size() == readedNum){
					hw.setHomeworkStatus(2);//已批阅完作业
					hw.setNotReadedHomeWork(0);
				}else{
					hw.setHomeworkStatus(3);//还未完成批阅
					hw.setNotReadedHomeWork(hwscs.size() - readedNum);//未批阅作业数量
				}
			}else{
				hw.setHomeworkStatus(0);//未留作业
				hw.setNotReadedHomeWork(0);
				
				hw.setCommitedHomeWork(0); //交作业数量
				hw.setNotCommitedHomeWork(buyNum); //未交作业数量
				hw.setReadedHomeWork(0);//已批阅
				hw.setNotReadedHomeWork(0);//未批阅作业数量
			}
			
			
			
			//页面有搜索条件“批阅状态”
			if(params.getHomeworkStatus() != null){
				if((int)params.getHomeworkStatus() == 0){//未留作业
					if((int)hw.getHomeworkStatus() == 0)
					searchs.add(hw);
				}else if((int)params.getHomeworkStatus() == 3){//未批阅完成
					if((int)hw.getHomeworkStatus() == 3)
					searchs.add(hw);
				}else{										//批阅完成
					if((int)hw.getHomeworkStatus() == 1 || (int)hw.getHomeworkStatus() == 2)
					searchs.add(hw);
				}
			}else{
				searchs.add(hw);
			}
		}
		
		//先按照批阅状态倒序，为批阅数量，先直播后录播
		Collections.sort(searchs, new Comparator<HomeWorkVo>(){
			@Override
			public int compare(HomeWorkVo o1, HomeWorkVo o2) {
				if((int)o1.getHomeworkStatus() == (int)o2.getHomeworkStatus()){
					if((int)o1.getNotReadedHomeWork() == (int)o2.getNotReadedHomeWork()){
						return o2.getLessonType() - o1.getLessonType();
					}else{
						return o2.getNotReadedHomeWork() - o1.getNotReadedHomeWork();
					}
				}else{
					return o2.getHomeworkStatus() - o1.getHomeworkStatus();
				}
			}
		});
		
		//分页
		int totalNum = searchs.size();
		int firstIndex = params.getFirstIndex();
		int pageSize = params.getPageSize();
		int endIndex = firstIndex + pageSize;
		
		List<HomeWorkVo> subList = null;
		if(endIndex > totalNum){
			endIndex = totalNum;
		}
		if(firstIndex > endIndex || firstIndex > totalNum || totalNum == 0){
			subList = new ArrayList<HomeWorkVo>();
		}else{
			subList = searchs.subList(firstIndex, endIndex);
		}
		PageFinder<HomeWorkVo> pf = new PageFinder<HomeWorkVo>(params.getPage(),params.getPageSize(), totalNum, subList);
		
		return pf;
	}
	
	/**
	 * 查询所有的试卷
	 * @author licong
	 * @date 2016年12月19日 上午11:15:55
	 * @param  
	 * @param request
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findTikuPaper")
	public Object findTikuPaper(HttpServletRequest request,TikuPaper search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		return tikuPaperServiecImpl.findTikuPaperVoByPage(search);
	}
	
	/**
	 * 新增或者修改作业
	 * @author licong
	 * @date 2016年12月19日 下午5:33:20
	 * @param  
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insertOrUpdate")
	public Object insertOrUpdate(Homework hw,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		
		Integer userId = WebUtils.getCurrentUserId(request);
		SysConfigTeacher userTeacher = sysConfigTeacherServiceImpl.findByUserId(userId);
		if(userTeacher != null)
			hw.setTeacherId(userTeacher.getId());
		
		if(hw.getId() != null){
			hw.setCreateTime(null);//这是一个坑，别管为什么
			homeworkServiceImpl.update(hw);
		}else{
			HomeWorkVo search = new HomeWorkVo();
			search.setCompanyId(WebUtils.getCurrentCompanyId());
			search.setLectureId(hw.getLectureId());
			search.setLessonId(hw.getLessonId());
			Homework homework = homeworkServiceImpl.findHomeworkByLessonId(search);
			if(homework != null){
				hw.setId(homework.getId());
				hw.setCreateTime(null);//这是一个坑，别管为什么
				homeworkServiceImpl.update(hw);
			}else{
				hw.setCompanyId(WebUtils.getCurrentCompanyId());
				ClassTypeVo classTypeVo = classTypeServiceImpl.findClassTypeVoByClassTypeId(hw.getCourseId());
				hw.setCourseId(classTypeVo.getId());
				hw.setCourseName(classTypeVo.getName());
				if(hw.getLessonId() != null) 
					hw.setLessonName(classModuleLessonServiecImpl.findClassModuleLessonById(hw.getLessonId()).getLessonName());
				if(hw.getLectureId() != null)
					hw.setLessonName(courseVideoLectureServiceImpl.findCourseVideoLectureById(hw.getLectureId()).getLectureName());
				hw.setStatus(1);
				hw.setCreateTime(new Date());
				homeworkServiceImpl.insert(hw);
			}
		}
		result.put("flag", true);
		result.put("hw", hw);
		return result;
	}
	
	/**
	 * 查询单个作业
	 * @author licong
	 * @date 2016年12月19日 下午5:33:20
	 * @param  
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selOne")
	public Object selOne(HomeWorkVo hw){
		Map<String,Object> result = new HashMap<String,Object>();
		if(hw.getId() == null){
			result.put("flag", false);
			result.put("msg", "参数错误");
			return result;
		}
		Homework homework = homeworkServiceImpl.findHomeworkById(hw.getId());
		if((int)homework.getType() == 1 && homework.getPaperId() != null){
			TikuPaper tikuPaper = tikuPaperServiecImpl.findTikuPaperById(homework.getPaperId());
			if(tikuPaper == null){
				homework.setPaperId(null);
				homework.setPaperName("");
			}else{
				homework.setPaperName(tikuPaper.getPaperName());
			}
		}
		if((int)homework.getType() == 2 && homework.getResourceId() != null){
			ResourceList resource = resourceListServiceImpl.findResourceListById(homework.getResourceId());
			if(resource != null && resource.getDelFlag() != null && (int)resource.getDelFlag() != 1)
			homework.setFileName(resource.getFileName());
		}
		result.put("flag", true);
		result.put("hw", homework);
		return result;
	}
	
	
//	@ResponseBody
//	@RequestMapping("/delFile")
//	public Object delFile(Integer id){
//		
//	}
	
	
}
