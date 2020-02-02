package com.isoftstone.test;

import java.nio.ByteBuffer;

import org.junit.Test;

public class TestBuffer {
	@Test
	public void test1(){
		//1.分配一个指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		System.out.println("*******allocate()********");
		printBuf(buf);
		
		//2.存入数据到缓冲区
		buf.put("abcde".getBytes());
		System.out.println("*******put()********");
		printBuf(buf);
		
		//3.切换到读取模式
		buf.flip();
		System.out.println("*******flip()********");
		printBuf(buf);
		
		//4.读取数据
		byte[] dst = new byte[buf.limit()];
		buf.get(dst);
		System.out.println("*******get()********");
		System.out.println(new String(dst,0,dst.length));
		printBuf(buf);
		
		//5.可重复读数据
		buf.rewind();
		System.out.println("*******rewind()********");
		printBuf(buf);
		
		//6. 清空缓冲区,但是数据依然存在，数据处于被遗忘状态
		buf.clear();
		System.out.println("******clear()********");
		printBuf(buf);
		System.out.println((char)buf.get());//这里会打印 a
	}
	@Test
	public void test2(){
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.put("abcde".getBytes());
		buf.flip();
		byte[] dst = new byte[buf.limit()];
		buf.get(dst,0,2);
		System.out.println(new String(dst,0,2));
		System.out.println(buf.position());// 2
		//mark() 标记一下
		buf.mark();
		buf.get(dst,2,2);
		System.out.println(new String(dst,2,2));
		System.out.println(buf.position()); // 4
		//reset() 恢复到mark的位置
		buf.reset();
		System.out.println(buf.position()); // 2
		
		//查看是否还有可操作的数据
		if (buf.hasRemaining()) {
			//显示可操作数据的数量
			System.out.println(buf.remaining()); // 3
		}
	}
	
	private void printBuf(ByteBuffer buf){
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
	}
}
