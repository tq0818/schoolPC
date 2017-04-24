package com.yuxin.wx.scheduled;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.company.ICompanyLiveCoursewareZsService;
import com.yuxin.wx.api.company.ICompanyLiveRecordZsService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.course.ILiveOpenCourseService;
import com.yuxin.wx.common.Coursewares;
import com.yuxin.wx.common.LiveRoomConstant;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.company.CompanyLiveCoursewareZs;
import com.yuxin.wx.model.company.CompanyLiveRecordZs;
import com.yuxin.wx.model.course.LiveOpenCourse;
import com.yuxin.wx.util.DateUtil;
import com.yuxin.wx.util.HttpPostRequest;
import com.yuxin.wx.vo.classes.CmlVo;
import com.yuxin.wx.vo.company.Record;

/**
 * 
 * @ClassName: ZSCoursewareTask
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 1
 * @date 2015-11-28 下午1:38:41
 * @version 1.0
 */
@Component
public class ZSCoursewareTask {
	
	private Log log = LogFactory.getLog("log");
	
	@Autowired
	private ILiveOpenCourseService liveOpenCourseServiceImpl;

	@Autowired
	private IClassModuleService classModuleServiceImpl;

	@Autowired
	private IClassModuleLessonService classModuleLessonServiceImpl;
	
	@Autowired
	private ICompanyLiveRecordZsService companyLiveRecordZsServiceImpl;
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	@Autowired
	private ICompanyLiveCoursewareZsService companyLiveCoursewareZsServiceImpl;
	
	/**
	 * 
	 * Class Name: ZSCoursewareTask.java
	 * @Description: 定时执行展视互动回看地址统计,如果没给时间，自动执行昨天的课堂统计
	 * @author 周文斌
	 * @date 2015-11-28 下午1:45:44
	 * @version 1.0
	 * @throws Exception 
	 */
	public void CoursewareStatistics(String time) throws Exception{
		Date date = null;
		
		if(StringUtils.isEmpty(time)){
			//昨天日期
			date = new SimpleDateFormat("yyyy-MM-dd").parse(DateUtil.getYesterDay("yyyy-MM-dd"));
		}else{
			//指定日期
			date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
		}
		log.info("couresware：date:" + new SimpleDateFormat("yyyy-MM-dd").format(date));
		//查询昨天或指定日期的类型 为 zs 并且 回看地址 为空的 直播id
		String url = LiveRoomConstant.DOMIN_NAME + LiveRoomConstant.COURSEWARE;
		String p = "&loginName=" + URLEncoder.encode(LiveRoomConstant.LOGIN_NAME) + "&password=" + URLEncoder.encode(LiveRoomConstant.PASSWORD);
		
		//查询空回放的zs直播
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("lessonDate", date);
		param.put("liveCompanyType", "zs");
		//直播
		List<CmlVo> roomIds = classModuleLessonServiceImpl.findZsLiveByTime(param);
		for (CmlVo r : roomIds) {
			String urls = url + "?roomId=" + r.getRoomId() + p;
			log.info("couresware：url:" + urls);
			String res = HttpPostRequest.get(urls);
			log.info("couresware：返回值:"+res);
			log.info("couresware：转成list");
			List<Coursewares> clist =  JSONObject.parseArray(JSONObject.parseObject(res).getString("coursewares"), Coursewares.class);
			if(clist != null && clist.size() > 0){
				log.info("couresware：lessonId："+r.getId()+",共几个:"+clist.size());
				log.info("couresware：一共几个地址:"+clist.size());
				for (Coursewares c : clist) {
					CompanyLiveCoursewareZs clzs = new CompanyLiveCoursewareZs(null,c.getId(),c.getNumber(),c.getUrl(),c.getSubject(),c.getRoomId(),c.getScreenshot(),c.getRecordId(),c.getDescription(),c.getToken(),r.getId(),r.getCompanyId(),r.getSchoolId(),date);
					companyLiveCoursewareZsServiceImpl.insert(clzs);
				}
				ClassModuleLesson cml = new ClassModuleLesson();
				cml.setId(r.getId());
				cml.setReplayUrlGh(clist.get(0).getUrl());
				classModuleLessonServiceImpl.update(cml);
			}
		}
		
		//公开课
		List<CmlVo> openRoomId = liveOpenCourseServiceImpl.findLiveRoomIds(param);
		for (CmlVo r : openRoomId) {
			String urls = url + "?roomId=" + r.getRoomId() + p;
			log.info("couresware：url:" + urls);
			String res = HttpPostRequest.get(urls);
			log.info("couresware：返回值:"+res);
			log.info("couresware：转成list");
			List<Coursewares> clist =  JSONObject.parseArray(JSONObject.parseObject(res).getString("coursewares"), Coursewares.class);
			if(clist != null && clist.size() > 0){
				log.info("couresware：lessonId："+r.getId()+",共几个:"+clist.size());
				log.info("couresware：一共几个地址:"+clist.size());
				for (Coursewares c : clist) {
					CompanyLiveCoursewareZs clzs = new CompanyLiveCoursewareZs(null,c.getId(),c.getNumber(),c.getUrl(),c.getSubject(),c.getRoomId(),c.getScreenshot(),c.getRecordId(),c.getDescription(),c.getToken(),r.getId(),r.getCompanyId(),r.getSchoolId(),date);
					companyLiveCoursewareZsServiceImpl.insert(clzs);
				}
				LiveOpenCourse cml = new LiveOpenCourse();
				cml.setId(r.getId());
				cml.setReplayUrlGh(clist.get(0).getUrl());
				liveOpenCourseServiceImpl.update(cml);
			}
		}
		//统计录制件
		String startTime = (time + " 00:00:00");
		String endTime = (time + " 23:59:59");
		if(StringUtils.isEmpty(time)){
			startTime = new SimpleDateFormat("yyyy-MM-dd").format(date) + " 00:00:00";
			endTime = new SimpleDateFormat("yyyy-MM-dd").format(date) + " 23:59:59";
		}else{
			startTime = (time + " 00:00:00");
			endTime = (time + " 23:59:59");
		}
		
		log.info("record：统计录制件，开始时间:"+startTime);
		log.info("record：统计录制件，结束时间:"+endTime);
		String rUrl = LiveRoomConstant.DOMIN_NAME + LiveRoomConstant.RECORD;
		for(int i = 1;;i++){
			String res = HttpPostRequest.get(rUrl+"?pageNo="+URLEncoder.encode(i+"")+"&startTime="+URLEncoder.encode(startTime)+"&endTime="+URLEncoder.encode(endTime)+p);
			log.info("record：录制件结果："+res);
			List<Record> reList = JSONObject.parseArray(((JSONObject.parseObject(res).getString("list"))), Record.class);
			if(reList != null && reList.size() > 0){
				for (Record r : reList) {
					log.info("record：查询公司id分校id");
					ClassModule cm = classModuleServiceImpl.findCompanyIdByClassNo(r.getRoomId());
					if(cm == null){
						continue;
					}
					CompanyLiveRecordZs rezs = new CompanyLiveRecordZs();
					rezs.setCompanyId(cm.getCompanyId());
					rezs.setSchoolId(cm.getSchoolId());
					rezs.setRecordId(r.getId());
					//查询是否存在
					List<CompanyLiveRecordZs> zlist = companyLiveRecordZsServiceImpl.findRecord(rezs);
					
					if(zlist == null || zlist.size() == 0){
						rezs.setRecordId(r.getId());
						rezs.setRecordStartTime(new Date(r.getRecordStartTime()));
						rezs.setRecordEndTime(new Date(r.getRecordEndTime()));
						rezs.setRecordCreatedTime(new Date(r.getCreatedTime()));
						rezs.setName(r.getName());
						rezs.setCreator(r.getCreator());
						rezs.setRecordSize(r.getSize());
						rezs.setRoomId(r.getRoomId());
						rezs.setCreateTime(new Date());
						companyLiveRecordZsServiceImpl.insert(rezs);
					}
				}
			}
			//第几页
			Integer pages = JSONObject.parseObject(JSONObject.parseObject(res).getString("page")).getInteger("totalPages");
			log.info("record：第几页了："+pages);
			if(pages.equals(i)){
				break;
			}
		}
	}
	
	public static void main(String[] args){
		String rUrl = LiveRoomConstant.DOMIN_NAME + LiveRoomConstant.RECORD;
		String p = "&loginName=" + URLEncoder.encode(LiveRoomConstant.LOGIN_NAME) + "&password=" + URLEncoder.encode(LiveRoomConstant.PASSWORD);
		try {
			String res = HttpPostRequest.get(rUrl+"?pageNo="+URLEncoder.encode("1")+"&startTime="+URLEncoder.encode("2016-10-28 00:00:00")+"&endTime="+URLEncoder.encode("2016-10-28 23:59:59")+p);
			System.out.println(res);
			List<Record> reList = JSONObject.parseArray(((JSONObject.parseObject(res).getString("list"))), Record.class);
			for (Record r : reList) {
				System.out.println(r.getName()+","+r.getRoomId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
