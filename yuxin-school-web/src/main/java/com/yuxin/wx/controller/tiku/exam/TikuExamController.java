package com.yuxin.wx.controller.tiku.exam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.protocol.HTTP;
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

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.tiku.ITikuCategoryService;
import com.yuxin.wx.api.tiku.ITikuPaperService;
import com.yuxin.wx.api.tiku.ITikuPaperTopicService;
import com.yuxin.wx.api.tiku.ITikuPaperTopicTypeService;
import com.yuxin.wx.api.tiku.ITikuSubjectService;
import com.yuxin.wx.api.tiku.exam.ITikuExamPaperRelationService;
import com.yuxin.wx.api.tiku.exam.ITikuExamService;
import com.yuxin.wx.api.tiku.exam.ITikuExamUserRelationService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.tiku.TikuCategory;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.TikuSubject;
import com.yuxin.wx.model.tiku.exam.TikuExam;
import com.yuxin.wx.model.tiku.exam.TikuExamPaperRelation;
import com.yuxin.wx.model.tiku.exam.TikuExamUserRelation;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.tiku.TikuExamPaperVo;
import com.yuxin.wx.vo.tiku.exam.TikuExamPaperRelationVo;
import com.yuxin.wx.vo.tiku.exam.TikuExamUserRelationVo;

/**
 * Controller of TikuExam
 * @author wang.zx
 * @date 2016-2-17
 */
@Controller
@RequestMapping("/tikuExam")
public class TikuExamController {
	
	private Log log = LogFactory.getLog("log");
	
	@Autowired
	private ITikuCategoryService tikuCategoryServiceImpl;
	
	@Autowired
	private ITikuSubjectService tikuSubjectServiceImpl;
	
	@Autowired
	private ITikuPaperTopicTypeService tikuPaperTopicTypeServiceImpl;
	
	@Autowired
	private ITikuPaperTopicService tikuPaperTopicServiceImpl;
	
	@Autowired
	private ITikuExamService tikuExamServiceImpl;
	
	@Autowired
	private ITikuExamUserRelationService tikuExamUserRelationServiceImpl;
	
	@Autowired
	private ITikuExamPaperRelationService tikuExamPaperRelationServiceImpl;
	
	@Autowired
	private ITikuPaperService tikuPaperServiceImpl;

	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;

	@Autowired
	private IClassTypeService classTypeServiceImpl;
	
	@Autowired
	private PropertiesUtil properties;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, TikuExam search){
		if (search == null) {
			search = new TikuExam();
			// search.setPageSize(20);
		}
		model.addAttribute("list", tikuExamServiceImpl.findTikuExamByPage(search));
		return "tikuExam/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(TikuExam TikuExam) {
		tikuExamServiceImpl.insert(TikuExam);
		return "redirect:/tikuExam";
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TikuExam TikuExam) {
		tikuExamServiceImpl.update(TikuExam);
		return "success";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		tikuExamServiceImpl.deleteTikuExamById(id);
		return "redirect:/tikuExam";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public TikuExam getJson(Model model, @PathVariable Integer id){
		return tikuExamServiceImpl.findTikuExamById(id);
	}
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 链接到考试列表
	 * @author yuchanglong
	 * @date 2016年2月17日 下午4:55:40
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="/toTikuExamIndex")
	public String toTikuExamIndex(){
		
		return "tiku/exam/examIndex";
	}
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 异步加载列表
	 * @author yuchanglong
	 * @date 2016年2月17日 下午4:57:04
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="/loadAjaxInfo")
	public String loadAjaxInfo(TikuExam exam,Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentSchoolId();
		exam.setPageSize(6);
		exam.setCompanyId(companyId);
		exam.setSchoolId(schoolId);
		PageFinder<TikuExam> pageFinder = tikuExamServiceImpl.findTikuExamsByPage(exam);
		model.addAttribute("pageFinder",pageFinder);
		return "tiku/exam/examAjax";
	}
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 判断考试是否该发布
	 * @author yuchanglong
	 * @date 2016年2月24日 下午7:24:55
	 * @version 1.0
	 * @param examId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/isFb")
	public Boolean isFb(Integer examId){
		TikuExam exam = tikuExamServiceImpl.findTikuExamById(examId);
		Integer examPassCount = exam.getExamCount();
		Integer examPaperCount = tikuExamPaperRelationServiceImpl.examPaperCount(examId);
		if(examPaperCount>=examPassCount&&!examPaperCount.equals(0)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 删除考试信息时判断是否有人参加
	 * @author yuchanglong
	 * @date 2016年2月17日 下午6:03:48
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/isHavePeople")
	public Boolean isHavePeople(TikuExamUserRelationVo search){
		Integer people = tikuExamUserRelationServiceImpl.findExamUserCount(search);
		if(people>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 查询该考试下是否存在试卷
	 * @author yuchanglong
	 * @date 2016年2月17日 下午6:10:06
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/isHavePaper")
	public Boolean isHavePaper(TikuExamPaperRelation search){
		Integer paperCount = tikuExamPaperRelationServiceImpl.findExamHavePaperCount(search);
		if(paperCount>0){
			return true;
		}
		return false;
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
	
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 添加考试
	 * @author 周文斌
	 * @date 2016-2-17 下午4:11:50
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/addExam")
	public String addExam(Model model,HttpServletRequest request){
		log.info("进入添加考试");
		model.addAttribute("types", 1);
		model.addAttribute("imgpath", "http://" + properties.getProjectImageUrl());
		return "tiku/exam/addExamOne";
	}
	
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 编辑考试
	 * @author 周文斌
	 * @date 2016-2-17 下午4:24:32
	 * @version 1.0
	 * @param model
	 * @param request
	 * @param examId
	 * @return
	 */
	@RequestMapping("/updateExam/{examId}")
	public String updateExam(Model model,HttpServletRequest request,@PathVariable Integer examId){
		//查询考试信息
		try {
			log.info("根据考试id:" + examId + " ,查询考试信息");
			TikuExam exam = tikuExamServiceImpl.findTikuExamById(examId);
			
			List<TikuExamPaperRelationVo> eprv = tikuExamPaperRelationServiceImpl.findPaperByExamId(examId);
			model.addAttribute("count", (eprv != null ? eprv.size() : 0));
			model.addAttribute("types", exam.getStatus());
			model.addAttribute("exam", exam);
			model.addAttribute("imgpath", "http://" + properties.getProjectImageUrl());
		} catch (Exception e) {
			log.error("查询考试信息出错：" + e.getMessage(),e);
			e.printStackTrace();
		}
		return "tiku/exam/addExamOne";
	}
	
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 保存考试信息，进入下一步
	 * @author 周文斌
	 * @date 2016-2-17 下午5:03:09
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveAndNext")
	public JSONObject saveAndNext(HttpServletRequest request,TikuExam exam,String startTime,String endsTime){
		JSONObject json = new JSONObject();
		try {
			if(startTime != null && !startTime.equals("")){
				exam.setBeginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime));
			}
			if(endsTime != null && !endsTime.equals("")){
				exam.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endsTime));
			}
			exam.setCompanyId(WebUtils.getCurrentCompanyId());
			exam.setSchoolId(WebUtils.getCurrentSchoolId());
			if(exam.getId() == null){
				exam.setCreateTime(new Date());
				exam.setCreator(WebUtils.getCurrentUserId(request));
				tikuExamServiceImpl.insert(exam);
				json.put("types", "add");
			}else{
				exam.setUpdateTime(new Date());
				exam.setUpdator(WebUtils.getCurrentUserId(request));
				tikuExamServiceImpl.updateById(exam);
				json.put("types", "edit");
			}
			
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
			json.put("id", exam.getId());
			return json;
		} catch (Exception e) {
			log.error("添加试卷信息出错:" + e.getMessage(),e);
			e.printStackTrace();
			json.put(JsonMsg.MSG, "添加试卷信息出错");
			return json;
		}
	}
	
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 进入编辑考试内容
	 * @author 周文斌
	 * @date 2016-2-17 下午6:10:42
	 * @version 1.0
	 * @param model
	 * @param request
	 * @param types
	 * @return
	 */
	@RequestMapping("/examContent/{types}/{examId}")
	public String examContent(Model model,HttpServletRequest request,@PathVariable String types,@PathVariable Integer examId){
		if(types.equals("add")){
			log.info("添加内容");
			model.addAttribute("types", 1);
		}else if(types.equals("edit")){
			log.info("编辑内容");
			log.info("查询关联试卷");
			TikuExam exam = tikuExamServiceImpl.findTikuExamById(examId);
			List<TikuExamPaperRelationVo> eprv = tikuExamPaperRelationServiceImpl.findPaperByExamId(examId);
			model.addAttribute("paperList", eprv);
			model.addAttribute("types", exam.getStatus());
		}
		model.addAttribute("examId", examId);
		return "tiku/exam/addExamTwo";
	}
	
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 查询考试下的试卷
	 * @author 周文斌
	 * @date 2016-2-17 下午8:25:37
	 * @version 1.0
	 * @param model
	 * @param request
	 * @param paper
	 * @return
	 */
	@RequestMapping("selPaper")
	public String selPaper(Model model,HttpServletRequest request,TikuPaper paper,Integer examId){
		
		paper.setCompanyId(WebUtils.getCurrentCompanyId());
		
		log.info("分页查询试卷");
		List<TikuExamPaperVo> paperVo = tikuPaperServiceImpl.findPaperByExam(paper); 
		
		log.info("总数");
		Integer count = tikuPaperServiceImpl.findPaperByExamCount(paper);
		
		log.info("查询试卷是否被使用");
		Map<String, Object> param = new HashMap<String, Object>();
		for (TikuExamPaperVo t : paperVo) {
			param.clear();
			param.put("tikuExamId", examId);
			param.put("tikuPaperId", t.getId());
			TikuExamPaperRelation tpr = tikuExamPaperRelationServiceImpl.findExist(param);
			if(tpr != null && tpr.getStatus().equals(1)){
				t.setStatus(1);
			}else{
				t.setStatus(2);
			}
		}
		
		PageFinder<TikuExamPaperVo> papPage = new PageFinder<TikuExamPaperVo>(paper.getPage(), paper.getPageSize(),count,paperVo);
		
		model.addAttribute("paper", papPage);
		return "tiku/exam/examPaperAjax";
	}
	
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 查询一二级项目
	 * @author 周文斌
	 * @date 2016-2-18 上午11:52:15
	 * @version 1.0
	 * @param request
	 * @param oneItem
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selItem")
	public JSONObject selItem(HttpServletRequest request,Integer oneItem){
		JSONObject json = new JSONObject();
		//根据一级项目id 查询 二级 项目
		log.info("查询1级或2级项目");
		try {
			if(oneItem == null){
				List<SysConfigItem> item = sysConfigItemServiceImpl.findSysConfigItemByPid("1", oneItem, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentUserSchoolId(request));
				json.put("item", item);
			}else{
				List<SysConfigItem> item = sysConfigItemServiceImpl.findSysConfigItemByPid("2", oneItem, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentUserSchoolId(request));
				json.put("item", item);
			}
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
		} catch (Exception e) {
			log.error("查询项目时出错："+e.getMessage(),e);
			e.printStackTrace();
			json.put(JsonMsg.MSG, "查询项目时出错");
		}
		return json;
	}
	
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 查询课程
	 * @author 周文斌
	 * @date 2016-2-18 下午12:16:42
	 * @version 1.0
	 * @param request
	 * @param oneItem
	 * @param twoItem
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selCoures")
	public JSONObject selClassType(HttpServletRequest request,Integer oneItem,Integer twoItem){
		JSONObject json = new JSONObject();
		//查询 班型
		log.info("查询课程");
		try {
			List<ClassType> types = classTypeServiceImpl.findClassByItem(WebUtils.getCurrentCompanyId(), WebUtils.getCurrentUserSchoolId(request), oneItem, twoItem);
			json.put("types", types);
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
		} catch (Exception e) {
			log.error("查询课程时出错：" + e.getMessage(),e);
			e.printStackTrace();
			json.put(JsonMsg.MSG, "查询课程时出错");
		}
		return json;
	}
	
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 查询试卷详细信息
	 * @author 周文斌
	 * @date 2016-2-18 下午7:30:55
	 * @version 1.0
	 * @param request
	 * @param paperId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selPaperDetail")
	public JSONObject selPaperDetail(HttpServletRequest request,Integer paperId){
		JSONObject json = new JSONObject();
		Map<String, Object> param = new HashMap<String, Object>();
		Integer count = 0;
		Double score = 0.0;
		Double sum = 0.0;
		try {
			//查询单选状态
			param.put("paperId", paperId);
			param.put("topicType", "TOPIC_TYPE_RADIO");
			count = getCount(tikuPaperTopicServiceImpl.findCount(param));
			score = getScore(tikuPaperTopicTypeServiceImpl.findScoreByPaperid(param));
			sum = count * score;
			json.put("radio_count", count);
			json.put("radio_score", score);
			json.put("radio_sum", sum);
			
			//查询多选状态
			param.clear();
			param.put("paperId", paperId);
			param.put("topicType", "TOPIC_TYPE_MULTIPLE");
			count = getCount(tikuPaperTopicServiceImpl.findCount(param));
			score = getScore(tikuPaperTopicTypeServiceImpl.findScoreByPaperid(param));
			sum = count * score;
			json.put("multiple_count", count);
			json.put("multiple_score", score);
			json.put("multiple_sum", sum);
			
			//查询不定项状态
			param.clear();
			param.put("paperId", paperId);
			param.put("topicType", "TOPIC_TYPE_UNDEFINED");
			count = getCount(tikuPaperTopicServiceImpl.findCount(param));
			score = getScore(tikuPaperTopicTypeServiceImpl.findScoreByPaperid(param));
			sum = count * score;
			json.put("undefined_count", count);
			json.put("undefined_score", score);
			json.put("undefined_sum", sum);

			//查询判断状态
			param.clear();
			param.put("paperId", paperId);
			param.put("topicType", "TOPIC_TYPE_TRUE_FALSE");
			count = getCount(tikuPaperTopicServiceImpl.findCount(param));
			score = getScore(tikuPaperTopicTypeServiceImpl.findScoreByPaperid(param));
			sum = count * score;
			json.put("truefalse_count", count);
			json.put("truefalse_score", score);
			json.put("truefalse_sum", sum);

			//查询填空状态
			param.clear();
			param.put("paperId", paperId);
			param.put("topicType", "TOPIC_TYPE_FILLING");
			count = getCount(tikuPaperTopicServiceImpl.findCount(param));
			score = getScore(tikuPaperTopicTypeServiceImpl.findScoreByPaperid(param));
			sum = count * score;
			json.put("filling_count", count);
			json.put("filling_score", score);
			json.put("filling_sum", sum);

			//查询简答状态
			param.clear();
			param.put("paperId", paperId);
			param.put("topicType", "TOPIC_TYPE_ANSWER");
			json.put("answer_count", getCount(tikuPaperTopicServiceImpl.findCount(param)));
			json.put("answer_sum", getSum(tikuPaperTopicServiceImpl.findSumScore(param)));
			json.put("answer_score", "----");
			
			//查询材料状态
			param.clear();
			param.put("paperId", paperId);
			param.put("topicType", "TOPIC_TYPE_CASE");
			json.put("case_count", getCount(tikuPaperTopicServiceImpl.findCount(param)));
			json.put("case_sum", getSum(tikuPaperTopicServiceImpl.findSumScore(param)));
			json.put("case_score", "----");
			
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
			
		} catch (Exception e) {
			log.error("查询试卷详细时出错：" + e.getMessage(),e);
			e.printStackTrace();
			json.put(JsonMsg.MSG, "查询试卷详细时出错");
		}
		return json;
	}
	
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 保存第二步
	 * @author 周文斌
	 * @date 2016-2-19 下午3:01:04
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/savecontent")
	public JSONObject savecontent(HttpServletRequest request,Integer id,String delid,String addid,Integer status,Integer counts){
		JSONObject json = new JSONObject();
		Integer userId = WebUtils.getCurrentUserId(request);
		try {
			//查询允许考的次数
			TikuExam te = tikuExamServiceImpl.findTikuExamById(id);

			if(te.getExamMode() == 1){
				//判断是否少了
				if(te.getExamCount() > counts){
					json.put(JsonMsg.MSG, "当前添加的试卷数不能少于允许考试次数");
					return json;
				}
				if(te.getExamCount().equals(0) && counts.equals(0)){
					json.put(JsonMsg.MSG, "至少添加一份试卷");
					return json;
				}
			}

			if(addid != null && !addid.equals("")){
				//添加的试卷
				if(addid.indexOf(",") > 0){
					String[] aid = addid.split(",");
					
					for (String s : aid) {
						addRelation(id,Integer.parseInt(s),1,userId);
					}
				}else{
					addRelation(id,Integer.parseInt(addid),1,userId);
				}
			}
			
			if(delid != null && !delid.equals("")){
				//删除的试卷
				if(delid.indexOf(",") > 0){
					String[] did = delid.split(",");
					for (String s : did) {
						updateRelation(Integer.parseInt(s),2,userId);
					}
				}else{
					updateRelation(Integer.parseInt(delid),2,userId);
				}
			}
			//更改考试状态
			TikuExam exam = new TikuExam();
			exam.setId(id);
			exam.setStatus(status);
			exam.setUpdateTime(new Date());
			exam.setUpdator(userId);
			tikuExamServiceImpl.update(exam);
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
		} catch (Exception e) {
			log.error("保存考试第二步错误:" + e.getMessage(),e);
			e.printStackTrace();
			json.put(JsonMsg.MSG, "保存时出现错误");
		}
		return json;
	}
	
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 获得题库列表
	 * @author 周文斌
	 * @date 2016-2-19 下午5:46:07
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCateList")
	public JSONObject getCateList(HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			List<TikuCategory> cate = tikuCategoryServiceImpl.findCateList(WebUtils.getCurrentCompanyId());
			json.put("cate", cate);
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
		} catch (Exception e) {
			log.error("获得题库列表出错:" + e.getMessage(),e);
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 
	 * Class Name: TikuExamController.java
	 * @Description: 获得题库科目列表
	 * @author 周文斌
	 * @date 2016-2-19 下午5:46:07
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSubList")
	public JSONObject getSubList(HttpServletRequest request,Integer cateId){
		JSONObject json = new JSONObject();
		try {
			List<TikuSubject> sub = tikuSubjectServiceImpl.findSubByCategoryId(cateId);
			json.put("sub", sub);
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
		} catch (Exception e) {
			log.error("获得题库列表出错:" + e.getMessage(),e);
			e.printStackTrace();
		}
		return json;
	}
	
	private void updateRelation(Integer id,Integer status,Integer userId){
		TikuExamPaperRelation tepr = new TikuExamPaperRelation();
		tepr.setId(id);
		tepr.setStatus(status);
		tepr.setUpdateTime(new Date());
		tepr.setUpdator(userId);
		tikuExamPaperRelationServiceImpl.update(tepr);
	}
	
	private void addRelation(Integer id,Integer paperId,Integer status,Integer userId){
		Map<String, Object> param = new HashMap<String, Object>();
		//查询是否存在
		param.put("tikuExamId", id);
		param.put("tikuPaperId", paperId);
		TikuExamPaperRelation tpr = tikuExamPaperRelationServiceImpl.findExist(param);
		if(tpr != null){
			tpr.setStatus(1);
			tpr.setUpdateTime(new Date());
			tpr.setUpdator(userId);
			tikuExamPaperRelationServiceImpl.update(tpr);
		}else{
			tpr = new TikuExamPaperRelation();
			tpr.setTikuExamId(id);
			tpr.setTikuPaperId(paperId);
			tpr.setStatus(1);
			tpr.setCreateTime(new Date());
			tpr.setCreator(userId);
			tikuExamPaperRelationServiceImpl.insert(tpr);
		}
	}
	private Integer getCount(Integer count){
		if(count == null){
			count = 0;
		}
		return count;
	}
	private Double getScore(Double score){
		if(score == null){
			score =0.0;
		}
		return score;
	}

	private Double getSum(Double sum){
		if(sum == null){
			sum =0.0;
		}
		return sum;
	}
}
