package com.yuxin.wx.controller.company;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;



import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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

import com.cloopen.rest.sdk.utils.encoder.BASE64Decoder;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.company.ICompanyConfigProxyOrgService;
import com.yuxin.wx.api.company.ICompanyInvitConfigOrgService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.IOrganInviteRewardRecordService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.company.CompanyConfigProxyOrg;
import com.yuxin.wx.model.company.CompanyInvitConfigOrg;
import com.yuxin.wx.model.company.OrganInviteRewardRecord;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.InviteCodeUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyConfigProxyOrg
 * @author chopin
 * @date 2017-3-13
 */
@Controller
@RequestMapping("/companyConfigProxyOrg")
public class CompanyConfigProxyOrgController {
	
	@Autowired
	private ICompanyConfigProxyOrgService companyConfigProxyOrgServiceImpl;
	
	@Autowired
	private IOrganInviteRewardRecordService organInviteRewardRecordServiceImpl;
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private ICompanyInvitConfigOrgService companyInvitConfigOrgServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyConfigProxyOrg search){
		if (search == null) {
			search = new CompanyConfigProxyOrg();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyConfigProxyOrgServiceImpl.findCompanyConfigProxyOrgByPage(search));
		return "companyConfigProxyOrg/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyConfigProxyOrg CompanyConfigProxyOrg) {
		companyConfigProxyOrgServiceImpl.insert(CompanyConfigProxyOrg);
		return "redirect:/companyConfigProxyOrg";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyConfigProxyOrg CompanyConfigProxyOrg) {
		companyConfigProxyOrgServiceImpl.update(CompanyConfigProxyOrg);
		return "redirect:/companyConfigProxyOrg";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyConfigProxyOrgServiceImpl.deleteCompanyConfigProxyOrgById(id);
		return "redirect:/companyConfigProxyOrg";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public CompanyConfigProxyOrg getJson(Model model, @PathVariable Integer id){
		return companyConfigProxyOrgServiceImpl.findCompanyConfigProxyOrgById(id);
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
	
	
	
	@RequestMapping(value="/toCompanyProxyAddOrUpdate")
	public String toCompanyProxyAddOrUpdate(HttpServletRequest request,Model model){
		String pid = request.getParameter("pid");
		if(StringUtils.isEmpty(pid))
		return "company/addProxy";
		else{
			try {
				CompanyConfigProxyOrg configProxyOrg = companyConfigProxyOrgServiceImpl.findCompanyConfigProxyOrgById(Integer.parseInt(pid));
				String address = configProxyOrg.getAddress();
				String[] adds = null;
				if(!StringUtils.isEmpty(address)){
					adds = address.split("\\^_\\^");
					if(adds.length==1){
						configProxyOrg.setDetailAddress(adds[0]);
					}else if(adds.length>=3){
						configProxyOrg.setProvince(Integer.parseInt(adds[0]));
						configProxyOrg.setCity(Integer.parseInt(adds[1]));
						model.addAttribute("provinceid", Integer.parseInt(adds[0]));
						model.addAttribute("cityid", Integer.parseInt(adds[1]));
						String detailad = adds[2];
						int index = detailad.indexOf("&");
						detailad = 	detailad.substring(index+1, detailad.length());	
						configProxyOrg.setDetailAddress(detailad);
					}else if(adds.length==2){
						configProxyOrg.setProvince(Integer.parseInt(adds[0]));
						configProxyOrg.setCity(Integer.parseInt(adds[1]));
						model.addAttribute("provinceid", Integer.parseInt(adds[0]));
						model.addAttribute("cityid", Integer.parseInt(adds[1]));
					}
				}
				model.addAttribute("proxy", configProxyOrg);
				model.addAttribute("prefixu", configProxyOrg.getPrefix());
				model.addAttribute("pid", pid);
			} catch (Exception e) {
				e.printStackTrace();
				return "404";
			}
			return "company/updateProxy";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/checkPrefix",method=RequestMethod.POST)
	public String checkPrefix(HttpServletRequest request,String prefix,String prefixu){
		CompanyConfigProxyOrg search = new CompanyConfigProxyOrg();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setPrefix(prefix);
		if(prefix.equalsIgnoreCase(prefixu))
			return "success";
		List<CompanyConfigProxyOrg> list = companyConfigProxyOrgServiceImpl.findCompanyConfigProxyOrgByPage(search);
		if(list!=null&& list.size()>0)
			return "fail";
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateProxy",method=RequestMethod.POST)
	public String addOrUpdateProxy(HttpServletRequest request,CompanyConfigProxyOrg proxy){
		Double inviteCastReward = null;
		CompanyInvitConfigOrg org = new CompanyInvitConfigOrg();
		org.setCompanyId(WebUtils.getCurrentCompanyId());
		List<CompanyInvitConfigOrg> list2 = companyInvitConfigOrgServiceImpl.findCompanyInvitConfigOrgByPage(org);
		if(list2!= null && list2.size()>0){
			CompanyInvitConfigOrg configOrg = list2.get(0);
			inviteCastReward = configOrg.getInvitCastAward();
		}
		if(proxy.getId()!=null){
			if(!StringUtils.isEmpty(proxy.getPrefix()))
			proxy.setPrefix(proxy.getPrefix().toUpperCase());
			if(proxy.getCommissionRate()==null)
				proxy.setCommissionRate(inviteCastReward);
			companyConfigProxyOrgServiceImpl.update(proxy);
			return "success";
		}else{
			if(proxy.getCommissionRate()==null)
				proxy.setCommissionRate(inviteCastReward);
			proxy.setCompanyId(WebUtils.getCurrentCompanyId());
			StringBuffer sb = new StringBuffer();
			String address = proxy.getAddress();
			Integer province = proxy.getProvince();
			Integer city = proxy.getCity();
			if(province!=null)
				sb.append(province).append("^_^");
			if(city!=null)
				sb.append(city).append("^_^");
			sb.append(address);
			proxy.setAddress(sb.toString());
			proxy.setCreateDate(new Date());
			proxy.setStatus(1);
			proxy.setDelFlag(0);
			if(!StringUtils.isEmpty(proxy.getPrefix()))
			proxy.setPrefix(proxy.getPrefix().toUpperCase());
			companyConfigProxyOrgServiceImpl.insert(proxy);
			return "success";
		}
		
	}
	
	@RequestMapping(value="/viewPrxoyList",method=RequestMethod.GET)
	public String viewPrxoyList(HttpServletRequest request,Model model){
		model.addAttribute("companyId", WebUtils.getCurrentCompanyId());
		CompanyInvitConfigOrg org = new CompanyInvitConfigOrg();
		org.setCompanyId(WebUtils.getCurrentCompanyId());
		org.setOpenFlag(1);
		List<CompanyInvitConfigOrg> list2 = companyInvitConfigOrgServiceImpl.findCompanyInvitConfigOrgByPage(org);
		if(list2!= null && list2.size()>0){
			model.addAttribute("openFlag", "success");
			CompanyInvitConfigOrg configOrg = list2.get(0);
			Integer customSetting = configOrg.getRewardsCustomSetting();
			model.addAttribute("customSetting", customSetting);
		}
		else
			model.addAttribute("openFlag", "fail");
		return "company/proxyList";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/queryProxysList")
	public PageFinder<CompanyConfigProxyOrg> queryProxysList(CompanyConfigProxyOrg search) {
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		if (search.getPageSize() == 12) {
			search.setPageSize(10);
		}
		PageFinder<CompanyConfigProxyOrg> pageFinder = companyConfigProxyOrgServiceImpl.findProxysList(search);
		return pageFinder;
	}
	
	@ResponseBody
	@RequestMapping(value="/checkOrgHasInviteRecord",method=RequestMethod.POST)
	public  String checkOrgHasInviteRecord(HttpServletRequest request,Integer proxyId){
		OrganInviteRewardRecord search = new OrganInviteRewardRecord();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setProxyOrganId(proxyId);
		List<OrganInviteRewardRecord> list = organInviteRewardRecordServiceImpl.findOrganInviteRewardRecordByPage(search);
		if(list!= null && list.size()>0)
			return "fail";
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/getOrgInviteCode",method=RequestMethod.POST)
	public String getOrgInviteCode(HttpServletRequest request,String proxyId){
		if(!StringUtils.isEmpty(proxyId)){
			//OrganInviteRewardRecord proxy = companyConfigProxyOrgServiceImpl.findOrganInviteRewardRecordById(Integer.parseInt(proxyId));
			CompanyConfigProxyOrg proxyOrg = companyConfigProxyOrgServiceImpl.findCompanyConfigProxyOrgById(Integer.parseInt(proxyId));
			if(proxyOrg!= null && !StringUtils.isEmpty(proxyOrg.getInviteCode()))
				return "http://" +companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId()).getDomain()+ "/shareLinkCode/orgIniteCode/" +proxyOrg.getInviteCode();
			else{
				String inviteCode = InviteCodeUtil.productInviteCode(Integer.parseInt(proxyId));
				proxyOrg.setInviteCode(inviteCode);
				companyConfigProxyOrgServiceImpl.update(proxyOrg);
				String inviteCodeStr = "http://" +companyServiceImpl.findCompanyById(WebUtils.getCurrentCompanyId()).getDomain()+ "/shareLinkCode/orgIniteCode/" + inviteCode;
				return inviteCodeStr;
			}
		}
		return null;
	}
	
	@RequestMapping(value="/downloadProxyInviteCode",method=RequestMethod.POST)
	public String downloadProxyInviteCode(HttpServletRequest request,HttpServletResponse response,String base64Code) throws URISyntaxException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ "代理机构二维码.png");
		
		 BASE64Decoder decoder = new BASE64Decoder();
		 base64Code = base64Code.substring(22);
		try {
			byte[] b = decoder.decodeBuffer(base64Code);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            ByteArrayInputStream in = new ByteArrayInputStream(b);
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            in.reset();
            String imagePath = FileUtil.upload2(".png", in, "temp", WebUtils.getCurrentCompanyId()+ "");
            String callUrl = "http://" +this.propertiesUtil.getProjectImageUrl() + "/" + imagePath;
            InputStream inputStream = new FileInputStream(new File(new URI(callUrl)));

			OutputStream os = response.getOutputStream();
			byte[] b2 = new byte[2048];
			int length;
			while ((length = inputStream.read(b2)) > 0) {
				os.write(b2, 0, length);
			}

			 // 这里主要关闭。
			os.close();
			in.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
}
