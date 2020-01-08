package com.wangjinyin.study191229;

public class VolitileDemo {

	public static void main(String[] args) {
       MyData myData = new MyData();
       
      new Thread(new Runnable() {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "\t come in");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myData.addTo60();
			
			System.out.println(Thread.currentThread().getName() + "\t update value:" + myData.number);
		}
	}).start();
      
      while (myData.number == 0) {
		
	  }
      
      System.out.println(Thread.currentThread().getName() + "\t end");
    }
}

class MyData {
	
	//volatile修饰的共享变量
	volatile int number = 0;
	
	//多线程
	public void addTo60() {
		this.number = 60;
	}
}
