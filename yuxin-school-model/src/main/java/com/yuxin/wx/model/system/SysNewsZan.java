package com.yuxin.wx.model.system;

import com.yuxin.wx.common.BaseEntity;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/17.
 */
@SuppressWarnings("serial")
public class SysNewsZan extends BaseEntity {
    private Integer userId;
    private Integer newsId;
    private Integer schoolId;
    private Integer companyId;
    private Date createDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
