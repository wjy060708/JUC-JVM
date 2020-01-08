package com.wangjinyin.study191229;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题的解决
 * @author wang
 * AtomicStampedReference 带时间戳的原子应用
 */
public class ABADemo {
	
	//原子引用
	static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(100);
	
	//原子时间戳引用
	static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100,1);

	public static void main(String[] args) {
		
		//线程1完成一次ABA
		new Thread(()->{
			atomicReference.compareAndSet(100, 101);  //true
			atomicReference.compareAndSet(101, 100);  //true
		},"t1").start();
		
		new Thread(()->{
			
			try {
				//暂停1秒保证线程1执行了一次ABA
				TimeUnit.SECONDS.sleep(1);
				System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get()); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"t2").start();
		
		//以下是ABA问题的解决方案
		System.out.println("ABA问题的解决");
		
		//线程1完成一次ABA
		new Thread(()->{
			int stampe = atomicStampedReference.getStamp();
			System.out.println(Thread.currentThread().getName() + "第一版本号\t" + stampe );
			
			//暂停一秒
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1); //true

			System.out.println(Thread.currentThread().getName() + "第2版本号\t" + stampe );
			
			atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1); //true

			System.out.println(Thread.currentThread().getName() + "第3版本号\t" + stampe );
		},"t3").start();
		
		new Thread(()->{
			int stampe = atomicStampedReference.getStamp();
			System.out.println(Thread.currentThread().getName() + "版本号\t" + stampe );
			
			//暂停一秒
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(atomicStampedReference.compareAndSet(100, 2019, stampe, stampe + 1) + "\t" + atomicStampedReference.getReference()); //true
		    System.out.println(atomicStampedReference.getStamp());
		},"t4").start();
	}
}
