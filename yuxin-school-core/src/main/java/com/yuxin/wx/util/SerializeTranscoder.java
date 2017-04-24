package com.yuxin.wx.util;

import java.io.Closeable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class SerializeTranscoder {
	protected static Log log = LogFactory.getLog("log");
	  
	  public abstract byte[] serialize(Object value);
	  
	  public abstract <T> T deserialize(byte[] in);
	  
	  public void close(Closeable closeable) {
	    if (closeable != null) {
	      try {
	        closeable.close();
	      } catch (Exception e) {
	    	  log.error("Unable to close " + closeable, e); 
	    	  e.printStackTrace();
	      }
	    }
	  }
}
