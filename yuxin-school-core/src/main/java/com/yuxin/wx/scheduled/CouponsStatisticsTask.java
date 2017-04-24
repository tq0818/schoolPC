package com.yuxin.wx.scheduled;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.company.ICompanyCouponsPatchService;
import com.yuxin.wx.util.DateUtil;

@Component
public class CouponsStatisticsTask {
	
	@Autowired
	private ICompanyCouponsPatchService companyCouponsPatchServiceImpl;

	private Log log = LogFactory.getLog("log");
	
	public void cleanCouponsTask(){
		
		Date nowdate = DateUtil.getCurrentDate("yyyy-MM-dd");
		
		Date cleantime = DateUtil.addDate(nowdate, -15);
		
		//获得已过期 优惠券id
		List<Integer> ids = companyCouponsPatchServiceImpl.findCouponsByDate(cleantime);
		log.info("可以清理的  id，" + ids);
		if(ids != null && ids.size() > 0){
			for (Integer i : ids) {
				List<String> codes = companyCouponsPatchServiceImpl.findCodeByPatchId(i);
				log.info("根据批次id，查询码," + codes);
				
				if(codes != null && codes.size() > 0){
					companyCouponsPatchServiceImpl.delUserByCode(codes);
					log.info("删除用户下的 优惠券");

					log.info("删除lib下的  优惠券,patchid," + i);
					companyCouponsPatchServiceImpl.delLibByPatch(i);
				}

				log.info("删除patch下的 优惠券,patchid," + i);
				companyCouponsPatchServiceImpl.deleteCompanyCouponsPatchById(i);
			}
		}
	}
	
}
