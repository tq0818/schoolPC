package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigIndexModel
 * 
 * @author chopin
 * @date 2015-3-17
 */
@SuppressWarnings("serial")
public class SysConfigIndexModel extends BaseEntity {
	
	private Integer	id;		
	private String	htmlElement;		 /* DIV模板的代码-其中定义的变量需要在使用的时候去根据用户定义内容初始化。
只存放html元素，不存js、css */ 

	// Constructor
	public SysConfigIndexModel() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigIndexModel(Integer id, String htmlElement) {
		this.id = id;
		this.htmlElement = htmlElement;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigIndexModel可以实现连缀设置属性
	
	public String getHtmlElement() {
		return htmlElement;
	}

	public SysConfigIndexModel setHtmlElement(String htmlElement) {
		this.htmlElement = htmlElement;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysConfigIndexModel [" + "ID=" + id + ", htmlElement=" + htmlElement +  "]";
	}
}
