package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.aliyun.oss.common.comm.ServiceClient.Request;
import com.google.gson.JsonObject;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigItemTagService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemTag;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of SysConfigItemTag
 * @author chopin
 * @date 2015-9-23
 */
@Controller
@RequestMapping("/sysConfigItemTag")
public class SysConfigItemTagController {
	
	@Autowired
	private ISysConfigItemTagService sysConfigItemTagServiceImpl;
	
	/**
	 * 
	 * Class Name: SysConfigItemTagController.java
	 * @Description: 条件查询标签信息
	 * @author zhang.zx
	 * @date 2015年9月23日 下午7:42:14
	 * @modifier
	 * @modify-date 2015年9月23日 下午7:42:14
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryTags")
	public List<SysConfigItemTag> queryTagsBycondition(SysConfigItemTag search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		return sysConfigItemTagServiceImpl.queryTagsBycondition(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/addTags", method = RequestMethod.POST)
	public String addTags(SysConfigItemTag SysConfigItemTag,HttpServletRequest request) {
		//查询是否存在，不存在就添加
		SysConfigItemTag search=new SysConfigItemTag();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		search.setItemOneId(SysConfigItemTag.getItemOneId());
		search.setItemSecondId(SysConfigItemTag.getItemSecondId());
		search.setTagName(SysConfigItemTag.getTagName());
		List<SysConfigItemTag> arr=sysConfigItemTagServiceImpl.queryTagsBycondition(search);
		if(arr.size()>0){
			return "error";
		}
		SysConfigItemTag.setCompanyId(WebUtils.getCurrentCompanyId());
		SysConfigItemTag.setSchoolId(WebUtils.getCurrentSchoolId());
		SysConfigItemTag.setCreateTime(new Date());
		SysConfigItemTag.setCreator(WebUtils.getCurrentUserId(request));
		sysConfigItemTagServiceImpl.insert(SysConfigItemTag);
		return "success";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigItemTag search){
		if (search == null) {
			search = new SysConfigItemTag();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysConfigItemTagServiceImpl.findSysConfigItemTagByPage(search));
		return "sysConfigItemTag/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigItemTag SysConfigItemTag) {
		sysConfigItemTagServiceImpl.insert(SysConfigItemTag);
		return "redirect:/sysConfigItemTag";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigItemTag SysConfigItemTag) {
		sysConfigItemTagServiceImpl.update(SysConfigItemTag);
		return "redirect:/sysConfigItemTag";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysConfigItemTagServiceImpl.deleteSysConfigItemTagById(id);
		return "redirect:/sysConfigItemTag";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigItemTag getJson(Model model, @PathVariable Integer id){
		return sysConfigItemTagServiceImpl.findSysConfigItemTagById(id);
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
	 * Class Name: SysConfigItemTagController.java
	 * @Description: 查询标签
	 * @author dongshuai
	 * @date 2016年7月26日 上午10:53:49
	 * @modifier
	 * @modify-date 2016年7月26日 上午10:53:49
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryTagslist",method=RequestMethod.POST)
	public List<SysConfigItemTag> queryTagslist(SysConfigItemTag search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		List<SysConfigItemTag> tagList = sysConfigItemTagServiceImpl.queryTags(search);
		return tagList;
	}
	
	/**
	 * 
	 * Class Name: SysConfigItemTagController.java
	 * @Description: 检查标签是否存在
	 * @author dongshuai
	 * @date 2016年7月26日 上午11:17:19
	 * @modifier
	 * @modify-date 2016年7月26日 上午11:17:19
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkTag",method=RequestMethod.POST)
	public JSONObject checkTag(SysConfigItemTag search){
		JSONObject json=new JSONObject();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		int count=sysConfigItemTagServiceImpl.checkTag(search);
		if(count>0){
			json.put(JsonMsg.MSG,JsonMsg.ERROR);
			return json;
		}
		json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
		return json;
	}
	
	/**
	 * 
	 * Class Name: SysConfigItemTagController.java
	 * @Description: 保存新标签
	 * @author dongshuai
	 * @date 2016年7月26日 上午10:59:33
	 * @modifier
	 * @modify-date 2016年7月26日 上午10:59:33
	 * @version 1.0
	 * @param request
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveTag",method=RequestMethod.POST)
	public JSONObject saveTag(HttpServletRequest request,SysConfigItemTag search){
		JSONObject json=new JSONObject();
		JSONObject checkJson= checkTag(search);
		if("success".equals(checkJson.get("msg"))){
			search.setCompanyId(WebUtils.getCurrentCompanyId());
			search.setSchoolId(WebUtils.getCurrentSchoolId());
			search.setCreateTime(new Date());
			search.setCreator(WebUtils.getCurrentUserId(request));
			sysConfigItemTagServiceImpl.insert(search);
		}else{
			json.put(JsonMsg.MSG, JsonMsg.ERROR);
			json.put(JsonMsg.RESULT, "标签已存在！");
			return json;
		}
		json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
		return json;
	}
	
	/**
	 * 
	 * Class Name: SysConfigItemTagController.java
	 * @Description: 修改标签
	 * @author dongshuai
	 * @date 2016年7月26日 下午1:50:31
	 * @modifier
	 * @modify-date 2016年7月26日 下午1:50:31
	 * @version 1.0
	 * @param request
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editTag/{old}",method=RequestMethod.POST)
	public JSONObject editTag(HttpServletRequest request,SysConfigItemTag search,@PathVariable String old){
		JSONObject json=new JSONObject();
		if(null!=old && !"".equals(old) && null != search.getTagName() && !"".equals(search.getTagName())){
			if(old.equals(search.getTagName())){
				
			}else{
				JSONObject checkJson= checkTag(search);
				if("success".equals(checkJson.get("msg"))){
					SysConfigItemTag scit = sysConfigItemTagServiceImpl.findSysConfigItemTagById(search.getId());
					scit.setTagName(search.getTagName());
					scit.setUpdateTime(new Date());
					scit.setUpdator(WebUtils.getCurrentUserId(request));
					sysConfigItemTagServiceImpl.update(scit);
				}else{
					json.put(JsonMsg.MSG, JsonMsg.ERROR);
					json.put(JsonMsg.RESULT, "标签已存在！");
					return json;
				}
			}
		}
		json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
		return json;
	}
	
	/**
	 * 
	 * Class Name: SysConfigItemTagController.java
	 * @Description: 删除标签
	 * @author dongshuai
	 * @date 2016年7月26日 下午2:14:36
	 * @modifier
	 * @modify-date 2016年7月26日 下午2:14:36
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delTag/{tagId}",method=RequestMethod.POST)
	public JSONObject delTag(@PathVariable String tagId){
		JSONObject json=new JSONObject();
		sysConfigItemTagServiceImpl.deleteSysConfigItemTagById(Integer.parseInt(tagId));
		json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
		return json;
	}
	
	/**
	 * 
	 * Class Name: SysConfigItemTagController.java
	 * @Description: 查询学科小类
	 * @author dongshuai
	 * @date 2016年7月26日 下午2:39:06
	 * @modifier
	 * @modify-date 2016年7月26日 下午2:39:06
	 * @version 1.0
	 * @param itemOneId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryItemSecondIdList/{itemOneId}",method=RequestMethod.POST)
	public List<SysConfigItem> queryItemSecondIdList(@PathVariable String itemOneId){
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("companyId", WebUtils.getCurrentCompanyId());
		map.put("itemOneId", Integer.parseInt(itemOneId));
		List<SysConfigItem> sciList=sysConfigItemTagServiceImpl.queryItemSecondIdList(map);
		return sciList;
	}
}
