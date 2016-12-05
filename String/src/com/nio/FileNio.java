package com.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
public class FileNio {
   public static void main(String[] args) throws IOException {
	FileInputStream fin = new FileInputStream("c:/1.txt");
	FileOutputStream fos = new FileOutputStream("c:/test/2.txt");
	//得到文件通道流
	FileChannel fic = fin.getChannel();
	FileChannel foc = fos.getChannel();
	
	//设置缓冲区大小
	ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
	int len=-1;
	while((len=fic.read(byteBuffer))!= -1){
		byteBuffer.flip();
		foc.write(byteBuffer);
		byteBuffer.clear();
	}
	fic.close();
	foc.close();
	fin.close();
	fos.close();
}
}
