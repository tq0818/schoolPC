package com.yuxin.wx.util.qiniuVideo;

public class QNVideoUploadThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				String obj = QNQueue.queue.take();
				QNQueue.executor.execute(new QNVideoStart(obj));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
