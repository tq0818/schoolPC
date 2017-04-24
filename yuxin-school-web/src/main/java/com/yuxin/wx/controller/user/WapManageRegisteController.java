package com.yuxin.wx.controller.user;

import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.ParameterUtil;
import com.yuxin.wx.utils.SMSUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * 网校注册
 * @author licong
 */
@Controller
@RequestMapping("/wap_manageRegiste")
public class WapManageRegisteController {
	private static Log log=LogFactory.getLog("log");
	
	@Autowired
	private IUsersService usersServiceImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private ISysConfigDictService sysConfigDictServiceImpl;
	/**
	 * @Description: 校验用户名
	 * @author licong
	 * @date 2016年4月26日 下午5:57:21
	 * @param  
	 * @param userName
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkUserName",method=RequestMethod.POST)
	public String checkUserName(String userName,HttpServletRequest request){
		if(null == userName || "".equals(userName))
			return "用户名不能为空";
		if(!ParameterUtil.isUserName(userName))
			return "只能以字母开头并由数字、字母或下划线组成";
		Users user=usersServiceImpl.queryUserByName(userName);
		if(user!=null)
			return "用户名已经被注册";
		return "true";
	}
	
	/**
	 * @Description: 校验手机号
	 * @author licong
	 * @date 2016年4月26日 下午5:57:01
	 * @param  
	 * @param mobile
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkMobile",method=RequestMethod.POST)
	public boolean checkMobile(Users users,HttpServletRequest request){
		List<Users> arr=usersServiceImpl.findUserByCondition(users);
		if(arr!=null&&arr.size()>0){
			return false;
		}
		return true;
	}
	
	/**
	 * @Description: 校验图片验证码
	 * @author licong
	 * @date 2016年4月26日 下午5:58:18
	 * @param  
	 * @param code
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkImgCode")
	public boolean checkCode(String code,HttpServletRequest request){
		String captcha = (String) request.getSession().getAttribute("captcha");
		if(code==null||"".equals(code))
			return false;
		if(!captcha.toLowerCase().equals(code.toLowerCase()) && !this.powerfulCode(code))
			return false;
		return true;
	}
	/**
	 * @Description: 验证短信验证码
	 * @author licong
	 * @date 2016年4月26日 下午6:01:11
	 * @param  
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/SMS/checkCode", method = RequestMethod.POST) 
	public Boolean checkSmsCode(HttpServletRequest request,HttpServletResponse response,String smsCode){
		if(smsCode == null || "".equals(smsCode))
			return false;
		if(this.powerfulCode(smsCode) || SMSUtil.validateCode(request.getSession(), smsCode)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @Description: 发送短信验证码
	 * @author licong
	 * @date 2016年4月26日 下午6:01:52
	 * @param  
	 * @param request
	 * @param response
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
				log.error(e,e);
				e.printStackTrace();
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
}
