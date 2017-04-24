package com.yuxin.wx.model.classes;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassTypeRemoteRelation
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class ClassTypeRemoteRelation extends BaseEntity {
	
	
	private Integer	classTypeId;		
	private Integer	remoteId;		

	// Constructor
	public ClassTypeRemoteRelation() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassTypeRemoteRelation(Integer id, Integer classTypeId, Integer remoteId) {
		setId(id);
		this.classTypeId = classTypeId;
		this.remoteId = remoteId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassTypeRemoteRelation可以实现连缀设置属性
	
	public Integer getClassTypeId() {
		return classTypeId;
	}

	public ClassTypeRemoteRelation setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
		return this;
	}
	
	
	public Integer getRemoteId() {
		return remoteId;
	}

	public ClassTypeRemoteRelation setRemoteId(Integer remoteId) {
		this.remoteId = remoteId;
		return this;
	}
	
	@Override
	public String toString() {
		return "ClassTypeRemoteRelation [" + "id=" + getId() + ", classTypeId=" + classTypeId + ", remoteId=" + remoteId +  "]";
	}
}
