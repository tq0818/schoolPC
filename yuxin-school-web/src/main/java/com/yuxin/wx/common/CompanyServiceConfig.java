package com.yuxin.wx.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.utils.WebUtils;

/**
 * 查询机构配置的工具类
 * @author licong
 *
 */
@Controller
@RequestMapping("/yuxin")
public class CompanyServiceConfig {
	
	
	/**
	 * 查询机构服务
	 * @author licong
	 * @date 2017年2月7日 上午10:51:51
	 * @param  
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryCompanyService")
	public Object queryCompanyService(String groupCode){
		return WebUtils.checkCompanyServiceIsOpen(WebUtils.getCurrentCompanyId(), groupCode);
	}
	
	/**
	 * 
	 * Class Name: CompanyServiceConfig.java
	 * @Description: 查询方法
	 * @author dongshuai
	 * @date 2017年4月11日 下午1:43:19
	 * @modifier
	 * @modify-date 2017年4月11日 下午1:43:19
	 * @version 1.0
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryFunctionSet")
	public Object queryFunctionSet(String code){
		return WebUtils.getFunctionSet(code);
	}
}
