package com.wangjinyin.study200103;

import java.security.AccessController;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 获取线程的方法  数据的jdbc 
 * 
 * System.out.println(Runtime.getRuntime().availableProcessors()); 查看计算机的有多个核
 * @author wang
 *  //array arrays
	//collection collections
	//excutor excutors 辅助工具类
	  
	 5种线程池
	     ExecutorService executorPool = Executors.newFixedThreadPool(5); //一池5线程  执行长期任务 性能较好
	     ExecutorService executorPool = Executors.newSingleThreadExecutor(); //一池1线程    
	     ExecutorService executorPool = Executors.newCachedThreadPool(); //一池n线程   短程异步的小程序或者负载较轻的服务器 
	    
	  public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }
    
            底层是由阻塞队列实现的
    
      ThreadPoolExecutor类的讲解以及7大参数
      public ThreadPoolExecutor(int corePoolSize,  线程池中常驻核心线程数，当线程池中的线程数目达到corePoolSize，就会把到达的任务放到缓存队列中
                              int maximumPoolSize, 线程池所能容纳的最大线程数 此值比喻大于等于1
                              long keepAliveTime,  多余的空闲线程的存活时间    当前线程池的数量超过corePoolSize时，当空闲线程达到keepAliveTime时  空闲线程会被销毁到corePoolSize数
                              TimeUnit unit, 时间单位
                              BlockingQueue<Runnable> workQueue,  阻塞队列 > 相当于银行的候客区  当没有核心线程时到达的任务就会进入阻塞队列
                              ThreadFactory threadFactory, 表示生成线程池中工作线程的线程工厂，用于创建线程 一般使用默认即可
                              RejectedExecutionHandler handler 当核心线程数达到线程池的最大线程数，且任务队列也已满 ，则会启动拒绝策略（4种）
                              )
      {
        if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }
 */

public class MyThreadPoolDemo {
	
  public static void main(String[] args) {
	 ExecutorService executorPool = Executors.newFixedThreadPool(5); //一池5线程  、
//	 ExecutorService executorPool = Executors.newSingleThreadExecutor(); //一池1线程
//	 ExecutorService executorPool = Executors.newCachedThreadPool(); //一池n线程
	 
	 //模拟10个用户来办理业务，每个业务就是一个处理线程
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
