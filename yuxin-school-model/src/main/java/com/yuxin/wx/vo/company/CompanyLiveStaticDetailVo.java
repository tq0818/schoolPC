package com.yuxin.wx.vo.company;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CompanyLiveStaticDetail
 * 
 * @author chopin
 * @date 2015-4-23
 */
public class CompanyLiveStaticDetailVo extends BaseEntity {

	
	private static final long serialVersionUID = 2363457869380778072L;
	private Integer stuId;
	private Integer classLessionId;/*课次编号*/
	private String mobile;
	private String userName;
	private String nickName;
	private String name;
	private String email;
	
	private String lessonName;
	private  Integer companyId;
	private  Integer  classTypeId;/*课程编号*/
	private  Integer countClass;/*合计上课次数*/
	private Integer  countClassPeoples;/*总计上课人数*/
	
	private Date liveDate;
	private Date inTime;			//进入直播的时间
	private Date outTime;			//离开直播的时间
	private Integer watchType;		//看直播、看回看
	
	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Integer getWatchType() {
		return watchType;
	}

	public void setWatchType(Integer watchType) {
		this.watchType = watchType;
	}

	public Date getLiveDate() {
		return liveDate;
	}

	public void setLiveDate(Date liveDate) {
		this.liveDate = liveDate;
	}

	public Integer getCountClassPeoples() {
		return countClassPeoples;
	}

	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public void setCountClassPeoples(Integer countClassPeoples) {
		this.countClassPeoples = countClassPeoples;
	}

	public Integer getCountClass() {
		return countClass;
	}

	public void setCountClass(Integer countClass) {
		this.countClass = countClass;
	}

	public Integer getClassTypeId() {
		return classTypeId;
	}

	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getClassLessionId() {
		return classLessionId;
	}

	public void setClassLessionId(Integer classLessionId) {
		this.classLessionId = classLessionId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	
}
