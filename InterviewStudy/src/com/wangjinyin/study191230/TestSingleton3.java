package com.wangjinyin.study191230;

/**
 * 懒汉式（线程不安全）
 *   起到了懒加载的效果，但只能在单线程情况下使用，
 *    多线程情况会出现线程不安全 
 *      if (testSingleton1 == null) {
			testSingleton1 = new TestSingleton3();
		} 
		一个线程判断通过还没有来的及创建对象此时该对象为实例化，另一个线程也判断成功，会创建多个实例
		在实际开发不能用
 */

public class TestSingleton3 {
	
	public static void main(String[] args) {
		TestSingleton3 instance1 = TestSingleton3.getInstance();
		TestSingleton3 instance2 = TestSingleton3.getInstance();
		
		System.out.println(instance1 == instance2); //true
		System.out.println(instance1.hashCode() + " " + instance2.hashCode());
		
	}
	
	//1.构造器私有化  防止外部通过new来创建对象实例
	private TestSingleton3() {
		
	}
	
	//2.在内部创建实例  在类加载的时候创建内部实例
	private  static TestSingleton3 testSingleton1;

	//3.对外提供方法返回  当使用到该实例时才创建该对象
	public static TestSingleton3 getInstance() {
		
		if (testSingleton1 == null) {
			testSingleton1 = new TestSingleton3();
		}
		
		return testSingleton1;
	}
}
