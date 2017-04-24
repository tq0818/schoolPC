package com.yuxin.wx.controller.course;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.yuxin.wx.api.course.IVideoTagService;
import com.yuxin.wx.model.course.VideoTag;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of VideoTag
 * @author wang.zx
 * @date 2015-5-8
 */
@Controller
@RequestMapping("/videoTag")
public class VideoTagController {
	
	@Autowired
	private IVideoTagService videoTagServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, VideoTag search){
		if (search == null) {
			search = new VideoTag();
			// search.setPageSize(20);
		}
		model.addAttribute("list", videoTagServiceImpl.findVideoTagByPage(search));
		return "videoTag/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(VideoTag VideoTag) {
		videoTagServiceImpl.insert(VideoTag);
		return "redirect:/videoTag";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(VideoTag VideoTag) {
		videoTagServiceImpl.update(VideoTag);
		return "redirect:/videoTag";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		videoTagServiceImpl.deleteVideoTagById(id);
		return "redirect:/videoTag";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public VideoTag getJson(Model model, @PathVariable Integer id){
		return videoTagServiceImpl.findVideoTagById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public List<VideoTag> findListByCompany(Model model){
		return videoTagServiceImpl.findList(WebUtils.getCurrentCompanyId());
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
