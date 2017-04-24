package com.yuxin.wx.controller.system;

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

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigTermService;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigTerm;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.ConfigTermVo;

/**
 * Controller of SysConfigTerm
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/sysConfigTerm")
public class SysConfigTermController{
	
	private Log log = LogFactory.getLog("log");
	
	@Autowired
	private ISysConfigTermService sysConfigTermServiceImpl;
	
	@Autowired
	private ISysConfigItemService configItemService;
	
	/**
	 * 
	 * Class Name: SysConfigTermController.java
	 * @Description: 跳向考期列表页
	 * @author liuxindong
	 * @date 2014-12-14 下午8:48:53
	 * @version 1.0
	 * @param model
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "goTermList",method = RequestMethod.GET)
	public String goTermList(Model model, SysConfigTerm search){
		return "system/term/termList";
	}
	
	/**
	 * 
	 * Class Name: SysConfigTermController.java
	 * @Description: 获得考期列表
	 * @author liuxindong
	 * @date 2014-12-14 下午8:49:23
	 * @version 1.0
	 * @param model
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "getTerms",method = RequestMethod.GET)
	public String getTerms(Model model, SysConfigTerm search, HttpServletRequest request){
		search.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		
		model.addAttribute("terms", sysConfigTermServiceImpl.findSysConfigTermByPage(search));
		model.addAttribute("search", search);
		return "system/term/termAjax";
	}
	
	/**
	 * 
	 * Class Name: SysConfigTermController.java
	 * @Description: 跳向新增或修改考期页面
	 * @author liuxindong
	 * @date 2014-12-14 下午8:49:36
	 * @version 1.0
	 * @param model
	 * @param search
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goManageTerm",method = RequestMethod.GET)
	public String goManageTerm(Model model, SysConfigTerm search,HttpServletRequest request){
		List<SysConfigTerm> terms = sysConfigTermServiceImpl.findTerm(search);
		if (terms != null && terms.size() > 0) {
			model.addAttribute("term", terms.get(0));
		}
		return "system/term/termManage";
	}
	
	/**
	 * 
	 * Class Name: SysConfigTermController.java
	 * @Description: 新增考期
	 * @author liuxindong
	 * @date 2014-12-14 下午8:50:07
	 * @version 1.0
	 * @param sysConfigTerm
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addTerm", method = RequestMethod.POST)
	public String addTerm(SysConfigTerm sysConfigTerm,HttpServletRequest request) {
		try {
			sysConfigTerm.setDelFlag(0);
			wrapperSaveTerm(sysConfigTerm,SysConfigConstant.OPERATE_ADD,request);
			sysConfigTermServiceImpl.insert(sysConfigTerm);
		} catch (Exception e) {
			log.error("新增考期出错！", e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 
	 * Class Name: SysConfigTermController.java
	 * @Description: 修改考期
	 * @author liuxindong
	 * @date 2014-12-14 下午8:50:22
	 * @version 1.0
	 * @param sysConfigTerm
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editTerm", method = RequestMethod.POST)
	public String editTerm(SysConfigTerm sysConfigTerm,HttpServletRequest request) {
		try {
			wrapperSaveTerm(sysConfigTerm,SysConfigConstant.OPERATE_EDIT,request);
			sysConfigTermServiceImpl.update(sysConfigTerm);
		} catch (Exception e) {
			log.error("修改考期出错！", e);
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 
	 * Class Name: SysConfigTermController.java
	 * @Description: 校验考期唯一性
	 * @author liuxindong
	 * @date 2014-12-14 下午8:50:34
	 * @version 1.0
	 * @param search
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkUnique", method = RequestMethod.POST)
	public String checkUnique(SysConfigTerm search,HttpServletRequest request) {
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<SysConfigTerm> terms = sysConfigTermServiceImpl.findTerm(search);
		if (terms != null && terms.size() > 0) {
			return "success";
		}
		return "fail";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigTerm getJson(Model model, @PathVariable Integer id){
		return sysConfigTermServiceImpl.findSysConfigTermById(id);
	}
	
	/**
	 * Class Name: SysConfigTermController.java
	 * @Description:(根据一级项目一级校区Id获取对应的目标考期)
	 * @author wang.zx 
	 * @date 2014-12-26 上午11:46:58
	 * @version 1.0
	 * @param request
	 * @param itemOneId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/dic/{itemOneId}", method = RequestMethod.GET)
	public List<SysConfigTerm> getJsons(HttpServletRequest request, @PathVariable Integer itemOneId){
		Users user=WebUtils.getCurrentUser(request);
		SysConfigTerm search=new SysConfigTerm();
		search.setItemOneId(itemOneId);
		search.setSchoolId(user.getSchoolId());
		return sysConfigTermServiceImpl.findSysConfigTermList(itemOneId);
	}
	
	@ResponseBody
	@RequestMapping(value="/terms/{itemOneId}")
	public List<SysConfigTerm> getTermByItemOne(@PathVariable Integer itemOneId){
		return sysConfigTermServiceImpl.getTermByItemOne(itemOneId,WebUtils.getCurrentCompanyId());
	}
	
	@ResponseBody
	@RequestMapping(value="/dict")
	public List<SysConfigTerm> getJsons(HttpServletRequest request,SysConfigTerm search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<SysConfigTerm> al=sysConfigTermServiceImpl.findSysConfigTermList(search);
		return al;
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
	 * Class Name: SysConfigTermController.java
	 * @Description: 包装考期新增或修改参数
	 * @author liuxindong
	 * @date 2014-12-14 下午8:51:19
	 * @version 1.0
	 * @param sysConfigTerm
	 * @param operate
	 * @param request
	 */
	private void wrapperSaveTerm(SysConfigTerm sysConfigTerm,String operate,HttpServletRequest request){
		if (operate.equals(SysConfigConstant.OPERATE_ADD)) {
			sysConfigTerm.setCreateTime(new Date());
			sysConfigTerm.setCreator(WebUtils.getCurrentUserId(request));
			sysConfigTerm.setCompanyId(WebUtils.getCurrentCompanyId());
		} else if (operate.equals(SysConfigConstant.OPERATE_EDIT)) {
			sysConfigTerm.setUpdateTime(new Date());
			sysConfigTerm.setUpdator(WebUtils.getCurrentUserId(request));
			sysConfigTerm.setCompanyId(WebUtils.getCurrentCompanyId());
		}
	}
	
/**
 * 
 * Class Name: SysConfigTermController.java
 * @Description: 链接到考期页面
 * @author ycl
 * @date 2015-5-9 上午10:41:17
 * @modifier
 * @modify-date 2015-5-9 上午10:41:17
 * @version 1.0
 * @param model
 * @return
 */
	@RequestMapping(value="/toExamDate")
	public String toExamDate(Model model){
		SysConfigTerm search = new SysConfigTerm();
		Users users = WebUtils.getCurrentUser();
		Integer companyId = users.getCompanyId();
		search.setCompanyId(companyId);
		
		//查询公司 校区下的一级项目
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("companyId", users.getCompanyId());
		param.put("schoolId", users.getSchoolId());
		param.put("itemType", 1);
		List<SysConfigItem> companyItems = configItemService.findItemBySchoolCompanyId(param);
		
		
		List<ConfigTermVo> sctList = sysConfigTermServiceImpl.findByCompandyId(search);
		Map<String,List<ConfigTermVo>> sctMap = new HashMap<String, List<ConfigTermVo>>();
		List<ConfigTermVo> newList = null;
		for (ConfigTermVo sct : sctList) {
			String keyMap = sct.getItemName();
			if(sctMap.containsKey(keyMap)){
				newList = sctMap.get(keyMap);
				newList.add(sct);
			}else{
				newList = new ArrayList<ConfigTermVo>();
				newList.add(sct);
				sctMap.put(keyMap, newList);
			}
		}
		Integer schoolId = users.getSchoolId();
		String schoolName = users.getSchoolName();
		
		model.addAttribute("schoolName",schoolName);
		model.addAttribute("schoolId",schoolId);
		model.addAttribute("companyItems",companyItems);
		model.addAttribute("sctMap",sctMap);
		return "resource/term/examDate";
	}
	
	@ResponseBody
	@RequestMapping("/selTermByOneItemId")
	public JSONObject selTermByOneItemId(Integer oneItemId){
		JSONObject json = new JSONObject();
		json.put("term", sysConfigTermServiceImpl.findTermByOneItemId(oneItemId));
		return json;
	}

}
