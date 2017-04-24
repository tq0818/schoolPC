package com.yuxin.wx.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.yuxin.wx.model.system.SysConfigIndexPrivatepage;

public class ComparatorForPrivatePage implements Comparator{
	
	@SuppressWarnings("unchecked")
	@Override
	public int compare(Object o1, Object o2) {
		SysConfigIndexPrivatepage p1=(SysConfigIndexPrivatepage)o1;
		SysConfigIndexPrivatepage p2=(SysConfigIndexPrivatepage)o2;
		return Integer.parseInt(p1.getSort())-Integer.parseInt(p2.getSort());
		
	}
	
	public static void main(String[] args) {
		SysConfigIndexPrivatepage p1=new SysConfigIndexPrivatepage();
		p1.setId(1);
		p1.setSort("1");
		SysConfigIndexPrivatepage p2=new SysConfigIndexPrivatepage();
		p2.setId(2);
		p2.setSort("2");
		SysConfigIndexPrivatepage p3=new SysConfigIndexPrivatepage();
		p3.setId(3);
		p3.setSort("3");
		
		List<SysConfigIndexPrivatepage> list=new ArrayList<SysConfigIndexPrivatepage>();
		list.add(p2);
		list.add(p1);
		list.add(p3);
		
		Collections.sort(list,new ComparatorForPrivatePage());
		for(SysConfigIndexPrivatepage p: list){
			System.out.println(p);
		}
	}
	
}
