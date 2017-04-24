package com.yuxin.wx.vo.course;

public class DeleteBFYParam {

	private String filename;
	private String filekey;
	private long deadline;
	private Integer servicetype;
	private String callbackurl;
	
	public DeleteBFYParam() {
		super();
	}
	public DeleteBFYParam(String filename, String filekey, long deadline,
			Integer servicetype, String callbackurl) {
		super();
		this.filename = filename;
		this.filekey = filekey;
		this.deadline = deadline;
		this.servicetype = servicetype;
		this.callbackurl = callbackurl;
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
	public long getDeadline() {
		return deadline;
	}
	public void setDeadline(long deadline) {
		this.deadline = deadline;
	}
	public Integer getServicetype() {
		return servicetype;
	}
	public void setServicetype(Integer servicetype) {
		this.servicetype = servicetype;
	}
	public String getCallbackurl() {
		return callbackurl;
	}
	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}
	
}
