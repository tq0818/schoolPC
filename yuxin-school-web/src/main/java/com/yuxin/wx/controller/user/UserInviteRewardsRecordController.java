package com.yuxin.wx.controller.user;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.oss.common.utils.DateUtilTest;
import com.yuxin.wx.api.company.ICompanyInvitConfigService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.user.IUserInviteRewardsRecordService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.ExcelSheetEntity;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.company.impl.CompanyInvitConfigServiceImpl;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyInvitConfig;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.user.UserInviteRewardsRecord;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.util.DateUtil;
import com.yuxin.wx.util.StringUtil;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.user.UserInviteRewardsRecordVo;


/**
 * Controller of UserInviteRewardsRecord
 * @author chopin
 * @date 2016-7-29
 */
@Controller
@RequestMapping("/userInviteRewardsRecord")
public class UserInviteRewardsRecordController {
	
	@Autowired
	private IUserInviteRewardsRecordService userInviteRewardsRecordServiceImpl;
	
	@Autowired
	private ICompanyService companyServiceImpl;
	
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	@Autowired
	private IUsersFrontService userfrontservice;
	
	@Autowired
	private ICompanyInvitConfigService companyInvitConfigServiceImpl;
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, UserInviteRewardsRecord search){
		if (search == null) {
			search = new UserInviteRewardsRecord();
			// search.setPageSize(20);
		}
		model.addAttribute("list", userInviteRewardsRecordServiceImpl.findUserInviteRewardsRecordByPage(search));
		return "userInviteRewardsRecord/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(UserInviteRewardsRecord UserInviteRewardsRecord) {
		userInviteRewardsRecordServiceImpl.insert(UserInviteRewardsRecord);
		return "redirect:/userInviteRewardsRecord";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(UserInviteRewardsRecord UserInviteRewardsRecord) {
		userInviteRewardsRecordServiceImpl.update(UserInviteRewardsRecord);
		return "redirect:/userInviteRewardsRecord";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		userInviteRewardsRecordServiceImpl.deleteUserInviteRewardsRecordById(id);
		return "redirect:/userInviteRewardsRecord";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public UserInviteRewardsRecord getJson(Model model, @PathVariable Integer id){
		return userInviteRewardsRecordServiceImpl.findUserInviteRewardsRecordById(id);
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
	
	@RequestMapping(value="/toRewardsRecordList", method = RequestMethod.GET)
	public String toRewardsRecordList(Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		//查询 机构信息
		Company company = companyServiceImpl.findCompanyById(companyId);
		model.addAttribute("company", company);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		int totalConNumber = userInviteRewardsRecordServiceImpl.getTotalConNumber(companyId);
		int totalRegNumber = userInviteRewardsRecordServiceImpl.getTotalRegNumber(companyId);
		int totalRewardsIntegral = userInviteRewardsRecordServiceImpl.getTotalRewardsIntegral(companyId);
		double totalRewardsMoney = userInviteRewardsRecordServiceImpl.getTotalRewardsMoney(companyId);
		model.addAttribute("totalConNumber", totalConNumber);
		model.addAttribute("totalRegNumber", totalRegNumber);
		model.addAttribute("totalRewardsIntegral", totalRewardsIntegral);
		model.addAttribute("totalRewardsMoney", totalRewardsMoney);
		return "/user/userinvitrewardsrecord/userInviteRewardsRecordList";
	}
	
	
	@RequestMapping(value="/queryRewardsRecordList", method = RequestMethod.POST)
	@ResponseBody
	public  PageFinder<UserInviteRewardsRecord> queryRewardsRecordList(Model model,HttpServletRequest request){
		Integer companyId = WebUtils.getCurrentCompanyId();
		model.addAttribute("companyId", companyId);
		UserInviteRewardsRecord search = new UserInviteRewardsRecord();
		String page = request.getParameter("page");
		if(page == null || "".equals(page))
			page = "1";
		search.setPage(Integer.parseInt(page));
		search.setPageSize(10);
		search.setCompanyId(companyId);
		CompanyInvitConfig invitConfigSearch = new CompanyInvitConfig();
		invitConfigSearch.setCompanyId(companyId);
		List<CompanyInvitConfig> list2 = companyInvitConfigServiceImpl.findCompanyInvitConfigByPage(invitConfigSearch);
		if(list2!=null && list2.size()>0){
			CompanyInvitConfig companyInvitConfig = list2.get(0);
			Integer twoInviteFlag = companyInvitConfig.getTwoInviteFlag();
			search.setTwoInviteFlag(twoInviteFlag);
		}
		String content = request.getParameter("content");
		if("".equals(content))
			content = null;
		String date = request.getParameter("date");
		if("1".equals(date)){
			search.setSearchDateType(1);
		}else if("2".equals(date)){
			search.setSearchDateType(2);
		}else if("3".equals(date)){
			search.setSearchDateType(3);
		}/*else{
			search.setCreateTime(null);
		}*/
		
		search.setSearchName(content);
		List<UserInviteRewardsRecord> list = userInviteRewardsRecordServiceImpl.findUserInviteRewardsRecordByPage(search);
		int totalNumberForPage = userInviteRewardsRecordServiceImpl.getTotalNumberForPage(search);
		if(list !=null ){
			PageFinder<UserInviteRewardsRecord> pageFinder = new PageFinder<UserInviteRewardsRecord>(search.getPage(), search.getPageSize(), totalNumberForPage,list);
			return pageFinder;
		}
		return null;
	}
	
	
	@RequestMapping(value="/exportExcelAll",method = RequestMethod.GET)
	public ModelAndView exportExcel(Model model,HttpServletRequest request) throws IOException{
		
		Integer companyId = WebUtils.getCurrentCompanyId();
		model.addAttribute("companyId", companyId);
		UserInviteRewardsRecord search = new UserInviteRewardsRecord();
		String page = request.getParameter("page");
		if(page == null || "".equals(page))
			page = "1";
		search.setPage(Integer.parseInt(page));
		search.setPageSize(10);
		search.setCompanyId(companyId);
		CompanyInvitConfig invitConfigSearch = new CompanyInvitConfig();
		invitConfigSearch.setCompanyId(companyId);
		List<CompanyInvitConfig> list2 = companyInvitConfigServiceImpl.findCompanyInvitConfigByPage(invitConfigSearch);
		if(list2!=null && list2.size()>0){
			CompanyInvitConfig companyInvitConfig = list2.get(0);
			Integer twoInviteFlag = companyInvitConfig.getTwoInviteFlag();
			search.setTwoInviteFlag(twoInviteFlag);
		}
		String content = request.getParameter("searchName");
		if("".equals(content))
			content = null;
		String date = request.getParameter("date");
		if("1".equals(date)){
			search.setSearchDateType(1);
		}else if("2".equals(date)){
			search.setSearchDateType(2);
		}else if("3".equals(date)){
			search.setSearchDateType(3);
		}/*else{
			search.setCreateTime(null);
		}*/
		
		search.setSearchName(content);
		DecimalFormat format = new DecimalFormat("#.##");
		List<UserInviteRewardsRecord> list = userInviteRewardsRecordServiceImpl.exprotWithoutPage(search);
		if(list != null && list.size()>0){
			for (UserInviteRewardsRecord userInviteRewardsRecord : list) {
				String mobile = userInviteRewardsRecord.getMobile();
				if(mobile != null && !mobile.equals("")){
					userInviteRewardsRecord.setUsername(mobile);
				}
				userInviteRewardsRecord.setTotalMoney(Double.parseDouble(format.format(userInviteRewardsRecord.getTotalMoney())));
			}
		}
		ViewFiles excel = new ViewFiles(); 
		Map<String,Object> map = new HashMap<String,Object>();  
		ExcelSheetEntity entity=ExcelSheetEntity.newInstance("邀请人:username,邀请码:inviteCode,邀请注册人数:inviteRegNumber,邀请消费人数:inviteConNumber,获得的代金券奖励金额:totalMoney,获得的积分奖励:totalIntegral",list);
		map.put("entity", entity);
		map.put("fileName", "邀请记录表.xls");
		return new ModelAndView(excel,map);
		
	}
	
	@RequestMapping(value="/toinviterewardsrecorddetail",method=RequestMethod.GET)
	@ResponseBody
	public UserInviteRewardsRecord toUsersInviteRewardsRecord(HttpServletRequest request,Model model){
 		String userId = request.getParameter("userId");
		Integer currentCompanyId = WebUtils.getCurrentCompanyId();
		UserInviteRewardsRecord search = new UserInviteRewardsRecord();
		search.setCompanyId(currentCompanyId);
		search.setUserId(Integer.parseInt(userId));
		model.addAttribute("userId", Integer.parseInt(userId));
		CompanyInvitConfig invitConfigSearch = new CompanyInvitConfig();
		invitConfigSearch.setCompanyId(currentCompanyId);
		List<CompanyInvitConfig> list2 = companyInvitConfigServiceImpl.findCompanyInvitConfigByPage(invitConfigSearch);
		if(list2!=null && list2.size()>0){
			CompanyInvitConfig companyInvitConfig = list2.get(0);
			Integer twoInviteFlag = companyInvitConfig.getTwoInviteFlag();
			search.setTwoInviteFlag(twoInviteFlag);
		}
		List<UserInviteRewardsRecord> rewardsRecordByPage = userInviteRewardsRecordServiceImpl.findUserInviteRewardsRecordByPage(search);
		if(rewardsRecordByPage !=null && rewardsRecordByPage.size()>0)
		return rewardsRecordByPage.get(0);
		return null;
	}
	
	@RequestMapping(value="/findDetailInviteRewardsRecord",method=RequestMethod.POST)
	@ResponseBody
	public PageFinder<UserInviteRewardsRecord> findDetailInviteRewardsRecord(Model model,HttpServletRequest request){
		Integer companyId = WebUtils.getCurrentCompanyId();
		String page = request.getParameter("page");
		UserInviteRewardsRecord search = new UserInviteRewardsRecord();
		search.setPage(Integer.parseInt(page));
		search.setPageSize(10);
		search.setCompanyId(companyId);
		String userId = request.getParameter("userId");
		search.setUserId(Integer.parseInt(userId));
		String yaoqingsel = request.getParameter("yaoqingsel");
		if("2".equals(yaoqingsel)){
			search.setMark("my_invite");
		}
		else if("3".equals(yaoqingsel))
		{
			search.setMark("two_invite");
		}
		String typesel = request.getParameter("typesel");
		if("2".equals(typesel)){
			search.setType("regist");
		}else if("3".equals(typesel)){
			search.setType("consume");
		}else{
			search.setType(null);
		}
		List<UserInviteRewardsRecord> list = userInviteRewardsRecordServiceImpl.findDetailRewardsRecordByParentId(search);
		if(list != null && list.size()>0){
			for (UserInviteRewardsRecord userInviteRewardsRecord : list) {
				if(userInviteRewardsRecord.getCid()==null && userInviteRewardsRecord.getCcid()==null){
					Integer userId2 = userInviteRewardsRecord.getParentId();
					UsersFront usersFront = userfrontservice.findUsersFrontById(userId2);
					if(usersFront!=null ){
							userInviteRewardsRecord.setParentUsername(usersFront.getUsername());	
							userInviteRewardsRecord.setParentMobile(usersFront.getMobile());
					}
					else{
						userInviteRewardsRecord.setParentUsername("");
					}
					UsersFront usersFront1 = userfrontservice.findUsersFrontById(userInviteRewardsRecord.getUserId());
					if(usersFront1!=null){
						userInviteRewardsRecord.setUsername(usersFront1.getUsername());
						userInviteRewardsRecord.setMobile(usersFront1.getMobile());
				}
					else{
					userInviteRewardsRecord.setUsername("");
					}
				}
				else if(userInviteRewardsRecord.getCid()!=null && userInviteRewardsRecord.getCcid()==null){
					Integer userId2 = userInviteRewardsRecord.getUserId();
					UsersFront usersFront = userfrontservice.findUsersFrontById(userId2);
					if(usersFront!=null ){
							userInviteRewardsRecord.setParentUsername(usersFront.getUsername());	
							userInviteRewardsRecord.setParentMobile(usersFront.getMobile());
					}
					else{
						userInviteRewardsRecord.setParentUsername("");
					}
					
					UsersFront usersFront1 = userfrontservice.findUsersFrontById(userInviteRewardsRecord.getCid());
					if(usersFront1!=null){
							userInviteRewardsRecord.setUsername(usersFront1.getUsername());
							userInviteRewardsRecord.setMobile(usersFront1.getMobile());
					}
					else{
						userInviteRewardsRecord.setUsername("");
					}
				}
				else if(userInviteRewardsRecord.getCid()!=null && userInviteRewardsRecord.getCcid()!=null){
					Integer userId3 = userInviteRewardsRecord.getCid();
					UsersFront usersFront = userfrontservice.findUsersFrontById(userId3);
					if(usersFront!=null ){
						
							userInviteRewardsRecord.setParentUsername(usersFront.getUsername());	
							userInviteRewardsRecord.setParentMobile(usersFront.getMobile());
					}
					else{
						userInviteRewardsRecord.setParentUsername("");
					}
					UsersFront usersFront2 = userfrontservice.findUsersFrontById(userInviteRewardsRecord.getCcid());
					if(usersFront2!=null){
							userInviteRewardsRecord.setUsername(usersFront2.getUsername());	
							userInviteRewardsRecord.setMobile(usersFront2.getMobile());
					}else{
						userInviteRewardsRecord.setUsername("");
					}
				}
			
			}
		}
		int totalCount = userInviteRewardsRecordServiceImpl.findDetailRewardsRecordCountByParentId(search);
		PageFinder<UserInviteRewardsRecord> pageFinder = new PageFinder<UserInviteRewardsRecord>(search.getPage(), search.getPageSize(), totalCount, list);
		return pageFinder;
	}
	
	@RequestMapping(value="/isEmptyInviteRewardsRecord" ,method=RequestMethod.GET)
	@ResponseBody
	public String isEmptyInviteRewardsRecord(Model model){
		Integer currentCompanyId = WebUtils.getCurrentCompanyId();
		UserInviteRewardsRecord search = new UserInviteRewardsRecord();
		search.setCompanyId(currentCompanyId);
		CompanyInvitConfig invitConfigSearch = new CompanyInvitConfig();
		invitConfigSearch.setCompanyId(currentCompanyId);
		List<CompanyInvitConfig> list2 = companyInvitConfigServiceImpl.findCompanyInvitConfigByPage(invitConfigSearch);
		if(list2!=null && list2.size()>0){
			CompanyInvitConfig companyInvitConfig = list2.get(0);
			Integer twoInviteFlag = companyInvitConfig.getTwoInviteFlag();
			search.setTwoInviteFlag(twoInviteFlag);
		}
		List<UserInviteRewardsRecord> list = userInviteRewardsRecordServiceImpl.queryForAll(search);
		if(list != null && list.size()>0)
			return "1";
		return "0";
	}
	
	
	
}
