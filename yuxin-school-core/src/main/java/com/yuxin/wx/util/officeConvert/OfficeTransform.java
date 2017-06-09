package com.yuxin.wx.util.officeConvert;

import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.util.FileQNUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeException;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CancellationException;

/**
 * Created by hanrb on 2016/5/25.
 */
public class OfficeTransform {
	private static Log log = LogFactory.getLog("log");
    private static OfficeManager officeManager;
    private static String outputDir;
    private static String openOffice;
	private static String imageServiceRealPath;
    static{
        try {
    		/*Process pid=Runtime.getRuntime().exec("ps -ef");
    		BufferedReader br = new BufferedReader(new InputStreamReader(
    				pid.getInputStream()), 1024);
    		String line = null;
    		 while ((line = br.readLine()) != null) {
	    		if(line.contains("soffice.bin")){
		    		String [] inf=line.split(" +");
			    	Runtime.getRuntime().exec("kill -9 "+inf[1]);
	    		}
    		}*/
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

			officeManager = new DefaultOfficeManagerConfiguration()
			        .setOfficeHome(new File(openOffice))
			        .setTaskExecutionTimeout(1000 * 60 * 30L)
			        .setMaxTasksPerProcess(10)
			        .setPortNumbers(8100,8101,8102,8103,8104)
			        .buildOfficeManager();
			officeManager.start();
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
//        officeManager = new DefaultOfficeManagerConfiguration().setPortNumber(8100)
//                .setOfficeHome(new File(openOffice))
//                .buildOfficeManager();
    }

    public static String resetManage(){
    	try {
    		Process pid=Runtime.getRuntime().exec("ps -ef");
    		BufferedReader br = new BufferedReader(new InputStreamReader(
    				pid.getInputStream()), 1024);
    		String line = null;
    		 while ((line = br.readLine()) != null) {
	    		if(line.contains("soffice.bin")){
		    		String [] inf=line.split(" +");
			    	Runtime.getRuntime().exec("kill -9 "+inf[1]);
	    		}
    		}
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

			officeManager = new DefaultOfficeManagerConfiguration()
			        .setOfficeHome(new File(openOffice))
			        .setTaskExecutionTimeout(1000 * 60 * 30L)
			        .setMaxTasksPerProcess(10)
			        .setPortNumbers(8100,8101,8102,8103,8104)
			        .buildOfficeManager();
			officeManager.start();
			return "success";
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} catch (OfficeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
    }
    /**
     * office转换成pdf
     *
     * @param inputFileStr 要转换的路径
     * @return Map<String,String> pdf="/2/20160726/11.pdf",html="/2/20160726/11.html"
     * @throws Exception
     */
    public static Map<String,String> transformPdf(String oldpath,String dir) throws Exception{
		oldpath = imageServiceRealPath + oldpath;
		log.info("存储路径," + oldpath);
		log.info("进入转换," + new SimpleDateFormat("HH:mm:ss").format(new Date()));

		String sname = oldpath.substring(oldpath.lastIndexOf("/"));
		File file = new File(outputDir + sname);
		//String qnpath = FileQNUtils.download(oldpath);

		//InputStream fis = new URL(qnpath).openConnection().getInputStream();
		InputStream fis = new FileInputStream(new File(oldpath));
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
	}

    private static Map<String,String> createFile(String dir,File file,String type) {
		Map<String,String> nameMap = new HashMap<String, String>();
		String fileFullName = file.getName().toLowerCase();
		String fileName = fileFullName.split("\\.")[0];
		String outFilePdfStr = imageServiceRealPath+"pdf/"+fileName+".pdf";
		File pdf = new File(outFilePdfStr);
		long starttime = System.currentTimeMillis();
		OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
		try {
			if(type.equals("pdf")){
				log.info("pdf存放路径," + "pdf/"+fileName+".pdf");
				converter.convert(file, pdf);
				nameMap.put("filePath", "pdf/"+fileName+".pdf");
				nameMap.put("size", String.valueOf(pdf.length()));
				//pdf.delete();
			}
			//把数据保存到数据库
           /* officeManager.stop();*/
			nameMap.put(JsonMsg.MSG, JsonMsg.SUCCESS);
			file.delete();
		} catch (OfficeException e) {
			long end = System.currentTimeMillis() - starttime;
			log.error("时长,"+end+",office转换异常pdf," + e.getMessage(),e);
			e.printStackTrace();
			//pdf.delete();
			nameMap.put(JsonMsg.MSG, "office转换异常pdf," + e.getMessage());
		} catch (CancellationException e){
			long end = System.currentTimeMillis() - starttime;
			log.error("时长,"+end+",office转换异常pdf," + e.getMessage(),e);
			e.printStackTrace();
			//pdf.delete();
			nameMap.put(JsonMsg.MSG, "office转换异常pdf,任务已被取消");
		} catch (Throwable e) {
			long end = System.currentTimeMillis() - starttime;
			log.error("时长,"+end+",office转换异常pdf," + e.getMessage(),e);
			e.printStackTrace();
			//pdf.delete();
            /*officeManager.stop();*/
			if(end > (1000*60*30L)){
				nameMap.put(JsonMsg.MSG, "office转换异常pdf,时间超时");
			}else{
				nameMap.put(JsonMsg.MSG, "office转换异常pdf," + e.getMessage());
			}
		}
		return nameMap;
	}

    public static void main(String[] args) {
        //System.out.println(OfficeTransform.transformPdf("C:/Users/hanrb/Desktop/letv.xlsx"));
        //System.out.println(OfficeTransform.transformHtml("e:/doc/1.docx"));
    }
}
