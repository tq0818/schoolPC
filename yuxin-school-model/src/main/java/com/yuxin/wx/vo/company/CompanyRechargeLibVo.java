package com.yuxin.wx.vo.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.LongDateSerializer;
import com.yuxin.wx.util.ShortDateSerializer;

public class CompanyRechargeLibVo extends BaseEntity {

    private String code; /* 编码 */
    private Date timeLimitFrom; /* 使用期限从 */
    private Date timeLimitTo; /* 使用期限至 */
    private Integer status; /* 状态 0-未使用 1-已使用 */
    private Integer patchId; /* 批次ID */
    private Integer userId; /* 使用人 user_front_id */
    private Date useTime; /* 使用时间 */
    private Integer companyId; /* 公司ID */

    private String username;
    private String mobile;

    private String usernameAndMobile;
    private double cashAmount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getTimeLimitFrom() {
        return timeLimitFrom;
    }

    public void setTimeLimitFrom(Date timeLimitFrom) {
        this.timeLimitFrom = timeLimitFrom;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getTimeLimitTo() {
        return timeLimitTo;
    }

    public void setTimeLimitTo(Date timeLimitTo) {
        this.timeLimitTo = timeLimitTo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPatchId() {
        return patchId;
    }

    public void setPatchId(Integer patchId) {
        this.patchId = patchId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonSerialize(using = LongDateSerializer.class)
    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsernameAndMobile() {
        return usernameAndMobile;
    }

    public void setUsernameAndMobile(String usernameAndMobile) {
        this.usernameAndMobile = usernameAndMobile;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }
}
