package com.yuxin.wx.vo.redis;

import java.io.Serializable;

/**
 * 查询统计时候缓存当前登录用户所管理的学校、年级拥有的lesson信息,避免多次查询数据库
 * @author liutingrong
 *
 */
public class ClassLectureVO implements Serializable{
	/*
	 * cvl.id, cvl.lecture_name lesson_name, v.video_time, ct.`item_one_code`,
	 * sci.`item_name` , ct.`item_second_code`
	 */

	private Integer id; // lessonId
	private String lesson_name; // lessonName
	private String video_time; // 视频长度字符串格式数据 hh:mm:ss
	private String item_one_code; // 学段code
	private String item_name; // 课程名
	private String item_second_code; // 年级code
	private Integer videoLen; // 视频长度数字表示，单位:秒
	private Integer liveLessonTime;//直播视频总时长

	public ClassLectureVO(){
		
	}
	
	public void initVedioLen(){
		/*this.id = id;
		this.lesson_name = lesson_name;
		this.video_time = video_time;
		this.item_one_code = item_one_code;
		this.item_name = item_name;
		this.item_second_code = item_second_code;*/
		
		if(null == this.video_time || "".equals(this.video_time)){
			this.videoLen = -1;
		}
		
		
		String[] arr = this.video_time.split(":");
		try{
			int len = Integer.valueOf(arr[0])*3600 + Integer.valueOf(arr[1])*60 + Integer.valueOf(arr[2]);
			this.videoLen = len;
		}catch(Exception e){
			System.out.println("====> Calculate videlLen failed when create ClassLectureVO with video_time =  "+this.video_time);
			this.videoLen = -1;
		}
	}
	
	/**
	 * 带参数构造函数
	 * @param id	lessonId
	 * @param lesson_name	lessonName
	 * @param video_time	视频长度字符串格式数据 hh:mm:ss
	 * @param item_one_code	学段code
	 * @param item_name	课程名
	 * @param item_second_code	年级code
	 */
	public ClassLectureVO(Integer id, String lesson_name, String video_time, String item_one_code, String item_name,String item_second_code) {
		super();
		this.id = id;
		this.lesson_name = lesson_name;
		this.video_time = video_time;
		this.item_one_code = item_one_code;
		this.item_name = item_name;
		this.item_second_code = item_second_code;
		
		if(null == this.video_time || "".equals(this.video_time)){
			this.videoLen = -1;
		}
		
		String[] arr = this.video_time.split(":");
		try{
			int len = Integer.valueOf(arr[0])*3600 + Integer.valueOf(arr[1])*60 + Integer.valueOf(arr[2]);
			this.videoLen = len;
		}catch(Exception e){
			System.out.println("====> Calculate videlLen failed when create ClassLectureVO with video_time =  "+this.video_time);
			this.videoLen = -1;
		}
		
	}

	@Override
	public String toString() {
		return "ClassLectureVO [id=" + id + ", lesson_name=" + lesson_name + ", video_time=" + video_time
				+ ", item_one_code=" + item_one_code + ", item_name=" + item_name + ", item_second_code="
				+ item_second_code + ", videoLen=" + videoLen + "]";
	}

	public Integer getLiveLessonTime() {
		return liveLessonTime;
	}

	public void setLiveLessonTime(Integer liveLessonTime) {
		this.liveLessonTime = liveLessonTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLesson_name() {
		return lesson_name;
	}

	public void setLesson_name(String lesson_name) {
		this.lesson_name = lesson_name;
	}

	public String getVideo_time() {
		return video_time;
	}

	public void setVideo_time(String video_time) {
		this.video_time = video_time;
	}

	public String getItem_one_code() {
		return item_one_code;
	}

	public void setItem_one_code(String item_one_code) {
		this.item_one_code = item_one_code;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_second_code() {
		return item_second_code;
	}

	public void setItem_second_code(String item_second_code) {
		this.item_second_code = item_second_code;
	}

	public Integer getVideoLen() {
		return videoLen;
	}

	public void setVideoLen(Integer videoLen) {
		this.videoLen = videoLen;
	}

}
