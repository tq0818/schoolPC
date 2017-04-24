package com.yuxin.wx.util.officeConvert;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.yuxin.wx.common.JsonMsg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.artofsolving.jodconverter.office.OfficeException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CancellationException;

public class OpenOfficeHandler {
	private static Log log = LogFactory.getLog("log");
    private static String outputDir;
    private static String openOffice;
    private  int port;

    private static String imageServiceRealPath;
    static{
        try {
			Resource resource = new ClassPathResource("config.properties");
			Properties props= null;
			try {
			    props = PropertiesLoaderUtils.loadProperties(resource);
			} catch (IOException e) {
			    e.printStackTrace();
			    System.out.println("28," + e.getMessage());
			}
			//outputDir = props.getProperty("office.output.dir");
			outputDir = props.getProperty("file.storage.path");
			openOffice = props.getProperty("openOffice.install.dir");

            imageServiceRealPath=props.getProperty("imageServiceRealPath");
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OfficeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public OpenOfficeHandler(int port){
    	this.port=port;
    }

    
    /**
     * office转换成pdf
     *
     * @param inputFileStr 要转换的路径
     * @return Map<String,String> pdf="/2/20160726/11.pdf",html="/2/20160726/11.html"
     * @throws Exception 
     */
    public Map<String,String> transformPdf(String oldpath,String dir){
        oldpath = imageServiceRealPath + oldpath;
        try {
        	log.info("存储路径," + oldpath);
            String sname = oldpath.substring(oldpath.lastIndexOf("/"));
    		File file = new File(outputDir + sname);
            InputStream fis = new FileInputStream(oldpath);
            FileOutputStream fos = new FileOutputStream(file);
            int len = 0;
            byte[] b = new byte[1024 * 1024 * 4];
            while ((len = fis.read(b)) != -1) {
            	fos.write(b, 0, len);
    		}
            fos.flush();
            fos.close();
			fis.close();
			
		   return createFile(dir,file,"pdf");
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return new HashMap<String,String>();
    }
    
    private Map<String,String> createFile(String dir,File file,String type) {
    	log.info(file.getAbsolutePath());
    	log.info(file.getName());
    	Map<String,String> nameMap = new HashMap<String, String>();
        String fileFullName = file.getName().toLowerCase();
        String fileName = fileFullName.split("\\.")[0];
        //String outFilePdfStr = outputDir+fileName+".pdf";
        String outFilePdfStr = imageServiceRealPath+"pdf/"+fileName+".pdf";
        File outFile = new File(outFilePdfStr);
        nameMap.put("pdf",outFilePdfStr);
        long starttime = System.currentTimeMillis();
        DocumentFormat docType= null;
        if(file.getName().contains(".docx")){
        	docType=new DocumentFormat("Microsoft Word 2007 XML",DocumentFamily.TEXT,"application/vnd.openxmlformats-officedocument.wordprocessingml.document","docx");
        }
        if(file.getName().contains(".xlsx")){
        	docType=new DocumentFormat("Microsoft Excel 2007 XML",DocumentFamily.TEXT,"application/vnd.ms-excel","xlsx");
        }
        if(file.getName().contains(".pptx")){
        	docType=new DocumentFormat("Microsoft PPT 2007 XML",DocumentFamily.TEXT,"application/vnd.ms-powerpoint","pptx");
        }
        log.info(this.toString());
    	log.info("********************************************************");
    	log.info("目标路径:"+outFilePdfStr);
    	log.info("源文件:"+file.getAbsolutePath()+file.getName());
    	log.info("********************************************************");
    	OpenOfficeConnection connection=new SocketOpenOfficeConnection("127.0.0.1",port);
        try {
            long start = System.currentTimeMillis();
			connection.connect();
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            if(type.equals("pdf")){
            	log.info("pdf存放路径," + outFilePdfStr);
//                converter.convert(file, outFile);
                converter.convert(file,docType,outFile,null);
                nameMap.put("filePath", "pdf/"+fileName+".pdf");
                nameMap.put("size", "0");
                nameMap.put("sourcePath", file.getAbsolutePath()+file.getName());
            }
            long end = System.currentTimeMillis() - start;
            nameMap.put(JsonMsg.MSG, JsonMsg.SUCCESS);
        } catch (OfficeException e) {
        	long end = System.currentTimeMillis() - starttime;
        	log.error("时长,"+end+",office转换异常," + e.getMessage(),e);
            e.printStackTrace();
            nameMap.put(JsonMsg.MSG, "office转换异常," + e.getMessage());
        } catch (CancellationException e){
        	long end = System.currentTimeMillis() - starttime;
        	log.error("时长,"+end+",office转换异常," + e.getMessage(),e);
            e.printStackTrace();
            nameMap.put(JsonMsg.MSG, "office转换异常,任务已被取消");
        } catch (Throwable e) {
            long end = System.currentTimeMillis() - starttime;
        	log.error("时长,"+end+",office转换异常," + e.getMessage(),e);
            e.printStackTrace();
            /*officeManager.stop();*/
            if(end > (1000*60*30L)){
                nameMap.put(JsonMsg.MSG, "office转换异常,时间超时");
            }else{
                nameMap.put(JsonMsg.MSG, "office转换异常," + e.getMessage());
            }
            file.delete();
        }finally{
        	if(connection.isConnected()){
        		connection.disconnect();
        	}
        }
        log.info("====>>>>>转换完成,耗时:"+(System.currentTimeMillis() - starttime)+"ms");
        return nameMap;
    }
}
