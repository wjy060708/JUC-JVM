package com.wangjinyin.study191229;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ��֤Volitile��֤ԭ����
 *   ʲô��ԭ���� �����ɷָ� Ҳ��ĳ���߳�����ĳ������ҵ��ʱ���м䲻���Ա��������߱��ָҪôͬʱ�ɹ���Ҫôͬʱʧ��
 *   ԭ���Ͻ��Ϊ2000   ʵ�ʽ��Ϊ17248
 *   
 *   why��i ++ �ᱻ����Ϊ3���ֽ���ָ��  �����д��ʧ
 *   
 *   ��ν��ԭ����
 *   1.��syn�ؼ���
 *   2.ԭ�ӱ���
 *   
 * @author wang
 *
 */
public class ActomicDemo {
	
	public static void main(String[] args) {
		MyActomicData myData = new MyActomicData();
       
	   //����20���߳� ÿ���̼߳�1000��
       for (int i = 0; i < 20; i++) {
    	   new Thread(new Runnable() {
    			@Override
    			public void run() {
    				
                   for (int j = 1; j <= 1000; j++) {
                	   myData.add();
                	   myData.addAutomic();
				  }
    			}
    		},String.valueOf(i)).start();
	  }
       
       //��ӡ���ս�� ������20���̼߳�����  Ϊʲô����2 һ����main�߳�  һ����gc�߳�
      while (Thread.activeCount() > 2) {
		Thread.yield();
	}
      
      System.out.println("���ս����"  + myData.number);
      System.out.println("atomic:" + myData.atomicInteger);
      
}

}
class MyActomicData {
	
	volatile int number = 0;
	
	public void addTo60() {
		this.number = 60;
	}
	
	//��ʱ�Ǽ���volatile�ؼ���
	public  void add() {
		number ++;
	}
	
	AtomicInteger atomicInteger = new AtomicInteger(); //Ĭ��ֵΪ0
	
	public void addAutomic() {
		atomicInteger.getAndIncrement();
	}
}
