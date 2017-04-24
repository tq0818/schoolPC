package com.yuxin.wx.controller.classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.config.WebIniSecurityManagerFactory;
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

import com.alibaba.fastjson.JSONArray;
import com.yuxin.wx.api.classes.IClassPackageRelationService;
import com.yuxin.wx.api.classes.IClassPackageStageService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.model.classes.ClassPackageRelation;
import com.yuxin.wx.model.classes.ClassPackageStage;
import com.yuxin.wx.model.system.SysPageHeadFoot;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassPackageConditionVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.system.SysPageHeadFootVo;

/**
 * Controller of ClassPackageStage
 * @author chopin
 * @date 2016-3-21
 */
@Controller
@RequestMapping("/classPackageStage")
public class ClassPackageStageController {
	
	@Autowired
	private IClassPackageStageService classPackageStageServiceImpl;
	@Autowired
	private IClassTypeService classTypeServiceImpl;
	@Autowired
	private IClassPackageRelationService classPackageRelationServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ClassPackageStage search){
		if (search == null) {
			search = new ClassPackageStage();
			// search.setPageSize(20);
		}
		model.addAttribute("list", classPackageStageServiceImpl.findClassPackageStageByPage(search));
		return "classPackageStage/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(ClassPackageStage ClassPackageStage) {
		classPackageStageServiceImpl.insert(ClassPackageStage);
		return "redirect:/classPackageStage";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(ClassPackageStage ClassPackageStage) {
		classPackageStageServiceImpl.update(ClassPackageStage);
		return "redirect:/classPackageStage";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		classPackageStageServiceImpl.deleteClassPackageStageById(id);
		return "redirect:/classPackageStage";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public ClassPackageStage getJson(Model model, @PathVariable Integer id){
		return classPackageStageServiceImpl.findClassPackageStageById(id);
	}
	
	
	/**
	 * 
	 * Class Name: ClassPackageStageController.java
	 * @Description: 查询课程包下的所有阶段
	 * @author zhang.zx
	 * @date 2016年3月23日 下午3:46:55
	 * @modifier
	 * @modify-date 2016年3月23日 下午3:46:55
	 * @version 1.0
	 * @param stage
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryClassPackageStages")
	public List<ClassPackageStage> findClassPackageStages(ClassPackageStage stage){
		stage.setCompanyId(WebUtils.getCurrentCompanyId());
		stage.setSchoolId(WebUtils.getCurrentSchoolId());
		List<ClassPackageStage> data=classPackageStageServiceImpl.queryClassPackageStages(stage);
		return data;
	}
	
	/**
	 * 
	 * Class Name: ClassPackageStageController.java
	 * @Description: 添加阶段
	 * @author zhang.zx
	 * @date 2016年3月23日 下午4:36:28
	 * @modifier
	 * @modify-date 2016年3月23日 下午4:36:28
	 * @version 1.0
	 * @param ClassPackageStage
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addStage", method = RequestMethod.POST)
	public String addStage(ClassPackageStage stage,HttpServletRequest request) {
		if(null!=stage.getTitle() && !"".equals(stage.getTitle())){
			if(stage.getTitle().length()>16){
				return "阶段名称最多不能超过16个字符";
			}
		}
		
		if(null!=stage.getDescription() && !"".equals(stage.getDescription())){
			if(stage.getDescription().length()>120){
				return "阶段描述最多不能超过120个字符";
			}
		}
		ClassPackageStage classStage=classPackageStageServiceImpl.findClassPackageStageById(stage.getId());
		if(null!=classStage){
			stage.setSort(null);
			stage.setUpdateTime(new Date());
			stage.setUpdator(WebUtils.getCurrentUserId(request));
			classPackageStageServiceImpl.update(stage);
		}else{
			stage.setCompanyId(WebUtils.getCurrentCompanyId());
			stage.setSchoolId(WebUtils.getCurrentSchoolId());
			stage.setOriginalPrice(0.00);
			stage.setDelFlag(0);
			stage.setCreateTime(new Date());
			stage.setCreator(WebUtils.getCurrentUserId(request));
			stage.setUpdateTime(new Date());
			stage.setUpdator(WebUtils.getCurrentUserId(request));
			classPackageStageServiceImpl.insert(stage);
		}
		//判断是否是添加课程完后第一次添加阶段，如果是则将原来课程包下的课程全部移动到当前阶段下
		if(null!=stage.getUpdateFlag() && !"".equals(stage.getUpdateFlag()) && "up".equals(stage.getUpdateFlag())){
			//查询当前课程包下的所有课程
			ClassPackageRelation search=new ClassPackageRelation();
			search.setClassPackageId(stage.getClassPackageId());
			List<ClassPackageRelation> data=classPackageRelationServiceImpl.queryClassPackageStageRelation(search);
			//将课程移动到当前阶段下
			for(ClassPackageRelation relation:data){
				relation.setClassPackageStageId(stage.getId());
				classPackageRelationServiceImpl.update(relation);
			}
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/upStagePrice", method = RequestMethod.POST)
	public String upStagePrice(ClassPackageStage stage,HttpServletRequest request) {
		ClassPackageStage classStage=classPackageStageServiceImpl.findClassPackageStageById(stage.getId());
		if(null!=classStage){
			stage.setUpdateTime(new Date());
			stage.setUpdator(WebUtils.getCurrentUserId(request));
			classPackageStageServiceImpl.update(stage);
		}
		return "success";
	}
	
	/**
	 * 
	 * Class Name: ClassPackageStageController.java
	 * @Description: 修改课程包阶段
	 * @author zhang.zx
	 * @date 2016年3月25日 上午9:52:52
	 * @modifier
	 * @modify-date 2016年3月25日 上午9:52:52
	 * @version 1.0
	 * @param stage
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateStage", method = RequestMethod.POST)
	public ClassPackageStage updateStage(ClassPackageStage stage,HttpServletRequest request) {
		ClassPackageStage classStage=classPackageStageServiceImpl.findClassPackageStageById(stage.getId());
		if(null!=classStage){
			stage.setUpdateTime(new Date());
			stage.setUpdator(WebUtils.getCurrentUserId(request));
			classPackageStageServiceImpl.update(stage);
		}
		return null;
	}
	
	/**
	 * 
	 * Class Name: ClassPackageStageController.java
	 * @Description: 删除阶段
	 * @author zhang.zx
	 * @date 2016年3月24日 下午4:55:08
	 * @modifier
	 * @modify-date 2016年3月24日 下午4:55:08
	 * @version 1.0
	 * @param stageId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delStage",method=RequestMethod.POST)
	public ClassPackageStage delStage(Integer stageId,HttpServletRequest request){
		ClassPackageStage stage=classPackageStageServiceImpl.findClassPackageStageById(stageId);
		if(null!=stage){
			stage.setDelFlag(1);
			stage.setPublishStatus("STOP_SALE");
			stage.setUpdateTime(new Date());
			stage.setUpdator(WebUtils.getCurrentUserId(request));
			classPackageStageServiceImpl.update(stage);
		}
		return stage;
	}
	
	/**
	 * 
	 * Class Name: ClassPackageStageController.java
	 * @Description:阶段排序
	 * @author zhang.zx
	 * @date 2016年3月25日 上午10:00:04
	 * @modifier
	 * @modify-date 2016年3月25日 上午10:00:04
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/orderStage", method = RequestMethod.POST)
	public String orderStage(HttpServletRequest request) {
		List<ClassPackageStage> stagelist=JSONArray.parseArray(request.getParameter("list"),ClassPackageStage.class);
		if(null!=stagelist){
			for(ClassPackageStage stage:stagelist){
				classPackageStageServiceImpl.update(stage);
			}
		}
		return "success";
	}
	/**
	 * 
	 * Class Name: ClassPackageStageController.java
	 * @Description:阶段排序
	 * @author zhang.zx
	 * @date 2016年3月25日 上午10:00:04
	 * @modifier
	 * @modify-date 2016年3月25日 上午10:00:04
	 * @version 1.0
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryStageInPackage/{id}", method = RequestMethod.POST)
	public List<ClassPackageStage> queryStageInPackage(HttpServletRequest request,@PathVariable Integer id){
		ClassPackageStage search =new ClassPackageStage();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setSchoolId(WebUtils.getCurrentSchoolId());
		search.setClassPackageId(id);
		return classPackageStageServiceImpl.queryClassPackageStages(search);
	}
	
	/**
	 * 
	 * Class Name: ClassPackageStageController.java
	 * @Description: 阶段名称校验
	 * @author DELL.COM
	 * @date 2016年4月6日 上午11:14:27
	 * @modifier
	 * @modify-date 2016年4月6日 上午11:14:27
	 * @version 1.0
	 * @param title
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkName")
	public boolean checkStageName(String title,Integer id,Integer packageId){
		boolean flag=true;
		ClassPackageStage search =new ClassPackageStage();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setTitle(title);
		search.setClassPackageId(packageId);
		List<ClassPackageStage> data=classPackageStageServiceImpl.queryClassPackageStages(search);
		if(null!=data && data.size()>0){
			if(null!=id && !"".equals(id)){
				ClassPackageStage stage=classPackageStageServiceImpl.findClassPackageStageById(id);
				if(null!=stage && title.equals(stage.getTitle())){
					flag=true;
				}else{
					flag=false;
				}
			}else{
				flag=false;
			}
		}
		return flag;
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
