package com.yuxin.wx.model.commodity;

import java.io.Serializable;
import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

public class CommoditySpecial extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8441136636461415502L;

	/**
	 * 专题封面标题 
	 */
	private String title;
	
	/**
	 * 专题 标签
	 */
	private String label;
	
	

	/**
	 * 详情标题
	 */
	private String detailTitle;
	
	/**
	 * 专题 封面图片地址
	 */
	private String coverPicUrl;
	
	/**
	 * 专题 封面描述
	 */
	private String descript;
	
	/**
	 * 专题 详情图片地址
	 */
	private String detailCoverPicUrl;
	
	/**
	 * 专题 正文
	 */
	private String detailText;
	
	/**
	 * 专题 推荐老师
	 */
	private String teacherIds;
	
	/**
	 * 专题推荐老师课程商品
	 */
	private String commodityIds;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 排序，降序
	 */
	private Integer orderFlag;

	/**
	 * 状态 0：未上架，1：上架
	 */
	private Integer status;
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	

	public Integer getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(Integer orderFlag) {
		this.orderFlag = orderFlag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCoverPicUrl() {
		return coverPicUrl;
	}

	public void setCoverPicUrl(String coverPicUrl) {
		this.coverPicUrl = coverPicUrl;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getDetailCoverPicUrl() {
		return detailCoverPicUrl;
	}

	public void setDetailCoverPicUrl(String detailCoverPicUrl) {
		this.detailCoverPicUrl = detailCoverPicUrl;
	}

	public String getDetailText() {
		return detailText;
	}

	public void setDetailText(String detailText) {
		this.detailText = detailText;
	}

	public String getTeacherIds() {
		return teacherIds;
	}

	public void setTeacherIds(String teacherIds) {
		this.teacherIds = teacherIds;
	}

	public String getCommodityIds() {
		return commodityIds;
	}

	public void setCommodityIds(String commodityIds) {
		this.commodityIds = commodityIds;
	}

	public String getDetailTitle() {
		return detailTitle;
	}

	public void setDetailTitle(String detailTitle) {
		this.detailTitle = detailTitle;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
