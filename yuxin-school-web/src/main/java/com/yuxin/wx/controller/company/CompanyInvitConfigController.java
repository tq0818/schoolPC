package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.company.ICompanyInvitConfigService;
import com.yuxin.wx.api.company.ICompanyMemberLevelConfigService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.company.impl.CompanyMemberLevelConfigServiceImpl;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyInvitConfig;
import com.yuxin.wx.model.company.CompanyMemberLevelConfig;
import com.yuxin.wx.model.company.CompanyMemberLevelConfigVo;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.util.StringUtil;
import com.yuxin.wx.utils.WebUtils;


/**
 * Controller of CompanyInvitConfig
 * @author chopin
 * @date 2016-7-29
 */
@Controller
@RequestMapping("/companyInvitConfig")
public class CompanyInvitConfigController {
	
	@Autowired
	private ICompanyInvitConfigService companyInvitConfigServiceImpl;
	
	@Autowired
	private ICompanyMemberLevelConfigService levelConfigService;
	@Autowired
	private ISysConfigServiceService sysConfigServiceImpl;
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyInvitConfig search){
		if (search == null) {
			search = new CompanyInvitConfig();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyInvitConfigServiceImpl.findCompanyInvitConfigByPage(search));
		return "companyInvitConfig/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyInvitConfig CompanyInvitConfig) {
		companyInvitConfigServiceImpl.insert(CompanyInvitConfig);
		return "redirect:/companyInvitConfig";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyInvitConfig CompanyInvitConfig) {
		companyInvitConfigServiceImpl.update(CompanyInvitConfig);
		return "redirect:/companyInvitConfig";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyInvitConfigServiceImpl.deleteCompanyInvitConfigById(id);
		return "redirect:/companyInvitConfig";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyInvitConfig getJson(Model model, @PathVariable Integer id){
		return companyInvitConfigServiceImpl.findCompanyInvitConfigById(id);
	}
	
	/**
	 * 后台接收Date转换
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	@RequestMapping(value="/toCompanyInviteCofig", method = RequestMethod.GET)
	public String toCompanyInviteCofig(Model model,HttpServletRequest request){
		Integer companyId = WebUtils.getCurrentCompanyId();
		//查询 机构信息
		
		Company company = companyServiceImpl.findCompanyById(companyId);
		model.addAttribute("company", company);
		CompanyInvitConfig search = new CompanyInvitConfig();
		search.setCompanyId(companyId);
		CompanyMemberLevelConfig levelConfigSearch = new CompanyMemberLevelConfig();
		levelConfigSearch.setCompanyId(companyId);
		/*List<CompanyMemberLevelConfigVo> levelConfigList = levelConfigService.findCompanyMemberLevelConfigByCompanyId(levelConfigSearch);
		model.addAttribute("levelConfigList", levelConfigList);*/
		//查询当前机构是否开通积分服务
		SysConfigService configService = new SysConfigService();
		configService.setGroupCode("SERVICE_INTEGRAL");
		configService.setCompanyId(companyId);
		List<SysConfigService> serviceList = sysConfigServiceImpl.findSysConfigServiceByPage(configService);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		if(serviceList != null && serviceList.size()>0){
			SysConfigService sysConfigService = serviceList.get(0);
			if(sysConfigService.getDelFlag()==1)
				model.addAttribute("IsIntegral", true);
			else
				model.addAttribute("IsIntegral", false);
		}
		else 
			model.addAttribute("IsIntegral", false);
		List<CompanyInvitConfig> list = companyInvitConfigServiceImpl.findCompanyInvitConfigByPage(search);
		
		if(list !=null && list.size()>0){
			model.addAttribute("inviteConfig", list.get(0));
			CompanyInvitConfig inviteConfig = list.get(0);
			String usePriv = inviteConfig.getUsePriv();
			if(usePriv !=null && !"".equals(usePriv)&& !"1".equals(usePriv)&& !"2".equals(usePriv)){
				String[] userlevels = usePriv.split(",");
				if(userlevels != null && userlevels.length >0){
					model.addAttribute("userlevels", Arrays.asList(userlevels));
				}
			}
			return "company/companyInviteConfigUpdate";
		}
		model.addAttribute("companyId", companyId.intValue());
		
		return "company/companyInviteConfigAdd";
	}
	
	@RequestMapping(value="/saveOrUpdateCompanyInvitConfig",method = RequestMethod.POST)
	@ResponseBody
	public String saveCompanyInvitConfig(Model model,HttpServletRequest request){
		Integer currentCompanyId = WebUtils.getCurrentCompanyId();
		CompanyInvitConfig inviteConfig = new CompanyInvitConfig();
		inviteConfig.setCompanyId(currentCompanyId);
		List<CompanyInvitConfig> list = companyInvitConfigServiceImpl.findCompanyInvitConfigByPage(inviteConfig);
		CompanyInvitConfig invitConfig = null;
		if(list != null && list.size()>0){
			invitConfig = list.get(0);
		}
		String openFlag = request.getParameter("openFlag");
		if(openFlag != null && Integer.parseInt(openFlag)==1 && invitConfig == null){
			 invitConfig = new CompanyInvitConfig();
			String usePriv = request.getParameter("usePriv");
			invitConfig.setCompanyId(WebUtils.getCurrentCompanyId());
			invitConfig.setOpenFlag(Integer.parseInt(openFlag));
			invitConfig.setUsePriv(usePriv);
			String oneBeinviteFlag = request.getParameter("oneBeinviteFlag");
			if(Integer.parseInt(oneBeinviteFlag)==1){
				invitConfig.setOneBeinviteFlag(Integer.parseInt(oneBeinviteFlag));
				String oneBeinviteGetMoney = request.getParameter("oneBeinviteGetMoney");
				String oneBeinviteMoneyPeriod = request.getParameter("oneBeinviteMoneyPeriod");
				invitConfig.setOneBeinviteGetMoney(Double.parseDouble(oneBeinviteGetMoney));
				invitConfig.setOneBeinviteMoneyPeriod(Integer.parseInt(oneBeinviteMoneyPeriod));
			}else{
				invitConfig.setOneBeinviteFlag(0);
				invitConfig.setOneBeinviteGetMoney(null);
				invitConfig.setOneBeinviteMoneyPeriod(null);
			}
			String oneInviteRgstMoney = request.getParameter("oneInviteRgstMoney");
			if(oneInviteRgstMoney!=null ){
				if("-1".equals(oneInviteRgstMoney) || "".equals(oneInviteRgstMoney)){
					invitConfig.setOneInviteRgstMoney(null);
				}else{
					invitConfig.setOneInviteRgstMoney(Double.parseDouble(oneInviteRgstMoney));
				}
			}
			
			String oneInviteRgstIntegral = request.getParameter("oneInviteRgstIntegral");
			if(oneInviteRgstIntegral!=null ){
				if("-1".equals(oneInviteRgstIntegral) ||"".equals(oneInviteRgstIntegral)){
					invitConfig.setOneInviteRgstIntegral(null);
				}else{
					invitConfig.setOneInviteRgstIntegral(Integer.parseInt(oneInviteRgstIntegral));
				}
			}
			String oneInviteCsptMoney = request.getParameter("oneInviteCsptMoney");
			if(oneInviteCsptMoney!=null){
				if("-1".equals(oneInviteCsptMoney) || "".equals(oneInviteCsptMoney)){
					invitConfig.setOneInviteCsptMoney(null);
				}else{
					invitConfig.setOneInviteCsptMoney(Double.parseDouble(oneInviteCsptMoney));
				}
			}
			String oneInviteCsptIntegral = request.getParameter("oneInviteCsptIntegral");
			if(oneInviteCsptIntegral!=null ){
				if("-1".equals(oneInviteCsptIntegral) || "".equals(oneInviteCsptIntegral)){
					invitConfig.setOneInviteCsptIntegral(null);
				}else{
					invitConfig.setOneInviteCsptIntegral(Integer.parseInt(oneInviteCsptIntegral));
				}
			}
			String oneInviteCsptPercent = request.getParameter("oneInviteCsptPercent");
			if(oneInviteCsptPercent!=null ){
				if("-1".equals(oneInviteCsptPercent) || "".equals(oneInviteCsptPercent)){
					invitConfig.setOneInviteCsptPercent(null);
				}else{
					invitConfig.setOneInviteCsptPercent(Integer.parseInt(oneInviteCsptPercent));
				}
			}
			String twoInviteFlag = request.getParameter("twoInviteFlag");
			if(Integer.parseInt(twoInviteFlag)==1){
				invitConfig.setTwoInviteFlag(Integer.parseInt(twoInviteFlag));
				String twoInviteRgstMoney = request.getParameter("twoInviteRgstMoney");
				if(twoInviteRgstMoney != null ){
					if("-1".equals(twoInviteRgstMoney) || "".equals(twoInviteRgstMoney)){
						invitConfig.setTwoInviteRgstMoney(null);
					}else{
						invitConfig.setTwoInviteRgstMoney(Double.parseDouble(twoInviteRgstMoney));
					}
				}
				String twoInviteRgstIntegral = request.getParameter("twoInviteRgstIntegral");
				if(twoInviteRgstIntegral != null ){
					if("-1".equals(twoInviteRgstIntegral) || "".equals(twoInviteRgstIntegral)){
						invitConfig.setTwoInviteRgstIntegral(null);
					}else{
						invitConfig.setTwoInviteRgstIntegral(Integer.parseInt(twoInviteRgstIntegral));
					}
				}
					
				String twoInviteCsptMoney = request.getParameter("twoInviteCsptMoney");
				if(twoInviteCsptMoney != null ){
					if("-1".equals(twoInviteCsptMoney) || "".equals(twoInviteCsptMoney)){
						invitConfig.setTwoInviteCsptMoney(null);
					}else{
						invitConfig.setTwoInviteCsptMoney(Double.parseDouble(twoInviteCsptMoney));
					}
				}
					
				String twoInviteCsptIntegral = request.getParameter("twoInviteCsptIntegral");
				if(twoInviteCsptIntegral != null ){
					if("-1".equals(twoInviteCsptIntegral) ||"".equals(twoInviteCsptIntegral)){
						invitConfig.setTwoInviteCsptIntegral(null);
					}else{
						invitConfig.setTwoInviteCsptIntegral(Integer.parseInt(twoInviteCsptIntegral));
					}
					
					
				}
				String twoInviteCsptPercent = request.getParameter("twoInviteCsptPercent");
				if(twoInviteCsptPercent != null ){
					if("-1".equals(twoInviteCsptPercent) || "".equals(twoInviteCsptPercent)){
						invitConfig.setTwoInviteCsptPercent(null);
					}else{
						invitConfig.setTwoInviteCsptPercent(Integer.parseInt(twoInviteCsptPercent));
					}
				}
			}else{
				invitConfig.setTwoInviteFlag(Integer.parseInt(twoInviteFlag));
				invitConfig.setTwoInviteCsptPercent(null);
				invitConfig.setTwoInviteCsptIntegral(null);
				invitConfig.setTwoInviteCsptMoney(null);
				invitConfig.setTwoInviteRgstIntegral(null);
				invitConfig.setTwoInviteRgstMoney(null);
			}
			
			try {
				companyInvitConfigServiceImpl.insert(invitConfig);
				return "1";
			} catch (Exception e) {
				e.printStackTrace();
				return "0";
			}
		}else{
			String usePriv = request.getParameter("usePriv");
			invitConfig.setCompanyId(WebUtils.getCurrentCompanyId());
			invitConfig.setOpenFlag(Integer.parseInt(openFlag));
			invitConfig.setUsePriv(usePriv);
			String oneBeinviteFlag = request.getParameter("oneBeinviteFlag");
			if(Integer.parseInt(oneBeinviteFlag)==1){
				invitConfig.setOneBeinviteFlag(Integer.parseInt(oneBeinviteFlag));
				String oneBeinviteGetMoney = request.getParameter("oneBeinviteGetMoney");
				String oneBeinviteMoneyPeriod = request.getParameter("oneBeinviteMoneyPeriod");
				invitConfig.setOneBeinviteGetMoney(Double.parseDouble(oneBeinviteGetMoney));
				invitConfig.setOneBeinviteMoneyPeriod(Integer.parseInt(oneBeinviteMoneyPeriod));
			}else if(Integer.parseInt(oneBeinviteFlag)==0){
				invitConfig.setOneBeinviteFlag(0);
				invitConfig.setOneBeinviteMoneyPeriod(null);
				invitConfig.setOneBeinviteGetMoney(null);
			}
			String oneInviteRgstMoney = request.getParameter("oneInviteRgstMoney");
			if(oneInviteRgstMoney!=null ){
				if("-1".equals(oneInviteRgstMoney) || "".equals(oneInviteRgstMoney)){
					invitConfig.setOneInviteRgstMoney(null);
				}else{
					invitConfig.setOneInviteRgstMoney(Double.parseDouble(oneInviteRgstMoney));
				}
			}
			
			String oneInviteRgstIntegral = request.getParameter("oneInviteRgstIntegral");
			if(oneInviteRgstIntegral!=null ){
				if("-1".equals(oneInviteRgstIntegral) ||"".equals(oneInviteRgstIntegral)){
					invitConfig.setOneInviteRgstIntegral(null);
				}else{
					invitConfig.setOneInviteRgstIntegral(Integer.parseInt(oneInviteRgstIntegral));
				}
			}
			String oneInviteCsptMoney = request.getParameter("oneInviteCsptMoney");
			if(oneInviteCsptMoney!=null){
				if("-1".equals(oneInviteCsptMoney) || "".equals(oneInviteCsptMoney)){
					invitConfig.setOneInviteCsptMoney(null);
				}else{
					invitConfig.setOneInviteCsptMoney(Double.parseDouble(oneInviteCsptMoney));
				}
			}
			String oneInviteCsptIntegral = request.getParameter("oneInviteCsptIntegral");
			if(oneInviteCsptIntegral!=null ){
				if("-1".equals(oneInviteCsptIntegral) || "".equals(oneInviteCsptIntegral)){
					invitConfig.setOneInviteCsptIntegral(null);
				}else{
					invitConfig.setOneInviteCsptIntegral(Integer.parseInt(oneInviteCsptIntegral));
				}
			}
			String oneInviteCsptPercent = request.getParameter("oneInviteCsptPercent");
			if(oneInviteCsptPercent!=null ){
				if("-1".equals(oneInviteCsptPercent) || "".equals(oneInviteCsptPercent)){
					invitConfig.setOneInviteCsptPercent(null);
				}else{
					invitConfig.setOneInviteCsptPercent(Integer.parseInt(oneInviteCsptPercent));
				}
			}
			String twoInviteFlag = request.getParameter("twoInviteFlag");
			if(Integer.parseInt(twoInviteFlag)==1){
				invitConfig.setTwoInviteFlag(Integer.parseInt(twoInviteFlag));
				String twoInviteRgstMoney = request.getParameter("twoInviteRgstMoney");
				if(twoInviteRgstMoney != null ){
					if("-1".equals(twoInviteRgstMoney) || "".equals(twoInviteRgstMoney)){
						invitConfig.setTwoInviteRgstMoney(null);
					}else{
						invitConfig.setTwoInviteRgstMoney(Double.parseDouble(twoInviteRgstMoney));
					}
				}
				String twoInviteRgstIntegral = request.getParameter("twoInviteRgstIntegral");
				if(twoInviteRgstIntegral != null ){
					if("-1".equals(twoInviteRgstIntegral) || "".equals(twoInviteRgstIntegral)){
						invitConfig.setTwoInviteRgstIntegral(null);
					}else{
						invitConfig.setTwoInviteRgstIntegral(Integer.parseInt(twoInviteRgstIntegral));
					}
				}
					
				String twoInviteCsptMoney = request.getParameter("twoInviteCsptMoney");
				if(twoInviteCsptMoney != null ){
					if("-1".equals(twoInviteCsptMoney) || "".equals(twoInviteCsptMoney)){
						invitConfig.setTwoInviteCsptMoney(null);
					}else{
						invitConfig.setTwoInviteCsptMoney(Double.parseDouble(twoInviteCsptMoney));
					}
				}
					
				String twoInviteCsptIntegral = request.getParameter("twoInviteCsptIntegral");
				if(twoInviteCsptIntegral != null ){
					if("-1".equals(twoInviteCsptIntegral) ||"".equals(twoInviteCsptIntegral)){
						invitConfig.setTwoInviteCsptIntegral(null);
					}else{
						invitConfig.setTwoInviteCsptIntegral(Integer.parseInt(twoInviteCsptIntegral));
					}
					
					
				}
				String twoInviteCsptPercent = request.getParameter("twoInviteCsptPercent");
				if(twoInviteCsptPercent != null ){
					if("-1".equals(twoInviteCsptPercent) || "".equals(twoInviteCsptPercent)){
						invitConfig.setTwoInviteCsptPercent(null);
					}else{
						invitConfig.setTwoInviteCsptPercent(Integer.parseInt(twoInviteCsptPercent));
					}
				}
					
			}
			else{
				invitConfig.setTwoInviteFlag(Integer.parseInt(twoInviteFlag));
				invitConfig.setTwoInviteFlag(Integer.parseInt(twoInviteFlag));
				invitConfig.setTwoInviteCsptPercent(null);
				invitConfig.setTwoInviteCsptIntegral(null);
				invitConfig.setTwoInviteCsptMoney(null);
				invitConfig.setTwoInviteRgstIntegral(null);
				invitConfig.setTwoInviteRgstMoney(null);
			}
			try {
				companyInvitConfigServiceImpl.update(invitConfig);
				return "1";
			} catch (Exception e) {
				e.printStackTrace();
				return "0";
			}
		}
		
	}
@RequestMapping(value="/directUpdate",method=RequestMethod.POST)
@ResponseBody
public String zhijieUpdate(Model model,HttpServletRequest request){
	Integer currentCompanyId = WebUtils.getCurrentCompanyId();
	CompanyInvitConfig inviteConfig = new CompanyInvitConfig();
	inviteConfig.setCompanyId(currentCompanyId);
	List<CompanyInvitConfig> list = companyInvitConfigServiceImpl.findCompanyInvitConfigByPage(inviteConfig);
	CompanyInvitConfig invitConfig = null;
	if(list != null && list.size()>0){
		invitConfig = list.get(0);
	}
	String openFlag = request.getParameter("openFlag");
	if(openFlag!=null && invitConfig != null){
		if(Integer.parseInt(openFlag)==0){
			companyInvitConfigServiceImpl.deleteCompanyInvitConfigById(invitConfig.getId());
		}else{
			invitConfig.setOpenFlag(Integer.parseInt(openFlag));
			companyInvitConfigServiceImpl.update(invitConfig);
		}
		return "1";
	}
	return "1";
	}

	//查询该机构是否开启邀请码功能
	@RequestMapping(value="/isOpenInvite",method=RequestMethod.GET)
	@ResponseBody
	public String isOpenInvite(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyInvitConfig search = new CompanyInvitConfig();
		search.setCompanyId(companyId);
		SysConfigService configService = new SysConfigService();
		configService.setCompanyId(companyId);
		configService.setGroupCode("SERVICE_PROMOTION");
		List<SysConfigService> list2 = sysConfigServiceImpl.findSysConfigServiceByPage(configService);
		if(list2!=null && list2.size()>0){
			SysConfigService sysConfigService = list2.get(0);
			Integer delFlag = sysConfigService.getDelFlag();
			if(delFlag==0){
				return "0";
			}
		}
		List<CompanyInvitConfig> list = companyInvitConfigServiceImpl.findCompanyInvitConfigByPage(search);
		if(list !=null && list.size()>0){
			CompanyInvitConfig companyInvitConfig = list.get(0);
			Integer openFlag = companyInvitConfig.getOpenFlag();
			if(openFlag==1)
				return "1";
			else
				return "0";
			
		}
		return "0";
	}
}
