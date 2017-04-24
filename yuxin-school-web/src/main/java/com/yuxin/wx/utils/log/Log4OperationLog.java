package com.yuxin.wx.utils.log;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.system.ISysLogManagerOptionDictService;
import com.yuxin.wx.api.system.ISysLogManagerOptionService;
import com.yuxin.wx.model.system.SysLogManagerOption;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.WebUtils;

@Aspect
@Component
public class Log4OperationLog {

	@Autowired
	private ISysLogManagerOptionService optionService;
	@Autowired
	private ISysLogManagerOptionDictService optionDictService;

	@AfterReturning(pointcut = "execution(* com.yuxin.wx.controller..*.*(..)) or execution(* com.yuxin.wx.common.BaseWebController.*(..))", returning = "result")
	private void after(JoinPoint joinPoint, Object result) {
		try {
			// 请求
			HttpServletRequest request = OperationLogUtils.getRequest(joinPoint);
			if (request == null)
				return;

			// 操作用户
			Users user = WebUtils.getCurrentUser(request);
			if (user == null)
				return;

			// 操作时间
			Date time = new Date();

			// 请求参数(request)
			String requestParams = OperationLogUtils.getRequestParams(request);
			// 请求参数(url)
			String urlParams = OperationLogUtils.getUrlParams(joinPoint);
			// 返回数据(function)
			String returnType = OperationLogUtils.getReturnType(result);
			// 返回数据(model)
			String modelParams = OperationLogUtils.getModelParams(joinPoint);

			// mongo async
			ThreadPool4MongoDB.getInstance().execute(new Thread4OperationLog(joinPoint, result, request, user, time,
					requestParams, urlParams, returnType, modelParams, optionService, optionDictService));
			// mysql
			saveOperationLog(joinPoint, result, request, user, time, requestParams, urlParams, returnType, modelParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: save mysql
	 * @author dongshuai
	 */
	private void saveOperationLog(JoinPoint joinPoint, Object result, HttpServletRequest request, Users user, Date time,
			String requestParams, String urlParams, String returnType, String modelParams) {
		try {
			// 方法名
			String methodName = OperationLogUtils.getMethodName(joinPoint);

			if (!OperationLogUtils.isNeed(methodName))
				return;

			// 方法全名
			String fullMethodName = OperationLogUtils.getFullMethodName(joinPoint);
			// 方法描述
			String operation = this.optionDictService.queryOperationByAction(fullMethodName);

			// 操作用户
			Integer userId = user.getId();
			Integer companyId = user.getCompanyId();

			// 操作方法
			methodName = operation == null ? fullMethodName : operation;

			// 参数Before
			String dataBefore = "【RequestParams】：" + requestParams + ", 【UrlParams】：" + urlParams;
			// 参数返回
			String dataAfter = "【Return】：" + returnType + ",【ModelParams】：" + modelParams;

			SysLogManagerOption log = new SysLogManagerOption();

			if (dataBefore.length() > 4000)
				dataBefore = dataBefore.substring(0, 3990) + "...";
			if (dataAfter.length() > 4000)
				dataAfter = dataAfter.substring(0, 3990) + "...";

			log.setOptionTime(time);
			log.setCompanyId(companyId);
			log.setUserId(userId);
			log.setOperation(methodName);
			if (OperationLogUtils.isNeedData(fullMethodName)) {
				log.setDataBefore(dataBefore);
				log.setDataAfter(dataAfter);
			}

			this.optionService.insert(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
