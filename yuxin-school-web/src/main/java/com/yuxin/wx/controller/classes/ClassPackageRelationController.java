package com.yuxin.wx.controller.classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleNoOnsaleService;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassPackageRelationService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentPaySlaveService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassModuleNoOnsale;
import com.yuxin.wx.model.classes.ClassPackageRelation;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.course.CourseVideoLecture;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassPackageConditionVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.classes.ClassmoduleNoOnsaleVo;
import com.yuxin.wx.vo.system.SysPageHeadFootVo;

/**
 * Controller of ClassPackageRelation
 * @author chopin
 * @date 2016-3-21
 */
@Controller
@RequestMapping("/classPackageRelation")
public class ClassPackageRelationController {
	private Log log=LogFactory.getLog("log");
	
	@Autowired
	private IClassPackageRelationService classPackageRelationServiceImpl;
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	@Autowired
	private IStudentPayMasterService studentPayMasterServiceImpl;
	@Autowired
	private IClassModuleService classModuleServiceImpl;
	@Autowired
	private IClassModuleNoService classModuleNoServiceImpl;
	@Autowired
	private IClassModuleLessonService classModuleLessonServiceImpl;
	@Autowired
	private IStudentPaySlaveService studentPaySlaveServiceImpl;
	@Autowired
	private IClassModuleNoOnsaleService classModuleNoOnsaleServiceImpl;
	@Autowired
	private ICommodityService commodityServiceImpl;
	@Autowired
	private ICommodityProductRealtionService commodityProductRealtionServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ClassPackageRelation search){
		if (search == null) {
			search = new ClassPackageRelation();
			// search.setPageSize(20);
		}
		model.addAttribute("list", classPackageRelationServiceImpl.findClassPackageRelationByPage(search));
		return "classPackageRelation/list";
	}
	
	/**
	 * 
	 * Class Name: ClassPackageRelationController.java
	 * @Description: 添加课程
	 * @author zhang.zx
	 * @date 2016年3月23日 下午4:47:01
	 * @modifier
	 * @modify-date 2016年3月23日 下午4:47:01
	 * @version 1.0
	 * @param ClassPackageRelation
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addCourse", method = RequestMethod.POST)
	public List<ClassPackageRelation> add(Integer classPackageId, HttpServletRequest request) {
		List<Integer> classList=new ArrayList<Integer>();
		Integer companyId=WebUtils.getCurrentCompanyId();
		Integer schoolId=WebUtils.getCurrentSchoolId();
		ClassPackageConditionVo search=new ClassPackageConditionVo();
		search.setCompanyId(companyId);
		search.setSchoolId(schoolId);
		//查询课程包下是否存在课程，没有才添加
		List<ClassPackageRelation> data=JSONArray.parseArray(request.getParameter("list"),ClassPackageRelation.class);
		for(ClassPackageRelation relation:data){
			if(null!=relation){
				search.setClassTypeId(relation.getClassTypeId());
				search.setId(relation.getClassPackageId());
				List<ClassTypeVo> arr=classTypeServiceImpl.queryIsExistByClassPackage(search);
				if(arr.size()<=0){
					classPackageRelationServiceImpl.insert(relation);
					classList.add(relation.getClassTypeId());
				}
			}
		}
		//给购买此课程包报名学员补子订单
		try {
			addStudentPaySlave(classList,classPackageId);
		} catch (Exception e) {
			log.error("课程包给学员补子订单中出现异常",e);
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 
	 * Class Name: ClassPackageRelationController.java
	 * @Description: 修改课程顺序
	 * @author zhang.zx
	 * @date 2016年3月23日 下午4:49:03
	 * @modifier
	 * @modify-date 2016年3月23日 下午4:49:03
	 * @version 1.0
	 * @param ClassPackageRelation
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateCourse", method = RequestMethod.POST)
	public String update(ClassPackageRelation classPackageRelation) {
		classPackageRelationServiceImpl.update(classPackageRelation);
		return "success";
	}
	
	/**
	 * 
	 * Class Name: ClassPackageRelationController.java
	 * @Description: 移除课程
	 * @author zhang.zx
	 * @date 2016年3月23日 下午4:50:33
	 * @modifier
	 * @modify-date 2016年3月23日 下午4:50:33
	 * @version 1.0
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/removeCourse", method = RequestMethod.POST)
	public String del(Integer id, Model model,HttpServletRequest request) {
		ClassPackageRelation relation=classPackageRelationServiceImpl.findClassPackageRelationById(id);
		if(null!=relation){
			classPackageRelationServiceImpl.deleteClassPackageRelationById(relation.getId());
			//给购买此课程包报名学员移除子订单
			try {
				deleteStudentPaySlave(relation.getClassTypeId(), relation.getClassPackageId());
			} catch (Exception e) {
				log.error("删除子订单异常",e);
				e.printStackTrace();
			}
		}
		return "success";
	}
	
	/**
	 * 
	 * Class Name: ClassPackageRelationController.java
	 * @Description: 课程排序
	 * @author zhang.zx
	 * @date 2016年3月23日 下午5:10:52
	 * @modifier
	 * @modify-date 2016年3月23日 下午5:10:52
	 * @version 1.0
	 * @param targetId
	 * @param classTypeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sortCourse")
	public String sortCourse(HttpServletRequest request){
		List<ClassPackageRelation> courseList=JSONArray.parseArray(request.getParameter("list"), ClassPackageRelation.class);
		for(ClassPackageRelation relation:courseList){
			classPackageRelationServiceImpl.update(relation);
		}
		return "success";
	}
	
	/**
	 * 
	 * Class Name: ClassPackageRelationController.java
	 * @Description: 改变课程所属的阶段
	 * @author zhang.zx
	 * @date 2016年3月25日 上午10:08:51
	 * @modifier
	 * @modify-date 2016年3月25日 上午10:08:51
	 * @version 1.0
	 * @param stageId
	 * @param classTypeId
	 * @param classPackageId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mvcourse_toStage")
	public String mvCourseStage(ClassPackageRelation relation){
		ClassPackageRelation r=classPackageRelationServiceImpl.findClassPackageRelationById(relation.getId());
		if(null!=r){
			classPackageRelationServiceImpl.update(relation);
		}
//		ClassPackageRelation search=new ClassPackageRelation();
//		search.setClassPackageId(classPackageId);
//		search.setClassPackageStageId(stageId);
//		search.setClassTypeId(classTypeId);
//		List<ClassPackageRelation> relation=classPackageRelationServiceImpl.queryClassPackageStageRelation(search);
//		if(null!=relation && relation.size()>0){
//			ClassPackageRelation r=new ClassPackageRelation();
//			r.setId(relation.get(0).getId());
//			r.setClassPackageStageId(stageId);
//			classPackageRelationServiceImpl.update(r);
//			return relation.get(0);
//		}	
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public ClassPackageRelation getJson(Model model, @PathVariable Integer id){
		return classPackageRelationServiceImpl.findClassPackageRelationById(id);
	}
	
	/**
	 * 
	 * Class Name: ClassPackageRelationController.java
	 * @Description: 补子订单
	 * @author zhang.zx
	 * @date 2016年3月23日 下午6:23:21
	 * @modifier
	 * @modify-date 2016年3月23日 下午6:23:21
	 * @version 1.0
	 * @param arr
	 */
	public void addStudentPaySlave(List<Integer> arr,Integer classPackageId){
		Integer companyId=WebUtils.getCurrentCompanyId();
		//查询报名此课程包学员
		StudentPayMaster search=new StudentPayMaster();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		search.setCommodityId(classPackageId);
		search.setCommodityType("COMMODITY_CLASS");
		List<StudentPayMaster> data=studentPayMasterServiceImpl.queryStusByClassPackage(search);
		//查询所有课程的在售班号
		for(StudentPayMaster pay:data){
			for(int i=0;i<arr.size();i++){
				//根据班型查询模块
		    	List<ClassModule> listModule=classModuleServiceImpl.findModulesByClassTypeId(arr.get(i));
		    	if(null!=listModule && listModule.size()>0){
		    		for(ClassModule moudules:listModule){
		    			if(null!=moudules){
		    				StudentPaySlave paySalve=new StudentPaySlave();
		    				paySalve.setCompanyId(companyId);
		    				paySalve.setSlaveStatusCode("SUB_ORDER_CREATED");
		    				paySalve.setModuleId(moudules.getId());
		    				paySalve.setPayMasterId(pay.getId());
		    				paySalve.setStuId(pay.getStuId());
		    				if("TEACH_METHOD_VIDEO".equals(moudules.getTeachMethod())){
			    				paySalve.setResourceId(moudules.getId()+"");
			    				paySalve.setResourceType(moudules.getTeachMethod());
			    				studentPaySlaveServiceImpl.insert(paySalve);
		    				}else{
		    					ClassModuleNoOnsale moduleOnsale=new ClassModuleNoOnsale();
			    				moduleOnsale.setModuleId(moudules.getId());
			    				moduleOnsale.setClasstypeId(arr.get(i));
			    				ClassModuleNoOnsale onsale=classModuleNoOnsaleServiceImpl.queryClassNoOnSale(moduleOnsale);
			    				if(null!=onsale){
			    					paySalve.setResourceId(onsale.getModuleNoId()+"");
			    				}
			    				paySalve.setResourceType(moudules.getTeachMethod());
			    				studentPaySlaveServiceImpl.insert(paySalve);
		    				}
		    			}
			    		
			    	}
		    	}
			}
		}
	}
	
	/**
	 * 
	 * Class Name: ClassPackageRelationController.java
	 * @Description: 删除子订单
	 * @author zhang.zx
	 * @date 2016年3月25日 上午10:46:45
	 * @modifier
	 * @modify-date 2016年3月25日 上午10:46:45
	 * @version 1.0
	 * @param arr
	 * @param classPackageId
	 */
	public void deleteStudentPaySlave(Integer classTypeId,Integer classPackageId){
		Integer companyId=WebUtils.getCurrentCompanyId();
		//查询报名此课程包学员
		StudentPayMaster search=new StudentPayMaster();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		search.setCommodityId(classPackageId);
		search.setCommodityType("COMMODITY_CLASS");
		List<StudentPayMaster> data=studentPayMasterServiceImpl.queryStusByClassPackage(search);
		//查询所有课程的在售班号
		for(StudentPayMaster pay:data){
				//根据班型查询模块
		    	List<ClassModule> listModule=classModuleServiceImpl.findModulesByClassTypeId(classTypeId);
		    	if(null!=listModule && listModule.size()>0){
		    		for(ClassModule moudules:listModule){
		    			if(null!=moudules){
		    				StudentPaySlave paySalve=new StudentPaySlave();
		    				paySalve.setCompanyId(companyId);
		    				paySalve.setSlaveStatusCode("SUB_ORDER_CREATED");
		    				paySalve.setModuleId(moudules.getId());
		    				paySalve.setPayMasterId(pay.getId());
		    				paySalve.setStuId(pay.getStuId());
		    				if("TEACH_METHOD_VIDEO".equals(moudules.getTeachMethod())){
			    				paySalve.setResourceId(moudules.getId()+"");
			    				paySalve.setResourceType(moudules.getTeachMethod());
			    				List<StudentPaySlave> orders=studentPaySlaveServiceImpl.queryStuSlaveBywhere(paySalve);
			    				if(null!=orders){
			    					studentPaySlaveServiceImpl.updateStuPaySlave(orders.get(0).getId());
			    				}
		    				}else{
		    					ClassModuleNoOnsale moduleOnsale=new ClassModuleNoOnsale();
			    				moduleOnsale.setModuleId(moudules.getId());
			    				moduleOnsale.setClasstypeId(classTypeId);
			    				ClassModuleNoOnsale onsale=classModuleNoOnsaleServiceImpl.queryClassNoOnSale(moduleOnsale);
			    				if(null!=onsale){
			    					paySalve.setResourceId(onsale.getModuleNoId()+"");
			    				}
			    				paySalve.setResourceType(moudules.getTeachMethod());
			    				List<StudentPaySlave> orders=studentPaySlaveServiceImpl.queryStuSlaveBywhere(paySalve);
			    				if(null!=orders){
			    					studentPaySlaveServiceImpl.updateStuPaySlave(orders.get(0).getId());
			    				}
		    				}
		    			}
			    		
			    	}
		    	}
		}
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
}
