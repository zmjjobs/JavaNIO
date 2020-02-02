package com.isoftstone.test;

import java.nio.ByteBuffer;

import org.junit.Test;

public class TestBuffer {
	@Test
	public void test1(){
		//1.����һ��ָ����С�Ļ�����
		ByteBuffer buf = ByteBuffer.allocate(1024);
		System.out.println("*******allocate()********");
		printBuf(buf);
		
		//2.�������ݵ�������
		buf.put("abcde".getBytes());
		System.out.println("*******put()********");
		printBuf(buf);
		
		//3.�л�����ȡģʽ
		buf.flip();
		System.out.println("*******flip()********");
		printBuf(buf);
		
		//4.��ȡ����
		byte[] dst = new byte[buf.limit()];
		buf.get(dst);
		System.out.println("*******get()********");
		System.out.println(new String(dst,0,dst.length));
		printBuf(buf);
		
		//5.���ظ�������
		buf.rewind();
		System.out.println("*******rewind()********");
		printBuf(buf);
		
		//6. ��ջ�����,����������Ȼ���ڣ����ݴ��ڱ�����״̬
		buf.clear();
		System.out.println("******clear()********");
		printBuf(buf);
		System.out.println((char)buf.get());//������ӡ a
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
		//mark() ���һ��
		buf.mark();
		buf.get(dst,2,2);
		System.out.println(new String(dst,2,2));
		System.out.println(buf.position()); // 4
		//reset() �ָ���mark��λ��
		buf.reset();
		System.out.println(buf.position()); // 2
		
		//�鿴�Ƿ��пɲ���������
		if (buf.hasRemaining()) {
			//��ʾ�ɲ������ݵ�����
			System.out.println(buf.remaining()); // 3
		}
	}
	
	private void printBuf(ByteBuffer buf){
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
	}
}
