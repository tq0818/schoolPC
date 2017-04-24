package com.yuxin.wx.model.course;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:CourseProtocolConfig
 * 
 * @author chopin
 * @date 2016-10-31
 */
@SuppressWarnings("serial")
public class CourseProtocolConfig extends BaseEntity {
	
	
	private String	name;		 /* 协议名称 */ 
	private String	title;		 /* 标题 */ 
	private Integer	type;		 /* 类型  0-课程协议  1-课程包协议 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	creator;		 /* 创建人 */ 
	private Integer	useTime;		 /*  生效时机   0-课程购买前 1-课程学习前 */ 
	private String	content;		 /* 内容 */ 
	private Integer	status;		 /* 状态 0-无效 1-有效 */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private Integer companyId;
	private String createUserName;
	private String createTimeStr;

	

	

	// Constructor
	public CourseProtocolConfig() {
	}
	
	/**
	 * full Constructor
	 */
	public CourseProtocolConfig(Integer id, String name, String title, Integer type, Date createTime, Integer creator, Integer useTime, String content, Integer status, Integer delFlag) {
		setId(id);
		this.name = name;
		this.title = title;
		this.type = type;
		this.createTime = createTime;
		this.creator = creator;
		this.useTime = useTime;
		this.content = content;
		this.status = status;
		this.delFlag = delFlag;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为CourseProtocolConfig可以实现连缀设置属性
	
	public String getName() {
		return name;
	}

	public CourseProtocolConfig setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public String getTitle() {
		return title;
	}

	public CourseProtocolConfig setTitle(String title) {
		this.title = title;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getType() {
		return type;
	}

	public CourseProtocolConfig setType(Integer type) {
		this.type = type;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public CourseProtocolConfig setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	
	
	public Integer getCreator() {
		return creator;
	}

	public CourseProtocolConfig setCreator(Integer creator) {
		this.creator = creator;
		return this;
	}
	
	
	public Integer getUseTime() {
		return useTime;
	}

	public CourseProtocolConfig setUseTime(Integer useTime) {
		this.useTime = useTime;
		return this;
	}
	
	
	public String getContent() {
		return content;
	}

	public CourseProtocolConfig setContent(String content) {
		this.content = content;
		return this;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public CourseProtocolConfig setStatus(Integer status) {
		this.status = status;
		return this;
	}
	
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public CourseProtocolConfig setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
		return this;
	}
	
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	
	@Override
	public String toString() {
		return "CourseProtocolConfig [" + "id=" + getId() + ", name=" + name + ", title=" + title + ", type=" + type + ", createTime=" + createTime + ", creator=" + creator + ", useTime=" + useTime + ", content=" + content + ", status=" + status + ", delFlag=" + delFlag +  "]";
	}
}
