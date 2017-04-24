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

import com.yuxin.wx.api.tiku.ITikuCategoryService;
import com.yuxin.wx.api.tiku.ITikuChapterService;
import com.yuxin.wx.api.tiku.ITikuSetService;
import com.yuxin.wx.api.tiku.ITikuSubjectService;
import com.yuxin.wx.model.tiku.TikuChapter;
import com.yuxin.wx.model.tiku.TikuSet;
import com.yuxin.wx.model.tiku.TikuSubject;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of TikuChapter
 * @author wang.zx
 * @date 2015-7-8
 */
@Controller
@RequestMapping("/tikuChapter")
public class TikuChapterController {
	
	@Autowired
	private ITikuSetService tikuSetServiceImpl;
	
	@Autowired
	private ITikuChapterService tikuChapterServiceImpl;
	
	@Autowired
	private ITikuSubjectService subServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, TikuChapter search){
		if (search == null) {
			search = new TikuChapter();
			// search.setPageSize(20);
		}
		model.addAttribute("list", tikuChapterServiceImpl.findTikuChapterByPage(search));
		return "tikuChapter/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(TikuChapter TikuChapter) {
		TikuChapter.setDelFlag("0");
		TikuChapter.setCompanyId(WebUtils.getCurrentCompanyId());
		tikuChapterServiceImpl.insert(TikuChapter);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TikuChapter TikuChapter) {
		tikuChapterServiceImpl.update(TikuChapter);
		return "success";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		tikuChapterServiceImpl.deleteTikuChapterById(id);
		return "redirect:/tikuChapter";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public TikuChapter getJson(Model model, @PathVariable Integer id){
		return tikuChapterServiceImpl.findTikuChapterById(id);
	}
	
	/**
	 * 
	 * Class Name: TikuChapterController.java
	 * @Description: 链接章节考点管理页面
	 * @author yuchanglong
	 * @date 2015年7月10日 下午12:00:04
	 * @version 1.0
	 * @param model
	 * @param tikuId
	 * @return
	 */
	@RequestMapping(value="/toChapter/{tikuId}")
	public String toChapter(Model model,@PathVariable Integer tikuId){
		Integer companyId = WebUtils.getCurrentCompanyId();
		TikuSet tikuSet = new TikuSet();
		tikuSet.setCompanyId(companyId);
		tikuSet = tikuSetServiceImpl.findSetByCompanyIdAndCategoryId(tikuSet);
		
		TikuSubject search = new TikuSubject();
		search.setDelFlag(0);
		search.setCategoryId(tikuId);
		List<TikuSubject> subList = subServiceImpl.findTikuSubject(search);

		model.addAttribute("tikuSet", tikuSet);
		model.addAttribute("subList",subList);
		model.addAttribute("tikuId",tikuId);
		return "tiku/chapterManage/tikuChapter";
	}
	/**
	 * 
	 * Class Name: TikuChapterController.java
	 * @Description: ajax加载章的信息
	 * @author yuchanglong
	 * @date 2015年7月10日 下午12:26:16
	 * @version 1.0
	 * @param model
	 * @param chapter
	 * @return
	 */
	@RequestMapping(value="/loadChapter")
	public String loadChapter(Model model,TikuChapter chapter){
		chapter.setDelFlag("0");
		chapter.setCompanyId(WebUtils.getCurrentCompanyId());
		if(chapter.getTikuSubjectId()==null){
			chapter.setTikuSubjectId(-1);
		}
		List<TikuChapter> chapterList = tikuChapterServiceImpl.findTikuChapter(chapter);
		model.addAttribute("chapterList",chapterList);
		return "tiku/chapterManage/chapterAjax";
	}
	
	/**
	 * 
	 * Class Name: TikuChapterController.java
	 * @Description: 根据科目查询章顺序最大值
	 * @author yuchanglong
	 * @date 2015年7月14日 下午3:37:11
	 * @version 1.0
	 * @param subId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getMaxChapterOrder")
	public Integer getMaxChapterOrder(Integer subId){
		Integer max = 0;
		max = tikuChapterServiceImpl.findMaxChapterOrder(subId);
		return max;
	}
	
	@ResponseBody
	@RequestMapping(value="/isExistsSection")
	public Boolean isExistsSection(Integer chapterId){
		Integer count = tikuChapterServiceImpl.selectSecByChapter(chapterId);
		if(count>0){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * Class Name: TikuChapterController.java
	 * @Description: 删除章
	 * @author yuchanglong
	 * @date 2015年7月10日 下午2:19:40
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delChapter")
	public String delChapter(Integer id){
		tikuChapterServiceImpl.deleteTikuChapterById(id);
		return "success";
	}
	
	/**
	 * 
	 * Class Name: TikuChapterController.java
	 * @Description: 添加章并返回章的信息
	 * @author yuchanglong
	 * @date 2015年7月10日 下午2:28:36
	 * @version 1.0
	 * @param chapter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addReturnChapter")
	public TikuChapter addReturnChapter(TikuChapter chapter){
		chapter.setDelFlag("0");
		chapter.setCompanyId(WebUtils.getCurrentCompanyId());
		
		tikuChapterServiceImpl.insert(chapter);
		return chapter;
	}
	/**
	 * 
	 * Class Name: TikuChapterController.java
	 * @Description: 查询题库列表
	 * @author yuchanglong
	 * @date 2015年7月10日 下午2:28:36
	 * @version 1.0
	 * @param chapter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getList")
	public List<TikuChapter> getlist(TikuChapter chapter){
		chapter.setDelFlag("0");
		chapter.setCompanyId(WebUtils.getCurrentCompanyId());
		
		List<TikuChapter> result=tikuChapterServiceImpl.findTikuChapter(chapter);
		return result;
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
