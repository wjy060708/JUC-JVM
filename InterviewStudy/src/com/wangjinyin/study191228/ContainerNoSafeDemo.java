package com.wangjinyin.study191228;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程不安全的问题
 * ArrayList
 * @author wang
 *
 */
public class ContainerNoSafeDemo {
	
	public static void main(String[] args) {
		
		//Set<String> set  = new HashSet<String>();  //HashSet底层调用hashMap hashMap的值键为穿进去的值 值为一个恒定的值object
		//Set<String> set  = Collections.synchronizedSet(new HashSet<String>());
		Set<String> set  = new CopyOnWriteArraySet<String>();
		
		for (int i = 0; i < 30; i++) {
			new Thread(()->{
				set.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(set);
			},String.valueOf(i)).start();
		}
	}
	
	//java.util.ConcurrentModificationException
	/**
	 * 1.故障现象 java.util.ConcurrentModificationException
	 * 
	 * 2.导致原因：
	 *           并发争抢导致
	 *         一个人正在写入，另外一个同学过来抢夺，导致数据修改异常
	 *    
	 * 3.解决方案
	 *   
	 *   3.1 Collections.synchronizedSet(new HashSet<String>()); 使用Collections类进行包装
	 *   3.2 juc包  Set<String> set  = new CopyOnWriteArraySet<String>();  写时复制  底层调用synchronizedList
	 *   
	 *   //源码解析
	 *  public void add(int index, E element) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            if (index > len || index < 0)
                throw new IndexOutOfBoundsException("Index: "+index+
                                                    ", Size: "+len);
            Object[] newElements;
            int numMoved = len - index;
            if (numMoved == 0)
                newElements = Arrays.copyOf(elements, len + 1);   //实现拷贝
            else {
                newElements = new Object[len + 1];
                System.arraycopy(elements, 0, newElements, 0, index);
                System.arraycopy(elements, index, newElements, index + 1,
                                 numMoved);
            }
            newElements[index] = element;
            setArray(newElements);    //复制回去
        } finally {
            lock.unlock();
        }
    }
	 * 
	 * 4.优化建议
	 * 
	 */
}
