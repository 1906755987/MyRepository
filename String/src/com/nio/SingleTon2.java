package com.nio;

public class SingleTon2 {
	  //������˽�л�
	private SingleTon2() {
		
	}
	private static SingleTon2 instance=null;
	
	public static SingleTon2 getInstance(){
		//1 2
		if (instance==null) {
		// 1 2
		synchronized (SingleTon2 .class) {

			if (instance==null) {
			instance=new SingleTon2();
		}
		
		
	}
		
	}
		return instance;
	}
	public static void main(String[] args) {
		System.err.println(123);
	}
}
