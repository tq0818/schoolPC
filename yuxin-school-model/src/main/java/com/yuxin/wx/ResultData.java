package com.yuxin.wx;

import java.io.Serializable;

public class ResultData<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public boolean flag;
	public String msg;
	public T data;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public ResultData(boolean flag, String msg, T data) {
		super();
		this.flag = flag;
		this.msg = msg;
		this.data = data;
	}
	public ResultData() {
		super();
	}
	@Override
	public String toString() {
		return "ResultData [flag=" + flag + ", msg=" + msg + ", data=" + data + "]";
	}

}
