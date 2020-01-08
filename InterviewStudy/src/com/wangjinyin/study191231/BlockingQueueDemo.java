package com.wangjinyin.study191231;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/***
 * 1.队列：先进先出
 * 
 * 2.阻塞队列
 *    1.1阻塞队列有没有好的一面 
 *    1.2不得不阻塞，你如何管理
 *   
 * 3.
 *
 */
public class BlockingQueueDemo {
	
	public static void main(String[] args) throws InterruptedException {
		//List list = new ArrayList();
		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
		
		//过时不候
		blockingQueue.offer("a", 2, TimeUnit.SECONDS);
		blockingQueue.offer("b", 2, TimeUnit.SECONDS);
		blockingQueue.offer("c", 2, TimeUnit.SECONDS);
		
		System.out.println("##############################");
		blockingQueue.offer("d", 2, TimeUnit.SECONDS);
		
		
		//死战不退  阻塞等待 put take
//		try {
//			blockingQueue.put("a");
//			blockingQueue.put("b");
//			blockingQueue.put("c");
//			System.out.println("############################");
//			
//			//阻塞
//			blockingQueue.put("d");
//			
//			System.out.println(blockingQueue.take());
//			System.out.println(blockingQueue.take());
//			System.out.println(blockingQueue.take());
//			
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	
		
		
		//插入失败返回false 返回失败返回null  offer peek poll
//		System.out.println(blockingQueue.offer("a"));
//		System.out.println(blockingQueue.offer("b"));
//		System.out.println(blockingQueue.offer("c"));
//		//System.out.println(blockingQueue.offer("d"));
//		
//		System.out.println(blockingQueue.peek());
//		
//		System.out.println(blockingQueue.poll());
//		System.out.println(blockingQueue.poll());
//		System.out.println(blockingQueue.poll());
//		System.out.println(blockingQueue.poll());
		
		//一言不合报异常  add remove element
//		System.out.println(blockingQueue.add("a"));
//		System.out.println(blockingQueue.add("b"));
//		System.out.println(blockingQueue.add("c"));
//		
//		//查看队首元素是谁
//		System.out.println(blockingQueue.element());
//		
//		System.out.println(blockingQueue.remove());
//		System.out.println(blockingQueue.remove());
//		System.out.println(blockingQueue.remove());
	}

}
