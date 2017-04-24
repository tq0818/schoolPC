package com.yuxin.wx.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: GetInjectionClassForSpringContainerUtil
 * @Description: 通过反射 获得 Spring容器中的 bean实例 注解
 * @author 周文斌
 * @date 2016-12-15 下午5:22:38
 * @version 1.0
 */
@Component
public class GetInjectionClassForSpringContainerUtil implements
		ApplicationContextAware {
	public static ApplicationContext application;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		application = applicationContext;
	}

}
