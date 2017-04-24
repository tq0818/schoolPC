package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigHelp
 * 
 * @author chopin
 * @date 2015-7-14
 */
@SuppressWarnings("serial")
public class SysConfigHelp extends BaseEntity {
	
	
	private String	funcName;		 /* 功能名称 */ 
	private String	funcUrl;		 /* 功能地址 */ 
	private String	helpUrl;		 /* 帮助文档地址 */ 
	private String	videoId;		
	private String	videoUrl;	
	private Integer buyFlag;  

	// Constructor
	public SysConfigHelp() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigHelp(Integer id, String funcName, String funcUrl, String helpUrl, String videoId, String videoUrl) {
		setId(id);
		this.funcName = funcName;
		this.funcUrl = funcUrl;
		this.helpUrl = helpUrl;
		this.videoId = videoId;
		this.videoUrl = videoUrl;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigHelp可以实现连缀设置属性
	
	public String getFuncName() {
		return funcName;
	}

	public SysConfigHelp setFuncName(String funcName) {
		this.funcName = funcName;
		return this;
	}
	
	
	public String getFuncUrl() {
		return funcUrl;
	}

	public SysConfigHelp setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
		return this;
	}
	
	
	public String getHelpUrl() {
		return helpUrl;
	}

	public SysConfigHelp setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
		return this;
	}
	
	
	public String getVideoId() {
		return videoId;
	}

	public SysConfigHelp setVideoId(String videoId) {
		this.videoId = videoId;
		return this;
	}
	
	
	public String getVideoUrl() {
		return videoUrl;
	}

	public SysConfigHelp setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
		return this;
	}
	
	
	public Integer getBuyFlag() {
		return buyFlag;
	}

	public void setBuyFlag(Integer buyFlag) {
		this.buyFlag = buyFlag;
	}

	@Override
	public String toString() {
		return "SysConfigHelp [" + "id=" + getId() + ", funcName=" + funcName + ", funcUrl=" + funcUrl + ", helpUrl=" + helpUrl + ", videoId=" + videoId + ", videoUrl=" + videoUrl +  "]";
	}
}
