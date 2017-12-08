package com.yuxin.wx.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.utils.ParameterUtil;

/**
 * Controller of ZcLive
 * @author zengdeqiang
 *
 */
@Controller
@RequestMapping("/zcLive")
public class ZcLiveController{
	@Autowired
    private IClassModuleLessonService classModuleLessonServiceImpl;
	/**
	 * 获取直播参数
	 * @param lessonId 课次编号
	 * @param userId 用户标识号
	 * @param companyId 机构名称
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/getParam", method = RequestMethod.POST)
	public  JSONObject queryZxLiveVoByParam(HttpServletRequest request,Integer lessonId,String userId,String companyId){
		JSONObject json = new JSONObject();
		if(lessonId==null||userId==null||userId==""||companyId==null||companyId==""){
			json.put("isError","1");
			json.put("error","lessonId或者userId或者companyId不能为空!");
			return json;
		}else{
			json.put("isError","0");
		}
		ClassModuleLesson cml = classModuleLessonServiceImpl.findClassModuleLessonById(lessonId);
		//获取房间号
		if(cml!=null){
			String studentUrlGh=cml.getStudentUrlGh();
			if(studentUrlGh!=null&&studentUrlGh!=""){
				String number=studentUrlGh.substring(studentUrlGh.lastIndexOf("/")+1);
				json.put("number",number);
			}
		}
		//直播配置参数
		CompanyLiveConfig companyLiveConfig=classModuleLessonServiceImpl.queryCompanyLiveConfigByCompanyId(companyId);
		if(companyLiveConfig!=null){
			json.put("domain",companyLiveConfig.getDomain().substring(companyLiveConfig.getDomain().lastIndexOf("//")+2));
			json.put("joinPwd",companyLiveConfig.getStudentClientToken());
		}else{
			json.put("domain","winshare-edu.gensee.com");
			json.put("joinPwd","zs_s_secret_c");
		}
		//拼接uid
		json.put("uid", 1000000000+Integer.valueOf(userId));
		//获取nicKname
		String nicKname=classModuleLessonServiceImpl.findNickNameByUserFrontId(userId);
		json.put("nicKname",nicKname);
		//获取k
		Integer uid =(1000000000+Integer.valueOf(userId));
        long timestamp = System.currentTimeMillis();
        String token=(String)json.get("joinPwd");
		String md5 = ParameterUtil.GenseeMd5(uid, lessonId,Integer.valueOf(companyId), timestamp,token);
        String k=uid+":" + lessonId + ":" + companyId + ":" + timestamp + ":" + token + ":" + md5;
        json.put("k",k);

       System.out.println(json.get("domain"));
		System.out.println(json.get("joinPwd"));
		System.out.println(json.get("number"));

		return json;
	}
	public static void main(String[] stra){
		String str="http://winshare-edu.gensee.com";
		System.out.print(str.substring(str.lastIndexOf("//")+2));


	}
}
