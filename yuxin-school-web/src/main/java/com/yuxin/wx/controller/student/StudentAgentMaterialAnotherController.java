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
@RequestMapping("/studentAgentMaterial2")
public class StudentAgentMaterialAnotherController {
	/**
	 * 
	 * Class Name: StudentAgentMaterialController.java
	 * @Description: 跳转到学员报考材料页面
	 * @author 杨延博
	 * @date 2015年10月12日 上午10:05:56
	 * @modifier
	 * @modify-date 2015年4月25日 上午10:05:56
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="/stuMaterial2",method=RequestMethod.POST)
	public String forwardStuMaterial2(String mobile , Model model){
		model.addAttribute("mobile", mobile);
		return "student/studentMaterial2";
	}
}
