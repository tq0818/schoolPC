package com.yuxin.wx.controller.system;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.company.ICompanyIndexModelClasstypeService;
import com.yuxin.wx.api.company.ICompanyIndexModelCustomService;
import com.yuxin.wx.api.company.ICompanyIndexModelItemService;
import com.yuxin.wx.api.company.ICompanyIndexModelNewsService;
import com.yuxin.wx.api.company.ICompanyIndexModelPrivatepageService;
import com.yuxin.wx.api.company.ICompanyIndexTemplateService;
import com.yuxin.wx.api.company.ICompanyPicsService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.IOrganLeaveMessageService;
import com.yuxin.wx.api.system.ISysBodyService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigIndexClasstypeService;
import com.yuxin.wx.api.system.ISysConfigIndexCustomService;
import com.yuxin.wx.api.system.ISysConfigIndexItemService;
import com.yuxin.wx.api.system.ISysConfigIndexNewsService;
import com.yuxin.wx.api.system.ISysConfigIndexPrivatepageService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysPageHeadFootService;
import com.yuxin.wx.common.CacheService;
import com.yuxin.wx.common.Constants;
import com.yuxin.wx.common.ExcelSheetEntity;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.common.CacheService.Ckey;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.OrganLeaveMessage;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysPageHeadFoot;
import com.yuxin.wx.util.JedisUtil;
import com.yuxin.wx.util.officeConvert.OfficeThreadPool;
import com.yuxin.wx.util.officeConvert.OpenOfficeManagement;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.SysConfigPrivatePageVo;
import com.yuxin.wx.vo.user.OrganLeaveMessageExcelVo;
import com.yuxin.wx.vo.user.OrganLeaveMessageVo;


@Controller
@RequestMapping("/console")
public class ConsoleController {
	private Log log = LogFactory.getLog("log");
	
	@Autowired
	private ICompanyIndexModelClasstypeService companyIndexModelClasstypeServiceImpl; 
	@Autowired
	private ICompanyIndexModelCustomService companyIndexModelCustomServiceImpl; 
	@Autowired
	private ICompanyIndexModelItemService companyIndexModelItemServiceImpl; 
	@Autowired
	private ICompanyIndexModelNewsService companyIndexModelNewsServiceImpl; 
	@Autowired
	private ICompanyIndexModelPrivatepageService companyIndexModelPrivatepageServiceImpl; 
	@Autowired
	private ICompanyIndexTemplateService companyIndexTemplateServiceImpl; 
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private ISysConfigIndexClasstypeService sysConfigIndexClasstypeServiceImpl; 
	@Autowired
	private ISysConfigIndexCustomService sysConfigIndexCustomServiceImpl;
	@Autowired
	private ISysConfigIndexItemService sysConfigIndexItemServiceImpl;
	@Autowired
	private ISysConfigIndexNewsService sysConfigIndexNewsServiceImpl;
	@Autowired
	private ISysConfigIndexPrivatepageService sysConfigIndexPrivatepageServiceImpl;
	@Autowired
	private ISysBodyService sysBodySerivceImpl;
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	@Autowired
	private ICompanyPicsService companyPicsServiceImpl;
	@Autowired
	private ISysConfigSchoolService sysConfigSchoolServiceImpl;
	@Autowired
	private ISysConfigDictService sysConfigDictServiceImpl;
	@Autowired
	private ICompanyService  companyServiceImpl;
	@Autowired
	private ISysPageHeadFootService sysPageHeadFootServiceImpl; 
	@Autowired
	private IAuthRoleService authRoleServiceImpl;
	@Autowired
	private IOrganLeaveMessageService organLeaveMessageServiceImpl;
	
	
	/**
	 * 
	 * Class Name: ConsoleController.java
	 * @Description: 显示主页
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"","/index"})
	public String show(Model model,HttpServletRequest request){
		if(!authRoleServiceImpl.checkUserHasPrivilege(WebUtils.getCurrentUserId(request), "system_privilege_config")){
 			return "404";
 		}
		return "console/redis";
	}
	
	
	@RequestMapping(value="exportLeaveMessage/{marks}")
	public ModelAndView exportLeaveMessage(Model model,HttpServletRequest request,OrganLeaveMessageVo olm,@PathVariable String marks){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if("today".equals(marks)){
		   olm.setMarks(marks);
		   olm.setTimeLen(0);
	    }else if("yesday".equals(marks)){
	       olm.setMarks(marks);
		   olm.setTimeLen(1);
	    }else if("sevnday".equals(marks)){
	       olm.setMarks(marks);
		   olm.setTimeLen(7);
	    }else if("thirty".equals(marks)){
	       olm.setMarks(marks);
		   olm.setTimeLen(30);
	    }
		if(!"nos".equals(marks)){
			olm.setStartTime("");
			olm.setEndTime("");
		}
		List<OrganLeaveMessage>  result=organLeaveMessageServiceImpl.queryOrganLeaveMessageListForExport(olm);
		List<OrganLeaveMessageExcelVo> olmList=new ArrayList<OrganLeaveMessageExcelVo>();
		for (OrganLeaveMessage organLeaveMessage : result) {
			OrganLeaveMessageExcelVo olmeVo=new OrganLeaveMessageExcelVo();
			olmeVo.setName(organLeaveMessage.getName());
			olmeVo.setMobile(organLeaveMessage.getMobile());
			olmeVo.setQq(organLeaveMessage.getQq());
			olmeVo.setUtmUrl(organLeaveMessage.getUtmUrl());
			olmeVo.setContents(organLeaveMessage.getContents());
			olmeVo.setRecordTime(formatter.format(organLeaveMessage.getRecordTime()));
			olmList.add(olmeVo);
		}
		ExcelSheetEntity sheet=ExcelSheetEntity.newInstance("姓名:name,手机号:mobile,QQ:qq,来源渠道:utmUrl,留言:contents,留言时间:recordTime", olmList);
		try{
			 ViewFiles excel = new ViewFiles(); 
		     Map<String,Object> map = new HashMap<String,Object>();  
		     map.put("entity", sheet);
		     map.put("fileName", "留言表.xls");
		     return new ModelAndView(excel,map);
		}catch(Exception ex){
			log.error(ex,ex);
			ex.printStackTrace();
		}
		return new ModelAndView();
	}
	
	/**
	 * 
	 * Class Name: ConsoleController.java
	 * @Description: 显示主页
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/redis/update/dict")
	public String redisUpdateDict(Model model,HttpServletRequest request){

		List<SysConfigDict>list=sysConfigDictServiceImpl.findAll();
		Ckey ckey=new Ckey();
		ckey.setModule(Constants.MODULE_DICT);

		CacheService.updateCache(ckey,list);
		return "success";
	}
	
	/**
	 * 
	 * Class Name: ConsoleController.java
	 * @Description: 更新模板信息
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/redis/update/model")
	public String redisUpdateModel(Model model,HttpServletRequest request){
		List<SysConfigDict>list=sysConfigDictServiceImpl.findAll();
		Ckey ckey=new Ckey();
		ckey.setModule(Constants.MODULE_BODY);
		ckey.setTag(Constants.TAG_MODEL);
		CacheService.updateCache(ckey,list);
		return "success";
	}
	
	/**
	 * 
	 * Class Name: ConsoleController.java
	 * @Description: 更新模板信息
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/redis/update/body")
	public String redisUpdateBody(Model model,HttpServletRequest request){
		Integer companyId=Integer.parseInt(request.getParameter("companyId"));
		Integer schoolId=Integer.parseInt(request.getParameter("schoolId"));
		Integer id=Integer.parseInt(request.getParameter("templeteId"));
		companyIndexTemplateServiceImpl.useTemplate(companyId, schoolId, id);
		List<SysConfigPrivatePageVo>list=sysBodySerivceImpl.publishToFront(WebUtils.getCurrentCompanyId(), schoolId, id);
		Ckey ckey=new Ckey();
		ckey.setCompanyId(""+companyId);
		ckey.setSchoolId(""+schoolId);
		ckey.setModule(Constants.MODULE_BODY);
		CacheService.updateCache(ckey,list);
		return "success";
	}
	
	/**
	 * 
	 * Class Name: ConsoleController.java
	 * @Description: 更新某公司首页配置
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/redis/update/company")
	public String redisUpdateCompany(Model model,HttpServletRequest request){
		List<Company> list=companyServiceImpl.findAll();
		Ckey ckey=new Ckey();
		ckey.setModule(Constants.MODULE_COMPANY);
		CacheService.updateCache(ckey,list);
		return "success";
	}
	
	/**
	 * 
	 * Class Name: ConsoleController.java
	 * @Description: 更新某公司首页配置
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/redis/update/headfoot")
	public String redisUpdateHeadfoot(Model model,HttpServletRequest request){
		String companyId=request.getParameter("comapnyId");
		List<SysPageHeadFoot> list=sysPageHeadFootServiceImpl.findByConpanyId(Integer.parseInt(companyId));
		Ckey ckey=new Ckey();
		ckey.setModule(Constants.MODULE_HEADFOOT);
		ckey.setCompanyId(companyId);
		CacheService.updateCache(ckey,list);
		return "success";
	}
	/**
	 * 
	 * Class Name: ConsoleController.java
	 * @Description: 更新某公司首页配置
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/redis/manage/release")
	public String redisManageRelease(Model model,HttpServletRequest request){
		Set<String> keys=JedisUtil.getKeysByPattern("*");
		for(String key : keys){
			JedisUtil.deleteByKey(key);
		}
		return "success";
	}
	/**
	 * 
	 * Class Name: ConsoleController.java
	 * @Description: 更新某公司首页配置
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/redis/manage/watch")
	public Set<String> redisManageWatch(Model model,HttpServletRequest request){
		Set<String> keys=JedisUtil.getKeysByPattern("*");
		return keys;
	}
	
	/**
	 * 
	 * Class Name: ConsoleController.java
	 * @Description: openoffice
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "openoffice")
	public String openOffice(Model model,HttpServletRequest request){
		try{
//			String info="";
//			OpenOfficeManagement.findPid(info);
//			model.addAttribute("info",info);
		}catch(Exception ex){
			
		}
		return "console/restartOpenOffice";
	}
	
	/**
	 * 
	 * Class Name: ConsoleController.java
	 * @Description: 重启openoffice
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/restart/openoffice")
	public String restartOpenOffice(Model model,HttpServletRequest request,String action,Integer port){
//		List<OpenOfficeManagement> manage=OfficeThreadPool.getNewInstance().getThreads();
//		OpenOfficeManagement m=new OpenOfficeManagement(port!=null?port:8100,300000L);
//			switch(action){
//			case "restart":
//				m.shutDownAll();
//				m.startUp();
//				model.addAttribute("result", "已重启");
//				break;
//			case "shutdown":
//				m.shutDownAll();
//				model.addAttribute("result", "已停止");
//				break;
//			case "startup":
//				m.startUp();
//				model.addAttribute("result", "已启动");
//				break;
//			}
		return "console/restartOpenOffice";
	}
}
