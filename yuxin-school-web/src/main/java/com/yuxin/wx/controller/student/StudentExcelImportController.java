package com.yuxin.wx.controller.student;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyRegisterConfigService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.utils.CheckImportUtil;
import com.yuxin.wx.utils.FileUploadUtil;
import com.yuxin.wx.utils.ImportExcl;
import com.yuxin.wx.utils.ParameterUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.student.StudentAll4CompanyVo;
import com.yuxin.wx.vo.student.StudentImportVo;
import com.yuxin.wx.vo.student.StudentListVo;


/**
 * 
 * @ClassName: StudentExcelImportController
 * @Description: 学员验证，导入（新）
 * @author dongshuai
 * @date 2017年1月18日 下午3:18:30
 * @modifier
 * @modify-date 2017年1月18日 下午3:18:30
 * @version 1.0
 */
@Controller
@RequestMapping("/excelImportStudents")
public class StudentExcelImportController {
	
	Log log_student = LogFactory.getLog( "student" );
	
	private static final String FILE_FORMAT_ERROR = "文件格式不正确，请下载标准模板！";
	private static final String FILE_UPLOAD_ERROR = "文件上传失败！";
	private static final String TEMPLET_ERROR     = "模板错误,请先下载标准模板！";
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private ICompanyRegisterConfigService companyRegisterConfigServiceImpl;
	@Autowired
	private IStudentService studentServiceImpl;
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping( value = "/studentsValidate", method = RequestMethod.POST )
	public JSONObject studentsValidate( MultipartRequest multiPartRquest, HttpServletRequest request, HttpServletResponse response ) {
		JSONObject json = new JSONObject();
		json.put( JsonMsg.RESULT, JsonMsg.SUCCESS );			/* 默认验证无误 */
		
		JSONObject json2In  = new JSONObject();					/* 异常信息列表（存内部校验异常） */
		JSONObject json2Out = new JSONObject();					/* 异常信息列表（存与网校学员校验异常） */
		
		Integer userId = WebUtils.getCurrentUserId( request );
		String content = this.propertiesUtil.getExclePath() + userId + "/";	/* 缓存文件目录 */
		MultipartFile multipartFile = multiPartRquest.getFile( "imgData" );
		String name = multipartFile.getOriginalFilename();
		
		if ( name != null && !"".equals( name ) ) {
			
			String suff = name.substring( name.lastIndexOf(".") );
			if ( !".xls".equals( suff ) && !".xlsx".equals( suff ) ) {
				json.put( JsonMsg.RESULT, JsonMsg.EXCEPTION );
				json.put( JsonMsg.MSG, FILE_FORMAT_ERROR );
				return json;
			}
			String fileName = FileUploadUtil.getlnstance().getNewFileName( name );
			File fileContent = new File( content );
			File file = new File( content + fileName );
			
			try {
				if ( !fileContent.exists() ) {
					file.mkdirs();
				}
				multipartFile.transferTo( file );
			} catch (IOException e) {
				json.put( JsonMsg.RESULT, JsonMsg.EXCEPTION );
				json.put( JsonMsg.MSG, FILE_UPLOAD_ERROR );
				e.printStackTrace();
			} 
			
			/* 验证信息 */
			try {
				List<Student> studentsList = excel2Student( content + fileName );
				json2In  = validateDatas2In( studentsList );
				json2Out = validateDatas2Out( (List<Student>)json2In.get("studentlist") );
			} catch (Exception e) {
				json.put( JsonMsg.RESULT, JsonMsg.EXCEPTION );
				json.put( JsonMsg.MSG, TEMPLET_ERROR );
				e.printStackTrace();
			}
		
			json.put( "excelPath", content + fileName );
			
			if( !(Boolean)json2In.get("result") || !(Boolean)json2Out.get("result") ){
				json.put( JsonMsg.RESULT, JsonMsg.ERROR );
				json.put( "errorMsg2In", (List<String>)json2In.get("msg") );
				json.put( "errorMsg2Out", (List<String>)json2Out.get("msg") );
				json.put( "students4Update", (List<Student>)json2In.get("students") );
				json.put( "students4Insert", (List<Student>)json2Out.get("students") );
				return json;
			}
			json.put( "students4Insert", (List<Student>)json2Out.get("students") );
			json.put( "updateList", (List<Student>)json2Out.get("updateList") );
		}
		return json;
	}
	
	/*
	 * Excel 自身校验
	 */
	private JSONObject validateDatas2In( List<Student> list ){
		JSONObject json = new JSONObject();
		List<String> errorMsg  = new ArrayList<String>();		/* 错误信息 */
 		List<Student> students = new ArrayList<Student>(); 		/* 验证，去重后的student */
		Map <String, String> eduAreaMap=new HashMap<String, String>();
		Map <String, String> eduSchoolMap=new HashMap<String, String>();
		Map <String, String> dictMap=new HashMap<String, String>();
		List<SysConfigDict> dictAreaList=studentServiceImpl.findEduAreaList();
		if(null!=dictAreaList && dictAreaList.size()>0){
			for(SysConfigDict vo : dictAreaList){
				dictMap.put(vo.getItemValue(), vo.getItemCode());
				if("EDU_SCHOOL_AREA".equals(vo.getDictCode())){
					eduAreaMap.put(vo.getItemCode(),vo.getItemValue());
				}else{
					eduSchoolMap.put(vo.getItemCode(),vo.getItemValue());
				}
			}
		}
 		
 		String isArea=WebUtils.getCurrentIsArea();
		Map<String,String>isAreaHava=new HashMap<String, String>();//存放对比区域
		Map<String,String>isSchoolHava=new HashMap<String, String>();//存放对比学校
		if("0".equals(isArea)){
			isAreaHava.putAll(eduAreaMap);
			isSchoolHava.putAll(eduSchoolMap);
		}else if("1".equals(isArea)){
			//学区  区域
			String eduArea=WebUtils.getCurrentCompany().getEduAreaSchool();
			if(eduAreaMap.containsKey(eduArea)){
				isAreaHava.put(eduAreaMap.get(eduArea),eduArea);
			}
			//学区  学校
			int areaCode=0;
			for(int k=0;k<dictAreaList.size();k++){
				if(eduArea.equals(dictAreaList.get(k).getItemCode())){
					areaCode=dictAreaList.get(k).getId();
				}
			}
			for(int k=0;k<dictAreaList.size();k++){
				if(null!=dictAreaList.get(k).getParentItemId() && areaCode==dictAreaList.get(k).getParentItemId()){
					isSchoolHava.put(dictAreaList.get(k).getItemCode(),dictAreaList.get(k).getItemValue());
				}
			}
		}else{
			//学校用户 学校
			String eduArea=WebUtils.getCurrentCompany().getEduAreaSchool();
			if(eduSchoolMap.containsKey(eduArea)){
				isSchoolHava.put(eduArea, eduSchoolMap.get(eduArea));
			}
			//学校用户  区域
			int parentId=0;
			for(int k=0;k<dictAreaList.size();k++){
				if(eduArea.equals(dictAreaList.get(k).getItemCode())){
					 parentId=dictAreaList.get(k).getParentItemId();
				}
			}
			for(int k=0;k<dictAreaList.size();k++){
				if(parentId==dictAreaList.get(k).getId()){
					isAreaHava.put(dictAreaList.get(k).getItemValue(), dictAreaList.get(k).getItemCode());
				}
			}
			
		}
		
		
		String flag = getCompanyRegisterFlag();					/* 机构注册方式 */
		
		for (int i = list.size() - 1; i >= 0; i--) {			/* 倒叙，保留第一个*/
			List<String> error = new ArrayList<String>();
			switch (flag) {
				case "mobile":
					/* 手机号 */
					if( list.get(i).getMobile() == null ){
						error.add("第" + (i + 2) + "行中手机号不能为空!");
					} else if ( !ParameterUtil.isMobilePhone(list.get(i).getMobile()) ){
						error.add("第" + (i + 2) + "行中手机号不正确!");
					}
					/* 用户名 */
					if( list.get(i).getUsername() != null && !ParameterUtil.isUserName(list.get(i).getUsername()) ){
						error.add("第" + (i + 2) + "行中用户名不正确!");
					}
					break;
				case "username":
					/* 手机号 */
					if( list.get(i).getMobile() != null && !ParameterUtil.isMobilePhone(list.get(i).getMobile())){
						error.add("第" + (i + 2) + "行中手机号不正确!");
					}
					/* 用户名 */
					if( list.get(i).getUsername() == null){
						error.add("第" + (i + 2) + "行中用户名不能为空!");
					} else if ( !ParameterUtil.isUserName(list.get(i).getUsername()) ){
						error.add("第" + (i + 2) + "行中用户名不正确!");
					}
					break;
				case "all":
					/* 手机号 */
					if( list.get(i).getMobile() == null ){
						error.add("第" + (i + 2) + "行中手机号不能为空!");
					} else if ( !ParameterUtil.isMobilePhone(list.get(i).getMobile()) ){
						error.add("第" + (i + 2) + "行中手机号不正确!");
					}
					/* 用户名 */
					if( list.get(i).getUsername() == null){
						error.add("第" + (i + 2) + "行中用户名不能为空!");
					} else if ( !ParameterUtil.isUserName(list.get(i).getUsername()) ){
						error.add("第" + (i + 2) + "行中用户名不正确!");
					}
					break;
				default:
					break;
			}
			
			/* 姓名 */
			if( list.get(i).getName() == null ){
				error.add("第" + (i + 2) + "行中姓名不能为空!");
			}
			/* 身份证号 */
			if( list.get(i).getIdentityId() != null ){
				if( ParameterUtil.isNum(list.get(i).getIdentityId()) && ( list.get(i).getIdentityId().length() == 15 || list.get(i).getIdentityId().length() == 18 ) ){
					
				} else {
					error.add("第" + (i + 2) + "行中身份证号不正确!");
				}
			}
			/* 邮箱 */
			if(	list.get(i).getEmail() != null && !ParameterUtil.isEmail(list.get(i).getEmail()) ){
				error.add("第" + (i + 2) + "行中邮箱不正确!");
			}
			/* QQ */
			if( list.get(i).getQq() != null ){
				if( ParameterUtil.isNum(list.get(i).getQq()) || list.get(i).getQq().length() > 10 ){
					
				} else {
					error.add("第" + (i + 2) + "行中QQ不正确!");
				}
			}
			/* 紧急联系电话 */
			if( list.get(i).getEmergencyPhone() != null && !ParameterUtil.isMobilePhone(list.get(i).getEmergencyPhone()) ){
				error.add("第" + (i + 2) + "行中紧急联系电话不正确!");
			}
			for (int j = 0; j < list.size(); j++) {
				if ( list.get(i) != null && list.get(j) != null) {
					if( i == j ) continue;
					/* 去重 */
					/* 手机号 */
					if ( list.get(i).getMobile() != null && list.get(j).getMobile() != null && list.get(i).getMobile().equals(list.get(j).getMobile()) ) {
						error.add("第" + (i + 2) + "行中手机号与第" + (j + 2) + "行相同！");
					}
					/* 用户名 */
					if ( list.get(i).getUsername() != null && list.get(j).getUsername() != null && list.get(i).getUsername().equals(list.get(j).getUsername()) ) {
						error.add("第" + (i + 2) + "行中用户名与第" + (j + 2) + "行相同！");
					}
				}
			}
			
//			//学区
//			if( list.get(i).getEduArea() != null && !eduAreaMap.containsValue(list.get(i).getEduArea())){
//				error.add("第" + (i + 2) + "行中区域不存在!");
//			}else{
//				if(list.get(i).getEduArea() != null && !isAreaHava.containsKey(list.get(i).getEduArea())){
//					error.add("第" + (i + 2) + "行中无权输入该区域!");
//				}
//			}
			//学校
			if( list.get(i).getEduSchool() != null && !eduSchoolMap.containsKey(list.get(i).getEduSchool())){
				error.add("第" + (i + 2) + "行中学校不存在!");
			}else{
				if(list.get(i).getEduSchool() != null && !isSchoolHava.containsKey(list.get(i).getEduSchool())){
					error.add("第" + (i + 2) + "行中无权输入该学校!");
				}else{
					//反推  给一条信息添加学区编号
					int parentId=0;
					for(int k=0 ;k<dictAreaList.size();k++){
						if(list.get(i).getEduSchool().equals(dictAreaList.get(k).getItemCode())){
							 parentId=dictAreaList.get(k).getParentItemId();
							 break;
						}
					}
					for(int k=0 ;k<dictAreaList.size();k++){
						if(parentId == dictAreaList.get(k).getId()){
							list.get(i).setEduArea(dictAreaList.get(k).getItemCode());
							 break;
						}
					}
				}
			}
			//学段
			if(list.get(i).getEduStep() != null && "小学".equals(list.get(i).getEduStep())){
				list.get(i).setEduStep("STEP_01");
			}else if(list.get(i).getEduStep() != null && "初中中学".equals(list.get(i).getEduStep())){
				list.get(i).setEduStep("STEP_02");
			}else if(list.get(i).getEduStep() != null && "高中中学".equals(list.get(i).getEduStep())){
				list.get(i).setEduStep("STEP_03");
			}else{
				error.add("第" + (i + 2) + "行中无效学段!");
			}
			//入学年份
			if( list.get(i).getEduYear() != null ){
				if( CheckImportUtil.isNum(list.get(i).getEduYear()) || list.get(i).getEduYear().length() > 4 ){
					
				} else {
					error.add("第" + (i + 2) + "行中无效年份!");
				}
			}
			if( list.get(i).getEduClass() != null ){
				if( CheckImportUtil.isNum(list.get(i).getEduClass())){
					
				} else {
					error.add("第" + (i + 2) + "行中无效班级!");
				}
			}
			
			if( error.size() > 0 ){
				list.set(i, null);
				errorMsg.addAll(getSortDesc(error));
			}
		}
		
		students = getUnEmptyStudents(list);	/* 非空student */
		
		json.put(JsonMsg.RESULT, errorMsg.size() > 0 ? false : true);
		json.put(JsonMsg.MSG, getSortDesc(errorMsg));
		json.put("students", students);
		json.put("studentlist", list);
		
		return json;
	}
	
	/*
	 * Excel 与 机构学员校验
	 */
	private JSONObject validateDatas2Out( List<Student> list ){
		JSONObject json = new JSONObject();
		
		List<String> errorMsg  = new ArrayList<String>();		/* 错误信息 */
 		List<Student> students = new ArrayList<Student>(); 		/* 验证，去重后的student */
		
		Integer companyId = WebUtils.getCurrentCompanyId();
		List<Student> updateList = new ArrayList<Student>();
		StudentAll4CompanyVo allStudents = getCompanyAllStudents(companyId);
		for (int i = list.size(); i > 0; i--) {
			List<String> error = new ArrayList<String>();
			Student student = list.get(i-1);
			boolean flag=false;
			boolean flag1=false;
			if( student != null ){
				//每个手机号 必定对应一个账号
				if( student.getMobile() != null && allStudents.getMobiles().containsKey(student.getMobile())){
					if("1".equals(allStudents.getMobiles().get(student.getMobile()).getIsInSchool())){
						error.add("第" + (i-1 + 2) + "行中手机号已存在！");	
					}else{
						flag=true;
					}
				}
				if( student.getUsername() != null && allStudents.getUsernames().containsKey(student.getUsername()) ){
					if("1".equals(allStudents.getUsernames().get(student.getUsername()).getIsInSchool())){
						error.add("第" + (i-1 + 2) + "行中用户名已存在！");
					}else{
						flag=true;
					}
				}
				if( student.getIdentityId() != null && allStudents.getIdentityIds().containsKey(student.getIdentityId()) ){
					if("1".equals(allStudents.getIdentityIds().get(student.getIdentityId()).getIsInSchool())){
						error.add("第" + (i-1 + 2) + "行中身份证号已存在！");
					}
				}
				
			}
			if(flag || flag1){
				updateList.add(list.get(i-1));
				list.remove(i-1);
			}
			if(error.size() > 0){
				list.set(i, null);
				errorMsg.addAll(error);
			}
		}
		
		students = getUnEmptyStudents(list);	/* 非空student */
		
		json.put(JsonMsg.RESULT, errorMsg.size() > 0 ? false : true);
		json.put(JsonMsg.MSG, errorMsg);
		json.put("students", students);
		json.put("updateList", updateList);
		
		return json;
	}
	
	/*
	 * Excel 转成 Student
	 */
	private List<Student> excel2Student( String path ){
		Integer companyId = WebUtils.getCurrentCompanyId();
		
		List<List<String>> list = ImportExcl.read( path );	/* excel转list */
		
		List<Student> studentslist = new ArrayList<Student>();	/* 返回studentList初始化 */
		Student s = null;
		
		if ( list != null ) {
			for (int i = 1; i < list.size(); i++) {           /* 从第二行开始 （第一行表头）*/
				List<String> studentList = list.get(i);
				
				s = new Student();
				if (!"".equals(studentList.get(0)))  s.setMobile(studentList.get(0)); 
				if (!"".equals(studentList.get(1)))  s.setUsername(studentList.get(1)); 
				if (!"".equals(studentList.get(2)))  s.setName(studentList.get(2)); 
				if (!"".equals(studentList.get(3)))  {
					s.setIdentityTypeCode("ID_IDCARD");
					s.setIdentityId(studentList.get(3)); 
				}
				if (!"".equals(studentList.get(4)))  s.setEmail(studentList.get(4)); 
				if (!"".equals(studentList.get(5)))  s.setQq(studentList.get(5)); 
				if (!"".equals(studentList.get(6)))  s.setEmergencyContact(studentList.get(6)); 
				if (!"".equals(studentList.get(7)))  s.setEmergencyPhone(studentList.get(7)); 
				if (!"".equals(studentList.get(8))) { 
					s.setPassword(new Md5Hash(studentList.get(8)).toHex()); 
				} else {
					if ("".equals(studentList.get(0))) {
						s.setPassword(new Md5Hash("123456").toHex());
					} else {
						s.setPassword(new Md5Hash(studentList.get(0).substring(5, 11)).toHex());
					}
				}
				//if (!"".equals(studentList.get(9)))  s.setEduArea(studentList.get(9)); 
				if (!"".equals(studentList.get(9)))  s.setEduSchool(studentList.get(9)); 
				if (!"".equals(studentList.get(10)))  s.setEduStep(studentList.get(10)); 
				if (!"".equals(studentList.get(11)))  s.setEduYear(studentList.get(11)); 
				if (!"".equals(studentList.get(12)))  s.setEduClass(studentList.get(12)); 

				s.setCompanyId(companyId);
				s.setSchoolId(WebUtils.getCurrentSchoolId());
				s.setDeleteFlag(0);
				studentslist.add(s);
			}
		}
		return studentslist;
	}
	
	/*
	 * 机构注册方式  
	 */
	private String getCompanyRegisterFlag(){
		
		CompanyRegisterConfig crc = new CompanyRegisterConfig();
		crc.setCompanyId(WebUtils.getCurrentCompanyId());
		crc = this.companyRegisterConfigServiceImpl.queryByCompanyId(crc);
		
		if( crc.getMobileFlag() != null && crc.getMobileFlag() == 1 && crc.getUsernameFlag() != null && crc.getUsernameFlag() == 1 ){
			return "all";
		}
		if( crc.getMobileFlag() != null && crc.getMobileFlag() == 1 ){
			return "mobile";
		}
		if( crc.getUsernameFlag() != null && crc.getUsernameFlag() == 1 ){
			return "username";
		}
		
		return "mobile";	/* 默认手机注册 */
	}
	
	/*
	 * 获取非空student
	 */
	private List<Student> getUnEmptyStudents( List<Student> list ){
		List<Student> students = new ArrayList<Student>();
		for (Student student : list) {
			if( student != null ){
				students.add(student);
			}
		}
		return students;
	}
	
	/*
	 * list倒序输出
	 */
	private List<String> getSortDesc(List<String> list){
		List<String> unlist = new ArrayList<String>();
		
		for (int i = list.size()-1; i >= 0; i--) {
			unlist.add(list.get(i));
		}
		return unlist;
	}
	
	/*
	 * 机构所有学员
	 */
	private StudentAll4CompanyVo getCompanyAllStudents(Integer companyId){
		
		List<StudentImportVo> studentslist = this.studentServiceImpl.queryAllStudentsByCompanyId(companyId);
		
		StudentAll4CompanyVo allStudents = new StudentAll4CompanyVo();
		
		for (int i = 0; i < studentslist.size(); i++) {
			if( studentslist.get(i).getMobile() != null ){
				allStudents.getMobiles().put(studentslist.get(i).getMobile(), studentslist.get(i));
			}
			if( studentslist.get(i).getUsername() != null ){
				allStudents.getUsernames().put(studentslist.get(i).getUsername(), studentslist.get(i));
			}
			if( studentslist.get(i).getIdentityId() != null && "ID_IDCARD".equals(studentslist.get(i).getIdentityTypeCode()) ){
				allStudents.getIdentityIds().put(studentslist.get(i).getIdentityId(),studentslist.get(i));
			}
			
		}
		return allStudents;
	}
	
	/**
	 * 
	 * Class Name: StudentExcelImportController.java
	 * @Description: 批量导入学员
	 * @author dongshuai
	 * @date 2017年2月7日 下午12:01:56
	 * @modifier
	 * @modify-date 2017年2月7日 下午12:01:56
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping( value = "/insertMoreStudents", method = RequestMethod.POST )
	public JSONObject insertMoreStudents(String data,String groupOneId,String groupTwoId,String updateList,HttpServletRequest request){
		JSONObject json = new JSONObject();
		Integer userId = WebUtils.getCurrentUserId(request);
		
		/* 待导入学员 */
 		List<StudentImportVo> students = JSONObject.parseArray(data, com.yuxin.wx.vo.student.StudentImportVo.class);
 		List<StudentImportVo> updateListstudents = JSONObject.parseArray(updateList, com.yuxin.wx.vo.student.StudentImportVo.class);
 		
 		if("".equals(groupOneId)) groupOneId = null;
 		if("".equals(groupTwoId)) groupTwoId = null;
 		
		List<Integer> studentIds = this.studentServiceImpl.insertMoreStudents(students,updateListstudents,groupOneId,groupTwoId,userId);
		
		json.put(JsonMsg.RESULT, studentIds.size()>0?JsonMsg.SUCCESS:JsonMsg.ERROR);
		json.put("studentIds", studentIds);
		
		return json;
	}
	
	
	/**
	 * 
	 * Class Name: StudentExcelImportController.java
	 * @Description: 回显页面
	 * @author dongshuai
	 * @date 2017年2月8日 上午11:33:31
	 * @modifier
	 * @modify-date 2017年2月8日 上午11:33:31
	 * @version 1.0
	 * @param model
	 * @param stuMobiles
	 * @return
	 */
	@RequestMapping(value = "/queryData")
	public String importStudentData(Model model, String stuMobiles) {
		CompanyFunctionSet search = new CompanyFunctionSet();
		search.setFunctionCode("COMPANY_FUNCTION_COURSE");
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		CompanyFunctionSet cfs = companyFunctionSetServiceImpl.findCompanyUseCourse(search);
		if (cfs != null && "1".equals(cfs.getStatus())) {
			model.addAttribute("isFull", 1);
		} else {
			model.addAttribute("isFull", 0);
		}
		model.addAttribute("stuMobiles", stuMobiles);
		// 查询公司注册设置
		CompanyRegisterConfig crc = new CompanyRegisterConfig();
		crc.setCompanyId(WebUtils.getCurrentCompanyId());
		crc = companyRegisterConfigServiceImpl.queryByCompanyId(crc);
		if (null != crc && crc.getCloseFlag() == 1) {
			crc.setMobileFlag(1);
		}
		model.addAttribute("registConfig", crc);
		return "student/importStudentData";
	}
	
	/**
	 * 
	 * Class Name: StudentExcelImportController.java
	 * @Description: 导入/更新数据回显
	 * @author dongshuai
	 * @date 2017年2月8日 上午11:34:06
	 * @modifier
	 * @modify-date 2017年2月8日 上午11:34:06
	 * @version 1.0
	 * @param stusMobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryStudentsList")
	public List<StudentListVo> queryExportData(String stusMobile) {
		List<StudentListVo> pageFinder = studentServiceImpl.queryStudentsListByIds(stusMobile);
		return pageFinder;
	}
}
