package com.yuxin.wx.vo.classes;

public class ClassLessonVO {
	/*
	 * spl.`class_type_id`, spl.`commodity_id`, spl.`id`, spl.`lecture_id`,
	 * spl.len, spl.`user_id`, ct.`item_third_code`, cvl.`lecture_name`,
	 * v.`video_time`, st.`name`, st.`edu_year`, st.`edu_class`, st.`edu_step`,
	 * st.`is_in_school`, ct.`origin_type`
	 */

	private Integer class_type_id;
	private Integer commodity_id;
	private Integer id;
	private Integer lecture_id;
	private Integer len;
	private Integer user_id;
	private String item_third_code;
	private String lecture_name;
	private String video_time;
	private String name;
	private String edu_year;
	private String edu_class;
	private String edu_step;
	private Integer is_in_school;
	private String origin_type;

	public Integer getClass_type_id() {
		return class_type_id;
	}

	public void setClass_type_id(Integer class_type_id) {
		this.class_type_id = class_type_id;
	}

	public Integer getCommodity_id() {
		return commodity_id;
	}

	public void setCommodity_id(Integer commodity_id) {
		this.commodity_id = commodity_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLecture_id() {
		return lecture_id;
	}

	public void setLecture_id(Integer lecture_id) {
		this.lecture_id = lecture_id;
	}

	public Integer getLen() {
		return len;
	}

	public void setLen(Integer len) {
		this.len = len;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getItem_third_code() {
		return item_third_code;
	}

	public void setItem_third_code(String item_third_code) {
		this.item_third_code = item_third_code;
	}

	public String getLecture_name() {
		return lecture_name;
	}

	public void setLecture_name(String lecture_name) {
		this.lecture_name = lecture_name;
	}

	public String getVideo_time() {
		return video_time;
	}

	public void setVideo_time(String video_time) {
		this.video_time = video_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEdu_year() {
		return edu_year;
	}

	public void setEdu_year(String edu_year) {
		this.edu_year = edu_year;
	}

	public String getEdu_class() {
		return edu_class;
	}

	public void setEdu_class(String edu_class) {
		this.edu_class = edu_class;
	}

	public String getEdu_step() {
		return edu_step;
	}

	public void setEdu_step(String edu_step) {
		this.edu_step = edu_step;
	}

	public Integer getIs_in_school() {
		return is_in_school;
	}

	public void setIs_in_school(Integer is_in_school) {
		this.is_in_school = is_in_school;
	}

	public String getOrigin_type() {
		return origin_type;
	}

	public void setOrigin_type(String origin_type) {
		this.origin_type = origin_type;
	}

}
