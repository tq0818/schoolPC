package com.yuxin.wx.controller.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.student.IStudentAgentMaterialService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigTermService;
import com.yuxin.wx.common.ExcelSheetEntity;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigTerm;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.EntityUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.student.StudentVo;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    IStudentPayMasterService studentPayMasterServiceImpl;
    @Autowired
    IStudentService StudentServiceImpl;
    @Autowired
    IStudentAgentMaterialService studentAgentMaterialServiceImpl;
    @Autowired
    ISysConfigItemService sysConfigItemServiceImpl;
    @Autowired
    IStudentService studentServiceImpl;
    @Autowired
	ISysConfigTermService sysConfigTermServiceImpl;
    
    /**
     * 
     * Class Name: ExamController.java
     * @Description: 查询一级项目并跳转到代报考页面
     * @author zhang.zx
     * @date 2015年5月3日 上午9:23:51
     * @modifier
     * @modify-date 2015年5月3日 上午9:23:51
     * @version 1.0
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/registerPage",method = RequestMethod.GET)
    public String forwardPage(Model model,HttpServletRequest request){
		List<SysConfigItem> firstItems = sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
		model.addAttribute("firstItems", firstItems);
    	return "/student/student-register";
    }
    
    @RequestMapping(value="/page/{page}", method = RequestMethod.GET)
    public String showPage(Model model,HttpServletRequest request, @PathVariable String page){
        return "/student/"+page;
    }
    
    /**
	 * 
	 * Class Name: StudentController.java
	 * @Description: 根据订单查询学员,异步
	 * @author zhang.zx
	 * @date 2015-5-4
	 * @version 1.0
	 * @param model
	 * @param search
	 * @return
	 */
	@RequestMapping(value="/ajaxStudentList", method = RequestMethod.POST)
	public String stuList2(Model model,HttpServletRequest request,StudentVo search){
		model.addAttribute("search", search);
		search.setPageSize(5);
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		search.setCompanyId(WebUtils.getCurrentCompanyId());
//		PageFinder<StudentVo> pageFinder=studentServiceImpl.findPageListByPayment(search);
		PageFinder<StudentVo> pageFinder=studentServiceImpl.findPageListByPaymentNew(search);
		model.addAttribute("pageFinder", pageFinder);
		model.addAttribute("stuMobiles", search.getMobile());
		return "student/studentAjaxList";
	}
	
	@RequestMapping(value="/exportExcels", method = RequestMethod.POST)
    public ModelAndView  exportExcels(Model model, StudentVo search){
     List<StudentVo> al=new ArrayList<StudentVo>();
     if (EntityUtil.isNotBlank(search)) {
      search.setPageSize(1000);
      search.setSchoolId(WebUtils.getCurrentSchoolId());
      search.setCompanyId(WebUtils.getCurrentCompanyId());
      al=studentServiceImpl.findListByPayments(search);
     }
     for(StudentVo stu:al){
    	 if(null!=stu.getIsAgent() && stu.getIsAgent().equals("1")){
    		 stu.setIsAgent("是");
    	 }else{
    		 stu.setIsAgent("否");
    	 }
    	 if(null!=stu.getIsAgentMaterialComplete() && stu.getIsAgentMaterialComplete().equals("1")){
    		 stu.setIsAgentMaterialComplete("是");
    	 }else{
    		 stu.setIsAgentMaterialComplete("否");
    	 }
    	 if(null!=stu.getIsAgentComplete() && stu.getIsAgentComplete().equals("1")){
    		 stu.setIsAgentComplete("是");
    	 }else{
    		 stu.setIsAgentComplete("否");
    	 }
     }
     ViewFiles excel = new ViewFiles(); 
     Map<String,Object> map = new HashMap<String,Object>();  
     ExcelSheetEntity entity=ExcelSheetEntity.newInstance("姓名:name,用户名:username,手机号:mobile,学科:itemOneName,学科小类:itemSecondName,课程名称:classTypeName,是否代报:isAgent,是否完成代报考:isAgentComplete,资料齐全:isAgentMaterialComplete,操作人:updator",al);
     map.put("entity", entity);
     map.put("fileName", "学员列表.xls");
     return new ModelAndView(excel,map);
    }
    
	/**
	 * 
	 * Class Name: ExamController.java
	 * @Description:根据一级项目查询二级项目
	 * @author zhang.zx
	 * @date 2015年5月3日 下午8:03:29
	 * @modifier
	 * @modify-date 2015年5月3日 下午8:03:29
	 * @version 1.0
	 * @param pid
	 * @return
	 */
    @ResponseBody
    @RequestMapping(value="/queryItemSecond",method=RequestMethod.POST)
	public List<SysConfigItem> findItemTwo(Integer pid){	
    	return sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_SECOND, pid, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
		// return sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_SECOND, pid,WebUtils.getCurrentCompanyId());
	}
    
    /**
     * 
     * Class Name: ExamController.java
     * @Description: 根据一级项目查询考期
     * @author zhang.zx
     * @date 2015年5月3日 上午10:44:52
     * @modifier
     * @modify-date 2015年5月3日 上午10:44:52
     * @version 1.0
     * @param itemOneId
     * @return
     */
    @ResponseBody
	@RequestMapping(value="/terms",method=RequestMethod.POST)
	public List<SysConfigTerm> getTermByItemOne(Integer itemOneId){
    	SysConfigTerm search=new SysConfigTerm();
    	search.setItemOneId(itemOneId);
    	search.setSchoolId(WebUtils.getCurrentSchoolId());
    	search.setCompanyId(WebUtils.getCurrentCompanyId());
		return sysConfigTermServiceImpl.findSysConfigTermList(search);
	}
    
    @ResponseBody
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public PageFinder<StudentVo> findList(HttpServletRequest request,StudentVo search){
    	search.setSchoolId(WebUtils.getCurrentSchoolId());
    	search.setCompanyId(WebUtils.getCurrentCompanyId());
        return studentPayMasterServiceImpl.findListByTerm(search);
    }
    
    @ResponseBody
    @RequestMapping(value="/apply/{id}", method = RequestMethod.POST)
    public String applyExam(HttpServletRequest request,@PathVariable Integer id){
    	StudentPayMaster data=new StudentPayMaster();
    	data.setId(id);
    	data.setIsAgentComplete(""+1);
    	studentPayMasterServiceImpl.update(data);
        return "success";
    }
    
    @ResponseBody
    @RequestMapping(value="/detail", method = RequestMethod.POST)
    public List<StudentAgentMaterial> showDetail(Model model,Integer stuId, Integer payMasterId){
        List<StudentAgentMaterial> al=studentAgentMaterialServiceImpl.findStudentAgentMaterialByStuId(stuId, payMasterId);
        return al;
    }
    
    @RequestMapping(value="/exportExcel", method = RequestMethod.POST)
    public ModelAndView  exportExcel(Model model, StudentVo search){
     List<StudentVo> al=new ArrayList<StudentVo>();
     if (EntityUtil.isNotBlank(search)) {
      search.setPageSize(100);
      search.setSchoolId(WebUtils.getCurrentSchoolId());
      search.setCompanyId(WebUtils.getCurrentCompanyId());
      al=studentPayMasterServiceImpl.exportToExcel(search);
     }
     ViewFiles excel = new ViewFiles(); 
     Map<String,Object> map = new HashMap<String,Object>();  
     ExcelSheetEntity entity=ExcelSheetEntity.newInstance("姓名:name,手机号:mobile,一级项目:itemOneName,二级项目:itemSecondName,班型:classTypeName,考期:examTermName,是否代报:isAgent,资料齐全:isAgentMaterialComplete,操作人:updator",al);
     map.put("entity", entity);
     map.put("fileName", "学员列表.xls");
     return new ModelAndView(excel,map);
    }
   
    @ResponseBody
    @RequestMapping(value="/repay", method = RequestMethod.POST)
    public String repayMaterial(HttpServletRequest request,StudentPayMaster master){
        List<StudentAgentMaterial> materials=JSONObject.parseArray(request.getParameter("material"), com.yuxin.wx.model.student.StudentAgentMaterial.class);
//        StudentPayMaster master=(StudentPayMaster)JSONObject.parse(request.getParameter("payMaster"));
        return studentAgentMaterialServiceImpl.repayMaterial(materials,master,WebUtils.getCurrentUserId(request));
    }
    
}
