package com.yuxin.wx.vo.system;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行中的openoffice
 * @author chopin
 *
 */
public class RunningOpenOffice {
	private List<Integer> pids=new ArrayList<Integer>();
	private RunningOpenOffice(){}
	private static RunningOpenOffice runningOpenOffice;
	private final int[] ports={8100,8101,8102,8103,8104,8105,8106,8107};
	
	public static RunningOpenOffice newInstance(){
		if(runningOpenOffice==null){
			runningOpenOffice=new RunningOpenOffice();
		}
		return runningOpenOffice;
	}
	public List<Integer> getPids(){
		return pids;
	}
	
	public void setPids(List<Integer> pids){
		this.pids=pids;
	}
	public int size(){
		return pids.size();
	}
	public void run(){
		pids.add(getNextPort());
	}
	
	public void release(int port){
		pids.remove(new Integer(port));
	}
	
	public int getNextPort(){
		int current=0;
		current=pids.size()>0?pids.get(pids.size()-1):0;
		int next=ports[0];
		for(int port: ports){
			if(port>current){
				next=port;
				return next;
			}
		}
		return next;
	}
	public String toString(){
		StringBuffer str=new StringBuffer();
		str.append("{");
		for(Integer pid: pids){
			str.append(pid).append(",");
		}
		if(str.length()>1){
			str.deleteCharAt(str.length()-1);
		}
		str.append("}");
		return str.toString();
	}
	public static void main(String[] args) {
		RunningOpenOffice ro=RunningOpenOffice.newInstance();
		System.out.println(ro.getNextPort());
		ro.run();
		System.out.println(ro.getNextPort());
		ro.run();
		ro.run();
		ro.run();
		ro.release(8100);
		System.out.println(ro);
		
		
	}
	
}
