package com.yuxin.wx.model.teacher;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * POJO:TeacherInviteRewardRecord
 * 
 * @author chopin
 * @date 2017-3-13
 */
@SuppressWarnings("serial")
public class UserCollectTeacher extends BaseEntity {

    private Integer userId;
    private Date conllectDate;
    private Integer teacherId; /* 教师Id */
    private Integer schoolId; /* 学校id */
    private Integer companyId; /* 系统id */

    // Constructor
    public UserCollectTeacher() {
    }

    // getter && setter
    // 在setter方法最后加上"return this;"并把返回参数改为TeacherInviteRewardRecord可以实现连缀设置属性

    public UserCollectTeacher(Integer userId, Integer teacherId, Date conllectDate, Integer schoolId, Integer companyId) {
        super();
        this.userId = userId;
        this.teacherId = teacherId;
        this.conllectDate = conllectDate;
        this.schoolId = schoolId;
        this.companyId = companyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getConllectDate() {
        return conllectDate;
    }

    public void setConllectDate(Date conllectDate) {
        this.conllectDate = conllectDate;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    @Override
    public String toString() {
        return "TeacherInviteRewardRecord [userId=" + userId + ", teacherId=" + teacherId + ", conllectDate=" + conllectDate + ", schoolId=" + schoolId + ", companyId=" + companyId + "]";
    }
}