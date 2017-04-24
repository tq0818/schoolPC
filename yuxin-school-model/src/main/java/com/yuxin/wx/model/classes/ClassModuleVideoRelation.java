package com.yuxin.wx.model.classes;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassModuleVideoRelation
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class ClassModuleVideoRelation extends BaseEntity {
	
	
	private Integer	moduleId;		 /* 模块id */ 
	private Integer	videoId;		 /* 视频课程id */ 

	// Constructor
	public ClassModuleVideoRelation() {
	}
	
	/**
	 * full Constructor
	 */
	public ClassModuleVideoRelation(Integer id, Integer moduleId, Integer videoId) {
		setId(id);
		this.moduleId = moduleId;
		this.videoId = videoId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为ClassModuleVideoRelation可以实现连缀设置属性
	
	public Integer getModuleId() {
		return moduleId;
	}

	public ClassModuleVideoRelation setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
		return this;
	}
	
	
	public Integer getVideoId() {
		return videoId;
	}

	public ClassModuleVideoRelation setVideoId(Integer videoId) {
		this.videoId = videoId;
		return this;
	}
	
	@Override
	public String toString() {
		return "ClassModuleVideoRelation [" + "id=" + getId() + ", moduleId=" + moduleId + ", videoId=" + videoId +  "]";
	}
}
