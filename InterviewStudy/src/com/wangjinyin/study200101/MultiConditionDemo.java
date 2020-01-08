package com.wangjinyin.study200101;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程实现 A打印5次 B打印10次 c打印15次
 * 来10轮
 * 
 *资源类 线程 操作
 */
public class MultiConditionDemo {
	
	public static void main(String[] args) {
		ShareResource shareResource = new ShareResource();
		new Thread(()->{
			shareResource.printA();
		},"AA").start();
		
		new Thread(()->{
			shareResource.printB();
		},"BB").start();
		
		new Thread(()->{
			shareResource.printC();
		},"CC").start();
	}
}

class ShareResource{
	
	private int number = 1; //默认1 为A 2为B 3为C
	
	private Lock lock = new ReentrantLock();
	
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	
	
    public void printA() {
    	try {
    		lock.lock();
    		//判断
    		while(number != 1) {
    			condition1.await();
    		}
    		
    		//干活
    		for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
    		
    		//通知
    		number = 2;
    		condition2.signal();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
    }
    
    public void printB()  {
    	try {
    		lock.lock();
    		//判断
    		while(number != 2) {
    			condition2.await();
    		}
    		
    		//干活
    		for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
    		
    		//通知
    		number = 3;
    		condition3.signal();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
    }
    
    public void printC() {
    	try {
    		lock.lock();
    		//判断
    		while(number != 3) {
    			condition3.await();
    		}
    		
    		//干活
    		for (int i = 1; i <= 15; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
    		
    		//通知
    		number = 1;
    		condition1.signal();
    		
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
    }
}


