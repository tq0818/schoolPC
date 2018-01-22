package com.yuxin.wx.model.system;

import com.yuxin.wx.common.BaseEntity;

public class sysKnowledgeTreeDefault extends BaseEntity {
    private Integer eduYear;
    private Integer eduSeason;

    public Integer getEduYear() {
        return eduYear;
    }

    public void setEduYear(Integer eduYear) {
        this.eduYear = eduYear;
    }

    public Integer getEduSeason() {
        return eduSeason;
    }

    public void setEduSeason(Integer eduSeason) {
        this.eduSeason = eduSeason;
    }
}
