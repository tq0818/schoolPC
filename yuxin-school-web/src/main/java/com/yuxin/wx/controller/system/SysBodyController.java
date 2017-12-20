package com.yuxin.wx.controller.system;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.company.ICompanyIndexModelClasstypeService;
import com.yuxin.wx.api.company.ICompanyIndexModelCustomService;
import com.yuxin.wx.api.company.ICompanyIndexModelItemService;
import com.yuxin.wx.api.company.ICompanyIndexModelNewsService;
import com.yuxin.wx.api.company.ICompanyIndexModelPrivatepageService;
import com.yuxin.wx.api.company.ICompanyIndexTemplateService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyPicsService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysBodyService;
import com.yuxin.wx.api.system.ISysConfigIndexClasstypeService;
import com.yuxin.wx.api.system.ISysConfigIndexCustomService;
import com.yuxin.wx.api.system.ISysConfigIndexItemService;
import com.yuxin.wx.api.system.ISysConfigIndexModelService;
import com.yuxin.wx.api.system.ISysConfigIndexNewsService;
import com.yuxin.wx.api.system.ISysConfigIndexPrivatepageService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigPageRedirectService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.common.CacheService;
import com.yuxin.wx.common.CacheService.Ckey;
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.common.Constants;
import com.yuxin.wx.common.Themes;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyIndexModelClasstype;
import com.yuxin.wx.model.company.CompanyIndexModelCustom;
import com.yuxin.wx.model.company.CompanyIndexModelItem;
import com.yuxin.wx.model.company.CompanyIndexModelNews;
import com.yuxin.wx.model.company.CompanyIndexModelPrivatepage;
import com.yuxin.wx.model.company.CompanyIndexTemplate;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyPics;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigPageRedirect;
import com.yuxin.wx.system.impl.SysConfigIndexModelServiceImpl;
import com.yuxin.wx.util.ImageUtils;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.CompanyIndexModelPrivatepageVo;
import com.yuxin.wx.vo.company.CompanyPicsVo;
import com.yuxin.wx.vo.system.SysConfigPrivatePageVo;

@Controller
@RequestMapping("/sysBody")
public class SysBodyController {
	private Log log = LogFactory.getLog("log");
	
	@Autowired
	private ISysConfigPageRedirectService sysConfigPageRedirectServiceImpl;
	
	@Autowired
	private ICompanyIndexModelClasstypeService companyIndexModelClasstypeServiceImpl; 
	@Autowired
	private ICompanyIndexModelCustomService companyIndexModelCustomServiceImpl; 
	@Autowired
	private ICompanyIndexModelItemService companyIndexModelItemServiceImpl; 
	@Autowired
	private ICompanyIndexModelNewsService companyIndexModelNewsServiceImpl; 
	@Autowired
	private ICompanyIndexModelPrivatepageService companyIndexModelPrivatepageServiceImpl; 
	@Autowired
	private ICompanyIndexTemplateService companyIndexTemplateServiceImpl; 
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private ISysConfigIndexClasstypeService sysConfigIndexClasstypeServiceImpl; 
	@Autowired
	private ISysConfigIndexCustomService sysConfigIndexCustomServiceImpl;
	@Autowired
	private ISysConfigIndexItemService sysConfigIndexItemServiceImpl;
	@Autowired
	private ISysConfigIndexNewsService sysConfigIndexNewsServiceImpl;
	@Autowired
	private ISysConfigIndexPrivatepageService sysConfigIndexPrivatepageServiceImpl;
	@Autowired
	private ISysConfigIndexModelService sysConfigIndexModelServiceImpl;
	@Autowired
	private ISysBodyService sysBodySerivceImpl;
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	@Autowired
	private ICompanyPicsService companyPicsServiceImpl;
	@Autowired
	private ISysConfigSchoolService sysConfigSchoolServiceImpl;
	@Autowired
	private IAuthRoleService authRoleServiceImpl;

	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 显示主页
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "show")
	public String show(Model model){
		model.addAttribute("schoolId",WebUtils.getCurrentSchoolId());
		model.addAttribute("schoolName",WebUtils.getCurrentUser().getSchoolName());
		if(authRoleServiceImpl.hasRoleFlag(WebUtils.getCurrentUser().getId(),WebUtils.getCurrentCompanyId())){
			model.addAttribute("isAdmin",true);
		}else{
			model.addAttribute("isSubAdmin",true);
		}
		Company company = companyService.findCompanyById(WebUtils.getCurrentCompanyId());
		model.addAttribute("company", company);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		
		return "/system/sysBody";
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 加载模板
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "load", method=RequestMethod.POST)
	public Object load(HttpServletRequest request,Integer schoolId){
		Map<String,Object> result = new HashMap<String,Object>();
		List<CompanyIndexTemplate> templates=new ArrayList<CompanyIndexTemplate>();
		templates=companyIndexTemplateServiceImpl.findTemplateByCompany(WebUtils.getCurrentCompanyId(),schoolId);
		result.put("own", templates);
		List<CompanyIndexTemplate> sys=companyIndexTemplateServiceImpl.findTemplateByCompanyDesc(0,0);
		result.put("system", sys);
		return result;
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 加载模板详细页
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "loadPage/{schoolId}/{templateId}", method=RequestMethod.POST)
	public List<CompanyIndexModelPrivatepageVo> loadPage(HttpServletRequest request,@PathVariable Integer schoolId,@PathVariable Integer templateId){
		List<CompanyIndexModelPrivatepageVo> pages=new ArrayList<CompanyIndexModelPrivatepageVo>();
		CompanyIndexModelPrivatepage search =new CompanyIndexModelPrivatepage();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(schoolId);
		search.setTemplateId(templateId);
		pages=companyIndexModelPrivatepageServiceImpl.findCompanyConfigList(search);
		return pages;
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 加载模板详细页
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addPage", method=RequestMethod.POST)
	public CompanyIndexModelPrivatepage addPage(HttpServletRequest request,CompanyIndexModelPrivatepage page){
		Integer config_id=null;
		String[] title={"单图课程","两图课程","三图课程","四图课程","五图课程","自定义内容","八图课程","新闻列表"};
		if("INDEX_ITEM".equals(page.getType())){
			CompanyIndexModelItem item=new CompanyIndexModelItem();
			if(page.getModelId()!=null){
				item.setTitle(title[page.getModelId()-1]);
			}
			companyIndexModelItemServiceImpl.insert(item);
			config_id=item.getId();
		}
		if("INDEX_CLASSTYPE".equals(page.getType())){
			CompanyIndexModelClasstype classType=new CompanyIndexModelClasstype();
			if(page.getModelId()!=null){
				classType.setTitle(title[page.getModelId()-1]);
			}
			companyIndexModelClasstypeServiceImpl.insert(classType);
			config_id=classType.getId();
		}
		if("INDEX_CUSTOM".equals(page.getType())){
			CompanyIndexModelCustom custom=new CompanyIndexModelCustom();
			if(page.getModelId()!=null){
				custom.setTitle(title[page.getModelId()-1]);
			}
			companyIndexModelCustomServiceImpl.insert(custom);
			config_id=custom.getId();
		}
		if("INDEX_NEWS".equals(page.getType())){
			CompanyIndexModelNews news= new CompanyIndexModelNews();
			if(page.getModelId()!=null){
				news.setTitle(title[page.getModelId()-1]);
			}
			companyIndexModelNewsServiceImpl.insert(news);
			config_id=news.getId();
		}
		page.setCompanyId(WebUtils.getCurrentCompanyId());	
		page.setConfigId(config_id);
		companyIndexModelPrivatepageServiceImpl.insert(page);
		return page;
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 加载模板详细页
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "editPage", method=RequestMethod.POST)
	public String editPage(HttpServletRequest request,CompanyIndexModelPrivatepage page,String type){
		CompanyIndexModelItem item=JSONObject.parseObject(request.getParameter("items"),CompanyIndexModelItem.class);
		CompanyIndexModelClasstype classType=JSONObject.parseObject(request.getParameter("classType"),CompanyIndexModelClasstype.class);
		CompanyIndexModelCustom custom=JSONObject.parseObject(request.getParameter("custom"),CompanyIndexModelCustom.class);
		CompanyIndexModelNews news=JSONObject.parseObject(request.getParameter("news"),CompanyIndexModelNews.class);
		if("INDEX_ITEM".equals(type)){
			companyIndexModelItemServiceImpl.update(item);
		}
		if("INDEX_CLASSTYPE".equals(type)){
			companyIndexModelClasstypeServiceImpl.update(classType);
		}
		if("INDEX_CUSTOM".equals(type)){
			companyIndexModelCustomServiceImpl.update(custom);
			companyIndexModelPrivatepageServiceImpl.update(page);
		}
		if("INDEX_NEWS".equals(type)){
			companyIndexModelNewsServiceImpl.update(news);
		}
	
		return "success";
	}
	
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 加载模板详细页
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delPage/{id}", method=RequestMethod.POST)
	public CompanyIndexModelPrivatepage delPage(HttpServletRequest request,@PathVariable Integer id){
		CompanyIndexModelPrivatepage page=companyIndexModelPrivatepageServiceImpl.findCompanyIndexModelPrivatepageById(id);
		if(page.getConfigId()!=null){
			if("INDEX_CLASSTYPE".equals(page.getType())){
				companyIndexModelClasstypeServiceImpl.deleteCompanyIndexModelClasstypeById(page.getConfigId());
			}
			if("INDEX_ITEM".equals(page.getType())){
				companyIndexModelItemServiceImpl.deleteCompanyIndexModelItemById(page.getConfigId());
			}
			if("INDEX_NEWS".equals(page.getType())){
				companyIndexModelNewsServiceImpl.deleteCompanyIndexModelNewsById(page.getConfigId());
			}
			if("INDEX_CUSTOM".equals(page.getType())){
				companyIndexModelCustomServiceImpl.deleteCompanyIndexModelCustomById(page.getConfigId());
			}
		}
		companyIndexModelPrivatepageServiceImpl.deleteCompanyIndexModelPrivatepageById(id);
		return page;
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 加载模板详细页
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updatePage", method=RequestMethod.POST)
	public String updatePage(HttpServletRequest request){
		List<CompanyIndexModelPrivatepage> pages=JSONArray.parseArray(request.getParameter("models"), CompanyIndexModelPrivatepage.class);
		for(CompanyIndexModelPrivatepage page : pages){
			companyIndexModelPrivatepageServiceImpl.update(page);
		}
		return "success";
	}
	
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 跳转新增页
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "check", method=RequestMethod.POST)
	public Boolean check(Model model , Integer schoolId,String templateName){
		model.addAttribute("schoolId", schoolId);
		CompanyIndexTemplate template=new CompanyIndexTemplate();
		template.setCompanyId(WebUtils.getCurrentCompanyId());
		template.setSchoolId(schoolId);
		template.setTemplateName(templateName);
		return companyIndexTemplateServiceImpl.checkByName(template);
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 跳转新增页
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addModule", method=RequestMethod.POST)
	public CompanyIndexTemplate addModule(Model model , Integer schoolId,String templateName){
		model.addAttribute("schoolId", schoolId);
		CompanyIndexTemplate template=new CompanyIndexTemplate();
		template.setCompanyId(WebUtils.getCurrentCompanyId());
		template.setSchoolId(schoolId);
		template.setTemplateStatus("0");
		template.setTemplateName(templateName);
		companyIndexTemplateServiceImpl.insert(template);
		return template;
	}
	
	@RequestMapping(value = "newModule", method=RequestMethod.POST)
	public String newModule(Model model ,CompanyIndexTemplate template){
		model.addAttribute("template", template);
		return "/system/addSysBody";
	}
	
	
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 跳转编辑页
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "editModule")
	public String editModule(Model model,Integer id){
		model.addAttribute("template", companyIndexTemplateServiceImpl.findCompanyIndexTemplateById(id));
		return "/system/addSysBody";
	}
	
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 删除
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delModule/{id}", method=RequestMethod.POST)
	public String  delModule(HttpServletRequest request,@PathVariable Integer id){
		companyIndexTemplateServiceImpl.deleteCompanyIndexTemplateById(id);
		return "success";
	}
	
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 使用模板
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "useTemplate", method=RequestMethod.POST)
	public String useTemplate(HttpServletRequest request,Integer id,Integer schoolId){
		companyIndexTemplateServiceImpl.useTemplate(WebUtils.getCurrentCompanyId(), schoolId, id);
		List<SysConfigPrivatePageVo>list=sysBodySerivceImpl.publishToFront(WebUtils.getCurrentCompanyId(), schoolId, id);
		Ckey ckey=new Ckey();
		ckey.setModule(Constants.MODULE_BODY);
		ckey.setCompanyId(""+WebUtils.getCurrentCompanyId());
		ckey.setSchoolId(""+schoolId);
		CacheService.updateCache(ckey,list);
		
		//禁用所有主题
		SysConfigPageRedirect redirect=new SysConfigPageRedirect();
		redirect.setCompanyId(WebUtils.getCurrentCompanyId());
		redirect.setSchoolId(schoolId);
		redirect.setStatus(0);
		redirect.setBussinessType("REDIRECT_FRONT_INDEX");
		sysConfigPageRedirectServiceImpl.updateByTempleteId(redirect);
		
		return "success";
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 使用模板
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update", method=RequestMethod.POST)
	public String userModule(HttpServletRequest request,CompanyIndexTemplate template){
		companyIndexTemplateServiceImpl.update(template);
		return "success";
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 使用模板
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "publish", method=RequestMethod.POST)
	public String publish(HttpServletRequest request,CompanyIndexTemplate template){
		//从company表复制到sys
		List<SysConfigPrivatePageVo> list=sysBodySerivceImpl.publishToFront(WebUtils.getCurrentCompanyId(), template.getSchoolId(), template.getId());
		Ckey ckey=new Ckey();
		ckey.setModule(Constants.MODULE_BODY);
		ckey.setCompanyId(""+WebUtils.getCurrentCompanyId());
		ckey.setSchoolId(""+WebUtils.getCurrentSchoolId());
		ckey.setTag("");
		CacheService.updateCache(ckey,list);
		return "success";
	}
	
	/**
	 * 保存图片
	 * @param multiPartRquest
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/savePic",method=RequestMethod.POST)
	public String queryPic(MultipartRequest multiPartRquest,HttpServletRequest req){
		MultipartFile multipartFile = multiPartRquest.getFile("imgData");
		String realPath=null;
		try {
			realPath = FileUtil.upload(multipartFile, "temp", WebUtils.getCurrentCompanyId()+"");
		} catch (Exception e) {
			log.error("文件上传失败!",e);
			e.printStackTrace();
		}
		String url="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
		return "{\"url\":\""+url+"\"}";
	}
	
	
	/**
	 * 
	 * Class Name: SysCyclePicController.java
	 * @Description: 上传自定义图片
	 * @author zhang.zx
	 * @date 2015年6月2日 下午2:35:50
	 * @modifier
	 * @modify-date 2015年6月2日 下午2:35:50
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/uploadPics",method=RequestMethod.POST)
	public String UploadPics(MultipartRequest multiPartRquest,HttpServletRequest req){
		String realPath=null;
		String picPath=null;
		MultipartFile	multipartFile = multiPartRquest.getFile("imgData");
		req.getSession().setAttribute("file", multipartFile);
		String name=multipartFile.getOriginalFilename();
		if(name!=null&&!"".equals(name)){
			try {
				realPath = FileUtil.upload(multipartFile, "sysbody", WebUtils.getCurrentCompanyId()+"");
			} catch (Exception e) {
				log.error("文件上传失败",e);
				e.printStackTrace();
				return "{\"url\":\"null\",\"picPath\":\"null\"}";
			}
		}
		picPath="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
		return "{\"url\":\""+picPath+"\",\"picPath\":\""+realPath+"\"}";
	}
	
	/**
	 * 获取上传进度
	 * 
	 * @param file
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "process/{name}")
	public Long process(HttpSession session,@PathVariable String name, HttpServletResponse response) {
//		System.out.println("process get:" + session.getId());
//		// 上传进度百分比
		long processPercent = 0;
		CommonsMultipartFile file = (CommonsMultipartFile) session.getAttribute(name);
		if (file == null) {
			return null;
		}
		long totalFileSize = file.getSize();
		long readedFileSize = file.getFileItem().getSize();

		System.out.println("totalFileSize:" + totalFileSize
				+ ",readedFileSize:" + readedFileSize);

		if (totalFileSize != 0) {
			processPercent = Math.round(readedFileSize / totalFileSize) * 100;
		}
		
//		final MultipartFile uploadFile = (MultipartFile) session.getAttribute(name);
//		Long process=FileUtil.uploadProcess(file, module, WebUtils.getCurrentCompanyId()+"");
//		System.out.println(process+"%");
//		if(process!=null){
//			return process;
//		}else{
//			return 0L;
//		}
		
//		try{
//		InputStream input = uploadFile.getInputStream();
//		InputStream tmpInput = input;
//		System.out.println("进度:"+(uploadFile.getSize() - tmpInput.available()) / uploadFile.getSize());
//		return (uploadFile.getSize() - tmpInput.available()) / uploadFile.getSize();
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
		return processPercent;
	}
	
	/**
	 * 
	 * Class Name: ClassTypeController.java
	 * @Description: 保存切图
	 * @author chopin
	 * @date 2015年5月5日 下午2:00:14
	 * @modifier
	 * @modify-date 2015年5月5日 下午2:00:14
	 * @version 1.0
	 * @param classtype
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveCutPic",method=RequestMethod.POST)
	public CompanyPicsVo saveCutPic(HttpServletRequest request){
		log.info("初始化截图开始===============");
		Resource resource = new ClassPathResource("config.properties");
		Properties props=null;
		try{
			props=PropertiesLoaderUtils.loadProperties(resource);
		}catch(Exception e){
			log.error(e,e);
			e.printStackTrace();
		}
		String path=request.getParameter("path");
		String fileName=path.substring(path.lastIndexOf("/")+1);
		String tempPath=props.getProperty("server.imageupload.tempPath")+"/source/"+fileName;
		String target=props.getProperty("server.imageupload.tempPath")+"/target/"+fileName;
		String header="http://"+props.getProperty("yunduoketang.oss.imagedomain")+"/";
		
		path=path.replace(header, "");
		System.out.println("oss临时文件路径["+path+"]=====本地磁盘临时文件路径["+tempPath+"]======切图后临时文件路径["+target+"]");
		FileUtil.download("temp", path,tempPath);
		//选中尺寸
		double x=Double.parseDouble(request.getParameter("x"));
		double y=Double.parseDouble(request.getParameter("y"));
		double w=Double.parseDouble(request.getParameter("w"));
		double h=Double.parseDouble(request.getParameter("h"));
		BufferedImage img =null;
		try{
			 img = ImageIO.read(new File(tempPath));
		}catch(Exception e){
			log.error("读取图片失败:"+e);
			e.printStackTrace();
		}
		
		//原图尺寸
		double realW=img.getWidth();
		double realH=img.getHeight();
		//示例图尺寸
		double slW=0;
		double slH=0;
		if(realW/realH>516.00/282.00){
			//过宽
			slH=516 * realH/realW;
			slW=516;
		}else{
			//过高
			slH=282;
			slW=282 * realW/realH;
		}
		//原图所选中位置和区域
		
		int xx=(new   Double(x*realW/slW)).intValue();	
		int yy=(new   Double(y*realH/slH)).intValue();
		int ww=(new   Double(w*realW/slW)).intValue();
		int hh=(new   Double(h*realH/slH)).intValue();
		System.out.println("选中区域:["+x+","+y+","+w+","+h+"]----原图选中区域:["+xx+","+yy+","+ww+","+hh+"]");
		//在原图中切图
		String cutImgPath=ImageUtils.cutImage(tempPath,target,xx,yy,ww,hh);
		//切好的图缩放到规定比例
		ImageUtils.resize(target,target,446);
		log.info("截图完成===============");
		log.info("上传图片开始===============");
		String realPath=null;
		try {
			realPath=FileUtil.upload(cutImgPath,"course", WebUtils.getCurrentCompanyId()+"");
		} catch (Exception e) {
			log.error("上传文件失败",e);
			e.printStackTrace();
		}
		log.info("上传图片后路径==============="+realPath);
		FileUtil.deleteFile(target);
		FileUtil.deleteFile(cutImgPath);
		
		List<SysConfigItem> list=sysConfigItemServiceImpl.findItemByCompanyId(1, WebUtils.getCurrentCompanyId());
		
		int itemOneid=list.get(0).getCompanyId();
		
		CompanyPics pic=new CompanyPics();
		pic.setItemOneId(itemOneid);
		pic.setCompanyId(WebUtils.getCurrentCompanyId());
		pic.setPicName("");
		pic.setPicType("classtype");
		pic.setPicOriginalUrl(realPath);
		//存库
		companyPicsServiceImpl.insert(pic);
		
		String picUrl="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
		log.info("图片回显路径==============="+picUrl);
		CompanyPicsVo pics=new CompanyPicsVo();
		pics.setPicOriginalUrl(picUrl);
		pics.setRealPath(realPath);
		
		return pics;
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 加入到我的模板列表
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "intoMine/{id}/{schoolId}", method=RequestMethod.POST)
	public CompanyIndexTemplate intoMine(HttpServletRequest request,@PathVariable Integer id,@PathVariable Integer schoolId){
		CompanyIndexTemplate template=null;
		if(schoolId!=null){
			template=companyIndexTemplateServiceImpl.copyToCompany(WebUtils.getCurrentCompanyId(),schoolId,id);
		}else{
			template=companyIndexTemplateServiceImpl.copyToCompany(WebUtils.getCurrentCompanyId(),WebUtils.getCurrentSchoolId(),id);
		}
		return template;
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 加入到我的模板列表
	 * @author chopin
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "cpTempleteToCompany/{companyId}/{id}", method=RequestMethod.POST)
	public CompanyIndexTemplate cpTempleteToCompany(HttpServletRequest request,@PathVariable Integer id,@PathVariable Integer companyId){
		Integer schoolId=sysConfigSchoolServiceImpl.findDefaultSchool(companyId);
		CompanyIndexTemplate template=companyIndexTemplateServiceImpl.copyToCompany2(companyId,schoolId,id);
		//使用此模板
		useTemplate(request,template.getId(),schoolId);
		return template;
	}
	
	@RequestMapping("/preview/{templateId}")
	public String perview(Model model,@PathVariable Integer templateId){
		model.addAttribute("schoolId", WebUtils.getCurrentCompanyId());
		model.addAttribute("templateId",templateId);
		return "/system/perview_index";
	}
	
	/**
	 * 首页配置
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/loadBody"}) 
	public Map<String,String> loadBody(HttpServletRequest request,Integer templateId){
		CompanyIndexModelPrivatepage search=new CompanyIndexModelPrivatepage();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		search.setTemplateId(templateId);
		List<CompanyIndexModelPrivatepageVo> pages=companyIndexModelPrivatepageServiceImpl.findByTemplate(search);
		List<String> list=new ArrayList<String>();
		for(CompanyIndexModelPrivatepageVo page: pages){
			if("INDEX_ITEM".equals(page.getType())){
				CompanyIndexModelItem item =companyIndexModelItemServiceImpl.findByConfigId(page.getConfigId());
				list.add(JSONObject.toJSONString(item));
			}
			if("INDEX_CLASSTYPE".equals(page.getType())){
				CompanyIndexModelClasstype classType=companyIndexModelClasstypeServiceImpl.findCompanyIndexModelClasstypeById(page.getConfigId());
				list.add(JSONArray.toJSONString(classType));
			}
			if("INDEX_CUSTOM".equals(page.getType())){
				CompanyIndexModelCustom custom=companyIndexModelCustomServiceImpl.findCompanyIndexModelCustomById(page.getConfigId());
				list.add(JSONArray.toJSONString(custom));
			}
			if("INDEX_NEWS".equals(page.getType())){
				CompanyIndexModelNews news=companyIndexModelNewsServiceImpl.findCompanyIndexModelNewsById(page.getConfigId());
				list.add(JSONArray.toJSONString(news));
			}

		}
		StringBuffer jsonArray=new StringBuffer();
		jsonArray.append("[");
		for(int i=0;i<list.size();i++){
			String l=list.get(i);
			if(i+1==list.size()){
				jsonArray.append(l);
			}else{
				jsonArray.append(l).append(",");
			}
		}
		jsonArray.append("]");
		String userPages=JSONArray.toJSONString(pages);
		String models=JSONArray.toJSONString(sysConfigIndexModelServiceImpl.findAll());
		String configs=jsonArray.toString();
		Map<String,String> map=new HashMap<String,String>();
		map.put("pages", userPages);
		map.put("models", models);
		map.put("configs", configs);
		return map;
	}
	
	/**
	 * 
	 * Class Name: SysBodyController.java
	 * @Description: 公开课模板配置
	 * @author 周文斌
	 * @date 2015-10-10 下午3:05:12
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "openClassOption")
	public String openClassOption(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		model.addAttribute("schoolId",WebUtils.getCurrentSchoolId());
		model.addAttribute("schoolName",WebUtils.getCurrentUser().getSchoolName());
		Subject subject = SecurityUtils.getSubject();
		model.addAttribute("isAdmin",subject.hasRole("机构管理员"));
		model.addAttribute("isSubAdmin",subject.hasRole("分校管理员"));
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "/system/openClassOption";
	}
	
	/**
	 * 
	 * Class Name: SysBodyController.java
	 * @Description: 查询使用模板
	 * @author 周文斌
	 * @date 2015-10-10 下午3:10:36
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selTemplate")
	public JSONObject selTemplate(HttpServletRequest request,Integer schoolId){
		JSONObject json = new JSONObject();
		SysConfigPageRedirect scpr = new SysConfigPageRedirect();
		scpr.setCompanyId(WebUtils.getCurrentCompanyId());
		scpr.setSchoolId(schoolId);
		scpr.setBussinessType("REDIRECT_FRONT_OPENCLASS");
		scpr.setSysType(0);
		try {
			SysConfigPageRedirect rect = sysConfigPageRedirectServiceImpl.findPageRedirect(scpr);
			if(rect != null){
				json.put("id", rect.getTemplateId());
			}
			json.put("msg", "success");
			return json;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put("msg", "出错了");
			return json;
		}
	}
	
	/**
	 * 
	 * Class Name: SysBodyController.java
	 * @Description: 使用公开课模板
	 * @author 周文斌
	 * @date 2015-10-10 下午3:24:00
	 * @version 1.0
	 * @param request
	 * @param templateId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/usesTemplate")
	public JSONObject usesTemplate(HttpServletRequest request,Integer templateId,Integer schoolId){
		JSONObject json = new JSONObject();
		SysConfigPageRedirect scpr = new SysConfigPageRedirect();
		scpr.setCompanyId(WebUtils.getCurrentCompanyId());
		scpr.setSchoolId(schoolId);
		scpr.setBussinessType("REDIRECT_FRONT_OPENCLASS");
		scpr.setSysType(0);
		try {
			SysConfigPageRedirect rect = sysConfigPageRedirectServiceImpl.findPageRedirect(scpr);
			if(rect != null){
				rect.setTemplateId(templateId);
				sysConfigPageRedirectServiceImpl.update(rect);
			}else{
				scpr.setTemplateId(templateId);
				scpr.setStatus(1);
				scpr.setLink("openClass/openClass");
				sysConfigPageRedirectServiceImpl.insert(scpr);
			}
			json.put("msg", "success");
			return json;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put("msg", "出错了");
			return json;
		}
	}
	
	/**
	 * 
	 * Class Name: SysBodyController.java
	 * @Description: 查询使用主题
	 * @author chopin
	 * @date 2015-10-10 下午3:10:36
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/useTheme")
	public String useTheme(HttpServletRequest request,Integer templeteId,Integer schoolId){
		SysConfigPageRedirect redirect=new SysConfigPageRedirect();
		redirect.setCompanyId(WebUtils.getCurrentCompanyId());
		redirect.setSchoolId(schoolId);
		

		//禁用其他模板
		redirect.setStatus(0);
		redirect.setBussinessType("REDIRECT_FRONT_INDEX");
		sysConfigPageRedirectServiceImpl.updateByTempleteId(redirect);
		
		redirect.setTemplateId(templeteId);
		SysConfigPageRedirect isExists=sysConfigPageRedirectServiceImpl.findPageRedirect(redirect);
		redirect.setStatus(1);
		
		if(templeteId.equals(Themes.ELE_SUPP_1)){
			redirect.setLink(Themes.ELE_SUPP_1_LINK);
		}
		if(templeteId.equals(Themes.ELE_SUPP_2)){
			redirect.setLink(Themes.ELE_SUPP_2_LINK);
		}
		if(templeteId.equals(Themes.CLASSIC_1)){
			redirect.setLink(Themes.CLASSIC_1_LINK);
		}
		if(templeteId.equals(Themes.EDUSOHO)){
			redirect.setLink(Themes.EDUSOHO_LINK);
		}
		if(templeteId.equals(Themes.FASHION)){
			redirect.setLink(Themes.FASHION_LINK);
		}
		redirect.setSysType(0);
		//使用当前模板
		if(isExists!=null){
			redirect.setId(isExists.getId());
			sysConfigPageRedirectServiceImpl.updateByTempleteId(redirect);
			
		}else{
			sysConfigPageRedirectServiceImpl.insert(redirect);
		}
		
		companyIndexTemplateServiceImpl.unUseTemplate(WebUtils.getCurrentCompanyId(), schoolId);
		return "success";
		
		
	}
	
	
	/**
	 * 
	 * Class Name: SysBodyController.java
	 * @Description: 查询使用中的主题
	 * @author chopin
	 * @date 2015-10-10 下午3:10:36
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/usedTheme")
	public SysConfigPageRedirect usedTheme(HttpServletRequest request){
		SysConfigPageRedirect redirect=new SysConfigPageRedirect();
		redirect.setCompanyId(WebUtils.getCurrentCompanyId());
		Integer schoolId=request.getParameter("schoolId")!=null?Integer.parseInt(request.getParameter("schoolId")):WebUtils.getCurrentSchoolId();
		redirect.setSchoolId(schoolId);
		redirect.setStatus(1);
		redirect.setBussinessType("REDIRECT_FRONT_INDEX");
		SysConfigPageRedirect result=sysConfigPageRedirectServiceImpl.findPageRedirect(redirect);
		return result;
	}
	
	/**
	 * 
	 * Class Name: SysBodyController.java
	 * @Description: 公开课示例
	 * @author 周文斌
	 * @date 2015-10-10 下午3:36:05
	 * @version 1.0
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/Example/{id}")
	public String Example(Model model,@PathVariable Integer id){
		model.addAttribute("src", "/images/open_class_" + id + ".png");
		return "system/Example";
	}
	
	
	/**
	 * 
	 * Class Name: SysBodyController.java
	 * @Description: 公开课示例
	 * @author 周文斌
	 * @date 2015-10-10 下午3:36:05
	 * @version 1.0
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/courseDetailExample/{id}")
	public String courseDetailExample(Model model,@PathVariable Integer id){
		model.addAttribute("src", "/images/img-course" + id + ".png");
		return "system/Example";
	}
	
	@RequestMapping("/indexExample/{id}")
	public String indexExample(Model model,@PathVariable Integer id){
		model.addAttribute("src", "/images/index-" + id + ".png");
		return "system/Example";
	}
}
