package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.yuxin.wx.api.company.ICompanyCouponsLibService;
import com.yuxin.wx.api.company.ICompanyCouponsPatchService;
import com.yuxin.wx.api.company.ICompanyMemberLevelConfigService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.company.CompanyCouponsLib;
import com.yuxin.wx.model.company.CompanyCouponsPatch;
import com.yuxin.wx.model.company.CompanyMemberLevelConfig;
import com.yuxin.wx.util.ClassPackageUtil;
import com.yuxin.wx.utils.ExcelUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.CompanyCouponsLibOrderVo;
import com.yuxin.wx.vo.company.CompanyCouponsLibVo;
import com.yuxin.wx.vo.company.CompanyCouponsPatchVo;
import com.yuxin.wx.vo.student.StudentClassLeanDetailVo;
import com.yuxin.wx.vo.student.StudentLessTimeVo;
import com.yuxin.wx.vo.student.StudentListVo;

/**
 * Controller of CompanyCouponsLib
 * @author chopin
 * @date 2016-6-20
 */
@Controller
@RequestMapping("/companyCouponsLib")
public class CompanyCouponsLibController {
	private Log log=LogFactory.getLog("log");
	@Autowired
	private ICompanyCouponsLibService companyCouponsLibServiceImpl;
	@Autowired
	private ICompanyCouponsPatchService companyCouponsPatchServiceImpl;
	@Autowired
	private ICompanyMemberLevelConfigService companyMemberLevelConfigServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyCouponsLib search){
		if (search == null) {
			search = new CompanyCouponsLib();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyCouponsLibServiceImpl.findCompanyCouponsLibByPage(search));
		return "companyCouponsLib/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyCouponsLib CompanyCouponsLib) {
		companyCouponsLibServiceImpl.insert(CompanyCouponsLib);
		return "redirect:/companyCouponsLib";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyCouponsLib CompanyCouponsLib) {
		companyCouponsLibServiceImpl.update(CompanyCouponsLib);
		return "redirect:/companyCouponsLib";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyCouponsLibServiceImpl.deleteCompanyCouponsLibById(id);
		return "redirect:/companyCouponsLib";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyCouponsLib getJson(Model model, @PathVariable Integer id){
		return companyCouponsLibServiceImpl.findCompanyCouponsLibById(id);
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
	 * Class Name: CompanyCouponsLibController.java
	 * @Description: 查询优惠码库byPatchId
	 * @author dongshuai
	 * @date 2016年6月21日 下午7:05:29
	 * @modifier
	 * @modify-date 2016年6月21日 下午7:05:29
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryLibsListByPatchId", method = RequestMethod.POST)
	public PageFinder<CompanyCouponsLib> queryLibsListByPatchId(String page,String id,HttpServletRequest request){
		//生成优惠码
		CompanyCouponsPatch ccp=companyCouponsPatchServiceImpl.findCompanyCouponsPatchById(Integer.parseInt(id));
		companyCouponsPatchServiceImpl.manageCouponsPatch(ccp,"saveAndSend",WebUtils.getCurrentUserId(request));
		//查看优惠码
		CompanyCouponsLib search=new CompanyCouponsLib();
		search.setPage(Integer.parseInt(page));
		search.setPageSize(10);
		search.setPatchId(Integer.parseInt(id));
		PageFinder<CompanyCouponsLib> pageFinder=companyCouponsLibServiceImpl.queryLibsListByPatchId(search);
		return pageFinder;
		
	}
	
	/**
	 * 
	 * Class Name: CompanyCouponsLibController.java
	 * @Description: 查询优惠券使用情况
	 * @author dongshuai
	 * @date 2016年6月22日 下午1:22:41
	 * @modifier
	 * @modify-date 2016年6月22日 下午1:22:41
	 * @version 1.0
	 * @param page
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryLibsListForOrderUsed", method = RequestMethod.POST)
	public PageFinder<CompanyCouponsLibOrderVo> queryLibsListForOrderUsed(String page,String id,String statusOrder,String usetimeOrder){
		log.info("优惠码使用情况查询");
		CompanyCouponsLibVo search=new CompanyCouponsLibVo();
		search.setPage(Integer.parseInt(page));
		search.setPageSize(5);
		if("up".equals(statusOrder)||"down".equals(statusOrder)){
			search.setStatusOrder(statusOrder);
		}
		if("up".equals(usetimeOrder)||"down".equals(usetimeOrder)){
			search.setUsetimeOrder(usetimeOrder);
		}
		search.setPatchId(Integer.parseInt(id));
		double begin = System.currentTimeMillis();
		PageFinder<CompanyCouponsLibOrderVo> pageFinder=companyCouponsLibServiceImpl.queryLibsForUseOrder(search);
		double end = System.currentTimeMillis(); 
		double time = end - begin;
		log.info("优惠券查询执行时间"+time / 1000 + "秒");
		log.info("优惠码使用情况查询");
		return pageFinder;
	}
	
	/**
	 * 
	 * Class Name: CompanyCouponsLibController.java
	 * @Description: 导出优惠券
	 * @author dongshuai
	 * @date 2016年6月22日 下午3:06:48
	 * @modifier
	 * @modify-date 2016年6月22日 下午3:06:48
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/exportCouponsLib/{id}")
	public ModelAndView exportCouponsLib(@PathVariable String id){
		CompanyCouponsPatchVo ccp=companyCouponsPatchServiceImpl.queryCouponsPatchById(Integer.parseInt(id));
		//优惠对象
		String  forCrowd="";
		if("COUPON_OBJECT_ALL".equals(ccp.getForCrowd())){
			forCrowd="所有用户";
		}
		if("COUPON_OBJECT_MEMBER".equals(ccp.getForCrowd())){
			forCrowd="会员 ( ";
			if(null!=ccp.getMemberList()){
				String[] memberIds=ccp.getMemberList().split(",");
				for (int i=0;i<memberIds.length;i++) {
					CompanyMemberLevelConfig cmlc = companyMemberLevelConfigServiceImpl.findCompanyMemberLevelConfigById(Integer.parseInt(memberIds[i]));
					if(null!=cmlc && null != cmlc.getName()){
						forCrowd+=(i==0?"":",")+cmlc.getName();
					}
				}
			}
			forCrowd+=" )";
		}
		if("COUPON_OBJECT_SOMEONE".equals(ccp.getForCrowd())){
			forCrowd="指定用户";
		}
		if("COUPON_OBJECT_STUDENT".equals(ccp.getForCrowd())){
			forCrowd="购买过课程的学员";
		}
		//优惠范围
		String  useRange="";
		switch (ccp.getCommodityType()) {
		case 1:
			if(ccp.getUseRange()==0){
				useRange="全部课程包";
			}else{
				useRange = ccp.getRangeItemPackageCategory();
			}
			break;
		default:
			if(ccp.getUseRange()==0){
				useRange="全部课程";
			}
			if(ccp.getUseRange()==1){
				useRange=ccp.getRangeItemOneName()+(null!=ccp.getRangeItemSecondName()?"-"+ccp.getRangeItemSecondName():"");
			}
			if(ccp.getUseRange()==2){
				useRange=ccp.getRangeItemOneName()+"-"+ccp.getRangeItemSecondName()+"-"+ccp.getRangeItemCourseName();
			}
			break;
		}
		
		CompanyCouponsLib search=new CompanyCouponsLib();
		search.setPatchId(Integer.parseInt(id));
		List<CompanyCouponsLib> list=companyCouponsLibServiceImpl.queryLibsListByPatchIdExport(search);
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (CompanyCouponsLib ccl : list) {
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put("code", ccl.getCode());
			dataMap.put("useRange", useRange);
			dataMap.put("forCrowd", forCrowd);
			if( null!=ccl.getTimeLimitFrom() && null!=ccl.getTimeLimitTo()){
				dataMap.put("time", "从 "+sdf.format(ccl.getTimeLimitFrom())+" 到 "+sdf.format(ccl.getTimeLimitTo()));
			}else{
				dataMap.put("time", "");
			}
			dataList.add(dataMap);
		}
		StringBuffer title = new StringBuffer("优惠码:code,优惠范围:useRange,使用期限:time");
		ViewFiles excel = new ViewFiles(); 
		HSSFWorkbook wb=new HSSFWorkbook();
		try{
			wb=ExcelUtil.newWorkbook(dataList, "sheet1", title.toString());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		Map<String,Object> view = new HashMap<String,Object>();
		view.put("workbook", wb);
		view.put("fileName","“"+ccp.getName()+"”优惠码.xls");
		CompanyCouponsPatch companyCouponsPatch_export=companyCouponsPatchServiceImpl.findCompanyCouponsPatchById(Integer.parseInt(id));
		companyCouponsPatch_export.setStatus("1");
		companyCouponsPatchServiceImpl.update(companyCouponsPatch_export);
		return new ModelAndView(excel,view);
	}
}
