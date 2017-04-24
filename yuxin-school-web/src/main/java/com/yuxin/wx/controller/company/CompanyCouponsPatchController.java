package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.yuxin.wx.api.classes.IClassPackageService;
import com.yuxin.wx.api.company.ICompanyCouponsLibService;
import com.yuxin.wx.api.company.ICompanyCouponsPatchService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyMessageHistoryService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.company.ICompanyStudentMessageService;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.company.impl.CompanyStudentMessageServiceImpl;
import com.yuxin.wx.model.classes.ClassPackage;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyCouponsLib;
import com.yuxin.wx.model.company.CompanyCouponsPatch;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyMessageHistory;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.model.user.UsersFrontMyCoupons;
import com.yuxin.wx.user.impl.UsersFrontServiceImpl;
import com.yuxin.wx.util.ClassPackageUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.utils.smsClient.interfacej.SmsClientSend;
import com.yuxin.wx.vo.company.CompanyCouponsPatchVo;

/**
 * Controller of CompanyCouponsPatch
 * @author chopin
 * @date 2016-6-20
 */
@Controller
@RequestMapping("/companyCouponsPatch")
public class CompanyCouponsPatchController {
	
	@Autowired
	private ICompanyCouponsPatchService companyCouponsPatchServiceImpl;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	@Autowired
	private ICompanyCouponsLibService companyCouponsLibServiceImpl;
	@Autowired
	private IUsersFrontService usersFrontServiceImpl;
	@Autowired
	private ICompanyMessageHistoryService companyMessageHistoryServiceImpl;
	@Autowired
	private ICompanyStudentMessageService companyStudentMessageServiceImpl;
	@Autowired
	private ISysConfigServiceService sysConfigServiceServiceImpl;
	@Autowired
	private IClassPackageService classPackageServiceImpl;
	
	/**
	 * 
	 * Class Name: CompanyCouponsPatchController.java
	 * @Description: 优惠券编辑页
	 * @author zhang.zx
	 * @date 2016年6月20日 下午6:05:51
	 * @modifier
	 * @modify-date 2016年6月20日 下午6:05:51
	 * @version 1.0
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/manage/{id}")
	public String manageCoupons(@PathVariable Integer id,Model model){
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyCouponsPatch couponsPatch=companyCouponsPatchServiceImpl.findCompanyCouponsPatchById(id);
		model.addAttribute("couponsPatch", couponsPatch);
		if(couponsPatch!=null && couponsPatch.getRangeItemPackageCategory() !=null){
			model.addAttribute("codes", ClassPackageUtil.getClassPackageUrl(companyId, couponsPatch.getRangeItemPackageCategory()));
			model.addAttribute("classPackageName", ClassPackageUtil.getClassPackageName(couponsPatch.getRangeItemCourse(), ClassPackageUtil.IDTYPE_COMMODITYID));
		}
		
		Company company = companyService.findCompanyById(companyId);
		model.addAttribute("company",company);
		SysConfigService sc=new SysConfigService();
		sc.setCompanyId(WebUtils.getCurrentCompanyId());
		sc.setGroupCode("SERVICE_MEMBER");
		//查询是否存在
		SysConfigService ser = sysConfigServiceServiceImpl.findExist(sc);
		if(null!=ser && null!=ser.getDelFlag() && ser.getDelFlag()==1){
			model.addAttribute("showMember", "show");
		}
		//课程包服务
		SysConfigService classPackage = WebUtils.getConfigService("SERVICE_CLASS_PACKAGE");
		model.addAttribute("classPackageService",classPackage==null?0:classPackage.getDelFlag());
		return "system/manageCoupon";
	}
	
	/**
	 * 
	 * Class Name: CompanyCouponsPatchController.java
	 * @Description:校验优惠码
	 * @author zhang.zx
	 * @date 2016年6月20日 下午7:44:47
	 * @modifier
	 * @modify-date 2016年6月20日 下午7:44:47
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkIsExist")
	public String checkCouponsIsExist(CompanyCouponsPatch search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		if(null!=search && null!=search.getPromoCodePrefix()){
			search.setPromoCodePrefix(search.getPromoCodePrefix().toUpperCase());
		}
		List<CompanyCouponsPatch> arr=companyCouponsPatchServiceImpl.queryCouponsByCondition(search);
		if(null!=arr && arr.size()>0){
			return "exist";
		}
		return "noexist";
	}
	
	/**
	 * 
	 * Class Name: CompanyCouponsPatchController.java
	 * @Description: 添加批次
	 * @author zhang.zx
	 * @date 2016年6月21日 上午10:06:19
	 * @modifier
	 * @modify-date 2016年6月21日 上午10:06:19
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addCouponsPatch")
	public JSONObject addCouponsPatch(CompanyCouponsPatch search,String type,HttpServletRequest request){
		JSONObject json=new JSONObject();
		String flag="success";
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		Integer userId=WebUtils.getCurrentUserId(request);
		if(null!=search.getPromoCodePrefix()){
			search.setPromoCodePrefix(search.getPromoCodePrefix().toUpperCase());
		}
		if(null!=search.getMemberList() && !"".equals(search.getMemberList())){
			search.setMemberList(search.getMemberList().substring(0, search.getMemberList().length()-1));
		}
		if(null!=search.getUserList() && !"".equals(search.getUserList())){
			search.setUserList(search.getUserList().substring(0, search.getUserList().length()-1));
		}
		if(null!=search && null!=search.getId()){
			CompanyCouponsPatch con=companyCouponsPatchServiceImpl.findCompanyCouponsPatchById(search.getId());
			if(null!=search.getName() && null!=con && !search.getName().equals(con.getName())){
				CompanyCouponsPatch se=new CompanyCouponsPatch();
				se.setCompanyId(WebUtils.getCurrentCompanyId());
				se.setName(search.getName());
				se.setDelFlag(0);
				List<CompanyCouponsPatch> arr=companyCouponsPatchServiceImpl.queryCouponseBycondtion(se);
				if(null!=arr && arr.size()>0){
					flag="优惠名称已存在";
					json.put("flag", flag);
					return json;
				}
			}
			companyCouponsPatchServiceImpl.update(search);
		}else{
			search.setUsedNum(0);
			search.setRemainNum(search.getTotalNum());
			search.setStatus("0");
			search.setDelFlag(0);
			CompanyCouponsPatch se=new CompanyCouponsPatch();
			se.setCompanyId(WebUtils.getCurrentCompanyId());
			se.setName(search.getName());
			se.setDelFlag(0);
			List<CompanyCouponsPatch> arr=companyCouponsPatchServiceImpl.queryCouponseBycondtion(se);
			if(null!=arr && arr.size()>0){
				flag="优惠名称已存在";
				json.put("flag", flag);
				return json;
			}
			companyCouponsPatchServiceImpl.insert(search);
		}
		flag=companyCouponsPatchServiceImpl.manageCouponsPatch(search,type,userId);
		if(flag=="success" && "saveAndSend".equals(type) && search.getIssueWay()==0){
			String fla=sendCouponsPatch(request, search.getId().toString());
			if(!"success".equals(fla)){
				if("userEmpty".equals(flag)){
					if("COUPON_OBJECT_MEMBER".equals(search.getForCrowd())){//会员
						flag="优惠码生成失败,该级别会员人数为0";
					}else if("COUPON_OBJECT_STUDENT".equals(search.getForCrowd())){
						flag="优惠码生成失败,没有人购买过课程";
					}else{
						flag="优惠码生成失败";
					}
				}else{
					flag="发放优惠券失败";
				}
			}
		}else{
			if(!"success".equals(flag)){
				if("COUPON_OBJECT_MEMBER".equals(search.getForCrowd())){//会员
					flag="优惠码生成失败,该级别会员人数为0";
				}else if("COUPON_OBJECT_STUDENT".equals(search.getForCrowd())){
					flag="优惠码生成失败,没有人购买过课程";
				}else{
					flag="发放优惠券失败";
				}
			}
		}
		json.put("patchId", search.getId());
		json.put("flag", flag);
		return json;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyCouponsPatch search){
		if (search == null) {
			search = new CompanyCouponsPatch();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyCouponsPatchServiceImpl.findCompanyCouponsPatchByPage(search));
		return "companyCouponsPatch/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyCouponsPatch CompanyCouponsPatch) {
		companyCouponsPatchServiceImpl.insert(CompanyCouponsPatch);
		return "redirect:/companyCouponsPatch";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyCouponsPatch CompanyCouponsPatch) {
		companyCouponsPatchServiceImpl.update(CompanyCouponsPatch);
		return "redirect:/companyCouponsPatch";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyCouponsPatchServiceImpl.deleteCompanyCouponsPatchById(id);
		return "redirect:/companyCouponsPatch";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyCouponsPatch getJson(Model model, @PathVariable Integer id){
		return companyCouponsPatchServiceImpl.findCompanyCouponsPatchById(id);
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
	 * Class Name: CompanyCouponsPatchController.java
	 * @Description: 查询优惠批次列表
	 * @author dongshuai
	 * @date 2016年6月21日 上午11:50:25
	 * @modifier
	 * @modify-date 2016年6月21日 上午11:50:25
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryCompanyCouponsPatchList", method = RequestMethod.POST)
	public PageFinder<CompanyCouponsPatchVo> queryCompanyCouponsPatchList(CompanyCouponsPatchVo search){
		search.setPageSize(10);
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		PageFinder<CompanyCouponsPatchVo> pageFinder=companyCouponsPatchServiceImpl.queryCouponsPatchListByCompanyId(search);
		return pageFinder;
	}
	
	/**
	 * 
	 * Class Name: CompanyCouponsPatchController.java
	 * @Description: 查询优惠信息
	 * @author dongshuai
	 * @date 2016年6月21日 下午6:17:57
	 * @modifier
	 * @modify-date 2016年6月21日 下午6:17:57
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryCouponsPatchById", method = RequestMethod.POST)
	public CompanyCouponsPatchVo queryCouponsPatchById(String id){
		return companyCouponsPatchServiceImpl.queryCouponsPatchById(Integer.parseInt(id));
	}
	
	/**
	 * 
	 * Class Name: CompanyCouponsPatchController.java
	 * @Description: 删除优惠码
	 * @author dongshuai
	 * @date 2016年6月22日 下午4:16:26
	 * @modifier
	 * @modify-date 2016年6月22日 下午4:16:26
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delCouponsPatch", method = RequestMethod.POST)
	public boolean delCouponsPatch(String id){
		CompanyCouponsPatch ccp = companyCouponsPatchServiceImpl.findCompanyCouponsPatchById(Integer.parseInt(id));
		if(null!=ccp){
			CompanyCouponsLib ccl=new CompanyCouponsLib();
			ccl.setPatchId(Integer.parseInt(id));
			List<CompanyCouponsLib> cclList = companyCouponsLibServiceImpl.queryLibsListByPatchIdExport(ccl);
			ccp.setDelFlag(1);
			companyCouponsPatchServiceImpl.update(ccp);
			if(cclList.size()>0){
				for (CompanyCouponsLib companyCouponsLib : cclList) {
					companyCouponsLib.setDelFlag(1);
					companyCouponsLibServiceImpl.update(companyCouponsLib);
				}
			}
		}else{
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * Class Name: CompanyCouponsPatchController.java
	 * @Description: 发送优惠码
	 * @author dongshuai
	 * @date 2016年6月22日 下午4:55:49
	 * @modifier
	 * @modify-date 2016年6月22日 下午4:55:49
	 * @version 1.0
	 * @param id
	 * @return
	 */
	//sendError:发送失败 userEmpty：用户列表为空 objError：指定范围为空 success：成功 sendMsgError：发送信息失败  sendSmsError ：发送短信失败 sendMsgAndSmsError：发送站内信短信
	@ResponseBody
	@RequestMapping(value="/sendCouponsPatch", method = RequestMethod.POST)
	public String sendCouponsPatch(HttpServletRequest request,String id){
		CompanyCouponsPatch ccp=companyCouponsPatchServiceImpl.findCompanyCouponsPatchById(Integer.parseInt(id));
		CompanyCouponsLib ccl=new CompanyCouponsLib();
		ccl.setPatchId(Integer.parseInt(id));
		if(null!=ccp.getNoticWay() && ccp.getNoticWay()!=0){
			CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
			CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
			if(((cms.getMessageTotal() + cms.getGiveMessage()) - css.getMessageSend()) <= 0){
				return "msgCountNotEnough";
			}
		}
		if(null!=ccp && null!=ccp.getForCrowd()){
			String flag = "error";
			List<CompanyCouponsLib> cclList = companyCouponsLibServiceImpl.queryLibsListByPatchIdExport(ccl);
			if(null == cclList || cclList.size() <= 0){
				//生成优惠码
				flag = companyCouponsPatchServiceImpl.manageCouponsPatch(ccp,"saveAndSend",WebUtils.getCurrentUserId(request));
			}else{
				flag = "success";
			}
			
			if("success".equals(flag)){
				cclList = companyCouponsLibServiceImpl.queryLibsListByPatchIdExport(ccl);
				if("COUPON_OBJECT_ALL".equals(ccp.getForCrowd())){//所有用户
					List<UsersFront> usersList=companyCouponsPatchServiceImpl.queryAllUsers(WebUtils.getCurrentCompanyId());
					if(usersList.size()>0){
						String[] userIds = new String[usersList.size()];
						List<String> userslist=new ArrayList<String>();
						Integer count=0;
						for (UsersFront user : usersList) {
							userslist.add(user.getId().toString());
							userIds[count]=user.getId().toString();
							count++;
						}
						boolean isOk=companyCouponsPatchServiceImpl.sendCouponsPatch(ccp,cclList,userslist);
						if(isOk){
							boolean isSendMsgOk=true;
							boolean isSendSmsOk=true;
							//发送消息
							if(null!=ccp.getNoticWay() && ccp.getNoticWay()!=1){
								isSendMsgOk=companyCouponsPatchServiceImpl.sendCouponsMessage(ccp,userIds,WebUtils.getCurrentUserId(request),WebUtils.getCurrentSchoolId(),WebUtils.getCurrentCompanyId());
							}
							//发短信
							if(null!=ccp.getNoticWay() && ccp.getNoticWay()!=0){
								for (UsersFront user : usersList) {
									String[] r = companyCouponsPatchServiceImpl.sendCouponsMobileMessage(ccp,user.getId().toString(),WebUtils.getCurrentUserId(request),WebUtils.getCurrentSchoolId(),WebUtils.getCurrentCompanyId()).split("#####");
									//SmsClientSend.sendSmsTwo(request,user.getMobile().trim(),contents+ "【在线网校】",user.getId(),"sys-notice");
									isSendSmsOk = sendSMS(user,r,request);
								}
							}
							if(!isSendMsgOk && !isSendSmsOk){
								return "sendMsgAndSmsError";
							}
							if(!isSendMsgOk){
								return "sendMsgError";
							}
							if(!isSendSmsOk){
								return "sendSmsError";
							}
						}else{
							return "sendError";
						}
					}else{
						return "userEmpty";
					}
				}
				if("COUPON_OBJECT_MEMBER".equals(ccp.getForCrowd())){//会员
					String[] memberIds=ccp.getMemberList().split(",");
					List<UsersFront> usersList=new ArrayList<UsersFront>();
					for (String memberId : memberIds) {
						Map<String,String> map=new HashMap<String,String>();
						map.put("companyId", WebUtils.getCurrentCompanyId().toString());
						map.put("memberId", memberId);
						List<UsersFront> list = companyCouponsPatchServiceImpl.queryMemberUsers(map);
						usersList.addAll(list);
					}
					Integer count=0;
					if(usersList.size()>0){
						String[] userIds = new String[usersList.size()];;
						List<String> userslist=new ArrayList<String>();
						for (UsersFront user : usersList) {
							userslist.add(user.getId().toString());
							userIds[count]=user.getId().toString();
							count++;
						}
						boolean isOk=companyCouponsPatchServiceImpl.sendCouponsPatch(ccp,cclList,userslist);
						if(isOk){
							boolean isSendMsgOk=true;
							boolean isSendSmsOk=true;
							//发送消息
							if(null!=ccp.getNoticWay() && ccp.getNoticWay()!=1){
								isSendMsgOk=companyCouponsPatchServiceImpl.sendCouponsMessage(ccp,userIds,WebUtils.getCurrentUserId(request),WebUtils.getCurrentSchoolId(),WebUtils.getCurrentCompanyId());
							}
							//发短信
							if(null!=ccp.getNoticWay() && ccp.getNoticWay()!=0){
								for (UsersFront user : usersList) {
									String[] r = companyCouponsPatchServiceImpl.sendCouponsMobileMessage(ccp,user.getId().toString(),WebUtils.getCurrentUserId(request),WebUtils.getCurrentSchoolId(),WebUtils.getCurrentCompanyId()).split("#####");
									//SmsClientSend.sendSmsTwo(request,user.getMobile(), contents,user.getId(),"sys-notice");
									isSendSmsOk = sendSMS(user,r,request);
								}
							}
							if(!isSendMsgOk && !isSendSmsOk){
								return "sendMsgAndSmsError";
							}
							if(!isSendMsgOk){
								return "sendMsgError";
							}
							if(!isSendSmsOk){
								return "sendSmsError";
							}
						}else{
							return "sendError";
						}
					}else{
						return "userEmpty";
					}
				}
				if("COUPON_OBJECT_SOMEONE".equals(ccp.getForCrowd())){//指定用户
					if(null!=ccp.getUserList()){
						String[] userIds=ccp.getUserList().split(",");
						boolean isOk=companyCouponsPatchServiceImpl.sendCouponsPatch(ccp,cclList,Arrays.asList(userIds));
						if(isOk){
							boolean isSendMsgOk=true;
							boolean isSendSmsOk=true;
							//发送消息
							if(null!=ccp.getNoticWay() && ccp.getNoticWay()!=1){
								isSendMsgOk = companyCouponsPatchServiceImpl.sendCouponsMessage(ccp,userIds,WebUtils.getCurrentUserId(request),WebUtils.getCurrentSchoolId(),WebUtils.getCurrentCompanyId());
								
							}
							//发短信
							if(null!=ccp.getNoticWay() && ccp.getNoticWay()!=0){
								
								for (String userid : userIds) {
									String[] r= companyCouponsPatchServiceImpl.sendCouponsMobileMessage(ccp,userid,WebUtils.getCurrentUserId(request),WebUtils.getCurrentSchoolId(),WebUtils.getCurrentCompanyId()).split("#####");
									UsersFront user = usersFrontServiceImpl.findUsersFrontById(Integer.parseInt(userid));
									//SmsClientSend.sendSmsTwo(request,user.getMobile(), contents,user.getId(),"sys-notice");
									isSendSmsOk = sendSMS(user,r,request);
								}
							}
							if(!isSendMsgOk && !isSendSmsOk){
								return "sendMsgAndSmsError";
							}
							if(!isSendMsgOk){
								return "sendMsgError";
							}
							if(!isSendSmsOk){
								return "sendSmsError";
							}
						}else{
							return "sendError";
						}
					}else{
						return "userEmpty";
					}
				}
				if("COUPON_OBJECT_STUDENT".equals(ccp.getForCrowd())){//购买过课程的学员
					//List<UsersFront> usersList=companyCouponsPatchServiceImpl.queryAlreadyBuyClassUsers(WebUtils.getCurrentCompanyId());
					UsersFront u=new UsersFront();
					u.setCompanyId(WebUtils.getCurrentCompanyId());
					u.setVipFlag(1);
					u.setStatus(1);
					List<UsersFront> usersList=usersFrontServiceImpl.findConponsUsersByCondition(u);
					if(usersList.size()>0){
						String[] userIds = new String[usersList.size()];
						List<String> userslist=new ArrayList<String>();
						Integer count=0;
						for (UsersFront user : usersList) {
							userslist.add(user.getId().toString());
							userIds[count]=user.getId().toString();
							count++;
						}
						boolean isOk=companyCouponsPatchServiceImpl.sendCouponsPatch(ccp,cclList,userslist);
						if(isOk){
							boolean isSendMsgOk=true;
							boolean isSendSmsOk=true;
							//发送消息
							if(null!=ccp.getNoticWay() && ccp.getNoticWay()!=1){
								isSendMsgOk = companyCouponsPatchServiceImpl.sendCouponsMessage(ccp,userIds,WebUtils.getCurrentUserId(request),WebUtils.getCurrentSchoolId(),WebUtils.getCurrentCompanyId());
							}
							//发短信
							if(null!=ccp.getNoticWay() && ccp.getNoticWay()!=0){
								for (UsersFront user : usersList) {
									String[] r=companyCouponsPatchServiceImpl.sendCouponsMobileMessage(ccp,user.getId().toString(),WebUtils.getCurrentUserId(request),WebUtils.getCurrentSchoolId(),WebUtils.getCurrentCompanyId()).split("#####");;
									//SmsClientSend.sendSmsTwo(request,user.getMobile(), contents,user.getId(),"sys-notice");
									isSendSmsOk = sendSMS(user,r,request);
								}
							}
							if(!isSendMsgOk && !isSendSmsOk){
								return "sendMsgAndSmsError";
							}
							if(!isSendMsgOk){
								return "sendMsgError";
							}
							if(!isSendSmsOk){
								return "sendSmsError";
							}
						}else{
							return "sendError";
						}
					}else{
						return "userEmpty";
					}
				}
			}else{
				return "createError";
			}
		}else{
			return "objError";
		}
		return "success";
	}
	
	/**
	 * 
	 * Class Name: CompanyCouponsPatchController.java
	 * @Description: 发送短信
	 * @author dongshuai
	 * @date 2016年6月24日 下午5:25:41
	 * @modifier
	 * @modify-date 2016年6月24日 下午5:25:41
	 * @version 1.0
	 * @param user
	 * @param r
	 * @param request
	 */
	public boolean sendSMS(UsersFront user,String[] r,HttpServletRequest request){
		if(null!=user && null!=user.getMobile() && !"".equals(user.getMobile())){//2016/7/7 手机号为空则不发送短信
			String contents=r[0];
			String companyStudentMessageId=r[1];
			Integer costNum=contents.length() % 70 == 0 ? contents.length() / 70 : (contents.length() / 70) +1;
			CompanyStudentMessage csm = companyStudentMessageServiceImpl.findCompanyStudentMessageById(Integer.parseInt(companyStudentMessageId));
			String result = SmsClientSend.sendSmsTwo(request,user.getMobile(),contents,user.getId(),"sys-notice");
			String status = result.substring(result.indexOf("<returnstatus>"),result.indexOf("</returnstatus>"));
			status = status.substring(status.indexOf(">") + 1);
			String message = result.substring(result.indexOf("<message>"),result.indexOf("</message>"));
			message = message.substring(message.indexOf(">") + 1);
			if(message.equals("ok")){
				message = "发送成功";
			}
			CompanyMessageHistory cmh = new CompanyMessageHistory();
			cmh.setReceiverUserId(user.getId().toString());
			cmh.setReceiverMobile(user.getMobile().trim());
			cmh.setContent(contents);
			cmh.setSendTime(new Date());
			if(status.equals("Success")){
				cmh.setSendStatus(1);
			}else{
				cmh.setSendStatus(0);
			}
			cmh.setSendResult(message);
			cmh.setBusinessType("BUSINESS_STUDENT_MESSAGE");
			cmh.setCompanyId(WebUtils.getCurrentCompanyId());
			cmh.setSchoolId(WebUtils.getCurrentSchoolId());
			cmh.setCostNum(costNum);
			cmh.setMessageId(Integer.parseInt(companyStudentMessageId));
			companyMessageHistoryServiceImpl.insert(cmh);
			
			//发送条数
			csm.setMessageCost(costNum);
			//查询失败人数
			CompanyMessageHistory cmh1 = new CompanyMessageHistory();
			cmh1.setBusinessType("BUSINESS_STUDENT_MESSAGE");
			cmh1.setCompanyId(WebUtils.getCurrentCompanyId());
			cmh1.setSchoolId(WebUtils.getCurrentSchoolId());
			cmh1.setMessageId(csm.getId());
			cmh1.setSendStatus(0);
			Integer failCount = companyMessageHistoryServiceImpl.findByUserCount(cmh1);
			if(failCount == null){
				failCount = 0;
			}
			csm.setFailNum(failCount);
			//查询全部人数
			CompanyMessageHistory cmh2 = new CompanyMessageHistory();
			cmh2.setBusinessType("BUSINESS_STUDENT_MESSAGE");
			cmh2.setCompanyId(WebUtils.getCurrentCompanyId());
			cmh2.setSchoolId(WebUtils.getCurrentSchoolId());
			cmh2.setMessageId(csm.getId());
			Integer sendCount = companyMessageHistoryServiceImpl.findByUserCount(cmh2);
			csm.setSendNum(sendCount);
			csm.setMessageStatus("STUDENT_MESSAGE_FINISH");
			companyStudentMessageServiceImpl.update(csm);
			
			return "发送成功".equals(message)?true:false;
		}
		return true;
	}
	
	/**
	 * 
	 * Class Name: CompanyCouponsPatchController.java
	 * @Description: 校验批次名称
	 * @author zhang.zx
	 * @date 2016年6月28日 下午6:35:02
	 * @modifier
	 * @modify-date 2016年6月28日 下午6:35:02
	 * @version 1.0
	 * @param se
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkPatch_name")
	public boolean checkPatchName(CompanyCouponsPatch se){
		se.setCompanyId(WebUtils.getCurrentCompanyId());
		se.setDelFlag(0);
		List<CompanyCouponsPatch> arr=companyCouponsPatchServiceImpl.queryCouponseBycondtion(se);
		if(null!=arr && arr.size()>0){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * Class Name: CompanyCouponsPatchController.java
	 * @Description: 根据code查询课程包
	 * @author dongshuai
	 * @date 2017年4月7日 下午4:14:48
	 * @modifier
	 * @modify-date 2017年4月7日 下午4:14:48
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryClassPackages", method = RequestMethod.POST)
	public List<ClassPackage> queryClassPackages(ClassPackage search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		return classPackageServiceImpl.queryCommodityByClassPackage(search);
	}
}
