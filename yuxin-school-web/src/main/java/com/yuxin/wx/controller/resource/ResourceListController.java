package com.yuxin.wx.controller.resource;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.course.IVideoTagService;
import com.yuxin.wx.api.resource.IResourceListService;
import com.yuxin.wx.api.system.ISysFileConvertTaskService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.course.VideoTag;
import com.yuxin.wx.model.resource.ResourceList;
import com.yuxin.wx.model.system.SysFileConvertTask;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of ResourceList
 * 
 * @author wang.zx
 * @date 2016-9-1
 */
@Controller
@RequestMapping("/resourceList")
public class ResourceListController {
	
	@Autowired
	private IVideoTagService videoTagServiceImpl;
	
	@Autowired
	private ISysFileConvertTaskService convertImpl;

	@Autowired
	private ICompanyServiceStaticService companyServiceStaticImpl;

	@Autowired
	private IResourceListService resourceListServiceImpl;

	private Logger log = Logger.getLogger(getClass());

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, ResourceList search) {
		if (search == null) {
			search = new ResourceList();
			// search.setPageSize(20);
		}
		model.addAttribute("list", resourceListServiceImpl.findResourceListByPage(search));
		return "resourceList/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(ResourceList ResourceList) {
		resourceListServiceImpl.insert(ResourceList);
		return "redirect:/resourceList";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ResourceList ResourceList) {
		resourceListServiceImpl.update(ResourceList);
		return "redirect:/resourceList";
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		resourceListServiceImpl.deleteResourceListById(id);
		return "redirect:/resourceList";
	}

	@ResponseBody
	@RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
	public ResourceList getJson(Model model, @PathVariable Integer id) {
		return resourceListServiceImpl.findResourceListById(id);
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
	 * Class Name: ResourceListController.java
	 * 
	 * @Description: 资源库保存
	 * @author 周文斌
	 * @date 2016-9-2 上午11:50:48
	 * @modify 2016-9-2 上午11:50:48
	 * @version
	 * @param request
	 * @param rl
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save")
	public JSONObject save(HttpServletRequest request, ResourceList rl) {
		JSONObject json = new JSONObject();
		Users user = WebUtils.getCurrentUser(request);

		try {
			rl.setCompanyId(user.getCompanyId());
			rl.setSchoolId(user.getSchoolId());
			rl.setUploadTime(new Date());
			rl.setUploadUserId(user.getId());
			rl.setUploadUserName((user.getRealName() != null ? 
					user.getRealName() : user.getUsername()));
			rl.setDelFlag(0);
			rl.setOldData(0);
			if(rl.getFileType().equals("pdf")){
					rl.setFilePath(rl.getSourcePath());
					rl.setFileSize(rl.getSourceSize());
					rl.setSourcePath(null);
					rl.setSourceSize(null);
			}
			resourceListServiceImpl.insert(rl);
			json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
			
			if(!rl.getTag().isEmpty()){
				VideoTag vt = new VideoTag();
				vt.setTagName(rl.getTag());
				vt.setCompanyId(rl.getCompanyId());
				VideoTag vtag = videoTagServiceImpl.findExist(vt);
				if(vtag == null){
					videoTagServiceImpl.insert(vt);
				}
			}
			if((rl.getFileCategory().equals("ppt") ||
					rl.getFileCategory().equals("docs"))
					&& !rl.getFileType().equals("pdf")){
				SysFileConvertTask sfct = new SysFileConvertTask();
				sfct.setCreateTime(new Date());
				sfct.setCreator(rl.getUploadUserId());
				sfct.setVersion(0);
				sfct.setStatus(0);
				sfct.setFileId(rl.getId());
				sfct.setFilePath(rl.getSourcePath());
				convertImpl.insert(sfct);
			}
			CompanyServiceStatic css = companyServiceStaticImpl.findByCompanyId(rl.getCompanyId());
			long crs = Long.parseLong(css.getResourceStorage() != null ? css.getResourceStorage() : "0");
			long fs = Long.parseLong(rl.getFileSize() != null 
					? rl.getFileSize() : "0");
			long ss = Long.parseLong(rl.getSourceSize() != null 
					? rl.getSourceSize() : "0");
			
			css.setResourceStorage((crs + fs + ss)+"");
			companyServiceStaticImpl.update(css);

			json.put("id", rl.getId());
			return json;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
			json.put(JsonMsg.MSG, "保存出现意外，");
			return json;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/searchResourceList",method=RequestMethod.POST)
	public PageFinder<ResourceList> searchResourceList(Model model,ResourceList search,HttpServletRequest request){
		search.setCompanyId(WebUtils.getCurrentCompanyId());
		search.setDelFlag(0);
		search.setPageSize(8);
		List<ResourceList> page = resourceListServiceImpl.pageByFilePath(search);
		Integer totalCount = resourceListServiceImpl.pageByFilePathCount(search);
		PageFinder<ResourceList> pageFinder = new PageFinder<ResourceList>(search.getPage(), search.getPageSize(), totalCount, page);
		return pageFinder;
	}
}
