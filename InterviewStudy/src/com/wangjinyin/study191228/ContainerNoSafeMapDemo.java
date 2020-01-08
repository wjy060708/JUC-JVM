package com.wangjinyin.study191228;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程不安全的问题
 * ArrayList
 * @author wang
 *
 */
public class ContainerNoSafeMapDemo {
	
	public static void main(String[] args) {
		Map<String, Object> map = new ConcurrentHashMap();// ConcurrentHashMap采用分段锁
		
		
		for (int i = 0; i < 30; i++) {
			new Thread(()->{
				map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
				System.out.println(map);
			},String.valueOf(i)).start();
		}
	}
	
	//java.util.ConcurrentModificationException
	
}
