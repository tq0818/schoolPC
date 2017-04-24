package com.yuxin.wx.controller.tiku;

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

import com.yuxin.wx.api.tiku.ITikuSectionService;
import com.yuxin.wx.model.tiku.TikuSection;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of TikuSection
 * @author wang.zx
 * @date 2015-7-8
 */
@Controller
@RequestMapping("/tikuSection")
public class TikuSectionController {
	
	@Autowired
	private ITikuSectionService tikuSectionServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, TikuSection search){
		if (search == null) {
			search = new TikuSection();
			// search.setPageSize(20);
		}
		model.addAttribute("list", tikuSectionServiceImpl.findTikuSectionByPage(search));
		return "tikuSection/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/add")
	public String add(TikuSection TikuSection) {
		TikuSection.setDelFlag(0);
		TikuSection.setCompanyId(WebUtils.getCurrentCompanyId());
		tikuSectionServiceImpl.insert(TikuSection);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(TikuSection TikuSection) {
		tikuSectionServiceImpl.update(TikuSection);
		return "success";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		tikuSectionServiceImpl.deleteTikuSectionById(id);
		return "redirect:/tikuSection";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public TikuSection getJson(Model model, @PathVariable Integer id){
		return tikuSectionServiceImpl.findTikuSectionById(id);
	}
	
	/**
	 * 
	 * Class Name: TikuSectionController.java
	 * @Description:ajax加载节的信息
	 * @author yuchanglong
	 * @date 2015年7月10日 下午2:44:42
	 * @version 1.0
	 * @param section
	 * @return
	 */
	@RequestMapping(value="/loadSection")
	public String loadSection(Model model,TikuSection section){
		section.setDelFlag(0);
		section.setCompanyId(WebUtils.getCurrentCompanyId());
		List<TikuSection> secList = tikuSectionServiceImpl.findTikuSection(section);
		model.addAttribute("secList",secList);
		return "tiku/chapterManage/sectionAjax";
	}
	
	/**
	 * 
	 * Class Name: TikuSectionController.java
	 * @Description:ajax加载节的信息
	 * @author yuchanglong
	 * @date 2015年7月10日 下午2:44:42
	 * @version 1.0
	 * @param section
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getList")
	public List<TikuSection> getList(Model model,TikuSection section){
		section.setDelFlag(0);
		section.setCompanyId(WebUtils.getCurrentCompanyId());
		List<TikuSection> secList = tikuSectionServiceImpl.findTikuSection(section);
		return secList;
	}
	
	/**
	 * 
	 * Class Name: TikuSectionController.java
	 * @Description:  检查节下面是否存在知识点
	 * @author yuchanglong
	 * @date 2015年7月10日 下午3:41:07
	 * @version 1.0
	 * @param secId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/isExistsPoint")
	public Boolean isExistsPoint(Integer secId){
		Integer count = tikuSectionServiceImpl.secCount(secId);
		if(count>0){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * Class Name: TikuSectionController.java
	 * @Description: 删除节
	 * @author yuchanglong
	 * @date 2015年7月10日 下午3:48:02
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delSection")
	public String delSection(Integer id){
		tikuSectionServiceImpl.deleteTikuSectionById(id);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/getMaxSectionOrder")
	public Integer getMaxSectionOrder(Integer chapterId){
		Integer sort = 0;
		sort = tikuSectionServiceImpl.getMaxSectionOrder(chapterId);
		return sort;
	}
	
	/**
	 * 
	 * Class Name: TikuSectionController.java
	 * @Description: 添加节并返回节的信息
	 * @author yuchanglong
	 * @date 2015年7月10日 下午3:52:47
	 * @version 1.0
	 * @param section
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addReturnSection")
	public TikuSection addReturnSection(TikuSection section){
		section.setCompanyId(WebUtils.getCurrentCompanyId());
		section.setDelFlag(0);
		tikuSectionServiceImpl.insert(section);
		return section;
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
