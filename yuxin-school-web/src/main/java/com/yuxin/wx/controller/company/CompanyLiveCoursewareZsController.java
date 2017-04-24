package com.yuxin.wx.controller.company;

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
import com.yuxin.wx.api.company.ICompanyLiveCoursewareZsService;
import com.yuxin.wx.api.company.ICompanyLiveIntercutZsService;
import com.yuxin.wx.api.company.ICompanyLiveRecordZsService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.LiveRoomConstant;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.company.CompanyLiveCoursewareZs;
import com.yuxin.wx.model.company.CompanyLiveIntercutZs;
import com.yuxin.wx.model.company.CompanyLiveRecordZs;
import com.yuxin.wx.util.HttpPostRequest;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyLiveCoursewareZs
 * @author wang.zx
 * @date 2015-11-28
 */
@Controller
@RequestMapping("/companyLiveCoursewareZs")
public class CompanyLiveCoursewareZsController {
	
	private Log log = LogFactory.getLog("log");
	
	@Autowired
	private ICompanyLiveIntercutZsService companyLiveIntercutZsServiceImpl;
	
	@Autowired
	private ICompanyLiveRecordZsService companyLiveRecordZsServiceImpl;
	
	@Autowired
	private ICompanyLiveCoursewareZsService companyLiveCoursewareZsServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyLiveCoursewareZs search){
		if (search == null) {
			search = new CompanyLiveCoursewareZs();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyLiveCoursewareZsServiceImpl.findCompanyLiveCoursewareZsByPage(search));
		return "companyLiveCoursewareZs/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyLiveCoursewareZs CompanyLiveCoursewareZs) {
		companyLiveCoursewareZsServiceImpl.insert(CompanyLiveCoursewareZs);
		return "redirect:/companyLiveCoursewareZs";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyLiveCoursewareZs CompanyLiveCoursewareZs) {
		companyLiveCoursewareZsServiceImpl.update(CompanyLiveCoursewareZs);
		return "redirect:/companyLiveCoursewareZs";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyLiveCoursewareZsServiceImpl.deleteCompanyLiveCoursewareZsById(id);
		return "redirect:/companyLiveCoursewareZs";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyLiveCoursewareZs getJson(Model model, @PathVariable Integer id){
		return companyLiveCoursewareZsServiceImpl.findCompanyLiveCoursewareZsById(id);
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
	 * Class Name: CompanyLiveCoursewareZsController.java
	 * @Description: 查询插拨件
	 * @author 周文斌
	 * @date 2015-12-4 下午2:44:47
	 * @version 1.0
	 * @param model
	 * @param request
	 * @param zs
	 * @return
	 */
	@RequestMapping("/selCourse")
	public String selCourse(Model model,HttpServletRequest request,CompanyLiveRecordZs lrzs){
		
		lrzs.setCompanyId(WebUtils.getCurrentCompanyId());
		lrzs.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
		try {
			log.info("course：查询插拨件");
			log.info("course：条件");
			log.info("course："+lrzs);
			List<CompanyLiveRecordZs> zsList = companyLiveRecordZsServiceImpl.findRecord(lrzs);
			log.info("course：查询list"+zsList);
			log.info("course：总数");
			Integer count = companyLiveRecordZsServiceImpl.findCountRecord(lrzs);
			log.info("course："+count);
			
			log.info("course：创建pageFinder");
			PageFinder<CompanyLiveRecordZs> zsPage = new PageFinder<CompanyLiveRecordZs>(lrzs.getPage(), lrzs.getPageSize(), count, zsList);
			
			model.addAttribute("zsPage", zsPage);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("course：粗错了："+e.getMessage());
		}
		return "operate/live/choose";
	}
	
	/**
	 * 
	 * Class Name: CompanyLiveCoursewareZsController.java
	 * @Description: 添加插拨件
	 * @author 周文斌
	 * @date 2015-12-4 下午5:40:05
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addcha")
	public JSONObject addcha(HttpServletRequest request,String names,String id,Integer lessonId){
		JSONObject json = new JSONObject();
		if(names.indexOf(",") > 0){
			String[] n = names.split(",");
			for (String s : n) {
					json = addChas(request,id,s,lessonId);
					if(!json.getString("msg").equals("success")){
						break;
					}
				}
		}else{
			json = addChas(request,id,names,lessonId);
		}
		return json;
	}
	
	/**
	 * 
	 * Class Name: CompanyLiveCoursewareZsController.java
	 * @Description: 添加插拨件
	 * @author 周文斌
	 * @date 2015-12-11 下午6:54:54
	 * @version 1.0
	 * @param id
	 * @param names
	 * @return
	 */
	private JSONObject addChas(HttpServletRequest request,String id,String names,Integer lessonId){
		JSONObject jsons = new JSONObject();
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		
		try {
			log.info("choose：添加插拨件");
			String url = LiveRoomConstant.DOMIN_NAME + LiveRoomConstant.CHOOSE_ADD;
			log.info("choose：接口地址："+url);
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("roomId", id);
			param.put("recordId", names);
			param.put("loginName", LiveRoomConstant.LOGIN_NAME);
			param.put("password", LiveRoomConstant.PASSWORD);
			log.info("choose：参数"+param);
			
			String res = HttpPostRequest.post(url, param);
			log.info("choose：返回结果"+res);
			Integer code = (JSONObject.parseObject(res)).getInteger("code");
			if(code.equals(0)){
				log.info("choose：保存插播数据");
				String name = companyLiveRecordZsServiceImpl.findNameByRecordId(names);
				log.info("choose：根据recordId:" + names+ ", 查询name:" + name);
				
				CompanyLiveIntercutZs interzs = new CompanyLiveIntercutZs(Integer.parseInt(names),name,lessonId,1,companyId,schoolId);
				log.info("choose：查询是否重复");
				List<CompanyLiveIntercutZs> itzs = companyLiveIntercutZsServiceImpl.findExist(interzs);
				if(itzs == null || itzs.size() == 0){
					companyLiveIntercutZsServiceImpl.insert(interzs);
				}
				jsons.put(JsonMsg.MSG, JsonMsg.SUCCESS);
			}else{
				jsons.put(JsonMsg.MSG, JsonMsg.INFORMATION);
			}
			return jsons;
		} catch (Exception e) {
			e.printStackTrace();
			jsons.put(JsonMsg.MSG, JsonMsg.INFORMATION);
			return jsons;
		}
	}
	
	/**
	 * 
	 * Class Name: CompanyLiveCoursewareZsController.java
	 * @Description: 查询已存在插播
	 * @author 周文斌
	 * @date 2015-12-11 下午7:30:18
	 * @version 1.0
	 * @param model
	 * @param request
	 * @param lrzs
	 * @return
	 */
	@RequestMapping("/selIntercut")
	public String selIntercut(Model model,HttpServletRequest request,CompanyLiveIntercutZs lrzs){
		lrzs.setCompanyId(WebUtils.getCurrentCompanyId());
		lrzs.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
		lrzs.setRecordType(1);
		try {
			log.info("course：查询插拨件");
			log.info("course：条件");
			log.info("course："+lrzs);
			List<CompanyLiveIntercutZs> zsList = companyLiveIntercutZsServiceImpl.findAllByCont(lrzs);
			log.info("course：查询list"+zsList);
			log.info("course：总数");
			Integer count = companyLiveIntercutZsServiceImpl.findCountAllByCont(lrzs);
			log.info("course："+count);
			
			log.info("course：创建pageFinder");
			PageFinder<CompanyLiveIntercutZs> zsPage = new PageFinder<CompanyLiveIntercutZs>(lrzs.getPage(), lrzs.getPageSize(), count, zsList);
			
			model.addAttribute("zsPage", zsPage);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("course：粗错了："+e.getMessage());
		}
		
		return "operate/live/interzs";
	}
	
	/**
	 * 
	 * Class Name: CompanyLiveCoursewareZsController.java
	 * @Description: 取消插拨件
	 * @author 周文斌
	 * @date 2015-12-11 下午8:40:33
	 * @version 1.0
	 * @param request
	 * @param id
	 * @param names
	 * @param inid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delcha")
	public JSONObject delcha(HttpServletRequest request,String id,String names,Integer lessonId){
		JSONObject json = new JSONObject();
		if(names.indexOf(",") > 0){
			String[] n = names.split(",");
			for (String s : n) {
					json = delChas(request,id,s,lessonId);
					if(!json.getString("msg").equals("success")){
						break;
					}
				}
		}else{
			json = delChas(request,id,names,lessonId);
		}
		return json;
	}
	
	/**
	 * 
	 * Class Name: CompanyLiveCoursewareZsController.java
	 * @Description: 取消插拨件
	 * @author 周文斌
	 * @date 2015-12-11 下午6:54:54
	 * @version 1.0
	 * @param id
	 * @param names
	 * @return
	 */
	private JSONObject delChas(HttpServletRequest request,String id,String names,Integer lessonId){
		JSONObject jsons = new JSONObject();
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		
		try {
			log.info("choose：取消插拨件");
			String url = LiveRoomConstant.DOMIN_NAME + LiveRoomConstant.RECORD_REMOVE;
			log.info("choose：接口地址："+url);
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("roomId", id);
			param.put("recordId", names);
			param.put("loginName", LiveRoomConstant.LOGIN_NAME);
			param.put("password", LiveRoomConstant.PASSWORD);
			log.info("choose：参数"+param);
			
			String res = HttpPostRequest.post(url, param);
			log.info("choose：返回结果"+res);
			Integer code = (JSONObject.parseObject(res)).getInteger("code");
			if(code.equals(0)){
				log.info("choose：删除插播数据");
				
				CompanyLiveIntercutZs interzs = new CompanyLiveIntercutZs();
				interzs.setClassModuleLessionId(lessonId);
				interzs.setSchoolId(schoolId);
				interzs.setCompanyId(companyId);
				interzs.setRecordId(Integer.parseInt(names));
				log.info("choose：参数:"+interzs);
				companyLiveIntercutZsServiceImpl.delByCont(interzs);
				jsons.put(JsonMsg.MSG, JsonMsg.SUCCESS);
			}else{
				jsons.put(JsonMsg.MSG, JsonMsg.INFORMATION);
			}
			return jsons;
		} catch (Exception e) {
			e.printStackTrace();
			jsons.put(JsonMsg.MSG, JsonMsg.INFORMATION);
			return jsons;
		}
	}
	
	public static void main(String[] args){
		String url = LiveRoomConstant.DOMIN_NAME + LiveRoomConstant.CHOOSE_ADD;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roomId", "xNN2UqmpYY");
		param.put("recordId", 1656282);
		param.put("loginName", LiveRoomConstant.LOGIN_NAME);
		param.put("password", LiveRoomConstant.PASSWORD);
		
		try {
			String res = HttpPostRequest.post(url, param);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
