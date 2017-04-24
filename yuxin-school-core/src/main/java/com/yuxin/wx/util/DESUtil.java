package com.yuxin.wx.util;

import java.io.IOException;
import java.security.SecureRandom;
 
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
 
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 /**
  * 
  * @ClassName: DESUtil
  * @Description: 用于对数据进行des加密和解密
  * @author 权飞虎
  * @date 2015年4月11日 上午10:13:49
  * @modifier
  * @modify-date 2015年4月11日 上午10:13:49
  * @version 1.0
  */
public class DESUtil {
 
    private final static String DES = "DES";
 
    public static void main(String[] args) throws Exception {
        String data = "1,quanfeihu@yuuxin.com,2015-04-20";
        String key = "12345678";
        System.err.println(encrypt(data, key));
        System.err.println(decrypt(encrypt(data, key), key));
       // 6RvzCz+zqvP0WNIEV2n3xtA4ik+IA870ocjF4hlNJX0MofNT+q7gKg==
    }
     

    /**
     * 
     * Class Name: DESUtil.java
     * @Description: 根据键值进行加密
     * @author 权飞虎
     * @date 2015年4月11日 上午10:14:34
     * @modifier
     * @modify-date 2015年4月11日 上午10:14:34
     * @version 1.0
     * @param data	要加密的数据
     * @param key	密码,从配置文件中读取password
     * @return	加密后的数据
     * @throws Exception	
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }
 

    /**
     * 
     * Class Name: DESUtil.java
     * @Description: 根据键值进行解密
     * @author 权飞虎
     * @date 2015年4月11日 上午10:16:50
     * @modifier
     * @modify-date 2015年4月11日 上午10:16:50
     * @version 1.0
     * @param data	需要解密的数据
     * @param key	加密密码,从配置文件中读取password
     * @return	解密后的数据
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf,key.getBytes());
        return new String(bt);
    }
 
    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
 
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
 
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }
     
     
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
 
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }
}
