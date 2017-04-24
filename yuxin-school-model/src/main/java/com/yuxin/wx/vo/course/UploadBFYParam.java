package com.yuxin.wx.vo.course;

public class UploadBFYParam {
	
	private Integer uptype;
	private Integer servicetype;
	private String filename;
	private String filekey;
	private long filesize;
	private Integer filetype;
	private long deadline;
	private String callbackurl;
	
	public UploadBFYParam() {
		super();
	}
	public UploadBFYParam(Integer uptype, Integer servicetype, String filename,
			String filekey, long filesize, Integer filetype, long deadline,
			String callbackurl) {
		super();
		this.uptype = uptype;
		this.servicetype = servicetype;
		this.filename = filename;
		this.filekey = filekey;
		this.filesize = filesize;
		this.filetype = filetype;
		this.deadline = deadline;
		this.callbackurl = callbackurl;
	}
	public Integer getUptype() {
		return uptype;
	}
	public void setUptype(Integer uptype) {
		this.uptype = uptype;
	}
	public Integer getServicetype() {
		return servicetype;
	}
	public void setServicetype(Integer servicetype) {
		this.servicetype = servicetype;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilekey() {
		return filekey;
	}
	public void setFilekey(String filekey) {
		this.filekey = filekey;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public Integer getFiletype() {
		return filetype;
	}
	public void setFiletype(Integer filetype) {
		this.filetype = filetype;
	}
	public long getDeadline() {
		return deadline;
	}
	public void setDeadline(long deadline) {
		this.deadline = deadline;
	}
	public String getCallbackurl() {
		return callbackurl;
	}
	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}
	
}
