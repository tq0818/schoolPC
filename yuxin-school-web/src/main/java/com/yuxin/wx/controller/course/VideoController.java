package com.yuxin.wx.controller.course;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyPayConfigService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.company.ICompanyVideoConfigService;
import com.yuxin.wx.api.course.IVideoService;
import com.yuxin.wx.api.course.IVideoTagService;
import com.yuxin.wx.api.resource.IResourceListService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.check.Base64;
import com.yuxin.wx.check.HashSha1;
import com.yuxin.wx.common.CCVideoConstant;
import com.yuxin.wx.common.Constant;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.PolyvParams;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.company.mapper.CompanyServiceStaticDayMapper;
import com.yuxin.wx.company.mapper.CompanyServiceStaticMapper;
import com.yuxin.wx.course.mapper.VideoMapper;
import com.yuxin.wx.model.ccVideo.CcNotifyVo;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;
import com.yuxin.wx.model.company.CompanyVideoConfig;
import com.yuxin.wx.model.course.Video;
import com.yuxin.wx.model.course.VideoTag;
import com.yuxin.wx.model.resource.ResourceList;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.util.APIServiceFunction;
import com.yuxin.wx.util.FileQNUtils;
import com.yuxin.wx.util.HttpPostRequest;
import com.yuxin.wx.util.MD5;
import com.yuxin.wx.util.SHA1;
import com.yuxin.wx.util.qiniuVideo.QNQueue;
import com.yuxin.wx.util.qiniuVideo.QNVideoUploadThread;
import com.yuxin.wx.utils.DateUtil;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.LetvCloudV1;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.utils.plupload.Plupload;
import com.yuxin.wx.utils.plupload.PluploadUtil;
import com.yuxin.wx.vo.course.VideoVo;

/**
 * Controller of Video
 *
 * @author wang.zx
 * @date 2015-5-8
 */
@Controller
@RequestMapping("/video")
public class VideoController {
    Log log = LogFactory.getLog("log");

    @Autowired
    private ICompanyVideoConfigService companyVideoConfigImpl;
    @Autowired
    private IResourceListService resourceListImpl;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;
    @Autowired
    private IVideoService videoServiceImpl;
    @Autowired
    private IVideoTagService videoTagServiceImpl;
    @Autowired
    private ICompanyMemberServiceService memberServiceService;
    @Autowired
    private ICompanyServiceStaticService serviceStaticService;
    @Autowired
    private ISysConfigItemService configItemService;
    @Autowired
    private IUsersService usersService;
    @Autowired
    private ISysConfigItemService sysConfigItemServiceImpl;
    @Autowired
    private ICompanyPayConfigService companyPayConfigService;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private CompanyServiceStaticDayMapper companyServiceStaticDayMapper;
    @Autowired
    private CompanyServiceStaticMapper companyServiceStaticMapper;
    @Autowired
    private ISysConfigDictService sysConfigDictServiceImpl;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @Description: 判断当前上传的文件是否大于所剩的空间大小
     * @author wzx
     * @date 2015-6-12 下午4:09:36
     * @version 1.0
     * @param request
     * @param totalSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "judgeVideoStorage", method = RequestMethod.POST)
    public String judgeVideoStorage(HttpServletRequest request, String totalSize) {
        Users user = WebUtils.getCurrentUser();
        if (user == null) {
            return "fail";
        }
        Integer companyId = user.getCompanyId();
        CompanyPayConfig config = this.companyPayConfigService.findByCompanyId(companyId);
        long time = System.currentTimeMillis();
        String salt = config.getCcApiKey();

        // 获取当前视频是否满足空间大小条件
        String videoInfo = this.getCCVideoUserInfo(config, totalSize, companyId, time, salt);
        if ("over_storage".equals(videoInfo) || "date_over".equals(videoInfo)) {
            return videoInfo;
        }
        return "no_storage";
    }

    /**
     * @Description: 乐视上传视频
     * @author hanrb
     * @date 2016-04-01
     * @version 1.0
     * @return
     */
    @RequestMapping("/uploadLetv")
    @ResponseBody
    public String uploadLetv(HttpServletRequest request, String video_name, Integer file_size, Integer uploadtype, String token) throws Exception {
        Users user = WebUtils.getCurrentUser();
        if (user == null) {
            return "fail";
        }
        Integer companyId = user.getCompanyId();
        CompanyPayConfig config = this.companyPayConfigService.findByCompanyId(companyId);
        LetvCloudV1 cloudInit = new LetvCloudV1(config.getLetvUUID(), config.getLetvApiKey());
        // 获取客户端的公网ip
        String client_ip = WebUtils.getIpAddr(request);
        if (token == null) {
            // 视频上传初始化（Web方式）
            String response = cloudInit.videoUploadInit(video_name, client_ip, file_size, uploadtype);
            return response;
        } else {
            // 视频上传续传
            String response = cloudInit.videoUploadResume(token, uploadtype);
            return response;
        }
    }

    /**
     * @Description: 乐视保存视频到表
     * @author hanrb
     * @date 2016-04-01
     * @version 1.0
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/saveLetv", method = RequestMethod.POST)
    public String saveLetv(String videoid, String fileName, String fileSize, String tag, Integer itemOneId, Integer itemSecondId, String videoUnique) {
        Users user = WebUtils.getCurrentUser();
        if (user == null) {
            return "fail";
        }

        Video video = new Video();
        NumberFormat nf = new DecimalFormat("###0.0");
        video.setCompanyId(user.getCompanyId());
        video.setItemOneId(itemOneId);
        video.setItemSecondId(itemSecondId);
        String fileSizeM = nf.format(Double.parseDouble(fileSize) / 1024 / 1024);
        video.setVodeoSize(Double.parseDouble(fileSizeM));
        video.setVideoName(fileName);
        video.setVideoTag(tag);
        video.setCreateTime(new Date());
        video.setCreator(user.getId());
        video.setSchoolId(user.getSchoolId());
        video.setVideoCcId(videoid);
        video.setStorageType("VIDEO_STORAGE_TYPE_LETV");
        video.setVideoStatus(Constant.VIDEO_PROCESS_INHAND);
        video.setWebVideoId(videoUnique);
        this.videoServiceImpl.insert(video);
        VideoTag vTag = new VideoTag();
        vTag.setCompanyId(user.getCompanyId());
        vTag.setTagName(tag);
        List<VideoTag> tags = this.videoTagServiceImpl.findVideoTagByPage(vTag);
        if (tags == null || (tags != null && tags.size() == 0)) {
            this.videoTagServiceImpl.insert(vTag);
        }

        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "letvNotify")
    public String letvNotify(HttpServletRequest request, CcNotifyVo notify) {
        String video_id = request.getParameter("video_id");
        this.log.info("处理完毕回调到回来");
        this.log.info("letv视频回调函数中 videoid：" + video_id + " status为：" + request.getParameter("status") + " duration为：" + request.getParameter("duration")
                + "image为：" + request.getParameter("image"));
        notify.setVideoid(video_id);
        if (notify != null && notify.getVideoid() != null) {
            this.log.info("视频ID为：" + notify.getVideoid());
            // 根据视频id获取companyid

            // Users user = WebUtils.getCurrentUser();
            // if (user == null) {
            // return "fail";
            // }
            // Integer companyId = user.getCompanyId();
            // CompanyPayConfig config =
            // companyPayConfigService.findByCompanyId(companyId);
            // LetvCloudV1 cloudInit = new LetvCloudV1(config.getLetvUserId(),
            // config.getLetvApiKey());
            // String video_unique = "";
            // try {
            // String videoInfo =
            // cloudInit.videoGet(Integer.parseInt(video_id));
            // System.out.println(videoInfo);
            // JSONObject obj = (JSONObject) JSONObject.parse(videoInfo);
            // if (obj.get("code") != null && obj.getInteger("code") == 0) {
            // JSONObject data = obj.getJSONObject("data");
            // notify.setImage(data.getString("img"));
            // notify.setDuration(data.getString("video_duration"));
            // video_unique = data.getString("video_unique");
            // }
            // } catch (Exception e) {
            // e.printStackTrace();
            // }

            Video video = new Video();
            video.setVideoCcId(notify.getVideoid());
            List<Video> videos = this.videoServiceImpl.findVideoByPage(video);
            if (videos != null && videos.size() > 0) {
                Video v = videos.get(0);
                Integer companyId = v.getCompanyId();
                CompanyPayConfig config = this.companyPayConfigService.findByCompanyId(companyId);
                LetvCloudV1 cloudInit = new LetvCloudV1(config.getLetvUUID(), config.getLetvApiKey());
                try {
                    String videoInfo = cloudInit.videoGet(Integer.parseInt(video_id));
                    JSONObject obj = (JSONObject) JSONObject.parse(videoInfo);
                    if (obj.get("code") != null && obj.getInteger("code") == 0) {
                        JSONObject data = obj.getJSONObject("data");
                        notify.setImage(data.getString("img"));
                        notify.setDuration(data.getString("video_duration"));
                        // video_unique = data.getString("video_unique");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (notify.getStatus().equals("OK")) {
                    v.setVideoStatus(Constant.VIDEO_PROCESS_NOMAL);

                } else if (notify.getStatus().equals("PLAY_OK") || notify.getStatus().equals("UPLOAD_OVER")) {

                } else {
                    v.setVideoStatus(Constant.VIDEO_PROCESS_FAIL);
                }

                v.setVideoPic(notify.getImage());
                // 返回的时常以秒为单位，需要将秒转换为 时分秒的形式
                if (notify.getDuration() != null && notify.getDuration().length() > 0) {
                    long durat = Long.parseLong(notify.getDuration());
                    long hour = durat / 3600; // !小时
                    long minute = durat % 3600 / 60; // !分钟
                    long second = durat % 60; // !秒

                    // 格式化视频时间长度
                    String hourStr = "";
                    String minuteStr = "";
                    String secondStr = "";
                    if (String.valueOf(hour).length() == 1) {
                        hourStr += "0" + hour;
                    } else {
                        hourStr = String.valueOf(hour);
                    }
                    if (String.valueOf(minute).length() == 1) {
                        minuteStr += "0" + minute;
                    } else {
                        minuteStr = String.valueOf(minute);
                    }
                    if (String.valueOf(second).length() == 1) {
                        secondStr += "0" + second;
                    } else {
                        secondStr = String.valueOf(second);
                    }

                    v.setVideoTime(hourStr + ":" + minuteStr + ":" + secondStr);
                }

                this.videoServiceImpl.update(v);
            }
        }
        this.log.info("=========letv视频返回处理完视频的之后===返回的结果为：" + notify);
        return "";
    }

    /**
     * @Description: H5异步上传视频
     * @author zx
     * @date 2015-7-22 下午4:35:08
     * @version 1.0
     * @param request
     * @param fileName
     * @param fileSize
     * @param tag
     * @param itemOneId
     * @param itemSecondId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String uploadVieo(HttpServletRequest request, String fileName, String fileSize, String tag, Integer itemOneId, Integer itemSecondId) {
        Users user = WebUtils.getCurrentUser();
        if (user == null) {
            return "fail";
        }

        Integer companyId = user.getCompanyId();

        CompanyPayConfig config = this.companyPayConfigService.findByCompanyId(companyId);

        this.log.info("CC视频上传，当前公司ID：" + companyId + ", 对应的配置信息为：" + config);

        String description = fileName;
        String notify_url = this.propertiesUtil.getHostUrl() + "/video/ccNotify";
        String format = "json";
        long time = System.currentTimeMillis();
        String salt = config.getCcApiKey();

        // 获取当前视频是否满足空间大小条件
        String videoInfo = this.getCCVideoUserInfo(config, fileSize, companyId, time, salt);
        this.log.info("CC视频上传，当前公司ID：" + companyId + ", 当前公司视频的使用情况：" + videoInfo);
        if ("over_storage".equals(videoInfo) || "date_over".equals(videoInfo)) {
            return videoInfo;
        }
        // 创建视频ID接口
        String url = CCVideoConstant.CC_VIDEO_CREATE;

        String title = fileName;
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("userid", config.getCcUserId());
        paramsMap.put("title", title);// new
                                      // String(title.getBytes("ISO-8859-1"),
                                      // "UTF-8"));
        paramsMap.put("tag", tag);// new String(tag.getBytes("ISO-8859-1"),
                                  // "UTF-8"));
        paramsMap.put("description", description);// new
                                                  // String(description.getBytes("ISO-8859-1"),
                                                  // "UTF-8"));
        paramsMap.put("filename", fileName);
        paramsMap.put("filesize", fileSize);
        paramsMap.put("notify_url", notify_url);
        paramsMap.put("format", format);

        String hash = APIServiceFunction.createHashedQueryString(paramsMap, time, salt);

        this.log.info("CC视频上传，当前公司ID：" + companyId + ", hash为：" + hash);

        url += hash;
        String detail;

        NumberFormat nf = new DecimalFormat("###0.0");

        try {
            detail = HttpPostRequest.get(url);
            Integer index = detail.indexOf("videoid");

            this.log.info("CC视频上传，当前公司ID：" + companyId + ", detail为：" + detail);
            // 将字符串转换为json对象，获取cc视频的ID，保存到数据库中
            if (index > 0) {
                // 保存到视频表中
                Video video = new Video();

                video.setCompanyId(user.getCompanyId());
                video.setItemOneId(itemOneId);
                video.setItemSecondId(itemSecondId);
                video.setStorageType("VIDEO_STORAGE_TYPE_CC");
                String fileSizeM = nf.format(Double.parseDouble(fileSize) / 1024 / 1024);
                video.setVodeoSize(Double.parseDouble(fileSizeM));
                video.setVideoName(fileName.substring(0, fileName.lastIndexOf(".")));
                video.setVideoTag(tag);
                video.setCreateTime(new Date());
                video.setCreator(user.getId());
                video.setSchoolId(user.getSchoolId());
                video.setVideoCcId(detail.substring(index + 10, index + 42));
                video.setVideoStatus(Constant.VIDEO_PROCESS_UPLOAD);
                this.videoServiceImpl.insert(video);

                VideoTag vTag = new VideoTag();
                vTag.setCompanyId(companyId);
                vTag.setTagName(tag);
                List<VideoTag> tags = this.videoTagServiceImpl.findVideoTagByPage(vTag);
                if (tags == null || (tags != null && tags.size() == 0)) {
                    this.videoTagServiceImpl.insert(vTag);
                }
                return detail;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * @Description: 根据当前的视频大小判断购买的空间剩余大小是否够存放
     * @author wzx
     * @date 2015-6-12 下午5:04:53
     * @version 1.0
     * @param config
     * @param fileSize
     * @param companyId
     * @param time
     * @param salt
     * @return
     */
    public String getCCVideoUserInfo(CompanyPayConfig config, String fileSize, Integer companyId, long time, String salt) {
        // 获取用户信息接口
        String userUrl = CCVideoConstant.USERINFO;
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", config.getCcUserId());
        params.put("format", "json");
        String hashUser = APIServiceFunction.createHashedQueryString(params, time, salt);
        userUrl += hashUser;
        String userInfo;
        try {
            userInfo = HttpPostRequest.get(userUrl);
            String str = (JSONObject.parseObject(userInfo)).getString("user");
            String str1 = (JSONObject.parseObject(str)).getString("space");
            // 获取出当前用户已经用了多少 {\"total\":100,\"remain\":98.335,\"used\":1.665
            String used = (JSONObject.parseObject(str1)).getString("used");

            // 查询当前公司服务信息， 判断当前公司服务时间是否到期、视频空间是否还满足继续上传的条件
            CompanyMemberService member = this.memberServiceService.findByCompanyId(companyId);
            if (member != null) {
                long diffDate = this.diffDateWithNow(member);
                if (diffDate < 0) {
                    return "date_over";
                }
            } else {
                return "redirect:/video/toVideo";
            }
            if (member != null && member.getGiveVideoStorage() != null && member.getVideoStorage() != null && used != null) {
                Integer totalStorage = member.getGiveVideoStorage() + member.getVideoStorage();
                Double usedStorage = Double.parseDouble(used);
                DecimalFormat df = new DecimalFormat("0.00");
                long intFileSize = Long.parseLong(fileSize);
                this.log.info("intFileSize:" + intFileSize + " , 格式化之后的:" + df.format(intFileSize / 1024 / 1024));
                if ((totalStorage - (usedStorage + Double.parseDouble(df.format(intFileSize / 1024 / 1024 / 1024)))) < 0) {
                    return "over_storage";
                }
            }
            this.log.info("当前用的CC视频的信息为:" + userInfo);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return "no_storage";
    }

    @ResponseBody
    @RequestMapping(value = "ccNotify", method = RequestMethod.GET)
    public String ccNotify(HttpServletRequest request, CcNotifyVo notify) {
        this.log.info("处理完毕回调到回来");
        this.log.info("CC视频回调函数中 videoid：" + request.getParameter("videoid") + " status为：" + request.getParameter("status") + " duration为："
                + request.getParameter("duration") + "image为：" + request.getParameter("image"));
        // CcNotify notify = JSONObject.parseObject(CcNotifyVo.class);
        if (notify != null && notify.getVideoid() != null) {
            this.log.info("视频ID为：" + notify.getVideoid());
            Video video = new Video();
            video.setVideoCcId(notify.getVideoid());
            List<Video> videos = this.videoServiceImpl.findVideoByPage(video);

            if (videos != null && videos.size() > 0) {
                Video v = videos.get(0);
                Double videoSize = v.getVodeoSize();
                if (notify.getStatus().equals("OK")) {
                    v.setVideoStatus(Constant.VIDEO_PROCESS_NOMAL);
                } else {
                    v.setVideoStatus(Constant.VIDEO_PROCESS_FAIL);
                }

                v.setVideoPic(notify.getImage());
                // 返回的时常以秒为单位，需要将秒转换为 时分秒的形式
                if (notify.getDuration() != null && notify.getDuration().length() > 0) {
                    long durat = Long.parseLong(notify.getDuration());
                    long hour = durat / 3600; // !小时
                    long minute = durat % 3600 / 60; // !分钟
                    long second = durat % 60; // !秒

                    // 格式化视频时间长度
                    String hourStr = "";
                    String minuteStr = "";
                    String secondStr = "";
                    if (String.valueOf(hour).length() == 1) {
                        hourStr += "0" + hour;
                    } else {
                        hourStr = String.valueOf(hour);
                    }
                    if (String.valueOf(minute).length() == 1) {
                        minuteStr += "0" + minute;
                    } else {
                        minuteStr = String.valueOf(minute);
                    }
                    if (String.valueOf(second).length() == 1) {
                        secondStr += "0" + second;
                    } else {
                        secondStr = String.valueOf(second);
                    }

                    v.setVideoTime(hourStr + ":" + minuteStr + ":" + secondStr);
                }

                // 调用cc视频接口 修改视频文件大小 处理公共账号的公司（公共账号&&收费用户） hanrb
                // 查询用户信息
                // 根据账号请求cc接口获取问价大小
                Map<String, String> queryMap = new HashMap<String, String>();

                CompanyPayConfig config = this.companyPayConfigService.findByCompanyId(v.getCompanyId());
                String userUrl = "http://spark.bokecc.com/api/video/v2?";
                config.getCcApiKey();
                long time = System.currentTimeMillis();
                String salt = config.getCcApiKey();
                queryMap.put("videoid", v.getVideoCcId());
                queryMap.put("userid", config.getCcUserId());
                queryMap.put("format", "json");
                String hashUser = APIServiceFunction.createHashedQueryString(queryMap, time, salt);
                userUrl += hashUser;
                String userInfo;
                try {
                    userInfo = HttpPostRequest.get(userUrl);
                    JSONObject obj = JSONObject.parseObject(userInfo);
                    JSONObject videoObj = obj.getJSONObject("video");
                    JSONArray arr = videoObj.getJSONArray("definition");
                    long fileSize = 0L;
                    for (int i = 0; i < arr.size(); i++) {
                        JSONObject definition = (JSONObject) arr.get(i);
                        fileSize += definition.getLong("filesize");
                    }
                    Double doubleSize = Double.parseDouble(String.valueOf(fileSize)) / 1024 / 1024;
                    doubleSize = new BigDecimal(doubleSize).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue() + (videoSize == null ? 0.0 : videoSize);
                    // 修改video视频的大小 改为M
                    v.setVodeoSize(doubleSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.videoServiceImpl.update(v);
                // 处理公共账号的公司（公共账号&&收费用户）
                if (config.getCcUserId().equals("9D962C153919B4DA")) {
                    // 查询公司
                    Company company = this.companyService.findCompanyById(v.getCompanyId());
                    if (company.getBuyFlag() != null && company.getBuyFlag().equals(1)) {
                        VideoVo search = new VideoVo();
                        search.setCompanyId(v.getCompanyId());
                        search.setCreateTime(v.getCreateTime());
                        // 获取当天的
                        String sum = this.videoMapper.sumVideoSize(search);
                        Double storage = Double.parseDouble(sum);
                        storage = new BigDecimal(storage / 1024).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                        // 更新公司今天使用情况
                        CompanyServiceStaticDay companyServiceStaticDay = new CompanyServiceStaticDay();
                        companyServiceStaticDay.setCompanyId(v.getCompanyId());
                        SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");

                        try {
                            companyServiceStaticDay.setUseDate(today.parse(today.format(v.getCreateTime())));
                            CompanyServiceStaticDay service = this.companyServiceStaticDayMapper.findByDateAndCompanyId(companyServiceStaticDay);
                            if (service == null) {
                                service = new CompanyServiceStaticDay();
                                service.setCompanyId(v.getCompanyId());
                                service.setUseDate(today.parse(today.format(v.getCreateTime())));
                                service.setVideoStorageNum(storage);
                                this.companyServiceStaticDayMapper.insert(service);
                            } else {
                                service.setVideoStorageNum(storage);
                                this.companyServiceStaticDayMapper.update(service);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // 更新公司总使用量
                        search.setCreateTime(null);
                        // 获取当天的
                        String totalSum = this.videoMapper.sumVideoSize(search);
                        Double totalStorage = Double.parseDouble(totalSum);
                        CompanyServiceStatic css = this.companyServiceStaticMapper.findByCompanyId(v.getCompanyId());
                        CompanyServiceStatic updateStatic = new CompanyServiceStatic();
                        totalStorage = new BigDecimal(totalStorage / 1024).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                        updateStatic.setVideoStorage(totalStorage);
                        if (css == null || css.getId() == null) {
                            updateStatic.setCompanyId(v.getCompanyId());
                            this.companyServiceStaticMapper.insert(updateStatic);
                        } else {
                            updateStatic.setId(css.getId());
                            this.companyServiceStaticMapper.update(updateStatic);
                        }
                    }
                }
            }
        }
        this.log.info("=========CC视频返回处理完视频的之后===返回的结果为：" + notify);

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "\n" + "<video>OK</video>";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, Video search) {
        if (search == null) {
            search = new Video();
            // search.setPageSize(20);
        }
        model.addAttribute("list", this.videoServiceImpl.findVideoByPage(search));
        return "video/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Video Video) {
        this.videoServiceImpl.insert(Video);
        return "redirect:/video";
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Video video) {
        this.videoServiceImpl.update(video);

        // 更新标签
        if (video.getVideoTag() != null && !video.equals("")) {
            VideoTag vTag = new VideoTag();
            vTag.setCompanyId(WebUtils.getCurrentCompanyId());
            vTag.setTagName(video.getVideoTag());
            List<VideoTag> tags = this.videoTagServiceImpl.findVideoTagByPage(vTag);
            if (tags == null || (tags != null && tags.size() == 0)) {
                this.videoTagServiceImpl.insert(vTag);
            }
        }

        return JsonMsg.SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/udpateStatus/{ccvid}", method = RequestMethod.POST)
    public String udpateStatus(@PathVariable String ccvid) {
        Video video = new Video();
        video.setVideoCcId(ccvid);
        List<Video> videos = this.videoServiceImpl.findVideoByPage(video);
        if (videos != null && videos.size() > 0) {
            Video video1 = videos.get(0);
            video1.setVideoStatus(Constant.VIDEO_PROCESS_INHAND);
            this.videoServiceImpl.update(videos.get(0));
        }
        return JsonMsg.SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/del/{ccvid}", method = RequestMethod.POST)
    public String del(Model model, @PathVariable String ccvid) {
        this.videoServiceImpl.deleteVideoByCCVId(ccvid);
        return JsonMsg.SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/delVideo/{vid}")
    public String delVideo(HttpServletRequest request, Model model, @PathVariable Integer vid) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyPayConfig config = this.companyPayConfigService.findByCompanyId(companyId);
        CompanyVideoConfig cvc = companyVideoConfigImpl.findConfigByCompanyId(companyId);
        String userUrl = CCVideoConstant.CC_DELETE_VIDEO;
        String videoid = request.getParameter("videoid");
        String stype = request.getParameter("stype");
        System.out.println("videoId:" + videoid);
        Video v = videoServiceImpl.findVideoById(vid);
        // 增加乐视视频删除 hanrb
        switch (stype) {
        case "VIDEO_STORAGE_TYPE_LETV": {
            // 查询账号
            LetvCloudV1 letvCloudV1 = new LetvCloudV1(config.getLetvUUID(), config.getLetvApiKey());
            try {
                letvCloudV1.videoDel(Integer.parseInt(videoid));
                this.videoServiceImpl.deleteVideoById(vid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            return JsonMsg.SUCCESS;
        case "VIDEO_STORAGE_TYPE_BLVS": {
            if (cvc != null && !"blvs".equals(cvc.getVideoType())) {
                cvc = null;
            }
            Video video = videoMapper.findById(vid);
            String strId = video.getWebVideoId();
            String url = PolyvParams.DELETE_VIDEO;
            String writetoken = (cvc != null ? cvc.getWriteToken() : PolyvParams.WRITE_TOKEN);
            String ssk = "vid=" + strId + "&writetoken=" + writetoken + (cvc != null ? cvc.getSecretKey() : PolyvParams.SECRET_KEY);
            String res = "";
            try {
                String sign = SHA1.SHA1(ssk);
                url += "&writetoken=" + writetoken + "&vid=" + strId + "&sign=" + sign;
                res = HttpPostRequest.get(url);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            log.info("删除结果:" + res);
            JSONObject json = JSONObject.parseObject(res);
            if (json.getIntValue("error") == 0) {
                if (cvc == null) {
                    VideoVo search = new VideoVo();
                    search.setCompanyId(v.getCompanyId());
                    this.videoServiceImpl.deleteVideoById(vid);
                    String totalSum = this.videoMapper.sumVideoSize(search);
                    Double totalStorage = Double.parseDouble(totalSum);
                    totalStorage = new BigDecimal(totalStorage / 1024).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                    CompanyServiceStatic modifyCompanyServiceStatic = new CompanyServiceStatic();

                    modifyCompanyServiceStatic.setVideoStorage(totalStorage);

                    CompanyServiceStatic css = this.companyServiceStaticMapper.findByCompanyId(config.getCompanyId());

                    if (css == null || css.getId() == null) {
                        modifyCompanyServiceStatic.setCompanyId(config.getCompanyId());
                        this.companyServiceStaticMapper.insert(modifyCompanyServiceStatic);
                    } else {
                        modifyCompanyServiceStatic.setId(css.getId());
                        this.companyServiceStaticMapper.update(modifyCompanyServiceStatic);
                    }
                }
                return JsonMsg.SUCCESS;
            } else {
                return JsonMsg.ERROR;
            }
        }
        case "VIDEO_STORAGE_TYPE_QNVD": {
            if (cvc != null && !"qnvd".equals(cvc.getVideoType())) {
                cvc = null;
            }
            boolean b = FileQNUtils.deleteQNVideo((cvc != null ? cvc.getAccessKey() : FileQNUtils.ak), (cvc != null ? cvc.getSecretKey() : FileQNUtils.sk),
                    (cvc != null ? cvc.getBuketName() : "ydkt"), v.getWebVideoId());
            if (b) {
                if (cvc == null) {
                    VideoVo search = new VideoVo();
                    search.setCompanyId(v.getCompanyId());
                    this.videoServiceImpl.deleteVideoById(vid);
                    String totalSum = this.videoMapper.sumVideoSize(search);
                    Double totalStorage = Double.parseDouble(totalSum);
                    totalStorage = new BigDecimal(totalStorage / 1024).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                    CompanyServiceStatic modifyCompanyServiceStatic = new CompanyServiceStatic();

                    modifyCompanyServiceStatic.setVideoStorage(totalStorage);

                    CompanyServiceStatic css = this.companyServiceStaticMapper.findByCompanyId(config.getCompanyId());

                    if (css == null || css.getId() == null) {
                        modifyCompanyServiceStatic.setCompanyId(config.getCompanyId());
                        this.companyServiceStaticMapper.insert(modifyCompanyServiceStatic);
                    } else {
                        modifyCompanyServiceStatic.setId(css.getId());
                        this.companyServiceStaticMapper.update(modifyCompanyServiceStatic);
                    }
                }
                return JsonMsg.SUCCESS;
            } else {
                return JsonMsg.ERROR;
            }
        }
        default: {
            Map<String, String> params = new HashMap<String, String>();
            params.put("userid", config.getCcUserId());
            params.put("format", "json");
            params.put("videoid", videoid);
            long time = System.currentTimeMillis();
            String salt = config.getCcApiKey();
            String hashUser = APIServiceFunction.createHashedQueryString(params, time, salt);
            userUrl += hashUser;

            String resultInfo = "";
            try {
                resultInfo = HttpPostRequest.get(userUrl);
                this.videoServiceImpl.deleteVideoById(vid);
                // 当此用户为cc公共账号时进行video的空间累加
                if (config.getCcUserId() != null && config.getCcUserId().equals("9D962C153919B4DA")) {
                    VideoVo search = new VideoVo();
                    search.setCompanyId(config.getCompanyId());
                    String totalSum = this.videoMapper.sumVideoSize(search);
                    Double totalStorage = Double.parseDouble(totalSum);
                    totalStorage = new BigDecimal(totalStorage / 1024).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                    CompanyServiceStatic modifyCompanyServiceStatic = new CompanyServiceStatic();

                    modifyCompanyServiceStatic.setVideoStorage(totalStorage);

                    CompanyServiceStatic css = this.companyServiceStaticMapper.findByCompanyId(config.getCompanyId());

                    if (css == null || css.getId() == null) {
                        modifyCompanyServiceStatic.setCompanyId(config.getCompanyId());
                        this.companyServiceStaticMapper.insert(modifyCompanyServiceStatic);
                    } else {
                        modifyCompanyServiceStatic.setId(css.getId());
                        this.companyServiceStaticMapper.update(modifyCompanyServiceStatic);
                    }
                }
                // CompanyServiceStatic modifyCompanyServiceStatic = new
                // CompanyServiceStatic();
                // flowAddSum = flowAddSum==null?0:flowAddSum;
                // modifyCompanyServiceStatic.setVideoFlow(new
                // BigDecimal(flowAddSum).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
                // modifyCompanyServiceStatic.setId(companyServiceStatic.getId());
                // companyServiceStaticServiceImpl.update(modifyCompanyServiceStatic);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            break;
        }

        return JsonMsg.SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public Video getJson(Model model, @PathVariable Integer id) {
        return this.videoServiceImpl.findVideoById(id);
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
     * Class Name: VideoController.java
     *
     * @Description: 链接到上传视频页面
     * @author ycl
     * @date 2015-5-8 下午2:45:06
     * @modifier
     * @modify-date 2015-5-8 下午2:45:06
     * @version 1.0
     * @return
     */
    @RequestMapping(value = "/toVideo")
    public String toVideo(Model model, HttpServletRequest request) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyMemberService comMember = memberServiceService.findByCompanyId(companyId);
        CompanyServiceStatic comService = serviceStaticService.findByCompanyId(companyId);
        List<SysConfigItem> firstItems = sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(),
                WebUtils.getCurrentSchoolId());
        // Company company = companyService.findCompanyById(companyId);
        // if(company!=null){
        // Integer status = company.getStatus();
        // if(status == 5 || status == 1 || status == 2 || status == 4){
        // model.addAttribute("CStatus","noAuth");
        // }
        // }
        // Subject subject = SecurityUtils.getSubject();
        // if(subject.hasRole("机构管理员")){
        // model.addAttribute("role","admin");
        // }
        if (comMember != null && comService != null) {
            Integer totalVideo = (comMember.getVideoStorage() != null ? comMember.getVideoStorage() : 0);
            Integer give = (comMember.getGiveVideoStorage() != null ? comMember.getGiveVideoStorage() : 0);
            totalVideo += give;
            double usedVideo = (comService.getVideoStorage() != null ? comService.getVideoStorage() : 0.0);
            long crs = Long.parseLong(comService.getResourceStorage() != null ? comService.getResourceStorage() : "0");
            //usedVideo += FileQNUtil.convertFileSize(crs);
            usedVideo = new BigDecimal(usedVideo).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            String upVideo = "can";
            if (usedVideo >= totalVideo) {
                upVideo = "no";
            }
            double cvf = (comService.getVideoFlow() != null ? comService.getVideoFlow() : 0.0);
            long crf = Long.parseLong(comService.getResourceFlow() != null ? comService.getResourceFlow() : "0");
            //cvf += FileQNUtil.convertFileSize(crf);

            cvf = new BigDecimal(cvf).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            comService.setVideoStorage(usedVideo);
            comService.setVideoFlow(cvf);
            String flag = "goOn";
            long diffDate = diffDateWithNow(comMember);
            if (diffDate < 0) {
                flag = "over";
            }
            model.addAttribute("flag", flag);
            model.addAttribute("upVideo", upVideo);
            model.addAttribute("comMember", comMember);
            model.addAttribute("comService", comService);
            model.addAttribute("userId", WebUtils.getCurrentUserId(request));
            model.addAttribute("companyId", companyId);
          
        } else {
            return "operate/videos/noVideoService";
        }
        if (firstItems != null) {
            model.addAttribute("oneItemList", firstItems);
        }

        return "operate/videos/video";
    }

    public long diffDateWithNow(CompanyMemberService comMember) {
        Date nowDate = new Date();
        Date date = null;
        if (comMember.getVideoEndDate() != null) {
            date = comMember.getVideoEndDate();
        } else if (comMember.getGiveVideoStorageDate() != null) {
            date = comMember.getGiveVideoStorageDate();
        } else {
            date = nowDate;
        }
        this.log.info("当前公司的视频服务到期时间为：" + this.sdf.format(date) + ", 现在的时间为：" + this.sdf.format(nowDate));

        long diffDate = DateUtil.diffDate(date, nowDate);
        return diffDate;
    }

    /**
     * @Description: H5上次cc视频
     * @author wzx
     * @date 2015-6-3 下午4:23:25
     * @version 1.0
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "toAddVideo")
    public String addVideo(HttpServletRequest request, Model model) {

        return "operate/videos/upload";
    }

    /**
     * @Description: H5上传CC视频, 自己的页面
     * @author wzx
     * @date 2015-6-7 下午12:23:33
     * @version 1.0
     * @param request
     * @return
     */
    @RequestMapping(value = "uploadVideo")
    public String uploadVideo(HttpServletRequest request, Model model, VideoVo video) {
        // 查询当前公司服务信息， 判断当前公司服务时间是否到期、视频空间是否还满足继续上传的条件
        CompanyMemberService member = this.memberServiceService.findByCompanyId(WebUtils.getCurrentCompanyId());
        model.addAttribute("member", member);
        if (member != null) {
            long diffDate = this.diffDateWithNow(member);
            // 如果服务到期，则直接重定向到列表页面
            if (diffDate < 0) {
                return "redirect:/video/toVideo";
            }
        } else {
            return "redirect:/video/toVideo";
        }
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(WebUtils.getCurrentCompanyId());
        companyFunctionSet.setFunctionCode("COMPANY_CONFIG_SCORM");
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;

        model.addAttribute("qndomain", this.propertiesUtil.getQndomain());
        model.addAttribute("comId", WebUtils.getCurrentCompanyId());
        model.addAttribute("schoolId", WebUtils.getCurrentSchoolId());
        model.addAttribute("cfs", cfs);
        model.addAllAttributes(validateBLVS());
        return "operate/videos/uploadVideo";
    }

    /**
     *
     * Class Name: VideoController.java
     *
     * @Description: 异步加载二级项目
     * @author ycl
     * @date 2015-5-9 下午5:55:43
     * @modifier
     * @modify-date 2015-5-9 下午5:55:43
     * @version 1.0
     * @param model
     * @param oneItemId
     * @return
     */
    @RequestMapping(value = "/findSecItemByOneId")
    public String findSecItemByOneId(Model model, HttpServletRequest request, Integer oneItemId) {
        SysConfigItem item = new SysConfigItem();
        item.setCompanyId(WebUtils.getCurrentCompanyId());
        item.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
        item.setItemType("2");
        item.setParentId(oneItemId);

        List<SysConfigItem> secItemList = this.configItemService.findStatus(item);
        if (secItemList != null) {
            model.addAttribute("secItemList", secItemList);
        }
        return "operate/videos/secItemAjaxInfo";
    }

    /**
     *
     * Class Name: VideoController.java
     *
     * @Description: 添加视频页面加载二级项目
     * @author ycl
     * @date 2015-5-11 下午12:53:51
     * @modifier
     * @modify-date 2015-5-11 下午12:53:51
     * @version 1.0
     * @param model
     * @param oneItemId
     * @return
     */
    @RequestMapping(value = "/findAddVideoItemByOneId")
    public String findAddVideoItemByOneId(Model model, Integer oneItemId) {
        List<SysConfigItem> secItemList = this.configItemService.findTwoByOneId(oneItemId);
        if (secItemList != null) {
            model.addAttribute("secItemList", secItemList);
        }
        return "operate/videos/addVideoItemAjaxInfo";
    }

    /**
     *
     * Class Name: VideoController.java
     *
     * @Description: 异步加载视频信息
     * @author ycl
     * @date 2015-5-9 下午5:56:07
     * @modifier
     * @modify-date 2015-5-9 下午5:56:07
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @RequestMapping(value = "/loadVideoAjaxInfo")
    public String loadVideoAjaxInfo(Model model, HttpServletRequest request, VideoVo search) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyPayConfig companyPayConfig = this.companyPayConfigService.findByCompanyId(companyId);
        search.setCompanyId(companyId);
        search.setPageSize(10);
        PageFinder<VideoVo> pageFinder = this.videoServiceImpl.findSourceByPage(search);
        if (pageFinder.getPageNo() > pageFinder.getPageCount()) {
            search.setPage(pageFinder.getPageCount());
            pageFinder = this.videoServiceImpl.findSourceByPage(search);
        }
        // 图片服务器地址
        model.addAttribute("ccUserId", companyPayConfig.getCcUserId());
        model.addAttribute("imageServeUrl", this.propertiesUtil.getImageServicePath());
        List<SysConfigDict> dictList = sysConfigDictServiceImpl.findAll();
        model.addAttribute("dictList", dictList);
        for (VideoVo videoVo : pageFinder.getData()) {
            Users user = this.usersService.findUsersById(videoVo.getCreator());
            if (user != null) {
                videoVo.setCreatorName(user.getUsername());
                String videoName = videoVo.getVideoName();
                videoVo.setSortName(videoVo.getVideoName());
                if (videoName.length() > 15) {
                    videoVo.setSortName(videoVo.getVideoName().substring(0, 15) + "...");
                }
            }
        }
        if (pageFinder.getData() != null) {
            model.addAttribute("pageFinder", pageFinder);
        }
        return "operate/videos/videoAjaxInfo";
    }

    /**
     *
     * Class Name: VideoController.java
     *
     * @Description: 判断该视频是否属于该校区
     * @author yuchanglong
     * @date 2015年6月8日 上午11:13:14
     * @version 1.0
     * @param videoId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/isGroupSchool")
    public Boolean isGroupSchool(Integer videoId) {
        Integer currentSchoolId = WebUtils.getCurrentSchoolId();
        if (currentSchoolId != null) {
            return this.videoServiceImpl.isGroupSchool(videoId, currentSchoolId);
        } else {
            return false;
        }

    }
    /**
     *
     * Class Name: VideoController.java
     *
     * @Description: 链接到添加视频页面
     * @author ycl
     * @date 2015-5-11 下午7:13:23
     * @modifier
     * @modify-date 2015-5-11 下午7:13:23
     * @version 1.0
     * @param model
     * @param video
     * @return
     */
    // @RequestMapping(value="/addVideo")
    // public String toAddVideo(Model model,VideoVo video){
    // Users user = WebUtils.getCurrentUser();
    // Integer companyId = user.getCompanyId();
    // CompanyMemberService comMenber =
    // memberServiceService.findByCompanyId(companyId);
    // CompanyServiceStatic comService =
    // serviceStaticService.findByCompanyId(companyId);
    // SysConfigItem search = new SysConfigItem();
    // search.setCompanyId(companyId);
    // search.setItemType("1");
    // //查询一级项目
    // List<SysConfigItem> oneItemList = configItemService.findItem(search);
    // //剩余空间=总 - 已使用
    // Integer videoOverPlus = (int) (comMenber.getVideoStorage() -
    // comService.getVideoStorage());
    // //已上传视频数量
    // video.setCompanyId(companyId);
    // Integer uploadCount = videoServiceImpl.uploadCount(video);
    // //查询视频占用多少空间
    // Double storageSize = 0.0;//M为单位
    //
    // List<VideoVo> videos = videoServiceImpl.findVideo(video);
    // for (VideoVo vo : videos) {
    // storageSize+=vo.getVodeoSize();
    // }
    // DecimalFormat df = new DecimalFormat("0.00");
    // String newSize = df.format(storageSize/1024);//G为单位
    //
    //
    // model.addAttribute("oneItemList",oneItemList);
    // model.addAttribute("storageSize",newSize);
    // model.addAttribute("uploadCount",uploadCount);
    // model.addAttribute("videoOverPlus",videoOverPlus);
    // return "operate/videos/addVideo";
    // }

    /**
     *
     * Class Name: VideoController.java
     *
     *
     * @Description: 检索视频信息
     * @author chopin
     * @date 2015-5-9 下午5:56:07
     * @modifier
     * @modify-date 2015-5-9 下午5:56:07
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchVideos")
    public PageFinder<VideoVo> searchVideos(Model model, VideoVo search) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        search.setCompanyId(companyId);
        search.setVideoStatus("VIDEO_PROCESS_NOMAL");
        search.setPageSize(8);
        PageFinder<VideoVo> videos = this.videoServiceImpl.findInfoByPage(search);
        // 查询当前公司的ccuserId
        // 查询 ccuserid
        CompanyPayConfig payConfig = this.companyPayConfigService.findByCompanyId(companyId);
        // 图片服务器地址
        String imageServeUrl = this.propertiesUtil.getImageServicePath();
        model.addAttribute("imageServeUrl", imageServeUrl);
        for (VideoVo videoVo : videos.getData()) {
            videoVo.setCcuserId(payConfig.getCcUserId());
            if (videoVo.getCreator() != null) {
                Users user = this.usersService.findUsersById(videoVo.getCreator());
                if (user != null) {
                    videoVo.setCreatorName(user.getUsername() == null ? "" : user.getUsername());
                } else {
                    videoVo.setCreatorName("");
                }

            } else {
                videoVo.setCreatorName("");
            }
        }
        return videos;
    }


    /**
     *
     * Class Name: VideoController.java
     *
     * @Description: 异步加载视频信息
     * @author ycl
     * @date 2015-5-9 下午5:56:07
     * @modifier
     * @modify-date 2015-5-9 下午5:56:07
     * @version 1.0
     * @param model
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchAudio")
    public JSONObject searchAudio(Model model, HttpServletRequest request, VideoVo search) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyPayConfig companyPayConfig = this.companyPayConfigService.findByCompanyId(companyId);
        search.setCompanyId(companyId);
        search.setPageSize(8);
        search.setSearchType("audio");
        PageFinder<VideoVo> pageFinder = this.videoServiceImpl.findSourceByPage(search);
        if (pageFinder.getPageNo() > pageFinder.getPageCount()) {
            search.setPage(pageFinder.getPageCount());
            pageFinder = this.videoServiceImpl.findSourceByPage(search);
        }
        // 图片服务器地址
        model.addAttribute("ccUserId", companyPayConfig.getCcUserId());
        model.addAttribute("imageServeUrl", this.propertiesUtil.getImageServicePath());
        List<SysConfigDict> dictList = sysConfigDictServiceImpl.findAll();
        // 查询 ccuserid
        CompanyPayConfig payConfig = this.companyPayConfigService.findByCompanyId(companyId);
        model.addAttribute("dictList", dictList);
        for (VideoVo videoVo : pageFinder.getData()) {
            videoVo.setCcuserId(payConfig.getCcUserId());
            if (videoVo.getCreator() != null) {
                Users user = this.usersService.findUsersById(videoVo.getCreator());
                if (user != null) {
                    videoVo.setCreatorName(user.getUsername() == null ? "" : user.getUsername());
                } else {
                    videoVo.setCreatorName("");
                }

            } else {
                videoVo.setCreatorName("");
            }
            if (pageFinder.getData() != null) {
                model.addAttribute("pageFinder", pageFinder);
            }
        }
        JSONObject json  = new JSONObject();
        json.put("pageFinder",pageFinder);
        json.put("imageServeUrl",this.propertiesUtil.getImageServicePath());
        return json;
    }



    public static void main(String[] args) {
        /*
         * Map<String, String> queryMap = new HashMap<String, String>();
         * 
         * String userUrl = "http://spark.bokecc.com/api/video/v2?"; long time =
         * System.currentTimeMillis(); String salt =
         * "7ZCRsfyU6xVIZ3fbQmuawjkqXf3PfMNM"; queryMap.put("videoid",
         * "FE455A7DE24438B19C33DC5901307461"); queryMap.put("userid",
         * "9D962C153919B4DA"); queryMap.put("format", "json"); String hashUser
         * = APIServiceFunction.createHashedQueryString(queryMap, time, salt);
         * userUrl += hashUser; String userInfo; try { userInfo =
         * HttpPostRequest.get(userUrl); System.out.println(userInfo);
         * JSONObject obj = JSONObject.parseObject(userInfo);
         * System.out.println(obj); JSONObject videoObj =
         * obj.getJSONObject("video"); System.out.println(videoObj); JSONArray
         * arr = videoObj.getJSONArray("definition"); System.out.println(arr);
         * long fileSize = 0; for (int i = 0; i < arr.size(); i++) { JSONObject
         * definition = (JSONObject) arr.get(i); fileSize +=
         * definition.getLong("filesize"); } Double doubleSize =
         * Double.parseDouble(String.valueOf(fileSize)) / 1024 / 1024;
         * doubleSize = new BigDecimal(doubleSize).setScale(3,
         * BigDecimal.ROUND_HALF_UP).doubleValue(); // 修改video视频的大小 改为M } catch
         * (Exception e) { e.printStackTrace(); }
         */
        /*
         * String url = PolyvParams.GET_USE_INFO; url = url.replace("{userid}",
         * PolyvParams.USER_ID); System.out.println(url); long ptime =
         * System.currentTimeMillis(); String secret = ptime +
         * PolyvParams.SECRET_KEY; Map<String, Object> map = new
         * HashMap<String,Object>(); map.put("ptime", secret); try { String sign
         * = SHA1.SHA1(map); url += "?ptime="+ptime+"&sign="+sign; String res =
         * HttpPostRequest.get(url); System.out.println(res); } catch (Exception
         * e) { // TODO Auto-generated catch block e.printStackTrace(); }
         */
        /*
         * String url = PolyvParams.GET_SINGLE_VIDEO; try { String strSha =
         * "readtoken="+PolyvParams.READ_TOKEN+"&vid="+
         * "0d86c4994e88b8006669ee8a32e7439b_0"+PolyvParams.SECRET_KEY; String
         * sign = SHA1.SHA1(strSha); url +=
         * "&readtoken="+PolyvParams.READ_TOKEN+
         * "&vid=0d86c4994e88b8006669ee8a32e7439b_0&format=json&sign="+sign;
         * String res = HttpPostRequest.get(url); System.out.println(res);
         * net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(res);
         * if(json.getInt("error") == 0){ net.sf.json.JSONArray data =
         * json.getJSONArray("data"); net.sf.json.JSONObject vdata =
         * net.sf.json.JSONObject .fromObject(data.get(0));
         * System.out.println("转码后："+vdata.get("filesize"));
         * System.out.println("转码前:"+vdata.get("source_filesize")); } } catch
         * (Exception e) { // TODO Auto-generated catch block
         * e.printStackTrace(); }
         */
        String url = PolyvParams.GET_VIDEO_LOG;
        url = url.replace("{userid}", PolyvParams.USER_ID);
        String day = new SimpleDateFormat("yyyyMMdd").format(new Date());
        long ts = System.currentTimeMillis();
        String param = "day=" + day + "&ptime=" + ts + "&userid=" + PolyvParams.USER_ID + PolyvParams.SECRET_KEY;
        String vid = "0d86c4994eb44e227212246cb7576b99_0";
        try {
            String sign = SHA1.SHA1(param);
            url += "?day=" + day + "&ptime=" + ts + "&sign=" + sign + "&type=json&vid=" + vid;
            String res = HttpPostRequest.get(url);
            JSONObject json = JSONObject.parseObject(res);
            if (json.getIntValue("code") == 200) {
                JSONObject vdata = JSONObject.parseArray(json.getString("data")).getJSONObject(0);
                System.out.println(vdata);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     * Class Name: VideoController.java
     *
     * @Description: 抓取视频名字
     * @author 周文斌
     * @date 2015-9-15 上午11:14:19
     * @version 1.0
     * @param url
     * @param domain
     * @return
     */
    @ResponseBody
    @RequestMapping("/selVideoName")
    public JSONObject selVideoName(String url, String domain) {
        JSONObject json = new JSONObject();
        try {
            String result = HttpPostRequest.getOneHtml(url, domain);
            this.log.info("videoController-------抓取页面：" + result);
            json.put(JsonMsg.MSG, result);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.ERROR);
        }
        return json;
    }

    /**
     *
     * Class Name: VideoController.java
     *
     * @Description: 保存外链接到数据库
     * @author 周文斌
     * @date 2015-9-15 下午2:13:24
     * @version 1.0
     * @param request
     * @param video
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveVideoUrl")
    public JSONObject saveVideoUrl(HttpServletRequest request, Video video) {
        JSONObject json = new JSONObject();
        try {
            video.setCompanyId(WebUtils.getCurrentCompanyId());
            video.setSchoolId(WebUtils.getCurrentUserSchoolId(request));
            video.setCreator(WebUtils.getCurrentUserId(request));
            video.setCreateTime(new Date());
            this.videoServiceImpl.insert(video);

            // 查询标签是否存在
            if (video.getVideoTag() != null && !video.equals("")) {
                VideoTag vTag = new VideoTag();
                vTag.setCompanyId(WebUtils.getCurrentCompanyId());
                vTag.setTagName(video.getVideoTag());
                List<VideoTag> tags = this.videoTagServiceImpl.findVideoTagByPage(vTag);
                if (tags == null || (tags != null && tags.size() == 0)) {
                    this.videoTagServiceImpl.insert(vTag);
                }
            }
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            json.put("id", video.getId());
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }

    /**
     *
     * Class Name: VideoController.java
     *
     * @Description: 删除外部链接视频
     * @author 周文斌
     * @date 2015-9-15 下午4:45:48
     * @version 1.0
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delVideoUrl")
    public JSONObject delVideoUrl(Integer id) {
        JSONObject json = new JSONObject();
        try {
            Video video = this.videoServiceImpl.findVideoById(id);
            this.log.info("查询video," + video);

            if ("VIDEO_STORAGE_TYPE_SCORM".equals(video.getStorageType())) {
                String key = video.getWebVideoId();
                key = key.substring(0, key.length() - 1);
                boolean b = FileUtil.deleteOssObject(key);
                this.log.info("删除状态，" + b);
            }

            this.videoServiceImpl.deleteVideoById(id);
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            return json;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.put(JsonMsg.MSG, JsonMsg.INFORMATION);
            return json;
        }
    }

    @ResponseBody
    @RequestMapping("/scormupload")
    public JSONObject scormupload(MultipartRequest multiPartRquest, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        MultipartFile multipartFile = multiPartRquest.getFile("files");
        String url = null;
        try {

            Double fm = this.convertFileSize(multipartFile.getSize());

            if (fm > (1024 * 1024 * 200)) {
                json.put(JsonMsg.MSG, "scorm课件不能大于200MB!");
                return json;
            }

            String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
            if (!".zip".equals(suffix)) {
                json.put(JsonMsg.MSG, "请上传格式为.zip的压缩文件");
                return json;
            }

            url = FileUtil.unZip(this.propertiesUtil.getFileStoragePath(), multipartFile, FileUtil.Module.SCORM, WebUtils.getCurrentCompanyId().toString(),
                    this.threadPoolTaskExecutor);
            this.log.info(url);
            if (url.equals("upload")) {
                json.put(JsonMsg.MSG, "文件出现损坏，上传错误");
            } else if (url.equals("socket")) {
                json.put(JsonMsg.MSG, "请求超时，请检查网络是否通畅");
            } else if (url.equals("conn")) {
                json.put(JsonMsg.MSG, "连接不稳定，请检查网络是否通畅");
            } else if (url.equals("zip")) {
                json.put(JsonMsg.MSG, "文件出现损坏，无法读取");
            } else if (url.equals(JsonMsg.ERROR)) {
                json.put(JsonMsg.MSG, "上传课件出现错误");
            } else if (url.equals("OSS")) {
                json.put(JsonMsg.MSG, "服务端连接异常");
            } else if (url.equals("Client")) {
                json.put(JsonMsg.MSG, "客户端服务异常");
            } else if (url.equals("Illegal")) {
                json.put(JsonMsg.MSG, "客户端参数非法");
            } else if (url.equals("ioe")) {
                json.put(JsonMsg.MSG, "文件解析异常");
            } else if (url.equals("")) {
                json.put(JsonMsg.MSG, "上传课件出现错误");
            } else {
                json.put("size", fm);
                json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
                json.put(JsonMsg.URL, url);
            }
            return json;
        } catch (Exception e) {
            this.log.error("上传scorm课件出错," + e.getMessage(), e);
            e.printStackTrace();
            this.log.error("上传scorm课件出错," + e.getMessage());
            json.put(JsonMsg.MSG, "上传课件出现异常");
            return json;
        }
    }

    private Double convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;

        Double f = new BigDecimal((size / mb)).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f;
    }

    @ResponseBody
    @RequestMapping("/token")
    public String getBFYToken(Model model, HttpServletRequest request) {
        String accessKey = this.propertiesUtil.getBfyAccessKey1();
        String secretKey = this.propertiesUtil.getBfySecretkey1();
        String filename = request.getParameter("filename");
        Integer filesize = Integer.parseInt(request.getParameter("filesize"));
        String filekey = request.getParameter("filekey");
        Integer filetype = Integer.parseInt(request.getParameter("filetype"));
        Integer uptype = Integer.parseInt(request.getParameter("uptype"));
        Integer servicetype = Integer.parseInt(request.getParameter("servicetype"));
        String callbackurl = this.propertiesUtil.getBfyCallBackDomain() + request.getParameter("callbackurl");
        long deadline = System.currentTimeMillis() + 3600;

        String str = "{\"uptype\":" + uptype + ",\"servicetype\":" + servicetype + ",\"filename\":" + filename + ",\"filekey\":" + filekey + ",\"filesize\":"
                + filesize + ",\"filetype\":" + filetype + ",\"deadline\":" + deadline + ",\"callbackurl\":" + callbackurl + "\"}";

        String data = "";
        try {

            String baseEncode = Base64.encode(str.getBytes());

            String shaEncode = HashSha1.encode(baseEncode, secretKey);

            String encode = Base64.encode(shaEncode.getBytes());

            String token = accessKey + ":" + encode + ":" + baseEncode;

            token = URLEncoder.encode(token, "utf-8");

            data = "{\"token\":\"" + token + "\"}";
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    @ResponseBody
    @RequestMapping("/deltoken")
    public String getBFYDelToken(Model model, HttpServletRequest request) {
        String accessKey = this.propertiesUtil.getBfyAccessKey1();
        String secretKey = this.propertiesUtil.getBfySecretkey1();
        String filename = request.getParameter("filename");
        String filekey = request.getParameter("filekey");
        Integer servicetype = Integer.parseInt(request.getParameter("servicetype"));
        String callbackurl = this.propertiesUtil.getBfyCallBackDomain() + request.getParameter("callbackurl");
        long deadline = System.currentTimeMillis() + 3600;

        String str = "{\"filename\":" + filename + ",\"filekey\":" + filekey + ",\"deadline\":" + deadline + ",\"servicetype\":" + servicetype
                + ",\"callbackurl\":" + callbackurl + "}";

        String data = "";
        try {
            String baseEncode = Base64.encode(str.getBytes());

            String shaEncode = HashSha1.encode(baseEncode, secretKey);

            String encode = Base64.encode(shaEncode.getBytes());

            String token = accessKey + ":" + encode + ":" + baseEncode;

            token = URLEncoder.encode(token, "utf-8");

            data = "{\"token\":\"" + token + "\"}";
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    /**
     *
     * Class Name: VideoController.java
     *
     * @Description: 暴风云上传回调
     * @author 周文斌
     * @date 2016-7-29 上午11:21:32
     * @version 1.0
     * @param request
     */
    @RequestMapping("/uploadCallback")
    public void uploadCallback(HttpServletRequest request) {

    }

    /**
     *
     * Class Name: VideoController.java
     *
     * @Description: 暴风云删除回调
     * @author 周文斌
     * @date 2016-7-29 上午11:22:53
     * @version 1.0
     * @param request
     */
    @RequestMapping("/deleteCallback")
    public void deleteCallback(HttpServletRequest request) {

    }

    /**
     *
     * Class Name: VideoController.java
     *
     * @Description:资源预览
     * @author 周文斌
     * @date 2016-9-2 下午6:13:19
     * @modify 2016-9-2 下午6:13:19
     * @version
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/preview")
    public JSONObject preview(HttpServletRequest request, Integer id) {
        JSONObject json = new JSONObject();
        try {
            ResourceList rl = this.resourceListImpl.findResourceListById(id);
            String path = FileQNUtils.download(rl.getFilePath());
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
            json.put(JsonMsg.URL, path);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            this.log.error("生成预览地址错误," + e.getMessage());
            json.put(JsonMsg.MSG, "获取预览地址失败");
        }
        return json;
    }

    /**
     *
     * Class Name: VideoController.java
     *
     * @Description: 视频 预览
     * @author 周文斌
     * @date 2016-9-7 下午4:32:13
     * @modify 2016-9-7 下午4:32:13
     * @version
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getpview")
    public JSONObject getpview(HttpServletRequest request, Integer id) {
        Integer companyId = WebUtils.getCurrentCompanyId();
        JSONObject json = new JSONObject();
        try {
            Video video = this.videoServiceImpl.findVideoById(id);
            if (!video.getVideoStatus().equals("VIDEO_PROCESS_NOMAL")) {
                json.put(JsonMsg.MSG, "当前视频无法预览");
                return json;
            }
            this.log.info("查询配置");
            CompanyPayConfig cpc = this.companyPayConfigService.findByCompanyId(companyId);
            if (cpc != null) {
                json.put("letvId", cpc.getLetvUUID());
                json.put("cuId", cpc.getLetvUserId());
            }
            String polyCode = getPolyCode(0, companyId, id, "web");

            json.put("code", polyCode);
            json.put("name", video.getVideoName());
            json.put("doma", this.propertiesUtil.getProjectImageUrl());
            json.put("playId", video.getWebVideoId());
            json.put("ccid", video.getVideoCcId());
            json.put("types", video.getStorageType());
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            json.put(JsonMsg.MSG, "预览发生异常，无法预览");
            return json;
        }

        return json;
    }

    private String getPolyCode(Integer userId, Integer companyId, Integer lessonId, String broswer) {
        String rex = "userId=" + userId + "::companyId=" + companyId + "::lessonId=" + lessonId;
        String md5 = MD5.getMD5ofStr(rex);
        String res = userId + "_" + companyId + "_" + lessonId + "_" + md5 + "_" + broswer;
        // String encoder = Base64Util.encoder(res);
        return res;
    }

    @ResponseBody
    @RequestMapping("/uploadQiniuVideo")
    public void uploadQiniuVideo(Plupload plupload, HttpServletRequest request, HttpServletResponse response) {
        plupload.setRequest(request);
        // 文件存储路径
        String dir = propertiesUtil.getFileStoragePath();

        response.setCharacterEncoding("utf-8");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            // 上传文件
            String name = PluploadUtil.upload(plupload, dir);
            // 判断文件是否上传成功（被分成块的文件是否全部上传完成）
            if (PluploadUtil.isUploadFinish(plupload)) {
                response.setStatus(200);
                pw.write(name);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.setStatus(500);
            pw.write("上传出现错误,请重新上传");
        }

        pw.flush();
        pw.close();
    }

    /**
     * 
     * Class Name: VideoController.java
     * 
     * @Description: 保存七牛视频信息
     * @author 周文斌
     * @date 2017-3-15 下午2:11:53
     * @modify 2017-3-15 下午2:11:53
     * @version
     * @param request
     * @param video
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveQNVideo")
    public JSONObject saveQNVideo(HttpServletRequest request, Video video) {
        JSONObject json = new JSONObject();
        Users user = WebUtils.getCurrentUser(request);
        try {
            video.setCompanyId(user.getCompanyId());
            video.setCreator(user.getId());
            video.setCreateTime(new Date());
            video.setSchoolId(user.getSchoolId());
            videoServiceImpl.insert(video);
            // 查询是否有七牛配置
            CompanyVideoConfig cvc = companyVideoConfigImpl.findConfigByCompanyId(user.getCompanyId());
            if (cvc != null && !"qnvd".equals(cvc.getVideoType())) {
                cvc = null;
            }
            if (QNQueue.upload) {
                new Thread(new QNVideoUploadThread()).start();
                QNQueue.upload = false;
            }
            String bucket = (cvc != null ? cvc.getBuketName() : "ydkt");
            int deadline = 3600;
            String fileType = "video";
            String aks = (cvc != null ? cvc.getAccessKey() : FileQNUtils.ak);
            String sks = (cvc != null ? cvc.getSecretKey() : FileQNUtils.sk);
            String key = video.getWebVideoId();
            String path = propertiesUtil.getFileStoragePath() + "/" + video.getFilePath();
            String obj = bucket + "::" + deadline + "::" + fileType + "::" + aks + "::" + sks + "::" + key + "::" + path + "::" + video.getId();
            QNQueue.queue.put(obj);
            json.put(JsonMsg.MSG, JsonMsg.SUCCESS);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.debug("保存七牛视频信息错误," + e.getMessage());
            json.put(JsonMsg.MSG, "内部错误，请重新上传");
        }
        return json;
    }

    /**
     * 
     * Class Name: VideoController.java
     * 
     * @Description: 保利威视获得校验码
     * @author 周文斌
     * @date 2017-3-20 上午11:02:59
     * @modify 2017-3-20 上午11:02:59
     * @version
     * @return
     */
    @ResponseBody
    @RequestMapping("/validateBLVS")
    public JSONObject validateBLVS() {
        JSONObject json = new JSONObject();
        Integer companyId = WebUtils.getCurrentCompanyId();
        CompanyVideoConfig cvc = companyVideoConfigImpl.findConfigByCompanyId(companyId);
        if (cvc != null && cvc.getVideoType().equals("blvs")) {
        } else {
            cvc = null;
        }
        long ts = System.currentTimeMillis();
        String writeToken = (cvc != null ? cvc.getWriteToken() : PolyvParams.WRITE_TOKEN);
        String secretKey = (cvc != null ? cvc.getSecretKey() : PolyvParams.SECRET_KEY);
        String hash = MD5.getMD5ofStr(ts + writeToken);
        String sign = MD5.getMD5ofStr(secretKey + ts);
        json.put("userid", (cvc != null ? cvc.getUserid() : PolyvParams.USER_ID));
        json.put("ts", ts);
        json.put("hash", hash.toLowerCase());
        json.put("sign", sign.toLowerCase());
        return json;
    }

}
