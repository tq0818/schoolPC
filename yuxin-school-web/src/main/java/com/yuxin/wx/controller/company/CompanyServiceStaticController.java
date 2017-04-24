package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.SystemConfigServiceVo;

/**
 * Controller of CompanyServiceStatic
 * @author chopin
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/companyServiceStatic")
public class CompanyServiceStaticController {
	
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyServiceStatic search){
		if (search == null) {
			search = new CompanyServiceStatic();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyServiceStaticServiceImpl.findCompanyServiceStaticByPage(search));
		return "companyServiceStatic/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyServiceStatic CompanyServiceStatic) {
		companyServiceStaticServiceImpl.insert(CompanyServiceStatic);
		return "redirect:/companyServiceStatic";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyServiceStatic CompanyServiceStatic) {
		companyServiceStaticServiceImpl.update(CompanyServiceStatic);
		return "redirect:/companyServiceStatic";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyServiceStaticServiceImpl.deleteCompanyServiceStaticById(id);
		return "redirect:/companyServiceStatic";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyServiceStatic getJson(Model model, @PathVariable Integer id){
		return companyServiceStaticServiceImpl.findCompanyServiceStaticById(id);
	}
	
	/**
	 * 
	 * Class Name: CompanyServiceStaticController.java
	 * @Description: 查询公司没开通的服务
	 * @author zhang.zx
	 * @date 2015年8月12日 下午4:57:30
	 * @modifier
	 * @modify-date 2015年8月12日 下午4:57:30
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryCompanyNoServices",method=RequestMethod.POST)
	public List<SystemConfigServiceVo> queryNoexitCompanyServices(){
		List<SystemConfigServiceVo> arr1=new ArrayList<SystemConfigServiceVo>();
		Integer companyId=WebUtils.getCurrentCompanyId();
		List<SystemConfigServiceVo> arr=companyServiceStaticServiceImpl.queryCompanyNoService(companyId);
		if(null!=arr&&!arr.isEmpty()){
			for(SystemConfigServiceVo service:arr){
				if("SERVICE_LIVE".equals(service.getGroupCode())||"SERVICE_VIDEO".equals(service.getGroupCode())||"SERVICE_FACE".equals(service.getGroupCode())){
					SystemConfigServiceVo sys=new SystemConfigServiceVo();
					sys.setId(service.getId());
					sys.setCompanyId(service.getCompanyId());
					sys.setGroupCode(service.getGroupCode());
					sys.setDelFlag(service.getDelFlag());
					arr1.add(sys);
				}
			}
		}
		return arr1;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/companyNoServices",method=RequestMethod.POST)
	public List<SystemConfigServiceVo> comNoexitCompanyServices(){
		List<SystemConfigServiceVo> arr1=new ArrayList<SystemConfigServiceVo>();
		Integer companyId=WebUtils.getCurrentCompanyId();
		List<SystemConfigServiceVo> arr=companyServiceStaticServiceImpl.queryCompanyNoService(companyId);
		if(null!=arr&&!arr.isEmpty()){
			for(SystemConfigServiceVo service:arr){
				if("SERVICE_LIVE".equals(service.getGroupCode())||"SERVICE_FACE".equals(service.getGroupCode())){
					SystemConfigServiceVo sys=new SystemConfigServiceVo();
					sys.setId(service.getId());
					sys.setCompanyId(service.getCompanyId());
					sys.setGroupCode(service.getGroupCode());
					sys.setDelFlag(service.getDelFlag());
					arr1.add(sys);
				}
			}
		}
		return arr1;
	}
	
	//简版公司服务开通判断
	@ResponseBody
	@RequestMapping(value="/simplecompanyNoServices",method=RequestMethod.POST)
	public List<SystemConfigServiceVo> comNosimpleCompanyServices(){
		List<SystemConfigServiceVo> arr1=new ArrayList<SystemConfigServiceVo>();
		Integer companyId=WebUtils.getCurrentCompanyId();
		List<SystemConfigServiceVo> arr=companyServiceStaticServiceImpl.queryCompanyNoService(companyId);
		if(null!=arr&&!arr.isEmpty()){
			for(SystemConfigServiceVo service:arr){
				if("SERVICE_LIVE".equals(service.getGroupCode())||"SERVICE_FACE".equals(service.getGroupCode())||"SERVICE_VIDEO".equals(service.getGroupCode())){
					SystemConfigServiceVo sys=new SystemConfigServiceVo();
					sys.setId(service.getId());
					sys.setCompanyId(service.getCompanyId());
					sys.setGroupCode(service.getGroupCode());
					sys.setDelFlag(service.getDelFlag());
					arr1.add(sys);
				}
			}
		}
		return arr1;
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
