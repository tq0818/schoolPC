package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

import com.yuxin.wx.api.company.ICompanyMarketSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMarketSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyMarketSet
 *
 * @author chopin
 * @date 2015-8-3
 */
@Controller
@RequestMapping("/companyMarketSet")
public class CompanyMarketSetController {
	private Log log=LogFactory.getLog("log");
	@Autowired
	private ICompanyMarketSetService companyMarketSetServiceImpl;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	@Autowired
	private PropertiesUtil propertiesUtil;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyMarketSet search) {
		if (search == null) {
			search = new CompanyMarketSet();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyMarketSetServiceImpl.findCompanyMarketSetByPage(search));
		return "companyMarketSet/list";
	}

	/**
	 *
	 * Class Name: CompanyMarketSetController.java
	 *
	 * @Description: 跳转到营销页面
	 * @author zhang.zx
	 * @date 2015年8月4日 上午9:46:57
	 * @modifier
	 * @modify-date 2015年8月4日 上午9:46:57
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showCompanyMarket", method = RequestMethod.GET)
	public String showList(Model model) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyMarketSet comMark = companyMarketSetServiceImpl.findByCompanyId(companyId);
		model.addAttribute("comMark", comMark);
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company", company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		// return "system/marketing";
		return "system/marketing_index";
	}

	/**
	 *
	 * Class Name: CompanyMarketSetController.java
	 *
	 * @Description: 获取公司营销数据
	 * @author zhang.zx
	 * @date 2015年8月4日 下午2:54:37
	 * @modifier
	 * @modify-date 2015年8月4日 下午2:54:37
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/companyMarketData", method = RequestMethod.POST)
	public CompanyMarketSet loadMarkData() {
		CompanyMarketSet comMark = companyMarketSetServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		// if(null!=comMark){
		// if(comMark.getWeiboPic()!=null&&!"".equals(comMark.getWeiboPic())){
		// comMark.setWeiboPic("http://"+propertiesUtil.getProjectImageUrl()+"/"+comMark.getWeiboPic());
		// }
		// if(comMark.getWeixinPic()!=null&&!"".equals(comMark.getWeixinPic())){
		// comMark.setWeixinPic("http://"+propertiesUtil.getProjectImageUrl()+"/"+comMark.getWeixinPic());
		// }
		// }
		return comMark;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(CompanyMarketSet CompanyMarketSet) {
		companyMarketSetServiceImpl.insert(CompanyMarketSet);
		return "redirect:/companyMarketSet";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(CompanyMarketSet CompanyMarketSet) {
		companyMarketSetServiceImpl.update(CompanyMarketSet);
		return "redirect:/companyMarketSet";
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyMarketSetServiceImpl.deleteCompanyMarketSetById(id);
		return "redirect:/companyMarketSet";
	}

	@ResponseBody
	@RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
	public CompanyMarketSet getJson(Model model, @PathVariable Integer id) {
		return companyMarketSetServiceImpl.findCompanyMarketSetById(id);
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(Model model) {
		return "system/marketing";
	}

	/**
	 *
	 * Class Name: CompanyMarketSetController.java
	 *
	 * @Description: 更新营销设置信息
	 * @author zhang.zx
	 * @date 2015年8月4日 下午1:53:55
	 * @modifier
	 * @modify-date 2015年8月4日 下午1:53:55
	 * @version 1.0
	 * @param CompanyMarketSet
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateMarket",method = RequestMethod.POST)
	public CompanyMarketSet updateMarket(CompanyMarketSet companyMarketSet) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		companyMarketSet.setCompanyId(companyId);
		Integer id = companyMarketSet.getId();
		if (id != null && !"".equals(id)) {
			companyMarketSetServiceImpl.update(companyMarketSet);
		} else {
			companyMarketSetServiceImpl.insert(companyMarketSet);
		}
		return companyMarketSetServiceImpl.findByCompanyId(companyId);
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

	@ResponseBody
	@RequestMapping(value="/Uploadewm",method=RequestMethod.POST)
	public String UploadCycles(MultipartRequest multiPartRquest,HttpServletRequest req){
		log.info(req.getServerName()+"==============="+req.getSession().getId());
		Subject subject = SecurityUtils.getSubject();
		String realPath = null;
		String picPath = null;
		MultipartFile multipartFile = multiPartRquest.getFile("imgData");
		subject.getSession().setAttribute("imgData", multipartFile);
		String name = multipartFile.getOriginalFilename();
		if (name != null && !"".equals(name)) {
			try {
				realPath = FileUtil.upload(multipartFile, "company", WebUtils.getCurrentCompanyId() + "");
			} catch (Exception e) {
				log.error("文件上传失败",e);
				e.printStackTrace();
			}
		}
		picPath = "http://" + propertiesUtil.getProjectImageUrl() + "/" + realPath;
		return "{\"url\":\"" + picPath + "\",\"picPath\":\"" + realPath + "\"}";
	}

	@ResponseBody
	@RequestMapping(value="/Uploadxl",method=RequestMethod.POST)
	public String UploadCyclesxl(MultipartRequest multiPartRquest,HttpServletRequest req){
		log.info(req.getServerName()+"==============="+req.getSession().getId());
		Subject subject = SecurityUtils.getSubject();
		String realPath = null;
		String picPath = null;
		MultipartFile multipartFile = multiPartRquest.getFile("imgDatas");
		subject.getSession().setAttribute("imgDatas", multipartFile);
		String name = multipartFile.getOriginalFilename();
		if (name != null && !"".equals(name)) {
			try {
				realPath = FileUtil.upload(multipartFile, "company", WebUtils.getCurrentCompanyId() + "");
			} catch (Exception e) {
				log.error("文件上传失败",e);
				e.printStackTrace();
			}
		}
		picPath = "http://" + propertiesUtil.getProjectImageUrl() + "/" + realPath;
		return "{\"url\":\"" + picPath + "\",\"picPath\":\"" + realPath + "\"}";
	}

	// --------------------------菜单修改------------------------------------

	@RequestMapping(value = "/CompanyMarket_qq", method = RequestMethod.GET)
	public String showMarkQq(Model model) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyMarketSet comMark = companyMarketSetServiceImpl.findByCompanyId(companyId);
		model.addAttribute("comMark", comMark);
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company", company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "system/marketing_index";
	}

	@RequestMapping(value = "/CompanyMarket_wx", method = RequestMethod.GET)
	public String showMarkWx(Model model) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyMarketSet comMark = companyMarketSetServiceImpl.findByCompanyId(companyId);
		model.addAttribute("comMark", comMark);
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company", company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "system/marketing_wx";
	}

	@RequestMapping(value = "/CompanyMarket_wb", method = RequestMethod.GET)
	public String showMarkWb(Model model) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyMarketSet comMark = companyMarketSetServiceImpl.findByCompanyId(companyId);
		model.addAttribute("comMark", comMark);
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company", company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "system/marketing_wb";
	}

	@RequestMapping(value = "/CompanyMarket_kf", method = RequestMethod.GET)
	public String showMarkKf(Model model) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyMarketSet comMark = companyMarketSetServiceImpl.findByCompanyId(companyId);
		model.addAttribute("comMark", comMark);
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company", company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "system/marketing_kf";
	}

	@RequestMapping(value = "/CompanyMarket_sq", method = RequestMethod.GET)
	public String CompanyMarketSq(Model model) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyMarketSet comMark = companyMarketSetServiceImpl.findByCompanyId(companyId);
		model.addAttribute("comMark", comMark);
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company", company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "system/marketing_sq";
	}

	@ResponseBody
	@RequestMapping(value = "/findMarket", method = RequestMethod.POST)
	public CompanyMarketSet findMarket(Model model, HttpServletRequest request) {
		CompanyMarketSet cm = companyMarketSetServiceImpl.findCompanyMarketSetByCompanyId(WebUtils.getCurrentCompanyId());
		return cm;
	}

}
