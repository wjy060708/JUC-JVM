package com.wangjinyin.study200107;

import java.util.ArrayList;
import java.util.List;

public class GcOverHeadDemo {
	
	public static void main(String[] args) {
		int i = 0;
		List<String> list = new ArrayList<String>();
		
		try {
			while (true) {
				list.add(String.valueOf(++ i).intern());
			}
			
			
		} catch (Throwable e) {
			System.out.println(i);
			e.printStackTrace();
		}
	}

}
