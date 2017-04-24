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
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassTypeModuleRelationService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.course.ICourseVideoChapterService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeModuleRelation;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.course.ChapterAndLectureListVo;

/**
 * Controller of CourseVideoChapter
 * 视频： 章
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/courseVideoChapter")
public class CourseVideoChapterController {
	
	@Autowired
	private ICourseVideoChapterService courseVideoChapterServiceImpl;
	
	@Autowired
	private IClassModuleService classModuleSerivceImpl;

	@Autowired
	private IClassTypeService classTypeServiceImpl;
	@Autowired
	private ICommodityProductRealtionService commodityProductRealtionServiceImpl;
	@Autowired
	private ICommodityService commodityServiceImpl;
	@Autowired
	private IClassTypeModuleRelationService classTypeModuleRelationServiceImpl;
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;
	@Autowired
	private ISysConfigServiceService sysconfigServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CourseVideoChapter search){
		if (search == null) {
			search = new CourseVideoChapter();
			// search.setPageSize(20);
		}
		model.addAttribute("list", courseVideoChapterServiceImpl.findCourseVideoChapterByPage(search));
		return "courseVideoChapter/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CourseVideoChapter courseVideoChapter) {
		courseVideoChapterServiceImpl.insert(courseVideoChapter);
		return "redirect:/courseVideoChapter";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CourseVideoChapter courseVideoChapter) {
		courseVideoChapterServiceImpl.update(courseVideoChapter);
		return "redirect:/courseVideoChapter";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		courseVideoChapterServiceImpl.deleteCourseVideoChapterById(id);
		return "redirect:/courseVideoChapter";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CourseVideoChapter getJson(Model model, @PathVariable Integer id){
		return courseVideoChapterServiceImpl.findCourseVideoChapterById(id);
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
	 * @Description:(保存视频课程章的基本信息)
	 * @author wang.zx 
	 * @date 2015-1-20 下午11:55:46
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="ajaxAddChapter", method=RequestMethod.POST)
	public Integer ajaxAddChapter(HttpServletRequest request, CourseVideoChapter chapter){
		
		if(chapter != null){
			courseVideoChapterServiceImpl.insert(chapter);
		}
		return chapter.getId();
	}
	
	/**
	 * @Description:加载章节信息
	 * @author chopin
	 * @date 2015-1-24 下午7:20:53
	 * @version 1.0
	 * @param request
	 * @param chapter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="load/{moduleId}", method=RequestMethod.POST)
	public List<ChapterAndLectureListVo>  load(HttpServletRequest request,@PathVariable Integer moduleId){
		return courseVideoChapterServiceImpl.findChapterAndLectureByModuleId(moduleId);
	}
	
	/**
	 * @Description:(更新章信息)
	 * @author wang.zx 
	 * @date 2015-1-24 下午7:20:53
	 * @version 1.0
	 * @param request
	 * @param chapter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="ajaxUpdateChapter", method=RequestMethod.POST)
	public String ajaxUpdateChapter(HttpServletRequest request, CourseVideoChapter chapter){
		try {
			if(chapter != null){
				courseVideoChapterServiceImpl.update(chapter);
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
	@RequestMapping(value="saveChapter", method=RequestMethod.POST)
	public Map<String,Object> saveChapter(HttpServletRequest request, CourseVideoChapter chapter){
		Map<String,Object> result = new HashMap<String,Object>();
		
		if(chapter==null){
			result.put("flag", false);
			result.put("msg", "参数错误");
			return result;
		}
//		新规则，不能存入相同章的名称
		if(StringUtils.isNotBlank(chapter.getChapterName())){
			CourseVideoChapter cvc = courseVideoChapterServiceImpl.findByChapterNameAndModuleId(chapter);
			if(cvc != null){
				if(chapter.getId() == null || (chapter.getId()!=null && (int)chapter.getId()!=(int)cvc.getId())){
					result.put("flag", false);
					result.put("msg", "已存在相同章的名称");
					return result;
				}
			}
		}
		
		if(chapter.getId()!=null){
			courseVideoChapterServiceImpl.update(chapter);
		}else{
			courseVideoChapterServiceImpl.insert(chapter);
		}
		result.put("flag", true);
		result.put("chapter", chapter);
		return result;
	}
	
	/**
	 * @Description:(保存视频课程章的基本信息)
	 * @author chopin
	 * @date 2015-1-20 下午11:55:46
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="addChapter", method=RequestMethod.POST)
	public CourseVideoChapter addChapter(HttpServletRequest request,Integer classTypeId,Integer itemOneId,Integer itemSecondId,Integer moduleId,String classTypeName,Integer chapterOrder){
		if(moduleId==null){
			ClassModule cmodule=classModuleSerivceImpl.queryModuleByClasstypeId(classTypeId);
			if(null!=cmodule){
				moduleId=cmodule.getId();
			}else{
				ClassModule module=new ClassModule();
				module.setName(classTypeName);
				module.setTeachMethod("TEACH_METHOD_VIDEO");
				module.setCreateTime(new Date());
				module.setCreator(WebUtils.getCurrentUserId(request));
				module.setItemOneId(itemOneId);
				module.setItemSecondId(itemSecondId);
				module.setSchoolId(WebUtils.getCurrentSchoolId());
				module.setCompanyId(WebUtils.getCurrentCompanyId());
				module.setDelFlag(0);
				classModuleSerivceImpl.insert(module);
				moduleId=module.getId();
				//添加班型关联的模块信息
				ClassTypeModuleRelation ctmr=new ClassTypeModuleRelation();
		    	ctmr.setModuleId(moduleId);
		    	ctmr.setClassTypeId(classTypeId);
		    	classTypeModuleRelationServiceImpl.insert(ctmr);
			}
			//更新班型表videoflag
			ClassType cy=classTypeServiceImpl.findClassTypeById(classTypeId);
			cy.setVideoFlag(1);
			classTypeServiceImpl.update(cy);
			
			//根据班型查出对应商品
			CommodityProductRealtion comm=commodityProductRealtionServiceImpl.findByClassTypeId(cy.getId()+"");
			Commodity commdity=commodityServiceImpl.findCommodityById(comm.getComId());
	    	
			//更新商品表videoflag
			commdity.setVideoFlag(1);
			commodityServiceImpl.update(commdity);
			
		}
		CourseVideoChapter chapter=new CourseVideoChapter();
		chapter.setModuleId(moduleId);
		chapter.setChapterOrder(chapterOrder);
		courseVideoChapterServiceImpl.insert(chapter);
		return chapter;
	}
	
	/**
	 * @Description:(删除章)
	 * @author chopin
	 * @date 2015-1-20 下午11:55:46
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delChapter/{id}", method = RequestMethod.POST)
	public String delChapter(Model model, @PathVariable Integer id) {
		courseVideoChapterServiceImpl.deleteCourseVideoChapterById(id);
		return "success";
	}
	
	/**
	 * @Description:(章排序)
	 * @author chopin
	 * @date 2015-1-20 下午11:55:46
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sortChapter", method = RequestMethod.POST)
	public List<CourseVideoChapter> sortChapter(HttpServletRequest request){
		List<CourseVideoChapter> chapters=JSONArray.parseArray(request.getParameter("list"), CourseVideoChapter.class);
		List<CourseVideoChapter> nchapters=new ArrayList<CourseVideoChapter>();
		for(CourseVideoChapter chapter :  chapters){
			courseVideoChapterServiceImpl.update(chapter);
			nchapters.add(courseVideoChapterServiceImpl.findCourseVideoChapterById(chapter.getId()));
		}
		return nchapters;
		
	}
	
	/**
	 * @Description:(获取自定义章节名称)
	 * @author chopin
	 * @date 2015-1-20 下午11:55:46
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/customChapter", method = RequestMethod.POST)
	public Map<String,Object> queryCustomChapterName(){
		CompanyFunctionSet search=new CompanyFunctionSet();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setFunctionCode("COMPANY_COURSE_SET");
		search.setStatus("1");
		Map<String,Object> map=new HashMap<String,Object>();
		List<CompanyFunctionSet> sets=companyFunctionSetServiceImpl.findCompanyFunctions(search);
		if(sets!=null && sets.size()>0){
			map.put("chapterName", sets.get(0).getColumn1());
			map.put("lectureName", sets.get(0).getColumn2());
		}
		//查询机构是否开启题库服务
		SysConfigService scs = new SysConfigService();
		scs.setCompanyId(WebUtils.getCurrentCompanyId());
		scs.setGroupCode("SERVICE_TIKU");
		scs = sysconfigServiceImpl.findExist(scs);
		map.put("tikuService", scs != null && scs.getDelFlag() != null && (int)scs.getDelFlag() == 1);
		
		return map;
	}
	
}
