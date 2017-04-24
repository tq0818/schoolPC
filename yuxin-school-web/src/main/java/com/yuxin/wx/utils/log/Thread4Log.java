package com.yuxin.wx.utils.log;

import org.apache.log4j.Priority;
import org.apache.log4j.Logger;

public class Thread4Log extends Thread {
	
	private Logger log;
	private String FQCN;
	private Priority traceLevel;
	private Object message;
	private Throwable t;
	
	public Thread4Log(Logger log,String FQCN,Priority traceLevel,Object message,Throwable t) {
		this.log = log;
		this.FQCN = FQCN;
		this.traceLevel = traceLevel;
		this.message = message;
		this.t = t;
	}
	@Override
	public void run() {
		super.run();
		log.log(FQCN, traceLevel, message,t);
	}

}
