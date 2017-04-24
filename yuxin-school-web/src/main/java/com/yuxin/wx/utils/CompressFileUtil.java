package com.yuxin.wx.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


/**
 * 
 * @ClassName: CompressFileUtil
 * @Description: java解压文件
 * @author zhang.zx
 * @date 2016年6月26日 下午9:17:57
 * @modifier
 * @modify-date 2016年6月26日 下午9:17:57
 * @version 1.0
 */
public class CompressFileUtil {
	
	public static final int DEFAULT_BUFSIZE = 1024 * 16;

	/**
	* 解压Zip文件
	* 
	* @param srcZipFile
	* @param destDir
	* @throws IOException
	*/
	public static void unZip(File srcZipFile, String destDir) throws IOException
	{
		ZipFile zipFile = new ZipFile(srcZipFile);
		unZip(zipFile, destDir);
	}

	/**
	* 解压Zip文件
	* 
	* @param srcZipFile
	* @param destDir
	* @throws IOException
	*/
	public static void unZip(String srcZipFile, String destDir) throws IOException
	{
		ZipFile zipFile = new ZipFile(srcZipFile);
		unZip(zipFile, destDir);
	}

	/**
	* 解压Zip文件
	* 
	* @param zipFile
	* @param destDir
	* @throws IOException
	*/
	public static void unZip(ZipFile zipFile, String destDir) throws IOException
 {
		Enumeration<? extends ZipEntry> entryEnum = zipFile.entries();
		ZipEntry entry = null;
		while (entryEnum.hasMoreElements()) {
			entry = entryEnum.nextElement();
			File destFile = new File(destDir + entry.getName());
			if (entry.isDirectory()) {
				destFile.mkdirs();
			} else {
				destFile.getParentFile().mkdirs();
				InputStream eis = zipFile.getInputStream(entry);
				write(eis, destFile);
			}
		}
	}

	/**
	* 将输入流中的数据写到指定文件
	* 
	* @param inputStream
	* @param destFile
	*/
	public static void write(InputStream inputStream, File destFile) throws IOException
	{
		BufferedInputStream bufIs = null;
		BufferedOutputStream bufOs = null;
		try {
			bufIs = new BufferedInputStream(inputStream);
			bufOs = new BufferedOutputStream(new FileOutputStream(destFile));
			byte[] buf = new byte[DEFAULT_BUFSIZE];
			int len = 0;
			while ((len = bufIs.read(buf, 0, buf.length)) > 0) {
				bufOs.write(buf, 0, len);
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			close(bufOs, bufIs);
		}
	}

	/**
	* 安全关闭多个流
	* 
	* @param streams
	*/
	public static void close(Closeable... streams)
	{
		try {
			for (Closeable s : streams) {
				if (s != null)
					s.close();
			}
		} catch (IOException e) {
		   e.printStackTrace(System.err);
		}
	}
	/**
	 * 
	 * Class Name: CompressFileUtil.java
	 * @Description: 测试
	 * @author zhang.zx
	 * @date 2016年6月27日 上午10:10:54
	 * @modifier
	 * @modify-date 2016年6月27日 上午10:10:54
	 * @version 1.0
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		unZip("C:/Users/1/Desktop/ASQ Learning Institute Quality Tools Training - Fishbone Diagram SCORM 1-2.zip", "D:/123/");
	}
}
