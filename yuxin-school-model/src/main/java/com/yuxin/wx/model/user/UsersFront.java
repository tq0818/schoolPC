package com.yuxin.wx.model.user;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:UsersFront
 * 
 * @author chopin
 * @date 2015-3-12
 */
@SuppressWarnings("serial")
public class UsersFront extends BaseEntity {
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
    private String userSign; /* 签名 */
    private String nickName; /* 昵称 */

    private String domain; /* 域名 */

    private String memberLevel;/* 会员等级 */
    private Integer memberId;/* 会员id */
    private Integer memberBuyLength;/* 会员购买的时长,累积型会员为-1 */
    private Date memberEndTime;/* 会员结束时间 */
    private Integer integralRemaining;/* 积分余额 */
    private Integer memberStatus;/* 会员状态 */

    private String qq;
    private String weibo;
    private String inviteCode;

    private double recharge;

    public UsersFront() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UsersFront(String username, String password, String mobile, String email, Integer schoolId, Integer status, Integer companyId, Integer emailAuthSign,
            Date emailAuthSendtime, String emailAuthCode, String userCity, String uuid, String headPicMin, String headPicMid, String headPicMax,
            Date registTime, String interests, Integer vipFlag, Integer registType, String promoteSource, String promoteKeyword, String userSign,
            String nickName, String domain, String memberLevel, Integer memberId, Integer memberBuyLength, Date memberEndTime, Integer integralRemaining,
            Integer memberStatus, String qq, String weibo, String inviteCode, double recharge) {
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
        this.domain = domain;
        this.memberLevel = memberLevel;
        this.memberId = memberId;
        this.memberBuyLength = memberBuyLength;
        this.memberEndTime = memberEndTime;
        this.integralRemaining = integralRemaining;
        this.memberStatus = memberStatus;
        this.qq = qq;
        this.weibo = weibo;
        this.inviteCode = inviteCode;
        this.recharge = recharge;
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

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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

    public Integer getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Integer memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public double getRecharge() {
        return recharge;
    }

    public void setRecharge(double recharge) {
        this.recharge = recharge;
    }

    @Override
    public String toString() {
        return "UsersFront [username=" + username + ", password=" + password + ", mobile=" + mobile + ", email=" + email + ", schoolId=" + schoolId
                + ", status=" + status + ", companyId=" + companyId + ", emailAuthSign=" + emailAuthSign + ", emailAuthSendtime=" + emailAuthSendtime
                + ", emailAuthCode=" + emailAuthCode + ", userCity=" + userCity + ", uuid=" + uuid + ", headPicMin=" + headPicMin + ", headPicMid=" + headPicMid
                + ", headPicMax=" + headPicMax + ", registTime=" + registTime + ", interests=" + interests + ", vipFlag=" + vipFlag + ", registType="
                + registType + ", promoteSource=" + promoteSource + ", promoteKeyword=" + promoteKeyword + ", userSign=" + userSign + ", nickName=" + nickName
                + ", domain=" + domain + ", memberLevel=" + memberLevel + ", memberId=" + memberId + ", memberBuyLength=" + memberBuyLength + ", memberEndTime="
                + memberEndTime + ", integralRemaining=" + integralRemaining + ", memberStatus=" + memberStatus + ", qq=" + qq + ", weibo=" + weibo
                + ", inviteCode=" + inviteCode + ", recharge=" + recharge + "]";
    }

}
