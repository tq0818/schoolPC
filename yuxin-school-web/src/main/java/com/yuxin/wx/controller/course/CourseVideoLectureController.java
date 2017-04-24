package com.yuxin.wx.controller.course;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.alibaba.fastjson.JSONArray;
import com.yuxin.wx.api.course.ICourseAfterTestService;
import com.yuxin.wx.api.course.ICourseVideoLectureService;
import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.model.course.CourseAfterTest;
import com.yuxin.wx.model.course.CourseVideoLecture;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CourseVideoLecture
 * 视频：节
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/courseVideoLecture")
public class CourseVideoLectureController {
	
	@Autowired
	private ICourseVideoLectureService courseVideoLectureServiceImpl;
	@Autowired
	private ICourseAfterTestService courseAfterTestServiceImpl;
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CourseVideoLecture search){
		if (search == null) {
			search = new CourseVideoLecture();
			// search.setPageSize(20);
		}
		model.addAttribute("list", courseVideoLectureServiceImpl.findCourseVideoLectureByPage(search));
		return "courseVideoLecture/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CourseVideoLecture courseVideoLecture) {
		courseVideoLectureServiceImpl.insert(courseVideoLecture);
		return "redirect:/courseVideoLecture";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CourseVideoLecture courseVideoLecture) {
		courseVideoLectureServiceImpl.update(courseVideoLecture);
		return "redirect:/courseVideoLecture";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		courseVideoLectureServiceImpl.deleteCourseVideoLectureById(id);
		return "redirect:/courseVideoLecture";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CourseVideoLecture getJson(Model model, @PathVariable Integer id){
		return courseVideoLectureServiceImpl.findCourseVideoLectureById(id);
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
	 * @Description:(添加节的信息)
	 * @author wang.zx 
	 * @date 2015-1-24 下午3:53:02
	 * @version 1.0
	 * @param request
	 * @param lecture
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="ajaxAddLecture", method=RequestMethod.POST)
	public Integer ajaxAddChapter(HttpServletRequest request, CourseVideoLecture lecture){
		Users user = WebUtils.getCurrentUser(request);
		
		if(lecture != null){
			lecture.setCreateTime(new Date());
			lecture.setCreator(user.getId());
			lecture.setPublishStatus(Constant.COURSE_VIDEO_UNPUBLISHED);
			courseVideoLectureServiceImpl.insert(lecture);
		}
		return lecture.getId();
	}
	
	/**
	 * @Description:(更新节信息)
	 * @author wang.zx 
	 * @date 2015-1-24 下午7:20:53
	 * @version 1.0
	 * @param request
	 * @param chapter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="ajaxUpdateChapter", method=RequestMethod.POST)
	public String ajaxUpdateChapter(HttpServletRequest request, CourseVideoLecture lecture){
		try {
			if(lecture != null){
				courseVideoLectureServiceImpl.update(lecture);
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	/**
	 * @Description:(保存章信息)
	 * @author chopin
	 * @date 2015-1-24 下午7:20:53
	 * @version 1.0
	 * @param request
	 * @param chapter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="saveLecture", method=RequestMethod.POST)
	public Map<String,Object> saveLecture(HttpServletRequest request, CourseVideoLecture lecture){
		Map<String,Object> result = new HashMap<String,Object>();
		if(lecture==null){
			result.put("flag", false);
			result.put("msg", "参数错误");
			return result;
		}
		if(StringUtils.isNotBlank(lecture.getLectureName())){
			CourseVideoLecture cvl = courseVideoLectureServiceImpl.findByChapterIdAndLecName(lecture);
			if(cvl != null){
				if(lecture.getId() == null || (int)lecture.getId() != (int)cvl.getId()){
					result.put("flag", false);
					result.put("msg", "已存在相同名称的小节");
					return result;
				}
			}
		}
		if(lecture.getId()!=null){
			courseVideoLectureServiceImpl.update(lecture);
		}else{
			courseVideoLectureServiceImpl.insert(lecture);
		}
		result.put("flag", true);
		result.put("lecture", lecture);
		return result;
	}
	
	/**
	 * @Description:(保存视频课程节的基本信息)
	 * @author chopin
	 * @date 2015-1-20 下午11:55:46
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="addLecture", method=RequestMethod.POST)
	public CourseVideoLecture addLecture(HttpServletRequest request, CourseVideoLecture lecture){
		if(lecture==null){
			lecture=new CourseVideoLecture();
		}
		lecture.setCreateTime(new Date());
		lecture.setCreator(WebUtils.getCurrentUserId(request));
		lecture.setPublishDate(new Date());
		lecture.setPublishStatus("COURSE_VIDEO_PUBLISHED");
		lecture.setDelFlag(0);
		courseVideoLectureServiceImpl.insert(lecture);
		return lecture;
	}
	
	/**
	 * @Description:(删除节)
	 * @author chopin
	 * @date 2015-1-20 下午11:55:46
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delLecture/{id}", method = RequestMethod.POST)
	public String delLcture(Model model, @PathVariable Integer id) {
		courseVideoLectureServiceImpl.deleteCourseVideoLectureById(id);
		return "success";
	}

	/**
	 * @Description:(节排序)
	 * @author chopin
	 * @date 2015-1-20 下午11:55:46
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sortLecture", method = RequestMethod.POST)
	public List<BaseEntity> sortLecture(HttpServletRequest request){
		List<CourseVideoLecture> lectures=JSONArray.parseArray(request.getParameter("list"), CourseVideoLecture.class);
		List nlectures=new ArrayList<CourseVideoLecture>();
		for(CourseVideoLecture lecture :  lectures){
			if(lecture.getType()==1){
				CourseVideoLecture findCourseVideoLectureById = courseVideoLectureServiceImpl.findCourseVideoLectureById(lecture.getId());
				findCourseVideoLectureById.setLectureOrder(lecture.getLectureOrder());
				courseVideoLectureServiceImpl.update(findCourseVideoLectureById);
				findCourseVideoLectureById = courseVideoLectureServiceImpl.findCourseVideoLectureById(lecture.getId());
				findCourseVideoLectureById.setType(1);
				nlectures.add(findCourseVideoLectureById);
			}
			if(lecture.getType()==2){
				CourseAfterTest courseAfterTest= courseAfterTestServiceImpl.findCourseAfterTestById(lecture.getId());
				if(courseAfterTest!=null)
					courseAfterTest.setSort(lecture.getLectureOrder());
				courseAfterTestServiceImpl.update(courseAfterTest);
				courseAfterTest= courseAfterTestServiceImpl.findCourseAfterTestById(lecture.getId());
				courseAfterTest.setType(2);
				nlectures.add(courseAfterTest);
			}
		}
		return nlectures;
	}
	/**
	 * @Description:(改变节所属的章)
	 * @author chopin
	 * @date 2015-1-20 下午11:55:46
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mvLecture", method = RequestMethod.POST)
	public CourseVideoLecture mvLecture(Integer target,Integer lecture){
		CourseVideoLecture l =courseVideoLectureServiceImpl.findCourseVideoLectureById(lecture);
		if(l!=null){
			courseVideoLectureServiceImpl.mvLecture(target, lecture);
		}
		return l;
	}
}
