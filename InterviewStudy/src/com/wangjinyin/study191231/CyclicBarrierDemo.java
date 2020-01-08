package com.wangjinyin.study191231;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/***
 * 一个同步帮助，允许一组线程相互等待，以达到一个共同的障碍点。
 * cyclicbarriers涉及一个固定大小的线程必须党偶尔互相等待程序是有用的。该障碍被称为循环，因为它可以在等待线程被释放后重新使用。
 * 初始值为0 增加到一定值才开始执行  与countDownLatch相反
 * 
 * @author wang
 */
public class CyclicBarrierDemo {
	
	public static void main(String[] args) {
		
		CyclicBarrier cyclicBarrier  = new CyclicBarrier(7, () -> {
			System.out.println("召唤龙珠");
		});
		
		for (int i = 1; i <= 7; i++) {
			final int temp = i;
			new Thread(()->{
				System.out.println(Thread.currentThread().getName() + "\t收集到第" + temp +"颗龙珠");
				try {
					cyclicBarrier.await();   //阻塞
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			},String.valueOf(i)).start();
		}
	}
}
