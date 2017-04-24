package com.yuxin.wx.controller.classes;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.classes.IClassPackageService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassPackage;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.student.StudentClassLeanDetailVo;
import com.yuxin.wx.vo.student.StudentClassLeanRecordVo;
import com.yuxin.wx.vo.student.StudentLessTimeVo;
import com.yuxin.wx.vo.student.StudentTiKuExcipseVo;
import com.yuxin.wx.vo.student.StudentTiKuOrSubjectVo;
/**
 * 课程包--学习记录
 * @author licong
 *
 */
@Controller
@RequestMapping("/stuLeanRecord")
public class ClassPackageStuLeanRecordController {
	@Autowired
	private IClassPackageService classPackageServiceImpl;
	@Autowired
	private IStudentService	studentServiceImpl;
	
	
	private Log log = LogFactory.getLog("log");
	/**
	 * 打开课程包学习详情页面
	 */
	@RequestMapping(value="/stuClassPackageLeanRecord/{classPackageId}/{stuId}")
	public String stuClassPackageLeanRecord(@PathVariable Integer classPackageId,@PathVariable Integer stuId,Model model){
		ClassPackage classPackage=classPackageServiceImpl.findClassPackageById(classPackageId);
		model.addAttribute("classPackage", classPackage);
		model.addAttribute("stuId", stuId);
 		model.addAttribute("classPackageId", classPackageId);
 		StudentClassLeanRecordVo cpRecord = studentServiceImpl.findClassPackInfo(classPackageId, stuId);
 		cpRecord.setPrecent(stuCpLeanPercent(classPackageId,stuId));
 		model.addAttribute("cpRecord", cpRecord);
		return "coursePackage/packageLeanRecard";
	}
	
	
	/**
	 * 查看视频 && 直播课的学习记录统计
	 */
	@ResponseBody
	@RequestMapping(value="/queryStudentCpLeanRecord", method = RequestMethod.POST)
	public Object studentLessionHistory(StudentClassLeanDetailVo search){
//		String companyType = "one";//是否开启多班号
//		boolean flag = false;	//是否购买整个课程包
//		//判断学校是否开启多版型单
//		Map<String, Object> param = new HashMap<String,Object>();
//		param.put("companyId", WebUtils.getCurrentCompanyId());
//		param.put("functionCode", "COMPANY_FUNCTION_COURSE");
//		CompanyFunctionSet set = companyFunctionSetServiceImpl.findSetByCom(param);
//		if(null != set && "1".equals(set.getStatus()))
//			companyType = "more";
//		
//		PageFinder<StudentClassLeanDetailVo>  obj =  null;
//		//判断是购买整个课程包还是购买课程包下的阶段
//		
//		List<Integer> classPackageStageIds = studentServiceImpl.findHasBuyStage(search.getStuId(), search.getClassPackageId());
//		for(int i=0;i<classPackageStageIds.size();i++){
//			if(null == classPackageStageIds.get(i)){
//				flag = true;
//				break;
//			}
//		}
//		search.setCompanyId(WebUtils.getCurrentCompanyId());
//		search.setSchoolId(WebUtils.getCurrentSchoolId());
//		if("one".equals(companyType) && flag)
//			obj = studentServiceImpl.queryStudentCpLeanRecord(search);
//		if("one".equals(companyType) && !flag)
//			obj = studentServiceImpl.queryStudentCpSomeStageLeanRecord(search);
//		if("more".equals(companyType) && flag)
//			obj = studentServiceImpl.queryStudentMoreCpLeanRecord(search);
//		if("more".equals(companyType) && !flag)
//			obj = studentServiceImpl.queryStudentMoreCpSomeStageLeanRecord(search);
		
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		PageFinder<StudentClassLeanDetailVo>  obj =  studentServiceImpl.queryStudentCtOrCpLeanRecord(search);
		
		return obj;
	}
	
	/**
	 * 查看视频课程||直播学习详情
	 */
	@ResponseBody
	@RequestMapping(value="/queryStudentLecOrLesStudyDetail", method = RequestMethod.POST)
	public Object queryStudentLecOrLesStudyDetail(StudentClassLeanDetailVo search){
		PageFinder<StudentClassLeanDetailVo>  obj = null;
		if("lecture".equals(search.getType())){
			 obj = studentServiceImpl.queryStudentLecLeanDetail(search);
		}else{
			obj = studentServiceImpl.queryStudentLessLeanDetail(search);
		}
		return obj;
	}
	
	/**
	 * 查询公司所有的题库
	 */
	@ResponseBody
	@RequestMapping(value="/studentTiKuList", method = RequestMethod.POST)
	public Object studentTiKuList(StudentTiKuOrSubjectVo search){
		if(search.getType()==0){
	 		Integer companyId=WebUtils.getCurrentCompanyId();
	 		search.setId(companyId);
			List<StudentTiKuOrSubjectVo> tiLuList = studentServiceImpl.queryCommpanyTiku(search);
			return tiLuList;
		}else{
			List<StudentTiKuOrSubjectVo> subjectList = studentServiceImpl.queryCommpanySubject(search);
			return subjectList;
		}
		
	}
	
	/**
	 * 查询学生做题记录
	 */
	@ResponseBody
	@RequestMapping(value="/studentTiKuRecord", method = RequestMethod.POST)
	public Object studentTiKuRecord(StudentTiKuExcipseVo StudentTiKuExcipseVo){
		StudentTiKuExcipseVo.setCompanyId(WebUtils.getCurrentCompanyId());
		PageFinder<StudentTiKuExcipseVo> pagefinder = studentServiceImpl.queryStudentTikuExperise(StudentTiKuExcipseVo);
		return pagefinder;
	}
	
	public String stuCpLeanPercent(Integer cpId,Integer stuId){
		DecimalFormat df = new DecimalFormat("######0.00");
		return df.format(leanPercent(cpId,stuId));
	}
	public double leanPercent(Integer classPackageId,Integer stuId){
		/**
		 * 思路：获取到课程下所有的节，包含视频、直播和面授。
		 * 		统计出用户在视频课次中所学习过的课次，其中user_history表中study_status值为3的记录
		 * 		获取所有的直播和面授的课次的结束时间，遍历集合跟当前时间做比较，其中小于当前时间的为用户已经学习完成的视频和直播课程
		 */
		StudentClassLeanDetailVo data = new StudentClassLeanDetailVo();
 		data.setStuId(stuId);
 		data.setClassPackageId(classPackageId);
 		data.setCompanyId(WebUtils.getCurrentCompanyId());
 		data.setSchoolId(WebUtils.getCurrentSchoolId());
 		Integer lecCount = studentServiceImpl.queryCpLecCount(data);									//所有的视频课次
		Integer lecFinishCount = studentServiceImpl.queryCpLecFinishCount(data);						//视频课次完成数量
		List<StudentLessTimeVo> lessionEndTimeList = studentServiceImpl.queryCpMoreNoLessionCount(data);//所有的直播课次数量（包含课程开始时间）
		
		int lessonFinishCount = 0;//直播课程完成数量
		if(lessionEndTimeList != null && lessionEndTimeList.size()>0){
			Calendar calOld = Calendar.getInstance();
			Calendar calNow = Calendar.getInstance();
			calNow.setTime(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			try {
				for (StudentLessTimeVo studentLessTimeVo : lessionEndTimeList) {
					String dateStr = sdf.format(studentLessTimeVo.getLessonDate());
					String year = dateStr.substring(0, 4);
					String month = dateStr.substring(4, 6);
					String day = dateStr.substring(6, 8);
					String endTime = studentLessTimeVo.getHour();
					System.out.println(year+"-"+month+"-"+day+" "+endTime);
					String[] hourAndMin = endTime.split(":");
					calOld.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day), Integer.parseInt(hourAndMin[0]), Integer.parseInt(hourAndMin[1]), 0);
					int result = calOld.compareTo(calNow);
					if(result<0){	//当前时间大于直播的结束时间并且直播记录表中存在用户的记录，则为学习完成该直播或者面授
						lessonFinishCount++;
					}
				}
			} catch (NumberFormatException e) {
				log.info("直播课次的结束时间转换异常：数据库数据不完整");
				e.printStackTrace();
			}
			sdf=null;
		}
		double finish = lecFinishCount+lessonFinishCount;
		double sumCount = lecCount+lessionEndTimeList.size();
		if(sumCount==0)
			return 0.00;
		data = null;lessionEndTimeList = null;
		return finish/sumCount*100;
	}
}
