package com.wangjinyin.study200103;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 * @author wang
 * 两个进程或两个以上的进程在执行过程中
 * 因争夺资源而相互等待的现象
 * 若无外力相助，他们则无法进行下去
 *
 *线程 操作 资源类
 */
class HoldLockThread implements Runnable{
	
	private String lockA;
	
	private String lockB;
	
	public HoldLockThread(String lockA, String lockB) {
		this.lockA = lockA;
		this.lockB = lockB;
	}
	
	@Override
	public void run() {
		
		synchronized (lockA) {
			System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockA + "\t 尝试获取" + lockB);
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized (lockB) {
				System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockB + "\t 尝试获取" + lockA);
			}
		}
	}
}


public class DeathLockDemo {
	
	public static void main(String[] args) {
		String lockA = "lockA";
		String lockB = "lockB";
		
		new Thread(new HoldLockThread(lockA, lockB),"AA").start();
		new Thread(new HoldLockThread(lockB, lockA),"BB").start();
		
		/**
		 * PS -ef|gref xxx
		 * JPS = java ps
		 */
	}
}
