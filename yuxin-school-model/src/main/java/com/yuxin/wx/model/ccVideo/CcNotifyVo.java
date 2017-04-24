package com.yuxin.wx.model.ccVideo;

public class CcNotifyVo {
	
	private String videoid;
	private String status;
	private String duration;
	private String image;
	
	public String getVideoid() {
		return videoid;
	}
	public void setVideoid(String videoid) {
		this.videoid = videoid;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "CcNotify [" + "videoid=" + videoid + ", status=" + status + ", duration=" + duration +", image=" + image +"]";
	}
}
