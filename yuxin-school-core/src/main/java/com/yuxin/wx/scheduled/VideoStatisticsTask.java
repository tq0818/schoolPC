package com.yuxin.wx.scheduled;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.resource.IResourceListService;
import com.yuxin.wx.api.resource.IResourceUseRecordService;
import com.yuxin.wx.api.system.ISysSmsLogService;
import com.yuxin.wx.common.PolyvParams;
import com.yuxin.wx.company.mapper.CompanyPayConfigMapper;
import com.yuxin.wx.company.mapper.CompanyServiceStaticDayMapper;
import com.yuxin.wx.company.mapper.CompanyServiceStaticMapper;
import com.yuxin.wx.company.mapper.CompanyVideoStaticCcMapper;
import com.yuxin.wx.course.mapper.VideoMapper;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;
import com.yuxin.wx.model.company.CompanyVideoStaticCc;
import com.yuxin.wx.model.course.Video;
import com.yuxin.wx.model.system.SysSmsLog;
import com.yuxin.wx.resource.mapper.ResourceListMapper;
import com.yuxin.wx.resource.mapper.ResourceUseRecordMapper;
import com.yuxin.wx.util.DateUtil;
import com.yuxin.wx.util.HttpPostRequest;
import com.yuxin.wx.util.MD5;
import com.yuxin.wx.util.ParameterUtil;
import com.yuxin.wx.util.SHA1;
import com.yuxin.wx.util.smsClient.interfacej.SmsClientSend;
import com.yuxin.wx.vo.company.SpaceVo;
import com.yuxin.wx.vo.company.TrafficVo;
import com.yuxin.wx.vo.course.VideoVo;

@Component
@Transactional
public class VideoStatisticsTask {

	@Autowired
	private VideoMapper videoMapper;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ISysSmsLogService sysSmsLogServiceimpl;
	@Autowired
	private CompanyServiceStaticMapper companyServiceStaticMapper;
	@Autowired
	private CompanyServiceStaticDayMapper companyServiceStaticDayMapper;
	@Autowired
	private CompanyVideoStaticCcMapper companyVideoStaticCcMapper;
	@Autowired
	private CompanyPayConfigMapper payConfigMapper;
	
	private Log log=LogFactory.getLog("log");
	
	public void videoStatistics(String url) throws Exception{
		//查询公司的 cc userid  和  key
		List<CompanyPayConfig> payList = payConfigMapper.findAllCC();
		String	format	= "json";
		//Date date = null;
		//今日
		Date yday = new Date();
		String sendTime = new SimpleDateFormat("yyyy-MM-dd").format(yday);
		yday = new SimpleDateFormat("yyyy-MM-dd").parse(sendTime);

		
		/*if(StringUtils.isEmpty(yesterday)){
			//上一次
			date = yday;
		}else{
			date = new SimpleDateFormat("yyyy-MM-dd").parse(yesterday);
		}*/

		for (CompanyPayConfig c : payList) {
			try {
				//获取每个公司保利的流量使用情况
				Video v = new Video();
				v.setCompanyId(c.getCompanyId());
				v.setStorageType("VIDEO_STORAGE_TYPE_BLVS");
				List<String> vids = videoMapper.findWebVideoIdBystorageType(v);
				long blvsFlow = 0L;
				for (String vid : vids) {
					String blvsurl = PolyvParams.GET_VIDEO_LOG;
					blvsurl = blvsurl.replace("{userid}", PolyvParams.USER_ID);
			    	String blvsday = new SimpleDateFormat("yyyyMMdd").format(new Date());
			    	long ts = System.currentTimeMillis();
			    	String param = "day=" + blvsday+"&ptime="+ts+"&userid="+PolyvParams.USER_ID+
			    			PolyvParams.SECRET_KEY;
			    	try {
						String sign = SHA1.SHA1(param);
						blvsurl += "?day="+blvsday+"&ptime="+ts+"&sign="+sign+"&type=json&vid="+vid;
						String res = HttpPostRequest.get(blvsurl);
						JSONObject json = JSONObject.parseObject(res);
						if(json.getIntValue("code") == 200){
							JSONObject vdata = JSONObject
									.parseArray(json.getString("data")).getJSONObject(0);
							blvsFlow += vdata.getLongValue("flowSize");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				VideoVo vo = new VideoVo();
				vo.setCompanyId(c.getCompanyId());
				vo.setStorageType("VIDEO_STORAGE_TYPE_BLVS");
				Double blvsStorage = Double.parseDouble((videoMapper .sumVideoSize(vo)));
				
				blvsStorage = new BigDecimal((blvsStorage / 1024))
					.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
				Double blvsUseFlow = new BigDecimal((blvsFlow / (1024 * 1024 * 1024)))
					.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
				
				
				if(c.getCcApiKey() != null && c.getCcUserId() != null && c.getCompanyId() != null){
					Map<String,Object> param = new TreeMap<String, Object>();
					String s = getCcInfo(c, format, url, param);
					SpaceVo sv = null;
					String traffic = null;
					TrafficVo tv = null;
					log.info("接口返回 -----视频信息：" + s);
					if(JSONObject.parseObject(s).getString("user") != null){
						String space = JSONObject.parseObject(JSONObject.parseObject(s).getString("user")).getString("space");
						sv = JSONObject.parseObject(space, SpaceVo.class);
						traffic = JSONObject.parseObject(JSONObject.parseObject(s).getString("user")).getString("traffic");
						tv = JSONObject.parseObject(traffic, TrafficVo.class);
					}else{
						sv = new SpaceVo(0.0, 0.0, 0.0);
						tv = new TrafficVo(0.0, 0.0, 0.0);
					}

					//查询今天记录
					CompanyVideoStaticCc cc = getTodayCc(c.getCompanyId(), yday, param, sv, tv);
					log.info("今天记录-----视频信息：" + cc);
					
					//查询本月的上一次的流量记录
					CompanyVideoStaticCc ycc = prevFlowOrSto("flow", c.getCompanyId(), yday, param);
					log.info("上一次记录-----视频信息：" + ycc);
					if(ycc == null){
						ycc = new CompanyVideoStaticCc();
					}
					
					//查询空间使用上一次
					CompanyVideoStaticCc sto = prevFlowOrSto("space", c.getCompanyId(), yday, param);
					log.info("上一次记录-----视频信息：" + sto);
					if(sto == null){
						sto = new CompanyVideoStaticCc();
					}
					
					//查询 公司今天使用情况
					CompanyServiceStaticDay cssd = new CompanyServiceStaticDay();
					cssd.setCompanyId(c.getCompanyId());
					cssd.setUseDate(yday);
					CompanyServiceStaticDay service = companyServiceStaticDayMapper.findByDateAndCompanyId(cssd);
					
					if(service == null){
						service = new CompanyServiceStaticDay();
					}
					
					service.setCompanyId(c.getCompanyId());
					service.setUseDate(yday);
					//如果上一次 没有记录
					if(ycc.getUsedFlow() == null){
						Double totalFlow = (new BigDecimal(cc.getUsedFlow())
							.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue() + 
							blvsUseFlow);
						service.setVideoTotalFlow(totalFlow);
					}else{
						//如果今天是1号
						if((yday.getDate()) == 1){
							Double totalFlow = (new BigDecimal(cc.getUsedFlow()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue() + blvsUseFlow);
							service.setVideoTotalFlow(totalFlow);
						}else{
						//如果今天不是一号
							Double totalFlow =(new BigDecimal((cc.getUsedFlow() + blvsUseFlow)- ycc.getUsedFlow()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
							service.setVideoTotalFlow(totalFlow);
						}
					}
					
					if(sto.getUsedStorage() == null){
						
						service.setVideoStorageNum(new BigDecimal(sv.getUsed()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue() + blvsStorage);
					}else{
						service.setVideoStorageNum(new BigDecimal(((sv.getUsed() + blvsStorage)- sto.getUsedStorage())).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
					}
					
					log.info("公司，" + c.getCompanyId() + 
							",使用空间," + service.getVideoStorageNum());

					if(service.getId() == null){
						companyServiceStaticDayMapper.insert(service);
					}else{
						companyServiceStaticDayMapper.update(service);
					}
					
					//查询 公司服务统计
					CompanyServiceStatic css = companyServiceStaticMapper.findByCompanyId(c.getCompanyId());
					
					log.info("公司服务统计：" + css);
					
					//查询统计公司流量使用总量 ，sum 统计
					Double sum = companyServiceStaticDayMapper.findFlowSum(c.getCompanyId());
					
					if(sum == null){
						sum = 0.0;
					}
					
					double pus = (sv.getUsed() + blvsStorage);
					double puf = (sum);
					if(css == null){
						css = new CompanyServiceStatic();
						css.setVideoFlow(new BigDecimal(puf).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
						css.setVideoStorage(new BigDecimal(pus).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
						css.setCompanyId(c.getCompanyId());
						css.setLiveConcurrent(0);
						css.setMessageSend(0);
						css.setEmailSend(0);
						css.setFaceStudent(0);
						css.setSchoolNum(0);
						companyServiceStaticMapper.insert(css);
					}else{
						css.setVideoFlow(new BigDecimal(puf).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
						css.setVideoStorage(new BigDecimal(pus).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
						css.setCompanyId(c.getCompanyId());
						if(css.getLiveConcurrent() == null){
							css.setLiveConcurrent(0);
						}
						if(css.getMessageSend() == null){
							css.setMessageSend(0);
						}
						if(css.getEmailSend() == null){
							css.setEmailSend(0);
						}
						if(css.getFaceStudent() == null){
							css.setFaceStudent(0);
						}
						if(css.getSchoolNum() == null){
							css.setSchoolNum(0);
						}
						companyServiceStaticMapper.update(css);
					}
					//公司服务
					CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(c.getCompanyId());
					
					/*Double giveStorage = new BigDecimal(cms.getGiveVideoStorage() 
							!= null ? cms.getGiveVideoStorage() : 0)
					.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();*/
					
					Double giveFlow = new BigDecimal(cms.getGiveVideoFlow()
							!= null ? cms.getGiveVideoFlow() : 0)
					.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
					
					if(cms != null){
						Double sumGiveFlow = new BigDecimal(((cms.getVideoFlow()
								!= null ? cms.getVideoFlow() : 0) + giveFlow))
						.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
						//阈值
						Double ljFlow = (sumGiveFlow * 0.1);
						//剩余流量，空间
						Double syFlow = (sumGiveFlow - css.getVideoFlow());
						//流量报警
						//flowWarning(syFlow, ljFlow, c, sendTime);
						
						//空间
						/*Double sumGiveStorage = new BigDecimal((giveStorage + 
								(cms.getVideoStorage() != null 
								? cms.getVideoStorage() : 0)))
						.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();*/

						Double sumGiveStorage = sv.getTotal();
						Double ljStorage = (sumGiveStorage * 0.1);
						/*Double syStorage = (sumGiveStorage - css.getVideoStorage());*/
						Double syStorage = (sumGiveStorage - sv.getUsed());
						//空间警报
						storageWarning(syStorage, ljStorage, c, sendTime);
					}
				}else{
					if(c.getCompanyId() != null){
						//查询 公司服务统计
						CompanyServiceStatic css = companyServiceStaticMapper.findByCompanyId(c.getCompanyId());
						
						log.info("公司服务统计：" + css);
						
						if(css == null){
							css = new CompanyServiceStatic();
							css.setVideoFlow(0.0);
							css.setVideoStorage(0.0);
							css.setCompanyId(c.getCompanyId());
							css.setLiveConcurrent(0);
							css.setMessageSend(0);
							css.setEmailSend(0);
							css.setFaceStudent(0);
							css.setSchoolNum(0);
							companyServiceStaticMapper.insert(css);
						}else{
							css.setVideoFlow(0.0);
							css.setVideoStorage(0.0);
							css.setCompanyId(c.getCompanyId());
							if(css.getLiveConcurrent() == null){
								css.setLiveConcurrent(0);
							}
							if(css.getMessageSend() == null){
								css.setMessageSend(0);
							}
							if(css.getEmailSend() == null){
								css.setEmailSend(0);
							}
							if(css.getFaceStudent() == null){
								css.setFaceStudent(0);
							}
							if(css.getSchoolNum() == null){
								css.setSchoolNum(0);
							}
							companyServiceStaticMapper.update(css);
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//通过接口取回cc信息
	private String getCcInfo(CompanyPayConfig c,String format
			,String url,Map<String,Object> param) throws Exception{
		String	userid	= c.getCcUserId();
		long	time	= System.currentTimeMillis();
		String	salt	= c.getCcApiKey();
		String	hash	= MD5.getMD5ofStr("format=" + format + "&userid=" + userid + "&time=" + time + "&salt=" + salt);

		param.put("format", format);
		param.put("userid", userid);
		param.put("time", time);
		param.put("hash", hash);
		String s = HttpPostRequest.post(url, param);
		return s;
	}
	
	//查询今天信息
	private CompanyVideoStaticCc getTodayCc(Integer companyId,Date yday
			,Map<String, Object> param,SpaceVo sv,TrafficVo tv){
		param.clear();
		param.put("companyId", companyId);
		param.put("staticDate", yday);
		CompanyVideoStaticCc cc = companyVideoStaticCcMapper.findByDate(param);
		if(cc == null){
			cc = new CompanyVideoStaticCc();
			//添加入记录
			cc.setCompanyId(companyId);
			cc.setStaticDate(yday);
			cc.setTotalFlow(new BigDecimal(tv.getTotal()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
			cc.setRemainFlow(new BigDecimal(tv.getRemain()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
			cc.setUsedFlow(new BigDecimal(tv.getUsed()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
			cc.setTotalStorage(new BigDecimal(sv.getTotal()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
			cc.setRemainStorage(new BigDecimal(sv.getRemain()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
			cc.setUsedStorage(new BigDecimal(sv.getUsed()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
			companyVideoStaticCcMapper.insert(cc);
		}else{
			cc.setCompanyId(companyId);
			cc.setStaticDate(yday);
			cc.setTotalFlow(new BigDecimal(tv.getTotal()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
			cc.setRemainFlow(new BigDecimal(tv.getRemain()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
			cc.setUsedFlow(new BigDecimal(tv.getUsed()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
			cc.setTotalStorage(new BigDecimal(sv.getTotal()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
			cc.setRemainStorage(new BigDecimal(sv.getRemain()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
			cc.setUsedStorage(new BigDecimal(sv.getUsed()).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue());
			companyVideoStaticCcMapper.update(cc);
		}
		return cc;
	}
	
	//流量警报
	private void flowWarning(Double syFlow,Double ljFlow
			,CompanyPayConfig c,String sendTime){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", c.getCompanyId());
		param.put("sendTime", sendTime);
		Integer id = sysSmsLogServiceimpl.findByCompanyId(param);
		if(id == null){
			if(syFlow < ljFlow){

				syFlow = new BigDecimal(syFlow).setScale(3
						,BigDecimal.ROUND_HALF_UP).doubleValue();
				
				String content = "公司：" + c.getCompanyId() +
						",ccid:" + c.getCcUserId() + 
						",流量剩余:" + syFlow + 
						"GB," + "【在线网校】";
				addSmslog(content, c.getCompanyId());
			}
		}
	}
	
	//空间警报
	private void storageWarning(Double syStorage,Double ljStorage
			,CompanyPayConfig c, String sendTime){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", c.getCompanyId());
		param.put("sendTime", sendTime);
		Integer id = sysSmsLogServiceimpl.findByCompanyId(param);
		if(id == null){
			if(syStorage < ljStorage){

				syStorage = new BigDecimal(syStorage).setScale(3
						,BigDecimal.ROUND_HALF_UP).doubleValue();
				
				String content = "公司：" + c.getCompanyId() +
						",cc用户id:" + c.getCcUserId() + 
						",空间剩余:" + syStorage + 
						"GB," + "【在线网校】";
				addSmslog(content, c.getCompanyId());
			}
		}
	}
	
	//查询上次的流量 或空间 记录
	private CompanyVideoStaticCc prevFlowOrSto(String types,Integer companyId,Date yday,
			Map<String, Object> param) throws ParseException{
		param.clear();
		param.put("companyId", companyId);
		param.put("staticDate", yday);
		param.put("types", types);
		param.put("firstDate", new SimpleDateFormat("yyyy-MM-dd").parse(DateUtil.getFirstDayOfCurMonth()));
		CompanyVideoStaticCc ycc = companyVideoStaticCcMapper.findByUpDate(param);
		log.info("上一次记录-----视频信息：" + ycc);
		return ycc;
	}
	
	private void addSmslog(String content,Integer companyId){

		String mobile = "18600863155,18612106036";
		String result = SmsClientSend.sendSmsTwo(mobile,
				content);
		SysSmsLog ssl = new SysSmsLog();
		ssl.setContent(content);
		ssl.setSendTime(new Date());
		ssl.setBusinessType("sys-notic");
		ssl.setMobile(mobile);
		
		String sendStatus = result.substring(result.indexOf("<message>"),result.indexOf("</message>"));
		sendStatus = sendStatus.substring(sendStatus.indexOf(">") + 1);
		if(sendStatus.equals("ok")){
			sendStatus = "发送成功";
		}
		
		ssl.setSendStatus(sendStatus);
		ssl.setCompanyId(companyId);
		sysSmsLogServiceimpl.insert(ssl);
	}
}
