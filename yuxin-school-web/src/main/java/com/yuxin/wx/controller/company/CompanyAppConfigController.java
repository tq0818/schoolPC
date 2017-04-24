package com.yuxin.wx.controller.company;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioFormat.Encoding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

import sun.misc.BASE64Decoder;

import com.yuxin.wx.api.company.ICompanyAppConfigService;
import com.yuxin.wx.api.company.ICompanyAppWelcomePicService;
import com.yuxin.wx.model.company.CompanyAppConfig;
import com.yuxin.wx.model.company.CompanyAppWelcomePic;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of CompanyAppConfig
 * @author chopin
 * @date 2016-5-27
 */
@Controller
@RequestMapping("/companyAppConfig")
public class CompanyAppConfigController {
	private Log log=LogFactory.getLog("log");
	@Autowired
	private ICompanyAppConfigService companyAppConfigServiceImpl;
	@Autowired
	private ICompanyAppWelcomePicService companyAppWelcomePicServiceImpl;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyAppConfig search){
		if (search == null) {
			search = new CompanyAppConfig();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyAppConfigServiceImpl.findCompanyAppConfigByPage(search));
		return "companyAppConfig/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/addConfig", method = RequestMethod.POST)
	public String add(CompanyAppConfig companyAppConfig) {
		Integer companyId=WebUtils.getCurrentCompanyId();
		CompanyAppConfig config=companyAppConfigServiceImpl.queryCompanyConfig(companyId);
		companyAppConfig.setCompanyId(companyId);
		if(null!=config){
			companyAppConfig.setId(config.getId());
			companyAppConfigServiceImpl.update(companyAppConfig);
		}else{
			companyAppConfigServiceImpl.insert(companyAppConfig);
		}
		return "success";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyAppConfig CompanyAppConfig) {
		companyAppConfigServiceImpl.update(CompanyAppConfig);
		return "redirect:/companyAppConfig";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyAppConfigServiceImpl.deleteCompanyAppConfigById(id);
		return "redirect:/companyAppConfig";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public CompanyAppConfig getJson(Model model, @PathVariable Integer id){
		return companyAppConfigServiceImpl.findCompanyAppConfigById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/queryCompanyConfig")
	public CompanyAppConfig queryCompanyConfig(){
		CompanyAppConfig config=companyAppConfigServiceImpl.queryCompanyConfig(WebUtils.getCurrentCompanyId());
		return config;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/UploadeAppImage",method=RequestMethod.POST)
	public String UploadCycles(MultipartRequest multiPartRquest,HttpServletRequest req){
		Subject subject = SecurityUtils.getSubject();
		String realPath=null;
		String picPath=null;
		MultipartFile	multipartFile = multiPartRquest.getFile("imgData");
		subject.getSession().setAttribute("imgData", multipartFile);
		String name=multipartFile.getOriginalFilename();
		if(name!=null&&!"".equals(name)){
			try {
				realPath = FileUtil.upload(multipartFile, "company", WebUtils.getCurrentCompanyId()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Integer id=0;
		picPath="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
		if(null!=realPath){
			CompanyAppWelcomePic config=companyAppWelcomePicServiceImpl.findByparam(WebUtils.getCurrentCompanyId());
			CompanyAppWelcomePic pic=new CompanyAppWelcomePic();
			pic.setCompanyId(WebUtils.getCurrentCompanyId());
			if(null!=config){
				pic.setId(config.getId());
				pic.setUrl(picPath);
				companyAppWelcomePicServiceImpl.update(pic);
			}else{
				pic.setUrl(picPath);
				pic.setStatus(1);
				companyAppWelcomePicServiceImpl.insert(pic);
			}
			id=pic.getId();
		}
		return "{\"url\":\""+picPath+"\",\"id\":\""+id+"\"}";
	}
	
	/**
	 * 
	 * Class Name: CompanyAppConfigController.java
	 * @Description: 上传二维码
	 * @author zhang.zx
	 * @date 2016年5月29日 下午4:59:33
	 * @modifier
	 * @modify-date 2016年5月29日 下午4:59:33
	 * @version 1.0
	 * @param multiPartRquest
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/UploadeAppEwm",method=RequestMethod.POST)
	public String UploadeAppEwm(MultipartRequest multiPartRquest,HttpServletRequest req){
		Subject subject = SecurityUtils.getSubject();
		String realPath=null;
		String picPath=null;
		MultipartFile	multipartFile = multiPartRquest.getFile("imgDatas");
		subject.getSession().setAttribute("imgDatas", multipartFile);
		String name=multipartFile.getOriginalFilename();
		if(name!=null&&!"".equals(name)){
			try {
				realPath = FileUtil.upload(multipartFile, "company", WebUtils.getCurrentCompanyId()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		picPath="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
		if(null!=realPath){
			CompanyAppConfig con=new CompanyAppConfig();
			Integer companyId=WebUtils.getCurrentCompanyId();
			CompanyAppConfig config=companyAppConfigServiceImpl.queryCompanyConfig(companyId);
			con.setCompanyId(companyId);
			if(null!=config){
				con.setId(config.getId());
				con.setQrcodeUrl(picPath);
				companyAppConfigServiceImpl.update(con);
			}else{
				con.setQrcodeUrl(picPath);
				companyAppConfigServiceImpl.insert(con);
			}
		}
		return "{\"url\":\""+picPath+"\",\"picPath\":\""+realPath+"\"}";
	}
	
	/**
	 * 
	 * Class Name: CompanyAppConfigController.java
	 * @Description: 下载二维码
	 * @author zhang.zx
	 * @date 2016年5月29日 下午12:37:23
	 * @modifier
	 * @modify-date 2016年5月29日 下午12:37:23
	 * @version 1.0
	 * @param request
	 * @param imagesdata
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/downLoadEwm/{type}")
	public String update(HttpServletRequest request,HttpServletResponse response,@PathVariable String type) throws UnsupportedEncodingException {
		if("url".equals(type)){
			String imagesdata=request.getParameter("url1");
			if(null!=imagesdata){
				try{
					String fileName=imagesdata.substring(imagesdata.lastIndexOf("/")+1,imagesdata.length()); 
			        response.setHeader("Content-Disposition", "attachment; filename="+fileName);    
			        response.addHeader("Content-Length", "");     
			        response.setContentType("image/jpeg");   
					 URL url = new URL(imagesdata);
			            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			            DataInputStream in = new DataInputStream(connection.getInputStream());
			            DataOutputStream out = new DataOutputStream(response.getOutputStream());
			            byte[] buffer = new byte[4096];
			            int count = 0;
			            while ((count = in.read(buffer)) > 0)
			            {
			                out.write(buffer, 0, count);
			            }
			            out.close();
			            out.flush();
			            in.close();
			            connection.disconnect();
				}catch (Exception e) {
					log.error("文件下载失败",e);
					e.printStackTrace();
				}
			}
		}else{
			String imagesdata=URLDecoder.decode(request.getParameter("url1"), "UTF-8");
			//String imagesdata="data:image/png;base64,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";
			if (imagesdata != null && !imagesdata.equals("")) {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");// 只有时分秒
				String imgName = sdf.format(date) + ".png"; 
				imagesdata = imagesdata.substring(imagesdata.indexOf(",") + 1);
				imagesdata = imagesdata.replaceAll(" ", "+");
				BASE64Decoder decoder = new BASE64Decoder();
				try {
					response.setHeader("Content-Disposition", "attachment; filename="+imgName);    
			        response.addHeader("Content-Length", "");     
			        response.setContentType("image/png"); 
					// Base64解码
					byte[] b = decoder.decodeBuffer(imagesdata);
					ByteArrayInputStream in = new ByteArrayInputStream(b);   
			        byte[] buffer = new byte[in.available()];   
			        in.read(buffer);   
			        in.close();   
					OutputStream out= new BufferedOutputStream(response.getOutputStream());
			        out.write(buffer);   
			        out.flush();
			        out.close();   
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 删除启动图
	 */
	@ResponseBody
	@RequestMapping(value="/del_qd/{id}")
	public String delteQdPic(@PathVariable Integer id){
		companyAppWelcomePicServiceImpl.deleteCompanyAppWelcomePicById(id);
		return "success";
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
