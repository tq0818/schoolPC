package com.yuxin.wx.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;

import com.yuxin.wx.api.user.IUsersLoginSessionService;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersLoginSession;
import com.yuxin.wx.user.impl.UsersLoginSessionServiceImpl;
import com.yuxin.wx.utils.AliIpAddressUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.address.Address;
import com.yuxin.wx.vo.address.Result;

public class UserLoginThread implements Runnable {
//	@Autowired
    private IUsersLoginSessionService usersLoginSessionServiceImpl;
	private Session session;
	private Users users;
	private HttpServletRequest request;
	
	
	public UserLoginThread(IUsersLoginSessionService usersLoginSessionServiceImpl,Session session, Users users, HttpServletRequest request) {
		super();
		this.usersLoginSessionServiceImpl = usersLoginSessionServiceImpl;
		this.session = session;
		this.users = users;
		this.request = request;
	}

	@Override
	public void run() {
		// 记录登录日志
        UsersLoginSession loginSession = usersLoginSessionServiceImpl.findHistoryByUserId("" + users.getId());
        if (loginSession != null && 1 == loginSession.getStatus()) {
            loginSession.setStatus(2);
            usersLoginSessionServiceImpl.update(loginSession);
            // 把上个用户session踢掉，暂时不启用
            // JedisUtil.deleteByKey("shiro_redis_session"+loginSession.getSessionId());
        }
        UsersLoginSession userSession = new UsersLoginSession();
        userSession.setUserId("" + users.getId());
        userSession.setLoginTime(new Date());
        userSession.setStatus(1);
        userSession.setSessionId(session.getId().toString());
        userSession.setMacAddress(request.getRemoteAddr());
        userSession.setCompanyId(WebUtils.getCurrentCompanyId());
        String ip = WebUtils.getIpAddr(request);
        if (ip != null) {
            userSession.setIp(ip);
             Result<Address> add = AliIpAddressUtil.getAddress(ip);
             if (add.getCode() != null && add.getCode() == 0) {
                 if (add.getData() != null) {
                     Address address = add.getData();
                     userSession.setAddress(address.getRegion() + "-" + address.getCity() + "-" + address.getCounty());
                     userSession.setIsp(address.getIsp());
                 }
             }
//            userSession.setAddress(AddressUtils.getAddresses("ip=" + ip, "utf-8"));
//            userSession.setIsp(AddressUtils.getIsp("ip=" + ip, "utf-8"));
        }
        usersLoginSessionServiceImpl.insert(userSession);

	}

}
