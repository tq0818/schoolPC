package com.yuxin.wx.tool.impl;

import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.tool.IToolService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.auth.mapper.AuthUserRoleMapper;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.user.mapper.UsersAreaRelationMapper;
import com.yuxin.wx.vo.user.UsersAreaRelation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

/**
 * Service Implementation:Users
 */
@Service
@Transactional
public class ToolServiceImpl extends BaseServiceImpl implements IToolService {
	private Log log = LogFactory.getLog("log");
	@Autowired
	private IUsersService usersService;
	@Autowired
	private UsersAreaRelationMapper usersAreaRelationMapper;
	@Autowired
	private AuthUserRoleMapper authUserRoleMapper;
	@Autowired
	private ISysConfigDictService sysConfigDictService;
	@Override
	public String createUsersByOrg(List<Users> userList, List<SysConfigDict> orgListNew) {
		try {
			for(int i=0; userList!=null&&i<userList.size(); i++){
				Users users = userList.get(i);
				SysConfigDict sysConfigDict = orgListNew.get(i);
				List<Users> uList = usersService.findUserByCondition(users);
				//不存在用户那么创建它
				if(uList==null || uList.size()==0){
					Date date = new Date();
					usersService.insert(users);

					UsersAreaRelation usersAreaRelation = new UsersAreaRelation();
					usersAreaRelation.setUserId(users.getId());
					SysConfigDict parent = sysConfigDictService.findSysConfigDictById(sysConfigDict.getParentItemId());
					usersAreaRelation.setEduArea(parent.getItemCode());
					usersAreaRelation.setEduSchool(sysConfigDict.getItemCode());
					usersAreaRelation.setUserType(1);
					usersAreaRelation.setCreateTime(date);
					usersAreaRelationMapper.insert(usersAreaRelation);

					AuthUserRole userRole = new AuthUserRole();
					userRole.setUserId(users.getId());
					userRole.setRoleUid("168");
					userRole.setCreateTime(date);
					userRole.setCreator("1137");
					userRole.setUpdateTime(date);
					userRole.setUpdator("1137");
					authUserRoleMapper.insert(userRole);
				}
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return "出现异常！";
		}
		return "";
	}
}
