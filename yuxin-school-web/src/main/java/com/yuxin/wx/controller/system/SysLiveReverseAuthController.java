package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
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

import com.yuxin.wx.api.system.ISysLiveReverseAuthService;
import com.yuxin.wx.model.system.SysLiveReverseAuth;

/**
 * Controller of SysLiveReverseAuth
 * @author wang.zx
 * @date 2015-11-6
 */
@Controller
@RequestMapping("/moduleLesson")
public class SysLiveReverseAuthController {
	
	Log log = LogFactory.getLog("log");
	
	@Autowired
	private ISysLiveReverseAuthService sysLiveReverseAuthServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET) 
	public String list(Model model, SysLiveReverseAuth search){
		if (search == null) {
			search = new SysLiveReverseAuth();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysLiveReverseAuthServiceImpl.findSysLiveReverseAuthByPage(search));
		return "sysLiveReverseAuth/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysLiveReverseAuth SysLiveReverseAuth) {
		sysLiveReverseAuthServiceImpl.insert(SysLiveReverseAuth);
		return "redirect:/sysLiveReverseAuth";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysLiveReverseAuth SysLiveReverseAuth) {
		sysLiveReverseAuthServiceImpl.update(SysLiveReverseAuth);
		return "redirect:/sysLiveReverseAuth";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysLiveReverseAuthServiceImpl.deleteSysLiveReverseAuthById(id);
		return "redirect:/sysLiveReverseAuth";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysLiveReverseAuth getJson(Model model, @PathVariable Integer id){
		return sysLiveReverseAuthServiceImpl.findSysLiveReverseAuthById(id);
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
	
	
	@ResponseBody
	@RequestMapping(value="/ghReverseAuth")
	public String ghReverseAuth(HttpServletRequest request, String k){
		log.info("E课堂直播回掉接口：当前的key为："+k);
		Integer authCount = 1;
		String msg = "";
		if(k != null){
			SysLiveReverseAuth auth = new SysLiveReverseAuth();
			auth.setAuthKey(k);
			List<SysLiveReverseAuth> suths = sysLiveReverseAuthServiceImpl.findSysLiveReverseAuthByPage(auth);
			if(suths != null && suths.size() > 0){
				authCount = suths.get(0).getAuthCount();
			}
		}
		if(authCount.equals(0)){
			msg = "key值有效";
			SysLiveReverseAuth auth1 = new SysLiveReverseAuth();
			auth1.setAuthCount(1);
			auth1.setAuthKey(k);
			sysLiveReverseAuthServiceImpl.update(auth1);
		}else{
			msg = "key值无效";
		}
		log.info("E课堂直播回掉接口：当前的msg为："+msg);
		return "{ \"code\":"+ authCount+",\"msg\":"+ msg+"}" ;
	}
	
}
