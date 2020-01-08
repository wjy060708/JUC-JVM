package com.wangjinyin.study200106;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 * @author wang
 *
 */
public class WeakRefDemo {

	public static void main(String[] args) {
		Object obj1 = new Object();
		WeakReference<Object> obj2 = new WeakReference<Object>(obj1);
		
		System.out.println(obj1);
		System.out.println(obj2.get());
		
		obj1 = null;
		System.gc();
		
		try {
			byte[] byteArray = new byte[30 * 1024 * 1024 ];
		} finally {
			System.out.println(obj1);
			System.out.println(obj2.get());
		}
	}
}
