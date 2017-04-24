package com.yuxin.wx.controller.homework;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.homework.IHomeworkService;
import com.yuxin.wx.api.homework.IHomeworkStudentCompleteService;
import com.yuxin.wx.api.homework.IHomeworkTeacherReadService;
import com.yuxin.wx.api.resource.IResourceListService;
import com.yuxin.wx.api.student.IStudentService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.homework.Homework;
import com.yuxin.wx.model.homework.HomeworkStudentComplete;
import com.yuxin.wx.model.homework.HomeworkTeacherRead;
import com.yuxin.wx.model.resource.ResourceList;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.homework.StudentHomeWorkVo;
import com.yuxin.wx.vo.homework.StudentHomeworkAttachmentVo;
import com.yuxin.wx.vo.user.UsersFrontVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller of HomeworkStudentComplete
 *
 * @author chopin
 * @date 2016-12-15
 */
@Controller
@RequestMapping("/homeworkStudentComplete")
public class HomeworkStudentCompleteController {

    @Autowired
    private IHomeworkStudentCompleteService homeworkStudentCompleteServiceImpl;
    @Autowired
    private IHomeworkService homeworkServiceImpl;
    @Autowired
    private IHomeworkTeacherReadService homeworkTeacherReadServiceImpl;
    @Autowired
    private IResourceListService resourceListServiceImpl;
    @Autowired
    private ICompanyServiceStaticService companyServiceStaticImpl;
    @Autowired
    private IResourceListService resourceListImpl;
    @Autowired
    private IStudentService studentServiceImpl;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HomeworkStudentComplete search) {
        if (search == null) {
            search = new HomeworkStudentComplete();
            // search.setPageSize(20);
        }
        model.addAttribute("list", this.homeworkStudentCompleteServiceImpl.findHomeworkStudentCompleteByPage(search));
        return "homeworkStudentComplete/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HomeworkStudentComplete HomeworkStudentComplete) {
        this.homeworkStudentCompleteServiceImpl.insert(HomeworkStudentComplete);
        return "redirect:/homeworkStudentComplete";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HomeworkStudentComplete HomeworkStudentComplete) {
        this.homeworkStudentCompleteServiceImpl.update(HomeworkStudentComplete);
        return "redirect:/homeworkStudentComplete";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String del(Model model, @PathVariable Integer id) {
        this.homeworkStudentCompleteServiceImpl.deleteHomeworkStudentCompleteById(id);
        return "redirect:/homeworkStudentComplete";
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public HomeworkStudentComplete getJson(Model model, @PathVariable Integer id) {
        return this.homeworkStudentCompleteServiceImpl.findHomeworkStudentCompleteById(id);
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
     * Class Name: HomeworkStudentCompleteController.java
     *
     * @Description: 进入作业学员列表页面
     * @author dongshuai
     * @date 2016年12月15日 下午6:43:16
     * @modifier
     * @modify-date 2016年12月15日 下午6:43:16
     * @version 1.0
     * @param model
     * @param courseId
     * @param lessonId
     * @param shv
     * @return
     */
    @RequestMapping(value = "/gotoHomeworkStudentCompletePage/{resourceId:\\d+}/{moduleId:\\d+}/{homeworkId:\\d+}", method = { RequestMethod.POST,
            RequestMethod.GET })
    public String gotoHomeworkStudentCompletePage(StudentHomeWorkVo shv, Model model, @PathVariable Integer resourceId, @PathVariable Integer moduleId,
            @PathVariable Integer homeworkId) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        Homework h = this.homeworkServiceImpl.findHomeworkById(homeworkId);

        shv.setCompanyId(companyId);
        shv.setResourceId(resourceId);
        shv.setCourseId(h.getCourseId());
        shv.setModuleId(moduleId);
        shv.setHomeworkId(homeworkId);
        /* 统计 */
        shv = this.homeworkStudentCompleteServiceImpl.queryAllStudentHomework(shv);

        model.addAttribute("homeworkId", homeworkId);
        model.addAttribute("resourceId", resourceId);
        model.addAttribute("courseId", h.getCourseId());
        model.addAttribute("moduleId", moduleId);
        model.addAttribute("courseName", h.getCourseName());
        model.addAttribute("lessonName", h.getLessonName());
        model.addAttribute("homeworkStatus", shv.getStudentHomeworkListStatus());
        model.addAttribute("alreadyCommit", shv.getAlreadyCommitNum());
        model.addAttribute("alreadyRead", shv.getAlreadyReadNum());
        model.addAttribute("lastCommit", shv.getStudentNum() - shv.getAlreadyCommitNum());
        model.addAttribute("lastRead", shv.getAlreadyCommitNum() - shv.getAlreadyReadNum());

        if (h != null) {
            model.addAttribute("type", h.getType());
        }

        // 学员分组
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("STUDENT_GROUP");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        if (companyFunctionSetList != null && companyFunctionSetList.size() > 0) {
            model.addAttribute("sgOpen", companyFunctionSetList.get(0).getStatus());
        }
        return "homework/studentHomeworks";
    }

    /**
     *
     * Class Name: HomeworkStudentCompleteController.java
     *
     * @Description: 学员作业列表
     * @author dongshuai
     * @date 2016年12月16日 上午10:38:42
     * @modifier
     * @modify-date 2016年12月16日 上午10:38:42
     * @version 1.0
     * @param shv
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getHomeworkStudentCompleteDataList", method = RequestMethod.POST)
    public PageFinder<StudentHomeWorkVo> getHomeworkStudentCompleteDataList(StudentHomeWorkVo shv) {
        Integer companyId = WebUtils.getCurrentCompanyId();

        shv.setCompanyId(companyId);

        PageFinder<StudentHomeWorkVo> pageFinder = this.homeworkStudentCompleteServiceImpl.queryStudentHomeworks(shv);

        return pageFinder;
    }

    /**
     *
     * Class Name: HomeworkStudentCompleteController.java
     *
     * @Description: 附件作业批阅页面
     * @author dongshuai
     * @date 2016年12月16日 下午5:57:03
     * @modifier
     * @modify-date 2016年12月16日 下午5:57:03
     * @version 1.0
     * @param model
     * @param homeworkId
     * @param stuId
     * @return
     */
    @RequestMapping(value = "/gotoAttachmentStudentPaperHomeworkPage", method = RequestMethod.GET)
    public String gotoAttachmentStudentPaperHomeworkPage(Model model, Integer studentCompleteId, Integer hwId) {
        Homework hw = this.homeworkServiceImpl.findHomeworkById(hwId);
        model.addAttribute("hw", hw);
        HomeworkStudentComplete hsc = this.homeworkStudentCompleteServiceImpl.findHomeworkStudentCompleteById(studentCompleteId);
        if (hsc != null && hsc.getId() != null) {
            model.addAttribute("hsc", hsc);
            UsersFrontVo frontVo = this.studentServiceImpl.findUserFrontvoByStuId(hsc.getStuId());

            model.addAttribute("frontVo", frontVo);
            // 查询是否已批阅
            HomeworkTeacherRead htr = new HomeworkTeacherRead();
            htr.setHomeworkId(hw.getId());
            htr.setHomeworkSId(hsc.getId());
            List<HomeworkTeacherRead> homeworkTeacherReadList = this.homeworkTeacherReadServiceImpl.findHomeworkTeacherReadByPage(htr);
            if (homeworkTeacherReadList != null && homeworkTeacherReadList.size() > 0) {
                model.addAttribute("htr", homeworkTeacherReadList.get(0));
            }
        }
        return "/homework/arrachmentStudentPaperHomework";
    }

    /**
     *
     * Class Name: HomeworkStudentCompleteController.java
     *
     * @Description: 附件作业批阅页面
     * @author dongshuai
     * @date 2016年12月16日 下午5:57:03
     * @modifier
     * @modify-date 2016年12月16日 下午5:57:03
     * @version 1.0
     * @param model
     * @param homeworkId
     * @param stuId
     * @return
     */
    @RequestMapping(value = "/gotoAttachmentStudentHomeworkPage/{homeworkId:\\d+}/{stuId:\\d+}/{stuName}/{hscId:\\d+}/{resourceId:\\d+}/{moduleId:\\d+}", method = RequestMethod.GET)
    public String gotoAttachmentStudentHomeworkPage(Model model, @PathVariable Integer homeworkId, @PathVariable Integer stuId, @PathVariable String stuName,
            @PathVariable Integer hscId, @PathVariable Integer resourceId, @PathVariable Integer moduleId) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        model.addAttribute("companyId", companyId);

        Homework h = this.homeworkServiceImpl.findHomeworkById(homeworkId);
        HomeworkStudentComplete hsc = this.homeworkStudentCompleteServiceImpl.findHomeworkStudentCompleteById(hscId);
        if (hsc == null) {
            hsc = new HomeworkStudentComplete();
            hsc.setCompanyId(companyId);
            hsc.setHomeworkId(homeworkId);
            hsc.setStuId(stuId);
            hsc.setStatus(0);
            this.homeworkStudentCompleteServiceImpl.insert(hsc);
        }
        model.addAttribute("hscId", hsc.getId());
        model.addAttribute("stuId", stuId);
        model.addAttribute("resourceId", resourceId);
        model.addAttribute("moduleId", moduleId);
        try {
            model.addAttribute("stuName", URLDecoder.decode(stuName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (hsc != null && hsc.getStatus() != null) {
            model.addAttribute("status", hsc.getStatus());
            switch (hsc.getStatus()) {
            case 0:
                model.addAttribute("statusHtml", "<em>学员未提交作业</em>");
                break;
            case 1:
                HomeworkTeacherRead htr = new HomeworkTeacherRead();
                htr.setReader(WebUtils.getCurrentUser().getId());
                htr.setHomeworkId(homeworkId);
                htr.setHomeworkSId(hsc.getId());
                htr = this.homeworkTeacherReadServiceImpl.queryTeacherHomeworkRead(htr);

                if (htr == null) {
                    htr = new HomeworkTeacherRead();
                    htr.setReader(WebUtils.getCurrentUser().getId());
                    htr.setHomeworkId(homeworkId);
                    htr.setHomeworkSId(hsc.getId());
                    this.homeworkTeacherReadServiceImpl.insert(htr);
                }
                model.addAttribute("htrId", htr.getId());

                if (htr.getResourceId() != null) {
                    ResourceList r = this.resourceListServiceImpl.findResourceListById(Integer.parseInt(htr.getResourceId()));
                    model.addAttribute("htrResourceId", htr.getResourceId());
                    model.addAttribute("htrResourceName", r.getFileName());
                }
                model.addAttribute("statusHtml", "<em>作业已提交作业</em>");
                break;
            case 2:
                model.addAttribute("statusHtml", "<em>待重新提交作业</em>");
                break;
            case 3:
                model.addAttribute("statusHtml", "<em>作业已完成批阅</em>");
                break;
            default:
                break;
            }
        }
        if (h != null) {
            model.addAttribute("homework", h);
        }
        return "/homework/attachmentStudentHomework";
    }

    /**
     *
     * Class Name: HomeworkStudentCompleteController.java
     *
     * @Description: 作业回答与批阅
     * @author dongshuai
     * @date 2016年12月16日 下午6:12:04
     * @modifier
     * @modify-date 2016年12月16日 下午6:12:04
     * @version 1.0
     * @param shav
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAttachmentStudentHomeworkList", method = RequestMethod.POST)
    public List<StudentHomeworkAttachmentVo> getAttachmentStudentHomeworkList(StudentHomeworkAttachmentVo shav) {
        Integer companyId = WebUtils.getCurrentCompanyId();

        shav.setCompanyId(companyId);
        List<StudentHomeworkAttachmentVo> list = this.homeworkStudentCompleteServiceImpl.queryStudentHomeworkAndReadList(shav);
        return list;
    }

    /**
     *
     * Class Name: HomeworkStudentCompleteController.java
     *
     * @Description: 删除作业附件
     * @author dongshuai
     * @date 2016年12月30日 下午6:16:05
     * @modifier
     * @modify-date 2016年12月30日 下午6:16:05
     * @version 1.0
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

}
