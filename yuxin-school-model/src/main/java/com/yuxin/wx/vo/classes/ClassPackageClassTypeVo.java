package com.yuxin.wx.vo.classes;


import com.yuxin.wx.common.BaseEntity;

public class ClassPackageClassTypeVo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Integer classTypeId;   //版型id
	private String	name;		 /* 班型名称 */ 
	private String	lableType;		 /* 班型标签 */ 
	private Double	realPrice;		 /* 真实价格（优惠之后的价格）保留字段，目前和original_price存一样的值 */ 
	private String	description;		 /* 班型描述 */ 
	private String	cover;		 /* 班型的封面，是一个url地址，如果在线售卖时启用该字段 */ 
	private Integer	baseNum;		 /*购买基数  */
	private Integer payNum;       /*购买数量  */
	
	private Integer faceFlag; /* 是否属于面授 */
	private Integer liveFlag; /* 是否属于直播 */
	private Integer videoFlag; /* 是否属于视频 */
	private Integer remoteFlag; /* 是否属于远程 */
	
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
	public Integer getRemoteFlag() {
		return remoteFlag;
	}
	public void setRemoteFlag(Integer remoteFlag) {
		this.remoteFlag = remoteFlag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLableType() {
		return lableType;
	}
	public void setLableType(String lableType) {
		this.lableType = lableType;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public Integer getBaseNum() {
		return baseNum;
	}
	public void setBaseNum(Integer baseNum) {
		this.baseNum = baseNum;
	}
	public Integer getPayNum() {
		return payNum;
	}
	public void setPayNum(Integer payNum) {
		this.payNum = payNum;
	}
	public Integer getFaceFlag() {
		return faceFlag;
	}
	public void setFaceFlag(Integer faceFlag) {
		this.faceFlag = faceFlag;
	}
	public Integer getLiveFlag() {
		return liveFlag;
	}
	public void setLiveFlag(Integer liveFlag) {
		this.liveFlag = liveFlag;
	}
	public Integer getVideoFlag() {
		return videoFlag;
	}
	public void setVideoFlag(Integer videoFlag) {
		this.videoFlag = videoFlag;
	}
	
	
	
}
