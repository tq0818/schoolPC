package com.yuxin.wx.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CertificateUtil {
	
	Log log = LogFactory.getLog("log");
	
	private Graphics2D g = null;  
	  
    private int fontSize = 0;  
    
  
    /** 
     * 导入本地图片
     */ 
    public BufferedImage loadImageLocal(String imgName) {  
        try {  
            return ImageIO.read(new File(imgName));  
        } catch (IOException e) {  
        	log.error("本地导入图片失败！",e);
        }  
        return null;  
    }  
    
    /** 
     * 导入远程图片
     */  
    public BufferedImage loadImageUrl(String imgName) {  
        try {  
            URL url = new URL(imgName);  
            return ImageIO.read(url);  
        } catch (IOException e) {  
            log.error("导入远程图片失败！",e); 
        }  
        return null;  
    }  
  
    /** 
     * 生成新图片
     */  
    public void writeImageLocal(String newImage, BufferedImage img) {  
        if (newImage != null && img != null) {  
            try {  
                File outputfile = new File(newImage);  
                ImageIO.write(img, "jpg", outputfile);  
            } catch (IOException e) {  
                log.error("生成图片失败！",e);
            }  
        }  
    }  
  
   /**
    * 
    * @Description: 设置字体
    * @param fontStyle	字体
    * @param fontSize	字体大小
    * @return
    */
    public Font setFont(String fontStyle, int fontSize) {  
        this.fontSize = fontSize;  
        return new Font(fontStyle, Font.PLAIN, fontSize);  
    }  
  
    /**
    * 
    * @Description: 修改图片
    * @param img			背景图片
    * @param content		内容
    * @param x				横坐标
    * @param y				纵坐标
    * @param font			字体
    * @param fontColor		字体颜色
    * @return
    */
    public BufferedImage modifyImage(BufferedImage img, Object content, int x,int y,Font font,Color fontColor) {  
        try {  
            g = img.createGraphics();  
            g.setBackground(Color.WHITE);  
            g.setColor(fontColor);
            g.setFont(font);
            if (content != null) {  
		        int strWidth = g.getFontMetrics().stringWidth(content.toString());
		        g.drawString(content.toString(), x - strWidth / 2, y);
            }  
            g.dispose();  
        } catch (Exception e) {  
            log.error("修改图片失败！",e);  
        }  
        return img;  
    }  
}
