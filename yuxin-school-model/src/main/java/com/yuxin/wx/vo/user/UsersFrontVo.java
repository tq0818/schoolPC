package com.yuxin.wx.vo.user;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

public class UsersFrontVo extends BaseEntity {

    private String username; /* 用户名 */
    private String password; /* 密码 */
    private String mobile; /* 手机号 */
    private String email; /* 邮箱 */
    private Integer schoolId; /* 所属校区id */
    private Integer status; /* 用户状态（1：有效；0：无效），默认为1 */
    private Integer companyId; /* 所属公司id */
    private Integer emailAuthSign; /* 是否通过邮箱验证 */
    private Date emailAuthSendtime; /* 发送邮箱验证时间 */
    private String emailAuthCode; /* 发送的邮箱验证码 */
    private String userCity; /* 用户所在城市，根据ip地址获取 */
    private String uuid; /* 用户唯一标识，32位的字符串 */
    private String headPicMin; /* 用户小头像地址 */
    private String headPicMid; /* 用户中头像地址 */
    private String headPicMax; /* 用户大头像地址 */
    private Date registTime; /* 注册时间 */
    private String interests; /* 兴趣标签，存储项目代码 */
    private Integer vipFlag; /* vip标记，默认为0，购买课程后修改为1 */
    private Integer registType; /* 注册类型，默认为1网页注册 */
    private String promoteSource; /* 用户来源地址，从哪个网站过来的 */
    private String promoteKeyword; /* 推广的关键字 */
    private String userSign; /* 用户签名 */
    private String nickName; /* 昵称 */
    private double recharge;

    private Integer stuId; /* 学生ID */
    private String name; /* 姓名 */
    private String sex; /* 性别（男：male；女：female） */
    private String identityTypeCode; /* 证件类型（身份证、护照、港澳通行证、台胞证、军官证、士官证等），字典表 */
    private String identityId; /* 证件号码（身份证号、护照、港澳通行证、台胞证、军官证、士官证等） */
    private Integer age; /* 年龄 */
    private Date birthday; /* 出生年月 */
    private String educationCode; /* 最高学历，字典表 */
    private String hkAddress; /* 户口所在地 */
    private String qq; /* QQ号码 */
    private String homePhone; /* 家庭电话 */
    private String officePhone; /* 办公电话 */
    private String weixinId; /* 微信号 */
    private String emergencyContact; /* 紧急联系人 */
    private String emergencyPhone; /* 紧急联系电话 */
    private String remark; /* 学员备注 */
    private Date createTime;
    private Integer creator; /* 操作员 */
    private Date updateTime;
    private Integer updator;
    private Integer deleteFlag; /* 删 */

    private String domain; /* 域名 */

    private String flag;

    private String schoolName;

    private String startDate;
    private String endDate;

    private Integer timeLen; /* 时间差 */
    private String timeMark;
    private String url;

    private String memberLevel;/* 会员等级 */
    private Integer memberId;/* 会员id */
    private Integer memberBuyLength;/* 会员购买的时长,累积型会员为-1 */
    private Date memberEndTime;/* 会员结束时间 */
    private Integer integralRemaining;/* 积分余额 */
    private Integer memberStatus;/* 会员状态 : 0无效 1有效 */
    private Integer companyVipIsOpen; // 机构是否开启会员服务
    private String integralName; // 积分名称
    private Integer companyIntegralIsOpen; // 机构是否开机积分服务
    private String userVipConfigIco; // 用户的会员图标
    private String vipIsOverdue; // 用户vip是否过期 0：已过期 1：未过期 2:无限期

    private String remarkName; /* 备注名 */

    private String weibo;
    private String invitCode;
    private Integer proxyOrgId;// 代理机构id

    private String eduArea;//区域
    private String eduSchool;//机构

    private Integer isStu; //是否学生,1是。0不是
    private String wxOpenId;
    private Integer teacherFlag;
    public Integer getIsStu() {
        return isStu;
    }

    public void setIsStu(Integer isStu) {
        this.isStu = isStu;
    }

    public String getEduArea() {
        return eduArea;
    }

    public void setEduArea(String eduArea) {
        this.eduArea = eduArea;
    }

    public String getEduSchool() {
        return eduSchool;
    }

    public void setEduSchool(String eduSchool) {
        this.eduSchool = eduSchool;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public Integer getTeacherFlag() {
        return teacherFlag;
    }

    public void setTeacherFlag(Integer teacherFlag) {
        this.teacherFlag = teacherFlag;
    }

    public UsersFrontVo() {

        super();
        // TODO Auto-generated constructor stub
    }

    public UsersFrontVo(String username, String password, String mobile, String email, Integer schoolId, Integer status, Integer companyId,
            Integer emailAuthSign, Date emailAuthSendtime, String emailAuthCode, String userCity, String uuid, String headPicMin, String headPicMid,
            String headPicMax, Date registTime, String interests, Integer vipFlag, Integer registType, String promoteSource, String promoteKeyword,
            String userSign, String nickName, double recharge, Integer stuId, String name, String sex, String identityTypeCode, String identityId, Integer age,
            Date birthday, String educationCode, String hkAddress, String qq, String homePhone, String officePhone, String weixinId, String emergencyContact,
            String emergencyPhone, String remark, Date createTime, Integer creator, Date updateTime, Integer updator, Integer deleteFlag, String domain,
            String flag, String schoolName, String startDate, String endDate, Integer timeLen, String timeMark, String url, String memberLevel,
            Integer memberId, Integer memberBuyLength, Date memberEndTime, Integer integralRemaining, Integer memberStatus, Integer companyVipIsOpen,
            String integralName, Integer companyIntegralIsOpen, String userVipConfigIco, String vipIsOverdue, String remarkName, String weibo, String invitCode,
            Integer proxyOrgId) {
        super();
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
        this.schoolId = schoolId;
        this.status = status;
        this.companyId = companyId;
        this.emailAuthSign = emailAuthSign;
        this.emailAuthSendtime = emailAuthSendtime;
        this.emailAuthCode = emailAuthCode;
        this.userCity = userCity;
        this.uuid = uuid;
        this.headPicMin = headPicMin;
        this.headPicMid = headPicMid;
        this.headPicMax = headPicMax;
        this.registTime = registTime;
        this.interests = interests;
        this.vipFlag = vipFlag;
        this.registType = registType;
        this.promoteSource = promoteSource;
        this.promoteKeyword = promoteKeyword;
        this.userSign = userSign;
        this.nickName = nickName;
        this.recharge = recharge;
        this.stuId = stuId;
        this.name = name;
        this.sex = sex;
        this.identityTypeCode = identityTypeCode;
        this.identityId = identityId;
        this.age = age;
        this.birthday = birthday;
        this.educationCode = educationCode;
        this.hkAddress = hkAddress;
        this.qq = qq;
        this.homePhone = homePhone;
        this.officePhone = officePhone;
        this.weixinId = weixinId;
        this.emergencyContact = emergencyContact;
        this.emergencyPhone = emergencyPhone;
        this.remark = remark;
        this.createTime = createTime;
        this.creator = creator;
        this.updateTime = updateTime;
        this.updator = updator;
        this.deleteFlag = deleteFlag;
        this.domain = domain;
        this.flag = flag;
        this.schoolName = schoolName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeLen = timeLen;
        this.timeMark = timeMark;
        this.url = url;
        this.memberLevel = memberLevel;
        this.memberId = memberId;
        this.memberBuyLength = memberBuyLength;
        this.memberEndTime = memberEndTime;
        this.integralRemaining = integralRemaining;
        this.memberStatus = memberStatus;
        this.companyVipIsOpen = companyVipIsOpen;
        this.integralName = integralName;
        this.companyIntegralIsOpen = companyIntegralIsOpen;
        this.userVipConfigIco = userVipConfigIco;
        this.vipIsOverdue = vipIsOverdue;
        this.remarkName = remarkName;
        this.weibo = weibo;
        this.invitCode = invitCode;
        this.proxyOrgId = proxyOrgId;
    }

    public Integer getProxyOrgId() {
        return proxyOrgId;
    }

    public void setProxyOrgId(Integer proxyOrgId) {
        this.proxyOrgId = proxyOrgId;
    }

    public String getWeibo() {
        return weibo;
    }

    public String getIntegralName() {
        return integralName;
    }

    public void setIntegralName(String integralName) {
        this.integralName = integralName;
    }

    public Integer getCompanyVipIsOpen() {
        return companyVipIsOpen;
    }

    public void setCompanyVipIsOpen(Integer companyVipIsOpen) {
        this.companyVipIsOpen = companyVipIsOpen;
    }

    public Integer getCompanyIntegralIsOpen() {
        return companyIntegralIsOpen;
    }

    public void setCompanyIntegralIsOpen(Integer companyIntegralIsOpen) {
        this.companyIntegralIsOpen = companyIntegralIsOpen;
    }

    public String getUserVipConfigIco() {
        return userVipConfigIco;
    }

    public void setUserVipConfigIco(String userVipConfigIco) {
        this.userVipConfigIco = userVipConfigIco;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public Integer getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Integer memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMemberBuyLength() {
        return memberBuyLength;
    }

    public void setMemberBuyLength(Integer memberBuyLength) {
        this.memberBuyLength = memberBuyLength;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getMemberEndTime() {
        return memberEndTime;
    }

    public void setMemberEndTime(Date memberEndTime) {
        this.memberEndTime = memberEndTime;
    }

    public Integer getIntegralRemaining() {
        return integralRemaining;
    }

    public void setIntegralRemaining(Integer integralRemaining) {
        this.integralRemaining = integralRemaining;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getEmailAuthSign() {
        return emailAuthSign;
    }

    public void setEmailAuthSign(Integer emailAuthSign) {
        this.emailAuthSign = emailAuthSign;
    }

    public Date getEmailAuthSendtime() {
        return emailAuthSendtime;
    }

    public void setEmailAuthSendtime(Date emailAuthSendtime) {
        this.emailAuthSendtime = emailAuthSendtime;
    }

    public String getEmailAuthCode() {
        return emailAuthCode;
    }

    public void setEmailAuthCode(String emailAuthCode) {
        this.emailAuthCode = emailAuthCode;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHeadPicMin() {
        return headPicMin;
    }

    public void setHeadPicMin(String headPicMin) {
        this.headPicMin = headPicMin;
    }

    public String getHeadPicMid() {
        return headPicMid;
    }

    public void setHeadPicMid(String headPicMid) {
        this.headPicMid = headPicMid;
    }

    public String getHeadPicMax() {
        return headPicMax;
    }

    public void setHeadPicMax(String headPicMax) {
        this.headPicMax = headPicMax;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public Integer getVipFlag() {
        return vipFlag;
    }

    public void setVipFlag(Integer vipFlag) {
        this.vipFlag = vipFlag;
    }

    public Integer getRegistType() {
        return registType;
    }

    public void setRegistType(Integer registType) {
        this.registType = registType;
    }

    public String getPromoteSource() {
        return promoteSource;
    }

    public void setPromoteSource(String promoteSource) {
        this.promoteSource = promoteSource;
    }

    public String getPromoteKeyword() {
        return promoteKeyword;
    }

    public void setPromoteKeyword(String promoteKeyword) {
        this.promoteKeyword = promoteKeyword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdentityTypeCode() {
        return identityTypeCode;
    }

    public void setIdentityTypeCode(String identityTypeCode) {
        this.identityTypeCode = identityTypeCode;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEducationCode() {
        return educationCode;
    }

    public void setEducationCode(String educationCode) {
        this.educationCode = educationCode;
    }

    public String getHkAddress() {
        return hkAddress;
    }

    public void setHkAddress(String hkAddress) {
        this.hkAddress = hkAddress;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdator() {
        return updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public Integer getTimeLen() {
        return timeLen;
    }

    public void setTimeLen(Integer timeLen) {
        this.timeLen = timeLen;
    }

    public String getTimeMark() {
        return timeMark;
    }

    public void setTimeMark(String timeMark) {
        this.timeMark = timeMark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public double getRecharge() {
        return recharge;
    }

    public void setRecharge(double recharge) {
        this.recharge = recharge;
    }

    public String getInvitCode() {
        return invitCode;
    }

    public void setInvitCode(String invitCode) {
        this.invitCode = invitCode;
    }

    public String getVipIsOverdue() {
        return vipIsOverdue;
    }

    public void setVipIsOverdue(String vipIsOverdue) {
        this.vipIsOverdue = vipIsOverdue;
    }

}
