package com.yuxin.wx.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.utils.WebUtils;

@Controller
@RequestMapping("/sysConfigStuLogin")
public class SysConfigStuLoginController {
	
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;
	/**
	 * 打开配置页面
	 * @author licong
	 * @date 2016年5月18日 下午8:04:26
	 * @param  
	 * @return
	 */
	@RequestMapping("/setPage")
	public String setPage(HttpServletRequest request,Model model){
		CompanyFunctionSet search = new CompanyFunctionSet();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setFunctionCode("COMAPNY_ALLOW_USER_DLOGIN");
		List<CompanyFunctionSet> sets = companyFunctionSetServiceImpl.findCompanyFunctionSetBySearch(search);
		if(sets!=null && sets.size()>0){
			search = sets.get(0);
		}else{
			search.setFunctionCode("COMAPNY_ALLOW_USER_DLOGIN");
			search.setFunctionName("允许学员重复登录");
			search.setStatus("0");
			companyFunctionSetServiceImpl.insert(search);
		}
		model.addAttribute("flag", Integer.parseInt(search.getStatus()));
		return "system/configStuLogin";
	}
	/**
	 * 设置开启还是关闭
	 * @author licong
	 * @date 2016年5月18日 下午8:35:20
	 * @param  
	 * @param status
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setStuLogin")
	public boolean setStuLogin(String status){
		CompanyFunctionSet search = new CompanyFunctionSet();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setFunctionCode("COMAPNY_ALLOW_USER_DLOGIN");
		List<CompanyFunctionSet> sets = companyFunctionSetServiceImpl.findCompanyFunctionSetBySearch(search);
		if(sets != null && sets.size()>0){
			search = sets.get(0);
			search.setStatus(status);
			companyFunctionSetServiceImpl.update(search);
		}
		return true;
	}
}
