package com.nio;

public class SingleTon {
	  //构造器私有化
	private SingleTon() {
	}
	private static SingleTon instance=null;
	
	public static SingleTon getInstance(){
		return instance;
	}
}
