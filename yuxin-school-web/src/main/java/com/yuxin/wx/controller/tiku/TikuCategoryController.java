package com.yuxin.wx.controller.tiku;

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

import com.yuxin.wx.api.system.ISysConfigItemIconService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.tiku.ITikuCategoryService;
import com.yuxin.wx.api.tiku.ITikuSetService;
import com.yuxin.wx.api.tiku.ITikuSubjectService;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigItemIcon;
import com.yuxin.wx.model.tiku.TikuCategory;
import com.yuxin.wx.model.tiku.TikuSet;
import com.yuxin.wx.model.tiku.TikuSubject;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.tiku.TikuCategoryVo;

/**
 * Controller of TikuCategory
 * @author wang.zx
 * @date 2015-7-8
 */
@Controller
@RequestMapping("/tikuCategory")
public class TikuCategoryController {
	
	@Autowired
	private ITikuCategoryService tikuCategoryServiceImpl;
	
	@Autowired
	private ITikuSubjectService tikuSubjectServiceImpl;
	
	@Autowired
	private ISysConfigItemService itemService;
	
	@Autowired
	private ISysConfigItemIconService itemIconService;
	
	@Autowired
	private ITikuSetService tikuSetServiceImpl;
	
	@Autowired
	private PropertiesUtil properties;
	
	@Autowired
	ISysConfigItemIconService sysConfigItemIconServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, TikuCategory search){
		if (search == null) {
			search = new TikuCategory();
			// search.setPageSize(20);
		}
		model.addAttribute("list", tikuCategoryServiceImpl.findTikuCategoryByPage(search));
		return "tikuCategory/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(TikuCategory TikuCategory) {
		tikuCategoryServiceImpl.insert(TikuCategory);
		return "redirect:/tikuCategory";
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TikuCategory TikuCategory) {
		tikuCategoryServiceImpl.update(TikuCategory);
		return "success";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		tikuCategoryServiceImpl.deleteTikuCategoryById(id);
		return "redirect:/tikuCategory";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public TikuCategory getJson(Model model, @PathVariable Integer id){
		return tikuCategoryServiceImpl.findTikuCategoryById(id);
	}
	
	/**
	 * 
	 * Class Name: TikuCategoryController.java
	 * @Description: 题库首页
	 * @author yuchanglong
	 * @date 2015年7月9日 下午3:02:22
	 * @version 1.0
	 * @param model
	 * @param category
	 * @return
	 */
	@RequestMapping(value="/toTiku")
	public String toTiku(Model model){
		
		List<SysConfigItem> itemOneList = itemService.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
		model.addAttribute("itemOneList",itemOneList);
		model.addAttribute("ImagePath", properties.getProjectImageUrl());
		model.addAllAttributes(selIcon(1, 8));
		
		return "tiku/tikuIndex";
	}
	
	/**
	 * 
	 * Class Name: TikuCategoryController.java
	 * @Description: ajax加载题库信息
	 * @author yuchanglong
	 * @date 2015年7月16日 下午3:28:47
	 * @version 1.0
	 * @param model
	 * @param category
	 * @return
	 */
	@RequestMapping(value="/loadTikuAjax")
	public String loadTikuAjax(Model model,TikuCategory category){
		Integer companyId = WebUtils.getCurrentCompanyId();
		if(companyId!=null){
			category.setCompanyId(companyId);
		}
		List<TikuCategoryVo> list = tikuCategoryServiceImpl.findTikuCategoryVo(category);
		model.addAttribute("ImagePath", properties.getProjectImageUrl());
		model.addAttribute("list",list);
		return "tiku/tikuAjax";
	}
	
	/**
	 * 
	 * Class Name: TikuCategoryController.java
	 * @Description: 添加题库
	 * @author yuchanglong
	 * @date 2015年7月9日 下午5:46:14
	 * @version 1.0
	 * @param category
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addTiku")
	public String addTiku(TikuCategory category,HttpServletRequest request){
		category.setCompanyId(WebUtils.getCurrentCompanyId());
		String picIdStr = category.getIconBackUrl();
		if(picIdStr!=null){
			Integer picId = Integer.parseInt(picIdStr);
			SysConfigItemIcon configItemIcon = itemIconService.findUrlById(picId);
			category.setIconBackUrl(configItemIcon.getIconUrl());
		}
		String subNames = request.getParameter("subjectNameArray");
		String [] subNameArray = subNames.split(",");
		tikuCategoryServiceImpl.insertTikuAndSub(category, subNameArray);
		return "success";
	}
	/**
	 * 
	 * Class Name: TikuCategoryController.java
	 * @Description: 检查题库名字是否重复
	 * @author yuchanglong
	 * @date 2015年9月24日 下午7:15:37
	 * @version 1.0
	 * @param category
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkTikuName")
	public Boolean checkTikuName(TikuCategory category){
		Integer comId = WebUtils.getCurrentCompanyId();
		category.setCompanyId(comId);
		Integer tikuCount = tikuCategoryServiceImpl.findTikuByComIdAndTName(category);
		if(tikuCount == 0){
			return true;
		}
		return false;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryItemSecond")
	public List<SysConfigItem> queryItemSecond(Integer pid){
		
		return itemService.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_SECOND, pid, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
	}
	
	
	@RequestMapping(value="/editTikuInfo")
	public String editTikuInfo(Integer tikuId,Model model){
		TikuCategory category = tikuCategoryServiceImpl.findTikuCategoryById(tikuId);
		model.addAttribute("tiku",category);
		List<TikuSubject> subjects = tikuSubjectServiceImpl.findSubByCategoryId(tikuId);
		List<SysConfigItem> itemOneList = itemService.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
		model.addAttribute("itemOneList",itemOneList);
		if(subjects!=null){
			model.addAttribute("subjects",subjects);
		}
		model.addAttribute("ImagePath", properties.getProjectImageUrl());
		model.addAllAttributes(selIcon(1, 8));
		return "tiku/editTikuAjax";
	}
	
	@ResponseBody
	@RequestMapping(value="/getTikiName")
	public String getTikiName(Integer id){
		TikuCategory category = tikuCategoryServiceImpl.findTikuCategoryById(id);
		if(category!=null){
			return category.getTikuName();
		}
		return null;
	}
	
	
	private Map<String, Object> selIcon(Integer page, Integer pageSize){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", ((page - 1) * pageSize));
		param.put("pageSize", pageSize);
		//分页查询
		List<SysConfigItemIcon> iconList = sysConfigItemIconServiceImpl.findByPage(param);
		//查询总数
		Integer count = sysConfigItemIconServiceImpl.findByPageCount(param);
		// 总页数
		Integer pagecount = (count % pageSize) == 0 ? (count / pageSize)
				: ((count / pageSize) + 1);

		param.put("nowpage", page);
		param.put("pagecount", pagecount);
		param.put("iconList", iconList);
		return param;
	}
	
	@ResponseBody
	@RequestMapping(value="/delById")
	public String delById(Integer id){
		tikuCategoryServiceImpl.delAllById(id);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/isExTop")
	public Boolean isExTop(Integer id){
		Integer count = tikuCategoryServiceImpl.findTopCountById(id);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getList")
	public List<TikuCategoryVo> getList(){
		TikuCategory search =new TikuCategory();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setDelFlag(0);
		List<TikuCategoryVo> result= tikuCategoryServiceImpl.findTikuCategoryVo(search);
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
