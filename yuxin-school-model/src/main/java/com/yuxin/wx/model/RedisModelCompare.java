package com.yuxin.wx.model;

import java.util.Comparator;

public class RedisModelCompare implements Comparator<RedisModel>{

	@Override
	public int compare(RedisModel o1, RedisModel o2) {
		if(o1.getValueSize()>o2.getValueSize())
			return -1;
		else if(o1.getValueSize()==o2.getValueSize())
			
			return 0;
		return 1;
	}

	
}
