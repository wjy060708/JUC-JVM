package com.wangjinyin.study200107;

/**
 * OOM 之 metaSpceSize
 * @author wang
 */
public class MetaSpceSizeDemo {
	
	static class OomTest {
		
	}
	
	public static void main(String[] args) {
		int i = 0; //模拟计数器
		try {
			i ++;
			while (true) {
				new OomTest();
				
			}
		} catch (Throwable e) {
			System.out.println("第多少次发生异常");
			e.printStackTrace();
			
		}
	}
}
