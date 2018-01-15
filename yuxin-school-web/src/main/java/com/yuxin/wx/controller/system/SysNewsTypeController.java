package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysNewsService;
import com.yuxin.wx.api.system.ISysNewsTypeService;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.system.SysNews;
import com.yuxin.wx.model.system.SysNewsType;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.SysNewsTypeVo;

@Controller
@RequestMapping("/sysNewsType")
public class SysNewsTypeController {
	@Autowired
	private ISysNewsTypeService sysNewsTypeServiceImpl;
	@Autowired
	private ISysConfigSchoolService sysConfigSchoolServiceImpl;
	@Autowired
	private ISysNewsService sysNewsServiceImpl;
	@Autowired
	private IAuthRoleService authRoleServiceImpl;
	/**
	 * 
	 * @fileName : SysNewsTypeController.java
	 * @date : 2015年12月3日 下午12:08:57
	 * @author :　杨延博
	 * @description :添加新闻类型
	 */
	@ResponseBody
	@RequestMapping(value="/insertSysNewsType", method = RequestMethod.POST)
	public JSONObject insert(SysNewsType sysNewsType,HttpServletRequest request){
		JSONObject json=new JSONObject();
		sysNewsType.setCompanyId(WebUtils.getCurrentCompanyId().toString());
		SysNewsType result=sysNewsTypeServiceImpl.findByName(sysNewsType);
		if(result!=null){
			json.put("flag", "error");
			return json;
		}
		sysNewsType.setDelFlag("0");
		sysNewsType.setCreateTime(new Date());
		sysNewsType.setCreater(WebUtils.getCurrentUserId(request));
		sysNewsTypeServiceImpl.insert(sysNewsType);
		json.put("flag", "success");
		return json;
	}
	/**
	 * 
	 * @fileName : SysNewsTypeController.java
	 * @date : 2015年12月3日 下午12:10:27
	 * @author :　杨延博
	 * @description :更新新闻类型
	 */
	@ResponseBody
	@RequestMapping(value="/updateSysNewsType", method = RequestMethod.POST)
	public JSONObject update(SysNewsType sysNewsType){
		JSONObject json=new JSONObject();
		sysNewsType.setCompanyId(WebUtils.getCurrentCompanyId().toString());
		if(sysNewsType.getId()!=null){
			SysNewsType result1=sysNewsTypeServiceImpl.findSysNewsTypeById(sysNewsType.getId());
			SysNewsType result2=sysNewsTypeServiceImpl.findByName(sysNewsType);
			if (result2!=null) {
				if(!result2.getName().equals(result1.getName())){
					json.put("flag", "error");
					return json;
				}
			}
		}
		sysNewsTypeServiceImpl.update(sysNewsType);
		json.put("flag", "success");
		return json;
	}
	
	/**
	 * 
	 * @fileName : SysNewsTypeController.java
	 * @date : 2015年12月3日 下午12:10:27
	 * @author :　杨延博
	 * @description :删除新闻类型
	 */
	@ResponseBody
	@RequestMapping(value="/deleteSysNewsType", method = RequestMethod.POST)
	public JSONObject delete(SysNewsType sysNewsType){
		JSONObject json=new JSONObject();
		if(sysNewsType.getDelFlag()!=null){
			if("0".equals(sysNewsType.getDelFlag())){
				sysNewsType.setDelFlag("1");
			}else if ("1".equals(sysNewsType.getDelFlag())) {
				sysNewsType.setDelFlag("0");
			}
		}
		try {
			sysNewsTypeServiceImpl.update(sysNewsType);
			SysNews sysNews=new SysNews();
			sysNews.setNewsType(sysNewsType.getId().toString());
			if("0".equals(sysNewsType.getDelFlag())){
				sysNews.setDelFlag(0);
			}else if ("1".equals(sysNewsType.getDelFlag())) {
				sysNews.setDelFlag(1);
			}
			sysNewsServiceImpl.updateDelFlag(sysNews);
			
		} catch (Exception e) {
			json.put("flag", "error");
		}
		json.put("flag", "success");
		return json;
	}
	
	/**
	 * 
	 * @fileName : SysNewsTypeController.java
	 * @date : 2015年12月3日 下午12:35:06
	 * @author :　杨延博
	 * @description :查询新闻类型列表
	 */
	@ResponseBody
	@RequestMapping(value="/selectSysNewsType", method = RequestMethod.POST)
	public List<SysNewsTypeVo> select(SysNewsType sysNewsType){
		sysNewsType.setCompanyId(WebUtils.getCurrentCompanyId().toString());
		List<SysNewsTypeVo> result=sysNewsTypeServiceImpl.queryNewsType(sysNewsType);
		return result;
	}
	
	/**
	 * 
	 * @fileName : SysNewsTypeController.java
	 * @date : 2015年12月3日 下午12:35:06
	 * @author :　杨延博
	 * @description :查询生效的新闻类型
	 */
	@ResponseBody
	@RequestMapping(value="/selectSysNewsType2", method = RequestMethod.POST)
	public List<SysNewsTypeVo> select2(SysNewsType sysNewsType){
		sysNewsType.setDelFlag("0");
		sysNewsType.setCompanyId(WebUtils.getCurrentCompanyId().toString());
		List<SysNewsTypeVo> result=sysNewsTypeServiceImpl.queryNewsType(sysNewsType);
		return result;
	}
	
	/**
	 * 
	 * @fileName : SysNewsTypeController.java
	 * @date : 2015年12月3日 下午12:10:27
	 * @author :　杨延博
	 * @description :检查新闻类型是否重复
	 */
	@ResponseBody
	@RequestMapping(value="/checkExist", method = RequestMethod.POST)
	public Boolean checkExist(SysNewsType sysNewsType){
		sysNewsType.setDelFlag("0");
		sysNewsType.setCompanyId(WebUtils.getCurrentCompanyId().toString());
		sysNewsType.setSchoolId(WebUtils.getCurrentSchoolId().toString());
		SysNewsType result=sysNewsTypeServiceImpl.findByName(sysNewsType);
		if(result!=null){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @fileName : SysNewsTypeController.java
	 * @date : 2015年12月3日 下午4:00:47
	 * @author :　杨延博
	 * @description :跳转新闻类型页面
	 */
	@RequestMapping(value="/newsTypePage", method = RequestMethod.GET)
	public String newsTypePage(Model model,HttpServletRequest request){
		Integer companyId=WebUtils.getCurrentCompanyId();
		Integer schoolId=WebUtils.getCurrentSchoolId();
		Integer userId=WebUtils.getCurrentUserId(request);
		if(authRoleServiceImpl.hasRoleFlag(userId,WebUtils.getCurrentCompanyId())){
			List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
			model.addAttribute("schoolId", schoolId);
			model.addAttribute("schoolList", schoolList);
		}else{
			SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(schoolId);
			model.addAttribute("school1",school);
		}
//		Subject subject = SecurityUtils.getSubject();
//		if(subject.hasRole("机构管理员")){
//			List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
//			model.addAttribute("schoolId", schoolId);
//			model.addAttribute("schoolList", schoolList);
//		}else if(subject.hasRole("分校管理员")){
//			SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(schoolId);
//			model.addAttribute("school1",school);
//		}else{
//			
//		}
		return "system/newsTypeManage";
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
