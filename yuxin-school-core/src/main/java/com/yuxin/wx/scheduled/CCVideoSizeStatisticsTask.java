package com.yuxin.wx.scheduled;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.company.mapper.CompanyMapper;
import com.yuxin.wx.company.mapper.CompanyMemberServiceMapper;
import com.yuxin.wx.company.mapper.CompanyPayConfigMapper;
import com.yuxin.wx.company.mapper.CompanyServiceStaticDayMapper;
import com.yuxin.wx.company.mapper.CompanyServiceStaticMapper;
import com.yuxin.wx.course.mapper.VideoMapper;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyPayConfig;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;
import com.yuxin.wx.model.course.Video;
import com.yuxin.wx.util.APIServiceFunction;
import com.yuxin.wx.util.HttpPostRequest;
import com.yuxin.wx.vo.course.VideoVo;

@Component
public class CCVideoSizeStatisticsTask {
	
	@Autowired
	private CompanyServiceStaticMapper cssMapper;
	@Autowired
	private CompanyServiceStaticDayMapper cssdMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private CompanyPayConfigMapper companyPayConfigMapper;
	@Autowired
	private VideoMapper videoMapper;
	
	private Log log = LogFactory.getLog("log");
	
	public void TaskStatistics(){
		log.info("查询为负值的video");
		List<Video> vlist = videoMapper.findEQZero();
		log.info("挨个取cc的值");
		Map<String, String> queryMap = new HashMap<String, String>();
		for (Video v : vlist) {
			Integer companyId = v.getCompanyId();
			CompanyPayConfig cpc = companyPayConfigMapper.findByCompanyId(companyId);
			
			queryMap.clear();
	        String userUrl = "http://spark.bokecc.com/api/video/v2?";
	        long time = System.currentTimeMillis();
	        String salt = cpc.getCcApiKey();
	        queryMap.put("videoid", v.getVideoCcId());
	        queryMap.put("userid", cpc.getCcUserId());
	        queryMap.put("format", "json");
	        String hashUser = APIServiceFunction.createHashedQueryString(queryMap, time, salt);
	        userUrl += hashUser;
	        String userInfo;
	        try {
	            userInfo = HttpPostRequest.get(userUrl);
	            JSONObject obj = JSONObject.parseObject(userInfo);
	            JSONObject videoObj = obj.getJSONObject("video");
	            JSONArray arr = videoObj.getJSONArray("definition");
	            long fileSize = 0;
	            for (int i = 0; i < arr.size(); i++) {
	                JSONObject definition = (JSONObject) arr.get(i);
	                fileSize += definition.getLong("filesize");
	            }
	            Double doubleSize = Double.parseDouble((fileSize+"")) / 1024 / 1024;
	            doubleSize = new BigDecimal(doubleSize).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	            // 修改video视频的大小 改为M
	            v.setVodeoSize(doubleSize);
	            videoMapper.update(v);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
            if (cpc.getCcUserId().equals("9D962C153919B4DA")) {
                // 查询公司
                Company company = companyMapper.findById(companyId);
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
                    companyServiceStaticDay.setCompanyId(companyId);
                    SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");

                    try {
                        companyServiceStaticDay.setUseDate(today.parse(today.format(v.getCreateTime())));
                        CompanyServiceStaticDay service = cssdMapper.findByDateAndCompanyId(companyServiceStaticDay);
                        if (service == null) {
                            service = new CompanyServiceStaticDay();
                            service.setCompanyId(v.getCompanyId());
                            service.setUseDate(today.parse(today.format(v.getCreateTime())));
                            service.setVideoStorageNum(storage);
                            cssdMapper.insert(service);
                        } else {
                            service.setVideoStorageNum(storage);
                            cssdMapper.update(service);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 更新公司总使用量
                    search.setCreateTime(null);
                    // 获取当天的
                    String totalSum = this.videoMapper.sumVideoSize(search);
                    Double totalStorage = Double.parseDouble(totalSum);
                    CompanyServiceStatic css = cssMapper.findByCompanyId(v.getCompanyId());
                    CompanyServiceStatic updateStatic = new CompanyServiceStatic();
                    totalStorage = new BigDecimal(totalStorage / 1024).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                    updateStatic.setVideoStorage(totalStorage);
                    if (css == null || css.getId() == null) {
                        updateStatic.setCompanyId(v.getCompanyId());
                        cssMapper.insert(updateStatic);
                    } else {
                        updateStatic.setId(css.getId());
                        cssMapper.update(updateStatic);
                    }
                }
            }
		}
	}
}
