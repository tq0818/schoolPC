package com.yuxin.wx.controller.system;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
import com.yuxin.wx.api.company.ICompanyIndexModelClasstypeService;
import com.yuxin.wx.api.company.ICompanyIndexModelCustomService;
import com.yuxin.wx.api.company.ICompanyIndexModelItemService;
import com.yuxin.wx.api.company.ICompanyIndexModelNewsService;
import com.yuxin.wx.api.company.ICompanyIndexModelPrivatepageService;
import com.yuxin.wx.api.company.ICompanyIndexTemplateService;
import com.yuxin.wx.api.company.ICompanyPicsService;
import com.yuxin.wx.api.system.ISysBodyService;
import com.yuxin.wx.api.system.ISysConfigIndexClasstypeService;
import com.yuxin.wx.api.system.ISysConfigIndexCustomService;
import com.yuxin.wx.api.system.ISysConfigIndexItemService;
import com.yuxin.wx.api.system.ISysConfigIndexNewsService;
import com.yuxin.wx.api.system.ISysConfigIndexPrivatepageService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.common.CacheService;
import com.yuxin.wx.common.CacheService.Ckey;
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.common.Constants;
import com.yuxin.wx.model.company.CompanyIndexModelClasstype;
import com.yuxin.wx.model.company.CompanyIndexModelCustom;
import com.yuxin.wx.model.company.CompanyIndexModelItem;
import com.yuxin.wx.model.company.CompanyIndexModelNews;
import com.yuxin.wx.model.company.CompanyIndexModelPrivatepage;
import com.yuxin.wx.model.company.CompanyIndexTemplate;
import com.yuxin.wx.model.company.CompanyPics;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.util.ImageUtils;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.CompanyIndexModelPrivatepageVo;
import com.yuxin.wx.vo.company.CompanyPicsVo;
import com.yuxin.wx.vo.system.SysConfigPrivatePageVo;

@Controller
@RequestMapping("/sysconfig")
public class SysConfigController {
	private Log log = LogFactory.getLog("log");
	
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
	private ISysBodyService sysBodySerivceImpl;
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	@Autowired
	private ICompanyPicsService companyPicsServiceImpl;
	@Autowired
	private ISysConfigSchoolService sysConfigSchoolServiceImpl;
	
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
	@RequestMapping(value = "/page/{name}")
	public String show(Model model,@PathVariable String name){
		return "/system/config/"+name;
	}
	
	
}
