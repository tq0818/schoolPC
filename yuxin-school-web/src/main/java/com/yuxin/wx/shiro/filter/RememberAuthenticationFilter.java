package com.yuxin.wx.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.user.UsersVo;

public class RememberAuthenticationFilter extends FormAuthenticationFilter{
    @Autowired
    private IUsersService usersServiceImpl;
    
 @Override  
 protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {  
     Subject subject = getSubject(request, response);  
     if(!subject.isAuthenticated() && subject.isRemembered()){  
         Session session = subject.getSession(true);  
         if(session.getAttribute("username") == null){  
             String username = subject.getPrincipal().toString();  
             initUserContext(username, subject);  
         }  
     }  
     return subject.isAuthenticated() || subject.isRemembered();  
 }
 /**
  * 把用户信息存入session 
  */
    private void initUserContext(String username, Subject subject) {
         Users user=usersServiceImpl.queryUserByName(username);
         Session session = subject.getSession(true);
         session.setAttribute("loginUser", user);
    }
}
