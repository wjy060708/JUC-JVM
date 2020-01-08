package com.wangjinyin.study200104;

/**
 * @author 汪进银
 */
public class HelloGC {

	public static void main(String[] args) {
	
		System.out.println("HelloGC");
		
//		byte[] byteArray = new byte[50 * 1024* 1024 ];
		
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
