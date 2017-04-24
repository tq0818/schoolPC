package com.yuxin.wx.shiro.filter;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class GobalSessionListener implements SessionListener{

    @Override
    public void onExpiration(Session session) {
        System.out.println("会话过期");
    }
    @Override
    public void onStart(Session session) {
        System.out.println("用户登入");
    }
    @Override
    public void onStop(Session session) {
        System.out.println("用户退出");
    }
    
}
