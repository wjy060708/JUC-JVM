package com.wangjinyin.study191230;

/**
 * 懒汉式（线程安全）
 *    同步代码块：这种方法想解决因使用synchronized加类锁带来的效率低下问题
 *    然而该方法并不能保证线程安全 原因跟实例3一样
 *    if (testSingleton1 == null) {
			
			synchronized(TestSingleton5.class) {
			  testSingleton1 = new TestSingleton5();
			}
	  }
 *    不推荐使用
 */

public class TestSingleton5 {
	
	public static void main(String[] args) {
		TestSingleton5 instance1 = TestSingleton5.getInstance();
		TestSingleton5 instance2 = TestSingleton5.getInstance();
		
		System.out.println(instance1 == instance2); //true
		System.out.println(instance1.hashCode() + " " + instance2.hashCode());
		
	}
	
	//1.构造器私有化  防止外部通过new来创建对象实例
	private TestSingleton5() {
		
	}
	
	//2.在内部创建实例  在类加载的时候创建内部实例
	private  static TestSingleton5 testSingleton1;

	//3.对外提供方法返回  当使用到该实例时才创建该对象  synchronized进行线程同步
	public static  TestSingleton5 getInstance() {
		
		if (testSingleton1 == null) {
			
			synchronized(TestSingleton5.class) {
			  testSingleton1 = new TestSingleton5();
			}
		}
		
		return testSingleton1;
	}
}
