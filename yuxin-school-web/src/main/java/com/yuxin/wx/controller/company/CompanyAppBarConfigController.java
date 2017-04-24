package com.yuxin.wx.controller.company;


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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.yuxin.wx.api.company.ICompanyAppAuthService;
import com.yuxin.wx.api.company.ICompanyAppBarConfigService;
import com.yuxin.wx.api.company.ICompanyAppConfigService;
import com.yuxin.wx.api.company.ICompanyAppWelcomePicService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyAppAuth;
import com.yuxin.wx.model.company.CompanyAppBarConfig;
import com.yuxin.wx.model.company.CompanyAppConfig;
import com.yuxin.wx.model.company.CompanyAppWelcomePic;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyAppBarConfig
 * @author chopin
 * @date 2016-5-5
 */
@Controller
@RequestMapping("/companyAppBarConfig")
public class CompanyAppBarConfigController {
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private ICompanyAppBarConfigService companyAppBarConfigServiceImpl;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	@Autowired
	private ICompanyAppAuthService companyAppAuthServiceImpl;
	@Autowired
	private ICompanyAppConfigService companyAppConfigServiceImpl;
	@Autowired
	private ICompanyAppWelcomePicService companyAppWelcomePicServiceImpl;
	@Autowired
	private PropertiesUtil propertiesUtil;
	/**
	 * 打开学校App导航配置页面
	 * @author licong
	 * @date 2016年5月11日 下午6:16:10
	 * @param  
	 * @param model
	 * @return
	 */
	@RequestMapping("/openPage")
	public String openPage(Model model){
		CompanyAppBarConfig cap = new CompanyAppBarConfig();
		cap.setCompanyId(WebUtils.getCurrentCompanyId());
		cap.setSchoolId(WebUtils.getCurrentSchoolId());
		cap.setPageType("head");
		boolean flag = false;//是否禁用头部导航
		List<CompanyAppBarConfig> headCaps = companyAppBarConfigServiceImpl.findByCompanyAppBarConfig(cap);
		if(headCaps.size()>0){
			for (CompanyAppBarConfig companyAppBarConfig : headCaps) {
				if((int)companyAppBarConfig.getStatus() == 1){
					flag = true;
					break;
				}
			}
		}else{
			flag = true;
		}
		model.addAttribute("flag", flag);
		model.addAttribute("headCaps", headCaps);
		
		cap.setPageType("foot");
		List<CompanyAppBarConfig> footCaps = companyAppBarConfigServiceImpl.findByCompanyAppBarConfig(cap);
		if(footCaps != null && footCaps.size()==0){
			CompanyAppBarConfig cap1 = new CompanyAppBarConfig();
			cap1.setCompanyId(WebUtils.getCurrentCompanyId());
			cap1.setSchoolId(WebUtils.getCurrentSchoolId());
			cap1.setStatus(1);
			cap1.setPageType("foot");
			cap1.setPageCode(4);
			cap1.setName("我的");
			cap1.setSequence(2);
			companyAppBarConfigServiceImpl.insert(cap1);
			CompanyAppBarConfig cap2 = new CompanyAppBarConfig();
			cap2.setCompanyId(WebUtils.getCurrentCompanyId());
			cap2.setSchoolId(WebUtils.getCurrentSchoolId());
			cap2.setStatus(1);
			cap2.setPageType("foot");
			cap2.setPageCode(2);
			cap2.setName("课程");
			cap2.setSequence(1);
			companyAppBarConfigServiceImpl.insert(cap2);
			CompanyAppBarConfig cap3 = new CompanyAppBarConfig();
			cap3.setCompanyId(WebUtils.getCurrentCompanyId());
			cap3.setSchoolId(WebUtils.getCurrentSchoolId());
			cap3.setStatus(1);
			cap3.setPageType("foot");
			cap3.setPageCode(1);
			cap3.setName("首页");
			cap3.setSequence(0);
			companyAppBarConfigServiceImpl.insert(cap3);
			footCaps = companyAppBarConfigServiceImpl.findByCompanyAppBarConfig(cap);
		}
		Integer companyId=WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		
		model.addAttribute("footCaps", footCaps);
		return "app/appBarConfig";
	}
	/**
	 * 新增导航
	 * @author licong
	 * @date 2016年5月11日 下午6:18:12
	 * @param  
	 * @param cap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addCap")
	public Object addCap(CompanyAppBarConfig cap){
		Map<String,Object> map = new HashMap<String,Object>();
		CompanyAppBarConfig search = new CompanyAppBarConfig();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		search.setStatus(1);
		search.setPageCode(cap.getPageCode());
		search.setPageType(cap.getPageType());
		List<CompanyAppBarConfig> caps = companyAppBarConfigServiceImpl.findByCompanyAppBarConfig(search);
		if(caps != null && caps.size() > 0){
			map.put("flag", false);
			map.put("msg", "不能重复添加导航");
			return map;
		}
		cap.setCompanyId(WebUtils.getCurrentCompanyId());
		cap.setSchoolId(WebUtils.getCurrentSchoolId());
		cap.setStatus(1);
		companyAppBarConfigServiceImpl.insert(cap);
		map.put("flag", true);
		map.put("cap", cap);
		return map;
	}
	/**
	 * findOne
	 * @author licong
	 * @date 2016年5月11日 下午7:13:43
	 * @param  
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findOne")
	public CompanyAppBarConfig findOne(Integer id){
		return companyAppBarConfigServiceImpl.findCompanyAppBarConfigById(id);
	}
	/**
	 * 修改页头或者页尾导航
	 * @author licong
	 * @date 2016年5月11日 下午6:18:12
	 * @param  
	 * @param cap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateCap")
	public Object updateCap(CompanyAppBarConfig data){
		Map<String,Object> map = new HashMap<String,Object>();
		if(data.getId() == null || data.getPageCode() == null || data.getName() == null){
			map.put("flag", false);
			map.put("msg", "参数不完整");
			return map;
		}
		CompanyAppBarConfig cap = findOne(data.getId());
		if(cap == null){
			map.put("flag", false);
			map.put("msg", "没有查找到数据");
			return map;
		}
		if((int)data.getPageCode() != (int)cap.getPageCode()){//如果修改为不同编号的导航则判断该企业是否有改编码的导航
			CompanyAppBarConfig search = new CompanyAppBarConfig();
			search.setCompanyId(WebUtils.getCurrentCompanyId());
			search.setSchoolId(WebUtils.getCurrentSchoolId());
			search.setStatus(1);
			search.setPageCode(data.getPageCode());
			search.setPageType(data.getPageType());
			List<CompanyAppBarConfig> caps = companyAppBarConfigServiceImpl.findByCompanyAppBarConfig(search);
			if(caps != null && caps.size() > 0){
				map.put("flag", false);
				map.put("msg", "不能重复添加导航");
				return map;
			}
			cap.setPageCode(data.getPageCode());
		}
		cap.setName(data.getName());
		companyAppBarConfigServiceImpl.update(cap);
		map.put("flag", true);
		map.put("cap", cap);
		return map;
	}
	/**
	 * 删除
	 * @author licong
	 * @date 2016年5月11日 下午7:25:13
	 * @param  
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteCap")
	public Object deleteCap(Integer id){
		Map<String,Object> map = new HashMap<String,Object>();
		if(id == null || "".equals(id)){
			map.put("flag", false);
			map.put("msg", "参数错误");
		}
		companyAppBarConfigServiceImpl.deleteCompanyAppBarConfigById(id);
		map.put("flag", true);
		return map;
	}
	/**
	 * 排序
	 * @author licong
	 * @date 2016年5月11日 下午7:26:40
	 * @param  
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/orderCaps")
	public Object orderCaps(HttpServletRequest request){
		List<CompanyAppBarConfig> caps = JSONArray.parseArray(request.getParameter("list"), CompanyAppBarConfig.class);
		if(caps == null || caps.size() == 0)
			return true;
		for (CompanyAppBarConfig cap : caps) {
			companyAppBarConfigServiceImpl.update(cap);
		}
		return true;
	}
	
	/**
	 * 
	 * Class Name: CompanyAppBarConfigController.java
	 * @Description: 配置app基本信息
	 * @author zhang.zx
	 * @date 2016年5月27日 下午4:24:43
	 * @modifier
	 * @modify-date 2016年5月27日 下午4:24:43
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/configBaseInfo")
	public String configCompanyAppInfo(Model model){
		Integer companyId=WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		
		CompanyAppConfig config=companyAppConfigServiceImpl.queryCompanyConfig(companyId);
		model.addAttribute("config", config);
		
		CompanyAppWelcomePic pic=companyAppWelcomePicServiceImpl.findByparam(WebUtils.getCurrentCompanyId());
		model.addAttribute("pic", pic);
		
		//查询公司app版本
		CompanyAppAuth search=new CompanyAppAuth();
		search.setCompanyId(companyId);
		CompanyAppAuth companyAuth=companyAppAuthServiceImpl.findByParams(search);
		if(null!=companyAuth && null!=companyAuth.getVersion() && "private".equals(companyAuth.getVersion())){
			return "app/appprivateBaseInfo";
		}
		String ewmUrl=propertiesUtil.getCompanyEwmImage();
		model.addAttribute("ewmUrl", ewmUrl);
		String iosUrl=propertiesUtil.getCompanyIosDownloadUrl();
		model.addAttribute("iosUrl", iosUrl);
		String andoridUrl=propertiesUtil.getCompanyAndoridDownloadUrl();
		model.addAttribute("andoridUrl", andoridUrl);
		return "app/appBaseInfo";
	}
 	
}
