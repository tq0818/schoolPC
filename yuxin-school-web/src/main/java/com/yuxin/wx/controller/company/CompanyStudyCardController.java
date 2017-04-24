package com.yuxin.wx.controller.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassPackageService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyConfigProxyOrgService;
import com.yuxin.wx.api.company.ICompanyStudyCardLibService;
import com.yuxin.wx.api.company.ICompanyStudyCardService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.classes.ClassPackage;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.company.CompanyConfigProxyOrg;
import com.yuxin.wx.model.company.CompanyStudyCard;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.company.CompanyStudyCardLibVo;
import com.yuxin.wx.vo.company.CompanyStudyCardsVo;

/**
 * Controller of CompanyStudyCard
 * 
 * @author chopin
 * @date 2017-3-14
 */
@Controller
@RequestMapping("/companyStudyCard")
public class CompanyStudyCardController {

	@Autowired
	private ICompanyStudyCardService companyStudyCardServiceImpl;
	@Autowired
	private ICompanyStudyCardLibService companyStudyCardLibServiceImpl;
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	@Autowired
	private IClassPackageService classPackageServiceImpl;
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private ICompanyConfigProxyOrgService companyConfigProxyOrgServiceImpl;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyStudyCard search) {
		if (search == null) {
			search = new CompanyStudyCard();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyStudyCardServiceImpl.findCompanyStudyCardByPage(search));
		return "companyStudyCard/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(CompanyStudyCard CompanyStudyCard) {
		companyStudyCardServiceImpl.insert(CompanyStudyCard);
		return "redirect:/companyStudyCard";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(CompanyStudyCard CompanyStudyCard) {
		companyStudyCardServiceImpl.update(CompanyStudyCard);
		return "redirect:/companyStudyCard";
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyStudyCardServiceImpl.deleteCompanyStudyCardById(id);
		return "redirect:/companyStudyCard";
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CompanyStudyCard getJson(Model model, @PathVariable Integer id) {
		return companyStudyCardServiceImpl.findCompanyStudyCardById(id);
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
	 * Class Name: CompanyStudyCardController.java
	 * 
	 * @Description: 进入学习卡管理页面
	 * @author dongshuai
	 * @date 2017年3月14日 下午2:24:23
	 * @modifier
	 * @modify-date 2017年3月14日 下午2:24:23
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value = "/gotoStudyCardsManage")
	public String gotoStudyCardsManage() {
		return "studyCards/studyCardsManage";
	}

	/**
	 * 
	 * Class Name: CompanyStudyCardController.java
	 * 
	 * @Description: 查看学习卡列表
	 * @author dongshuai
	 * @date 2017年3月14日 下午3:59:02
	 * @modifier
	 * @modify-date 2017年3月14日 下午3:59:02
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryStudyCards", method = RequestMethod.POST)
	public JSONObject queryStudyCards(CompanyStudyCardsVo search) {
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		Integer companyId = WebUtils.getCurrentCompanyId();

		search.setCompanyId(companyId);

		PageFinder<CompanyStudyCardsVo> studyCards = this.companyStudyCardServiceImpl.queryStudyCards(search);

		json.put("studyCards", studyCards);
		return json;
	}

	/**
	 * 
	 * Class Name: CompanyStudyCardController.java
	 * 
	 * @Description: 导出学习卡
	 * @author dongshuai
	 * @date 2017年3月14日 下午4:25:31
	 * @modifier
	 * @modify-date 2017年3月14日 下午4:25:31
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/exportStudyCards")
	public ModelAndView exportStudyCards(CompanyStudyCardsVo search) {

		Integer companyId = WebUtils.getCurrentCompanyId();
		search.setCompanyId(companyId);
		List<CompanyStudyCardsVo> studycards = this.companyStudyCardServiceImpl.queryStudyCardsList(search);

		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for (CompanyStudyCardsVo s : studycards) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", s.getName());
			map.put("proxyName", s.getProxyName());
			map.put("totalNum", s.getTotalNum());
			map.put("usedNum", s.getUsedNum());
			map.put("price", s.getPrice());
			map.put("useDate",
					s.getUseDateBegin() == null ? "永久有效"
							: new SimpleDateFormat("yyyy-MM-dd").format(s.getUseDateBegin()) + " 至 "
									+ new SimpleDateFormat("yyyy-MM-dd").format(s.getUseDateEnd()));
			map.put("createTime", s.getCreateTime());
			map.put("username", s.getRealName()!=null?s.getRealName():s.getUsername() );
			lists.add(map);
		}
		StringBuffer title = new StringBuffer(
				"学习卡名称:name,代理商:proxyName,学习卡数量:totalNum,已领取数量:usedNum,学习卡价格:price,使用期限:useDate,创建时间:createTime,创建人:username");
		ViewFiles excel = new ViewFiles();
		@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workbook", wb);
		map.put("fileName", "学习卡.xls");
		return new ModelAndView(excel, map);

	}

	/**
	 * 
	 * Class Name: CompanyStudyCardController.java
	 * 
	 * @Description: 进入创建学习卡页面
	 * @author dongshuai
	 * @date 2017年3月14日 下午4:43:57
	 * @modifier
	 * @modify-date 2017年3月14日 下午4:43:57
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value = "/gotoCreateStudyCard")
	public String gotoCreateStudyCard(Model model) {
		SysConfigService service = WebUtils.getConfigService("SERVICE_CLASS_PACKAGE");
		model.addAttribute("classPackageflag", service!=null?service.getDelFlag():0 );
		return "studyCards/createStudyCard";
	}

	/**
	 * 
	 * Class Name: CompanyStudyCardController.java
	 * 
	 * @Description: 创建学习卡
	 * @author dongshuai
	 * @date 2017年3月14日 下午5:05:58
	 * @modifier
	 * @modify-date 2017年3月14日 下午5:05:58
	 * @version 1.0
	 * @param sc
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createStudyCards", method = RequestMethod.POST)
	public JSONObject createStudyCards(HttpServletRequest request, CompanyStudyCard sc) {
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer userId = WebUtils.getCurrentUserId(request);
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("companyId", companyId);
		map.put("name", sc.getName());
		int nameCount = this.companyStudyCardServiceImpl.queryCountByNameOrPrefix(map);
		if(nameCount>0){
			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
			json.put(JsonMsg.MSG, "学习卡名称已存在！");
			return json;
		}
		
		map.clear();
		map.put("companyId", companyId);
		map.put("prefix", sc.getPrefix());
		int prefixCount = this.companyStudyCardServiceImpl.queryCountByNameOrPrefix(map);
		if(prefixCount>0){
			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
			json.put(JsonMsg.MSG, "学习码前缀已存在！");
			return json;
		}
		
		sc.setPrefix(sc.getPrefix().toUpperCase());
		
		if(sc.getStartDate() != null){
			try {
				sc.setUseDateBegin(new SimpleDateFormat("yyyy-MM-dd").parse(sc.getStartDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(sc.getEndDate() != null){
			try {
				sc.setUseDateEnd(new SimpleDateFormat("yyyy-MM-dd").parse(sc.getEndDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		sc.setCreateTime(new Date());
		sc.setCreator(userId);

		if (sc.getProxyOrgId() != null) {
			// 有代理
			String[] proxyOrgIds = sc.getProxyOrgId().split(",");
			sc.setProxyOrgId(null);
			sc.setCompanyId(companyId);
			sc.setUsedNum(0);
			boolean isSuccess = this.companyStudyCardServiceImpl.createHasProxyOrgStudyCards(sc, proxyOrgIds);
			if (!isSuccess) {
				json.put(JsonMsg.RESULT, JsonMsg.ERROR);
				json.put(JsonMsg.MSG, "添加学习卡失败！");
				return json;
			}
		} else {
			// 无代理
			sc.setCompanyId(companyId);
			sc.setUsedNum(0);
			this.companyStudyCardServiceImpl.insert(sc);
		}

		return json;
	}

	/**
	 * 
	 * Class Name: CompanyStudyCardController.java
	 * 
	 * @Description: 生成学习卡库
	 * @author dongshuai
	 * @date 2017年3月14日 下午5:21:09
	 * @modifier
	 * @modify-date 2017年3月14日 下午5:21:09
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createStudyCardLibs", method = RequestMethod.POST)
	public JSONObject createStudyCardLibs(CompanyStudyCardLibVo lib) {
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		Integer companyId = WebUtils.getCurrentCompanyId();

		CompanyStudyCard studyCard = this.companyStudyCardServiceImpl.findCompanyStudyCardById(lib.getCardId());

		lib.setCompanyId(companyId);
		List<CompanyStudyCardLibVo> data = this.companyStudyCardLibServiceImpl.queryStudyCardLibsListByCardId(lib);

		if (data != null && data.size() > 0) {
			// 已生成则返回生成成功
			return json;
		}

		boolean isSuccess = this.companyStudyCardLibServiceImpl.createStudyCardLibs(studyCard);

		if (!isSuccess) {
			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
			json.put(JsonMsg.MSG, "学习卡库生成失败！");
			return json;
		}

		return json;
	}

	/**
	 * 
	 * Class Name: CompanyStudyCardController.java
	 * 
	 * @Description: 查询学习卡库
	 * @author dongshuai
	 * @date 2017年3月15日 下午1:56:29
	 * @modifier
	 * @modify-date 2017年3月15日 下午1:56:29
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryStudyCardLibs", method = RequestMethod.POST)
	public JSONObject queryStudyCardLibs(CompanyStudyCardLibVo search) {
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);

		Integer companyId = WebUtils.getCurrentCompanyId();

		search.setCompanyId(companyId);
		PageFinder<CompanyStudyCardLibVo> studyCardLibs = this.companyStudyCardLibServiceImpl
				.queryStudyCardLibs(search);
		
		json.put("studyCardLibs", studyCardLibs);

		return json;
	}
	
	/**
	 * 
	 * Class Name: CompanyStudyCardController.java
	 * @Description: 导出学习卡库
	 * @author dongshuai
	 * @date 2017年3月15日 下午2:14:58
	 * @modifier
	 * @modify-date 2017年3月15日 下午2:14:58
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/exportStudyCardLibs")
	public ModelAndView exportStudyCardLibs(CompanyStudyCardLibVo search) {

		Integer companyId = WebUtils.getCurrentCompanyId();
		search.setCompanyId(companyId);
		List<CompanyStudyCardLibVo> studycardLibs = this.companyStudyCardLibServiceImpl
				.queryStudyCardLibsListByCardId(search);

		CompanyStudyCard studyCard = this.companyStudyCardServiceImpl.findCompanyStudyCardById(search.getCardId());

		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for (CompanyStudyCardLibVo s : studycardLibs) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", s.getCode());
			map.put("status", s.getStatus() == 1?"已使用":"未使用");
			map.put("username", s.getUsername());
			map.put("mobile", s.getMobile());
			map.put("name", s.getName());
			map.put("userTime", s.getUseTime());
			lists.add(map);
		}
		StringBuffer title = new StringBuffer(
				"学习码:code,状态:status,使用者用户名:username,使用者手机号:mobile,使用者姓名:name,使用日期:userTime");
		ViewFiles excel = new ViewFiles();
		@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			wb = ExcelUtil.newWorkbook(lists, "sheet1", title.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workbook", wb);
		map.put("fileName", "“" + studyCard.getName() + (studyCard.getProxyOrgName()!=null?"_" +studyCard.getProxyOrgName():"" ) + "”学习卡库.xls");
		return new ModelAndView(excel, map);

	}
	
	/**
	 * 
	 * Class Name: CompanyStudyCardController.java
	 * @Description: 查询课程
	 * @author dongshuai
	 * @date 2017年3月15日 下午5:12:27
	 * @modifier
	 * @modify-date 2017年3月15日 下午5:12:27
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryClassTypes", method = RequestMethod.POST)
	public JSONObject queryClassTypes(ClassType search){
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentSchoolId();
		
		search.setPublishStatus("CLASS_ON_SALE");
		search.setCreateSchoolId(schoolId);
		search.setCompanyId(companyId);
		PageFinder<ClassTypeVo> classTypes = this.classTypeServiceImpl.findClassTypesByPage(search);
		String commodityPicUrl="http://"+propertiesUtil.getProjectImageUrl()+"/";
		
		json.put("commodityPicUrl", commodityPicUrl);
		json.put("classTypes", classTypes);
		return json;
	}
	
	/**
	 * 
	 * Class Name: CompanyStudyCardController.java
	 * @Description: 查询课程包
	 * @author dongshuai
	 * @date 2017年3月15日 下午5:33:02
	 * @modifier
	 * @modify-date 2017年3月15日 下午5:33:02
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryClassPackage", method = RequestMethod.POST)
	public JSONObject queryClassPackage(ClassPackage search){
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer schoolId = WebUtils.getCurrentSchoolId();
		
		search.setPublishStatus("CLASS_ON_SALE");
		search.setCompanyId(companyId);
		search.setSchoolId(schoolId);
		PageFinder<ClassPackage> classPackages = this.classPackageServiceImpl.queryClassPackageHasCountsByCondition(search);
		String commodityPicUrl="http://"+propertiesUtil.getProjectImageUrl()+"/";
		
		json.put("commodityPicUrl", commodityPicUrl);
		json.put("classPackages", classPackages);
		return json;
	}
	
	/**
	 * 
	 * Class Name: CompanyStudyCardController.java
	 * @Description: 学习卡库详情
	 * @author dongshuai
	 * @date 2017年3月20日 下午2:54:48
	 * @modifier
	 * @modify-date 2017年3月20日 下午2:54:48
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value = "/gotoStudyCardLibDetails")
	public String gotoStudyCardLibDetails(HttpServletRequest request, Model model){
		String cardId = request.getParameter("cardId");
		
		if(cardId == null) {
			model.addAttribute("errorMsg","数据异常01");
			return "/studyCards/studyCardLibDetails";
		}
		
		Integer companyId = WebUtils.getCurrentCompanyId();
		
		CompanyStudyCardsVo search = new CompanyStudyCardsVo();
		search.setId(Integer.parseInt(cardId));
		search.setCompanyId(companyId);
		PageFinder<CompanyStudyCardsVo> studyCards = this.companyStudyCardServiceImpl.queryStudyCards(search);
		
		CompanyStudyCardsVo studyCard = studyCards.getData().get(0);

		if(studyCard == null) {
			model.addAttribute("errorMsg","数据异常02");
			return "/studyCards/studyCardLibDetails";
		}
		
		String courseList = studyCard.getCourseList();
		String[] courseArrays = courseList.split(",");
		Integer type = studyCard.getCourseType();
		List<String> courseNames = getCourseNameList(courseArrays, type);
		
		if(studyCard.getUseDateBegin() != null)	studyCard.setUseDateBeginString(new SimpleDateFormat("yyyy-MM-dd").format(studyCard.getUseDateBegin()));
		if(studyCard.getUseDateEnd() != null)	studyCard.setUseDateEndString(new SimpleDateFormat("yyyy-MM-dd").format(studyCard.getUseDateEnd()));
		if(studyCard.getCreateTime() != null)	studyCard.setCreateTimeString(new SimpleDateFormat("yyyy-MM-dd").format(studyCard.getCreateTime()));
		
		
		model.addAttribute("studyCard", studyCard);
		model.addAttribute("courseNames", courseNames);
		
		return "/studyCards/studyCardLibDetails";
	}

	private List<String> getCourseNameList(String[] courseArrays, Integer type) {
		
		List<String> courseNames = new ArrayList<String>();
		
		switch(type){
			case 0:
				for (String courseId : courseArrays) {
					ClassType classType = classTypeServiceImpl.findClassTypeById(Integer.parseInt(courseId));
					if(classType!=null)
						courseNames.add(classType.getName());
				}
				break;
			case 1:
				for (String courseId : courseArrays) {
					ClassPackage classPackage = classPackageServiceImpl.findClassPackageById(Integer.parseInt(courseId));
					if(classPackage!=null)
						courseNames.add(classPackage.getName());
				}
				break;
		}
		
		return courseNames;
	}
	
	/**
	 * 
	 * Class Name: CompanyStudyCardController.java
	 * @Description: 查询代理机构
	 * @author dongshuai
	 * @date 2017年3月21日 下午6:55:06
	 * @modifier
	 * @modify-date 2017年3月21日 下午6:55:06
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryProxyOrgs",method = RequestMethod.POST)
	public JSONObject queryProxyOrgs(){
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyConfigProxyOrg search = new CompanyConfigProxyOrg();
		
		search.setCompanyId(companyId);
		List<CompanyConfigProxyOrg> proxyOrgs = this.companyConfigProxyOrgServiceImpl.queryProxyByCompanyId(search);
		
		json.put("proxyOrgs", proxyOrgs);
		
		return json;
	}
	
	/**
	 * 
	 * Class Name: CompanyStudyCardController.java
	 * @Description: 检验学习卡名称或前缀
	 * @author dongshuai
	 * @date 2017年3月22日 下午3:12:38
	 * @modifier
	 * @modify-date 2017年3月22日 下午3:12:38
	 * @version 1.0
	 * @param data
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkStudyCard",method = RequestMethod.POST)
	public Boolean checkStudyCardNameOrPrefix(String data, String type){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("companyId", companyId);
		map.put(type, "prefix".equals(type)?data.toUpperCase():data);
		int count = this.companyStudyCardServiceImpl.queryCountByNameOrPrefix(map);
		return count > 0?false:true;
	}
	
	/**
	 * 
	 * Class Name: CompanyStudyCardController.java
	 * @Description: 添加代理机构
	 * @author dongshuai
	 * @date 2017年3月23日 上午11:42:01
	 * @modifier
	 * @modify-date 2017年3月23日 上午11:42:01
	 * @version 1.0
	 * @param request
	 * @param proxy
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addOrUpdateProxy",method=RequestMethod.POST)
	public JSONObject addOrUpdateProxy(HttpServletRequest request,CompanyConfigProxyOrg proxy){
			JSONObject json = new JSONObject();
			json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
 			Integer companyId = WebUtils.getCurrentCompanyId();
			
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("companyId", companyId);
			map.put("name", proxy.getName());
			int nameCount = this.companyConfigProxyOrgServiceImpl.queryCountByNameOrPrefix(map);
			
			if(nameCount>0){
				json.put(JsonMsg.RESULT, JsonMsg.ERROR);
				json.put(JsonMsg.MSG, "代理机构名称已存在！");
				return json;
			}
			
			map.clear();
			map.put("companyId", companyId);
			map.put("prefix", proxy.getPrefix());
			int prefixCount = this.companyConfigProxyOrgServiceImpl.queryCountByNameOrPrefix(map);
			
			if(prefixCount>0){
				json.put(JsonMsg.RESULT, JsonMsg.ERROR);
				json.put(JsonMsg.MSG, "代理机构前缀已存在！");
				return json;
			}
			
			proxy.setCompanyId(companyId);
			proxy.setCreateDate(new Date());
			proxy.setStatus(1);
			proxy.setDelFlag(0);
			proxy.setPrefix(proxy.getPrefix().toUpperCase());
			companyConfigProxyOrgServiceImpl.insert(proxy);
			return json;
	}
}
	
