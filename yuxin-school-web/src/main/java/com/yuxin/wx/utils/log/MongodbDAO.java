package com.yuxin.wx.utils.log;

import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;

public interface MongodbDAO {
    public boolean insert(String collectionName, BasicDBObject bean);
    
    public boolean delete(String collectionName, BasicDBObject bean);
    
    public List find(String collectionName, BasicDBObject bean);
    
    public boolean update(String collectionName, BasicDBObject oldBean, BasicDBObject newBean);
    
    public boolean drop(String collectionName);
    
    public Set<String> query();
    
}