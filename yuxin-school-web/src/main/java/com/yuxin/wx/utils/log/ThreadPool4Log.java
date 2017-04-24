package com.yuxin.wx.utils.log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool4Log {
	
    volatile private static ExecutorService instance = null;  
      
    private ThreadPool4Log(){}  
       
    public static ExecutorService getInstance() {  
        try {    
            if(instance != null){
                  
            }else{  
                Thread.sleep(300);  
                synchronized (ThreadPool4Log.class) {  
                    if(instance == null){
                        instance = Executors.newSingleThreadExecutor();;  
                    }  
                }  
            }   
        } catch (InterruptedException e) {   
            e.printStackTrace();  
        }  
        return instance;  
    }  
}