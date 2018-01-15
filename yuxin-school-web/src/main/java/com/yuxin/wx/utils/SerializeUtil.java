package com.yuxin.wx.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.utils.log.Log4JLogger;

import net.sf.json.JSONObject;

@SuppressWarnings("unchecked")
public class SerializeUtil {
	static final Class<?> CLAZZ = SerializeUtil.class;
	 private static Log log_serializeUtil = LogFactory.getLog("SerializeUtil");
    public static byte[] serialize(Object value) {
        if (value == null) { 
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (Exception e) {
        	log_serializeUtil.error("byte[] serialize()",e);
        } finally {
            close(os);
            close(bos);
        }
        return rv;
    }

    
	public static Object deserialize(byte[] in) {
        return deserialize(in, Object.class);
    }

    public static <T> T deserialize(byte[] in, Class<T>...requiredType) {
        Object rv = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in != null) {
                bis = new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                rv = is.readObject();
            }
        } catch (Exception e) {
        	log_serializeUtil.error("deserialize(byte[], Class<T>...requiredType)",e);
        } finally {
            close(is);
            close(bis);
        }
        return (T) rv;
    }

    private static void close(Closeable closeable) {
        if (closeable != null)
            try {
                closeable.close();
            } catch (IOException e) {
            	log_serializeUtil.error("close(Closeable)",e);
            }
    }

}
