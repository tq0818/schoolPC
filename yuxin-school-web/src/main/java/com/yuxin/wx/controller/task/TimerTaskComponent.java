package com.yuxin.wx.controller.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.course.IVideoService;
import com.yuxin.wx.api.system.ISysTaskLogService;
import com.yuxin.wx.common.CCVideoConstant;
import com.yuxin.wx.company.mapper.CompanyPayConfigMapper;
import com.yuxin.wx.company.mapper.CompanyServiceStaticDayMapper;
import com.yuxin.wx.company.mapper.CompanyServiceStaticMapper;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;
import com.yuxin.wx.model.system.SysFileConvertTask;
import com.yuxin.wx.model.system.SysTaskLog;
import com.yuxin.wx.scheduled.*;
import com.yuxin.wx.system.mapper.SysFileConvertTaskMapper;
import com.yuxin.wx.utils.*;
import com.yuxin.wx.utils.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sun.net.ftp.FtpClient;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

@Component
@Transactional
public class TimerTaskComponent {

	@Autowired
	private SysFileConvertTaskMapper sysFileConvertTaskImpl;

	@Autowired
	private ResourceStatistics resourceStatistics;

	@Autowired
	private CouponsStatisticsTask couponsStatisticsTask;

	@Autowired
	private CompanyMemberStatisticsTask cmcStatisticsTask;

	@Autowired
	private FaceStatisticsTask faceStatisticsTask;

	@Autowired
	private ISysTaskLogService sysTaskLogServiceImpl;

	@Autowired
	private VideoStatisticsTask videoStatisticsTask;

	@Autowired
	private SendWeixinMsgTask sendWeixinMsgTask;

	@Autowired
	private MessageStatisticsTask messageStatisticsTask;

	@Autowired
	private EmailStatisticsTask emailStatisticsTask;

	@Autowired
	private LiveStatisticsTask liveStatisticsTask;

	@Autowired
	private ZSCoursewareTask zscStatisticsTask;
	@Autowired
	private TikuStatisticsTask tikuStatisticsTask;

	@Autowired
	private PropertiesUtil properties;
	@Autowired
	private CompanyPayConfigMapper payConfigMapper;
	@Autowired
	private CompanyServiceStaticMapper companyServiceStaticMapper;
	@Autowired
	private CompanyServiceStaticDayMapper companyServiceStaticDayMapper;
	@Autowired
	private IVideoService videoServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;


	private Logger log = Logger.getLogger(getClass());

	public static int pdfcount = 0;

	private Properties props = null;
	{
		try {
			Resource resource = new ClassPathResource("config.properties");
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "0 0 * * * ?")
	public void live() {
		if (properties.getTimerTaskSwitchLive().equals("off")) {
			return;
		}
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("直播并发统计");
			stl.setOperator(0);
			stl.setOperateTime(new Date());
			log.info("直播定时任务-----执行时间：" + new Date());
			liveStatisticsTask.liveStatistics();
			log.info("直播定时任务-----处理：执行完成");
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("直播定时任务-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
		} finally {
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	@Scheduled(cron = "0 10 * * * ?")
	public void email() {
		if (properties.getTimerTaskSwitchEmail().equals("off")) {
			return;
		}
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("邮件使用统计");
			stl.setOperator(0);
			stl.setOperateTime(new Date());
			log.info("邮件定时任务-----执行时间：" + new Date());
			emailStatisticsTask.emailStatistics();
			log.info("邮件定时任务-----处理：完成");
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("邮件定时任务-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
		} finally {
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	@Scheduled(cron = "0 30 * * * ?")
	public void message() {
		if (properties.getTimerTaskSwitchMessage().equals("off")) {
			return;
		}
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("短信使用统计");
			stl.setOperator(0);
			stl.setOperateTime(new Date());
			log.info("短信定时任务-----执行时间：" + new Date());
			messageStatisticsTask.messageStatistics();
			log.info("短信定时任务-----处理：完成");
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("短信定时任务-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog("统计错误，" + e.getMessage());
		} finally {
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	@Scheduled(cron = "0 40 * * * ?")
	public void video() {
		if (properties.getTimerTaskSwitchVideo().equals("off")) {
			return;
		}
		String url = CCVideoConstant.USERINFO;
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("视频使用统计");
			stl.setOperator(0);
			stl.setOperateTime(new Date());
			log.info("视频定时任务-----执行时间：" + new Date());
			videoStatisticsTask.videoStatistics(url);
			log.info("视频定时任务-----处理：完成");
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("视频定时任务-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
		} finally {
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	@Scheduled(cron = "0 50 * * * ?")
	public void face() {
		if (properties.getTimerTaskSwitchFace().equals("off")) {
			return;
		}
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("人数分校统计");
			stl.setOperator(0);
			stl.setOperateTime(new Date());
			log.info("人数分校定时任务-----执行时间：" + new Date());
			faceStatisticsTask.faceStatistics();
			log.info("人数分校定时任务-----处理：完成");
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("人数分校定时任务-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
		} finally {
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	// @Scheduled(cron = "0 */12 * * * ?")
	@Scheduled(cron = "0 45 * * * ?")
	public void videoByLetv() {
		if (properties.getTimerTaskSwitchVideo().equals("off")) {
			return;
		}
		// String url = CCVideoConstant.LETV_VIDEO_URL;
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("letv视频使用统计");
			stl.setOperator(0);
			stl.setOperateTime(new Date());
			log.info("letv视频定时任务-----执行时间：" + new Date());
			// 查询所有的视频
			List<CompanyPayConfig> payList = payConfigMapper.findAllLetv();
			for (CompanyPayConfig companyPayConfig : payList) {
				try {
					String api = companyPayConfig.getLetvApiKey().trim();
					String uuid = companyPayConfig.getLetvUUID().trim();
					String userid = companyPayConfig.getLetvUserId().trim();
					LetvCloudV1 letvCloudV1 = new LetvCloudV1(api, uuid);

					// 获取空间
					Double storage = 0.0;
					String authorization = userid + "GET/data/vod/usespace" + api;
					log.info("authorization:" + authorization);
					// 0d3fde75c05dc7cec9dc58b795dc99e9GET/data/vod/usespace0d3fde75c05dc7cec9dc58b795dc99e9
					// 800765GET/data/vod/usespace0d3fde75c05dc7cec9dc58b795dc99e9
					authorization = userid + ":" + MD5.getMD5(authorization);
					String userspace = letvCloudV1.doGetApi(CCVideoConstant.LETV_VIDEO_URL + "/data/vod/usespace",
							authorization);
					log.info("userspace:-------------" + userspace);
					if (userspace != null) {
						JSONObject userspaceObj = (JSONObject) JSONObject.parse(userspace);
						if (userspaceObj.getJSONObject("data") != null) {
							// {"data":{"usespace":{"unit":"MB","value":506.12}}}
							JSONObject data = userspaceObj.getJSONObject("data");
							JSONObject usespace = data.getJSONObject("usespace");
							String unit = usespace.getString("unit");
							if (unit.equals("MB")) {
								storage = usespace.getDouble("value");
								storage = storage / 1024;
							} else if (unit.equals("B")) {
								storage = usespace.getDouble("value");
								storage = storage / 1024 / 1024;
							} else {
								storage = usespace.getDouble("value");
							}
						} else {
							stl.setEndTime(new Date());
							stl.setResult("letv统计中出错" + companyPayConfig.getCompanyId());
							stl.setErrorLog("userid:" + userid + userspace);
							sysTaskLogServiceImpl.insert(stl);
							return;
						}
					}
					// 获取流量
					Double flow = 0.0;// 获取到今天使用的流量
					Double flowSum = 0.0;// 数据库里的总使用流量
					authorization = userid + "GET/data/traffic" + api;
					authorization = userid + ":" + MD5.getMD5(authorization);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String date = sdf.format(new Date());
					String beginDate = sdf.format(DateUtil.addDate(new Date(), -4));
					String u = CCVideoConstant.LETV_VIDEO_URL + "/data/traffic"
							+ "?domaintype=VOD_DOWNLOAD&productline=VOD&unit=G&granularity=day&startday=" + beginDate
							+ "&endday=" + date;
					String traffic = letvCloudV1.doGetApi(u, authorization);
					// {"data":{"traffic":{"granularity":"day","timelist":["20160402","20160403","20160404","20160405","20160406"],"unit":"GB","valuelist":[0,0,0,0,0]}}}
					log.info(beginDate + ":" + date);
					log.info("traffic:-------------" + traffic);
					if (traffic != null) {
						JSONObject trafficObj = (JSONObject) JSONObject.parse(traffic);
						if (trafficObj.getJSONObject("data") != null) {
							JSONObject data = trafficObj.getJSONObject("data");
							JSONObject trafficStr = data.getJSONObject("traffic");
							// JSONArray timelist =
							// trafficStr.getJSONArray("timelist");
							JSONArray valuelist = trafficStr.getJSONArray("valuelist");
							// timelist.get(0);
							flow = valuelist.getDouble(valuelist.size() - 1);
						}
					}

					// 更新公司今天使用情况
					CompanyServiceStaticDay companyServiceStaticDay = new CompanyServiceStaticDay();
					companyServiceStaticDay.setCompanyId(companyPayConfig.getCompanyId());
					SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
					companyServiceStaticDay.setUseDate(today.parse(today.format(new Date())));
					CompanyServiceStaticDay service = companyServiceStaticDayMapper
							.findByDateAndCompanyId(companyServiceStaticDay);
					if (service == null) {
						service = new CompanyServiceStaticDay();
						service.setCompanyId(companyPayConfig.getCompanyId());
						service.setUseDate(today.parse(today.format(new Date())));
						service.setVideoTotalFlow(flow);
						service.setVideoStorageNum(storage);
						companyServiceStaticDayMapper.insert(service);
					} else {
						service.setVideoTotalFlow(flow);
						service.setVideoStorageNum(storage);
						companyServiceStaticDayMapper.update(service);
					}

					// 查询到的总使用流量
					flowSum = companyServiceStaticDayMapper.findFlowSum(companyPayConfig.getCompanyId());
					if (flowSum == null) {
						flowSum = 0.0;
					}
					// 更改CompanyServiceStatic表的流量和空间
					CompanyServiceStatic css = companyServiceStaticMapper
							.findByCompanyId(companyPayConfig.getCompanyId());
					if (css == null) {
						css = new CompanyServiceStatic();
						css.setVideoFlow(new BigDecimal(flow).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
						css.setVideoStorage(
								new BigDecimal(storage).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
						css.setCompanyId(companyPayConfig.getCompanyId());
						css.setLiveConcurrent(0);
						css.setMessageSend(0);
						css.setEmailSend(0);
						css.setFaceStudent(0);
						css.setSchoolNum(0);
						companyServiceStaticMapper.insert(css);
					} else {
						css.setVideoFlow(new BigDecimal(flowSum).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
						css.setVideoStorage(
								new BigDecimal(storage).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
						css.setCompanyId(companyPayConfig.getCompanyId());
						if (css.getLiveConcurrent() == null) {
							css.setLiveConcurrent(0);
						}
						if (css.getMessageSend() == null) {
							css.setMessageSend(0);
						}
						if (css.getEmailSend() == null) {
							css.setEmailSend(0);
						}
						if (css.getFaceStudent() == null) {
							css.setFaceStudent(0);
						}
						if (css.getSchoolNum() == null) {
							css.setSchoolNum(0);
						}
						companyServiceStaticMapper.update(css);
					}
				} catch (Exception e) {
					log.info("letv视频定时任务-----异常：" + e.getMessage());
					stl.setEndTime(new Date());
					stl.setResult("letv统计公司" + companyPayConfig.getCompanyId() + "出错");
					stl.setErrorLog(e.getMessage() + "");
					sysTaskLogServiceImpl.insert(stl);
					e.printStackTrace();
				}
			}
			log.info("letv视频定时任务-----处理：完成");
			stl.setEndTime(new Date());
			stl.setResult("letv统计成功");
			stl.setErrorLog("无错误");
			sysTaskLogServiceImpl.insert(stl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("letv视频定时任务-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("letv统计中出错");
			stl.setErrorLog(e.getMessage());
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	@Scheduled(cron = "0 5 0 * * ?")
	public void coupons() {
		if (properties.getTimerTaskSwitchCoupons().equals("off")) {
			return;
		}
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("清理优惠券");
			stl.setOperator(0);
			stl.setOperateTime(new Date());
			log.info("清理优惠券-----执行时间：" + new Date());
			couponsStatisticsTask.cleanCouponsTask();
			log.info("清理优惠券-----处理：完成");
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("清理优惠券-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
		} finally {
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	/**
	 * 
	 * Class Name: TimerTaskComponent.java
	 * 
	 * @Description: 会员到期提醒
	 * @author 周文斌
	 * @date 2016-6-13 下午5:58:59
	 * @version 1.0
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void cmctask() {
		if (properties.getTimerTaskSwitchCmc().equals("off")) {
			return;
		}
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("会员到期提醒");
			stl.setOperator(0);
			stl.setOperateTime(new Date());
			log.info("会员到期提醒-----执行时间：" + new Date());
			cmcStatisticsTask.UserVipNotice();
			log.info("会员到期提醒-----处理：完成");
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("会员到期提醒-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
		} finally {
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	@Scheduled(cron = "0 0 5 * * ?")
	public void zsurl() {
		if (properties.getTimerTaskSwitchZsurl().equals("off")) {
			return;
		}
		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("展示回播统计");
			stl.setOperator(0);
			stl.setOperateTime(new Date());
			log.info("展示回播定时任务-----执行时间：" + new Date());
			zscStatisticsTask.CoursewareStatistics(null);
			log.info("展示回播定时任务-----处理：完成");
			stl.setEndTime(new Date());
			stl.setResult("统计成功");
			stl.setErrorLog("无错误");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("展示回播定时任务-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("统计中出错");
			stl.setErrorLog(e.getMessage());
		} finally {
			sysTaskLogServiceImpl.insert(stl);
		}
	}

	private String unCompress(String path, boolean delete) {
		File file = new File(path);
		try {
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(file.getPath().replace(".gz", ""));

			GZIPInputStream gis = new GZIPInputStream(fis);
			int BUFFERR = 1024;
			int count;
			byte data[] = new byte[BUFFERR];
			while ((count = gis.read(data, 0, BUFFERR)) != -1) {
				fos.write(data, 0, count);
			}

			gis.close();
			fis.close();
			fos.flush();
			fos.close();

			if (delete) {
				file.delete();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.info("解压cc日志文件出错，没有找到文件");
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			log.info("解压cc日志文件出错");
			return "";
		}

		return file.getPath().replace(".gz", "");
	}

	private String downloadCCLog(String date, String savePath) {
		FtpClient ftpClient = null;
		String fileLog = "access." + date + ".log.gz";
		try {
			log.info("连接ftp开始：---------");
			// ftpClient = new
			// sun.net.ftp.FtpClient.create("ftp.log.bokecc.com");
			ftpClient = FtpClient.create("ftp.log.bokecc.com");
			ftpClient.login("9D962C153919B4DA", "5754ACC9B7C93A3BC9C385B9D06EF776".toCharArray(),
					"5754ACC9B7C93A3BC9C385B9D06EF776");
		} catch (Exception e) {
			log.info("连接ftp错误");
			e.printStackTrace();
			return "";
		}
		// InputStream is = null;
		FileInputStream is = null;
		FileOutputStream os = null;
		try {
			// ftpClient.binary();
			ftpClient.setBinaryType();
			// 获取远程机器上的文件filename，借助TelnetInputStream把该文件传送到本地。

			log.info("将要下载文件" + fileLog);
			is = (FileInputStream) ftpClient.getFileStream("/" + fileLog);
			// is = ftpClient.get("/"+fileLog);
			File file_in = new File(savePath + fileLog);
			os = new FileOutputStream(file_in);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			log.info("文件下载成功");
		} catch (Exception ex) {
			log.info("文件下载失败");
			ex.printStackTrace();
			return "";
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return fileLog;
	}

	private static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^\\d+$|-\\d+$");
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}

	/**
	 * 处理正常流程的office文件转换成pdf文件
	 * 
	 */
	@Scheduled(cron = "0 0/2 * * * ?")
	public void convertPdf() {
		if(!"on".equals(properties.getConvertPDF())){
			log.info("===================pdf转换任务关闭，不执行=============");
			return;
		}
		log.info("===================pdf转换任务begin=============");
		// TODO 1.查库,每次查询50条 
		List<SysFileConvertTask> tasks = sysFileConvertTaskImpl.queryTaskNeedConvert(0, 50);
		// TODO 2.处理多服务器并发问题，尝试更新库，如果更新成功说明独占，可以操作
		for (SysFileConvertTask t : tasks) {
			t.setExcuteTime(new Date());
			t.setServerId(props.getProperty("mongodb.server.name"));
			int row = sysFileConvertTaskImpl.checkTaskVersion(t);
			if (row > 0) {
				t.setVersion(1);
				t.setStatus(1);
				OfficeConvert2PDFTask convert = OfficeConvert2PDFTask.newInstance();
				// TODO 3.检查线程池中的队列，如果队列里任务数没满，则把查询结果扔到线程池执行
				log.info("=======>>>>>队列长度:" + convert.size());
				if (convert != null && convert.size() < 80) {
					// 如果还有能力服务，就服务，防止服务器宕机
					convert.execute(t);
				} else {
					// 没有能力，则原样扔回库里
					log.info("=======>>>>>>队列已满，延后处理");
					t.setVersion(0);
					t.setStatus(0);
					sysFileConvertTaskImpl.update(t);
				}
			}
		}
		log.info("===================pdf转换任务end=============");
	}

	/**
	 * 处理正常流程的office文件转换成pdf文件
	 * 
	 */
	// @Scheduled(cron = "0 0/2 * * * ?")
	// public void convertPdf() {
	// log.info("---------进来了----------");
	// List<SysFileConvertTask> jobs =
	// sysFileConvertTaskImpl.queryTaskNeedConvert(0, 100);
	// for (SysFileConvertTask job : jobs) {
	// String oldpath = job.getFilePath();
	// String result=FileQNUtils.convertFile(oldpath);
	// log.info(result);
	// job.setStatus(1);
	// job.setVersion(1);
	// sysFileConvertTaskImpl.update(job);
	// }
	// }

	/**
	 * 统计用户答题结果（只针对单选和多选）
	 *
	 */
//	 @Scheduled(cron = "0 0 * * * ?")
	 public void taskTikuUserExerciseAnswer() {
		 SysTaskLog stl = new SysTaskLog();
		 try {
			 stl.setExecuteDate(new Date());
			 stl.setStartTime(new Date());
			 stl.setTaskName("用户课后练习试卷统计");
			 stl.setOperator(0);
			 stl.setOperateTime(new Date());
			 log.info("用户课后练习试卷统计任务-----执行时间：" + new Date());
			 tikuStatisticsTask.tikuStatistics();
			 log.info("用户课后练习试卷统计任务-----处理：完成");
			 stl.setEndTime(new Date());
			 stl.setResult("统计成功");
			 stl.setErrorLog("无错误");
		 } catch (Exception e) {
			 // TODO: handle exception
			 e.printStackTrace();
			 log.info("用户课后练习试卷统计-----异常：" + e.getMessage());
			 stl.setEndTime(new Date());
			 stl.setResult("统计中出错");
			 stl.setErrorLog(e.getMessage());
		 } finally {
			 sysTaskLogServiceImpl.insert(stl);
		 }
	 }


	/**
	 * 每晚8点定时发送微信通知
	 *
	 */
	@Scheduled(cron = "0 0 20 * * ?")
	public void taskSendWeixinMsg() {
 		SysTaskLog stl = new SysTaskLog();
		try {
			stl.setExecuteDate(new Date());
			stl.setStartTime(new Date());
			stl.setTaskName("定时微信账号通知");
			stl.setOperator(0);
			stl.setOperateTime(new Date());
			log.info("定时微信账号通知任务-----执行时间：" + new Date());
			sendWeixinMsgTask.sendWeixinMsg(FileUtil.props);
			log.info("定时微信账号通知任务-----处理：完成");
			stl.setEndTime(new Date());
			stl.setResult("发送成功");
			stl.setErrorLog("无错误");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("定时微信账号通知-----异常：" + e.getMessage());
			stl.setEndTime(new Date());
			stl.setResult("发送中出错");
			stl.setErrorLog(e.getMessage());
		} finally {
			sysTaskLogServiceImpl.insert(stl);
		}
	}
}
