package com.yuxin.wx.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class RequestUtil {

	public static String convert(HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");
		InputStream is=request.getInputStream();
		int contentLen=request.getContentLength();
		if (contentLen > 0) {
			int readLen = 0;

			int readLengthThisTime = 0;

			byte[] message = new byte[contentLen];

			try {

				while (readLen != contentLen) {

					readLengthThisTime = is.read(message, readLen, contentLen - readLen);

					if (readLengthThisTime == -1) {// Should not happen.
						break;
					}

					readLen += readLengthThisTime;
				}

				return new String(message);
			} catch (Exception e) {
				// Ignore
				// e.printStackTrace();
			}

		}
		return new String();
	}
}
