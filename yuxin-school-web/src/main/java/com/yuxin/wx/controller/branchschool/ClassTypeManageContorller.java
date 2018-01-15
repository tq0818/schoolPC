package com.yuxin.wx.controller.branchschool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassTypeOfBranchSchoolService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.vo.classes.ClassTypeVo;

/**
 * 数校课程管理:为分校添加购买课程
 * 
 */
@Controller
@RequestMapping("/classTypeManage")
public class ClassTypeManageContorller {

	private Log log=LogFactory.getLog("log");
	
    @Autowired
	private IClassTypeService classTypeService;
    
    @Autowired
	private ISysConfigSchoolService sysConfigSchoolService;
    
    @Autowired
  	private IClassTypeOfBranchSchoolService classTypeOfBranchSchoolService;
	
    @Autowired
  	private ICompanyService companyService;
	/**
     * 分校开放课程管理
     * @return
     */
    @RequestMapping(value = "/queryClassType/{companyId}")
    public String queryClassType(Model model,HttpServletRequest request,@PathVariable Integer companyId){
    	List<SysConfigSchool> schoollist=sysConfigSchoolService.findAllSysConfigSchool(companyId);
    	if(null!=schoollist&&schoollist.size()>0){
    		model.addAttribute("school", schoollist.get(0));
    	}
    	Company company=companyService.findCompanyById(companyId);
     	model.addAttribute("company", company);
    	model.addAttribute("companyId", companyId);
        return "berkeley/classTypeManage/classManage";
    }

    @RequestMapping("/classManageDetail")
   	public String classManageDetail(Model model,HttpServletRequest req,ClassTypeVo classtype){
       	Map<String, Object> queryParams=new HashMap<String, Object>();
       	queryParams.put("companyId", req.getParameter("companyId"));
       	queryParams.put("name", req.getParameter("name"));
       	queryParams.put("livestatus", req.getParameter("livestatus"));

       	queryParams.put("firstIndex", classtype.getFirstIndex());
       	queryParams.put("pageSize", classtype.getPageSize());
       	
       	//查询分校购买的直播课程
       	List<ClassTypeVo> clist=classTypeService.queryLiveClassOfBranchSchool(queryParams);
       	int count=classTypeService.queryCountLiveClassOfBranchSchool(queryParams);
   		PageFinder<ClassTypeVo> msgPage = new PageFinder<ClassTypeVo>(classtype.getPage(), classtype.getPageSize(), count, clist);
   		model.addAttribute("msgPage", msgPage);
   		return "berkeley/classTypeManage/classManageDetail";
   	}
    
    @RequestMapping("/classListOfOtherSchool")
   	public String classListOfOtherSchool(Model model,HttpServletRequest req,ClassTypeVo classtype){
       	Map<String, Object> queryParams=new HashMap<String, Object>();
       	queryParams.put("companyId", req.getParameter("companyId"));
       	queryParams.put("className", req.getParameter("className"));
       	queryParams.put("schoolName", req.getParameter("schoolName"));

       	queryParams.put("firstIndex", classtype.getFirstIndex());
       	queryParams.put("pageSize", classtype.getPageSize());
       	
       	//查询分校购买的直播课程
       	List<ClassTypeVo> clist=classTypeService.queryLiveClassOfOtherSchool(queryParams);
       	int count=classTypeService.queryCountLiveClassOfOtherSchool(queryParams);
   		PageFinder<ClassTypeVo> msgPage = new PageFinder<ClassTypeVo>(classtype.getPage(), classtype.getPageSize(), count, clist);
   		model.addAttribute("resPage", msgPage);
   		return "berkeley/classTypeManage/classOfOtherSchoolDetail";
   	}
    
    @ResponseBody
    @RequestMapping("/addClassType")
    public JSONObject addClassType(HttpServletRequest req){
    	JSONObject json=new JSONObject();
    	Map<String, String> params=new HashMap<String, String>();
    	params.put("sourceClassTypeId", req.getParameter("classTypeId"));
    	params.put("classId", req.getParameter("classTypeId"));
    	params.put("targetCompanyId", req.getParameter("companyId"));
    	params.put("targetSchoolId", req.getParameter("schoolId"));
    	try {
    		//判断该课程是否已添加
    		Integer count=this.classTypeOfBranchSchoolService.findSchoolShareClassType(params);
    		if(null!=count&&count>1){
    			json.put(JsonMsg.RESULT, "该分校已经添加该课程");
    			return json;
    		}
    		//复制课程
			String result=this.classTypeOfBranchSchoolService.copyClassTypeToTargetCompany(params);
    		if("success"==result){
    			json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
    		}else{
    			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
    		}
		} catch (Exception e) {
			log.error(e.getMessage());
			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
		}
    	return json;
    }
}
