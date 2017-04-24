package com.yuxin.wx.util.officeConvert;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OfficeThreadPool{
	private Log log =LogFactory.getLog(this.getClass());
	private final int[] ports={8100,8101};
	private static OfficeThreadPool pool;
	private static int count;
	
	private OfficeThreadPool(){}
	
	public synchronized static OfficeThreadPool getNewInstance(){
		if(pool==null){
			pool=new OfficeThreadPool();
		}
		return pool;
	}
	public OpenOfficeHandler getThread(){
//		count++;
//		int i=count%2;
		OpenOfficeHandler handler=new OpenOfficeHandler(8100);
		return handler;
	}
	
}
