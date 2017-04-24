package com.yuxin.wx.vo.fee;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

public class RemoteFeeVo extends BaseEntity{
    
    private Integer classTypeId;
    private String classTypeName;
    private Integer examTermId;
    private String examTermName;
    private Date payoffDate;
    private Double payoffFee;
    private Date createTime;
    private Integer creator;
    private Date updateTime;
    private Integer updator;
    private Double totalFee;
    private Double hasPayFee;
    private String payoffPercent;
    private Integer itemOneId;
    private String itemOneName;
    private Integer itemSecondId;
    private String itemSecondName;
    private String payoffStatus;
    private Integer schoolId;
    
    private Integer companyId;
    private Integer payId;
    private Integer detailId;
    private String userName;
    
    
    public Integer getClassTypeId() {
        return classTypeId;
    }
    public void setClassTypeId(Integer classTypeId) {
        this.classTypeId = classTypeId;
    }
    public String getClassTypeName() {
        return classTypeName;
    }
    public void setClassTypeName(String classTypeName) {
        this.classTypeName = classTypeName;
    }
    public Integer getExamTermId() {
        return examTermId;
    }
    public void setExamTermId(Integer examTermId) {
        this.examTermId = examTermId;
    }
    public String getExamTermName() {
        return examTermName;
    }
    public void setExamTermName(String examTermName) {
        this.examTermName = examTermName;
    }
    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getPayoffDate() {
        return payoffDate;
    }
    public void setPayoffDate(Date payoffDate) {
        this.payoffDate = payoffDate;
    }

    public Double getPayoffFee() {
		return payoffFee;
	}
	public void setPayoffFee(Double payoffFee) {
		this.payoffFee = payoffFee;
	}
	@JsonSerialize(using = ShortDateSerializer.class)
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
    @JsonSerialize(using = ShortDateSerializer.class)
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
    public Double getTotalFee() {
        return totalFee;
    }
    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }
    public Double getHasPayFee() {
        return hasPayFee;
    }
    public void setHasPayFee(Double hasPayFee) {
        this.hasPayFee = hasPayFee;
    }
    public String getPayoffPercent() {
        return payoffPercent;
    }
    public void setPayoffPercent(String payoffPercent) {
        this.payoffPercent = payoffPercent;
    }
    public Integer getItemOneId() {
        return itemOneId;
    }
    public void setItemOneId(Integer itemOneId) {
        this.itemOneId = itemOneId;
    }
    public String getItemOneName() {
        return itemOneName;
    }
    public void setItemOneName(String itemOneName) {
        this.itemOneName = itemOneName;
    }
    public Integer getItemSecondId() {
        return itemSecondId;
    }
    public void setItemSecondId(Integer itemSecondId) {
        this.itemSecondId = itemSecondId;
    }
    public String getItemSecondName() {
        return itemSecondName;
    }
    public void setItemSecondName(String itemSecondName) {
        this.itemSecondName = itemSecondName;
    }
    public String getPayoffStatus() {
        return payoffStatus;
    }
    public void setPayoffStatus(String payoffStatus) {
        this.payoffStatus = payoffStatus;
    }
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getPayId() {
		return payId;
	}
	public void setPayId(Integer payId) {
		this.payId = payId;
	}
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}
