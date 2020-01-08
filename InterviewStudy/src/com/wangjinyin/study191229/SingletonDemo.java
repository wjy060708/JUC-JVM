package com.wangjinyin.study191229;

/**
 * 
 * @author wang
 *
 */
public class SingletonDemo {
	
	private static volatile SingletonDemo instance = null;
	
	//防止
	private SingletonDemo() {
		System.out.println(Thread.currentThread().getName() + "\t 我是构造函数");
	}
	
	//1.第一种 相当于类锁 太重量级
	/*private synchronized static SingletonDemo getInstace() {
		
		if (instance == null) {
			instance = new SingletonDemo();
		}
		return instance;
	}*/
	
	//2.第二种 dcl double check lock 由于数据没有依赖性，允许指令重排，可能导致渠道的对象为空
	//采用volatile限制指令重排
   private  static SingletonDemo getInstace() {
		
		if (instance == null) {
			synchronized (SingletonDemo.class) {
				if (instance == null) {
				  instance = new SingletonDemo();
				}
			}
		}
		return instance;
	}
	
	
	public static void main(String[] args) {
		
		for (int j = 0; j <10; j++) {
			new Thread(()->{
				SingletonDemo.getInstace();
			  },String.valueOf(j)).start();
		}
	} 
}
