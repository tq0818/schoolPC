package com.yuxin.wx.model.ccVideo;

public class CcNotify {
	
	private String videoid;
	private String userid;
	private String servicetype;
	private String metaurl;
	private String chunkurl;
	
	public String getVideoid() {
		return videoid;
	}
	public void setVideoid(String videoid) {
		this.videoid = videoid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getServicetype() {
		return servicetype;
	}
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	public String getMetaurl() {
		return metaurl;
	}
	public void setMetaurl(String metaurl) {
		this.metaurl = metaurl;
	}
	public String getChunkurl() {
		return chunkurl;
	}
	public void setChunkurl(String chunkurl) {
		this.chunkurl = chunkurl;
	}
	
	@Override
	public String toString() {
		return "CcNotify [" + "videoid=" + videoid + ", userid=" + userid + ", servicetype=" + servicetype +", metaurl=" + metaurl +", chunkurl=" + chunkurl +"]";
	}
}
