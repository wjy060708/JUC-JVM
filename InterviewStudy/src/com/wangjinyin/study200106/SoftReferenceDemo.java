package com.wangjinyin.study200106;

import java.lang.ref.SoftReference;

/**
 * 软引用Demo
 * @author wang
 *
 */
public class SoftReferenceDemo {

	//内存够用不回收
	public static void softRef_Memory_Enough() {
		Object obj1 = new Object();
		SoftReference<Object> obj2 = new SoftReference<Object>(obj1);
		
		System.out.println(obj1);
		System.out.println(obj2.get());
		
		obj1 = null;
		System.gc();  //手动进行垃圾回收
		
		System.out.println(obj1);
		System.out.println(obj2.get());
	}
	
	//故意产生 大对象配置小内存  -Xms5m -Xmx5m -XX:+PrintGCDetails
    public static void softRef_Memory_NotEnough() {
    	Object obj1 = new Object();
		SoftReference<Object> obj2 = new SoftReference<Object>(obj1);
		
		System.out.println(obj1);
		System.out.println(obj2.get());
		
		obj1 = null;
		System.gc();  //手动进行垃圾回收
		
		try {
			byte[] byteArray = new byte[30 * 1024 * 1024 ];
		} finally {
			System.out.println(obj1);
			System.out.println(obj2.get());
		}
	}
    
    public static void main(String[] args) {
    	//softRef_Memory_Enough();
    	softRef_Memory_NotEnough();
	}
}
