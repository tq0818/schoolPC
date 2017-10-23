package com.yuxin.wx.api.watchInfo;

import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.user.UserHistory;
import com.yuxin.wx.model.watchInfo.ClassRoomRelation;
import com.yuxin.wx.model.watchInfo.WatchInfo;

import java.util.Date;
import java.util.List;

/**
 * Service Interface:UserHistory
 * @author chopin
 * @date 2016-9-27
 */
public interface IWatchInfoService {
	 List<WatchInfo> getWatchInfo(WatchInfo watchInfo);
	 void addWatchInfo(WatchInfo watchInfo);
	 void addClassRoomRelation(List<ClassRoomRelation> list);
	List<WatchInfo> getClassRoomRelation(ClassRoomRelation relation);
	List<WatchInfo> getLessonByDate(String lessonDate);
}