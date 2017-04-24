package com.yuxin.wx.controller.certificate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.yuxin.wx.api.certificate.ICertificateUserRelationService;
import com.yuxin.wx.common.ExcelSheetEntity;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.ViewFiles;
import com.yuxin.wx.model.certificate.CertificateUserRelation;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.utils.EntityUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.certificate.ViewCertificateVo;
import com.yuxin.wx.vo.student.StudentVo;

/**
 * Controller of CertificateUserRelation
 * @author chopin
 * @date 2016-9-22
 */
@Controller
@RequestMapping("/certificateUserRelation")
public class CertificateUserRelationController {
	
	@Autowired
	private ICertificateUserRelationService certificateUserRelationServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CertificateUserRelation search){
		if (search == null) {
			search = new CertificateUserRelation();
			// search.setPageSize(20);
		}
		model.addAttribute("list", certificateUserRelationServiceImpl.findCertificateUserRelationByPage(search));
		return "certificateUserRelation/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CertificateUserRelation CertificateUserRelation) {
		certificateUserRelationServiceImpl.insert(CertificateUserRelation);
		return "redirect:/certificateUserRelation";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CertificateUserRelation CertificateUserRelation) {
		certificateUserRelationServiceImpl.update(CertificateUserRelation);
		return "redirect:/certificateUserRelation";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		certificateUserRelationServiceImpl.deleteCertificateUserRelationById(id);
		return "redirect:/certificateUserRelation";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CertificateUserRelation getJson(Model model, @PathVariable Integer id){
		return certificateUserRelationServiceImpl.findCertificateUserRelationById(id);
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
	
	@ResponseBody
	@RequestMapping(value="/releaseCertificate",method=RequestMethod.POST)
	public String releaseCertificate(CertificateUserRelation item,HttpServletRequest req){
		if(item != null && item.getCerId() != null && item.getCourseId() != null && item.getUserId()!= null){
			List<CertificateUserRelation> list = certificateUserRelationServiceImpl.findCertificateUserRelationByPage(item);
			if(list!= null && list.size()>0){
				certificateUserRelationServiceImpl.update(item);
				return "success";
			}else{
				item.setCompanyId(WebUtils.getCurrentCompanyId());
				item.setStatus(0);
				item.setReceiveTime(new Date());
				certificateUserRelationServiceImpl.insert(item);
				return "success";
			}
		}
		return "error";
	}
	
	@ResponseBody
	@RequestMapping(value="/viewCertificateList",method=RequestMethod.POST)
	public PageFinder<ViewCertificateVo> viewCertificateList(ViewCertificateVo search,HttpServletRequest req,Model model){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<ViewCertificateVo> list = certificateUserRelationServiceImpl.queryUserAndCertificateInfo(search);
		Integer count = certificateUserRelationServiceImpl.queryUserAndCertificateInfoCount(search);
		Integer releaseCount = certificateUserRelationServiceImpl.queryReleaseCount(search);
		PageFinder<ViewCertificateVo> pageFinder = new PageFinder<ViewCertificateVo>(search.getPage(), search.getPageSize(), count, list);
		model.addAttribute("releaseCount", releaseCount);
		return pageFinder;
	}
	
	@ResponseBody
	@RequestMapping(value="/releaseCount",method=RequestMethod.POST)
	public Integer releaseCount(ViewCertificateVo search,HttpServletRequest req){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		if(search.getReleaseStatus()!= null&& search.getReleaseStatus() == 0){
			return 0;
		}
		Integer count = certificateUserRelationServiceImpl.queryReleaseCount(search);
		return count;
	}
	
	@RequestMapping(value = "/exportExcel",method=RequestMethod.POST)
	public ModelAndView exportExcel(ViewCertificateVo search,Model model,HttpServletRequest req,HttpServletResponse rep){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<ViewCertificateVo> al = new ArrayList<ViewCertificateVo>();
			al = certificateUserRelationServiceImpl.findListBySearchCondtion(search);
			if(al != null && al.size() >0){
				for (ViewCertificateVo ccv : al) {
					if(ccv.getStatus()==1){
						ccv.setCerStatus("启用");
					}else{
						ccv.setCerStatus("禁用");
					}
					if(ccv.getReleaseStatus() >0){
						ccv.setReleaseStatusStr("已发放");
					}else{
						ccv.setReleaseStatusStr("未发放");
					}
				}
			}
		ViewFiles excel = new ViewFiles();
		Map<String, Object> map = new HashMap<String, Object>();
		ExcelSheetEntity entity = ExcelSheetEntity.newInstance(
		        "证书名称:cerName,证书状态:cerStatus,学员姓名:stuName,手机号:mobile,用户名:username,发放状态:releaseStatusStr,发放日期:receiveTime",
		        al);
		map.put("entity", entity);
		map.put("fileName", "证书查看列表.xls");
		return new ModelAndView(excel, map);
	}
}
