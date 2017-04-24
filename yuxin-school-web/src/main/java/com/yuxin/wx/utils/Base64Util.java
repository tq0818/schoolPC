package com.yuxin.wx.utils;

import java.io.UnsupportedEncodingException;

import com.cloopen.rest.sdk.utils.encoder.BASE64Decoder;
import com.cloopen.rest.sdk.utils.encoder.BASE64Encoder;

public class Base64Util {  
    // 加密  
    public static String encoder(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        s = s.replace("+", "-").replace("/", "_").replace("=", "");
        return s;  
    }  
  
    // 解密  
    public static String decoder(String s) {  
    	s = s.replace("-", "+").replace("_", "/");
    	if((s.length() % 2 != 0)){
    		s += "=";
    	}
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    } 
}  
