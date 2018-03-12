package com.yuxin.wx.common;

import java.io.Serializable;
import java.util.List;

public class SimplePage implements Serializable {

	/*
	 * status = 1 : 操作成功 status = 0 操作失败
	 */

	private static final long serialVersionUID = 1L;
	public static final int SUCCESS = 1;
	public static final int FAILED = 0;
	private int status; // 1成功，0失败
	private String msg;
	private int count;
	private int page;
	private int size;
	private List<? extends Object> list;
	private Object data;

	/**
	 * 参数错误
	 * 
	 * @return
	 */
	public static SimplePage getParamError() {
		return new SimplePage("参数错误", SimplePage.FAILED);
	}

	/**
	 * 用户未登录
	 * 
	 * @return
	 */
	public static SimplePage getLogin() {
		return new SimplePage("用户未登录", SimplePage.FAILED);
	}

	/**
	 * 操作失败
	 * 
	 * @return
	 */
	public static SimplePage getFailed(String msg) {
		return new SimplePage(msg, SimplePage.FAILED);
	}

	public static SimplePage getServerError() {
		return new SimplePage("服务器错误，请联系客服", SimplePage.FAILED);
	}

	public SimplePage(String msg, int status) {
		this.msg = msg;
		this.status = status;

	}

	public SimplePage() {
		this.status = SimplePage.SUCCESS;
		this.msg = "操作成功";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<? extends Object> getList() {
		return list;
	}

	public void setList(List<? extends Object> list) {
		this.list = list;
	}

}
