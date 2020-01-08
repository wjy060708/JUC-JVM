package com.wangjinyin.study191230;

/**
 * 懒汉式（线程安全）
 *   double check + volatile关键字保证内存的可见性    以及禁止指令重排
 *   实际开发中建议使用
 *   
 *   线程安全：懒加载
 */

public class TestSingleton6 {
	
	public static void main(String[] args) {
		TestSingleton6 instance1 = TestSingleton6.getInstance();
		TestSingleton6 instance2 = TestSingleton6.getInstance();
		
		System.out.println(instance1 == instance2); //true
		System.out.println(instance1.hashCode() + " " + instance2.hashCode());
		
	}
	
	//1.构造器私有化  防止外部通过new来创建对象实例
	private TestSingleton6() {
		
	}
	
	//2.在内部创建实例  在类加载的时候创建内部实例  volatile保证内存可见
	private  static volatile TestSingleton6 testSingleton1;

	//3.对外提供方法返回  当使用到该实例时才创建该对象  synchronized进行线程同步
	public static  TestSingleton6 getInstance() {
		
		if (testSingleton1 == null) {
			
			synchronized(TestSingleton6.class) {
				if (testSingleton1 == null) {
			       testSingleton1 = new TestSingleton6();
				}
			}
		}
		
		return testSingleton1;
	}
}
