package com.yuxin.wx.controller.tool;

import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.tool.IToolService;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.user.Users;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tool")
public class ToolController {

    @Autowired
    private ISysConfigDictService sysConfigDictService;
    @Autowired
    private IToolService toolService;
	/**
	 * 工具类首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(Model model, HttpServletRequest request){
		return "/tool/index";
	}

	/**
	 * 通过机构生成机构账号工具
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/createUsersByOrg")
	@ResponseBody
	public String createUsersByOrg(Model model, HttpServletRequest request){
        //查询所有非直属校机构
        SysConfigDict search = new SysConfigDict();
        search.setDictCode("EDU_SCHOOL");
        search.setItemStatusCode("1");
		search.setPageSize(10000);
        List<SysConfigDict> orgList = sysConfigDictService.findSysConfigDictByPage(search);
		List<Users> usersList = new ArrayList<Users>();
		List<SysConfigDict> orgListNew = new ArrayList<SysConfigDict>();
		String message = "";
		Users users = null;
		for(int i=0; orgList!=null && i<orgList.size(); i++){
			SysConfigDict orgDict = orgList.get(i);
			if(orgDict!=null && !Integer.valueOf(1).equals(orgDict.getIsDirectly())){
				users = new Users();
				users.setUsername(orgDict.getItemCode());
				String password = new Md5Hash("111111", ByteSource.Util.bytes(orgDict.getItemCode() + "salt")).toHex();
				users.setPassword(password);
				users.setRealName(orgDict.getItemValue());
				users.setSex("MALE");
				users.setSchoolId(681);
				users.setStatus(1);
				users.setCompanyId(18113);
				usersList.add(users);
				orgListNew.add(orgDict);
			}
		}
		message = toolService.createUsersByOrg(usersList, orgListNew);
		return message;
	}

}
