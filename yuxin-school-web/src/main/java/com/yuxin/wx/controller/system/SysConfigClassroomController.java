package com.yuxin.wx.controller.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.system.ISysConfigCampusService;
import com.yuxin.wx.api.system.ISysConfigClassroomService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.common.SysLoader;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.system.SysConfigCampus;
import com.yuxin.wx.model.system.SysConfigClassroom;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of SysConfigClassroom
 * @author wang.zx
 * @date 2014-12-5
 */
@Controller
@RequestMapping("/sysConfigClassroom")
public class SysConfigClassroomController {
	
	@Autowired
	private IClassModuleLessonService classModuleLessonServiceImpl;
	
	@Autowired
	private ISysConfigDictService sysConfigDictServiceImpl;
	
	@Autowired
	private ISysConfigCampusService sysConfigCampusServiceImpl;
	
	private Log log = LogFactory.getLog("log");
	
	@Autowired
	private ISysConfigClassroomService sysConfigClassroomServiceImpl;
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 跳向教室列表页
	 * @author liuxindong
	 * @date 2014-12-21 下午3:25:22
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "goClassroomList",method = RequestMethod.GET)
	public String goClassroomList(Model model){
		return "system/classroom/classroomList";
	}
	
	/**
	 * @Description:( 根据校区、教室获取教室 )
	 * @author wang.zx 
	 * @date 2015-1-13 下午5:35:28
	 * @version 1.0
	 * @param search
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getAjaxClassRooms")
	public List<SysConfigClassroom> getAjaxClassRooms(SysConfigClassroom search,HttpServletRequest request){
		List<SysConfigClassroom> classRooms = null;
		if(search != null){
			classRooms = sysConfigClassroomServiceImpl.findClassroom(search);
			if(classRooms != null && classRooms.size() > 0){
				for(SysConfigClassroom classRoom : classRooms){
					if(classRoom.getRoomAttrCode() != null){
						classRoom.setRoomAttrCode(SysLoader.dictCode2Name(classRoom.getRoomAttrCode()));
					}
					if(classRoom.getRoomTypeCode() != null){
						classRoom.setRoomTypeCode(SysLoader.dictCode2Name(classRoom.getRoomTypeCode()));
					}
					if(classRoom.getRentScope() != null){
						classRoom.setRentScope(SysLoader.dictCode2Name(classRoom.getRentScope()));
					}
				}
			}
		}
		return classRooms;
	}
	
	/**
	 * @Description:(根据校区ID获取对应的教室)
	 * @author wang.zx 
	 * @date 2014-12-31 下午3:30:02
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getClassRoomByCampus/{campusId}")
	public List<SysConfigClassroom> getClassRoomByCampus(HttpServletRequest request, @PathVariable String campusId){
		Users user = WebUtils.getCurrentUser(request);
		SysConfigClassroom room = new SysConfigClassroom();
		Integer schoolId = 0;
		Integer campus = 0;
		room.setStatus(1);
		if(user != null){
			schoolId = user.getSchoolId();
			room.setSchoolId(schoolId);
		}
		if(campusId != null && campusId.length() > 0){
			campus = Integer.parseInt(campusId);
			room.setCampusId(campus);
		}
		if(schoolId != 0){
			List<SysConfigClassroom> classroomes = sysConfigClassroomServiceImpl.findClassroom(room);
			return classroomes;
		}
		return null;
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 跳向新增或修改教室页
	 * @author liuxindong
	 * @date 2014-12-21 下午3:26:44
	 * @version 1.0
	 * @param model
	 * @param search
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goManageClassroom",method = RequestMethod.GET)
	public String goManageClassroom(Model model, SysConfigClassroom search,HttpServletRequest request){
		wrapperSearchClassroom(search,request);
		List<SysConfigClassroom> classroomes = sysConfigClassroomServiceImpl.findClassroom(search);
		if (classroomes != null && classroomes.size() > 0) {
			model.addAttribute("classroom", classroomes.get(0));
		}
		return "system/classroom/classroomManage";
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 新增教室
	 * @author liuxindong
	 * @date 2014-12-21 下午3:27:18
	 * @version 1.0
	 * @param sysConfigClassroom
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addClassroom", method = RequestMethod.POST)
	public String addClassroom(SysConfigClassroom sysConfigClassroom,HttpServletRequest request) {
		try {
			wrapperSaveClassroom(sysConfigClassroom,SysConfigConstant.OPERATE_ADD,request);
			sysConfigClassroomServiceImpl.insert(sysConfigClassroom);
		} catch (Exception e) {
			log.error("新增教室出错！", e);
			e.printStackTrace();
			return "fail";
		}
		return JsonMsg.SUCCESS;
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 修改教室
	 * @author liuxindong
	 * @date 2014-12-21 下午3:27:30
	 * @version 1.0
	 * @param sysConfigClassroom
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editClassroom", method = RequestMethod.POST)
	public String editClassroom(SysConfigClassroom sysConfigClassroom,HttpServletRequest request) {
		return modifyClassroom(sysConfigClassroom,request);
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 检查唯一性
	 * @author liuxindong
	 * @date 2014-12-21 下午3:28:16
	 * @version 1.0
	 * @param search
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkUnique", method = RequestMethod.POST)
	public String checkUnique(SysConfigClassroom search,HttpServletRequest request) {
		wrapperSearchClassroom(search,request);
		List<SysConfigClassroom> Classroomes = sysConfigClassroomServiceImpl.findClassroom(search);
		if (Classroomes != null && Classroomes.size() > 0) {
			return JsonMsg.SUCCESS;
		}
		return "fail";
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
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 包装查询参数
	 * @author liuxindong
	 * @date 2014-12-21 下午3:28:32
	 * @version 1.0
	 * @param sysConfigClassroom
	 * @param request
	 */
	private void wrapperSearchClassroom(SysConfigClassroom sysConfigClassroom,HttpServletRequest request){
		sysConfigClassroom.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 包装新增或修改参数
	 * @author liuxindong
	 * @date 2014-12-21 下午3:28:50
	 * @version 1.0
	 * @param sysConfigClassroom
	 * @param operate
	 * @param request
	 */
	private void wrapperSaveClassroom(SysConfigClassroom sysConfigClassroom,String operate,HttpServletRequest request){
		sysConfigClassroom.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
		if (operate.equals(SysConfigConstant.OPERATE_ADD)) {
			sysConfigClassroom.setStatus(1);
			sysConfigClassroom.setCompanyId(WebUtils.getCurrentCompanyId());
			sysConfigClassroom.setCreateTime(new Date());
			sysConfigClassroom.setCreator(WebUtils.getCurrentUserId(request));
			sysConfigClassroom.setUpdateTime(new Date());
			sysConfigClassroom.setUpdator(WebUtils.getCurrentUserId(request));
		} else if (operate.equals(SysConfigConstant.OPERATE_EDIT)) {
			sysConfigClassroom.setUpdateTime(new Date());
			sysConfigClassroom.setUpdator(WebUtils.getCurrentUserId(request));
		}
		sysConfigClassroom.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
	}
	
	/**
	 * @Description: 为班号模块的课次添加教室
	 * @author wang.zx
	 * @date 2015-01-07 下午10:28:50
	 * @version 1.0
	 * @param request
	 */
	@RequestMapping(value="/addClassRoomForLesson")
	public String addClassRoomForLesson(HttpServletRequest request){
		System.out.println("选择教室");
		return "module/classModuleNo/addClassRoomForLesson";
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 进入教室页面，根据公司id 查询校区 教室
	 * @author 周文斌
	 * @date 2015-5-5 下午8:25:40
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping("/classroom")
	public String classroom(Model model,HttpServletRequest request){
		//获得公司id 和 学校 id
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		Integer companyId = WebUtils.getCurrentCompanyId();
		//查询校区集合
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("companyId", companyId);
		param.put("schoolId", schoolId);
		List<SysConfigCampus> sccList = sysConfigCampusServiceImpl.findCampusBySchoolCompanyId(param);
		
		model.addAttribute("sccList", sccList);
		return "/resource/classroom/classroom";
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 根据分校id  查询 教室 信息
	 * @author 周文斌
	 * @date 2015-5-5 下午9:15:14
	 * @version 1.0
	 * @param model
	 * @param campusId
	 * @return
	 */
	@RequestMapping("/selClassroom")
	public String selClassroom(Model model,Integer campusId){
		//根据id 获得校区信息
		SysConfigCampus scc = sysConfigCampusServiceImpl.findSysConfigCampusById(campusId);
		
		//获得教室信息
		List<SysConfigClassroom> scrList = sysConfigClassroomServiceImpl.findClassroomByCampusId(campusId);
		
		model.addAttribute("scc", scc);
		model.addAttribute("scrList", scrList);
		return "resource/classroom/selClassroomAjax";
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 获得字典信息  添加到 下拉框
	 * @author 周文斌
	 * @date 2015-5-6 下午3:36:39
	 * @version 1.0
	 * @param model
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDict")
	public String getDict(Model model,Integer id,HttpServletRequest request){
		
		List<SysConfigDict> roomAttr = new ArrayList<SysConfigDict>();
		List<SysConfigDict> roomType = new ArrayList<SysConfigDict>();
		List<SysConfigDict> roomKind = new ArrayList<SysConfigDict>();
		List<SysConfigDict> roomTime = new ArrayList<SysConfigDict>();
		List<SysConfigDict> roomLevel = new ArrayList<SysConfigDict>();
		//查询教室相关 字典表
		List<SysConfigDict> scdList = sysConfigDictServiceImpl.findDictByClassroom();
		for (SysConfigDict d : scdList) {
			if(d.getDictCode().equals("CLASSROOM_ATTR")){
				roomAttr.add(d);
			}else if(d.getDictCode().equals("CLASSROOM_TYPE")){
				roomType.add(d);
			}else if(d.getDictCode().equals("CLASSROOM_KIND")){
				roomKind.add(d);
			}else if(d.getDictCode().equals("CLASSROOM_RENT_TIME")){
				roomTime.add(d);
			}else if(d.getDictCode().equals("CLASSROOM_LEVEL")){
				roomLevel.add(d);
			}
		}
		
		//在查询当前教室
		SysConfigClassroom scr = sysConfigClassroomServiceImpl.findSysConfigClassroomById(id);
		
		//获得公司id 和 学校 id
		Integer schoolId = WebUtils.getCurrentUserSchoolId(request);
		Integer companyId = WebUtils.getCurrentCompanyId();
		//查询校区集合
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("companyId", companyId);
		param.put("schoolId", schoolId);
		List<SysConfigCampus> sccList = sysConfigCampusServiceImpl.findCampusBySchoolCompanyId(param);
		
		model.addAttribute("sccList", sccList);
		model.addAttribute("scr", scr);
		model.addAttribute("roomAttr", roomAttr);
		model.addAttribute("roomType", roomType);
		model.addAttribute("roomKind", roomKind);
		model.addAttribute("roomTime", roomTime);
		model.addAttribute("roomLevel", roomLevel);
		
		return "resource/classroom/dict";
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 检查停用时 是否还有课程 在使用教室
	 * @author 周文斌
	 * @date 2015-5-7 上午10:10:04
	 * @version 1.0
	 * @param classroomId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/stopStatus")
	public JSONObject stopStatus(SysConfigClassroom sysConfigClassroom,HttpServletRequest request){
		JSONObject json = new JSONObject();
		// 查询今天之后 是否还有 课程占用该教室
		ClassModuleLesson cml = new ClassModuleLesson();
		cml.setClassroomId(sysConfigClassroom.getId());
		cml.setLessonDate(new Date());
		List<ClassModuleLesson> cmlList = classModuleLessonServiceImpl.findLessonInfoByRoomIdAndDate(cml);
		
		if(cmlList != null && cmlList.size() > 0){
			//被占用 获得 占用信息
			json.put("cmlList", cmlList);
		}else{
			//没有被占用 就更改状态
			String msg = modifyClassroom(sysConfigClassroom,request);
			json.put(JsonMsg.MSG, msg);
		}
		return json;
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 修改教室
	 * @author 周文斌
	 * @date 2015-5-7 上午11:01:37
	 * @version 1.0
	 * @param sysConfigClassroom
	 * @param request
	 * @return
	 */
	private String modifyClassroom(SysConfigClassroom sysConfigClassroom,HttpServletRequest request){
		try {
			wrapperSaveClassroom(sysConfigClassroom,SysConfigConstant.OPERATE_EDIT,request);
			sysConfigClassroomServiceImpl.update(sysConfigClassroom);
			return JsonMsg.SUCCESS;
		} catch (Exception e) {
			log.error("修改教室出错！", e);
			e.printStackTrace();
			return "fail";
		}
	}
	
	/**
	 * 
	 * Class Name: SysConfigClassroomController.java
	 * @Description: 加载教室信息
	 * @author zhang.zx
	 * @date 2015年9月13日 上午10:12:19
	 * @modifier
	 * @modify-date 2015年9月13日 上午10:12:19
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/loadClassroom",method=RequestMethod.POST)
	public List<SysConfigClassroom> queryClassroom(){
		//获得公司id 和 学校 id
		Integer schoolId = WebUtils.getCurrentSchoolId();
		Integer companyId = WebUtils.getCurrentCompanyId();
		//查询校区集合
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("companyId", companyId);
		param.put("schoolId", schoolId);
		param.put("status", 1);
		//获得教室信息
		return sysConfigClassroomServiceImpl.findClassroomByconditions(param);
	}
}
