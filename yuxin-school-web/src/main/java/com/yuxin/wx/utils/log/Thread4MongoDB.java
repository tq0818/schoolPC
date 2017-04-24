package com.yuxin.wx.utils.log;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.yuxin.wx.utils.WebUtils;

public class Thread4MongoDB extends Thread {

    private Object message;
    private Logger log;
    private String level;
    private Throwable t;
    private Date date;

    public Thread4MongoDB(Object message, Logger log, String level, Throwable t, Date date) {
        this.message = message;
        this.log = log;
        this.level = level;
        this.t = t;
        this.date = date;
    }

    @Override
    public void run() {
        super.run();
        this.addMongodb(this.message, this.log, this.level, this.t, this.date);
    }

    /**
     * 存入MongoDB
     */
    private void addMongodb(Object message, Logger logger, String level, Throwable throwable, Date date) {
        try {
            if ("error".equals(level)
                    && !message.toString().contains("====================================================================================================")) {
                Properties prop = new Properties();
                InputStream in = Log4JLogger.class.getClassLoader().getResourceAsStream("config.properties");
                prop.load(in);
                String serverName = prop.getProperty("mongodb.server.name"); // 服务器名
                String moduleList = prop.getProperty("mongodb.modulelist"); // 模块名
                String className = ""; // 类
                String moduleName = ""; // 模块
                String t = new SimpleDateFormat("yyyy-MM-dd").format(date); // 时间

                if (null != logger.getName()) {
                    if (Arrays.asList(moduleList.split(",")).contains(logger.getName())) {
                        className = message.toString().substring(message.toString().indexOf("[") + 1, message.toString().indexOf("]"));
                    } else {
                        className = logger.getName();
                    }
                    moduleName = logger.getName();
                }

                MongodbDAO mongodbDAOImpl = new MongodbDAOImpl();
                BasicDBObject log = new BasicDBObject();

                log.put("class_name", className);
                if (null != throwable) {
                    message = message.toString() + "\n" + throwable.toString();
                    for (StackTraceElement st : throwable.getStackTrace()) {
                        message = message.toString() + "\n" + st.toString();
                    }
                }
                log.put("message", message.toString().indexOf("-") < message.toString().length()
                        ? message.toString().substring(message.toString().indexOf("-") + 1).trim() : "");
                try {
                    log.put("company_id", WebUtils.getCurrentCompanyId());
                } catch (Exception e) {
                    log.put("company_id", 0);
                }
                log.put("record_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                log.put("server_id", serverName);
                log.put("level", level);
                log.put("module_name", moduleName);

                //mongodbDAOImpl.insert("log_" + t, log);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
