package com.yuxin.wx.scheduled;

import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.tiku.*;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.api.weixin.IWeiXinService;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.tiku.*;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.vo.commodity.CommodityVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 
 * @ClassName: EmailStatisticsTask
 * @Description: 邮件定时任务
 * @author 周文斌
 * @date 2015-5-21 下午12:44:43
 * @version 1.0
 */
@Component
public class SendWeixinMsgTask {
	private Log log = LogFactory.getLog("log");
	@Autowired
	private IWeiXinService weiXinServiceImpl;
	@Autowired
	private ICommodityService commodityServiceImpl;
	@Autowired
	private IClassModuleService classModuleServiceImpl;
	@Autowired
	private IUsersFrontService usersFrontService;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private IClassModuleNoService classModuleNoServiceImpl;
	@Autowired
	private IClassModuleLessonService classModuleLessonServiceImpl;
	@Autowired
	private IStudentService studentServiceImpl;
	/**
	 * 发送微信通知方法
	 */
	public void sendWeixinMsg(Properties props) {
		CommodityVo search = new CommodityVo();
		List<CommodityVo> commodityVoList = commodityServiceImpl.queryClassScheduleList(search);
		for(CommodityVo commodityVo:commodityVoList){
			//学生集合
			CompanyStudentMessage companyStudentMessage = new CompanyStudentMessage();
			companyStudentMessage.setIsWxOpen(1);
			companyStudentMessage.setCompanyId(commodityVo.getCompanyId());
			companyStudentMessage.setSchoolId(commodityVo.getSchoolId());
			companyStudentMessage.setClassTypeId(commodityVo.getClassTypeId());
			companyStudentMessage.setItemOneCode(commodityVo.getItemOneCode());
			companyStudentMessage.setItemSecondCode(commodityVo.getItemSecondCode());
			companyStudentMessage.setItemThirdCode(commodityVo.getItemThirdCode());
			List<Student> stuList = studentServiceImpl.findByPayMaster(companyStudentMessage);

			for(Student stu : stuList){//第二天是否有未上的课程
				UsersFront uf = usersFrontService.findUserFrontByStudentId(stu.getId());
				sendWXTemplate(commodityVo,commodityVo.getClassTypeId(),stu,uf, commodityVo.getCompanyId(), props);
			}
		}
	}

	private void sendWXTemplate(CommodityVo comm,Integer classTypeId, Student stu, UsersFront uf,
								Integer companyId, Properties props){
		try{
			Company company = companyServiceImpl.findCompanyById(companyId);
			String openId = uf.getWxOpenId();
			if(StringUtils.isBlank(openId)){
				log.info("sendWXTemplate openId is null by user :"+uf.getId());
				return;
			}
			String token = weiXinServiceImpl.wxGetToken(props.getProperty("wxBaseUrl"), props.getProperty("wxAppId"), props.getProperty("wxSecret"));
			String template = props.getProperty("attendClassTemplateMsg");//报名结果通知
			com.alibaba.fastjson.JSONObject paramsJson = new com.alibaba.fastjson.JSONObject();
			paramsJson.put("userName", uf.getUsername());
			paramsJson.put("courseName", comm.getName());
			if(comm.getLiveFlag() != null && comm.getLiveFlag().intValue() == 1){
				paramsJson.put("url", "http://"+company.getDomain()+"/wx?urlNew="+company.getDomain()+"/html/starcube/index/details-live.html?invite="+comm.getId());
			}
			List<ClassModuleLesson> cmlList = new ArrayList<ClassModuleLesson>();
			List<ClassModule> modulesVoList=classModuleServiceImpl.findModulesByClassTypeId(classTypeId);
			for(ClassModule module:modulesVoList){
				if(StringUtils.equals(module.getTeachMethod(),"TEACH_METHOD_LIVE")){
					//查询模块对应的班号
					List<ClassModuleNo> list = classModuleNoServiceImpl.findByCmId(module.getId(),classTypeId);
					if(list.size() > 0){
						ClassModuleNo mNo=list.get(0);
						//查询班号对应的课次
						List<ClassModuleLesson> lessonList=classModuleLessonServiceImpl.findClassModuleLessonByModuleNoId(mNo.getId());
						if(lessonList.size() > 0){
							cmlList.addAll(lessonList);
						}

					}

				}
			}
			if(cmlList.size() > 0){
				Collections.sort(cmlList,new Comparator<ClassModuleLesson>(){
					public int compare(ClassModuleLesson l1,ClassModuleLesson l2) {
						Date d1 = null;
						Date d2 = null;
						try{
							d1 = getLessonDateTime(l1);
							d2 = getLessonDateTime(l2);
						}catch(Exception e){
							log.error("compare is error", e);
						}
						return d1.compareTo(d2);
					}
				});
				ClassModuleLesson cml = cmlList.get(0);
				Date lessonDateTime = cml.getLessonDateTime();
				SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
				if(lessonDateTime == null){
					String lessonDateStr = df.format(cml.getLessonDate());
					lessonDateTime = dateFormat.parse(lessonDateStr+ " "+cml.getLessonTimeStart()+":00");
				}
				String time = dateFormat.format(lessonDateTime);
				paramsJson.put("date", time);//获取课次上课时间
			}
			weiXinServiceImpl.wxSendTemplate(token, openId, template, paramsJson, props.getProperty("wxBaseUrl"));
		}catch(Exception e){
			log.error("sendWXTemplate is error :", e);
		}

	}

	private Date getLessonDateTime(ClassModuleLesson lesson) throws Exception{
		Date date = null;
		try{
			SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
			String lessonDateStr = df.format(lesson.getLessonDate());
			date = dateFormat.parse(lessonDateStr+ " "+lesson.getLessonTimeStart()+":00");
			lesson.setLessonDateTime(date);
			return date;
		}catch(Exception e){
			throw new Exception(e);
		}

	}
}
