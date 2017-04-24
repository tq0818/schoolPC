package com.yuxin.wx.utils;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.alibaba.fastjson.JSONObject;


public class SWFToolsUtils {  
	
	private static Log log = LogFactory.getLog("log");
    
    public static int SUCCESS = 0;
    public static int CONVERT_ERROR = 1;
    public static int NOT_EXIST = 2;
    public static int OTHER_ERROR = 3;
    public static int TYPE_ERROR = 4;
    public static int FILE_FUZA = 5;
    // pdf2swf.exe的路径  
    private static String PDF2SWF_PATH;  
   
    public static void main(String[] args) {
    	SWFToolsUtils.pdfToSwf(null,"C:/Users/1/Desktop/展示互动新版/Gensee_Web_Player_SDK.pdf"
    			, "C:/Users/1/Desktop/展示互动新版/Gensee_Web_Player_SDK.swf","C:/Program Files (x86)/SWFTools/pdf2swf.exe");  
    }  
    /**   
     * PDF转SWF 
     * @param pdffile PDF文件全路径   
     * @param swffile 转换后SWF文件存放路径   
     */  
    public static JSONObject pdfToSwf(ThreadPoolTaskExecutor threadPoolTaskExecutor,String pdffile, String swffile,String cmdpath)  
    {  
    	JSONObject json = new JSONObject();
        PDF2SWF_PATH = cmdpath;
        if(checkContentType(pdffile, swffile)){  
            return toSwf(threadPoolTaskExecutor,pdffile, swffile);
        }else{
        	json.put("swfstatus", SWFToolsUtils.TYPE_ERROR);
        	return json;
        }
    }  
    /**   
     * 检查文件是否是pdf类型的   
     * @return   
     */   
    private static boolean checkContentType(String pdffile, String swffile)  
    {  
        String type = pdffile.substring(pdffile.lastIndexOf(".") + 1, pdffile.length())  
                .toLowerCase();  
        if (type.equals("pdf")){     
        	log.info("*****是pdf文件*****");     
            return true;     
        }else{  
        	log.info("*****非pdf文件*****");   
            return false;  
        }         
    }  
    /**   
     * 调用批处理文件生成swf文件   
     */   
    private static JSONObject toSwf(ThreadPoolTaskExecutor threadPoolTaskExecutor,String pdffile, String swffile) {  
        JSONObject json = new JSONObject();
    	if(new File(pdffile).isFile()){  
        	log.info("*****正在转换..*****");  
            try {      
                // 调用创建的bat文件进行转换   
            	String command = "";   
            	if ("\\".equals(File.separator)) {
                    command = PDF2SWF_PATH + " -o \"" + swffile + "\" -s flashversion=9 \"" + pdffile + "\"";    
                } else if ("/".equals(File.separator)) {
                	command = PDF2SWF_PATH + " -o " + swffile + " -s flashversion=9 " + pdffile;    
                }
                Process proc = Runtime.getRuntime().exec(command);
                // any error message?
//                StreamGobbler errorGobbler = new
//                StreamGobbler(proc.getErrorStream(), "ERR");
                // any output?
                // kick them off
//                errorGobbler.start();
                FutureTask<String> futureTask = new FutureTask<String>(new
                        StreamGobbler(proc.getInputStream(), "OUT"));
                threadPoolTaskExecutor.execute(futureTask);
                String count = futureTask.get();
                
                // any error???
                int i = proc.waitFor();
                log.info(i);
                log.info("*****转换成功*******");
                if(i == 0){
                	json.put("swfstatus", SWFToolsUtils.SUCCESS);
                    return json;
                }else{
                	json.put("swfstatus", FILE_FUZA);
                	json.put("pages", count);
                    return json;
                }
            }   
            catch (Exception e) {    
            	log.error("*****转换失败*******" + e.getMessage(),e);
                e.printStackTrace();    
            	json.put("swfstatus", SWFToolsUtils.CONVERT_ERROR);
                return json;
            }    
        }  
        else{  
        	log.info("*****文件不存在*****");
        	json.put("swfstatus", SWFToolsUtils.NOT_EXIST);
        	return json;
        }  
    }    
  
}  