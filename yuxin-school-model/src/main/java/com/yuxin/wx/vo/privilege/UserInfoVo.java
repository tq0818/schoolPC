package com.yuxin.wx.vo.privilege;

import java.util.List;

public class UserInfoVo {
	//权限列表
	private List<PrivilegeVo> privilegeVos;
	//区县列表
	private List<AreaInfoVo> areaInfoVos;
	//科目列表
	private List<SubjectInfoVo> subjectInfoVos;
	//年级列表
	private List<GradeInfoVo>gradeInfoVos;
	//存储当前所选角色
	private String roleName;
	private List<EduSubjectClassTeacherInfo>eduSubjectClassTeacherInfos;
	
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<EduSubjectClassTeacherInfo> getEduSubjectClassTeacherInfos() {
		return eduSubjectClassTeacherInfos;
	}
	public void setEduSubjectClassTeacherInfos(
			List<EduSubjectClassTeacherInfo> eduSubjectClassTeacherInfos) {
		this.eduSubjectClassTeacherInfos = eduSubjectClassTeacherInfos;
	}
	public List<PrivilegeVo> getPrivilegeVos() {
		return privilegeVos;
	}
	public void setPrivilegeVos(List<PrivilegeVo> privilegeVos) {
		this.privilegeVos = privilegeVos;
	}
	public List<AreaInfoVo> getAreaInfoVos() {
		return areaInfoVos;
	}
	public void setAreaInfoVos(List<AreaInfoVo> areaInfoVos) {
		this.areaInfoVos = areaInfoVos;
	}
	public List<SubjectInfoVo> getSubjectInfoVos() {
		return subjectInfoVos;
	}
	public void setSubjectInfoVos(List<SubjectInfoVo> subjectInfoVos) {
		this.subjectInfoVos = subjectInfoVos;
	}
	public List<GradeInfoVo> getGradeInfoVos() {
		return gradeInfoVos;
	}
	public void setGradeInfoVos(List<GradeInfoVo> gradeInfoVos) {
		this.gradeInfoVos = gradeInfoVos;
	}	
}
