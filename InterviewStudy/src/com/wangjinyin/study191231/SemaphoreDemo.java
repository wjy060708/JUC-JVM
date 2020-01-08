package com.wangjinyin.study191231;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *计数信号量  多个线程抢多个资源
 *主要的目的是：实现多个资源互斥使用   另一个目的是控制并发线程数
                       
                    构造方法    Constructor and Description 
   Semaphore(int permits)  //允许的并发数
                    创建一个 Semaphore与给定数量的许可证和nonfair公平设置。 默认为非公平锁效率较高  
   Semaphore(int permits, boolean fair) 
                    创建一个允许给定数量 Semaphore和给定的公平环境。  
 */
public class SemaphoreDemo {
	
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3); //3表示线程的并发数
		
		for (int i = 0; i < 6; i++) {  //模拟六部车
			new Thread(()->{
				try {
					semaphore.acquire();  //占用资源
					System.out.println(Thread.currentThread().getName() + "\t抢到车位");
					//假设该车需要停多长时间
					TimeUnit.SECONDS.sleep(3);
					System.out.println(Thread.currentThread().getName() + "\t停车3秒后离开");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaphore.release(); //释放资源
				}
			},String.valueOf(i)).start();;
		}
	}
}
