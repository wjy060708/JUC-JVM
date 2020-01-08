package com.wangjinyin.study200104;

public class ReferenceCountingGC {
	
	private Object instance=null;
	private static final int _1MB=1024*1024;
	private byte[] bigSize=new byte[1024*1024];//1MB的堆空间
 
	public static void main(String[] args) {
		
		ReferenceCountingGC objA=new ReferenceCountingGC();
		ReferenceCountingGC objB=new ReferenceCountingGC();
		objA.instance=objB;
		objB.instance=objA;
		objA=null;
		objB=null;
		
		System.gc();//调用GC
 
	}
}
