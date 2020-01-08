package com.wangjinyin.study200106;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * WeakHashMap Demo
 * @author wang
 *
 */
public class WeakHashMapDemo {
	
	public static void main(String[] args) {
		//myHashMap();
		myWeakHashMap();
	}

	private static void myWeakHashMap() {
		WeakHashMap<Integer, String> map = new WeakHashMap<Integer, String>();
		Integer key = new Integer(1);
		String value = "HashMap";
		
		map.put(key, value);
		System.out.println(map);
		key = null;
		
		System.gc();
		System.out.println(map);
		
	}

	private static void myHashMap() {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		Integer key = new Integer(1);
		String value = "HashMap";
		
		map.put(key, value);
		System.out.println(map);
		key = null;
		
		System.gc();
		System.out.println(map);
	}
}
