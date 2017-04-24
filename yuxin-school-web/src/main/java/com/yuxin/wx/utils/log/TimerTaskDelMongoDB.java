package com.yuxin.wx.utils.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yuxin.wx.util.DateUtil;

@Component
public class TimerTaskDelMongoDB {
	public void delMongoDB(){
		try {
			MongodbDAO mongodbDAO = new MongodbDAOImpl();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date now=DateUtil.addMonthsToDate(new Date(), -1);
			Date operation_now=DateUtil.addMonthsToDate(new Date(), -3);
			Set<String> names = mongodbDAO.query();
			for (String name : names) {
				if (name.contains("log_")) {
					String dateStr = name.substring(name.indexOf("_")+1);
					Date date = sdf.parse(dateStr);
					if(date.before(now)){
						mongodbDAO.drop(name);
					}
				}
				if (name.contains("operation_")) {
					String dateStr = name.substring(name.indexOf("_")+1);
					Date date = sdf.parse(dateStr);
					if(date.before(operation_now)){
						mongodbDAO.drop(name);
					}
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
