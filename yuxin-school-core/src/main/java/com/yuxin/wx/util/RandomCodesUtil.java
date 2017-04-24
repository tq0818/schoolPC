package com.yuxin.wx.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.yuxin.wx.util.JedisUtil;

public class RandomCodesUtil {

	private static StringBuffer bufs = new StringBuffer();

	/**
	 * 优惠码
	 */
	public static final String CODETYPE_COUPON = "CODETYPE_COUPON";
	private static final Long COUPON_BASE_NUM = 11000000L;
	private static final int COUPON_ADD_NUM = 1000000;
	private static final int COUPON_EACH_NUM = 1000;
	/**
	 * 学习码
	 */
	public static final String CODETYPE_STUDYCARD = "CODETYPE_STUDYCARD";
	private static final Long STUDYCARD_BASE_NUM = 1100000L;
	private static final int STUDYCARD_ADD_NUM = 100000;
	private static final int STUDYCARD_EACH_NUM = 100;
	/**
	 * 充值卡
	 */
	public static final String CODETYPE_RECHARGECARD = "CODETYPE_RECHARGECARD";
	private static final Long RECHARGECARD_BASE_NUM = 1100000L;
	private static final int RECHARGECARD_ADD_NUM = 100000;
	private static final int RECHARGECARD_EACH_NUM = 100;

	/**
	 * 生成码
	 */
	public static List<String> getCodes(String codeType, Integer totalNum) {
		List<String> codeslist = new ArrayList<String>();
		Random rd = new Random();

		switch (codeType) {
		case CODETYPE_COUPON:
			if (JedisUtil.getString("baseNum") == null) {
				JedisUtil.put("baseNum", COUPON_BASE_NUM.toString());
			}
			Long baseNum = JedisUtil.incrBy("baseNum", COUPON_ADD_NUM) - COUPON_BASE_NUM;
			for (int i = 0; i < totalNum; i++) {
				Integer a = rd.nextInt(COUPON_EACH_NUM) + 1;
				codeslist.add(productCode(baseNum + a));
				baseNum += a;
			}
			break;
		case CODETYPE_STUDYCARD:
			if (JedisUtil.getString("studyCardBaseNum") == null) {
				JedisUtil.put("studyCardBaseNum", STUDYCARD_BASE_NUM.toString());
			}
			Long studyCardBaseNum = JedisUtil.incrBy("studyCardBaseNum", STUDYCARD_ADD_NUM) - STUDYCARD_BASE_NUM;
			for (int i = 0; i < totalNum; i++) {
				Integer a = rd.nextInt(STUDYCARD_EACH_NUM) + 1;
				codeslist.add(productCode(studyCardBaseNum + a));
				studyCardBaseNum += a;
			}
			break;
		case CODETYPE_RECHARGECARD:
			if (JedisUtil.getString("rechargeCardBaseNum") == null) {
				JedisUtil.put("rechargeCardBaseNum", RECHARGECARD_BASE_NUM.toString());
			}
			Long rechargeCardBaseNum = JedisUtil.incrBy("rechargeCardBaseNum", RECHARGECARD_ADD_NUM) - RECHARGECARD_BASE_NUM;
			for (int i = 0; i < totalNum; i++) {
				Integer a = rd.nextInt(RECHARGECARD_EACH_NUM) + 1;
				codeslist.add(productCode(rechargeCardBaseNum + a));
				rechargeCardBaseNum += a;
			}
			break;
		default:
			break;
		}
		return codeslist;
	}

	/**
	 * 组合码
	 */
	private static String productCode(Long baseNum) {
		StringBuffer buf = new StringBuffer();
		getYu(baseNum);
		final char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		String[] str = reverseSort(bufs.toString()).split(",");
		for (int i = 0; i < str.length; i++) {
			if (!StringUtils.isEmpty(str[i])) {
				buf.append(digits[Integer.parseInt(str[i])]);
			}
		}
		bufs.setLength(0);
		return buf.toString();
	}

	/**
	 * 转36进制
	 */
	private static Long getYu(Long i) {
		if (i > 36) {
			if (i % 36 < 36) {
				bufs.append(i % 36 + ",");
			}
			return getYu(i / 36);
		} else {
			if (i < 36) {
				bufs.append(i + ",");
			}
			return i;
		}
	}

	/**
	 * 得到余数后反转
	 */
	private static String reverseSort(String str) {
		StringBuffer b = new StringBuffer();
		String[] s = str.split(",");
		for (int i = s.length - 1; i >= 0; i--) {
			if (!StringUtils.isEmpty(s[i])) {
				b.append(s[i] + ",");
			}
		}
		return b.toString();
	}
}
