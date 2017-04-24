package com.yuxin.wx.model.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysTeacherPersonalStatusPic
 * 
 * @author chopin
 * @date 2015-10-28
 */
@SuppressWarnings("serial")
public class SysTeacherPersonalStatusPic extends BaseEntity {
	
	
	private Integer	statusId;		
	private String	url;		
	private Date	createTime;		
	private Integer	creator;		

	// Constructor
	public SysTeacherPersonalStatusPic() {
	}
	
	/**
	 * full Constructor
	 */
	public SysTeacherPersonalStatusPic(Integer id, Integer statusId, String url, Date createTime, Integer creator) {
		setId(id);
		this.statusId = statusId;
		this.url = url;
		this.createTime = createTime;
		this.creator = creator;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysTeacherPersonalStatusPic可以实现连缀设置属性
	
	public Integer getStatusId() {
		return statusId;
	}

	public SysTeacherPersonalStatusPic setStatusId(Integer statusId) {
		this.statusId = statusId;
		return this;
	}
	
	
	public String getUrl() {
		return url;
	}

	public SysTeacherPersonalStatusPic setUrl(String url) {
		this.url = url;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public SysTeacherPersonalStatusPic setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public SysTeacherPersonalStatusPic setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysTeacherPersonalStatusPic [" + "id=" + getId() + ", statusId=" + statusId + ", url=" + url + ", createTime=" + createTime + ", creator=" + creator +  "]";
	}
}
