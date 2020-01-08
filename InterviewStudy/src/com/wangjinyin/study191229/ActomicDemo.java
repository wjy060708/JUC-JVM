package com.wangjinyin.study191229;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 验证Volitile保证原子性
 *   什么是原子性 即不可分割 也即某个线程在做某个具体业务时，中间不可以被加塞或者被分割，要么同时成功，要么同时失败
 *   原则上结果为2000   实际结果为17248
 *   
 *   why：i ++ 会被编译为3条字节码指令  会出现写丢失
 *   
 *   如何解决原子性
 *   1.加syn关键字
 *   2.原子变量
 *   
 * @author wang
 *
 */
public class ActomicDemo {
	
	public static void main(String[] args) {
		MyActomicData myData = new MyActomicData();
       
	   //创建20个线程 每个线程加1000次
       for (int i = 0; i < 20; i++) {
    	   new Thread(new Runnable() {
    			@Override
    			public void run() {
    				
                   for (int j = 1; j <= 1000; j++) {
                	   myData.add();
                	   myData.addAutomic();
				  }
    			}
    		},String.valueOf(i)).start();
	  }
       
       //打印最终结果 等上面20个线程计算完  为什么大于2 一个是main线程  一个是gc线程
      while (Thread.activeCount() > 2) {
		Thread.yield();
	}
      
      System.out.println("最终结果："  + myData.number);
      System.out.println("atomic:" + myData.atomicInteger);
      
}

}
class MyActomicData {
	
	volatile int number = 0;
	
	public void addTo60() {
		this.number = 60;
	}
	
	//此时是加了volatile关键字
	public  void add() {
		number ++;
	}
	
	AtomicInteger atomicInteger = new AtomicInteger(); //默认值为0
	
	public void addAutomic() {
		atomicInteger.getAndIncrement();
	}
}
