package com.yuxin.wx.model.course;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CourseRemote
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class CourseRemotePayoff extends BaseEntity {
	
	
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
    private String payoffPercent;
    private String payoffStatus;

	// Constructor
	public CourseRemotePayoff() {
	}
	
	/**
	 * full Constructor
	 */
	public CourseRemotePayoff(Integer id, String name, Integer itemOneId, Integer itemSecondId, String status, Date createTime, Integer creator, Date updateTime, Integer updator, Integer delFlag) {
		setId(id);
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CourseRemote可以实现连缀设置属性
	
	
	@Override
	public String toString() {
		return "CourseRemotePayoff [" + "id=" + getId() + ", classTypeId=" + classTypeId + ", classTypeName=" + classTypeName + ", examTermId=" + examTermId + ", examTermName=" + examTermName + ", payoffDate=" + payoffDate + ", payoffFee=" + payoffFee + ", updateTime=" + updateTime + ", updator=" + updator+ "]";
	}

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

    public String getPayoffPercent() {
        return payoffPercent;
    }

    public void setPayoffPercent(String payoffPercent) {
        this.payoffPercent = payoffPercent;
    }

    public String getPayoffStatus() {
        return payoffStatus;
    }

    public void setPayoffStatus(String payoffStatus) {
        this.payoffStatus = payoffStatus;
    }

    
}
