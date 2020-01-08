package com.wangjinyin.study191230;
/**
 * 利用jdk1.5的枚举类型来保证线程安全性
 */

public class TestSingleton8 {
	
	public static void main(String[] args) {
		Singleton instance1 = Singleton.INSTANCE;
		Singleton instance2 = Singleton.INSTANCE;
		
		System.out.println(instance1 == instance2); //true
		System.out.println(instance1.hashCode() + " " + instance2.hashCode());
	}
	
}

//可以实现单例模式
enum Singleton{
	
	INSTANCE;
	 
	public void sayOk() {
		System.out.println("ok");
	}
	
}
