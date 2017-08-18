package com.yuxin.wx.vo.course;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:Video
 *
 * @author ycl
 * @date 2015-5-8
 */
@SuppressWarnings("serial")
public class VideoVo extends BaseEntity {

	private String videoName; 		/* 视频名称 */
	private String videoCcId; 		/* 视频id */
	private String videoTime; 		/* 视频时长 */
	private Double vodeoSize; 		/* 视频大小 */
	private String videoStatus; 	/* 视频状态：正常；处理中；屏蔽； */
	private Integer itemOneId; 		/* 所属一级项目id */
	private Integer itemSecondId; 	/* 所属二级项目id */
	private String videoTag; 		/* 视频所属标签 */
	private String videoPic; 		/* 视频图片路径 */
	private Integer creator; 		/* 创建者id */
	private String creatorName; 	/* 创建者名称 */
	private Date createTime; 		/* 创建时间 */
	private Integer updator; 		/* 修改者id */
	private Date updateTime; 		/* 更新时间 */
	private Date beginTime;		 	/* 选择上传时间开始日期 */
	private Date endTime; 			/* 选择上传时间结束日期 */
	private String sortBy; 			/* 升序还是降序 */
	private Integer companyId; 		/* 公司Id */
	private Integer schoolId; 		/* 校区Id */
	private String webVideoId; 		/* 视频播放id */
	private String webVideoDomain; 	/* 视频来源 */
	private String resourceType; 	// 资源名称
	private String schoolName; 		/* 所属校区 */
	private String itemName; 		/* 一级项目名 */
	private String secondItemName; 	/* 二级项目名称 */
	private String storageType;
	private String ccuserId;
	private String flag;
	private String searchType;
	private Integer convertType;	/* 资源处理情况*/
	private String excep;			/* 错误描述*/
	private String filePath;
	// 简称
	private String sortName;

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoCcId() {
		return videoCcId;
	}

	public void setVideoCcId(String videoCcId) {
		this.videoCcId = videoCcId;
	}

	public String getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}

	public Double getVodeoSize() {
		return vodeoSize;
	}

	public void setVodeoSize(Double vodeoSize) {
		this.vodeoSize = vodeoSize;
	}

	public String getVideoStatus() {
		return videoStatus;
	}

	public void setVideoStatus(String videoStatus) {
		this.videoStatus = videoStatus;
	}

	public Integer getItemOneId() {
		return itemOneId;
	}

	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}

	public Integer getItemSecondId() {
		return itemSecondId;
	}

	public void setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
	}

	public String getVideoTag() {
		return videoTag;
	}

	public void setVideoTag(String videoTag) {
		this.videoTag = videoTag;
	}

	public String getVideoPic() {
		return videoPic;
	}

	public void setVideoPic(String videoPic) {
		this.videoPic = videoPic;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUpdator() {
		return updator;
	}

	public void setUpdator(Integer updator) {
		this.updator = updator;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	@JsonSerialize(using = ShortDateSerializer.class)
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
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

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	public String getCcuserId() {
		return ccuserId;
	}

	public void setCcuserId(String ccuserId) {
		this.ccuserId = ccuserId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getSecondItemName() {
		return secondItemName;
	}

	public void setSecondItemName(String secondItemName) {
		this.secondItemName = secondItemName;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public Integer getConvertType() {
		return convertType;
	}

	public void setConvertType(Integer convertType) {
		this.convertType = convertType;
	}

	public String getExcep() {
		return excep;
	}

	public void setExcep(String excep) {
		this.excep = excep;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
