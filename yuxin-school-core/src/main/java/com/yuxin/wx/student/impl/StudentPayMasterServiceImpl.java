package com.yuxin.wx.student.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.homework.IHomeworkService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.classes.mapper.ClassModuleNoMapper;
import com.yuxin.wx.classes.mapper.ClassTypeMapper;
import com.yuxin.wx.classes.mapper.ClassTypeModuleRelationMapper;
import com.yuxin.wx.commodity.mapper.CommodityMapper;
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyCashFlowMapper;
import com.yuxin.wx.course.mapper.CourseVideoChapterMapper;
import com.yuxin.wx.course.mapper.CourseVideoLectureMapper;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassPackageCategory;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.company.CompanyCashFlow;
import com.yuxin.wx.model.pay.PayOrder;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.model.student.StudentFeeRefund;
import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.model.student.StudentPayChangeInfo;
import com.yuxin.wx.model.student.StudentPayLog;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.system.SysConfigTerm;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.pay.mapper.PayOrderMapper;
import com.yuxin.wx.student.mapper.StudentAgentMaterialMapper;
import com.yuxin.wx.student.mapper.StudentFeeRefundMapper;
import com.yuxin.wx.student.mapper.StudentFeeStageMapper;
import com.yuxin.wx.student.mapper.StudentMapper;
import com.yuxin.wx.student.mapper.StudentPayChangeInfoMapper;
import com.yuxin.wx.student.mapper.StudentPayLogMapper;
import com.yuxin.wx.student.mapper.StudentPayMasterMapper;
import com.yuxin.wx.student.mapper.StudentPaySlaveMapper;
import com.yuxin.wx.system.mapper.SysConfigSchoolMapper;
import com.yuxin.wx.system.mapper.SysConfigTermMapper;
import com.yuxin.wx.user.mapper.UserLessonTimeMapper;
import com.yuxin.wx.user.mapper.UsersFrontMapper;
import com.yuxin.wx.vo.classes.ClassModuleNoVo;
import com.yuxin.wx.vo.classes.ClassTypeModuleRelationVo;
import com.yuxin.wx.vo.student.StuClassTypeVoList;
import com.yuxin.wx.vo.student.StuPayMasterVo;
import com.yuxin.wx.vo.student.StuPaymasterVoList;
import com.yuxin.wx.vo.student.StudentClassLeanDetailVo;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.student.StudentPayMaster4ClassPackageVo;
import com.yuxin.wx.vo.student.StudentPayMasterVo4;
import com.yuxin.wx.vo.student.StudentVo;

/**
 * Service Implementation:StudentPayMaster
 *
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class StudentPayMasterServiceImpl extends BaseServiceImpl implements IStudentPayMasterService {
    Log log = LogFactory.getLog("log");
    Log log_student = LogFactory.getLog("student");
    @Autowired
    private StudentPayMasterMapper studentPayMasterMapper;
    @Autowired
    private StudentAgentMaterialMapper studentAgentMaterialMapper;
    @Autowired
    private StudentPaySlaveMapper studentPaySlaveMapper;
    @Autowired
    private StudentFeeStageMapper studentFeeStageMapper;
    @Autowired
    private StudentPayChangeInfoMapper studentPayChangeInfoMapper;
    @Autowired
    private StudentPayLogMapper studentPayLogMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SysConfigSchoolMapper sysConfigSchoolMapper;
    @Autowired
    private StudentFeeRefundMapper studentFeeRefundMapper;
    @Autowired
    private UsersFrontMapper usersFrontMapper;
    @Autowired
    private SysConfigTermMapper sysConfigTermMapper;
    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private ClassTypeMapper classTypeMapper;
    @Autowired
    private ClassTypeModuleRelationMapper classTypeModuleRelationMapper;
    @Autowired
    private ClassModuleNoMapper classModuleNoMapper;
    @Autowired
    private CompanyCashFlowMapper companyCashFlowMapper;
    @Autowired
    private PayOrderMapper payOrderMapper;
    @Autowired
    private CourseVideoChapterMapper courseVideoChapterMapper;
    @Autowired
    private CourseVideoLectureMapper courseVideoLectureMapper;
    @Autowired
    private UserLessonTimeMapper userLessonTimeMapper;
    @Autowired
    private IHomeworkService homeworkServiceImpl;
    @Autowired
    private IClassModuleNoService classModuleNoServiceImpl;
    @Autowired
    private IClassModuleLessonService classModuleLessonServiceImpl;

    /**
     *
     * @Title: saveStudentPayMaster
     * @Description: 新增StudentPayMaster
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public void insert(StudentPayMaster studentPayMaster) {
        this.studentPayMasterMapper.insert(studentPayMaster);
    }

    /**
     *
     * @Title: batchSaveStudentPayMaster
     * @Description: 批量新增StudentPayMaster
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public void batchInsert(List<StudentPayMaster> studentPayMasters) {
        if (studentPayMasters != null && !studentPayMasters.isEmpty()) {
            this.studentPayMasterMapper.batchInsert(studentPayMasters);
        }
    }

    /**
     *
     * @Title: updateStudentPayMaster
     * @Description: 编辑StudentPayMaster
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public void update(StudentPayMaster studentPayMaster) {
        this.studentPayMasterMapper.update(studentPayMaster);
    }

    /**
     *
     * @Title: deleteStudentPayMasterById
     * @Description: 根据id删除StudentPayMaster
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public void deleteStudentPayMasterById(Integer id) {
        this.studentPayMasterMapper.deleteById(id);
    }

    /**
     *
     * @Title: deleteStudentPayMasterByIds
     * @Description: 根据id批量删除StudentPayMaster
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public void deleteStudentPayMasterByIds(Integer[] ids) {
        this.studentPayMasterMapper.deleteByIds(ids);
    }

    /**
     *
     * @Title: findStudentPayMasterById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public StudentPayMaster findStudentPayMasterById(Integer id) {
        return this.studentPayMasterMapper.findById(id);
    }

    /**
     *
     * @Title: findStudentPayMasterById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public List<StudentPayMaster> findStudentPayMasterByStuId(Integer stuId, Integer schoolId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("stuId", "" + stuId);
        map.put("schoolId", "" + schoolId);
        return this.studentPayMasterMapper.findByStuId(map);
    }

    /**
     *
     * @Title: findStudentPayMasterByPage
     * @Description: 分页查询
     * @return
     * @return List<StudentPayMaster> 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public List<StudentPayMaster> findStudentPayMasterByPage(StudentPayMaster search) {
        Integer totalRecords = this.studentPayMasterMapper.pageCount(search);
        search.setTotalRecords(totalRecords);
        return this.studentPayMasterMapper.page(search);
    }

    /**
     *
     * @Title: updatePayMaster
     * @Description: 分页查询
     * @return
     * @return List<StudentPayMaster> 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by chopin
     */
    @Override
    public void updatePayMaster(StudentPayMaster payMaster, Users user, List<StudentPaySlave> slaves) {

        /* 存主定单，获得订单编号 */
        payMaster.setApplyTime(new Date());
        payMaster.setCreator(user.getId().toString());
        payMaster.setCreateTime(new Date());
        payMaster.setUpdator(user.getId().toString());
        payMaster.setUpdateTime(new Date());
        payMaster.setDeleteFlag(0);
        payMaster.setSchoolId(user.getSchoolId());
        payMaster.setCompanyId(user.getCompanyId());
        this.studentPayMasterMapper.update(payMaster);
        /* 存子订单表 */
        for (StudentPaySlave slave : slaves) {
            slave.setStuId(payMaster.getStuId());
            slave.setPayMasterId(payMaster.getId());
            slave.setCampusId(payMaster.getCampusId());
            slave.setCompanyId(user.getCompanyId());
        }
        // if(videos!=null){
        // for(CourseVideo video:videos){
        // StudentPaySlave slave=new StudentPaySlave();
        // slave.setPayMasterId(payMaster.getId());
        // slave.setResourceId(video.getId().toString());
        // slave.setStuId(payMaster.getStuId());
        // slave.setResourceType("VIDEO");
        // slave.setPayMasterId(payMaster.getId());
        // slave.setCampusId(payMaster.getCampusId());
        // slave.setCompanyId(user.getCompanyId());
        // slaves.add(slave);
        // }
        // }

        for (StudentPaySlave slave : slaves) {
            if (slave.getId() != null) {
                StudentPaySlave s = this.studentPaySlaveMapper.findById(slave.getId());
                if (s != null && s.getId() != null) {
                    this.studentPaySlaveMapper.update(slave);
                } else {
                    this.studentPaySlaveMapper.insert(slave);
                }
            } else {
                this.studentPaySlaveMapper.insert(slave);
            }

        }
        // if(slaves!=null && !slaves.isEmpty()){
        // studentPaySlaveMapper.batchInsert(slaves);
        // }
        // 存操作日志
        StudentPayLog log = new StudentPayLog();
        log.setOperateTime(new Date());
        log.setDescription("");
        log.setOperateType("");
        log.setOperator(user.getId());
        log.setPayMasterId(payMaster.getId());
        log.setCompanyId(user.getCompanyId());
        if (log != null) {
            this.studentPayLogMapper.insert(log);
        }
    }

    /**
     *
     * @Title: saveStudentPayMaster
     * @Description: 新增StudentPayMaster
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-25
     * @user by chopin
     */
    @Override
    public Boolean savePayMaster(StudentPayMaster payMaster, Users user, List<StudentAgentMaterial> materials, List<StudentFeeStage> stages) {

        /* 存主定单，获得订单编号 */
        payMaster.setApplyTime(new Date());
        payMaster.setCreator(user.getId().toString());
        payMaster.setCreateTime(new Date());
        payMaster.setUpdator(user.getId().toString());
        payMaster.setUpdateTime(new Date());
        payMaster.setDeleteFlag(0);
        payMaster.setSchoolId(user.getSchoolId());
        payMaster.setApplyChannelCode("CHANNEL_OFFLINE");
        payMaster.setPayStatusCode("ORDER_CREATED");
        payMaster.setBizStatusCode("ORDER_BIZ_NEW_ORDER");
        payMaster.setCompanyId(user.getCompanyId());

        /* 存子订单表 */
        List<StudentPaySlave> slaves = new ArrayList<StudentPaySlave>();
        HashMap map = new HashMap();
        map.put("companyId", user.getCompanyId());
        map.put("schoolId", user.getSchoolId());
        map.put("classTypeId", payMaster.getCommodityId());
        ClassType ct = this.classTypeMapper.findById(payMaster.getCommodityId());
        List<ClassTypeModuleRelationVo> vo = this.classTypeModuleRelationMapper.findClassModuleRelationByClassTypeId(map);
        for (ClassTypeModuleRelationVo v : vo) {
            StudentPaySlave slave = new StudentPaySlave();
            slave.setCompanyId(user.getCompanyId());
            slave.setStuId(payMaster.getStuId());
            slave.setPayMasterId(payMaster.getId());
            slave.setSlaveStatusCode("SUB_ORDER_FINISHED");
            if ("TEACH_METHOD_FACE".equals(v.getTeachMethod()) || "TEACH_METHOD_LIVE".equals(v.getTeachMethod())) {
                ClassModuleNo cmn = new ClassModuleNo();
                cmn.setModuleId(v.getModuleId());
                List<ClassModuleNoVo> moduleNos = this.classModuleNoMapper.findListByModule(cmn);
                Integer moduleNo = null;
                // 其他课程
                if (1 != ct.getRemoteFlag() && moduleNos != null) {
                    moduleNo = moduleNos.get(0).getId();
                } else {
                    return false;
                }
                slave.setResourceType(v.getTeachMethod());
                slave.setResourceId("" + moduleNo);
                slave.setModuleId(v.getModuleId());
            }
            if ("TEACH_METHOD_VIDEO".equals(v.getTeachMethod())) {
                slave.setResourceType(v.getTeachMethod());
                slave.setResourceId("" + v.getModuleId());
                slave.setModuleId(v.getModuleId());
            }
            slaves.add(slave);
        }
        // 其他课程
        if (1 != ct.getRemoteFlag() && slaves.size() == 0) {
            return false;
        }

        // 补订单payOrder
        Student stu = this.studentMapper.findById(payMaster.getStuId());
        StringBuffer orderIdBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        String orderNum = orderIdBuffer.append(RandomUtils.nextInt(1000)).toString();
        PayOrder payOrder = new PayOrder();
        payOrder.setUserId(stu.getUserId());
        payOrder.setOrderNum(orderNum);
        payOrder.setOrderTime(new Date());
        payOrder.setCommodityName(payMaster.getClassTypeName());
        payOrder.setCommodityId(payMaster.getCommodityId());
        payOrder.setCommdityType("COMMODITY_CLASS");
        payOrder.setOriginalPrice(payMaster.getTotalAmount());
        payOrder.setPayPrice(payMaster.getTotalAmount());
        payOrder.setPayStatus(Constant.PAY_STATUS_SUCCESS);
        payOrder.setItemOneId(payMaster.getItemOneId());
        payOrder.setItemSecondId(payMaster.getItemSecondId());
        payOrder.setCompanyId(payMaster.getCompanyId());
        payOrder.setSchoolId(payMaster.getSchoolId());
        this.payOrderMapper.insert(payOrder);
        Integer payOrderId = this.payOrderMapper.findPayOrderByOrderNum(orderNum).getId();

        payMaster.setPayOrderId(payOrderId);

        this.studentPayMasterMapper.insertPayMaster(payMaster);
        // log_student.info("订单PayMasterID："+payMaster.getId());
        for (StudentPaySlave slave : slaves) {
            slave.setPayMasterId(payMaster.getId());
        }
        if (slaves.size() > 0) {
            this.studentPaySlaveMapper.batchInsert(slaves);
        }

        /********************* 报考资料 begin ***************************/
        // 更新考期
        if (StringUtils.isNotBlank(payMaster.getExamTermName())) {
            SysConfigTerm search = new SysConfigTerm();
            search.setCompanyId(user.getCompanyId());
            search.setItemOneId(payMaster.getItemOneId());
            search.setTermName(payMaster.getExamTermName());
            search.setSchoolId(user.getSchoolId());
            List<SysConfigTerm> terms = this.sysConfigTermMapper.findByName(search);
            if (terms != null && terms.size() > 0) {
                payMaster.setExamTermId(terms.get(0).getId());
            } else {
                this.sysConfigTermMapper.insert(search);
                payMaster.setExamTermId(search.getId());
            }
        }

        // 没有则新增
        if (materials != null) {
            for (StudentAgentMaterial m : materials) {
                m.setCompanyId(user.getCompanyId());
                m.setCreateTime(new Date());
                m.setCreator(user.getSchoolId());
                m.setPayMasterId(payMaster.getId());
                this.studentAgentMaterialMapper.insertMaterial(m);
            }
        } else {
            materials = new ArrayList<StudentAgentMaterial>();
        }
        /********************* 报考资料 end ******************************/

        /********************* 分期 begin **************************/
        if (stages.size() > 1) {
            payMaster.setPayStatusCode("ORDER_PART_PAY");
        } else {
            payMaster.setPayStatusCode("ORDER_PAID");
        }
        for (StudentFeeStage stage : stages) {
            StudentFeeStage s = this.studentFeeStageMapper.findById(stage.getId());
            stage.setPayMasterId(payMaster.getId());
            if (stage.getStageDate() == null) {// 没有分期日期就说明是当期付款，所以当前日期=支付日期
                stage.setPayDate(new Date());
            }
            stage.setCreateTime(new Date());
            stage.setCreator(user.getId());
            stage.setCreateType("STAGE_TYPE_SIGN");
            stage.setUpdateTime(new Date());
            stage.setUpdator(user.getId());
            stage.setCompanyId(user.getCompanyId());
            if (s != null && s.getId() != null) {
                this.studentFeeStageMapper.update(stage);
            } else {
                this.studentFeeStageMapper.insert(stage);
            }
            // 更新公司流水表
            try {
                this.updateCompanyCash(payMaster, user, stage);
            } catch (Exception e) {
                this.log.info("添加公司流水表失败", e);
                e.printStackTrace();
            }
        }
        /********************** 分期 end ******************************/
        // 更新主订单
        // payMaster.setPayStatusCode("ORDER_PAID");
        this.studentPayMasterMapper.update(payMaster);
        // 存操作日志
        StudentPayLog log = new StudentPayLog();
        log.setOperateTime(new Date());
        log.setDescription("");
        log.setOperateType("CHANGE_STU");
        log.setOperator(user.getId());
        log.setPayMasterId(payMaster.getId());
        log.setCompanyId(user.getCompanyId());
        if (log != null) {
            this.studentPayLogMapper.insert(log);
        }
        return true;
    }

    /**
     *
     * Class Name: StudentPayMasterServiceImpl.java
     *
     * @Description: 更新公司流水表
     * @author zhang.zx
     * @date 2016年6月29日 上午11:17:39
     * @modifier
     * @modify-date 2016年6月29日 上午11:17:39
     * @version 1.0
     * @param payMaster
     * @param user
     * @param stage
     * @throws Exception
     */
    private void updateCompanyCash(StudentPayMaster payMaster, Users user, StudentFeeStage stage) throws Exception {
        CompanyCashFlow t = new CompanyCashFlow();
        Student stu = this.studentMapper.findById(payMaster.getStuId());
        if (null != stu) {
            t.setUserId(stu.getUserId());
        }
        t.setStuId(payMaster.getStuId());
        t.setCompanyId(user.getCompanyId());
        t.setTradeAmount(stage.getStageFee());
        if (stage.getStageDate() == null) {// 没有分期日期就说明是当期付款，所以当前日期=支付日期
            t.setTradeDate(new Date());
        } else {
            t.setTradeDate(stage.getStageDate());
        }
        t.setTradeReason("CHANNEL_OFFLINE");
        t.setTradeType("TRADE_IN");
        if (null != stage.getStageStatus() && "1".equals(stage.getStageStatus())) {
            t.setTradeResult("success");
        } else {
            t.setTradeResult("failed");
        }
        t.setTradeWay("PAY_OFFLINE");
        t.setTradeSource("PAY_OFFLINE");
        t.setUpdateTime(new Date());
        t.setUpdator(user.getId());
        t.setSchoolId(user.getSchoolId());
        t.setPayMasterId(payMaster.getId());
        this.companyCashFlowMapper.insert(t);
    }

    /**
     *
     * @fileName : StudentPayMasterServiceImpl.java
     * @date : 2015年10月21日 下午6:46:59
     * @author : 杨延博
     * @description :
     */
    @Override
    public void newPayMasterMany(StudentPayMaster payMaster, Users user, List<StudentPaySlave> slaves) {

        /* 存主定单，获得订单编号 */
        payMaster.setApplyTime(new Date());
        payMaster.setCreator(user.getId().toString());
        payMaster.setCreateTime(new Date());
        payMaster.setUpdator(user.getId().toString());
        payMaster.setUpdateTime(new Date());
        payMaster.setDeleteFlag(0);
        payMaster.setSchoolId(user.getSchoolId());
        payMaster.setApplyChannelCode("CHANNEL_OFFLINE");
        payMaster.setPayStatusCode("ORDER_PAID");
        payMaster.setBizStatusCode("ORDER_BIZ_NEW_ORDER");
        payMaster.setCompanyId(user.getCompanyId());
        this.studentPayMasterMapper.insertPayMaster(payMaster);
        /* 存子订单表 */
        for (StudentPaySlave slave : slaves) {
            slave.setStuId(payMaster.getStuId());
            slave.setSlaveStatusCode("SUB_ORDER_CREATED");
            slave.setPayMasterId(payMaster.getId());
            slave.setCampusId(payMaster.getCampusId());
            slave.setCompanyId(user.getCompanyId());
        }
        // if(videos!=null){
        // for(CourseVideo video:videos){
        // StudentPaySlave slave=new StudentPaySlave();
        // slave.setPayMasterId(payMaster.getId());
        // slave.setResourceId(video.getId().toString());
        // slave.setStuId(payMaster.getStuId());
        // slave.setSlaveStatusCode("SUB_ORDER_CREATED");
        // slave.setResourceType("VIDEO");
        // slave.setPayMasterId(payMaster.getId());
        // slave.setCampusId(payMaster.getCampusId());
        // slave.setCompanyId(user.getCompanyId());
        // slaves.add(slave);
        // }
        // }
        if (slaves != null && !slaves.isEmpty()) {
            this.studentPaySlaveMapper.batchInsert(slaves);
        }
        // 存操作日志
        StudentPayLog log = new StudentPayLog();
        log.setOperateTime(new Date());
        log.setDescription("");
        log.setOperateType("CHANGE_STU");
        log.setOperator(user.getId());
        log.setPayMasterId(payMaster.getId());
        log.setCompanyId(user.getCompanyId());
        if (log != null) {
            this.studentPayLogMapper.insert(log);
        }

    }

    /**
     *
     * @Title: saveStudentPayMaster
     * @Description: 新增StudentPayMaster
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-25
     * @user by chopin
     */
    @Override
    public void newPayMaster(StudentPayMaster payMaster, Users user, List<StudentPaySlave> slaves) {
        /* 存主定单，获得订单编号 */
        payMaster.setApplyTime(new Date());
        payMaster.setCreator(user.getId().toString());
        payMaster.setCreateTime(new Date());
        payMaster.setUpdator(user.getId().toString());
        payMaster.setUpdateTime(new Date());
        payMaster.setDeleteFlag(0);
        payMaster.setSchoolId(user.getSchoolId());
        payMaster.setApplyChannelCode("CHANNEL_OFFLINE");
        payMaster.setPayStatusCode("ORDER_CREATED");
        payMaster.setBizStatusCode("ORDER_BIZ_NEW_ORDER");
        payMaster.setCompanyId(user.getCompanyId());
        this.studentPayMasterMapper.insertPayMaster(payMaster);
        /* 存子订单表 */
        for (StudentPaySlave slave : slaves) {
            slave.setStuId(payMaster.getStuId());
            slave.setSlaveStatusCode("SUB_ORDER_CREATED");
            slave.setPayMasterId(payMaster.getId());
            slave.setCampusId(payMaster.getCampusId());
            slave.setCompanyId(user.getCompanyId());
        }
        // if(videos!=null){
        // for(CourseVideo video:videos){
        // StudentPaySlave slave=new StudentPaySlave();
        // slave.setPayMasterId(payMaster.getId());
        // slave.setResourceId(video.getId().toString());
        // slave.setStuId(payMaster.getStuId());
        // slave.setSlaveStatusCode("SUB_ORDER_CREATED");
        // slave.setResourceType("VIDEO");
        // slave.setPayMasterId(payMaster.getId());
        // slave.setCampusId(payMaster.getCampusId());
        // slave.setCompanyId(user.getCompanyId());
        // slaves.add(slave);
        // }
        // }
        if (slaves != null && !slaves.isEmpty()) {
            this.studentPaySlaveMapper.batchInsert(slaves);
        }
        // 存操作日志
        StudentPayLog log = new StudentPayLog();
        log.setOperateTime(new Date());
        log.setDescription("");
        log.setOperateType("CHANGE_STU");
        log.setOperator(user.getId());
        log.setPayMasterId(payMaster.getId());
        log.setCompanyId(user.getCompanyId());
        if (log != null) {
            this.studentPayLogMapper.insert(log);
        }
    }

    @Override
    public void newStage(StudentPayMaster payMaster, Users user, List<StudentFeeStage> stages) {

        /* 付款信息 */
        for (StudentFeeStage stage : stages) {
            stage.setPayMasterId(payMaster.getId());
            if (stage.getStageDate() == null) {// 没有分期日期就说明是当期付款，所以当前日期=支付日期
                stage.setPayDate(new Date());
            }
            stage.setCreateTime(new Date());
            stage.setCreator(user.getId());
            stage.setCreateType("STAGE_TYPE_SIGN");
            stage.setUpdateTime(new Date());
            stage.setUpdator(user.getId());
            stage.setCompanyId(user.getCompanyId());
        }
        if (stages != null && !stages.isEmpty()) {
            this.studentFeeStageMapper.batchInsert(stages);
        }
    }

    /**
     *
     * @Title: saveStudentPayMaster
     * @Description: 新增StudentPayMaster
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-25
     * @user by chopin
     */
    @Override
    public Boolean savePackagePayMaster(StudentPayMaster payMaster, Users user, List<StudentAgentMaterial> materials, List<StudentFeeStage> stages) {

        /* 存主定单，获得订单编号 */
        payMaster.setApplyTime(new Date());
        payMaster.setCreator(user.getId().toString());
        payMaster.setCreateTime(new Date());
        payMaster.setUpdator(user.getId().toString());
        payMaster.setUpdateTime(new Date());
        payMaster.setDeleteFlag(0);
        payMaster.setSchoolId(user.getSchoolId());
        payMaster.setApplyChannelCode("CHANNEL_OFFLINE");
        payMaster.setPayStatusCode("ORDER_CREATED");
        payMaster.setBizStatusCode("ORDER_BIZ_NEW_ORDER");
        payMaster.setCompanyId(user.getCompanyId());

        /* 存子订单表 */
        List<StudentPaySlave> slaves = new ArrayList<StudentPaySlave>();
        HashMap map = new HashMap();
        map.put("companyId", user.getCompanyId());
        map.put("schoolId", user.getSchoolId());
        map.put("classPackageId", payMaster.getCommodityId());
        List<ClassTypeModuleRelationVo> vo = this.classTypeModuleRelationMapper.findClassModuleRelationByPackageId(map);
        for (ClassTypeModuleRelationVo v : vo) {
            this.classTypeMapper.findById(v.getClassTypeId());
            StudentPaySlave slave = new StudentPaySlave();
            slave.setCompanyId(user.getCompanyId());
            slave.setStuId(payMaster.getStuId());
            slave.setPayMasterId(payMaster.getId());
            slave.setSlaveStatusCode("SUB_ORDER_CREATED");
            if ("TEACH_METHOD_FACE".equals(v.getTeachMethod()) || "TEACH_METHOD_LIVE".equals(v.getTeachMethod())) {
                ClassModuleNo cmn = new ClassModuleNo();
                cmn.setModuleId(v.getModuleId());
                List<ClassModuleNoVo> moduleNos = this.classModuleNoMapper.findListByModule(cmn);
                Integer moduleNo = null;
                if (moduleNos != null) {
                    moduleNo = moduleNos.get(0).getId();
                } else {
                    return false;
                }
                slave.setResourceType(v.getTeachMethod());
                slave.setResourceId("" + moduleNo);
                slave.setModuleId(v.getModuleId());
            }
            if ("TEACH_METHOD_VIDEO".equals(v.getTeachMethod())) {
                slave.setResourceType(v.getTeachMethod());
                slave.setResourceId("" + v.getModuleId());
                slave.setModuleId(v.getModuleId());
            }
            slaves.add(slave);
        }
        if (slaves.size() == 0) {
            return false;
        }

        // 补订单payOrder
        Student stu = this.studentMapper.findById(payMaster.getStuId());
        StringBuffer orderIdBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        String orderNum = orderIdBuffer.append(RandomUtils.nextInt(1000)).toString();
        PayOrder payOrder = new PayOrder();
        payOrder.setUserId(stu.getUserId());
        payOrder.setOrderNum(orderNum);
        payOrder.setOrderTime(new Date());
        payOrder.setCommodityName(payMaster.getClassTypeName());
        payOrder.setCommodityId(payMaster.getCommodityId());
        payOrder.setCommdityType("COMMODITY_PACKAGE");
        payOrder.setOriginalPrice(payMaster.getTotalAmount());
        payOrder.setPayPrice(payMaster.getTotalAmount());
        payOrder.setPayStatus(Constant.PAY_STATUS_SUCCESS);
        payOrder.setCompanyId(payMaster.getCompanyId());
        payOrder.setSchoolId(payMaster.getSchoolId());
        if (null != payMaster.getClassPackageStageId()) {
            payOrder.setClassPackageStageId(payMaster.getClassPackageStageId());
        }
        this.payOrderMapper.insert(payOrder);
        Integer payOrderId = this.payOrderMapper.findPayOrderByOrderNum(orderNum).getId();

        payMaster.setPayOrderId(payOrderId);

        this.studentPayMasterMapper.insertPayMaster(payMaster);
        for (StudentPaySlave slave : slaves) {
            slave.setPayMasterId(payMaster.getId());
        }
        if (slaves.size() > 0) {
            this.studentPaySlaveMapper.batchInsert(slaves);
        }
        /********************* 报考资料 begin ***************************/
        // 更新考期
        if (StringUtils.isNotBlank(payMaster.getExamTermName())) {
            SysConfigTerm search = new SysConfigTerm();
            search.setCompanyId(user.getCompanyId());
            search.setItemOneId(payMaster.getItemOneId());
            search.setTermName(payMaster.getExamTermName());
            search.setSchoolId(user.getSchoolId());
            List<SysConfigTerm> terms = this.sysConfigTermMapper.findByName(search);
            if (terms != null && terms.size() > 0) {
                payMaster.setExamTermId(terms.get(0).getId());
            } else {
                this.sysConfigTermMapper.insert(search);
                payMaster.setExamTermId(search.getId());
            }
        }

        // 没有则新增
        if (materials != null) {
            for (StudentAgentMaterial m : materials) {
                m.setCompanyId(user.getCompanyId());
                m.setCreateTime(new Date());
                m.setCreator(user.getSchoolId());
                m.setPayMasterId(payMaster.getId());
                this.studentAgentMaterialMapper.insertMaterial(m);
            }
        } else {
            materials = new ArrayList<StudentAgentMaterial>();
        }
        /********************* 报考资料 end ******************************/

        /********************* 分期 begin **************************/
        if (stages.size() > 1) {
            payMaster.setPayStatusCode("ORDER_PART_PAY");
        } else {
            payMaster.setPayStatusCode("ORDER_PAID");
        }
        for (StudentFeeStage stage : stages) {
            StudentFeeStage s = this.studentFeeStageMapper.findById(stage.getId());
            stage.setPayMasterId(payMaster.getId());
            if (stage.getStageDate() == null) {// 没有分期日期就说明是当期付款，所以当前日期=支付日期
                stage.setPayDate(new Date());
            }
            stage.setCreateTime(new Date());
            stage.setCreator(user.getId());
            stage.setCreateType("STAGE_TYPE_SIGN");
            stage.setUpdateTime(new Date());
            stage.setUpdator(user.getId());
            stage.setCompanyId(user.getCompanyId());
            if (s != null && s.getId() != null) {
                this.studentFeeStageMapper.update(stage);
            } else {
                this.studentFeeStageMapper.insert(stage);
            }
            // 更新公司流水表
            try {
                this.updateCompanyCash(payMaster, user, stage);
            } catch (Exception e) {
                this.log.info("添加公司流水表失败", e);
                e.printStackTrace();
            }
        }
        /********************** 分期 end ******************************/
        // 更新主订单
        // payMaster.setPayStatusCode("ORDER_PAID");
        this.studentPayMasterMapper.update(payMaster);
        // 存操作日志
        StudentPayLog log = new StudentPayLog();
        log.setOperateTime(new Date());
        log.setDescription("");
        log.setOperateType("CHANGE_STU");
        log.setOperator(user.getId());
        log.setPayMasterId(payMaster.getId());
        log.setCompanyId(user.getCompanyId());
        if (log != null) {
            this.studentPayLogMapper.insert(log);
        }
        return true;
    }

    /**
     *
     * @Title: changeClass
     * @Description: 转班
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-25
     * @user by chopin
     */
    @Override
    public String fullChangeClass(StudentPayMaster payMaster, Integer oMasterId, Users user, List<StudentPaySlave> slaves, List<StudentAgentMaterial> material,
            List<StudentFeeStage> nstages, StudentFeeRefund refund) {
        if (nstages == null) {
            nstages = new ArrayList<StudentFeeStage>();
        }
        // 原订单
        StudentPayMaster oMaster = this.studentPayMasterMapper.findById(oMasterId);
        if (oMaster.getPayStatusCode().equals("ORDER_PRODUCT_CHANGED") || oMaster.getPayStatusCode().equals("ORDER_STOPED")) {
            this.log_student.error(">>> [转班-复杂版] " + "状态：订单已转班型或已解约，不能做转班型操作");
            return "订单已转班型或已解约，不能做转班型操作";
        }
        oMaster.setDeleteFlag(1);// 已删除
        oMaster.setUpdator(user.getId().toString());
        oMaster.setUpdateTime(new Date());
        oMaster.setPayStatusCode("ORDER_PRODUCT_CHANGED");// 支付状态：转班型
        oMaster.setBizStatusCode("ORDER_BIZ_CHANGE_PRODUCT");
        // oMaster.setUsedFee(payMaster.getUsedFee());
        this.studentPayMasterMapper.update(oMaster);

        /* 存主定单，获得订单编号 */
        payMaster.setId(null);
        payMaster.setApplyTime(new Date());
        payMaster.setCreator(user.getId().toString());
        payMaster.setCreateTime(new Date());
        payMaster.setDeleteFlag(0);
        payMaster.setSchoolId(user.getSchoolId());
        payMaster.setApplyChannelCode(oMaster.getApplyChannelCode());
        payMaster.setPayOrderId(oMaster.getPayOrderId());
        if (!nstages.isEmpty() && nstages.get(0).getStageFee().equals(payMaster.getTotalAmount())) {
            payMaster.setPayStatusCode("ORDER_PAID");
        } else if (nstages.size() > 1) {
            payMaster.setPayStatusCode("ORDER_PART_PAY");
        } else {
            payMaster.setPayStatusCode("ORDER_PAID");
        }
        payMaster.setBizStatusCode("CHANGE_PRODUCT");
        payMaster.setApplyPlaceId(oMaster.getId());// 存原订单编号
        payMaster.setUpdateTime(new Date());
        payMaster.setUpdator(user.getId().toString());
        payMaster.setCompanyId(user.getCompanyId());
        payMaster.setRelatedPayId(oMaster.getId());
        this.studentPayMasterMapper.insertPayMaster(payMaster);

        // 删除观看记录
        List<Integer> classModuleList = this.classTypeModuleRelationMapper.findClassModuleIdsByClassTypeId(oMaster.getCommodityId());
        if (null != classModuleList && classModuleList.size() > 0) {
            for (Integer classModuleId : classModuleList) {
                // 删除录播记录
                List<Integer> chapterList = this.courseVideoChapterMapper.findIdByModuleId(classModuleId);
                if (null != chapterList && chapterList.size() > 0) {
                    for (Integer chapterId : chapterList) {
                        List<Integer> lectureList = this.courseVideoLectureMapper.findIdByChapterId(chapterId);
                        if (null != lectureList && lectureList.size() > 0) {
                            for (Integer lessonId : lectureList) {
                                Map<String, Integer> userLessonTimeMap = new HashMap<String, Integer>();
                                userLessonTimeMap.put("userId", this.usersFrontMapper.findUserFrontByStudentId(payMaster.getStuId()).getId());
                                userLessonTimeMap.put("lessonId", lessonId);
                                this.userLessonTimeMapper.deleteByUserIdAndLessonId(userLessonTimeMap);
                            }
                        }
                    }
                }
                // 删除直播记录
                List<Integer> classModuleNolist = this.classModuleNoServiceImpl.findClassModuleNoIdsByModuleId(classModuleId);
                for (Integer classModuleNoId : classModuleNolist) {
                    List<Integer> classModuleLessonlist = this.classModuleLessonServiceImpl.findClassModuleLessonIdsByModuleNoId(classModuleNoId);
                    for (Integer classModuleLessonId : classModuleLessonlist) {
                        Map<String, Integer> userLessonTimeMap = new HashMap<String, Integer>();
                        userLessonTimeMap.put("userId", this.usersFrontMapper.findUserFrontByStudentId(payMaster.getStuId()).getId());
                        userLessonTimeMap.put("lessonId", classModuleLessonId);
                        this.userLessonTimeMapper.deleteByUserIdAndLessonId(userLessonTimeMap);
                    }
                }
            }
        }

        // 删除作业记录
        Map<String, Object> homeworkMap = new HashMap<String, Object>();
        homeworkMap.put("companyId", user.getCompanyId());
        homeworkMap.put("stuId", payMaster.getStuId());
        homeworkMap.put("courseId", oMaster.getCommodityId());
        this.homeworkServiceImpl.deleteHomeworkHistory(homeworkMap);

        this.log_student.info(">>> [转班-复杂版]" + "状态：success " + ", Step：2（新旧订单） " + ", 信息：" + "原订单 PayMasterID = " + oMasterId + ", 课程ClassTypeID = "
                + oMaster.getCommodityId() + ", 课程名称 = " + oMaster.getClassTypeName() + ", 新订单 PayMasterID = " + payMaster.getId() + ", 课程ClassTypeID = "
                + payMaster.getCommodityId() + ", 课程名称 = " + payMaster.getClassTypeName());
        /* 存子订单表 */
        for (StudentPaySlave slave : slaves) {
            slave.setStuId(payMaster.getStuId());
            slave.setSlaveStatusCode("SUB_ORDER_CREATED");
            slave.setPayMasterId(payMaster.getId());
            slave.setCompanyId(user.getCompanyId());
        }
        this.studentPaySlaveMapper.batchInsert(slaves);
        /* 存资料 */
        if (payMaster.getIsAgent() == "1" && material != null) {
            for (StudentAgentMaterial m : material) {
                m.setPayMasterId(payMaster.getId());
            }
            this.studentAgentMaterialMapper.batchInsert(material);
        }

        List<StudentFeeStage> ostages = this.studentFeeStageMapper.findHasPayed(oMasterId);
        nstages.addAll(ostages);
        this.studentFeeStageMapper.deleteByMasterId(oMasterId);
        if (!nstages.isEmpty()) {
            /* 付款信息 */
            for (StudentFeeStage stage : nstages) {
                stage.setPayMasterId(payMaster.getId());
                if (stage.getStageDate() == null) {// 没有分期日期就说明是当期付款，所以当前日期=支付日期
                    stage.setPayDate(new Date());
                }
                stage.setId(null);// 把旧分期的id清了
                stage.setCreateTime(new Date());
                stage.setCreator(user.getId());
                stage.setCreateType("STAGE_TYPE_SIGN");
                stage.setUpdateTime(new Date());
                stage.setUpdator(user.getId());
                stage.setCompanyId(user.getCompanyId());
                // 更新公司流水表
                try {
                    CompanyCashFlow t = new CompanyCashFlow();
                    t.setTradeReason("ORDER_OPERTE_CHANGE_PRODUCT");
                    t.setStuId(payMaster.getStuId());
                    t.setCompanyId(user.getCompanyId());
                    t.setTradeAmount(stage.getStageFee());
                    if (stage.getStageDate() == null) {// 没有分期日期就说明是当期付款，所以当前日期=支付日期
                        t.setTradeDate(new Date());
                    } else {
                        t.setTradeDate(stage.getStageDate());
                    }
                    t.setTradeResult(stage.getStageStatus());
                    t.setTradeWay("CHANGE_PRODUCT");
                    t.setTradeSource("PAY_OFFLINE");
                    t.setUpdateTime(new Date());
                    t.setUpdator(user.getId());
                    t.setSchoolId(user.getSchoolId());
                    this.companyCashFlowMapper.insert(t);
                } catch (Exception e) {
                    this.log_student.error(">>> [转班-复杂版]" + "状态：添加公司流水表失败 ", e);
                    e.printStackTrace();
                }
            }
            this.studentFeeStageMapper.batchInsert(nstages);
        }
        // 如果有退费，插入退费表
        if (refund != null) {
            refund.setCreateTime(new Date());
            refund.setCreator(user.getId());
            refund.setRefundDate(new Date());
            refund.setStuId(payMaster.getStuId());
            refund.setPayMasterId(payMaster.getId());
            refund.setCreateType("ORDER_OPERTE_CHANGE_PRODUCT");
            this.studentFeeRefundMapper.insert(refund);
            // 更新公司流水表
            try {
                CompanyCashFlow t = new CompanyCashFlow();
                t.setTradeReason("ORDER_OPERTE_CHANGE_PRODUCT");
                t.setStuId(payMaster.getStuId());
                t.setCompanyId(user.getCompanyId());
                t.setTradeAmount(-(refund.getRefundFee()));
                t.setTradeDate(new Date());
                t.setTradeWay("ORDER_OPERTE_STOPED");
                t.setTradeSource("PAY_OFFLINE");
                t.setUpdateTime(new Date());
                t.setUpdator(user.getId());
                t.setSchoolId(user.getSchoolId());
                this.companyCashFlowMapper.insert(t);
            } catch (Exception e) {
                this.log_student.error(">>> [转班-复杂版]" + "状态：添加公司流水表失败 ", e);
                e.printStackTrace();
            }
        }

        // 存操作日志
        StudentPayLog log = new StudentPayLog();
        log.setOperateTime(new Date());
        log.setDescription("");
        log.setOperateType("CHANGE_STU");
        log.setOperator(user.getId());
        log.setPayMasterId(oMaster.getId());
        this.studentPayLogMapper.insert(log);

        // 维护commodity（buy_num）
        StudentPayMaster oldPayMaster = this.studentPayMasterMapper.findById(oMasterId);
        Commodity commodity1 = this.commodityMapper.findById(this.commodityMapper.findCommodityIdByClassTypeId(oldPayMaster.getCommodityId()));
        if (commodity1.getBuyNum() > 0) {
            commodity1.setBuyNum(commodity1.getBuyNum() - 1);
        } else {
            commodity1.setBuyNum(0);
        }
        this.commodityMapper.update(commodity1);
        Commodity commodity2 = this.commodityMapper.findById(this.commodityMapper.findCommodityIdByClassTypeId(payMaster.getCommodityId()));
        commodity2.setBuyNum(commodity2.getBuyNum() + 1);
        this.commodityMapper.update(commodity2);

        return "success";
    }

    /**
     *
     * @Title: changeClass
     * @Description: 转班
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-25
     * @user by chopin
     */
    @Override
    public String changeClass(StudentPayMaster payMaster, Integer oMasterId, Users user, List<StudentAgentMaterial> material, List<StudentFeeStage> nstages,
            StudentFeeRefund refund) {
        if (nstages == null) {
            nstages = new ArrayList<StudentFeeStage>();
        }
        // 原订单
        StudentPayMaster oMaster = this.studentPayMasterMapper.findById(oMasterId);

        if (oMaster.getPayStatusCode().equals("ORDER_PRODUCT_CHANGED") || oMaster.getPayStatusCode().equals("ORDER_STOPED")) {
            this.log_student.error(">>> [转班] " + "状态：订单已转班型或已解约，不能做转班型操作");
            return "订单已转班型或已解约，不能做转班型操作";
        }
        oMaster.setDeleteFlag(1);// 已删除
        oMaster.setUpdator(user.getId().toString());
        oMaster.setUpdateTime(new Date());
        oMaster.setPayStatusCode("ORDER_PRODUCT_CHANGED");// 支付状态：转班型
        oMaster.setBizStatusCode("ORDER_BIZ_CHANGE_PRODUCT");
        // oMaster.setUsedFee(payMaster.getUsedFee());
        this.studentPayMasterMapper.update(oMaster);

        /* 存主定单，获得订单编号 */
        payMaster.setId(null);
        payMaster.setApplyTime(new Date());
        payMaster.setCreator(user.getId().toString());
        payMaster.setCreateTime(new Date());
        payMaster.setDeleteFlag(0);
        payMaster.setSchoolId(user.getSchoolId());
        payMaster.setApplyChannelCode(oMaster.getApplyChannelCode());
        payMaster.setPayOrderId(oMaster.getPayOrderId());
        if (!nstages.isEmpty() && nstages.get(0).getStageFee().equals(payMaster.getTotalAmount())) {
            payMaster.setPayStatusCode("ORDER_PAID");
        } else if (nstages.size() > 1) {
            payMaster.setPayStatusCode("ORDER_PART_PAY");
        } else {
            payMaster.setPayStatusCode("ORDER_PAID");
        }
        payMaster.setBizStatusCode("CHANGE_PRODUCT");
        payMaster.setApplyPlaceId(oMaster.getId());// 存原订单编号
        payMaster.setUpdateTime(new Date());
        payMaster.setUpdator(user.getId().toString());
        payMaster.setCompanyId(user.getCompanyId());
        payMaster.setRelatedPayId(oMaster.getId());
        this.studentPayMasterMapper.insertPayMaster(payMaster);

        // 删除观看记录
        List<Integer> classModuleList = this.classTypeModuleRelationMapper.findClassModuleIdsByClassTypeId(oMaster.getCommodityId());
        if (null != classModuleList && classModuleList.size() > 0) {
            for (Integer classModuleId : classModuleList) {
                // 删除录播记录
                List<Integer> chapterList = this.courseVideoChapterMapper.findIdByModuleId(classModuleId);
                if (null != chapterList && chapterList.size() > 0) {
                    for (Integer chapterId : chapterList) {
                        List<Integer> lectureList = this.courseVideoLectureMapper.findIdByChapterId(chapterId);
                        if (null != lectureList && lectureList.size() > 0) {
                            for (Integer lessonId : lectureList) {
                                Map<String, Integer> userLessonTimeMap = new HashMap<String, Integer>();
                                userLessonTimeMap.put("userId", this.usersFrontMapper.findUserFrontByStudentId(payMaster.getStuId()).getId());
                                userLessonTimeMap.put("lessonId", lessonId);
                                this.userLessonTimeMapper.deleteByUserIdAndLessonId(userLessonTimeMap);
                            }
                        }
                    }
                }

                // 删除直播记录
                List<Integer> classModuleNolist = this.classModuleNoServiceImpl.findClassModuleNoIdsByModuleId(classModuleId);
                for (Integer classModuleNoId : classModuleNolist) {
                    List<Integer> classModuleLessonlist = this.classModuleLessonServiceImpl.findClassModuleLessonIdsByModuleNoId(classModuleNoId);
                    for (Integer classModuleLessonId : classModuleLessonlist) {
                        Map<String, Integer> userLessonTimeMap = new HashMap<String, Integer>();
                        userLessonTimeMap.put("userId", this.usersFrontMapper.findUserFrontByStudentId(payMaster.getStuId()).getId());
                        userLessonTimeMap.put("lessonId", classModuleLessonId);
                        this.userLessonTimeMapper.deleteByUserIdAndLessonId(userLessonTimeMap);
                    }
                }
            }
        }

        // 删除作业记录
        Map<String, Object> homeworkMap = new HashMap<String, Object>();
        homeworkMap.put("companyId", user.getCompanyId());
        homeworkMap.put("stuId", payMaster.getStuId());
        homeworkMap.put("courseId", oMaster.getCommodityId());
        this.homeworkServiceImpl.deleteHomeworkHistory(homeworkMap);

        this.log_student.info(">>> [转班]" + "状态：success " + ", Step：2（新旧订单） " + ", 信息：" + "原订单 PayMasterID = " + oMasterId + ", 课程ClassTypeID = "
                + oMaster.getCommodityId() + ", 课程名称 = " + oMaster.getClassTypeName() + ", 新订单 PayMasterID = " + payMaster.getId() + ", 课程ClassTypeID = "
                + payMaster.getCommodityId() + ", 课程名称 = " + payMaster.getClassTypeName());
        /* 存子订单表 */
        List<StudentPaySlave> slaves = new ArrayList<StudentPaySlave>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("classTypeId", payMaster.getCommodityId());
        List<ClassTypeModuleRelationVo> modules = this.classTypeModuleRelationMapper.findClassModuleRelationByClassTypeId(map);
        for (ClassTypeModuleRelationVo vo : modules) {
            StudentPaySlave slave = new StudentPaySlave();
            ClassModuleNo search = new ClassModuleNo();
            search.setModuleId(vo.getModuleId());
            List<ClassModuleNoVo> moduleNos = this.classModuleNoMapper.findListByModule(search);
            slave.setModuleId(vo.getModuleId());
            slave.setPayMasterId(payMaster.getId());
            slave.setResourceType(vo.getTeachMethod());
            slave.setSlaveStatusCode("SUB_ORDER_CREATED");
            slave.setStuId(payMaster.getStuId());
            if ("TEACH_METHOD_LIVE".equals(vo.getTeachMethod()) || "TEACH_METHOD_FACE".equals(vo.getTeachMethod())) {
                slave.setResourceId(moduleNos.size() > 0 ? moduleNos.get(0).getId() + "" : "");
            } else {
                slave.setResourceId(vo.getModuleId() + "");
            }
            slaves.add(slave);
        }
        this.studentPaySlaveMapper.batchInsert(slaves);
        /* 存资料 */
        if (payMaster.getIsAgent() == "1" && material != null) {
            for (StudentAgentMaterial m : material) {
                m.setPayMasterId(payMaster.getId());
            }
            this.studentAgentMaterialMapper.batchInsert(material);
        }

        List<StudentFeeStage> ostages = this.studentFeeStageMapper.findHasPayed(oMasterId);
        nstages.addAll(ostages);
        this.studentFeeStageMapper.deleteByMasterId(oMasterId);
        if (!nstages.isEmpty()) {
            /* 付款信息 */
            for (StudentFeeStage stage : nstages) {
                stage.setPayMasterId(payMaster.getId());
                if (stage.getStageDate() == null) {// 没有分期日期就说明是当期付款，所以当前日期=支付日期
                    stage.setPayDate(new Date());
                }
                stage.setId(null);// 把旧分期的id清了
                stage.setCreateTime(new Date());
                stage.setCreator(user.getId());
                stage.setCreateType("STAGE_TYPE_SIGN");
                stage.setUpdateTime(new Date());
                stage.setUpdator(user.getId());
                stage.setCompanyId(user.getCompanyId());
            }
            this.studentFeeStageMapper.batchInsert(nstages);
        }
        // 如果有退费，插入退费表
        if (refund != null) {
            refund.setCreateTime(new Date());
            refund.setCreator(user.getId());
            refund.setRefundDate(new Date());
            refund.setStuId(payMaster.getStuId());
            refund.setPayMasterId(payMaster.getId());
            refund.setCreateType("ORDER_OPERTE_CHANGE_PRODUCT");
            this.studentFeeRefundMapper.insert(refund);
        }

        // 存操作日志
        StudentPayLog log = new StudentPayLog();
        log.setOperateTime(new Date());
        log.setDescription("");
        log.setOperateType("CHANGE_STU");
        log.setOperator(user.getId());
        log.setPayMasterId(oMaster.getId());
        this.studentPayLogMapper.insert(log);

        // 维护commodity（buy_num）
        StudentPayMaster oldPayMaster = this.studentPayMasterMapper.findById(oMasterId);
        Commodity commodity1 = this.commodityMapper.findById(this.commodityMapper.findCommodityIdByClassTypeId(oldPayMaster.getCommodityId()));
        if (commodity1.getBuyNum() > 0) {
            commodity1.setBuyNum(commodity1.getBuyNum() - 1);
        } else {
            commodity1.setBuyNum(0);
        }
        this.commodityMapper.update(commodity1);
        Commodity commodity2 = this.commodityMapper.findById(this.commodityMapper.findCommodityIdByClassTypeId(payMaster.getCommodityId()));
        commodity2.setBuyNum(commodity2.getBuyNum() + 1);
        this.commodityMapper.update(commodity2);

        return "success";
    }

    /**
     *
     * @Title: changeClass
     * @Description: 转班
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-25
     * @user by chopin
     */
    @Override
    public String changeClass_bak(StudentPayMaster payMaster, Integer oMasterId, Users user, List<StudentPaySlave> slaves) {
        // 原订单
        StudentPayMaster oMaster = this.studentPayMasterMapper.findById(oMasterId);
        if (oMaster.getPayStatusCode().equals("PRODUCT_CHANGED") || oMaster.getPayStatusCode().equals("ORDER_STOPED")) {
            return "订单已转班型或已解约，不能做转班型操作";
        }
        oMaster.setBizStatusCode("ORDER_BIZ_CHANGE_PRODUCT");
        oMaster.setPayStatusCode("ORDER_PRODUCT_CHANGED");
        oMaster.setDeleteFlag(1);// 已删除
        oMaster.setUpdator(user.getId().toString());
        oMaster.setUpdateTime(new Date());
        this.studentPayMasterMapper.update(oMaster);

        /* 存主定单，获得订单编号 */
        payMaster.setId(null);
        payMaster.setApplyTime(new Date());
        payMaster.setCreator(user.getId().toString());
        payMaster.setCreateTime(new Date());
        payMaster.setDeleteFlag(0);
        payMaster.setSchoolId(user.getSchoolId());
        payMaster.setApplyChannelCode("ONLINE");
        payMaster.setPayStatusCode("CREATED");
        payMaster.setBizStatusCode("CHANGE_PRODUCT");
        payMaster.setApplyPlaceId(oMaster.getId());// 存原订单编号
        payMaster.setUpdateTime(new Date());
        payMaster.setUpdator(user.getId().toString());
        payMaster.setCompanyId(user.getCompanyId());
        Integer payMasterId = this.studentPayMasterMapper.insertPayMaster(payMaster);
        /* 存子订单表 */
        for (StudentPaySlave slave : slaves) {
            slave.setStuId(payMaster.getStuId());
            slave.setSlaveStatusCode("SUB_ORDER_CREATED");
            slave.setPayMasterId(payMasterId);
            slave.setCompanyId(user.getCompanyId());
        }
        this.studentPaySlaveMapper.batchInsert(slaves);

        // 存操作日志
        StudentPayLog log = new StudentPayLog();
        log.setOperateTime(new Date());
        log.setDescription("");
        log.setOperateType("CHANGE_STU");
        log.setOperator(user.getId());
        log.setPayMasterId(oMaster.getId());
        this.studentPayLogMapper.insert(log);
        return "success";
    }

    /**
     *
     * @Title: changeClass
     * @Description: 转人
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-25
     * @user by chopin
     */
    @Override
    public String changeStudent(Users user, Student student, StudentPayChangeInfo changeInfo, Integer mid) {

        Integer newStudentId = student.getId();
        Integer oldStudentId = changeInfo.getStuId();

        // 1、待转的学员，没有则新增,有则更新 2、增加更新主订单和子订单的学员编号，同时将相关状态更新为“已转人” 3、记录订单改变信息表
        // 4、存操作日志
        /*
         * Student s=studentMapper.queryByCode(student); if(s!=null &&
         * s.getId()!=null){ student.setId(s.getId());
         * studentMapper.update(student); }else{ studentMapper.insert(student);
         * }
         */
        StudentPayMaster payMaster = this.studentPayMasterMapper.findById(mid);

        if (payMaster.getPayStatusCode().equals("ORDER_STOPED")) {
            this.log_student.error(">>> [转人] " + "状态：订单已解约，不能做转人操作");
            return "订单已解约，不能做转人操作";
        }
        if (payMaster.getStuId().equals(student.getId())) {
            this.log_student.error(">>> [转人] " + "状态：不能转给自己");
            return "不能转给自己";
        }
        // 复制订单
        StudentPayMaster payMaster2 = new StudentPayMaster();
        BeanUtils.copyProperties(payMaster, payMaster2);
        payMaster2.setId(null);
        payMaster2.setApplyTime(new Date());
        payMaster2.setStuId(student.getId());
        payMaster2.setCreator(user.getId().toString());
        payMaster2.setCreateTime(new Date());
        payMaster2.setRelatedPayId(mid);
        payMaster2.setCompanyId(user.getCompanyId());
        this.studentPayMasterMapper.insert(payMaster2);

        // 查询出原来子订单
        List<StudentPaySlave> list_1 = this.studentPaySlaveMapper.findByPayMasterId3(mid);
        // 删除子订单
        // studentPaySlaveMapper.deleteByMasterId(mid);
        // 复制子订单
        List<StudentPaySlave> list1 = new ArrayList<StudentPaySlave>();
        list1.addAll(list_1);
        // BeanUtils.copyProperties(list_1, list1);
        for (StudentPaySlave studentPaySlave : list1) {
            studentPaySlave.setId(null);
            studentPaySlave.setStuId(student.getId());
            studentPaySlave.setPayMasterId(payMaster2.getId());
        }
        if (list1.size() > 0) {
            this.studentPaySlaveMapper.batchInsert(list1);
        }

        // 复制分期表
        List<StudentFeeStage> list_2 = this.studentFeeStageMapper.findAll(mid);
        // 批量删除原分期表
        this.studentFeeStageMapper.deleteByMasterId(mid);
        List<StudentFeeStage> list2 = new ArrayList<StudentFeeStage>();
        // BeanUtils.copyProperties(list_2, list2);
        list2.addAll(list_2);
        for (StudentFeeStage studentFeeStage : list2) {
            studentFeeStage.setId(null);
            studentFeeStage.setStuId(student.getId());
            studentFeeStage.setPayMasterId(payMaster2.getId());
            studentFeeStage.setCreateTime(new Date());
            studentFeeStage.setCreator(user.getId());
        }
        if (list2.size() > 0) {
            this.studentFeeStageMapper.batchInsert(list2);
        }

        payMaster.setBizStatusCode("ORDER_BIZ_CHANGE_STU");
        payMaster.setPayStatusCode("ORDER_STU_CHANGED");
        payMaster.setUpdator(user.getId().toString());
        payMaster.setUpdateTime(new Date());
        payMaster.setDeleteFlag(1);
        this.studentPayMasterMapper.update(payMaster);
        // 将前台用户改为vip用户
        if (student.getUserId() != null) {
            UsersFront uf = new UsersFront();
            uf.setId(student.getUserId());
            uf.setVipFlag(1);
            this.usersFrontMapper.update(uf);
        }
        // 为新人增加一条订单

        changeInfo.setPayMasterId(mid);
        changeInfo.setStuId(payMaster.getStuId());
        changeInfo.setCompanyId(user.getCompanyId());
        this.studentPayChangeInfoMapper.insert(changeInfo);
        // studentFeeStageMapper.update2(payMaster);
        // 存操作日志
        StudentPayLog log = new StudentPayLog();
        log.setOperateTime(new Date());
        log.setDescription("");
        log.setOperateType("CHANGE_STU");
        log.setOperator(user.getId());
        log.setPayMasterId(mid);
        log.setCompanyId(user.getCompanyId());
        this.studentPayLogMapper.insert(log);

        // 维护 usersfront（vip_flag）
        Student student1 = this.studentMapper.findById(oldStudentId);
        UsersFront usersFront1 = this.usersFrontMapper.findById(student1.getUserId());
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("stuId", student1.getId());
        Integer count1 = this.studentPayMasterMapper.queryPayMasterBuyCommodityByStuId(map1);
        if (count1 > 0) {
            usersFront1.setVipFlag(1);
        } else {
            usersFront1.setVipFlag(0);
        }
        this.usersFrontMapper.update(usersFront1);

        Student student2 = this.studentMapper.findById(newStudentId);
        UsersFront usersFront2 = this.usersFrontMapper.findById(student2.getUserId());
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("stuId", student2.getId());
        Integer count2 = this.studentPayMasterMapper.queryPayMasterBuyCommodityByStuId(map2);
        if (count2 > 0) {
            usersFront2.setVipFlag(1);
        } else {
            usersFront2.setVipFlag(0);
        }
        this.usersFrontMapper.update(usersFront2);

        // 删除观看记录
        List<Integer> classModuleList = this.classTypeModuleRelationMapper.findClassModuleIdsByClassTypeId(payMaster.getCommodityId());
        if (null != classModuleList && classModuleList.size() > 0) {
            for (Integer classModuleId : classModuleList) {
                // 删除录播记录
                List<Integer> chapterList = this.courseVideoChapterMapper.findIdByModuleId(classModuleId);
                if (null != chapterList && chapterList.size() > 0) {
                    for (Integer chapterId : chapterList) {
                        List<Integer> lectureList = this.courseVideoLectureMapper.findIdByChapterId(chapterId);
                        if (null != lectureList && lectureList.size() > 0) {
                            for (Integer lessonId : lectureList) {
                                Map<String, Integer> userLessonTimeMap = new HashMap<String, Integer>();
                                userLessonTimeMap.put("userId", usersFront1.getId());
                                userLessonTimeMap.put("lessonId", lessonId);
                                this.userLessonTimeMapper.deleteByUserIdAndLessonId(userLessonTimeMap);
                            }
                        }
                    }
                }

                // 删除直播记录
                List<Integer> classModuleNolist = this.classModuleNoServiceImpl.findClassModuleNoIdsByModuleId(classModuleId);
                for (Integer classModuleNoId : classModuleNolist) {
                    List<Integer> classModuleLessonlist = this.classModuleLessonServiceImpl.findClassModuleLessonIdsByModuleNoId(classModuleNoId);
                    for (Integer classModuleLessonId : classModuleLessonlist) {
                        Map<String, Integer> userLessonTimeMap = new HashMap<String, Integer>();
                        userLessonTimeMap.put("userId", usersFront1.getId());
                        userLessonTimeMap.put("lessonId", classModuleLessonId);
                        this.userLessonTimeMapper.deleteByUserIdAndLessonId(userLessonTimeMap);
                    }
                }
            }
        }

        // 删除作业记录
        Map<String, Object> homeworkMap = new HashMap<String, Object>();
        homeworkMap.put("companyId", usersFront1.getCompanyId());
        homeworkMap.put("stuId", oldStudentId);
        homeworkMap.put("courseId", payMaster.getCommodityId());
        this.homeworkServiceImpl.deleteHomeworkHistory(homeworkMap);

        this.log_student
                .info(">>> [转人] " + "状态：success" + ", Step：2 （新旧订单）" + ", 信息：" + "原学生ID = " + oldStudentId + ", 新学生ID =" + newStudentId + ", 原订单 = PayMasterID："
                        + mid + " 课程ClassTypeID：" + payMaster.getCommodityId() + " 课程名称：" + payMaster.getClassTypeName() + ", 新订单 = ID：" + payMaster2.getId());
        return "success";
    }

    /**
     *
     * @Title: changeClass
     * @Description: 解约
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-25
     * @user by chopin
     */
    @Override
    public String unWind(Users user, StudentPayChangeInfo changeInfo, Integer mid) {
        // 1、更新主订单和子订单的状态为“已解约” 2、记录订单改变信息表3、存操作日志
        StudentPayMaster payMaster = this.studentPayMasterMapper.findById(mid);
        if (payMaster.getPayStatusCode().equals("ORDER_STOPED")) {
            return "订单已解约，不能做解约操作";
        }
        payMaster.setUpdator(user.getId().toString());
        payMaster.setUpdateTime(new Date());
        payMaster.setPayStatusCode("STOPED");
        payMaster.setCompanyId(user.getCompanyId());
        this.studentPayMasterMapper.update(payMaster);
        changeInfo.setPayMasterId(mid);
        changeInfo.setStuId(payMaster.getStuId());
        changeInfo.setCompanyId(user.getCompanyId());
        this.studentPayChangeInfoMapper.insert(changeInfo);
        // 存操作日志
        StudentPayLog log = new StudentPayLog();
        log.setOperateTime(new Date());
        log.setDescription("");// TODO 记什么？
        log.setOperateType("");// TODO 解约记什么？
        log.setOperator(user.getId());
        log.setPayMasterId(mid);
        log.setCompanyId(user.getCompanyId());
        this.studentPayLogMapper.insert(log);
        return "success";
    }

    /**
     *
     * @Title: changeClass
     * @Description: 根据考期和资料查询订单列表
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-25
     * @user by chopin
     */
    @Override
    public PageFinder<StudentVo> findListByTerm(StudentVo search) {
        List<StudentVo> data = this.studentPayMasterMapper.findListByTerm(search);
        int rowCount = this.studentPayMasterMapper.pageCount(search);
        PageFinder<StudentVo> pf = new PageFinder<StudentVo>(search.getPage(), search.getPageSize(), rowCount, data);
        return pf;
    }

    /**
     *
     * @Title: changeClass
     * @Description: 根据考期和资料查询订单列表
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-25
     * @user by chopin
     */
    @Override
    public List<StudentVo> exportToExcel(StudentVo search) {
        List<StudentVo> data = this.studentPayMasterMapper.queryListByTerm(search);
        return data;
    }

    @Override
    public List<StudentPayMaster> findByStuMbile(String mobile, Integer companyId, Integer schoolId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", mobile);
        map.put("companyId", companyId);
        map.put("schoolId", schoolId);
        return this.studentPayMasterMapper.findByStuMbile(map);
    }

    @Override
    public List<StudentPayMaster> findByStuMbile2(String mobile, Integer currentCompanyId, Integer schoolId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", mobile);
        map.put("companyId", currentCompanyId);
        map.put("schoolId", schoolId);
        return this.studentPayMasterMapper.findByStuMbile2(map);
    }

    /**
     *
     * @Title: changeClass
     * @Description: 查询未缴费订单
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-25
     * @user by chopin
     */
    @Override
    public List<StudentPayMaster> findUnPayMasterByStuId(Student student) {
        return this.studentPayMasterMapper.findUnPayMasterByStuId(student);
    }

    @Override
    public List<StudentPayMaster> findEntryMessage(Integer stuId, Integer currentCompanyId) {
        StudentPayMaster payMaster = new StudentPayMaster();
        payMaster.setCompanyId(currentCompanyId);
        payMaster.setStuId(stuId);
        return this.studentPayMasterMapper.findEntryMessage(payMaster);
    }

    @Override
    public PageFinder<StudentPayMaster> findList(StudentPayMaster payMaster) {
        Integer count = this.studentPayMasterMapper.getCount(payMaster);
        List<StudentPayMaster> list = this.studentPayMasterMapper.findList(payMaster);
        PageFinder<StudentPayMaster> pageFinder = new PageFinder<StudentPayMaster>(payMaster.getPage(), payMaster.getPageSize(), count, list);
        return pageFinder;
    }

    @Override
    public List<StuPaymasterVoList> queryClassTypeByStuId(StuPaymasterVoList stuPaymasterVoList) {
        // TODO Auto-generated method stub
        return this.studentPayMasterMapper.queryClassTypeByStuId(stuPaymasterVoList);
    }

    @Override
    public StuClassTypeVoList queryClasstypeById(StuClassTypeVoList search) {
        // TODO Auto-generated method stub
        return this.studentPayMasterMapper.queryClasstypeById(search);
    }

    @Override
    public PageFinder<StuPayMasterVo> findStudentPayMasterList(StuPayMasterVo search) {
        List<StuPayMasterVo> data = this.studentPayMasterMapper.stuPayMasterList(search);
        if (data.size() > 0) {
            for (StuPayMasterVo s : data) {
                SysConfigSchool school = this.sysConfigSchoolMapper.findById(s.getSchoolId());
                if (school != null) {
                    s.setSchoolName(school.getSchoolName());
                    Student st = this.studentMapper.findById(s.getStuId());
                    if (st != null) {
                        s.setStuName(st.getName());
                    }
                }
            }
        }
        int count = this.studentPayMasterMapper.stuPayMasterListCount(search);
        PageFinder<StuPayMasterVo> pageFinder = new PageFinder<StuPayMasterVo>(search.getPage(), search.getPageSize(), count, data);
        return pageFinder;
    }

    @Override
    public Integer getCount(StudentPayMaster payMaster) {
        // TODO Auto-generated method stub
        return this.studentPayMasterMapper.getCount(payMaster);
    }

    @Override
    public Integer findByResourceid(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return this.studentPayMasterMapper.findByResourceid(param);
    }

    @Override
    public List<StudentPayMaster> findByStuMbilestu(Integer stuId, Integer companyId, Integer schoolId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stuId", stuId);
        map.put("companyId", companyId);
        map.put("schoolId", schoolId);
        return this.studentPayMasterMapper.findByStuMbilestu(map);
    }

    @Override
    public List<Map> countOrderByDate(StuPayMasterVo stuPayMasterVo) {
        // TODO Auto-generated method stub
        return this.studentPayMasterMapper.countUserByDate(stuPayMasterVo);
    }

    @Override
    public Integer findCountByPayMaster(StudentPayMaster payMaster) {
        // TODO Auto-generated method stub
        return this.studentPayMasterMapper.findCountByPayMaster(payMaster);
    }

    @Override
    public List<Map> queryorderTotalMoney(StuPayMasterVo search) {
        // TODO Auto-generated method stub
        return this.studentPayMasterMapper.queryorderTotalMoney(search);
    }

    @Override
    public Integer findStuCountByCompanyId(Integer companyId) {
        // TODO Auto-generated method stub
        return this.studentPayMasterMapper.findStuCountByCompanyId(companyId);
    }

    /**
     *
     * Class Name: IStudentPayMasterService.java
     *
     * @Description: 根据学员ID和班型查询订单
     * @author 周文斌
     * @date 2015-6-25 上午10:43:36
     * @version 1.0
     * @param companyId
     * @return
     */
    @Override
    public List<StudentPayMaster> findByClassTypeAndStu(StudentPayMaster payMaster) {
        return this.studentPayMasterMapper.findByClassAndStu(payMaster);
    }

    @Override
    public Integer findStuCountBySchoolId(Integer companyId, Integer schoolId) {
        // TODO Auto-generated method stub
        return this.studentPayMasterMapper.findStuCountBySchoolId(companyId, schoolId);
    }

    @Override
    public List<StudentPayMaster> findpayIdByClassTypeStudent(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return this.studentPayMasterMapper.findpayIdByClassTypeStudent(map);
    }

    @Override
    public void updateByStuId(StudentPayMaster payMaster) {
        this.studentPayMasterMapper.updateByStuId(payMaster);
    }

    @Override
    public List<StudentPayMaster> findpayIdByStudentsId(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return this.studentPayMasterMapper.findpayIdByStudentsId(map);
    }

    @Override
    public List<StudentPayMaster> queryStusByClassPackage(StudentPayMaster payMaster) {
        // TODO Auto-generated method stub
        return this.studentPayMasterMapper.queryStusByClassPackage(payMaster);
    }

    @Override
    public List<StudentPayMaster> findByCommodityId(StudentListVo search) {
        return this.studentPayMasterMapper.findByCommodityId(search);
    }

    @Override
    public List<StudentPayMasterVo4> queryStuHasByClass(StudentClassLeanDetailVo search) {
        return this.studentPayMasterMapper.queryStuHasByClass(search);
    }

    /**
     * Class Name: IStudentPayMasterService.java
     *
     * @Description: 升级会员时，查询订单
     * @author xukaiqiang
     * @date 2016年5月30日 下午3:28:14
     * @modifier
     * @modify-date 2016年5月30日 下午3:28:14
     * @version 1.0
     * @param map
     * @return
     */
    @Override
    public StudentPayMaster findOrder(Map map) {
        return this.studentPayMasterMapper.findOrder(map);
    }

    @Override
    public Integer queryCommodityIdById(Integer id) {
        return this.studentPayMasterMapper.queryCommodityIdById(id);
    }

    @Override
    public List<StudentPayMaster> findByClassPackageAndStu(StudentPayMaster search) {
        // TODO Auto-generated method stub
        return this.studentPayMasterMapper.findByClassPackageAndStu(search);
    }

    @Override
    public Integer queryPayMasterBuyCommodityByStuId(Map<String, Object> map) {
        return this.studentPayMasterMapper.queryPayMasterBuyCommodityByStuId(map);
    }

    @Override
    public Integer queryPayMasterBuyCommodityByStuIdAndCommodityId(Map<String, Object> map) {
        return this.studentPayMasterMapper.queryPayMasterBuyCommodityByStuIdAndCommodityId(map);
    }

    @Override
    public List<StudentPayMaster4ClassPackageVo> findClassPackageEntryMessage(StudentPayMaster spm) {
        return this.studentPayMasterMapper.findClassPackageEntryMessage(spm);
    }

    @Override
    public ClassPackageCategory selectClassPackageCategoryCode(Map<String, Object> map) {
        return this.studentPayMasterMapper.selectClassPackageCategoryCode(map);
    }

    @Override
    public Integer selectStuNumByComId(Integer comId) {
        return this.studentPayMasterMapper.selectStuNumByComId(comId);
    }
}