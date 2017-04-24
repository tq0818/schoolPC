package com.yuxin.wx.controller.system;

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

import com.yuxin.wx.api.system.ILongitudinalTableDataService;
import com.yuxin.wx.model.system.LongitudinalTableColDefine;
import com.yuxin.wx.model.system.LongitudinalTableData;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of LongitudinalTableData
 * @author wang.zx
 * @date 2016-1-13
 */
@Controller
@RequestMapping("/longitudinalTableData")
public class LongitudinalTableDataController {
	
	@Autowired
	private ILongitudinalTableDataService longitudinalTableDataServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, LongitudinalTableData search){
		if (search == null) {
			search = new LongitudinalTableData();
			// search.setPageSize(20);
		}
		model.addAttribute("list", longitudinalTableDataServiceImpl.findLongitudinalTableDataByPage(search));
		return "longitudinalTableData/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(LongitudinalTableData LongitudinalTableData) {
		longitudinalTableDataServiceImpl.insert(LongitudinalTableData);
		return "redirect:/longitudinalTableData";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(LongitudinalTableData LongitudinalTableData) {
		longitudinalTableDataServiceImpl.update(LongitudinalTableData);
		return "redirect:/longitudinalTableData";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		longitudinalTableDataServiceImpl.deleteLongitudinalTableDataById(id);
		return "redirect:/longitudinalTableData";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public LongitudinalTableData getJson(Model model, @PathVariable Integer id){
		return longitudinalTableDataServiceImpl.findLongitudinalTableDataById(id);
	}
	
	/**
	 * 获取公司纵向表配置
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getData", method = RequestMethod.GET)
	public List<LongitudinalTableData> getData(LongitudinalTableData search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<LongitudinalTableData> define=longitudinalTableDataServiceImpl.query(search);
		if(define.size()>0){
			int row=define.get(0).getRowId();
			search.setColName(null);
			search.setColValue(null);
			search.setRowId(row);
			List<LongitudinalTableData> result=longitudinalTableDataServiceImpl.query(search);
			return result;
		}
		return null;
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
