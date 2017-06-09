package com.yuxin.wx.util.officeConvert;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.util.FileQNUtils;
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

public class OpenOfficeManagement {
	private static Log log = LogFactory.getLog("log");
    private static String outputDir;
    private static String openOffice;
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
    
    public OpenOfficeManagement(){
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
    		//String qnpath = FileQNUtils.download(oldpath);
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
        String outFilePdfStr = outputDir+fileName+".pdf";
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
    	SocketOpenOfficeConnection connection=new SocketOpenOfficeConnection("127.0.0.1",8100);
        try {
            long start = System.currentTimeMillis();
            connection.connect();
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            if(type.equals("pdf")){
            	log.info("pdf存放路径," + outFilePdfStr);
//                converter.convert(file, outFile);
                converter.convert(file,docType,outFile,null);
				nameMap.put("filePath", outFilePdfStr);
				nameMap.put("size", String.valueOf(outFile.length()));
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
        }finally{
        	if(connection.isConnected()){
        		
        	}
        }
        log.info("====>>>>>转换完成,耗时:"+(System.currentTimeMillis() - starttime)+"ms");
        return nameMap;
    }
	
	
	public int startUp() {
		int processid=0;
//		try {
//			Process pro = null;
//			Resource resource = new ClassPathResource("config.properties");
//			Properties props = null;
//			try {
//				props = PropertiesLoaderUtils.loadProperties(resource);
//			} catch (IOException e) {
//				e.printStackTrace();
//				System.out.println("28," + e.getMessage());
//			}
//			String OpenOffice_HOME = props.getProperty("openOffice.install.dir");
//			log.info("-------------->>>>>>尝试启动新的openoffice进程");
//			List<String> command =new ArrayList<String>();
//			command.add(OpenOffice_HOME+"program/soffice");
//			command.add("-accept=\"socket,host=127.0.0.1,port="+port+";urp;\"");
//			command.add("-env:UserInstallation=file:///tmp/tempOffice"+port);
//			command.add("-headless");
//			command.add("-nocrashreport");
//			command.add("-nodefault");
//			command.add("-nofirststartwizard");
//			command.add("-nolockcheck");
//			command.add("-nologo");
//			command.add("-norestore");
//			ProcessBuilder processBuilder = new ProcessBuilder(command);
//			Process process = processBuilder.start();
//			processid=getPid();
//			log.info("-------------->>>>>>openoffice启动!["+processid+"]");
//			return processid;
//		} catch (Exception ex) {
//			log.info("",ex);
			return processid;
//		}
	}
	
	private void connect (){
//		try {
//			connection=new SocketOpenOfficeConnection("127.0.0.1",port);
//			connection.connect();
//			log.info("是否链接："+connection.isConnected());
//		} catch (ConnectException e) {
//			// TODO Auto-generated catch block
//			log.info("链接失败,重试",e);
//			try {
//				Thread.sleep(1000L);
//				connect();
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
	}
	
	private void disconnect(){
//		connection.disconnect();
	}
	
	public synchronized void release(){
		// 启动监控线程，超时后释放
//		tick.start();
	}

	public  Boolean shutDown() {
//		log.info("------------------------>>>>>>回收openoffice线程:"+pid+":"+port);
//		try{
//			if(pid!=0 && active==0){
//				Runtime.getRuntime().exec("kill -9 "+pid);
//				if(!this.isAlive()){
//					pool.kill(this);
//				}
//			}else{
//				log.info("=====>>>>>没有启动的officce进程");
//			}
//		}catch(Exception ex){
//			log.info("===>>>结束openoffice进程失败",ex);
//		}
		return true;
	}

	public  Boolean shutDownAll() {
//		String command = "ps -ef";
//		int ppid = 0;
//		try{
//			Process child = Runtime.getRuntime().exec(command);
//			BufferedReader ins = new BufferedReader(new InputStreamReader(child.getInputStream()));
//			String c = null;
//			while ((c = ins.readLine()) != null) {
//				if (c.contains("soffice.bin")) {
//					String [] inf=c.split(" +");
//					ppid = Integer.parseInt(inf[1]);
//				}
//	
//			}
//			ins.close();
//		}catch(Exception ex){
//			log.info(ex);
//		}
//		try{
//			if(ppid!=0 && active==0){
//				Runtime.getRuntime().exec("kill -9 "+ppid);
//			}else{
//				log.info("=====>>>>>没有启动的officce进程");
//			}
//		}catch(Exception ex){
//			log.info("===>>>结束openoffice进程失败",ex);
//		}
		return true;
	}
	

	
	public int getPid(){
		String command = "ps -ef";
		int pid = 0;
//		try{
//			Process child = Runtime.getRuntime().exec(command);
//			BufferedReader ins = new BufferedReader(new InputStreamReader(child.getInputStream()));
//			String c = null;
//			while ((c = ins.readLine()) != null) {
//				if (c.contains("soffice.bin") && c.contains(String.valueOf(this.port))) {
//					String [] inf=c.split(" +");
//					pid = Integer.parseInt(inf[1]);
//				}
//	
//			}
//			ins.close();
//		}catch(Exception ex){
//			log.info(ex);
//		}
		return pid;
	}

}
