package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.yuxin.wx.api.company.ICompanyFootInfoService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFootInfo;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyFootInfo
 * @author chopin
 * @date 2016-2-29
 */
@Controller
@RequestMapping("/companyFootInfo")
public class CompanyFootInfoController {
	
	@Autowired
	private ICompanyFootInfoService companyFootInfoServiceImpl;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 
	 * Class Name: CompanyFootInfoController.java
	 * @Description: 查询公司相关信息
	 * @author zhang.zx
	 * @date 2016年3月1日 下午3:28:28
	 * @modifier
	 * @modify-date 2016年3月1日 下午3:28:28
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryCompanyId")
	public CompanyFootInfo queryCompanyInfo(HttpServletRequest request){
		CompanyFootInfo companyInfo=companyFootInfoServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		return companyInfo;
	}
	
	/**
	 * 
	 * Class Name: CompanyFootInfoController.java
	 * @Description: 操作公司信息
	 * @author zhang.zx
	 * @date 2016年3月2日 上午11:27:52
	 * @modifier
	 * @modify-date 2016年3月2日 上午11:27:52
	 * @version 1.0
	 * @param companyInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editCompanyIo")
	public String editCompanyInfos(CompanyFootInfo companyInfo){
//		if(!isEnterpriseVersion()){
//			return "error";
//		}
		CompanyFootInfo footInfo=companyFootInfoServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		if(null!=footInfo){
			companyInfo.setId(footInfo.getId());
			companyInfo.setSecurityIco(footInfo.getSecurityIco());
			companyInfo.setSecurityRegno(footInfo.getSecurityRegno());
			companyInfo.setSecurityLink(footInfo.getSecurityLink());
			companyFootInfoServiceImpl.update(companyInfo);
		}else{
//			companyInfo.setCompanyId(WebUtils.getCurrentCompanyId());
//			companyFootInfoServiceImpl.insert(companyInfo);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/editCompanyMarking")
	public String editCompanyMarking(CompanyFootInfo companyInfo){
		CompanyFootInfo footInfo=companyFootInfoServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		if(null!=footInfo){
			companyInfo.setId(footInfo.getId());
			companyInfo.setSecurityIco(footInfo.getSecurityIco());
			companyInfo.setSecurityRegno(footInfo.getSecurityRegno());
			companyInfo.setSecurityLink(footInfo.getSecurityLink());
			companyFootInfoServiceImpl.update(companyInfo);
		}else{
//			companyInfo.setCompanyId(WebUtils.getCurrentCompanyId());
//			companyFootInfoServiceImpl.insert(companyInfo);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("/isEnterootPathriseVersion")
	private Boolean isEnterpriseVersion(){
		boolean isSelfNet=false;
		Company company = companyService.findCompanyById(WebUtils.getCurrentCompanyId());
		Integer serviceLeverl=company.getMemberLevel();
		if(serviceLeverl!=null&&serviceLeverl>=40){
			isSelfNet=true;
		}
		return isSelfNet;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyFootInfo search){
		if (search == null) {
			search = new CompanyFootInfo();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyFootInfoServiceImpl.findCompanyFootInfoByPage(search));
		return "companyFootInfo/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyFootInfo CompanyFootInfo) {
		companyFootInfoServiceImpl.insert(CompanyFootInfo);
		return "redirect:/companyFootInfo";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyFootInfo CompanyFootInfo) {
		companyFootInfoServiceImpl.update(CompanyFootInfo);
		return "redirect:/companyFootInfo";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyFootInfoServiceImpl.deleteCompanyFootInfoById(id);
		return "redirect:/companyFootInfo";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyFootInfo getJson(Model model, @PathVariable Integer id){
		return companyFootInfoServiceImpl.findCompanyFootInfoById(id);
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
	 * 上传备案图片
	 * @author licong
	 * @date 2017年1月3日 下午5:56:19
	 * @param  
	 * @param multiPartRquest
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/upload")
	public Object UploadCycles(MultipartRequest multiPartRquest,HttpServletRequest req){
		Map<String,Object> result = new HashMap<String,Object>();
		String imgPath=null;
		String path = null;
		MultipartFile	multipartFile = multiPartRquest.getFile("secico");
		String name=multipartFile.getOriginalFilename();
		if(name!=null&&!"".equals(name)){
			try {
				imgPath = FileUtil.upload(multipartFile, "company", WebUtils.getCurrentCompanyId()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		path = "http://"+propertiesUtil.getProjectImageUrl()+"/"+imgPath;
		result.put("imgPath", imgPath);
		result.put("path", path);
		return result;
	}
	
	/**
	 * 更新数据
	 * @author licong
	 * @date 2017年1月3日 下午6:20:22
	 * @param  
	 * @param companyInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editgongan")
	public String editgongan(CompanyFootInfo companyInfo){
		CompanyFootInfo footInfo=companyFootInfoServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		if(null!=footInfo){
			companyInfo.setId(footInfo.getId());
			companyFootInfoServiceImpl.update(companyInfo);
		}else{
//			companyInfo.setCompanyId(WebUtils.getCurrentCompanyId());
//			companyFootInfoServiceImpl.insert(companyInfo);
		}
		return "success";
	}
	
}
