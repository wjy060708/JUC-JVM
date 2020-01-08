package com.wangjinyin.study191228;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现一个自旋锁 
 *     不进行阻塞，循环获取锁的方式，减少上下文的切换 缺点：循环会消耗cpu
 * @author wang
 *
 */
public class SpinLockDemo {
	
	//原子引用线程
	AtomicReference<Thread> atomicReference = new AtomicReference<Thread>();
	
	public void myLock() {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName() + "\t come in");
		
		while (!atomicReference.compareAndSet(null, thread)) {
			
		}
	}
	
	public void myUnLock() {
		Thread thread = Thread.currentThread();
		
		atomicReference.compareAndSet(thread, null);
		System.out.println(thread.getName() + "\t invoked myunlock");
	}
  
  
	public static void main(String[] args) {
		SpinLockDemo spinLockDemo = new SpinLockDemo();
		
		new Thread(()->{
			spinLockDemo.myLock();  //占用锁
			
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			spinLockDemo.myUnLock();  //解锁
		   },"AA").start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(()->{
			spinLockDemo.myLock();  //占用锁
			
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			spinLockDemo.myUnLock();  //解锁
		   },"BB").start();
   }
}
