package com.yuxin.wx.controller.user;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
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

import com.yuxin.wx.api.company.ICompanyEmailHistoryService;
import com.yuxin.wx.api.company.ICompanyInvitConfigTeacherService;
import com.yuxin.wx.api.company.ICompanyMessageHistoryService;
import com.yuxin.wx.api.company.ICompanyNewStepService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.api.system.ISysCyclePicService;
import com.yuxin.wx.api.system.ISysPageHeadFootService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyEmailHistory;
import com.yuxin.wx.model.company.CompanyInvitConfigTeacher;
import com.yuxin.wx.model.company.CompanyMessageHistory;
import com.yuxin.wx.model.company.CompanyNewStep;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.MailUtil;
import com.yuxin.wx.utils.CaptchaUtil;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.InviteCodeUtil;
import com.yuxin.wx.utils.ParameterUtil;
import com.yuxin.wx.utils.SMSUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.common.email.ChangeAuthCodeYunduo;
import com.yuxin.wx.vo.common.email.Mail;
import com.yuxin.wx.vo.common.email.VerifyEmailYunduo;
import com.yuxin.wx.vo.privilege.RoleVo;
import com.yuxin.wx.vo.privilege.UserRoleVo;

/**
 * Controller of Users
 * 
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/users")
public class UsersController {
	Log log = LogFactory.getLog("log");
	@Autowired
	private IUsersService iUsersService;

	@Autowired
	private IUsersService usersServiceImpl;
	@Autowired
	private ISysPageHeadFootService sysPageHeadFootServiceImpl;
	@Autowired
	private ICompanyEmailHistoryService companyEmailHistoryServiceImpl;
	@Autowired
	private ISysCyclePicService sysCyclePicServiceImpl;
	@Autowired
	private ICompanyMessageHistoryService companyMessageHistoryServiceImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	@Autowired
	private ICompanyNewStepService companyNewStepServiceImpl;
	@Autowired
	private ISysConfigTeacherService sysConfigTeacherServiceImpl;
	@Autowired
	private ICompanyInvitConfigTeacherService companyInviteConfigTeacherServiceImpl;
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Users search) {
		if (search == null) {
			search = new Users();
			// search.setPageSize(20);
		}
		model.addAttribute("list", usersServiceImpl.findUsersByPage(search));
		return "users/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Users users) {
		usersServiceImpl.insert(users);
		return "redirect:/users";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Users users) {
		usersServiceImpl.update(users);
		return "redirect:/users";
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		usersServiceImpl.deleteUsersById(id);
		return "redirect:/users";
	}

	@ResponseBody
	@RequestMapping(value = "/{id:\\d+}", method = RequestMethod.POST)
	public Users getJson(Model model, @PathVariable Integer id) {
		return usersServiceImpl.findUsersById(id);
	}

	@ResponseBody
	@RequestMapping(value = "/findList", method = RequestMethod.POST)
	public PageFinder<UserRoleVo> findList(UserRoleVo search) {
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		return usersServiceImpl.findUserList(search);
	}

	/**
	 * 
	 * Class Name: StudentController.java
	 * 
	 * @Description: 验证学员是否已经注册过，可以通过姓名，手机，证件号码验证
	 * @author Chopin
	 * @date 2014年12月19日
	 * @version 1.0
	 * @param student
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
	public Boolean checkUsername(String username) {
		Boolean ie = usersServiceImpl.isExists("username", username);
		return !ie;
	}

	/**
	 * 
	 * Class Name: StudentController.java
	 * 
	 * @Description: 验证学员是否已经注册过，可以通过姓名，手机，证件号码验证
	 * @author Chopin
	 * @date 2014年12月19日
	 * @version 1.0
	 * @param student
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkMobile", method = RequestMethod.POST)
	public Boolean checkMobile(String mobile) {
		Boolean ie = usersServiceImpl.isExists("mobile", mobile);
		return !ie;
	}

	/**
	 * 
	 * Class Name: UsersController.java
	 * 
	 * @Description: 通过邮箱验证学员是否已经注册过
	 * @author ycl
	 * @date 2015-5-9 下午4:31:34
	 * @modifier
	 * @modify-date 2015-5-9 下午4:31:34
	 * @version 1.0
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
	public Boolean checkEmail(String email) {
		Boolean ie = usersServiceImpl.isExists("email", email);
		return !ie;
	}

	/**
	 * 
	 * Class Name: StudentController.java
	 * 
	 * @Description: 验证学员是否已经注册过，可以通过姓名，手机，证件号码验证
	 * @author Chopin
	 * @date 2014年12月19日
	 * @version 1.0
	 * @param student
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkIdentityId", method = RequestMethod.POST)
	public Boolean checkIdentityId(String identityId) {
		Boolean ie = usersServiceImpl.isExists("identityId", identityId);
		return !ie;
	}

	@ResponseBody
	@RequestMapping(value = "/organUser/add", method = RequestMethod.POST)
	public String addOrganUser(UserRoleVo vo) {
		// 密码加密
		vo.setPassword(new Md5Hash(vo.getPassword(), ByteSource.Util.bytes(vo
				.getUsername() + "salt")).toHex());
		return usersServiceImpl.addOrganUser(vo, WebUtils.getCurrentUser()
				.getId());
	}

	@ResponseBody
	@RequestMapping(value = "/organUser/edit", method = RequestMethod.POST)
	public String editOrganUser(UserRoleVo vo) {
		// 如果已经是md5值，则不做加密
		if (null != vo.getPassword() && vo.getPassword().length() != 32) {
			// 密码加密
			vo.setPassword(new Md5Hash(vo.getPassword(), ByteSource.Util
					.bytes(vo.getUsername() + "salt")).toHex());
		}
		return usersServiceImpl.editOrganUser(vo, WebUtils.getCurrentUser()
				.getId());
	}

	@ResponseBody
	@RequestMapping(value = "/OrganUser/list/{roleId}", method = RequestMethod.POST)
	public List<Users> findOrganUserList(@PathVariable String roleId) {
		RoleVo roleVo = new RoleVo();
		roleVo.setRoleUid(roleId);
		roleVo.setCompanyId(WebUtils.getCurrentCompanyId());
		return usersServiceImpl.findUsersByRoleId(roleVo);
	}

	/**
	 * 后台接收Date转换
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	public static void main(String[] args) {
		System.out.println(new Md5Hash("111111", ByteSource.Util
				.bytes("test1salt")).toHex());
	}

	/**
	 * 
	 * Class Name: UsersController.java
	 * 
	 * @Description: 链接到账户信息页面
	 * @author ycl
	 * @date 2015-5-6 下午4:31:25
	 * @modifier
	 * @modify-date 2015-5-6 下午4:31:25
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/accountInfo")
	public String accountInfo(Model model) {
		Users user = iUsersService.findUserByCompanyIdAndUserType(WebUtils
				.getCurrentCompanyId(),WebUtils.getCurrentUser().getId());
		Subject subject = SecurityUtils.getSubject();
		model.addAttribute("isTeacher", subject.hasRole("直播老师"));
		if(subject.hasRole("直播老师")){
			CompanyInvitConfigTeacher configTeacher = new CompanyInvitConfigTeacher();
			configTeacher.setCompanyId(WebUtils.getCurrentCompanyId());
			configTeacher.setOpenFlag(1);
			List<CompanyInvitConfigTeacher> list2 = companyInviteConfigTeacherServiceImpl.findCompanyInvitConfigTeacherByPage(configTeacher);
			model.addAttribute("isInviteOpen", list2!=null&&list2.size()>0);
			SysConfigTeacher teacher = new SysConfigTeacher();
			teacher.setCompanyId(WebUtils.getCurrentCompanyId());
			teacher.setUserId(WebUtils.getCurrentUser().getId());
			List<SysConfigTeacher> list = sysConfigTeacherServiceImpl.findTeacherByUserId(teacher);
			if(list!=null && list.size()>0){
				SysConfigTeacher sysConfigTeacher = list.get(0);
				String inviteCodeStr = "";
				if(!StringUtils.isEmpty(sysConfigTeacher.getInviteCode())){
					model.addAttribute("inviteCode", sysConfigTeacher.getInviteCode());
					inviteCodeStr = "http://" +companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId()).getDomain()+ "/shareLinkCode/teacherInvitCode/" +sysConfigTeacher.getInviteCode();
				}else {
					String inviteCode = InviteCodeUtil.productInviteCode(sysConfigTeacher.getId());
					sysConfigTeacher.setInviteCode(inviteCode);
					sysConfigTeacherServiceImpl.update(sysConfigTeacher);
					inviteCodeStr = "http://" +companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId()).getDomain()+ "/shareLinkCode/teacherInvitCode/" +inviteCode;
				}
				model.addAttribute("inviteCodeStr", inviteCodeStr);
			}
		}
		model.addAttribute("user", user);
		return "company/accountInformation";
	}

	
	@RequestMapping(value = "/teacherAccountInfo")
	public String accountInfo2(Model model) {
		model.addAttribute("user", WebUtils.getCurrentUser());
		CompanyInvitConfigTeacher configTeacher = new CompanyInvitConfigTeacher();
		configTeacher.setCompanyId(WebUtils.getCurrentCompanyId());
		configTeacher.setOpenFlag(1);
		List<CompanyInvitConfigTeacher> list2 = companyInviteConfigTeacherServiceImpl.findCompanyInvitConfigTeacherByPage(configTeacher);
		model.addAttribute("isInviteOpen", list2!=null&&list2.size()>0);
		SysConfigTeacher teacher = new SysConfigTeacher();
		teacher.setCompanyId(WebUtils.getCurrentCompanyId());
		teacher.setUserId(WebUtils.getCurrentUser().getId());
		List<SysConfigTeacher> list = sysConfigTeacherServiceImpl.findTeacherByUserId(teacher);
		if(list!=null && list.size()>0){
			SysConfigTeacher sysConfigTeacher = list.get(0);
			String inviteCodeStr = "";
			if(!StringUtils.isEmpty(sysConfigTeacher.getInviteCode())){
				model.addAttribute("inviteCode", sysConfigTeacher.getInviteCode());
				inviteCodeStr = "http://" +companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId()).getDomain()+ "/shareLinkCode/teacherInvitCode/" +sysConfigTeacher.getInviteCode();
			}else {
				String inviteCode = InviteCodeUtil.productInviteCode(sysConfigTeacher.getId());
				sysConfigTeacher.setInviteCode(inviteCode);
				sysConfigTeacherServiceImpl.update(sysConfigTeacher);
				inviteCodeStr = "http://" +companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId()).getDomain()+ "/shareLinkCode/teacherInvitCode/" +inviteCode;
			}
			model.addAttribute("inviteCodeStr", inviteCodeStr);
		}
		return "company/accountInformation2";
	}

	
	/**
	 * 
	 * Class Name: UsersController.java
	 * 
	 * @Description: 更新账户信息
	 * @author ycl
	 * @date 2015-5-6 下午4:30:57
	 * @modifier
	 * @modify-date 2015-5-6 下午4:30:57
	 * @version 1.0
	 * @param users
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAccountInfo")
	public String updateAccountInfo(Users users) {
		iUsersService.updateUserByCompanyIdAndUserType(users);
		return "success";
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
	public String sendMobileCode(HttpServletRequest request,
			HttpServletResponse response, String phoneNum, String captcha)
			throws Exception {

		// 插入短信历史
		Users user = iUsersService.findUserByCompanyIdAndUserType(WebUtils
				.getCurrentCompanyId());

		CompanyMessageHistory companyMessageHistory = new CompanyMessageHistory();
		companyMessageHistory.setReceiverUserId(user.getId().toString());
		companyMessageHistory.setReceiverMobile(phoneNum);
		Date now = new Date();
		companyMessageHistory.setSendTime(now);
		companyMessageHistory.setCostNum(1);
		companyMessageHistory.setCompanyId(user.getCompanyId());
		companyMessageHistory.setSchoolId(user.getSchoolId());
		companyMessageHistory.setBusinessType("BUSINESS_CHANGE_ACCOUNT");

		if (null != captcha) {
			if (!CaptchaUtil.checkCaptcha(captcha, request.getSession()
					.getAttribute("captcha").toString())) {
				return "{'flag':'error','message':'图片验证码不正确'}";
			}
		} else {
			return "{'flag':'error','message':'图片验证码不能为空'}";
		}
		if (ParameterUtil.isMobilePhone(phoneNum)) {
			try {
				SMSUtil.sendSMSCode(request, phoneNum,
						SMSUtil.SMS_TEMPLETE_RESETMOBILE);
				Cookie cookie = new Cookie("sendSms_timestemp", ""
						+ System.currentTimeMillis());
				cookie.setMaxAge(6000);// 存在60s
				response.addCookie(cookie);
				companyMessageHistory.setSendStatus(1);
				companyMessageHistory.setSendResult("成功");
				return "{'flag':'success','message':'短信已发送至您的手机'}";
			} catch (Exception e) {
				log.error(e,e);
				e.printStackTrace();
				companyMessageHistory.setSendStatus(0);
				companyMessageHistory.setSendResult("失败");
				return "{'flag':'eorror','message':'出现意外，短信发送失败'}";
			} finally {
				companyMessageHistoryServiceImpl.insert(companyMessageHistory);
			}

		} else {
			return "{'flag':'error','message':'不是有效的手机号'}";
		}

	}

	/**
	 * 
	 * Class Name: UsersController.java
	 * 
	 * @Description: 校验手机验证码是否正确
	 * @author ycl
	 * @date 2015-5-6 下午4:50:09
	 * @modifier
	 * @modify-date 2015-5-6 下午4:50:09
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkMobileCode")
	public String checkMobileCode(HttpServletRequest request, String smsCode) {
		Boolean validateCode = SMSUtil.validateCode(request.getSession(),
				smsCode);
		if (validateCode == false) {
			return "smsCodeError";
		}
		return "success";
	}

	/**
	 * 
	 * Class Name: UsersController.java
	 * 
	 * @Description: 发送邮箱验证码
	 * @author ycl
	 * @date 2015-5-6 下午7:59:37
	 * @modifier
	 * @modify-date 2015-5-6 下午7:59:37
	 * @version 1.0
	 * @param request
	 * @param response
	 * @param phoneNum
	 * @param captcha
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@ResponseBody
	@RequestMapping(value = "/sendMaileCode")
	public String sendMaileCode(HttpServletRequest request,
			HttpServletResponse response, String newMaile, String captcha)
			throws ClientProtocolException, IOException {
		// 插入邮件历史表
		Users userNow = iUsersService.findUserByCompanyIdAndUserType(WebUtils
				.getCurrentCompanyId());
		CompanyEmailHistory companyEmailHistory = new CompanyEmailHistory();
		companyEmailHistory.setReceiverUserId(userNow.getId());
		companyEmailHistory.setReceiverEmail(newMaile);
		companyEmailHistory.setCompanyId(userNow.getCompanyId());
		companyEmailHistory.setSchoolId(userNow.getSchoolId());
		companyEmailHistory.setTitle("修改账户邮箱");
		companyEmailHistory.setBusinessType("BUSINESS_CHANGE_ACCOUNT");

		if (null != captcha) {
			if (!CaptchaUtil.checkCaptcha(captcha, request.getSession()
					.getAttribute("captcha").toString())) {
				return "{'flag':'error','message':'图片验证码不正确'}";
			}
		} else {
			return "{'flag':'error','message':'图片验证码不能为空'}";
		}
		Users user = WebUtils.getCurrentUser();
		Properties prop = new Properties();
		InputStream in = MailUtil.class.getClassLoader().getResourceAsStream(
				"config.properties");
		prop.load(in);
		String url = prop.getProperty("url");
		String apiUser = prop.getProperty("apiUser");
		String apiKey = prop.getProperty("apiKey");

		ChangeAuthCodeYunduo model1 = new ChangeAuthCodeYunduo();
		model1.setUserName(user.getUsername());
		model1.setAuthCode(SMSUtil.generate());
		model1.setSendDate(DateUtil.getCurrentDay("yyyyMMdd"));

		Mail mail = new Mail();
		mail.setFrom("admin@yuuxin.com");
		mail.setFromName("云朵课堂");
		mail.setSubject("更改邮箱");
		mail.setTo(newMaile);
		MailUtil u = new MailUtil(mail, model1);
		String sendmailStatus = u.sendmail(url, apiUser, apiKey,"");
		if ("success".equals(sendmailStatus)) {
			request.getSession().setAttribute("sms_code", model1.getAuthCode());
			companyEmailHistory.setSendStatus(1);
			companyEmailHistory.setSendResult("成功");
			companyEmailHistoryServiceImpl.insert(companyEmailHistory);
			return "{'flag':'success','message':'验证码已发送至您的邮箱'}";
		} else {
			companyEmailHistory.setSendStatus(0);
			companyEmailHistory.setSendResult("失败");
			companyEmailHistoryServiceImpl.insert(companyEmailHistory);
			return "{'flag':'nosend','message':'发送失败，出现未知错误，请联系客服'}";
		}

	}

	@RequestMapping(value = "guide")
	public String guide(Model model) {
		// 查询是否配置过页头.页尾,以及轮播图
		// int headCount
		// =sysPageHeadFootServiceImpl.selectHeadCount(WebUtils.getCurrentCompanyId());
		// int footCount
		// =sysPageHeadFootServiceImpl.seleFootCount(WebUtils.getCurrentCompanyId());
		// int cyclePicCount =
		// sysCyclePicServiceImpl.selectCount(WebUtils.getCurrentCompanyId());
		// model.addAttribute("headCount", headCount);
		// model.addAttribute("footCount", footCount);
		// model.addAttribute("cyclePicCount", cyclePicCount);
		// 查看机构认证状态

		return "/student/guide/guide_1";
	}

	@ResponseBody
	@RequestMapping(value = "getStatus", method = RequestMethod.POST)
	public String getStatus() {
		String status = companyServiceImpl.findStatus(WebUtils
				.getCurrentCompanyId());
		int userCount = usersServiceImpl.selectCount(WebUtils
				.getCurrentCompanyId());
		int itemCount = sysConfigItemServiceImpl.selectCount(WebUtils
				.getCurrentCompanyId());
		List<CompanyNewStep> list = companyNewStepServiceImpl
				.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
		// companyNewStepServiceImpl.findCompanyNewStepById(list.get(0).getId());
		Integer schoolHead = 0;
		Integer schoolFoot = 0;
		Integer schoolIndex = 0;
		if (list.size() > 0) {
			CompanyNewStep newStep = list.get(0);
			// 查看页头是否设置
			schoolHead = newStep.getSchoolHead();
			if (schoolHead == null || "".equals(schoolHead)) {
				schoolHead = 0;
			}
			// 查看页尾是否设置
			schoolFoot = newStep.getSchoolFoot();
			if (schoolFoot == null || "".equals(schoolFoot)) {
				schoolFoot = 0;
			}
			// 查看首页是否配置
			schoolIndex = newStep.getSchoolIndex();
			if (schoolIndex == null || "".equals(schoolIndex)) {
				schoolIndex = 0;
			}
		} else {
			CompanyNewStep newStep = new CompanyNewStep(null, 0, 0, 0, 0, 0, 0);
			newStep.setCompanyId(WebUtils.getCurrentCompanyId());
			if ("3".equals(status)) {
				newStep.setCompanyAuthority(1);
			}
			if (userCount > 1) {
				newStep.setUserCreate(1);
			}
			if (itemCount > 0) {
				newStep.setItemAll(1);
			}
			companyNewStepServiceImpl.insert(newStep);
		}
		if ((!"1".equals(status) && inTrial()) || ("3".equals(status))) {
			WebUtils.setSatus();
		}

		return "{'status':'" + status + "','userCount':'" + userCount
				+ "',itemCount:'" + itemCount + "',schoolHead:'" + schoolHead
				+ "',schoolFoot:'" + schoolFoot + "',schoolIndex:'"
				+ schoolIndex + "'}";
	}

	@ResponseBody
	@RequestMapping(value = "isPass", method = RequestMethod.POST)
	public Boolean isPass() {
		Boolean isPass = false;
		List<CompanyNewStep> list = companyNewStepServiceImpl
				.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
		String status = companyServiceImpl.findStatus(WebUtils
				.getCurrentCompanyId());
		if (list.size() > 0) {
			int newStepCount = companyNewStepServiceImpl.selectCount(WebUtils
					.getCurrentCompanyId());
			if (newStepCount > 0) {
				isPass = true;
			} else {
				int userCount = usersServiceImpl.selectCount(WebUtils
						.getCurrentCompanyId());
				int itemCount = sysConfigItemServiceImpl.selectCount(WebUtils
						.getCurrentCompanyId());
				CompanyNewStep entity = new CompanyNewStep();
				entity.setId(list.get(0).getId());
				entity.setCompanyId(WebUtils.getCurrentCompanyId());
				if ("3".equals(status)) {
					entity.setCompanyAuthority(1);

				}
				// 查询该公司是否有其他用户
				if (userCount > 1) {
					entity.setUserCreate(1);

				}
				if (itemCount > 0) {
					entity.setItemAll(1);
				}
				if ("3".equals(status) || userCount > 1 || itemCount > 0) {
					companyNewStepServiceImpl.update(entity);
				}

				int count = companyNewStepServiceImpl.selectCount(WebUtils
						.getCurrentCompanyId());
				if (count > 0) {
					isPass = true;
				}
			}

		} else {
			CompanyNewStep entity = new CompanyNewStep();
			entity.setCompanyId(WebUtils.getCurrentCompanyId());
			int userCount = usersServiceImpl.selectCount(WebUtils
					.getCurrentCompanyId());
			int itemCount = sysConfigItemServiceImpl.selectCount(WebUtils
					.getCurrentCompanyId());
			if ("3".equals(status)) {
				entity.setCompanyAuthority(1);
				// companyNewStepServiceImpl.update(entity);
			}
			// 查询该公司是否有其他用户
			if (userCount > 1) {
				entity.setUserCreate(1);

			}
			if (itemCount > 0) {
				entity.setItemAll(1);
			}
			companyNewStepServiceImpl.insert(entity);
		}
		if ((!"1".equals(status) && inTrial()) || ("3".equals(status))) {
			WebUtils.setSatus();
			isPass = true;
		}

		return isPass;
	}

	@RequestMapping(value = "business")
	public String business() {

		return "/student/guide/guide_2";
	}

	@RequestMapping(value = "toSetHead")
	public String toSetHead() {
		List<CompanyNewStep> list = companyNewStepServiceImpl
				.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
		CompanyNewStep entity = new CompanyNewStep();
		entity.setSchoolHead(1);
		if (list.size() > 0) {
			entity.setId(list.get(0).getId());
			companyNewStepServiceImpl.update(entity);
		} else {
			companyNewStepServiceImpl.insert(entity);
		}
		isPass();
		return "redirect:/sysPageHeadFoot/showHead";
	}

	@RequestMapping(value = "toSetFoot")
	public String toSetFoot() {
		List<CompanyNewStep> list = companyNewStepServiceImpl
				.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
		CompanyNewStep entity = new CompanyNewStep();
		entity.setSchoolFoot(1);
		if (list.size() > 0) {
			entity.setId(list.get(0).getId());
			companyNewStepServiceImpl.update(entity);
		} else {
			companyNewStepServiceImpl.insert(entity);
		}
		isPass();
		return "redirect:/sysPageHeadFoot/toConfigFooter";
	}

	@RequestMapping(value = "toSetIndex")
	public String toSetIndex() {
		List<CompanyNewStep> list = companyNewStepServiceImpl
				.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
		CompanyNewStep entity = new CompanyNewStep();
		entity.setSchoolIndex(1);
		if (list.size() > 0) {
			entity.setId(list.get(0).getId());
			companyNewStepServiceImpl.update(entity);
		} else {
			companyNewStepServiceImpl.insert(entity);
		}
		isPass();
		return "redirect:/sysCyclePic/showPic";
	}

	@RequestMapping(value = "startTeach")
	public String startTeach() {

		return "/student/guide/guide_3";
	}

	@RequestMapping(value = "/error")
	public String error() {

		return "/student/guide/error";
	}

	/**
	 * 
	 * Class Name: UsersController.java
	 * 
	 * @Description: 判断是否认证
	 * @author 权飞虎
	 * @date 2015年5月28日 下午2:34:45
	 * @modifier
	 * @modify-date 2015年5月28日 下午2:34:45
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "isAuthority", method = RequestMethod.POST)
	public Boolean isAuthority() {
		String status = companyServiceImpl.findStatus(WebUtils
				.getCurrentCompanyId());
		if (!"1".equals(status)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Class Name: UsersFrontController.java
	 * 
	 * @Description: 返回加密后的密码
	 * @author ycl
	 * @date 2015-5-29 下午6:00:58
	 * @modifier
	 * @modify-date 2015-5-29 下午6:00:58
	 * @version 1.0
	 * @param pwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/md5Pwd")
	public String md5Pwd(String pwd, HttpServletRequest request) {
		String userName = WebUtils.getCurrentUserName(request);
		String md5Pwd = new Md5Hash(pwd, ByteSource.Util.bytes(userName
				+ "salt")).toHex();
		return md5Pwd;
	}
	/**
	 * 
	 * Class Name: UsersController.java
	 * @Description: 登陆后修改密码
	 * @author yuchanglong
	 * @date 2015年9月15日 下午8:31:08
	 * @version 1.0
	 * @param pwd
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/upCurrPwd")
	public String upCurrPwd(String pwd, HttpServletRequest request) {
		String userName = WebUtils.getCurrentUserName(request);
		String md5Pwd = new Md5Hash(pwd, ByteSource.Util.bytes(userName
				+ "salt")).toHex();
		Users users = new Users();
		System.out.println(md5Pwd);
		Integer id = WebUtils.getCurrentUserId(request);
		users.setId(id);
		users.setPassword(md5Pwd);
		usersServiceImpl.update(users);
		return "success";
	}

	/**
	 * 
	 * Class Name: UsersFrontController.java
	 * 
	 * @Description: 验证密码是否匹配
	 * @author ycl
	 * @date 2015-5-29 下午5:44:42
	 * @modifier
	 * @modify-date 2015-5-29 下午5:44:42
	 * @version 1.0
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkPwd", method = RequestMethod.POST)
	public Boolean checkPwd(String password, HttpServletRequest request) {

		Integer userId = WebUtils.getCurrentUserId(request);
		Users newUser = usersServiceImpl.findUsersById(userId);
		System.out.println(newUser.getPassword());
		System.out.println(password);
		if (password.equals(newUser.getPassword())) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Class Name: UsersController.java
	 * 
	 * @Description: 重置密码页面
	 * @author yuchanglong
	 * @date 2015年7月1日 上午11:47:33
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value = "/findPwd")
	public String findPwd(HttpServletRequest request, Model model) {

		return "company/findPwd";
	}

	/**
	 * 
	 * Class Name: UsersController.java
	 * 
	 * @Description: 根据手机号查询出用户Id
	 * @author yuchanglong
	 * @date 2015年7月1日 下午4:15:37
	 * @version 1.0
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserIdByName")
	public Integer getUserIdByName(String name) {
		Users users = new Users();
		users.setUsername(name);
		List<Users> user = usersServiceImpl.findUserByCondition(users);
		return user.get(0).getId();
	}

	@ResponseBody
	@RequestMapping(value = "/isExixts")
	public boolean isExixts(Users users) {
		Integer count = usersServiceImpl.isExixits(users);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Class Name: UsersController.java
	 * 
	 * @Description: 校验域名
	 * @author yuchanglong
	 * @date 2015年7月1日 下午4:51:08
	 * @version 1.0
	 * @param domain
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkDomain", method = RequestMethod.POST)
	public boolean checkDomain(String domain, HttpServletRequest request) {
		Company company = new Company();
		company.setDomain(domain);
		Company comp = companyServiceImpl
				.queryCompanyByCopanyCondition(company);
		if (comp == null) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @Title: checkUserName
	 * @Description: 校验用户名
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2015-5-15
	 * @user ycl
	 */
	@ResponseBody
	@RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
	public boolean checkUserName(String userName, HttpServletRequest request) {
		Users user = usersServiceImpl.queryUserByName(userName);
		if (user == null) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @Title: checkMobile
	 * @Description: 校验手机号
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2015-5-15
	 * @user zhang.zx
	 */
	@ResponseBody
	@RequestMapping(value = "/checkMobileForFind")
	public boolean checkMobileForFind(String mobile, HttpServletRequest request) {
		Users users = new Users();
		users.setMobile(mobile);
		List<Users> user = usersServiceImpl.findUserByCondition(users);
		if (user == null) {
			return false;
		}
		return true;
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param request
	 * @param phoneNum
	 * @param captcha
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sendSMS/{phoneNum}/{captcha}", method = RequestMethod.POST)
	public String sms(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String phoneNum, @PathVariable String captcha) {
		String captcha1 = (String) request.getSession().getAttribute("captcha");
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
			return "{'flag':'error','message':'您的访问过于频繁,尚需等待["
					+ (System.currentTimeMillis() - sendSms_timestemp) / 1000
					+ "s]'}";
		}

		if (ParameterUtil.isMobilePhone(phoneNum)) {
			try {
				SMSUtil.sendSMSCode(request, phoneNum,
						SMSUtil.SMS_TEMPLETE_FORGET);
				Cookie cookie = new Cookie("sendSms_timestemp", ""
						+ System.currentTimeMillis());
				cookie.setMaxAge(6000);// 存在60s
				response.addCookie(cookie);
				return "{'flag':'success','message':'短信已发送至您的手机'}";
			} catch (Exception e) {
				log.error(e,e);
				e.printStackTrace();
				return "{'flag':'eorror','message':'出现意外，短信发送失败'}";
			}

		} else {
			return "{'flag':'error','message':'不是有效的手机号'}";
		}
	}

	/**
	 * 
	 * Class Name: UsersFrontController.java
	 * 
	 * @Description: 返回加密后的密码
	 * @author ycl
	 * @date 2015-5-29 下午6:00:58
	 * @modifier
	 * @modify-date 2015-5-29 下午6:00:58
	 * @version 1.0
	 * @param pwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/newPwd")
	public String newPwd(String pwd, String userName, Integer id,
			HttpServletRequest request) {

		String md5Pwd = new Md5Hash(pwd, ByteSource.Util.bytes(userName
				+ "salt")).toHex();
		Users users = new Users();
		System.out.println(md5Pwd);
		users.setId(id);
		users.setPassword(md5Pwd);
		usersServiceImpl.update(users);
		return "success";
	}

	private Boolean inTrial() {
		Subject subject = SecurityUtils.getSubject();
		return (Boolean) subject.getSession().getAttribute("trial");
	}

}
