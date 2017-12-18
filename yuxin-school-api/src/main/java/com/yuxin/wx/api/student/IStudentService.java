package com.yuxin.wx.api.student;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.model.company.CompanyRegisterConfig;
import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.system.SysConfigDict;
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
 * Service Interface:Student
 * @author wang.zx
 * @date 2014-12-5
 */
public interface IStudentService  {
	/**
	 * 
	* @Title: saveStudent
	* @Description: 新增Student
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(Student student);
	
	/**
	 * 
	* @Title: batchSaveStudent 
	* @Description: 批量新增Student
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<Student> student);
	
	/**
	 * 
	* @Title: updateStudent 
	* @Description: 编辑Student
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(Student student);
	
	/**
	 * 
	* @Title: deleteStudentById 
	* @Description: 根据id删除Student
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteStudentById(Integer id);
	
	/**
	 * 
	* @Title: deleteStudentByIds 
	* @Description: 根据id批量删除Student
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteStudentByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findStudentById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	Student findStudentById(Integer id);
	
	/**
	 * 
	* @Title: findStudentByPage 
	* @Description: 分页查询
	* @return
	* @return List<Student>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	PageFinder<Student> findStudentByPage(Student search);
	
	/**
	 * 
	* @Title: findStudentList
	* @Description: 分页查询
	* @return
	* @return List<Student>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	List<Student> findStudentList(Student search);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 根据订单查学员
	 * @author Chopin
	 * @date 2014年12月12日 下午3:52:25
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<StudentVo> findPageListByPayment(StudentVo search);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 根据订单查学员
	 * @author Chopin
	 * @date 2014年12月12日 下午3:52:25
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<StudentVo> findListByPayment(StudentVo search);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 根据班型查学员
	 * @author Chopin
	 * @date 2014年12月12日 下午3:52:25
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<StudentVo> findListByClassType(StudentVo search);
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 根据班号查学员
	 * @author Chopin
	 * @date 2014年12月12日 下午3:52:25
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<StudentVo> findListByClassNo(StudentVo search);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 根据班型查学员
	 * @author Chopin
	 * @date 2014年12月12日 下午3:52:25
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<StudentVo> findPageListByClassType(StudentVo search);
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 根据班号查学员
	 * @author Chopin
	 * @date 2014年12月12日 下午3:52:25
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<StudentVo> findPageListByClassNo(StudentVo search);
	
	/**
	 * 
	* @Title: findStudentByPage 
	* @Description: 根据姓名证件号查学员
	* @return
	* @return Student    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	Student findByCode(Student search);
	
	/**
	 * 
	* @Title: findStudentByPage 
	* @Description: 根据姓名证件号查学员
	* @return
	* @return Student    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	Student findByCode2(Map<String,String> map);
	
	/**
	 * 
	* @Title: findStudentList 
	* @Description: 检查用户信息是否重复
	* @return
	* @return List<Student>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	public List<Student> checkUserInfo(Student search);
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 补交费用时查询一些学员信息
	 * @author 权飞虎
	 * @date 2015年4月25日 下午5:14:04
	 * @modifier
	 * @modify-date 2015年4月25日 下午5:14:04
	 * @version 1.0
	 * @param mobile
	 * @return
	 */
	Student findMessageByMobile(String mobile,Integer companyId,Integer schoolId);
	
	/**
	 * 
	* @Title: findStudentByPage 
	* @Description: 根据手机号查学员
	* @return
	* @return List<StudentVo>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	public Student findByMobile(Student student);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 根据分校id查询 学生数量
	 * @author 周文斌
	 * @date 2015-5-9 下午4:21:53
	 * @version 1.0
	 * @param schoolId
	 * @return
	 */
	Integer findCountBySchoolId(Integer schoolId);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 根据班号id 查询
	 * @author 周文斌
	 * @date 2015-5-15 下午7:21:01
	 * @version 1.0
	 * @param noId
	 * @return
	 */
	List<StuVo> findStuByNoId(StuVo stuVo);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 查询总数根据 班号id
	 * @author 周文斌
	 * @date 2015-5-15 下午7:42:15
	 * @version 1.0
	 * @param stuVo
	 * @return
	 */
	Integer findCountByNoId(StuVo stuVo);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 根据班型id 查询
	 * @author 周文斌
	 * @date 2015-6-8 上午11:22:35
	 * @version 1.0
	 * @param companyStudentMessage
	 * @return
	 */
	List<Student> findByPayMaster(CompanyStudentMessage companyStudentMessage);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 根据班号id查询
	 * @author 周文斌
	 * @date 2015-6-8 上午11:23:30
	 * @version 1.0
	 * @param companyStudentMessage
	 * @return
	 */
	List<Student> findByPaySlave(CompanyStudentMessage companyStudentMessage);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 根据电话查询
	 * @author 周文斌
	 * @date 2015-6-8 下午1:07:22
	 * @version 1.0
	 * @param phone
	 * @return
	 */
	Student findByPhone(Map<String,Object> param);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 查询多课程支持状态
	 * @author 周文斌
	 * @date 2015-9-16 下午9:04:07
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findClassMore(Map<String,Object> param);
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 添加学生  支持EXCEL批量添加
	 * @author 杨延博
	 * @date 2015-9-16 下午9:04:07
	 * @version 1.0
	 * @param Student
	 * @return
	 */
	
	String insertSupportExcel(Student student, boolean isUserFront);
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 查询学生  支持EXCEL批量查询
	 * @author 杨延博
	 * @date 2015-10-12 下午9:04:07
	 * @version 1.0
	 * @param Student
	 * @return
	 */
	
	String querySupportExcel(Student student);
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 通过手机查询学生
	 * @author 杨延博
	 * @date 2015-9-16 下午9:04:07
	 * @version 1.0
	 * @param Student
	 * @return
	 */
	Student queryByMobile(Student student);
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 查询学员列表
	 * @author zhang.zx
	 * @date 2015年9月29日 下午4:38:52
	 * @modifier
	 * @modify-date 2015年9月29日 下午4:38:52
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder2<StudentListVo> findStudentsList(StudentListVo search);
	PageFinder2<StudentListVo> findNewStudentsList(StudentListVo search);
	
	PageFinder<StudentListVo> findStudentsList1(StudentListVo search);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 条件查询学员信息
	 * @author zhang.zx
	 * @date 2015年10月9日 上午11:07:18
	 * @modifier
	 * @modify-date 2015年10月9日 上午11:07:18
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<StudentListVo> findStudentsData(StudentListVo search);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 通过身份证查询学生
	 * @author 杨延博
	 * @date 2015-9-16 下午9:04:07
	 * @version 1.0
	 * @param Student
	 * @return
	 */
	Student queryByIdentityId(Student student);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 代报考导出学员信息
	 * @author zhang.zx
	 * @date 2015年10月16日 下午5:19:14
	 * @modifier
	 * @modify-date 2015年10月16日 下午5:19:14
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<StudentVo> findListByPayments(StudentVo search);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 查询当前公司下最大学员id
	 * @author zhang.zx
	 * @date 2016年1月12日 下午2:17:32
	 * @modifier
	 * @modify-date 2016年1月12日 下午2:17:32
	 * @version 1.0
	 * @param id
	 * @return
	 */
	Integer queryMaxIdByCompany(Integer id);
	
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 条件查询导入学员信息
	 * @author zhang.zx
	 * @date 2016年1月12日 下午2:17:36
	 * @modifier
	 * @modify-date 2016年1月12日 下午2:17:36
	 * @version 1.0
	 * @param map
	 * @return
	 */
	List<Student> queryImportStudentsBycondition(Map<String, Object> map);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 查询课程下所有学员
	 * @author zhang.zx
	 * @date 2016年3月7日 下午5:36:17
	 * @modifier
	 * @modify-date 2016年3月7日 下午5:36:17
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<StudentListVo> queryStudentsListByClassTypeId(StudentListVo search);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 查询学员是否存在
	 * @author zhang.zx
	 * @date 2016年3月10日 上午11:51:00
	 * @modifier
	 * @modify-date 2016年3月10日 上午11:51:00
	 * @version 1.0
	 * @return
	 */
	Student queryStuIsExists(Student stu);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 批量导入学员
	 * @author DELL.COM
	 * @date 2016年3月11日 下午6:45:57
	 * @modifier
	 * @modify-date 2016年3月11日 下午6:45:57
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<StudentListVo> queryStudentsList2(StudentListVo search);
	/**
	 * 查看学员在某一个视频课程下的做题记录
	 */
	PageFinder<StudentTiKuExcipseVo> queryStudentTikuExperise(StudentTiKuExcipseVo search);
	/**
	 * 查询公司所有的题库
	 */
	List<StudentTiKuOrSubjectVo> queryCommpanyTiku(StudentTiKuOrSubjectVo search);
	/**
	 * 查询题库所对应的科目
	 */
	List<StudentTiKuOrSubjectVo> queryCommpanySubject(StudentTiKuOrSubjectVo search);
	/**
	 * 单班号-查看学员在某课程下的直播或者面授以及视频的课次学习记录 
	 */
	PageFinder<StudentClassLeanDetailVo> queryStudentClassLeanRecord(StudentClassLeanDetailVo search);
	/**
	 * 多班号-查看学员在某课程下的直播或者面授以及视频的课次学习记录 
	 */
	PageFinder<StudentClassLeanDetailVo> queryStudentMoreClassLeanRecord(StudentClassLeanDetailVo codition);
	/**
	 * 查询注册时间，购买时间，提问和评价数量统计
	 */
	StudentClassLeanRecordVo queryStudentLeanRecord(Integer stuId, Integer classTypeId);
	/**
	 * 查询视频课的学习记录详情
	 */
	PageFinder<StudentClassLeanDetailVo> queryStudentLecLeanDetail(StudentClassLeanDetailVo codition);
	/**
	 * 查询直播课的学习记录详情
	 */
	PageFinder<StudentClassLeanDetailVo> queryStudentLessLeanDetail(StudentClassLeanDetailVo codition);
	/**
	 * 所有的直播/面授课次数
	 */
	List<StudentLessTimeVo> queryLessionCount(StudentClassLeanDetailVo codition);
	/**
	 * 所有的视频课次数
	 */
	Integer queryLecCount(StudentClassLeanDetailVo codition);
	/**
	 * 已经完成的视频课次
	 */
	Integer queryLecFinishCount(StudentClassLeanDetailVo codition);
	/**
	 * 更新导入学员
	 */
	String updateSupportExcel(Student student, boolean isUserFront);
	/**
	 * 查询购买了课程包的学员列表
	 * @param search
	 * @return
	 */
	List<StudentListVo> findStudentListBuyPackage(StudentListVo search);
	/**
	 * 查询购买了课程包的学员列表数量
	 * @param search
	 * @return
	 */
	int findCountByCommodityId(StudentListVo search);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 查询学员列表(姓名或手机号)
	 * @author zhang.zx
	 * @date 2016年3月29日 下午4:23:51
	 * @modifier
	 * @modify-date 2016年3月29日 下午4:23:51
	 * @version 1.0
	 * @param map
	 * @return
	 */
	List<Student> findStuInfos(Map<String, Object> map);
	/**
	 * 课程包的学习记录
	 */
	PageFinder<StudentClassLeanDetailVo> queryStudentCpLeanRecord(StudentClassLeanDetailVo codition);
	/**
	 * 查询学员的课程包提问以及评价，注册时间，购买时间
	 */
	StudentClassLeanRecordVo findClassPackInfo(Integer classPackageId, Integer stuId);
	/**
	 * 以手机号更新学员
	 * @param student
	 */
	void updateByMobile(Student student);
	/**
	 * 课程包--购买部分阶段后--单班号---的学习记录
	 */
	PageFinder<StudentClassLeanDetailVo> queryStudentCpSomeStageLeanRecord(StudentClassLeanDetailVo codition);
	/**
	 * 课程包--购买整个课程包后--多班号---的学习记录
	 */
	PageFinder<StudentClassLeanDetailVo> queryStudentMoreCpLeanRecord(StudentClassLeanDetailVo codition);
	/**
	 * 课程包--购买部分阶段后--多班号---的学习记录
	 */
	PageFinder<StudentClassLeanDetailVo> queryStudentMoreCpSomeStageLeanRecord(StudentClassLeanDetailVo codition);
	/**
	 * 检查购买的是整个课程包还是课程包内的部分阶段
	 */
	List<Integer> findHasBuyStage(Integer stuId, Integer classPackeId);
	/**
	 * 课程包  所有的直播/面授课次数
	 */
	List<StudentLessTimeVo> queryCpMoreNoLessionCount(StudentClassLeanDetailVo codition);
	/**
	 * 课程包  所有的视频课次数
	 */
	Integer queryCpLecCount(StudentClassLeanDetailVo codition);
	/**
	 *课程包   已经完成的视频课次
	 */
	Integer queryCpLecFinishCount(StudentClassLeanDetailVo codition);
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 导出数据
	 * @author DELL.COM
	 * @date 2016年4月13日 上午1:01:04
	 * @modifier
	 * @modify-date 2016年4月13日 上午1:01:04
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<StudentListVo> exportClassPackageByCommodityId(StudentListVo search);
	/**
	 * 课程或者课程包学生学习记录
	 */
	PageFinder<StudentClassLeanDetailVo> queryStudentCtOrCpLeanRecord(StudentClassLeanDetailVo codition);

	StudentClassLeanRecordVo queryStudentQuestionCount1(Integer stuId, Integer classTypeId);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 导入
	 * @author zhang.zx
	 * @date 2016年6月2日 上午11:59:32
	 * @modifier
	 * @modify-date 2016年6月2日 上午11:59:32
	 * @version 1.0
	 * @param list
	 * @return
	 */
	String importStuList(List<Student> list,String mark,CompanyRegisterConfig crc);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 查询定制学员数据
	 * @author zhang.zx
	 * @date 2016年6月14日 下午7:58:19
	 * @modifier
	 * @modify-date 2016年6月14日 下午7:58:19
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<StudentListDataVo> queryPayListByStuId(StudentListVo search);
	
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 根据用户查询学员
	 * @author zhang.zx
	 * @date 2016年6月28日 下午1:57:48
	 * @modifier
	 * @modify-date 2016年6月28日 下午1:57:48
	 * @version 1.0
	 * @param map
	 * @return
	 */
	List<Student> findStuInfoByUserId(Map<String, Object> map);
	
	Student queryByUsername(Student search);
	Integer checkUsersFrontByUsername(Student search);
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description:条件查询用户信息
	 * @author zhang.zx
	 * @date 2016年7月13日 上午10:16:04
	 * @modifier
	 * @modify-date 2016年7月13日 上午10:16:04
	 * @version 1.0
	 * @param search
	 * @return
	 */
	Student queryStuInfoByWhere(Student search);

	public PageFinder<StudentVo> findPageListByPaymentNew(StudentVo search);
	
	List<StudentListVo> queryStudentsListByClassTypeId2(StudentListVo search);

	Integer findByGroupId(Map<String, Object> map);

	List<Student> findGroupStu(Map<String, Object> map);

	Student findByEmail(Student s);

	UsersFrontVo findUserFrontvoByStuId(Integer sId);
	
	Integer findStudentCountOnlyByMobile(SelectStudentOrUsersfrontVo search);
	
	List<StudentImportVo> queryAllStudentsByCompanyId(Integer companyId);
	
	List<Integer> insertMoreStudents(List<StudentImportVo> students,String groupOneId,String groupTwoId,Integer userId);
	
	List<StudentListVo> queryStudentsListByIds(String ids);
	
	/**
	 * 
	 * @author jishangyang 2017年12月17日 下午5:47:36
	 * @Method: findClassByTeacherId 
	 * @Description: TODO
	 * @param 查询班主任所在班级
	 * @return 
	 * @throws
	 */
	List<Student> findClassByTeacherId(Integer id);
	List<SysConfigDict> findEduAreaList();
}