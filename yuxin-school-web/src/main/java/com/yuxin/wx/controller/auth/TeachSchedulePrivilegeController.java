package com.yuxin.wx.controller.auth;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.utils.WebUtils;

/**
 * @ClassName: TeachschedulePrivilegeController
 * @Description:判断是否有排课教师权限
 * @author xukaiqiang
 * @date 2016年6月5日 下午5:49:32
 * @modifier
 * @modify-date 2016年6月5日 下午5:49:32
 * @version 1.0
 */
@Controller
@RequestMapping("/teachSchedulePrivilege")
public class TeachSchedulePrivilegeController {

	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;

	/**
	 * Class Name: TeachschedulePrivilegeController.java
	 * 
	 * @Description: 判断是否有排课教师权限
	 * @author xukaiqiang
	 * @date 2016年6月5日 下午5:50:50
	 * @modifier
	 * @modify-date 2016年6月5日 下午5:50:50
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/haveTeachSchedulePrivilege")
	public boolean haveTeachSchedulePrivilege() {
		
		Subject subject = SecurityUtils.getSubject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", WebUtils.getCurrentCompanyId());
		map.put("functionCode", "TEACHER_MODIFY_COURSE");
		Integer status = companyFunctionSetServiceImpl.findHaveTeachSchedulePrivilege(map);
		//不让修改，禁用一些操作
		if ((subject.hasRole("排课老师") && !(subject.hasRole("机构管理员") || subject.hasRole("分校管理员") || subject.hasRole("运营")) && status!=null && status == 1)) {
			return false;
		} else {
			return true;
		}
	}

}
