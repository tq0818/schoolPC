package com.yuxin.wx.controller.user;

import com.alibaba.fastjson.JSON;
import com.yuxin.wx.ResultData;
import com.yuxin.wx.api.company.ICompanyIndexTemplateService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.system.ISysBodyService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.CacheService;
import com.yuxin.wx.common.CacheService.Ckey;
import com.yuxin.wx.common.Constants;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.DESUtil;
import com.yuxin.wx.util.MailUtil;
import com.yuxin.wx.utils.ParameterUtil;
import com.yuxin.wx.utils.SMSUtil;
import com.yuxin.wx.utils.smsClient.interfacej.SmsClientSend;
import com.yuxin.wx.vo.company.companySpecialDomain;
import com.yuxin.wx.vo.system.SysConfigPrivatePageVo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * 注册 Controller
 * 
 * @author zhang.zx
 * 
 * @version
 * 
 * @since 2015年5月16日
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	private static Logger log=Logger.getLogger(RegisterController.class);
	@Autowired
	private IUsersService usersServiceImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private ICompanyIndexTemplateService companyIndexTemplateServiceImpl; 
	@Autowired
	private ISysBodyService sysBodySerivceImpl;


	@RequestMapping(value="registByEmail")
	public String registByEmailSuccess(String registCode,Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		if(registCode==null||registCode==""){
			/*log.error("密码找回时,没有传回来电话号码或者邮箱激活码");*/
			response.setStatus(404);
			return "404";
		}else{
			Properties prop = new Properties();
			InputStream in = MailUtil.class.getClassLoader().getResourceAsStream(
					"config.properties");
			prop.load(in);
			String desPassword = prop.getProperty("password");
			try {
				registCode = DESUtil.decrypt(registCode, desPassword);
				
				String[] codes = registCode.split(",");
				String email=codes[1];	//获取邮箱
				String mobile = codes[3];	//获取手机号
				String password=codes[4];	//获取密码
				model.addAttribute("email", email);
				model.addAttribute("mobile", mobile);
				model.addAttribute("password", password);
				Users u=new Users();
				u.setEmail(email);
				List<Users> user = usersServiceImpl.findUserByCondition(u); 
				if (user != null&&user.size()>0) {
					response.setStatus(404);
					return "404";
				}
				Users u1=new Users();
				u1.setEmailAuthSign(1);
				u1.setEmailAuthSendtime(new Date());
				u1.setId(user.get(0).getId());
				usersServiceImpl.update(u1);
				return "success";
			 }catch(Exception e){
				e.printStackTrace();
				log.error("邮箱注册码被修改!");
				response.setStatus(404);
				return "404"; 
			 }
		}
	}
	
	/**
	 * 
	* @Title: checkUserName
	* @Description: 校验用户名
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-15
	* @user zhang.zx
	 */
	@ResponseBody
	@RequestMapping(value="/checkUserName",method=RequestMethod.POST)
	public String checkUserName(String userName,HttpServletRequest request){
		if(null!=userName&&!"".equals(userName)){
 			if(ParameterUtil.isUserName(userName)){
 				Users user=usersServiceImpl.queryUserByName(userName);
 	 			if(user!=null){
 	 				return "用户名已经被注册";
 	 			}
 	 			return "true";
 			}else{
 				return "只能以字母开头并由数字、字母或下划线组成";
 			}
 			
 		}else{
 			return "用户名不能为空";
 		}
		
	}
	
	/**
	 * 
	* @Title: checkEmail
	* @Description: 校验邮箱
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-15
	* @user zhang.zx
	 */
	@ResponseBody
	@RequestMapping(value="/checkEmail",method=RequestMethod.POST)
	public boolean checkEmail(String email,HttpServletRequest request){
		Users users=new Users();
		users.setEmail(email);
		List<Users> user=usersServiceImpl.findUserByCondition(users);
		if(user!=null&&user.size()>0){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	* @Title: checkMobile
	* @Description: 校验手机号
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-15
	* @user zhang.zx
	 */
	@ResponseBody
	@RequestMapping(value="/checkMobile",method=RequestMethod.POST)
	public boolean checkMobile(String mobile,HttpServletRequest request){
		log.info("==================进检查手机了=================");
		Users users=new Users();
		users.setMobile(mobile);
		List<Users> arr=usersServiceImpl.findUserByCondition(users);
		if(arr!=null&&arr.size()>0){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	* @Title: checkCompanyName
	* @Description: 校验公司名称
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-15
	* @user zhang.zx
	 */
	@ResponseBody
	@RequestMapping(value="/checkCompanyName",method=RequestMethod.POST)
	public String checkCompanyName(String companyName,HttpServletRequest request){
		//if(ParameterUtil.isChinese(companyName)){
		Company company=new Company();
	    company.setCompanyName(companyName);
	    Company comp= companyServiceImpl.queryCompanyByCopanyCondition(company);
	    if(comp!=null){
	    	return "此网校已经注册过,请勿重新注册";
	    }
//		}else{
//			return "网校名称只能为汉字";
//		}
		return "true";
	}
	
	/**
	 * 
	* @Title: checkDomain
	* @Description: 校验公司公司域名
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-15
	* @user zhang.zx
	 */
	@ResponseBody
	@RequestMapping(value="/checkDomain",method=RequestMethod.POST)
	public String checkDomain(String domain,HttpServletRequest request){
		log.info("验证域名开始----------------------------");
		try {
			if(ParameterUtil.isLetter(domain)){
				 Company company=new Company();
				    company.setDomain(domain.trim().toLowerCase()+".yunduoketang.com");
				    Company comp= companyServiceImpl.queryCompanyByCopanyCondition(company);
				    if(comp!=null){
				    	return "此域名已经被占用";
				    }
				    company.setDomain(null);
				    company.setDomainBackup(domain);
				    Company domainbackUpCompany= companyServiceImpl.queryCompanyByCopanyCondition(company);
				    if(domainbackUpCompany!=null){
				    	return "此域名已经被占用";
				    }
				    List<companySpecialDomain> arr=companyServiceImpl.findSpecialName(null);
				    if(arr.size()>0){
					    for(companySpecialDomain csd:arr){
					    	if(csd.getDomainName().contains(domain)){
					    		return "此域名已经被占用";
					    	}
					    }
				    }
			}else{
				return "域名只能填写一个英文单词";
			}
		} catch (Exception e) {
			log.info("机构已存在多个重复域名");
			return "此域名已经被占用";
		}
		log.info("验证域名结束----------------------------");
		return "true";
	   
	}
	
	//校验验证码
	@ResponseBody
	@RequestMapping(value="/checkCode")
	public boolean checkCode(String code,HttpServletRequest request){
			String captcha = (String) request.getSession().getAttribute("captcha");
			boolean flag=this.powerfulCode(captcha);
			if(code!=null){
				if(captcha.equals(code)||flag){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
			
			
	}
	
	/**
	 * 获取短信验证码
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/SMS/checkCode", method = RequestMethod.POST) 
	public Boolean checkSmsCode(HttpServletRequest request,HttpServletResponse response){
		String smsCode = request.getParameter("smsCode");
		boolean flag=this.powerfulCode(smsCode);
		if(SMSUtil.validateCode(request.getSession(), smsCode)||flag){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 发送短信验证码
	 * @param request
	 * @param phoneNum
	 * @param captcha
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sendSMS/{phoneNum}/{captcha}", method = RequestMethod.POST)
	public String sms(HttpServletRequest request,HttpServletResponse response,@PathVariable String phoneNum,@PathVariable String captcha){
		String captcha1 = (String) request.getSession().getAttribute("captcha");
	//	boolean flag=this.powerfulCode(captcha1);
		if(null!=captcha){
			if(!captcha.equals(captcha1)){
				return "{'flag':'error','message':'验证码不正确'}";
			}
		}else{
			return "{'flag':'error','message':'验证码不能为空'}";
		}
		Long sendSms_timestemp=null;
		for(Cookie c: request.getCookies()){
			if("sendSms_timestemp".equals(c.getName())){
				sendSms_timestemp=Long.getLong(c.getValue());
			}
		}
		if(null!=sendSms_timestemp){
			return "{'flag':'error','message':'您的访问过于频繁,尚需等待["+(System.currentTimeMillis()-sendSms_timestemp)/1000+"s]'}";
		}

		if(ParameterUtil.isMobilePhone(phoneNum)){
			try{
				SMSUtil.sendSMSCode(request, phoneNum,SMSUtil.SMS_TEMPLETE_REGISTER);
				Cookie cookie =new Cookie("sendSms_timestemp",""+System.currentTimeMillis());
				cookie.setMaxAge(6000);//存在60s
				response.addCookie(cookie);
				return "{'flag':'success','message':'短信已发送至您的手机'}";
			}catch(Exception e){
				e.printStackTrace();
				log.error(e);
				return "{'flag':'eorror','message':'出现意外，短信发送失败'}";
			}
			
		}else{
			return "{'flag':'error','message':'不是有效的手机号'}";
		}	
	}
	
	private Boolean powerfulCode(String code){
		if(code==null){
			return false;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
		String y=sdf.format(new Date()).substring(0,2);
		String m=sdf.format(new Date()).substring(2,4);
		String d=sdf.format(new Date()).substring(4,6);

		y=y.substring(1)+y.substring(0,1);
		m=m.substring(1)+m.substring(0,1);
		d=d.substring(1)+d.substring(0,1);
		System.out.println(m+d+y);
		System.out.println(code);
		return code.equals(m+d+y);
	}
	public String useTemplate(HttpServletRequest request,Integer id,Integer schoolId,Integer companyId){
		companyIndexTemplateServiceImpl.useTemplate(companyId, schoolId, id);
		List<SysConfigPrivatePageVo>list=sysBodySerivceImpl.publishToFront(companyId, schoolId, id);
		Ckey ckey=new Ckey();
		ckey.setModule(Constants.MODULE_BODY);
		ckey.setCompanyId(""+companyId);
		ckey.setSchoolId(""+companyId);
		CacheService.updateCache(ckey,list);
		return "success";
	}
	
	/**
	 * 对外提供的发送短信验证码
	 * @param request
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/send")
	public Object jsonpSendSms(HttpServletRequest request,String mobile) {
		String callback = request.getParameter("callback");
		
		ResultData<Map<String,Object>> json = new ResultData<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		json.setData(map);
		if(StringUtils.isBlank(mobile) || !ParameterUtil.isMobilePhone(mobile)) {
			json.setFlag(false);
			json.setMsg("请输入有效的手机号码");
			return callback + "(" +JSON.toJSONString(json) + ")";
		}
		String code = SMSUtil.generate();
		String content = "您的留言验证码：" + code + ",3分钟内有效。【在线网校】";
		String result = SmsClientSend.sendSmsTwo(request, mobile, content, 0, "liuyan");
		
		String status = result.substring(result.indexOf("<returnstatus>"),result.indexOf("</returnstatus>"));
		status = status.substring(status.indexOf(">") + 1);
		String message = result.substring(result.indexOf("<message>"),result.indexOf("</message>"));
		message = message.substring(message.indexOf(">") + 1);
		
		if("ok".equals(message)) {
			json.setFlag(true);
			json.setMsg(content);
			map.put("code", code);
		}else {
			json.setFlag(false);
			json.setMsg(message);
		}
		return callback + "(" +JSON.toJSONString(json) + ")";
	}
}