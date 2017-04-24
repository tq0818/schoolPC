package com.yuxin.wx.controller.student;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyRegisterConfigService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.student.Student;
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
		
		StudentAll4CompanyVo allStudents = getCompanyAllStudents(companyId);
		
		for (int i = 0; i < list.size(); i++) {
			List<String> error = new ArrayList<String>();
			Student student = list.get(i);
			if( student != null ){
				if( student.getMobile() != null && allStudents.getMobiles().contains(student.getMobile()) ){
					error.add("第" + (i + 2) + "行中手机号已存在！");
				}
				if( student.getUsername() != null && allStudents.getUsernames().contains(student.getUsername()) ){
					error.add("第" + (i + 2) + "行中用户名已存在！");
				}
				if( student.getIdentityId() != null && allStudents.getIdentityIds().contains(student.getIdentityId()) ){
					error.add("第" + (i + 2) + "行中身份证号已存在！");
				}
				if( student.getEmail() != null && allStudents.getEmails().contains(student.getEmail()) ){
					error.add("第" + (i + 2) + "行中邮箱已存在！");
				}
				if( student.getQq() != null && allStudents.getQqs().contains(student.getQq()) ){
					error.add("第" + (i + 2) + "行中QQ已存在！");
				}
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
				if (!"".equals(studentList.get(1)))  s.setName(studentList.get(1)); 
				if (!"".equals(studentList.get(2)))  {
					s.setIdentityTypeCode("ID_IDCARD");
					s.setIdentityId(studentList.get(2)); 
				}
				if (!"".equals(studentList.get(3)))  s.setEmail(studentList.get(3)); 
				if (!"".equals(studentList.get(4)))  s.setQq(studentList.get(4)); 
				if (!"".equals(studentList.get(5)))  s.setEmergencyContact(studentList.get(5)); 
				if (!"".equals(studentList.get(6)))  s.setEmergencyPhone(studentList.get(6)); 
				if (!"".equals(studentList.get(7))) { 
					s.setPassword(new Md5Hash(studentList.get(7)).toHex()); 
				} else {
					if ("".equals(studentList.get(0))) {
						s.setPassword(new Md5Hash("111111").toHex());
					} else {
						s.setPassword(new Md5Hash(studentList.get(0).substring(5, 11)).toHex());
					}
				}
				if (!"".equals(studentList.get(8)))  s.setUsername(studentList.get(8)); 

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
				allStudents.getMobiles().add(studentslist.get(i).getMobile());
			}
			if( studentslist.get(i).getUsername() != null ){
				allStudents.getUsernames().add(studentslist.get(i).getUsername());
			}
			if( studentslist.get(i).getIdentityId() != null && "ID_IDCARD".equals(studentslist.get(i).getIdentityTypeCode()) ){
				allStudents.getIdentityIds().add(studentslist.get(i).getIdentityId());
			}
			if( studentslist.get(i).getEmail() != null ){
				allStudents.getEmails().add(studentslist.get(i).getEmail());
			}
			if(  studentslist.get(i).getQq() != null ){
				allStudents.getQqs().add(studentslist.get(i).getQq());
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
	public JSONObject insertMoreStudents(String data,String groupOneId,String groupTwoId,HttpServletRequest request){
		JSONObject json = new JSONObject();
		Integer userId = WebUtils.getCurrentUserId(request);
		
		/* 待导入学员 */
 		List<StudentImportVo> students = JSONObject.parseArray(data, com.yuxin.wx.vo.student.StudentImportVo.class);
 		
 		if("".equals(groupOneId)) groupOneId = null;
 		if("".equals(groupTwoId)) groupTwoId = null;
 		
		List<Integer> studentIds = this.studentServiceImpl.insertMoreStudents(students,groupOneId,groupTwoId,userId);
		
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
