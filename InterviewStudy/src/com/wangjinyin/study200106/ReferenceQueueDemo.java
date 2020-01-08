package com.wangjinyin.study200106;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 
 * @author wang
 *
 */
public class ReferenceQueueDemo {
	
	public static void main(String[] args) {
		Object obj1 = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
		
		WeakReference<Object> obj2 = new WeakReference<Object>(obj1, referenceQueue);
		System.out.println(obj1);
		System.out.println(obj2);
		System.out.println(referenceQueue.poll());
		
		System.out.println("================================");
		
		obj1 = null;
		System.gc();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(obj1);
		System.out.println(obj2);
		System.out.println(referenceQueue.poll());
	}
}
