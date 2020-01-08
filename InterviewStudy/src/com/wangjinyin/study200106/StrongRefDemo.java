package com.wangjinyin.study200106;

/**
 * 强应用Demo
 * @author wang
 *
 */
public class StrongRefDemo {
	
	public static void main(String[] args) {
		Object obj1 = new Object();  //默认为强引用对象
		Object obj2 = obj1;          //obj2引用赋值
		obj1 = null;                 //置空
		System.gc();
		System.out.println(obj2);
	}
}
