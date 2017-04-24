package com.yuxin.wx.utils.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;

public class MongoDBUtil {
    private static Mongo mongo = null;

    private MongoDBUtil() {

    }

    // 获取数据库连接
    public static DB getDB() {
        String dbstring = "", dbUsername = "", dbPassword = "";
        try {
            Properties prop = new Properties();
            InputStream in = Log4JLogger.class.getClassLoader().getResourceAsStream("config.properties");
            prop.load(in);
            dbstring = prop.getProperty("mongodb.dbstring");
            dbUsername = prop.getProperty("mongodb.username");
            dbPassword = prop.getProperty("mongodb.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (MongoDBUtil.mongo == null) {
            MongoDBUtil.init();
        }
        DB db = MongoDBUtil.mongo.getDB(dbstring);
        db.authenticate(dbUsername, dbPassword.toCharArray());
        return db;
    }

    // 初始化数据库
    private static void init() {
        try {
            Properties prop = new Properties();
            InputStream in = Log4JLogger.class.getClassLoader().getResourceAsStream("config.properties");
            prop.load(in);
            // 实例化Mongo
            MongoDBUtil.mongo = new Mongo(prop.getProperty("mongodb.hostname"), Integer.parseInt(prop.getProperty("mongodb.port")));
            MongoOptions opt = MongoDBUtil.mongo.getMongoOptions();
            // 设置连接池大小
            opt.connectionsPerHost = Integer.parseInt(prop.getProperty("mongodb.poolsize"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}