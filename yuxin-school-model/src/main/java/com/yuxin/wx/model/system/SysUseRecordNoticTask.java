package com.yuxin.wx.model.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysUseRecordNoticTask
 * 
 * @author wang.zx
 * @date 2016-12-1
 */
@SuppressWarnings("serial")
public class SysUseRecordNoticTask extends BaseEntity {
	
	
	private Integer	bussinessType;		 /* 业务类型：0-并发 1-空间 2-流量 3-短信4-邮件 5-网校服务期  6-学员账号7-在线 */ 
	private Date	recordDateTime;		 /* 记录时间  yyyy-MM-dd HH:mm:ss */ 
	private Integer	recordStatus;		 /* 状态： 0-已记录  1-已处理 2-处理异常 3-再次处理异常 */ 
	private String	excepDetail;		 /* 异常详情 */ 
	private String	recordData;		 /* 当前记录数据标准 */ 
	private Date	lastExecTime;		 /* 最后执行日期 */ 
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	recordDateMonth;		 /* 记录年月 yyyy-MM */ 
	private Double	totalNum;		 /* 总数 */ 
	private Double	currtNum;		 /* 当前数量 */ 
	private Double	lastNum;		 /* 剩余数量 */ 
	private Date	deadDate;		 /* 到期日期 */ 
	private Integer noticType;			/* 通知类型  0-预警通知  1-到期通知*/

	// Constructor
	public SysUseRecordNoticTask() {
	}
	
	/**
	 * full Constructor
	 */
	public SysUseRecordNoticTask(Integer id, Integer bussinessType, Date recordDateTime, Integer recordStatus, String excepDetail, String recordData, Date lastExecTime, Integer companyId, Integer recordDateMonth, Double totalNum, Double currtNum, Double lastNum, Date deadDate) {
		setId(id);
		this.bussinessType = bussinessType;
		this.recordDateTime = recordDateTime;
		this.recordStatus = recordStatus;
		this.excepDetail = excepDetail;
		this.recordData = recordData;
		this.lastExecTime = lastExecTime;
		this.companyId = companyId;
		this.recordDateMonth = recordDateMonth;
		this.totalNum = totalNum;
		this.currtNum = currtNum;
		this.lastNum = lastNum;
		this.deadDate = deadDate;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysUseRecordNoticTask可以实现连缀设置属性
	
	public Integer getBussinessType() {
		return bussinessType;
	}

	public SysUseRecordNoticTask setBussinessType(Integer bussinessType) {
		this.bussinessType = bussinessType;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getRecordDateTime() {
		return recordDateTime;
	}

	public SysUseRecordNoticTask setRecordDateTime(Date recordDateTime) {
		this.recordDateTime = recordDateTime;
		return this;
	}
	
	
	public Integer getRecordStatus() {
		return recordStatus;
	}

	public SysUseRecordNoticTask setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
		return this;
	}
	
	
	public String getExcepDetail() {
		return excepDetail;
	}

	public SysUseRecordNoticTask setExcepDetail(String excepDetail) {
		this.excepDetail = excepDetail;
		return this;
	}
	
	
	public String getRecordData() {
		return recordData;
	}

	public SysUseRecordNoticTask setRecordData(String recordData) {
		this.recordData = recordData;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getLastExecTime() {
		return lastExecTime;
	}

	public SysUseRecordNoticTask setLastExecTime(Date lastExecTime) {
		this.lastExecTime = lastExecTime;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysUseRecordNoticTask setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getRecordDateMonth() {
		return recordDateMonth;
	}

	public SysUseRecordNoticTask setRecordDateMonth(Integer recordDateMonth) {
		this.recordDateMonth = recordDateMonth;
		return this;
	}
	
	
	public Double getTotalNum() {
		return totalNum;
	}

	public SysUseRecordNoticTask setTotalNum(Double totalNum) {
		this.totalNum = totalNum;
		return this;
	}
	
	
	public Double getCurrtNum() {
		return currtNum;
	}

	public SysUseRecordNoticTask setCurrtNum(Double currtNum) {
		this.currtNum = currtNum;
		return this;
	}
	
	
	public Double getLastNum() {
		return lastNum;
	}

	public SysUseRecordNoticTask setLastNum(Double lastNum) {
		this.lastNum = lastNum;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getDeadDate() {
		return deadDate;
	}

	public SysUseRecordNoticTask setDeadDate(Date deadDate) {
		this.deadDate = deadDate;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysUseRecordNoticTask [" + "id=" + getId() + ", bussinessType=" + bussinessType + ", recordDateTime=" + recordDateTime + ", recordStatus=" + recordStatus + ", excepDetail=" + excepDetail + ", recordData=" + recordData + ", lastExecTime=" + lastExecTime + ", companyId=" + companyId + ", recordDateMonth=" + recordDateMonth + ", totalNum=" + totalNum + ", currtNum=" + currtNum + ", lastNum=" + lastNum + ", deadDate=" + deadDate +  "]";
	}

	public Integer getNoticType() {
		return noticType;
	}

	public void setNoticType(Integer noticType) {
		this.noticType = noticType;
	}
}
