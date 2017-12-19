package com.yuxin.wx.student.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.company.mapper.CompanyRegisterConfigMapper;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.student.mapper.StudentMapper;
import com.yuxin.wx.student.mapper.StudentPayMasterMapper;
import com.yuxin.wx.system.mapper.SysConfigSchoolMapper;
import com.yuxin.wx.user.mapper.UsersFrontMapper;
import com.yuxin.wx.user.mapper.UsersLoginSessionMapper;
import com.yuxin.wx.util.ParameterUtil;
import com.yuxin.wx.vo.student.SelectStudentOrUsersfrontVo;
import com.yuxin.wx.vo.student.StuVo;
import com.yuxin.wx.vo.student.StudentClassLeanDetailVo;
import com.yuxin.wx.vo.student.StudentClassLeanRecordVo;
import com.yuxin.wx.vo.student.StudentImportVo;
import com.yuxin.wx.vo.student.StudentLessTimeVo;
import com.yuxin.wx.vo.student.StudentListDataVo;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.student.StudentTiKuExcipseVo;
import com.yuxin.wx.vo.student.StudentTiKuOrSubjectVo;
import com.yuxin.wx.vo.student.StudentVo;
import com.yuxin.wx.vo.user.UsersFrontVo;

/**
 * Service Implementation:Student
 * 
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class StudentServiceImpl extends BaseServiceImpl implements IStudentService {
	
	Log log_student = LogFactory.getLog("student");
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private UsersFrontMapper usersFrontMapper;
	@Autowired
	private StudentPayMasterMapper studentPayMasterMapper;
	@Autowired
	private UsersLoginSessionMapper usersLoginSessionMapper;
	@Autowired
	private SysConfigSchoolMapper sysConfigSchoolMapper;
	@Autowired
	private CompanyRegisterConfigMapper companyRegisterConfigMapper;


	/**
	 * 
	 * @Title: saveStudent
	 * @Description: 新增Student
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by wangzx
	 */
	@Override
	public void insert(Student student) {
		studentMapper.insert(student);
	}

	/**
	 * 
	 * @Title: batchSaveStudent
	 * @Description: 批量新增Student
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by wangzx
	 */
	@Override
	public void batchInsert(List<Student> students) {
		if (students != null && !students.isEmpty()) {
			studentMapper.batchInsert(students);
		}
	}

	/**
	 * 
	 * @Title: updateStudent
	 * @Description: 编辑Student
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by wangzx
	 */
	@Override
	public void update(Student student) {
		studentMapper.update(student);
	}
	
	/**
	 * 
	 * @Title: updateStudent
	 * @Description: 编辑Student
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by wangzx
	 */
	@Override
	public void updateByMobile(Student student) {
		studentMapper.updateByMobile(student);
	}

	/**
	 * 
	 * @Title: deleteStudentById
	 * @Description: 根据id删除Student
	 * @param id
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by wangzx
	 */
	@Override
	public void deleteStudentById(Integer id) {
		studentMapper.deleteById(id);
	}

	/**
	 * 
	 * @Title: deleteStudentByIds
	 * @Description: 根据id批量删除Student
	 * @param ids
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by wangzx
	 */
	@Override
	public void deleteStudentByIds(Integer[] ids) {
		studentMapper.deleteByIds(ids);
	}

	/**
	 * 
	 * @Title: findStudentById
	 * @Description: 根据id查询
	 * @param id
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by wangzx
	 */
	@Override
	public Student findStudentById(Integer id) {
		Student stu=studentMapper.findById(id);
		if(null!=stu && null!=stu.getUserId()){
			UsersFront u=usersFrontMapper.findById(stu.getUserId());
			if(null!=u && null!=u.getUsername()){
				stu.setUsername(u.getUsername());
			}
		}
		return stu;
	}

	/**
	 * 
	 * @Title: findStudentByPage
	 * @Description: 分页查询
	 * @return
	 * @return List<Student> 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by wangzx
	 */
	@Override
	public PageFinder<Student> findStudentByPage(Student search) {

		List<Student> al = studentMapper.queryList(search);
		Integer rowCount = studentMapper.queryCount(search);
		PageFinder<Student> pf = new PageFinder<Student>(search.getPage(),
				search.getPageSize(), rowCount, al);
		return pf;
	}

	/**
	 * 
	 * @Title: findStudentList
	 * @Description: 分页查询
	 * @return
	 * @return List<Student> 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by wangzx
	 */
	@Override
	public List<Student> findStudentList(Student search) {
		Integer totalRecords = studentMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return studentMapper.queryList(search);
	}

	/**
	 * 
	 * @Title: findStudentList
	 * @Description: 检查用户信息是否重复
	 * @return
	 * @return List<Student> 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by wangzx
	 */
	@Override
	public List<Student> checkUserInfo(Student search) {
		return studentMapper.queryList(search);
	}

	/**
	 * 
	 * @Title: findStudentByPage
	 * @Description: 根据订单查学员
	 * @return
	 * @return List<StudentVo> 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by chopin
	 */
	@Override
	public PageFinder<StudentVo> findPageListByPayment(StudentVo search) {
		List<StudentVo> list = studentMapper.queryListByPayment(search);
		int rowCount = studentMapper.pageCount2(search);
		PageFinder<StudentVo> pf = new PageFinder<StudentVo>(search.getPage(),
				search.getPageSize(), rowCount, list);
		return pf;
	}
	
	@Override
	public PageFinder<StudentVo> findPageListByPaymentNew(StudentVo search) {
		List<StudentVo> list = studentMapper.queryListByPaymentNew(search);
		int rowCount = studentMapper.pageCount2New(search);
		PageFinder<StudentVo> pf = new PageFinder<StudentVo>(search.getPage(),
				search.getPageSize(), rowCount, list);
		return pf;
	}

	/**
	 * 
	 * @Title: findStudentByPage
	 * @Description: 根据订单查学员
	 * @return
	 * @return List<StudentVo> 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by chopin
	 */
	@Override
	public List<StudentVo> findListByPayment(StudentVo search) {
		return studentMapper.findListByPayment(search);
	}

	/**
	 * 
	 * @Title: findStudentByPage
	 * @Description: 根据班型查学员
	 * @return
	 * @return List<StudentVo> 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by chopin
	 */
	@Override
	public List<StudentVo> findListByClassType(StudentVo search) {
		return studentMapper.findListByPayment2(search);
	}

	/**
	 * 
	 * @Title: findStudentByPage
	 * @Description: 根据班型查学员
	 * @return
	 * @return List<StudentVo> 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by chopin
	 */
	@Override
	public List<StudentVo> findListByClassNo(StudentVo search) {
		return studentMapper.queryListByPayment3(search);
	}

	/**
	 * 
	 * @Title: findStudentByPage
	 * @Description: 根据班型查学员
	 * @return
	 * @return List<StudentVo> 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by chopin
	 */
	@Override
	public PageFinder<StudentVo> findPageListByClassType(StudentVo search) {
		List<StudentVo> list = studentMapper.queryListByPayment2(search);
		int rowCount = studentMapper.pageCount3(search);
		PageFinder<StudentVo> pf = new PageFinder<StudentVo>(search.getPage(),
				search.getPageSize(), rowCount, list);
		return pf;
	}

	/**
	 * 
	 * @Title: findStudentByPage
	 * @Description: 根据班型查学员
	 * @return
	 * @return List<StudentVo> 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by chopin
	 */
	@Override
	public PageFinder<StudentVo> findPageListByClassNo(StudentVo search) {
		List<StudentVo> list = studentMapper.queryListByPayment3(search);
		int rowCount = studentMapper.pageCount4(search);
		PageFinder<StudentVo> pf = new PageFinder<StudentVo>(search.getPage(),
				search.getPageSize(), rowCount, list);
		return pf;
	}

	/**
	 * 
	 * @Title: findStudentByPage
	 * @Description: 根据姓名证件号查学员
	 * @return
	 * @return List<StudentVo> 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by chopin
	 */
	@Override
	public Student findByCode(Student search) {
		return studentMapper.queryByCode(search);
	}

	/**
	 * 
	 * @Title: findStudentByPage
	 * @Description: 根据姓名证件号查学员
	 * @return
	 * @return List<StudentVo> 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by chopin
	 */
	@Override
	public Student findByCode2(Map<String, String> map) {
		return studentMapper.queryByCode2(map);
	}

	@Override
	public Student findMessageByMobile(String mobile, Integer companyId,
			Integer schoolId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", mobile);
		map.put("companyId", companyId);
		map.put("schoolId", schoolId);
		return studentMapper.findMessageByMobile(map);
	}

	/**
	 * 
	 * @Title: findStudentByPage
	 * @Description: 根据手机号查学员
	 * @return
	 * @return List<StudentVo> 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-5
	 * @user by chopin
	 */
	@Override
	public Student findByMobile(Student search) {
		Student stu=studentMapper.queryByMobile(search);
		if(null!=stu && null!=stu.getUserId()){
			UsersFront u=usersFrontMapper.findById(stu.getUserId());
			if(null!=u && null!=u.getUsername()){
				stu.setUsername(u.getUsername());
			}
		}
		return stu;
	}

	@Override
	public Integer findCountBySchoolId(Integer schoolId) {
		// TODO Auto-generated method stub
		return studentMapper.findCountBySchoolId(schoolId);
	}

	@Override
	public List<StuVo> findStuByNoId(StuVo stuVo) {
		// TODO Auto-generated method stub
		return studentMapper.findStuByNoId(stuVo);
	}

	@Override
	public Integer findCountByNoId(StuVo stuVo) {
		// TODO Auto-generated method stub
		return studentMapper.findCountByNoId(stuVo);
	}

	@Override
	public List<Student> findByPayMaster(
			CompanyStudentMessage companyStudentMessage) {
		// TODO Auto-generated method stub
		return studentMapper.findByPayMaster(companyStudentMessage);
	}

	@Override
	public List<Student> findByPaySlave(
			CompanyStudentMessage companyStudentMessage) {
		// TODO Auto-generated method stub
		return studentMapper.findByPaySlave(companyStudentMessage);
	}

	@Override
	public Student findByPhone(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return studentMapper.findByPhone(param);
	}

	@Override
	public Integer findClassMore(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return studentMapper.findClassMore(param);
	}

	@Override
	public PageFinder2<StudentListVo> findStudentsList(StudentListVo search) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("commodityType", "COMMODITY_CLASS");
		List<StudentListVo> data = studentMapper.queryStudentsList(search);
		for(StudentListVo stus:data){
		     if(null!=stus && null!=stus.getPaymaterCount() && stus.getPaymaterCount()>0){//已报名学员
		    	 map.put("stuId", stus.getId());
		    	 List<StudentPayMaster> pay=studentPayMasterMapper.findpayIdByStudentsId(map);
		    	 //代报考
		    	 for(StudentPayMaster spm:pay){
		    		 if(null!=spm&&null!=spm.getIsAgent()&&!"".equals(spm.getIsAgent())&&"1".equals(spm.getIsAgent())){
		    			 stus.setIsAgent(spm.getIsAgent());
		    			 break;
		    		 }
		    	 }
		    	 //分期,补费
		    	 for(StudentPayMaster spm:pay){
		    		 if(null!=spm && null!=spm.getPayStatusCode() && "ORDER_PART_PAY".equals(spm.getPayStatusCode())){
		    			 stus.setIspay("1");
		    			 break;
		    		 }
		    	 }
		    	 stus.setAgentFlag(search.getAgentFlag());
		     }
		}
		Integer count = studentMapper.queryStudentsListCount(search);
		PageFinder2<StudentListVo> pageFinder = new PageFinder2<StudentListVo>(
				search.getPage(), search.getPageSize(), count, data);
		return pageFinder;
	}
	@Override
	public PageFinder2<StudentListVo> findNewStudentsList(StudentListVo search) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("commodityType", "COMMODITY_CLASS");
		List<StudentListVo> data = studentMapper.queryNewStudentsList(search);
		for(StudentListVo stus:data){
			if(null!=stus && null!=stus.getPaymaterCount() && stus.getPaymaterCount()>0){//已报名学员
				map.put("stuId", stus.getId());
				List<StudentPayMaster> pay=studentPayMasterMapper.findpayIdByStudentsId(map);
				//代报考
				for(StudentPayMaster spm:pay){
					if(null!=spm&&null!=spm.getIsAgent()&&!"".equals(spm.getIsAgent())&&"1".equals(spm.getIsAgent())){
						stus.setIsAgent(spm.getIsAgent());
						break;
					}
				}
				//分期,补费
				for(StudentPayMaster spm:pay){
					if(null!=spm && null!=spm.getPayStatusCode() && "ORDER_PART_PAY".equals(spm.getPayStatusCode())){
						stus.setIspay("1");
						break;
					}
				}
				stus.setAgentFlag(search.getAgentFlag());
			}
		}
		Integer count = studentMapper.queryNewStudentsListCount(search);
		PageFinder2<StudentListVo> pageFinder = new PageFinder2<StudentListVo>(
				search.getPage(), search.getPageSize(), count, data);
		return pageFinder;
	}

	
	
	/**
	 * 
	 * @Title: findStudentList
	 * @Description: 插入学生支持excel
	 * @return
	 * @return Integer 返回类型
	 * @throws
	 * @exception
	 * @date 2015-9-29
	 * @user by 杨延博
	 */
	@Override
	public String insertSupportExcel(Student student, boolean isUserFront) {
		
		String mobile = student.getMobile();
		String identityTypeCode = student.getIdentityTypeCode();
		String identityId = student.getIdentityId();
		String name = student.getName();
		String email = student.getEmail();
		String username=student.getUsername();
		String emergencyPhone = student.getEmergencyPhone();
		if(null!=student.getAddFlag() && "username".equals(student.getAddFlag())){
			// 判断是否为身份证
			if ("ID_IDCARD".equals(identityTypeCode)) {
				if (!(identityId == null || "".equals(identityId))) {
					// 判断是否含有非数字
					if (!identityId.matches("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])")) {
						return "0302";
					}
					// 判断字符串是否为15或者18
					if (identityId.trim().length() != 15
							&& identityId.trim().length() != 18) {
						return "0302";
					}
					Student result = studentMapper.queryByIdentityId(student);
					// 判断身份证号是否已存在
					if (result != null) {
						return "0303";
					}
				}
			}
			// 判断姓名是否合法
			if (name == null || "".equals(name)) {
				return "0201";
			}
			// 判断email
			if (!(email == null || "".equals(email))) {
				if (!ParameterUtil.isEmail(email)) {
					return "0402";
				}
			}
			// 判断紧急联系人电话
			if (!(emergencyPhone == null || "".equals(emergencyPhone))) {
				// 判断紧急手机号格式是否正确
				if (!ParameterUtil.isMobilePhone(emergencyPhone)) {
					return "0702";
				}
			}
			Date nowTime = new Date();
			student.setCreateTime(nowTime);
			student.setDeleteFlag(0);
			//如果该手机号已经在前台注册，则直接关联此前台账号
			UsersFront uf=new UsersFront();
			uf.setUsername(student.getUsername());
			uf.setCompanyId(student.getCompanyId());
			UsersFront f=usersFrontMapper.findUsersFrontByUsername(uf);
			if(f!=null){
				student.setUserId(f.getId());
				Student st=studentMapper.queryStuInfoByUserId(student);
				if(null==st){
					studentMapper.insert(student);
				}
			}else{
				studentMapper.insert(student);
			}
			//如果根据手机号查询不到前台账号，则新建前台账号并绑定
			if (isUserFront && f==null && student!=null) {
					UsersFront usersFront = new UsersFront();
					if(null!=username && !"".equals(username)){
						usersFront.setUsername(username.toLowerCase());
					}
					usersFront.setPassword(student.getPassword());
					usersFront.setMobile(mobile);
					usersFront.setEmail(student.getEmail());
					usersFront.setCompanyId(student.getCompanyId());
					usersFront.setVipFlag(0);
					usersFront.setStatus(1);
					usersFront.setRegistType(2);
					usersFront.setSchoolId(student.getSchoolId());
					usersFront.setRegistTime(nowTime);
//					UsersFront usersFrontExists=usersFrontMapper.findUsersFrontByUsername(usersFront);
//					if(usersFrontExists!=null){

//					}else{
						usersFrontMapper.insert(usersFront);
//					}
					student.setUserId(usersFront.getId());
					studentMapper.update(student);
					return "success";
			}
		}else{
			// 判断手机号是否为空
			if (mobile == null || "".equals(mobile)) {
				return "0101";
			}else{
				// 判断手机号格式是否正确
				if (!ParameterUtil.isMobilePhone(mobile)) {
					return "0102";
				}
			}
			
			// 判断是否为身份证
			if ("ID_IDCARD".equals(identityTypeCode)) {
				if (!(identityId == null || "".equals(identityId))) {
//					return "0301";
				
					// 判断是否含有非数字
					if (!identityId.matches("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])")) {
						return "0302";
					}
					// 判断字符串是否为15或者18
					if (identityId.trim().length() != 15
							&& identityId.trim().length() != 18) {
						return "0302";
					}
					Student result = studentMapper.queryByIdentityId(student);
					// 判断身份证号是否已存在
					if (result != null) {
						return "0303";
					}
				}
			}
			// 判断手机号是否已经存在
			if (studentMapper.queryByMobile(student) != null) {
				return "0103";
			}
			// 判断姓名是否合法
			if (name == null || "".equals(name)) {
				return "0201";
			}
//			if (!name.matches("([0-9A-Za-z]|[\\u4e00-\\u9fa5])+")) {
//				return "0202";
//			}
			// 判断email
			if (!(email == null || "".equals(email))) {
				if (!ParameterUtil.isEmail(email)) {
					return "0402";
				}
			}
			
			// 判断紧急联系人
//			if (!(emergencyName == null || "".equals(emergencyName))) {
//				if (!emergencyName.matches("([0-9A-Za-z]|[\\u4e00-\\u9fa5])+")) {
//					return "0602";
//				}
//			}
			
			// 判断紧急联系人电话
			if (!(emergencyPhone == null || "".equals(emergencyPhone))) {
				// 判断紧急手机号格式是否正确
				if (!ParameterUtil.isMobilePhone(emergencyPhone)) {
					return "0702";
				}
			}
			Date nowTime = new Date();
			student.setCreateTime(nowTime);
			student.setDeleteFlag(0);
			//如果该手机号已经在前台注册，则直接关联此前台账号
			UsersFront uf=new UsersFront();
			uf.setMobile(student.getMobile());
			uf.setCompanyId(student.getCompanyId());
			UsersFront f=usersFrontMapper.findUsersFrontByMobile(uf);
			if(f!=null){
				student.setUserId(f.getId());
			}
			studentMapper.insert(student);
			//如果根据手机号查询不到前台账号，则新建前台账号并绑定
			if (isUserFront && f==null && student!=null) {
					UsersFront usersFront = new UsersFront();
					if(null!=username && !"".equals(username)){
						usersFront.setUsername(username);
					}
					usersFront.setPassword(student.getPassword());
					usersFront.setMobile(mobile);
					usersFront.setEmail(student.getEmail());
					usersFront.setCompanyId(student.getCompanyId());
					usersFront.setVipFlag(0);
					usersFront.setStatus(1);
					usersFront.setRegistType(2);
					usersFront.setSchoolId(student.getSchoolId());
					usersFront.setRegistTime(nowTime);
					//2017-01-17 目前findUsersFrontByMobile有问题不是only by mobile
//					UsersFront usersFrontExists=usersFrontMapper.findUsersFrontByMobile(usersFront);
//					if(usersFrontExists!=null){
//						usersFront.setId(usersFrontExists.getId());
//						usersFrontMapper.update(usersFront);
//					}else{
						usersFrontMapper.insert(usersFront);
//					}
					student.setUserId(usersFront.getId());
					studentMapper.update(student);
					return "success";
			}
		}
		return "success";
	}

	@Override
	public List<StudentListVo> findStudentsData(StudentListVo search) {
		List<StudentListVo> data = studentMapper.queryStudentsList(search);
		return data;
	}

	@Override
	public Student queryByMobile(Student student) {
		// TODO Auto-generated method stub
		return studentMapper.queryByMobile(student);
	}

	@Override
	public Student queryByIdentityId(Student student) {
		// TODO Auto-generated method stub
		return studentMapper.queryByIdentityId(student);
	}

	@Override
	public String querySupportExcel(Student student) {
		String identityTypeCode = student.getIdentityTypeCode();
		// 判断手机号是否已经存在
		if (studentMapper.queryByMobile(student) != null) {
			return "0103";
		}
		// 判断是否为身份证
		if ("ID_IDCARD".equals(identityTypeCode)) {
			Student result = studentMapper.queryByIdentityId(student);
			// 判断身份证号是否已存在
			if (result != null) {
				return "0303";
			}
		}
		return "success";
	}

	@Override
	public PageFinder<StudentListVo> findStudentsList1(StudentListVo search) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("commodityType", "COMMODITY_CLASS");
			List<StudentListVo> data = studentMapper.queryStudentsList1(search);
			for(StudentListVo stus:data){
			     if(null!=stus&&stus.getPaymaterCount()>0){
			    	 map.put("stuId", stus.getId());
			    	 List<StudentPayMaster> pay=studentPayMasterMapper.findpayIdByStudentsId(map);
			    	 for(StudentPayMaster spm:pay){
			    		 if(null!=spm&&null!=spm.getIsAgent()&&!"".equals(spm.getIsAgent())&&"1".equals(spm.getIsAgent())){
			    			 stus.setIsAgent(spm.getIsAgent());
			    			 break;
			    		 }
			    	 }
			     }
			}
			Integer count = studentMapper.queryStudentsListCount1(search);
			PageFinder<StudentListVo> pageFinder = new PageFinder<StudentListVo>(
					search.getPage(), search.getPageSize(), count, data);
			return pageFinder;
	}

	@Override
	public List<StudentVo> findListByPayments(StudentVo search) {

		return studentMapper.queryListByPayment(search);
	}

	@Override
	public Integer queryMaxIdByCompany(Integer id) {
		// TODO Auto-generated method stub
		return studentMapper.queryMaxIdByCompany(id);
	}

	@Override
	public List<Student> queryImportStudentsBycondition(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return studentMapper.queryImportStudentsBycondition(map);
	}

	@Override
	public PageFinder<StudentListVo> queryStudentsListByClassTypeId(
			StudentListVo search) {
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<StudentListVo> data = studentMapper.queryStudentsListByClassTypeId(search);
//		for(StudentListVo st:data){
//			if(null!=st){
//				String time=studentPayMasterMapper.queryLastApplytime(st.getId());
//				if(null!=time){
//					try {
//						st.setSignUpTime(sdf.parse(time));
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		}
		Integer count = studentMapper.queryStudentsListCountByClassId(search);
		PageFinder<StudentListVo> pageFinder = new PageFinder<StudentListVo>(
				search.getPage(), search.getPageSize(), count, data);
		return pageFinder;
	}

	@Override
	public Student queryStuIsExists(Student stu) {
		return studentMapper.queryByMobile(stu);
	}

	@Override
	public List<StudentListVo> queryStudentsList2(StudentListVo search) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("commodityType", "COMMODITY_CLASS");
		List<StudentListVo> data=studentMapper.queryStudentsList2(search);
		for(StudentListVo stus:data){
		     if(null!=stus&&stus.getPaymaterCount()>0){
		    	 map.put("stuId", stus.getId());
		    	 List<StudentPayMaster> pay=studentPayMasterMapper.findpayIdByStudentsId(map);
		    	 for(StudentPayMaster spm:pay){
		    		 if(null!=spm&&null!=spm.getIsAgent()&&!"".equals(spm.getIsAgent())&&"1".equals(spm.getIsAgent())){
		    			 stus.setIsAgent(spm.getIsAgent());
		    			 break;
		    		 }
		    	 }
		     }
		}
		return data;
	}
	@Override
	public StudentClassLeanRecordVo queryStudentLeanRecord(Integer stuId, Integer classTypeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("stuId", stuId);
		map.put("classTypeId", classTypeId);
		StudentClassLeanRecordVo vo = studentMapper.queryStudentLeanRecord(map);
 		vo.setQuestionNum(studentMapper.queryStudentQuestionCount(map));
 		vo.setEvaluateNum(studentMapper.queryStudentCommentCount(map));
 		vo.setApplyTime(studentMapper.queryStudentBaomingTime(map));
		return vo;
	}
	@Override
	public StudentClassLeanRecordVo queryStudentQuestionCount1(Integer stuId, Integer classTypeId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("stuId", stuId);
		map.put("classTypeId", classTypeId);
		StudentClassLeanRecordVo vo = studentMapper.queryStudentLeanRecord(map);
 		vo.setQuestionNum(studentMapper.queryStudentQuestionCount(map));
 		vo.setEvaluateNum(studentMapper.queryStudentCommentCount(map));
		return vo;
	}
	
	/**
	 * 单班号-查看学员在某课程下的直播或者面授以及视频的课次学习记录
	 */
	@Override
	public PageFinder<StudentClassLeanDetailVo> queryStudentClassLeanRecord(StudentClassLeanDetailVo codition) {
		List<StudentClassLeanDetailVo> dataList = studentMapper.queryStudentClassLeanRecord(codition);
		Integer dataListCount = studentMapper.queryStudentClassLeanRecordCount(codition);
		PageFinder<StudentClassLeanDetailVo> pageFinder = new PageFinder<StudentClassLeanDetailVo>(codition.getPage(),codition.getPageSize(),dataListCount,dataList);
		return pageFinder;
	}
	/**
	 * 多班号-查看学员在某课程下的直播或者面授以及视频的课次学习记录 
	 */
	@Override
	public PageFinder<StudentClassLeanDetailVo> queryStudentMoreClassLeanRecord(StudentClassLeanDetailVo codition) {
		List<StudentClassLeanDetailVo> dataList = studentMapper.queryStudentMoreClassLeanRecord(codition);
		Integer dataListCount = studentMapper.queryStudentMoreClassLeanRecordCount(codition);
		PageFinder<StudentClassLeanDetailVo> pageFinder = new PageFinder<StudentClassLeanDetailVo>(codition.getPage(),codition.getPageSize(),dataListCount,dataList);
		return pageFinder;
	}
	
	@Override
	public PageFinder<StudentTiKuExcipseVo> queryStudentTikuExperise(StudentTiKuExcipseVo search){
		List<StudentTiKuExcipseVo> listData = studentMapper.queryStudentTikuExperise(search);
		Integer count = studentMapper.queryStudentTikuExperiseCount(search);
		PageFinder<StudentTiKuExcipseVo> pageFider = new PageFinder<StudentTiKuExcipseVo>(search.getPage(),search.getPageSize(),count,listData);
		return pageFider;
	}
	
	@Override
	public List<StudentTiKuOrSubjectVo> queryCommpanyTiku(StudentTiKuOrSubjectVo search){
		return studentMapper.queryCommpanyTiku(search);
	}
	
	@Override
	public List<StudentTiKuOrSubjectVo> queryCommpanySubject(StudentTiKuOrSubjectVo search){
		return studentMapper.queryCommpanySubject(search);
	}
	/**
	 * 查询视频课的学习记录详情
	 */
	@Override
	public PageFinder<StudentClassLeanDetailVo> queryStudentLecLeanDetail(StudentClassLeanDetailVo codition){
		List<StudentClassLeanDetailVo> dataList = studentMapper.queryStudentLecLeanDetail(codition);
		Integer dataListCount = studentMapper.queryStudentLecLeanDetailCount(codition);
		PageFinder<StudentClassLeanDetailVo> pageFinder = new PageFinder<StudentClassLeanDetailVo>(codition.getPage(),codition.getPageSize(),dataListCount,dataList);
		return pageFinder;
	}
	/**
	 * 查询直播课的学习记录详情
	 */
	@Override
	public PageFinder<StudentClassLeanDetailVo> queryStudentLessLeanDetail(StudentClassLeanDetailVo codition){
		List<StudentClassLeanDetailVo> dataList = studentMapper.queryStudentLessLeanDetail(codition);
		Integer dataListCount = studentMapper.queryStudentLessLeanDetailCount(codition);
		PageFinder<StudentClassLeanDetailVo> pageFinder = new PageFinder<StudentClassLeanDetailVo>(codition.getPage(),codition.getPageSize(),dataListCount,dataList);
		return pageFinder;
	}
	/**
	 * 课程  所有的直播/面授课次数
	 */
	@Override
	public List<StudentLessTimeVo> queryLessionCount(StudentClassLeanDetailVo codition){
		List<StudentLessTimeVo> dataListCount = studentMapper.queryLessionCount(codition);
		return dataListCount;
	}
	/**
	 * 课程  所有的视频课次数
	 */
	@Override
	public Integer queryLecCount(StudentClassLeanDetailVo codition){
		Integer dataListCount = studentMapper.queryLecCount(codition);
		return dataListCount;
	}
	/**
	 *课程   已经完成的视频课次
	 */
	@Override
	public Integer queryLecFinishCount(StudentClassLeanDetailVo codition){
		Integer dataListCount = studentMapper.queryLecFinishCount(codition);
		return dataListCount;
	}
	/**
	 * 课程包  所有的视频课次数
	 */
	@Override
	public Integer queryCpLecCount(StudentClassLeanDetailVo codition){
		Integer dataListCount = studentMapper.queryCpLecCount(codition);
		return dataListCount;
	}
	/**
	 *课程包   已经完成的视频课次
	 */
	@Override
	public Integer queryCpLecFinishCount(StudentClassLeanDetailVo codition){
		Integer dataListCount = studentMapper.queryCpLecFinishCount(codition);
		return dataListCount;
	}
	/**
	 * 课程包  所有的直播/面授课次数
	 */
	@Override
	public List<StudentLessTimeVo> queryCpMoreNoLessionCount(StudentClassLeanDetailVo codition){
		List<StudentLessTimeVo> dataListCount = studentMapper.queryCpMoreNoLessionCount(codition);
		return dataListCount;
	}
	
	@Override
	public String updateSupportExcel(Student student, boolean isUserFront) {
		String mobile = student.getMobile();
		String identityTypeCode = student.getIdentityTypeCode();
		String identityId = student.getIdentityId();
		String name = student.getName();
		String email = student.getEmail();
		String emergencyPhone = student.getEmergencyPhone();
		String username = student.getUsername();
		
		if(null!=student.getAddFlag() && "username".equals(student.getAddFlag())){
			// 判断是否为身份证
			if ("ID_IDCARD".equals(identityTypeCode)) {
				if (!(identityId == null || "".equals(identityId))) {
					// 判断是否含有非数字
					if (!identityId.matches("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])")) {
						return "0302";
					}
					// 判断字符串是否为15或者18
					if (identityId.trim().length() != 15
							&& identityId.trim().length() != 18) {
						return "0302";
					}
				}
			}
			// 判断姓名是否合法
			if (name == null || "".equals(name)) {
				return "0201";
			}
			// 判断email
			if (!(email == null || "".equals(email))) {
				if (!ParameterUtil.isEmail(email)) {
					return "0402";
				}
			}
			
			// 判断紧急联系人电话
			if (!(emergencyPhone == null || "".equals(emergencyPhone))) {
				// 判断紧急手机号格式是否正确
				if (!ParameterUtil.isMobilePhone(emergencyPhone)) {
					return "0702";
				}
			}
			Date nowTime = new Date();
			student.setCreateTime(nowTime);
			student.setDeleteFlag(0);
			//如果该手机号已经在前台注册，则直接关联此前台账号
			UsersFront uf=new UsersFront();
			uf.setUsername(student.getUsername());
			uf.setCompanyId(student.getCompanyId());
			UsersFront f=usersFrontMapper.findUsersFrontByUsername(uf);
			if(f!=null){
				if(f.getMobile()!=null && f.getMobile().equals(student.getMobile())){
					student.setUserId(f.getId());
					// 判断手机号是否已经存在
					Student st=studentMapper.queryStuInfoByUserId(student);
					if(null!=st){
						student.setId(st.getId());
						studentMapper.update(student);
					}else{
						studentMapper.insert(student);
					}
				}else{
					f.setMobile(student.getMobile());
					usersFrontMapper.update(f);
					student.setUserId(f.getId());
					Student st=studentMapper.queryStuInfoByUserId(student);
					if(null!=st){
						student.setId(st.getId());
						studentMapper.update(student);
					}else{
						studentMapper.insert(student);
					}
				}
			}else{
				SelectStudentOrUsersfrontVo search = new SelectStudentOrUsersfrontVo();
				search.setCompanyId( student.getCompanyId() );
				if(	student.getMobile() != null ) { search.setMobile( student.getMobile() ); }
				if( student.getUsername() != null) { search.setUsername( student.getUsername() ); }
				
				Integer usersfrontCount = usersFrontMapper.findUsersfrontCountByMobileOrUsername(search);
				Integer studentCount = studentMapper.findStudentCountOnlyByMobile(search);
				if( studentCount == 0 &&  usersfrontCount ==0 ){
					UsersFront usersFront = new UsersFront();
					if(null!=username && !"".equals(username)){
						usersFront.setUsername(username);
					}
					usersFront.setPassword(student.getPassword());
					usersFront.setMobile(mobile);
					usersFront.setEmail(student.getEmail());
					usersFront.setCompanyId(student.getCompanyId());
					usersFront.setVipFlag(0);
					usersFront.setStatus(1);
					usersFront.setRegistType(2);
					usersFront.setSchoolId(student.getSchoolId());
					usersFront.setRegistTime(nowTime);
					usersFrontMapper.insert(usersFront);
					student.setUserId(usersFront.getId());
				}else{
					return "error";
				}
				studentMapper.insert(student);
			}
			
//			//如果根据手机号查询不到前台账号，则新建前台账号并绑定
//			if (isUserFront && f==null && student!=null) {
//					UsersFront usersFront = new UsersFront();
//					if(null!=username && !"".equals(username)){
//						usersFront.setUsername(username);
//					}
//					usersFront.setPassword(student.getPassword());
//					usersFront.setMobile(mobile);
//					usersFront.setEmail(student.getEmail());
//					usersFront.setCompanyId(student.getCompanyId());
//					usersFront.setVipFlag(0);
//					usersFront.setStatus(1);
//					usersFront.setRegistType(2);
//					usersFront.setSchoolId(student.getSchoolId());
//					usersFront.setRegistTime(nowTime);
////					UsersFront usersFrontExists=usersFrontMapper.findUsersFrontByUsername(usersFront);
////					if(usersFrontExists!=null){
////						usersFront.setId(usersFrontExists.getId());
////						usersFrontMapper.update(usersFront);
////					}else{
//						usersFrontMapper.insert(usersFront);
////					}
//					
//					student.setUserId(usersFront.getId());
//					studentMapper.update(student);
//					return "success";
//			}
		}else{
			// 判断手机号是否为空
			if (mobile == null || "".equals(mobile)) {
				return "0101";
			}else{
				// 判断手机号格式是否正确
				if (!ParameterUtil.isMobilePhone(mobile)) {
					return "0102";
				}
			}
			// 判断是否为身份证
			if ("ID_IDCARD".equals(identityTypeCode)) {
				if (!(identityId == null || "".equals(identityId))) {
					// 判断是否含有非数字
					if (!identityId.matches("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])")) {
						return "0302";
					}
					// 判断字符串是否为15或者18
					if (identityId.trim().length() != 15
							&& identityId.trim().length() != 18) {
						return "0302";
					}
				}
			}
			// 判断姓名是否合法
			if (name == null || "".equals(name)) {
				return "0201";
			}
			// 判断email
			if (!(email == null || "".equals(email))) {
				if (!ParameterUtil.isEmail(email)) {
					return "0402";
				}
			}
			
			// 判断紧急联系人电话
			if (!(emergencyPhone == null || "".equals(emergencyPhone))) {
				// 判断紧急手机号格式是否正确
				if (!ParameterUtil.isMobilePhone(emergencyPhone)) {
					return "0702";
				}
			}
			Date nowTime = new Date();
			student.setCreateTime(nowTime);
			student.setDeleteFlag(0);
			//如果该手机号已经在前台注册，则直接关联此前台账号
//			UsersFront uf=new UsersFront();
//			uf.setMobile(student.getMobile());
//			uf.setCompanyId(student.getCompanyId());
//			UsersFront f=usersFrontMapper.findUsersFrontByMobile(uf);
//			
//			if(f!=null){
//				student.setUserId(f.getId());
//			}
			// 判断手机号是否已经存在
			Student st=studentMapper.queryByMobile(student);
			if(null!=st){
				if( st.getUserId() != null ){
					UsersFront uf = usersFrontMapper.findById(st.getUserId());
					if(uf.getUsername() != null && uf.getUsername().equals(student.getUsername())){
						
					}else{
						SelectStudentOrUsersfrontVo search = new SelectStudentOrUsersfrontVo();
						search.setCompanyId(student.getCompanyId());
						if(	student.getUsername()!=null	){
							search.setUsername(student.getUsername());
						}
						Integer ufCount = usersFrontMapper.findUsersfrontCountByMobileOrUsername(search);
						if(ufCount <= 0){
							uf.setUsername(student.getUsername());
							usersFrontMapper.update(uf);
						}
					}
				}else{
					UsersFront usersFront = new UsersFront();
					if(null!=username && !"".equals(username)){
						SelectStudentOrUsersfrontVo search = new SelectStudentOrUsersfrontVo();
						search.setCompanyId(student.getCompanyId());
						if(	student.getUsername()!=null	){
							search.setUsername(student.getUsername());
						}
						Integer ufCount = usersFrontMapper.findUsersfrontCountByMobileOrUsername(search);
						if(ufCount <= 0){
							usersFront.setUsername(username);
						}else{
							return "error";
						}
					}
					usersFront.setPassword(student.getPassword());
					usersFront.setMobile(mobile);
					usersFront.setEmail(student.getEmail());
					usersFront.setCompanyId(student.getCompanyId());
					usersFront.setVipFlag(0);
					usersFront.setStatus(1);
					usersFront.setRegistType(2);
					usersFront.setSchoolId(student.getSchoolId());
					usersFront.setRegistTime(nowTime);
					usersFrontMapper.insert(usersFront);
					student.setUserId(usersFront.getId());
				}
				studentMapper.update(student);
			}else{
				SelectStudentOrUsersfrontVo search = new SelectStudentOrUsersfrontVo();
				search.setCompanyId( student.getCompanyId() );
				if(	student.getMobile() != null ) { search.setMobile( student.getMobile() ); }
				if( student.getUsername() != null) { search.setUsername( student.getUsername() ); }
				
				Integer usersfrontCount = usersFrontMapper.findUsersfrontCountByMobileOrUsername(search);
				
				if( usersfrontCount == 0 ) { 
					studentMapper.insert(student);
					UsersFront usersFront = new UsersFront();
					if(null!=username && !"".equals(username)){
						usersFront.setUsername(username);
					}
					usersFront.setPassword(student.getPassword());
					usersFront.setMobile(mobile);
					usersFront.setEmail(student.getEmail());
					usersFront.setCompanyId(student.getCompanyId());
					usersFront.setVipFlag(0);
					usersFront.setStatus(1);
					usersFront.setRegistType(2);
					usersFront.setSchoolId(student.getSchoolId());
					usersFront.setRegistTime(nowTime);
					usersFrontMapper.insert(usersFront);
					student.setUserId(usersFront.getId());
					studentMapper.update(student);
				}else{
					return "error";
				}
			}
//			//如果根据手机号查询不到前台账号，则新建前台账号并绑定
//			if (isUserFront && f==null && student!=null) {
//					UsersFront usersFront = new UsersFront();
//					if(null!=username && !"".equals(username)){
//						usersFront.setUsername(username);
//					}
//					usersFront.setPassword(student.getPassword());
//					usersFront.setMobile(mobile);
//					usersFront.setEmail(student.getEmail());
//					usersFront.setCompanyId(student.getCompanyId());
//					usersFront.setVipFlag(0);
//					usersFront.setStatus(1);
//					usersFront.setRegistType(2);
//					usersFront.setSchoolId(student.getSchoolId());
//					usersFront.setRegistTime(nowTime);
//					//2017-01-17 目前findUsersFrontByMobile有问题不是only by mobile
////					UsersFront usersFrontExists=usersFrontMapper.findUsersFrontByMobile(usersFront);
////					if(usersFrontExists!=null){
////						usersFront.setId(usersFrontExists.getId());
////						usersFrontMapper.update(usersFront);
////					}else{
//						usersFrontMapper.insert(usersFront);
////					}
//					
//					student.setUserId(usersFront.getId());
//					studentMapper.update(student);
//					return "success";
//			}else{//不能更改用户名
////用户名不是空更新用户名 （这个功能取消）
////				if(null!=username && !"".equals(username)){
////					f.setUsername(username);
////					usersFrontMapper.update(f);
////				}
//			}
		}
		return "success";
	}
	@Override
	public List<StudentListVo> findStudentListBuyPackage(StudentListVo search){
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<StudentListVo> data=studentMapper.findClassPackageByCommodityId(search);
//		for(StudentListVo st:data){
//			if(null!=st){
//				String time=studentPayMasterMapper.queryLastApplytime(st.getId());
//				if(null!=time){
//					try {
//						st.setSignUpTime(sdf.parse(time));
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		}
		return data;
	}
	@Override
	public int findCountByCommodityId(StudentListVo search){
		return studentMapper.findCountByCommodityId(search);
	}

	@Override
	public List<Student> findStuInfos(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return studentMapper.findStuInfos(map);
	}
	
	/**
	 * 查询学员的课程包提问以及评价，注册时间，购买时间
	 */
	@Override
	public StudentClassLeanRecordVo findClassPackInfo(Integer classPackageId,Integer stuId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("classPackageId", classPackageId);
		map.put("stuId", stuId);
		StudentClassLeanRecordVo info = studentMapper.findClassPackInfo(map);
		if(info==null)
			return null;
		String baoMingTime = studentMapper.queryStudentCpBaomingTime(map);
		Integer questionCount = studentMapper.findStuClassPackageQuesCount(map);
		Integer commetCount = studentMapper.findStuClassPacageCommentCount(map);
		info.setApplyTime(baoMingTime);
		info.setQuestionNum(questionCount);
		info.setEvaluateNum(commetCount);
		return info;
	}
	/**
	 * 检查购买的是整个课程包还是课程包内的部分阶段
	 */
	@Override
	public List<Integer> findHasBuyStage(Integer stuId,Integer classPackeId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("stuId", stuId);
		map.put("classPackageId", classPackeId);
		return studentMapper.findHasBuyStage(map);
	}
	/**
	 * 课程包--全部购买后--单班号---的学习记录
	 */
	@Override
	public PageFinder<StudentClassLeanDetailVo> queryStudentCpLeanRecord(StudentClassLeanDetailVo codition) {
		List<StudentClassLeanDetailVo> dataList = studentMapper.queryStudentCpLeanRecord(codition);
		Integer dataListCount = studentMapper.queryStudentCpLeanRecordCount(codition);
		PageFinder<StudentClassLeanDetailVo> pageFinder = new PageFinder<StudentClassLeanDetailVo>(codition.getPage(),codition.getPageSize(),dataListCount,dataList);
		return pageFinder;
	}
	/**
	 * 课程包--购买部分阶段后--单班号---的学习记录
	 */
	@Override
	public PageFinder<StudentClassLeanDetailVo> queryStudentCpSomeStageLeanRecord(StudentClassLeanDetailVo codition) {
		List<StudentClassLeanDetailVo> dataList = studentMapper.queryStudentCpSomeStageLeanRecord(codition);
		Integer dataListCount = studentMapper.queryStudentCpSomeStageLeanRecordCount(codition);
		PageFinder<StudentClassLeanDetailVo> pageFinder = new PageFinder<StudentClassLeanDetailVo>(codition.getPage(),codition.getPageSize(),dataListCount,dataList);
		return pageFinder;
	}
	/**
	 * 课程包--购买整个课程包后--多班号---的学习记录
	 */
	@Override
	public PageFinder<StudentClassLeanDetailVo> queryStudentMoreCpLeanRecord(StudentClassLeanDetailVo codition) {
		List<StudentClassLeanDetailVo> dataList = studentMapper.queryStudentMoreCpLeanRecord(codition);
		Integer dataListCount = studentMapper.queryStudentMoreCpLeanRecordCount(codition);
		PageFinder<StudentClassLeanDetailVo> pageFinder = new PageFinder<StudentClassLeanDetailVo>(codition.getPage(),codition.getPageSize(),dataListCount,dataList);
		return pageFinder;
	}
	/**
	 * 课程包--购买部分阶段后--多班号---的学习记录
	 */
	@Override
	public PageFinder<StudentClassLeanDetailVo> queryStudentMoreCpSomeStageLeanRecord(StudentClassLeanDetailVo codition) {
		List<StudentClassLeanDetailVo> dataList = studentMapper.queryStudentMoreCpSomeStageLeanRecord(codition);
		Integer dataListCount = studentMapper.queryStudentMoreCpSomeStageLeanRecordCount(codition);
		PageFinder<StudentClassLeanDetailVo> pageFinder = new PageFinder<StudentClassLeanDetailVo>(codition.getPage(),codition.getPageSize(),dataListCount,dataList);
		return pageFinder;
	}
	
	/**
	 * 课程或者课程包学生学习记录
	 */
	@Override
	public PageFinder<StudentClassLeanDetailVo> queryStudentCtOrCpLeanRecord(StudentClassLeanDetailVo codition) {
		List<StudentClassLeanDetailVo> dataList = studentMapper.queryStudentCtOrCpLeanRecord(codition);
		Integer dataListCount = studentMapper.queryStudentCtOrCpLeanRecordCount(codition);
		PageFinder<StudentClassLeanDetailVo> pageFinder = new PageFinder<StudentClassLeanDetailVo>(codition.getPage(),codition.getPageSize(),dataListCount,dataList);
		return pageFinder;
	}

	@Override
	public List<StudentListVo> exportClassPackageByCommodityId(
			StudentListVo search) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<StudentListVo> data=studentMapper.findClassPackageByCommodityId(search);
		for(StudentListVo st:data){
			if(null!=st){
				String time=studentPayMasterMapper.queryLastApplytime(st.getId());
				if(null!=time){
					try {
						st.setSignUpTime(sdf.parse(time));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return data;
	}

	@Override
	public String importStuList(List<Student> list,String mark,CompanyRegisterConfig crc) {
		try {
			if (list != null) {
				for(Student s:list){
					if(null!=s){
						if("update".equals(mark)){
							updateSupportExcel(s, true);
						}else{
							insertSupportExcel(s, true);
						}
					}
				}
			}
		} catch (Exception e) {
			log_student.error(">>> [导入学员] "
					+ "状态：导入学员失败",e);
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	@Override
	public List<StudentListDataVo> queryPayListByStuId(StudentListVo search) {
		List<StudentListDataVo> data=studentPayMasterMapper.queryOrderCourseByStuId(search);
		if(null!=data && data.size()>0){
			for(StudentListDataVo stu:data){
				if(null!=stu){
					//所属分校
					if(null!=stu.getSchoolId()){
						SysConfigSchool school=sysConfigSchoolMapper.findById(stu.getSchoolId());
						if(null!=school && null!=school.getSchoolName()){
							stu.setSchoolName(school.getSchoolName());
						}
					}
					if(null!=stu.getPayType()){
						if("PAY_TYPE_ZFB".equals(stu.getPayType())){
							stu.setPayType("支付宝");
						}else if("PAY_TYPE_REMIT".equals(stu.getPayType())){
							stu.setPayType("银行汇款");
						}else if("PAY_TYPE_ZFB_ZZ".equals(stu.getPayType())){
							stu.setPayType("支付宝转账");
						}else if("PAY_TYPE_WX_PERSON".equals(stu.getPayType())){
							stu.setPayType("微信个人收款");
						}else if("PAY_TYPE_WX_GZH".equals(stu.getPayType())){
							stu.setPayType("微信公众号");
						}else{
							stu.setPayType("线下支付");
						}
					}else{
						stu.setPayType("线下支付");
					}
				}
			}
		}
		return data;
	}

	@Override
	public List<Student> findStuInfoByUserId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return studentMapper.findStuInfoByUserId(map);
	}

	@Override
	public Student queryByUsername(Student search) {
		Student stu=studentMapper.queryByUsername(search);
		if(null!=stu && null!=stu.getUserId()){
			UsersFront u=usersFrontMapper.findById(stu.getUserId());
			if(null!=u && null!=u.getUsername()){
				stu.setUsername(u.getUsername());
			}
		}
		return stu; 
	}

	@Override
	public Integer checkUsersFrontByUsername(Student search) {
		return studentMapper.checkUsersFrontByUsername(search);
	}

	@Override
	public Student queryStuInfoByWhere(Student search) {
		// TODO Auto-generated method stub
		return studentMapper.queryStuInfoByWhere(search);
	}

	@Override
	public List<StudentListVo> queryStudentsListByClassTypeId2(StudentListVo search) {
		return studentMapper.queryStudentsListByClassTypeId2(search);
		}

	@Override
	public Integer findByGroupId(Map<String, Object> map) {
		return studentMapper.findByGroupId(map);
	}
	@Override
	public List<Student> findGroupStu(Map<String, Object> map) {
		return studentMapper.findGroupStu(map);
	}

	@Override
	public Student findByEmail(Student s) {
		return studentMapper.findByEmail(s);
	}

	@Override
	public UsersFrontVo findUserFrontvoByStuId(Integer sId) {
		return studentMapper.findUserFrontvoByStuId(sId);
	}

	@Override
	public Integer findStudentCountOnlyByMobile(SelectStudentOrUsersfrontVo search) {
		return studentMapper.findStudentCountOnlyByMobile(search);
	}


	@Override
	public List<StudentImportVo> queryAllStudentsByCompanyId(Integer companyId) {
		return studentMapper.queryAllStudentsByCompanyId(companyId);
	}


	@Override
	public List<Integer> insertMoreStudents(List<StudentImportVo> students,List<StudentImportVo> updateListstudents,String groupOneId,String groupTwoId,Integer userId) {
		
		List<Integer> studentIds = new ArrayList<Integer>();
		
		Student s = null;
		UsersFront usersFront = null;
		Date date = new Date();
		for (StudentImportVo student : students) {
			//新增学生集合
			s = new Student();
			if(student.getMobile() != null) s.setMobile(student.getMobile());
			if(student.getName() != null) s.setName(student.getName());
			if(student.getIdentityTypeCode() != null) s.setIdentityTypeCode(student.getIdentityTypeCode());
			if(student.getIdentityId() != null) s.setIdentityId(student.getIdentityId());
			if(student.getEmail() != null) s.setEmail(student.getEmail());
			if(student.getQq() != null) s.setQq(student.getQq());
			if(student.getEmergencyContact() != null) s.setEmergencyContact(student.getEmergencyContact());
			if(student.getEmergencyPhone() != null) s.setEmergencyPhone(student.getEmergencyPhone());
			if(student.getCompanyId() != null) s.setCompanyId(student.getCompanyId());
			if(student.getSchoolId() != null) s.setSchoolId(student.getSchoolId());
			if(student.getEduArea() != null) s.setEduArea(student.getEduArea());
			if(student.getEduSchool() != null) s.setEduSchool(student.getEduSchool());
			if(student.getEduStep() != null) s.setEduStep(student.getEduStep());
			if(student.getEduYear() != null) s.setEduYear(student.getEduYear());
			if(student.getEduClass() != null) s.setEduClass(student.getEduClass());
			if(student.getDeleteFlag() != null) s.setDeleteFlag(student.getDeleteFlag());
			if(groupOneId!=null) s.setGroupOneId(Integer.parseInt(groupOneId));
			if(groupTwoId!=null) s.setGroupTwoId(Integer.parseInt(groupTwoId));
			s.setCreateTime(date);
			s.setCreator(userId);
			s.setDeleteFlag(0);
			s.setIsInSchool(1);
			this.studentMapper.insert(s);
			
			/* 生成usersfront */
			usersFront = new UsersFront();
			if(student.getUsername() != null) usersFront.setUsername(student.getUsername().toLowerCase());
			if(student.getPassword() != null) usersFront.setPassword(student.getPassword());
			if(student.getMobile() != null) usersFront.setMobile(student.getMobile());
			if(student.getEmail() != null) usersFront.setEmail(student.getEmail());
			if(student.getCompanyId() != null) usersFront.setCompanyId(student.getCompanyId());
			if(student.getSchoolId() != null) usersFront.setSchoolId(student.getSchoolId());
			usersFront.setVipFlag(0);
			usersFront.setStatus(1);
			usersFront.setRegistType(2);
			usersFront.setRegistTime(date);
			
			this.usersFrontMapper.insert(usersFront);
			
			/* 更新student */
			s.setUserId(usersFront.getId());
			this.studentMapper.update(s);
			
			studentIds.add(s.getId());
		}
		for(StudentImportVo student : updateListstudents){
			//更新学生
			SelectStudentOrUsersfrontVo search = new SelectStudentOrUsersfrontVo();
			search.setMobile(student.getMobile());
			search.setCompanyId(student.getCompanyId());
			s = new Student();
			//先看时候是电话重复  如果电话重复 直接更新学生及学生账号
			s = this.studentMapper.findStudentOnlyByMobile(search); 
			if(null!=s){
			if(student.getMobile() != null) s.setMobile(student.getMobile());
			if(student.getName() != null) s.setName(student.getName());
			if(student.getIdentityTypeCode() != null) s.setIdentityTypeCode(student.getIdentityTypeCode());
			if(student.getIdentityId() != null) s.setIdentityId(student.getIdentityId());
			if(student.getEmail() != null) s.setEmail(student.getEmail());
			if(student.getQq() != null) s.setQq(student.getQq());
			if(student.getEmergencyContact() != null) s.setEmergencyContact(student.getEmergencyContact());
			if(student.getEmergencyPhone() != null) s.setEmergencyPhone(student.getEmergencyPhone());
			if(groupOneId!=null) s.setGroupOneId(Integer.parseInt(groupOneId));
			if(groupTwoId!=null) s.setGroupTwoId(Integer.parseInt(groupTwoId));
			s.setUpdateTime(date);
			s.setUpdator(userId);
			s.setIsInSchool(1);
			this.studentMapper.update(s);
			usersFront =new UsersFront();
			usersFront = this.usersFrontMapper.findUsersFrontOnlyByMobile(search);
			usersFront.setEmail(student.getEmail());
			usersFront.setQq(student.getQq());
			usersFront.setUsername(student.getUsername());
			usersFront.setPassword(student.getPassword());
			usersFront.setStatus(1);
			this.usersFrontMapper.update(usersFront);
			}else{
				//不是手机号重复   而是账号重复       通过账号去修改学生信息  在修改账号信息
				SelectStudentOrUsersfrontVo search1 = new SelectStudentOrUsersfrontVo();
				search1.setUsername(student.getUsername());
				usersFront = this.usersFrontMapper.findUsersFrontOnlyByUsername(search1);
				s = this.studentMapper.findStudentOnlyByUserId(usersFront.getId());
				if(s != null){
					if(student.getMobile() != null) s.setMobile(student.getMobile());
					if(student.getName() != null) s.setName(student.getName());
					if(student.getIdentityTypeCode() != null) s.setIdentityTypeCode(student.getIdentityTypeCode());
					if(student.getIdentityId() != null) s.setIdentityId(student.getIdentityId());
					if(student.getEmail() != null) s.setEmail(student.getEmail());
					if(student.getQq() != null) s.setQq(student.getQq());
					if(student.getEmergencyContact() != null) s.setEmergencyContact(student.getEmergencyContact());
					if(student.getEmergencyPhone() != null) s.setEmergencyPhone(student.getEmergencyPhone());
					if(groupOneId!=null) s.setGroupOneId(Integer.parseInt(groupOneId));
					if(groupTwoId!=null) s.setGroupTwoId(Integer.parseInt(groupTwoId));
					s.setUpdateTime(date);
					s.setUpdator(userId);
					s.setIsInSchool(1);
					this.studentMapper.update(s);
					
					usersFront.setEmail(student.getEmail());
					usersFront.setQq(student.getQq());
					usersFront.setUsername(student.getUsername());
					usersFront.setPassword(student.getPassword());
					usersFront.setStatus(1);
					this.usersFrontMapper.update(usersFront);
				}
			}
			studentIds.add(s.getId());
		}		
			
		
		return studentIds;
	}
	
//	/*  
//	 *	机构注册方式
//	 */
//	private String getCompanyRegisterFlag(Integer companyId){
//		
//		CompanyRegisterConfig crc = new CompanyRegisterConfig();
//		crc.setCompanyId(companyId);
//		crc = this.companyRegisterConfigMapper.queryByCompanyId(crc);
//		
//		if( crc.getMobileFlag() != null && crc.getMobileFlag() == 1 && crc.getUsernameFlag() != null && crc.getUsernameFlag() == 1 ){
//			return "all";
//		}
//		if( crc.getMobileFlag() != null && crc.getMobileFlag() == 1 ){
//			return "mobile";
//		}
//		if( crc.getUsernameFlag() != null && crc.getUsernameFlag() == 1 ){
//			return "username";
//		}
//		
//		return "mobile";	/* 默认手机注册 */
//	}
//	
//	/*
//	 * 机构所有学员
//	 */
//	private StudentAll4CompanyVo getCompanyAllStudents(Integer companyId){
//		
//		List<StudentImportVo> studentslist = this.studentMapper.queryAllStudentsByCompanyId(companyId);
//		
//		StudentAll4CompanyVo allStudents = new StudentAll4CompanyVo();
//		for (int i = 0; i < studentslist.size(); i++) {
//			if( studentslist.get(i).getMobile() != null ){
//				allStudents.getMobiles().put(studentslist.get(i).getMobile(), studentslist.get(i));
//			}
//			if( studentslist.get(i).getUsername() != null ){
//				allStudents.getUsernames().put(studentslist.get(i).getUsername(), studentslist.get(i));
//			}
//		}
//		return allStudents;
//	}
	
	@Override
	public List<StudentListVo> queryStudentsListByIds(String ids) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("commodityType", "COMMODITY_CLASS");
		String[] idsArray = ids.split(",");
		Integer[] idArray = new Integer[idsArray.length];
		for (int i = 0; i < idsArray.length; i++) {
			idArray[i] = Integer.parseInt(idsArray[i]);
		}
		List<StudentListVo> data=studentMapper.queryStudentsListByIds(idArray);
		for(StudentListVo stus:data){
		     if(null!=stus&&stus.getPaymaterCount()>0){
		    	 map.put("stuId", stus.getId());
		    	 List<StudentPayMaster> pay=studentPayMasterMapper.findpayIdByStudentsId(map);
		    	 for(StudentPayMaster spm:pay){
		    		 if(null!=spm&&null!=spm.getIsAgent()&&!"".equals(spm.getIsAgent())&&"1".equals(spm.getIsAgent())){
		    			 stus.setIsAgent(spm.getIsAgent());
		    			 break;
		    		 }
		    	 }
		     }
		}
		return data;
	}
	
	@Override
	public List<Student> findClassByTeacherId(Integer id) {
		return studentMapper.findClassByTeacherId(id);
	}

	@Override
    public List<SysConfigDict> findEduAreaList() {
		
		return studentMapper.sysConfigDict();
    }
}
