package com.yuxin.wx.controller.tiku;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.tiku.ITikuConfigService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.tiku.TikuConfig;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of TikuConfig
 * @author yuchanglong
 * @date 2016-3-2
 */
@Controller
@RequestMapping("/tikuConfig")
public class TikuConfigController {
	
	private Log log =LogFactory.getLog("log");
	
	@Autowired
	private ITikuConfigService tikuConfigServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, TikuConfig search){
		if (search == null) {
			search = new TikuConfig();
			// search.setPageSize(20);
		}
		model.addAttribute("list", tikuConfigServiceImpl.findTikuConfigByPage(search));
		return "tikuConfig/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(TikuConfig TikuConfig) {
		tikuConfigServiceImpl.insert(TikuConfig);
		return "redirect:/tikuConfig";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(TikuConfig TikuConfig) {
		tikuConfigServiceImpl.update(TikuConfig);
		return "redirect:/tikuConfig";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		tikuConfigServiceImpl.deleteTikuConfigById(id);
		return "redirect:/tikuConfig";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public TikuConfig getJson(Model model, @PathVariable Integer id){
		return tikuConfigServiceImpl.findTikuConfigById(id);
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
	 * Class Name: TikuConfigController.java
	 * @Description: 更改设置状态
	 * @author 周文斌
	 * @date 2016-3-2 下午7:18:06
	 * @version 1.0
	 * @param request
	 * @param val
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upStatus")
	public JSONObject upStatus(HttpServletRequest request,Integer val,String code){
		JSONObject json = new JSONObject();
		Integer companyId = WebUtils.getCurrentCompanyId();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", companyId);
		param.put("configValue", code);
		try {
			log.info("查询是否存在");
			TikuConfig tc = tikuConfigServiceImpl.findByCodeValue(param);
			if(tc != null){
				tc.setDelFlag(val);
				tikuConfigServiceImpl.update(tc);
			}else{
				tc = new TikuConfig();
				tc.setCompanyId(companyId);
				tc.setConfigType("TIKU_OBTAIN_TOPIC_RANGE");
				tc.setConfigValue(code);
				tc.setDelFlag(val);
				tikuConfigServiceImpl.insert(tc);
			}
			
			if(code.equals("TOPIC_OF_ALL")){
				if(val.equals(0)){
					param.clear();
					param.put("companyId", companyId);
					param.put("configType", "TIKU_OBTAIN_TOPIC_RANGE");
					List<TikuConfig> tclist = tikuConfigServiceImpl.findList(param);
					for (TikuConfig t : tclist) {
						if(t.getConfigValue().equals(code)){
							continue;
						}
						t.setDelFlag(1);
						tikuConfigServiceImpl.update(t);
					}
				}else{
					param.clear();
					param.put("companyId", companyId);
					param.put("configValue", "TOPIC_OF_ALL_NOT_PAPER");
					TikuConfig notp = tikuConfigServiceImpl.findByCodeValue(param);
					notp.setDelFlag(0);
					tikuConfigServiceImpl.update(notp);
				}
			}
			
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
			return json;
		} catch (Exception e) {
			log.error("更改设置出错：" + e.getMessage(),e);
			e.printStackTrace();
			json.put(JsonMsg.MSG, "更改设置时出错");
			return json;
		}
	}
}
