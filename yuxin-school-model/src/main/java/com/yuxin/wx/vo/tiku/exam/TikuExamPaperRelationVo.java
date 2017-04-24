package com.yuxin.wx.vo.tiku.exam;

import java.text.DecimalFormat;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:TikuExamPaperRelation
 * 
 * @author wang.zx
 * @date 2016-2-17
 */
@SuppressWarnings("serial")
public class TikuExamPaperRelationVo extends BaseEntity {

    private Integer tikuExamId; /* 题库考试表ID */
    private Integer tikuPaperId; /* 题库试卷表ID */
    private Integer status; /* 状态（1. 使用中 2. 已删除） */
    private Date createTime; /* 创建时间 */
    private Integer creator; /* 创建人 */
    private String paperName;/* 试卷名称 */
    private String paperNameSub;/* 截取后的试卷名称 */
    private Date updateTime; /* 修改时间 */
    private Integer updator; /* 修改人 */

    private Integer allPeople; /* 报名考试总人数 */
    private Integer passPeople; /* 通过考试考试人数 */
    private String examPassRate;/* 考试通过率 */
    private Integer allPaperPeople; /* 试卷总人数 */
    private Integer passPaperPeople; /* 通过试卷人数 */
    private String paperPassRate;/* 试卷通过率 */
    private Integer paperCount;/* 试卷考过的次数 */
    private Double paperAvg;/* 试卷平均分 */

    private String cateName; /* 题库名 */
    private String subName; /* 分类名 */

    // Constructor
    public TikuExamPaperRelationVo() {
    }

    /**
     * full Constructor
     */
    public TikuExamPaperRelationVo(Integer id, Integer tikuExamId, Integer tikuPaperId, Integer status, Date createTime, Integer creator) {
        setId(id);
        this.tikuExamId = tikuExamId;
        this.tikuPaperId = tikuPaperId;
        this.status = status;
        this.createTime = createTime;
        this.creator = creator;
    }

    // getter && setter
    // 在setter方法最后加上"return this;"并把返回参数改为TikuExamPaperRelation可以实现连缀设置属性

    public Integer getTikuExamId() {
        return tikuExamId;
    }

    public TikuExamPaperRelationVo setTikuExamId(Integer tikuExamId) {
        this.tikuExamId = tikuExamId;
        return this;
    }

    public Integer getTikuPaperId() {
        return tikuPaperId;
    }

    public TikuExamPaperRelationVo setTikuPaperId(Integer tikuPaperId) {
        this.tikuPaperId = tikuPaperId;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public TikuExamPaperRelationVo setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @JsonSerialize(using = ShortDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public TikuExamPaperRelationVo setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getCreator() {
        return creator;
    }

    public TikuExamPaperRelationVo setCreator(Integer creator) {
        this.creator = creator;
        return this;
    }

    @Override
    public String toString() {
        return "TikuExamPaperRelationVo [tikuExamId=" + tikuExamId + ", tikuPaperId=" + tikuPaperId + ", status=" + status + ", createTime=" + createTime
                + ", creator=" + creator + ", paperName=" + paperName + ", paperNameSub=" + paperNameSub + ", updateTime=" + updateTime + ", updator=" + updator
                + ", allPeople=" + allPeople + ", passPeople=" + passPeople + ", examPassRate=" + examPassRate + ", allPaperPeople=" + allPaperPeople
                + ", passPaperPeople=" + passPaperPeople + ", paperPassRate=" + paperPassRate + ", paperCount=" + paperCount + ", paperAvg=" + paperAvg
                + ", cateName=" + cateName + ", subName=" + subName + "]";
    }

    public Integer getAllPeople() {
        return allPeople;
    }

    public void setAllPeople(Integer allPeople) {
        this.allPeople = allPeople;
    }

    public Integer getPassPeople() {
        return passPeople;
    }

    public void setPassPeople(Integer passPeople) {
        this.passPeople = passPeople;
    }

    public String getExamPassRate() {
        DecimalFormat df = new DecimalFormat("#0.00");
        Double PassRate = null;
        if (passPeople != null && allPeople != null && allPeople > 0) {
            PassRate = (passPeople * 1.0 / allPeople) * 100;
        }
        if (PassRate == null || PassRate.isNaN()) {
            PassRate = 0.0;
        }
        String rate = df.format(PassRate);
        PassRate = Double.parseDouble(rate);
        return PassRate.toString();
    }

    public void setExamPassRate(String examPassRate) {
        this.examPassRate = examPassRate;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getPaperName() {
        return this.paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Integer getAllPaperPeople() {
        return allPaperPeople;
    }

    public void setAllPaperPeople(Integer allPaperPeople) {
        this.allPaperPeople = allPaperPeople;
    }

    public Integer getPassPaperPeople() {
        return passPaperPeople;
    }

    public void setPassPaperPeople(Integer passPaperPeople) {
        this.passPaperPeople = passPaperPeople;
    }

    public String getPaperPassRate() {
        DecimalFormat df = new DecimalFormat("#0.00");
        Double PassRate = null;
        if (passPeople != null && allPeople != null && allPeople > 0) {
            PassRate = (passPeople * 1.0 / allPeople) * 100;
        }
        if (PassRate == null || PassRate.isNaN()) {
            PassRate = 0.0;
        }
        String rate = df.format(PassRate);
        PassRate = Double.parseDouble(rate);
        return PassRate.toString();
    }

    public void setPaperPassRate(String paperPassRate) {
        this.paperPassRate = paperPassRate;
    }

    public Integer getPaperCount() {
        return paperCount;
    }

    public void setPaperCount(Integer paperCount) {
        this.paperCount = paperCount;
    }

    public Double getPaperAvg() {
        DecimalFormat df = new DecimalFormat("#0.00");
        if (paperAvg == null) {
            paperAvg = 0.0;
        }
        String rate = df.format(paperAvg);
        Double paperAvgs = Double.parseDouble(rate);
        return paperAvgs;
    }

    public void setPaperAvg(Double paperAvg) {
        this.paperAvg = paperAvg;
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

    public String getPaperNameSub() {
        if (paperName != null && paperName.length() > 10) {
            paperNameSub = paperName.substring(0, 10) + "......";
        } else {
            paperNameSub = paperName;
        }
        return paperNameSub;
    }

    public void setPaperNameSub(String paperNameSub) {
        this.paperNameSub = paperNameSub;
    }
}
