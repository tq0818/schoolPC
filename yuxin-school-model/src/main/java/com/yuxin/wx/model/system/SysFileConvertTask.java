package com.yuxin.wx.model.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysFileConvertTask
 * 
 * @author wang.zx
 * @date 2016-10-31
 */
@SuppressWarnings("serial")
public class SysFileConvertTask extends BaseEntity implements Task{
	
	
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	creator;		 /* 创建人-用户ID */ 
	private Integer	version;		 /* 版本号 */ 
	private Integer	status;		 /* 状态 0-上传成功待处理 1-正在处理 2-处理完成 3-处理异常 4-异常后重试仍异常 */ 
	private String	serverId;		 /* 处理任务的服务器 */ 
	private Date	excuteTime;		 /* 处理时间 */ 
	private Date	excepExcuteTime;		 /* 异常处理时间 */ 
	private String	excepReason;		 /* 异常原因 */ 
	private Date	completeTime;		 /* 完成时间 */ 
	private Integer	fileId;		 /* 对应文件ID */ 
	private String	filePath;		 /* 对应文件路径 */ 
	
	private Integer companyId;	/* 公司id*/
	// Constructor
	public SysFileConvertTask() {
	}
	
	/**
	 * full Constructor
	 */
	public SysFileConvertTask(Integer id, Date createTime, Integer creator, Integer version, Integer status, String serverId, Date excuteTime, Date excepExcuteTime, String excepReason, Date completeTime, Integer fileId, String filePath) {
		setId(id);
		this.createTime = createTime;
		this.creator = creator;
		this.version = version;
		this.status = status;
		this.serverId = serverId;
		this.excuteTime = excuteTime;
		this.excepExcuteTime = excepExcuteTime;
		this.excepReason = excepReason;
		this.completeTime = completeTime;
		this.fileId = fileId;
		this.filePath = filePath;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysFileConvertTask可以实现连缀设置属性
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SysFileConvertTask setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public SysFileConvertTask setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	
	public Integer getVersion() {
		return version;
	}

	public SysFileConvertTask setVersion(Integer version) {
		this.version = version;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public SysFileConvertTask setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public String getServerId() {
		return serverId;
	}

	public SysFileConvertTask setServerId(String serverId) {
		this.serverId = serverId;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getExcuteTime() {
		return excuteTime;
	}

	public SysFileConvertTask setExcuteTime(Date excuteTime) {
		this.excuteTime = excuteTime;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getExcepExcuteTime() {
		return excepExcuteTime;
	}

	public SysFileConvertTask setExcepExcuteTime(Date excepExcuteTime) {
		this.excepExcuteTime = excepExcuteTime;
		return this;
	}
	
	
	public String getExcepReason() {
		return excepReason;
	}

	public SysFileConvertTask setExcepReason(String excepReason) {
		this.excepReason = excepReason;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCompleteTime() {
		return completeTime;
	}

	public SysFileConvertTask setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
		return this;
	}
	
	
	public Integer getFileId() {
		return fileId;
	}

	public SysFileConvertTask setFileId(Integer fileId) {
		this.fileId = fileId;
		return this;
	}
	
	
	public String getFilePath() {
		return filePath;
	}

	public SysFileConvertTask setFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysFileConvertTask [" + "id=" + getId() + ", createTime=" + createTime + ", creator=" + creator + ", version=" + version + ", status=" + status + ", serverId=" + serverId + ", excuteTime=" + excuteTime + ", excepExcuteTime=" + excepExcuteTime + ", excepReason=" + excepReason + ", completeTime=" + completeTime + ", fileId=" + fileId + ", filePath=" + filePath +  "]";
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
}
