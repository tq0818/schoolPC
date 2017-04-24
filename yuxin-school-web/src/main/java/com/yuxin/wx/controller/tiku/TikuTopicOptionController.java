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

import com.yuxin.wx.api.tiku.ITikuTopicOptionService;
import com.yuxin.wx.model.tiku.TikuTopicOption;

/**
 * Controller of TikuTopicOption
 * @author wang.zx
 * @date 2015-7-8
 */
@Controller
@RequestMapping("/tikuTopicOption")
public class TikuTopicOptionController {
	
	@Autowired
	private ITikuTopicOptionService tikuTopicOptionServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, TikuTopicOption search){
		if (search == null) {
			search = new TikuTopicOption();
			// search.setPageSize(20);
		}
		model.addAttribute("list", tikuTopicOptionServiceImpl.findTikuTopicOptionByPage(search));
		return "tikuTopicOption/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(TikuTopicOption TikuTopicOption) {
		tikuTopicOptionServiceImpl.insert(TikuTopicOption);
		return "redirect:/tikuTopicOption";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(TikuTopicOption TikuTopicOption) {
		tikuTopicOptionServiceImpl.update(TikuTopicOption);
		return "redirect:/tikuTopicOption";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		tikuTopicOptionServiceImpl.deleteTikuTopicOptionById(id);
		return "redirect:/tikuTopicOption";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public TikuTopicOption getJson(Model model, @PathVariable Integer id){
		return tikuTopicOptionServiceImpl.findTikuTopicOptionById(id);
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
