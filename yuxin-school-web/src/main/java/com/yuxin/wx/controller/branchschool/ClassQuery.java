package com.yuxin.wx.controller.branchschool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassTypeVo;

@Controller
@RequestMapping("/classQuery")
public class ClassQuery {

    private static Log log = LogFactory.getLog("ClassQuery");
    
    @Autowired
	private IClassTypeService classTypeService;
    
    @Autowired
    private PropertiesUtil propertiesUtil;
        
    @RequestMapping(value = "/getClassList/{companyId}")
    public String getClassList(Model model, HttpServletRequest req,@PathVariable Integer companyId){
    	model.addAttribute("companyId", companyId);
        return"berkeley/classQuery/classQuery";
    }

    @RequestMapping("/classQueryDetail")
	public String noticeDetail(Model model,HttpServletRequest req,ClassTypeVo classtype){
    	Map<String, Object> queryParams=new HashMap<String, Object>();
    	queryParams.put("companyId", req.getParameter("companyId"));
    	queryParams.put("name", req.getParameter("name"));
    	queryParams.put("lable", req.getParameter("lable"));
    	queryParams.put("order_signup", req.getParameter("order_signup"));
    	queryParams.put("order_buy", req.getParameter("order_buy"));

    	queryParams.put("firstIndex", classtype.getFirstIndex());
    	queryParams.put("pageSize", classtype.getPageSize());
    	
    	//查询分校课程
    	List<ClassTypeVo> clist=classTypeService.queryClassOfBranchSchool(queryParams);
    	int count=classTypeService.queryCountClassOfBranchSchool(queryParams);
		PageFinder<ClassTypeVo> msgPage = new PageFinder<ClassTypeVo>(classtype.getPage(), classtype.getPageSize(), count, clist);
		model.addAttribute("msgPage", msgPage);
		return "berkeley/classQuery/classQueryDetail";
	}
    
}
