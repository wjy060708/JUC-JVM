package com.wangjinyin.study200107;

import java.util.Random;
/**
 * 
 * @author 
 *
 */
public class JavaHeapSpaceDemo {
	
	public static void main(String[] args) {
		String string = "testData";
		
		while (true) {
			
			string += string + new Random().nextInt(111) + new Random().nextInt(22222);
			string.intern();
		}
	}
}
