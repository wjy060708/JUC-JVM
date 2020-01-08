package com.wangjinyin.study200107;

import java.nio.ByteBuffer;

/**
 * 
 * @author
 *
 */
public class DirectBufferMemoryDemo {
	
	public static void main(String[] args) {
		//查看jvm可以操作本地内存大小
		//System.out.println("DirectBufferMemory" + (sun.misc.VM.maxDirectMemory() / (double)1024 / 1024));
		
		//ByteBuffer该对象直接在本地内存分配空间
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
	}
}
