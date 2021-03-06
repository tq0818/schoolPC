package com.yuxin.wx.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ObjectTranscoder<BaseEntity> extends SerializeTranscoder {
	Log log=LogFactory.getLog("log");
	@SuppressWarnings("unchecked")
	@Override
	public byte[] serialize(Object value) {
		if (value == null) {
			throw new NullPointerException("Can't serialize null");
		}
		byte[] result = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			BaseEntity m = (BaseEntity) value;
			os.writeObject(m);
			os.close();
			bos.close();
			result = bos.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException("Non-serializable object", e);
		} finally {
			close(os);
			close(bos);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseEntity deserialize(byte[] in) {
		BaseEntity result = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				result = (BaseEntity) is.readObject();
				is.close();
				bis.close();
			}
		} catch (IOException e) {
			log.error(
					String.format(
							"Caught IOException decoding %d bytes of data",
							in == null ? 0 : in.length)
							+ e,e);
		} catch (ClassNotFoundException e) {
			log.error(
					String.format("Caught CNFE decoding %d bytes of data",
							in == null ? 0 : in.length) + e,e);
		} finally {
			close(is);
			close(bis);
		}
		return result;
	}
}
