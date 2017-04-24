package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Patch;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyRechargeLibService;
import com.yuxin.wx.api.company.ICompanyRechargePatchService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.company.CompanyRechargeLib;
import com.yuxin.wx.model.company.CompanyRechargePatch;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.CompanyRechargeLibVo;

/**
 * Controller of CompanyRechargeLib
 * @author chopin
 * @date 2017-4-10
 */
@Controller
@RequestMapping("/companyRechargeLib")
public class CompanyRechargeLibController {
	
	@Autowired
	private ICompanyRechargeLibService companyRechargeLibServiceImpl;
	@Autowired
	private ICompanyRechargePatchService companyRechargePatchServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyRechargeLib search){
		if (search == null) {
			search = new CompanyRechargeLib();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyRechargeLibServiceImpl.findCompanyRechargeLibByPage(search));
		return "companyRechargeLib/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyRechargeLib CompanyRechargeLib) {
		companyRechargeLibServiceImpl.insert(CompanyRechargeLib);
		return "redirect:/companyRechargeLib";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyRechargeLib CompanyRechargeLib) {
		companyRechargeLibServiceImpl.update(CompanyRechargeLib);
		return "redirect:/companyRechargeLib";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyRechargeLibServiceImpl.deleteCompanyRechargeLibById(id);
		return "redirect:/companyRechargeLib";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public CompanyRechargeLib getJson(Model model, @PathVariable Integer id){
		return companyRechargeLibServiceImpl.findCompanyRechargeLibById(id);
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
	
	/**
	 * 
	 * Class Name: CompanyRechargeLibController.java
	 * @Description: 查询
	 * @author dongshuai
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryLibs", method = RequestMethod.POST)
	public JSONObject queryLibs(CompanyRechargeLibVo search){
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		Integer companyId = WebUtils.getCurrentCompanyId();
		
		search.setCompanyId(companyId);
		PageFinder<CompanyRechargeLibVo> libs = companyRechargeLibServiceImpl.queryByPatchId(search);
		json.put("libs", libs);
		
		return json;
	}
	
	/**
	 * 
	 * Class Name: CompanyRechargeLibController.java
	 * @Description: 导出充值卡
	 * @author dongshuai
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/exportRechargeLibs")
	public ModelAndView exportRechargeLibs(CompanyRechargeLibVo search) {

		Integer companyId = WebUtils.getCurrentCompanyId();
		search.setCompanyId(companyId);
		List<CompanyRechargeLibVo> libs = this.companyRechargeLibServiceImpl.queryListByPatchId(search);
		CompanyRechargePatch patch = null;
		if(libs.size()>0){
			patch = companyRechargePatchServiceImpl.findCompanyRechargePatchById(libs.get(0).getPatchId());
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for (CompanyRechargeLibVo s : libs) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", s.getCode());
			map.put("cashAmount",patch!=null?patch.getCashAmount()+"元":"" );
			map.put("status", s.getStatus()==1?"已充值":"未充值");
			map.put("useTime", sdf.format(s.getTimeLimitFrom()) + "至" + sdf.format(s.getTimeLimitTo()));
			lists.add(map);
		}
		StringBuffer title = new StringBuffer(
				"充值卡号:code,充值卡金额:cashAmount,状态:status,有效期:useTime");
		ViewFiles excel = new ViewFiles();
		@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workbook", wb);
		map.put("fileName", "充值卡.xls");
		return new ModelAndView(excel, map);

	}
}
