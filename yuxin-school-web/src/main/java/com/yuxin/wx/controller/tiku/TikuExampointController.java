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

import com.yuxin.wx.api.tiku.ITikuExampointService;
import com.yuxin.wx.model.tiku.TikuChapter;
import com.yuxin.wx.model.tiku.TikuExampoint;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of TikuExampoint
 * @author wang.zx
 * @date 2015-7-8
 */
@Controller
@RequestMapping("/tikuExampoint")
public class TikuExampointController {
	
	@Autowired
	private ITikuExampointService tikuExampointServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, TikuExampoint search){
		if (search == null) {
			search = new TikuExampoint();
			// search.setPageSize(20);
		}
		model.addAttribute("list", tikuExampointServiceImpl.findTikuExampointByPage(search));
		return "tikuExampoint/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/add")
	public String add(TikuExampoint TikuExampoint) {
		TikuExampoint.setDelFlag(0);
		TikuExampoint.setCompanyId(WebUtils.getCurrentCompanyId());
		tikuExampointServiceImpl.insert(TikuExampoint);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TikuExampoint TikuExampoint) {
		tikuExampointServiceImpl.update(TikuExampoint);
		return "success";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		tikuExampointServiceImpl.deleteTikuExampointById(id);
		return "redirect:/tikuExampoint";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public TikuExampoint getJson(Model model, @PathVariable Integer id){
		return tikuExampointServiceImpl.findTikuExampointById(id);
	}
	/**
	 * 
	 * Class Name: TikuExampointController.java
	 * @Description: 加载知识点信息
	 * @author yuchanglong
	 * @date 2015年7月13日 上午10:27:45
	 * @version 1.0
	 * @param exampoint
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loadExamPoint")
	public String loadExamPoint(TikuExampoint exampoint,Model model){
		exampoint.setCompanyId(WebUtils.getCurrentCompanyId());
		exampoint.setDelFlag(0);
		List<TikuExampoint> pointList = tikuExampointServiceImpl.findTikuExampoint(exampoint);
		model.addAttribute("pointList",pointList);
		return "tiku/chapterManage/pointAjax";
	}
	
	/**
	 * 
	 * Class Name: TikuExampointController.java
	 * @Description: 检查该知识点下是否存在子知识点
	 * @author yuchanglong
	 * @date 2015年7月13日 下午12:54:35
	 * @version 1.0
	 * @param pointId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/isExixtsChildrenPoint")
	public Boolean isExixtsChildrenPoint(Integer pointId){
		Integer count = tikuExampointServiceImpl.getCPointCount(pointId);
		if(count>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * Class Name: TikuExampointController.java
	 * @Description: 删除知识点
	 * @author yuchanglong
	 * @date 2015年7月13日 下午12:52:13
	 * @version 1.0
	 * @param pointId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delExamPoint")
	public String delExamPoint(Integer pointId){
		tikuExampointServiceImpl.delByChecked(pointId);
		return "success";
	}
	/**
	 * 
	 * Class Name: TikuExampointController.java
	 * @Description: 添加知识点并返回知识点的信息
	 * @author yuchanglong
	 * @date 2015年7月13日 下午2:44:59
	 * @version 1.0
	 * @param exampoint
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addReturnPoint")
	public TikuExampoint addReturnPoint(TikuExampoint exampoint){
		exampoint.setDelFlag(0);
		exampoint.setCompanyId(WebUtils.getCurrentCompanyId());
		tikuExampointServiceImpl.insert(exampoint);
		return exampoint;
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
