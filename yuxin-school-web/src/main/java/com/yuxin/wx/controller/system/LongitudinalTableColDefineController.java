package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.system.ILongitudinalTableColDefineService;
import com.yuxin.wx.api.system.ILongitudinalTableDataService;
import com.yuxin.wx.common.BaseSqlService;
import com.yuxin.wx.model.system.LongitudinalTableColDefine;
import com.yuxin.wx.model.system.LongitudinalTableData;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of LongitudinalTableColDefine
 * @author wang.zx
 * @date 2016-1-13
 */
@Controller
@RequestMapping("/longitudinalTableColDefine")
public class LongitudinalTableColDefineController {
	
	@Autowired
	private ILongitudinalTableColDefineService longitudinalTableColDefineServiceImpl;
	@Autowired
	private ILongitudinalTableDataService longitudinalTableDataServiceImpl;
	@Autowired
	private BaseSqlService baseSqlService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, LongitudinalTableColDefine search){
		if (search == null) {
			search = new LongitudinalTableColDefine();
			// search.setPageSize(20);
		}
		model.addAttribute("list", longitudinalTableColDefineServiceImpl.findLongitudinalTableColDefineByPage(search));
		return "longitudinalTableColDefine/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(LongitudinalTableColDefine LongitudinalTableColDefine) {
		longitudinalTableColDefineServiceImpl.insert(LongitudinalTableColDefine);
		return "redirect:/longitudinalTableColDefine";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(LongitudinalTableColDefine LongitudinalTableColDefine) {
		longitudinalTableColDefineServiceImpl.update(LongitudinalTableColDefine);
		return "redirect:/longitudinalTableColDefine";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		longitudinalTableColDefineServiceImpl.deleteLongitudinalTableColDefineById(id);
		return "redirect:/longitudinalTableColDefine";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public LongitudinalTableColDefine getJson(Model model, @PathVariable Integer id){
		return longitudinalTableColDefineServiceImpl.findLongitudinalTableColDefineById(id);
	}
	/**
	 * 资料是否完善
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/dataIsExists", method = RequestMethod.GET)
	public Boolean dataIsExists(Model model){
		LongitudinalTableColDefine search=new LongitudinalTableColDefine();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setColAllowNull(0);//必填
		List<LongitudinalTableColDefine> list=longitudinalTableColDefineServiceImpl.findLongitudinalTableColDefineByPage(search);
		if(list!=null && list.size()>0){
			for(LongitudinalTableColDefine l: list){
				LongitudinalTableData search2=new LongitudinalTableData();
				search2.setColId(l.getId());
				search2.setCompanyId(WebUtils.getCurrentCompanyId());
				/*暂时不加分校条件*/
//				seach2.setSchoolId(WebUtils.getCurrentSchoolId());
				List<LongitudinalTableData> data=longitudinalTableDataServiceImpl.findLongitudinalTableDataByPage(search2);
				if(data !=null && data.size()>0){
					return true;
				}
			}
		}else{
			return true;
		}
		return false;
		
	}
	/**
	 * 获取公司纵向表数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getData", method = RequestMethod.GET)
	public List<LongitudinalTableColDefine> getData(){
		
		List<LongitudinalTableColDefine> result=longitudinalTableColDefineServiceImpl.findByCompany(WebUtils.getCurrentCompanyId(),"student");
		for(LongitudinalTableColDefine r : result){
			String sql=r.getItems();
			if(StringUtils.isNotBlank(sql)){
				List<Map> itemsData=baseSqlService.executeQuery(sql);
				r.setItemsData(itemsData);
			}
		}
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
