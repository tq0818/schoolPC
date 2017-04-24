package com.yuxin.wx.controller.course;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

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

import com.yuxin.wx.api.course.ICourseAfterTestContentService;
import com.yuxin.wx.api.tiku.ITikuChapterService;
import com.yuxin.wx.api.tiku.ITikuPaperService;
import com.yuxin.wx.api.tiku.ITikuSectionService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.course.CourseAfterTestContent;
import com.yuxin.wx.model.tiku.TikuChapter;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.TikuSection;

/**
 * Controller of CourseAfterTestContent
 * @author chopin
 * @date 2016-9-1
 */
@Controller
@RequestMapping("/courseAfterTestContent")
public class CourseAfterTestContentController {
	
	@Autowired
	private ICourseAfterTestContentService courseAfterTestContentServiceImpl;
	
	@Autowired
	private ITikuChapterService tikuChapterServiceImpl;
	
	@Autowired
	private ITikuSectionService tikuSectionServiceImpl;
	
	@Autowired
	private ITikuPaperService tikuPaperServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CourseAfterTestContent search){
		if (search == null) {
			search = new CourseAfterTestContent();
			// search.setPageSize(20);
		}
		model.addAttribute("list", courseAfterTestContentServiceImpl.findCourseAfterTestContentByPage(search));
		return "courseAfterTestContent/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CourseAfterTestContent CourseAfterTestContent) {
		courseAfterTestContentServiceImpl.insert(CourseAfterTestContent);
		return "redirect:/courseAfterTestContent";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CourseAfterTestContent CourseAfterTestContent) {
		courseAfterTestContentServiceImpl.update(CourseAfterTestContent);
		return "redirect:/courseAfterTestContent";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		courseAfterTestContentServiceImpl.deleteCourseAfterTestContentById(id);
		return "redirect:/courseAfterTestContent";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CourseAfterTestContent getJson(Model model, @PathVariable Integer id){
		return courseAfterTestContentServiceImpl.findCourseAfterTestContentById(id);
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
	@RequestMapping(value="/saveTestContent" ,method = RequestMethod.POST)
	public CourseAfterTestContent saveTestContent(CourseAfterTestContent item,HttpServletRequest request){
		
		if(item != null && item.getPaperId() !=null){
			List<CourseAfterTestContent> list = courseAfterTestContentServiceImpl.findCourseAfterTestContentByPage(item);
			if(list != null && list.size() >0){
				courseAfterTestContentServiceImpl.update(item);
			}else{
				courseAfterTestContentServiceImpl.insert(item);
			}
		}else{
			courseAfterTestContentServiceImpl.insert(item);
		}
		return item;
	}
	
	@ResponseBody
	@RequestMapping(value="/findContentListByTestId",method=RequestMethod.POST)
	public List<CourseAfterTestContent>  findContentListByTestId(HttpServletRequest request){
		String testIdstr = request.getParameter("testId");
		Integer testId;
		try {
			testId = Integer.parseInt(testIdstr);
			if(testId != null){
				List<CourseAfterTestContent> list = courseAfterTestContentServiceImpl.findContentListByTestId(testId);
				if(list != null && list.size() >0){
					for (CourseAfterTestContent cc : list) {
						TikuChapter tikuChapter = tikuChapterServiceImpl.findTikuChapterById(cc.getChapterId());
						if(tikuChapter != null){
							cc.setChapterName(tikuChapter.getChapterName());
						}
						TikuSection tikuSection = tikuSectionServiceImpl.findTikuSectionById(cc.getLectureId());
						if(tikuSection != null){
							cc.setSectionName(tikuSection.getSectionName());
						}
					}
				}
				return list;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/findContentListByTestId2",method=RequestMethod.POST)
	public List<CourseAfterTestContent>  findContentListByTestId2(HttpServletRequest request){
		
		String testIdstr = request.getParameter("testId");
		Integer testId;
		try {
			testId = Integer.parseInt(testIdstr);
			if(testId != null){
				List<CourseAfterTestContent> list = courseAfterTestContentServiceImpl.findContentListByTestId2(testId);
				if(list != null && list.size() >0){
					for (CourseAfterTestContent cc : list) {
						TikuPaper tiku = tikuPaperServiceImpl.findTikuPaperById(cc.getPaperId());
						if(tiku != null){
							cc.setPaperName(tiku.getPaperName());
						}
					}
				}
				
				return list;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/delContent",method=RequestMethod.POST)
	public String delContent(HttpServletRequest request){
		
		String contentIdStr = request.getParameter("contentId");
		if(contentIdStr != null && contentIdStr != ""){
			Integer contentId = Integer.parseInt(contentIdStr);
			if(contentId != null){
				CourseAfterTestContent contentById = courseAfterTestContentServiceImpl.findCourseAfterTestContentById(contentId);
				if(contentById != null){
					courseAfterTestContentServiceImpl.deleteCourseAfterTestContentById(contentId);
				}
			}
		}
		return "success";
	}
}
