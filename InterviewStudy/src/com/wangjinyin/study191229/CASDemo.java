package com.wangjinyin.study191229;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.CAS是什么？ compareAndSet
 *              比较交换
 */
public class CASDemo {
	
	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger(5);
		
		//compareAndSwapInt(this, valueOffset, expect, update);
		//从主物理内存读取的值为5，存进工作内存，当操作完成之后，刷新主内存，期望主内存的值为被改变过，如果未改变则刷新内存，如果改变过则放弃此次操作
		System.out.println(atomicInteger.compareAndSet(5, 2019) + ":" + atomicInteger.get());
		
		System.out.println(atomicInteger.compareAndSet(5, 2014) + ":" + atomicInteger.get());
//		atomicInteger.compareAndSet(5, 2019);
		
		//探究unsafe   下面代码相当于i++
		
		/**
            private static final Unsafe unsafe = Unsafe.getUnsafe();
            private static final long valueOffset;

		    static {
		        try {
		            valueOffset = unsafe.objectFieldOffset
		                (AtomicInteger.class.getDeclaredField("value"));
		        } catch (Exception ex) { throw new Error(ex); }
		    }
		
		    private volatile int value;
		    
		    unsafe 是CAS的核心类 由于java无法访问底层系统，通过本地（native）方法来调用    
		             可以操作特定的物理内存，向c指针一样直接操作内存（偏移量   ）
		             
		    valueOffset指得是偏移量
		    
		    cas在不加锁的情况下保证了原子操作，保证了并发性的同时也保证了原子性
		    cas的缺点：1.循环时间开销大
		            2.只能保证一个共享变量的原子性，如果出现多个共享变量，也需要通过加锁来实现原子性
		            3.ABA问题：
		 */
		atomicInteger.getAndIncrement();
		
		/**
		 * ABA问题？ 原子更新引用 -》如何规避ABA问题
		 *    ABA : 狸猫换太子
		 */
		
	}

}
