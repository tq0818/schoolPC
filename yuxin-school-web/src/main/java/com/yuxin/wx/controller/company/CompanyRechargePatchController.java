package com.yuxin.wx.controller.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.functions.Count;
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

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyRechargePatchService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyRechargePatch;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.CompanyRechargePatchVo;

/**
 * Controller of CompanyRechargePatch
 * @author chopin
 * @date 2017-4-10
 */
@Controller
@RequestMapping("/companyRechargePatch")
public class CompanyRechargePatchController {
	
	@Autowired
	private ICompanyRechargePatchService companyRechargePatchServiceImpl;
	
	@Autowired
	private ICompanyFunctionSetService companyFunctionSetServiceImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyRechargePatch search){
		if (search == null) {
			search = new CompanyRechargePatch();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyRechargePatchServiceImpl.findCompanyRechargePatchByPage(search));
		return "companyRechargePatch/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyRechargePatch CompanyRechargePatch) {
		companyRechargePatchServiceImpl.insert(CompanyRechargePatch);
		return "redirect:/companyRechargePatch";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyRechargePatch CompanyRechargePatch) {
		companyRechargePatchServiceImpl.update(CompanyRechargePatch);
		return "redirect:/companyRechargePatch";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyRechargePatchServiceImpl.deleteCompanyRechargePatchById(id);
		return "redirect:/companyRechargePatch";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public CompanyRechargePatch getJson(Model model, @PathVariable Integer id){
		return companyRechargePatchServiceImpl.findCompanyRechargePatchById(id);
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
	
	/**
	 * 
	 * Class Name: CompanyRechargePatchController.java
	 * @Description: 进入充值卡管理页面
	 * @author dongshuai
	 * @date 2017年4月10日 下午6:04:43
	 * @modifier
	 * @modify-date 2017年4月10日 下午6:04:43
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value = "/gotoRechargeCardManagePage")
	public String gotoRechargeCardManagePage(){
		return "rechargeCards/rechargeCard-manage";
	}
	
	/**
	 * 
	 * Class Name: CompanyRechargePatchController.java
	 * @Description: 进入创建充值卡页面
	 * @author dongshuai
	 * @date 2017年4月10日 下午6:06:46
	 * @modifier
	 * @modify-date 2017年4月10日 下午6:06:46
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value = "/gotoRechargeCardCreatePage")
	public String gotoRechargeCardCreatePage(){
		return "rechargeCards/rechargeCard-create";
	}
	
	/**
	 * 
	 * Class Name: CompanyRechargePatchController.java
	 * @Description: 创建充值卡
	 * @author dongshuai
	 * @date 2017年4月10日 下午7:27:02
	 * @modifier
	 * @modify-date 2017年4月10日 下午7:27:02
	 * @version 1.0
	 * @param data
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createRechargeCard", method = RequestMethod.POST)
	public JSONObject createRechargeCard(HttpServletRequest request, CompanyRechargePatch crp){
		
		JSONObject json  = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		
		Integer companyId = WebUtils.getCurrentCompanyId();
		Integer userId = WebUtils.getCurrentUserId(request);
		
		CompanyRechargePatch search = new CompanyRechargePatch();
		search.setCompanyId(companyId);
		search.setPromoCodePrefix(crp.getPromoCodePrefix().toUpperCase());
		
		int count = companyRechargePatchServiceImpl.queryPrefixCount(search);
		
		if(count>0){
			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
			json.put(JsonMsg.MSG, "充值卡前缀已存在！");
			return json;
		}
		
		//初始化数据
		crp.setPromoCodePrefix(crp.getPromoCodePrefix().toUpperCase());
		crp.setCompanyId(companyId);
		crp.setUsedNum(0);
		crp.setRemainNum(crp.getTotalNum());
		crp.setStatus(1);
		crp.setIssueWay(1);
		crp.setCreateDate(new Date());
		crp.setCreator(userId);
		
		if(crp.getStartDate() != null){
			try {
				crp.setTimeLimitFrom(new SimpleDateFormat("yyyy-MM-dd").parse(crp.getStartDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(crp.getEndDate() != null){
			try {
				crp.setTimeLimitTo(new SimpleDateFormat("yyyy-MM-dd").parse(crp.getEndDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		companyRechargePatchServiceImpl.insert(crp);
		
		boolean createOk = createRechargeLibs(crp);
		
		if(!createOk){
			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
			json.put(JsonMsg.MSG, "充值卡库生成失败！");
			return json;
		}
		json.put("patchId", crp.getId());
		return json;
	}
	
	private boolean createRechargeLibs(CompanyRechargePatch crp){
		return companyRechargePatchServiceImpl.createRechargeLibs(crp);
	}
	
	/**
	 * 
	 * Class Name: CompanyRechargePatchController.java
	 * @Description: 查询
	 * @author dongshuai
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value= "/queryRechargePatch", method = RequestMethod.POST)
	public JSONObject queryRechargePatch(CompanyRechargePatchVo search){
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		Integer companyId = WebUtils.getCurrentCompanyId();
		if(search.getTimeMark()!=null){
			switch (search.getTimeMark()) {
			case "today":
				search.setTimeLen(0);
				break;
			case "yesday":
				search.setTimeLen(1);
				break;
			case "sevnday":
				search.setTimeLen(7);
				break;
			case "thirty":
				search.setTimeLen(30);
				break;
			case "thirmonth":
				search.setTimeLen(90);
				break;
			default:
				break;
			}
		}
		search.setCompanyId(companyId);
		
		Double price = 0.00;
		List<CompanyRechargePatchVo> list = companyRechargePatchServiceImpl.queryPatchsList(search);
		for (CompanyRechargePatchVo crp : list) {
			price += crp.getUsedNum() * crp.getCashAmount();
		}
		json.put("price", price);	
		
		PageFinder<CompanyRechargePatchVo> patchs = companyRechargePatchServiceImpl.queryPatchList(search);
		json.put("patchs", patchs);
		
		return json;
	}
	
	/**
	 * 
	 * Class Name: CompanyRechargePatchController.java
	 * @Description: 禁用充值卡
	 * @author dongshuai
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delPatch", method = RequestMethod.POST)
	public JSONObject delPatch(CompanyRechargePatch search){
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		
		search = companyRechargePatchServiceImpl.findCompanyRechargePatchById(search.getId());
		
		if(search == null){
			json.put(JsonMsg.RESULT, JsonMsg.ERROR);
			json.put(JsonMsg.MSG, "数据错误,请刷新页面重试！");
			return json;
		}
		
		search.setStatus(0);
		companyRechargePatchServiceImpl.update(search);
		return json;
	}
	
	/**
	 * 
	 * Class Name: CompanyRechargePatchController.java
	 * @Description: 开关
	 * @author dongshuai
	 * @date 2017年4月11日 下午1:05:05
	 * @modifier
	 * @modify-date 2017年4月11日 下午1:05:05
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value = "/gotoRechargeCardOpenFlagPage")
	public String gotoRechargeCardOpenFlagPage(Model model){
		//公司配置
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company=companyServiceImpl.findCompanyById(companyId);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("company", company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		
		CompanyFunctionSet rechargeCardService = WebUtils.getFunctionSet("RECHARGECARD_OPENFLAG");
		if(rechargeCardService == null){
			rechargeCardService = new CompanyFunctionSet();
			rechargeCardService.setCompanyId(companyId);
			rechargeCardService.setFunctionCode("RECHARGECARD_OPENFLAG");
			rechargeCardService.setFunctionName("充值卡开关");
			rechargeCardService.setContent("0: 关闭；1：开启");
			rechargeCardService.setStatus("0");
			companyFunctionSetServiceImpl.insert(rechargeCardService);
		}
		model.addAttribute("rechargeCardService", rechargeCardService.getStatus());
		
		return "rechargeCards/rechargeCard-openflag";
	}
	
	/**
	 * 
	 * Class Name: CompanyRechargePatchController.java
	 * @Description: 更改服务开关
	 * @author dongshuai
	 * @date 2017年4月11日 下午1:23:33
	 * @modifier
	 * @modify-date 2017年4月11日 下午1:23:33
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value= "/updateOpenFlag", method = RequestMethod.POST)
	public JSONObject updateOpenFlag(CompanyFunctionSet search){
		Integer companyId = WebUtils.getCurrentCompanyId();
		JSONObject json = new JSONObject();
		json.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		
		CompanyFunctionSet rechargeCardService = WebUtils.getFunctionSet("RECHARGECARD_OPENFLAG");
		
		if(rechargeCardService == null){
			rechargeCardService = new CompanyFunctionSet();
			rechargeCardService.setCompanyId(companyId);
			rechargeCardService.setFunctionCode("RECHARGECARD_OPENFLAG");
			rechargeCardService.setFunctionName("充值卡开关");
			rechargeCardService.setContent("0: 关闭；1：开启");
			rechargeCardService.setStatus("0");
			companyFunctionSetServiceImpl.insert(rechargeCardService);
		}
		
		rechargeCardService.setStatus(search.getStatus());
		
		companyFunctionSetServiceImpl.update(rechargeCardService);
		
		return json;
	}
}
