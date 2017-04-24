package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.yuxin.wx.api.company.ICompanyIntegralConfigService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.controller.system.SysConfigIcoController;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyIntegralConfig;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;


/**
 * Controller of CompanyIntegralConfig
 * @author chopin
 * @date 2016-5-18
 */
@Controller
@RequestMapping("/companyIntegralConfig")
public class CompanyIntegralConfigController {
	private Log log=LogFactory.getLog("log");
	@Autowired
	private ICompanyIntegralConfigService companyIntegralConfigServiceImpl;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private ICompanyService companyService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyIntegralConfig search){
		if (search == null) {
			search = new CompanyIntegralConfig();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyIntegralConfigServiceImpl.findCompanyIntegralConfigByPage(search));
		return "companyIntegralConfig/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyIntegralConfig CompanyIntegralConfig) {
		companyIntegralConfigServiceImpl.insert(CompanyIntegralConfig);
		return "redirect:/companyIntegralConfig";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyIntegralConfig CompanyIntegralConfig) {
		companyIntegralConfigServiceImpl.update(CompanyIntegralConfig);
		return "redirect:/companyIntegralConfig";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyIntegralConfigServiceImpl.deleteCompanyIntegralConfigById(id);
		return "redirect:/companyIntegralConfig";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyIntegralConfig getJson(Model model, @PathVariable Integer id){
		return companyIntegralConfigServiceImpl.findCompanyIntegralConfigById(id);
	}
	
	/**
	 * 
	 * Class Name: CompanyIntegralConfigController.java
	 * @Description: 查询公司积分开关
	 * @author zhang.zx
	 * @date 2016年5月19日 上午10:10:40
	 * @modifier
	 * @modify-date 2016年5月19日 上午10:10:40
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryIntegralConfig")
	public CompanyIntegralConfig queryIntegralConfigByCondition(CompanyIntegralConfig search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		CompanyIntegralConfig config=companyIntegralConfigServiceImpl.findIntegralConfigByWhere(search);
		return config;
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
	 * 打开积分设置页面
	 * @author licong
	 * @date 2016年5月20日 下午4:20:45
	 * @param  
	 * @return
	 */
	@RequestMapping("/openIntegralSet")
	public String openIntegralSet(Model model){
		String commodityPicUrl="http://"+propertiesUtil.getProjectImageUrl()+"/";
		model.addAttribute("commodityPicUrl", commodityPicUrl);
		Integer companyId = WebUtils.getCurrentCompanyId();
		
		CompanyIntegralConfig search = new CompanyIntegralConfig();
		search.setCompanyId(companyId);
		search = companyIntegralConfigServiceImpl.findIntegralConfigByWhere(search);
		model.addAttribute("cic", search);
		
		Company company = companyService.findCompanyById(companyId);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "integral/integralSet";
	}
	/**
	 * 上传积分图片
	 * @author licong
	 * @date 2016年5月20日 下午4:39:01
	 * @param  
	 * @param multiPartRquest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upIntegralFile")
	public Map<String,Object> upIntegralFile(MultipartRequest multiPartRquest){
		String picPath=null;
		Map<String,Object> map = new HashMap<String,Object>();
		MultipartFile	multipartFile = multiPartRquest.getFile("imgData");
		String name=multipartFile.getOriginalFilename();
		if(name!=null&&!"".equals(name)){
			try {
				picPath = FileUtil.upload(multipartFile, "company", WebUtils.getCurrentCompanyId()+"");
				map.put("url", "http://"+propertiesUtil.getProjectImageUrl()+"/"+picPath);
				map.put("picPath", picPath);
			} catch (Exception e) {
				log.error("文件上传失败",e);
				e.printStackTrace();
				return map;
			}
		}
		return map;
	}
	/**
	 * 新增或者修改
	 * @author licong
	 * @date 2016年5月24日 上午11:00:36
	 * @param  
	 * @param cic
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdate")
	public Object saveOrUpdate(CompanyIntegralConfig cic){
		if(cic.getId() != null && !"".equals(cic.getId())){
			companyIntegralConfigServiceImpl.update(cic);
		}else{
			CompanyIntegralConfig search = new CompanyIntegralConfig();
			search.setCompanyId(WebUtils.getCurrentCompanyId());
			search = companyIntegralConfigServiceImpl.findIntegralConfigByWhere(search);
			if(search == null){
				cic.setCompanyId(WebUtils.getCurrentCompanyId());
				companyIntegralConfigServiceImpl.insert(cic);
			}else{
				cic.setId(search.getId());
				companyIntegralConfigServiceImpl.update(cic);
			}
		}
		return cic;
	}
 	
	/**
	 * 打开获取积分页面
	 * @author licong
	 * @date 2016年5月20日 下午4:20:45
	 * @param  
	 * @return
	 */
	@RequestMapping("/openGetIntegral")
	public String openGetIntegral(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyIntegralConfig search = new CompanyIntegralConfig();
		search.setCompanyId(companyId);
		search = companyIntegralConfigServiceImpl.findIntegralConfigByWhere(search);
		model.addAttribute("cic", search);
		
		Company company = companyService.findCompanyById(companyId);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "integral/integralGet";
	}
	
	
	
	
	
	
	/**
	 * 打开获取积分页面
	 * @author licong
	 * @date 2016年5月20日 下午4:20:45
	 * @param  
	 * @return
	 */
	@RequestMapping("/openCustomIntegral")
	public String openCustomIntegral(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyIntegralConfig search = new CompanyIntegralConfig();
		search.setCompanyId(companyId);
		search = companyIntegralConfigServiceImpl.findIntegralConfigByWhere(search);
		model.addAttribute("cic", search);
		
		Company company = companyService.findCompanyById(companyId);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "integral/integralCustom";
	}
}
