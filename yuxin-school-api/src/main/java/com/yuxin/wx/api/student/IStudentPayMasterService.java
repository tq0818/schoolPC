package com.yuxin.wx.api.student;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassPackageCategory;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.model.student.StudentFeeRefund;
import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.model.student.StudentPayChangeInfo;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.student.StuClassTypeVoList;
import com.yuxin.wx.vo.student.StuPayMasterVo;
import com.yuxin.wx.vo.student.StuPaymasterVoList;
import com.yuxin.wx.vo.student.StudentClassLeanDetailVo;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.student.StudentPayMaster4ClassPackageVo;
import com.yuxin.wx.vo.student.StudentPayMasterVo4;
import com.yuxin.wx.vo.student.StudentVo;
/**
 * Service Interface:StudentPayMaster
 * @author wang.zx
 * @date 2014-12-5
 */
public interface IStudentPayMasterService  {
	/**
	 * 
	* @Title: saveStudentPayMaster
	* @Description: 新增StudentPayMaster
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(StudentPayMaster studentPayMaster);
	
	/**
	 * 
	* @Title: batchSaveStudentPayMaster 
	* @Description: 批量新增StudentPayMaster
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<StudentPayMaster> studentPayMaster);
	
	/**
	 * 
	* @Title: updateStudentPayMaster 
	* @Description: 编辑StudentPayMaster
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(StudentPayMaster studentPayMaster);
	
	/**
	 * 
	* @Title: deleteStudentPayMasterById 
	* @Description: 根据id删除StudentPayMaster
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteStudentPayMasterById(Integer id);
	
	/**
	 * 
	* @Title: deleteStudentPayMasterByIds 
	* @Description: 根据id批量删除StudentPayMaster
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteStudentPayMasterByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findStudentPayMasterById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	StudentPayMaster findStudentPayMasterById(Integer id);
	
	/**
	 * 
	* @Title: findStudentPayMasterById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<StudentPayMaster> findStudentPayMasterByStuId(Integer stuId,Integer companyId);
	
	/**
	 * 
	* @Title: findStudentPayMasterByPage 
	* @Description: 分页查询
	* @return
	* @return List<StudentPayMaster>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<StudentPayMaster> findStudentPayMasterByPage(StudentPayMaster search);
	
	/**
	 * 
	* @Title: savePayMaster
	* @Description: 新增StudentPayMaster
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-25
	* @user by chopin
	 */
	Boolean savePayMaster(StudentPayMaster PayMaster,Users user,List<StudentAgentMaterial> material,List<StudentFeeStage> stages);
	
	/**
	 * 
	* @Title: saveStudentPayMaster
	* @Description: 新增StudentPayMaster
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-25
	* @user by chopin
	 */
	public void newPayMaster(StudentPayMaster payMaster,Users user,List<StudentPaySlave> slaves);
	
	/**
	 * 
	* @Title: updatePayMaster 
	* @Description: 分页查询
	* @return
	* @return List<StudentPayMaster>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	public void updatePayMaster(StudentPayMaster payMaster,Users user,List<StudentPaySlave> slaves);
	
	/**
	 * 
	* @Title: changeClass
	* @Description: 转班
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-6
	* @user by chopin
	 */
	public String fullChangeClass(StudentPayMaster payMaster, Integer oMasterId,
			Users user, List<StudentPaySlave> slaves,
			List<StudentAgentMaterial> material, List<StudentFeeStage> nstages,StudentFeeRefund refund);
	
	/**
	 * 
	* @Title: changeClass
	* @Description: 转班
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-6
	* @user by chopin
	 */
	public String changeClass(StudentPayMaster payMaster, Integer oMasterId,
			Users user,
			List<StudentAgentMaterial> material, List<StudentFeeStage> nstages,StudentFeeRefund refund);
	
	/**
	 * 
	* @Title: changeClass
	* @Description: 转班
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-25
	* @user by chopin
	 */
	String changeClass_bak(StudentPayMaster payMaster,Integer oMasterId,Users user,List<StudentPaySlave> slaves);
	
	String changeStudent(Users user,Student student,StudentPayChangeInfo changeInfo,Integer mid);
	String unWind(Users user,StudentPayChangeInfo changeInfo,Integer mid);
	
 /**
  * 
 * @Title: changeClass
 * @Description: 根据考期和资料查询订单列表
 * @return void    返回类型 
 * @throws 
 * @exception 
 * @date 2014-12-25
 * @user by chopin
  */
 PageFinder<StudentVo> findListByTerm(StudentVo search);
 
 /**
  * 
 * @Title: changeClass
 * @Description: 导出到excel
 * @return void    返回类型 
 * @throws 
 * @exception 
 * @date 2014-12-25
 * @user by chopin
  */
 
  List<StudentVo> exportToExcel(StudentVo search);
/**
 * 
 * Class Name: IStudentPayMasterService.java
 * @Description:根据手机号查询该公司的学员未付款订单
 * @author 权飞虎
 * @date 2015年4月25日 下午3:43:22
 * @modifier
 * @modify-date 2015年4月25日 下午3:43:22
 * @version 1.0
 * @param mobile
 * @return
 */
List<StudentPayMaster> findByStuMbile(String mobile,Integer companyId,Integer schoolId);
/**
 * 
 * Class Name: IStudentPayMasterService.java
 * @Description: 异动时查询信息
 * @author 权飞虎
 * @date 2015年5月4日 下午12:24:12
 * @modifier
 * @modify-date 2015年5月4日 下午12:24:12
 * @version 1.0
 * @param mobile
 * @param currentCompanyId
 * @return
 */
List<StudentPayMaster> findByStuMbile2(String mobile, Integer currentCompanyId,Integer schoolId);

/**
 * 
* @Title: changeClass
* @Description: 查询未缴费订单
* @return void    返回类型 
* @throws 
* @exception 
* @date 2014-12-25
* @user by chopin
 */
public List<StudentPayMaster> findUnPayMasterByStuId(Student student);
/**
 * 
 * Class Name: IStudentPayMasterService.java
 * @Description: 查询该学生所有报名信息
 * @author 权飞虎
 * @date 2015年5月4日 下午3:26:02
 * @modifier
 * @modify-date 2015年5月4日 下午3:26:02
 * @version 1.0
 * @param stuId
 * @param currentCompanyId
 * @return
 */
List<StudentPayMaster> findEntryMessage(Integer stuId, Integer currentCompanyId);

/**
 * 
* @Title: changeClass
* @Description: 增加订单分期缴费信息
* @return void    返回类型 
* @throws 
* @exception 
* @date 2014-12-25
* @user by chopin
 */
public void newStage(StudentPayMaster payMaster,Users user,List<StudentFeeStage>stages);
/**
 * 
 * Class Name: IStudentPayMasterService.java
 * @Description: 分页条件查询
 * @author admin
 * @date 2015年5月15日 下午5:53:45
 * @modifier
 * @modify-date 2015年5月15日 下午5:53:45
 * @version 1.0
 * @param payMaster
 * @return
 */
PageFinder<StudentPayMaster> findList(StudentPayMaster payMaster);
	/**
	 * 
	 * Class Name: IStudentPayMasterService.java
	 * @Description: 根据学员查询班型
	 * @author zhang.zx
	 * @date 2015年6月1日 下午8:48:43
	 * @modifier
	 * @modify-date 2015年6月1日 下午8:48:43
	 * @version 1.0
	 * @param stuPaymasterVoList
	 * @return
	 */
   List<StuPaymasterVoList> queryClassTypeByStuId(StuPaymasterVoList stuPaymasterVoList);
   
   /**
    * 
    * Class Name: IStudentPayMasterService.java
    * @Description: 报考材料班型查询
    * @author zhang.zx
    * @date 2015年6月2日 上午11:52:14
    * @modifier
    * @modify-date 2015年6月2日 上午11:52:14
    * @version 1.0
    * @param search
    * @return
    */
   StuClassTypeVoList queryClasstypeById(StuClassTypeVoList search);
   
   /**
    * 
    * Class Name: IStudentPayMasterService.java
    * @Description: 条件查询订单信息
    * @author zhang.zx
    * @date 2015年6月4日 上午11:34:05
    * @modifier
    * @modify-date 2015年6月4日 上午11:34:05
    * @version 1.0
    * @param search
    * @return
    */
   PageFinder<StuPayMasterVo> findStudentPayMasterList(StuPayMasterVo search); 

   /**
    * 
    * Class Name: IStudentPayMasterService.java
    * @Description: 查询总人数
    * @author 周文斌
    * @date 2015-6-5 下午2:31:34
    * @version 1.0
    * @param payMaster
    * @return
    */
   Integer getCount(StudentPayMaster payMaster);
	
   /**
    * 
    * Class Name: IStudentPayMasterService.java
    * @Description: 根据班号查询人数
    * @author 周文斌
    * @date 2015-6-5 下午5:32:05
    * @version 1.0
    * @param payMaster
    * @return
    */
   Integer findByResourceid(Map<String,Object> param);
   
   /**
    * 
    * Class Name: IStudentPayMasterService.java
    * @Description: 报考材料根据手机号查询订单
    * @author zhang.zx
    * @date 2015年6月8日 下午10:56:44
    * @modifier
    * @modify-date 2015年6月8日 下午10:56:44
    * @version 1.0
    * @param map
    * @return
    */
    List<StudentPayMaster> findByStuMbilestu(Integer stuId,Integer companyId,Integer schoolId);
    
    /**
     * 
     * Class Name: IStudentPayMasterService.java
     * @Description: 学员订单折线图
     * @author zhang.zx
     * @date 2015年6月9日 下午5:53:44
     * @modifier
     * @modify-date 2015年6月9日 下午5:53:44
     * @version 1.0
     * @param search
     * @return
     */
    List<Map> countOrderByDate(StuPayMasterVo stuPayMasterVo);
    
    Integer findCountByPayMaster(StudentPayMaster payMaster);
    
    /**
     * 
     * Class Name: IStudentPayMasterService.java
     * @Description: 查询订单合计金额
     * @author zhang.zx
     * @date 2015年6月12日 下午12:20:34
     * @modifier
     * @modify-date 2015年6月12日 下午12:20:34
     * @version 1.0
     * @param search
     * @return
     */
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
     * @Description: 根据学员ID和班型查询订单
     * @author 周文斌
     * @date 2015-6-25 上午10:43:36
     * @version 1.0
     * @param companyId
     * @return
     */
    List<StudentPayMaster> findByClassTypeAndStu(StudentPayMaster payMaster);
    
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
     * @fileName : IStudentPayMasterService.java
     * @date : 2015年10月21日 下午6:47:22
     * @author :　杨延博
     * @description : 批量添加订单复杂版
     */
	void newPayMasterMany(StudentPayMaster payMaster, Users user,
			List<StudentPaySlave> slaves);
	
	/**
	 * 
	 * Class Name: IStudentPayMasterService.java
	 * @Description: 查询学员订单
	 * @author zhang.zx
	 * @date 2016年3月8日 下午1:35:42
	 * @modifier
	 * @modify-date 2016年3月8日 下午1:35:42
	 * @version 1.0
	 * @param stuId
	 * @return
	 */
	List<StudentPayMaster> findpayIdByClassTypeStudent(Map<String, Object> map);
	
	/**
	 * 
	 * Class Name: IStudentPayMasterService.java
	 * @Description: 查询学员有效订单数
	 * @author zhang.zx
	 * @date 2016年3月10日 上午10:30:05
	 * @modifier
	 * @modify-date 2016年3月10日 上午10:30:05
	 * @version 1.0
	 * @param stuId
	 * @return
	 */
	List<StudentPayMaster> findpayIdByStudentsId(Map<String, Object> map);
	
	/**
	 * 
	 * @Title: deleteByStuId
	 * @Description: 删除学员所有的订单
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-25
	 * @user by chopin
	 */
	public void updateByStuId(StudentPayMaster payMaster);
	
	/**
	 * 
	 * Class Name: IStudentPayMasterService.java
	 * @Description: 查询课程包下报名学员
	 * @author zhang.zx
	 * @date 2016年3月23日 下午6:54:46
	 * @modifier
	 * @modify-date 2016年3月23日 下午6:54:46
	 * @version 1.0
	 * @param payMaster
	 * @return
	 */
	List<StudentPayMaster> queryStusByClassPackage(StudentPayMaster payMaster);
	
	/**
	 * 
	 * Class Name: IStudentPayMasterService.java
	 * @Description: 查询课程包下报名学员
	 * @author zhang.zx
	 * @date 2016年3月23日 下午6:54:46
	 * @modifier
	 * @modify-date 2016年3月23日 下午6:54:46
	 * @version 1.0
	 * @param payMaster
	 * @return
	 */
	public List<StudentPayMaster>  findByCommodityId(StudentListVo search); 
	
	/**
	 * 
	 * @Title: saveStudentPayMaster
	 * @Description: 新增StudentPayMaster
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2014-12-25
	 * @user by chopin
	 */
	public Boolean savePackagePayMaster(StudentPayMaster payMaster, Users user,
			List<StudentAgentMaterial> materials, List<StudentFeeStage> stages);

	List<StudentPayMasterVo4> queryStuHasByClass(StudentClassLeanDetailVo search);
	/**
	 * Class Name: IStudentPayMasterService.java
	 * @Description: 升级会员时，查询订单
	 * @author xukaiqiang
	 * @date 2016年5月30日 下午3:28:14
	 * @modifier
	 * @modify-date 2016年5月30日 下午3:28:14
	 * @version 1.0
	 * @param map
	 * @return
	 */
	StudentPayMaster findOrder(Map map);
 
	/**
	 * 
	 * Class Name: IStudentPayMasterService.java
	 * @Description: 根据id查询commodityId
	 * @author dongshuai
	 * @date 2016年8月8日 下午3:05:51
	 * @modifier
	 * @modify-date 2016年8月8日 下午3:05:51
	 * @version 1.0
	 * @param id
	 * @return
	 */
	Integer queryCommodityIdById(Integer id);

	/**
	 * 
	 * Class Name: IStudentPayMasterService.java
	 * @Description: 验证学员是否买过课程包
	 * @author zhang.zx
	 * @date 2016年8月8日 下午4:43:50
	 * @modifier
	 * @modify-date 2016年8月8日 下午4:43:50
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<StudentPayMaster> findByClassPackageAndStu(StudentPayMaster search);
	
	/**
	 * 
	 * Class Name: IStudentPayMasterService.java
	 * @Description: 根据stuId查询订单（未删除）
	 * @author dongshuai
	 * @date 2016年8月8日 下午4:12:49
	 * @modifier
	 * @modify-date 2016年8月8日 下午4:12:49
	 * @version 1.0
	 * @param map
	 * @return
	 */
	Integer queryPayMasterBuyCommodityByStuId(Map<String, Object> map);
	
	/**
	 * 
	 * Class Name: IStudentPayMasterService.java
	 * @Description: 查询用户是否有某个课程
	 * @author dongshuai
	 * @date 2016年8月8日 下午6:33:53
	 * @modifier
	 * @modify-date 2016年8月8日 下午6:33:53
	 * @version 1.0
	 * @param map
	 * @return
	 */
	Integer queryPayMasterBuyCommodityByStuIdAndCommodityId(Map<String, Object> map);
	
	/**
	 * 
	 * Class Name: IStudentPayMasterService.java
	 * @Description: TODO(学员购买课程包相详情)
	 * @author dongshuai
	 * @date 2016年10月31日 下午4:36:45
	 * @modifier
	 * @modify-date 2016年10月31日 下午4:36:45
	 * @version 1.0
	 * @param spm
	 * @return
	 */
	List<StudentPayMaster4ClassPackageVo> findClassPackageEntryMessage(StudentPayMaster spm);
	
	/**
	 * 
	 * Class Name: IStudentPayMasterService.java
	 * @Description: TODO(获取课程包分类名称)
	 * @author dongshuai
	 * @date 2016年10月31日 下午5:23:30
	 * @modifier
	 * @modify-date 2016年10月31日 下午5:23:30
	 * @version 1.0
	 * @param map
	 * @return
	 */
	ClassPackageCategory selectClassPackageCategoryCode(Map<String, Object> map);

	Integer selectStuNumByComId(Integer comId);
}