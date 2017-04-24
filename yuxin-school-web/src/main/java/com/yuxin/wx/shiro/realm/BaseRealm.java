package com.yuxin.wx.shiro.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.yuxin.wx.api.auth.IAuthRolePrivilegeService;
import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.auth.IAuthUserRoleService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.user.UsersVo;

/**
 * 
 * @ClassName: BaseRealm 
 * @Description: shiro的realm基类
 * @author Chopin
 * @date 2015年1月25日
 * @version 1.0
 */
public class BaseRealm extends AuthorizingRealm{ 
    @Autowired
    private IUsersService usersServiceImpl;
    @Autowired
    private IAuthUserRoleService authUserRoleServiceImpl;
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        Users user = usersServiceImpl.queryUserByName(username);
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if(Boolean.TRUE.equals(user.getStatus()==0)) { 
            throw new LockedAccountException(); //帐号无效
        }
	        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
	        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
	        user.getUsername(), //用户名
	        user.getPassword(), //密码
	        ByteSource.Util.bytes(user.getUsername()+"salt"),//salt=username+salt
	        getName() //realm name
        );
        return authenticationInfo;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        Set set=principals.asSet();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(authUserRoleServiceImpl.queryUserRoles(username));
        Set<String> ss=authUserRoleServiceImpl.findUserPermissions(username);
        authorizationInfo.setStringPermissions(ss);
        return authorizationInfo;
    }
    protected void getRoleNamesForUser(){
        
    }
    
    protected void getPermissions(){
        
    }

}
