package com.yuxin.wx.student.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassPackageCategory;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.vo.course.OrderManage;
import com.yuxin.wx.vo.student.StuClassTypeVoList;
import com.yuxin.wx.vo.student.StuPayMasterVo;
import com.yuxin.wx.vo.student.StuPaymasterVoList;
import com.yuxin.wx.vo.student.StudentClassLeanDetailVo;
import com.yuxin.wx.vo.student.StudentListDataVo;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.student.StudentPayMaster4ClassPackageVo;
import com.yuxin.wx.vo.student.StudentPayMasterVo4;
import com.yuxin.wx.vo.student.StudentVo;
/**
 * Service Interface:StudentPayMaster
 * @author wang.zx
 * @date 2014-12-5
 */
public interface StudentPayMasterMapper extends BaseMapper<StudentPayMaster> {
	List<StudentPayMaster> findByStuId(Map map);
	
	
	Integer insertPayMaster(StudentPayMaster payMaster);
	
	List<StudentVo> findListByTerm(StudentVo search);
	List<StudentVo> queryListByTerm(StudentVo search);
	
	List<StudentPayMaster> queryListByClassType(StudentPayMaster payMaster);
	Integer pageCountClassType(StudentPayMaster payMaster);
	
	List<StudentVo> queryListByClassType2(StudentPayMaster payMaster);
 	Integer pageCountClassType2(StudentPayMaster payMaster);


 	List<StudentPayMaster> findByStuMbile(Map<String, Object> map);

	List<StudentPayMaster> findUnPayMasterByStuId(Student student);


	List<StudentPayMaster> findByStuMbile2(Map<String, Object> map);


	List<StudentPayMaster> findEntryMessage(StudentPayMaster payMaster);


	List<StudentPayMaster> findList(StudentPayMaster payMaster);


	Integer getCount(StudentPayMaster payMaster);
	
	List<StuPaymasterVoList> queryClassTypeByStuId(StuPaymasterVoList stuPaymasterVoList);
	
	StuClassTypeVoList queryClasstypeById(StuClassTypeVoList search);
	
	List<StudentPayMaster> findByClassAndStu(StudentPayMaster payMaster);

	   /**
	    * 
	    * Class Name: IStudentPayMasterService.java
	    * @Description: 根据资源 查询  总数
	    * @author 周文斌
	    * @date 2015-6-5 下午2:53:02
	    * @version 1.0
	    * @param param
	    * @return
	    */
	   Integer findByResourceid(Map<String,Object> param);
	   
	List<StuPayMasterVo> stuPayMasterList(StuPayMasterVo search);
	
	Integer stuPayMasterListCount(StuPayMasterVo search);
	
	List<StudentPayMaster> findByStuMbilestu(Map<String, Object> map);
	
	List<Map> countUserByDate(StuPayMasterVo search);
	
	Integer findCountByPayMaster(StudentPayMaster payMaster);
	
	List<Map> queryorderTotalMoney(StuPayMasterVo search);

    /**
     * 
     * Class Name: IStudentPayMasterService.java
     * @Description: 查询公司招生数
     * @author 周文斌
     * @date 2015-6-25 上午10:43:36
     * @version 1.0
     * @param companyId
     * @return
     */
    Integer findStuCountByCompanyId(Integer companyId);

    /**
     * 
     * Class Name: IStudentPayMasterService.java
     * @Description: 根据schoolId 查询人数
     * @author 周文斌
     * @date 2015-9-9 上午11:04:27
     * @version 1.0
     * @param companyId
     * @param schoolId
     * @return
     */
    Integer findStuCountBySchoolId(Integer companyId,Integer schoolId);
    
    /**
     * 
     * Class Name: StudentPayMasterMapper.java
     * @Description: 根据学员查询订单
     * @author zhang.zx
     * @date 2015年12月24日 下午4:20:47
     * @modifier
     * @modify-date 2015年12月24日 下午4:20:47
     * @version 1.0
     * @param stuId
     * @return
     */
    List<StudentPayMaster> findpayIdByStudentsId(Map<String, Object> map);
    
    List<StudentPayMaster> findpayIdByClassTypeStudent(Map<String, Object> map);
    
    void updateByStuId(StudentPayMaster payMaster);
    
    List<StudentPayMaster> queryStusByClassPackage(StudentPayMaster payMaster);
    
    List<StudentPayMaster> findByCommodityId(StudentListVo search);
    
    List<OrderManage> queryClassPackageOrder(OrderManage search);
    
    Integer queryClassPackageOrderCount(OrderManage search);
    
    List<OrderManage> queryClassPackageTotalOrder(OrderManage search);
    
    String queryLastApplytime(Integer stuId);


	List<StudentPayMasterVo4> queryStuHasByClass(StudentClassLeanDetailVo search);

	StudentPayMaster findOrder(Map map);
	
	List<StudentListDataVo> queryOrderCourseByStuId(StudentListVo search);
	
	Integer queryCommodityIdById(Integer id);
	
	List<StudentPayMaster> findByClassPackageAndStu(StudentPayMaster search);
	
	Integer queryPayMasterBuyCommodityByStuId(Map<String, Object> map);
	
	Integer queryPayMasterBuyCommodityByStuIdAndCommodityId(Map<String, Object> map);
	
	List<StudentPayMaster4ClassPackageVo> findClassPackageEntryMessage(StudentPayMaster spm);
	
	ClassPackageCategory selectClassPackageCategoryCode(Map<String, Object> map);


	Integer selectStuNumByComId(Integer comId);
}