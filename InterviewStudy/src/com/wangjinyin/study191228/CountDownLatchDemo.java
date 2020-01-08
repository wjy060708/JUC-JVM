package com.wangjinyin.study191228;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁：倒计时发射火箭
 * 让一个阻塞，直到另一些线程完成，一系列操作之后才被唤醒
 * 
 * 等到最后一个人同学走了，主线程才能关门
 * 
 *  * @author wang
 *
 */
public class CountDownLatchDemo {

	public static void main(String[] args) {
		
	  CountDownLatch countDownLatch = new CountDownLatch(6);
		
		for (int i = 0; i < 6; i++) {
			 new Thread(()->{
				    try {
				    	System.out.println(Thread.currentThread().getName() + "\t上完自习，离开教室" );
				    	countDownLatch.countDown(); //一个线程执行完，则减去1
					} catch (Exception e) {
						e.printStackTrace();
					}
			   },String.valueOf(i)).start();
		}
		
		try {
			countDownLatch.await();  //让当前线程阻塞
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "\t********************班长最后关门走人" );
	}
}
