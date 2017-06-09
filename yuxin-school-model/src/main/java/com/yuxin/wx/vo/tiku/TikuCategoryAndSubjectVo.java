package com.yuxin.wx.vo.tiku;

import java.util.List;

import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.model.tiku.TikuSubject;

public class TikuCategoryAndSubjectVo extends BaseEntity {
	private String	tikuName;		 /* 题库分类名称 */
	private String	tikuDesc;		 /* 题库分类描述 */ 
	private String  iconUrl;			/*题库图标地址*/
	private String  iconBackUrl;/*题库图标地址*/
	
	List<TikuSubject> subjects;
	

	public String getTikuName() {
		return tikuName;
	}
	public void setTikuName(String tikuName) {
		this.tikuName = tikuName;
	}
	public String getTikuDesc() {
		return tikuDesc;
	}
	public void setTikuDesc(String tikuDesc) {
		this.tikuDesc = tikuDesc;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getIconBackUrl() {
		return iconBackUrl;
	}
	public void setIconBackUrl(String iconBackUrl) {
		this.iconBackUrl = iconBackUrl;
	}
	public List<TikuSubject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<TikuSubject> subjects) {
		this.subjects = subjects;
	}
	
	
}
