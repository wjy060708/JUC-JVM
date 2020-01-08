package com.wangjinyin.studyJUC02;

import java.util.concurrent.atomic.AtomicInteger;

/*
 *i++的操作实际上分为三个步骤: "读-改-写";
 *原子性: 就是"i++"的"读-改-写"是不可分割的三个步骤;
 *原子变量: JDK1.5 以后, java.util.concurrent.atomic包下,提供了常用的原子变量;
 *原子变量中的值,使用 volatile 修饰,保证了内存可见性;
 *CAS(Compare-And-Swap) 算法保证数据的原子性;
 */

public class TestAtomicDemo {

	public static void main(String[] args) {
		AtomicDemo ad = new AtomicDemo();
		
		for (int i = 0; i < 10; i++) {
			new Thread(ad).start();
		}
	}
	
}

class AtomicDemo implements Runnable{
	
//	private volatile int serialNumber = 0;
	
	private AtomicInteger serialNumber = new AtomicInteger(0);

	@Override
	public void run() {
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
		
		System.out.println(getSerialNumber());
	}
	
	public int getSerialNumber(){
		return serialNumber.getAndIncrement();
	}
	
	
}
