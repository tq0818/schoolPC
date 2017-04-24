package com.yuxin.wx.controller.student;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.student.IStudentStarService;
import com.yuxin.wx.api.system.ISysCyclePicService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.student.StudentStar;
import com.yuxin.wx.model.system.SysCyclePic;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangzhenya on 2016-12-22.
 */
@Controller
@RequestMapping("/studentStar")
public class StudentStarController {
    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private IStudentStarService studentStarService;
    @Autowired
    private ISysCyclePicService sysCyclePicService;
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
	private ICompanyService companyService;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
    // 跳转到学员列表页面
    @RequestMapping(value = "/studentStarIndex")
    public String forwardStudentList(Model model) {
        return "studentStar/sudentStarIndex";
    }
    @ResponseBody
    @RequestMapping(value = "/saveStar")
    public JSONObject saveStar(Model model, StudentStar studentStar) {
        JSONObject rmsg=new JSONObject();
        try {
        	studentStar.setCompanyId(WebUtils.getCurrentCompanyId());
            this.studentStarService.insert(studentStar);
            rmsg.put("status", JsonMsg.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            rmsg.put("status", JsonMsg.ERROR);
            rmsg.put("msg", e.getMessage());
        }
        return rmsg;
    }
    @ResponseBody
    @RequestMapping(value = "/updateStar")
    public JSONObject updateStar(Model model, StudentStar studentStar) {
        JSONObject rmsg=new JSONObject();
        try {
            this.studentStarService.update(studentStar);
            rmsg.put("status", JsonMsg.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            rmsg.put("status", JsonMsg.ERROR);
            rmsg.put("msg", e.getMessage());
        }
        return rmsg;
    }
    @ResponseBody
    @RequestMapping(value = "/delStar")
    public JSONObject delStar(Model model, Integer id) {
        JSONObject rmsg=new JSONObject();
        try {
            this.studentStarService.deleteStudentStarById(id);
            rmsg.put("status", JsonMsg.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            rmsg.put("status", JsonMsg.ERROR);
            rmsg.put("msg", e.getMessage());
        }
        return rmsg;
    }
    @ResponseBody
    @RequestMapping(value = "/findById")
    public JSONObject findById(Model model, Integer id) {
        JSONObject rmsg=new JSONObject();
        try {
            rmsg.put("obj",this.studentStarService.findStudentStarById(id));
            rmsg.put("imgHost", "http://" + propertiesUtil.getProjectImageUrl() + "/");
            rmsg.put("status", JsonMsg.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            rmsg.put("status", JsonMsg.ERROR);
            rmsg.put("msg", e.getMessage());
        }
        return rmsg;
    }
    @RequestMapping(value = "/findByPage")
    public String delStar(Model model, StudentStar studentStar) {
    	studentStar.setCompanyId(WebUtils.getCurrentCompanyId());
        PageFinder<StudentStar> page=this.studentStarService.page(studentStar);
        model.addAttribute("page",page);
        model.addAttribute("imgHost", "http://" + propertiesUtil.getProjectImageUrl() + "/");
        return "studentStar/sudentStarList";
    }
    @ResponseBody
    @RequestMapping(value="/UploadImg",method= RequestMethod.POST)
    public String UploadImg(MultipartRequest multiPartRquest, HttpServletRequest req){
        return uploadModelImg(multiPartRquest, req, "headpic");
    }
    @ResponseBody
    @RequestMapping(value="/UploadSetPic",method= RequestMethod.POST)
    public String UploadSetPic(MultipartRequest multiPartRquest, HttpServletRequest req){
        return uploadModelImg(multiPartRquest, req, "cycllepic");
    }
    @ResponseBody
    @RequestMapping(value="/UploadActionPic",method= RequestMethod.POST)
    public String UploadActionPic(MultipartRequest multiPartRquest, HttpServletRequest req){
        return uploadModelImg(multiPartRquest, req, "company");
    }

    private String uploadModelImg(MultipartRequest multiPartRquest, HttpServletRequest req, String model) {
        log.info(req.getServerName() + "===============" + req.getSession().getId());
        Subject subject = SecurityUtils.getSubject();
        String realPath = null;
        String picPath = null;
        MultipartFile multipartFile = multiPartRquest.getFile("imgData");
        subject.getSession().setAttribute("imgData", multipartFile);
        String name = multipartFile.getOriginalFilename();
        if (name != null && !"".equals(name)) {
            try {
                realPath = FileUtil.upload(multipartFile, model, WebUtils.getCurrentCompanyId() + "");
            } catch (Exception e) {
                log.error("文件上传失败", e);
                e.printStackTrace();
            }
        }
        picPath = "http://" + propertiesUtil.getProjectImageUrl() + "/" + realPath;
        return "{\"url\":\"" + picPath + "\",\"picPath\":\"" + realPath + "\"}";
    }


    @RequestMapping(value = "/showImg")
    public String showImg(Model model) {
        Integer companyId=WebUtils.getCurrentCompanyId();
        SysCyclePic search=new SysCyclePic();
        search.setCompanyId(companyId);
        search.setPicType("star");
        List<SysCyclePic> list=this.sysCyclePicService.findSysCyclePic(search);
        if(list!=null&&list.size()>0){
            model.addAttribute("imgHost", "http://" + propertiesUtil.getProjectImageUrl() + "/");
            model.addAttribute("pic",list.get(0).getPicUrl());
        }
        Company company = companyService.findCompanyById(companyId);

		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("company",company);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
        return "studentStar/showImg";
    }

    @ResponseBody
    @RequestMapping(value = "/updateImg")
    public JSONObject updateImg(String model,MultipartRequest multiPartRquest, HttpServletRequest req) {
    	JSONObject rmsg=new JSONObject();
    	Subject subject = SecurityUtils.getSubject();
        String realPath = null;
        String picPath = null;
        MultipartFile multipartFile = multiPartRquest.getFile("imgData");
        subject.getSession().setAttribute("imgData", multipartFile);
        String name = multipartFile.getOriginalFilename();
        if (name != null && !"".equals(name)) {
            try {
                realPath = FileUtil.upload(multipartFile, model, WebUtils.getCurrentCompanyId() + "");
            } catch (Exception e) {
                log.error("文件上传失败", e);
                e.printStackTrace();
                rmsg.put("status", JsonMsg.ERROR);
                rmsg.put("msg", "图片上传失败");
                return rmsg;
            }
        }
        picPath = "http://" + propertiesUtil.getProjectImageUrl() + "/" + realPath;
        rmsg.put("url",picPath);
        rmsg.put("picPath", realPath);
        String picUrl = realPath;
        
        Integer companyId=WebUtils.getCurrentCompanyId();
        Users user=WebUtils.getCurrentUser();
        try {
            SysCyclePic search=new SysCyclePic();
            search.setCompanyId(companyId);
            search.setPicType("star");
            List<SysCyclePic> list=this.sysCyclePicService.findSysCyclePic(search);
            if(list!=null&&list.size()>0){
                SysCyclePic update=new SysCyclePic();
                update.setId(list.get(0).getId());
                update.setPicUrl(picUrl);
                update.setUpdateTime(new Date());
                update.setUpdator(user.getId());
                this.sysCyclePicService.update(update);
            }else{
                SysCyclePic add=new SysCyclePic();
                add.setCompanyId(companyId);
                add.setPicType("star");
                add.setPicSequence(1);
                add.setPicUrl(picUrl);
                add.setCreator(user.getId());
                add.setCreateTime(new Date());
                add.setValidFlag(1);
                this.sysCyclePicService.insert(add);
            }
            rmsg.put("status", JsonMsg.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            rmsg.put("status", JsonMsg.ERROR);
            rmsg.put("msg", e.getMessage());
        }
        return rmsg;
    }
    @ResponseBody
	@RequestMapping(value = "/getJson/{page}")
    public Object getJson(StudentStar studentStar,HttpServletRequest request, @PathVariable Integer page) {
		Map<String,Object> result = new HashMap<String,Object>();
		studentStar.setCompanyId(WebUtils.getCurrentCompanyId());
		if(page != null) {
			studentStar.setPage(page);
		}
        List<StudentStar> data=this.studentStarService.findStudentStarByPage(studentStar);
        result.put("data", data);
        result.put("page", page);
        return result;
    }
}
