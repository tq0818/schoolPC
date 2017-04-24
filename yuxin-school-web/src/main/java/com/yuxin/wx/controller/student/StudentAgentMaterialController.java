package com.yuxin.wx.controller.student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.student.IStudentAgentMaterialService;
import com.yuxin.wx.api.student.IStudentFeeStageService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentPaySlaveService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.student.StuPaymasterVoList;
import com.yuxin.wx.vo.student.StudentPaySlaveVo;

/**
 * Controller of StudentAgentMaterial
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/studentAgentMaterial")
public class StudentAgentMaterialController {
	
	@Autowired
	private IStudentAgentMaterialService studentAgentMaterialServiceImpl;
	
	@Autowired
	private IStudentService studentServiceImpl;
	
	@Autowired
	private IStudentPayMasterService studentPayMasterServiceImpl; 
	
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	
	@Autowired
	private IClassModuleNoService classModuleNoServiceImpl;
	
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl; 
	
	@Autowired
	private ISysConfigSchoolService sysConfigSchoolServiceImpl;
	
	@Autowired
	private IClassModuleService classModuleServiceImpl;
	
	@Autowired
	private IStudentPaySlaveService studentPaySlaveServiceImpl;
	
	@Autowired
	private IStudentFeeStageService studentFeeStageServiceImpl;
	
	/**
	 * 
	 * Class Name: StudentAgentMaterialController.java
	 * @Description: 查询学员
	 * @author zhang.zx
	 * @date 2015年6月1日 下午8:12:21
	 * @modifier
	 * @modify-date 2015年6月1日 下午8:12:21
	 * @version 1.0
	 * @param mobile
	 * @param model
	 * @return
	 */
	@ResponseBody
 	@RequestMapping(value="/search",method=RequestMethod.POST)
 	public List<StuPaymasterVoList> search(String mobile,Model model){
 		Integer companyId = WebUtils.getCurrentCompanyId();
 		Integer schoolId=WebUtils.getCurrentSchoolId();
 		Student student=new Student();
 		student.setMobile(mobile);
 		student.setCompanyId(companyId);
		//student = studentServiceImpl.findByMobile(student);
 		student=studentServiceImpl.queryStuInfoByWhere(student);
		if(student!=null){
			List<StudentPayMaster> list = studentPayMasterServiceImpl.findByStuMbilestu(student.getId(),companyId,schoolId);
			if(list.size()>0){
				StuPaymasterVoList sc=new StuPaymasterVoList();
				sc.setCompanyId(companyId);
				sc.setSchoolId(schoolId);
				sc.setStuId(student.getId());
			    List<StuPaymasterVoList> arr=studentPayMasterServiceImpl.queryClassTypeByStuId(sc);
			    for(StuPaymasterVoList sp:arr){
				  sp.setStuName(student.getName());
				  sp.setMobile(student.getMobile());
			    }
			    return arr;
			}else{
			    return null;
			}
		}else{
			return null;
		}	
 	}
	
	/**
	 * 
	 * Class Name: StudentAgentMaterialController.java
	 * @Description: 跳转到学员报考材料页面
	 * @author zhang.zx
	 * @date 2015年4月25日 上午10:05:56
	 * @modifier
	 * @modify-date 2015年4月25日 上午10:05:56
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="/stuMaterial",method=RequestMethod.GET)
	public String forwardStuMaterial(){
		return "student/studentMaterial";
	}
	
	
	/**
	 * 
	 * Class Name: StudentAgentMaterialController.java
	 * @Description: 跳转到学员报考材料页面
	 * @author zhang.zx
	 * @date 2015年4月25日 下午2:44:35
	 * @modifier
	 * @modify-date 2015年4月25日 下午2:44:35
	 * @version 1.0
	 * @param model
	 * @param mobile
	 * @param studentPayMasterId
	 * @return
	 */
	@RequestMapping(value="/showStuMaterial",method=RequestMethod.POST)
	public String toSearchBaseMessage(Model model,String mobile,Integer studentPayMasterId){
	 if(studentPayMasterId!=null){
		StudentPayMaster payMaster= studentPayMasterServiceImpl.findStudentPayMasterById(studentPayMasterId);
		model.addAttribute("payMaster", payMaster);
		//Student student = studentServiceImpl.findMessageByMobile(mobile,WebUtils.getCurrentCompanyId(),WebUtils.getCurrentSchoolId());
		Student student=studentServiceImpl.findStudentById(payMaster.getStuId());
		model.addAttribute("student", student);
		model.addAttribute("mobile", mobile);
			List<StudentAgentMaterial> stuMaterialList= studentAgentMaterialServiceImpl.findStudentAgentMaterialByStuId(student.getId(), studentPayMasterId);
			model.addAttribute("stuMaterialList",stuMaterialList);
	 }
		return "student/studentMaterialDetailList";
	}
	@RequestMapping(value="/material",method=RequestMethod.POST)
	public String toSearchBaseMessage2(Model model,String mobile,Integer id){
		StudentPayMaster payMaster= studentPayMasterServiceImpl.findStudentPayMasterById(id);
		model.addAttribute("payMaster", payMaster);
		Student student = studentServiceImpl.findMessageByMobile(mobile,WebUtils.getCurrentCompanyId(),WebUtils.getCurrentSchoolId());
		model.addAttribute("student", student);
		model.addAttribute("mobile", mobile);
		List<StudentAgentMaterial> stuMaterialList= studentAgentMaterialServiceImpl.findStudentAgentMaterialByStuId(student.getId(), id);
		model.addAttribute("stuMaterialList",stuMaterialList);
		return "/student/transaction/material";
	}
	
	/**
	 * 
	 * Class Name: StudentAgentMaterialController.java
	 * @Description: 根据学员查询报考材料
	 * @author zhang.zx
	 * @date 2015年4月25日 下午4:58:02
	 * @modifier
	 * @modify-date 2015年4月25日 下午4:58:02
	 * @version 1.0
	 * @param stuId
	 * @param payMasterId 报名主订单
	 * @return
	 */
	@ResponseBody 
	@RequestMapping(value="/queryMaterialsByStu",method=RequestMethod.POST)
	public List<StudentAgentMaterial> queryAgentMaterial(Integer stuId,Integer payMasterId){
		return studentAgentMaterialServiceImpl.findStudentAgentMaterialByStuId(stuId, payMasterId);
	}
	
	/**
	 * 
	 * Class Name: StudentAgentMaterialController.java
	 * @Description: 异步加载基本信息
	 * @author zhang.zx
	 * @date 2015年4月25日 下午2:44:45
	 * @modifier
	 * @modify-date 2015年4月25日 下午2:44:45
	 * @version 1.0
	 * @param model
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="/baseMessage",method=RequestMethod.POST)
	public String baseMessage(Model model,String mobile){
		Student student = studentServiceImpl.findMessageByMobile(mobile,WebUtils.getCurrentCompanyId(),WebUtils.getCurrentSchoolId());
		model.addAttribute("student", student);
		return "student/BaseMessageList";
	}
	
	/**
	 * 
	 * Class Name: StudentAgentMaterialController.java
	 * @Description: 异步加载代报考
	 * @author zhang.zx
	 * @date 2015年4月25日 下午12:35:44
	 * @modifier
	 * @modify-date 2015年4月25日 下午12:35:44
	 * @version 1.0
	 * @param model
	 * @param stuId
	 * @param paymaterId
	 * @return
	 */
	@RequestMapping(value="/stuMaterialLists",method=RequestMethod.POST)
	public String stuMaterialList(Model model,String mobile,Integer paymaterId){
		Student student = studentServiceImpl.findMessageByMobile(mobile,WebUtils.getCurrentCompanyId(),WebUtils.getCurrentSchoolId());
		StudentPayMaster payMaster= studentPayMasterServiceImpl.findStudentPayMasterById(paymaterId);
		model.addAttribute("payMaster", payMaster);
		List<StudentAgentMaterial> stuMaterialList= studentAgentMaterialServiceImpl.findStudentAgentMaterialByStuId(student.getId(), paymaterId);
		model.addAttribute("stuMaterialList",stuMaterialList);
		return "student/materialAjaxList";
	}
	
	/**
	 * 
	 * Class Name: StudentAgentMaterialController.java
	 * @Description:异步加载版型信息
	 * @author zhang.zx
	 * @date 2015年4月25日 下午2:46:13
	 * @modifier
	 * @modify-date 2015年4月25日 下午2:46:13
	 * @version 1.0
	 * @param model
	 * @param mobile
	 * @param stuId
	 * @param payMaster
	 * @return
	 */
	@RequestMapping(value="/classTypeMessage",method=RequestMethod.POST)
	public String classTypeMessage(Model model,String mobile,Integer stuId,Integer classTypeId,StudentPayMaster payMaster){
		Integer companyId=WebUtils.getCurrentCompanyId();
		//Student student = studentServiceImpl.findMessageByMobile(mobile);
		if(payMaster==null||payMaster.getId()==null){
			Student s=new Student();
			s.setMobile(mobile);
			s.setCompanyId(companyId);
			Student st=studentServiceImpl.queryStuInfoByWhere(s);
			List<StudentPayMaster> list = studentPayMasterServiceImpl.findByStuMbilestu(st.getId(),companyId,WebUtils.getCurrentSchoolId());
			if(list!=null){
				payMaster=list.get(0);
				stuId=payMaster.getStuId();
			}
		}
		           
		//查询所报所有班型
		ClassType classType=classTypeServiceImpl.findClassTypeById(classTypeId);
 		//ClassType classType = classTypeServiceImpl.findClassTypeByStuId(stuId,companyId,payMaster.getId());
 		//查询班型所对应的一级项目和二级项目
 		//查询班型所对应的一级项目和二级项目
 		Integer oneId = classType.getItemOneId();
 		Integer secondId = classType.getItemSecondId();
 		SysConfigItem oneItem = sysConfigItemServiceImpl.findSysConfigItemById(oneId);
 		SysConfigItem secondItem = sysConfigItemServiceImpl.findSysConfigItemById(secondId);
 		model.addAttribute("classType", classType);
 		model.addAttribute("oneItem", oneItem);
 		model.addAttribute("secondItem", secondItem);
 		//查询出所属分校
 		String schoolsId = classType.getSchoolsId();
 		if(schoolsId!=null&&!"".equals(schoolsId)){
 			String[] schoolIds = schoolsId.split(",");
 	 		List<SysConfigSchool> schoolList = new ArrayList<SysConfigSchool>();
 	 		for (String schoolId : schoolIds) {
 	 			SysConfigSchool school = sysConfigSchoolServiceImpl.findSysConfigSchoolById(Integer.parseInt(schoolId));
 	 			schoolList.add(school);
 			}
 	 	    //所属分校
 	 		model.addAttribute("schoolList", schoolList);
 		}
 		//查询课程所对应模块
 		List<ClassModule> classModuleList = classModuleServiceImpl.findByClassTypeId(classType.getId());
 		Integer classHour=0;
 		for (ClassModule classModule : classModuleList) {
			if(null!=classModule && null!=classModule.getTotalClassHour()){
				classHour+=classModule.getTotalClassHour();
			}
		}
 		model.addAttribute("classHour", classHour);
 		//model.addAttribute("payMaster", list.get(0));
 		//优先上课校区
 		Integer sch_id = payMaster.getSchoolId();
 		SysConfigSchool schoo = sysConfigSchoolServiceImpl.findSysConfigSchoolById(sch_id);
 		model.addAttribute("schoo", schoo);
 		//查询所有模块信息
 		List<StudentPaySlaveVo> paySlaveList = studentPaySlaveServiceImpl.findStudentPaySlaveByPayMasterId(payMaster.getId());
 		model.addAttribute("paySlaveList", paySlaveList);
		return "student/ClassTypeList";
	}
	
	/**
	 * 
	 * Class Name: StudentAgentMaterialController.java
	 * @Description: 添加代报考
	 * @author zhang.zx
	 * @date 2015年4月25日 下午6:36:10
	 * @modifier
	 * @modify-date 2015年4月25日 下午6:36:10
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addStuMaterial")
	public String addStuMaterial(HttpServletRequest request){
		String payMethod="";
		List<StudentAgentMaterial> materials=JSONObject.parseArray(request.getParameter("material"),com.yuxin.wx.model.student.StudentAgentMaterial.class);
		List<StudentPayMaster> stuMasters=JSONObject.parseArray(request.getParameter("stuMaster"),com.yuxin.wx.model.student.StudentPayMaster.class);
		List<StudentAgentMaterial> arr=studentAgentMaterialServiceImpl.findStudentAgentMaterialByStuId(Integer.parseInt(materials.get(0).getStuId()), materials.get(0).getPayMasterId());
		if(stuMasters!=null){
			payMethod=stuMasters.get(0).getPaymentTypeCode();
		}
		//添加报考材料
		if(arr.size()>0){
			//更新学员订单表
//			for(StudentPayMaster stuMaster:stuMasters){
//				StudentPayMaster pay=studentPayMasterServiceImpl.findStudentPayMasterById(stuMaster.getId());
//				StudentPayMaster data=new StudentPayMaster();
//				data.setId(stuMaster.getId());
//		    	data.setIsAgentComplete(""+stuMaster.getIsAgentComplete());
//		    	data.setAgentRemark(stuMaster.getAgentRemark());
//		    	data.setIsAgentMaterialComplete(stuMaster.getIsAgentMaterialComplete());
//		    	data.setUpdateTime(new Date());
//		    	data.setUpdator(""+WebUtils.getCurrentUserId(request));
//		    	data.setIsAgent(stuMaster.getIsAgent());
//		    	data.setExamAgentFee(stuMaster.getExamAgentFee());
//		    	if(stuMaster.getExamAgentFee()!=null&&!"".equals(stuMaster.getExamAgentFee())){
//		    		data.setTotalAmount(stuMaster.getExamAgentFee()+pay.getTotalAmount());
//		    	}
//				studentPayMasterServiceImpl.update(data);	
//			}	
			studentAgentMaterialServiceImpl.repayMaterial(materials, stuMasters.get(0), WebUtils.getCurrentUserId(request));
		}else{
			for(StudentAgentMaterial m:materials){
				StudentAgentMaterial material=new StudentAgentMaterial();
				material.setStuId(m.getStuId());
				material.setCompanyId(WebUtils.getCurrentCompanyId());
				material.setPayMasterId(m.getPayMasterId());
				material.setMaterialTypeCode(m.getMaterialTypeCode());
				material.setMaterialName(m.getMaterialName());
				material.setMaterialNumYet(m.getMaterialNumYet());
				material.setMaterialNumNot(m.getMaterialNumNot());
				material.setCreateTime(new Date());
				material.setCreator(WebUtils.getCurrentUserId(request));
				studentAgentMaterialServiceImpl.insertMaterial(material);
			}
			//更新学员订单表
			for(StudentPayMaster stuMaster:stuMasters){
				StudentPayMaster pay=studentPayMasterServiceImpl.findStudentPayMasterById(stuMaster.getId());
				StudentPayMaster data=new StudentPayMaster();
				data.setId(stuMaster.getId());
		    	data.setIsAgentComplete(""+stuMaster.getIsAgentComplete());
		    	data.setAgentRemark(stuMaster.getAgentRemark());
		    	data.setIsAgentMaterialComplete(stuMaster.getIsAgentMaterialComplete());
		    	data.setUpdateTime(new Date());
		    	data.setUpdator(""+WebUtils.getCurrentUserId(request));
		    	data.setIsAgent(stuMaster.getIsAgent());
		    	data.setExamAgentFee(stuMaster.getExamAgentFee());
		    	if(stuMaster.getExamAgentFee()!=null&&!"".equals(stuMaster.getExamAgentFee())){
		    		data.setTotalAmount(stuMaster.getExamAgentFee()+pay.getTotalAmount());
		    	}
				studentPayMasterServiceImpl.update(data);	
			}	
		}
		//添加费用表
		if(payMethod!=null&&!"".equals(payMethod)){
			Integer pMethod=Integer.parseInt(payMethod);
			StudentFeeStage studentFeeStage=new StudentFeeStage();
			studentFeeStage.setStuId(Integer.parseInt(materials.get(0).getStuId()));
			studentFeeStage.setPayMasterId(materials.get(0).getPayMasterId());
			studentFeeStage.setStageDate(new Date());
			studentFeeStage.setStageFee(stuMasters.get(0).getExamAgentFee());
			studentFeeStage.setStageStatus(1+"");
			if(pMethod==1||pMethod.equals(1)){
				studentFeeStage.setCashReal(stuMasters.get(0).getExamAgentFee());
			}else if(pMethod==0||pMethod.equals(0)){
				studentFeeStage.setPosReal(stuMasters.get(0).getExamAgentFee());
			}else{
				studentFeeStage.setRemitReal(stuMasters.get(0).getExamAgentFee());
			}
			studentFeeStage.setRemark(stuMasters.get(0).getAgentRemark());
			studentFeeStage.setPayDate(new Date());
			studentFeeStage.setCreateType("STAGE_TYPE_SIGN");
			studentFeeStage.setCreateTime(new Date());
			studentFeeStage.setCreator(WebUtils.getCurrentUserId(request));
			studentFeeStage.setCompanyId(WebUtils.getCurrentCompanyId());
			studentFeeStage.setUpdateTime(new Date());
			studentFeeStage.setUpdator(WebUtils.getCurrentUserId(request));
			studentFeeStageServiceImpl.insert(studentFeeStage);
		}
		return "success";
	}
	
	/**
	 * 
	 * Class Name: StudentAgentMaterialController.java
	 * @Description: 完成代报考
	 * @author zhang.zx
	 * @date 2015年4月25日 上午10:31:21
	 * @modifier
	 * @modify-date 2015年4月25日 上午10:31:21
	 * @version 1.0
	 * @param request
	 * @param id
	 * @return
	 */
	 @ResponseBody
	 @RequestMapping(value="/applyMaterial", method = RequestMethod.POST)
	 public String applyExam(HttpServletRequest request, Integer paymaterId, Integer stuId){
	    	StudentPayMaster data=new StudentPayMaster();
	    	data.setId(paymaterId);
	    	data.setIsAgentComplete(""+1);
	    	studentPayMasterServiceImpl.update(data);
	        return "success";
	 }
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, StudentAgentMaterial search){
		if (search == null) {
			search = new StudentAgentMaterial();
			// search.setPageSize(20);
		}
		model.addAttribute("list", studentAgentMaterialServiceImpl.findStudentAgentMaterialByPage(search));
		return "studentAgentMaterial/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(StudentAgentMaterial studentAgentMaterial) {
		studentAgentMaterialServiceImpl.insert(studentAgentMaterial);
		return "redirect:/studentAgentMaterial";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(StudentAgentMaterial studentAgentMaterial) {
		studentAgentMaterialServiceImpl.update(studentAgentMaterial);
		return "redirect:/studentAgentMaterial";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		studentAgentMaterialServiceImpl.deleteStudentAgentMaterialById(id);
		return "redirect:/studentAgentMaterial";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public StudentAgentMaterial getJson(Model model, @PathVariable Integer id){
		return studentAgentMaterialServiceImpl.findStudentAgentMaterialById(id);
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
