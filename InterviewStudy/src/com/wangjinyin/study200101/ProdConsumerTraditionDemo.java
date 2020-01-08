package com.wangjinyin.study200101;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 题目一个线程加1  一个线程减去1 两个线程交替执行  来5轮
 * 
 * 1.线程 资源（方法） 操作类
 * 2.判断 干活 通知
 * 3.防止虚假交换
 */
public class ProdConsumerTraditionDemo {
	
	public static void main(String[] args) {
		
	 ShareData shareData = new ShareData();
		new Thread(()->{
			for (int i = 0; i < 5; i++) {
				try {
					shareData.increment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"AA").start();
		
		new Thread(()->{
			for (int i = 0; i < 5; i++) {
				try {
					shareData.decrement();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"BB").start();
		
		new Thread(()->{
			for (int i = 0; i < 5; i++) {
				try {
					shareData.increment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"CC").start();
		
		new Thread(()->{
			for (int i = 0; i < 5; i++) {
				try {
					shareData.decrement();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"DD").start();
	}
	
	
}

//资源类
class ShareData {
	
	private int number = 0;
	
	private Lock lock = new ReentrantLock();
	
	private Condition condition = lock.newCondition();
	
	public void increment() throws Exception {
		
		//0.加锁
		try {
			lock.lock();
			//1.判断  使用if会出现徐姐唤醒，使用while可以避免线程的虚假唤醒
			while (number != 0) {
				//等待
				condition.await();
			}
			//2.干活
			number ++;
			
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			//3.通知唤醒
			condition.signalAll();
		} catch (Exception e) {
			
		} finally {
			lock.unlock();
		}
	}
	
    public void decrement() throws Exception {
		
		//0.加锁
		try {
			lock.lock();
			//1.判断  使用if会出现徐姐唤醒，使用while可以避免线程的虚假唤醒
			while (number == 0) {
				//等待
				condition.await();
			}
			//2.干活
			number --;
			
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			//通知唤醒
			condition.signalAll();
		} catch (Exception e) {
			
		} finally {
			lock.unlock();
		}
	}
}
