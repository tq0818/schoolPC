package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.yuxin.wx.api.system.ISysConfigDivisionService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.system.SysConfigDivision;
import com.yuxin.wx.util.JedisUtil;
import com.yuxin.wx.vo.company.ProvinceVo;

/**
 * Controller of SysConfigDivision
 * @author wang.zx
 * @date 2016-7-14
 */
@Controller
@RequestMapping("/sysConfigDivision")
public class SysConfigDivisionController {
	
	private Log log = LogFactory.getLog("log");
	
	@Autowired
	private ISysConfigDivisionService sysConfigDivisionServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigDivision search){
		if (search == null) {
			search = new SysConfigDivision();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysConfigDivisionServiceImpl.findSysConfigDivisionByPage(search));
		return "sysConfigDivision/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigDivision SysConfigDivision) {
		sysConfigDivisionServiceImpl.insert(SysConfigDivision);
		return "redirect:/sysConfigDivision";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigDivision SysConfigDivision) {
		sysConfigDivisionServiceImpl.update(SysConfigDivision);
		return "redirect:/sysConfigDivision";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigDivisionServiceImpl.deleteSysConfigDivisionById(id);
		return "redirect:/sysConfigDivision";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigDivision getJson(Model model, @PathVariable Integer id){
		return sysConfigDivisionServiceImpl.findSysConfigDivisionById(id);
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
	 * Class Name: SysConfigDivisionController.java
	 * @Description: 分校使用地址
	 * @author 周文斌
	 * @date 2016-7-15 上午10:20:32
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCitys")
	public JSONObject getCitys(HttpServletRequest request){
		JSONObject json = new JSONObject();
		
		try {
			List<ProvinceVo> plist = JedisUtil
					.getList("yuxin_address_table_cache");
			if(plist != null && plist.size() > 0){
				json.put("citylist", plist);
			}else{
				plist = sysConfigDivisionServiceImpl
						.queryAlls();
				JedisUtil.put("yuxin_address_table_cache", plist);
				json.put("citylist", plist);
			}
		} catch (Exception e) {
			log.error("查询城市 错误," + e.getMessage(),e);
			e.printStackTrace();
		}
		
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/getProvince",method=RequestMethod.POST)
	public JSONObject getProvince(){
		JSONObject json = new JSONObject();
		List<SysConfigDivision> list = sysConfigDivisionServiceImpl.firstLiandong();
		json.put("provinces", list);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/getCityByPid",method=RequestMethod.POST)
	public JSONObject getProvince(HttpServletRequest reqeust,String pid){
		JSONObject json = new JSONObject();
		List<SysConfigDivision> list2 = sysConfigDivisionServiceImpl.secLiandong(Integer.parseInt(pid));
		json.put("citys", list2);
		return json;
	}
}
