package com.wangjinyin.study191228;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * reenterLockDemo（可重入锁又称递归锁）线程（拥有锁）可以获取任何一个该锁同步的代码块
 * 同一个线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码
 * 
 * 在外层方法获取锁 进入内层方法自动获取锁
 * 验证case one synchronized是一个典型的重入锁
 *  10	 invoked sendSMS 
	10	 invoked sendEmail
	11	 invoked sendSMS
	11	 invoked sendEmail
 *case two 验证reenterLock是一个典型的重入锁
 *
 */

public class ReenterLockDemo{
	
   public static void main(String[] args) {
	   
	   Phone phone = new Phone();
	   
	   new Thread(()->{
		    try {
				phone.sendSMS();
			} catch (Exception e) {
				e.printStackTrace();
			}
	   },"t1").start();
	   
	   new Thread(()->{
		    try {
				phone.sendSMS();
			} catch (Exception e) {
				e.printStackTrace();
			}
	   },"t2").start();
	   
	   
	   try {
		TimeUnit.SECONDS.sleep(1);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	   
	   System.out.println();
	   System.out.println();
	   System.out.println();
	   System.out.println();
	   
	   Thread t3 = new Thread(phone);
	   Thread t4 = new Thread(phone);
	   
	   t3.start();
	   t4.start();
	
   }

}

class  Phone  implements Runnable{
	
	public synchronized void sendSMS() throws Exception {   //外层锁
		System.out.println(Thread.currentThread().getId() + "\t invoked sendSMS");
		
		//调用另一个同步方法
		sendEmail();  //内层锁
	}
	
	public synchronized void sendEmail() throws Exception {
		System.out.println(Thread.currentThread().getId() + "\t invoked sendEmail");
	}

	 Lock lock = new ReentrantLock(); //默认为非公平锁
		@Override
		public void run() {
			get();
		} 
		
		public void get() {
			lock.lock();
			
			try {
				System.out.println(Thread.currentThread().getId() + "\t invoked get");
				set();
			}finally {
				lock.unlock();
			}
		}
		
		public void set() {
			lock.lock();
			 
			try {
				System.out.println(Thread.currentThread().getId() + "\t invoked get");
				set();
			}finally {
				lock.unlock();
			}
		}
}