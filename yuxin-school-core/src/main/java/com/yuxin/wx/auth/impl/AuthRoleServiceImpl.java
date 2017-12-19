package com.yuxin.wx.auth.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.auth.mapper.AuthRoleMapper;
import com.yuxin.wx.auth.mapper.AuthRolePrivilegeMapper;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.auth.AuthRolePrivilege;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.privilege.AreaInfoVo;
import com.yuxin.wx.vo.privilege.AreaSchoolInfoVo;
import com.yuxin.wx.vo.privilege.ClassInfoVo;
import com.yuxin.wx.vo.privilege.EduSubjectClassTeacherInfo;
import com.yuxin.wx.vo.privilege.GradeInfoVo;
import com.yuxin.wx.vo.privilege.PrivilegeVo;
import com.yuxin.wx.vo.privilege.SchoolInfoVo;
import com.yuxin.wx.vo.privilege.SubjectInfoVo;
import com.yuxin.wx.vo.privilege.TreeNode;
import com.yuxin.wx.vo.privilege.UserRolesListVo;
import com.yuxin.wx.vo.user.UsersAreaRelation;

/**
 * Service Implementation:AuthRole
 * @author wang.zx
 * @date 2015-1-27
 */
@Service
@Transactional
public class AuthRoleServiceImpl extends BaseServiceImpl implements IAuthRoleService {

	@Autowired
	private AuthRoleMapper authRoleMapper;
	
	@Autowired
	private AuthRolePrivilegeMapper authRolePrivilegeMapper;
	
	/**
	 * 
	* @Title: saveAuthRole
	* @Description: 新增AuthRole
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  insert(AuthRole authRole){
		authRoleMapper.insert(authRole);
	};
	
	/**
	 * 
	* @Title: batchSaveAuthRole 
	* @Description: 批量新增AuthRole
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  batchInsert(List<AuthRole> entity){
		authRoleMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateAuthRole 
	* @Description: 编辑AuthRole
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  update(AuthRole entity){
		authRoleMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteAuthRoleById 
	* @Description: 根据id删除AuthRole
	* @param id
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  deleteAuthRoleById(String id){
	    authRoleMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteAuthRoleByIds 
	* @Description: 根据id批量删除AuthRole
	* @param ids
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public void  deleteAuthRoleByIds(String[] ids){
		authRoleMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findAuthRoleById 
	* @Description: 根据id查询
	* @param id
	* @return public void     返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx
	 */
	@Override
	public AuthRole findAuthRoleById(String id){
		return authRoleMapper.findById(id);
	};
	
 /**
  * 
 * @Title: findAuthRoleById 
 * @Description: 根据id查询
 * @param id
 * @return public void     返回类型 
 * @throws 
 * @exception 
 * @date 2015-1-27
 * @user by wangzx
  */
 @Override
 public List<AuthRole> findAllAuthRole(){
  return authRoleMapper.queryAll();
 };
	
	/**
	 * 
	* @Title: findAuthRoleByPage 
	* @Description: 分页查询
	* @return
	* @return List<AuthRole>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-1-27
	* @user by wangzx	 */
	@Override
	public List<AuthRole> findAuthRoleByPage(AuthRole search){
		return authRoleMapper.page(search);
	};
	
 /**
  * 
 * @Title: saveRoles 
 * @Description: 保存权限树
 * @return
 * @return List<AuthRole>    返回类型 
 * @throws 
 * @exception 
 * @date 2015-1-27
 * @user by chopin  */
 @Override
	public String saveRoles(List<TreeNode> nodes,Users loginUser){
	    authRoleMapper.deleteAll();
	    List<AuthRole> roles=new ArrayList<AuthRole>();
	    for(TreeNode node : nodes){
	        AuthRole role =new AuthRole();
	        role.setRoleName(node.getName());
	        role.setParentId(node.getPId());
	        role.setDescription("");
	        role.setCompanyId(loginUser.getCompanyId());
	        role.setCreateTime(new Date());
	        role.setCreator(loginUser.getUsername());
	        role.setUpdateTime(new Date());
	        role.setUpdator(loginUser.getUsername());
	        roles.add(role);
	    }
     authRoleMapper.batchInsert(roles);
	    return "success";
	}

	@Override
	public PageFinder<UserRolesListVo> queryAllUser(UserRolesListVo search) {
		List<AuthRole> arr=null;
		List<UserRolesListVo> data=authRoleMapper.queryAllUser(search);
		for(UserRolesListVo user:data){
			arr=authRoleMapper.findAuthRoleListByUser(user.getUserId());
			user.setArr(arr);
		}
		int rowCount=authRoleMapper.queryAllUserCount(search);
		PageFinder<UserRolesListVo> pageFinder=new PageFinder<UserRolesListVo>(search.getPage(), search.getPageSize(), rowCount, data);
		return pageFinder;
	}
	@Override
	public PageFinder<UserRolesListVo> queryNewAllUser(UserRolesListVo search) {
		List<AuthRole> arr=null;
		List<UserRolesListVo> data=authRoleMapper.queryNewAllUser(search);
		for(UserRolesListVo user:data){
			arr=authRoleMapper.findAuthRoleListByUser(user.getUserId());
			user.setArr(arr);
		}
		int rowCount=authRoleMapper.queryNewAllUserCount(search);
		PageFinder<UserRolesListVo> pageFinder=new PageFinder<UserRolesListVo>(search.getPage(), search.getPageSize(), rowCount, data);
		return pageFinder;
	}

	@Override
	public List<AuthRole> queryAuthRoleListByUser(Integer userId) {
		
		return authRoleMapper.findAuthRoleListByUser(userId);
	}

	@Override
	public List<AuthRole> findByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return authRoleMapper.findByCompanyId(companyId);
	}

	@Override
	public List<AuthRole> findAll() {
		// TODO Auto-generated method stub
		return authRoleMapper.queryAll();
	}

	@Override
	public List<AuthRole> queryRolesByCondition(AuthRole search) {
		// TODO Auto-generated method stub
		return authRoleMapper.queryRolesByCondition(search);
	}

	@Override
	public List<AuthRole> queryRolesByConditionInfo(AuthRole search) {
		// TODO Auto-generated method stub
		return authRoleMapper.queryRolesByConditionInfo(search);
	}

	@Override
	public boolean addAuthRoleandMenu(Integer companyId, String name,
			String privlageIds,Integer roleFlag) {
		boolean flag=false;
		try {
			AuthRole role=new AuthRole();
			role.setRoleName(name);
			role.setDescription(name);
			role.setCreateTime(new Date());
			role.setCompanyId(companyId);
			role.setRoleFlag(roleFlag);
			authRoleMapper.insert(role);
			role.setRoleUid(role.getId()+"");
			authRoleMapper.update(role);
			String[] priIds=privlageIds.split(",");
			for(int i=0;i<priIds.length;i++){
				AuthRolePrivilege privali=new AuthRolePrivilege();
				privali.setPrivilegeId(Integer.parseInt(priIds[i]));
				privali.setRoleUid(role.getId()+"");
				privali.setCreateTime(new Date());
				authRolePrivilegeMapper.insert(privali);
			}
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			flag=false;
		}
		
		return flag;
	}

	@Override
	public List<AuthRole> queryRolesByUid(String rUids) {
		// TODO Auto-generated method stub
		return authRoleMapper.queryRolesByUid(rUids);
	}

	/**
	 * 根据用户id查询用户角色，根据角色标记判断当前用户是否可跨分校
	 * true:当前可跨分校，false:当前不可跨分校
	 */
	@Override
	public boolean hasRoleFlag(Integer userId) {
		List<AuthRole> list=authRoleMapper.findAuthRoleListByUser(userId);
		if(null!=list){
			for(AuthRole auth:list){
				if(null!=auth && null!=auth.getRoleFlag() && auth.getRoleFlag() ==1){
					return true;
				}
			}
		}
		return false;
	}	

	@Override
	public boolean checkUserHasPrivilege(Integer userId, String privilegeCode) {
		List<AuthRole> roleArr=authRoleMapper.findAuthRoleListByUser(userId);
		if(null!=roleArr){
			for(AuthRole role:roleArr){
				if(null!=role){
					List<PrivilegeVo> prisArr=authRolePrivilegeMapper.findUserPrivileges(role.getRoleUid());
					if(null!=prisArr){
						for(PrivilegeVo prves:prisArr){
							if(null!=prves && privilegeCode.equals(prves.getPrivilegeName())){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public List<AreaInfoVo> queryAreaSchoolInfo(String userId,String isArea,String areaCode,String roleName) {
		//先查询出所有的行政
		List<AreaSchoolInfoVo> areaSchoolInfoVos=authRoleMapper.queryAreaSchoolInfo(); 
		//查询出用户所对应的区或者学校
		//usersAreaRelation
		
		Map<String,String> areaInfoMap=new HashMap<String,String>();
		if(StringUtils.isNotEmpty(userId)){
			List<UsersAreaRelation> usersAreaRelationInfos=authRoleMapper.queryUsersAreaRelationByUserId(userId);
			if(usersAreaRelationInfos!=null&&usersAreaRelationInfos.size()>0){
				for(UsersAreaRelation usersAreaRelation:usersAreaRelationInfos){
					if(usersAreaRelation!=null&&StringUtils.isNotEmpty(usersAreaRelation.getEduArea())&&"区县负责人".equals(roleName)
							&&StringUtils.isEmpty(usersAreaRelation.getEduSchool())){
						areaInfoMap.put(usersAreaRelation.getEduArea(),null);
					}
					if(usersAreaRelation!=null&&StringUtils.isNotEmpty(usersAreaRelation.getEduSchool())&&"学校负责人".equals(roleName)
							&&StringUtils.isNotEmpty(usersAreaRelation.getEduSchool())){
						areaInfoMap.put(usersAreaRelation.getEduArea()+"_"+usersAreaRelation.getEduSchool(),null);
					}
				}
			}
		}
		//找出学校对应的区代码
		String schoolArea=null;
		if("2".equals(isArea)){
			for(AreaSchoolInfoVo vo:areaSchoolInfoVos){
				if(StringUtils.isNotEmpty(areaCode)&&areaCode.equals(vo.getCode())){
					schoolArea=vo.getParentCode();
					break;
				}
			}
		}
		//进行分区
		Map<String,List<SchoolInfoVo>> datas=new HashMap<String,List<SchoolInfoVo>>();
		if(areaSchoolInfoVos!=null&&areaSchoolInfoVos.size()>0){
			//为区、学校级代码，设置区
			for(AreaSchoolInfoVo info:areaSchoolInfoVos){
				String parentCode=info.getParentCode();
				List<SchoolInfoVo> schoolInfoVos=null;
				String key=info.getCode()+"_"+info.getCodeName();
				if(StringUtils.isNotEmpty(parentCode)){
					key=info.getParentCode()+"_"+info.getParentName();
				}
				schoolInfoVos=datas.get(key);
				if(!StringUtils.isNotEmpty(parentCode)){
					if(StringUtils.isNotEmpty(schoolArea)&&!schoolArea.equals(info.getCode())){
						continue;
					}
					if(schoolInfoVos==null){
						schoolInfoVos=new ArrayList<SchoolInfoVo>();
					}
					datas.put(key,schoolInfoVos);
				}else{
					if(schoolInfoVos==null) continue;
					SchoolInfoVo schoolInfoVo=new SchoolInfoVo();
					if(areaInfoMap.containsKey(info.getParentCode()+"_"+info.getCode())){
						schoolInfoVo.setSelected(true);
					}
					if("2".equals(isArea)){
						if(info.getCode().equals(areaCode)){
							schoolInfoVo.setSchoolName(info.getCodeName());
							schoolInfoVo.setSchoolCode(info.getCode());
							schoolInfoVo.setParentName(info.getParentName());
							schoolInfoVo.setParentCode(info.getParentCode());
							if(areaInfoMap.containsKey(info.getParentCode()+"_"+info.getCode())){
								schoolInfoVo.setSelected(true);
							}
								schoolInfoVos.add(schoolInfoVo);
						}
					}else{
						schoolInfoVo.setSchoolName(info.getCodeName());
						schoolInfoVo.setSchoolCode(info.getCode());
						schoolInfoVo.setParentName(info.getParentName());
						schoolInfoVo.setParentCode(info.getParentCode());
						schoolInfoVos.add(schoolInfoVo);
					}
				}
			}
		}
		List<AreaInfoVo> areaInfoVos=new ArrayList<AreaInfoVo>();
		for(String key:datas.keySet()){
			List<SchoolInfoVo> values=datas.get(key);
			AreaInfoVo areaInfoVo=new AreaInfoVo();
			String areaCodes=key.split("_")[0];
			String areaName=key.split("_")[1];
			if("1".equals(isArea)){
				if(areaCodes.equals(areaCode)){
					//只能取所在区数据
					if(areaInfoMap.containsKey(areaCodes)){
						areaInfoVo.setSelected(true);
					}
					areaInfoVo.setAreaCode(areaCodes);
					areaInfoVo.setAreaName(areaName);
					areaInfoVo.setSchoolInfos(values);
					areaInfoVos.add(areaInfoVo);
					break;
				}
			}else{
				if(values!=null&&values.size()>0&&"学校负责人".equals(roleName)){
					for(SchoolInfoVo vo:values){
						if(vo.getSelected()){
							areaInfoVo.setSelected(true);
							break;
						}
					}
				}else if(areaInfoMap.containsKey(areaCodes)&&"区县负责人".equals(roleName)){
					areaInfoVo.setSelected(true);
				}
				areaInfoVo.setAreaCode(areaCodes);
				areaInfoVo.setAreaName(areaName);
				areaInfoVo.setSchoolInfos(values);
				areaInfoVos.add(areaInfoVo);
			}
		}
		return areaInfoVos;
	}
	@Override
	public List<SubjectInfoVo> queryAllSubjectInfo(String companyId) {
		return authRoleMapper.queryAllSubjectInfo(companyId);
	}

	@Override
	public List<GradeInfoVo> queryAllGradeInfos(String areaCode, String userId,String roleName) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("areaCode",areaCode);
		List<GradeInfoVo> gradeInfoVos=new ArrayList<GradeInfoVo>();
		//获取该分校所有的班级
		List<ClassInfoVo> classInfos=authRoleMapper.queryAllGradeInfos(params);
		Map<String,List<ClassInfoVo>> classMap=new HashMap<String,List<ClassInfoVo>>();
		List<String> gradeNames=new ArrayList<String>();
		String gradeName=null;
		//选择出对应的年级以及对应的班主任
		if(classInfos!=null&&classInfos.size()>0){
			for(ClassInfoVo info:classInfos){
				if("班主任".equals(roleName)&&StringUtils.isNotEmpty(userId)&&userId.equals(info.getUserId())){
					info.setSelected(true);
					gradeName=info.getGradeName();
				}
				List<ClassInfoVo> classInfoVos=classMap.get(info.getGradeName());
				if(classInfoVos==null){
					classInfoVos=new ArrayList<ClassInfoVo>();
					classMap.put(info.getGradeName(),classInfoVos);
					gradeNames.add(info.getGradeName());
				}
				classInfoVos.add(info);
			}
		}
		//初始化年级
		for(String key:gradeNames){
			List<ClassInfoVo> classInfoVos=classMap.get(key);
			GradeInfoVo gradeInfoVo=new GradeInfoVo();
			if(key.equals(gradeName)){
				gradeInfoVo.setSelected(true);
			}
			gradeInfoVo.setGradeName(key);
			gradeInfoVo.setClassInfos(classInfoVos);
			gradeInfoVos.add(gradeInfoVo);
		}
		return gradeInfoVos;
	}

	@Override
	public List<EduSubjectClassTeacherInfo> queryEduSubjectClassTeacherInfo(
			String areaCode, String userId) {
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("areaCode",areaCode);
		params.put("userId",userId);
		return authRoleMapper.queryEduSubjectClassTeacherInfo(params);
	}
	
}
