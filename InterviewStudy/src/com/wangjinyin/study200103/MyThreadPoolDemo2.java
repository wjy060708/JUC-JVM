package com.wangjinyin.study200103;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


//手写线程池
public class MyThreadPoolDemo2 {
	
  public static void main(String[] args) {
	  
	  System.out.println(Runtime.getRuntime().availableProcessors());
	  
	  ExecutorService executorPool = new ThreadPoolExecutor(2, 5, 1L, 
			  TimeUnit.SECONDS,
			  new LinkedBlockingQueue<Runnable>(3), 
			  Executors.defaultThreadFactory(),
			  new ThreadPoolExecutor.DiscardOldestPolicy());
	  
	  try {
			 
			 for (int i = 0; i < 10; i++) {
				 executorPool.execute(()->{
					 System.out.println(Thread.currentThread().getName() + "\t办理业务");
				 });
			}
		} finally {
			executorPool.shutdown();
		}
  }
}
