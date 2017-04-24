package com.yuxin.wx.controller.company;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyPayConfigService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.QNInterface;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/payConfig")
public class CompanyPayConfigController {

	private Log log_pay = LogFactory.getLog("pay");

	@Autowired
	private IUsersService usersServiceImpl;

	@Autowired
	private PropertiesUtil propertiesUtil;

	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;

	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private ICompanyPayConfigService companyPayConfigService;

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 设置支付信息
	 * @author yuchanglong
	 * @date 2015年12月17日 下午3:50:05
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showPay")
	public String showInfo(Model model, HttpServletRequest request) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);

		CompanyPayConfig companyPayConfig = companyPayConfigService.findByCompanyId(companyId);
		model.addAttribute("cpc", companyPayConfig);
		// 图片服务器地址
		String imageServeUrl = propertiesUtil.getProjectImageUrl();
		model.addAttribute("imageServeUrl", imageServeUrl);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "company/buyAliPay";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 企业即时到帐
	 * @author yuchanglong
	 * @date 2015年12月17日 下午3:48:17
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showZFB")
	public String showZFB(Model model, HttpServletRequest request) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);

		CompanyPayConfig companyPayConfig = companyPayConfigService.findByCompanyId(companyId);
		model.addAttribute("cpc", companyPayConfig);
		// 图片服务器地址
		String imageServeUrl = propertiesUtil.getProjectImageUrl();
		model.addAttribute("imageServeUrl", imageServeUrl);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "company/payConfig/buyAliPayOne";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 个人担保交易
	 * @author yuchanglong
	 * @date 2015年12月17日 下午3:48:17
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showGRDB")
	public String showGRDB(Model model, HttpServletRequest request) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);

		CompanyPayConfig companyPayConfig = companyPayConfigService.findByCompanyId(companyId);
		model.addAttribute("cpc", companyPayConfig);
		// 图片服务器地址
		String imageServeUrl = propertiesUtil.getProjectImageUrl();
		model.addAttribute("imageServeUrl", imageServeUrl);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "company/payConfig/buyAliPayTwo";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 支付宝网银
	 * @author dongshuai
	 * @date 2016年06月28日
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showZFBWY")
	public String showZFBWY(Model model, HttpServletRequest request) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);

		CompanyPayConfig companyPayConfig = companyPayConfigService.findByCompanyId(companyId);
		model.addAttribute("cpc", companyPayConfig);
		// 图片服务器地址
		String imageServeUrl = propertiesUtil.getProjectImageUrl();
		model.addAttribute("imageServeUrl", imageServeUrl);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "company/payConfig/buyAliPayZFBWY";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 支付宝网银
	 * @author dongshuai
	 * @date 2016年06月28日
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showSXY")
	public String showSXY(Model model, HttpServletRequest request) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);

		CompanyPayConfig companyPayConfig = companyPayConfigService.findByCompanyId(companyId);
		model.addAttribute("cpc", companyPayConfig);
		// 图片服务器地址
		String imageServeUrl = propertiesUtil.getProjectImageUrl();
		model.addAttribute("imageServeUrl", imageServeUrl);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "company/payConfig/buyAliPaySXY";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 个人担保交易
	 * @author yuchanglong
	 * @date 2015年12月17日 下午3:48:17
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showZFBZZ")
	public String showZFBZZ(Model model, HttpServletRequest request) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);

		CompanyPayConfig companyPayConfig = companyPayConfigService.findByCompanyId(companyId);
		model.addAttribute("cpc", companyPayConfig);
		// 图片服务器地址
		String imageServeUrl = propertiesUtil.getProjectImageUrl();
		model.addAttribute("imageServeUrl", imageServeUrl);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "company/payConfig/buyAliPayThree";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 微信扫码
	 * @author yuchanglong
	 * @date 2015年12月17日 下午3:48:17
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showWX")
	public String showWX(Model model, HttpServletRequest request) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);

		CompanyPayConfig companyPayConfig = companyPayConfigService.findByCompanyId(companyId);
		model.addAttribute("cpc", companyPayConfig);
		// 图片服务器地址
		String imageServeUrl = propertiesUtil.getProjectImageUrl();
		model.addAttribute("imageServeUrl", imageServeUrl);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "company/payConfig/buyAliPayFour";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 微信公众号
	 * @author yuchanglong
	 * @date 2015年12月17日 下午3:48:17
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showWXGZ")
	public String showWXGZ(Model model, HttpServletRequest request) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);

		CompanyPayConfig companyPayConfig = companyPayConfigService.findByCompanyId(companyId);
		model.addAttribute("cpc", companyPayConfig);
		// 图片服务器地址
		String imageServeUrl = propertiesUtil.getProjectImageUrl();
		model.addAttribute("imageServeUrl", imageServeUrl);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "company/payConfig/buyAliPayFive";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 银行汇款
	 * @author yuchanglong
	 * @date 2015年12月17日 下午3:48:17
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showBank")
	public String showBank(Model model, HttpServletRequest request) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);

		CompanyPayConfig companyPayConfig = companyPayConfigService.findByCompanyId(companyId);
		model.addAttribute("cpc", companyPayConfig);
		// 图片服务器地址
		String imageServeUrl = propertiesUtil.getProjectImageUrl();
		model.addAttribute("imageServeUrl", imageServeUrl);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "company/payConfig/buyAliPaySix";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 选择支付方式页面
	 * @author yuchanglong
	 * @date 2015年12月17日 下午3:53:04
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showPayChoice")
	public String showPayChoice(Model model, HttpServletRequest request) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);
		CompanyPayConfig companyPayConfig = companyPayConfigService.findByCompanyId(companyId);
		model.addAttribute("cpc", companyPayConfig);
		// 图片服务器地址
		String imageServeUrl = propertiesUtil.getProjectImageUrl();
		model.addAttribute("imageServeUrl", imageServeUrl);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "company/payConfig/payChoice";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 网银支付
	 * @author dongshuai
	 * @date 2016年06月28日
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showSXYChoice")
	public String showSXYChoice(Model model, HttpServletRequest request) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);
		CompanyPayConfig companyPayConfig = companyPayConfigService.findByCompanyId(companyId);
		model.addAttribute("cpc", companyPayConfig);
		// 图片服务器地址
		String imageServeUrl = propertiesUtil.getProjectImageUrl();
		model.addAttribute("imageServeUrl", imageServeUrl);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "company/payConfig/sxyChoice";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 如何开通微信公众号
	 * @author yuchanglong
	 * @date 2015年12月17日 下午3:53:04
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/howWxGzh")
	public String howWxGzh(Model model, HttpServletRequest request) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);
		CompanyPayConfig companyPayConfig = companyPayConfigService.findByCompanyId(companyId);
		model.addAttribute("cpc", companyPayConfig);
		// 图片服务器地址
		String imageServeUrl = propertiesUtil.getProjectImageUrl();
		model.addAttribute("imageServeUrl", imageServeUrl);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "company/payConfig/howWxGzh";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 选择微信支付方式页面
	 * @author yuchanglong
	 * @date 2015年12月17日 下午3:53:04
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showWxChoice")
	public String showWxChoice(Model model, HttpServletRequest request) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);
		CompanyPayConfig companyPayConfig = companyPayConfigService.findByCompanyId(companyId);
		model.addAttribute("cpc", companyPayConfig);
		// 图片服务器地址
		String imageServeUrl = propertiesUtil.getProjectImageUrl();
		model.addAttribute("imageServeUrl", imageServeUrl);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "company/payConfig/wxChoice";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 选择银行汇款方式页面
	 * @author yuchanglong
	 * @date 2015年12月17日 下午3:53:04
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showBankChoice")
	public String showBankChoice(Model model, HttpServletRequest request) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		// 用户信息
		Users user = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);
		CompanyPayConfig companyPayConfig = companyPayConfigService.findByCompanyId(companyId);
		model.addAttribute("cpc", companyPayConfig);
		// 图片服务器地址
		String imageServeUrl = propertiesUtil.getProjectImageUrl();
		model.addAttribute("imageServeUrl", imageServeUrl);
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "company/payConfig/bankChoice";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 更新支付类型
	 * @author yuchanglong
	 * @date 2015年12月18日 上午10:22:46
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePayType")
	public String updatePayType(String payType, String addType) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyPayConfig config = companyPayConfigService.findByCompanyId(companyId);
		String oldType = config.getPayType();
		String newType = "";
		Boolean isAdd = true;
		if (!StringUtils.isBlank(oldType)) {
			String[] typeList = oldType.split(",");
			for (int i = 0; i < typeList.length; i++) {
				if (!typeList[i].equals(payType)) {
					if (StringUtils.isBlank(newType)) {
						newType += typeList[i];
					} else {
						newType += "," + typeList[i];
					}
				}
				if (typeList[i].equals(addType) || addType.equals("")) {
					isAdd = false;
				}
			}
			if (isAdd) {
				if (StringUtils.isBlank(newType)) {
					newType += addType;
				} else {
					newType += "," + addType;
				}
			}
			CompanyPayConfig payConfig = new CompanyPayConfig();
			payConfig.setCompanyId(companyId);
			payConfig.setPayType(newType);
			companyPayConfigService.updateByCompanyId(payConfig);
		}

		return "success";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 如何支付页面
	 * @author yuchanglong
	 * @date 2015年7月9日 上午10:48:13
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value = "/howPay")
	public String howPay() {

		return "company/howPay";
	}

	/**
	 *
	 * Class Name: CompanyPayConfigController.java
	 * 
	 * @Description: 如何支付页面
	 * @author yuchanglong
	 * @date 2015年7月9日 上午10:48:13
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value = "/howPayTwo")
	public String howPayTwo() {

		return "company/howPayTwo";
	}

	@ResponseBody
	@RequestMapping(value = "/saveInfo")
	public String saveInfo(CompanyPayConfig companyPayConfig) {
		Integer companyId = companyPayConfig.getCompanyId();
		Integer isHave = companyPayConfigService.findCountByCompanyId(companyId);
		if (isHave == 0) {
			companyPayConfigService.insert(companyPayConfig);
		}
		if (isHave > 0) {
			companyPayConfigService.updateByCompanyId(companyPayConfig);
		}
		return "success";
	}

	/**
	 * 保存图片
	 * 
	 * @param multiPartRquest
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/savePic", method = RequestMethod.POST)
	public String queryPic(MultipartRequest multiPartRquest, HttpServletRequest req, String imgData) {
		MultipartFile multipartFile = multiPartRquest.getFile(imgData);
		String realPath = null;
		try {
			realPath = FileUtil.upload(multipartFile, "temp", WebUtils.getCurrentCompanyId() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// String url=propertiesUtil.getProjectImageUrl()+realPath;
		/* String url="/images/video-icons.png"; */
		return "{\"url\":\"" + realPath + "\"}";
	}

	@ResponseBody
	@RequestMapping(value = "/findByCpmIdAndPay")
	public CompanyPayConfig findByCpmIdAndPay(CompanyPayConfig config) {
		CompanyPayConfig comPay = new CompanyPayConfig();
		comPay = companyPayConfigService.findByComIdAndPayType(config);
		return comPay;
	}

	/**
	 *
	 * Class Name: UsersController.java
	 * 
	 * @Description: 发送验证码至手机
	 * @author ycl
	 * @date 2015-5-6 下午4:49:03
	 * @modifier
	 * @modify-date 2015-5-6 下午4:49:03
	 * @version 1.0
	 * @param request
	 * @param phoneNum
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/sendMobileCode")
	public String sendMobileCode(HttpServletRequest request, HttpServletResponse response, String phoneNum, String captcha) throws Exception {
		String captcha1 = (String) request.getSession().getAttribute("captcha");
		// boolean flag=this.powerfulCode(captcha1);
		if (null != captcha) {
			if (!captcha.equals(captcha1)) {
				return "{'flag':'error','message':'验证码不正确'}";
			}
		} else {
			return "{'flag':'error','message':'验证码不能为空'}";
		}
		Long sendSms_timestemp = null;
		for (Cookie c : request.getCookies()) {
			if ("sendSms_timestemp".equals(c.getName())) {
				sendSms_timestemp = Long.getLong(c.getValue());
			}
		}
		if (null != sendSms_timestemp) {
			return "{'flag':'error','message':'您的访问过于频繁,尚需等待[" + (System.currentTimeMillis() - sendSms_timestemp) / 1000 + "s]'}";
		}

		if (ParameterUtil.isMobilePhone(phoneNum)) {
			try {
				SMSUtil.sendSMSCode(request, phoneNum, SMSUtil.SMS_TEMPLETE_SETPAYINFO);
				Cookie cookie = new Cookie("sendSms_timestemp", "" + System.currentTimeMillis());
				cookie.setMaxAge(6000);// 存在60s
				response.addCookie(cookie);
				return "{'flag':'success','message':'短信已发送至您的手机'}";
			} catch (Exception e) {
				log_pay.error(e, e);
				e.printStackTrace();
				return "{'flag':'eorror','message':'出现意外，短信发送失败'}";
			}

		} else {
			return "{'flag':'error','message':'不是有效的手机号'}";
		}

	}


	public static void main(String[] args) {
		String url = "";
		String ak = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("grant_type", "password");
		map.put("username", "liyushan@yuuxin.com");
		map.put("password", "yuuxin2016!@");
		try {
			url = QNInterface.QN_CREATE_DOMAIN + QNInterface.QN_AUTH_TOKEN;
			String res = HttpPostRequest.postAK(url, map);
			ak = JSONObject.parseObject(res).getString("access_token");
			System.out.println(ak);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		map.clear();
		try {
			ak = "Bearer " + ak;
			// ak = "QBox " + ak;
			url = QNInterface.QN_CREATE_DOMAIN + QNInterface.QN_USER_INFO;
			String res = HttpPostRequest.getQN(url, ak);
			System.out.println(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
