package com.wangjinyin.study191230;

/**
 * 懒汉式（线程安全）
 * 这种方式采用类加载机制保证该类只能被初始化一次，
 * //3.写一个静态内部类  当类装载的时候并不会装载SingletonInstance类保证了懒加载
	private static class SingletonInstance{
		private final static TestSingleton7 instace = new TestSingleton7();
	}
	
	静态内部类在TestSingleton7类被装载的时候并不会立即被实例化，在调用getInstance()方法时才被实例化，
	jvm帮助我们保证了线程安全
	结论：延迟加载，线程安全 推荐使用
 */

public class TestSingleton7 {
	
	public static void main(String[] args) {
		TestSingleton7 instance1 = TestSingleton7.getInstance();
		TestSingleton7 instance2 = TestSingleton7.getInstance();
		
		System.out.println(instance1 == instance2); //true
		System.out.println(instance1.hashCode() + " " + instance2.hashCode());
		
	}
	
	//1.在内部创建实例  在类加载的时候创建内部实例  volatile保证内存可见
		private  static volatile TestSingleton7 testSingleton1;
		
	//2.构造器私有化  防止外部通过new来创建对象实例
	private TestSingleton7() {
		
	}
	
	//3.写一个静态内部类  当类装载的时候并不会装载SingletonInstance类保证了懒加载
	private static class SingletonInstance{
		private final static TestSingleton7 instace = new TestSingleton7();
	}
	
	//4.对外提供方法返回  当使用到该实例时才创建该对象  synchronized进行线程同步
	public static  TestSingleton7 getInstance() {

		return SingletonInstance.instace;  //这时会装载SingletonInstance 类装载保证了线程安全
	}
}
