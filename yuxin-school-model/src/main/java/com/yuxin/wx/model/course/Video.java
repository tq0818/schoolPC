package com.yuxin.wx.model.course;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:Video
 * 
 * @author wang.zx
 * @date 2015-5-8
 */
@SuppressWarnings("serial")
public class Video extends BaseEntity {
	
	
	private String	videoName;			/* 视频名称 */ 
	private String	videoCcId;			/* 视频id */ 
	private String	videoTime;			/* 视频时长 */ 
	private Double	vodeoSize;			/* 视频大小 */ 
	private String	videoStatus;		/* 视频状态：正常；处理中；屏蔽； */ 
	private Integer	itemOneId;			/* 所属一级项目id */ 
	private Integer	itemSecondId;		/* 所属二级项目id */ 
	private String	videoTag;			/* 视频所属标签 */ 
	private String	videoPic;			/* 视频图片路径 */ 
	private Integer	creator;			/* 创建者id */ 
	private Date	createTime;			/* 创建时间 */ 
	private Integer	updator;			/* 修改者id */ 
	private Date	updateTime;			/* 更新时间 */ 
	private Integer companyId;			/*公司Id*/
	private Integer schoolId;			/*校区Id*/
	private String	webVideoId;			/*视频播放id*/
	private String	webVideoDomain;		/*视频来源*/
	
	private String storageType;     /*视频存储类型*/ 
	private String filePath;

	// Constructor
	public Video() {
	}
	
	/**
	 * full Constructor
	 */
	public Video(Integer id, String videoName, String videoCcId, String videoTime, Double vodeoSize, String videoStatus, Integer itemOneId, Integer itemSecondId, String videoTag, String videoPic, Integer creator, Date createTime, Integer updator, Date updateTime,Integer companyId,Integer schoolId) {
		setId(id);
		this.videoName = videoName;
		this.videoCcId = videoCcId;
		this.videoTime = videoTime;
		this.vodeoSize = vodeoSize;
		this.videoStatus = videoStatus;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.videoTag = videoTag;
		this.videoPic = videoPic;
		this.creator = creator;
		this.createTime = createTime;
		this.updator = updator;
		this.updateTime = updateTime;
		this.companyId = companyId;
		this.schoolId = schoolId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为Video可以实现连缀设置属性
	
	public String getVideoName() {
		return videoName;
	}

	public Video setVideoName(String videoName) {
		this.videoName = videoName;
		return this;
	}
	
	
	public String getVideoCcId() {
		return videoCcId;
	}

	public Video setVideoCcId(String videoCcId) {
		this.videoCcId = videoCcId;
		return this;
	}
	
	
	public String getVideoTime() {
		return videoTime;
	}

	public Video setVideoTime(String videoTime) {
		this.videoTime = videoTime;
		return this;
	}
	
	
	public Double getVodeoSize() {
		return vodeoSize;
	}

	public Video setVodeoSize(Double vodeoSize) {
		this.vodeoSize = vodeoSize;
		return this;
	}
	
	
	public String getVideoStatus() {
		return videoStatus;
	}

	public Video setVideoStatus(String videoStatus) {
		this.videoStatus = videoStatus;
		return this;
	}
	
	
	public Integer getItemOneId() {
		return itemOneId;
	}

	public Video setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
		return this;
	}
	
	
	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public Video setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
		return this;
	}
	
	
	public String getVideoTag() {
		return videoTag;
	}

	public Video setVideoTag(String videoTag) {
		this.videoTag = videoTag;
		return this;
	}
	
	
	public String getVideoPic() {
		return videoPic;
	}

	public Video setVideoPic(String videoPic) {
		this.videoPic = videoPic;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public Video setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public Video setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getUpdator() {
		return updator;
	}

	public Video setUpdator(Integer updator) {
		this.updator = updator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public Video setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "Video [" + "id=" + getId() + ", videoName=" + videoName + ", videoCcId=" + videoCcId + ", videoTime=" + videoTime + ", vodeoSize=" + vodeoSize + ", videoStatus=" + videoStatus + ", itemOneId=" + itemOneId + ", itemSecondId=" + itemSecondId + ", videoTag=" + videoTag + ", videoPic=" + videoPic + ", creator=" + creator + ", createTime=" + createTime + ", updator=" + updator + ", updateTime=" + updateTime +  ",companyId="+companyId+",schoolId="+schoolId+"]";
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	public String getWebVideoId() {
		return webVideoId;
	}

	public void setWebVideoId(String webVideoId) {
		this.webVideoId = webVideoId;
	}

	public String getWebVideoDomain() {
		return webVideoDomain;
	}

	public void setWebVideoDomain(String webVideoDomain) {
		this.webVideoDomain = webVideoDomain;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
