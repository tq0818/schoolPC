package com.yuxin.wx.vo.classes;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;
/**
 * 
 * @ClassName: ClassFavVo
 * @Description: 收藏课程条件查询
 * @author ycl
 * @date 2015-4-14 下午2:54:39
 * @modifier
 * @modify-date 2015-4-14 下午2:54:39
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ClassFavVo extends BaseEntity {
	private Integer stuId;/*用户ID*/
	private String lableType;/*播放类型*/
	private Integer faceFlag; /* 是否属于面授 */
	private Integer liveFlag; /* 是否属于直播 */
	private Integer videoFlag; /* 是否属于视频 */
	private Integer remoteFlag; /* 是否属于远程 */
	private String commodityType;/*商品类型*/
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public String getLableType() {
		return lableType;
	}
	public void setLableType(String lableType) {
		this.lableType = lableType;
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
	public Integer getRemoteFlag() {
		return remoteFlag;
	}
	public void setRemoteFlag(Integer remoteFlag) {
		this.remoteFlag = remoteFlag;
	}
	public String getCommodityType() {
		return commodityType;
	}
	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}
	
}
