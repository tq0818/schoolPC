package com.yuxin.wx.controller.company;

import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.vo.company.CompanyVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller of CompanyLiveStaticDetail
 * @author zhang.zx
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/companySetInfo")
public class CompanyCinfigController {
	private Log log=LogFactory.getLog("log");
	@Autowired
	private IUsersService iUsersService;
	@Autowired
	private ICompanyService companyServiceImpl;
	
	/**
	 * 
	 * Class Name: CompanyCinfigController.java
	 * @Description: 根据手机号查询公司详细信息
	 * @author zhang.zx
	 * @date 2015年8月31日 上午11:21:01
	 * @modifier
	 * @modify-date 2015年8月31日 上午11:21:01
	 * @version 1.0
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryDetail/{mobile}")
	public CompanyVo queryCompanyDetailInfo(@PathVariable String mobile){
		CompanyVo com=new CompanyVo();
		try {
			//根据手机号查询用户信息
			Users user=new Users();
			user.setMobile(mobile);
			user.setUserType("USER_TYPE_ORG");
			Users u=iUsersService.findUsersByConfition(user);
			if(null!=u){
				//根据用户所在公司的id查询公司信息
			    Company c=companyServiceImpl.findCompanyById(u.getCompanyId());
				//封装数据
			    com.setId(u.getId());
			    com.setcId(c.getId());
				com.setCompanyName(c.getCompanyName());
				com.setLoginUsername(u.getUsername());
				com.setContactAddress(c.getCompanyDistrict());
				com.setLoginMobile(u.getMobile());
				com.setLoginEmail(u.getEmail());
				com.setDomain(c.getDomain());
				com.setRegistTime(c.getRegistTime());
				com.setCreateTime(c.getCreateTime());
				com.setStatus(c.getStatus());
				com.setMemberLevel(c.getMemberLevel());
				com.setMemberStartDate(c.getMemberStartDate());
				com.setMemberEndDate(c.getMemberEndDate());
			}
		} catch (Exception e) {
			log.error("查询出错",e);
			e.printStackTrace();
		}
		return com;
	}
	
	/**
	 * 
	 * Class Name: CompanyCinfigController.java
	 * @Description: 重置密码
	 * @author zhang.zx
	 * @date 2015年8月31日 下午12:40:57
	 * @modifier
	 * @modify-date 2015年8月31日 下午12:40:57
	 * @version 1.0
	 * @param pwd
	 * @param userName
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/resetPwd")
	public String newPwd(Integer userId, String pwd, HttpServletRequest request) {
		try {
			Users user=iUsersService.findUsersById(userId);
			String password = new Md5Hash(pwd, ByteSource.Util.bytes(user.getUsername()+"salt")).toHex();
			user.setPassword(password);
			iUsersService.update(user);
		} catch (Exception e) {
			log.error("修改密码失败",e);
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * 
	 * Class Name: CompanyCinfigController.java
	 * @Description: 公司服务延期
	 * @author zhang.zx
	 * @date 2015年9月1日 上午11:25:28
	 * @modifier
	 * @modify-date 2015年9月1日 上午11:25:28
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addServiceTime")
	public String addCompanyServiceTime(Integer companyId,Integer day){
		//如果当前时间没有到期这在当前时间上加，如果服务已到期，则按当前时间加
//		if(flag>0){
//			Company c=companyServiceImpl.findCompanyById(companyId);
//			Company company=new Company();
//			company.setId(companyId);
//			company.setMemberEndDate(DateUtil.addDate(c.getMemberEndDate(), day));
//			companyServiceImpl.update(company);
//		}else{
			Company company=new Company();
			company.setId(companyId);
			company.setMemberEndDate(DateUtil.addDate(new Date(), day));
			companyServiceImpl.update(company);
		//}
		return "success";
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
