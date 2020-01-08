package com.wangjinyin.study200108;

/**
 * 
 * @author wang
 *
 */
public class HelloGC {
	
	public static void main(String[] args) {
		
		System.out.println("hello gc");
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
