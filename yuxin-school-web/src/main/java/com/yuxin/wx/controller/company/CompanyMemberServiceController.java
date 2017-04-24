package com.yuxin.wx.controller.company;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.company.*;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysConfigVideoPriceService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.CompanyServiceConstant;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.*;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.system.SysConfigVideoPrice;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.FileQNUtils;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.CompanyMemberServiceVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller of CompanyMemberService
 *
 * @author chopin
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/companyMemberService")
public class CompanyMemberServiceController {

	@Autowired
	private ICompanyLiveConcurrentService companyLiveConcurrentServiceImpl;

	@Autowired
	private ICompanyMemberServiceChangelogService companyMemberServiceChangelogServiceImpl;

	@Autowired
	private ICompanyServiceStaticDayService companyServiceStaticDayService;

	@Autowired
	private ISysConfigSchoolService sysConfigSchoolServiceImpl;

	@Autowired
	private IUsersService usersServiceImpl;

	@Autowired
	private ICompanyServiceStaticDayService dayService;

	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;

	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;

	@Autowired
	private ISysConfigVideoPriceService configVideoPriceService;

	@Autowired
	private ICompanyService companyServiceImpl;

	@Autowired
	private IAuthRoleService authRoleServiceImpl;

	@Autowired
	private PropertiesUtil properties;

	Log log = LogFactory.getLog("log");

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyMemberService search) {
		if (search == null) {
			search = new CompanyMemberService();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyMemberServiceServiceImpl.findCompanyMemberServiceByPage(search));
		return "companyMemberService/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(CompanyMemberService CompanyMemberService) {
		companyMemberServiceServiceImpl.insert(CompanyMemberService);
		return "redirect:/companyMemberService";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(CompanyMemberService CompanyMemberService) {
		companyMemberServiceServiceImpl.update(CompanyMemberService);
		return "redirect:/companyMemberService";
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyMemberServiceServiceImpl.deleteCompanyMemberServiceById(id);
		return "redirect:/companyMemberService";
	}

	@ResponseBody
	@RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
	public CompanyMemberService getJson(Model model, @PathVariable Integer id) {
		return companyMemberServiceServiceImpl.findCompanyMemberServiceById(id);
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
	 * 周文斌 查询公司信息 以及服务 记录
	 */
	private Map<String, Object> selCompay(Integer companyId) {
		// 查询公司信息
		Company company = getCompany(companyId);
		// 公司拥有的服务
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		// 公司 已使用的服务
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		double usedVideo = (css.getVideoStorage() != null ? css.getVideoStorage() : 0.0);
		long crs = 0;
		usedVideo += FileQNUtils.convertFileSize(crs);
		usedVideo = new BigDecimal(usedVideo).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		double cvf = (css.getVideoFlow() != null ? css.getVideoFlow() : 0.0);
		long crf = Long.parseLong(css.getResourceFlow() != null ? css.getResourceFlow() : "0");
		cvf += FileQNUtils.convertFileSize(crf);

		cvf = new BigDecimal(cvf).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);
		/*
		 * company 是公司信息 cms 是 公司已购买的服务 css 是公司 已使用的服务
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("company", company);
		map.put("cms", cms);
		map.put("css", css);
		map.put("cvs", usedVideo);
		map.put("cvf", cvf);
		return map;
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: (查询公司信息)
	 * @author zwb
	 * @date 2015-4-29 下午2:50:56
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	private Company getCompany(Integer companyId) {
		// 查询公司信息
		return companyServiceImpl.findCompanyById(companyId);
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 录播模块下的视频统计
	 * @author ycl
	 * @date 2015-5-21 下午12:27:33
	 * @modifier
	 * @modify-date 2015-5-21 下午12:27:33
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toVideoStatistics")
	public String toVideoStatistics(Model model) {
		Map<String, Object> companyMap = selCompay(WebUtils.getCurrentCompanyId());
		model.addAllAttributes(companyMap);

		List<CompanyServiceStaticDay> dayList = dayService.findInfoByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("dayList", dayList);

		return "company/videoStatistics";
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 异步加载流量图表
	 * @author ycl
	 * @date 2015-5-23 下午4:56:25
	 * @modifier
	 * @modify-date 2015-5-23 下午4:56:25
	 * @version 1.0
	 * @param model
	 * @param cssday
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/toVsAjaxInfo")
	public Map<String, Object> toVsAjaxInfo(Model model, CompanyServiceStaticDay cssday) {
		Map<String, Object> res = new HashMap<String, Object>();
		cssday.setCompanyId(WebUtils.getCurrentCompanyId());
		if (cssday.getEndTime() == null || cssday.getEndTime() == "") {
			Date date = new Date();
			cssday.setEndTime(DateUtil.formatDateByFormat(date, "yyyy-MM-dd"));
		}
		if (cssday.getStartTime() == null || cssday.getStartTime() == "") {
			Date date = new Date();
			Date agoDate = DateUtil.addDate(date, -15);
			cssday.setStartTime(DateUtil.formatDateByFormat(agoDate, "yyyy-MM-dd"));
		}

		List<CompanyServiceStaticDay> newCDay = new ArrayList<CompanyServiceStaticDay>();
		List<CompanyServiceStaticDay> cssd = companyServiceStaticDayService.findInfoByDate(cssday);
		for (CompanyServiceStaticDay day : cssd) {
			if (day.getVideoTotalFlow() == null) {
				day.setVideoTotalFlow(0.0);
			}
			newCDay.add(day);
		}
		res.put("flow", newCDay);
		return res;
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 链接到邮件统计图表
	 * @author ycl
	 * @date 2015-5-23 下午5:00:52
	 * @modifier
	 * @modify-date 2015-5-23 下午5:00:52
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toEmailStatistics")
	public String toEmailStatistics(Model model) {
		Map<String, Object> companyMap = selCompay(WebUtils.getCurrentCompanyId());
		model.addAllAttributes(companyMap);

		return "company/emailStatistics";
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 异步加载邮件图表
	 * @author ycl
	 * @date 2015-5-23 下午5:01:14
	 * @modifier
	 * @modify-date 2015-5-23 下午5:01:14
	 * @version 1.0
	 * @param model
	 * @param cssday
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/toEmAjaxInfo")
	public JSONObject toEmAjaxInfo(Model model, CompanyServiceStaticDay cssday) {
		JSONObject json = new JSONObject();
		cssday.setCompanyId(WebUtils.getCurrentCompanyId());
		if (cssday.getEndTime() == null || cssday.getEndTime() == "") {
			Date date = new Date();
			cssday.setEndTime(DateUtil.formatDateByFormat(date, "yyyy-MM-dd"));
		}
		if (cssday.getStartTime() == null || cssday.getStartTime() == "") {
			Date date = new Date();
			Date agoDate = DateUtil.addDate(date, -15);
			cssday.setStartTime(DateUtil.formatDateByFormat(agoDate, "yyyy-MM-dd"));
		}
		List<CompanyServiceStaticDay> newCDay = new ArrayList<CompanyServiceStaticDay>();
		List<CompanyServiceStaticDay> cssd = companyServiceStaticDayService.findInfoByDate(cssday);
		for (CompanyServiceStaticDay day : cssd) {
			if (day.getEmailNum() == null) {
				day.setEmailNum(0);
			}
			newCDay.add(day);
		}
		json.put("email", newCDay);
		return json;
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 链接到短信统计图表
	 * @author ycl
	 * @date 2015-5-25 下午1:01:51
	 * @modifier
	 * @modify-date 2015-5-25 下午1:01:51
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toMessageStatistics")
	public String toMessageStatistics(Model model) {
		Map<String, Object> companyMap = selCompay(WebUtils.getCurrentCompanyId());
		model.addAllAttributes(companyMap);

		return "company/messageStatistics";
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 异步加载短信图表
	 * @author ycl
	 * @date 2015-5-25 下午2:15:47
	 * @modifier
	 * @modify-date 2015-5-25 下午2:15:47
	 * @version 1.0
	 * @param model
	 * @param cssday
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/toMsAjaxInfo ")
	public JSONObject toMsAjaxInfo(Model model, CompanyServiceStaticDay cssday) {
		JSONObject json = new JSONObject();
		cssday.setCompanyId(WebUtils.getCurrentCompanyId());
		if (cssday.getEndTime() == null || cssday.getEndTime() == "") {
			Date date = new Date();
			cssday.setEndTime(DateUtil.formatDateByFormat(date, "yyyy-MM-dd"));
		}
		if (cssday.getStartTime() == null || cssday.getStartTime() == "") {
			Date date = new Date();
			Date agoDate = DateUtil.addDate(date, -15);
			cssday.setStartTime(DateUtil.formatDateByFormat(agoDate, "yyyy-MM-dd"));
		}
		List<CompanyServiceStaticDay> newCDay = new ArrayList<CompanyServiceStaticDay>();
		List<CompanyServiceStaticDay> cssd = companyServiceStaticDayService.findInfoByDate(cssday);
		for (CompanyServiceStaticDay day : cssd) {
			if (day.getMessageNum() == null) {
				day.setMessageNum(0);
			}
			newCDay.add(day);
		}
		json.put("message", newCDay);
		return json;
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 链接到直播图表统计
	 * @author ycl
	 * @date 2015-5-25 下午2:36:05
	 * @modifier
	 * @modify-date 2015-5-25 下午2:36:05
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toLiveStatistics")
	public String toLiveStatistics(Model model) {
		Map<String, Object> companyMap = selCompay(WebUtils.getCurrentCompanyId());
		Company company = (Company) companyMap.get("company");
		// 取公司最大并发
		Map<String, Object> param = new HashMap<String, Object>();

		Date date = new Date();
		param.put("companyId", company.getId());
		param.put("concurrentMonth",
		        Integer.parseInt((date.getYear() + 1900) + "" + ((date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1))));
		// 查询本月并发
		CompanyLiveConcurrent clc = companyLiveConcurrentServiceImpl.findLiveByComidAndDate(param);
		// 下月并发
		Date time = DateUtil.addMonthsToDate(date, 1);
		Integer concurrentMonths = Integer
		        .parseInt((time.getYear() + 1900) + "" + ((time.getMonth() + 1) < 10 ? "0" + (time.getMonth() + 1) : (time.getMonth() + 1)));

		param.clear();
		param.put("companyId", company.getId());
		param.put("concurrentMonth", concurrentMonths);
		CompanyLiveConcurrent clcn = companyLiveConcurrentServiceImpl.findLiveByComidAndDate(param);
		model.addAttribute("clcn", clcn);
		model.addAttribute("clc", clc);
		model.addAllAttributes(companyMap);
		if (company.getServiceVersion() != null && company.getServiceVersion().equals("ONLINE_COUNT")) {
			return "company/liveStatistics";
		} else {
			return "company/liveStatistics";
		}
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 异步加载直播图表
	 * @author ycl
	 * @date 2015-5-25 下午2:36:49
	 * @modifier
	 * @modify-date 2015-5-25 下午2:36:49
	 * @version 1.0
	 * @param model
	 * @param cssday
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/toLvAjaxInfo ")
	public JSONObject toLvAjaxInfo(Model model, CompanyServiceStaticDay cssday) {
		JSONObject json = new JSONObject();
		cssday.setCompanyId(WebUtils.getCurrentCompanyId());
		if (cssday.getEndTime() == null || cssday.getEndTime() == "") {
			Date date = new Date();
			cssday.setEndTime(DateUtil.formatDateByFormat(date, "yyyy-MM-dd"));
		}
		if (cssday.getStartTime() == null || cssday.getStartTime() == "") {
			Date date = new Date();
			Date agoDate = DateUtil.addDate(date, -15);
			cssday.setStartTime(DateUtil.formatDateByFormat(agoDate, "yyyy-MM-dd"));
		}
		List<CompanyServiceStaticDay> newCDay = new ArrayList<CompanyServiceStaticDay>();
		List<CompanyServiceStaticDay> cssd = companyServiceStaticDayService.findInfoByDate(cssday);
		for (CompanyServiceStaticDay day : cssd) {
			if (day.getLiveNum() == null) {
				day.setLiveNum(0);
			}
			newCDay.add(day);
		}
		json.put("live", newCDay);
		return json;
	}



	/**
	 * @Description: 将后台查询出来视频流量、空间的价格赋值到对应的对象中
	 * @author wzx
	 * @date 2015-4-29 下午7:07:50
	 * @version 1.0
	 * @param serives
	 * @return
	 */
	public List<CompanyStanderdServie> joinService(List<CompanyStanderdServie> serives) {

		List<SysConfigVideoPrice> videos = configVideoPriceService.findSysConfigVideoPriceLowerEastWithSameService();

		List<SysConfigVideoPrice> spacePrice = new ArrayList<SysConfigVideoPrice>();
		List<SysConfigVideoPrice> flowPrice = new ArrayList<SysConfigVideoPrice>();

		if (videos != null && videos.size() > 0) {
			for (SysConfigVideoPrice price : videos) {
				if (price.getBuyType() == 0) {
					spacePrice.add(price);
				} else {
					flowPrice.add(price);
				}
			}
		}

		if (serives != null && serives.size() > 0 && spacePrice != null && spacePrice.size() > 0) {
			for (CompanyStanderdServie serv : serives) {
				if (serv.getServiceLevel() < CompanyServiceConstant.COMPANY_SERVIEVE_LEVEL_30) {
					serv.setSpacePrice(spacePrice.get(0).getUnitPrice());
				}
				if (serv.getServiceLevel() >= CompanyServiceConstant.COMPANY_SERVIEVE_LEVEL_30) {
					serv.setSpacePrice(spacePrice.get(1).getUnitPrice());
				}
			}
		}

		if (serives != null && serives.size() > 0 && flowPrice != null && flowPrice.size() > 0) {
			for (CompanyStanderdServie serv : serives) {
				if (serv.getServiceLevel() < CompanyServiceConstant.COMPANY_SERVIEVE_LEVEL_30) {
					serv.setFlowPrice(flowPrice.get(0).getUnitPrice());
				}
				if (serv.getServiceLevel() >= CompanyServiceConstant.COMPANY_SERVIEVE_LEVEL_30) {
					serv.setFlowPrice(flowPrice.get(1).getUnitPrice());
				}
			}
		}

		return serives;
	}
	@ResponseBody
	@RequestMapping(value = "/findCompanyLevel")
	public String findCompanyLevel(Integer companyId) {
		Integer companyLevel = companyServiceImpl.findCompanyLevel(companyId);
		String target = "";
		if (companyLevel >= 40) {
			target = "vip";
		} else if (companyLevel < 40) {
			target = "novip";
		}
		return target;
	}

	@RequestMapping(value = "/editDomain")
	public String editDomain(Model model) {
		Company company = companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
		model.addAttribute("company", company);
		return "company/companyDomain"; 
	}

	@RequestMapping("/openSchool")
	public String openSchool(Model model, HttpServletRequest request) {
		// 获得公司id 
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", companyId);
		param.put("schoolId", schoolId);
		// 查询所属分校根据 公司id
		List<SysConfigSchool> scsList = sysConfigSchoolServiceImpl.queryAllByCompanyId(companyId);
		// 根据公司id 查询 服务

		Map<String, Object> companyMap = selCompay(WebUtils.getCurrentCompanyId());
		model.addAllAttributes(companyMap);
		model.addAttribute("scsList", scsList);
		return "system/openSchool";
	}

	@RequestMapping("/sel")
	public String sel() {
		return "system/create";
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 异步加载表格
	 * @author ycl
	 * @date 2015-5-16 上午10:45:20
	 * @modifier
	 * @modify-date 2015-5-16 上午10:45:20
	 * @version 1.0
	 * @param model
	 * @param company
	 * @return
	 */
	@RequestMapping(value = "/loadServiceAuthInfo")
	public String loadServiceAuthInfo(Model model, Company company) {
		company.setPageSize(6);
		PageFinder<CompanyMemberServiceVo> pageFinder = companyMemberServiceServiceImpl.findCompanyMemberServiceVoByPage(company);
		model.addAttribute("pageFinder", pageFinder);
		return "institution/serviceAuthorizeAjaxInfo";
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 会员授权部分
	 * @author ycl
	 * @date 2015-5-16 上午10:47:03
	 * @modifier
	 * @modify-date 2015-5-16 上午10:47:03
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value = "/toAuthorize/{companyId}")
	public String toAuthorize(Model model, @PathVariable Integer companyId) {
		// 图片服务器地址
		String imageServeUrl = properties.getImageServicePath();
		model.addAttribute("imageServeUrl", imageServeUrl);
		CompanyMemberServiceVo memberServiceVo = companyMemberServiceServiceImpl.findCompanyMemberInfoByCompanyId(companyId);
		model.addAttribute("memberServiceVo", memberServiceVo);
		return "institution/authorize";
	}

	@RequestMapping("/liveDetail")
	public String liveDetail(HttpServletRequest request, Model model) throws ParseException {
		// 查询公司信息
		Integer companyId = WebUtils.getCurrentCompanyId();
		model.addAllAttributes(selCompay(companyId));
		return "company/liveDetail";
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 查询公司购买的版本允许招生的学员数
	 * @author chopin
	 * @date 2015-5-28 下午4:29:20
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/studentNum")
	public Integer studentNum() {
		CompanyMemberService service = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		return service.getFaceStudentAll();
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 链接到修改账户密码
	 * @author yuchanglong
	 * @date 2016年1月11日 上午11:51:41
	 * @version 1.0
	 * @return
	 */
	@RequestMapping("/toUpdatePwd")
	public String toUpdatePwd(HttpServletRequest request, Model model) {
		Integer userId = WebUtils.getCurrentUserId(request);
		Integer isMaxRole = 0;
		if (userId != null && authRoleServiceImpl.hasRoleFlag(userId)) {
			// 相当于原来的机构管理员
			isMaxRole = 1;
		}
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole("直播老师"))
			isMaxRole = 2;
		model.addAttribute("isMaxRole", isMaxRole);

		return "company/editPwd";
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 根据公司id 修改公司服务
	 * @author 周文斌
	 * @date 2016-3-23 下午5:27:02
	 * @version 1.0
	 * @param request
	 * @param cms
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateByCompanyId")
	public JSONObject updateByCompanyId(HttpServletRequest request, CompanyMemberService cms, CompanyMemberServiceChangelog cmsc) {
		JSONObject json = new JSONObject();
		cmsc.setTableName("CompanyMemberService");
		cmsc.setUpdator(WebUtils.getCurrentUserId(request));
		cmsc.setChangeTime(new Date());
		try {
			companyMemberServiceServiceImpl.updateByCompanyId(cms);
			// 记录
			companyMemberServiceChangelogServiceImpl.insert(cmsc);

			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
			return json;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put(JsonMsg.MSG, "修改时遇到问题");
			return json;
		}
	}

	/**
	 *
	 * Class Name: CompanyMemberServiceController.java
	 *
	 * @Description: 服务开通生成订单并授权
	 * @author yuchanglong
	 * @date 2016年4月1日 上午10:53:52
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/serviceOpen/{companyId}")
	public String serviceOpen(Model model, HttpServletRequest request, @PathVariable Integer companyId) {
		model.addAttribute("companyId", companyId);

		return "institution/serviceOpen";
	}

}
