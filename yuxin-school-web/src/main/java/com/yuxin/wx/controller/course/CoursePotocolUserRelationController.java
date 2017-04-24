package com.yuxin.wx.controller.course;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.yuxin.wx.api.course.ICoursePotocolUserRelationService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.course.CoursePotocolUserRelation;
import com.yuxin.wx.model.course.CourseProtocolConfig;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.course.ProtocolCourseOrPackageRelation;

/**
 * Controller of CoursePotocolUserRelation
 * @author chopin
 * @date 2016-11-1
 */
@Controller
@RequestMapping("/coursePotocolUserRelation")
public class CoursePotocolUserRelationController {
	
	@Autowired
	private ICoursePotocolUserRelationService coursePotocolUserRelationServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CoursePotocolUserRelation search){
		if (search == null) {
			search = new CoursePotocolUserRelation();
			// search.setPageSize(20);
		}
		model.addAttribute("list", coursePotocolUserRelationServiceImpl.findCoursePotocolUserRelationByPage(search));
		return "coursePotocolUserRelation/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CoursePotocolUserRelation CoursePotocolUserRelation) {
		coursePotocolUserRelationServiceImpl.insert(CoursePotocolUserRelation);
		return "redirect:/coursePotocolUserRelation";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CoursePotocolUserRelation CoursePotocolUserRelation) {
		coursePotocolUserRelationServiceImpl.update(CoursePotocolUserRelation);
		return "redirect:/coursePotocolUserRelation";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		coursePotocolUserRelationServiceImpl.deleteCoursePotocolUserRelationById(id);
		return "redirect:/coursePotocolUserRelation";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CoursePotocolUserRelation getJson(Model model, @PathVariable Integer id){
		return coursePotocolUserRelationServiceImpl.findCoursePotocolUserRelationById(id);
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
	@RequestMapping(value="queryCourseOrPackageRelation",method=RequestMethod.POST)
	public PageFinder<ProtocolCourseOrPackageRelation> queryCourseOrPackageRelation(CourseProtocolConfig search,HttpServletRequest request){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<ProtocolCourseOrPackageRelation> list = coursePotocolUserRelationServiceImpl.queryProtocolRelationCourseOrCoursePackage(search);
		if(list != null && list.size()>0){
			for (ProtocolCourseOrPackageRelation pcp : list) {
				if(!StringUtils.isEmpty(pcp.getCreateTime())&&pcp.getCreateTime().length()>=11){
					pcp.setCreateTime(pcp.getCreateTime().substring(0, 11));
				}
			}
		}
		int count = coursePotocolUserRelationServiceImpl.queryProtocolRelationCourseOrCoursePackageCount(search);
		return new PageFinder<ProtocolCourseOrPackageRelation>(search.getPage(), search.getPageSize(), count, list);
	}
	
	@ResponseBody
	@RequestMapping(value="queryHistoryCourseOrPackageRelation",method=RequestMethod.POST)
	public PageFinder<ProtocolCourseOrPackageRelation> queryHistoryCourseOrPackageRelation(CourseProtocolConfig search,HttpServletRequest request){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<ProtocolCourseOrPackageRelation> data = coursePotocolUserRelationServiceImpl.queryHistoryBindCourseOrPackage(search);
		if(data != null && data.size()>0){
			for (ProtocolCourseOrPackageRelation pcp : data) {
				if(!StringUtils.isEmpty(pcp.getCreateTime())&&pcp.getCreateTime().length()>=11){
					pcp.setCreateTime(pcp.getCreateTime().substring(0, 11));
				}
			}
		}
		int rowCount = coursePotocolUserRelationServiceImpl.queryHistoryBindCourseOrPackageCount(search);
		return new PageFinder<ProtocolCourseOrPackageRelation>(search.getPage(), search.getPageSize(), rowCount, data);
	}
	
	
	@ResponseBody
	@RequestMapping(value="checkPrototolIsRelease",method=RequestMethod.POST)
	public String checkPrototolIsRelease(CoursePotocolUserRelation search){
		List<CoursePotocolUserRelation> list = coursePotocolUserRelationServiceImpl.findCoursePotocolUserRelationByPage(search);
		return list.size()>0?"success":"fail";
	}
}
