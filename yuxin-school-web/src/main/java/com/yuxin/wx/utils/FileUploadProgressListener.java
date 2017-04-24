package com.yuxin.wx.utils;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class FileUploadProgressListener implements ProgressListener {
	
	private HttpSession session;  
	  
    public FileUploadProgressListener(HttpSession session) {  
        this.session = session;  
    }
	@Override
	public void update(long upload, long count, int items) {
		// TODO Auto-generated method stub
		System.out.println((double)upload/count);  
	}

}
