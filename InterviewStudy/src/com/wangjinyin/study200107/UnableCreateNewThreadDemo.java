package com.wangjinyin.study200107;

import java.util.concurrent.TimeUnit;

/***
 * OOM 之unable create new thread
 * @author wang
 *
 */
public class UnableCreateNewThreadDemo {
	
	public static void main(String[] args) {
		for (int i = 0; ; i++) {
			new Thread(()->{
				try {
					TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			},String.valueOf(i)).start();;
		}
	}
 }
