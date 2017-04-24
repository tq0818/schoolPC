package com.yuxin.wx.controller.tiku;

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

import com.yuxin.wx.api.tiku.ITikuPaperTopicTypeService;
import com.yuxin.wx.model.tiku.TikuPaperTopicType;

/**
 * Controller of TikuPaperTopicType
 * @author wang.zx
 * @date 2015-7-8
 */
@Controller
@RequestMapping("/tikuPaperTopicType")
public class TikuPaperTopicTypeController {
	
	@Autowired
	private ITikuPaperTopicTypeService tikuPaperTopicTypeServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, TikuPaperTopicType search){
		if (search == null) {
			search = new TikuPaperTopicType();
			// search.setPageSize(20);
		}
		model.addAttribute("list", tikuPaperTopicTypeServiceImpl.findTikuPaperTopicTypeByPage(search));
		return "tikuPaperTopicType/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(TikuPaperTopicType TikuPaperTopicType) {
		tikuPaperTopicTypeServiceImpl.insert(TikuPaperTopicType);
		return "redirect:/tikuPaperTopicType";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(TikuPaperTopicType TikuPaperTopicType) {
		tikuPaperTopicTypeServiceImpl.update(TikuPaperTopicType);
		return "redirect:/tikuPaperTopicType";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		tikuPaperTopicTypeServiceImpl.deleteTikuPaperTopicTypeById(id);
		return "redirect:/tikuPaperTopicType";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public TikuPaperTopicType getJson(Model model, @PathVariable Integer id){
		return tikuPaperTopicTypeServiceImpl.findTikuPaperTopicTypeById(id);
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
