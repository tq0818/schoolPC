package com.yuxin.wx.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.company.ICompanyCouponsLibService;
import com.yuxin.wx.api.company.ICompanyCouponsPatchService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.pay.IPayOrderService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.api.user.IUserIntegralFlowService;
import com.yuxin.wx.api.user.IUserInviteRewardsRecordService;
import com.yuxin.wx.api.user.IUsersFrontMyCouponsService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.ViewResult;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyCouponsLib;
import com.yuxin.wx.model.company.CompanyCouponsPatch;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.user.UserIntegralFlow;
import com.yuxin.wx.model.user.UserInviteRewardsRecord;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.model.user.UsersFrontMyCoupons;
import com.yuxin.wx.vo.address.Address;
import com.yuxin.wx.vo.address.Result;
import com.yuxin.wx.vo.user.UsersFrontVo;

@Component
public class WebUtils {

    private static Log log = LogFactory.getLog("WebUtils");
    private static ICompanyCouponsPatchService companyCouponsPatchServiceImpl;

    @Autowired
    public void setCompanyCouponsPatchServiceImpl(ICompanyCouponsPatchService companyCouponsPatchServiceImpl) {
        WebUtils.companyCouponsPatchServiceImpl = companyCouponsPatchServiceImpl;
    }

    private static ISysConfigServiceService sysConfigServiceServiceImpl;

    @Autowired
    public void setISysConfigServiceServiceImpl(ISysConfigServiceService sysConfigServiceServiceImpl) {
        WebUtils.sysConfigServiceServiceImpl = sysConfigServiceServiceImpl;
    }

    private static IUsersFrontMyCouponsService usersFrontMyCouponsServiceImpl;

    @Autowired
    public void setUsersFrontMyCouponsServiceImpl(IUsersFrontMyCouponsService usersFrontMyCouponsServiceImpl) {
        WebUtils.usersFrontMyCouponsServiceImpl = usersFrontMyCouponsServiceImpl;
    }

    private static IUserInviteRewardsRecordService userInviteRewardsRecordServiceImpl;

    @Autowired
    public void setUserInviteRewardsRecordServiceImpl(IUserInviteRewardsRecordService userInviteRewardsRecordServiceImpl) {
        WebUtils.userInviteRewardsRecordServiceImpl = userInviteRewardsRecordServiceImpl;
    }

    private static IUserIntegralFlowService userIntegralFlowServiceImpl;

    @Autowired
    public void setUserIntegralFlowServiceImpl(IUserIntegralFlowService userIntegralFlowServiceImpl) {
        WebUtils.userIntegralFlowServiceImpl = userIntegralFlowServiceImpl;
    }

    private static ICompanyCouponsLibService companyCouponsLibServiceImpl;

    @Autowired
    public void setCompanyCouponsLibServiceImpl(ICompanyCouponsLibService companyCouponsLibServiceImpl) {
        WebUtils.companyCouponsLibServiceImpl = companyCouponsLibServiceImpl;
    }

    private static IPayOrderService payOrderServiceImpl;

    @Autowired
    public void setPayOrderServiceImpl(IPayOrderService payOrderServiceImpl) {
        WebUtils.payOrderServiceImpl = payOrderServiceImpl;
    }

    private static IUsersFrontService usersFrontService;

    @Autowired
    public void setUsersFrontService(IUsersFrontService usersFrontService) {
        WebUtils.usersFrontService = usersFrontService;
    }

    private static ICompanyFunctionSetService companyFunctionSetServiceImpl;

    @Autowired
    public void setcompanyFunctionSetServiceImpl(ICompanyFunctionSetService companyFunctionSetServiceImpl) {
        WebUtils.companyFunctionSetServiceImpl = companyFunctionSetServiceImpl;
    }

    private static ISysConfigServiceService sysConfigServiceImpl;

    @Autowired
    public void setSysconfigServiceImpl(ISysConfigServiceService sysConfigServiceImpl) {
        WebUtils.sysConfigServiceImpl = sysConfigServiceImpl;
    }
    
	private static IUsersService usersServiceImpl;
	@Autowired
	public void setUsersServiceImpl(IUsersService usersServiceImpl) {
        WebUtils.usersServiceImpl = usersServiceImpl;
    }
    /**
     * 判断当前请求是否ajax请求
     * 
     * @param request
     * @return
     */
    public static Boolean isAjax(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && "XMLHttpRequest".equals(requestType)) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前请求是否ajax请求
     * 
     * @param request
     * @return
     */
    public static Boolean isAjax(ServletRequest req) {
        HttpServletRequest request = toHttp(req);
        return isAjax(request);
    }

    /**
     * response直接返回json对象
     * 
     * @param ServletResponse
     *            resp
     * @param ViewResult
     *            result
     */
    public static void sendJson(ServletResponse resp, ViewResult result) {
        JSONObject responseJSONObject = JSONObject.fromObject(result);
        HttpServletResponse response = toHttp(resp);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(403);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(responseJSONObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * ServletRequest 转换为 HttpServletRequest
     * 
     * @param request
     * @return
     */
    public static HttpServletRequest toHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    /**
     * ServletResponse 转换为 HttpServletResponse
     * 
     * @param request
     * @return
     */
    public static HttpServletResponse toHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }
    /**
     * 登录用户缓存key
     */
    public static final String LOGIN_USER = "loginUser";
    /**
     * 机构使用视频服务key
     */
    public static final String USE_VIDEO = "useVideo";
    /**
     * 公司课程版本key
     */
    public static final String COURSE_FUNCTION = "courseFunction";
    /**
     * 当前访问机构
     */
    public static final String CURRENT_COMAPNY="company";
    /**
     * 当前访问机构ID
     */
    public static final String COMPANY_ID="COMPANY_ID";
    /**
     * 当前访问机构类型 0数校，1区，2校
     */
    public static final String CURRENT_IS_AREA="CURRENT_IS_AREA";
    
    public static final String COMPANY_INFO="companyInfo";
    /**
     *
     * Class Name: UserHolder.java
     * 
     * @Description: 从session中获取登录用户对象
     * @author liuxindong
     * @date 2014-12-9 下午3:19:47
     * @version 1.0
     * @param request
     * @return
     */
    public static Users getCurrentUser(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Users user = (Users) subject.getSession().getAttribute(LOGIN_USER);
            return user;
        }
        log.error("User is expired!");
        throw new RuntimeException("您长时间没有操作，系统已经自动将您退出，要继续操作请重新登录！");
    }

    /**
     *
     * Class Name: UserHolder.java
     * 
     * @Description: 从session中获取登录用户对象
     * @author liuxindong
     * @date 2014-12-9 下午3:19:47
     * @version 1.0
     * @param request
     * @return
     */
    public static Users getCurrentUser() {
        // Object obj = request.getSession().getAttribute(LOGIN_USER);
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Users user = (Users) subject.getSession().getAttribute(LOGIN_USER);
            return user;
        }
        log.error("User is expired!");
        throw new RuntimeException("您长时间没有操作，系统已经自动将您退出，要继续操作请重新登录！");
    }

    /**
     *
     * Class Name: UserHolder.java
     * 
     * @Description: 获取当前访问的公司ID
     * @author Chopin
     * @date 2015年2月2日
     * @version 1.0
     * @return
     */
    public static Integer getCurrentCompanyId() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
        	return (Integer) subject.getSession().getAttribute(COMPANY_ID);
        }
        throw new RuntimeException("您长时间没有操作，系统已经自动将您退出，要继续操作请重新登录！");
    }
    public static Company getCurrentCompany(){
    	Subject subject = SecurityUtils.getSubject();
    	if (subject != null) {
    		Company company = (Company) subject.getSession().getAttribute(CURRENT_COMAPNY);
            return company;
        }
        throw new RuntimeException("您长时间没有操作，系统已经自动将您退出，要继续操作请重新登录！");
    }
    /**
     * @Description: 获取当前机构的类别，1代表区，2代表校，0代表数字学校
     * @return
     */
    public static String getCurrentIsArea(){
    	Subject subject = SecurityUtils.getSubject();
    	if (subject != null) {
    		return (String) subject.getSession().getAttribute(CURRENT_IS_AREA);
        }
        throw new RuntimeException("您长时间没有操作，系统已经自动将您退出，要继续操作请重新登录！");
    }
    public static Integer getCurrentSchoolId() {
        Users user = getCurrentUser();
        return user != null ? user.getSchoolId() : null;
    }

    /**
     *
     * @Description: 获取登录用户的id
     * @author liuxindong
     * @date 2014-12-9 下午3:22:36
     * @version 1.0
     * @param request
     * @return
     */
    public static Integer getCurrentUserId(HttpServletRequest request) {
        Users user = getCurrentUser(request);
        return user != null ? user.getId() : null;
    }

    /**
     *
     * @Description: 获取登录用户的用户名
     * @author liuxindong
     * @date 2014-12-9 下午3:22:46
     * @version 1.0
     * @param request
     * @return
     */
    public static String getCurrentUserName(HttpServletRequest request) {
        Users user = getCurrentUser(request);
        return user.getUsername();
    }

    /**
     *
     * @Description: 获取登录用户所属分校id
     * @author liuxindong
     * @date 2014-12-9 下午3:23:03
     * @version 1.0
     * @param request
     * @return
     */
    public static Integer getCurrentUserSchoolId(HttpServletRequest request) {
        Users user = getCurrentUser(request);
        return user.getSchoolId();
    }

    /**
     *
     * Class Name: UserHolder.java
     * 
     * @Description: 把登录用户放到session中
     * @author liuxindong
     * @date 2014-12-9 下午3:20:25
     * @version 1.0
     * @param request
     * @param user
     */
    public static void setCurrentUser(HttpServletRequest request, Users user) {
        if (user == null) {
            throw new RuntimeException("您的登录操作无效！");
        }
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute(LOGIN_USER, user);
    }

    /**
     *
     * Class Name: UserHolder.java
     * 
     * @Description: 销毁session中的登录用户对象
     * @author liuxindong
     * @date 2014-12-9 下午3:20:51
     * @version 1.0
     * @param request
     */
    public static void distoryCurrentUser(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().removeAttribute(LOGIN_USER);
    }

    /**
     *
     * Class Name: UserHolder.java
     * 
     * @Description: 销毁session
     * @author liuxindong
     * @date 2014-12-9 下午3:27:32
     * @version 1.0
     * @param request
     */
    public static void distorySession(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().stop();
    }

    /**
     *
     * Class Name: WebUtils.java
     * 
     * @Description: 新手引导如果做完写入session
     * @author 权飞虎
     * @date 2015年5月28日 下午3:53:09
     * @modifier
     * @modify-date 2015年5月28日 下午3:53:09
     * @version 1.0
     */
    public static void setSatus() {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("isPass", "yes");
    }

    public static Object getSatus() {
        Subject subject = SecurityUtils.getSubject();
        return subject.getSession().getAttribute("isPass");
    }

    /**
     * 获取客户端真实IP
     * 
     * @author chopin
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.indexOf(",") > 0) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    public static Object getSessionAttribute(String name) {
        Subject subject = SecurityUtils.getSubject();
        return subject.getSession().getAttribute(name);
    }

    public static void setSessionAttribute(String name, Object value) {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute(name, value);
    }

    /**
     *
     * Class Name: WebUtils.java
     * 
     * @Description: 视频使用服务
     * @author zhang.zx
     * @date 2015年9月7日 上午10:23:00
     * @modifier
     * @modify-date 2015年9月7日 上午10:23:00
     * @version 1.0
     * @return
     */
    public static String getCompanyUseVideo() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            String type = (String) subject.getSession().getAttribute(USE_VIDEO);
            return type;
        }
        throw new RuntimeException("您长时间没有操作，系统已经自动将您退出，要继续操作请重新登录！");
    }

    /**
     *
     * Class Name: WebUtils.java
     * 
     * @Description: 课程使用方式
     * @author zhang.zx
     * @date 2015年9月7日 上午10:23:23
     * @modifier
     * @modify-date 2015年9月7日 上午10:23:23
     * @version 1.0
     * @return
     */
    public static String getCompanyCourseMethod() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            String type = (String) subject.getSession().getAttribute(COURSE_FUNCTION);
            return type;
        }
        throw new RuntimeException("您长时间没有操作，系统已经自动将您退出，要继续操作请重新登录！");
    }

    public static boolean checkChuPay(Integer userId, Integer companyId) {
        int count = payOrderServiceImpl.findOrderFinishCountByUserId(userId, companyId);
        return (count == 0 || count == 1) ? true : false;
    }

    /**
     * 查看机构的服务是否开启
     * 
     * @author licong
     * @date 2016年8月8日 下午5:47:54
     * @param
     * @param companyId
     * @param groupCode
     *            服务的code码
     * @return
     */
    public static boolean checkCompanyServiceIsOpen(Integer companyId, String groupCode) {
        SysConfigService search = new SysConfigService();
        search.setCompanyId(companyId);
        search.setGroupCode(groupCode);
        SysConfigService couponsConfig = sysConfigServiceServiceImpl.findExist(search);
        return (couponsConfig != null && couponsConfig.getDelFlag() == 1) ? true : false;
    }

    public static boolean moreThanZero(Double money) {
        if (money == null)
            return false;
        BigDecimal val = new BigDecimal(money);
        BigDecimal zero = new BigDecimal(0);
        return val.compareTo(zero) > 0 ? true : false;
    }

    public static void updateInteger(int type, UsersFrontVo user, Integer rewardsIntegral) {
        // 邀请人插入积分变化记录
        UserIntegralFlow uif1 = new UserIntegralFlow();
        if (0 == type)
            uif1.setReason("被邀请人注册");
        if (1 == type)
            uif1.setReason("被邀请人首次消费");
        uif1.setUpdateTime(new Date());
        uif1.setRecord(rewardsIntegral);
        Integer integralRemaining = user.getIntegralRemaining();
        integralRemaining = integralRemaining == null ? 0 : integralRemaining;
        uif1.setLastRecord(integralRemaining + rewardsIntegral);
        uif1.setStuId(user.getStuId());
        uif1.setUserId(user.getId());
        userIntegralFlowServiceImpl.insert(uif1);
        // 更新邀请人的积分余额
        UsersFront uf = new UsersFront();
        uf.setId(user.getId());
        uf.setIntegralRemaining(integralRemaining + rewardsIntegral);
        usersFrontService.update(uf);
    }

    public static String checkChuRegist(HttpServletRequest request, Integer companyId) {
        String inviteCode = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return inviteCode;
        }
        for (Cookie cookie : cookies) {
            if ("shareCode".equals(cookie.getName())) {
                inviteCode = cookie.getValue();
                break;
            }
        }
        if (StringUtils.isBlank(inviteCode)) {
            return null;
        }
        return inviteCode;
    }

    /**
     * 根据邀请人获取被邀请人信息
     * 
     * @author licong
     * @date 2016年8月5日 上午11:10:54
     * @param
     * @param invitedPerson
     *            邀请人
     * @return invitePerson 被邀请人
     */
    public static UsersFrontVo getInviteUsersFrontVo(UsersFrontVo invitedPerson) {
        UsersFrontVo invitePerson = null;
        UserInviteRewardsRecord uirr = new UserInviteRewardsRecord();
        uirr.setCompanyId(invitedPerson.getCompanyId());
        uirr.setUserId(invitedPerson.getId());
        uirr.setReason(0);
        invitePerson = userInviteRewardsRecordServiceImpl.findRewardRecordBySearch(uirr);
        return invitePerson;
    }

    public static void insertUserCoupons(UsersFrontVo user, String rewardsCode, Double insCashNum, Integer useDate) {
        // 检查批次，如果没有邀请码批次则生成一个批次
        CompanyCouponsPatch ccp = new CompanyCouponsPatch();
        ccp.setCompanyId(user.getCompanyId());
        ccp.setUseWay(3);
        ccp = companyCouponsPatchServiceImpl.findCompanyCouponsPatchBySearch(ccp);
        if (ccp == null) {
            ccp = new CompanyCouponsPatch();
            ccp.setName("邀请码");
            ccp.setTotalNum(0);
            ccp.setStatus(1 + "");
            ccp.setIssueWay(0);
            ccp.setUseWay(3);
            ccp.setDelFlag(0);
            ccp.setCompanyId(user.getCompanyId());
            ccp.setSendDate(new Date());
            companyCouponsPatchServiceImpl.insert(ccp);
        }
        // 插入邀请码说明
        CompanyCouponsLib ccl = new CompanyCouponsLib();
        ccl.setCode(rewardsCode);
        ccl.setStatus(0);
        ccl.setPatchId(ccp.getId());
        ccl.setUserId(user.getId());
        ccl.setInsCashNum(insCashNum);
        ccl.setUseWay(3 + "");
        ccl.setUseRange(3);
        ccl.setDelFlag(0);
        if (useDate != null && useDate > 0) {
            ccl.setTimeLimitFrom(new Date());
            ccl.setTimeLimitTo(DateUtil.addDate(new Date(), useDate));
        }
        companyCouponsLibServiceImpl.insert(ccl);
        // 插入我的邀请码
        UsersFrontMyCoupons ufmc = new UsersFrontMyCoupons();
        ufmc.setUserId(user.getId());
        ufmc.setCouponsCode(rewardsCode);
        ufmc.setStatus(0);
        usersFrontMyCouponsServiceImpl.insert(ufmc);
        // 邀请码批次发放数量+1
        Integer totalNum = ccp.getTotalNum();
        ccp.setTotalNum(totalNum + 1);
        companyCouponsPatchServiceImpl.update(ccp);
    }

    /**
     * 生成一个邀请人的奖励记录
     * 
     * @author licong
     * @date 2016年8月5日 下午2:30:53
     * @param
     * @param oneInvitePerson
     *            邀请人
     * @param twoInviteFlag
     *            二级邀请开关
     * @param twoInvitePerson
     *            被邀请人
     * @param reason
     *            0-邀请注册 1-被邀请人注册， 2-被邀请人首次消费，3-二级被邀请人注册 4-二级被邀请人首次消费
     * @return
     */
    public static UserInviteRewardsRecord getInvitReward(UsersFrontVo invitedPerson, UsersFrontVo parentPerson, UsersFrontVo cidPerson, UsersFrontVo ccidPerson,
            Integer reason) {
        UserInviteRewardsRecord uirr = new UserInviteRewardsRecord();
        uirr.setUserId(invitedPerson.getId());
        if (parentPerson != null)
            uirr.setParentId(parentPerson.getId());
        if (cidPerson != null)
            uirr.setCid(cidPerson.getId());
        if (ccidPerson != null)
            uirr.setCcid(ccidPerson.getId());
        uirr.setCreateTime(new Date());
        uirr.setReason(reason);
        uirr.setCompanyId(invitedPerson.getCompanyId());
        return uirr;
    }

    public static Double fromatDouble(Double money) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(money));
    }

    /**
     * 
     * Class Name: WebUtils.java
     * 
     * @Description: 获得权限对象
     * @author 周文斌
     * @date 2016-12-13 上午10:20:29
     * @modify 2016-12-13 上午10:20:29
     * @version
     * @param request
     * @return
     */
    public static Integer getSysServiceDredge(String code) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Object ssd = subject.getSession().getAttribute(code);
            if (ssd != null) {
                return (Integer) ssd;
            } else {
                return null;
            }
        }
        log.error("User is expired!");
        throw new RuntimeException("您长时间没有操作，系统已经自动将您退出，要继续操作请重新登录！");
    }

    /**
     * 注册时获取用户的省市县
     * 
     * @author licong
     * @date 2017年2月16日 下午5:07:00
     * @param
     * @param request
     * @param usersfront
     */
    public static void setUserCity(HttpServletRequest request, Users users) {
        String ip = WebUtils.getIpAddr(request);
        if (ip != null) {
            // 正式环境ip可能会是以,分割的两个ip地址，只有第一个有用
            if (ip.indexOf(",") != -1) {
                String[] ips = ip.split(",");
                ip = ips[0];
            }
            // try {
            // AddressUtils.getAddresses("ip="+ip, "utf-8");
            // } catch (UnsupportedEncodingException e) {
            // e.printStackTrace();
            // }

            Result<Address> add = AliIpAddressUtil.getAddress(ip);
            // if (add != null) {
            // log.error(add.toString());
            // if ("ok".equals(add.getMsg()) && add != null && add.getResult()
            // != null) {
            // users.setUserCity(add.getResult().getArea());
            // }
            // }
            if (add.getCode() != null && add.getCode() == 0) {
                if (add.getData() != null) {
                    Address address = add.getData();
                    users.setUserCity(address.getRegion() + "-" + address.getCity() + "-" + address.getCounty());
                    usersServiceImpl.update(users);
                }
            }
        }
    }

    /**
     * @Description: 获取服务开关
     * @author dongshuai
     */
    public static SysConfigService getConfigService(String service) {
        SysConfigService search = new SysConfigService();
        search.setCompanyId(getCurrentCompanyId());
        search.setGroupCode(service);
        return sysConfigServiceImpl.findExist(search);
    }

    /**
     * @Description: 获取方法开关
     * @author dongshuai
     */
    public static CompanyFunctionSet getFunctionSet(String code) {

        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(getCurrentCompanyId());
        companyFunctionSet.setFunctionCode(code);
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        return cfs;
    }
}
