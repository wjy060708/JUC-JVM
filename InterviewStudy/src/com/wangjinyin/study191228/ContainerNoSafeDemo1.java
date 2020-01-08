package com.wangjinyin.study191228;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程不安全的问题
 * ArrayList
 * @author wang
 *
 */
public class ContainerNoSafeDemo1 {
	
	public static void main(String[] args) {
		//List<String> list = new ArrayList<String>();
		//List<String> list = new Vector();
		//List<String> list = Collections.synchronizedList(new ArrayList<String>());
		List<String> list = new CopyOnWriteArrayList<String>();
		
		for (int i = 0; i < 30; i++) {
			new Thread(()->{
				list.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
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
	 *   3.1 使用Vector
	 *   3.2 List<String> list = Collections.synchronizedList(new ArrayList<String>()); 使用Collections类进行包装
	 *   3.3juc包 copyOnWriterArrayList  写时复制
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
