package com.yuxin.wx.common;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuxin.wx.api.auth.IAuthUserRoleService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyLiveConcurrentService;
import com.yuxin.wx.api.company.ICompanyMemberServiceChangelogService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyNewStepService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.company.IOrganLeaveMessageService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.api.system.ISysServiceDredgeConfigService;
import com.yuxin.wx.api.user.IUsersLoginSessionService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyLiveConcurrent;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyMemberServiceChangelog;
import com.yuxin.wx.model.company.CompanyNewStep;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.OrganLeaveMessage;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersLoginSession;
import com.yuxin.wx.shiro.service.JedisShiroCacheManager;
import com.yuxin.wx.util.DateUtil;
import com.yuxin.wx.util.FileQNUtils;
import com.yuxin.wx.utils.AliIpAddressUtil;
import com.yuxin.wx.utils.CaptchaUtil;
import com.yuxin.wx.utils.NetworkUtil;
import com.yuxin.wx.utils.SMSUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.address.Address;
import com.yuxin.wx.vo.address.Result;
import com.yuxin.wx.vo.company.CompanyManageLoginHistoryVo;
import com.yuxin.wx.vo.company.CompanyOrgMessageVo;
import com.yuxin.wx.vo.system.SysServiceDredgeVo;

/**
 * 
 * @ClassName: BaseWebController
 * @Description: controller的基类，
 * @author liuxindong
 * @date 2014-12-9 下午3:21:39
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class BaseWebController {

    private Log log = LogFactory.getLog("log");

    @Autowired
    private ISysServiceDredgeConfigService sysServiceDredgeImpl;
    @Autowired
    private IUsersService usersServiceImpl;
    @Autowired
    private ICompanyService companyServiceImpl;
    @Autowired
    private ICompanyNewStepService companyNewStepServiceImpl;
    @Autowired
    private IUsersLoginSessionService usersLoginSessionServiceImpl;
    @Autowired
    private ICompanyMemberServiceService companyMemberServiceServiceImpl;
    @Autowired
    private ICompanyServiceStaticService companyServiceStaticServiecImpl;
    @Autowired
    private ICompanyLiveConcurrentService companyLiveConcurrentServiceImpl;
    @Autowired
    private ICompanyMemberServiceChangelogService changeLogServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetImpl;
    @Autowired
    private IAuthUserRoleService authUserRoleServiceImpl;

    @Autowired
    private IOrganLeaveMessageService organLeaveMessageServiceImpl;

    @Autowired
    private ISysConfigServiceService sysConfigServiceServiceImpl;
    @Autowired
    private JedisShiroCacheManager jedisShiroCacheManager;

    @RequestMapping(value = "/index", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView index(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("教科院")){
            mv.setViewName("redirect:/query/statistics/index");
        }else if(subject.hasRole("区县负责人")){
            mv.setViewName("redirect:/query/areastatistics/index");
        }else if(subject.hasRole("学校负责人")){
            mv.setViewName("redirect:/query/orgstatistics/index");
        }else{
            mv.setViewName("index/index");
        }
        Session session = subject.getSession(true);
        Company currtCompany = companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
        if (currtCompany != null) {
            Date registTime = currtCompany.getRegistTime();
            Integer max = Integer.parseInt(CacheService.dictCode2Name("AUTHORITY_ALARM_DAY"));
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            Calendar trial = Calendar.getInstance();
            trial.setTime(registTime);
            int days = now.compareTo(trial);
            if (max - days > 0) {
                session.setAttribute("trial", true);
            } else {
                session.setAttribute("trial", false);
            }

            // 查询机构是否开启对应的服务

            // 直播服务(为了兼容之前的错误数据，至格的ce7网校存在的一种情况.这是很矬的办法。。。。)
            boolean liveService = true;
            boolean videoService = true;
            // 名师专题-老师动态回复
            boolean serviceTeacher = true;
            // 社区问答-最新未作答问题
            boolean serviceQa = true;
            // 查询未开通服务
            List<SysConfigService> scservList = sysConfigServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
            for (SysConfigService s : scservList) {
                if (s.getGroupCode().equals("SERVICE_LIVE")) {
                    liveService = false;
                }
                if (s.getGroupCode().equals("SERVICE_TEACHER")) {
                    serviceTeacher = false;
                }
                if (s.getGroupCode().equals("SERVICE_QUESTION_ANSWER")) {
                    serviceQa = false;
                }
                if (s.getGroupCode().equals("SERVICE_VIDEO")) {
                    videoService = false;
                }
            }
            model.addAttribute("liveService", liveService);
            model.addAttribute("videoService", videoService);
            model.addAttribute("serviceTeacher", serviceTeacher);
            model.addAttribute("serviceQa", serviceQa);

            // 课程评论
            CompanyFunctionSet CompanyFunctionSet = new CompanyFunctionSet();
            CompanyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
            CompanyFunctionSet.setFunctionCode("COURSE_COMMENT");
            List<CompanyFunctionSet> CompanyFunctionSetList = companyFunctionSetImpl.findCompanyFunctionSetByPage(CompanyFunctionSet);
            CompanyFunctionSet courseComment = CompanyFunctionSetList != null && CompanyFunctionSetList.size() > 0 ? CompanyFunctionSetList.get(0) : null;

            if (courseComment != null && "1".equals(courseComment.getStatus())) {
                model.addAttribute("courseComment", true);
            }

        }
        return mv;
    }

    /**
     * 查询产品公告、服务消息
     * 
     * @author licong
     * @date 2016年10月19日 下午4:08:01
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryMessage")
    public Object queryMessage(CompanyOrgMessageVo search) {
        search.setCompanyId(WebUtils.getCurrentCompanyId());
        return companyServiceImpl.queryMessageServiceList(search);
    }

    @ResponseBody
    @RequestMapping("/queryCompanyService")
    public Object queryCompanyService() {
        Map<String, Object> map = new HashMap<String, Object>();
        Company company = companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
        Integer companyId = company.getId();
        Date date = new Date();
        CompanyServiceStatic css = companyServiceStaticServiecImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
        double usedVideo = (css.getVideoStorage() != null ? css.getVideoStorage() : 0.0);
        long crs = Long.parseLong(css.getResourceStorage() != null ? css.getResourceStorage() : "0");
        usedVideo += FileQNUtils.convertFileSize(crs);
        usedVideo = new BigDecimal(usedVideo).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        double cvf = (css.getVideoFlow() != null ? css.getVideoFlow() : 0.0);
        long crf = Long.parseLong(css.getResourceFlow() != null ? css.getResourceFlow() : "0");
        cvf += FileQNUtils.convertFileSize(crf);

        cvf = new BigDecimal(cvf).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        css.setVideoStorage(usedVideo);
        css.setVideoFlow(cvf);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("companyId", companyId);
        param.put("concurrentMonth",
                Integer.parseInt((date.getYear() + 1900) + "" + ((date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1))));
        // 查询本月并发
        CompanyLiveConcurrent clc = companyLiveConcurrentServiceImpl.findLiveByComidAndDate(param);
        map.put("clc", clc);
        // 下月并发
        Date time = DateUtil.addMonthsToDate(date, 1);
        Integer concurrentMonths = Integer
                .parseInt((time.getYear() + 1900) + "" + ((time.getMonth() + 1) < 10 ? "0" + (time.getMonth() + 1) : (time.getMonth() + 1)));

        param.clear();
        param.put("companyId", companyId);
        param.put("concurrentMonth", concurrentMonths);
        CompanyLiveConcurrent clcn = companyLiveConcurrentServiceImpl.findLiveByComidAndDate(param);
        map.put("clcn", clcn);

        map.put("company", company);
        //map.put("serivce", companyStanderdServeicImpl.findServiceByLevel(company.getMemberLevel()));
        map.put("cms", companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId()));
        map.put("css", css);
        return map;
    }

    @RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView login(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("../../login");
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        //获取当前访问域名对应的companyid
        Integer port=(Integer)request.getServerPort();
        String rootPath="";
        if(80==port){
        	rootPath=request.getServerName()+request.getContextPath();
        }else{
        	rootPath=request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        }
        //通过rootPath获取CompanyId，先查缓存，缓存没有在查数据库
        Cache<String,Integer> cache=jedisShiroCacheManager.getCache(WebUtils.COMPANY_INFO);
        Integer companyId=cache.get(rootPath);
        if(companyId==null){
        	companyId=companyServiceImpl.findComanyIdByRootPath(rootPath);
        	if(companyId!=null){
        		cache.put(rootPath, companyId);
        	}else{
        		response.sendRedirect(request.getServletPath()+"/fonts/404.html");;
        		return null;
        	}
        }
        session.setAttribute(WebUtils.COMPANY_ID, companyId);
        if (subject.isAuthenticated()) {// 已经成功登录过,直接跳到首页
            // 已登录时，且没有选择过服务则踢到选择服务
            List<CompanyNewStep> l = companyNewStepServiceImpl.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
            if (l == null || l.isEmpty() || l.get(0).getNewStepFlag() == 0) {
                mv.setViewName("redirect:/serviceGroup/chooseCompanyService");// 跳到选择服务页
            } else {
                /* 直接跳首页 */
                mv.setViewName("redirect:index");// 跳首页
            }
            return mv;
        }
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        if (userName != null && password != null) {
            password = new Md5Hash(password, ByteSource.Util.bytes(userName + "salt")).toHex();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            try {
                subject.login(token);
                Users users = usersServiceImpl.queryUserByName(userName);
                session.setAttribute(WebUtils.LOGIN_USER, users);
                // 设置公司的相关信息 add by jaler 16.11.1
                Company company = companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
                session.setAttribute(WebUtils.CURRENT_COMAPNY, company);
                session.setAttribute(WebUtils.CURRENT_IS_AREA,company.getIsArea());
                if (company.getMemberLevel() < 20) {
                    List<SysServiceDredgeVo> ssdVo = sysServiceDredgeImpl.findDredgeByCom(company.getId());
                    if (ssdVo != null) {
                        for (SysServiceDredgeVo s : ssdVo) {
                            session.setAttribute(s.getName(), (s.getStatus() != null ? s.getStatus() : 0));
                        }
                    }
                }
                // 设置登录后的状态
                setUserStatus();
                try {
                    // 将机构使用视频服务存入session
                    CompanyMemberService service = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
                    if (service != null) {
                        session.setAttribute(WebUtils.USE_VIDEO, service.getVideoServiceProvider());
                    }
                    // 将公司课程版本存入session(1表示简易版，0或空值表示复杂版)
                    CompanyFunctionSet search = new CompanyFunctionSet();
                    search.setCompanyId(WebUtils.getCurrentCompanyId());
                    search.setFunctionCode("COMPANY_FUNCTION_COURSE");
                    CompanyFunctionSet functionSet = companyFunctionSetImpl.findCompanyUseCourse(search);
                    if (functionSet instanceof Object) {
                        session.setAttribute(WebUtils.COURSE_FUNCTION, functionSet.getStatus());
                    } else {
                        session.setAttribute(WebUtils.COURSE_FUNCTION, "");
                    }

                } catch (Exception e) {
                    log.error("存入session值失败", e);
                }
                try {
                	//异步插入用户登录日志
                	new Thread(new UserLoginThread(usersLoginSessionServiceImpl,session,users,request)).start();

                } catch (Exception e) {
                    log.error(e, e);
                    e.printStackTrace();
                }

                // 瑞哥说，暂时全部跳引导页 2015-6-4
                // 瑞哥说，跳到首页 2015-8-17
                // 未登录时，且没有选择过服务则踢到选择服务
                List<CompanyNewStep> l = companyNewStepServiceImpl.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
                if (l == null || l.isEmpty() || l.get(0).getNewStepFlag() == 0) {
                    mv.setViewName("redirect:/serviceGroup/chooseCompanyService");// 跳到选择服务页
                } else {
                    mv.setViewName("redirect:index");// 跳首页
                }
            } catch (UnknownAccountException e) {
                log.error("用户名或密码错误", e);
                mv.addObject("info", "用户名或密码错误");
            } catch (IncorrectCredentialsException e) {
                mv.addObject("info", "用户名或密码错误");
                log.error("用户名或密码错误", e);
            } catch (AuthenticationException e) {
                // 其他错误，比如锁定，如果想单独处理请单独catch 处理
                mv.addObject("info", "账号异常，请联系管理员");
                log.error("账号异常，请联系管理员", e);
            }

        } else {
            // mv.addObject("info", "用户名不能为空");
        }

        return mv;
    }

    @RequestMapping(value = "/firstlogin", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView firstlogin(HttpServletRequest request, Model model) {
        log.info("============首次登录==========");
        ModelAndView mv = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        if (userName != null && password != null) {
            password = new Md5Hash(password, ByteSource.Util.bytes(userName + "salt")).toHex();

            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            try {
                subject.login(token);
                Users users = usersServiceImpl.queryUserByName(userName);
                session.setAttribute("loginUser", users);
                // 设置登录后的状态
                setUserStatus();

            } catch (UnknownAccountException e) {
                mv.addObject("info", "用户名或密码错误");
                log.error("============用户名或密码错误==========", e);

            } catch (IncorrectCredentialsException e) {
                mv.addObject("info", "用户名或密码错误");
                log.error("============用户名或密码错误==========", e);
            } catch (AuthenticationException e) {
                // 其他错误，比如锁定，如果想单独处理请单独catch 处理
                mv.addObject("info", "账号异常，请联系管理员");
                log.error("============账号异常==========", e);
            }

        }
        log.info("============首次登录完成，跳转到选择服务页面==========");
        mv.setViewName("redirect:/serviceGroup/chooseCompanyService");// 跳到选择服务页
        return mv;
    }

    // 管理人员登录
    @RequestMapping(value = "/manageLogin/{companyId}/{userid}", method = RequestMethod.GET)
    public ModelAndView manageLogin(HttpServletRequest request, Model model, @PathVariable Integer userid, @PathVariable Integer companyId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("../../login");
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        Users users = usersServiceImpl.findUserByCompanyIdAndUserType(companyId);

        UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(), users.getPassword());
        try {
            subject.login(token);
            session.setAttribute("loginUser", users);
            // 设置登录后的状态
            setUserStatus();
            try {
                // 将机构使用视频服务存入session
                CompanyMemberService service = companyMemberServiceServiceImpl.findByCompanyId(companyId);
                if (service != null) {
                    session.setAttribute("useVideo", service.getVideoServiceProvider());
                }
                // 将公司课程版本存入session(1表示简易版，0或空值表示复杂版)
                CompanyFunctionSet search = new CompanyFunctionSet();
                search.setCompanyId(companyId);
                search.setFunctionCode("COMPANY_FUNCTION_COURSE");
                CompanyFunctionSet functionSet = companyFunctionSetImpl.findCompanyUseCourse(search);
                if (functionSet instanceof Object) {
                    session.setAttribute("courseFunction", functionSet.getStatus());
                } else {
                    session.setAttribute("courseFunction", "");
                }

            } catch (Exception e) {
                log.error("存入session值失败", e);
                e.printStackTrace();
            }
            try {
            	//异步插入用户登录日志
            	new Thread(new UserLoginThread(usersLoginSessionServiceImpl,session,users,request)).start();
            } catch (Exception e) {
                log.error(e, e);
            }

            // 瑞哥说，暂时全部跳引导页 2015-6-4
            // 瑞哥说，跳到首页 2015-8-17
            List<CompanyNewStep> l=(List<CompanyNewStep>)WebUtils.getSessionAttribute("l_companyNewSteps");
            if(l==null){
            		l=companyNewStepServiceImpl.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
            		WebUtils.setSessionAttribute("l_companyNewSteps",l);
            }
            if (l == null || l.isEmpty() || l.get(0).getNewStepFlag() == 0) {
                mv.setViewName("redirect:/serviceGroup/chooseCompanyService");// 跳到选择服务页
            } else {
                mv.setViewName("index/index");// 跳首页
            }

            CompanyMemberServiceChangelog cmscl = new CompanyMemberServiceChangelog();
            cmscl.setChangeReason("登录用户后台,companyId:" + companyId);
            cmscl.setUpdator(userid);
            cmscl.setChangeTime(new Date());
            cmscl.setCompanyId(companyId);
            changeLogServiceImpl.insert(cmscl);

        } catch (UnknownAccountException e) {
            mv.addObject("info", "用户名或密码错误");
            log.error("用户名或密码错误", e);
            e.printStackTrace();

        } catch (IncorrectCredentialsException e) {
            mv.addObject("info", "用户名或密码错误");
            log.error("用户名或密码错误", e);
            e.printStackTrace();
        } catch (AuthenticationException e) {
            // 其他错误，比如锁定，如果想单独处理请单独catch 处理
            mv.addObject("info", "账号异常，请联系管理员");
            log.error("账号异常，请联系管理员", e);
            e.printStackTrace();
        }
        return mv;
    }

    public void writeLogData(Users users, HttpServletRequest request, Session session) throws Exception {
        // 记录登录日志
//        UsersLoginSession loginSession = usersLoginSessionServiceImpl.findHistoryByUserId("" + users.getId());
//        if (loginSession != null && 1 == loginSession.getStatus()) {
//            loginSession.setStatus(2);
//            usersLoginSessionServiceImpl.update(loginSession);
//        }
//
//        UsersLoginSession userSession = new UsersLoginSession();
//        userSession.setUserId("" + users.getId());
//        userSession.setLoginTime(new Date());
//        userSession.setStatus(1);
//        userSession.setSessionId(session.getId().toString());
//        userSession.setMacAddress(request.getRemoteAddr());
//        String ip = WebUtils.getIpAddr(request);
//        if (ip != null) {
//            userSession.setIp(ip);
//            // Result<Address> add = AliIpAddressUtil.getAddress(request,ip);
//            // if("ok".equals(add.getMsg()) && add.getResult() != null){
//            // userSession.setAddress(add.getResult().getArea());
//            // userSession.setIsp(add.getResult().getType());
//            // }
//            userSession.setAddress(AddressUtils.getAddresses("ip=" + ip, "utf-8"));
//            userSession.setIsp(AddressUtils.getIsp("ip=" + ip, "utf-8"));
//        }
//        usersLoginSessionServiceImpl.insert(userSession);
    	//异步插入用户登录日志
    	new Thread(new UserLoginThread(usersLoginSessionServiceImpl,session,users,request)).start();
        // 添加登录日志记录
        CompanyManageLoginHistoryVo comlogin = new CompanyManageLoginHistoryVo();
        comlogin.setOperator(users.getId() + "");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        comlogin.setOperateTime(sdf.format(new Date()));
        comlogin.setTargetCompanyId(users.getCompanyId() + "");
        comlogin.setTargetUserId(users.getId() + "");
        comlogin.setTargetSchoolId(users.getSchoolId() + "");
        usersLoginSessionServiceImpl.insertManageLoginHistory(comlogin);
    }

    @RequestMapping(value = "/logout", method = { RequestMethod.POST, RequestMethod.GET })
    public String logout() {

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);

        try {
            Users users = (Users) session.getAttribute("loginUser");
            if (users != null) {
                UsersLoginSession loginSession = usersLoginSessionServiceImpl.findHistoryByUserId("" + users.getId());
                if (loginSession != null) {
                    loginSession.setLogoutTime(new Date());
                    loginSession.setStatus(2);
                    usersLoginSessionServiceImpl.update(loginSession);
                }
            }
        } catch (Exception e) {
            log.error(e, e);
        } finally {
            subject.logout();
        }
        return "redirect:login";
    }

    /**
     * 
     * Class Name: BaseWebController.java
     * 
     * @Description: 获取登录用户对象
     * @author liuxindong
     * @date 2014-12-9 下午3:22:17
     * @version 1.0
     * @param request
     * @return
     */
    protected Users getCurrentUser(HttpServletRequest request) {
        Users user = WebUtils.getCurrentUser(request);
        if (user != null) {
            return user;
        }
        log.error("User is expired! 您长时间没有操作，系统已经自动将您退出，要继续操作请重新登录！");
        throw new RuntimeException("您长时间没有操作，系统已经自动将您退出，要继续操作请重新登录！");
    }

    /**
     * 
     * Class Name: BaseWebController.java
     * 
     * @Description: 获取登录用户的id
     * @author liuxindong
     * @date 2014-12-9 下午3:22:36
     * @version 1.0
     * @param request
     * @return
     */
    protected Integer getCurrentUserId(HttpServletRequest request) {
        Users user = getCurrentUser(request);
        return user.getId();
    }

    /**
     * 
     * Class Name: BaseWebController.java
     * 
     * @Description: 获取登录用户的用户名
     * @author liuxindong
     * @date 2014-12-9 下午3:22:46
     * @version 1.0
     * @param request
     * @return
     */
    protected String getCurrentUserName(HttpServletRequest request) {
        Users user = getCurrentUser(request);
        return user.getUsername();
    }

    /**
     * 
     * Class Name: BaseWebController.java
     * 
     * @Description: 获取登录用户所属分校id
     * @author liuxindong
     * @date 2014-12-9 下午3:23:03
     * @version 1.0
     * @param request
     * @return
     */
    protected Integer getCurrentUserSchoolId(HttpServletRequest request) {
        Users user = getCurrentUser(request);
        return user.getSchoolId();
    }

    /**
     * 
     * Class Name: BaseWebController.java
     * 
     * @Description: 销毁session中的登录用户
     * @author liuxindong
     * @date 2014-12-9 下午3:23:26
     * @version 1.0
     * @param request
     */
    protected void distoryCurrentUser(HttpServletRequest request) {
        WebUtils.distoryCurrentUser(request);
    }

    /**
     * 
     * Class Name: BaseWebController.java
     * 
     * @Description: 销毁session
     * @author liuxindong
     * @date 2014-12-9 下午3:25:35
     * @version 1.0
     * @param request
     */
    protected void distorySession(HttpServletRequest request) {
        WebUtils.distorySession(request);
    }

    @RequestMapping(value = "/system", method = { RequestMethod.POST, RequestMethod.GET })
    public String system(HttpServletRequest request, Model model) {
        return "redirect:/sysCyclePic/showPic";
    }

    /**
     * 获取图片验证码
     * 
     * @param request
     * @param captcha
     * @return
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public byte[] captcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            OutputStream out = response.getOutputStream();

            String word = CaptchaUtil.getCaptcha(out, 3);
            request.getSession().setAttribute("captcha", word);
            out.write(word.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 校验图片验证码
     * 
     * @param request
     * @param captcha
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/captcha/checkCode", method = RequestMethod.POST)
    public Boolean checkCaptchaCode(HttpServletRequest request, String captcha) {
        return CaptchaUtil.checkCaptcha(captcha, request.getSession().getAttribute("captcha").toString());
    }

    /**
     * 获取短信验证码
     * 
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/SMS/checkCode", method = RequestMethod.POST)
    public Boolean checkSmsCode(HttpServletRequest request, HttpServletResponse response) {
        String smsCode = request.getParameter("smsCode");
        if (SMSUtil.validateCode(request.getSession(), smsCode)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * 查询机构的 购买标记、试用标记、认证标记，计入session
     * 
     */
    // TODO 待完善
    public void setUserStatus() {
        Company company=WebUtils.getCurrentCompany();
        if(company==null){
        	company=companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
        	WebUtils.setSessionAttribute(WebUtils.CURRENT_COMAPNY,company);
        }
        List<CompanyNewStep> l=(List<CompanyNewStep>)WebUtils.getSessionAttribute("l_companyNewSteps");
        if(l==null){
        		l=companyNewStepServiceImpl.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
        		WebUtils.setSessionAttribute("l_companyNewSteps",l);
        }
        String status = String.valueOf(company.getStatus());

        WebUtils.setSessionAttribute("company_status", status);// 公司认证标记
        WebUtils.setSessionAttribute("company_buy_flag", company.getBuyFlag());// 购买标记
        if (l != null && !l.isEmpty() && l.get(0) != null) {
            WebUtils.setSessionAttribute("company_first_use", l.get(0).getNewStepFlag());
        } else {
            WebUtils.setSessionAttribute("company_first_use", null);
        }
    }

    public static void main(String[] args) {
        // String[]
        // username={"admin1","bj-schadmin1","bj-oper1","bj-sale1","bj-serv1",
        // "bj-tch1","bj-ta1","tj-schadmin1","tj-oper1","tj-sale1","tj-serv1","tj-tch1","tj-ta1",
        // "wh-schadmin1","wh-oper1","wh-sale1","wh-serv1","wh-tch1","wh-ta1","admin2","bj-schadmin2",
        // "bj-oper2","bj-sale2","bj-serv2","bj-tch2","bj-ta2"};
        // String[]
        // realname={"应试英语机构管理员","应试英语北京校管理员","应试英语北京校运营","应试英语北京校课程顾问","应试英语北京校客服",
        // "应试英语北京校老师","应试英语北京校助教","应试英语天津校管理员","应试英语天津校运营","应试英语天津校课程顾问","应试英语天津校客服",
        // "应试英语天津校老师","应试英语天津校助教","应试英语武汉校管理员","应试英语武汉校运营","应试英语武汉校课程顾问","应试英语武汉校客服",
        // "应试英语武汉校老师","应试英语武汉校助教","职业教育机构管理员","职业教育北京校管理员","职业教育北京校运营","职业教育北京校课程顾问",
        // "职业教育北京校客服","职业教育北京校老师","职业教育北京校助教"};
        // String password="admin";
        // for(int i=0;i<username.length;i++){
        // String pw=new
        // Md5Hash(password,ByteSource.Util.bytes(username[i]+"salt")).toHex();
        // System.out.println("insert into users (username,password,real_name)
        // values('"+username[i]+"','"+pw+"','"+realname[i]+"');");
        // }

        System.out.println(new Md5Hash(/* 密码 */"111111", ByteSource.Util.bytes(/* 用户名 */"lilei" + "salt")).toHex());
    }

    private Integer daysBetween(Date smdate, Date bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (Exception e) {
            return null;
        }
    }

    public void relogin() {
        Subject subject = SecurityUtils.getSubject();
        Users users = usersServiceImpl.queryUserByName(subject.getPrincipal().toString());
        subject.logout();
        UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(), users.getPassword());
        subject.login(token);
        subject.getSession().setAttribute("loginUser", users);
        // 设置登录后的状态
        setUserStatus();
        Company company = companyServiceImpl.findCompanyById(users.getCompanyId());
        if (company.getMemberLevel() < 20) {
            List<SysServiceDredgeVo> ssdVo = sysServiceDredgeImpl.findDredgeByCom(company.getId());
            if (ssdVo != null) {
                for (SysServiceDredgeVo s : ssdVo) {
                    subject.getSession().setAttribute(s.getName(), (s.getStatus() != null ? s.getStatus() : 0));
                }
            }
        }
        String status = String.valueOf(company.getStatus());

        try {
            // 将机构使用视频服务存入session
            CompanyMemberService service = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
            subject.getSession().setAttribute("useVideo", service.getVideoServiceProvider());

            // 将公司课程版本存入session(1表示简易版，0或空值表示复杂版)
            CompanyFunctionSet search = new CompanyFunctionSet();
            search.setCompanyId(WebUtils.getCurrentCompanyId());
            search.setFunctionCode("COMPANY_FUNCTION_COURSE");
            CompanyFunctionSet functionSet = companyFunctionSetImpl.findCompanyUseCourse(search);
            if (functionSet instanceof Object) {
                subject.getSession().setAttribute("courseFunction", functionSet.getStatus());
            } else {
                subject.getSession().setAttribute("courseFunction", "");
            }
        } catch (Exception e) {
            log.error("存入session值失败", e);
            e.printStackTrace();
        }

        WebUtils.setSessionAttribute("company_status", status);// 公司认证标记
        WebUtils.setSessionAttribute("company_buy_flag", company.getBuyFlag());// 购买标记
    }

    /**
     * 加载菜单 ,写的比较死，后期要改
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loadMenu")
    public String loadMenu(HttpServletRequest request) {
        Set<String> permissions = authUserRoleServiceImpl.findUserPermissions(WebUtils.getCurrentUserName(request));
        Boolean hasOp = false;
        Boolean hasOp_op1 = false;
        Boolean hasOp_op2 = false;
        Boolean hasOp_op3 = false;
        Boolean hasOp_op4 = false;
        Boolean hasOp_op5 = false;
        Boolean hasOp_op6 = false;
        Boolean hasOp_op7 = false;
        for (String permission : permissions) {
            if ("operating_head".equals(permission)) {
                hasOp = true;
            }
        }
        if (hasOp) {
            for (String p : permissions) {
                if ("course_qa".equals(p)) {
                    hasOp_op1 = true;
                }
                if ("community_qa".equals(p)) {
                    hasOp_op2 = true;
                }
                if ("teacher_person_status".equals(p)) {
                    hasOp_op3 = true;
                }
                if ("teacher_person_comment".equals(p)) {
                    hasOp_op4 = true;
                }
                if ("netschool_news".equals(p)) {
                    hasOp_op5 = true;
                }
                if ("financial".equals(p)) {
                    hasOp_op6 = true;
                }
                if ("query_statistics".equals(p)) {
                    hasOp_op7 = true;
                }
            }
        }

        if (hasOp_op1) {
            return "/Question/questionIndex";
        } else if (hasOp_op2) {
            return "/Question/comQuestionIndex";
        } else if (hasOp_op3) {
            return "/classModule/dynamics";
        } else if (hasOp_op4) {
            return "/classModule/comment";
        } else if (hasOp_op5) {
            return "/sysConfigNews/showNews";
        } else if (hasOp_op6) {
            return "/payOrder/toOrder";
        } else if (hasOp_op7) {
        	Subject subject = SecurityUtils.getSubject();
        	if(subject.hasRole("代理机构")) return "/query/page/payMaster";
            return "/query/page/student";
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/leaveWords")
    public void leaveWords(HttpServletRequest request, String name, String mobile, String qq, String contens, String thisUrl) {
        try {
            boolean isOk = validateLeaveMessageAuthority(mobile);
            if (isOk) {
                String ip = NetworkUtil.getIpAddress(request);
                OrganLeaveMessage leavewords = new OrganLeaveMessage();
                leavewords.setMobile(mobile);
                if (null != name && !"".equals(name)) {
                    try {
                        leavewords.setName(URLDecoder.decode(name, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    leavewords.setName(name);
                }
                if (null != contens && !"".equals(contens)) {
                    try {
                        leavewords.setContents(URLDecoder.decode(contens, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    leavewords.setContents(contens);
                }
                leavewords.setUtmUrl(thisUrl);
                leavewords.setIp(ip);
                leavewords.setQq(qq);
                leavewords.setRecordTime(new Date());
                organLeaveMessageServiceImpl.insert(leavewords);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validateLeaveMessageAuthority(String mobile) {
        OrganLeaveMessage olm = new OrganLeaveMessage();
        olm.setMobile(mobile);
        List<OrganLeaveMessage> olmlist = organLeaveMessageServiceImpl.queryOrganLeaveMessageListByMobile(olm);
        if (null == olmlist || olmlist.size() < 1) {
            return true;
        }
        return false;
    }

    /**
     * 推广用的登录注册页面。。。
     * 
     * @author licong
     * @date 2016年11月7日 下午5:42:44
     * @param
     * @return
     */
    @RequestMapping("/toCampaignLogin")
    public String toCampaignLogin() {
        return "../../campaignLogin";
    }

    @RequestMapping(value = "/campaignLogin", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView campaignLogin(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("../../campaignLogin");
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        if (subject.isAuthenticated()) {// 已经成功登录过,直接跳到首页
            // 已登录时，且没有选择过服务则踢到选择服务
            List<CompanyNewStep> l = companyNewStepServiceImpl.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
            if (l == null || l.isEmpty() || l.get(0).getNewStepFlag() == 0) {
                mv.setViewName("redirect:/serviceGroup/chooseCompanyService");// 跳到选择服务页
            } else {
                /* 直接跳首页 */
                mv.setViewName("redirect:index");// 跳首页
            }
            return mv;
        }
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        if (userName != null && password != null) {
            password = new Md5Hash(password, ByteSource.Util.bytes(userName + "salt")).toHex();

            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            try {
                subject.login(token);
                Users users = usersServiceImpl.queryUserByName(userName);
                session.setAttribute("loginUser", users);
                // 设置公司的相关信息 add by jaler 16.11.1
                Company company = companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId());
                session.setAttribute("company", company);
                // 设置登录后的状态
                setUserStatus();
                try {
                    // 将机构使用视频服务存入session
                    CompanyMemberService service = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
                    if (service != null) {
                        session.setAttribute("useVideo", service.getVideoServiceProvider());
                    }
                    // 将公司课程版本存入session(1表示简易版，0或空值表示复杂版)
                    CompanyFunctionSet search = new CompanyFunctionSet();
                    search.setCompanyId(WebUtils.getCurrentCompanyId());
                    search.setFunctionCode("COMPANY_FUNCTION_COURSE");
                    CompanyFunctionSet functionSet = companyFunctionSetImpl.findCompanyUseCourse(search);
                    if (functionSet instanceof Object) {
                        session.setAttribute("courseFunction", functionSet.getStatus());
                    } else {
                        session.setAttribute("courseFunction", "");
                    }

                } catch (Exception e) {
                    log.error("存入session值失败", e);
                    e.printStackTrace();
                }
                try {
                    // 记录登录日志
                    UsersLoginSession loginSession = usersLoginSessionServiceImpl.findHistoryByUserId("" + users.getId());
                    if (loginSession != null && 1 == loginSession.getStatus()) {
                        loginSession.setStatus(2);
                        usersLoginSessionServiceImpl.update(loginSession);
                        // 把上个用户session踢掉，暂时不启用
                        // JedisUtil.deleteByKey("shiro_redis_session"+loginSession.getSessionId());
                    }
                    UsersLoginSession userSession = new UsersLoginSession();
                    userSession.setUserId("" + users.getId());
                    userSession.setLoginTime(new Date());
                    userSession.setStatus(1);
                    userSession.setSessionId(session.getId().toString());
                    userSession.setMacAddress(request.getRemoteAddr());
                    userSession.setCompanyId(WebUtils.getCurrentCompanyId());
                    String ip = WebUtils.getIpAddr(request);
                    if (ip != null) {
                        userSession.setIp(ip);
                        Result<Address> add = AliIpAddressUtil.getAddress(ip);
                        // userSession.setAddress(AddressUtils.getAddresses("ip="+ip,
                        // "utf-8"));
                        // userSession.setIsp(AddressUtils.getIsp("ip="+ip,
                        // "utf-8"));
                        if (add.getCode() != null && add.getCode() == 0) {
                            if (add.getData() != null) {
                                Address address = add.getData();
                                userSession.setAddress(address.getRegion() + "-" + address.getCity() + "-" + address.getCountry());
                                userSession.setIsp(address.getIsp());
                            }
                        }
                    }
                    usersLoginSessionServiceImpl.insert(userSession);

                } catch (Exception e) {
                    log.error(e, e);
                    e.printStackTrace();
                }

                // 瑞哥说，暂时全部跳引导页 2015-6-4
                // 瑞哥说，跳到首页 2015-8-17
                // 未登录时，且没有选择过服务则踢到选择服务
                List<CompanyNewStep> l = companyNewStepServiceImpl.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
                if (l == null || l.isEmpty() || l.get(0).getNewStepFlag() == 0) {
                    mv.setViewName("redirect:/serviceGroup/chooseCompanyService");// 跳到选择服务页
                } else {
                    mv.setViewName("redirect:index");// 跳首页
                }
            } catch (UnknownAccountException e) {
                log.error("用户名或密码错误", e);
                mv.addObject("info", "用户名或密码错误");
            } catch (IncorrectCredentialsException e) {
                mv.addObject("info", "用户名或密码错误");
                log.error("用户名或密码错误", e);
            } catch (AuthenticationException e) {
                // 其他错误，比如锁定，如果想单独处理请单独catch 处理
                mv.addObject("info", "账号异常，请联系管理员");
                log.error("账号异常，请联系管理员", e);
            }

        } else {
            // mv.addObject("info", "用户名不能为空");
        }

        return mv;
    }

}