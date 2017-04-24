package com.yuxin.wx.controller.classes;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassTypeResourceService;
import com.yuxin.wx.api.classes.IClassTypeResourceTypeService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyPayConfigService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.course.IVideoTagService;
import com.yuxin.wx.api.resource.IResourceListService;
import com.yuxin.wx.api.resource.IResourceUseRecordService;
import com.yuxin.wx.api.system.ISysFileConvertTaskService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassTypeResource;
import com.yuxin.wx.model.classes.ClassTypeResourceType;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.course.VideoTag;
import com.yuxin.wx.model.resource.ResourceList;
import com.yuxin.wx.model.resource.ResourceUseRecord;
import com.yuxin.wx.model.system.SysFileConvertTask;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.classes.ClassTypeResourceVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Controller of ClassTypeResource
 *
 * @author wang.zx
 * @date 2015-8-11
 */
@Controller
@RequestMapping("/classTypeResource")
public class ClassTypeResourceController {

    @Autowired
    private ISysFileConvertTaskService convertImpl;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private IUsersService usersImpl;

    @Autowired
    private IResourceUseRecordService resourceUseRecordImpl;

    @Autowired
    private ICompanyMemberServiceService companyMemberServiceImpl;

    @Autowired
    private ICompanyServiceStaticService companyServiceStaticImpl;

    @Autowired
    private IResourceListService resourceListImpl;

    @Autowired
    private IVideoTagService videoTagServiceImpl;

    @Autowired
    private PropertiesUtil propertiesUtil;

    private Log log = LogFactory.getLog("log");

    @Autowired
    private ICompanyPayConfigService companyPayConfigImpl;

    @Autowired
    private IClassTypeResourceTypeService classTypeResourceTypeServiceImpl;

    @Autowired
    private IClassTypeResourceService classTypeResourceServiceImpl;
    @Autowired
    private ISysFileConvertTaskService sysFileConvertTaskServiceImpl;
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, ClassTypeResource search) {
        if (search == null) {
            search = new ClassTypeResource();
            // search.setPageSize(20);
        }
        model.addAttribute("list", this.classTypeResourceServiceImpl.findClassTypeResourceByPage(search));
        return "classTypeResource/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(ClassTypeResource ClassTypeResource) {
        this.classTypeResourceServiceImpl.insert(ClassTypeResource);
        return "redirect:/classTypeResource";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ClassTypeResource ClassTypeResource) {
        this.classTypeResourceServiceImpl.update(ClassTypeResource);
        return "redirect:/classTypeResource";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        this.classTypeResourceServiceImpl.deleteClassTypeResourceById(id);
        return "redirect:/classTypeResource";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public ClassTypeResource getJson(Model model, @PathVariable Integer id) {
        return this.classTypeResourceServiceImpl.findClassTypeResourceById(id);
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
     * Class Name: ClassTypeResourceController.java
     *
     * @Description: 查询上传资源列表
     * @author 周文斌
     * @date 2015-8-11 下午5:55:20
     * @version 1.0
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/selResource")
    public String selResource(Model model, HttpServletRequest request, ClassTypeResource res) {
        // 公司id
        Integer companyId = WebUtils.getCurrentCompanyId();
        res.setCompanyId(companyId);
        // 查询
        List<ClassTypeResourceVo> resList = this.classTypeResourceServiceImpl.findResBy(res);

        for (ClassTypeResourceVo c : resList) {
            if (c.getSourceSize() != null) {
                c.setSourceSize(FileUtil.convertFileSize(Long.parseLong(c.getSourceSize())));
            }
        }
        // 总数
        Integer count = this.classTypeResourceServiceImpl.findResCountBy(res);
        // 分页
        PageFinder<ClassTypeResourceVo> resPage = new PageFinder<ClassTypeResourceVo>(res.getPage(), res.getPageSize(), count, resList);
        model.addAttribute("resPage", resPage);
        return "classes/resourceTable";
    }

    /**
     * 课程资料列表
     */
    @ResponseBody
    @RequestMapping("/rourseList")
    public List<ClassTypeResourceVo> selResource(HttpServletRequest request, ClassTypeResource res) {
        // 公司id
        Integer companyId = WebUtils.getCurrentCompanyId();
        res.setCompanyId(companyId);
        // 查询
        List<ClassTypeResourceVo> resList = this.classTypeResourceServiceImpl.findResByCondition(res);
        return resList;
    }

    @ResponseBody
    @RequestMapping("/docupload")
    public String docupload(MultipartRequest multiPartRquest,
                            HttpServletRequest request) {
        MultipartFile multipartFile = multiPartRquest.getFile("doc");
		/* JSONObject json = new JSONObject(); */
        Integer companyId = WebUtils.getCurrentCompanyId();
        String oname = multipartFile.getOriginalFilename();
        String suffix = oname.substring(oname.lastIndexOf(".")).toLowerCase();
        String filename = UUID.randomUUID().toString();
        String officepath = propertiesUtil.getFileStoragePath() + filename
                + suffix;
        File f = new File(officepath);
        boolean ism = true;
        try {
            // 判断是否为图像
            multipartFile.transferTo(f);
            if (oname.length() > 120) {
                return "{\"msg\":\"" + "nameTooLang" + "\"}";
            }
            if (f.length() > (1024 * 1024 * 20)) {
				/* json.put("msg", "sizeOutOf"); */
                return "{\"msg\":\"" + "sizeOutOf" + "\"}";
            }
            String[] types = new String[] { ".pdf", ".swf", ".doc", ".docx",
                    ".xls", ".xlsx", ".ppt", ".pptx", ".zip", ".rar" };
            for (String s : types) {
                if (suffix.equals(s)) {
                    ism = false;
                    break;
                }
            }
            String dir = "";
			/* String cate = ""; */
            JSONObject json = new JSONObject();
            if (ism) {
                return "{\"msg\":\"" + "文件格式不正确" + "\"}";
            }
            dir += companyId + "/docs/";
            json = FileUtil.uploadDocument(f, "coursefile", "zs");
            Users user = usersImpl.findUsersById(WebUtils
                    .getCurrentUserId(request));
            if (json.getString("msg").equals("success")) {
                ResourceList rl = new ResourceList();
                rl.setCompanyId(companyId);
                rl.setDelFlag(0);
                rl.setFileCategory("docs");
                rl.setFileName(oname);
                rl.setFileType(suffix.substring(1));
                rl.setSourcePath(json.getString("filePath"));
                rl.setSourceSize(json.getString("size"));
                rl.setUploadTime(new Date());
                rl.setUploadUserId(user.getId());
                rl.setUploadType(1);
                rl.setUploadUserName((user.getRealName() != null ? user
                        .getRealName() : user.getUsername()));
                rl.setUuid(filename);
                rl.setOldData(0);
                resourceListImpl.insert(rl);
                return "{\"msg\":\"success\",\"fileId\":" + rl.getId() + "}";
            } else {
                f.delete();
                return "{\"msg\":\"" + "文件上传失败" + "\"}";
            }
        } catch (Exception e) {
            log.error("文件上传失败!", e);
            e.printStackTrace();
            f.delete();
			/* json.put("msg", "文件上传失败"); */
            return "{\"msg\":\"" + "文件上传失败" + "\"}";
        }
    }
    @ResponseBody
    @RequestMapping("/docupload2")
    public String docupload2(MultipartRequest multiPartRquest,
                            HttpServletRequest request) {
        MultipartFile multipartFile = multiPartRquest.getFile("doc");
		/* JSONObject json = new JSONObject(); */
        Integer companyId = WebUtils.getCurrentCompanyId();
        String oname = multipartFile.getOriginalFilename();
        String suffix = oname.substring(oname.lastIndexOf(".")).toLowerCase();
        String filename = UUID.randomUUID().toString();
        String officepath = propertiesUtil.getFileStoragePath() + filename
                + suffix;
        File f = new File(officepath);
        boolean ism = true;
        try {
            // 判断是否为图像
            multipartFile.transferTo(f);
            if (oname.length() > 120) {
                return "{\"msg\":\"" + "nameTooLang" + "\"}";
            }
            if (f.length() > (1024 * 1024 * 20)) {
				/* json.put("msg", "sizeOutOf"); */
                return "{\"msg\":\"" + "sizeOutOf" + "\"}";
            }
            String[] types = new String[] { ".pdf", ".swf", ".doc", ".docx",
                    ".xls", ".xlsx", ".ppt", ".pptx", ".zip", ".rar" };
            for (String s : types) {
                if (suffix.equals(s)) {
                    ism = false;
                    break;
                }
            }
            String dir = "";
			/* String cate = ""; */
            JSONObject json = new JSONObject();
            if (ism) {
                return "{\"msg\":\"" + "文件格式不正确" + "\"}";
            }
            dir += companyId + "/docs/";
            json = FileUtil.uploadDocument(f, "coursefile", "zs");
            Users user = usersImpl.findUsersById(WebUtils
                    .getCurrentUserId(request));
            if (json.getString("msg").equals("success")) {
                ResourceList rl = new ResourceList();
                rl.setCompanyId(companyId);
                rl.setDelFlag(0);
                rl.setFileCategory("docs");
                rl.setFileName(oname);
                rl.setFileType(suffix.substring(1));
                rl.setSourcePath(json.getString("filePath"));
                rl.setSourceSize(json.getString("size"));
                rl.setUploadTime(new Date());
                rl.setUploadUserId(user.getId());
                rl.setUploadType(2);
                rl.setUploadUserName((user.getRealName() != null ? user
                        .getRealName() : user.getUsername()));
                rl.setUuid(filename);
                rl.setOldData(0);
                resourceListImpl.insert(rl);
                return "{\"msg\":\"success\",\"fileId\":" + rl.getId() + "}";
            } else {
                f.delete();
                return "{\"msg\":\"" + "文件上传失败" + "\"}";
            }
        } catch (Exception e) {
            log.error("文件上传失败!", e);
            e.printStackTrace();
            f.delete();
			/* json.put("msg", "文件上传失败"); */
            return "{\"msg\":\"" + "文件上传失败" + "\"}";
        }
    }
    /**
     *
     * Class Name: ClassTypeResourceController.java
     *
     * @Description: 保存课程资料
     * @author 周文斌
     * @date 2015-8-12 下午1:06:39
     * @version 1.0
     * @param request
     * @param res
     * @param resName
     * @return
     */
    @ResponseBody
    @RequestMapping("/save")
    public JSONObject save(HttpServletRequest request, ClassTypeResource res, String resName) {
        JSONObject json = new JSONObject();
        // 获得当前操作人姓名
        res.setCompanyId(WebUtils.getCurrentCompanyId());
        try {
            this.classTypeResourceServiceImpl.insert(res);
            // 查询当前公司有没有这个类型

            ClassTypeResourceType ctrt = new ClassTypeResourceType();
            ctrt.setCompanyId(WebUtils.getCurrentCompanyId());
            ctrt.setResourceType(res.getResourceType());
            ctrt.setResourceName(resName);

            ClassTypeResourceType restype = this.classTypeResourceTypeServiceImpl.findResourceTypeBy(ctrt);

            if (restype == null) {
                this.classTypeResourceTypeServiceImpl.insert(ctrt);
            }

            ResourceList rl = new ResourceList();
            rl.setId(res.getFileId());
            rl.setSysItemOne(res.getItemOneId());
            rl.setSysItemSecond(res.getItemSecondId());
            rl.setCompanyId(WebUtils.getCurrentCompanyId());
            rl.setSchoolId(WebUtils.getCurrentUserSchoolId(request));

            this.resourceListImpl.update(rl);

            json.put("msg", "success");
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put("msg", "保存异常");
            return json;
        }
    }

    /**
     *
     * Class Name: ClassTypeResourceController.java
     *
     * @Description: 删除课程资料
     * @author 周文斌
     * @date 2015-8-12 下午1:13:13
     * @version 1.0
     * @param request
     * @param res
     * @return
     */
    @ResponseBody
    @RequestMapping("/delres")
    public JSONObject delres(HttpServletRequest request, Integer id) {
        JSONObject json = new JSONObject();
        try {
            CompanyServiceStatic css = this.companyServiceStaticImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
            ClassTypeResource res = this.classTypeResourceServiceImpl.findClassTypeResourceById(id);
            ResourceList rl = this.resourceListImpl.findResourceListById(res.getFileId());
            JSONObject jsons = new JSONObject();
            if (rl.getOldData() != null && rl.getOldData().equals(1)) {
                rl.setDelFlag(1);
                rl.setUpdator(WebUtils.getCurrentCompanyId());
                rl.setDelTime(new Date());
                rl.setUpdateTime(new Date());
                this.resourceListImpl.update(rl);
            } else {
                rl.setDelFlag(1);
                rl.setUpdator(WebUtils.getCurrentCompanyId());
                rl.setDelTime(new Date());
                rl.setUpdateTime(new Date());
                this.resourceListImpl.update(rl);
                long crs = Long.parseLong(css.getResourceStorage() != null ? css.getResourceStorage() : "0");
                long lrf = Long.parseLong((rl.getFileSize() != null ? rl.getFileSize() : "0"));
                long lrs = Long.parseLong(rl.getSourceSize() != null ? rl.getSourceSize() : "0");
                css.setResourceStorage((crs - (lrf + lrs) + ""));
                this.companyServiceStaticImpl.update(css);

                rl.setDelFlag(1);
                rl.setUpdator(WebUtils.getCurrentCompanyId());
                rl.setDelTime(new Date());
                rl.setUpdateTime(new Date());
                this.resourceListImpl.update(rl);
            }

            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "删除过程异常");
            return json;
        }
    }

    @ResponseBody
    @RequestMapping("/updateReslist")
    public JSONObject updateReslist(HttpServletRequest request, Integer id, String videoName, String videoTag, Integer itemOneId, Integer itemSecondId) {
        JSONObject json = new JSONObject();
        try {
            ResourceList rl = this.resourceListImpl.findResourceListById(id);
            if (rl == null || !rl.getCompanyId().equals(WebUtils.getCurrentCompanyId())) {
                json.put("msg", "更新失败");
                return json;
            }
            rl.setUpdator(WebUtils.getCurrentCompanyId());
            rl.setUpdateTime(new Date());
            rl.setFileName(videoName);
            rl.setTag(videoTag);
            rl.setSysItemOne(itemOneId);
            rl.setSysItemSecond(itemSecondId);
            this.resourceListImpl.update(rl);

            // 更新标签

            if (rl.getTag() != null && !rl.getTag().equals("")) {
                VideoTag vTag = new VideoTag();
                vTag.setCompanyId(WebUtils.getCurrentCompanyId());
                vTag.setTagName(rl.getTag());
                List<VideoTag> tags = this.videoTagServiceImpl.findVideoTagByPage(vTag);
                if (tags == null || (tags != null && tags.size() == 0)) {
                    this.videoTagServiceImpl.insert(vTag);
                }
            }

            json.put("msg", "success");
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put("msg", "更新异常");
            return json;
        }
    }

    /**
     *
     * Class Name: ClassTypeResourceController.java
     *
     * @Description: 删除资源库文件
     * @author 周文斌
     * @date 2016-9-12 上午10:49:26
     * @modify 2016-9-12 上午10:49:26
     * @version
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delreslist")
    public JSONObject delreslist(HttpServletRequest request, Integer id) {
        JSONObject json = new JSONObject();
        try {
            CompanyServiceStatic css = this.companyServiceStaticImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
            ResourceList rl = this.resourceListImpl.findResourceListById(id);
            JSONObject jsons = new JSONObject();

            if ((!json.isEmpty() && json.getString("msg").equals("success")) || (!jsons.isEmpty() && jsons.getString("msg").equals("success"))) {
                rl.setDelFlag(1);
                rl.setUpdator(WebUtils.getCurrentCompanyId());
                rl.setDelTime(new Date());
                rl.setUpdateTime(new Date());
                this.resourceListImpl.update(rl);
                long crs = Long.parseLong(css.getResourceStorage() != null ? css.getResourceStorage() : "0");
                long lrf = Long.parseLong((rl.getFileSize() != null ? rl.getFileSize() : "0"));
                long lrs = Long.parseLong(rl.getSourceSize() != null ? rl.getSourceSize() : "0");
                css.setResourceStorage((crs - (lrf + lrs) + ""));
                this.companyServiceStaticImpl.update(css);
            }
            rl.setDelFlag(1);
            rl.setUpdator(WebUtils.getCurrentCompanyId());
            rl.setDelTime(new Date());
            rl.setUpdateTime(new Date());
            this.resourceListImpl.update(rl);
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "删除过程异常");
        }
        return json;
    }

    @ResponseBody
    @RequestMapping("/delete/{id}")
    public String del(@PathVariable Integer id, HttpServletRequest request) {
        ClassTypeResource res = this.classTypeResourceServiceImpl.findClassTypeResourceById(id);
        try {
            CompanyServiceStatic css = this.companyServiceStaticImpl.findByCompanyId(WebUtils.getCurrentCompanyId());
            ResourceList rl = this.resourceListImpl.findResourceListById(res.getFileId());
            JSONObject json = new JSONObject();
            JSONObject jsons = new JSONObject();
            if (rl.getOldData() != null && rl.getOldData().equals(1)) {
                rl.setDelFlag(1);
                rl.setUpdator(WebUtils.getCurrentUserId(request));
                rl.setUpdateTime(new Date());
                rl.setDelTime(new Date());
                this.resourceListImpl.update(rl);
                return "success";
            } else {

                if ((!json.isEmpty() && !json.equals("") && json.getString("msg").equals("success"))
                        || (!jsons.isEmpty() && !jsons.equals("") && jsons.getString("msg").equals("success"))) {
                    rl.setDelFlag(1);
                    rl.setUpdator(WebUtils.getCurrentUserId(request));
                    rl.setUpdateTime(new Date());
                    rl.setDelTime(new Date());
                    this.resourceListImpl.update(rl);
                    long crs = Long.parseLong(css.getResourceStorage() != null ? css.getResourceStorage() : "0");
                    long lrf = Long.parseLong((rl.getFileSize() != null ? rl.getFileSize() : "0"));
                    long lrs = Long.parseLong(rl.getSourceSize() != null ? rl.getSourceSize() : "0");
                    css.setResourceStorage((crs - (lrf + lrs) + ""));
                    this.companyServiceStaticImpl.update(css);
                }
                rl.setDelFlag(1);
                rl.setUpdator(WebUtils.getCurrentUserId(request));
                rl.setUpdateTime(new Date());
                rl.setDelTime(new Date());
                this.resourceListImpl.update(rl);
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     *
     * Class Name: ClassTypeResourceController.java
     *
     * @Description: 资源库上传
     * @author 周文斌
     * @date 2016-9-12 上午10:49:54
     * @modify 2016-9-12 上午10:49:54
     * @version
     * @param multiPartRquest
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadToSwf")
    public JSONObject uploadToSwf(MultipartRequest multiPartRquest,
                                  HttpServletRequest request, Integer oneItemId, Integer twoItemId,
                                  String tag) {
        JSONObject json = new JSONObject();
        boolean isDoc = false;
        MultipartFile file = multiPartRquest.getFile("files");

        Integer companyId = WebUtils.getCurrentCompanyId();

        Users user = WebUtils.getCurrentUser();

        CompanyServiceStatic css = companyServiceStaticImpl
                .findByCompanyId(companyId);
        CompanyMemberService cms = companyMemberServiceImpl
                .findByCompanyId(companyId);

		/*
		 * if ((file.getSize()) > (1024 * 1024 * 50)) { json.put("msg",
		 * "sizeOutOf"); json.put(JsonMsg.MSG, "文件不能超过 50MB"); return json; }
		 */

        String fna = file.getOriginalFilename();

        if (fna.length() > 120) {
            json.put(JsonMsg.MSG, "文件名太长");
            return json;
        }

        String suffix = fna.substring(fna.lastIndexOf(".")).toLowerCase();

        fna = fna.substring(0, fna.lastIndexOf("."));
        fna += suffix;

        // CompanyPayConfig cpc =
        // companyPayConfigImpl.findByCompanyId(companyId);

        if (suffix.equals(".pdf")) {
            log.info("是pdf转swf");
            json = pdfUpload(file, fna, "docs", suffix);

        } else if (suffix.equals(".swf")) {
            log.info("是swf，直接上传");
            json = swfUpload(file, fna,"flash",suffix);

        } else {
            String[] types1 = new String[] { ".doc", ".docx", ".xls", ".xlsx" };
            boolean b1 = false;
            boolean b2 = false;
            for (String s : types1) {
                if (s.equals(suffix)) {
                    b1 = true;
                    break;
                }
            }
            String[] types2 = new String[] { ".ppt", ".pptx" };
            for (String s : types2) {
                if (s.equals(suffix)) {
                    b2 = true;
                    break;
                }
            }
            if (b1) {
                json = officeUpload(file, fna, "docs");
                isDoc = true;
            } else if (b2) {
                json = officeUpload(file, fna, "ppt");
                isDoc = true;
            } else {
                json = audioUpload(file, fna, suffix, "audio");
            }

        }
        if (json.getString("msg").equals("success")) {
            Date date = new Date();
            ResourceList rl = new ResourceList();
            rl.setCompanyId(companyId);
            rl.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
            rl.setSysItemOne(oneItemId);
            rl.setSysItemSecond(twoItemId);
            rl.setUploadTime(date);
            rl.setUploadType(0);
            rl.setUploadUserId(user.getId());
            rl.setUploadUserName((user.getRealName() != null ? user
                    .getRealName() : user.getUsername()));
            rl.setDelFlag(0);
            rl.setTag(tag);
            rl.setFileCategory(json.getString("fileCategory"));
            rl.setFileName(json.getString("fileName"));
            rl.setFilePath(json.getString("filePath"));
            rl.setFileSize(json.getString("fileSize"));
            rl.setFileType(json.getString("fileType"));
            rl.setUuid(json.getString("uuid"));
            rl.setSourcePath(json.getString("sourcePath"));
            rl.setSourceSize(json.getString("sourceSize"));
            rl.setOldData(0);
            resourceListImpl.insert(rl);
            if(isDoc){
                SysFileConvertTask task = new SysFileConvertTask();
                task.setCompanyId(companyId);
                task.setFileId(rl.getId());
                task.setCreateTime(new Date());
                task.setCreator(user.getId());
                task.setFilePath(json.getString("sourcePath"));
                task.setStatus(0);
                task.setVersion(0);
                sysFileConvertTaskServiceImpl.insert(task);
            }


            if (!rl.getTag().isEmpty()) {
                VideoTag vt = new VideoTag();
                vt.setTagName(rl.getTag());
                vt.setCompanyId(rl.getCompanyId());
                VideoTag vtag = videoTagServiceImpl.findExist(vt);
                if (vtag == null) {
                    videoTagServiceImpl.insert(vt);
                }
            }
            json.clear();
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            json.put("id", rl.getId());
        }

        return json;
    }

    /**
     *
     * Class Name: ClassTypeResourceController.java
     *
     * @Description: 资源预览
     * @author 周文斌
     * @date 2016-9-7 下午5:04:53
     * @modify 2016-9-7 下午5:04:53
     * @version
     * @param args
     */
    @ResponseBody
    @RequestMapping("/pview")
    public JSONObject pview(HttpServletRequest request, Integer id) {
        JSONObject json = new JSONObject();
        Integer companyId = WebUtils.getCurrentCompanyId();
        try {
            ResourceList rl = resourceListImpl.findResourceListById(id);
            if (rl.getFilePath() == null || rl.getFilePath().equals("")) {
                json.put(JsonMsg.MSG, "当前资源文件无法预览");
                return json;
            }

            String p = "http://" + propertiesUtil.getProjectImageUrl() + "/"
                    + rl.getFilePath();
            json.put("path", p);
            json.put("cate", rl.getFileCategory());
            json.put("resid", rl.getId());
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            json.put(JsonMsg.MSG, "预览异常，当前资源无法预览");
        }
        return json;
    }

    /**
     *
     * Class Name: ClassModuleController.java
     *
     * @Description: 预览pdf
     * @author 周文斌
     * @date 2016-10-26 下午3:31:14
     * @modify 2016-10-26 下午3:31:14
     * @version
     * @param request
     * @param response
     * @param resid
     */
    @RequestMapping("/getPDFStream/{resid}")
    public void getPDFStream(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer resid) {
        Users user = WebUtils.getCurrentUser(request);
        CompanyServiceStatic css = companyServiceStaticImpl
                .findByCompanyId(user.getCompanyId());
        try {
            ResourceList rl = resourceListImpl.findResourceListById(resid);

            String p = "http://" + propertiesUtil.getProjectImageUrl() + "/"
                    + rl.getFilePath();
            InputStream in = new URL(p).openConnection().getInputStream();

            response.setContentType("application/octet-stream");
            OutputStream os = response.getOutputStream();

            byte[] b = new byte[1024 * 40];
            int i = 0;
            while ((i = in.read(b)) != -1) {
                os.write(b, 0, i);
            }

            in.close();
            os.flush();
            os.close();

            long dangq = (Long.parseLong(rl.getFileSize()));
            ResourceUseRecord rur = new ResourceUseRecord();
            rur.setFileId(rl.getId());
            rur.setUseFlow((dangq) + "");
            if (user.getId() != null) {
                rur.setUserId("b_" + user.getId());
            }
            rur.setUseTime(new Date());
            rur.setUseType(0);
            rur.setCompanyId(user.getCompanyId());
            resourceUseRecordImpl.insert(rur);
            long crf = Long.parseLong(css.getResourceFlow() != null ? css
                    .getResourceFlow() : "0");
            css.setResourceFlow((crf + dangq) + "");
            companyServiceStaticImpl.update(css);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("MalformedURL读取流错误," + e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("IO 文件错误," + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("其他错误," + e.getMessage());
        }
    }

    public static void main(String[] args) {
        File f = new File("C:/Users/1/Desktop/附件1-嘉福操作流程160902.pdf");
        ClassTypeResourceController crc = new ClassTypeResourceController();

    }

    private JSONObject pdfUpload(MultipartFile file, String fna, String cate,
                                 String suffix) {
        JSONObject json = new JSONObject();

        String filename = UUID.randomUUID().toString();
        String pdfpath = propertiesUtil.getFileStoragePath() + filename
                + ".pdf";

        File pdffile = new File(pdfpath);
        try {

            file.transferTo(pdffile);
            json = FileUtil.uploadDocument(pdffile, "coursefile", "zs");
            String s1 = json.getString("size");
            json.put("fileSize", s1);
            json.put("fileName", fna);
            json.put("fileType", suffix.substring(1));
            json.put("uuid", filename);
            json.put("fileCategory", cate);
        } catch (IllegalStateException e) {
            log.error("参数非法，" + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, "参数非法");
        } catch (IOException e) {
            log.error("文件读取异常，" + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, "文件读取异常");
        } catch (Exception e) {
            log.error("上传pdf文件出现错误," + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, "上传pdf文件出现错误");
        } finally {
            //pdffile.delete();
            return json;
        }
    }

    private JSONObject swfUpload(MultipartFile file, String fna,String cate,String suffix) {
        JSONObject json = new JSONObject();
        String filename = UUID.randomUUID().toString();
        String swfpath = propertiesUtil.getFileStoragePath() + filename
                + ".swf";
        File swffile = new File(swfpath);
        try {
            file.transferTo(swffile);
            json = FileUtil.uploadDocument(swffile, "coursefile", "zs");
            String s1 = json.getString("size");
            json.put("fileSize", s1);
            json.put("fileName", fna);
            json.put("fileType", suffix.substring(1));
            json.put("uuid", filename);
            json.put("fileCategory", cate);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            log.error("参数非法，" + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, "参数非法");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error("文件读取异常，" + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, "文件读取异常");
        } catch (Exception e) {
            log.error("上传pdf文件出现错误," + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, "上传pdf文件出现错误");
        } finally {
            //swffile.delete();
            return json;
        }
    }

    private JSONObject officeUpload(MultipartFile file, String fna, String cate) {
        JSONObject json = new JSONObject();
        log.info("将office转成pdf");
        String filename = UUID.randomUUID().toString();
        String oldname = file.getOriginalFilename();
        String suffix = oldname.substring(oldname.lastIndexOf("."));
        String officepath = propertiesUtil.getFileStoragePath() + filename
                + suffix;
        String pdfpath = "";

        log.info("office文件存放地址," + officepath);
        File office = new File(officepath);
        try {
            file.transferTo(office);
            //Map<String, String> map = OfficeTransform.transformPdf(null,officepath);
            //String msg = map.get("msg").toString();
            //pdfpath = map.get("pdf").toString();
            //if (msg.equals(JsonMsg.SUCCESS)) {
            log.info("成功转换为pdf，地址" + pdfpath);
            // 转换为swf
            //json = FileUtil.uploadDocument(new File(pdfpath), "coursefile", "zs");
            JSONObject jsons = FileUtil.uploadDocument(office, "coursefile", "zs");
            String s1 = json.getString("size");
            String s2 = jsons.getString("size");
            json.put("fileSize", s1);
            json.put("sourceSize", s2);
            json.put("sourcePath", jsons.getString("filePath"));
            json.put("fileName", fna);
            json.put("fileType", suffix.substring(1));
            json.put("uuid", filename);
            json.put("fileCategory", cate);
            json.put(JsonMsg.MSG,JsonMsg.SUCCESS);
//            } else {
//                json.put(JsonMsg.MSG, "office文档转换错误");
//            }
        } catch (IllegalStateException e) {
            log.error("参数非法，" + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, "参数非法");
        } catch (IOException e) {
            log.error("文件读取异常，" + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, "文件读取异常");
        } catch (Exception e) {
            log.error("上传office文件出现错误," + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, "上传office文件出现错误");
        } finally {
            //new File(pdfpath).delete();
            //office.delete();
            return json;
        }
    }

    private JSONObject audioUpload(MultipartFile file, String fna,
                                   String suffix, String cate) {
        JSONObject json = new JSONObject();
        String filename = UUID.randomUUID().toString();
        String swfpath = propertiesUtil.getFileStoragePath() + filename
                + suffix;
        File swffile = new File(swfpath);
        try {
            file.transferTo(swffile);
            json = FileUtil.uploadDocument(swffile, "coursefile", "zs");
            String s1 = json.getString("size");
            json.put("fileSize", s1);
            json.put("fileName", fna);
            json.put("fileType", suffix.substring(1));
            json.put("uuid", filename);
            json.put("fileCategory", cate);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            log.error("参数非法，" + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, "参数非法");
        } catch (IOException e) { // TODO Auto-generated catch block
            log.error("文件读取异常，" + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, "文件读取异常");
        } catch (Exception e) {
            log.error("上传pdf文件出现错误," + e.getMessage(), e);
            e.printStackTrace();
            json.put(JsonMsg.MSG, "上传audio文件出现错误");
        } finally {
            //swffile.delete();
            return json;
        }
    }
}