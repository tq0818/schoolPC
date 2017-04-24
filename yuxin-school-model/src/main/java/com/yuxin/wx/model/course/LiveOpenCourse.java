package com.yuxin.wx.model.course;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:LiveOpenCourse
 * 
 * @author wang.zx
 * @date 2015-9-25
 */
@SuppressWarnings("serial")
public class LiveOpenCourse extends BaseEntity {
	
	
	private Integer	itemOneId;		 /* 学科 */ 
	private Integer	itemSecondId;		 /* 学科小类 */ 
	private String	openCourseName;		 /* 课程名称 */ 
	private Integer	schoolId;		 /* 所属分校 */ 
	private Integer	companyId;		 /* 所属公司 */ 
	private Integer	teacherId;		 /* 老师ID */ 
	private Integer assistantId;	 /*助教id*/
	private Date	startOpenData;		 /* 公开课开始日期 */ 
	private String	startTime;		 /* 开始时间 */ 
	private String	endTime;		 /* 结束时间 */ 
	private Integer	publishStatus;		 /* 发布状态 */ 
	private Date	publishTime;		 /* 发布时间 */ 
	private String	cover;		 /* 公开课封面图片路径 */ 
	private String	detailDesc;		 /* 公开课详情描述 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	creator;		 /* 创建人 */ 
	private Date	updateTime;		 /* 修改时间 */ 
	private Integer	updator;		 /* 修改人 */ 
	private Integer	delFlag;		 /* 是否删除（0：未删除 1：已删除） */ 
	private String liveRoom;		/* 直播公开课唯一标识 UUID */
	private String liveroomIdGh;	/*直播教室ID*/
	private String studentUrlGh;	/*光慧学生观看地址*/
	private String assistantUrlGh;	/*光慧助教登陆地址*/
	private String teacherUrlGh;	/*光慧老师讲课地址*/
	private String replayUrlGh;	/*光慧回看地址*/
	private Integer scanCount; /*观看数量*/
	private Integer supportMobile;/*是否支持手机端*/
	private String liveServiceProvider; /* 类型*/
	private Date endOpenData;/*结束日期*/
	private Integer barrage;	/* 弹幕*/
	private Integer modetype;	/*模式*/
	
	private String teacherName;		 /*教师姓名*/
	
	private Integer playStatus;		/*课程直播状态 0-已结束  1-正在播放 -1-暂未开始*/

	// Constructor
	public LiveOpenCourse() {
	}
	
	/**
	 * full Constructor
	 */
	public LiveOpenCourse(Integer id, Integer itemOneId, Integer itemSecondId, String openCourseName, Integer schoolId, Integer companyId, Integer teacherId, Date startOpenData, String startTime, String endTime, Integer publishStatus, Date publishTime, String cover, String detailDesc, Date createTime, Integer creator, String liveRoom, Date updateTime, Integer updator, Integer delFlag) {
		setId(id);
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.openCourseName = openCourseName;
		this.schoolId = schoolId;
		this.companyId = companyId;
		this.teacherId = teacherId;
		this.startOpenData = startOpenData;
		this.startTime = startTime;
		this.endTime = endTime;
		this.publishStatus = publishStatus;
		this.publishTime = publishTime;
		this.cover = cover;
		this.detailDesc = detailDesc;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
		this.delFlag = delFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为LiveOpenCourse可以实现连缀设置属性
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public LiveOpenCourse setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public LiveOpenCourse setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public String getOpenCourseName() {
		return openCourseName;
	}

	public LiveOpenCourse setOpenCourseName(String openCourseName) {
		this.openCourseName = openCourseName;
		return this;
	}
	
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public LiveOpenCourse setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public LiveOpenCourse setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getTeacherId() {
		return teacherId;
	}

	public LiveOpenCourse setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getStartOpenData() {
		return startOpenData;
	}

	public LiveOpenCourse setStartOpenData(Date startOpenData) {
		this.startOpenData = startOpenData;
		return this;
	}
	
	
	public String getStartTime() {
		return startTime;
	}

	public LiveOpenCourse setStartTime(String startTime) {
		this.startTime = startTime;
		return this;
	}
	
	
	public String getEndTime() {
		return endTime;
	}

	public LiveOpenCourse setEndTime(String endTime) {
		this.endTime = endTime;
		return this;
	}
	
	
	public Integer getPublishStatus() {
		return publishStatus;
	}

	public LiveOpenCourse setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getPublishTime() {
		return publishTime;
	}

	public LiveOpenCourse setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
		return this;
	}
	
	
	public String getCover() {
		return cover;
	}

	public LiveOpenCourse setCover(String cover) {
		this.cover = cover;
		return this;
	}
	
	
	public String getDetailDesc() {
		return detailDesc;
	}
	
	public String getDetailDescForM() {
		String descformat = "";
		if(detailDesc!=null){
			if(detailDesc.length()>36){
				descformat = detailDesc.substring(0, 36)+"...";
			}
		}
		return descformat;
		
	}

	public LiveOpenCourse setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public LiveOpenCourse setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public LiveOpenCourse setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public LiveOpenCourse setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public LiveOpenCourse setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public LiveOpenCourse setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	@Override
	public String toString() {
		return "LiveOpenCourse [" + "id=" + getId() + ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId + ", openCourseName=" + openCourseName + ", schoolId=" + schoolId + ", companyId=" + companyId + ", teacherId=" + teacherId + ", startOpenData=" + startOpenData + ", startTime=" + startTime + ", endTime=" + endTime + ", publishStatus=" + publishStatus + ", publishTime=" + publishTime + ", cover=" + cover + ", detailDesc=" + detailDesc + ", createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator=" + updator + ", delFlag=" + delFlag +  "]";
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getPlayStatus() {
		return playStatus;
	}

	public void setPlayStatus(Integer playStatus) {
		this.playStatus = playStatus;
	}

	public String getLiveroomIdGh() {
		return liveroomIdGh;
	}

	public void setLiveroomIdGh(String liveroomIdGh) {
		this.liveroomIdGh = liveroomIdGh;
	}

	public String getStudentUrlGh() {
		return studentUrlGh;
	}

	public void setStudentUrlGh(String studentUrlGh) {
		this.studentUrlGh = studentUrlGh;
	}

	public String getAssistantUrlGh() {
		return assistantUrlGh;
	}

	public void setAssistantUrlGh(String assistantUrlGh) {
		this.assistantUrlGh = assistantUrlGh;
	}

	public String getTeacherUrlGh() {
		return teacherUrlGh;
	}

	public void setTeacherUrlGh(String teacherUrlGh) {
		this.teacherUrlGh = teacherUrlGh;
	}

	public String getReplayUrlGh() {
		return replayUrlGh;
	}

	public void setReplayUrlGh(String replayUrlGh) {
		this.replayUrlGh = replayUrlGh;
	}

	public Integer getScanCount() {
		return scanCount;
	}

	public void setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
	}

	public Integer getSupportMobile() {
		return supportMobile;
	}

	public void setSupportMobile(Integer supportMobile) {
		this.supportMobile = supportMobile;
	}

	public String getLiveRoom() {
		return liveRoom;
	}

	public void setLiveRoom(String liveRoom) {
		this.liveRoom = liveRoom;
	}

	public Integer getAssistantId() {
		return assistantId;
	}

	public void setAssistantId(Integer assistantId) {
		this.assistantId = assistantId;
	}

	public String getLiveServiceProvider() {
		return liveServiceProvider;
	}

	public void setLiveServiceProvider(String liveServiceProvider) {
		this.liveServiceProvider = liveServiceProvider;
	}

	public Date getEndOpenData() {
		return endOpenData;
	}

	public void setEndOpenData(Date endOpenData) {
		this.endOpenData = endOpenData;
	}

	public Integer getBarrage() {
		return barrage;
	}

	public void setBarrage(Integer barrage) {
		this.barrage = barrage;
	}

	public Integer getModetype() {
		return modetype;
	}

	public void setModetype(Integer modetype) {
		this.modetype = modetype;
	}

	
}
