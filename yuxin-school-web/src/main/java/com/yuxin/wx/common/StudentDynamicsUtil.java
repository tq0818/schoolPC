package com.yuxin.wx.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.api.system.ISysLogStudentOperationService;
import com.yuxin.wx.model.system.SysLogStudentOperation;
import com.yuxin.wx.utils.GetInjectionClassForSpringContainerUtil;
import com.yuxin.wx.vo.system.SysLogStudentOperationVo;
import com.yuxin.wx.vo.user.UsersFrontVo;

public class StudentDynamicsUtil extends GetInjectionClassForSpringContainerUtil {

	private static ISysLogStudentOperationService sysLogStuOperationImpl;
	private static Log log = LogFactory.getLog("log");
	
	static{
		init();
	} 
	
	private static void init(){
		sysLogStuOperationImpl = application
				.getBean(ISysLogStudentOperationService.class);
	}
	
	/**
	 * 
	 * Class Name: StudentDynamicsUtil.java
	 * @Description: 添加学员动态
	 * @author 周文斌
	 * @date 2017-2-14 下午2:30:51
	 * @modify	2017-2-14 下午2:30:51
	 * @version 
	 * @param user 
	 * @param action 动作 0:购买,1:上直播,2:公开课,3:上录播,4:提问,5:回答,6:评论,7:做题,8:登录,9:注册
	 * @param eventName 事件名（课程名，提问名...名字）,
	 * @param price 购买时 使用 没有 传入 null
	 * @return
	 */
	public static boolean insert(UsersFrontVo user,Integer action,String eventName
			,Double price,Integer sourceId){
		try {
			SysLogStudentOperation slso = new SysLogStudentOperation();
			Date date = new Date();
			slso.setUserId(user.getId());
			slso.setOperaTime(date);
			slso.setOperationType(action);
			slso.setCost(price);
			slso.setCompanyId(user.getCompanyId());
			slso.setSourceId(sourceId);
			String describe = "";
			String uname = "";
			String times = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			if(user.getNickName() != null && !user.getNickName().equals("")){
				uname = user.getNickName();
			}else if(user.getMobile() != null && !user.getMobile().equals("")){
				uname = user.getMobile().substring(0,3) + "****" + user.getMobile().substring(7);
			} else if(user.getUsername() != null && !user.getUsername().equals("")){
				uname = user.getUsername();
			}
			slso.setUserName(uname);
			switch (action) {
				case 0:
					describe = times + " " + uname + " 购买了 {dhead}" + eventName +"{dfoot}";
					break;
				case 1:
					describe = times + " " + uname + " 上了 {dhead}" + eventName + " 直播课{dfoot}";  
					break;
				case 2:
					describe = times + " " + uname + " 上了 {dhead}" + eventName + " 公开课{dfoot}";  
					break;
				case 3:
					describe = times + " " + uname + " 上了 {dhead}" + eventName + " 录播课{dfoot}"; 
					break;
				case 4:
					describe = times + " " + uname + " 提问 {dhead}" + eventName + "{dfoot}"; 
					break;
				case 5:
					describe = times + " " + uname + " 回复 {dhead}" + eventName + "{dfoot}";
					break;
				case 6:
					describe = times + " " + uname + " 评论了 {dhead}" + eventName + "{dfoot}";
					break;
				case 7:
					describe = times + " " + uname + " 做了 {dhead} " + eventName + "{dfoot}";
					break;
				case 8:
					describe = times + " " + uname + " {dhead}登陆了{dfoot} " + eventName;
					break;
				case 9:
					describe = times + " " + uname + " {dhead}注册{dfoot}了账户，成为了新学员 ";
					break;
				case 10:
					describe = times + " " + uname + " 做了 {dhead} " + eventName + "{dfoot}";
					break;
				case 11:
					describe = times + " " + uname + " 购买了 {dhead} " + eventName + "{dfoot}";
					break;
				case 12:
					describe = times + " " + uname + " 购买了 {dhead} " + eventName + "{dfoot}";
					break;
				case 13:
					describe = times + " " + uname + " 购买了 {dhead} " + eventName + "{dfoot}";
					break;
				case 14:
					describe = times + " " + uname + " 做了 {dhead} " + eventName + "{dfoot}";
					break;
				case 15:
					describe = times + " " + uname + " 做了 {dhead} " + eventName + "{dfoot}";
					break;
				default:
					break;
			}
			slso.setOperation(describe);
			sysLogStuOperationImpl.insert(slso);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug(e.getMessage());
			return false;
		}
	}
	
	/**
	 * 
	 * Class Name: StudentDynamicsUtil.java
	 * @Description: 根据条件查询单个消息
	 * @author 周文斌
	 * @date 2017-2-14 下午3:40:15
	 * @modify	2017-2-14 下午3:40:15
	 * @version 
	 * @param slso 条件
	 * @param sort 0 正序， 1 倒序
	 * @return
	 */
	public static SysLogStudentOperation selectOne(SysLogStudentOperation slso,Integer sort){
		try {
			return sysLogStuOperationImpl.selectOne(slso, sort);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("查询学院动态错误，" + e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * Class Name: StudentDynamicsUtil.java
	 * @Description: 根据条件查询list消息
	 * @author 周文斌
	 * @date 2017-2-14 下午3:40:15
	 * @modify	2017-2-14 下午3:40:15
	 * @version 
	 * @param slso 条件
	 * @param sort 0 正序， 1 倒序
	 * @return
	 */
	public static PageFinder<SysLogStudentOperation> selectList(SysLogStudentOperation slso
			,Integer sort){
		try {
			return sysLogStuOperationImpl.selectList(slso, sort);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("查询学院动态错误，" + e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * Class Name: StudentDynamicsUtil.java
	 * @Description: 根据时间段查询分页
	 * @author 周文斌
	 * @date 2017-2-14 下午3:40:15
	 * @modify	2017-2-14 下午3:40:15
	 * @version 
	 * @param slso 条件
	 * @param sort 0 正序， 1 倒序
	 * @return
	 */
	public static PageFinder<SysLogStudentOperation> selectListByDate(
			SysLogStudentOperationVo slsoVo,Integer sort){
		try {
			return sysLogStuOperationImpl.selectListByDate(slsoVo, sort);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("时间查询学院动态错误，" + e.getMessage());
			return null;
		}
	}
}
