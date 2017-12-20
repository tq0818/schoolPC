package com.yuxin.wx.controller.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.company.ICompanyLiveConcurrentService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.course.IVideoService;
import com.yuxin.wx.api.system.ISysTaskLogService;
import com.yuxin.wx.api.system.ISysUseRecordNoticTaskService;
import com.yuxin.wx.common.CCVideoConstant;
import com.yuxin.wx.company.mapper.CompanyPayConfigMapper;
import com.yuxin.wx.company.mapper.CompanyServiceStaticDayMapper;
import com.yuxin.wx.company.mapper.CompanyServiceStaticMapper;
import com.yuxin.wx.course.mapper.VideoMapper;
import com.yuxin.wx.model.company.*;
import com.yuxin.wx.model.course.Video;
import com.yuxin.wx.model.system.SysTaskLog;
import com.yuxin.wx.model.system.SysUseRecordNoticTask;
import com.yuxin.wx.scheduled.*;
import com.yuxin.wx.system.mapper.SysFileConvertTaskMapper;
import com.yuxin.wx.util.APIServiceFunction;
import com.yuxin.wx.util.FileQNUtils;
import com.yuxin.wx.util.HttpPostRequest;
import com.yuxin.wx.util.ThreadConvertPDF;
import com.yuxin.wx.util.officeConvert.OfficeTransform;
import com.yuxin.wx.utils.*;
import com.yuxin.wx.utils.sendMSG4ServiceWarning.TimerTaskSendMSG4ServiceWarning;
import com.yuxin.wx.vo.course.VideoVo;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.net.ftp.FtpClient;

import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

@Controller
@RequestMapping("/task")
public class TimerTaskController {
	
	@Autowired
	private CCVideoSizeStatisticsTask ccssTask;
	
	@Autowired
	private SysFileConvertTaskMapper sysFileConvertTaskImpl;
	
	@Autowired
	private ResourceStatistics resourceStatistics;
	
	@Autowired
	private CouponsStatisticsTask couponsStatisticsTask;
	
	@Autowired
	private CompanyMemberStatisticsTask cmcStatisticsTask;

	@Autowired
	private IAuthRoleService authRoleServiceImpl;

	private Log log=LogFactory.getLog("log");

	@Autowired
	private FootBook footBookTask;

	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;

	@Autowired
	private FaceStatisticsTask faceStatisticsTask;

	@Autowired
	private ISysTaskLogService sysTaskLogServiceImpl;

	@Autowired
	private VideoStatisticsTask videoStatisticsTask;

	@Autowired
	private MessageStatisticsTask messageStatisticsTask;

	@Autowired
	private EmailStatisticsTask emailStatisticsTask;

	@Autowired
	private LiveStatisticsTask liveStatisticsTask;

	@Autowired
	private ISysUseRecordNoticTaskService sysUseRecordNoticTaskServiceImpl;
	@Autowired
	private ICompanyLiveConcurrentService companyLiveConcurrentServiceImpl;
	@Autowired
	private ZSCoursewareTask zscStatisticsTask;
	@Autowired
	private ICompanyMemberServiceService cmsImpl;
	@Autowired
	private ICompanyServiceStaticService cssImpl;

//	@Autowired
//	private PropertiesUtil properties;
	@Autowired
	private IVideoService videoServiceImpl;
	@Autowired
	private VideoMapper videoMapper;
	@Autowired
	private CompanyServiceStaticDayMapper companyServiceStaticDayMapper;

	@Autowired
	private CompanyPayConfigMapper payConfigMapper;
	@Autowired
	private CompanyServiceStaticMapper companyServiceStaticMapper;
	@Autowired
	private PropertiesUtil properties;
	private Properties props=null;
	{
		try{
			Resource resource = new ClassPathResource("config.properties");
			props= PropertiesLoaderUtils.loadProperties(resource);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@RequestMapping("/manage")
	public String manage(HttpServletRequest request){
		Integer userId = WebUtils.getCurrentUserId(request);
		String privilegeCode = "system_privilege_config";
		boolean b =authRoleServiceImpl.checkUserHasPrivilege(userId, privilegeCode,WebUtils.getCurrentCompanyId());
		if(!b){
			return "redirect:/";
		}
		return "console/timerTask";
	}
	
	@ResponseBody
	@RequestMapping("/convertPDF")
	public JSONObject convertPDF(HttpServletRequest request){
		JSONObject json = new JSONObject();
		Date date = new Date();
		//SysTaskLog stl = new SysTaskLog();
		try {
			/*stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("七牛存储统计");*/
			/*stl.setOperator(WebUtils.getCurrentUserId(request));
			stl.setOperateTime(new Date());*/
			log.info("添加队列任务-----执行时间：" + date);
			if(TimerTaskComponent.pdfcount == 0){
				log.info("创建线程");
				for (int i = 0; i < 10; i++) {
					new Thread(new ThreadConvertPDF(sysFileConvertTaskImpl,
							companyServiceStaticMapper,(i+1))).start();
				}
			}
			if(ConvertPDFTask.queue.size() == 0){
				ConvertPDFTask.convertPDF(properties.getServerName(),sysFileConvertTaskImpl);
			}
			json.put("msg", "添加任务到队列<br>执行时间："+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
			log.info("添加任务到队列-----处理：执行完成");
			/*stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");*/
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("msg", e.getMessage());
			log.info("添加任务到队列-----异常："+e.getMessage());
			/*stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());*/
			return json;
		}finally{
			TimerTaskComponent.pdfcount += 1;
			//sysTaskLogServiceImpl.insert(stl);
		}
	}
	
	@ResponseBody
	@RequestMapping("/openoffice")
	public JSONObject openoffice(HttpServletRequest request){
		JSONObject json = new JSONObject();
		Date date = new Date();
		//SysTaskLog stl = new SysTaskLog();
		try {
			log.info("重启openoffice-----执行时间：" + date);
			String res = OfficeTransform.resetManage();
			json.put("msg", "重启openoffice<br>执行时间："
					+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date))
					+"<br>"+res);
			log.info("重启openoffice-----处理：执行完成");
			/*stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");*/
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("msg", e.getMessage());
			log.info("重启openoffice-----异常："+e.getMessage());
			/*stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());*/
			return json;
		}finally{
			//sysTaskLogServiceImpl.insert(stl);
		}
	}
	
	@ResponseBody
	@RequestMapping("/qiniu")
	public JSONObject qiniu(HttpServletRequest request){
		JSONObject json = new JSONObject();
		Date date = new Date();
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("七牛存储统计");
			stl.setOperator(WebUtils.getCurrentUserId(request));
			stl.setOperateTime(new Date());
			log.info("七牛存储统计-----执行时间：" + date);
			resourceStatistics.resourceStatistics();
			json.put("msg", "七牛存储统计完成<br>执行时间："+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
			log.info("七牛存储统计-----处理：执行完成");
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("msg", e.getMessage());
			log.info("七牛存储统计-----异常："+e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
			return json;
		}finally{
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	@ResponseBody
	@RequestMapping("/live")
	public JSONObject live(HttpServletRequest request){
		JSONObject json = new JSONObject();
		Date date = new Date();
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("直播并发统计");
			stl.setOperator(WebUtils.getCurrentUserId(request));
			stl.setOperateTime(new Date());
			log.info("直播定时任务-----执行时间：" + date);
			liveStatisticsTask.liveStatistics();
			json.put("msg", "直播统计任务完成<br>执行时间："+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
			log.info("直播定时任务-----处理：执行完成");
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("msg", e.getMessage());
			log.info("直播定时任务-----异常："+e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
			return json;
		}finally{
			sysTaskLogServiceImpl.insert(stl);
		}
	}
	
	@ResponseBody
	@RequestMapping("/cmc")
	public JSONObject cmc(HttpServletRequest request){
		JSONObject json = new JSONObject();
		Date date = new Date();
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("会员到期提醒");
			stl.setOperator(WebUtils.getCurrentUserId(request));
			stl.setOperateTime(new Date());
			log.info("会员到期提醒-----执行时间：" + date);
			cmcStatisticsTask.UserVipNotice();
			log.info("会员到期提醒-----处理：完成");
			json.put("msg", "会员到期提醒<br>执行时间："+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
			return json;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put("msg", e.getMessage());
			log.info("会员到期提醒-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
			return json;
		}finally{
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	@ResponseBody
	@RequestMapping("/email")
	public JSONObject email(HttpServletRequest request){
		JSONObject json = new JSONObject();
		Date date = new Date();
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("邮件使用统计");
			stl.setOperator(WebUtils.getCurrentUserId(request));
			stl.setOperateTime(new Date());
			log.info("邮件定时任务-----执行时间：" + date);
			emailStatisticsTask.emailStatistics();
			log.info("邮件定时任务-----处理：完成");
			json.put("msg", "邮件统计任务完成<br>执行时间："+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("msg", e.getMessage());
			log.info("邮件定时任务-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
			return json;
		}finally{
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	@ResponseBody
	@RequestMapping("/coupons")
	public JSONObject coupons(HttpServletRequest request){
		JSONObject json = new JSONObject();
		Date date = new Date();
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("清理优惠券");
			stl.setOperator(WebUtils.getCurrentUserId(request));
			stl.setOperateTime(new Date());
			log.info("清理优惠券-----执行时间：" + date);
			couponsStatisticsTask.cleanCouponsTask();
			log.info("清理优惠券-----处理：完成");
			json.put("msg", "清理优惠券<br>执行时间："+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("msg", e.getMessage());
			log.info("清理优惠券-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
			return json;
		}finally{
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	@ResponseBody
	@RequestMapping("/message")
	public JSONObject message(HttpServletRequest request){
		JSONObject json = new JSONObject();
		Date date = new Date();
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("短信使用统计");
			stl.setOperator(WebUtils.getCurrentUserId(request));
			stl.setOperateTime(new Date());
			log.info("短信定时任务-----执行时间："+ date);
			messageStatisticsTask.messageStatistics();
			json.put("msg", "短信统计任务完成<br>执行时间："+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
			log.info("短信定时任务-----处理：完成");
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("msg", e.getMessage());
			log.info("短信定时任务-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
			return json;
		}finally{
			sysTaskLogServiceImpl.insert(stl);
		}
	}
/*
	@ResponseBody
	@RequestMapping("/give")
	public JSONObject give(Model model){
		JSONObject json = new JSONObject();
		Date date = new Date();
		try {
			log.info("每月定时任务-----执行时间：" + date);
			giveStatisticsTask.giveStatistics();
			json.put("msg", "赠送统计任务完成<br>执行时间："+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
			log.info("每月定时任务-----处理：完成");
			return json;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("msg", e.getMessage());
			log.info("每月定时任务-----异常：" + e.getMessage());
			return json;
		}
	}
	*/
	@ResponseBody
	@RequestMapping("/videoDetail")
	public JSONObject video(HttpServletRequest request){
		String url = CCVideoConstant.USERINFO;
		JSONObject json = new JSONObject();
		Date date = new Date();
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("视频使用统计");
			stl.setOperator(WebUtils.getCurrentUserId(request));
			stl.setOperateTime(new Date());
			log.info("视频定时任务-----执行时间：" + date);
			videoStatisticsTask.videoStatistics(url);
			json.put("msg", "视频统计任务完成<br>执行时间："+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
			log.info("视频定时任务-----处理：完成");
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("msg", e.getMessage());
			log.info("视频定时任务-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
			return json;
		}finally{
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	@ResponseBody
	@RequestMapping("/face")
	public JSONObject face(HttpServletRequest request){
		JSONObject json = new JSONObject();
		Date date = new Date();
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("人数分校统计");
			stl.setOperator(WebUtils.getCurrentUserId(request));
			stl.setOperateTime(new Date());
			log.info("人数分校定时任务-----执行时间：" + date);
			faceStatisticsTask.faceStatistics();
			log.info("人数分校定时任务-----处理：完成");
			json.put("msg", "人数分校统计任务完成<br>执行时间："+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("msg", e.getMessage());
			log.info("人数分校定时任务-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
			return json;
		}finally{
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	@ResponseBody
	@RequestMapping("/companyService")
	public JSONObject sendMsgToCompany(HttpServletRequest request){
		JSONObject json = new JSONObject();
		Date date = new Date();
		log.info("定时任务开始执行------------------------"+new Date());
		List<Integer> companyIds = companyServiceImpl.findCompanyId(null);
		String s = "";
		SysTaskLog stl = new SysTaskLog();
		stl.setTaskName("公司预警通知");
		stl.setExecuteDate(date);
		stl.setStartTime(date);
		stl.setOperator(0);
		stl.setOperateTime(date);
		boolean b = false;
		for (Integer i : companyIds) {
			try {
				CompanyMemberService cms = cmsImpl.findByCompanyId(i);
				CompanyServiceStatic css = cssImpl.findByCompanyId(i);
				if(cms != null && css != null){
					Company com = companyServiceImpl.findCompanyById(i);
					//用量预警
					userEarlyWarning(com, cms, css);
					//到期提醒
					expirationReminder(com, cms, css);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				log.info("公司"+i+",预警通知异常"+e.getMessage()+";");
				s += "公司"+i+",预警通知异常"+e.getMessage()+";";
				b = true;
			}
		}
		if(b){
			stl.setErrorLog(s);
			stl.setResult("预警部分异常");
		}else {
			stl.setErrorLog("无错误");
			stl.setResult("完成");
		}
		Date end = new Date();
		stl.setEndTime(end);
		sysTaskLogServiceImpl.insert(stl);
		log.info("定时任务执行完毕------------------------"+end);
		json.put("msg", "公司服务告警提醒任务完成<br>执行时间："
				+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end))
				+ "<br>" + s);
		return json;
	}

	@ResponseBody
	@RequestMapping("/zsurl")
	public JSONObject zsurl(HttpServletRequest request,String time){
		Date date = new Date();
		SysTaskLog stl = new SysTaskLog();
		JSONObject json = new JSONObject();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("展示回播使用统计");
			stl.setOperator(WebUtils.getCurrentUserId(request));
			stl.setOperateTime(new Date());
			log.info("展示回播定时任务-----执行时间：" + date);
			zscStatisticsTask.CoursewareStatistics(time);
			json.put("msg", "展示回播任务完成<br>执行时间："+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
			log.info("展示回播定时任务-----处理：完成");
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("msg", e.getMessage());
			log.info("展示回播定时任务-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
			return json;
		}finally{
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	@ResponseBody
	@RequestMapping("/footBook")
	public JSONObject footBook(HttpServletRequest request,Integer companyId){
		Date date = new Date();
		SysTaskLog stl = new SysTaskLog();
		JSONObject json = new JSONObject();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("修改视频统计");
			stl.setOperator(WebUtils.getCurrentUserId(request));
			stl.setOperateTime(new Date());
			log.info("修改视频统计-----执行时间：" + date);
			footBookTask.up(companyId);
			ccssTask.TaskStatistics();
			json.put("msg", "修改视频统计完成<br>执行时间："+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));
			log.info("修改视频统计-----处理：完成");
			stl.setEndTime(new Date());
			stl.setResult("修改视频统计成功");
			stl.setErrorLog("无错误");
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("msg", e.getMessage());
			log.info("修改视频统计-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("修改视频统计出错");
			stl.setErrorLog(e.getMessage());
			return json;
		}finally{
			sysTaskLogServiceImpl.insert(stl);
		}
	}
	@ResponseBody
	@RequestMapping("/cureLetv")
	public JSONObject cureLetv(HttpServletRequest request) {
		JSONObject returnObj = new JSONObject();
		List<CompanyPayConfig> payList = payConfigMapper.findAllLetv();
		String sd = request.getParameter("sd");
		String ed = request.getParameter("ed");
		JSONArray arr = new JSONArray();
		returnObj.put("size",payList.size());
		for (CompanyPayConfig companyPayConfig:payList){
			JSONObject obj = new JSONObject();
			String api = companyPayConfig.getLetvApiKey().trim();
			String uuid = companyPayConfig.getLetvUUID().trim();
			String userid = companyPayConfig.getLetvUserId().trim();
			LetvCloudV1 letvCloudV1 = new LetvCloudV1(api,uuid);
			obj.put("companyId",companyPayConfig.getCompanyId());
			obj.put("api",api);
			obj.put("uuid",uuid);
			obj.put("userid",userid);
			//获取空间
			Double storage = 0.0;
			String authorization= userid+"GET/data/vod/usespace"+api;
			authorization = userid+":"+ MD5.getMD5(authorization);
			try {
				//获取流量
				Double flow = 0.0;//获取到今天使用的流量
				authorization= userid+"GET/data/traffic"+api;
				authorization = userid+":"+ MD5.getMD5(authorization);

				String u= CCVideoConstant.LETV_VIDEO_URL+"/data/traffic" +"?domaintype=VOD_DOWNLOAD&productline=VOD&unit=G&granularity=day&startday="+sd+"&endday="+ed;
				String traffic = letvCloudV1.doGetApi(u,authorization);
				if(traffic!=null){
					JSONObject trafficObj = (JSONObject) JSONObject.parse(traffic);
					obj.put("traffic",trafficObj);
					if(trafficObj.getJSONObject("data")!=null){
						JSONObject data = trafficObj.getJSONObject("data");
						JSONObject trafficStr = data.getJSONObject("traffic");
						JSONArray timelist = trafficStr.getJSONArray("timelist");
						JSONArray valuelist = trafficStr.getJSONArray("valuelist");

						for(int i=0;i<timelist.size();i++){
							//更新公司今天使用情况
							CompanyServiceStaticDay companyServiceStaticDay = new CompanyServiceStaticDay();
							companyServiceStaticDay.setCompanyId(companyPayConfig.getCompanyId());
							companyServiceStaticDay.setUseDate(DateUtil.parseDate(timelist.getString(i),"yyyyMMdd"));
							flow = valuelist.getDouble(i);
							CompanyServiceStaticDay service = companyServiceStaticDayMapper.findByDateAndCompanyId(companyServiceStaticDay);
							if(service == null){
								service = new CompanyServiceStaticDay();
								service.setCompanyId(companyPayConfig.getCompanyId());
								service.setUseDate(DateUtil.parseDate(timelist.getString(i),"yyyyMMdd"));
								service.setVideoTotalFlow(flow);
								service.setVideoStorageNum(storage);
								companyServiceStaticDayMapper.insert(service);
							}else{
								service.setVideoTotalFlow(flow);
								service.setVideoStorageNum(storage);
								companyServiceStaticDayMapper.update(service);
							}
						}
					}
				}
				arr.add(obj);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				continue;
			}
		}

		returnObj.put("arr",arr);
//{"data":{"traffic":{"granularity":"day","timelist":["20160402","20160403","20160404","20160405","20160406"],"unit":"GB","valuelist":[0,0,0,0,0]}}}


return returnObj;
		}


	@ResponseBody
	@RequestMapping("/letv")
	public JSONObject getLetvInfo(HttpServletRequest request) throws Exception{
		JSONObject objj = new JSONObject();
		String api = request.getParameter("api");
		String uuid = request.getParameter("uuid");
		String userid = request.getParameter("userid");

		LetvCloudV1 letvCloudV1 = new LetvCloudV1(api,uuid);
		//获取空间
		Double storage = 0.0;
		String authorization= userid+"GET/data/vod/usespace"+api;
		log.info("authorization:"+authorization);
		//0d3fde75c05dc7cec9dc58b795dc99e9GET/data/vod/usespace0d3fde75c05dc7cec9dc58b795dc99e9
		//800765GET/data/vod/usespace0d3fde75c05dc7cec9dc58b795dc99e9
		authorization = userid+":"+ MD5.getMD5(authorization);
		String userspace = letvCloudV1.doGetApi(CCVideoConstant.LETV_VIDEO_URL+"/data/vod/usespace",authorization);
		log.info("userspace:-------------"+userspace);
		if(userspace!=null){
			JSONObject userspaceObj = (JSONObject) JSONObject.parse(userspace);
			if(userspaceObj.getJSONObject("data")!=null){
				//{"data":{"usespace":{"unit":"MB","value":506.12}}}
				JSONObject data = userspaceObj.getJSONObject("data");
				JSONObject usespace = data.getJSONObject("usespace");
				String unit = usespace.getString("unit");
				if(unit.equals("MB")){
					storage = usespace.getDouble("value");
					storage = storage/1024;
				}else{
					storage = usespace.getDouble("value");
				}
			}else{
				return userspaceObj;
			}
		}
		//获取流量
		Double flow = 0.0;//获取到今天使用的流量
		Double flowSum = 0.0;//数据库里的总使用流量
		authorization= userid+"GET/data/traffic"+api;
		authorization = userid+":"+ MD5.getMD5(authorization);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		String beginDate = sdf.format(DateUtil.addDate(new Date(),-4));
		String u= CCVideoConstant.LETV_VIDEO_URL+"/data/traffic" +"?domaintype=VOD_DOWNLOAD&productline=VOD&unit=G&granularity=day&startday="+beginDate+"&endday="+date;
		String traffic = letvCloudV1.doGetApi(u,authorization);
//{"data":{"traffic":{"granularity":"day","timelist":["20160402","20160403","20160404","20160405","20160406"],"unit":"GB","valuelist":[0,0,0,0,0]}}}
		log.info(beginDate+":"+date);
		log.info("traffic:-------------"+traffic);
		if(traffic!=null){
			JSONObject trafficObj = (JSONObject) JSONObject.parse(traffic);
			if(trafficObj.getJSONObject("data")!=null){
				JSONObject data = trafficObj.getJSONObject("data");
				JSONObject trafficStr = data.getJSONObject("traffic");
				//JSONArray timelist = trafficStr.getJSONArray("timelist");
				JSONArray valuelist = trafficStr.getJSONArray("valuelist");
				//timelist.get(0);
				flow = valuelist.getDouble(valuelist.size()-1);
			}
		}
		objj.put("userspace",userspace);
		objj.put("traffic",traffic);
		objj.put("storage",storage);
		objj.put("flow",flow);
		return objj;
	}
	private static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^\\d+$|-\\d+$");
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
	private String unCompress(String path, boolean delete){
		File file = new File(path);
		try {
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(file.getPath().replace(".gz",""));

			GZIPInputStream gis = new GZIPInputStream(fis);
			int BUFFERR = 1024;
			int count;
			byte data[] = new byte[BUFFERR];
			while ((count = gis.read(data, 0, BUFFERR)) != -1) {
				fos.write(data, 0, count);
			}

			gis.close();
			fis.close();
			fos.flush();
			fos.close();

			if (delete) {
				file.delete();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.info("解压cc日志文件出错，没有找到文件");
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			log.info("解压cc日志文件出错");
			return "";
		}

		return file.getPath().replace(".gz","");
	}
	private String downloadCCLog(String date,String savePath){
		FtpClient ftpClient = null;
		String fileLog = "access."+date+".log.gz";
		try {
			log.info("连接ftp开始：---------");
			//ftpClient = new sun.net.ftp.FtpClient.create("ftp.log.bokecc.com");
			ftpClient = FtpClient.create("ftp.log.bokecc.com");
			ftpClient.login("9D962C153919B4DA","5754ACC9B7C93A3BC9C385B9D06EF776".toCharArray(),"5754ACC9B7C93A3BC9C385B9D06EF776");
		} catch (Exception e) {
			log.info("连接ftp错误");
			e.printStackTrace();
			return "";
		}
		//InputStream is = null;
		FileInputStream  is = null;
		FileOutputStream os = null;
		try {
			//ftpClient.binary();
			ftpClient.setBinaryType();
			//获取远程机器上的文件filename，借助TelnetInputStream把该文件传送到本地。

			log.info("将要下载文件"+fileLog);
			is = (FileInputStream )ftpClient.getFileStream("/"+fileLog);
			//is = ftpClient.get("/"+fileLog);
			File file_in = new File(savePath+fileLog);
			os = new FileOutputStream(file_in);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			log.info("文件下载成功");
		} catch (Exception ex) {
			log.info("文件下载失败");
			ex.printStackTrace();
			return "";
		} finally{
			try {
				if(is != null){
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(os != null){
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return fileLog;
	}



	@ResponseBody
	@RequestMapping("/updateCCSize")
	public JSONObject updateCCSize(HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put("state","error");
		//查找所有的公共账号id

		Video video = new Video();
		video.setStorageType("VIDEO_STORAGE_TYPE_CC");
		List<CompanyPayConfig> companyPayConfigList = payConfigMapper.findPublicCC();
		try{
		for(CompanyPayConfig companyPayConfig:companyPayConfigList){
			video.setCompanyId(companyPayConfig.getCompanyId());
			video.setPageSize(99999);
			video.setStorageType("VIDEO_STORAGE_TYPE_CC");
			List<Video> videoList = videoMapper.page(video);
			if(videoList.size()==0){
				continue;
			}
			for(Video v:videoList){
				Map<String,String> queryMap = new HashMap<String,String>();
				String userUrl = "http://spark.bokecc.com/api/video/v2?";
				String format = "json";
				long time = System.currentTimeMillis();
				String salt = companyPayConfig.getCcApiKey();
				queryMap.put("videoid",v.getVideoCcId());
				queryMap.put("userid",companyPayConfig.getCcUserId());
				queryMap.put("format", "json");
				String  hashUser = APIServiceFunction.createHashedQueryString(queryMap,time,salt);
				userUrl += hashUser;
				String userInfo;
				try {
					userInfo = HttpPostRequest.get(userUrl);
					JSONObject obj = JSONObject.parseObject(userInfo);
					if(obj.getString("error")!=null){
						continue;
					}
					JSONObject videoObj  = obj.getJSONObject("video");
					JSONArray arr = videoObj.getJSONArray("definition");
					Integer fileSize = 0;
					for(int i=0;i<arr.size();i++){
						JSONObject definition = (JSONObject)arr.get(i);
						fileSize+=definition.getInteger("filesize");
					}
					Double doubleSize = Double.parseDouble(String.valueOf(fileSize))/1024/1024;
					doubleSize = new BigDecimal(doubleSize).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
					//修改video视频的大小 改为M
					Video modifyVideo = new Video();
					modifyVideo.setVodeoSize(doubleSize);
					modifyVideo.setId(v.getId());
					videoServiceImpl.update(modifyVideo);
				}catch(Exception e){
					e.printStackTrace();
					json.put("state",e.getStackTrace());
				}
			}
			//修改视频后 修改总量
			VideoVo search = new VideoVo();
			search.setCompanyId(companyPayConfig.getCompanyId());
			String totalSum = videoMapper.sumVideoSize(search);
			Double totalStorage = Double.parseDouble(totalSum);
			totalStorage = new BigDecimal(totalStorage/1024).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
			CompanyServiceStatic css = companyServiceStaticMapper.findByCompanyId(companyPayConfig.getCompanyId());
			if(css==null || css.getId()==null){
				continue;
			}
			CompanyServiceStatic updateStatic = new CompanyServiceStatic();
			totalStorage = new BigDecimal(totalStorage/1024).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
			updateStatic.setVideoStorage(totalStorage);
			updateStatic.setId(css.getId());
			companyServiceStaticMapper.update(updateStatic);

			//更改每天的使用
			List<VideoVo> list = videoMapper.getGroupSize(companyPayConfig.getCompanyId());
			for(VideoVo groupVideo:list){
				CompanyServiceStaticDay companyServiceStaticDay = new CompanyServiceStaticDay();
				companyServiceStaticDay.setCompanyId(companyPayConfig.getCompanyId());
				SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Double groupVideoSize = groupVideo.getVodeoSize()==null?0:groupVideo.getVodeoSize();
					companyServiceStaticDay.setUseDate(today.parse(today.format(groupVideo.getCreateTime())));
					CompanyServiceStaticDay service = companyServiceStaticDayMapper.findByDateAndCompanyId(companyServiceStaticDay);

					if(service == null){
						service = new CompanyServiceStaticDay();
						service.setCompanyId(companyPayConfig.getCompanyId());
						service.setUseDate(today.parse(today.format(groupVideo.getCreateTime())));
						service.setVideoStorageNum(groupVideoSize);
						companyServiceStaticDayMapper.insert(service);
					}else{
						service.setVideoStorageNum(groupVideoSize);
						companyServiceStaticDayMapper.update(service);
					}

				} catch (ParseException e) {
					e.printStackTrace();
				}

			}

		}
		}catch (Exception e){
			e.printStackTrace();
		}
		json.put("state","ok");
				return json;
	}
	
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 用量预警
	 * @author 周文斌
	 * @date 2016-12-1 下午5:57:05
	 * @modify	2016-12-1 下午5:57:05
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param css
	 * @throws Exception
	 */
	private void userEarlyWarning(Company com,CompanyMemberService cms,
			CompanyServiceStatic css)
		throws Exception{
		Date date = new Date();
		Integer currMonth = Integer.parseInt((date.getYear() + 1900)
				+ "" + ((date.getMonth() + 1) < 10 ?
						"0" + (date.getMonth() + 1) :
						(date.getMonth() + 1)));
		//并发预警
		liveWarning(com.getId(), css.getLiveConcurrent(), date , currMonth);
		//空间预警
		spaceWarning(com.getId(), cms, css, date, currMonth);
		//流量预警
		flowWarning(com.getId(), cms, css, date, currMonth);
		//短信预警
		msgWarning(com.getId(), cms, css, date, currMonth);
		//邮件预警
		emailWarning(com.getId(), cms, css, date, currMonth);
		if("ONLINE_COUNT".equals(com.getServiceVersion())){
			//在线预警
			onlineWarning(com.getId(), cms, css, date, currMonth);
		}else{
			//学员预警
			studentWarning(com.getId(), cms, css, date, currMonth);
		}
	}
	
	private void expirationReminder(Company com,CompanyMemberService cms,
			CompanyServiceStatic css) throws Exception {
		Date date = new Date();
		Integer currMonth = Integer.parseInt((date.getYear() + 1900)
				+ "" + ((date.getMonth() + 1) < 10 ?
						"0" + (date.getMonth() + 1) :
						(date.getMonth() + 1)));
		//并发到期
		liveReminder(com.getId(), date, currMonth);
		//空间到期
		spaceReminder(com.getId(), cms, currMonth, date);
		//服务到期
		faceReminder(com, currMonth, date);
	}
	
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 直播并发预警
	 * @author 周文斌
	 * @date 2016-12-1 下午5:56:45
	 * @modify	2016-12-1 下午5:56:45
	 * @version 
	 * @param companyId
	 * @param use
	 * @throws Exception
	 */
	private void liveWarning(Integer companyId,double use,Date date,Integer currMonth)
		throws Exception{
		log.info("并发预警,使用,"+use+",公司," + companyId);
		//通过公司查询本月并发
		Map<String, Object> comtime = new HashMap<String, Object>();
		comtime.clear();
		comtime.put("companyId", companyId);
		comtime.put("concurrentMonth", currMonth);
		CompanyLiveConcurrent livec =  companyLiveConcurrentServiceImpl
				.findLiveByComidAndDate(comtime);
		if(livec == null){
			return;
		}
		Integer max = livec.getConcurrentMax();
		SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
		entity.setCompanyId(companyId);
		entity.setNoticType(0);
		entity.setRecordDateMonth(currMonth);
		entity.setBussinessType(0);
		entity.setRecordDateTime(date);
		entity.setRecordStatus(0);
		if((max - use) <= 0){
			//100%预警查询
			entity.setRecordData("100/100预警"+max+"总并发");
			insertWarning(entity, (max+0.0), use, (max - use));
		}else if((max - use) < ((int)(max * 0.1)+1)){
			entity.setRecordData("90/100预警"+max+"总并发");
			insertWarning(entity, (max+0.0), use, (max - use));
		}else{
			return;
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 空间预警
	 * @author 周文斌
	 * @date 2016-12-1 下午6:44:26
	 * @modify	2016-12-1 下午6:44:26
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param css
	 * @param date
	 * @param currMonth
	 * @throws Exception
	 */
	private void spaceWarning(Integer companyId,CompanyMemberService cms,
			CompanyServiceStatic css, Date date,Integer currMonth) throws Exception{
		double total = ((cms.getVideoStorage() != null ? cms.getVideoStorage() : 0)
				+ (cms.getGiveVideoStorage() != null ? cms.getGiveVideoStorage() : 0) + 0.0);

		double cssvs = (css.getVideoStorage() != null ?
						css.getVideoStorage() : 0.0);
		double use = (cssvs);
		//剩余
		double surplus = (total - use);
		
		SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
		entity.setCompanyId(companyId);
		entity.setNoticType(0);
		entity.setRecordDateMonth(currMonth);
		entity.setBussinessType(1);
		entity.setRecordDateTime(date);
		entity.setRecordStatus(0);
		if(surplus <= 0){
			entity.setRecordData("100/100预警"+total+"总空间");
			insertWarning(entity, total, use, surplus);
		}else if(surplus <= (total * 0.1)){
			entity.setRecordData("90/100预警"+total+"总空间");
			insertWarning(entity, total, use, surplus);
		}else{
			return;
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 流量预警
	 * @author 周文斌
	 * @date 2016-12-1 下午7:05:27
	 * @modify	2016-12-1 下午7:05:27
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param css
	 * @param date
	 * @param currMonth
	 * @throws Exception
	 */
	private void flowWarning(Integer companyId,CompanyMemberService cms,
			CompanyServiceStatic css, Date date,Integer currMonth) throws Exception{
		double total = ((cms.getVideoFlow() != null ? cms.getVideoFlow() : 0)
				+ (cms.getGiveVideoFlow() != null ? cms.getGiveVideoFlow() : 0) + 0.0);
		long crf = Long.parseLong(css.getResourceFlow() != null ?
						css.getResourceFlow() : "0");
		double cssrf = FileQNUtils.convertFileSize(crf);
		double cssvf = (css.getVideoFlow() != null ?
						css.getVideoFlow() : 0.0);
		double use = (cssrf + cssvf);
		//剩余
		double surplus = (total - use);
		
		SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
		entity.setCompanyId(companyId);
		entity.setNoticType(0);
		entity.setRecordDateMonth(currMonth);
		entity.setBussinessType(2);
		entity.setRecordDateTime(date);
		entity.setRecordStatus(0);
		if(surplus <= 0){
			entity.setRecordData("100/100预警"+total+"总流量");
			insertWarning(entity, total, use, surplus);
		}else if(surplus <= (total * 0.1)){
			entity.setRecordData("90/100预警"+total+"总流量");
			insertWarning(entity, total, use, surplus);
		}else{
			return;
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 短信预警
	 * @author 周文斌
	 * @date 2016-12-1 下午7:11:27
	 * @modify	2016-12-1 下午7:11:27
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param css
	 * @param date
	 * @param currMonth
	 * @throws Exception
	 */
	private void msgWarning(Integer companyId,CompanyMemberService cms,
			CompanyServiceStatic css, Date date,Integer currMonth) throws Exception{
		double total = ((cms.getMessageTotal() != null ? cms.getMessageTotal() : 0)
				+ (cms.getGiveMessage() != null ? cms.getGiveMessage() : 0) + 0.0);
		double use = (css.getMessageSend() != null ?
				css.getMessageSend() : 0.0);
		//剩余
		double surplus = (total - use);
		
		SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
		entity.setCompanyId(companyId);
		entity.setNoticType(0);
		entity.setRecordDateMonth(currMonth);
		entity.setBussinessType(3);
		entity.setRecordDateTime(date);
		entity.setRecordStatus(0);
		if(surplus <= 0){
			entity.setRecordData("100/100预警"+total+"总短信");
			insertWarning(entity, total, use, surplus);
		}else if(surplus <= ((int)(total * 0.1)+1)){
			entity.setRecordData("90/100预警"+total+"总短信");
			insertWarning(entity, total, use, surplus);
		}else{
			return;
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 邮件预警
	 * @author 周文斌
	 * @date 2016-12-1 下午7:21:23
	 * @modify	2016-12-1 下午7:21:23
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param css
	 * @param date
	 * @param currMonth
	 * @throws Exception
	 */
	private void emailWarning(Integer companyId,CompanyMemberService cms,
			CompanyServiceStatic css, Date date,Integer currMonth) throws Exception{
		double total = ((cms.getEmailTotal() != null ? cms.getEmailTotal() : 0)
				+ (cms.getGiveEmail() != null ? cms.getGiveEmail() : 0) + 0.0);
		double use = (css.getEmailSend() != null ?
				css.getEmailSend() : 0.0);
		//剩余
		double surplus = (total - use);
		
		SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
		entity.setCompanyId(companyId);
		entity.setNoticType(0);
		entity.setRecordDateMonth(currMonth);
		entity.setBussinessType(4);
		entity.setRecordDateTime(date);
		entity.setRecordStatus(0);
		if(surplus <= 0){
			entity.setRecordData("100/100预警"+total+"总邮件");
			insertWarning(entity, total, use, surplus);
		}else if(surplus <= ((int)(total * 0.1)+1)){
			entity.setRecordData("90/100预警"+total+"总邮件");
			insertWarning(entity, total, use, surplus);
		}else{
			return;
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 学员预警
	 * @author 周文斌
	 * @date 2016-12-1 下午7:23:52
	 * @modify	2016-12-1 下午7:23:52
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param css
	 * @param date
	 * @param currMonth
	 * @throws Exception
	 */
	private void studentWarning(Integer companyId,CompanyMemberService cms,
			CompanyServiceStatic css, Date date,Integer currMonth) throws Exception {
		double total = ((cms.getFaceStudentAll() != null ? cms.getFaceStudentAll() : 0) + 0.0);
		double use = (css.getFaceStudent() != null ?
				css.getFaceStudent() : 0.0);
		//剩余
		double surplus = (total - use);
		
		SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
		entity.setCompanyId(companyId);
		entity.setNoticType(0);
		entity.setRecordDateMonth(currMonth);
		entity.setBussinessType(6);
		entity.setRecordDateTime(date);
		entity.setRecordStatus(0);
		if(surplus <= 0){
			entity.setRecordData("100/100预警"+total+"总学员");
			insertWarning(entity, total, use, surplus);
		}else if(surplus <= ((int)(total * 0.1)+1)){
			entity.setRecordData("90/100预警"+total+"总学员");
			insertWarning(entity, total, use, surplus);
		}else{
			return;
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 在线预警
	 * @author 周文斌
	 * @date 2016-12-1 下午7:25:50
	 * @modify	2016-12-1 下午7:25:50
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param css
	 * @param date
	 * @param currMonth
	 * @throws Exception
	 */
	private void onlineWarning(Integer companyId,CompanyMemberService cms,
			CompanyServiceStatic css, Date date,Integer currMonth) throws Exception {
		double total = ((cms.getFaceStudentAll() != null ? cms.getFaceStudentAll() : 0) + 0.0);
		double use = (css.getOnlineStudent() != null ?
				css.getOnlineStudent() : 0.0);
		//剩余
		double surplus = (total - use);
		
		SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
		entity.setCompanyId(companyId);
		entity.setNoticType(0);
		entity.setRecordDateMonth(currMonth);
		entity.setBussinessType(7);
		entity.setRecordDateTime(date);
		entity.setRecordStatus(0);
		if(surplus <= 0){
			entity.setRecordData("100/100预警"+total+"总在线");
			insertWarning(entity, total, use, surplus);
		}else if(surplus <= ((int)(total * 0.1)+1)){
			entity.setRecordData("90/100预警"+total+"总在线");
			insertWarning(entity, total, use, surplus);
		}else{
			return;
		}
	}
	
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 并发到期提醒
	 * @author 周文斌
	 * @date 2016-12-2 上午11:15:13
	 * @modify	2016-12-2 上午11:15:13
	 * @version 
	 * @param companyId
	 * @param date
	 * @param currMonth
	 * @throws Exception
	 */
	private void liveReminder(Integer companyId,Date date ,Integer currMonth)
		throws Exception{
		//下月是否有并发
		Date next = DateUtil.addMonthsToDate(date, 1);
		Integer nextMonth = Integer.parseInt((next.getYear() + 1900)
				+ "" + ((next.getMonth() + 1) < 10 ?
						"0" + (next.getMonth() + 1) :
						(next.getMonth() + 1)));
		Map<String, Object> comtime = new HashMap<String, Object>();
		comtime.clear();
		comtime.put("companyId", companyId);
		comtime.put("concurrentMonth", currMonth);
		CompanyLiveConcurrent livec =  companyLiveConcurrentServiceImpl
				.findLiveByComidAndDate(comtime);
		if(livec == null){
			return;
		}
		comtime.clear();
		comtime.put("companyId", companyId);
		comtime.put("concurrentMonth", nextMonth);
		CompanyLiveConcurrent nextc =  companyLiveConcurrentServiceImpl
				.findLiveByComidAndDate(comtime);

		//获得本月最后一天的年月日()
		String last = DateUtil.getLastDayOfCurMonth();
		Date lastd = new SimpleDateFormat("yyyy-MM-dd").parse(last);
		Date time = DateUtil.parseDate(DateUtil.format(date, "yyyy-MM-dd"), "yyyy-MM-dd");
		int days = DateUtil.diffDate(lastd, time);
		if(nextc == null){
			SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
			entity.setCompanyId(companyId);
			entity.setNoticType(1);
			entity.setRecordDateMonth(currMonth);
			entity.setBussinessType(0);
			entity.setRecordDateTime(date);
			entity.setRecordStatus(0);
			if(days == 7){
				entity.setRecordData("7天到期"+last+"并发到期");
				entity.setDeadDate(lastd);
				insertReminder(entity);
			}/*else if(days == 30){
				entity.setRecordData("30天到期"+last+"并发到期");
				entity.setDeadDate(lastd);
				insertReminder(entity);
			}*/else {
				return;
			}
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 空间到期提醒
	 * @author 周文斌
	 * @date 2016-12-2 上午11:18:17
	 * @modify	2016-12-2 上午11:18:17
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param currMonth
	 * @throws Exception
	 */
	private void spaceReminder(Integer companyId,CompanyMemberService cms
			,Integer currMonth,Date date)throws Exception {
		Date lastd = (cms.getVideoEndDate() != null ? cms.getVideoEndDate()
				: cms.getGiveVideoStorageDate());
		Date time = DateUtil.parseDate(DateUtil.format(date, "yyyy-MM-dd"), "yyyy-MM-dd");
		int days = DateUtil.diffDate(lastd, time);
		if(days == 7){
			SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
			entity.setCompanyId(companyId);
			entity.setNoticType(1);
			entity.setRecordDateMonth(currMonth);
			entity.setBussinessType(1);
			entity.setRecordDateTime(date);
			entity.setRecordStatus(0);
			entity.setRecordData("7天到期" + DateUtil.getDate(lastd, "yyyy-MM-dd") + "空间到期");
			entity.setDeadDate(lastd);
			insertReminder(entity);
		}
	}
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 服务到期
	 * @author 周文斌
	 * @date 2016-12-2 上午11:30:49
	 * @modify	2016-12-2 上午11:30:49
	 * @version 
	 * @param companyId
	 * @param cms
	 * @param currMonth
	 * @param date
	 * @throws Exception
	 */
	private void faceReminder(Company com,Integer currMonth,Date date) throws Exception{
		Date lastd = (com.getMemberEndDate() != null ? com.getMemberEndDate()
				: null);
		Date time = DateUtil.parseDate(DateUtil.format(date, "yyyy-MM-dd"), "yyyy-MM-dd");
		if(lastd != null){
			int days = DateUtil.diffDate(lastd, time);
			SysUseRecordNoticTask entity = new SysUseRecordNoticTask();
			entity.setCompanyId(com.getId());
			entity.setNoticType(1);
			entity.setRecordDateMonth(currMonth);
			entity.setBussinessType(5);
			entity.setRecordDateTime(date);
			entity.setRecordStatus(0);
			if(days == 7){
				entity.setRecordData("7天到期" + new SimpleDateFormat("yyyy-MM-dd").format(lastd) 
						+ "服务到期");
				entity.setDeadDate(lastd);
				insertReminder(entity);
			}else if(days == 30){
				entity.setRecordData("30天到期" + new SimpleDateFormat("yyyy-MM-dd").format(lastd) 
						+ "服务到期");
				entity.setDeadDate(lastd);
				insertReminder(entity);
			}
		}
	}
	
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 预警插入表数据
	 * @author 周文斌
	 * @date 2016-12-1 下午6:55:28
	 * @modify	2016-12-1 下午6:55:28
	 * @version 
	 * @param entity
	 * @param total
	 * @param use
	 * @param surplus
	 * @throws Exception
	 */
	private void insertWarning(SysUseRecordNoticTask entity,Double total,
			Double use,Double surplus)
		throws Exception{
		SysUseRecordNoticTask su = sysUseRecordNoticTaskServiceImpl
				.findByCompanyId(entity);
		if(su == null){
			entity.setTotalNum(total);
			entity.setCurrtNum(use);
			entity.setLastNum(surplus);
			sysUseRecordNoticTaskServiceImpl.insert(entity);
		}
	}
	
	/**
	 * 
	 * Class Name: TimerTaskCompanyServices.java
	 * @Description: 到期插入
	 * @author 周文斌
	 * @date 2016-12-2 上午11:14:18
	 * @modify	2016-12-2 上午11:14:18
	 * @version 
	 * @param entity
	 * @throws Exception
	 */
	private void insertReminder(SysUseRecordNoticTask entity) throws Exception {
		SysUseRecordNoticTask su = sysUseRecordNoticTaskServiceImpl
				.findByCompanyId(entity);
		if(su == null){
			sysUseRecordNoticTaskServiceImpl.insert(entity);
		}
	}
	
	/**
	 * 
	 * Class Name: TimerTaskController.java
	 * @Description: 手动执行服务预警信息发送
	 * @author dongshuai
	 * @date 2016年12月5日 下午4:32:14
	 * @modifier
	 * @modify-date 2016年12月5日 下午4:32:14
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/serviceWarning" ,method = RequestMethod.POST)
	public JSONObject serviceWarningExecutor(){
		JSONObject json = new JSONObject();
		json = TimerTaskSendMSG4ServiceWarning.serviceWarning(1);
		return json;
	}

	@RequestMapping("/apitest")
	public void worku(){
		
	}
}
