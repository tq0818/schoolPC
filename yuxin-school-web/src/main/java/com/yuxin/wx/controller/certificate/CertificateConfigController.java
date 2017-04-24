package com.yuxin.wx.controller.certificate;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.yuxin.wx.api.certificate.ICertificateConfigService;
import com.yuxin.wx.api.certificate.ICertificateCourseRelationService;
import com.yuxin.wx.api.certificate.ICertificateUserRelationService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.common.BaseWebController;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.certificate.CertificateConfig;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.util.DateUtil;
import com.yuxin.wx.utils.CertificateUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.certificate.CertificateConfigVo;
import com.yuxin.wx.vo.certificate.CertificateUserRelationVo;

/**
 * Controller of CertificateConfig
 * @author chopin
 * @date 2016-9-22
 */
@Controller
@RequestMapping("/certificateConfig")
public class CertificateConfigController extends BaseWebController{
	
	@Autowired
	private ICertificateConfigService certificateConfigServiceImpl;
	@Autowired
	private ICertificateUserRelationService certificateUserRelationServiceImpl;
	@Autowired
	private IUsersFrontService usersFrontServiceImpl;
	@Autowired
	private ICertificateCourseRelationService certificateCourseServiceImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	Log log = LogFactory.getLog("log");
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CertificateConfig search){
		if (search == null) {
			search = new CertificateConfig();
			// search.setPageSize(20);
		}
		model.addAttribute("list", certificateConfigServiceImpl.findCertificateConfigByPage(search));
		return "certificateConfig/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CertificateConfig CertificateConfig) {
		certificateConfigServiceImpl.insert(CertificateConfig);
		return "redirect:/certificateConfig";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CertificateConfig CertificateConfig) {
		certificateConfigServiceImpl.update(CertificateConfig);
		return "redirect:/certificateConfig";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		certificateConfigServiceImpl.deleteCertificateConfigById(id);
		return "redirect:/certificateConfig";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CertificateConfig getJson(Model model, @PathVariable Integer id){
		return certificateConfigServiceImpl.findCertificateConfigById(id);
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
	 * Class Name: CertificateConfigController.java
	 * @Description: 查看证书
	 * @author dongshuai
	 * @date 2016年9月23日 下午7:39:55
	 * @modifier
	 * @modify-date 2016年9月23日 下午7:39:55
	 * @version 1.0
	 * @param id
	 * @param response
	 */
	@RequestMapping(value="/queryCertificate",method = RequestMethod.POST )
	public void queryCertificate(String cerId,String userId,String classId,HttpServletResponse response){
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", Integer.parseInt(userId));
		map.put("cerId", Integer.parseInt(cerId));
		map.put("classId", Integer.parseInt(classId));
		map.put("companyId",WebUtils.getCurrentCompanyId());
		CertificateUserRelationVo cur = certificateUserRelationServiceImpl.queryCertificateUserRelationByStuIdAndCerIdAndClassId(map);
		
		response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        
		BufferedImage cBufferedImage = drawCertificate(cur);
		try {  
	        ServletOutputStream sos = response.getOutputStream();
			ImageIO.write(cBufferedImage, "png", sos);
	        sos.close();
		} catch (IOException e) {
			log.error("查看证书失败！",e);
		}
	}

	public BufferedImage drawCertificate(CertificateUserRelationVo cur){
		CertificateUtil cfu = new CertificateUtil();
		BufferedImage image = null; 
		
		BufferedImage d = cfu.loadImageUrl("http://image.yunduoketang.com/certificate/certificate.png"); 
		
		String userName = "";
		if(null != cur.getStuName() && !"".equals(cur.getStuName())){
			userName = cur.getStuName();
		}else if(null != cur.getUserName() && !"".equals(cur.getUserName())){
			userName = cur.getUserName();
		}else if(null != cur.getMobile() && !"".equals(cur.getMobile())){
			userName = cur.getMobile();
		}
		
        String[] s = new String[]{userName,cur.getClassTypeName(),new SimpleDateFormat("MM/dd/yyyy").format(cur.getReceiveTime())};
        Color[]  c = new Color[]{Color.black,Color.black,Color.blue};
        Font[]   f = new Font[]{cfu.setFont("微软雅黑", 28),cfu.setFont("微软雅黑", 28),cfu.setFont("微软雅黑", 18)};
        int[] 	 x = new int[]{555,555,620};
        int[] 	 y = new int[]{245,355,442};
        for (int i = 0; i < s.length; i++) {
        	image=cfu.modifyImage(d,s[i],x[i],y[i],f[i],c[i]);  
		}
		return image;
	}
	
	@RequestMapping(value="/toCertificateList",method=RequestMethod.GET)
	public String toCertificateList(HttpServletRequest request,Model model){
		
		int companyId=WebUtils.getCurrentCompanyId();
		Company company=companyServiceImpl.findCompanyById(companyId);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("company", company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		return "/certificate/certificateList";
	}
	
	@ResponseBody
	@RequestMapping(value="/certificateList",method=RequestMethod.POST)
	public PageFinder<CertificateConfigVo> certificateList(CertificateConfig search,HttpServletRequest req,Model model){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setDelFlag(0);
		search.setPageSize(5);
		List<CertificateConfigVo> voList =  new ArrayList<CertificateConfigVo>();
		List<CertificateConfig> list = certificateConfigServiceImpl.findCertificateConfigByPage(search);
		Integer pageCount = certificateConfigServiceImpl.pageCount(search);
		if(list != null && list.size() >0){
			for (CertificateConfig cc : list) {
				if(cc!=null){
					CertificateConfigVo ccvo = new CertificateConfigVo();
					Integer userCount = certificateUserRelationServiceImpl.queryUserCountByCerId(cc.getId());
					ccvo.setReleaseNum(userCount);
					ccvo.setId(cc.getId());
					ccvo.setName(cc.getName());
					ccvo.setCompanyId(cc.getCompanyId());
					ccvo.setCreateTime(cc.getCreateTime());
					ccvo.setCreator(cc.getCreator());
					ccvo.setPassConditions(cc.getPassConditions());
					ccvo.setStatus(cc.getStatus());
					voList.add(ccvo);
				}
			}
			
		}
		PageFinder<CertificateConfigVo> pageFinder = new PageFinder<CertificateConfigVo>(search.getPage(), search.getPageSize(), pageCount, voList);
		return pageFinder;
	}
	
	@ResponseBody
	@RequestMapping(value="/saveOrUpdateCertificateConfig",method=RequestMethod.POST)
	public CertificateConfig saveOrUpdateCertificateConfig(CertificateConfig item,HttpServletRequest req,Model model){
		
		if(item == null)
			return null;
		item.setCompanyId(WebUtils.getCurrentCompanyId());
		Integer count = certificateConfigServiceImpl.checkNameIsExist(item);
		if(item!=null && item.getId() == null &&  count == 0){
			item.setCreator(WebUtils.getCurrentUserId(req));
			item.setStatus(1);
			item.setPassConditions("1");
			item.setDelFlag(0);
			certificateConfigServiceImpl.insert(item);
		}else if(item != null && item.getId()!= null){
			certificateCourseServiceImpl.deletByCerId(item.getId());
			certificateConfigServiceImpl.update(item);
		}
		return item;
	}
	
	@ResponseBody
	@RequestMapping(value="/checkNameIsExist",method=RequestMethod.POST)
	public String checkNameIsExist(CertificateConfig search,HttpServletRequest req){
		if(search != null && search.getName() != null && !"".equals(search.getName())){
			search.setCompanyId(WebUtils.getCurrentCompanyId());
			Integer count = certificateConfigServiceImpl.checkNameIsExist(search);
			return count>0?"true":"false";
		}
		return "error";
	}
	
	@ResponseBody
	@RequestMapping(value="queryAllCertificate",method=RequestMethod.POST)
	public List<CertificateConfig> queryAll(CertificateConfig search){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		return certificateConfigServiceImpl.queryAllCertificate(search);
	}
}
