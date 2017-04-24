package com.yuxin.wx.model.system;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.util.ShortDateSerializer;

/**
 * POJO:SysTaskLog
 * 
 * @author wang.zx
 * @date 2015-6-17
 */
@SuppressWarnings("serial")
public class SysTaskLog extends BaseEntity {
	
	
	private String	taskName;		 	/* 任务名称 */ 
	private Date	executeDate;		
	private Date	startTime;		 	/* 执行开始时间 */ 
	private Date	endTime;		 	/* 执行结束时间 */ 
	private String	result;			 	/* 执行结果 */ 
	private Integer	operator;		 	/* 操作人，默认为系统0 */ 
	private Date	operateTime;		/* 操作时间 */ 
	private String	errorLog;			/* 详细错误信息*/

	// Constructor
	public SysTaskLog() {
	}
	
	/**
	 * full Constructor
	 */
	public SysTaskLog(Integer id, String taskName, Date executeDate, Date startTime, Date endTime, String result, Integer operator, Date operateTime) {
		setId(id);
		this.taskName = taskName;
		this.executeDate = executeDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.result = result;
		this.operator = operator;
		this.operateTime = operateTime;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysTaskLog可以实现连缀设置属性
	
	public String getTaskName() {
		return taskName;
	}

	public SysTaskLog setTaskName(String taskName) {
		this.taskName = taskName;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getExecuteDate() {
		return executeDate;
	}

	public SysTaskLog setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getStartTime() {
		return startTime;
	}

	public SysTaskLog setStartTime(Date startTime) {
		this.startTime = startTime;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getEndTime() {
		return endTime;
	}

	public SysTaskLog setEndTime(Date endTime) {
		this.endTime = endTime;
		return this;
	}
	
	
	public String getResult() {
		return result;
	}

	public SysTaskLog setResult(String result) {
		this.result = result;
		return this;
	}
	
	
	public Integer getOperator() {
		return operator;
	}

	public SysTaskLog setOperator(Integer operator) {
		this.operator = operator;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getOperateTime() {
		return operateTime;
	}

	public SysTaskLog setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysTaskLog [" + "id=" + getId() + ", taskName=" + taskName + ", executeDate=" + executeDate + ", startTime=" + startTime + ", endTime=" + endTime + ", result=" + result + ", operator=" + operator + ", operateTime=" + operateTime +  "]";
	}

	public String getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}
}
