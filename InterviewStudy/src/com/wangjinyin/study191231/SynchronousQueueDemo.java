package com.wangjinyin.study191231;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 每个put必须等待一个take操作，负责不能添加元素，反之亦然
 */
public class SynchronousQueueDemo {
	
	public static void main(String[] args) {
		BlockingQueue<String> blaBlockingQueue = new SynchronousQueue<String>();
		
		new Thread(()-> {
			try {
				System.out.println(Thread.currentThread().getName() + "\t put A");
				blaBlockingQueue.put("A");
				
				System.out.println(Thread.currentThread().getName() + "\t put B");
				blaBlockingQueue.put("B");
				
				System.out.println(Thread.currentThread().getName() + "\t put C");
				blaBlockingQueue.put("C");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"AA").start();;
		
		new Thread(()-> {
			try {
				TimeUnit.SECONDS.sleep(5);
				System.out.println(blaBlockingQueue.take());
				
				TimeUnit.SECONDS.sleep(5);
				System.out.println(blaBlockingQueue.take());
				
				TimeUnit.SECONDS.sleep(5);
				System.out.println(blaBlockingQueue.take());
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"AA").start();
	}
}
