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

import com.yuxin.wx.api.tiku.ITikuPaperTopicService;
import com.yuxin.wx.model.tiku.TikuPaperTopic;

/**
 * Controller of TikuPaperTopic
 * @author wang.zx
 * @date 2015-7-8
 */
@Controller
@RequestMapping("/tikuPaperTopic")
public class TikuPaperTopicController {
	
	@Autowired
	private ITikuPaperTopicService tikuPaperTopicServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, TikuPaperTopic search){
		if (search == null) {
			search = new TikuPaperTopic();
			// search.setPageSize(20);
		}
		model.addAttribute("list", tikuPaperTopicServiceImpl.findTikuPaperTopicByPage(search));
		return "tikuPaperTopic/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(TikuPaperTopic TikuPaperTopic) {
		tikuPaperTopicServiceImpl.insert(TikuPaperTopic);
		return "redirect:/tikuPaperTopic";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(TikuPaperTopic TikuPaperTopic) {
		tikuPaperTopicServiceImpl.update(TikuPaperTopic);
		return "redirect:/tikuPaperTopic";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		tikuPaperTopicServiceImpl.deleteTikuPaperTopicById(id);
		return "redirect:/tikuPaperTopic";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public TikuPaperTopic getJson(Model model, @PathVariable Integer id){
		return tikuPaperTopicServiceImpl.findTikuPaperTopicById(id);
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
