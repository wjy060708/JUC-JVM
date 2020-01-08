package com.wangjinyin.study191230;

/**
 * 
 *饿汉模式之静态代码块
 *   类似于：饿汉模式
 *   也是在类加载的时候的调用
 *   
 */

public class TestSingleton2 {
	
	public static void main(String[] args) {
		TestSingleton2 instance1 = TestSingleton2.getInstance();
		TestSingleton2 instance2 = TestSingleton2.getInstance();
		
		System.out.println(instance1 == instance2); //true
		System.out.println(instance1.hashCode() + " " + instance2.hashCode());
		
	}
	
	//1.构造器私有化  防止外部通过new来创建对象实例
	private TestSingleton2() {
		
	}
	
	//2.在内部创建实例  在类加载的时候创建内部实例
	private  static TestSingleton2 testSingleton1;
	
	//将对象的创建放在静态代码块里 
	static {
		testSingleton1 = new TestSingleton2();
	}
	
	//3.对外提供方法返回
	public static TestSingleton2 getInstance() {
		return testSingleton1;
	}
}
