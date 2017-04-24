package com.yuxin.wx.common;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

public class UTF8StringHttpMessageConverter extends StringHttpMessageConverter {

	private static final MediaType utf8 = new MediaType("text","plain",Charset.forName("UTF-8"));
	private boolean writeAcceptCharset = true;
	protected MediaType getDefaultContentType(String dumy) {
		return utf8;
	}
	
}
