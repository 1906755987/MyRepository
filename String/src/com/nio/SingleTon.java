package com.nio;

public class SingleTon {
	  //������˽�л�
	private SingleTon() {
	}
	private static SingleTon instance=null;
	
	public static SingleTon getInstance(){
		return instance;
	}
}
