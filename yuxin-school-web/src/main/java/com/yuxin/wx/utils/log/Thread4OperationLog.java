package com.yuxin.wx.utils.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;

import com.mongodb.BasicDBObject;
import com.yuxin.wx.api.system.ISysLogManagerOptionDictService;
import com.yuxin.wx.api.system.ISysLogManagerOptionService;
import com.yuxin.wx.model.system.SysLogManagerOptionDict;
import com.yuxin.wx.model.user.Users;

public class Thread4OperationLog extends Thread {

	private ISysLogManagerOptionService optionService;
	private ISysLogManagerOptionDictService optionDictService;
	private JoinPoint joinPoint;
	private Object result;
	private HttpServletRequest request;
	private Users user;
	private Date time;
	private String requestParams;
	private String urlParams;
	private String returnType;
	private String modelParams;

	public Thread4OperationLog(JoinPoint joinPoint, Object result, HttpServletRequest request, Users user, Date time,
			String requestParams, String urlParams, String returnType, String modelParams,
			ISysLogManagerOptionService optionService, ISysLogManagerOptionDictService optionDictService) {
		this.joinPoint = joinPoint;
		this.result = result;
		this.request = request;
		this.user = user;
		this.time = time;
		this.optionService = optionService;
		this.optionDictService = optionDictService;
		this.requestParams = requestParams;
		this.urlParams = urlParams;
		this.returnType = returnType;
		this.modelParams = modelParams;
	}

	@Override
	public void run() {
		super.run();
		this.saveOperationLog(this.joinPoint, this.result, this.request, this.user, this.time, this.requestParams,
				this.urlParams, this.returnType, this.modelParams, this.optionService, this.optionDictService);
	}

	private void saveOperationLog(JoinPoint joinPoint, Object result, HttpServletRequest request, Users user, Date time,
			String requestParams, String urlParams, String returnType, String modelParams,
			ISysLogManagerOptionService optionService, ISysLogManagerOptionDictService optionDictService) {
		try {
			// 方法名
			String methodName = OperationLogUtils.getMethodName(joinPoint);

			if (!OperationLogUtils.isNeed(methodName))
				return;

			// 方法全名
			String fullMethodName = OperationLogUtils.getFullMethodName(joinPoint);

			// 方法操作
			SysLogManagerOptionDict operation = this.optionDictService.queryByAction(fullMethodName);

			if (operation == null)
				return;

			// 操作用户
			Integer userId = user.getId();
			Integer companyId = user.getCompanyId();

			// 参数Before
			String dataBefore = "【RequestParams】：" + requestParams + ", 【UrlParams】：" + urlParams;
			// 参数返回
			String dataAfter = "【Return】：" + returnType + ",【ModelParams】：" + modelParams;

			MongodbDAO mongodbDAOImpl = new MongodbDAOImpl();
			BasicDBObject log = new BasicDBObject();

			log.put("option_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time));
			log.put("company_id", companyId);
			log.put("user_id", userId);
			log.put("operation", operation.getOperation());
			if (OperationLogUtils.isNeedData(fullMethodName)) {
				log.put("data_before", dataBefore);
				if (operation.getFlag() == null || "modify".equals(operation.getFlag()))
					log.put("data_after", dataAfter);
			}
			
			mongodbDAOImpl.insert("operation_" + new SimpleDateFormat("yyyy-MM-dd").format(time), log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
