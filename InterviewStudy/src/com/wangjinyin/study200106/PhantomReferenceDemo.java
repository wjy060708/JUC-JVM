package com.wangjinyin.study200106;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/***
 * 虚引用
 * @author wang
 *
 */
public class PhantomReferenceDemo {
	
	public static void main(String[] args) {
		Object obj1 = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
		
		//在gc后将obj1对象放到referenceQueue 队列中
		PhantomReference<Object> phantomReference = new PhantomReference<Object>(obj1, referenceQueue);
		
		System.out.println(obj1);
		System.out.println(referenceQueue.poll());
		System.out.println(phantomReference.get());
		
		obj1 = null;
		System.gc();
		
		System.out.println(obj1);
		System.out.println(referenceQueue.poll());  //队列中会出现值
		System.out.println(phantomReference.get());
	}
}
