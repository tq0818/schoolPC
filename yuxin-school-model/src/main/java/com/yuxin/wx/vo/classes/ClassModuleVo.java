package com.yuxin.wx.vo.classes;

import java.util.Date;
import java.util.List;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:ClassModule
 * @author wang.zx
 * @date 2014-12-5
 */
@SuppressWarnings("serial")
public class ClassModuleVo extends BaseEntity {
	
	
	
	private String	name;		 /* 模块名称 */ 
	private Integer	itemOneId;		 /* 一级项目id */ 
	private Integer	itemSecondId;		 /* 二级项目id */ 
	private String	itemOneName;		 /* 一级项目name */ 
	private String	itemSecondName;		 /* 二级项目name */
	private Integer	totalClassHour;		 /* 模块总课时 */ 
	private String	teachMethod;		 /* 授课方式（面授；直播；视频）字典表数据 */ 
	private String	moduleType;		 /* 模块类型，保留字段；前导课；理论课；实操课；串讲课；.。。。取字典表数据 */ 
	private String	moduleDesc;		 /* 模块的描述 */ 
	private Integer	schoolId;		 /* 所属分校 */ 
	private String  campusName;      /* 校区名称 */ 
	private String  moduleNoName;    /* 班号名称*/
	private Integer  enrollYetStudents; /* 以招生人数*/
	private String	publishStatus;		 /* 模块发布状态（已发布；未发布；停用）取字典表数据 */ 
	private Date	publishTime;		 /* 模块发布时间 */ 
	private Integer	delFlag;		 /* 删除标记 */ 
	private Date	createTime;		 /* 创建时间 */ 
	private Integer	creator;		 /* 创建人 */ 
	private Date	updateTime;		 /* 修改时间 */ 
	private Integer	updator;		 /* 修改人 */
	private Integer companyId;
	private Integer moduleNoNum;    /*该模块的班号数量*/
	private Integer campusId;
    private List<ClassModuleNoListVo> classModuleNo;
	
	
    
	public Integer getCampusId() {
		return campusId;
	}
	public void setCampusId(Integer campusId) {
		this.campusId = campusId;
	}
	public List<ClassModuleNoListVo> getClassModuleNo() {
		return classModuleNo;
	}
	public void setClassModuleNo(List<ClassModuleNoListVo> classModuleNo) {
		this.classModuleNo = classModuleNo;
	}
	
	public String getCampusName() {
		return campusName;
	}
	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getItemOneId() {
		return itemOneId;
	}
	public void setItemOneId(Integer itemOneId) {
		this.itemOneId = itemOneId;
	}
	public Integer getItemSecondId() {
		return itemSecondId;
	}
	public void setItemSecondId(Integer itemSecondId) {
		this.itemSecondId = itemSecondId;
	}
	
	public String getItemOneName() {
		return itemOneName;
	}
	public void setItemOneName(String itemOneName) {
		this.itemOneName = itemOneName;
	}
	public String getItemSecondName() {
		return itemSecondName;
	}
	public void setItemSecondName(String itemSecondName) {
		this.itemSecondName = itemSecondName;
	}
	public Integer getTotalClassHour() {
		return totalClassHour;
	}
	
	public String getModuleNoName() {
		return moduleNoName;
	}
	public void setModuleNoName(String moduleNoName) {
		this.moduleNoName = moduleNoName;
	}
	
	public Integer getEnrollYetStudents() {
		return enrollYetStudents;
	}
	public void setEnrollYetStudents(Integer enrollYetStudents) {
		this.enrollYetStudents = enrollYetStudents;
	}
	public void setTotalClassHour(Integer totalClassHour) {
		this.totalClassHour = totalClassHour;
	}
	public String getTeachMethod() {
		return teachMethod;
	}
	public void setTeachMethod(String teachMethod) {
		this.teachMethod = teachMethod;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getModuleDesc() {
		return moduleDesc;
	}
	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getUpdator() {
		return updator;
	}
	public void setUpdator(Integer updator) {
		this.updator = updator;
	}
	
	public Integer getModuleNoNum() {
		return moduleNoNum;
	}
	public void setModuleNoNum(Integer moduleNoNum) {
		this.moduleNoNum = moduleNoNum;
	}
	public ClassModuleVo() {
	}
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	@Override
	public String toString() {
		return "ClassModuleVo [name=" + name + ", itemOneId=" + itemOneId
				+ ", itemSecondId=" + itemSecondId + ", itemOneName="
				+ itemOneName + ", itemSecondName=" + itemSecondName
				+ ", totalClassHour=" + totalClassHour + ", teachMethod="
				+ teachMethod + ", moduleType=" + moduleType + ", moduleDesc="
				+ moduleDesc + ", schoolId=" + schoolId + ", publishStatus="
				+ publishStatus + ", publishTime=" + publishTime + ", delFlag="
				+ delFlag + ", createTime=" + createTime + ", creator="
				+ creator + ", updateTime=" + updateTime + ", updator="
				+ updator + "]";
	}
	public ClassModuleVo(String name, Integer itemOneId, Integer itemSecondId,
			String itemOneName, String itemSecondName,
			Integer totalClassHour, String teachMethod, String moduleType,
			String moduleDesc, Integer schoolId, String publishStatus,
			Date publishTime, Integer delFlag, Date createTime,
			Integer creator, Date updateTime, Integer updator) {
		super();
		this.name = name;
		this.itemOneId = itemOneId;
		this.itemSecondId = itemSecondId;
		this.itemOneName = itemOneName;
		this.itemSecondName = itemSecondName;
		this.totalClassHour = totalClassHour;
		this.teachMethod = teachMethod;
		this.moduleType = moduleType;
		this.moduleDesc = moduleDesc;
		this.schoolId = schoolId;
		this.publishStatus = publishStatus;
		this.publishTime = publishTime;
		this.delFlag = delFlag;
		this.createTime = createTime;
		this.creator = creator;
		this.updateTime = updateTime;
		this.updator = updator;
	} 
}
