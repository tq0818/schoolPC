package com.yuxin.wx.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticDayService;
import com.yuxin.wx.api.resource.IResourceListService;
import com.yuxin.wx.api.resource.IResourceUseRecordService;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;

/**
 *
 * @ClassName: ResourceStatistics
 * @Description: 统计七牛每天使用情况
 * @author 1
 * @date 2016-9-2 上午11:02:08
 * @version 1.0
 */
@Component
public class ResourceStatistics {

	@Autowired
	private ICompanyServiceStaticDayService companyServiceStaticDayService;

	@Autowired
	private ICompanyService companyServiceImpl;

	@Autowired
	private IResourceListService resourceListImpl;

	@Autowired
	private IResourceUseRecordService resourceUseRecordtImpl;

	public void resourceStatistics() throws Exception {
		// 获得公司id
		List<Integer> companyIds = companyServiceImpl.findCompanyId(null);

		// 今日
		Date day = new Date();
		String sendTime = new SimpleDateFormat("yyyy-MM-dd").format(day);
		day = new SimpleDateFormat("yyyy-MM-dd").parse(sendTime);
		Map<String, Object> param = new HashMap<String, Object>();
		for (Integer i : companyIds) {
			param.clear();
			param.put("companyId", i);
			param.put("times", day);
			// 查询今天使用总量
			String stroage = resourceListImpl.findUseSumStroage(param);

			String flow = resourceUseRecordtImpl.findUseSumFlow(param);
			// 查询每天记录
			CompanyServiceStaticDay cssd = new CompanyServiceStaticDay();
			cssd.setCompanyId(i);
			cssd.setUseDate(day);
			CompanyServiceStaticDay cssds = companyServiceStaticDayService.findByDateAndCompanyId(cssd);
			if (cssds != null) {
				cssds.setResourceFlow(flow);
				cssds.setResourceStorageNum(stroage);
				companyServiceStaticDayService.update(cssds);
			} else {
				cssds = new CompanyServiceStaticDay();
				cssds.setCompanyId(i);
				cssds.setResourceFlow(flow);
				cssds.setResourceStorageNum(stroage);
				cssds.setUseDate(day);
				companyServiceStaticDayService.insert(cssds);
			}
		}
	}
}
