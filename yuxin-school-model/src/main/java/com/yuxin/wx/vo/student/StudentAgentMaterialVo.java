package com.yuxin.wx.vo.student;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.course.CourseVideo;
import com.yuxin.wx.model.student.StudentAgentMaterial;
import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.util.ShortDateSerializer;
import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:StudentPayMaster
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class StudentAgentMaterialVo extends BaseEntity {
	
	private Integer	itemOneId;		 /* 一级项目id */ 
	private String	itemOneName;		 /* 一级项目名称 */ 
	private String materialName; 	 /* 资料名称 */ 
	private Integer materialNumYet;      /* 已交资料数量 */
	
	public Integer getItemOneId() {
		return itemOneId;
	}
	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}
	public String getItemOneName() {
		return itemOneName;
	}
	public void setItemOneName(String itemOneName) {
		this.itemOneName = itemOneName;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Integer getMaterialNumYet() {
		return materialNumYet;
	}
	public void setMaterialNumYet(Integer materialNumYet) {
		this.materialNumYet = materialNumYet;
	} 
}
