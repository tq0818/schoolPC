package com.yuxin.wx.controller.commodity;

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

import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;

/**
 * Controller of CommodityProductRealtion
 * @author wang.zx
 * @date 2015-3-14
 */
@Controller
@RequestMapping("/commodityProductRealtion")
public class CommodityProductRealtionController {
	
	@Autowired
	private ICommodityProductRealtionService commodityProductRealtionServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CommodityProductRealtion search){
		if (search == null) {
			search = new CommodityProductRealtion();
			// search.setPageSize(20);
		}
		model.addAttribute("list", commodityProductRealtionServiceImpl.findCommodityProductRealtionByPage(search));
		return "commodityProductRealtion/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CommodityProductRealtion CommodityProductRealtion) {
		commodityProductRealtionServiceImpl.insert(CommodityProductRealtion);
		return "redirect:/commodityProductRealtion";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CommodityProductRealtion CommodityProductRealtion) {
		commodityProductRealtionServiceImpl.update(CommodityProductRealtion);
		return "redirect:/commodityProductRealtion";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		commodityProductRealtionServiceImpl.deleteCommodityProductRealtionById(id);
		return "redirect:/commodityProductRealtion";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CommodityProductRealtion getJson(Model model, @PathVariable Integer id){
		return commodityProductRealtionServiceImpl.findCommodityProductRealtionById(id);
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
