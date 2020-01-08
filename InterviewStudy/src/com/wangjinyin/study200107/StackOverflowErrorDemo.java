package com.wangjinyin.study200107;

/**
 *   错误
 * @author wang
 *
 */
public class StackOverflowErrorDemo {
	
	public static void main(String[] args) {
		stackOverflow();
	}

	private static void stackOverflow() {
		stackOverflow();
	}
}
