package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.yuxin.wx.api.company.ICompanyConfigCustompageGroupService;
import com.yuxin.wx.api.company.ICompanyConfigCustompageService;
import com.yuxin.wx.model.classes.ClassPackageStage;
import com.yuxin.wx.model.company.CompanyConfigCustompage;
import com.yuxin.wx.model.company.CompanyConfigCustompageGroup;
import com.yuxin.wx.utils.WebUtils;


/**
 * Controller of CompanyConfigCustompageGroup
 * @author chopin
 * @date 2016-4-25
 */
@Controller
@RequestMapping("/companyConfigCustompageGroup")
public class CompanyConfigCustompageGroupController {
	
	@Autowired
	private ICompanyConfigCustompageGroupService companyConfigCustompageGroupServiceImpl;
	@Autowired
	private ICompanyConfigCustompageService companyConfigCustompageServiceImpl;
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageGroupController.java
	 * @Description: 查询自定义分组列表
	 * @author zhang.zx
	 * @date 2016年4月25日 下午6:54:53
	 * @modifier
	 * @modify-date 2016年4月25日 下午6:54:53
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryGroupList")
	public List<CompanyConfigCustompageGroup> queryCustomGroupList(CompanyConfigCustompageGroup search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<CompanyConfigCustompageGroup> arr=companyConfigCustompageGroupServiceImpl.queryCustomGroupList(search);
		return arr;
	}
	
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageGroupController.java
	 * @Description: 添加模板组
	 * @author zhang.zx
	 * @date 2016年4月26日 下午2:41:20
	 * @modifier
	 * @modify-date 2016年4月26日 下午2:41:20
	 * @version 1.0
	 * @param group
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addCustomGroup")
	public CompanyConfigCustompageGroup add(CompanyConfigCustompageGroup group) {
		companyConfigCustompageGroupServiceImpl.insert(group);
		return companyConfigCustompageGroupServiceImpl.findCompanyConfigCustompageGroupById(group.getId());
	}
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageGroupController.java
	 * @Description: 修改模板组
	 * @author zhang.zx
	 * @date 2016年4月26日 下午2:42:29
	 * @modifier
	 * @modify-date 2016年4月26日 下午2:42:29
	 * @version 1.0
	 * @param group
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateCustomGroup")
	public CompanyConfigCustompageGroup update(CompanyConfigCustompageGroup group) {
		companyConfigCustompageGroupServiceImpl.update(group);
		return companyConfigCustompageGroupServiceImpl.findCompanyConfigCustompageGroupById(group.getId());
	}
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageGroupController.java
	 * @Description: 检验自定义组名
	 * @author zhang.zx
	 * @date 2016年4月27日 下午4:15:10
	 * @modifier
	 * @modify-date 2016年4月27日 下午4:15:10
	 * @version 1.0
	 * @param name
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkName")
	public boolean checkGroupName(String name,Integer id){
		boolean flag=true;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("companyId", WebUtils.getCurrentCompanyId());
		map.put("groupName", name);
		List<CompanyConfigCustompageGroup> data=companyConfigCustompageGroupServiceImpl.queryCustomGroupByCondition(map);
		if(null!=data && data.size()>0){
			if(null!=id && !"".equals(id)){
				CompanyConfigCustompageGroup group=companyConfigCustompageGroupServiceImpl.findCompanyConfigCustompageGroupById(id);
				if(null!=group && name.equals(group.getGroupName())){
					flag=true;
				}else{
					flag=false;
				}
			}else{
				flag=false;
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageGroupController.java
	 * @Description: 自定义组管理
	 * @author zhang.zx
	 * @date 2016年4月27日 下午3:58:55
	 * @modifier
	 * @modify-date 2016年4月27日 下午3:58:55
	 * @version 1.0
	 * @param group
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/manageCustomGroup")
	public CompanyConfigCustompageGroup manageCustomGroup(CompanyConfigCustompageGroup group){
		group.setCompanyId(WebUtils.getCurrentCompanyId());
		CompanyConfigCustompageGroup search=companyConfigCustompageGroupServiceImpl.findCompanyConfigCustompageGroupById(group.getId());
		if(null!=search){
			group.setId(search.getId());
			companyConfigCustompageGroupServiceImpl.update(group);
		}else{
			group.setDelFlag(0);
			companyConfigCustompageGroupServiceImpl.insert(group);
		}
		return companyConfigCustompageGroupServiceImpl.findCompanyConfigCustompageGroupById(group.getId());
	}
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageGroupController.java
	 * @Description: 查询当前组下是否存在自定义页面
	 * @author zhang.zx
	 * @date 2016年4月27日 下午5:07:52
	 * @modifier
	 * @modify-date 2016年4月27日 下午5:07:52
	 * @version 1.0
	 * @param groupId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/isDelete")
	public boolean queryCusPageCountByGroupId(Integer groupId){
		boolean flag=true;
		CompanyConfigCustompage search=new CompanyConfigCustompage();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setGroupId(groupId.toString());
		Integer count=companyConfigCustompageServiceImpl.templeteCount(search);
		if(null!=count && count>0){
			flag=false;
		}
		return flag;
	}
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageGroupController.java
	 * @Description: 删除模板组
	 * @author zhang.zx
	 * @date 2016年4月27日 下午5:13:51
	 * @modifier
	 * @modify-date 2016年4月27日 下午5:13:51
	 * @version 1.0
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/del/{id}")
	public String del(Model model, @PathVariable Integer id) {
		CompanyConfigCustompageGroup group=companyConfigCustompageGroupServiceImpl.findCompanyConfigCustompageGroupById(id);
		if(null!=group){
			group.setDelFlag(1);
			companyConfigCustompageGroupServiceImpl.update(group);
			return "success";
		}
		return "error";
	}
	
	@ResponseBody
	@RequestMapping(value="/queryBroupId/{id}")
	public CompanyConfigCustompageGroup getJson(Model model, @PathVariable Integer id){
		return companyConfigCustompageGroupServiceImpl.findCompanyConfigCustompageGroupById(id);
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
