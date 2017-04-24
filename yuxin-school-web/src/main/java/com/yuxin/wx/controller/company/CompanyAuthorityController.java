package com.yuxin.wx.controller.company;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyIndexTemplateService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.system.ISysBodyService;
import com.yuxin.wx.common.CacheService;
import com.yuxin.wx.common.CacheService.Ckey;
import com.yuxin.wx.common.Constants;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.SysConfigPrivatePageVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller of CompanyAuthority
 * @author chopin
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/companyAuthority")
public class CompanyAuthorityController {
	private Log log=LogFactory.getLog("log");
	@Autowired
	private ISysBodyService sysBodySerivceImpl;
	
	@Autowired
	private ICompanyIndexTemplateService companyIndexTemplateServiceImpl; 
	@Autowired
	private ICompanyService companyServiceImpl;

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
	 * 周文斌
	 * 异步上传图片 返回图片路径
	 * @param img
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/imgUpload")
	public String imgUpload(MultipartRequest multiPartRquest,HttpServletRequest request) throws Exception{
		MultipartFile multipartFile = multiPartRquest.getFile("imgFile");
		String url=null;
		try {
			//判断是否为图像
			 String[] type={".jpg",".png",".bmp",".gif",".jpeg"};
			 String suffix = multipartFile.getOriginalFilename().substring  
                     (multipartFile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
			boolean isImage=false;
			for(int i=0;i<type.length;i++){
				if(suffix.equals(type[i])){
					isImage=true;
				}
			}
			if(!isImage){
				return "{\"url\":\"formatNotRight\"}";
			}
			if((multipartFile.getSize()) > (1024 * 1024 * 2)){
				return "{\"url\":\"sizeOutOf\"}";
			}
			url = FileUtil.upload(multipartFile, "company", WebUtils.getCurrentCompanyId()+"");
			return "{\"url\":\""+url+"\"}";
		} catch (Exception e) {
			log.error("文件上传失败!",e);
			e.printStackTrace();
			return "{\"url\":\"error\"}";
		}
	}
	

	public String useTemplate(HttpServletRequest request,Integer id,Integer schoolId){
		companyIndexTemplateServiceImpl.useTemplate(WebUtils.getCurrentCompanyId(), schoolId, id);
		List<SysConfigPrivatePageVo>list=sysBodySerivceImpl.publishToFront(WebUtils.getCurrentCompanyId(), schoolId, id);
		Ckey ckey=new Ckey();
		ckey.setModule(Constants.MODULE_BODY);
		ckey.setCompanyId(""+WebUtils.getCurrentCompanyId());
		ckey.setSchoolId(""+WebUtils.getCurrentSchoolId());
		CacheService.updateCache(ckey,list);
		return JsonMsg.SUCCESS;
	}
	@ResponseBody
	@RequestMapping("/tempAuther")
	public JSONObject tempAuther(HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			//公司id
			Integer companyId = WebUtils.getCurrentCompanyId();

			Company company = new Company();
			company.setId(companyId);
			company.setStatus(5);
			companyServiceImpl.update(company);

			WebUtils.setSatus();
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put(JsonMsg.MSG, JsonMsg.ERROR);
			return json;
		}
	}
}
