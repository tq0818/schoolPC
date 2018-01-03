package com.yuxin.wx.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSONArray;
import com.yuxin.wx.api.company.ICompanyFootInfoService;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyNewStepService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysPageHeadFootService;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFootInfo;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyNewStep;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.system.SysPageHeadFoot;
import com.yuxin.wx.utils.FileUploadUtil;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.SysPageHeadFootVo;

/**
 * Controller of SysPageHeadFoot
 * @author chopin
 * @date 2015-4-11
 */
@Controller
@RequestMapping("/sysPageHeadFoot")
public class SysPageHeadFootController {
	private Log log=LogFactory.getLog("log");
	@Autowired
	private ISysPageHeadFootService sysPageHeadFootServiceImpl;
	
	@Autowired
	private ISysConfigSchoolService sysConfigSchoolServiceImpl;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private ICompanyNewStepService companyNewStepServiceImpl;
	
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	
	@Autowired
	private ICompanyFootInfoService companyFootInfoServiceImpl;
	
	@Autowired
	private ICompanyFunctionSetService CompanyFunctionSetServiceImpl;

	/**
	 * 跳转到页头配置页面
	 * @param model
	 * @return
	 */
	@SuppressWarnings("null")
	@RequestMapping(value="/showHead",method=RequestMethod.GET)
	public String ConfigHeadShow(SysPageHeadFootVo search, Model model){
		Integer companyId=WebUtils.getCurrentCompanyId();
		Company company=companyService.findCompanyById(companyId);
		if(company == null || "".equals(company)){
			return null;
		}
		//查询默认公司图标
		String companyPicUrl="http://"+propertiesUtil.getProjectImageUrl()+"/";
		company.setCompanyLogo(companyPicUrl+company.getCompanyLogo());
		model.addAttribute("company", company);
		SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(WebUtils.getCurrentSchoolId());
		model.addAttribute("school", school);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyFunctionSet set = new CompanyFunctionSet();
		set.setCompanyId(WebUtils.getCurrentCompanyId());
		set.setFunctionCode("HEAD_THEMES");
		CompanyFunctionSet set1 = CompanyFunctionSetServiceImpl.findCompanyUseCourse(set);
		if(set1==null){
			set.setContent("header-default");
			CompanyFunctionSetServiceImpl.updateByComIdAndCode(set);
		}else{
			if(set1.getContent()==null||"".equals(set1.getContent())){
				set.setContent("header-default");
				CompanyFunctionSetServiceImpl.updateByComIdAndCode(set);
			}
			set=set1;
		}
		model.addAttribute("set",set);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		//return "system/configHead";
		return "system/systemHead";
	}
	
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 异步加载标题
	 * @author zhang.zx
	 * @date 2015年4月24日 下午6:30:22
	 * @modifier
	 * @modify-date 2015年4月24日 下午6:30:22
	 * @version 1.0
	 * @param search
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showTitles",method=RequestMethod.POST)
	public String queryHeadTitles(SysPageHeadFootVo search,Model model){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setParentId(0);
		search.setPageType("head");
		List<SysPageHeadFootVo> IndexheadTitleList=sysPageHeadFootServiceImpl.findSysPageHeadFoot(search);
		Iterator it = IndexheadTitleList.iterator();
		while(it.hasNext()){
			SysPageHeadFootVo sys = (SysPageHeadFootVo) it.next();
			//去掉学员心声和题库
			if(sys.getId() == 3509 || sys.getId() == 3498){
				it.remove();
			}
		}
		model.addAttribute("IndexheadTitleList", IndexheadTitleList);
		//查询所有二级标题
		List<SysPageHeadFootVo> sysPageChildHead=sysPageHeadFootServiceImpl.queryTwoSysPagerList();
		model.addAttribute("sysPageChildHead", sysPageChildHead);
		return "system/HeadTitleAjax1";
	}
	
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 修改页头标题
	 * @author zhang.zx
	 * @date 2015年4月23日 下午7:11:01
	 * @modifier
	 * @modify-date 2015年4月23日 下午7:11:01
	 * @version 1.0
	 * @param SysPageHeadFoot
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateTitle",method=RequestMethod.POST)
	public SysPageHeadFoot updateHeadTitle(SysPageHeadFoot SysPageHeadFoot,HttpServletRequest request){
		this.wrapSysPageHeadContent("update", SysPageHeadFoot,request);
		sysPageHeadFootServiceImpl.update(SysPageHeadFoot);
		return sysPageHeadFootServiceImpl.findSysPageHeadFootById(SysPageHeadFoot.getId());
	}
	
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 修改页头标题状态
	 * @author zhang.zx
	 * @date 2015年4月23日 下午8:10:14
	 * @modifier
	 * @modify-date 2015年4月23日 下午8:10:14
	 * @version 1.0
	 * @param SysPageHeadFoot
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateStatus",method=RequestMethod.POST)
	public SysPageHeadFoot updateHeadTitleStatus(SysPageHeadFoot sysPageHeadFoot,HttpServletRequest request){
		sysPageHeadFoot.setUpdateTime(new Date());
		sysPageHeadFoot.setUpdator(WebUtils.getCurrentUserId(request));
		sysPageHeadFootServiceImpl.changeStatus(sysPageHeadFoot);
		return sysPageHeadFootServiceImpl.findSysPageHeadFootById(sysPageHeadFoot.getId());
	}
	
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description:添加页头标题
	 * @author zhang.zx
	 * @date 2015年4月23日 下午9:15:49
	 * @modifier
	 * @modify-date 2015年4月23日 下午9:15:49
	 * @version 1.0
	 * @param sysPageHeadFoot
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addHeadTitle",method=RequestMethod.POST)
	public List<SysPageHeadFootVo> addHeadTitle(SysPageHeadFoot sysPageHeadFoot,HttpServletRequest request){
		this.wrapSysPageHeadContent("add", sysPageHeadFoot,request);
		sysPageHeadFootServiceImpl.insertHeadTitle(sysPageHeadFoot);
		//新手任务部分
		List<CompanyNewStep> list = companyNewStepServiceImpl.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
		if(list.size()<=0){
			CompanyNewStep newStep = new CompanyNewStep();
			newStep.setCompanyId(WebUtils.getCurrentCompanyId());
			newStep.setSchoolHead(1);
			companyNewStepServiceImpl.insert(newStep);
		}else{
			CompanyNewStep newStep = list.get(0);
			newStep.setSchoolHead(1);
			companyNewStepServiceImpl.update(newStep);
		}
			SysPageHeadFootVo search=new SysPageHeadFootVo();
			search.setCompanyId(WebUtils.getCurrentCompanyId());
			search.setSchoolId(WebUtils.getCurrentSchoolId());
			search.setParentId(0);
			search.setPageType("head");
			search.setValidFlag(1);
			return sysPageHeadFootServiceImpl.findSysPageHeadFoot(search);
	}
	

	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 查询公司可用标题数量
	 * @author zhang.zx
	 * @date 2015年4月24日 上午10:01:10
	 * @modifier
	 * @modify-date 2015年4月24日 上午10:01:10
	 * @version 1.0
	 * @param sysPageHeadFoot
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryIsUseCount",method=RequestMethod.POST)
	public Integer queryHeadTitleCount(SysPageHeadFoot sysPageHeadFoot){
		sysPageHeadFoot.setCompanyId(WebUtils.getCurrentCompanyId());
		sysPageHeadFoot.setPageType("head");
		sysPageHeadFoot.setParentId(0);
		return sysPageHeadFootServiceImpl.findIsUseHeadTitle(sysPageHeadFoot);
	}
	
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 修改公司logo
	 * @author zhang.zx
	 * @date 2015年4月24日 下午6:00:07
	 * @modifier
	 * @modify-date 2015年4月24日 下午6:00:07
	 * @version 1.0
	 * @param multiPartRquest
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateCompanyIco",method=RequestMethod.POST)
	public String updateCompanyIco(Company company, MultipartRequest multiPartRquest,HttpServletRequest req){
		MultipartFile multipartFile = multiPartRquest.getFile("imgData");
		String realPath=null;
		String url=null;
		try {
			realPath = FileUtil.upload(multipartFile, "company", WebUtils.getCurrentCompanyId()+"");
		} catch (Exception e) {
			log.error("文件上传失败!",e);
			e.printStackTrace();
		}
		if(realPath!=null){
			company.setId(WebUtils.getCurrentCompanyId());
			company.setCompanyLogo(realPath);
			company.setUpdateTime(new Date());
			company.setUpdator(WebUtils.getCurrentUserId(req)+"");
			companyService.update(company);
			url="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
		}
		return "{\"url\":\""+url+"\"}";
	}
	
//	@ResponseBody
//	@RequestMapping(value="/updateCompanyPic",method=RequestMethod.POST)
//	public String updateCompanyIco(Company company,HttpServletRequest req,String imagesdata){
//		String url="";
//		if (imagesdata != null && !imagesdata.equals("")) {
//			//文件格式
//			String imgType=imagesdata.substring(11, imagesdata.indexOf(";"));
//			imagesdata = imagesdata.substring(imagesdata.indexOf(",") + 1);
//			imagesdata = imagesdata.replace(" ", "+");
//			BASE64Decoder decoder = new BASE64Decoder();
//			try {
//				// Base64解码
//				byte[] b = decoder.decodeBuffer(imagesdata);
//				InputStream input = new ByteArrayInputStream(b);
//			    String realPath = FileUtil.upload(input, "company", WebUtils.getCurrentCompanyId()+"");
//			    company.setId(WebUtils.getCurrentCompanyId());
//				company.setCompanyLogo(realPath);
//				company.setUpdateTime(new Date());
//				company.setUpdator(WebUtils.getCurrentUserId(req)+"");
//				companyService.update(company);
//				url="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
//			} catch (Exception e) {
//				log.info("文件上传失败!");
//				e.printStackTrace();
//				log.error(e.getStackTrace());
//			}
//	    }else{
//	    	log.info("文件为空");
//	    }
//	  return url;
//	}
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 查询一级可用标题
	 * @author zhang.zx
	 * @date 2015年4月24日 下午6:36:01
	 * @modifier
	 * @modify-date 2015年4月24日 下午6:36:01
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryOneHeadTitle",method=RequestMethod.POST)
	public List<SysPageHeadFoot> findHeadIsUse(){
		SysPageHeadFoot sys=new SysPageHeadFoot();
		sys.setCompanyId(WebUtils.getCurrentCompanyId());
		sys.setPageType("head");
		return sysPageHeadFootServiceImpl.queryHeadIsUse(sys);
	}
	
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 封装数据
	 * @author zhang.zx
	 * @date 2015年4月23日 下午9:03:42
	 * @modifier
	 * @modify-date 2015年4月23日 下午9:03:42
	 * @version 1.0
	 */
	private void wrapSysPageHeadContent(String option,SysPageHeadFoot sysPageHeadFoot,HttpServletRequest request){
		if("add".equals(option)){
			sysPageHeadFoot.setCreator(WebUtils.getCurrentUserId(request));
			sysPageHeadFoot.setCreateTime(new Date());
			if(sysPageHeadFoot.getParentId()==null || "".equals(sysPageHeadFoot.getParentId())){
				sysPageHeadFoot.setParentId(0);
			}
		}
		sysPageHeadFoot.setCompanyId(WebUtils.getCurrentCompanyId());
		sysPageHeadFoot.setUpdateTime(new Date());
		sysPageHeadFoot.setPageType("head");
		sysPageHeadFoot.setSchoolId(WebUtils.getCurrentSchoolId());
		sysPageHeadFoot.setUpdator(WebUtils.getCurrentUserId(request));
		//sysPageHeadFoot.setValidFlag(1);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysPageHeadFoot search){
		if (search == null) {
			search = new SysPageHeadFoot();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysPageHeadFootServiceImpl.findSysPageHeadFootByPage(search));
		return "sysPageHeadFoot/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysPageHeadFoot SysPageHeadFoot) {
		sysPageHeadFootServiceImpl.insert(SysPageHeadFoot);
		return "redirect:/sysPageHeadFoot";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysPageHeadFoot SysPageHeadFoot) {
		sysPageHeadFootServiceImpl.update(SysPageHeadFoot);
		return "redirect:/sysPageHeadFoot";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysPageHeadFootServiceImpl.deleteSysPageHeadFootById(id);
		return "redirect:/sysPageHeadFoot";
	}
	
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 删除二级标题
	 * @author zhang.zx
	 * @date 2015年4月24日 下午6:00:20
	 * @modifier
	 * @modify-date 2015年4月24日 下午6:00:20
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteChildHeadTitle",method=RequestMethod.POST)
	public String delHeadTitle(Integer id){
		sysPageHeadFootServiceImpl.deleteSysPageHeadFootById(id);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysPageHeadFoot getJson(Model model, @PathVariable Integer id){
		return sysPageHeadFootServiceImpl.findSysPageHeadFootById(id);
	}
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 跳转到页脚配置页
	 * @author 权飞虎
	 * @date 2015年4月24日 下午5:57:10
	 * @modifier
	 * @modify-date 2015年4月24日 下午5:57:10
	 * @version 1.0
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toConfigFooter")
	public String toConfigFooter(Model model){
		//查询该公司的名称
		
		Company company = companyService.findCompanyById(WebUtils.getCurrentCompanyId());
		model.addAttribute("company", company);
		List<SysPageHeadFoot> foot = sysPageHeadFootServiceImpl.findByConpanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("foots", foot);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		//return "company/configFooter/configFooter";
		return "company/configFooter/systemFoot";
	}
	
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 根据登录公司id查询公司版权信息
	 * @author zhang.zx
	 * @date 2015年6月3日 下午12:16:40
	 * @modifier
	 * @modify-date 2015年6月3日 下午12:16:40
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/querySelfCompany",method=RequestMethod.POST)
	public Company querySelfCompany(){
		return companyService.findCompanyById(WebUtils.getCurrentCompanyId());
	} 
	
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 保存公司名称,版权,备案号
	 * @author 权飞虎
	 * @date 2015年4月24日 下午5:57:52
	 * @modifier
	 * @modify-date 2015年4月24日 下午5:57:52
	 * @version 1.0
	 * @param id	公司id
	 * @param name	所改字段
	 * @param value	要改的值
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="toSave",method=RequestMethod.POST)
	public String toSave(String name,String value){
		if(!isEnterpriseVersion()){
			return "error";
		}
		Company company = companyService.findCompanyById(WebUtils.getCurrentCompanyId());
		if("name".equals(name)){
			company.setCompanyName(value);
		}else if("copyright".equals(name)){
			company.setCopyright(value);
		}else if("icp".equals(name)){
			company.setRegistNo(value);
		}
		companyService.update(company);
		List<CompanyNewStep> list = companyNewStepServiceImpl.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
		if(list.size()<=0){
			CompanyNewStep newStep = new CompanyNewStep();
			newStep.setCompanyId(WebUtils.getCurrentCompanyId());
			newStep.setSchoolFoot(1);
			companyNewStepServiceImpl.insert(newStep);
		}else{
			CompanyNewStep newStep = list.get(0);
			newStep.setSchoolFoot(1);
			companyNewStepServiceImpl.update(newStep);
		}
		return "success";
	}
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 修改关于我们,联系我们,加入我们
	 * @author 权飞虎
	 * @date 2015年4月24日 上午10:22:37
	 * @modifier
	 * @modify-date 2015年4月24日 上午10:22:37
	 * @version 1.0
	 * @param flag	要修改的内容标记
	 * @param value	要修改的值
	 * @param id	公司id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="saveFoot",method=RequestMethod.POST)
	public String saveFoot(Integer flag,String value,String id){
		String name=null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", WebUtils.getCurrentCompanyId().toString());
		if(1==flag){
			name="关于我们";
		}else if(2==flag){
			name="联系我们";
		}else if(3==flag){
			name="隐私条款";
		}else if(4==flag){
			if(WebUtils.getCurrentCompanyId()==1644){
				name="爱亲公益";
			}else{
				name="法律声明";
			}
		}
		map.put("name", name);
		map.put("value", value);
		map.put("schoolId", WebUtils.getCurrentSchoolId()+"");
		
		String str = sysPageHeadFootServiceImpl.selectByCpIdAndName(map);
		if(str!=null){
			map.put("updateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			map.put("updator", WebUtils.getCurrentUser().getId().toString());
			
			sysPageHeadFootServiceImpl.updateByNameAndCpId(map);
		}else{
			SysPageHeadFoot pageHeadFoot = new SysPageHeadFoot();
			pageHeadFoot.setCompanyId(WebUtils.getCurrentCompanyId());
			pageHeadFoot.setContent(value);
			pageHeadFoot.setName(name);
			pageHeadFoot.setPageType("foot");
			pageHeadFoot.setValidFlag(1);
			pageHeadFoot.setParentId(0);
			pageHeadFoot.setSchoolId(WebUtils.getCurrentSchoolId());
			pageHeadFoot.setCreateTime(new Date());
			pageHeadFoot.setCreator(WebUtils.getCurrentUser().getId());
			sysPageHeadFootServiceImpl.insert(pageHeadFoot);
			List<CompanyNewStep> list = companyNewStepServiceImpl.findCompanyNewStepByCompany(WebUtils.getCurrentCompanyId());
			if(list.size()<=0){
				CompanyNewStep newStep = new CompanyNewStep();
				newStep.setCompanyId(WebUtils.getCurrentCompanyId());
				newStep.setSchoolFoot(1);
				companyNewStepServiceImpl.insert(newStep);
			}else{
				CompanyNewStep newStep = list.get(0);
				newStep.setSchoolFoot(1);
				companyNewStepServiceImpl.update(newStep);
			}
			
		}
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="selectByCpIdAndName",method=RequestMethod.POST)
	public String selectByCpIdAndName(String id,String flag){
		
		String name=null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		if("1".equals(flag)){
			name="关于我们";
		}else if("2".equals(flag)){
			name="联系我们";
		}else if("3".equals(flag)){
			name="隐私条款";
		}else if("4".equals(flag)){
			if(WebUtils.getCurrentCompanyId()==1644){
				name="爱亲公益";
			}else{
				name="法律声明";
			}
		}
		map.put("name", name);
		String content = sysPageHeadFootServiceImpl.selectByCpIdAndName(map);
		
		return content;
	}
	@ResponseBody
	@RequestMapping(value="upload")
	public String upload(HttpServletRequest request,HttpServletResponse response,MultipartRequest multiPartRequest) throws IOException{
		//Integer userId=WebUtils.getCurrentUserId(request);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(new Date());
		PrintWriter out=response.getWriter();
		MultipartFile multipartFile = multiPartRequest.getFile("upload");
		String fileName = multipartFile.getOriginalFilename();
		String newFileName=FileUploadUtil.getlnstance().getNewFileName(fileName);
		String realPath = FileUploadUtil.getlnstance().saveFileUpload(
				multipartFile,
				propertiesUtil.getImageServiceRealPath()+"footer/"+time,newFileName);
		//图片回显路径
		String callUrl=propertiesUtil.getImageServicePath()+"footer/"+time+"/"+newFileName;
		 // 将上传的图片的url返回给ckeditor
        String callback = request.getParameter("CKEditorFuncNum");
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction("
                + callback + ",'" + callUrl + "',''" + ")");
        out.println("</script>");
        return null;
		
	}
	/**
	 * 判断是否企业标准版
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="isEnterpriseVersion",method=RequestMethod.POST)
	public Boolean isEnterpriseVersion(){
		boolean isSelfNet=false;
		Company company = companyService.findCompanyById(WebUtils.getCurrentCompanyId());
		Integer serviceLeverl=company.getMemberLevel();
//		System.out.println(serviceLeverl);
		if(serviceLeverl!=null&&serviceLeverl>=40){
			isSelfNet=true;
		}
		return isSelfNet;
	}
	
	
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 查询分校
	 * @author zhang.zx
	 * @date 2015-4-13
	 * @modifier
	 * @modify-date 2015-4-13
	 * @version 1.0
	 * @param companyId
	 * @param search
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/showHeadChoose",method=RequestMethod.POST)
	public List<SysConfigSchool> querySysPageHeadDetail(HttpServletRequest request,Model model){
		Integer companyId=WebUtils.getCurrentCompanyId();
		List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
		System.out.println(schoolList.size());
		for(SysConfigSchool sc:schoolList){
			sc.setSchoolName(sc.getSchoolName().trim());
		}
		return schoolList;
	}
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 查询特色推荐下的标题
	 * @author zhang.zx
	 * @date 2015年4月13日 下午5:37:35
	 * @modifier
	 * @modify-date 2015年4月13日 下午5:37:35
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/showSpecial",method=RequestMethod.POST)
	public List<SysPageHeadFootVo> querySysPageHeadChoose(SysPageHeadFootVo search,HttpServletRequest request,Model model){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setPageType("head");
		List<SysPageHeadFootVo> arr=sysPageHeadFootServiceImpl.findSysPageHeadFoot(search);
		return arr;
	}
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description:查询默认分校
	 * @author zhang.zx
	 * @date 2015-4-13
	 * @modifier
	 * @modify-date 2015-4-13
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryDefaultSchool",method=RequestMethod.POST)
	public SysConfigSchool queryDefaultSchool(Integer schoolId, HttpServletRequest request,HttpServletResponse response){
		Integer id= sysConfigSchoolServiceImpl.findDefaultSchool(WebUtils.getCurrentCompanyId());
		return sysConfigSchoolServiceImpl.findSysConfigSchoolById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/queryIco",method=RequestMethod.POST)
	public Company queryCompany(HttpServletRequest request){
		Company company=companyService.findCompanyById(WebUtils.getCurrentCompanyId());
		if(company == null || "".equals(company)){
			return null;
		}
		String companyPicUrl="http://"+propertiesUtil.getProjectImageUrl()+"/";
		company.setCompanyLogo(companyPicUrl+company.getCompanyLogo());
		return company;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryHeadContent",method=RequestMethod.POST)
	public List<SysPageHeadFootVo> queryHeadContentBySchoolId(SysPageHeadFootVo search,HttpServletRequest request){
		Integer companyId=WebUtils.getCurrentCompanyId();
		search.setCompanyId(companyId);
		search.setPageType("head");
		search.setParentId(0);
		List<SysPageHeadFootVo> arr=sysPageHeadFootServiceImpl.findSysPageHeadFoot(search);
		for(SysPageHeadFootVo s:arr){
			SysPageHeadFootVo search1=new SysPageHeadFootVo();
			search1.setCompanyId(WebUtils.getCurrentCompanyId());
			search1.setPageType("head");
			search1.setParentId(s.getId());
			List<SysPageHeadFootVo> arr1=sysPageHeadFootServiceImpl.findSysPageHeadFoot(search1);
			s.setCount(arr1.size());
		}
		return arr;
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
	@RequestMapping(value="/orderSortTitle")
	public String orderSortTitle(HttpServletRequest request){
		List<SysPageHeadFootVo> arr=JSONArray.parseArray(request.getParameter("list"),SysPageHeadFootVo.class);
		for(SysPageHeadFootVo sys:arr){
			SysPageHeadFoot s=new SysPageHeadFoot();
			s.setId(sys.getId());
			s.setSequence(sys.getSequence());
			sysPageHeadFootServiceImpl.update(s);
		}
		return "success";
	}
	
	//--------------新版页尾开发----------------------
	/**
	 * 根据配置查询页尾信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/queryByConfigId/{id}")
	public String queryFootContentByConfigId(@PathVariable Integer id,Model model){
		SysPageHeadFoot foot=new SysPageHeadFoot();
		foot.setCompanyId(WebUtils.getCurrentCompanyId());
		foot.setConfigId(id);
		foot.setParentId(0);
		foot.setPageType("foot");
		List<SysPageHeadFootVo> foots=sysPageHeadFootServiceImpl.queryFootContentsByConfigId(foot);
		model.addAttribute("foots", foots);
		return "system/footAjax_sys";
	}
	
	@RequestMapping(value="/queryFootFlexById/{id}")
	public String showFootContents(@PathVariable Integer id,Model model){
		SysPageHeadFoot foot=new SysPageHeadFoot();
		foot.setCompanyId(WebUtils.getCurrentCompanyId());
		foot.setConfigId(id);
		foot.setParentId(0);
		foot.setPageType("foot");
		List<SysPageHeadFootVo> foots=sysPageHeadFootServiceImpl.queryFootContentsByConfigId(foot);
		model.addAttribute("foots", foots);
		//查询所有二级标题
		SysPageHeadFoot search=new SysPageHeadFoot();
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		List<SysPageHeadFoot> sysPageChild=sysPageHeadFootServiceImpl.queryFootFlex(search);
		model.addAttribute("sysPageChild", sysPageChild);
		return "system/footAjax_flex";
	}
	
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 查询页尾预览内容
	 * @author zhang.zx
	 * @date 2016年3月1日 下午4:29:21
	 * @modifier
	 * @modify-date 2016年3月1日 下午4:29:21
	 * @version 1.0
	 * @param id
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryFootShowContent/{id}")
	public List<SysPageHeadFootVo> queryFootShowContent(@PathVariable Integer id,Model model){
		SysPageHeadFoot foot=new SysPageHeadFoot();
		foot.setCompanyId(WebUtils.getCurrentCompanyId());
		foot.setConfigId(id);
		foot.setPageType("foot");
		return sysPageHeadFootServiceImpl.queryFootContentsByConfigId(foot);
	}
	
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 保存页尾信息
	 * @author zhang.zx
	 * @date 2016年3月1日 下午6:19:43
	 * @modifier
	 * @modify-date 2016年3月1日 下午6:19:43
	 * @version 1.0
	 * @param foot
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveFoots")
	public SysPageHeadFoot saveFoots(SysPageHeadFoot foot){
		foot.setCompanyId(WebUtils.getCurrentCompanyId());
		foot.setSchoolId(WebUtils.getCurrentSchoolId());
		foot.setValidFlag(1);
		if(null!=foot.getParentId() && !"".equals(foot.getParentId())){
			foot.setParentId(foot.getParentId());
		}else{
			foot.setParentId(0);
		}
		foot.setPageType("foot");
		foot.setCreateTime(new Date());
		foot.setCreator(WebUtils.getCurrentUser().getId());
		foot.setUpdateTime(new Date());
		foot.setUpdator(WebUtils.getCurrentUser().getId());
		sysPageHeadFootServiceImpl.insert(foot);
		return sysPageHeadFootServiceImpl.findSysPageHeadFootById(foot.getId());
	}

	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 修改页尾
	 * @author zhang.zx
	 * @date 2016年3月2日 下午5:46:50
	 * @modifier
	 * @modify-date 2016年3月2日 下午5:46:50
	 * @version 1.0
	 * @param foot
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateFoots")
	public String updateFoots(SysPageHeadFoot foot){
		foot.setCompanyId(WebUtils.getCurrentCompanyId());
		foot.setSchoolId(WebUtils.getCurrentSchoolId());
		foot.setUpdateTime(new Date());
		foot.setUpdator(WebUtils.getCurrentUser().getId());
		sysPageHeadFootServiceImpl.update(foot);
		return "success";
	}
	
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 修改页尾logo
	 * @author zhang.zx
	 * @date 2016年3月2日 下午3:30:16
	 * @modifier
	 * @modify-date 2016年3月2日 下午3:30:16
	 * @version 1.0
	 * @param company
	 * @param multiPartRquest
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateCompanyFootIco",method=RequestMethod.POST)
	public String updateCompanyFootIco(CompanyFootInfo footInfo,String type, MultipartRequest multiPartRquest,HttpServletRequest req){
		MultipartFile multipartFile = multiPartRquest.getFile("imgData");
		String realPath=null;
		String url=null;
		try {
			realPath = FileUtil.upload(multipartFile, "company", WebUtils.getCurrentCompanyId()+"");
		} catch (Exception e) {
			log.info("文件上传失败!",e);
			e.printStackTrace();
		}
		if(realPath!=null){
			CompanyFootInfo footContent=companyFootInfoServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
			if(null!=footContent){
				footInfo.setId(footContent.getId());
				if("logo".equals(type)){
					footInfo.setLogoPic(realPath);
				}else{
					String path="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
					//try {
						footInfo.setTencentWechat(path);
//					} catch (UnsupportedEncodingException e) {
//						log.info("微信图片路径转码失败");
//					}
				}
				companyFootInfoServiceImpl.update(footInfo);
			}else{
				footInfo.setCompanyId(WebUtils.getCurrentCompanyId());
				if("logo".equals(type)){
					footInfo.setLogoPic(realPath);
				}else{
					String path="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
					footInfo.setTencentWechat(path);
				}
				companyFootInfoServiceImpl.insert(footInfo);
			}
			url="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
		}
		return "{\"url\":\""+url+"\"}";
	}
	
	/**
	 * 
	 * Class Name: SysPageHeadFootController.java
	 * @Description: 查询页尾所有信息
	 * @author zhang.zx
	 * @date 2016年3月2日 下午3:48:14
	 * @modifier
	 * @modify-date 2016年3月2日 下午3:48:14
	 * @version 1.0
	 * @param id
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryFootContents/{id}")
	public List<SysPageHeadFootVo> queryFootContents(@PathVariable Integer id,Model model){
		SysPageHeadFoot foot=new SysPageHeadFoot();
		foot.setCompanyId(WebUtils.getCurrentCompanyId());
		foot.setConfigId(id);
		foot.setPageType("foot");
		foot.setParentId(0);
		List<SysPageHeadFootVo> arr=sysPageHeadFootServiceImpl.queryFootContentsByConfigId(foot);
		if(arr.size()>0){
			for(SysPageHeadFootVo footp:arr){
				if(null!=footp){
					SysPageHeadFoot search=new SysPageHeadFoot();
					search.setParentId(footp.getId());
					search.setCompanyId(WebUtils.getCurrentCompanyId());
					search.setPageType("foot");
					List<SysPageHeadFootVo> list=sysPageHeadFootServiceImpl.queryFootContentByParentId(search);
					footp.setList(list);
				}
			}
		}
		return arr;
	}
	
	/**
	 * wz
	 * 主题颜色修改
	 * @param content
	 * @return
	 */
	@SuppressWarnings("null")
	@ResponseBody
	@RequestMapping(value="/updateThemes")
	public String updateThemes(String content){
		
		CompanyFunctionSet set = new CompanyFunctionSet();
		set.setCompanyId(WebUtils.getCurrentCompanyId());
		set.setFunctionCode("HEAD_THEMES");
		CompanyFunctionSet set1 = CompanyFunctionSetServiceImpl.findCompanyUseCourse(set);
		if(set1==null){
			set.setContent("header-default");
			CompanyFunctionSetServiceImpl.updateByComIdAndCode(set);
		}else{
			if(set1.getContent()==null||"".equals(set1.getContent())){
				set.setContent("header-default");
				CompanyFunctionSetServiceImpl.updateByComIdAndCode(set);
			}else{
				set.setContent(content);
				CompanyFunctionSetServiceImpl.updateByComIdAndCode(set);
			}
		}
		CompanyFootInfo companyInfo=new CompanyFootInfo();
		companyInfo.setCompanyId(WebUtils.getCurrentCompanyId());
		CompanyFootInfo companyInfo1=companyFootInfoServiceImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
		String theme = getFootThemeByHeadTheme(content);
		if(null==companyInfo1){								
			companyInfo.setThemes(theme);
			companyFootInfoServiceImpl.insert(companyInfo);
		}else{
			//修改页尾主题颜色
			companyInfo.setThemes(theme);
			companyFootInfoServiceImpl.updateByCompanyId(companyInfo);
		}
		return "success";
	}
	
	private String getFootThemeByHeadTheme(String theme){
		String footTheme="footer-white";
		theme=(theme.split("-"))[1];
		String[] themes={"footer-default","footer-gray","footer-white","footer-red","footer-cyan","footer-orange","footer-green","footer-blue"};
		for (int i = 0; i < themes.length; i++) {
			footTheme = (themes[i].split("-"))[1];
			if(footTheme.equals(theme)){
				return themes[i];
			}
		}
		return footTheme;
	}
	
}
