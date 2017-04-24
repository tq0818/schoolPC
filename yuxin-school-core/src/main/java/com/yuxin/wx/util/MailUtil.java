package com.yuxin.wx.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.yuxin.wx.vo.common.email.Mail;
import com.yuxin.wx.vo.common.email.MailBaseModel;
import com.yuxin.wx.vo.common.email.NoticeEmail;
import com.yuxin.wx.vo.common.email.VerifyEmailYunduo;


/**
 * 
 * @ClassName: MailUtil
 * @Description: 用于发送邮件
 * @author 权飞虎
 * @date 2015年4月8日 下午3:08:55
 * @modifier
 * @modify-date 2015年4月8日 下午3:08:55
 * @version 1.0
 */
public class MailUtil {
	private static Log log = LogFactory.getLog("log");
	private List<Mail> mails = new ArrayList<Mail>();
	private List<MailBaseModel> models = new ArrayList<MailBaseModel>();
	public static String NOTICE = "notice_msg";
	

	/**
	 * 单发
	 * 
	 * @param mail
	 * @param model
	 */
	public MailUtil(Mail mail, MailBaseModel model) {
		models.add(model);
		mails.add(mail);
	}

	/**
	 * 群发
	 * 
	 * @param mails
	 * @param models
	 */
	public MailUtil(List<Mail> mails, List<MailBaseModel> models) {
		this.models = models;
		this.mails = mails;
	}


	/**
	 * 
	 * Class Name: MailUtil.java
	 * 
	 * @Description: 拼接字符串
	 * @author 权飞虎
	 * @date 2015年4月9日 下午8:14:47
	 * @modifier
	 * @modify-date 2015年4月9日 下午8:14:47
	 * @version 1.0
	 * @param mail
	 * @return
	 */
	private String constractVars() {
		StringBuffer substitution_vars = new StringBuffer();
		substitution_vars.append("\"sub\":{");
		Class clazz = models.get(0).getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			substitution_vars.append("\"%").append(convertPara(field.getName())).append("%\"");
			substitution_vars.append(":");
			Method method = null;
			try {
				substitution_vars.append("[");
				for(int i=0;i<models.size();i++){
					MailBaseModel m = models.get(i);
					method = clazz.getDeclaredMethod(getMethodName(field.getName()), null);
					String str = (String) method.invoke(m, null);
					if(i==0){
						substitution_vars.append("\"" + str + "\"");
					}
					if(i!=0 && i<=models.size()-1){
						substitution_vars.append(",");
						substitution_vars.append("\"" + str + "\"");
					}
				}
				substitution_vars.append("]");
				substitution_vars.append(",");
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				// TODO 打印异常
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		substitution_vars.deleteCharAt(substitution_vars.length() - 1);
		substitution_vars.append("}}");
		return substitution_vars.toString();
	}

	private String getMethodName(String name) {
		String result = "";
		if (name.indexOf("_") != -1) {
			String[] str = name.split("_");
			for (int i = 0; i < str.length; i++) {
				result += str[i].substring(0, 1).toUpperCase()
						+ str[i].substring(1);
			}
		} else {
			result = name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		result = "get" + result;
		return result;
	}

	private String convertPara(String name) {
		String result = "";
		for (int i = 0; i < name.length(); i++) {
			if (Character.isUpperCase(name.charAt(i))) {
				result += "_" + String.valueOf(name.charAt(i)).toLowerCase();
			} else {
				result += name.charAt(i);
			}
		}
		return result;
	}
	/**
	 * 
	 * Class Name: MailUtil.java
	 * @Description: 获取目标json
	 * @author 权飞虎
	 * @date 2015年4月10日 上午11:20:07
	 * @modifier
	 * @modify-date 2015年4月10日 上午11:20:07
	 * @version 1.0
	 * @return
	 */
	private String getSubstitutions(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("{\"to\":[");
		for (Mail mail : mails) {
			buffer.append("\"" + mail.getTo() + "\"").append(",");
		}
		buffer.deleteCharAt(buffer.length() - 1);
		buffer.append("],");

		return buffer.toString() + constractVars();
	}
	/**
	 * 
	 * Class Name: MailUtil.java
	 * @Description: 用于发送邮件
	 * @author 权飞虎
	 * @date 2015年4月10日 上午11:21:13
	 * @modifier
	 * @modify-date 2015年4月10日 上午11:21:13
	 * @version 1.0
	 * @param apiKey	发送邮件密码,从配置文件中读取 
	 * @param apiUser	发送邮件账号,从配置文件中读取
	 * @param url	接口地址,从配置文件中读取
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	/*String url, String apiKey, String apiUser*/
	public String sendmail(String url, String apiUser, String apiKey,String type) throws ClientProtocolException, IOException {
		
		// String url ="http://sendcloud.sohu.com/webapi/mail.send.json";
		/*String url = "http://sendcloud.sohu.com/webapi/mail.send_template.json";
		final String apiUser = "postmasteryuuxincom";
		final String apiKey = "wZLYJ69xkwFJVZVq";*/
		HttpPost httpPost = new HttpPost(url);
		HttpClient httpclient = new DefaultHttpClient();

		// 组装基本邮件的参数
		MultipartEntity entity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE, null,
				Charset.forName("UTF-8"));
		entity.addPart("api_user",
				new StringBody(apiUser, Charset.forName("UTF-8")));
		entity.addPart("api_key",
				new StringBody(apiKey, Charset.forName("UTF-8")));
		entity.addPart(
				"from",
				new StringBody(mails.get(0).getFrom(), Charset.forName("UTF-8")));
		entity.addPart("fromname", new StringBody(mails.get(0).getFromName(),
				Charset.forName("UTF-8")));

		entity.addPart("subject", new StringBody(mails.get(0).getSubject(),
				Charset.forName("UTF-8")));
		entity.addPart("use_maillist", new StringBody("false"));
		entity.addPart("resp_email_id", new StringBody("true"));
		
		String className = models.get(0).getClass().getName();
		System.out.println(className);
		//TODO 做法不行,使用split,但是split切开不正确
		System.out.println(className.substring(29, className.length()));
		String name = className.substring(29, className.length());
		/*String[] str = className.split(".");
		System.out.println(str[str.length-1]);*/
		/*String s = name.toLowerCase().split("email")[0];
		StringBuffer sb = new StringBuffer();
		sb.append(s).append("_email");*/
		 String[]ss = new String [20];
	        int count = 0;
	        ss[count] = "";
	        
	        for( int i = 0 ; i<name.length(); i++ ){            
	            char a = name.charAt(i);
	            String b = ""+a;            
	            if( a>64 && a<91 ){ //大写字母的ASCLL码取值范围                
	                count++;                
	                ss[count]=b;                
	            }else{                
	                ss[count]=ss[count].concat(b);                
	            }            
	        }
	        StringBuffer sb = new StringBuffer();
	        for( int i = 1 ; i < ss.length ; i++){ 
	        	if(ss[i]!=null){
	        		String s = ss[i].toLowerCase();
		        	sb.append(s+"_");	
	        	}
	        	
	        }
	        sb.deleteCharAt(sb.length()-1);
	        System.out.println(sb);
		
		
		
//		entity.addPart("template_invoke_name", new StringBody(sb.toString(),
//				Charset.forName("UTF-8")));
		
		entity.addPart("template_invoke_name", new StringBody(/*sb.toString()*/type + "_email",
				Charset.forName("UTF-8")));
		entity.addPart("substitution_vars", new StringBody(getSubstitutions(),
				Charset.forName("UTF-8")));

		// 添加附件
		if (mails.get(0).getFilePath() != null) {
			File file = new File(mails.get(0).getFilePath());
			FileBody attachment = new FileBody(file,
					"application/octet-stream", "UTF-8");
			entity.addPart("files", attachment);
		}
			httpPost.setEntity(entity);
			HttpResponse response = httpclient.execute(httpPost);
			// 处理响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 正常返回, 解析返回数据
				String result = EntityUtils.toString(response.getEntity());
				log.info("发送邮件时,响应信息:"+result);
				result=result.split(",")[0].split(":")[1].replaceAll("\"", "");
			
				return result;
			} else {
				httpPost.releaseConnection();
				return "error";
			}
		
	}

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
//		List<MailBaseModel> models = new ArrayList<MailBaseModel>();
//		VerifyEmailYunduo model1 = new VerifyEmailYunduo();
//		model1.setCompanyName("与新");
//		model1.setSendDate("20150409");
//		model1.setUserEmail("aa@11.com");
//		model1.setVerifyUrl("www.baidu.com");
//		//model1.setTemplateName("verify_email");
//		VerifyEmailYunduo model2 = new VerifyEmailYunduo();
//		model2.setCompanyName("与新");
//		model2.setSendDate("20150409");
//		model2.setUserEmail("aa@11.com");
//		model2.setVerifyUrl("www.baidu.com");
//
//		models.add(model1);
//		models.add(model2);
//		List<Mail> mails = new ArrayList<Mail>();
//		Mail mail1 = new Mail();
//		
//		mail1.setFrom("admin@yuuxin.com");
//		mail1.setFromName("权飞虎");
//		mail1.setSubject("关于....");
//		mail1.setTo("quanfeihu@yuuxin.com");
//		
//		
//
//		Mail mail2 = new Mail();
//		
//		mail2.setFrom("admin@yuuxin.com");
//		mail2.setFromName("权飞虎");
//		mail2.setSubject("关于....");
//		mail2.setTo("1343552919@qq.com");
//		
//		
//		mails.add(mail1);
//		mails.add(mail2);
//		MailUtil u = new MailUtil(mails, models);
		
		String url="http://sendcloud.sohu.com/webapi/mail.send_template.json";
		String apiUser="postmasteryuuxincom";
		String apiKey="wZLYJ69xkwFJVZVq";
//		u.sendmail(url, apiUser, apiKey);
		//String substitutions = u.sendmail();
		//System.out.println(substitutions);
		
		
//		Properties prop = new Properties();
//		InputStream in = MailUtil.class.getClassLoader().getResourceAsStream("config.properties");
//		prop.load(in);
//		String url = prop.getProperty("url");
//		String apiUser = prop.getProperty("apiUser");
//		String apiKey = prop.getProperty("apiKey");
		//邮件标题头
		Mail mail = new Mail();
		mail.setFrom("admin@yuuxin.com");
		mail.setFromName("老黑");
		mail.setSubject("sdfsd");
		//邮件模板
		NoticeEmail model = new NoticeEmail();
		model.setMsg("<p>sjkdflskdfl</p>");
		mail.setTo("licong@yuuxin.com");
		MailUtil mu = new MailUtil(mail, model);
		String result11 = mu.sendmail(url, apiUser, apiKey,MailUtil.NOTICE);
		System.out.println(result11);
		

	}

	

	

}
