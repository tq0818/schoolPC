package com.yuxin.wx.controller.task;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/1/11.
 */
@Component("myQuartzJobFactory")
public class MyQuartzJobFactory extends SpringBeanJobFactory {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    /**
     *
     * 这里覆盖了super的createJobInstance方法，对其创建出来的类再进行autowire。
     *
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {

        Object jobInstance = super.createJobInstance(bundle);

        beanFactory.autowireBean(jobInstance);

        return jobInstance;

    }
}
