package com.yuxin.wx.controller.resource;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.yuxin.wx.api.resource.IResourceUseRecordService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.resource.ResourceUseRecord;

/**
 * Controller of ResourceUseRecord
 * @author wang.zx
 * @date 2016-9-1
 */
@Controller
@RequestMapping("/resourceUseRecord")
public class ResourceUseRecordController {
	
	@Autowired
	private IResourceUseRecordService resourceUseRecordServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ResourceUseRecord search){
		if (search == null) {
			search = new ResourceUseRecord();
			// search.setPageSize(20);
		}
		model.addAttribute("list", resourceUseRecordServiceImpl.findResourceUseRecordByPage(search));
		return "resourceUseRecord/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(ResourceUseRecord ResourceUseRecord) {
		resourceUseRecordServiceImpl.insert(ResourceUseRecord);
		return "redirect:/resourceUseRecord";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(ResourceUseRecord ResourceUseRecord) {
		resourceUseRecordServiceImpl.update(ResourceUseRecord);
		return "redirect:/resourceUseRecord";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		resourceUseRecordServiceImpl.deleteResourceUseRecordById(id);
		return "redirect:/resourceUseRecord";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public ResourceUseRecord getJson(Model model, @PathVariable Integer id){
		return resourceUseRecordServiceImpl.findResourceUseRecordById(id);
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
