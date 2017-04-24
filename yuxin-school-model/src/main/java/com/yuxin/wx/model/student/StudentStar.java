package com.yuxin.wx.model.student;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:StudentStar
 * 
 * @author chopin
 * @date 2017-3-24
 */
@SuppressWarnings("serial")
public class StudentStar extends BaseEntity {
	
	
	private String	userName;		 /* 学员名称 */ 
	private String	userPic;		 /* 学员图像 */ 
	private Integer	sortNum;		 /* 排序号 */ 
	private String	des;		 /* 学员感想 */ 
	private Integer	companyId;		
	private String	userPhoto;		 /* 生活照 */ 

	// Constructor
	public StudentStar() {
	}
	
	/**
	 * full Constructor
	 */
	public StudentStar(Integer id, String userName, String userPic, Integer sortNum, String des, Integer companyId, String userPhoto) {
		setId(id);
		this.userName = userName;
		this.userPic = userPic;
		this.sortNum = sortNum;
		this.des = des;
		this.companyId = companyId;
		this.userPhoto = userPhoto;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为StudentStar可以实现连缀设置属性
	
	public String getUserName() {
		return userName;
	}

	public StudentStar setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	
	
	public String getUserPic() {
		return userPic;
	}

	public StudentStar setUserPic(String userPic) {
		this.userPic = userPic;
		return this;
	}
	
	
	public Integer getSortNum() {
		return sortNum;
	}

	public StudentStar setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
		return this;
	}
	
	
	public String getDes() {
		return des;
	}

	public StudentStar setDes(String des) {
		this.des = des;
		return this;
	}
	
	
	public Integer getCompanyId() {
		return companyId;
	}

	public StudentStar setCompanyId(Integer companyId) {
		this.companyId = companyId;
		return this;
	}
	
	
	public String getUserPhoto() {
		return userPhoto;
	}

	public StudentStar setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
		return this;
	}
	
	@Override
	public String toString() {
		return "StudentStar [" + "id=" + getId() + ", userName=" + userName + ", userPic=" + userPic + ", sortNum=" + sortNum + ", des=" + des + ", companyId=" + companyId + ", userPhoto=" + userPhoto +  "]";
	}
}
