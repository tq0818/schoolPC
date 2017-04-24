package com.yuxin.wx.controller.commodity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.commodity.CommodityVo;

/**
 * Controller of Commodity
 * @author wang.zx
 * @date 2015-3-14
 */
@Controller
@RequestMapping("/commodity")
public class CommodityController {
	
	@Autowired
	private ICommodityService commodityServiceImpl;
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Commodity search){
		if (search == null) {
			search = new Commodity();
			// search.setPageSize(20);
		}
		model.addAttribute("list", commodityServiceImpl.findCommodityByPage(search));
		return "commodity/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(Commodity Commodity) {
		commodityServiceImpl.insert(Commodity);
		return "redirect:/commodity";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(Commodity Commodity) {
		commodityServiceImpl.update(Commodity);
		return "redirect:/commodity";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		commodityServiceImpl.deleteCommodityById(id);
		return "redirect:/commodity";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public Commodity getJson(Model model, @PathVariable Integer id){
		return commodityServiceImpl.findCommodityById(id);
	}
	
	
	/**
	 * 
	 * Class Name: CommodityController.java
	 * @Description: 显示所有商品列表并分页
	 * @author zhang.zx
	 * @date 2015年4月8日 下午4:41:30
	 * @modifier
	 * @modify-date 2015年4月8日 下午4:41:30
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="/showAllProject",method=RequestMethod.POST)
	public String showAllProjectProduct(CommodityVo search,Model model){
		model.addAttribute("itemOneId", search.getItemOneId());
		search.setPageSize(7);
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		String commodityPicUrl=propertiesUtil.getImageServicePath();
		model.addAttribute("commodityPicUrl", commodityPicUrl);
		PageFinder<CommodityVo> pageFinder=commodityServiceImpl.queryCommodityByPage(search);
		model.addAttribute("pageFinder", pageFinder);
		return "classType/classIndexAjaxList";
	} 
	
	@ResponseBody
	@RequestMapping(value="/loadData")
	public List<CommodityVo> loadData(HttpServletRequest request,CommodityVo search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
//		search.setSchoolId(WebUtils.getCurrentSchoolId());
		List<Integer> list = new ArrayList<Integer>();
		if(search.getItemOneId() != null) {
			list.add(search.getItemOneId());
			search.setItemOneIds(list);
		}
		if(StringUtils.isNotBlank(search.getItemOneIdStrs())) {
			String[] arr = search.getItemOneIdStrs().split(",");
			for (String s : arr) {
				list.add(Integer.parseInt(s));
			}
			search.setItemOneIds(list);
		}
		return commodityServiceImpl.loadData(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/loadData3", method = RequestMethod.POST)
	public List<CommodityVo> loadData3(HttpServletRequest request,CommodityVo search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		return commodityServiceImpl.loadData(search);
	}
	@ResponseBody
	@RequestMapping(value="/loadData2", method = RequestMethod.POST)
	public List<CommodityVo> loadData2(HttpServletRequest request,@RequestParam("classTypeOne") String classTypeOne,@RequestParam("classTypeTwo")String classTypeTwo){
		Map search =new HashMap();
		search.put("companyId",WebUtils.getCurrentCompanyId());
		search.put("classTypeOne", classTypeOne);
		search.put("classTypeTwo", classTypeTwo);
		List<CommodityVo> vo=commodityServiceImpl.loadData2(search);
		return vo ;
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
