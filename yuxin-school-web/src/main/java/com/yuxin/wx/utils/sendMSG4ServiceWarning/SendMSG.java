package com.yuxin.wx.utils.sendMSG4ServiceWarning;

public interface SendMSG {
	
	/**
	 * 
	 * Class Name: SendMSG.java
	 * @Description: 发送预警短信
	 * @author dongshuai
	 * @date 2016年12月1日 下午3:37:27
	 * @modifier
	 * @modify-date 2016年12月1日 下午3:37:27
	 * @version 1.0
	 * @return
	 */
	public boolean sendSms4Warn();
	
	/**
	 * 
	 * Class Name: SendMSG.java
	 * @Description: 发送到期提醒短信
	 * @author dongshuai
	 * @date 2016年12月1日 下午3:37:50
	 * @modifier
	 * @modify-date 2016年12月1日 下午3:37:50
	 * @version 1.0
	 * @return
	 */
	public boolean sendSms4Due();
	
	/**
	 * 
	 * Class Name: SendMSG.java
	 * @Description: 发送警告站内信
	 * @author dongshuai
	 * @date 2016年12月1日 下午3:38:22
	 * @modifier
	 * @modify-date 2016年12月1日 下午3:38:22
	 * @version 1.0
	 * @return
	 */
	public boolean sendMsg4Warn();
	
	/**
	 * 
	 * Class Name: SendMSG.java
	 * @Description: 发送过期提醒站内信
	 * @author dongshuai
	 * @date 2016年12月1日 下午3:38:38
	 * @modifier
	 * @modify-date 2016年12月1日 下午3:38:38
	 * @version 1.0
	 * @return
	 */
	public boolean sendMsg4Due();

}
