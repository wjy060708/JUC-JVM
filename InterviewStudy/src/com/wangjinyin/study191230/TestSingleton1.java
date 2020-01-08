package com.wangjinyin.study191230;

/**
 * 
 *饿汉模式： 多线程安全
 *   优点:写法简单，在类加载的时候进行实例化，避免了线程同步问题
 *   缺点:在类加载的时候完成实例化，没有起到懒加载的目的，如果该类从没与使用过，则造成资源浪费
 */

public class TestSingleton1 {
	
	public static void main(String[] args) {
		TestSingleton1 instance1 = TestSingleton1.getInstance();
		TestSingleton1 instance2 = TestSingleton1.getInstance();
		
		System.out.println(instance1 == instance2); //true
		System.out.println(instance1.hashCode() + " " + instance2.hashCode());
		
	}
	
	//1.构造器私有化  防止外部通过new来创建对象实例
	private TestSingleton1() {
		
	}
	
	//2.在内部创建实例  在类加载的时候创建内部实例
	private final static TestSingleton1 testSingleton1 = new TestSingleton1();
	
	//3.对外提供方法返回
	public static TestSingleton1 getInstance() {
		return testSingleton1;
	}
}
