package com.yuxin.wx.student.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.EduMasterClass;
import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.vo.student.SelectStudentOrUsersfrontVo;
import com.yuxin.wx.vo.student.StuVo;
import com.yuxin.wx.vo.student.StudentClassLeanDetailVo;
import com.yuxin.wx.vo.student.StudentClassLeanRecordVo;
import com.yuxin.wx.vo.student.StudentImportVo;
import com.yuxin.wx.vo.student.StudentLessTimeVo;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.student.StudentTiKuExcipseVo;
import com.yuxin.wx.vo.student.StudentTiKuOrSubjectVo;
import com.yuxin.wx.vo.student.StudentVo;
import com.yuxin.wx.vo.user.UsersFrontVo;
import org.apache.ibatis.annotations.MapKey;

/**
 * Service Interface:Student
 * @author wang.zx
 * @date 2014-12-5
 */
public interface StudentMapper extends BaseMapper<Student> {
	List<StudentVo> queryListByPayment(StudentVo search);
	List<StudentVo> findListByPayment(StudentVo search);
	List<StudentVo> queryListByPayment2(StudentVo search);
	List<StudentVo> findListByPayment2(StudentVo search);
	List<StudentVo> queryListByPayment3(StudentVo search);
	List<StudentVo> queryListByClassType(ClassType search);
	List<StudentVo> queryListByClassNo(ClassModuleNo search);
	List<Student> queryList(Student search);
	Integer queryCount(Student search);
	Student queryByCode(Student student);
	Student queryByCode2(Map<String,String> map);
	Integer pageCount2(StudentVo search);
	Integer pageCount3(StudentVo search);
	Integer pageCount4(StudentVo search);
	Student findMessageByMobile(Map<String, Object> map);
	Student queryByMobile(Student search);
	
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

	
	//学员列表
	List<StudentListVo> queryStudentsList(StudentListVo search);
	
	List<StudentListVo> queryAllStudentsList(StudentListVo search);
	Integer queryAllStudentsListCount(StudentListVo search);
	
	List<StudentListVo> queryAreaStudentsList(StudentListVo search);
	Integer queryAreaStudentsListCount(StudentListVo search);
	
	List<StudentListVo> querySchoolStudentsList(StudentListVo search);
	Integer querySchoolStudentsListCount(StudentListVo search);
	
	List<StudentListVo> queryUserListData(StudentListVo search);
	List<Map<String, Object>> exportUserInfo(StudentListVo search);
	Integer queryStudentsListCount(StudentListVo search);
	Integer queryUserListDataCount(StudentListVo search);
	
	List<StudentListVo> queryNewStudentsList(StudentListVo search);
	Integer queryNewStudentsListCount(StudentListVo search);

	List<StudentListVo> queryStudentsList1(StudentListVo search);
	Integer queryStudentsListCount1(StudentListVo search);
	/**
	 * 
	 * Class Name: IStudentService.java
	 * @Description: 根据身份证号查询
	 * @author 杨延博
	 * @date 2015-9-29 下午9:04:07
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Student queryByIdentityId(Student search);
	/**
	 * 
	 * @fileName : StudentMapper.java
	 * @author   : 杨延博
	 * @date     : 2015年10月29日下午5:16:41
	 * @description : 根据userId查询
	 */
	Student findByUserId(Integer id);
	
	Integer queryMaxIdByCompany(Integer id);
	
	List<Student> queryImportStudentsBycondition(Map<String, Object> map);
	
	List<StudentListVo> queryStudentsListByClassTypeId(StudentListVo search);
	
	Integer queryStudentsListCountByClassId(StudentListVo search);
	
	List<StudentListVo> queryStudentsList2(StudentListVo search);

	/**
	 * 查看学员在某一个视频课程下的做题记录
	 */
	List<StudentTiKuExcipseVo> queryStudentTikuExperise(StudentTiKuExcipseVo search);
	Integer queryStudentTikuExperiseCount(StudentTiKuExcipseVo search);
	/**
	 * 查询公司所有的题库
	 */
	List<StudentTiKuOrSubjectVo> queryCommpanyTiku(StudentTiKuOrSubjectVo search);
	/**
	 * 查询题库所对应的科目
	 */
	List<StudentTiKuOrSubjectVo> queryCommpanySubject(StudentTiKuOrSubjectVo search);
	/**
	 * 单班号--查看学员在某课程下的直播或者面授以及视频的课次学习记录 
	 */
	List<StudentClassLeanDetailVo> queryStudentClassLeanRecord(StudentClassLeanDetailVo codition);
	Integer queryStudentClassLeanRecordCount(StudentClassLeanDetailVo codition);
	StudentClassLeanRecordVo queryStudentLeanRecord(Map<String, Object> map);
	/**
	 * 多班号--查看学员在某课程下的直播或者面授以及视频的课次学习记录 
	 */
	List<StudentClassLeanDetailVo> queryStudentMoreClassLeanRecord(StudentClassLeanDetailVo codition);
	Integer queryStudentMoreClassLeanRecordCount(StudentClassLeanDetailVo codition);
	/**
	 * 查询视频课的学习记录详情
	 */
	List<StudentClassLeanDetailVo> queryStudentLecLeanDetail(StudentClassLeanDetailVo codition);
	Integer queryStudentLecLeanDetailCount(StudentClassLeanDetailVo codition);
	/**
	 * 查询直播课的学习记录详情
	 */
	List<StudentClassLeanDetailVo> queryStudentLessLeanDetail(StudentClassLeanDetailVo codition);
	Integer queryStudentLessLeanDetailCount(StudentClassLeanDetailVo codition);
	List<StudentLessTimeVo> queryLessionCount(StudentClassLeanDetailVo codition);
	Integer queryLecCount(StudentClassLeanDetailVo codition);
	Integer queryLecFinishCount(StudentClassLeanDetailVo codition);
	/**
	 * 查询学员在某课次下的提问次数
	 */
	Integer queryStudentQuestionCount(Map<String, Object> map);
	/**
	 * 查询学员在某班型下的评价次数
	 */
	Integer queryStudentCommentCount(Map<String, Object> map);
	/**
	 * 查询学员在某班型下的最早报名次数
	 */
	String queryStudentBaomingTime(Map<String, Object> map);
	List<StudentListVo> findClassPackageByCommodityId(StudentListVo search);
	Integer findCountByCommodityId(StudentListVo search);
	
	List<Student> findStuInfos(Map<String, Object> map);
	
	/**
	 * 课程包的学习记录
	 */
	List<StudentClassLeanDetailVo> queryStudentCpLeanRecord(StudentClassLeanDetailVo codition);
	Integer queryStudentCpLeanRecordCount(StudentClassLeanDetailVo codition);
	/**
	 * 查询学员的课程包提问以及评价，注册时间，购买时间
	 */
	StudentClassLeanRecordVo findClassPackInfo(Map<String, Object> map);
	Integer findStuClassPackageQuesCount(Map<String, Object> map);
	Integer findStuClassPacageCommentCount(Map<String, Object> map);
	
	void updateByMobile(Student student);
	String queryStudentCpBaomingTime(Map<String, Object> map);
	/**
	 * 课程包--购买部分阶段后--单班号---的学习记录
	 */
	List<StudentClassLeanDetailVo> queryStudentCpSomeStageLeanRecord(StudentClassLeanDetailVo codition);
	Integer queryStudentCpSomeStageLeanRecordCount(StudentClassLeanDetailVo codition);
	/**
	 * 课程包--购买整个课程包后--多班号---的学习记录
	 */
	Integer queryStudentMoreCpLeanRecordCount(StudentClassLeanDetailVo codition);
	List<StudentClassLeanDetailVo> queryStudentMoreCpLeanRecord(StudentClassLeanDetailVo codition);
	/**
	 * 课程包--购买部分阶段后--多班号---的学习记录
	 */
	List<StudentClassLeanDetailVo> queryStudentMoreCpSomeStageLeanRecord(StudentClassLeanDetailVo codition);
	Integer queryStudentMoreCpSomeStageLeanRecordCount(StudentClassLeanDetailVo codition);
	/**
	 * 检查购买的是整个课程包还是课程包内的部分阶段
	 */
	List<Integer> findHasBuyStage(Map<String, Object> map);
	/**
	 * 课程包 所有的直播/面授课次数
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
	
	List<StudentListVo> exportClassPackageByCommodityId(StudentListVo search);
	/**
	 * 课程或者课程包学生学习记录
	 */
	List<StudentClassLeanDetailVo> queryStudentCtOrCpLeanRecord(StudentClassLeanDetailVo codition);
	Integer queryStudentCtOrCpLeanRecordCount(StudentClassLeanDetailVo codition);
	
	List<Student> findStuInfoByUserId(Map<String, Object> map);
	
	Student queryByUsername(Student search);
	Integer checkUsersFrontByUsername(Student search);
	Student queryStuInfoByWhere(Student search);
	Student queryStuInfoByUserId(Student search);
	
	
	List<StudentVo> queryListByPaymentNew(StudentVo search);
	Integer pageCount2New(StudentVo search);
	List<StudentListVo> queryStudentsListByClassTypeId2(StudentListVo search);
	Integer findByGroupId(Map<String, Object> map);
	List<Student> findGroupStu(Map<String, Object> map);
	Student findByEmail(Student s);
	UsersFrontVo findUserFrontvoByStuId(Integer sId);
	
	Integer findStudentCountOnlyByMobile(SelectStudentOrUsersfrontVo search);
	
	List<StudentImportVo> queryAllStudentsByCompanyId(Integer companyId);
	
	Student findStudentOnlyByMobile(SelectStudentOrUsersfrontVo search);
	
	Student findStudentOnlyByUserId(Integer id);
	
	List<StudentListVo> queryStudentsListByIds(Integer[] ids);
	/**
	 * 
	 * @author jishangyang 2017年12月17日 下午5:52:45
	 * @Method: findClassByTeacherId 
	 * @Description: 通过班主任ID 查询班级
	 * @param id
	 * @return 
	 * @throws
	 */
	List<EduMasterClass> findClassByTeacherId(EduMasterClass ets);
	/**
	 * 查询任课教师信息
	 * @param ets 
	 * @return
	 */
	List<EduMasterClass> findSubjectClassByTeacherId(EduMasterClass ets);
	/**
	 * 
	 * @author jishangyang 2017年12月20日 下午5:57:30
	 * @Method: findClassByRKTeacherId 
	 * @Description: 查询任课教师
	 * @param id
	 * @return 
	 * @throws
	 */
	List<EduMasterClass> findClassByRKTeacherId(Integer id);
	List<SysConfigDict> sysConfigDict();
	List<StudentListVo> queryStudentsListTwo(StudentListVo search);

	List<String> queryAllStudents(Integer companyId);
}