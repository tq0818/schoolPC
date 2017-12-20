package com.yuxin.wx.vo.student;

import java.util.HashMap;
import java.util.Map;

import com.yuxin.wx.common.BaseEntity;

public class StudentAll4CompanyVo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	Map<String,StudentImportVo> mobiles 	 = new HashMap<String, StudentImportVo>();		/* 存网校学员手机号 */
	Map<String,StudentImportVo> usernames 	 = new HashMap<String, StudentImportVo>();		/* 存网校学员用户名 */
	Map<String,StudentImportVo> identityIds = new HashMap<String, StudentImportVo>();		/* 存网校学员身份证号 */
//	List<String> emails 	 = new ArrayList<String>();		/* 存网校学员邮箱 */
//	List<String> qqs 		 = new ArrayList<String>();		/* 存网校学员QQ */
	@Override
    public String toString() {
	    return "StudentAll4CompanyVo [mobiles=" + mobiles + ", usernames=" + usernames + ", identityIds=" + identityIds + "]";
    }
	
    public Map<String, StudentImportVo> getMobiles() {
    	return mobiles;
    }
	
    public void setMobiles(Map<String, StudentImportVo> mobiles) {
    	this.mobiles = mobiles;
    }
	
    public Map<String, StudentImportVo> getUsernames() {
    	return usernames;
    }
	
    public void setUsernames(Map<String, StudentImportVo> usernames) {
    	this.usernames = usernames;
    }
	
    public Map<String, StudentImportVo> getIdentityIds() {
    	return identityIds;
    }
	
    public void setIdentityIds(Map<String, StudentImportVo> identityIds) {
    	this.identityIds = identityIds;
    }
	
    public static long getSerialversionuid() {
    	return serialVersionUID;
    }
	

}
