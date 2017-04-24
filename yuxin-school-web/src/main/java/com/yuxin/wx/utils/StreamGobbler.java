package com.yuxin.wx.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


class StreamGobbler implements Callable {
	private Log log = LogFactory.getLog("log");
	private InputStream is;
	private String type;
	private OutputStream os;

	public StreamGobbler(InputStream is, String type) {
		this(is, type, null);
	}

	public StreamGobbler(InputStream is, String type, OutputStream redirect) {
		this.is = is;
		this.type = type;
		this.os = redirect;
	}

	/*public void run() {
		try {
			PrintWriter pw = null;
			if (os != null)
				pw = new PrintWriter(os);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			int counts = 0;
			while ((line = br.readLine()) != null) {
				if (pw != null){
					pw.println(line);
				}
				counts += 1;
				log.info(type + ">" + line);
			}
			count = (counts - 1)+"";
			if (pw != null)
				pw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}*/

	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		int counts = 0;
		try {
			PrintWriter pw = null;
			if (os != null)
				pw = new PrintWriter(os);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (pw != null){
					pw.println(line);
				}
				if(line.indexOf("page") != -1){
					counts += 1;
				}
				log.info(type + ">" + line);
			}
			
			if (pw != null)
				pw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return (counts + 1)+"";
	}
}