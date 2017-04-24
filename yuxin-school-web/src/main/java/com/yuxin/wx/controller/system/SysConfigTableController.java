package com.yuxin.wx.controller.system;

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

import com.yuxin.wx.api.system.ISysConfigTableService;
import com.yuxin.wx.common.SysTableConfig;
import com.yuxin.wx.model.system.SysConfigTable;
import com.yuxin.wx.vo.system.ConfigTableVo;

/**
 * Controller of SysConfigTable
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/sysConfigTable")
public class SysConfigTableController {
	
	@Autowired
	private ISysConfigTableService sysConfigTableServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysConfigTable search){
		if (search == null) {
			search = new SysConfigTable();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysConfigTableServiceImpl.findSysConfigTableByPage(search));
		return "sysConfigTable/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysConfigTable sysConfigTable) {
		sysConfigTableServiceImpl.insert(sysConfigTable);
		return "redirect:/sysConfigTable";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysConfigTable sysConfigTable) {
		sysConfigTableServiceImpl.update(sysConfigTable);
		return "redirect:/sysConfigTable";
	}
	
	@RequestMapping(value="/del/{tableName}/${columnName}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable String tableName, @PathVariable String columnName) {
		sysConfigTableServiceImpl.deleteSysConfigTableByTableNameAndColumn(tableName, columnName);
		return "redirect:/sysConfigTable";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigTable getJson(Model model, @PathVariable Integer id){
		return sysConfigTableServiceImpl.findSysConfigTableById(id);
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
	 * @Description:(根据表名以及字段名获取vo)
	 * @author wang.zx 
	 * @date 2014-12-9 下午6:33:36
	 * @version 1.0
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getConfigVo")
	public String getConfigTableVoByByTableNameAndColumn(String tableName, String columnName){
		ConfigTableVo vo = SysTableConfig.getConfig(tableName, columnName);
		return "{\"isRequired\":\""+vo.isRequired+"\",\"isSystem\":\""+vo.isSystem+"\"}";
	}
}
