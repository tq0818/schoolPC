package com.yuxin.wx.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.company.ICompanyConfigProxyOrgService;
import com.yuxin.wx.api.company.ICompanyCouponsLibService;
import com.yuxin.wx.api.company.ICompanyCouponsPatchService;
import com.yuxin.wx.api.company.ICompanyInvitConfigOrgService;
import com.yuxin.wx.api.company.ICompanyInvitConfigService;
import com.yuxin.wx.api.company.ICompanyInvitConfigTeacherService;
import com.yuxin.wx.api.company.IOrganInviteRewardRecordService;
import com.yuxin.wx.api.company.ITeacherInviteRewardRecordService;
import com.yuxin.wx.api.pay.IPayOrderService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.api.system.ISysLogInvitLogService;
import com.yuxin.wx.api.user.IUserIntegralFlowService;
import com.yuxin.wx.api.user.IUserInviteRewardsRecordService;
import com.yuxin.wx.api.user.IUsersFrontMyCouponsService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.model.company.CompanyConfigProxyOrg;
import com.yuxin.wx.model.company.CompanyCouponsLib;
import com.yuxin.wx.model.company.CompanyCouponsPatch;
import com.yuxin.wx.model.company.CompanyInvitConfig;
import com.yuxin.wx.model.company.CompanyInvitConfigOrg;
import com.yuxin.wx.model.company.CompanyInvitConfigTeacher;
import com.yuxin.wx.model.company.OrganInviteRewardRecord;
import com.yuxin.wx.model.pay.PayOrder;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.system.SysLogInvitLog;
import com.yuxin.wx.model.teacher.TeacherInviteRewardRecord;
import com.yuxin.wx.model.user.UserIntegralFlow;
import com.yuxin.wx.model.user.UserInviteRewardsRecord;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.model.user.UsersFrontMyCoupons;
import com.yuxin.wx.vo.user.UsersFrontVo;

@Component
public class InviteRecordUtil {
    static Log log = LogFactory.getLog("log");

    private static IUsersFrontService usersFrontService;

    private static IUserIntegralFlowService userIntegralFlowServiceImpl;

    private static ICompanyInvitConfigService companyInvitConfigServie;

    private static IPayOrderService payOrderServiceImpl;

    private static IUserInviteRewardsRecordService userInviteRewardsRecordServiceImpl;

    private static ICompanyCouponsPatchService companyCouponsPatchServiceImpl;

    private static ICompanyCouponsLibService companyCouponsLibServiceImpl;

    private static IUsersFrontMyCouponsService usersFrontMyCouponsServiceImpl;

    private static ISysConfigServiceService sysConfigServiceServiceImpl;

    private static ISysLogInvitLogService sysLogInvitLogServiceImpl;

    private static ICompanyInvitConfigOrgService companyInvitConfigOrgServiceImpl;

    private static ICompanyInvitConfigTeacherService companyInvitConfigTeacherServiceImpl;

    private static ITeacherInviteRewardRecordService teacherInviteRewardRecordServiceImpl;

    private static IOrganInviteRewardRecordService organInviteRewardRecordServiceImpl;
    private static ICompanyConfigProxyOrgService companyConfigProxyOrgServiceImpl;

    @Autowired
    public void setIUsersFrontService(IUsersFrontService usersFrontService) {
        InviteRecordUtil.usersFrontService = usersFrontService;
    }

    @Autowired
    public void setSysLogInvitLogServiceImpl(ISysLogInvitLogService sysLogInvitLogServiceImpl) {
        InviteRecordUtil.sysLogInvitLogServiceImpl = sysLogInvitLogServiceImpl;
    }

    @Autowired
    public void setISysConfigServiceServiceImpl(ISysConfigServiceService sysConfigServiceServiceImpl) {
        InviteRecordUtil.sysConfigServiceServiceImpl = sysConfigServiceServiceImpl;
    }

    @Autowired
    public void setCompanyCouponsLibServiceImpl(ICompanyCouponsLibService companyCouponsLibServiceImpl) {
        InviteRecordUtil.companyCouponsLibServiceImpl = companyCouponsLibServiceImpl;
    }

    @Autowired
    public void setUsersFrontMyCouponsServiceImpl(IUsersFrontMyCouponsService usersFrontMyCouponsServiceImpl) {
        InviteRecordUtil.usersFrontMyCouponsServiceImpl = usersFrontMyCouponsServiceImpl;
    }

    @Autowired
    public void setICompanyCouponsPatchServiceImpl(ICompanyCouponsPatchService companyCouponsPatchServiceImpl) {
        InviteRecordUtil.companyCouponsPatchServiceImpl = companyCouponsPatchServiceImpl;
    }

    @Autowired
    public void setIUserInviteRewardsRecordServiceImpl(IUserInviteRewardsRecordService userInviteRewardsRecordServiceImpl) {
        InviteRecordUtil.userInviteRewardsRecordServiceImpl = userInviteRewardsRecordServiceImpl;
    }

    @Autowired
    public void setPayOrderServiceImpl(IPayOrderService payOrderServiceImpl) {
        InviteRecordUtil.payOrderServiceImpl = payOrderServiceImpl;
    }

    @Autowired
    public void setICompanyInvitConfigServie(ICompanyInvitConfigService companyInvitConfigServie) {
        InviteRecordUtil.companyInvitConfigServie = companyInvitConfigServie;
    }

    @Autowired
    public void setIUserIntegralFlowServiceImpl(IUserIntegralFlowService userIntegralFlowServiceImpl) {
        InviteRecordUtil.userIntegralFlowServiceImpl = userIntegralFlowServiceImpl;
    }

    @Autowired
    public void setITeacherInviteRewardRecordService(ITeacherInviteRewardRecordService teacherInviteRewardRecordServiceImple) {
        InviteRecordUtil.teacherInviteRewardRecordServiceImpl = teacherInviteRewardRecordServiceImple;
    }

    @Autowired
    public void setIOrganInviteRewardRecordService(IOrganInviteRewardRecordService organInviteRewardRecordServiceImple) {
        InviteRecordUtil.organInviteRewardRecordServiceImpl = organInviteRewardRecordServiceImple;
    }

    @Autowired
    public void setICompanyInvitConfigOrgService(ICompanyInvitConfigOrgService companyInvitConfigOrgServiceImpl) {
        InviteRecordUtil.companyInvitConfigOrgServiceImpl = companyInvitConfigOrgServiceImpl;
    }

    @Autowired
    public void setICompanyInvitConfigTeacherService(ICompanyInvitConfigTeacherService companyInvitConfigTeacherServiceImpl) {
        InviteRecordUtil.companyInvitConfigTeacherServiceImpl = companyInvitConfigTeacherServiceImpl;
    }

    @Autowired
    public void setICompanyConfigProxyOrgService(ICompanyConfigProxyOrgService companyConfigProxyOrgServiceImpl) {
        InviteRecordUtil.companyConfigProxyOrgServiceImpl = companyConfigProxyOrgServiceImpl;
    }

    /**
     * 
     * 邀请奖励
     * 
     * 系统目前有三种邀请形式：学员邀请、老师邀请、机构邀请
     * 
     * @author licong
     * @date 2016年8月4日 下午4:05:05
     * @param request
     * @param type
     *            0:注册,1:消费
     * @param money
     *            消费的金额
     * @return
     */

    public static boolean inviteReward(Integer invitedUserId, PayOrder order) {
        UsersFrontVo invitedPerson = usersFrontService.findUsersFrontVoById(invitedUserId);// 被邀请人
        if (invitedPerson == null) {
            log.error(">>>>>>>>>>>>>>b被邀请人没有查到");
            return false;
        }
        // 查询机构邀请码服务是否开启
        if (!checkCompanyServiceIsOpen(invitedPerson.getCompanyId(), "SERVICE_PROMOTION")) {
            return true;
        }

        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("userId", invitedPerson.getId());
        condition.put("stuId", invitedPerson.getStuId());
        Map<String, Object> findInvitedTypeAndId = userInviteRewardsRecordServiceImpl.findInvitedTypeAndId(condition);
        if (findInvitedTypeAndId == null) {
            return true;
        }
        switch (Integer.parseInt(findInvitedTypeAndId.get("inviteType").toString())) {
        case 0: {
            stuInviteRecord(invitedPerson, order);
            break;
        }
        case 1: {
            teacherInviteRecord(invitedPerson, order);
            break;
        }
        case 2: {
            orgInviteRecord(invitedPerson, order);
            break;
        }
        default:
            break;
        }
        return true;
    }

    /**
     * 学员邀请奖励
     */
    public static boolean stuInviteRecord(UsersFrontVo invitedPerson, PayOrder order) {
        // 查询公司学员邀请码设置
        CompanyInvitConfig cic = companyInvitConfigServie.findInvitConfigByCompanyId(invitedPerson.getCompanyId());
        if (cic == null || cic.getOpenFlag() == null || cic.getOpenFlag() != 1) {
            return true;
        }
        if (!checkChuPay(invitedPerson.getId(), invitedPerson.getCompanyId())) {
            return false;
        }
        Double oneInviteCsptMoney = cic.getOneInviteCsptMoney();
        Integer oneInviteCsptIntegral = cic.getOneInviteCsptIntegral();
        Integer oneInviteCsptPercent = cic.getOneInviteCsptPercent();

        Integer twoInviteFlag = cic.getTwoInviteFlag();
        Double twoInviteCsptMoney = cic.getTwoInviteCsptMoney();
        Integer twoInviteCsptIntegral = cic.getTwoInviteCsptIntegral();
        Integer twoInviteCsptPercent = cic.getTwoInviteCsptPercent();

        UsersFrontVo oneInvitePerson = null;// 一级邀请人
        UsersFrontVo twoInvitePerson = null;// 二级邀请人
        UsersFrontVo ThreeInvitePerson = null;// 三级邀请人
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>邀请人id：" + invitedPerson.getId());
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>邀请人信息：" + invitedPerson.toString());
        // 如果是首次消费，并且没有被邀请人，则直接返回
        oneInvitePerson = getInviteUsersFrontVo(invitedPerson);
        if (oneInvitePerson == null) {
            return true;
        }
        log.info("================进入插入邀请奖励方法==============");
        twoInvitePerson = getInviteUsersFrontVo(oneInvitePerson);
        if (twoInviteFlag != null && twoInviteFlag == 1 && twoInvitePerson != null) {
            ThreeInvitePerson = getInviteUsersFrontVo(twoInvitePerson);
        }
        log.info("================进入插入邀请奖励方法begin==============");
        List<UserInviteRewardsRecord> inviteRewardList = new ArrayList<UserInviteRewardsRecord>();
        // 邀请人插入消费记录，注：目的是为了后台查询统计消费的人数
        UserInviteRewardsRecord invitedReward = getInvitReward(invitedPerson, oneInvitePerson, null, null);
        inviteRewardList.add(invitedReward);

        // 一级邀请人获得奖励
        UserInviteRewardsRecord oneInviteReward = getInvitReward(oneInvitePerson, twoInvitePerson, invitedPerson, null);// 这里的二级邀请开关设置为1的原因：被邀请人首次消费时邀请人必得奖励，所以二级开关写死为开启状态
        oneInviteReward.setInviteCode(oneInvitePerson.getInvitCode());
        // 奖励代金券
        if (moreThanZero(oneInviteCsptMoney)) {
            log.info(">>>>>>>>>>邀请人获得代金券奖励begin");
            String rewardsCode = InviteCodeUtil.productCouponsCode(1).get(0);
            oneInviteReward.setRewardsMoney(oneInviteCsptMoney);
            oneInviteReward.setRewardsCode(rewardsCode);
            insertUserCoupons(oneInvitePerson, rewardsCode, oneInviteCsptMoney, null);
            log.info(">>>>>>>>>>邀请人获得代金券奖励end");
        }
        // 提成奖励
        Double oneRewardMoney = null;
        if (oneInviteCsptPercent != null) {
            oneRewardMoney = fromatDouble(order.getPayPrice() * oneInviteCsptPercent * 0.01);
            log.info(">>>>>>>>>>邀请人获得提成奖励金钱为：" + oneRewardMoney);
            if (moreThanZero(oneRewardMoney)) {
                log.info(">>>>>>>>>>邀请人获得提成奖励begin");
                String rewardsCode = InviteCodeUtil.productCouponsCode(1).get(0);
                oneInviteReward.setRewardsMoney(oneRewardMoney);
                oneInviteReward.setRewardsCode(rewardsCode);
                // 给一级邀请人生成代金券
                insertUserCoupons(oneInvitePerson, rewardsCode, oneRewardMoney, null);
                log.info(">>>>>>>>>>邀请人获得提成奖励end");
            }
        }
        // 奖励积分
        if (oneInviteCsptIntegral != null) {
            log.info(">>>>>>>>>>邀请人获得积分奖励begin");
            oneInviteReward.setRewardsIntegral(oneInviteCsptIntegral);
            // 给一级邀请人赠送积分
            updateInteger(oneInvitePerson, oneInviteCsptIntegral);
            log.info(">>>>>>>>>>邀请人获得积分奖励end");
        }
        if (moreThanZero(oneInviteCsptMoney) || (oneRewardMoney != null && moreThanZero(oneRewardMoney)) || oneInviteCsptIntegral != null)
            inviteRewardList.add(oneInviteReward);

        // 二级邀请人或得奖励
        if (twoInviteFlag != null && twoInviteFlag == 1 && twoInvitePerson != null) {
            UserInviteRewardsRecord twoInviteReward = getInvitReward(twoInvitePerson, ThreeInvitePerson, oneInvitePerson, invitedPerson);
            twoInviteReward.setInviteCode(oneInvitePerson.getInvitCode());
            // 奖励代金券
            if (moreThanZero(twoInviteCsptMoney)) {
                log.info(">>>>>>>>>>二级邀请人获得代金券奖励begin");
                String rewardsCode = InviteCodeUtil.productCouponsCode(1).get(0);
                twoInviteReward.setRewardsMoney(twoInviteCsptMoney);
                twoInviteReward.setRewardsCode(rewardsCode);
                insertUserCoupons(twoInvitePerson, rewardsCode, twoInviteCsptMoney, null);
                log.info(">>>>>>>>>>二级邀请人获得代金券奖励end");
            }
            // 提成奖励
            Double twoRewardMoney = null;
            if (twoInviteCsptPercent != null) {
                twoRewardMoney = fromatDouble(order.getPayPrice() * twoInviteCsptPercent * 0.01);
                log.info(">>>>>>>>>>二级邀请人获得提成奖励金钱为：" + twoRewardMoney);
                if (moreThanZero(twoRewardMoney)) {
                    log.info(">>>>>>>>>>二级邀请人获得提成奖励begin");
                    String rewardsCode = InviteCodeUtil.productCouponsCode(1).get(0);
                    twoInviteReward.setRewardsMoney(twoRewardMoney);
                    twoInviteReward.setRewardsCode(rewardsCode);
                    // 给二级邀请人生成代金券
                    insertUserCoupons(twoInvitePerson, rewardsCode, twoRewardMoney, null);
                    log.info(">>>>>>>>>>二级邀请人获得提成奖励end");
                }
            }
            // 奖励积分
            if (twoInviteCsptIntegral != null) {
                log.info(">>>>>>>>>>二级邀请人获得积分奖励begin");
                twoInviteReward.setRewardsIntegral(twoInviteCsptIntegral);
                // 给二级邀请人赠送积分
                updateInteger(twoInvitePerson, twoInviteCsptIntegral);
                log.info(">>>>>>>>>>二级邀请人获得积分奖励end");
            }
            if (moreThanZero(twoInviteCsptMoney) || (twoRewardMoney != null && moreThanZero(twoRewardMoney)) || twoInviteCsptIntegral != null)
                inviteRewardList.add(twoInviteReward);
        }

        log.info(">>>>插入获得奖励的条数：" + inviteRewardList.size());
        userInviteRewardsRecordServiceImpl.batchInsert(inviteRewardList);
        return true;
    }

    /**
     * 教师邀请奖励
     * 
     * @param request
     * @param invitedPerson
     *            被邀请人
     * @param type
     *            0注册 1消费
     * @param money
     */
    private static void teacherInviteRecord(UsersFrontVo invitedPerson, PayOrder order) {

        CompanyInvitConfigTeacher cct = companyInvitConfigTeacherServiceImpl.findByCompanyId(invitedPerson.getCompanyId());

        if (cct == null || cct.getOpenFlag() == 0)
            return;

        if ((order.getCommdityType().equals("COMMODITY_CLASS") && cct.getCastTypeCourse() == 1)
                || (order.getCommdityType().equals("COMMODITY_PACKAGE") && cct.getCastTypePackage() == 1)
                || (order.getCommdityType().equals("INTEGRAL") && cct.getCastTypeIntegral() == 1)
                || (order.getCommdityType().equals("MEMBER") && cct.getCastTypeMember() == 1)) {
            TeacherInviteRewardRecord record = new TeacherInviteRewardRecord();
            // 删除邀请记录
            record.setStuId(invitedPerson.getStuId());
            record.setReason(1);
            List<TeacherInviteRewardRecord> list = teacherInviteRewardRecordServiceImpl.findTeacherInviteRewardRecordByPage(record);
            for (TeacherInviteRewardRecord tr : list) {
                tr.setReason(0);
                teacherInviteRewardRecordServiceImpl.update(tr);
            }
            record = new TeacherInviteRewardRecord();
            if (checkChuPay(invitedPerson.getId(), invitedPerson.getCompanyId()) && cct.getInvitCastAwardFlag() != null && cct.getInvitCastAwardFlag() == 1) {// 首次消费
                // 首次消费奖励
                record.setReason(2);
            } else if (cct.getInvitCastAwardFlag() != null && cct.getInvitCastAwardFlag() == 0) {
                // 每次消费奖励
                record.setReason(3);
            }
            if (record.getReason() != null) {
                // 查询邀请该学生的老师
                TeacherInviteRewardRecord search = new TeacherInviteRewardRecord();
                search.setStuId(invitedPerson.getStuId());
                search.setReason(0);// 注册奖励记录
                List<TeacherInviteRewardRecord> teacherInviteRewardRecordList = teacherInviteRewardRecordServiceImpl
                        .findTeacherInviteRewardRecordByPage(search);
                if (teacherInviteRewardRecordList != null && teacherInviteRewardRecordList.size() > 0 && cct.getInvitCastAward() != null) {
                    TeacherInviteRewardRecord teacherInviteRewardRecord = teacherInviteRewardRecordList.get(0);
                    record.setCompanyId(invitedPerson.getCompanyId());
                    record.setRecordTime(new Date());
                    record.setRewardsMoney(order.getPayPrice() * cct.getInvitCastAward() / 100);
                    record.setStuId(invitedPerson.getStuId());
                    record.setInviteCode(teacherInviteRewardRecord.getInviteCode());
                    record.setTeacherId(teacherInviteRewardRecord.getTeacherId());
                    record.setPayOrderId(order.getId());
                    teacherInviteRewardRecordServiceImpl.insert(record);
                }
            }
        }
    }

    /**
     * 机构邀请奖励
     * 
     * @param request
     * @param invitedPerson
     * @param type
     * @param money
     */
    private static void orgInviteRecord(UsersFrontVo invitedPerson, PayOrder order) {

        CompanyInvitConfigOrg cco = companyInvitConfigOrgServiceImpl.findByCompanyId(invitedPerson.getCompanyId());

        if (cco == null || cco.getOpenFlag() == 0)
            return;

        if ((order.getCommdityType().equals("COMMODITY_CLASS") && cco.getCastTypeCourse() == 1)
                || (order.getCommdityType().equals("COMMODITY_PACKAGE") && cco.getCastTypePackage() == 1)
                || (order.getCommdityType().equals("INTEGRAL") && cco.getCastTypeIntegral() == 1)
                || (order.getCommdityType().equals("MEMBER") && cco.getCastTypeMember() == 1)) {
            OrganInviteRewardRecord record = new OrganInviteRewardRecord();
            // 删除邀请记录
            record.setStuId(invitedPerson.getStuId());
            record.setReason(1);
            List<OrganInviteRewardRecord> list = organInviteRewardRecordServiceImpl.findOrganInviteRewardRecordByPage(record);
            for (OrganInviteRewardRecord tr : list) {
                tr.setReason(0);
                organInviteRewardRecordServiceImpl.update(tr);
            }
            record = new OrganInviteRewardRecord();
            if (checkChuPay(invitedPerson.getId(), invitedPerson.getCompanyId()) && cco.getInvitCastAwardFlag() != null && cco.getInvitCastAwardFlag() == 1) {// 首次消费
                // 首次消费奖励
                record.setReason(2);
            } else if (cco.getInvitCastAwardFlag() != null && cco.getInvitCastAwardFlag() == 0) {
                // 每次消费奖励
                record.setReason(3);
            }
            if (record.getReason() != null) {
                // 查询邀请该学生的老师
                OrganInviteRewardRecord search = new OrganInviteRewardRecord();
                search.setStuId(invitedPerson.getStuId());
                search.setReason(0);// 注册奖励记录
                List<OrganInviteRewardRecord> organInviteRewardRecordList = organInviteRewardRecordServiceImpl.findOrganInviteRewardRecordByPage(search);
                if (organInviteRewardRecordList != null && organInviteRewardRecordList.size() > 0 && cco.getInvitCastAward() != null) {
                    OrganInviteRewardRecord organInviteRewardRecord = organInviteRewardRecordList.get(0);
                    record.setCompanyId(invitedPerson.getCompanyId());
                    record.setRecordTime(new Date());
                    if (cco.getRewardsCustomSetting() != null && cco.getRewardsCustomSetting() == 1) {
                        CompanyConfigProxyOrg companyConfigProxyOrg = new CompanyConfigProxyOrg();
                        companyConfigProxyOrg.setInviteCode(organInviteRewardRecord.getInviteCode());
                        companyConfigProxyOrg.setStatus(1);
                        companyConfigProxyOrg.setDelFlag(0);
                        List<CompanyConfigProxyOrg> companyConfigProxyOrgList = companyConfigProxyOrgServiceImpl
                                .findCompanyConfigProxyOrgByPage(companyConfigProxyOrg);
                        if (companyConfigProxyOrgList != null && companyConfigProxyOrgList.size() > 0) {
                            companyConfigProxyOrg = companyConfigProxyOrgList.get(0);
                            record.setRewardsMoney(order.getPayPrice() * companyConfigProxyOrg.getCommissionRate() / 100);
                        } else {
                            record.setRewardsMoney(order.getPayPrice() * cco.getInvitCastAward() / 100);
                        }
                    } else {
                        record.setRewardsMoney(order.getPayPrice() * cco.getInvitCastAward() / 100);
                    }
                    record.setStuId(invitedPerson.getStuId());
                    record.setInviteCode(organInviteRewardRecord.getInviteCode());
                    record.setProxyOrganId(organInviteRewardRecord.getProxyOrganId());
                    record.setPayOrderId(order.getId());
                    organInviteRewardRecordServiceImpl.insert(record);
                }
            }
        }
    }

    /**
     * 生成一个学员邀请人的奖励记录
     * 
     * @author licong
     * @date 2016年8月5日 下午2:30:53
     * @param
     * @param invitedPerson
     *            被邀请人
     * @param parentPerson
     *            邀请人
     * @param cidPerson
     *            下级被邀请人
     * @param ccidPerson
     *            下下级被邀请人
     * @param reason
     *            0-被邀请注册 1-被邀请人首次消费
     * @return
     */
    public static UserInviteRewardsRecord getInvitReward(UsersFrontVo invitedPerson, UsersFrontVo parentPerson, UsersFrontVo cidPerson,
            UsersFrontVo ccidPerson) {
        UserInviteRewardsRecord uirr = new UserInviteRewardsRecord();
        uirr.setUserId(invitedPerson.getId());
        if (parentPerson != null)
            uirr.setParentId(parentPerson.getId());
        if (cidPerson != null)
            uirr.setCid(cidPerson.getId());
        if (ccidPerson != null)
            uirr.setCcid(ccidPerson.getId());
        uirr.setCreateTime(new Date());
        uirr.setReason(1);
        uirr.setCompanyId(invitedPerson.getCompanyId());
        return uirr;
    }

    /**
     * 根据邀请人获取被邀请人信息
     * 
     * @author licong
     * @date 2016年8月5日 上午11:10:54
     * @param
     * @param invitedPerson
     *            邀请人
     * @return invitePerson 被邀请人
     */
    public static UsersFrontVo getInviteUsersFrontVo(UsersFrontVo invitedPerson) {
        UsersFrontVo invitePerson = null;
        UserInviteRewardsRecord uirr = new UserInviteRewardsRecord();
        uirr.setCompanyId(invitedPerson.getCompanyId());
        uirr.setUserId(invitedPerson.getId());
        uirr.setReason(0);
        invitePerson = userInviteRewardsRecordServiceImpl.findRewardRecordBySearch(uirr);
        return invitePerson;
    }

    public static String checkChuRegist(HttpServletRequest request, String inviteType) {
        String inviteCode = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return inviteCode;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(inviteType)) {
                inviteCode = cookie.getValue();
                break;
            }
        }
        if (StringUtils.isBlank(inviteCode)) {
            return null;
        }
        return inviteCode;
    }

    /**
     * 删除cookie
     * 
     * @param request
     * @param response
     * @param key
     */
    public static void delInviteCookie(HttpServletRequest request, HttpServletResponse response, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    // 设置生存期为0
                    cookie.setMaxAge(0);
                    // 设回Response中生效
                    response.addCookie(cookie);
                }
            }
        }
    }

    public static boolean checkChuPay(Integer userId, Integer companyId) {
        int count = payOrderServiceImpl.findOrderFinishCountByUserId(userId, companyId);
        log.info(">>>>>>>>>>判断是否是首次消费：" + count);
        return (count == 0 || count == 1) ? true : false;
    }

    public static boolean moreThanZero(Double money) {
        if (money == null)
            return false;
        BigDecimal val = new BigDecimal(money);
        BigDecimal zero = new BigDecimal(0);
        return val.compareTo(zero) > 0 ? true : false;
    }

    public static Double fromatDouble(Double money) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(money));
    }

    /**
     * 
     * @author licong
     * @date 2016年8月12日 下午2:25:55
     * @param
     * @param user
     *            获得奖励代金券的学员
     * @param rewardsCode
     *            奖励的代金券码
     * @param insCashNum
     *            奖励代金券的金额
     * @param userDate
     *            奖励代金券的有效期
     */
    public static void insertUserCoupons(UsersFrontVo user, String rewardsCode, Double insCashNum, Integer useDate) {
        // 检查批次，如果没有代金券批次则生成一个批次
        CompanyCouponsPatch ccp = new CompanyCouponsPatch();
        ccp.setCompanyId(user.getCompanyId());
        ccp.setUseWay(3);
        ccp = companyCouponsPatchServiceImpl.findCompanyCouponsPatchBySearch(ccp);
        if (ccp == null) {
            ccp = new CompanyCouponsPatch();
            ccp.setName("邀请码");
            ccp.setTotalNum(0);
            ccp.setStatus(1 + "");
            ccp.setIssueWay(0);
            ccp.setUseWay(3);
            ccp.setDelFlag(0);
            ccp.setCompanyId(user.getCompanyId());
            ccp.setSendDate(new Date());
            companyCouponsPatchServiceImpl.insert(ccp);
        }
        // 插入代金券说明
        CompanyCouponsLib ccl = new CompanyCouponsLib();
        ccl.setCode(rewardsCode);
        ccl.setStatus(0);
        ccl.setPatchId(ccp.getId());
        ccl.setUserId(user.getId());
        ccl.setInsCashNum(insCashNum);
        ccl.setUseWay(3 + "");
        ccl.setUseRange(3);
        ccl.setDelFlag(0);
        if (useDate != null && useDate > 0) {
            ccl.setTimeLimitFrom(new Date());
            ccl.setTimeLimitTo(DateUtil.addDate(new Date(), useDate));
        }
        companyCouponsLibServiceImpl.insert(ccl);
        // 插入我的代金券
        UsersFrontMyCoupons ufmc = new UsersFrontMyCoupons();
        ufmc.setUserId(user.getId());
        ufmc.setCouponsCode(rewardsCode);
        ufmc.setStatus(0);
        usersFrontMyCouponsServiceImpl.insert(ufmc);
        // 代金券批次发放数量+1
        Integer totalNum = ccp.getTotalNum();
        ccp.setTotalNum(totalNum + 1);
        companyCouponsPatchServiceImpl.update(ccp);
    }

    public static void updateInteger(UsersFrontVo user, Integer rewardsIntegral) {
        // 邀请人插入积分变化记录
        UserIntegralFlow uif1 = new UserIntegralFlow();
        uif1.setReason("被邀请人首次消费");
        uif1.setUpdateTime(new Date());
        uif1.setRecord(rewardsIntegral);
        Integer integralRemaining = user.getIntegralRemaining();
        integralRemaining = integralRemaining == null ? 0 : integralRemaining;
        uif1.setLastRecord(integralRemaining + rewardsIntegral);
        uif1.setStuId(user.getStuId());
        uif1.setUserId(user.getId());
        userIntegralFlowServiceImpl.insert(uif1);
        // 更新邀请人的积分余额
        UsersFront uf = new UsersFront();
        uf.setId(user.getId());
        uf.setIntegralRemaining(integralRemaining + rewardsIntegral);
        usersFrontService.update(uf);
    }

    /**
     * 查看机构的服务是否开启
     * 
     * @author licong
     * @date 2016年8月8日 下午5:47:54
     * @param
     * @param companyId
     * @param groupCode
     *            服务的code码
     * @return
     */
    public static boolean checkCompanyServiceIsOpen(Integer companyId, String groupCode) {
        SysConfigService search = new SysConfigService();
        search.setCompanyId(companyId);
        search.setGroupCode(groupCode);
        SysConfigService couponsConfig = sysConfigServiceServiceImpl.findExist(search);
        return (couponsConfig != null && couponsConfig.getDelFlag() == 1) ? true : false;
    }

    public static void insertInviteLog(String inviteCode, HttpServletRequest request) {
        SysLogInvitLog slil = new SysLogInvitLog();
        slil.setInviteCode(inviteCode);
        slil.setRequestTime(new Date());
        String ip = WebUtils.getIpAddr(request);
        if (ip != null) {
            if (ip.indexOf(",") != -1) {
                String[] ips = ip.split(",");
                ip = ips[0];
            }
            // String str = AddressUtils.getResult("ip=" + ip, "utf-8");
            // try {
            // slil.setIp(AddressUtils.getIsp(str));
            // } catch (UnsupportedEncodingException e) {
            // e.printStackTrace();
            // }
        }
        sysLogInvitLogServiceImpl.insert(slil);
    }
}
