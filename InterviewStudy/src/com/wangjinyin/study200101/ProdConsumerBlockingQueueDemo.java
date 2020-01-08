package com.wangjinyin.study200101;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volitile/cas/atominInteger/BlockingQueue/线程交互/原子引用
 * @author wang
 * 
 * 第三版本的生产者与消费者  生产一个  消费一个   随时可以叫停
 * 线程 资源 操作类
 */
public class ProdConsumerBlockingQueueDemo {

	public static void main(String[] args) throws Exception {
		MySource mySource = new MySource(new ArrayBlockingQueue<String>(3)); //3表示当前阻塞队列的大小
		
		new Thread(() ->  {
			try {
				mySource.myProduct();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"Prod").start();
		
		new Thread(() ->  {
			try {
				mySource.myConsumer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"Consumer").start();
		
		TimeUnit.SECONDS.sleep(5);
		
		System.out.println("活动结束");
		mySource.stop();
	} 
}

class MySource {
	
	private volatile boolean flag = true; //默认开始 多个线程操作 必须保证原子性
	
    private AtomicInteger atomicInteger = new AtomicInteger();
    
    BlockingQueue<String> blockingQueue = null;

	public MySource(BlockingQueue<String> blockingQueue) {  //面向接口编程  传递接口 而不是实现类
		this.blockingQueue = blockingQueue;
		
		System.out.println(blockingQueue.getClass().getName());
	}
	
	public void myProduct() throws Exception{
		
		String data = null;
		while (flag) {
			data = atomicInteger.incrementAndGet() + "";
			boolean result = blockingQueue.offer(data, 2l, TimeUnit.SECONDS);
			
			if (result) {
				System.out.println(Thread.currentThread().getName() + "\t 插入" + data +  "数据成功" );
			} else {
				System.out.println(Thread.currentThread().getName() + "\t 插入" + data +  "数据失败" );
			}
			
			TimeUnit.SECONDS.sleep(1);
		}
		
		System.out.println(Thread.currentThread().getName() + "\t生产数据完成");
	}
    
	public void myConsumer() throws Exception{
		
	   String result = null;
       while (flag) {
	    //两秒钟消费一个
		 result =  blockingQueue.poll(2l,TimeUnit.SECONDS);
		 
		 if (null == result || result.equalsIgnoreCase("")) {
			 flag = false;  //如果两秒取不到数据 停止消费动作
			 System.out.println(Thread.currentThread().getName() + "\t 超过两秒钟没有取到数据 退出消费" );
			 
			 return;
		 }
		 System.out.println(Thread.currentThread().getName() + "\t 消费成功" );
	   }
	}
	
	public void stop() {
		this.flag = false;
	}
}


