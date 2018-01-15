package com.yuxin.wx.api.watchInfo;

import com.yuxin.wx.model.watchInfo.ClassRoomRelation;
import com.yuxin.wx.model.watchInfo.WatchInfo;
import com.yuxin.wx.model.watchInfo.WatchInfoFromZSGet;
import com.yuxin.wx.model.watchInfo.WatchInfoFromZSSend;

import java.util.List;
import java.util.Map;

/**
 * Service Interface:UserHistory
 * @author chopin
 * @date 2016-9-27
 */
public interface IWatchInfoService {
	 List<WatchInfo> getWatchInfo(WatchInfo watchInfo);
	 void addWatchInfo(WatchInfo watchInfo);
	 void addClassRoomRelation(ClassRoomRelation list);
	List<WatchInfo> getClassRoomRelation(ClassRoomRelation relation);
	List<WatchInfo> getLessonByDate(Map map);
	//写入课堂历史并发量
	void addWatchInfoFromZSResult(WatchInfoFromZSGet watchInfo);
	//写入课堂实时并发量
	void addWatchInfoFromZSSent(WatchInfoFromZSSend watchInfo);
	//查询最大并发量
	Map getCurrentByRoom(Map map);


	//根据参数统计人员观看视频是否超过30分钟
	List<Map<String, Object>> queryWatchInfoByParam(Map<String, Object> param);
}