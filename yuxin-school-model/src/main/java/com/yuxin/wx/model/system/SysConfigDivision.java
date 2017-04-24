package com.yuxin.wx.model.system;


import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysConfigDivision
 * 
 * @author wang.zx
 * @date 2016-7-14
 */
@SuppressWarnings("serial")
public class SysConfigDivision extends BaseEntity {
	
	
	private String	code;		
	private String	name;		
	private Integer	parentId;		
	private String	pinyin;		

	// Constructor
	public SysConfigDivision() {
	}
	
	/**
	 * full Constructor
	 */
	public SysConfigDivision(Integer id, String code, String name, Integer parentId, String pinyin) {
		setId(id);
		this.code = code;
		this.name = name;
		this.parentId = parentId;
		this.pinyin = pinyin;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysConfigDivision可以实现连缀设置属性
	
	public String getCode() {
		return code;
	}

	public SysConfigDivision setCode(String code) {
		this.code = code;
		return this;
	}
	
	
	public String getName() {
		return name;
	}

	public SysConfigDivision setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public Integer getParentId() {
		return parentId;
	}

	public SysConfigDivision setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}
	
	
	public String getPinyin() {
		return pinyin;
	}

	public SysConfigDivision setPinyin(String pinyin) {
		this.pinyin = pinyin;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysConfigDivision [" + "id=" + getId() + ", code=" + code + ", name=" + name + ", parentId=" + parentId + ", pinyin=" + pinyin +  "]";
	}
}
