package com.yuxin.wx.controller.commodity;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.mysql.jdbc.log.Log;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.commodity.ICommoditySpecialService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.commodity.mapper.CommodityMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.controller.student.StudentController;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommoditySpecial;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.system.impl.SysConfigTeacherServiceImpl;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.commodity.CommodityVo;

/**
 * Controller of Commodity
 * @author wang.zx
 * @date 2015-3-14
 */
@Controller
@RequestMapping("/commodity")
public class CommodityController {
	
	@Autowired
	private ICommodityService commodityServiceImpl;
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private ISysConfigTeacherService sysConfigTeacherServiceImpl; 
	@Autowired
	private ICommoditySpecialService commoditySpecialServiceImpl; 
	
    private static Logger log = Logger.getLogger(CommodityController.class);

	
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Commodity search){
		if (search == null) {
			search = new Commodity();
			// search.setPageSize(20);
		}
		model.addAttribute("list", commodityServiceImpl.findCommodityByPage(search));
		return "commodity/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(Commodity Commodity) {
		commodityServiceImpl.insert(Commodity);
		return "redirect:/commodity";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(Commodity Commodity) {
		commodityServiceImpl.update(Commodity);
		return "redirect:/commodity";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		commodityServiceImpl.deleteCommodityById(id);
		return "redirect:/commodity";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public Commodity getJson(Model model, @PathVariable Integer id){
		return commodityServiceImpl.findCommodityById(id);
	}
	
	
	/**
	 * 
	 * Class Name: CommodityController.java
	 * @Description: 显示所有商品列表并分页
	 * @author zhang.zx
	 * @date 2015年4月8日 下午4:41:30
	 * @modifier
	 * @modify-date 2015年4月8日 下午4:41:30
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="/showAllProject",method=RequestMethod.POST)
	public String showAllProjectProduct(CommodityVo search,Model model){
		model.addAttribute("itemOneId", search.getItemOneId());
		search.setPageSize(7);
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		String commodityPicUrl=propertiesUtil.getImageServicePath();
		model.addAttribute("commodityPicUrl", commodityPicUrl);
		PageFinder<CommodityVo> pageFinder=commodityServiceImpl.queryCommodityByPage(search);
		model.addAttribute("pageFinder", pageFinder);
		return "classType/classIndexAjaxList";
	} 
	
	@ResponseBody
	@RequestMapping(value="/loadData")
	public List<CommodityVo> loadData(HttpServletRequest request,CommodityVo search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
//		search.setSchoolId(WebUtils.getCurrentSchoolId());
		List<Integer> list = new ArrayList<Integer>();
		if(search.getItemOneId() != null) {
			list.add(search.getItemOneId());
			search.setItemOneIds(list);
		}
		if(StringUtils.isNotBlank(search.getItemOneIdStrs())) {
			String[] arr = search.getItemOneIdStrs().split(",");
			for (String s : arr) {
				list.add(Integer.parseInt(s));
			}
			search.setItemOneIds(list);
		}
		return commodityServiceImpl.loadData(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/loadData3", method = RequestMethod.POST)
	public List<CommodityVo> loadData3(HttpServletRequest request,CommodityVo search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		return commodityServiceImpl.loadData(search);
	}
	@ResponseBody
	@RequestMapping(value="/loadData2", method = RequestMethod.POST)
	public List<CommodityVo> loadData2(HttpServletRequest request,@RequestParam("classTypeOne") String classTypeOne,@RequestParam("classTypeTwo")String classTypeTwo){
		Map search =new HashMap();
		search.put("companyId",WebUtils.getCurrentCompanyId());
		search.put("classTypeOne", classTypeOne);
		search.put("classTypeTwo", classTypeTwo);
		List<CommodityVo> vo=commodityServiceImpl.loadData2(search);
		return vo ;
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
	 * 专题页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("toSpecialPage")
	public String toSpecialPage(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		   return "/system/specialManage";
	}
	
	
	/**
	 * 分页数据
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("findSpecialByapge")
	public String findSpecialByapge(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		try{
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			CommoditySpecial special = new CommoditySpecial();
			if(StringUtils.isNotBlank(pageNum)){
				special.setPage(Integer.parseInt(pageNum));
			}
			if(StringUtils.isNotBlank(pageSize)){
				special.setPageSize(Integer.parseInt(pageSize));
			}
			List<CommoditySpecial> specialList = commoditySpecialServiceImpl.findSpecialByPage(special);
			int count = commoditySpecialServiceImpl.findSpecialByPageCount();
			model.addAttribute("specialList", specialList);
			model.addAttribute("count", count);
			model.addAttribute("pageNum", pageNum);
		}catch(Exception e){
			log.error("findSpecialByapge is error", e);
		}
		return "/system/specialList";
	}
	
	
	/**
	 * 跳转新增专题页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("toAddSpecialPage")
	public String toAddSpecialPage(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		SysConfigItem search = new SysConfigItem();
		List<SysConfigItem> subjectList = null;
		try{
			Users user = WebUtils.getCurrentUser(request);
			search.setItemType("1");
			search.setCompanyId(user.getCompanyId());
			search.setSchoolId(user.getSchoolId());
			subjectList = sysConfigItemServiceImpl.findItem(search);
			String id = request.getParameter("id");
			if(StringUtils.isNotBlank(id)){
				CommoditySpecial cs = new CommoditySpecial();
				cs.setId(Integer.valueOf(id));
				CommoditySpecial special = commoditySpecialServiceImpl.findSpecialById(cs);
				model.addAttribute("special", special);
				String baseUrl = "http://" + FileUtil.props.getProperty("yunduoketang.oss.imagedomain") ;
				model.addAttribute("baseUrl", baseUrl);
			}
		}catch(Exception e){
			log.error("toAddSpecialPage is error :", e);
		}
		if(subjectList == null){
			subjectList = new ArrayList<SysConfigItem>();
		}
		model.addAttribute("subjectList", subjectList);
		return "/system/addSpecial";
	}
	
	/**
	 * 新增专题
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addOrUpdateSpecial")
	public String addSpecial(HttpServletRequest request,HttpServletResponse response,MultipartRequest multiPartRquest){
		String result = "redirect:/commodity/toSpecialPage";
		CommoditySpecial special = new CommoditySpecial();
		try{
			String title = request.getParameter("title");
			String label = request.getParameter("label");
			String detailTitle = request.getParameter("detailTitle");
			String realPath[] = uploadImg(multiPartRquest);
			String coverPicUrl = realPath[0];
			String descript = request.getParameter("descript");
			String detailCoverPicUrl = realPath[1];
			String detailText = request.getParameter("detailText");
			String teacherIds = request.getParameter("teacherIds");
			String commodityIds = request.getParameter("commodityIds");
			String subjectSelect = request.getParameter("subjectSelect");
			String specialId = request.getParameter("specialId");
			if(StringUtils.isBlank(title) || StringUtils.isBlank(label) 
			|| StringUtils.isBlank(descript) || (StringUtils.isBlank(specialId) &&  (StringUtils.isBlank(coverPicUrl) || StringUtils.isBlank(detailCoverPicUrl)))
			|| StringUtils.isBlank(detailText) || StringUtils.isBlank(subjectSelect)
			|| StringUtils.isBlank(teacherIds) || StringUtils.isBlank(commodityIds) ){
				return result;
			}
		    special.setTitle(title);
		    special.setLabel(label);
		    special.setSubjectId(Integer.valueOf(subjectSelect));
		    special.setDetailTitle(detailTitle);
			special.setCoverPicUrl(coverPicUrl);
			special.setDescript(descript);
			special.setDetailCoverPicUrl(detailCoverPicUrl);
			special.setDetailText(detailText);
			special.setTeacherIds(teacherIds);
			special.setCommodityIds(commodityIds);
			special.setCreateTime(new Date());
			if(StringUtils.isBlank(specialId)){
				special.setStatus(0);
				commoditySpecialServiceImpl.insert(special);
			}else{
				special.setId(Integer.valueOf(specialId));
				special.setUpdateTime(new Date());
				int row = commoditySpecialServiceImpl.updateSpecial(special);
			}
			
		}catch(Exception e){
			log.error("addSpecial is error :", e);
		}
		return result;
	}
	
	/**
	 * 根据学科查询老师
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getTeachersBySubject")
	public JSONObject getTeachersBySubject(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = new JSONObject();
		SysConfigTeacher teacher = new SysConfigTeacher();
		try{
			int itemOneId = Integer.parseInt(request.getParameter("itemOneId"));
			teacher.setItemOneId(itemOneId);
			List<SysConfigTeacher> teacherList = sysConfigTeacherServiceImpl.findTeacherBySubject(teacher);
			json.put("teacherList", teacherList);
		}catch(Exception e){
			log.error("getTeachersBySubject is error :", e);
		}
		return json;
	}
	
	/**
	 * 根据老师查询课程
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCourseByTeacher")
	public JSONObject getCourseByTeacher(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Users user = WebUtils.getCurrentUser(request);
			map.put("companyId", user.getCompanyId());
			map.put("schoolId", user.getSchoolId());
			String teacherIds =  request.getParameter("teacherIds");
			if(StringUtils.isNotBlank(teacherIds)){
				List<Integer> idList = new ArrayList<Integer>();
				String ids[] = teacherIds.split(",");
				for(String id : ids){
					idList.add(Integer.valueOf(id));
				}
				map.put("teacherIds",idList);
				List<CommodityVo> commodityList = commodityServiceImpl.queryCourseByTeacherIds(map);
				String url="http://"+propertiesUtil.getProjectImageUrl()+"/";
				json.put("commodityList", commodityList);
				json.put("url", url);
			}
		}catch(Exception e){
			log.error("getCourseByTeacher is error :", e);
		}
		return json;
	}
	
	/**
	 * 专题上下架，修改排序
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateStatusOrder")
	public String updateStatus(HttpServletRequest request,HttpServletResponse response){
		String result = "failed";
		try{
			CommoditySpecial special = new CommoditySpecial();
			String status = request.getParameter("status");
			String orderFlag = request.getParameter("orderFlag");
			int specialId = Integer.valueOf(request.getParameter("specialId"));
			special.setId(specialId);
			if(StringUtils.isNotBlank(status)){
				special.setStatus(Integer.valueOf(status));
			}
			if(StringUtils.isNotBlank(orderFlag)){
				special.setOrderFlag(Integer.valueOf(orderFlag));
			}
			int row = commoditySpecialServiceImpl.updateSpecial(special);
			if(row > 0){
				result = "success";
			}
		}catch(Exception e){
			log.error("updateStatus is error :",e);
		}
		return result;
	}
	
	
	private String[] uploadImg(MultipartRequest multiPartRquest) throws Exception{
		InputStream coverPicIS = null;
		InputStream detailCoverPicIS = null;
		String relativePath[] = new String[2];
		try{
			MultipartFile	coverPic = multiPartRquest.getFile("coverPic");
			MultipartFile	detailCoverPic = multiPartRquest.getFile("detailCoverPic");
		    String path = FileUtil.props.getProperty("imageServiceRealPath");
		    if(coverPic.getSize() > 0){
			    String coverRelativePath = FileUtil.getPath("special", String.valueOf(WebUtils.getCurrentCompanyId()), coverPic.getOriginalFilename());
			    String coverPicPath = path + coverRelativePath;
			    coverPicIS = coverPic.getInputStream();
			    File coverPicFile = new File(coverPicPath);
			    FileUtils.copyInputStreamToFile(coverPicIS, coverPicFile);
				relativePath[0] = coverRelativePath;
		    }
		    if(detailCoverPic.getSize() > 0){
		    	String detailCoverRelativePath =  FileUtil.getPath("special", String.valueOf(WebUtils.getCurrentCompanyId()), detailCoverPic.getOriginalFilename());
		    	String detailCoverPicPath =path + detailCoverRelativePath;
				File detailCoverPicFile= new File(detailCoverPicPath);
				detailCoverPicIS = detailCoverPic.getInputStream();
				FileUtils.copyInputStreamToFile(detailCoverPicIS, detailCoverPicFile);
			    relativePath[1] = detailCoverRelativePath;
		    }
			
		}finally{
			if(coverPicIS != null){
				IOUtils.close(coverPicIS);
			}
			if(detailCoverPicIS != null){
				IOUtils.close(detailCoverPicIS);
			}
		}
		return relativePath;
	}
}
