package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysConfigIcoService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMarketSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysConfigIco;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;


/**
 * Controller of SysConfigIco
 * @author chopin
 * @date 2015-12-4
 */
@Controller
@RequestMapping("/sysConfigIco")
public class SysConfigIcoController {
	
	private Log log=LogFactory.getLog("log");
	
	@Autowired
	private ISysConfigIcoService sysConfigIcoServiceImpl;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 
	 * Class Name: SysConfigIcoController.java
	 * @Description: 跳转到公司配置ico文件
	 * @author zhang.zx
	 * @date 2015年12月4日 上午11:47:52
	 * @modifier
	 * @modify-date 2015年12月4日 上午11:47:52
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showIco")
	public String showIco(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "system/systemCompanyIco";
	}
	
	@RequestMapping(value="/showCode")
	public String showCode(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "system/configCompanyCode";
	}
	
	@RequestMapping(value="/showLogo")
	public String showLogo(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "system/configCompanyIco";
	}
	
	@ResponseBody
	@RequestMapping(value="/queryIcoList")
	public List<SysConfigIco> queryAllIcolist(SysConfigIco search){
		String url="http://"+propertiesUtil.getProjectImageUrl()+"/";
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<SysConfigIco> list=sysConfigIcoServiceImpl.queryAllIcoLists(search);
		if(null!=list){
			for(SysConfigIco ico:list){
				if(null!=ico&&null!=ico.getPath()){
					ico.setPath(url+ico.getPath());
				}
			}
		}
		return list;
	}
	
	/**
	 * 
	 * Class Name: SysConfigIcoController.java
	 * @Description: 查询当前公司使用的ico文件个数
	 * @author zhang.zx
	 * @date 2015年12月4日 下午12:05:25
	 * @modifier
	 * @modify-date 2015年12月4日 下午12:05:25
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryUseCount")
	public Integer queryCompanyUseIco(){
		Integer companyId=WebUtils.getCurrentCompanyId();
		List<SysConfigIco> arr=sysConfigIcoServiceImpl.queryUseIco(companyId);
		if(null!=arr){
			return arr.size();
		}
		return 0;
	}
	
	/**
	 * 
	 * Class Name: SysConfigIcoController.java
	 * @Description: 上传ico文件
	 * @author zhang.zx
	 * @date 2015年12月4日 上午11:55:54
	 * @modifier
	 * @modify-date 2015年12月4日 上午11:55:54
	 * @version 1.0
	 * @param multiPartRquest
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/uploadIco")
	public String UploadCycles(MultipartRequest multiPartRquest,HttpServletRequest req){
		String realPath=null;
		String picPath=null;
		MultipartFile	multipartFile = multiPartRquest.getFile("imgData");
		String name=multipartFile.getOriginalFilename();
		if(name!=null&&!"".equals(name)){
			try {
				realPath = FileUtil.upload(multipartFile, "company", WebUtils.getCurrentCompanyId()+"");
			} catch (Exception e) {
				log.error("文件上传失败",e);
				e.printStackTrace();
			}
		}
		//查询当前公司的ico图标
		List<SysConfigIco> arr=sysConfigIcoServiceImpl.queryUseIco(WebUtils.getCurrentCompanyId());
		if(null!=arr&&arr.size()>0){
			arr.get(0).setPath(realPath);
			sysConfigIcoServiceImpl.update(arr.get(0));
		}else{
			SysConfigIco ico=new SysConfigIco();
			ico.setCompanyId(WebUtils.getCurrentCompanyId());
			ico.setCreateTime(new Date());
			ico.setStatus(1);
			ico.setPath(realPath);
			sysConfigIcoServiceImpl.insert(ico);
		}
		picPath="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
		return "{\"url\":\""+picPath+"\",\"picPath\":\""+realPath+"\"}";
	}
	
	/**
	 * 
	 * Class Name: SysConfigIcoController.java
	 * @Description: 查询当前公司使用的ico
	 * @author zhang.zx
	 * @date 2015年12月25日 下午2:37:44
	 * @modifier
	 * @modify-date 2015年12月25日 下午2:37:44
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryIco")
	public SysConfigIco queryCompanyUseIcos(){
		String url="http://"+propertiesUtil.getProjectImageUrl()+"/";
		SysConfigIco ico=new SysConfigIco();
		List<SysConfigIco> arr=sysConfigIcoServiceImpl.queryUseIco(WebUtils.getCurrentCompanyId());
		if(null!=arr&&arr.size()>0){
			ico=arr.get(0);
		}
		if(ico.getPath() != null && !"".equals(ico.getPath())){
			ico.setPath(url+ico.getPath());
		}
		return ico;
	}
	
	@ResponseBody
	@RequestMapping(value="/add")
	public Integer add(SysConfigIco SysConfigIco) {
		SysConfigIco.setCompanyId(WebUtils.getCurrentCompanyId());
		SysConfigIco.setCreateTime(new Date());
		SysConfigIco.setStatus(0);
		sysConfigIcoServiceImpl.insert(SysConfigIco);
		return SysConfigIco.getId();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(SysConfigIco SysConfigIco) {
		SysConfigIco.setCompanyId(WebUtils.getCurrentCompanyId());
		SysConfigIco.setCreateTime(new Date());
		sysConfigIcoServiceImpl.update(SysConfigIco);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/del/{id}")
	public String del(Model model, @PathVariable Integer id) {
		sysConfigIcoServiceImpl.deleteSysConfigIcoById(id);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysConfigIco getJson(Model model, @PathVariable Integer id){
		return sysConfigIcoServiceImpl.findSysConfigIcoById(id);
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
