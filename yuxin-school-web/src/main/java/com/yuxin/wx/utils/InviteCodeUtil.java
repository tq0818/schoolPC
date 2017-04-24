package com.yuxin.wx.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.yuxin.wx.util.JedisUtil;


/**
 * 
 * @ClassName: InviteCodeUtil
 * @Description: 邀请码/优惠码
 * @author zhang.zx
 * @date 2016年8月1日 上午11:50:40
 * @modifier
 * @modify-date 2016年8月1日 上午11:50:40
 * @version 1.0
 */
public class InviteCodeUtil {
	
	private static StringBuffer bufs=new StringBuffer();
	private final static int baseNum=10000000;
	private static final Long BASE_NUM=10000000L;
	private static final int ADD_NUM=1000000;
	
	private static Long getYu(Long i){
		 if(i>36){
			 if(i%36<36){
				 bufs.append(i%36+",");
			 }
			 return getYu(i/36);
		 }else{
			 if(i<36){
				 bufs.append(i+",");
			 }
			 return i;
		 }
	 }
	 private static String productCode(Long baseNum){
		 StringBuffer buf=new StringBuffer();
		 getYu(baseNum);
		 final char[] digits = {'0', '1', '2', '3', '4','5','6', '7', '8', '9', 'A', 'B', 'C', 'D','E', 'F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		 String[] str=bufs.toString().split(",");
         for(int i=0;i<str.length;i++){
	      	 if(null!=str[i]){
	      		 buf.append(digits[Integer.parseInt(str[i])]);
	      	 }
         }
         bufs.setLength(0);
         return buf.toString();
	}
	
	private static List<String> genreateCode(Integer len,long baseIn){
		List<String> listcode=new ArrayList<String>();
		Random rd=new Random();
		for(int i=0;i<len;i++){
			Integer a=rd.nextInt(1000)+1;
			listcode.add(productCode(baseIn+a));
			baseIn+=a;
		}
		return listcode;
	}

	 /**
	 * 
	 * Class Name: InviteCodeUtil.java
	 * @Description: 生成优惠码
	 * @author zhang.zx
	 * @date 2016年7月29日 下午4:15:15
	 * @modifier
	 * @modify-date 2016年7月29日 下午4:15:15
	 * @version 1.0
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public static String productInviteCode(Integer userId){
		if(null!=userId){
			return productCode(Long.valueOf(baseNum+userId));
		}
		return null;
	}
	
	/**
	 * 
	 * Class Name: InviteCodeUtil.java
	 * @Description: 生成优惠码
	 * @author zhang.zx
	 * @date 2016年8月1日 上午11:48:45
	 * @modifier
	 * @modify-date 2016年8月1日 上午11:48:45
	 * @version 1.0
	 * @param len 生成优惠码个数
	 * @return List<String> 优惠码集合
	 */
	public static List<String> productCouponsCode(Integer len){
		if(null==JedisUtil.getString("baseNum")){
			JedisUtil.put("base_num", BASE_NUM+"");
		}
		Long base_in=JedisUtil.incrBy("baseNum", ADD_NUM)-BASE_NUM;
		return genreateCode(len, base_in);
	}
	
	
	public static void main(String[] args) throws Exception {
		for(int i=1;i<100;i++){
			System.out.println(productInviteCode(i));
		}
	}
}
