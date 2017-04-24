package com.yuxin.wx.utils.log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool4MongoDB {
	
    volatile private static ExecutorService instance = null;  
      
    private ThreadPool4MongoDB(){}  
       
    public static ExecutorService getInstance() {  
        try {    
            if(instance != null){
                  
            }else{  
                Thread.sleep(300);  
                synchronized (ThreadPool4MongoDB.class) {  
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