package com.yuxin.wx.vo.commodity;

import java.io.Serializable;
import java.util.Date;
/**
 * 订单结果
 * @author admin
 *
 */
public class PayorderResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String comName;//课程名称
	private String coverUrl;//图片地址
	private String teacherName;//教师名字
	private String courseType;//课程类型
	private String grade;//年级
	private String payStatusName;//付款结果
	private String payStatus;//付款状态
	private Double originalPrice;//定价
	private Double realPrice;//真实价格
	private String orderNum;//订单编号
	private Date orderTime;//订单时间
	private Integer comId;//商品id
	private String payType;//支付类型
	private String payInfo;//wap端支付地址
	private String wxCodeImgUrl;//微信支付地址
	private String zfbCodeImgUrl;//支付宝支付地址
	private String liveFlag;//直播
	private String videoFlag;//录播
	private String isMicroClass;//回放或微课
	private Double payPrice;//真实价格
	private Long remanentTime;//剩余支付时间
	private String remark;//备注
	private Integer Id;//主键id
	private String status;//订单对应的课程是否上架 1：上架 0：未上架
	private String originalPrices;
	private String realPrices;
	private String publicPrices;
	private String payPrices;
	private String publicPrice;
	private String cddsStatus;//是否数校上架（1：是，0：否）

	public String getPublicPrice() {
		return publicPrice;
	}
	public void setPublicPrice(String publicPrice) {
		this.publicPrice = publicPrice;
	}
	public String getPayPrices() {
		return payPrices;
	}
	public void setPayPrices(String payPrices) {
		this.payPrices = payPrices;
	}
	public String getOriginalPrices() {
		return originalPrices;
	}
	public void setOriginalPrices(String originalPrices) {
		this.originalPrices = originalPrices;
	}
	public String getRealPrices() {
		return realPrices;
	}
	public void setRealPrices(String realPrices) {
		this.realPrices = realPrices;
	}
	public String getPublicPrices() {
		return publicPrices;
	}
	public void setPublicPrices(String publicPrices) {
		this.publicPrices = publicPrices;
	}
	public String getCddsStatus() {
		return cddsStatus;
	}
	public void setCddsStatus(String cddsStatus) {
		this.cddsStatus = cddsStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLiveFlag() {
		return liveFlag;
	}
	public void setLiveFlag(String liveFlag) {
		this.liveFlag = liveFlag;
	}
	public String getVideoFlag() {
		return videoFlag;
	}
	public void setVideoFlag(String videoFlag) {
		this.videoFlag = videoFlag;
	}
	public String getIsMicroClass() {
		return isMicroClass;
	}
	public void setIsMicroClass(String isMicroClass) {
		this.isMicroClass = isMicroClass;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getPayStatusName() {
		return payStatusName;
	}
	public void setPayStatusName(String payStatusName) {
		this.payStatusName = payStatusName;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayInfo() {
		return payInfo;
	}
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}
	public String getWxCodeImgUrl() {
		return wxCodeImgUrl;
	}
	public void setWxCodeImgUrl(String wxCodeImgUrl) {
		this.wxCodeImgUrl = wxCodeImgUrl;
	}
	public String getZfbCodeImgUrl() {
		return zfbCodeImgUrl;
	}
	public void setZfbCodeImgUrl(String zfbCodeImgUrl) {
		this.zfbCodeImgUrl = zfbCodeImgUrl;
	}
	public Double getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
	}
	public Long getRemanentTime() {
		return remanentTime;
	}
	public void setRemanentTime(Long remanentTime) {
		this.remanentTime = remanentTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}

}
