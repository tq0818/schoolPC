package com.yuxin.wx.controller.certificate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import com.yuxin.wx.api.certificate.ICertificateConfigService;
import com.yuxin.wx.api.certificate.ICertificateCourseRelationService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.user.IUserHistoryService;
import com.yuxin.wx.certificate.impl.CertificateConfigServiceImpl;
import com.yuxin.wx.classes.impl.ClassTypeServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.certificate.CertificateConfig;
import com.yuxin.wx.model.certificate.CertificateCourseRelation;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.user.UserHistory;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.certificate.CertificateUserCourseVo;
import com.yuxin.wx.vo.certificate.ReleaseSearchVo;
import com.yuxin.wx.vo.certificate.ViewCertificateVo;
import com.yuxin.wx.vo.student.StudentClassLeanDetailVo;
import com.yuxin.wx.vo.student.StudentListVo;

/**
 * Controller of CertificateCourseRelation
 * @author chopin
 * @date 2016-9-22
 */
@Controller
@RequestMapping("/certificateCourseRelation")
public class CertificateCourseRelationController {
	
	@Autowired
	private ICertificateCourseRelationService certificateCourseRelationServiceImpl;
	
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	
	@Autowired
	private ICertificateConfigService certificateServiceImpl;
	
	@Autowired
	private IStudentService studentServiceImpl;
	
	@Autowired
	private IUserHistoryService userHistoryServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CertificateCourseRelation search){
		if (search == null) {
			search = new CertificateCourseRelation();
			// search.setPageSize(20);
		}
		model.addAttribute("list", certificateCourseRelationServiceImpl.findCertificateCourseRelationByPage(search));
		return "certificateCourseRelation/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CertificateCourseRelation CertificateCourseRelation) {
		certificateCourseRelationServiceImpl.insert(CertificateCourseRelation);
		return "redirect:/certificateCourseRelation";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CertificateCourseRelation CertificateCourseRelation) {
		certificateCourseRelationServiceImpl.update(CertificateCourseRelation);
		return "redirect:/certificateCourseRelation";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		certificateCourseRelationServiceImpl.deleteCertificateCourseRelationById(id);
		return "redirect:/certificateCourseRelation";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CertificateCourseRelation getJson(Model model, @PathVariable Integer id){
		return certificateCourseRelationServiceImpl.findCertificateCourseRelationById(id);
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
	@RequestMapping(value="/saveOrUpdateCertificateCourseRelation",method=RequestMethod.POST)
	public String saveOrUpdateCertificateCourseRelation(CertificateCourseRelation item,Model model,HttpServletRequest req){
		
		if(item !=null && item.getCourseId() !=null){
			List<CertificateCourseRelation> list = certificateCourseRelationServiceImpl.findCertificateCourseRelationByPage(item);
			if(list!=null && list.size() >0){
				certificateCourseRelationServiceImpl.update(item);
			}
			else{
				certificateCourseRelationServiceImpl.insert(item);
			}
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/delRelation",method=RequestMethod.POST)
	public String delRelation(HttpServletRequest req){
		String ridStr = req.getParameter("rid");
		if(ridStr != null && !"".equals(ridStr)){
			int rid = Integer.parseInt(ridStr);
			CertificateCourseRelation search = new CertificateCourseRelation();
			search.setCourseId(rid);
		}
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/queryClassTypesBycourseId",method=RequestMethod.POST)
	public List<ClassType> queryClassTypesBycourseId(HttpServletRequest req){
		String cerIdStr = req.getParameter("cerId");
		List<ClassType> classTypeList = new ArrayList<ClassType>();
		if(cerIdStr != null && !"".equals(cerIdStr)){
			int cerId = Integer.parseInt(cerIdStr);
			List<CertificateCourseRelation> list = certificateCourseRelationServiceImpl.queryRelationByCerId(cerId);
			if(list!= null && list.size()>0){
				for (CertificateCourseRelation ccr : list) {
					Integer courseId = ccr.getCourseId();
					if(courseId!=null){
						ClassType classType = classTypeServiceImpl.findClassTypeById(courseId);
						if(classType!=null){
							Integer itemOneId = classType.getItemOneId();
							Integer itemSecondId = classType.getItemSecondId();
							SysConfigItem itemOne = sysConfigItemServiceImpl.findSysConfigItemById(itemOneId);
							SysConfigItem itemSecond = sysConfigItemServiceImpl.findSysConfigItemById(itemSecondId);
							if(itemOne!= null)
								classType.setItemOneName(itemOne.getItemName());
							if(itemSecond != null){
								classType.setItemSecondName(itemSecond.getItemName());
							}
							classType.setRelationId(ccr.getId());
							classTypeList.add(classType);
						}
					}
				}
			}
		}
		return classTypeList;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteByCerIdAndCourseId",method=RequestMethod.POST)
	public String deleteByCerIdAndCourseId(CertificateCourseRelation item,HttpServletRequest request){
		if(item!=null && item.getCerId() != null && item.getCourseId()!=null && item.getId() != null){
			List<CertificateCourseRelation> list = certificateCourseRelationServiceImpl.findCertificateCourseRelationByPage(item);
			if(list != null && list.size()>0){
				CertificateCourseRelation courseRelation = list.get(0);
				certificateCourseRelationServiceImpl.deleteCertificateCourseRelationById(courseRelation.getId());
			}
		}
		return "success";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/toReleasePage",method=RequestMethod.POST)
	public ModelAndView toReleasePage(HttpServletRequest req,Model model){
		
		ModelAndView mv = new ModelAndView();
		int cerId = Integer.parseInt(req.getParameter("cerId"));
		List<CertificateCourseRelation> list = certificateCourseRelationServiceImpl.queryRelationByCerId(cerId);
		if(list != null && list.size()>0){
			for (CertificateCourseRelation ccr : list) {
				Integer courseId = ccr.getCourseId();
				ClassType ct = classTypeServiceImpl.findClassTypeById(courseId);
				if(ct!=null)
					ccr.setCourseName(ct.getName());
			}
		}
		mv.addObject("courseList", list);
		CertificateConfig certificateConfig = certificateServiceImpl.findCertificateConfigById(cerId);
		mv.addObject("certificateName", certificateConfig.getName());
		mv.addObject("cerId", certificateConfig.getId());
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/releaseList",method=RequestMethod.POST)
	public PageFinder<CertificateUserCourseVo> releaseList(HttpServletRequest request,Model model ,ReleaseSearchVo search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<CertificateUserCourseVo> list = certificateCourseRelationServiceImpl.pageReleaseCertificateList(search);
		if(list != null && list.size() >0){
			for (CertificateUserCourseVo cuc : list) {
				Integer courseId = cuc.getCourseId();
				ClassType classType = classTypeServiceImpl.findClassTypeById(courseId);
				if(classType!=null){
					cuc.setCourseName(classType.getName());
				}
				Integer userNum = cuc.getUserNum();
				Integer passLectureNum = cuc.getPassLectureNum();
				Integer totalLectureNum = cuc.getTotalLectureNum();
				if(userNum ==0 && passLectureNum == totalLectureNum){
					cuc.setIsRelease(1);
				}
			}
		}
		Integer count = certificateCourseRelationServiceImpl.pageReleaseCertificateListCount(search);
		PageFinder<CertificateUserCourseVo> pageFinder = new PageFinder<CertificateUserCourseVo>(search.getPage(), search.getPageSize(), count, list);
		return pageFinder;
	}
	
	
}
