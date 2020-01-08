package com.wangjinyin.study200102;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
 * 出现了Runnable接口，为什么会还需要callable接口
 * 返回的结果建议放到最后，如果线程执行时间长，会导致主线程出现阻塞的情况，违背初衷
 */
class Mythread implements Runnable{

	@Override
	public void run() {
		
	}
}

//有返回值 可以抛出异常
class Mythread2 implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
	    System.out.println("************************come in callable");
		return 1024;
	}
}

public class CallableDemo {
	
   public static void main(String[] args) throws InterruptedException, ExecutionException {
	   
	   FutureTask fu = new FutureTask<Integer>(new Mythread2());
	   
	   Thread thread = new Thread(fu,"AA");
	   Thread thread2 = new Thread(fu,"BB");
	   thread.start();
	   thread2.start();
	   
//	   while (!fu.isDone()) {
//		
//	   }
	   Integer result = (Integer) fu.get(); //建议放到最后，避免阻塞主线程
  }
}
