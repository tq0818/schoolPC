package com.yuxin.wx.model.company;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:CompanyRechargePatch
 * 
 * @author chopin
 * @date 2017-4-10
 */
@SuppressWarnings("serial")
public class CompanyRechargePatch extends BaseEntity {

    private Integer totalNum; /* 总数量 */
    private Integer usedNum; /* 已使用数量 */
    private Integer remainNum; /* 剩余数量 */
    private Integer status; /* 状态 0-禁用 1-启用 */
    private Integer issueWay; /* 发放方式 0-线上发放 1-线下发放 */
    private Float cashAmount; /* 现金金额 */
    private Date timeLimitFrom; /* 使用期限从 */
    private Date timeLimitTo; /* 使用期限至 */
    private String promoCodePrefix; /* 充值卡前缀 */
    private Integer orgId; /* 关联代理机构 */
    private Integer companyId; /* 公司ID */
    private Date createDate;
    private Integer creator;

    private String startDate;
    private String endDate;

    // Constructor
    public CompanyRechargePatch() {
    }

    /**
     * full Constructor
     */
    public CompanyRechargePatch(Integer id, Integer totalNum, Integer usedNum, Integer remainNum, Integer status, Integer issueWay, Float cashAmount,
            Date timeLimitFrom, Date timeLimitTo, String promoCodePrefix, Integer orgId, Integer companyId, Date createDate, Integer creator) {
        setId(id);
        this.totalNum = totalNum;
        this.usedNum = usedNum;
        this.remainNum = remainNum;
        this.status = status;
        this.issueWay = issueWay;
        this.cashAmount = cashAmount;
        this.timeLimitFrom = timeLimitFrom;
        this.timeLimitTo = timeLimitTo;
        this.promoCodePrefix = promoCodePrefix;
        this.orgId = orgId;
        this.companyId = companyId;
        this.createDate = createDate;
        this.creator = creator;
    }

    // getter && setter
    // 在setter方法最后加上"return this;"并把返回参数改为CompanyRechargePatch可以实现连缀设置属性

    public Integer getTotalNum() {
        return totalNum;
    }

    public CompanyRechargePatch setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    public Integer getUsedNum() {
        return usedNum;
    }

    public CompanyRechargePatch setUsedNum(Integer usedNum) {
        this.usedNum = usedNum;
        return this;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public CompanyRechargePatch setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public CompanyRechargePatch setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getIssueWay() {
        return issueWay;
    }

    public CompanyRechargePatch setIssueWay(Integer issueWay) {
        this.issueWay = issueWay;
        return this;
    }

    public Float getCashAmount() {
        return cashAmount;
    }

    public CompanyRechargePatch setCashAmount(Float cashAmount) {
        this.cashAmount = cashAmount;
        return this;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getTimeLimitFrom() {
        return timeLimitFrom;
    }

    public CompanyRechargePatch setTimeLimitFrom(Date timeLimitFrom) {
        this.timeLimitFrom = timeLimitFrom;
        return this;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getTimeLimitTo() {
        return timeLimitTo;
    }

    public CompanyRechargePatch setTimeLimitTo(Date timeLimitTo) {
        this.timeLimitTo = timeLimitTo;
        return this;
    }

    public String getPromoCodePrefix() {
        return promoCodePrefix;
    }

    public CompanyRechargePatch setPromoCodePrefix(String promoCodePrefix) {
        this.promoCodePrefix = promoCodePrefix;
        return this;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public CompanyRechargePatch setOrgId(Integer orgId) {
        this.orgId = orgId;
        return this;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public CompanyRechargePatch setCompanyId(Integer companyId) {
        this.companyId = companyId;
        return this;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    public CompanyRechargePatch setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Integer getCreator() {
        return creator;
    }

    public CompanyRechargePatch setCreator(Integer creator) {
        this.creator = creator;
        return this;
    }

    @Override
    public String toString() {
        return "CompanyRechargePatch [" + "id=" + getId() + ", totalNum=" + totalNum + ", usedNum=" + usedNum + ", remainNum=" + remainNum + ", status="
                + status + ", issueWay=" + issueWay + ", cashAmount=" + cashAmount + ", timeLimitFrom=" + timeLimitFrom + ", timeLimitTo=" + timeLimitTo
                + ", promoCodePrefix=" + promoCodePrefix + ", orgId=" + orgId + ", companyId=" + companyId + ", createDate=" + createDate + ", creator="
                + creator + "]";
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
}
