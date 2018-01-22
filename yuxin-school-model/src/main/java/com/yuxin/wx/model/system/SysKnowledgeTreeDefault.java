package com.yuxin.wx.model.system;

import com.yuxin.wx.common.BaseEntity;

public class SysKnowledgeTreeDefault extends BaseEntity {
    private Integer eduYear;
    private String eduSeason;
    private Integer companyId;

    public Integer getEduYear() {
        return eduYear;
    }

    public void setEduYear(Integer eduYear) {
        this.eduYear = eduYear;
    }

    public String getEduSeason() {
        return eduSeason;
    }

    public void setEduSeason(String eduSeason) {
        this.eduSeason = eduSeason;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
