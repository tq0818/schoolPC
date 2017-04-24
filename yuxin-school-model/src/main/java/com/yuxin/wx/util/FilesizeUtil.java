package com.yuxin.wx.util;

import java.text.DecimalFormat;

/**
 * 参数工具,用于处理参数
 * @author chopin
 *
 */
public class FilesizeUtil {
	
	public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        DecimalFormat df = new DecimalFormat("#.00");
        if (size >= gb) {
            return df.format((double) size / gb) + "GB";
        } else if (size >= mb) {
            return df.format((double) size / mb) + "MB";
        } else if (size >= kb) {
            return df.format((double) size / kb) + "KB";
        } else
            return df.format((double) size) + "B";
    }
}
