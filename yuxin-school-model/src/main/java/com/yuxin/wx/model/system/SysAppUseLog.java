package com.yuxin.wx.model.system;


import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysAppUseLog
 * 
 * @author chopin
 * @date 2016-5-5
 */
@SuppressWarnings("serial")
public class SysAppUseLog extends BaseEntity {
	
	
	private Integer	companyId;		 /* 公司ID */ 
	private Integer	userId;		 /* 用户ID */ 
	private String	v;		 /* 客户端版本 */ 
	private String	os;		 /* 操作系统 */ 
	private String	osv;		 /* 操作系统版本 */ 
	private String	model;		 /* 设备类型 */ 
	private String	screen;		 /* 设备的基础尺寸 */ 
	private String	density;		 /* 屏幕密度 */ 
	private String	uuid;		 /* 设备的uuid(IOS= idfa,android = MD5(imei,adroidid,mac))
             */ 
	private Date	recordTime;		 /* 记录时间 */ 
	private String	phoneNum;		 /* 手机号 */ 

	// Constructor
	public SysAppUseLog() {
	}
	
	/**
	 * full Constructor
	 */
	public SysAppUseLog(Integer id, Integer companyId, Integer userId, String v, String os, String osv, String model, String screen, String density, String uuid, Date recordTime, String phoneNum) {
		setId(id);
		this.companyId = companyId;
		this.userId = userId;
		this.v = v;
		this.os = os;
		this.osv = osv;
		this.model = model;
		this.screen = screen;
		this.density = density;
		this.uuid = uuid;
		this.recordTime = recordTime;
		this.phoneNum = phoneNum;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysAppUseLog可以实现连缀设置属性
	
	public Integer getCompanyId() {
		return companyId;
	}

	public SysAppUseLog setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public SysAppUseLog setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public String getV() {
		return v;
	}

	public SysAppUseLog setV(String v) {
		this.v = v;
		return this;
	}
	
	
	public String getOs() {
		return os;
	}

	public SysAppUseLog setOs(String os) {
		this.os = os;
		return this;
	}
	
	
	public String getOsv() {
		return osv;
	}

	public SysAppUseLog setOsv(String osv) {
		this.osv = osv;
		return this;
	}
	
	
	public String getModel() {
		return model;
	}

	public SysAppUseLog setModel(String model) {
		this.model = model;
		return this;
	}
	
	
	public String getScreen() {
		return screen;
	}

	public SysAppUseLog setScreen(String screen) {
		this.screen = screen;
		return this;
	}
	
	
	public String getDensity() {
		return density;
	}

	public SysAppUseLog setDensity(String density) {
		this.density = density;
		return this;
	}
	
	
	public String getUuid() {
		return uuid;
	}

	public SysAppUseLog setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getRecordTime() {
		return recordTime;
	}

	public SysAppUseLog setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
		return this;
	}
	
	
	public String getPhoneNum() {
		return phoneNum;
	}

	public SysAppUseLog setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysAppUseLog [" + "id=" + getId() + ", companyId=" + companyId + ", userId=" + userId + ", v=" + v + ", os=" + os + ", osv=" + osv + ", model=" + model + ", screen=" + screen + ", density=" + density + ", uuid=" + uuid + ", recordTime=" + recordTime + ", phoneNum=" + phoneNum +  "]";
	}
}
