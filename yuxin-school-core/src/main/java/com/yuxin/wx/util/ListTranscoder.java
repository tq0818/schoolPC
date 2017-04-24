package com.yuxin.wx.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ListTranscoder<BaseEntity> extends SerializeTranscoder {
	Log log = LogFactory.getLog("log");
	@SuppressWarnings("unchecked")
	  public List<BaseEntity> deserialize(byte[] in) {
	    List<BaseEntity> list = new ArrayList<BaseEntity>();
	    ByteArrayInputStream bis = null;
	    ObjectInputStream is = null;
	    try {
	      if (in != null) {
	        bis = new ByteArrayInputStream(in);
	        is = new ObjectInputStream(bis);
	        while (true) {
        	Object obj= is.readObject();
        	log.error(obj);
        	BaseEntity m = (BaseEntity)obj;
	          if (m == null) {
	            break;
	          }
	          list.add(m);
	        }
	        is.close();
	        bis.close();
	      }
	    } catch (IOException e) {  
	    	log.error(String.format("Caught IOException decoding %d bytes of data",  
	        in == null ? 0 : in.length) + e,e);  
	  } catch (ClassNotFoundException e) {  
		  	log.error(String.format("Caught CNFE decoding %d bytes of data",  
	        in == null ? 0 : in.length) + e,e);  
	  }  finally {
	      close(is);
	      close(bis);
	    }
	    return  list;
	  }

	@SuppressWarnings("unchecked")
	@Override
	public byte[] serialize(Object value) {
		if (value == null)
			throw new NullPointerException("Can't serialize null");

		List<BaseEntity> values = (List<BaseEntity>) value;

		byte[] results = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;

		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			for (BaseEntity m : values) {
				os.writeObject(m);
			}

			// os.writeObject(null);
			os.close();
			bos.close();
			results = bos.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException("Non-serializable object", e);
		} finally {
			close(os);
			close(bos);
		}

		return results;
	}

}
