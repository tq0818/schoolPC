package com.yuxin.wx.utils.log;

import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;


public class MongodbDAOImpl implements MongodbDAO {

    @Override
    public boolean insert(String collectionName, BasicDBObject bean) {
        DB db = MongoDBUtil.getDB();
        db.getCollection(collectionName).insert(bean);
        return false;
    }

    @Override
    public boolean delete(String collectionName, BasicDBObject bean) {
        DB db = MongoDBUtil.getDB();
        db.getCollection(collectionName).remove(bean);
        return false;
    }

    @Override
    public List find(String collectionName, BasicDBObject bean) {
        DB db = MongoDBUtil.getDB();
        List list = db.getCollection(collectionName).find(bean).toArray();
        return list ;
    }

    @Override
    public boolean update(String collectionName, BasicDBObject oldBean, BasicDBObject newBean) {
        DB db = MongoDBUtil.getDB();
        db.getCollection(collectionName).update(oldBean, newBean);
        return false;
    }

	@Override
	public boolean drop(String collectionName) {
		DB db = MongoDBUtil.getDB();
        db.getCollection(collectionName).drop();
		return false;
	}

	@Override
	public Set<String> query() {
		DB db = MongoDBUtil.getDB();
		return db.getCollectionNames();
	}

}