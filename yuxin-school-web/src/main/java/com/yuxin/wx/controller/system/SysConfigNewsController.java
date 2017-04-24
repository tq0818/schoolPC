package com.yuxin.wx.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.yuxin.wx.api.auth.IAuthRoleService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.api.system.ISysNewsService;
import com.yuxin.wx.api.system.ISysNewsTypeService;
import com.yuxin.wx.common.BaseWebController;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.system.SysNews;
import com.yuxin.wx.model.system.SysNewsType;
import com.yuxin.wx.utils.FileUploadUtil;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.system.SysNewsTypeVo;
import com.yuxin.wx.vo.system.SysNewsVo;

	/**
	 * @ClassName: SysConfigNewsController
	 * @Description: controller of news
	 * @author zhang.zx
	 * @date 2015年3月31日 下午1:47:46
	 */
	@Controller
	@RequestMapping("/sysConfigNews")
	public class SysConfigNewsController extends BaseWebController {
		
		private Log log = LogFactory.getLog("log");
		
		@Autowired
		private PropertiesUtil propertiesUtil;
		
		@Autowired
		private ISysNewsService sysNewsServiceImpl;
		
		@Autowired
		private ISysConfigDictService sysConfigDictService;
		
		@Autowired
		private ISysConfigSchoolService sysConfigSchoolServiceImpl;
		
		@Autowired
		private ISysNewsTypeService sysNewsTypeServiceImpl;
		
		@Autowired
		private IAuthRoleService authRoleServiceImpl;
		/**
		 * 
		 * Class Name: SysConfigNewsController.java
		 * @Description: 上传新闻图片
		 * @author zhang.zx
		 * @date 2015年6月3日 上午10:58:01
		 * @modifier
		 * @modify-date 2015年6月3日 上午10:58:01
		 * @version 1.0
		 * @param multiPartRquest
		 * @param req
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/UploadPic",method=RequestMethod.POST)
		public String UploadCycles(MultipartRequest multiPartRquest,HttpServletRequest req){
			String realPath=null;
			String picPath=null;
			MultipartFile	multipartFile = multiPartRquest.getFile("imgData");
			String name=multipartFile.getOriginalFilename();
			if(name!=null&&!"".equals(name)){
				try {
					realPath = FileUtil.upload(multipartFile, "news", WebUtils.getCurrentCompanyId()+"");
				} catch (Exception e) {
					log.error("文件上传失败",e);
					e.printStackTrace();
				}
			}
			picPath="http://"+propertiesUtil.getProjectImageUrl()+"/"+realPath;
			return "{\"url\":\""+picPath+"\",\"picPath\":\""+realPath+"\"}";
		}
		
		/**
		 * 
		 * Class Name: SysConfigNewsController.java
		 * @Description: 跳转到公告管理
		 * @author zhang.zx
		 * @date 2015年3月31日 下午5:00:52
		 * @version 1.0
		 * @return
		 */
		@RequestMapping(value="/showNews",method=RequestMethod.GET)
		public String list(Model model,HttpServletRequest request){
			Integer companyId=WebUtils.getCurrentCompanyId();
			Integer schoolId=WebUtils.getCurrentSchoolId();
			Integer uId=WebUtils.getCurrentUserId(request);
			if(authRoleServiceImpl.hasRoleFlag(uId)){
				List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
				model.addAttribute("schoolId", schoolId);
				model.addAttribute("schoolList", schoolList);
				SysNewsType sysNewsType=new SysNewsType();
				sysNewsType.setCompanyId(WebUtils.getCurrentCompanyId().toString());
				sysNewsType.setDelFlag("0");
				List<SysNewsTypeVo> list=sysNewsTypeServiceImpl.queryNewsType(sysNewsType);
				model.addAttribute("newsType", list);
			}else{
				SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(schoolId);
				SysNewsType sysNewsType=new SysNewsType();
				sysNewsType.setCompanyId(WebUtils.getCurrentCompanyId().toString());
				sysNewsType.setSchoolId(schoolId.toString());
				sysNewsType.setDelFlag("0");
				List<SysNewsTypeVo> list=sysNewsTypeServiceImpl.queryNewsType(sysNewsType);
				model.addAttribute("newsType", list);
				model.addAttribute("school1",school);
			}
//			Subject subject = SecurityUtils.getSubject();
//			if(subject.hasRole("机构管理员")){
//				List<SysConfigSchool> schoolList=sysConfigSchoolServiceImpl.findSysConfigSchoolByCompanyId(companyId);
//				model.addAttribute("schoolId", schoolId);
//				model.addAttribute("schoolList", schoolList);
//				SysNewsType sysNewsType=new SysNewsType();
//				sysNewsType.setCompanyId(WebUtils.getCurrentCompanyId().toString());
//				sysNewsType.setDelFlag("0");
//				List<SysNewsTypeVo> list=sysNewsTypeServiceImpl.queryNewsType(sysNewsType);
//				model.addAttribute("newsType", list);
//			}else if(subject.hasRole("分校管理员")){
//				SysConfigSchool school=sysConfigSchoolServiceImpl.findSysConfigSchoolById(schoolId);
//				SysNewsType sysNewsType=new SysNewsType();
//				sysNewsType.setCompanyId(WebUtils.getCurrentCompanyId().toString());
//				sysNewsType.setSchoolId(schoolId.toString());
//				sysNewsType.setDelFlag("0");
//				List<SysNewsTypeVo> list=sysNewsTypeServiceImpl.queryNewsType(sysNewsType);
//				model.addAttribute("newsType", list);
//				model.addAttribute("school1",school);
//			}else{
//				
//			}
			return "system/newsManage";
		}
		
		/**
		 * 
		 * Class Name: SysConfigNewsController.java
		 * @Description: 编辑公告
		 * @author zhang.zx
		 * @date 2015年5月21日 下午5:20:58
		 * @modifier
		 * @modify-date 2015年5月21日 下午5:20:58
		 * @version 1.0
		 * @param model
		 * @param request
		 * @param type
		 * @param id
		 * @return
		 */
		@RequestMapping(value="/queryNewsById",method=RequestMethod.POST)
		public String editNesManage(Model model,HttpServletRequest request,String type,Integer id,Integer schoolId){
			SysNews sysNews=null;
			if("1".equals(type)){
				sysNews=sysNewsServiceImpl.findSysNewsById(0);
			}else{
				String url="http://"+propertiesUtil.getProjectImageUrl()+"/";
				sysNews=sysNewsServiceImpl.findSysNewsById(id);
				if(sysNews.getNewsPic()!=null && !"".equals(sysNews.getNewsPic()))
				sysNews.setNewsPic(url+sysNews.getNewsPic());
				else
				sysNews.setNewsPic("");
			}
			SysNewsType sysNewsType=new SysNewsType();
			sysNewsType.setCompanyId(WebUtils.getCurrentCompanyId().toString());
			sysNewsType.setSchoolId(schoolId.toString());
			sysNewsType.setDelFlag("0");
			List<SysNewsTypeVo> list=sysNewsTypeServiceImpl.queryNewsType(sysNewsType);
			model.addAttribute("newsType", list);
			model.addAttribute("sysNews", sysNews);
			model.addAttribute("type", type);
			model.addAttribute("schoolId", schoolId);
			return "system/editNewsManage";
		}
		
		/**
		 * 
		 * Class Name: SysConfigNewsController.java
		 * @Description: 添加公告
		 * @author zhang.zx
		 * @date 2015年4月1日 下午1:56:58
		 * @version 1.0
		 * @param sysNews
		 * @return
		 * @throws IOException 
		 */
		@ResponseBody
		@RequestMapping(value="/addNews", method = RequestMethod.POST)
		public boolean add(SysNews sysNews,HttpServletRequest req) {
			try {
				wrapperSaveNews(sysNews,req);
				sysNewsServiceImpl.insert(sysNews);
			} catch (Exception e) {
				log.error("新增公告出错！", e);
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		/**
		 * 
		 * Class Name: SysConfigNewsController.java
		 * @Description: 修改公告
		 * @author zhang.zx
		 * @date 2015年4月1日 下午1:56:00
		 * @version 1.0
		 * @param SysNews
		 * @return String 返回类型
		 */
		@ResponseBody
		@RequestMapping(value="/updateNews", method = RequestMethod.POST)
		public boolean update(SysNews sysNews,HttpServletRequest req) {
			System.out.println(sysNews.getSchoolId());
			sysNews.setUpdateTime(new Date());
			sysNews.setUpdator(getCurrentUserId(req));
			try{
				sysNewsServiceImpl.update(sysNews);
			} catch (Exception e) {
				log.error("修改公告出错！", e);
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		/**
		 * 
		 * Class Name: SysConfigNewsController.java
		 * @Description: 查询公告
		 * @author zhang.zx
		 * @date 2015年3月31日 下午7:44:24
		 * @version 1.0
		 * @param search
		 * @param model
		 * @return
		 */
		@RequestMapping(value="/showNewsList",method={RequestMethod.POST,RequestMethod.GET})
		public String newsAjaxList(SysNewsVo search, Integer day, Model model){
			search.setCompanyId(WebUtils.getCurrentCompanyId());
			search.setDelFlag(0);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			PageFinder<SysNewsVo> pageFinder=sysNewsServiceImpl.findSysNewByPageByKeys(search);
			if(day!=null&&!"".equals(day)){
				List<SysNewsVo> ar1=new ArrayList<SysNewsVo>();
				int count=0;
				List<SysNewsVo> data=pageFinder.getData();
				for(SysNewsVo sysNews:data){
					 String startTime=sdf.format(sysNews.getPublishTime());
					 String endTime=sdf.format(new Date());
					 Integer a= getDaySub(startTime, endTime);
					 if(day>=a){
						SysNewsVo s1=new SysNewsVo();
						s1.setId(sysNews.getId());
						s1.setNewsTitle(sysNews.getNewsTitle());
						s1.setNewsContent(sysNews.getNewsContent());
						s1.setNewsType(sysNews.getNewsType());
						s1.setPublishTime(sysNews.getPublishTime());
						s1.setCreateName(sysNews.getCreateName());
						s1.setUserName(sysNews.getUserName());
						s1.setNewsStatus(sysNews.getNewsStatus());
						ar1.add(s1);
						count++;
					 }
				}
				PageFinder<SysNewsVo> pf=new PageFinder<SysNewsVo>(search.getPage(), search.getPageSize(), count, ar1);
				model.addAttribute("pageFinder", pf);
			}else{
				model.addAttribute("pageFinder", pageFinder);
			}
			return "system/newsAjaxList";
		} 
		
		/**
		 * 
		 * Class Name: SysConfigNewsController.java
		 * @Description: 改变新闻发布状态
		 * @author zhang.zx
		 * @date 2015年3月31日 下午7:44:24
		 * @version 1.0
		 * @param model
		 * @return String 返回类型
		 */
		@ResponseBody
		@RequestMapping(value="/changNewsStatus",method=RequestMethod.POST)
		public SysNews changNewsStatus(SysNewsVo sysNews, Model model){
			sysNewsServiceImpl.changNewsStatus(sysNews);
			return sysNewsServiceImpl.findSysNewsById(sysNews.getId());
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
		 * Class Name: SysConfigNewsController.java
		 * 
		 * @Description: 包装新增的参数
		 * @author liuxindong
		 * @date 2014-12-13 下午12:40:30
		 * @version 1.0
		 * @param sysConfigItem
		 * @param operate
		 * @param request
		 */
		private void wrapperSaveNews(SysNews sysNews,HttpServletRequest req) {
			    Integer companyId = WebUtils.getCurrentCompanyId();
					sysNews.setPublishTime(new Date());
					sysNews.setPublishUser(getCurrentUserId(req));
					sysNews.setCreateTime(new Date());
					sysNews.setCreator(getCurrentUserId(req));
					//sysNews.setSchoolId(sysNews.getSchoolId());
					sysNews.setCompanyId(companyId);
					sysNews.setUpdator(getCurrentUserId(req));
					sysNews.setUpdateTime(new Date());
					sysNews.setDelFlag(0);
		}
		
		/**
		 * 
		 * Class Name: SysConfigNewsController.java
		 * @Description: 去除文件后缀
		 * @author zhang.zx
		 * @date 2015年4月7日 下午5:50:01
		 * @modifier
		 * @modify-date 2015年4月7日 下午5:50:01
		 * @version 1.0
		 * @param filename
		 * @return
		 */
		 public static String getFileNameNoEx(String filename) {   
		        if ((filename != null) && (filename.length() > 0)) {   
		            int dot = filename.lastIndexOf('.');   
		            if ((dot >-1) && (dot < (filename.length()))) {   
		                return filename.substring(0, dot);   
		            }   
		        }   
		        return filename;   
		    }   
     
		 /**
		  * 
		  * Class Name: SysConfigNewsController.java
		  * @Description: 计算一段时间相隔天数
		  * @author zhang.zx
		  * @date 2015年6月8日 下午9:20:07
		  * @modifier
		  * @modify-date 2015年6月8日 下午9:20:07
		  * @version 1.0
		  * @param beginDateStr
		  * @param endDateStr
		  * @return
		  */
	    public static Integer getDaySub(String beginDateStr,String endDateStr) {
	        Integer day=0;
	        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");    
	        java.util.Date beginDate;
	        java.util.Date endDate;
	        try{
	            beginDate = format.parse(beginDateStr);
	            endDate= format.parse(endDateStr);    
	            day=(int) ((endDate.getTime()-beginDate.getTime())/(24*60*60*1000));    
	            //System.out.println("相隔的天数="+day);   
	        } catch (ParseException e){
	            e.printStackTrace();
	        }   
	        return day;
	    }
}
