package com.yuxin.wx.controller.course;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.yuxin.wx.api.course.ICourseVideoChapterService;
import com.yuxin.wx.api.course.ICourseVideoService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.config.Config;
import com.yuxin.wx.model.course.CourseVideo;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.APIServiceFunction;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.course.ChapterAndLectureListVo;
import com.yuxin.wx.vo.course.CourseVideoVo;

/**
 * Controller of CourseVideo
 * 视频： 基础信息
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/courseVideo")
public class CourseVideoController {
	
	@Autowired
	private ICourseVideoService courseVideoServiceImpl;
	
	@Autowired
	private ICourseVideoChapterService courseVideoChapterServiceImpl;
	
	@Autowired
	private ISysConfigTeacherService sysConfigTeacherServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CourseVideo search){
		if (search == null) {
			search = new CourseVideo();
			// search.setPageSize(20);
		}
		model.addAttribute("list", courseVideoServiceImpl.findCourseVideoByPage(search));
		return "courseVideo/list";
	}
	
	/**
	 * @Description:(添加视频课程)
	 * @author wang.zx 
	 * @date 2015-1-14 下午4:54:07
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add")
	public String addVideo(Model model, HttpServletRequest request) {
		
		/**
		 * 上传视频之前获取对应的分类,目前没有用，先注释   2014-01-15
		 */
//		Map<String, String> paramsMap = new HashMap<String, String>();
//		paramsMap.put("userid", Config.userid);
//		paramsMap.put("format", "json");   //json数据格式返回，默认为xml
//		long time = System.currentTimeMillis();
//		String requestURL = APIServiceFunction.createHashedQueryString(paramsMap, time, Config.key);
//		//get方式
//		String responseStr = APIServiceFunction.HttpRetrieve(Config.api_category + "?" + requestURL);
//		model.addAttribute("json", responseStr);
		
		
		return "course/video/addVideo";
	}
	
	@RequestMapping(value="editVideo")
	public String editVideo(Model model, HttpServletRequest request, Integer id){
		Users user = WebUtils.getCurrentUser(request);
		
		//根据Id查询视频课程信息
		CourseVideo video = courseVideoServiceImpl.findCourseVideoById(id);
		
		CourseVideoVo videoVo = courseVideoServiceImpl.findCourseVideoVoByVideoId(id);
		
		//获取用户对应的学校ID，查询对应的老师列表
		List<SysConfigTeacher> teachers = sysConfigTeacherServiceImpl.findTeacherBySchoolAndModuleId(user.getSchoolId(), null, Constant.PERSON_TEACHER);
		model.addAttribute("teachers", teachers);
		
		if(video != null && video.getId() > 0){
			model.addAttribute("video", video);
			model.addAttribute("videoVo", videoVo);
			
			//根据video查询对应的章
		    List<ChapterAndLectureListVo> chapterAndLectureVos = courseVideoChapterServiceImpl.findChapterAndLectureByVideoId(id);
		    model.addAttribute("chapterAndLectures", chapterAndLectureVos);
		}
		
		return "course/video/editVideo";
	}
	
	/**
	 * @Description:(ajax上传CC视频文件)
	 * @author wang.zx 
	 * @date 2015-1-15 下午5:59:38
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="ajaxAddVideo",method = RequestMethod.GET)
	public String ajaxAddVideo(HttpServletRequest request){
		String title = request.getParameter("title");
		String tag = request.getParameter("tag");
		String description = request.getParameter("description");
		String categoryId = request.getParameter("categoryid");
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("userid",Config.userid);
		paramsMap.put("title", title);//new String(title.getBytes("ISO-8859-1"), "UTF-8"));
		paramsMap.put("description",description);//new String(description.getBytes("ISO-8859-1"), "UTF-8"));
		paramsMap.put("tag",tag);//new String(tag.getBytes("ISO-8859-1"), "UTF-8"));
		paramsMap.put("categoryid",categoryId);//new String(categoryId.getBytes("ISO-8859-1"), "UTF-8"));
		long time = System.currentTimeMillis();
		String salt = Config.key;
		String requestURL = APIServiceFunction.createHashedQueryString(paramsMap, time, salt);
		
		return requestURL;
	}
	
	/**
	 * @Description:(保存视频课程的基本信息)
	 * @author wang.zx 
	 * @date 2015-1-20 下午11:55:54
	 * @version 1.0
	 * @param request
	 * @param video
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="ajaxAddVideoInfo",method = RequestMethod.POST)
	public Integer ajaxAddVideoInfo(HttpServletRequest request, CourseVideo video){
		Users user = WebUtils.getCurrentUser(request);
		
		if(video != null && user != null){
			if(video.getId() != null && video.getId() > 0){
				courseVideoServiceImpl.update(video);
			}else{
				//新创建的视频课程，所以状态设置为新创建
				video.setPublishStatus(Constant.COURSE_VIDEO_DISABLE);
				video.setCreateTime(new Date());
				
				if(user != null){
					video.setCreator(user.getId());
					video.setSchoolId(user.getSchoolId());
					video.setCompanyId(user.getCompanyId());
				}
				
				courseVideoServiceImpl.insert(video);
			}
		}
		return video.getId();
	}
	
	@ResponseBody
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CourseVideo courseVideo,HttpServletRequest request) {
		int currentUserId=WebUtils.getCurrentUserId(request);
		courseVideo.setUpdator(currentUserId);
		courseVideo.setUpdateTime(new Date());
		courseVideoServiceImpl.update(courseVideo);
		return "success";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		courseVideoServiceImpl.deleteCourseVideoById(id);
		return "redirect:/courseVideo";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CourseVideo getJson(Model model, @PathVariable Integer id){
		return courseVideoServiceImpl.findCourseVideoById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/findList/{moduleId}", method = RequestMethod.POST)
	public List<CourseVideo> findList(@PathVariable Integer moduleId){
		return courseVideoServiceImpl.findCourseVideoByModuleId(moduleId);
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
	 * @Description:(添加是否必填项页面测试)
	 * @author wang.zx 
	 * @date 2014-12-10 上午11:31:14
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="addTest")
	public String addTest(Model model){
		List<String> list = new ArrayList<String>();
		list.add("测试C标签1");
		list.add("测试C标签2");
		list.add("测试C标签3");
		model.addAttribute("list", list);
		return "course/addTestInputMust";
	}
	
	@RequestMapping(value="/videoIndex")
	public String videoIndex(Model model) {
		return "/course/video/videoIndex";
	}
	
	@RequestMapping(value="/videoAjaxList", method = RequestMethod.POST)
	public String videoAjaxList(Model model,CourseVideo search) {
		PageFinder<CourseVideoVo> pageFinder=courseVideoServiceImpl.queryVideoByKeys(search);
		model.addAttribute("pageFinder", pageFinder);
		return "/course/video/videoAjaxList";
	}
	
}
