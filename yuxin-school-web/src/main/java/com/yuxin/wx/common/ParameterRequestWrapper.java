package com.yuxin.wx.common;

import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Description: update params
 * @author dongshuai
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

	@SuppressWarnings("rawtypes")
	private Map params;

	@SuppressWarnings("rawtypes")
	public ParameterRequestWrapper(HttpServletRequest request, Map newParams) {
		super(request);
		this.params = newParams;
	}

	@SuppressWarnings("rawtypes")
	public Map getParameterMap() {
		return params;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Enumeration getParameterNames() {
		Vector v = new Vector(params.keySet());
		return v.elements();
	}

	public String[] getParameterValues(String name) {
		Object o = params.get(name);
		if (o == null) {
			return null;
		} else if (o instanceof String[]) {
			return (String[]) o;
		} else if (o instanceof String) {
			return new String[] { (String) o };
		} else {
			return new String[] { o.toString() };
		}
	}

	public String getParameter(String name) {
		Object o = params.get(name);
		if (o == null) {
			return null;
		} else if (o instanceof String[]) {
			String[] strArr = (String[]) o;
			if (strArr.length > 0) {
				return strArr[0];
			} else {
				return null;
			}
		} else if (o instanceof String) {
			return (String) o;
		} else {
			return o.toString();
		}
	}
}
