package com.yuxin.wx.common;

/**
 * 
 * @ClassName: JsonMsg
 * @Description: TODO(消息通知 接口)
 * @author 1
 * @date 2015-10-26 下午6:29:35
 * @version 1.0
 */
public interface JsonMsg {
	//jsonKEY
	public static final String MSG = "msg";
	public static final String URL = "url";
	public static final String RESULT = "result";
	public static final String CODE = "code";
	//jsonVALUE
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String EXCEPTION = "exception";
	
	public static final String NULL_RESULT = "null_result";
	
	public static final String INFORMATION = "哎呀，出现了一点点意外";
	
	public static final Integer CODE_0 = 0;	/* 成功*/
	public static final Integer CODE_1 = 1;
	public static final Integer CODE_2 = 2;
	public static final Integer CODE_3 = 3;
	public static final Integer CODE_4 = 4;
	public static final Integer CODE_5 = 5;
	public static final Integer CODE_6 = 6;
	public static final Integer CODE_7 = 7;
	public static final Integer CODE_8 = 8;
	public static final Integer CODE_9 = 9;
	public static final Integer CODE_101 = 101;
	public static final Integer CODE_102 = 102;
	public static final Integer CODE_103 = 103;
}
