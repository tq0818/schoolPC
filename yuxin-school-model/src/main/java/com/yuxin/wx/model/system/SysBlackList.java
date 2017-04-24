package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysBlackList
 * 
 * @author chopin
 * @date 2015-9-15
 */
@SuppressWarnings("serial")
public class SysBlackList extends BaseEntity {
	
	
	private String	tagKey;		 /* 维度  user-后台用户,user_front- 前台用户,mobile-手机号,ip-ip地址 */ 
	private String	tagValue;		 /* 值 */ 
	private Integer	records;		 /* 记录次数 */ 
	private Integer	flag;		 /* 生效状态：0-未生效 1-生效 */ 
	private String	recordTime;		 /* 记录时间 */ 
	private String	recordReason;		 /* 记录原因： sys_seruity_code 表  */ 

	// Constructor
	
	
	public SysBlackList() {
	}

	public SysBlackList(String tagKey, String tagValue, Integer records,
			Integer flag, String recordTime, String recordReason) {
		super();
		this.tagKey = tagKey;
		this.tagValue = tagValue;
		this.records = records;
		this.flag = flag;
		this.recordTime = recordTime;
		this.recordReason = recordReason;
	}

	public String getTagKey() {
		return tagKey;
	}

	public void setTagKey(String tagKey) {
		this.tagKey = tagKey;
	}

	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getRecordReason() {
		return recordReason;
	}

	public void setRecordReason(String recordReason) {
		this.recordReason = recordReason;
	}

	@Override
	public String toString() {
		return "SysBlackList [tagKey=" + tagKey + ", tagValue=" + tagValue
				+ ", records=" + records + ", flag=" + flag + ", recordTime="
				+ recordTime + ", recordReason=" + recordReason + "]";
	}
	

}
