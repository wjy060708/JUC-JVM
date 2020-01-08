package com.wangjinyin.study191230;

/**
 * 懒汉式（线程安全）
 *   采用synchronized进行线程同步 因而来保证线程安全
 *   但由于synchronized是重量级同步锁，效率低下 每次执行getInstance方法都要进行同步，而其实该方法执行一次就够了，后面想获得该实例直接return就可以
 *   不推荐使用
 */

public class TestSingleton4 {
	
	public static void main(String[] args) {
		TestSingleton4 instance1 = TestSingleton4.getInstance();
		TestSingleton4 instance2 = TestSingleton4.getInstance();
		
		System.out.println(instance1 == instance2); //true
		System.out.println(instance1.hashCode() + " " + instance2.hashCode());
		
	}
	
	//1.构造器私有化  防止外部通过new来创建对象实例
	private TestSingleton4() {
		
	}
	
	//2.在内部创建实例  在类加载的时候创建内部实例
	private  static TestSingleton4 testSingleton1;

	//3.对外提供方法返回  当使用到该实例时才创建该对象  synchronized进行线程同步
	public static synchronized TestSingleton4 getInstance() {
		
		if (testSingleton1 == null) {
			testSingleton1 = new TestSingleton4();
		}
		
		return testSingleton1;
	}
}
