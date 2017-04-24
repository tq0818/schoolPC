package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.system.ISysConfigCampusService;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.system.SysConfigCampus;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.ConfigCampusVo;

/**
 * Controller of SysConfigCampus
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/sysConfigCampus")
public class SysConfigCampusController{
	
	private Log log = LogFactory.getLog("log");
	
	@Autowired
	private ISysConfigCampusService sysConfigCampusServiceImpl;
	
	@Autowired
	private IClassModuleNoService classModuleNoServiceImpl;
	
	/**
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 跳向校区列表页
	 * @author liuxindong
	 * @date 2014-12-13 下午7:05:05
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "goCampusList",method = RequestMethod.GET)
	public String goCampusList(Model model){
		return "system/campus/campusList";
	}
	
	/**
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 获取校区列表
	 * @author liuxindong
	 * @date 2014-12-13 下午7:05:32
	 * @version 1.0
	 * @param model
	 * @param search
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getCampuses",method = RequestMethod.POST)
	public String getCampuses(Model model, SysConfigCampus search,HttpServletRequest request){
		wrapperSearchCampus(search,request);
		List<SysConfigCampus> campuses = sysConfigCampusServiceImpl.findSysConfigCampusByPage(search);
		model.addAttribute("search", search);
		model.addAttribute("campuses", campuses);
		return "system/campus/campusAjax";
	}
	
	/**
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 获取校区列表
	 * @author chopin
	 * @date 2014-12-21
	 * @version 1.0
	 * @param model
	 * @param search
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dicts",method = RequestMethod.POST)
	public List<SysConfigCampus>  getList(SysConfigCampus search,HttpServletRequest request){
		if(search.getSchoolId()==null){
			search.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
		}
		search.setStatus(1);
		List<SysConfigCampus> campuses = sysConfigCampusServiceImpl.findCampus(search);
		return campuses;
	}
	
	/**
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 获取校区列表
	 * @author chopin
	 * @date 2014-12-21
	 * @version 1.0
	 * @param model
	 * @param search
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dict",method = RequestMethod.POST)
	public SysConfigCampus  getDict(SysConfigCampus search,HttpServletRequest request){
		search.setStatus(1);
		List<SysConfigCampus> campuses = sysConfigCampusServiceImpl.findCampus(search);
		if(campuses!=null && campuses.size()>0){
			return campuses.get(0);
		}else{
			return new SysConfigCampus();
		}
		
	}
	
	/**
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 跳向新增或编辑校区页面
	 * @author liuxindong
	 * @date 2014-12-13 下午7:05:46
	 * @version 1.0
	 * @param model
	 * @param search
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goManageCampus",method = RequestMethod.GET)
	public String goManageCampus(Model model, SysConfigCampus search,HttpServletRequest request){
		wrapperSearchCampus(search,request);
		List<SysConfigCampus> campuses = sysConfigCampusServiceImpl.findCampus(search);
		if (campuses != null && campuses.size() > 0) {
			model.addAttribute("campus", campuses.get(0));
		}
		return "system/campus/campusManage";
	}
	
	/**
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 新增校区
	 * @author liuxindong
	 * @date 2014-12-13 下午7:06:25
	 * @version 1.0
	 * @param sysConfigCampus
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addCampus", method = RequestMethod.POST)
	public String addCampus(SysConfigCampus sysConfigCampus,HttpServletRequest request) {
		try {
			sysConfigCampus.setStatus(1);
			wrapperSaveCampus(sysConfigCampus,SysConfigConstant.OPERATE_ADD,request);
			sysConfigCampusServiceImpl.insert(sysConfigCampus);
		} catch (Exception e) {
			log.error("新增校区出错！", e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 编辑校区
	 * @author liuxindong
	 * @date 2014-12-13 下午7:06:41
	 * @version 1.0
	 * @param sysConfigCampus
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editCampus", method = RequestMethod.POST)
	public String editCampus(SysConfigCampus sysConfigCampus,HttpServletRequest request) {
		try {
			wrapperSaveCampus(sysConfigCampus,SysConfigConstant.OPERATE_EDIT,request);
			sysConfigCampusServiceImpl.update(sysConfigCampus);
		} catch (Exception e) {
			log.error("修改校区出错！", e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 更改校区状态
	 * @author liuxindong
	 * @date 2014-12-13 下午7:06:53
	 * @version 1.0
	 * @param sysConfigCampus
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/changeCampusStatus", method = RequestMethod.POST)
	public String changeCampusStatus(SysConfigCampus sysConfigCampus,HttpServletRequest request) {
		//查询该校区所有未结束的课程
		List<ClassModuleNo> nos = classModuleNoServiceImpl.findClassModuleNoListByCampusId(sysConfigCampus.getId());
		if(nos != null && nos.size() > 0){
			return "has_campus";
		}
		try {
			wrapperSaveCampus(sysConfigCampus,SysConfigConstant.OPERATE_EDIT,request);
			sysConfigCampusServiceImpl.update(sysConfigCampus);
		} catch (Exception e) {
			log.error("修改校区状态出错！", e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 校验唯一性（校区名称，校区编号）
	 * @author liuxindong
	 * @date 2014-12-13 下午7:07:07
	 * @version 1.0
	 * @param search
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkUnique", method = RequestMethod.POST)
	public String checkUnique(SysConfigCampus search,HttpServletRequest request) {
		wrapperSearchCampus(search,request);
		List<SysConfigCampus> campuses = sysConfigCampusServiceImpl.checkedCampus(search);
		if (campuses != null && campuses.size() > 0) {
			return "success";
		}
		return "fail";
	}
	
	/**
	 * ajax获取校区列表json
	 * @param model
	 * @param search
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getCampusesJson",method = RequestMethod.POST)
	public List<SysConfigCampus> getCampusesJson(Model model, SysConfigCampus search,HttpServletRequest request){
		wrapperSearchCampus(search,request);
		search.setStatus(1);
		return sysConfigCampusServiceImpl.findCampus(search);
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
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 包装查询参数
	 * @author liuxindong
	 * @date 2014-12-13 下午7:07:31
	 * @version 1.0
	 * @param sysConfigCampus
	 * @param request
	 */
	private void wrapperSearchCampus(SysConfigCampus sysConfigCampus,HttpServletRequest request){
		sysConfigCampus.setSchoolId(WebUtils.getCurrentSchoolId());
		sysConfigCampus.setCompanyId(WebUtils.getCurrentCompanyId());
	}
	
	/**
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 包装新增或编辑参数
	 * @author liuxindong
	 * @date 2014-12-13 下午7:07:43
	 * @version 1.0
	 * @param sysConfigCampus
	 * @param operate
	 * @param request
	 */
	private void wrapperSaveCampus(SysConfigCampus sysConfigCampus,String operate,HttpServletRequest request){
		if (operate.equals(SysConfigConstant.OPERATE_ADD)) {
			sysConfigCampus.setDelFlag(0);
			sysConfigCampus.setCreateTime(new Date());
			sysConfigCampus.setCreator(WebUtils.getCurrentUserId(request));//WebUtils.getCurrentUserId(request)
		} else if (operate.equals(SysConfigConstant.OPERATE_EDIT)) {
			sysConfigCampus.setUpdateTime(new Date());
			sysConfigCampus.setUpdator(WebUtils.getCurrentUserId(request));//WebUtils.getCurrentUserId(request)
		}
		sysConfigCampus.setSchoolId(WebUtils.getCurrentSchoolId());
		sysConfigCampus.setCompanyId(WebUtils.getCurrentCompanyId());
	}
	/**
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 链接到校区首页
	 * @author ycl
	 * @date 2015-5-5 下午9:09:11
	 * @modifier
	 * @modify-date 2015-5-5 下午9:09:11
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="/toCampusInfo")
	public String toCampusInfo(){
		
		return "resource/campus/campusInfo";
	}
	/**
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 异步加载校区信息
	 * @author ycl
	 * @date 2015-5-5 下午9:09:30
	 * @modifier
	 * @modify-date 2015-5-5 下午9:09:30
	 * @version 1.0
	 * @param search
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/campusAjaxInfo")
	public String campusAjaxInfo(SysConfigCampus search,Model model){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		List<ConfigCampusVo> camList = sysConfigCampusServiceImpl.findCampusVo(search);
		if(camList!=null){
			model.addAttribute("camList",camList);
		}
		return "resource/campus/campusAjaxInfo";
	}
	
	/**
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 查询该校区下正在进行的课程
	 * @author ycl
	 * @date 2015-5-6 上午11:54:13
	 * @modifier
	 * @modify-date 2015-5-6 上午11:54:13
	 * @version 1.0
	 * @param sysConfigCampus
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findNoStopClassModelNo")
	public List<ClassModuleNo> findNoStopClassModelNo(SysConfigCampus sysConfigCampus,Model model){
		List<ClassModuleNo> nos = classModuleNoServiceImpl.findClassModuleNoListByCampusId(sysConfigCampus.getId());
		if(nos!=null){
			model.addAttribute("nos",nos);
		}
		return nos;
	}
	
	/**
	 * 
	 * Class Name: SysConfigCampusController.java
	 * @Description: 根据公司 和 分校 id 查询校区
	 * @author 周文斌
	 * @date 2015-5-15 下午12:47:29
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/schoolCampus")
	public JSONObject schoolCampus(HttpServletRequest request){
		JSONObject json = new JSONObject();
		//获得公司id 和 学校 id
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		Integer companyId = WebUtils.getCurrentCompanyId();
		//查询校区集合
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("companyId", companyId);
		param.put("schoolId", schoolId);
		List<SysConfigCampus> sccList = sysConfigCampusServiceImpl.findCampusBySchoolCompanyId(param);
		json.put("campus", sccList);
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/schoolCampusList")
	public List<SysConfigCampus>  schoolCampusList(HttpServletRequest request){
		//获得公司id 和 学校 id
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		Integer companyId = WebUtils.getCurrentCompanyId();
		//查询校区集合
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("companyId", companyId);
		param.put("schoolId", schoolId);
		List<SysConfigCampus> sccList = sysConfigCampusServiceImpl.findCampusBySchoolCompanyId(param);
		return sccList;
	}
}
