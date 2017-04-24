package com.yuxin.wx.vo.user;


public class ReturnValueVo{
	
	private String name;  
	private String bucket;
	private String etag;
	private String mimeType;   /* 文件类型*/
	private String persistentId;
	private String fsize;
    private String key;       /* 文件的名称*/
    private String hash;
    private String width;
    private String height;
    private String videohour;   /* 视频小时*/
    private String videoMinute; /* 视频分钟*/
    
    private String videoName;
    private String videoCcId;
    private String videoTime;
    private String videoSize;
    private String videoStatus;
    private String itemOneId;
    private String itemSeondId;
    private String videoTag;
    private String videoPic;
    private String schoolId;
    private Integer companyId;  /* 公司id*/
    private String createTime;  /* 创建时间*/
    private String creator;
    private String updator;
    private String updateTime;
	public ReturnValueVo() {

	}
	public ReturnValueVo(String name, String bucket, String etag,
			String mimeType, String persistentId, String fsize, String key,
			String hash, String width, String height, String videohour,
			String videoMinute, String videoName, String videoCcId,
			String videoTime, String videoSize, String videoStatus,
			String itemOneId, String itemSeondId, String videoTag,
			String videoPic, String schoolId, Integer companyId,
			String createTime, String creator, String updator, String updateTime) {
		this.name = name;
		this.bucket = bucket;
		this.etag = etag;
		this.mimeType = mimeType;
		this.persistentId = persistentId;
		this.fsize = fsize;
		this.key = key;
		this.hash = hash;
		this.width = width;
		this.height = height;
		this.videohour = videohour;
		this.videoMinute = videoMinute;
		this.videoName = videoName;
		this.videoCcId = videoCcId;
		this.videoTime = videoTime;
		this.videoSize = videoSize;
		this.videoStatus = videoStatus;
		this.itemOneId = itemOneId;
		this.itemSeondId = itemSeondId;
		this.videoTag = videoTag;
		this.videoPic = videoPic;
		this.schoolId = schoolId;
		this.companyId = companyId;
		this.createTime = createTime;
		this.creator = creator;
		this.updator = updator;
		this.updateTime = updateTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBucket() {
		return bucket;
	}
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	public String getEtag() {
		return etag;
	}
	public void setEtag(String etag) {
		this.etag = etag;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getPersistentId() {
		return persistentId;
	}
	public void setPersistentId(String persistentId) {
		this.persistentId = persistentId;
	}
	public String getFsize() {
		return fsize;
	}
	public void setFsize(String fsize) {
		this.fsize = fsize;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getVideohour() {
		return videohour;
	}
	public void setVideohour(String videohour) {
		this.videohour = videohour;
	}
	public String getVideoMinute() {
		return videoMinute;
	}
	public void setVideoMinute(String videoMinute) {
		this.videoMinute = videoMinute;
	}
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
	public String getVideoSize() {
		return videoSize;
	}
	public void setVideoSize(String videoSize) {
		this.videoSize = videoSize;
	}
	public String getVideoStatus() {
		return videoStatus;
	}
	public void setVideoStatus(String videoStatus) {
		this.videoStatus = videoStatus;
	}
	public String getItemOneId() {
		return itemOneId;
	}
	public void setItemOneId(String itemOneId) {
		this.itemOneId = itemOneId;
	}
	public String getItemSeondId() {
		return itemSeondId;
	}
	public void setItemSeondId(String itemSeondId) {
		this.itemSeondId = itemSeondId;
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
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
   
}
