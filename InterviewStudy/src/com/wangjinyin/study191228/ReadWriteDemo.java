package com.wangjinyin.study191228;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：
 *      多尔衮线程读一个资源没有任何问题，所以为了满足并发量，读取共享资源应该同时进行
 *    小总结：读读可以共存
 *                      读写;不能共存
 *                    写写：不能共存
 *   写操作：原子+独占  整个过程必须是一个统一体，中间允许被分割和打断
 *
 */
public class ReadWriteDemo {

	public static void main(String[] args) {
		
		Mycache mycache = new Mycache();
		
		for (int i = 0; i < 5; i++) {
			
			final int temp = i;
			 new Thread(()->{
				    try {
				    	mycache.put(temp+"", temp+"");
					} catch (Exception e) {
						e.printStackTrace();
					}
			   },String.valueOf(i)).start();
		}
		
		for (int i = 0; i < 5; i++) {
			final int temp = i;
			 new Thread(()->{
				    try {
				    	mycache.get(temp + "");
					} catch (Exception e) {
						e.printStackTrace();
					}
			   },String.valueOf(i)).start();
		}
	}
}

class Mycache {  //资源类
	
	private volatile Map<String, Object> map = new HashMap<String, Object>();
	
//	private Lock lock = new ReentrantLock(); //此锁读和写同时独占 ，违背高并发的初衷
	
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();  //读写锁
	
	
	public void put(String  key, Object value) {
		
		try {
			readWriteLock.writeLock().lock();
			System.out.println(Thread.currentThread().getName() + "\t正在写入 " + key);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + "\t写入完成");
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}
	
	public void get(String key) {
		
		try {
			readWriteLock.readLock().lock();
			System.out.println(Thread.currentThread().getName() + "\t开始读取");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Object object = map.get(key);
			System.out.println(Thread.currentThread().getName() + "\t读取完成  " + object);
		} finally {
			readWriteLock.readLock().unlock();
		}
	}
	
}
