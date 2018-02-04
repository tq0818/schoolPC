package com.yuxin.wx.controller.pay;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yuxin.wx.common.*;
import com.yuxin.wx.utils.EntityUtil;
import com.yuxin.wx.vo.student.StudentVo;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.yuxin.wx.api.classes.IClassModuleNoOnsaleService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassPackageRelationService;
import com.yuxin.wx.api.classes.IClassPackageService;
import com.yuxin.wx.api.classes.IClassTypeModuleRelationService;
import com.yuxin.wx.api.classes.IClassTypeRemoteRelationService;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.api.commodity.ICommodityService;
import com.yuxin.wx.api.company.ICompanyCouponsLibService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyIntegralConfigService;
import com.yuxin.wx.api.company.ICompanyMemberConfigService;
import com.yuxin.wx.api.company.ICompanyMemberLevelConfigService;
import com.yuxin.wx.api.company.ICompanyStudentMessageService;
import com.yuxin.wx.api.course.ICoursePotocolUserRelationService;
import com.yuxin.wx.api.course.ICourseProtocolConfigService;
import com.yuxin.wx.api.pay.IPayOrderService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.student.IStudentPaySlaveService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysLogCouponUseService;
import com.yuxin.wx.api.user.IUserIntegralFlowService;
import com.yuxin.wx.api.user.IUsersFrontMyCouponsService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.model.classes.ClassModuleNoOnsale;
import com.yuxin.wx.model.classes.ClassPackage;
import com.yuxin.wx.model.classes.ClassPackageRelation;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeRemoteRelation;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.company.CompanyCouponsLib;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyIntegralConfig;
import com.yuxin.wx.model.company.CompanyMemberConfig;
import com.yuxin.wx.model.company.CompanyMemberLevelConfig;
import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.course.CoursePotocolUserRelation;
import com.yuxin.wx.model.course.CourseProtocolConfig;
import com.yuxin.wx.model.pay.PayOrder;
import com.yuxin.wx.model.pay.PayOrderHistory;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.model.system.SysLogCouponUse;
import com.yuxin.wx.model.user.UserIntegralFlow;
import com.yuxin.wx.model.user.UserMessage;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.model.user.UsersFrontMyCoupons;
import com.yuxin.wx.util.DateUtil;
import com.yuxin.wx.utils.InviteRecordUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassTypeModuleRelationVo;
import com.yuxin.wx.vo.course.OrderManage;
import com.yuxin.wx.vo.student.PayOrderVo;
import com.yuxin.wx.vo.user.UsersFrontVo;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

/**
 * 订单
 *
 * @author chopin
 * @date 2015-3-12
 */
@Controller
@RequestMapping("/payOrder")
public class PayOrderController {

    @Autowired
    private IPayOrderService payOrderServiceImpl;
    @Autowired

    private ISysLogCouponUseService sysLogCouponUseServiceImpl;
    @Autowired
    private ICommodityService commodityServiceImpl;
    @Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;
    @Autowired
    private IClassPackageRelationService classPackageRelationServiceImpl;
    @Autowired
    private IClassTypeService classTypeServiceImpl;
    /** 生成主订单用到的东西 */
    @Autowired
    private IStudentService studentServiceImpl;
    @Autowired
    private IStudentPayMasterService studentPayMasterServiceImpl;
    @Autowired
    private IClassModuleService classModuleServiceImpl;
    @Autowired
    private IUsersFrontService usersFrontServiceImpl;
    @Autowired
    private IClassTypeModuleRelationService classTypeModuleRelationServiceImpl;
    @Autowired
    private ICommodityProductRealtionService commodityProductRealtionServiceImpl;
    @Autowired
    private IStudentPaySlaveService studentPaySlaveServiceImpl;
    @Autowired
    private IClassTypeRemoteRelationService classTypeRemoteRelationServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;
    @Autowired
    private ICompanyStudentMessageService companyStudentMessageServiceImpl;

    @Autowired
    private IClassModuleNoOnsaleService classModuleNoOnsaleServiceImpl;

    @Autowired
    private IClassPackageService classPackageServiceImpl;

    @Autowired
    private ICompanyCouponsLibService companyCouponsLibServiceImpl;

    @Autowired
    private IUsersFrontMyCouponsService usersFrontMyCouponsServiceImpl;

    @Autowired
    private ICompanyMemberLevelConfigService companyMemberLevelConfigServiceImpl;

    @Autowired
    private ICompanyIntegralConfigService companyIntegralConfigServiceImpl;

    @Autowired
    private IUserIntegralFlowService userIntegralFlowServiceImpl;

    @Autowired
    private ICompanyMemberConfigService companyMemberConfigServiceImpl;
    @Autowired
    private ICourseProtocolConfigService courseProtocolConfigServiceImpl;
    @Autowired
    private ICoursePotocolUserRelationService coursePotocolUserRelationServiceImpl;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Log log_pay = LogFactory.getLog("pay");

    @RequestMapping(value = "index")
    public String toIndex(Model model) {
        List<SysConfigItem> itemOnes = this.sysConfigItemServiceImpl.findSysConfigItemByPid("1", null, WebUtils.getCurrentCompanyId(),
                WebUtils.getCurrentSchoolId());
        if (itemOnes != null && !itemOnes.isEmpty()) {
            List<SysConfigItem> itemSeconds = this.sysConfigItemServiceImpl.findSysConfigItemByPid("2", itemOnes.get(0).getId(), WebUtils.getCurrentCompanyId(),
                    WebUtils.getCurrentSchoolId());
            model.addAttribute("itemOnes", itemOnes);
            model.addAttribute("itemSeconds", itemSeconds);

            ClassType classType = new ClassType();
            classType.setCompanyId(WebUtils.getCurrentCompanyId());
            List<ClassType> list = this.classTypeServiceImpl.findByItem(WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId(), itemOnes.get(0).getId(),
                    null);
            model.addAttribute("classTypes", list);
        }
        return "/student/order/main";
    }

    /**
     *
     * Class Name: PayOrderController.java
     *
     * @Description: 检查用户积分是否够支付订单的
     * @author yuchanglong
     * @date 2016年6月16日 上午11:50:27
     * @version 1.0
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkIntegral")
    public JSONObject checkIntegral(Integer orderId) {
        JSONObject json = new JSONObject();
        PayOrder order = this.payOrderServiceImpl.findPayOrderById(orderId);
        if (order != null) {
            Integer orderIntegral = order.getIntegralUsed();
            if (orderIntegral != null && orderIntegral > 0) {
                this.log_pay.info("msg：订单使用了积分");
                UsersFront userfont = this.usersFrontServiceImpl.findUsersFrontById(order.getUserId());
                Integer userIntegral = userfont.getIntegralRemaining();
                if (userIntegral == null) {
                    userIntegral = 0;
                }
                if (userIntegral != null && userIntegral > 0 && userIntegral >= orderIntegral) {
                    json.put("pass", true);
                } else {
                    Integer diffIntegral = orderIntegral - userIntegral;
                    json.put("pass", false);
                    json.put("msg", "该学员积分不足，请提示学员充值积分,差" + diffIntegral + "积分");
                }
            } else {
                this.log_pay.info("msg：订单未使用积分");
                json.put("pass", true);
            }
            String coupons = order.getCouponCode();
            if (coupons != null) {
                Integer fh = coupons.indexOf(",");
                Boolean patchBoolean = false;
                UsersFrontMyCoupons search1 = new UsersFrontMyCoupons();
                search1.setUserId(order.getUserId());
                List<UsersFrontMyCoupons> muCoupons = this.usersFrontMyCouponsServiceImpl.findUsersFrontMyCouponsNoPage(search1);
                if (fh > 0) {
                    this.log_pay.info("msg：使用了多张优惠券");
                    String[] couponCodesList = coupons.split(",");
                    for (String cou : couponCodesList) {
                        CompanyCouponsLib ccl = this.companyCouponsLibServiceImpl.findOneByCode(cou);
                        if (ccl != null) {
                            Integer cclPatchId = ccl.getPatchId();
                            for (UsersFrontMyCoupons myCou : muCoupons) {
                                CompanyCouponsLib cc = this.companyCouponsLibServiceImpl.findOneByCode(myCou.getCouponsCode());
                                if (cc.getStatus().equals(1)) {
                                    Integer patchId = cc.getPatchId();
                                    if (patchId.equals(cclPatchId)) {
                                        patchBoolean = true;
                                    }
                                }
                            }
                        } else {
                            patchBoolean = true;
                        }
                    }
                } else {
                    this.log_pay.info("msg：使用了一张优惠券");
                    CompanyCouponsLib ccl = this.companyCouponsLibServiceImpl.findOneByCode(coupons);
                    if (ccl != null) {
                        Integer cclPatchId = ccl.getPatchId();
                        for (UsersFrontMyCoupons myCou : muCoupons) {
                            CompanyCouponsLib cc = this.companyCouponsLibServiceImpl.findOneByCode(myCou.getCouponsCode());
                            if (cc.getStatus().equals(1)) {
                                Integer patchId = cc.getPatchId();
                                if (patchId.equals(cclPatchId)) {
                                    patchBoolean = true;
                                }
                            }
                        }
                    } else {
                        patchBoolean = true;
                    }
                }
                if (patchBoolean) {
                    json.put("pass", false);
                    json.put("msg", "该订单使用的优惠券已不满足条件，请重新下单");
                }
            } else {
                this.log_pay.info("msg：订单未使用优惠券");
                json.put("pass", true);
            }

        }
        return json;
    }

    @RequestMapping(value = "toOrder")
    public String toOrder(HttpServletRequest reqeust, Model model) {
        // 学习卡服务
        SysConfigService service = WebUtils.getConfigService("SERVICE_STUDYCARD");
        model.addAttribute("stydycardservice", service != null ? service.getDelFlag() : 0);
        return "system/allOrder";
    }

    /**
     * 查询总订单
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryAllOrder")
    public String selOrder(Model model, HttpServletRequest request) {
        String isArea = WebUtils.getCurrentIsArea();
        Integer companyId = WebUtils.getCurrentCompanyId();
        Map<String,Object>map = new HashMap<String,Object>();
        PayOrder payOrder = new PayOrder();
        //2018-2-2 修改，经询问,数校的订单也是查询出数校产生的订单信息
//        if(!"0".equals(isArea)){
//            map.put("companyId",companyId);
//        }
        map.put("companyId",companyId);
        map.put("orderNum",request.getParameter("orderNum"));
        map.put("inpstart",request.getParameter("inpstart"));
        map.put("inpend",request.getParameter("inpend"));
        String payMethod=request.getParameter("payMethod");
       if(null != payMethod  && !"".equals(payMethod) ){
            if(payMethod.equals("PAY_TYPE_WX_PERSON")){
                payMethod="WX";
            }
            if(payMethod.equals("PAY_TYPE_ZFB")){
                payMethod="ZFB";
            }
        }
        map.put("payMethod",payMethod);
        map.put("firstPrice",request.getParameter("firstPrice"));
        map.put("secondPrice",request.getParameter("secondPrice"));
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        map.put("pageSize",pageSize);
        payOrder.setPage(page);
        payOrder.setPageSize(pageSize);
        map.put("page",payOrder.getFirstIndex());
        map.put("payStates",request.getParameter("payStates"));
        // 查询 订单 集合
        List<PayOrder> cpoList = this.payOrderServiceImpl.findPayOrderByParams(map);
        // 总数
        Integer count = this.payOrderServiceImpl.findCountByParams(map);
        // 分页
        PageFinder<PayOrder> payPage = new PageFinder<PayOrder>(page, pageSize, count, cpoList);

        model.addAttribute("payPage", payPage);
        model.addAttribute("payStates",request.getParameter("payStates"));
        return "system/orderDetail";
    }
    /**
     * 查询分校订单
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryAllSchoolOrder")
    public String queryAllSchoolOrder(Model model, HttpServletRequest request) {
    	/*String isArea = WebUtils.getCurrentIsArea();*/
    	String companyId = request.getParameter("companyId");
    	Map<String,Object>map = new HashMap<String,Object>();
    	PayOrder payOrder = new PayOrder();
    	/*if(!"0".equals(isArea)){*/
    		map.put("companyId",companyId);
    	//}
    	map.put("orderNum",request.getParameter("orderNum"));
    	map.put("inpstart",request.getParameter("inpstart"));
    	map.put("inpend",request.getParameter("inpend"));
    	String payMethod=request.getParameter("payMethod");
    	if(null != payMethod  && !"".equals(payMethod) ){
    		if(payMethod.equals("PAY_TYPE_WX_PERSON")){
    			payMethod="WX";
    		}
    		if(payMethod.equals("PAY_TYPE_ZFB")){
    			payMethod="ZFB";
    		}
    	}
    	map.put("payMethod",payMethod);
    	map.put("firstPrice",request.getParameter("firstPrice"));
    	map.put("secondPrice",request.getParameter("secondPrice"));
    	Integer page = Integer.parseInt(request.getParameter("page"));
    	Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
    	map.put("pageSize",pageSize);
    	payOrder.setPage(page);
    	payOrder.setPageSize(pageSize);
    	map.put("page",payOrder.getFirstIndex());
    	map.put("payStates",request.getParameter("payStates"));
    	// 查询 订单 集合
    	List<PayOrder> cpoList = this.payOrderServiceImpl.findPayOrderByParams(map);
    	// 总数
    	Integer count = this.payOrderServiceImpl.findCountByParams(map);
    	// 分页
    	PageFinder<PayOrder> payPage = new PageFinder<PayOrder>(page, pageSize, count, cpoList);
    	
    	model.addAttribute("payPage", payPage);
    	model.addAttribute("payStates",request.getParameter("payStates"));
    	return "system/orderDetail";
    }

    /**
     * 查询分校收入情况
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/querySchoolMoney")
    public String querySchoolMoney(Model model, HttpServletRequest request,PayOrder payOrder) {
        String isArea = WebUtils.getCurrentIsArea();
        //2018-1-29修改,根据sort来确定根据什么进行排序
        String sort = request.getParameter("sort");
        Map<String,Object>map = new HashMap<String,Object>();
        map.put("pageSize",payOrder.getPageSize());
        map.put("page",payOrder.getFirstIndex());
        map.put("inpstart",request.getParameter("inpstart"));
        map.put("inpend",request.getParameter("inpend"));
        map.put("totalSort","");
    	map.put("fetchSort","");
    	map.put("handInSort","");
        if("totalSort".equals(sort)||StringUtils.isEmpty(sort)){
        	map.put("totalSort",request.getParameter("sortRule"));
        }else if("fetchSort".equals(sort)){
        	map.put("fetchSort",request.getParameter("sortRule"));
        }else if("handInSort".equals(sort)){
        	map.put("handInSort",request.getParameter("sortRule"));
        }
        List<PayOrder> cpoList = null;
        Integer count = null;
        //分校收入情况
        if("0".equals(isArea)){
            map.put("companyId",request.getParameter("schoolId"));
            map.put("aereId",request.getParameter("areaId"));
            cpoList = payOrderServiceImpl.findSchoolMoneyByCondition(map);
            // 总数
            count = payOrderServiceImpl.findSchoolMoneyCountByCondition(map);
        }else{
            map.put("companyId",WebUtils.getCurrentCompanyId());
            cpoList = payOrderServiceImpl.findPrivateSchoolMoneyByCondition(map);
            // 总数
            count = payOrderServiceImpl.findPrivateSchoolMoneyCountByCondition(map);
        }
        double totalMoneyAdd1 = 0;
        String totalMoneyAdd="0";
        DecimalFormat df = new DecimalFormat("0.00");
        if(null!=cpoList && cpoList.size()>0){
            for(PayOrder po : cpoList){
                totalMoneyAdd1+=Double.parseDouble(po.getTotalMoney());
            }
             totalMoneyAdd = df.format(totalMoneyAdd1);
        }
        // 分页
        PageFinder<PayOrder> payPage = new PageFinder<PayOrder>(payOrder.getPage(), payOrder.getPageSize(), count, cpoList);
        model.addAttribute("payPage", payPage);
        model.addAttribute("totalMoneyAdd", totalMoneyAdd);
        model.addAttribute("isArea", isArea);
        return "system/moneyAjax";
    }



    @RequestMapping(value = "/exportExcelschoolMoney", method = RequestMethod.POST)
    public ModelAndView exportExcelschoolMoney(Model model, HttpServletRequest request,PayOrder payOrder) {
        String isArea = WebUtils.getCurrentIsArea();
        Map<String,Object>map = new HashMap<String,Object>();
        payOrder.setPage(1);
        map.put("pageSize",1000000);
        map.put("page",payOrder.getFirstIndex());
        map.put("inpstart",request.getParameter("inpstart"));
        map.put("inpend",request.getParameter("inpend"));
        List<PayOrder> cpoList = null;
        //分校收入情况
        ViewFiles excel = new ViewFiles();
        Map<String, Object> map01 = new HashMap<String, Object>();
        String tittle= null;
        if("0".equals(isArea)){
            map.put("companyId",request.getParameter("schoolId"));
            map.put("aereId",request.getParameter("areaId"));
            cpoList = payOrderServiceImpl.findSchoolMoneyByCondition(map);
            //处理导出的数据  数校应收 = 总收入 - 实际收入 2018-2-2
            if(cpoList != null && cpoList.size() > 0){
            	for(PayOrder p:cpoList){
            		double d = Double.parseDouble(p.getTotalMoney()) - Double.parseDouble( p.getFetchMoney());
            		p.setHandInMoney(String.valueOf(d));
            	}
            }
            tittle =  "分校名称:schoolName,所属区域:aeraName,分校总收入(元):totalMoney,应收费用(元):handInMoney";
        }else{
            map.put("companyId",WebUtils.getCurrentCompanyId());
            cpoList = payOrderServiceImpl.findPrivateSchoolMoneyByCondition(map);
            tittle =  "时间:orderTime,总收入(元):totalMoney,应缴费用(元):handInMoney,实际收入(元):fetchMoney";
        }
        //将应缴费用和实际收入取小数点后两位
//        for (PayOrder payOrder2 : cpoList) {
//			if(payOrder2.getFetchMoney() != null && payOrder2.getFetchMoney() != ""){
//				String fetchMoney = String.format("%.2f",Double.valueOf(payOrder2.getFetchMoney()));
//				payOrder2.setFetchMoney(fetchMoney);
//			}
//			if(payOrder2.getHandInMoney() != null && payOrder2.getHandInMoney() != ""){
//				String handInMoney = String.format("%.2f",Double.valueOf(payOrder2.getHandInMoney()));
//				payOrder2.setHandInMoney(handInMoney);
//			}
//		}
        ExcelSheetEntity entity = ExcelSheetEntity.newInstance(
                tittle,
                cpoList);
        map01.put("entity", entity);
        map01.put("fileName", "分校收入情况.xls");
        return new ModelAndView(excel, map01);
    }

    @RequestMapping(value = "/exportExcelAllOrder", method = RequestMethod.POST)
    public ModelAndView exportExcelAllOrder(Model model, HttpServletRequest request) {

        String isArea = WebUtils.getCurrentIsArea();
        Integer companyId = WebUtils.getCurrentCompanyId();
        Map<String,Object>map = new HashMap<String,Object>();
        PayOrder payOrder = new PayOrder();
        if(!"0".equals(isArea)){
            map.put("companyId",companyId);
        }
        map.put("orderNum",request.getParameter("orderNum"));
        map.put("inpstart",request.getParameter("inpstart"));
        map.put("inpend",request.getParameter("inpend"));
        map.put("firstPrice",request.getParameter("firstPrice"));
        map.put("secondPrice",request.getParameter("secondPrice"));
        map.put("pageSize",1000000);
        map.put("page",payOrder.getFirstIndex());
        
        //2018-1-29 新增 支付状态
        map.put("payStates",request.getParameter("payStates"));
        //处理支付方式
        String payMethod = request.getParameter("payMethod");
        if(Constant.PAY_TYPE_WX_PERSON.equals(payMethod)){
       	 map.put("payMethod","WX");
       }else if("PAY_TYPE_ZFB".equals(payMethod)){
       	 map.put("payMethod","ZFB");
       }else{
       	 map.put("payMethod",payMethod);
       }
        // 查询 订单 集合
        List<PayOrder> cpoList = this.payOrderServiceImpl.findPayOrderByParams(map);
        String tittle  = "订单编号:orderNum,课程名:commodityName,金额（元）:payPrice,姓名:stuName,电话:discountNo,下单时间:orderTime,付款时间:payTime,订单状态:payStatus";
        ExcelSheetEntity entity = ExcelSheetEntity.newInstance(
                tittle,
                cpoList);
        Map<String,Object>map01 = new HashMap<String,Object>();
        map01.put("entity", entity);
        map01.put("fileName", "订单.xls");
        ViewFiles excel = new ViewFiles();
        return new ModelAndView(excel, map01);
    }
    @RequestMapping(value = "/exportExcelAllSchoolOrder", method = RequestMethod.POST)
    public ModelAndView exportExcelAllSchoolOrder(Model model, HttpServletRequest request) {
    	
    	//String isArea = WebUtils.getCurrentIsArea();
    	String companyId = request.getParameter("companyId");
    	Map<String,Object>map = new HashMap<String,Object>();
    	PayOrder payOrder = new PayOrder();
    	//if(!"0".equals(isArea)){
    		map.put("companyId",companyId);
    	//}
    	map.put("orderNum",request.getParameter("orderNum"));
    	map.put("inpstart",request.getParameter("inpstart"));
    	map.put("inpend",request.getParameter("inpend"));
    	map.put("payMethod",request.getParameter("payMethod"));
    	map.put("firstPrice",request.getParameter("firstPrice"));
    	map.put("secondPrice",request.getParameter("secondPrice"));
    	map.put("pageSize",1000000);
    	map.put("page",payOrder.getFirstIndex());
    	
    	//2018-1-29 新增 支付方式
    	map.put("payStates",request.getParameter("payStates"));
    	// 查询 订单 集合
    	List<PayOrder> cpoList = this.payOrderServiceImpl.findPayOrderByParams(map);
    	String tittle  = "订单编号:orderNum,课程名:commodityName,金额（元）:payPrice,姓名:stuName,电话:discountNo,下单时间:orderTime,付款时间:payTime,订单状态:payStatus";
    	ExcelSheetEntity entity = ExcelSheetEntity.newInstance(
    			tittle,
    			cpoList);
    	Map<String,Object>map01 = new HashMap<String,Object>();
    	map01.put("entity", entity);
    	map01.put("fileName", "订单.xls");
    	ViewFiles excel = new ViewFiles();
    	return new ModelAndView(excel, map01);
    }




    /**
     * 查询分校老师在数字学校的收入情况
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryTeacherMoney")
    public String queryTeacherMoney(Model model, HttpServletRequest request,PayOrder payOrder) {
        String isArea = WebUtils.getCurrentIsArea();
        Map<String,Object>map = new HashMap<String,Object>();
        //分校收入情况

        map.put("pageSize",payOrder.getPageSize());
        map.put("page",payOrder.getFirstIndex());
        map.put("inpstart",request.getParameter("inpstart"));
        map.put("inpend",request.getParameter("inpend"));
        map.put("totalSort",request.getParameter("totalSort"));
        List<PayOrder> cpoList = null;
        Integer count = null;
        if("0".equals(isArea)){
            map.put("companyId",request.getParameter("schoolId"));
            map.put("areaFlag",1);
        }else{
            map.put("companyId",WebUtils.getCurrentCompanyId());

        }
        cpoList = payOrderServiceImpl.queryTeacherMoneyByCondition(map);
        // 总数
        count = payOrderServiceImpl.queryTeacherMoneyCountByCondition(map);
        // 分页
        PageFinder<PayOrder> payPage = new PageFinder<PayOrder>(payOrder.getPage(), payOrder.getPageSize(), count, cpoList);
        model.addAttribute("payPage", payPage);
        model.addAttribute("isArea", isArea);
        return "system/teacherMoneyAjax";
    }


    /**
     * 查询分校老师在数字学校的收入情况
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryTeacherMoneyDetails")
    public String queryTeacherMoneyDetails(Model model, HttpServletRequest request,PayOrder payOrder) {
        String isArea = WebUtils.getCurrentIsArea();
        Map<String,Object>map = new HashMap<String,Object>();
        //分校收入情况

        map.put("pageSize",payOrder.getPageSize());
        map.put("page",payOrder.getFirstIndex());
        map.put("inpstart",request.getParameter("inpstart"));
        map.put("inpend",request.getParameter("inpend"));
        map.put("fetchSort",request.getParameter("fetchSort"));
        map.put("teacherId",request.getParameter("teacherId"));
        List<PayOrder> cpoList = null;
        Integer count = null;
        if("0".equals(isArea)){
//            map.put("companyId",request.getParameter("schoolId"));
            map.put("areaFlag",1);
        }else{
//            map.put("companyId",WebUtils.getCurrentCompanyId());
        }
        cpoList = payOrderServiceImpl.queryTeacherMoneyDetails(map);
        // 总数
        count = payOrderServiceImpl.queryTeacherMoneyDetailsCount(map);
        // 分页
        PageFinder<PayOrder> payPage = new PageFinder<PayOrder>(payOrder.getPage(), payOrder.getPageSize(), count, cpoList);
        model.addAttribute("orderDetails", payPage);
        model.addAttribute("isArea", isArea);
        model.addAttribute("teacherId", request.getParameter("teacherId"));
        return "system/teacherMoneyDetails";
    }
    @ResponseBody
    @RequestMapping("/selOrderLast5")
    public PageFinder<PayOrder> selOrder() {
        Integer companyId = WebUtils.getCurrentCompanyId();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("companyId", companyId);
        map.put("payStatus", 2);
        map.put("page", 0);
        map.put("pageSize", 5);

        Subject subject = SecurityUtils.getSubject();
        if (!subject.hasRole("机构管理员")) {
            map.put("schoolId", WebUtils.getCurrentSchoolId());
        }
        // 查询 订单 集合
        List<PayOrder> cpoList = this.payOrderServiceImpl.findPayOrderByParams(map);
        // 总数
        Integer count = this.payOrderServiceImpl.findCountByParams(map);
        // 分页
        PageFinder<PayOrder> payPage = new PageFinder<PayOrder>(0, 5, count, cpoList);

        return payPage;
    }

    /**
     * @Description: 去收款
     * @author zx
     * @date 2015-8-14 下午3:48:46
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/detailOrder/{orderId}")
    public String detailOrder(Model model, @PathVariable Integer orderId) {
        PayOrderVo payOrder = this.payOrderServiceImpl.findPayOrderVoById(orderId);
        model.addAttribute("payOrder", payOrder);
        return "system/confirmOrderDetail";
    }

    /**
     * @Description: 转账/汇款用户转账后，管理员确认收货
     * @author zx
     * @date 2015-8-14 下午5:46:45
     * @version 1.0
     * @param request
     * @param payOrder
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/submitOrder")
    public String submitDetailOrder(HttpServletRequest request, PayOrder payOrder) {

        UsersFront user = this.usersFrontServiceImpl.findUsersFrontById(payOrder.getUserId());
        payOrder = this.payOrderServiceImpl.findPayOrderById(payOrder.getId());
        if (payOrder != null) {
            // 设置为确认转账成功
            payOrder.setPayStatus("PAY_REMIT_CONFIRM");
            payOrder.setCollectionTime(new Date());
        }
        // 处理确认收款后后续的订单处理业务，跟前台支付成功处理的逻辑一样

        PayOrderHistory history = new PayOrderHistory();
        // 记录到支付历史表
        history.setPayNum(payOrder.getRemittanceNumber());
        history.setPayOrderId(payOrder.getOrderNum());
        history.setPrice(payOrder.getCollectionAmount());
        history.setPayTime(new Date());
        history.setPayType(payOrder.getPayType());
        history.setPayStatus(Constant.PAY_STATUS_SUCCESS);
        this.payOrderServiceImpl.insertOrderHistory(history);

        // 根据订单中的商品ID,获取商品信息
        Commodity commodity = this.commodityServiceImpl.findCommodityById(payOrder.getCommodityId());
        this.log_pay.info("当前的商品为：" + commodity);
        // 根据订单中的用户ID，获取用户信息
        this.log_pay.info("后台支付成功后根据order中获取用户ID为: " + user.getId());

        this.payOrderServiceImpl.update(payOrder);
        this.makeMasterAndSlaveOrder(request, commodity, user, true, payOrder);

        UsersFrontVo uvo = this.usersFrontServiceImpl.findUsersFrontVoById(payOrder.getUserId());
        this.log_pay.info("msg：执行首次消费邀请人获得奖励的方法");
        InviteRecordUtil.inviteReward(uvo.getId(), payOrder);
        return "success";
    }

    /**
     * @Description: 支付成功调用改方法，插入主订单
     * @author wzx
     * @date 2015-5-13 下午4:04:11
     * @version 1.0
     * @param commodity
     * @param user
     * @param payStatus
     */
    public Integer makeMasterAndSlaveOrder(HttpServletRequest request, Commodity commodity, UsersFront user, boolean payStatus, PayOrder order) {

        // 查询课程或课程包是否有绑定购买协议签订协议
        CompanyFunctionSet companyFunctionSetSearch = new CompanyFunctionSet();
        companyFunctionSetSearch.setCompanyId(order.getCompanyId());
        companyFunctionSetSearch.setStatus("1");

        Integer commdityId = order.getCommodityId();
        CommodityProductRealtion commodityProductRealtionSearch = new CommodityProductRealtion();
        commodityProductRealtionSearch.setComId(commdityId);
        Integer productId = this.commodityProductRealtionServiceImpl.findCommodityProductRealtionByPage(commodityProductRealtionSearch).get(0).getProductId();
        CoursePotocolUserRelation coursePotocolUserRelation = new CoursePotocolUserRelation();
        coursePotocolUserRelation.setUserId(order.getUserId());
        List<CoursePotocolUserRelation> coursePotocolUserRelationList = null;
        if ("COMMODITY_CLASS".equals(order.getCommdityType())) {

            companyFunctionSetSearch.setFunctionCode("CLASS_POTOCOL_SET");
            List<CompanyFunctionSet> companyFunctionSetList = this.companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSetSearch);
            if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
                ClassType commodityClass = this.classTypeServiceImpl.findClassTypeById(productId);
                // 判断用户是否已经签订过该协议
                if (commodityClass.getProtocolId() != null && !"".equals(commodityClass.getProtocolId())) {// 课程有绑定协议
                    coursePotocolUserRelation.setCompanyId(commodityClass.getCompanyId());
                    coursePotocolUserRelation.setPotocolId(commodityClass.getProtocolId());
                    coursePotocolUserRelation.setCourseId(commodityClass.getId());
                    coursePotocolUserRelationList = this.coursePotocolUserRelationServiceImpl.findCoursePotocolUserRelationByPage(coursePotocolUserRelation);
                }
            }
        } else if ("COMMODITY_PACKAGE".equals(order.getCommdityType())) {// 课程包购买协议查询
            companyFunctionSetSearch.setFunctionCode("CLASSPACKAGE_POTOCOL_SET");
            List<CompanyFunctionSet> companyFunctionSetList = this.companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSetSearch);
            if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
                ClassPackage classPackage = this.classPackageServiceImpl.findClassPackageById(productId);
                if (classPackage.getProtocolId() != null && !"".equals(classPackage.getProtocolId())) {
                    // 查询该用户是否已经绑定过该协议
                    coursePotocolUserRelation.setCompanyId(classPackage.getCompanyId());
                    coursePotocolUserRelation.setPotocolId(classPackage.getProtocolId());
                    coursePotocolUserRelation.setClassPackageId(classPackage.getId());
                    coursePotocolUserRelationList = this.coursePotocolUserRelationServiceImpl.findCoursePotocolUserRelationByPage(coursePotocolUserRelation);
                }
            }
        }
        if (coursePotocolUserRelationList == null || coursePotocolUserRelationList.size() == 0) {
            CourseProtocolConfig potocol = this.courseProtocolConfigServiceImpl.findCourseProtocolConfigById(coursePotocolUserRelation.getPotocolId());
            if (potocol != null && potocol.getDelFlag() != 1 && potocol.getStatus() == 1 && potocol.getUseTime() == 0) {
                coursePotocolUserRelation.setRecordTime(new Date());
                this.coursePotocolUserRelationServiceImpl.insert(coursePotocolUserRelation);
            }
        }

        String orderType = order.getCommdityType();
        this.log_pay.info("当前购买的商品类型为：" + orderType);
        // 判断当前订单包含的课程是否为纯视频或者远程课程， 如果是则插入到主订单表以及子订单表， 否则只插入到主订单表
        boolean isVideo = false;
        boolean isRemote = false;
        if (orderType.equals("COMMODITY_CLASS") || orderType.equals("COMMODITY_PACKAGE")) {
            // 首先判断当前商品是否包含视频，如果包含则先置为true,然后再判断其他的课程分类
            if (commodity.getVideoFlag() != null && 1 == commodity.getVideoFlag()) {
                isVideo = true;
                if (commodity.getLiveFlag() != null && 1 == commodity.getLiveFlag()) {
                    isVideo = false;
                } else if (commodity.getLiveFlag() != null && 1 == commodity.getFaceFlag()) {
                    isVideo = false;
                }
            }

            this.log_pay.info("当前购买的商品是否为纯视频课程：" + isVideo);

            if (commodity.getRemoteFlag() != null && commodity.getRemoteFlag().equals(1)) {
                isRemote = true;
            }
        }

        if (orderType.equals("COMMODITY_CLASS")) {
            // 每多购买一次，更新一下商品表 的buy_num + 1
            if (commodity != null && commodity.getBuyNum() != null) {
                this.log_pay.info("当前商品购买的数量为：" + commodity.getBuyNum());
                int buyNum = commodity.getBuyNum();
                commodity.setBuyNum(buyNum + 1);
            } else {
                commodity.setBuyNum(1);
            }
            commodity.setUpdateTime(new Date());
            this.commodityServiceImpl.update(commodity);
        }
        if (orderType.equals("COMMODITY_PACKAGE")) {
            // 每多购买一次，更新一下商品表 的buy_num + 1,阶段分次购买时会出现加多个学员
            // 查询学员是否购买过该课程包中的其中一个阶段
            PayOrder payOrderSearch = new PayOrder();
            payOrderSearch.setCommodityId(order.getCommodityId());
            payOrderSearch.setUserId(order.getUserId());
            List<PayOrder> payOrderList = this.payOrderServiceImpl.findPayOrderByPage(payOrderSearch);
            // 学员没有购买过该课程包下任何一个阶段
            if (payOrderList == null || payOrderList.size() <= 1) {
                if (commodity != null && commodity.getBuyNum() != null) {
                    this.log_pay.info("当前商品购买的数量为：" + commodity.getBuyNum());
                    int buyNum = commodity.getBuyNum();
                    commodity.setBuyNum(buyNum + 1);
                } else {
                    commodity.setBuyNum(1);
                }
                commodity.setUpdateTime(new Date());
                this.commodityServiceImpl.update(commodity);
            }
            // 购买课程包时将课程包里面的课程也设置购买+1
            commodityProductRealtionSearch = new CommodityProductRealtion();
            commodityProductRealtionSearch.setComId(order.getCommodityId());
            commodityProductRealtionSearch.setProductType("2");// 课程包
            CommodityProductRealtion commodityProductRealtion = this.commodityProductRealtionServiceImpl
                    .findCommodityProductRealtionByPage(commodityProductRealtionSearch).get(0);
            ClassPackageRelation classPackageRelationSearch = new ClassPackageRelation();
            classPackageRelationSearch.setClassPackageId(commodityProductRealtion.getProductId());
            if (order.getClassPackageStageId() != null && order.getClassPackageStageId() != 0) {
                classPackageRelationSearch.setClassPackageStageId(order.getClassPackageStageId());
            }
            List<ClassPackageRelation> classPackageRelationList = this.classPackageRelationServiceImpl
                    .findClassPackageRelationByPage(classPackageRelationSearch);
            for (ClassPackageRelation ele : classPackageRelationList) {
                ele.getClassTypeId();
                commodityProductRealtionSearch = new CommodityProductRealtion();
                commodityProductRealtionSearch.setProductId(ele.getClassTypeId());
                commodityProductRealtionSearch.setProductType("1");
                CommodityProductRealtion productRealtion = this.commodityProductRealtionServiceImpl
                        .findCommodityProductRealtionByPage(commodityProductRealtionSearch).get(0);

                Commodity commoditySearch = new Commodity();
                commoditySearch.setId(productRealtion.getComId());
                List<Commodity> commodityList = this.commodityServiceImpl.findCommodityByPage(commoditySearch);
                if (commodityList != null && commodityList.size() > 0) {
                    Commodity _c = commodityList.get(0);
                    _c.setBuyNum(_c.getBuyNum() == null ? 1 : _c.getBuyNum() + 1);
                    this.commodityServiceImpl.update(_c);
                }
            }
        }
        // 根据手机查询学生信息，如果没有则新增一条
        Student student = new Student();
        student.setMobile(user.getMobile());
        student.setCompanyId(user.getCompanyId());
        student.setUserId(user.getId());
        List<Student> students = this.studentServiceImpl.findStudentList(student);
        if (students == null || students.size() == 0) {
            Student stu = new Student();

            stu.setName(user.getUsername());
            stu.setMobile(user.getMobile());
            stu.setEmail(user.getEmail());
            stu.setCompanyId(user.getCompanyId());
            stu.setUserId(user.getId());
            stu.setCreateTime(new Date());
            stu.setCreator(user.getId());
            stu.setDeleteFlag(0);
            stu.setCompanyId(user.getCompanyId());
            this.studentServiceImpl.insert(stu);
            student = stu;
        } else {
            student = students.get(0);
        }

        return this.insertPayMasterOrder(request, commodity, student, isVideo, isRemote, order);
    }

    /**
     * @Description: 插入主订单表
     * @author wzx
     * @date 2015-5-12 下午6:38:55
     * @version 1.0
     */
    public Integer insertPayMasterOrder(HttpServletRequest request, Commodity commodity, Student stu, boolean isVideo, boolean isRemote, PayOrder order) {
        String orderType = order.getCommdityType();
        Integer packageId = 0;
        Integer stageId = order.getClassPackageStageId();
        if (order != null && order.getId() > 0) {
            StudentPayMaster master = new StudentPayMaster();
            master.setPayOrderId(order.getId());
            master.setDeleteFlag(0);
            List<StudentPayMaster> pays = this.studentPayMasterServiceImpl.findStudentPayMasterByPage(master);
            if (pays != null && pays.size() > 0) {
                this.log_pay.info("掉用到这里返回了，当前的orderId为：" + order.getId());
                return pays.get(0).getId();
            }
        }

        StudentPayMaster master = new StudentPayMaster();
        master.setApplyTime(new Date());
        ClassType type = null;
        UsersFrontVo uv = usersFrontServiceImpl.findUsersFrontVoById(stu.getUserId());
        if (orderType.equals("COMMODITY_CLASS") || orderType.equals("COMMODITY_PACKAGE")) {
            master.setItemOneId(commodity.getItemOneId());
            master.setItemSecondId(commodity.getItemSecondId());
            if (commodity.getItemOneId() != null) {
                SysConfigItem itemOne = this.sysConfigItemServiceImpl.findSysConfigItemById(commodity.getItemOneId());
                master.setItemOneName(itemOne.getItemName());
            }
            if (commodity.getItemSecondId() != null) {
                SysConfigItem itemSecond = this.sysConfigItemServiceImpl.findSysConfigItemById(commodity.getItemSecondId());
                master.setItemSecondName(itemSecond.getItemName());
            }

            // 根据商品Id查询对应的班型信息
            type = this.classTypeServiceImpl.findClassTypeByCommodity(commodity.getId());
            // master.setCommodityId(type.getId());
            // master.setCommodityType("COMMODITY_CLASS");
            // master.setClassTypeName(type.getName());

            /**
             * 3.4版本增加课程包之后，订单对应的逻辑处理
             */
            // 如果该商品为课程

            if (commodity != null && commodity.getType() != null && "COMMODITY_CLASS".equals(commodity.getType())) {
                // 主订单中的commodityId，如果是课程，则是class_typeId
                master.setCommodityId(type.getId());
                master.setCommodityType("COMMODITY_CLASS");
                master.setClassTypeName(commodity.getName());
                master.setTrainingFee(type.getRealPrice());
                // 根据版型ID获取对应的模块ID, 查询出所对应的总课时
                Integer totalHour = this.classModuleServiceImpl.calculationHourByClassType(type.getId());
                master.setClassTypeHour(totalHour);
                StudentDynamicsUtil.insert(uv, 0, order.getCommodityName(), order.getPayPrice(), commodity.getId());
            } else {
                // 根据商品ID判断是否为课程包类型
                String cType = commodity.getType();
                if (cType != null && "COMMODITY_PACKAGE".equals(cType)) { // 如果该商品为课程包
                    // 根据班型与商品的中间表查询出该商品对应的课程包
                    CommodityProductRealtion realtion = new CommodityProductRealtion();
                    realtion.setComId(commodity.getId());
                    realtion.setProductType("2");
                    List<CommodityProductRealtion> realtions = this.commodityProductRealtionServiceImpl.findCommodityProductRealtionByPage(realtion);
                    if (realtions != null && realtions.size() > 0) {
                        packageId = realtions.get(0).getProductId();
                    }
                    if (!packageId.equals(0)) {
                        master.setCommodityId(packageId);
                        ClassPackage classPackage = this.classPackageServiceImpl.findClassPackageById(packageId);
                        if (classPackage != null) {
                            master.setClassTypeName(classPackage.getName());
                        }

                        master.setCommodityType("COMMODITY_PACKAGE");
                        master.setClassPackageStageId(stageId);
                        master.setTrainingFee(classPackage.getRealPrice());
                    }
                    StudentDynamicsUtil.insert(uv, 13, order.getCommodityName(), order.getPayPrice(), commodity.getId());
                }
            }
            master.setBizStatusCode(Constant.ORDER_BIZ_NEW_ORDER);
            master.setIsAgent("0");
        } else if (orderType.equals("INTEGRAL")) {
            master.setCommodityType("INTEGRAL");
            master.setClassTypeName("积分购买");
            master.setIntegralNum(order.getIntegralNum());
            master.setExchangeScale(order.getExchangeScale());
            master.setTrainingFee(order.getPayPrice());
            StudentDynamicsUtil.insert(uv, 11, order.getCommodityName(), order.getPayPrice(), null);
        } else if (orderType.equals("MEMBER")) {
            master.setCommodityType("MEMBER");
            master.setClassTypeName("会员购买");
            master.setMemberId(order.getMemberId());
            master.setMemberLength(order.getMemberLength());
            master.setTrainingFee(order.getPayPrice());
            StudentDynamicsUtil.insert(uv, 12, order.getCommodityName(), order.getPayPrice(), null);
        }
        master.setApplyChannelCode(Constant.CHANNEL_ONLINE);
        master.setStuId(stu.getId());
        master.setPayStatusCode(Constant.ORDER_PAID);
        master.setTotalAmount(order.getPayPrice());
        // 付款方式， 如果为在线支付则默认为全款支付
        master.setPaymentTypeCode(Constant.PAY_TYPE_ALL);

        master.setCreateTime(new Date());
        master.setDeleteFlag(0);
        master.setSchoolId(WebUtils.getCurrentSchoolId());
        master.setCompanyId(stu.getCompanyId());
        master.setPayOrderId(order.getId());
        master.setIntegralUsed(order.getIntegralUsed());
        master.setExchangeScale(order.getExchangeScale());
        master.setCouponCode(order.getCouponCode());
        master.setCouponInstead(order.getCouponInstead());
        this.log_pay.info("订单ID：" + order.getId());
        this.studentPayMasterServiceImpl.insert(master);

        // 插入住订单之后需要给分期缴费表中添加一条数据
        // insertStudentFeeStage(request, master, com);
        if (orderType.equals("COMMODITY_CLASS") || orderType.equals("COMMODITY_PACKAGE")) {
            this.log_pay.info("后台商品插入主订单信息成功, 学生ID为: " + stu.getId() + "商品ID: " + commodity.getId());
            // 根据用户Id查询用户对象
            UsersFront userFront = this.usersFrontServiceImpl.findUsersFrontById(stu.getUserId());
            if (userFront != null) {
                if (userFront.getVipFlag() == 0 || userFront.getVipFlag() == null) {
                    userFront.setVipFlag(1);
                    this.usersFrontServiceImpl.update(userFront);
                }
            }
            // 其他课程不插入子订单
            if (!isRemote) {
                // 添加完主订单添加子订单
                this.insertPaySlaveOrder(commodity, stu, type, master.getId(), isVideo, isRemote, packageId, stageId);
            }
        } else if (orderType.equals("INTEGRAL")) {
            this.log_pay.info("购买积分成功更新用户积分");
            UsersFront userFront = this.usersFrontServiceImpl.findUsersFrontById(stu.getUserId());
            if (userFront != null) {
                Integer userIntegral = userFront.getIntegralRemaining();
                if (userIntegral == null) {
                    userIntegral = 0;
                }

                this.log_pay.info("msg：插入积分变化表");
                UserIntegralFlow uf = new UserIntegralFlow();
                uf.setReason("充值");
                uf.setRecord(order.getIntegralNum());
                uf.setUserId(userFront.getId());
                uf.setStuId(stu.getId());
                uf.setUpdateTime(new Date());

                userIntegral = order.getIntegralNum() + userIntegral;
                userFront.setIntegralRemaining(userIntegral);

                uf.setLastRecord(userIntegral);
                this.userIntegralFlowServiceImpl.insert(uf);

                this.log_pay.info("更新用户积分，用户购买的积分是：" + order.getIntegralNum() + "，购买后的是：" + userIntegral);
                this.usersFrontServiceImpl.update(userFront);
            }
        } else if (orderType.equals("MEMBER")) {
            String memType = order.getBizCode();
            UsersFront userFront = this.usersFrontServiceImpl.findUsersFrontById(stu.getUserId());
            if (userFront != null) {
                Integer memLength = order.getMemberLength();
                Integer memId = order.getMemberId();
                userFront.setMemberId(memId);
                userFront.setMemberStatus(1);
                CompanyMemberLevelConfig memConfig = this.companyMemberLevelConfigServiceImpl.findCompanyMemberLevelConfigById(memId);
                if (memConfig != null) {
                    userFront.setMemberLevel(memConfig.getName());
                } else {
                    this.log_pay.info("没有查到相应会员：");
                }
                if (memType.equals("ORDER_BIZ_BUY_MEMBER") || memType.equals("ORDER_BIZ_UPDATE_MEMBER")) {
                    Date memEndDate = DateUtil.addMonthsToDate(new Date(), memLength);
                    userFront.setMemberEndTime(memEndDate);
                    userFront.setMemberBuyLength(memLength);
                } else if (memType.equals("ORDER_BIZ_RENEW_MEMBER")) {
                    Integer userMemLength = userFront.getMemberBuyLength();
                    Date userMemEndDate = userFront.getMemberEndTime();
                    userMemLength = userMemLength + memLength;
                    userFront.setMemberBuyLength(userMemLength);
                    userMemEndDate = DateUtil.addMonthsToDate(userMemEndDate, memLength);
                    userFront.setMemberEndTime(userMemEndDate);
                }
            }
            this.usersFrontServiceImpl.update(userFront);
        }
        this.log_pay.info("msg：购买获取积分");
        CompanyIntegralConfig cic = this.companyIntegralConfigServiceImpl.findByCompanyId(order.getCompanyId());
        if (cic != null) {
            Integer getIntegral = cic.getGetIntegralSwitch();
            if (getIntegral.equals(1)) {
                this.log_pay.info("msg：购买获取积分开关开启:" + getIntegral);
                Double payPrice = order.getPayPrice();
                this.log_pay.info("msg：这笔订单的费用为:" + payPrice);
                Double costMoreThan = cic.getCostMoreThen();
                this.log_pay.info("msg：单笔金额大于多少可以获得积分:" + costMoreThan);
                if (costMoreThan != null && payPrice >= costMoreThan) {
                    Integer conNum = cic.getConsumeNum();
                    if (conNum == null) {
                        conNum = 0;
                    }
                    Double s = payPrice / costMoreThan;
                    Integer a = s.intValue();
                    conNum = a * conNum;
                    this.log_pay.info("msg：消费满足条件可获得积分是:" + conNum);
                    // 根据用户Id查询用户对象
                    UsersFront userFront = this.usersFrontServiceImpl.findUsersFrontById(stu.getUserId());
                    if (userFront != null) {
                        Integer userIntegral = userFront.getIntegralRemaining();
                        if (userIntegral == null) {
                            userIntegral = 0;
                        }
                        userIntegral = userIntegral + conNum;
                        userFront.setIntegralRemaining(userIntegral);
                        this.usersFrontServiceImpl.update(userFront);

                        this.log_pay.info("msg：插入积分变化表");
                        UserIntegralFlow uf = new UserIntegralFlow();
                        uf.setReason("单笔消费赠送");
                        uf.setRecord(conNum);
                        uf.setUserId(userFront.getId());
                        uf.setStuId(stu.getId());
                        uf.setUpdateTime(new Date());
                        uf.setLastRecord(userIntegral);
                        this.userIntegralFlowServiceImpl.insert(uf);
                    }
                }
            }
        }
        this.log_pay.info("msg：判断是不是累计消费获得会员");
        Integer companyId = order.getCompanyId();
        CompanyMemberConfig cmc = this.companyMemberConfigServiceImpl.findCompanyMemberConfigByCompanyId(companyId);
        Integer beMember = 0;
        if (cmc != null) {
            beMember = cmc.getBecomeMember();
        }
        if (beMember != null && beMember.equals(1)) {
            this.log_pay.info("msg：累计消费获得会员");
            UsersFront userFront = this.usersFrontServiceImpl.findUsersFrontById(stu.getUserId());
            PayOrder uo = new PayOrder();
            uo.setPayStatus("PAY_SUCCESS");
            uo.setUserId(order.getUserId());
            uo.setCompanyId(companyId);
            uo.setPageSize(100000);
            List<PayOrder> orderVo = this.payOrderServiceImpl.findPayOrderByPage(uo);
            Double userAllPrice = 0.0;
            for (PayOrder payOrderVo : orderVo) {
                userAllPrice += payOrderVo.getPayPrice();
            }
            this.log_pay.info("msg：用户以往所有消费金额为：" + userAllPrice);
            CompanyMemberLevelConfig search = new CompanyMemberLevelConfig();
            search.setCompanyId(companyId);
            search.setPageSize(100000);
            search.setOpenWay(0);
            List<CompanyMemberLevelConfig> cmlc = this.companyMemberLevelConfigServiceImpl.findCompanyMemberLevelConfigByPage(search);
            if (cmlc.size() > 0) {
                Integer memberId = 0;
                String memName = "";
                Double coMoney = 0.0;
                for (CompanyMemberLevelConfig cml : cmlc) {
                    if (cml.getConsumption() != null) {
                        coMoney = cml.getConsumption() * 1.0;
                        if (userAllPrice >= coMoney) {
                            memberId = cml.getId();
                            memName = cml.getName();
                        }
                    }
                }
                this.log_pay.info("msg：用户所能达到的最高会员id" + memberId);
                if (!memberId.equals(0)) {
                    // 根据用户Id查询用户对象
                    CompanyMemberLevelConfig member = this.companyMemberLevelConfigServiceImpl.findCompanyMemberLevelConfigById(memberId);
                    Integer userMemId = userFront.getMemberId();
                    CompanyMemberLevelConfig userMember = null;
                    if (userMemId != null) {
                        userMember = this.companyMemberLevelConfigServiceImpl.findCompanyMemberLevelConfigById(userMemId);
                    }
                    if (userMember == null || member.getSequence() > userMember.getSequence()) {
                        userFront.setMemberId(memberId);
                        userFront.setMemberLevel(memName);
                        userFront.setMemberBuyLength(-1);
                        userFront.setMemberStatus(1);
                        this.usersFrontServiceImpl.update(userFront);
                    }
                }
            }
        }
        UsersFront userF = this.usersFrontServiceImpl.findUsersFrontById(order.getUserId());
        if (userF != null) {
            // 判断该订单是否使用优惠券
            String couponCodes = order.getCouponCode();
            if (couponCodes != null) {
                Integer fh = couponCodes.indexOf(",");
                if (fh > 0) {
                    this.log_pay.info("msg：使用了多张优惠券");
                    String[] codes = couponCodes.split(",");
                    for (String c : codes) {
                        CompanyCouponsLib ccl = this.companyCouponsLibServiceImpl.findOneByCode(c);
                        if (ccl != null) {
                            ccl.setStatus(1);
                            ccl.setUseTime(new Date());
                            ccl.setUserId(order.getUserId());
                            ccl.setOrderId(order.getId());
                            this.companyCouponsLibServiceImpl.update(ccl);
                            UsersFrontMyCoupons search = new UsersFrontMyCoupons();
                            search.setUserId(userF.getId());
                            search.setCouponsCode(c);
                            List<UsersFrontMyCoupons> isHave = this.usersFrontMyCouponsServiceImpl.findUsersFrontMyCouponsNoPage(search);
                            if (isHave.size() > 0) {
                                UsersFrontMyCoupons up = isHave.get(0);
                                up.setStatus(0);
                                this.usersFrontMyCouponsServiceImpl.update(up);
                            } else {
                                search.setStatus(0);
                                this.usersFrontMyCouponsServiceImpl.insert(search);
                            }
                            Integer patchId = ccl.getPatchId();
                            this.usersFrontMyCouponsServiceImpl.updateUsedCouponCount(patchId);

                            SysLogCouponUse couUse = new SysLogCouponUse();
                            couUse.setCommodityId(order.getCommodityId());
                            couUse.setCommodityType(order.getCommdityType());
                            couUse.setCouponCode(c);
                            couUse.setUserId(order.getUserId());
                            couUse.setUseTime(new Date());
                            // 插入
                            this.sysLogCouponUseServiceImpl.insert(couUse);
                        }
                    }
                } else {
                    this.log_pay.info("msg：使用了一张优惠券");
                    CompanyCouponsLib ccl = this.companyCouponsLibServiceImpl.findOneByCode(couponCodes);
                    if (ccl != null) {
                        ccl.setStatus(1);
                        ccl.setUseTime(new Date());
                        ccl.setUserId(order.getUserId());
                        ccl.setOrderId(order.getId());
                        this.companyCouponsLibServiceImpl.update(ccl);
                        UsersFrontMyCoupons search = new UsersFrontMyCoupons();
                        search.setUserId(userF.getId());
                        search.setCouponsCode(couponCodes);
                        List<UsersFrontMyCoupons> isHave = this.usersFrontMyCouponsServiceImpl.findUsersFrontMyCouponsNoPage(search);
                        if (isHave.size() > 0) {
                            UsersFrontMyCoupons up = isHave.get(0);
                            up.setStatus(0);
                            this.usersFrontMyCouponsServiceImpl.update(up);
                        } else {
                            search.setStatus(0);
                            this.usersFrontMyCouponsServiceImpl.insert(search);
                        }
                        Integer patchId = ccl.getPatchId();
                        this.usersFrontMyCouponsServiceImpl.updateUsedCouponCount(patchId);

                        SysLogCouponUse couUse = new SysLogCouponUse();
                        couUse.setCommodityId(order.getCommodityId());
                        couUse.setCommodityType(order.getCommdityType());
                        couUse.setCouponCode(couponCodes);
                        couUse.setUserId(order.getUserId());
                        couUse.setUseTime(new Date());
                        // 插入
                        this.sysLogCouponUseServiceImpl.insert(couUse);
                    }
                }
            }
            // 判断该订单是否使用了积分，使用的话更新用户积分
            Integer integralUsed = order.getIntegralUsed();
            if (integralUsed == null) {
                integralUsed = 0;
            }
            this.log_pay.info("用户使用的积分为：" + integralUsed);
            Integer userIntegral = userF.getIntegralRemaining();
            if (userIntegral == null) {
                userIntegral = 0;
            }
            this.log_pay.info("用户的积分为：" + userIntegral);
            userIntegral = userIntegral - integralUsed;
            this.log_pay.info("扣除使用的后用户的积分为：" + userIntegral + "，更新用户积分");
            userF.setIntegralRemaining(userIntegral);
            this.usersFrontServiceImpl.update(userF);
            if (integralUsed > 0) {
                this.log_pay.info("msg：插入积分变化表");
                UserIntegralFlow uf = new UserIntegralFlow();
                uf.setReason("消费");
                uf.setRecord(-integralUsed);
                uf.setUserId(userF.getId());
                uf.setStuId(stu.getId());
                uf.setUpdateTime(new Date());
                uf.setLastRecord(userIntegral);
                this.userIntegralFlowServiceImpl.insert(uf);
            }
        }
        return master.getId();
    }

    /**
     * @Description: 插入子订单表
     * @author wzx
     * @date 2015-5-12 下午8:42:07
     * @version 1.0
     * @param com
     * @param stu
     * @param payStatus
     */
    public void insertPaySlaveOrder(Commodity com, Student stu, ClassType type, Integer masterId, boolean isVideo, boolean isRemote, Integer packageId,
            Integer stageId) {
        this.log_pay.info("后台商品进入插入子订单,当前是否为纯视频：" + isVideo + " , 是否为远程：" + isRemote);
        List<StudentPaySlave> slaves = new ArrayList<StudentPaySlave>();
        // 根据商品ID查询对应的班型
        CommodityProductRealtion realtion = new CommodityProductRealtion();
        realtion.setComId(com.getId());
        realtion.setProductType("1");
        List<CommodityProductRealtion> realtions = this.commodityProductRealtionServiceImpl.findCommodityProductRealtionByPage(realtion);
        if (realtions != null && realtions.size() > 0) {
            realtion = realtions.get(0);
        }

        // 根据商品查询当前课程中所有的模块
        if (isRemote) {
            this.log_pay.info("进来插入远程课程的订单");
            ClassTypeRemoteRelation relation = new ClassTypeRemoteRelation();
            relation.setClassTypeId(realtion.getProductId());
            List<ClassTypeRemoteRelation> relations = this.classTypeRemoteRelationServiceImpl.findClassTypeRemoteRelationByPage(relation);

            for (ClassTypeRemoteRelation relationFor : relations) {
                StudentPaySlave slave = new StudentPaySlave();
                slave.setCompanyId(stu.getCompanyId());
                slave.setPayMasterId(masterId);
                slave.setStuId(stu.getId());
                slave.setResourceType(Constant.TEACH_METHOD_REMOTE);
                slave.setResourceId(String.valueOf(relationFor.getRemoteId()));
                slave.setSlaveStatusCode(Constant.SUB_ORDER_FINISHED);
                slaves.add(slave);
            }
            this.studentPaySlaveServiceImpl.batchInsert(slaves);
        } else {
            if ("COMMODITY_CLASS".equals(com.getType())) {
                this.log_pay.info("进来插入纯视频课程的订单");
                // 根据班型ID查询对应的模块
                HashMap<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("classTypeId", realtion.getProductId());
                List<ClassTypeModuleRelationVo> ctmlList = this.classTypeModuleRelationServiceImpl.findClassModuleRelationByClassTypeId(paramMap);
                this.log_pay.info("当前的ClassTypeModuleRelation的长度为：" + ctmlList.size() + "....+" + ctmlList);

                // 查询模块售卖班号表
                ClassModuleNoOnsale onSale = new ClassModuleNoOnsale();
                onSale.setClasstypeId(type.getId());
                onSale.setDefaultFlag(1);
                onSale.setPageSize(30);
                this.log_pay.info("当前购买的班型Id为：" + type.getId());
                List<ClassModuleNoOnsale> onSales = this.classModuleNoOnsaleServiceImpl.findClassModuleNoOnsaleByPage(onSale);
                this.log_pay.info("查询出来的模块班号售卖表的长度为：" + onSales.size() + "..." + onSales.toString());

                for (ClassTypeModuleRelationVo relation : ctmlList) {
                    boolean flag = false;
                    ClassModuleNoOnsale saleBean = null;
                    StudentPaySlave slave = new StudentPaySlave();
                    slave.setCompanyId(stu.getCompanyId());
                    slave.setModuleId(relation.getModuleId());
                    slave.setPayMasterId(masterId);
                    slave.setStuId(stu.getId());
                    for (ClassModuleNoOnsale NoOnSale : onSales) {
                        if (NoOnSale.getModuleId().equals(relation.getModuleId())) {
                            saleBean = NoOnSale;
                            flag = true;
                            break;
                        }
                    }
                    // 判断模块Id是否相同
                    if (flag) {
                        this.log_pay.info("添加资源ID为班号ID");
                        slave.setResourceId(saleBean.getModuleNoId().toString());
                    } else {
                        this.log_pay.info("添加资源ID为模块Id");
                        slave.setResourceId(String.valueOf(relation.getModuleId()));
                    }

                    slave.setResourceType(relation.getTeachMethod());

                    slave.setSlaveStatusCode(Constant.SUB_ORDER_FINISHED);
                    slaves.add(slave);
                }
                if (slaves.size() > 0) {
                    this.log_pay.info("slaves：" + slaves.size() + ", 当前的子订单为：" + slaves.get(0));
                    this.studentPaySlaveServiceImpl.batchInsert(slaves);
                }
            } else {
                ClassPackageRelation search = new ClassPackageRelation();
                search.setClassPackageId(packageId);
                search.setClassPackageStageId(stageId);
                List<ClassPackageRelation> rs = this.classPackageRelationServiceImpl.findClassPackageRelations(search);
                for (ClassPackageRelation r : rs) {
                    this.log_pay.info("进来插入纯视频课程的订单");
                    // 根据班型ID查询对应的模块
                    HashMap<String, Object> paramMap = new HashMap<String, Object>();
                    paramMap.put("classTypeId", r.getClassTypeId());
                    List<ClassTypeModuleRelationVo> ctmlList = this.classTypeModuleRelationServiceImpl.findClassModuleRelationByClassTypeId(paramMap);
                    this.log_pay.info("当前的ClassTypeModuleRelation的长度为：" + ctmlList.size() + "....+" + ctmlList);

                    // 查询模块售卖班号表
                    ClassModuleNoOnsale onSale = new ClassModuleNoOnsale();
                    onSale.setClasstypeId(r.getClassTypeId());
                    onSale.setDefaultFlag(1);
                    onSale.setPageSize(30);
                    this.log_pay.info("当前购买的班型Id为：" + r.getClassTypeId());
                    List<ClassModuleNoOnsale> onSales = this.classModuleNoOnsaleServiceImpl.findClassModuleNoOnsaleByPage(onSale);
                    this.log_pay.info("查询出来的模块班号售卖表的长度为：" + onSales.size() + "..." + onSales.toString());

                    for (ClassTypeModuleRelationVo relation : ctmlList) {
                        boolean flag = false;
                        ClassModuleNoOnsale saleBean = null;
                        StudentPaySlave slave = new StudentPaySlave();
                        slave.setCompanyId(stu.getCompanyId());
                        slave.setModuleId(relation.getModuleId());
                        slave.setPayMasterId(masterId);
                        slave.setStuId(stu.getId());
                        for (ClassModuleNoOnsale NoOnSale : onSales) {
                            if (NoOnSale.getModuleId().equals(relation.getModuleId())) {
                                saleBean = NoOnSale;
                                flag = true;
                                break;
                            }
                        }
                        // 判断模块Id是否相同
                        if (flag) {
                            this.log_pay.info("添加资源ID为班号ID");
                            slave.setResourceId(saleBean.getModuleNoId().toString());
                        } else {
                            this.log_pay.info("添加资源ID为模块Id");
                            slave.setResourceId(String.valueOf(relation.getModuleId()));
                        }

                        slave.setResourceType(relation.getTeachMethod());

                        slave.setSlaveStatusCode(Constant.SUB_ORDER_FINISHED);
                        slaves.add(slave);
                    }
                }
                if (slaves.size() > 0) {
                    this.log_pay.info("slaves：" + slaves.size() + ", 当前的子订单为：" + slaves.get(0));
                    this.studentPaySlaveServiceImpl.batchInsert(slaves);
                }

            }
        }
        this.log_pay.info("后台商品插入子订单信息成功, 学生ID为: " + stu.getId() + "商品ID: " + com.getId());

        // 插入完子订单之后，发送学员购买完成的站内信
        this.insertUserMessege(stu, com);
    }

    /**
     * @Description: 收款完成之后，给学员发送站内信
     * @author zx
     * @date 2015-8-20 下午3:40:53
     * @version 1.0
     * @param stu
     * @param com
     */
    public void insertUserMessege(Student stu, Commodity com) {
        // 判断当前是否需要发送站内信
        CompanyFunctionSet set = new CompanyFunctionSet();
        set.setCompanyId(stu.getCompanyId());
        set.setFunctionCode("COMPANY_FUNCTION_BUY");
        set.setStatus("1");
        List<CompanyFunctionSet> functionSets = this.companyFunctionSetServiceImpl.findCompanyFunctionSet(set);
        if (functionSets.size() > 0) {
            CompanyFunctionSet functionSet = functionSets.get(0);
            String userName = stu.getName();
            if (userName == null || userName == "") {
                userName = stu.getMobile();
                if (userName == null || userName == "") {
                    UsersFront usersFront = this.usersFrontServiceImpl.findUsersFrontById(stu.getUserId());
                    userName = usersFront.getUsername();
                    if (userName == null) {
                        userName = "";
                    }
                }
            }
            if (functionSet.getContent() != null) {
                String content = functionSet.getContent().replaceAll("【username】", userName).replaceAll("【coursename】", com.getName());
                UserMessage um = new UserMessage();
                um.setContent(content);
                um.setUserId(stu.getUserId());
                Integer sameCount = this.companyStudentMessageServiceImpl.findSameCount(um);
                if (sameCount > 0) {
                    this.log_pay.info("已经有相同数据不能再次插入");
                } else {
                    this.log_pay.info("插入信息");
                    Date date = new Date();
                    CompanyStudentMessage message = new CompanyStudentMessage();
                    message.setTitle("感谢您购买我们的课程");
                    message.setContent(content);
                    message.setMessageType("STUDENT_MESSAGE_OTHER");
                    message.setCompanyId(stu.getCompanyId());
                    message.setCreateTime(date);
                    this.companyStudentMessageServiceImpl.insert(message);

                    UserMessage userMessage = new UserMessage();
                    userMessage.setUserId(stu.getUserId());
                    userMessage.setMessageId(message.getId());
                    userMessage.setReadFlag(0);
                    this.companyStudentMessageServiceImpl.insertUserMessage(userMessage);

                    this.log_pay.info("收款完成给学员发送站内信: " + message.toString());
                }
            }
        } else {
            this.log_pay.info("该机构没有设置发送站内信的功能，故此不发送站内信");
        }
    }

    // 判断数组中是否包含某元素
    public Boolean isContains(String[] strs, String schoolId) {
        // int count=0;
        for (String str : strs) {
            if (schoolId.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * Class Name: PayOrderController.java
     *
     * @Description: 根据一级项目拿到班型
     * @author 权飞虎
     * @date 2015年5月15日 下午2:35:55
     * @modifier
     * @modify-date 2015年5月15日 下午2:35:55
     * @version 1.0
     * @param itemOneId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getSecondItem")
    public List<SysConfigItem> getTwoItem(Integer itemOneId) {
        if (itemOneId == null || "".equals(itemOneId)) {
            // List<SysConfigItem> itemOnes =
            // sysConfigItemServiceImpl.findItemByCompanyId(1,
            // WebUtils.getCurrentCompanyId());
            List<SysConfigItem> itemOnes = this.sysConfigItemServiceImpl.findSysConfigItemByPid("1", null, WebUtils.getCurrentCompanyId(),
                    WebUtils.getCurrentSchoolId());
            List<SysConfigItem> itemSeconds = this.sysConfigItemServiceImpl.findSysConfigItemByPid("2", itemOnes.get(0).getId(), WebUtils.getCurrentCompanyId(),
                    WebUtils.getCurrentSchoolId());
            return itemSeconds;
        }
        List<SysConfigItem> itemSeconds = this.sysConfigItemServiceImpl.findSysConfigItemByPid("2", itemOneId, WebUtils.getCurrentCompanyId(),
                WebUtils.getCurrentSchoolId());
        // System.out.println(itemSeconds);
        return itemSeconds;
    }

    @ResponseBody
    @RequestMapping(value = "getClassType")
    public List<ClassType> getClassType(Integer itemOneId) {
        if (itemOneId == null || "".equals(itemOneId)) {
            ClassType classType = new ClassType();
            classType.setCompanyId(WebUtils.getCurrentCompanyId());
            return this.classTypeServiceImpl.findClassTypeList(classType);
        }

        List<ClassType> list = this.classTypeServiceImpl.findByItemOne(itemOneId, WebUtils.getCurrentCompanyId());
        // System.out.println(list);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "getClassType2")
    public List<ClassType> getClassType(ClassType classType) {
        /*
         * classType.setCompanyId(WebUtils.getCurrentCompanyId());
         * classType.setDelFlag(0);
         */
        List<ClassType> list = this.classTypeServiceImpl.findByItem(WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId(), classType.getItemOneId(),
                classType.getItemSecondId());
        // for (int i = 0; i < list.size(); i++) {
        // String[] strs = list.get(i).getSchoolsId().split(",");
        // System.out.println(Arrays.asList(strs));
        // System.out.println(WebUtils.getCurrentSchoolId());
        // if(!isContains(strs,WebUtils.getCurrentSchoolId().toString())){
        // list.remove(i);
        // i--;
        // }
        //
        // }
        return list;
    }

    /**
     *
     * Class Name: PayOrderController.java
     *
     * @Description: 课程包订单总额计算
     * @author zhang.zx
     * @date 2016年3月29日 下午5:40:33
     * @modifier
     * @modify-date 2016年3月29日 下午5:40:33
     * @version 1.0
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/classPackageOrder")
    public Map queryOrderManages(OrderManage search) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        if ("sevnday".equals(search.getTimeMark())) {
            search.setTimeLen(7);
        }
        search.setCompanyId(companyId);
        return this.payOrderServiceImpl.findPayOrderTotalMoney(search);
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
}
