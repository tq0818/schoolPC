package com.yuxin.wx.util.qiniuVideo;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.yuxin.wx.course.mapper.VideoMapper;
import com.yuxin.wx.model.course.Video;
import com.yuxin.wx.util.FileQNUtils;

public class QNVideoStart implements Runnable,ApplicationContextAware {

	private VideoMapper videoMapper;
	private String obj;
	private Log log = LogFactory.getLog("log");
	public QNVideoStart(String obj){
		this.obj = obj;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			log.info("准备上传");
			String[] arr = obj.split("::");
			
			String bucket = arr[0];
			int deadline = Integer.parseInt(arr[1]);
			String fileType = arr[2];
			String aks = arr[3];
			String sks = arr[4];
			String key = arr[5];
			String path = arr[6];
			int vid = Integer.parseInt(arr[7]);
			log.info("信息：" + obj);
			try {
				String keys = FileQNUtils.QiniuVideoUpload(bucket, deadline, fileType,
						aks, sks, key, path);
				System.out.println(key+":" + keys);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Video v = videoMapper.findById(vid);
				v.setVideoStatus("VIDEO_PROCESS_FAIL");
				videoMapper.update(v);
			}
			File file = new File(path);
			file.delete();
			log.info("上传完成");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("上传七牛出现错误：" + e.getMessage());
		}
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		videoMapper = applicationContext.getBean(VideoMapper.class);
	}
	
}
