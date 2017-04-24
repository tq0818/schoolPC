package com.yuxin.wx.util.qiniuVideo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class QNQueue {
	public static boolean upload = true;
	public static final LinkedBlockingQueue<String> queue =  new LinkedBlockingQueue<String>(5000);
	public static final ExecutorService executor = Executors.newFixedThreadPool(10);
}
