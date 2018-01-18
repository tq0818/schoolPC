package com.yuxin.wx.model.course;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CourseVideoLookAuth
 * s
 * @author wang.zx
 * @date 2016-3-4
 */
@SuppressWarnings("serial")
public class CourseVideoLookAuth extends BaseEntity {
	
	
	private String	testListenAuth;		 /* 试听权限（ALL_USERS 全部用户,LAND_USERS 登陆用户） */ 
	private Integer	overFlowTime;		 /* 试听时长（分钟） */ 
	private String	overFlowInfo;		 /* 试听到时提示 */ 
	private String	userSeeAuth;		 /* 试听权限（ALL_USERS 全部用户,LAND_USERS 登陆用户） */ 
	private String	setPointName;		 /* 随堂点名设置（LOOK_VIDEO_NOT_NAMED 不点名,LOOK_VIDEO_BY_TIME 按时间,LOOK_VIDEO_BY_NUM 按次数） */ 
	private Integer	namedTime;		 /* 间隔时间（分钟） */ 
	private Integer	namedNum;		 /* 次数 */ 
	private Integer	companyId;		 /* 公司id */ 
	private Integer	creator;		
	private Date	createTime;		
	private Integer	updator;		
	private Date	updateTime;	
	private Integer zhuCompanyId;//主要机构标识号

	public Integer getZhuCompanyId() {
		return zhuCompanyId;
	}

	public void setZhuCompanyId(Integer zhuCompanyId) {
		this.zhuCompanyId = zhuCompanyId;
	}

	// Constructor
	public CourseVideoLookAuth() {
	}
	
	/**
	 * full Constructor
	 */
	public CourseVideoLookAuth(Integer id, String testListenAuth, Integer overFlowTime, String overFlowInfo, String userSeeAuth, String setPointName, Integer namedTime, Integer namedNum, Integer companyId, Integer creator, Date createTime, Integer updator, Date updateTime) {
		setId(id);
		this.testListenAuth = testListenAuth;
		this.overFlowTime = overFlowTime;
		this.overFlowInfo = overFlowInfo;
		this.userSeeAuth = userSeeAuth;
		this.setPointName = setPointName;
		this.namedTime = namedTime;
		this.namedNum = namedNum;
		this.companyId = companyId;
		this.creator = creator;
		this.createTime = createTime;
		this.updator = updator;
		this.updateTime = updateTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CourseVideoLookAuth可以实现连缀设置属性
	
	public String getTestListenAuth() {
		return testListenAuth;
	}

	public CourseVideoLookAuth setTestListenAuth(String testListenAuth) {
		this.testListenAuth = testListenAuth;
		return this;
	}
	
	
	public Integer getOverFlowTime() {
		return overFlowTime;
	}

	public CourseVideoLookAuth setOverFlowTime(Integer overFlowTime) {
		this.overFlowTime = overFlowTime;
		return this;
	}
	
	
	public String getOverFlowInfo() {
		return overFlowInfo;
	}

	public CourseVideoLookAuth setOverFlowInfo(String overFlowInfo) {
		this.overFlowInfo = overFlowInfo;
		return this;
	}
	
	
	public String getUserSeeAuth() {
		return userSeeAuth;
	}

	public CourseVideoLookAuth setUserSeeAuth(String userSeeAuth) {
		this.userSeeAuth = userSeeAuth;
		return this;
	}
	
	
	public String getSetPointName() {
		return setPointName;
	}

	public CourseVideoLookAuth setSetPointName(String setPointName) {
		this.setPointName = setPointName;
		return this;
	}
	
	
	public Integer getNamedTime() {
		return namedTime;
	}

	public CourseVideoLookAuth setNamedTime(Integer namedTime) {
		this.namedTime = namedTime;
		return this;
	}
	
	
	public Integer getNamedNum() {
		return namedNum;
	}

	public CourseVideoLookAuth setNamedNum(Integer namedNum) {
		this.namedNum = namedNum;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public CourseVideoLookAuth setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public CourseVideoLookAuth setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public CourseVideoLookAuth setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public CourseVideoLookAuth setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public CourseVideoLookAuth setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "CourseVideoLookAuth [" + "id=" + getId() + ", testListenAuth=" + testListenAuth + ", overFlowTime=" + overFlowTime + ", overFlowInfo=" + overFlowInfo + ", userSeeAuth=" + userSeeAuth + ", setPointName=" + setPointName + ", namedTime=" + namedTime + ", namedNum=" + namedNum + ", companyId=" + companyId + ", creator=" + creator + ", createTime=" + createTime + ", updator=" + updator + ", updateTime=" + updateTime +  "]";
	}
}
