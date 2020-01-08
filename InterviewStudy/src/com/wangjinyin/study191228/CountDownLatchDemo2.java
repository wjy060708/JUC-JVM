package com.wangjinyin.study191228;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁：倒计时发射火箭
 * 
 * 等到最后一个人同学走了，主线程才能关门
 * 
 *  * @author wang
 *
 */
public class CountDownLatchDemo2 {

	public static void main(String[] args) {
		
	  CountDownLatch countDownLatch = new CountDownLatch(6);
		
		for (int i = 1; i <= 6; i++) {
			 new Thread(()->{
				    try {
				    	System.out.println(Thread.currentThread().getName() + "\t被灭" );
				    	countDownLatch.countDown(); //一个线程执行完，则减去1
					} catch (Exception e) {
						e.printStackTrace();
					}
			   },CountryEnum.forEachCountryEnum(i).getRetMessage()).start();
		}
		
		try {
			countDownLatch.await();  //await相当于一个障碍
			} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "\t********************秦国统一江山" );
	}
}
