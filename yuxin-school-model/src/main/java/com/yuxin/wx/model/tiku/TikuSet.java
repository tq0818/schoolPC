package com.yuxin.wx.model.tiku;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuSet
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuSet extends BaseEntity {
	
	
	private Integer	freeStuNo;		 /* 免费学员-只有报名了课程的学员才可以使用题库 */ 
	private Integer	freeStuTry;		 /* 免费学员-免费用户可以免费体验题库 */ 
	private Integer	freeStuTryDay;		 /* 免费学员-可以体验的天数 */ 
	private Integer	chargeStuAll;		 /* 收费学员设置-可以做任何科目的下的题库 */ 
	private Integer	chargeStuItem;		 /* 收费学员设置-只可以做自己所购买的课程所属的学科下的题库 */ 
	private Integer	chargeStuItemSecond;		 /* 收费学员设置-只可以做自己所购买的课程所属的学科小类下的题库 */ 
	private Integer	topicAuditYes;		 /* 试题审核-每道题目必须经过审核 */ 
	private Integer	topicAuditNo;		 /* 试题审核-题目创建完毕，即可对外开放，不用经过审核 */ 
	private Integer	paperAuditYes;		 /* 试卷审核-试卷必须经过审核，并且审核通过后才能对外开放 */ 
	private Integer	paperAuditNo;		 /* 试卷审核-试卷创建完毕，即可对外开放，不用经过审核 */ 
//	private Integer	tikuCategoryId;		 /* 所属题库分类id */ 
	private Integer	companyId;		
	private Integer zhuCompanyId;//主要机构标识号

	public Integer getZhuCompanyId() {
		return zhuCompanyId;
	}

	public void setZhuCompanyId(Integer zhuCompanyId) {
		this.zhuCompanyId = zhuCompanyId;
	}

	// Constructor
	public TikuSet() {
	}
	
	/**
	 * full Constructor
	 */
	public TikuSet(Integer id, Integer freeStuNo, Integer freeStuTry, Integer freeStuTryDay, Integer chargeStuAll, Integer chargeStuItem, Integer chargeStuItemSecond, Integer topicAuditYes, Integer topicAuditNo, Integer paperAuditYes, Integer paperAuditNo, Integer tikuCategoryId, Integer companyId) {
		setId(id);
		this.freeStuNo = freeStuNo;
		this.freeStuTry = freeStuTry;
		this.freeStuTryDay = freeStuTryDay;
		this.chargeStuAll = chargeStuAll;
		this.chargeStuItem = chargeStuItem;
		this.chargeStuItemSecond = chargeStuItemSecond;
		this.topicAuditYes = topicAuditYes;
		this.topicAuditNo = topicAuditNo;
		this.paperAuditYes = paperAuditYes;
		this.paperAuditNo = paperAuditNo;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为TikuSet可以实现连缀设置属性
	
	public Integer getFreeStuNo() {
		return freeStuNo;
	}

	public TikuSet setFreeStuNo(Integer freeStuNo) {
		this.freeStuNo = freeStuNo;
		return this;
	}
	
	
	public Integer getFreeStuTry() {
		return freeStuTry;
	}

	public TikuSet setFreeStuTry(Integer freeStuTry) {
		this.freeStuTry = freeStuTry;
		return this;
	}
	
	
	public Integer getFreeStuTryDay() {
		return freeStuTryDay;
	}

	public TikuSet setFreeStuTryDay(Integer freeStuTryDay) {
		this.freeStuTryDay = freeStuTryDay;
		return this;
	}
	
	
	public Integer getChargeStuAll() {
		return chargeStuAll;
	}

	public TikuSet setChargeStuAll(Integer chargeStuAll) {
		this.chargeStuAll = chargeStuAll;
		return this;
	}
	
	
	public Integer getChargeStuItem() {
		return chargeStuItem;
	}

	public TikuSet setChargeStuItem(Integer chargeStuItem) {
		this.chargeStuItem = chargeStuItem;
		return this;
	}
	
	
	public Integer getChargeStuItemSecond() {
		return chargeStuItemSecond;
	}

	public TikuSet setChargeStuItemSecond(Integer chargeStuItemSecond) {
		this.chargeStuItemSecond = chargeStuItemSecond;
		return this;
	}
	
	
	public Integer getTopicAuditYes() {
		return topicAuditYes;
	}

	public TikuSet setTopicAuditYes(Integer topicAuditYes) {
		this.topicAuditYes = topicAuditYes;
		return this;
	}
	
	
	public Integer getTopicAuditNo() {
		return topicAuditNo;
	}

	public TikuSet setTopicAuditNo(Integer topicAuditNo) {
		this.topicAuditNo = topicAuditNo;
		return this;
	}
	
	
	public Integer getPaperAuditYes() {
		return paperAuditYes;
	}

	public TikuSet setPaperAuditYes(Integer paperAuditYes) {
		this.paperAuditYes = paperAuditYes;
		return this;
	}
	
	
	public Integer getPaperAuditNo() {
		return paperAuditNo;
	}

	public TikuSet setPaperAuditNo(Integer paperAuditNo) {
		this.paperAuditNo = paperAuditNo;
		return this;
	}
	
	
//	public Integer getTikuCategoryId() {
//		return tikuCategoryId;
//	}
//
//	public TikuSet setTikuCategoryId(Integer tikuCategoryId) {
//		this.tikuCategoryId = tikuCategoryId;
//		return this;
//	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public TikuSet setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "TikuSet [" + "id=" + getId() + ", freeStuNo=" + freeStuNo + ", freeStuTry=" + freeStuTry + ", freeStuTryDay=" + freeStuTryDay + ", chargeStuAll=" + chargeStuAll + ", chargeStuItem=" + chargeStuItem + ", chargeStuItemSecond=" + chargeStuItemSecond + ", topicAuditYes=" + topicAuditYes + ", topicAuditNo=" + topicAuditNo + ", paperAuditYes=" + paperAuditYes + ", paperAuditNo=" + paperAuditNo + ", companyId=" + companyId +  "]";
	}
}
