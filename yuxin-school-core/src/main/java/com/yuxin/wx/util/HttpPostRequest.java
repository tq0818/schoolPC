package com.yuxin.wx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpPostRequest {
    /**
     * 请求类型： GET
     */
    public final static String GET = "GET";

	public final static String POST = "POST";
	/**
     * 模拟Http Get请求
     * @param urlStr
     *             请求路径
     * @param paramMap
     *             请求参数
     * @return
     * @throws Exception
     */
    public static String get(String urlStr) throws Exception{
        HttpURLConnection conn = null;
        try{
            //创建URL对象
            URL url = new URL(urlStr);
            //获取URL连接
            conn = (HttpURLConnection) url.openConnection();
            //设置通用的请求属性
            setHttpUrlConnection(conn, GET);
            
            //建立实际的连接
            conn.connect();
            
            //获取响应的内容
            return readResponseContent(conn.getInputStream());
        }finally{
            if(null!=conn) conn.disconnect();
        }
    }
	/**
     * 模拟Http Post请求
     * @param urlStr
     *             请求路径
     * @param paramMap
     *             请求参数
     * @return
     * @throws Exception 
     */
    public static String post(String urlStr, Map<String, Object> paramMap) throws Exception{
        HttpURLConnection conn = null;
        PrintWriter writer = null;
        try{
            //创建URL对象
            URL url = new URL(urlStr);
            //获取请求参数
            String param = getParamString(paramMap);
            //获取URL连接
            conn = (HttpURLConnection) url.openConnection();
            //设置通用请求属性
            setHttpUrlConnection(conn, POST);
            //建立实际的连接
            conn.connect();
            //将请求参数写入请求字符流中
            writer = new PrintWriter(conn.getOutputStream());
            writer.print(param);
            writer.flush();
            //读取响应的内容
            return readResponseContent(conn.getInputStream());
        }finally{
            if(null!=conn) conn.disconnect();
            if(null!=writer) writer.close();
        }
    }
    
    /**
     * 读取响应字节流并将之转为字符串
     * @param in
     *         要读取的字节流
     * @return
     * @throws IOException
     */
    private static String readResponseContent(InputStream in) throws IOException{
        Reader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            reader = new InputStreamReader(in);
            char[] buffer = new char[1024];
            int head = 0;
            while( (head=reader.read(buffer))>0 ){
                content.append(new String(buffer, 0, head));
            }
    		return content.toString();
        }finally{
            if(null!=in) in.close();
            if(null!=reader) reader.close();
        }
    }
    
    /**
     * 设置Http连接属性 
     * @param conn
     *             http连接
     * @return
     * @throws ProtocolException 
     * @throws Exception 
     */
    private static void setHttpUrlConnection(HttpURLConnection conn, String requestMethod) throws ProtocolException{
        conn.setRequestMethod(requestMethod);
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
        conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
        if(null!=requestMethod && POST.equals(requestMethod)){
            conn.setDoOutput(true);
            conn.setDoInput(true);
        }
    }
    
    /**
     * 将参数转为路径字符串
     * @param params
     *             参数
     * @return
     */
    private static String getParamString(Map<String, Object> paramMap){
        if(null==paramMap || paramMap.isEmpty()){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(String key : paramMap.keySet() ){
            builder.append("&")
                   .append(key).append("=").append(paramMap.get(key));
        }
        return builder.deleteCharAt(0).toString();
    }
    
    /**
     * 将参数转为路径字符串
     * @param params
     *             参数
     * @return
     */
    private static String getParamStr(Map<String, String> paramMap){
        if(null==paramMap || paramMap.isEmpty()){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(String key : paramMap.keySet() ){
            builder.append("&")
                   .append(key).append("=").append(paramMap.get(key));
        }
        return builder.deleteCharAt(0).toString();
    }
    
    /**
     * 
     * Class Name: HttpPostRequest.java
     * @Description: 抓取网页
     * @author 周文斌
     * @date 2015-9-14 下午9:08:53
     * @version 1.0
     * @param htmlurl
     * @return
     * @throws IOException
     */
	public static String getOneHtml(String htmlurl, String domain)
			throws IOException {
		URL url;
		String temp;
		String name = "";
		try {
			url = new URL(htmlurl);
			if(domain.equals("open.163.com")){
				BufferedReader in = new BufferedReader(new InputStreamReader(
						url.openStream(), "utf-8"));// 读取网页全部内容
				while ((temp = in.readLine()) != null) {
					name = temp.replaceAll("\"", "\'");
					if (name.indexOf("f-thide sname") >= 0) {
						name = name.substring(name.indexOf("f-thide sname"));
						name = name.substring(name.indexOf(">") + 1 , name.lastIndexOf("<"));
						break;
					}
				}
				in.close();
			}else if(domain.equals("v.qq.com")){
				BufferedReader in = new BufferedReader(new InputStreamReader(
						url.openStream(), "utf-8"));// 读取网页全部内容
				while ((temp = in.readLine()) != null) {
					name = temp.replaceAll("\"", "\'");
					if (name.indexOf("<meta itemprop='name'") >= 0) {
						name = name.substring(name.indexOf("<meta itemprop='name'"));
						name = name.substring(name.indexOf("content='") + 9);
						name = name.substring(0,name.indexOf("- 腾讯视频")).trim();
						break;
					}
				}
				in.close();
			}else{
				BufferedReader in = new BufferedReader(new InputStreamReader(
						url.openStream(), "utf-8"));// 读取网页全部内容
				while ((temp = in.readLine()) != null) {
					name = temp.replaceAll("\"", "\'");
					if (name.indexOf("<meta name='irTitle'") >= 0) {
						name = name.substring(name.indexOf("<meta name='irTitle'"));
						name = name.substring(name.indexOf("content='") + 9);
						name = name.substring(0,name.indexOf("'"));
						break;
					}
				}
				in.close();
			}
		} catch (final MalformedURLException me) {
			me.getMessage();
			throw me;
		} catch (final IOException e) {
			e.printStackTrace();
			throw e;
		}
		return name;
	} 
	
	/**
     * 模拟Http Post请求
     * @param urlStr
     *             请求路径
     * @param paramMap
     *             请求参数
     * @return
     * @throws Exception 
     */
    public static String postjson(String urlStr, String jsonstr) throws Exception{
       HttpClient client = new DefaultHttpClient();
       HttpPost post = new HttpPost(urlStr); 
       StringEntity postingString = new StringEntity(jsonstr);
       post.setEntity(postingString);  
       post.setHeader("Content-type", "application/json");
       HttpResponse response = client.execute(post);
       String content = EntityUtils.toString(response.getEntity());
       return content;
    }
    
}
