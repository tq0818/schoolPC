package com.yuxin.wx.vo.tiku;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:TikuPaper
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@SuppressWarnings("serial")
public class TikuExamPaperVo extends BaseEntity {
	
	
	private String	paperName;		 /* 试卷名称 */ 
	private Double	totalScore;		 /* 试卷总分 */ 
	private Integer	totalTopicNum;		 /* 总共包含的题数 */ 
	private Integer	creator;		
	private Date	createTime;		
	
	private Integer  status;		/*是否被考试使用*/
	private String	name;		/*创建人姓名*/
	private String mobile;		/*创建人手机*/
	private String cateName;	/*题库名*/
	private String subName;		/*科目名*/
	// Constructor
	public TikuExamPaperVo() {
	}
	

	public TikuExamPaperVo(String paperName, Double totalScore,
			Integer totalTopicNum, Integer creator, Date createTime,
			Integer status) {
		super();
		this.paperName = paperName;
		this.totalScore = totalScore;
		this.totalTopicNum = totalTopicNum;
		this.creator = creator;
		this.createTime = createTime;
		this.status = status;
	}


	public String getPaperName() {
		return paperName;
	}


	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}


	public Double getTotalScore() {
		return totalScore;
	}


	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}


	public Integer getTotalTopicNum() {
		return totalTopicNum;
	}


	public void setTotalTopicNum(Integer totalTopicNum) {
		this.totalTopicNum = totalTopicNum;
	}


	public Integer getCreator() {
		return creator;
	}


	public void setCreator(Integer creator) {
		this.creator = creator;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	
}
