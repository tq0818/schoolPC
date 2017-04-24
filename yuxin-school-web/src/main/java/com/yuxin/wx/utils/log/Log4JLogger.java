package com.yuxin.wx.utils.log;

import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

import com.mongodb.BasicDBObject;
import com.yuxin.wx.utils.WebUtils;

import org.apache.log4j.Level;

public class Log4JLogger implements Log, Serializable {

	private static final long serialVersionUID = 1L;

	private static final String FQCN = Log4JLogger.class.getName();
    
    private transient Logger logger = null;

    private String name = null;

    private static Priority traceLevel;
    
    private static final String TRACE = "trace";
	
	private static final String DEBUG = "debug";
	
	private static final String INFO  = "info";
	
	private static final String WARN  = "warn";
	
	private static final String ERROR = "error";
	
	private static final String FATAL = "fatal";
	
    static {
        if (!Priority.class.isAssignableFrom(Level.class)) {
            throw new InstantiationError("Log4J 1.2 not available");
        }
        
        try {
            traceLevel = (Priority) Level.class.getDeclaredField("TRACE").get(null);
        } catch(Exception ex) {
            traceLevel = Priority.DEBUG;
        }
    }

    public Log4JLogger() {
    }

    public Log4JLogger(String name) {
        this.name = name;
        this.logger = getLogger();
    }

    public Log4JLogger(Logger logger ) {
        if (logger == null) {
            throw new IllegalArgumentException(
                "Warning - null logger in constructor; possible log4j misconfiguration.");
        }
        this.name = logger.getName();
        this.logger=logger;
    }
    
    public void trace(Object message) {
    	message = formatMessage(message,TRACE);
//    	Date date = new Date();
    	getLogger().log(FQCN, traceLevel, message, null );
    	//ThreadPool4Log.getInstance().execute(new Thread4Log(getLogger(), FQCN, traceLevel, message, null));
//    	ThreadPool4MongoDB.getInstance().execute(new Thread4MongoDB(message, getLogger(), TRACE, null ,date));
    }
    
    public void trace(Object message, Throwable t) {
    	message = formatMessage(message,TRACE);
//    	Date date = new Date();
    	getLogger().log(FQCN, traceLevel, message, t );
    	//ThreadPool4Log.getInstance().execute(new Thread4Log(getLogger(), FQCN, traceLevel, message, t));
//    	ThreadPool4MongoDB.getInstance().execute(new Thread4MongoDB(message, getLogger(), TRACE, t,date));
    }

    public void debug(Object message) {
    	message = formatMessage(message,DEBUG);
//    	Date date = new Date();
    	getLogger().log(FQCN, Priority.DEBUG, message, null );
    	//ThreadPool4Log.getInstance().execute(new Thread4Log(getLogger(), FQCN, Priority.DEBUG, message, null));
//    	ThreadPool4MongoDB.getInstance().execute(new Thread4MongoDB(message, getLogger(), DEBUG, null,date));
    }
    
    public void debug(Object message, Throwable t) {
    	message = formatMessage(message,DEBUG);
//    	Date date = new Date();
    	getLogger().log(FQCN, Priority.DEBUG, message, t );
    	//ThreadPool4Log.getInstance().execute(new Thread4Log(getLogger(), FQCN, Priority.DEBUG, message, t));
//    	ThreadPool4MongoDB.getInstance().execute(new Thread4MongoDB(message, getLogger(), DEBUG, t,date));
    }

    public void info(Object message) {
    	message = formatMessage(message,INFO);
//    	Date date = new Date();
    	getLogger().log(FQCN, Priority.INFO, message, null );
    	//ThreadPool4Log.getInstance().execute(new Thread4Log(getLogger(), FQCN, Priority.INFO, message, null));
//    	ThreadPool4MongoDB.getInstance().execute(new Thread4MongoDB(message, getLogger(), INFO, null,date));
    }
    
    public void info(Object message, Throwable t) {
    	message = formatMessage(message,INFO);
//    	Date date = new Date();
    	getLogger().log(FQCN, Priority.INFO, message, t );
    	//ThreadPool4Log.getInstance().execute(new Thread4Log(getLogger(), FQCN, Priority.INFO, message, t));
//    	ThreadPool4MongoDB.getInstance().execute(new Thread4MongoDB(message, getLogger(), INFO, t,date));
    }

    public void warn(Object message) {
    	message = formatMessage(message,WARN);
//    	Date date = new Date();
    	getLogger().log(FQCN, Priority.WARN, message, null );
    	//ThreadPool4Log.getInstance().execute(new Thread4Log(getLogger(), FQCN, Priority.WARN, message, null));
//    	ThreadPool4MongoDB.getInstance().execute(new Thread4MongoDB(message, getLogger(), WARN, null,date));
    }
    
    public void warn(Object message, Throwable t) {
    	message = formatMessage(message,WARN);
//    	Date date = new Date();
    	getLogger().log(FQCN, Priority.WARN, message, t );
    	//ThreadPool4Log.getInstance().execute(new Thread4Log(getLogger(), FQCN, Priority.WARN, message, t));
//    	ThreadPool4MongoDB.getInstance().execute(new Thread4MongoDB(message, getLogger(), WARN, t,date));
    }

    public void error(Object message) {
    	message = formatMessage(message,ERROR);
    	Date date = new Date();
    	getLogger().log(FQCN, Priority.ERROR, message, null );
    	//ThreadPool4Log.getInstance().execute(new Thread4Log(getLogger(), FQCN, Priority.ERROR, message, null));
    	ThreadPool4MongoDB.getInstance().execute(new Thread4MongoDB(message, getLogger(), ERROR, null,date));
    }
    
    public void error(Object message, Throwable t) {
    	message = formatMessage(message,ERROR);
    	Date date = new Date();
    	getLogger().log(FQCN, Priority.ERROR, message, t );
    	//ThreadPool4Log.getInstance().execute(new Thread4Log(getLogger(), FQCN, Priority.ERROR, message, t));
    	ThreadPool4MongoDB.getInstance().execute(new Thread4MongoDB(message, getLogger(), ERROR, t,date));
    }

    public void fatal(Object message) {
    	message = formatMessage(message,FATAL);
//    	Date date = new Date();
    	getLogger().log(FQCN, Priority.FATAL, message, null );
    	//ThreadPool4Log.getInstance().execute(new Thread4Log(getLogger(), FQCN, Priority.FATAL, message, null));
//    	ThreadPool4MongoDB.getInstance().execute(new Thread4MongoDB(message, getLogger(), FATAL, null,date));
    }

    public void fatal(Object message, Throwable t) {
    	message = formatMessage(message,FATAL);
//    	Date date = new Date();
    	getLogger().log(FQCN, Priority.FATAL, message, t );
    	//ThreadPool4Log.getInstance().execute(new Thread4Log(getLogger(), FQCN, Priority.FATAL, message, t));
//    	ThreadPool4MongoDB.getInstance().execute(new Thread4MongoDB(message, getLogger(), FATAL, t,date));
    }

    public Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger(name);
        }
        return (this.logger);
    }

    public boolean isDebugEnabled() {
        return getLogger().isDebugEnabled();
    }

    public boolean isErrorEnabled() {
        return getLogger().isEnabledFor(Priority.ERROR);
    }

    public boolean isFatalEnabled() {
        return getLogger().isEnabledFor(Priority.FATAL);
    }

    public boolean isInfoEnabled() {
        return getLogger().isInfoEnabled();
    }

    public boolean isTraceEnabled() {
        return getLogger().isEnabledFor(traceLevel);
    }

    public boolean isWarnEnabled() {
        return getLogger().isEnabledFor(Priority.WARN);
    }
    
    /**
     * 日志格式
     */
    private Object formatMessage(Object message,String type){
    	if("===".equals(message)){
    		message = "====================================================================================================";
    	}
    	String className = getCurrentClassname(type);
    	if(className.contains("com.yuxin") && !className.contains("Log4JLogger")){
    		return "["+className+"] - "+message;
    	}else{
    		return "- "+message;
    	}
	}
    
    /**
	 * 获取类名
	 */
	private String getCurrentClassname(String type){
        StackTraceElement[] st = new Throwable().getStackTrace();
        for (int i = 0; i < st.length; i++) {
        	if(st[i].toString().contains("com.yuxin") && !st[i].toString().contains("Log4JLogger")){
				return ERROR.equals(type)?st[i].toString():st[i].toString().substring(0,st[i].toString().indexOf("("));
			}
		}
        return "EMPTYERROR:CLASS NOT FIND!";
    }
}
