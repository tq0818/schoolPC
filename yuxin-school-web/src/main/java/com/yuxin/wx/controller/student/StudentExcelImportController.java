package com.yuxin.wx.controller.student;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
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
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.classes.EduMasterClass;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.StringUtil;
import com.yuxin.wx.utils.CheckImportUtil;
import com.yuxin.wx.utils.FileUploadUtil;
import com.yuxin.wx.utils.ImportExcl;
import com.yuxin.wx.utils.ParameterUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.student.StudentAll4CompanyVo;
import com.yuxin.wx.vo.student.StudentImportVo;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.user.UsersAreaRelation;


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
    private IUsersService usersServiceImpl;
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
				List<Student> studentsList = excel2Student( content + fileName ,null);
				Map<Integer,Student> errorStudentMap=new HashMap<Integer,Student>();
				json2In  = validateDatas2In( studentsList , null,errorStudentMap);
				json2Out = validateDatas2Out( (List<Student>)json2In.get("studentlist"),errorStudentMap);
			} catch (Exception e) {
				json.put( JsonMsg.RESULT, JsonMsg.EXCEPTION );
				json.put( JsonMsg.MSG, TEMPLET_ERROR );
				e.printStackTrace();
			}
		
			json.put( "excelPath", content + fileName );
			//不管是否有错，都允许导入
			if( !(Boolean)json2In.get("result") || !(Boolean)json2Out.get("result") ){
				json.put( JsonMsg.RESULT, JsonMsg.ERROR);
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
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping( value = "/studentsValidateNo", method = RequestMethod.POST )
	public JSONObject studentsValidateNo( MultipartRequest multiPartRquest, HttpServletRequest request, HttpServletResponse response ) {
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
				List<Student> studentsList = excel2Student( content + fileName ,"1");
				Map<Integer,Student> errorStudentMap=new HashMap<Integer,Student>();
				json2In  = validateDatas2In( studentsList ,"1",errorStudentMap);
				json2Out = validateDatas2Out( (List<Student>)json2In.get("studentlist"),errorStudentMap);
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
	private JSONObject validateDatas2In( List<Student> list ,String biaoji,Map<Integer,Student> errorStudentMap){
		JSONObject json = new JSONObject();
		List<String> errorMsg  = new ArrayList<String>();		/* 错误信息 */
 		List<Student> students = new ArrayList<Student>(); 		/* 验证，去重后的student */
		Map <String, String> eduAreaMap=new HashMap<String, String>();
		Map <String, String> eduSchoolMap=new HashMap<String, String>();
		Map <String, String> dictMap=new HashMap<String, String>();
		if(list==null||list.size()<1){
			errorMsg.add("导入模板数据不能为空");
		}
		if(null==biaoji){
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
			Map<String,Object>isClassHava=new HashMap<String,Object>();//存放对比班级
			Subject subject = SecurityUtils.getSubject();
			if (subject.hasRole("班主任") ){
				EduMasterClass ets =new EduMasterClass();
				Users users=WebUtils.getCurrentUser();
				if(users!=null)
					ets.setUserId(String.valueOf(users.getId()));
	    		List<EduMasterClass> eduMasterClassList=studentServiceImpl.findClassByTeacherId(ets,WebUtils.getCurrentCompany().getEduAreaSchool());
	    		if(eduMasterClassList!=null&&eduMasterClassList.size()>0){
	    			EduMasterClass eduMasterClass=eduMasterClassList.get(0);
	    			if(eduMasterClass!=null){
	    				if("STEP_01".equals(eduMasterClass.getEduStep())){
	    					eduMasterClass.setEduStep("小学");
	    				}else if("STEP_02".equals(eduMasterClass.getEduStep())){
	    					eduMasterClass.setEduStep("初中中学");
	    				}else if("STEP_03".equals(eduMasterClass.getEduStep())){
	    					eduMasterClass.setEduStep("高中中学");
	    				}
	    				isClassHava.put(eduMasterClass.getEduSchool()+"_"+eduMasterClass.getEduStep()+"_"+eduMasterClass.getEduYear()+"_"+eduMasterClass.getEduClass(),eduMasterClass);
	    			}
	    		}
			}
			if("0".equals(isArea)){
				isAreaHava.putAll(eduAreaMap);
				Users users=WebUtils.getCurrentUser();
				if (subject.hasRole("学校负责人")){
					UsersAreaRelation uersAreaRelation=usersServiceImpl.findUsersAreaRelation(users.getId());
					String eduValue=eduSchoolMap.get(uersAreaRelation.getEduSchool());
					isSchoolHava.put(uersAreaRelation.getEduSchool(),eduValue);
				}else{
					isSchoolHava.putAll(eduSchoolMap);
				}	
			}else if("1".equals(isArea)){
				//学区  区域
				String eduArea=WebUtils.getCurrentCompany().getEduAreaSchool();
				if(eduAreaMap.containsKey(eduArea)){
					isAreaHava.put(eduAreaMap.get(eduArea),eduArea);
				}
				if (subject.hasRole("学校负责人")){
					Users users=WebUtils.getCurrentUser();
					UsersAreaRelation uersAreaRelation=usersServiceImpl.findUsersAreaRelation(users.getId());
					String eduValue=eduSchoolMap.get(uersAreaRelation.getEduSchool());
					isSchoolHava.put(uersAreaRelation.getEduSchool(),eduValue);
				}else{
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
//						/* 紧急联系人电话 */
//						if( list.get(i).getEmergencyPhone() == null ){
//							error.add("第" + (i + 2) + "行中紧急联系人手机号不能为空!");
//						} else if ( !ParameterUtil.isMobilePhone(list.get(i).getEmergencyPhone()) ){
//							error.add("第" + (i + 2) + "行中紧急联系人手机号不正确!");
//						}
//						/* 紧急联系人姓名 */
//						if( list.get(i).getEmergencyContact() == null ){
//							error.add("第" + (i + 2) + "行中紧急联系人不能为空!");
//						}
						/* 入学年份 */
						if( list.get(i).getEduYear() == null ){
							error.add("第" + (i + 2) + "行中入学年份不能为空!");
						}
						/* 班级 */
						if( list.get(i).getEduClass() == null ){
							error.add("第" + (i + 2) + "行中班级不能为空!");
						}
						/* 用户名 */
//						if( list.get(i).getUsername() != null && !ParameterUtil.isUserName(list.get(i).getUsername()) ){
//							error.add("第" + (i + 2) + "行中用户名不正确!");
//						}
						/* 紧急联系人电话 */
//						if( list.get(i).getEmergencyPhone() == null ){
//							error.add("第" + (i + 2) + "行中紧急联系人手机号不能为空!");
//						} else if ( !ParameterUtil.isMobilePhone(list.get(i).getEmergencyPhone()) ){
//							error.add("第" + (i + 2) + "行中紧急联系人手机号不正确!");
//						}
//						/* 紧急联系人姓名 */
//						if( list.get(i).getEmergencyContact() == null ){
//							error.add("第" + (i + 2) + "行中紧急联系人不能为空!");
//						}
						/* 入学年份 */
						if( list.get(i).getEduYear() == null ){
							error.add("第" + (i + 2) + "行中入学年份不能为空!");
						}
						/* 班级 */
						if( list.get(i).getEduClass() == null ){
							error.add("第" + (i + 2) + "行中入班级不能为空!");
						}
						break;
					case "username":
						/* 手机号 */
						if( list.get(i).getMobile() != null && !ParameterUtil.isMobilePhone(list.get(i).getMobile())){
							error.add("第" + (i + 2) + "行中手机号不正确!");
						}
						/* 用户名 */
//						if( list.get(i).getUsername() == null){
//							error.add("第" + (i + 2) + "行中用户名不能为空!");
//						} else if ( !ParameterUtil.isUserName(list.get(i).getUsername()) ){
//							error.add("第" + (i + 2) + "行中用户名不正确!");
//						}
//						/* 紧急联系人电话 */
//						if( list.get(i).getEmergencyPhone() == null ){
//							error.add("第" + (i + 2) + "行中紧急联系人手机号不能为空!");
//						} else if ( !ParameterUtil.isMobilePhone(list.get(i).getEmergencyPhone()) ){
//							error.add("第" + (i + 2) + "行中紧急联系人手机号不正确!");
//						}
//						/* 紧急联系人姓名 */
//						if( list.get(i).getEmergencyContact() == null ){
//							error.add("第" + (i + 2) + "行中紧急联系人不能为空!");
//						}
						/* 入学年份 */
						if( list.get(i).getEduYear() == null ){
							error.add("第" + (i + 2) + "行中入学年份不能为空!");
						}
						/* 班级 */
						if( list.get(i).getEduClass() == null ){
							error.add("第" + (i + 2) + "行中入班级不能为空!");
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
//						if( list.get(i).getUsername() == null){
//							error.add("第" + (i + 2) + "行中用户名不能为空!");
//						} else if ( !ParameterUtil.isUserName(list.get(i).getUsername()) ){
//							error.add("第" + (i + 2) + "行中用户名不正确!");
//						}
						/* 紧急联系人电话 */
//						if( list.get(i).getEmergencyPhone() == null ){
//							error.add("第" + (i + 2) + "行中紧急联系人手机号不能为空!");
//						} else if ( !ParameterUtil.isMobilePhone(list.get(i).getEmergencyPhone()) ){
//							error.add("第" + (i + 2) + "行中紧急联系人手机号不正确!");
//						}
						/* 紧急联系人姓名 */
//						if( list.get(i).getEmergencyContact() == null ){
//							error.add("第" + (i + 2) + "行中紧急联系人不能为空!");
//						}
						/* 入学年份 */
						if( list.get(i).getEduYear() == null ){
							error.add("第" + (i + 2) + "行中入学年份不能为空!");
						}
						/* 班级 */
						if( list.get(i).getEduClass() == null ){
							error.add("第" + (i + 2) + "行中班级不能为空!");
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
//				if( list.get(i).getIdentityId() != null ){
//					if( ParameterUtil.isNum(list.get(i).getIdentityId()) && ( list.get(i).getIdentityId().length() == 15 || list.get(i).getIdentityId().length() == 18 ) ){
//						
//					} else {
//						error.add("第" + (i + 2) + "行中身份证号不正确!");
//					}
//				}
				/* 邮箱 */
//				if(	list.get(i).getEmail() != null && !ParameterUtil.isEmail(list.get(i).getEmail()) ){
//					error.add("第" + (i + 2) + "行中邮箱不正确!");
//				}
				/* QQ */
//				if( list.get(i).getQq() != null ){
//					if( ParameterUtil.isNum(list.get(i).getQq()) || list.get(i).getQq().length() > 10 ){
//						
//					} else {
//						error.add("第" + (i + 2) + "行中QQ不正确!");
//					}
//				}
				/* 紧急联系电话 */
//				if( list.get(i).getEmergencyPhone() != null && !ParameterUtil.isMobilePhone(list.get(i).getEmergencyPhone()) ){
//					error.add("第" + (i + 2) + "行中紧急联系电话不正确!");
//				}
				for (int j = 0; j < list.size(); j++) {
					if ( list.get(i) != null && list.get(j) != null) {
						if( i == j ) continue;
						/* 去重 */
						/* 手机号 */
						if ( list.get(i).getMobile() != null && list.get(j).getMobile() != null && list.get(i).getMobile().equals(list.get(j).getMobile()) ) {
							error.add("第" + (i + 2) + "行中手机号与第" + (j + 2) + "行相同！");
						}
						/* 用户名 */
//						if ( list.get(i).getUsername() != null && list.get(j).getUsername() != null && list.get(i).getUsername().equals(list.get(j).getUsername()) ) {
//							error.add("第" + (i + 2) + "行中用户名与第" + (j + 2) + "行相同！");
//						}
					}
				}
				
//				//学区
//				if( list.get(i).getEduArea() != null && !eduAreaMap.containsValue(list.get(i).getEduArea())){
//					error.add("第" + (i + 2) + "行中区域不存在!");
//				}else{
//					if(list.get(i).getEduArea() != null && !isAreaHava.containsKey(list.get(i).getEduArea())){
//						error.add("第" + (i + 2) + "行中无权输入该区域!");
//					}
//				}
				//学校
				Student student=list.get(i);
				if(StringUtils.isBlank(list.get(i).getEduSchool())){
					error.add("第" + (i + 2) + "行中学校不能为空!");
				}else if( list.get(i).getEduSchool() != null && !eduSchoolMap.containsKey(student.getEduSchool())){
					error.add("第" + (i + 2) + "行中学校不存在!");
				}else{
					if(list.get(i).getEduSchool() != null && !isSchoolHava.containsKey(student.getEduSchool())){
						error.add("第" + (i + 2) + "行中无权输入该学校!");
					}else if(subject.hasRole("班主任")&&!isClassHava.containsKey(student.getEduSchool()+"_"+student.getEduStep()+"_"+student.getEduYear()+"_"+student.getEduClass())){
						error.add("第" + (i + 2) + "行中无权输入该班级!");
					}else{
						//反推  给一条信息添加学区编号
						int parentId=0;
						for(int k=0 ;k<dictAreaList.size();k++){
							if(StringUtils.isNotEmpty(list.get(i).getEduSchool())&&list.get(i).getEduSchool().equals(dictAreaList.get(k).getItemCode())){
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
					if( CheckImportUtil.isNum(list.get(i).getEduYear())&&list.get(i).getEduYear().length()==4 ){
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
					errorStudentMap.put(i,list.get(i));
					list.set(i, null);
					errorMsg.addAll(getSortDesc(error));
				}
			}	
		}else{
			
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
						/* 紧急联系人电话 */
//						if( list.get(i).getEmergencyPhone() == null ){
//							error.add("第" + (i + 2) + "行中紧急联系人手机号不能为空!");
//						} else if ( !ParameterUtil.isMobilePhone(list.get(i).getEmergencyPhone()) ){
//							error.add("第" + (i + 2) + "行中紧急联系人手机号不正确!");
//						}
//						/* 紧急联系人姓名 */
//						if( list.get(i).getEmergencyContact() == null ){
//							error.add("第" + (i + 2) + "行中紧急联系人不能为空!");
//						}
						/* 入学年份 */
						if( list.get(i).getEduYear() == null ){
							error.add("第" + (i + 2) + "行中入学年份不能为空!");
						}
						
						/* 班级 */
						if( list.get(i).getEduClass() == null ){
							error.add("第" + (i + 2) + "行中班级不能为空!");
						}
						/* 用户名 */
//						if( list.get(i).getUsername() != null && !ParameterUtil.isUserName(list.get(i).getUsername()) ){
//							error.add("第" + (i + 2) + "行中用户名不正确!");
//						}
						break;
					case "username":
						/* 手机号 */
						if( list.get(i).getMobile() != null && !ParameterUtil.isMobilePhone(list.get(i).getMobile())){
							error.add("第" + (i + 2) + "行中手机号不正确!");
						}
						/* 用户名 */
//						if( list.get(i).getUsername() == null){
//							error.add("第" + (i + 2) + "行中用户名不能为空!");
//						} else if ( !ParameterUtil.isUserName(list.get(i).getUsername()) ){
//							error.add("第" + (i + 2) + "行中用户名不正确!");
//						}
//						/* 紧急联系人电话 */
//						if( list.get(i).getEmergencyPhone() == null ){
//							error.add("第" + (i + 2) + "行中紧急联系人手机号不能为空!");
//						} else if ( !ParameterUtil.isMobilePhone(list.get(i).getEmergencyPhone()) ){
//							error.add("第" + (i + 2) + "行中紧急联系人手机号不正确!");
//						}
//						/* 紧急联系人姓名 */
//						if( list.get(i).getEmergencyContact() == null ){
//							error.add("第" + (i + 2) + "行中紧急联系人不能为空!");
//						}
						/* 入学年份 */
						if( list.get(i).getEduYear() == null ){
							error.add("第" + (i + 2) + "行中入学年份不能为空!");
						}
						/* 班级 */
						if( list.get(i).getEduClass() == null ){
							error.add("第" + (i + 2) + "行中班级不能为空!");
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
//						if( list.get(i).getUsername() == null){
//							error.add("第" + (i + 2) + "行中用户名不能为空!");
//						} else if ( !ParameterUtil.isUserName(list.get(i).getUsername()) ){
//							error.add("第" + (i + 2) + "行中用户名不正确!");
//						}
//						/* 紧急联系人电话 */
//						if( list.get(i).getEmergencyPhone() == null ){
//							error.add("第" + (i + 2) + "行中紧急联系人手机号不能为空!");
//						} else if ( !ParameterUtil.isMobilePhone(list.get(i).getEmergencyPhone()) ){
//							error.add("第" + (i + 2) + "行中紧急联系人手机号不正确!");
//						}
//						/* 紧急联系人姓名 */
//						if( list.get(i).getEmergencyContact() == null ){
//							error.add("第" + (i + 2) + "行中紧急联系人不能为空!");
//						}
						/* 入学年份 */
						if( list.get(i).getEduYear() == null ){
							error.add("第" + (i + 2) + "行中入学年份不能为空!");
						}
						/* 班级 */
						if( list.get(i).getEduClass() == null ){
							error.add("第" + (i + 2) + "行中班级不能为空!");
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
//				if( list.get(i).getIdentityId() != null ){
//					if( ParameterUtil.isNum(list.get(i).getIdentityId()) && ( list.get(i).getIdentityId().length() == 15 || list.get(i).getIdentityId().length() == 18 ) ){
//						
//					} else {
//						error.add("第" + (i + 2) + "行中身份证号不正确!");
//					}
//				}
//				/* 邮箱 */
//				if(	list.get(i).getEmail() != null && !ParameterUtil.isEmail(list.get(i).getEmail()) ){
//					error.add("第" + (i + 2) + "行中邮箱不正确!");
//				}
//				/* QQ */
//				if( list.get(i).getQq() != null ){
//					if( ParameterUtil.isNum(list.get(i).getQq()) || list.get(i).getQq().length() > 10 ){
//						
//					} else {
//						error.add("第" + (i + 2) + "行中QQ不正确!");
//					}
//				}
//				/* 紧急联系电话 */
//				if( list.get(i).getEmergencyPhone() != null && !ParameterUtil.isMobilePhone(list.get(i).getEmergencyPhone()) ){
//					error.add("第" + (i + 2) + "行中紧急联系电话不正确!");
//				}
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

				//学校
				if( list.get(i).getEduSchool() != null && !eduSchoolMap.containsKey(list.get(i).getEduSchool())){
					error.add("第" + (i + 2) + "行中学校不存在!");
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
					errorStudentMap.put(i,list.get(i));
					list.set(i, null);
					errorMsg.addAll(getSortDesc(error));
				}
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
	private JSONObject validateDatas2Out( List<Student> list,Map<Integer,Student> errorStudentMap){
		JSONObject json = new JSONObject();
		
		List<String> errorMsg  = new ArrayList<String>();		/* 错误信息 */
 		List<Student> students = new ArrayList<Student>(); 		/* 验证，去重后的student */
		
		Integer companyId = WebUtils.getCurrentCompanyId();
		StudentAll4CompanyVo allStudents = getCompanyAllStudents(companyId);
		for (int i = list.size()-1; i >=0; i--) {
			List<String> error = new ArrayList<String>();
			Student student = list.get(i);
			boolean flag=false;
			if( student != null ){
				//每个手机号 必定对应一个账号
				if( student.getMobile() != null && allStudents.getMobiles().containsKey(student.getMobile())){
					if("1".equals(allStudents.getMobiles().get(student.getMobile()).getIsInSchool())){
						error.add("第" + (i + 2) + "行中手机号已存在！");	
					}else{
						flag=true;
					}
				}
//				if( student.getUsername() != null && allStudents.getUsernames().containsKey(student.getUsername()) ){
//					if("1".equals(allStudents.getUsernames().get(student.getUsername()).getIsInSchool())){
//						error.add("第" + (i + 2) + "行中用户名已存在！");
//					}else{
//						flag=true;
//					}
//				}
//				if( student.getIdentityId() != null && allStudents.getIdentityIds().containsKey(student.getIdentityId()) ){
//					if("1".equals(allStudents.getIdentityIds().get(student.getIdentityId()).getIsInSchool())){
//						error.add("第" + (i + 2) + "行中身份证号已存在！");
//					}
//				}
				
			}else{
				Student errorStudent=errorStudentMap.get(i);
				if( errorStudent.getMobile() != null && allStudents.getMobiles().containsKey(errorStudent.getMobile())){
					if("1".equals(allStudents.getMobiles().get(errorStudent.getMobile()).getIsInSchool())){
						error.add("第" + (i + 2) + "行中手机号已存在！");	
					}
				}
			}
			if(null!=student && (flag)){
				student.setUpdateB(1);
			}else if(null!=student && (!flag)){
				student.setUpdateB(0);
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
//		json.put("updateList", updateList);
		return json;
	}
	
	/*
	 * Excel 转成 Student
	 */
	private List<Student> excel2Student( String path ,String biaoshi){
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company=WebUtils.getCurrentCompany();
		
		List<List<String>> list = ImportExcl.read(path);	/* excel转list */
		
		List<Student> studentslist = new ArrayList<Student>();	/* 返回studentList初始化 */
		Student s = null;
		
		if ( list != null ) {
			for (int i = 1; i < list.size(); i++) {           /* 从第二行开始 （第一行表头）*/
				List<String> studentList = list.get(i);
				s = new Student();
				if (!"".equals(studentList.get(0)))  s.setMobile(studentList.get(0)); 
				//if (!"".equals(studentList.get(1)))  s.setUsername(studentList.get(1)); 
				if (!"".equals(studentList.get(1)))  s.setName(studentList.get(1)); 
//				if (!"".equals(studentList.get(3)))  {
//					s.setIdentityTypeCode("ID_IDCARD");
//					s.setIdentityId(studentList.get(3)); 
//				}
				//if (!"".equals(studentList.get(4)))  s.setEmail(studentList.get(4)); 
				//if (!"".equals(studentList.get(5)))  s.setQq(studentList.get(5)); 
				//if (!"".equals(studentList.get(6)))  s.setEmergencyContact(studentList.get(6)); 
				//if (!"".equals(studentList.get(7)))  s.setEmergencyPhone(studentList.get(7)); 
				if (!"".equals(studentList.get(0))) { 
					s.setPassword(new Md5Hash(studentList.get(0).substring(studentList.get(0).length()-6)).toHex()); 
				} else {
					if ("".equals(studentList.get(0))) {
						s.setPassword(new Md5Hash("123456").toHex());
					} else {
						s.setPassword(new Md5Hash(studentList.get(0).substring(5, 11)).toHex());
					}
				}
				//if (!"".equals(studentList.get(9)))  s.setEduArea(studentList.get(9)); 
				if (!"".equals(studentList.get(2)))  s.setEduStep(studentList.get(2)); 
				if (!"".equals(studentList.get(3)))  s.setEduYear(studentList.get(3)); 
				if (!"".equals(studentList.get(4)))  s.setEduClass(studentList.get(4)); 
				if (studentList.size()>5){
					s.setEduSchool(studentList.get(5)); 
				}else{
					Users users=WebUtils.getCurrentUser();
					Subject subject = SecurityUtils.getSubject();
					if (subject.hasRole("学校负责人")&&("0".equals(company.getIsArea())||"1".equals(company.getIsArea()))){
						UsersAreaRelation uersAreaRelation=usersServiceImpl.findUsersAreaRelation(users.getId());
						s.setEduSchool(uersAreaRelation.getEduSchool());
					}else{
						s.setEduSchool(company.getEduAreaSchool());
					}
				}
				s.setCompanyId(companyId);
				s.setSchoolId(WebUtils.getCurrentSchoolId());
				s.setDeleteFlag(0);
				if(null == biaoshi){
					s.setIsInSchool(1);
				}else{
					s.setIsInSchool(0);
				}
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
		
//		List<StudentImportVo> studentslist = this.studentServiceImpl.queryAllStudentsByCompanyId(companyId);

		List<String> students = this.studentServiceImpl.queryAllStudents(companyId);
		StudentAll4CompanyVo allStudents = new StudentAll4CompanyVo();
		for(String s :students){
			StudentImportVo student = new StudentImportVo();
			if(StringUtils.isNotEmpty(s)){
				student.setMobile(s.split("_")[0]);
				student.setIsInSchool(s.split("_")[1]);
				allStudents.getMobiles().put(s.split("_")[0],student);
			}
		}

		/*for (int i = 0; i < studentslist.size(); i++) {
			if( studentslist.get(i).getMobile() != null ){
				allStudents.getMobiles().put(studentslist.get(i).getMobile(), studentslist.get(i));
			}
			if( studentslist.get(i).getUsername() != null ){
				allStudents.getUsernames().put(studentslist.get(i).getUsername(), studentslist.get(i));
			}
			if( studentslist.get(i).getIdentityId() != null && "ID_IDCARD".equals(studentslist.get(i).getIdentityTypeCode()) ){
				allStudents.getIdentityIds().put(studentslist.get(i).getIdentityId(),studentslist.get(i));
			}
			
		}*/
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
 		//List<StudentImportVo> updateListstudents = JSONObject.parseArray(updateList, com.yuxin.wx.vo.student.StudentImportVo.class);
 		
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
	public String importStudentData(Model model, String stuMobiles,String sourceFromTj) {
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
		model.addAttribute("sourceFromTj",sourceFromTj);
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
