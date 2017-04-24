package com.yuxin.wx.common;

import java.io.Serializable;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 实体Bean基础类
 * @author wang.zx
 * @date 2014-11-26
 */
public class BaseEntity extends BaseModel implements Comparable<Object>, Serializable{
	private static final long serialVersionUID = 1L;
	protected Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	public int compareTo(Object obj) {
		int compare = -1;
		if (obj == null)
			compare = -1;
		else if (this == obj)
			compare = 0;
		else if (!(obj instanceof BaseEntity))
			compare = -1;
		else if (!this.getClass().equals(obj.getClass()))
			compare = -1;
		else {
			BaseEntity castObj = (BaseEntity) obj;
			CompareToBuilder builder = new CompareToBuilder();
			builder.append(this.getId(), castObj.getId());
			compare = builder.toComparison();
		}
		return compare;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if (obj == null) {
			isEqual = false;
		} else if (this == obj) {
			isEqual = true;
		} else if (!(obj instanceof BaseEntity)) {
			isEqual = false;
		} else if (!this.getClass().equals(obj.getClass())) {
			isEqual = false;
		} else {
			BaseEntity castObj = (BaseEntity) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.getId(), castObj.getId());
			isEqual = builder.isEquals();
		}
		return isEqual;
	}
}
