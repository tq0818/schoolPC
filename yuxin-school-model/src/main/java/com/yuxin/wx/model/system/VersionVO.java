package com.yuxin.wx.model.system;

import java.io.Serializable;

/**
 * 版本VO
 * @author admin
 *
 */
public class VersionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String sysType;//系统类型
	private String versionNum;//当前版本
	private Integer isCoercive;//是否强制更新
	private String url;//安卓下载地址
	private String content;//内容
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSysType() {
		return sysType;
	}
	public void setSysType(String sysType) {
		this.sysType = sysType;
	}
	public String getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
	public Integer getIsCoercive() {
		return isCoercive;
	}
	public void setIsCoercive(Integer isCoercive) {
		this.isCoercive = isCoercive;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
