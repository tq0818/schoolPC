package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysStudentScore
 * 
 * @author chopin
 * @date 2016-11-16
 */
@SuppressWarnings("serial")
public class SysStudentScore extends BaseEntity {
	
	
	private String	name;		 /* 姓名 */ 
	private String	code;		 /* 考号 */ 
	private String	idCode;		 /* 身份证号 */ 
	private String	grade;		 /* 参赛年级组 */ 
	private String	score;		 /* 分数 */ 
	private String	awards;		 /* 奖项 */ 
	private String	link;		 /* 成绩分析 */ 
	private Integer	companyId;		 /* 公司ID */ 

	// Constructor
	public SysStudentScore() {
	}
	
	/**
	 * full Constructor
	 */
	public SysStudentScore(Integer id, String name, String code, String idCode, String grade, String score, String awards, String link, Integer companyId) {
		setId(id);
		this.name = name;
		this.code = code;
		this.idCode = idCode;
		this.grade = grade;
		this.score = score;
		this.awards = awards;
		this.link = link;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysStudentScore可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public SysStudentScore setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public String getCode() {
		return code;
	}

	public SysStudentScore setCode(String code) {
		this.code = code;
		return this;
	}
	
	
	public String getIdCode() {
		return idCode;
	}

	public SysStudentScore setIdCode(String idCode) {
		this.idCode = idCode;
		return this;
	}
	
	
	public String getGrade() {
		return grade;
	}

	public SysStudentScore setGrade(String grade) {
		this.grade = grade;
		return this;
	}
	
	
	public String getScore() {
		return score;
	}

	public SysStudentScore setScore(String score) {
		this.score = score;
		return this;
	}
	
	
	public String getAwards() {
		return awards;
	}

	public SysStudentScore setAwards(String awards) {
		this.awards = awards;
		return this;
	}
	
	
	public String getLink() {
		return link;
	}

	public SysStudentScore setLink(String link) {
		this.link = link;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysStudentScore setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysStudentScore [" + "id=" + getId() + ", name=" + name + ", code=" + code + ", idCode=" + idCode + ", grade=" + grade + ", score=" + score + ", awards=" + awards + ", link=" + link + ", companyId=" + companyId +  "]";
	}
}
