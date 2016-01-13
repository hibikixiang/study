package com.wx.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.Test;

public class Demo3 {

	/**
	 * 实例1：从键盘读取 
	 * TODO
	 * 2015/11/10 上午8:21:09, F3859385
	 */
	@Test
	public void readByKey() {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入一个整数");
		int i = input.nextInt();
		System.out.println("你输入的整数是：" + i);
	}
	/**
	 * 实例2：从字符串读取 
	 * TODO
	 * 2015/11/10 上午8:23:42, F3859385
	 */
	@Test
	public void readByStr() {
		Scanner input = new Scanner("hello\r\nworld\r\n");
		//循环读取，hasNext()方法和集合框架里面的一样使 
		while (input.hasNext()) {
			//每次读取一行，别的读取方法见API，比较简单 
			String s = input.nextLine();
			System.out.println(s);
		}
	}
	/**
	 * 实例3：从文件读取 
	 * TODO
	 * 2015/11/10 上午8:26:42, F3859385
	 */
	@Test
	public void readByFile() {
		String path = "src/file/demo.txt";
		File f = new File(path);
		Scanner input = null;
		
		try {
			input = new Scanner(f);
			while (input.hasNext()) {
				String s = input.nextLine();
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			input.close();
		}
	}
	/**
	 * 实例4：向文件写入内容 
	 * TODO
	 * 2015/11/10 上午8:32:03, F3859385
	 */
	@Test
	public void writeToFile() {
		String path = "src/copyFile/demo.txt";
		File file = new File(path);
		PrintWriter pw = null;	
		try {
			//此处构造函数还可以传其他对象，具体参考API文档 
			pw = new PrintWriter(file);
			pw.println("如果有一天我回到从前");
			pw.println("回到最原始的我");
			pw.println("你是否会觉得我不错");
			//刷新流 
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
	/**
	 * 实例5：实现PrintWriter的数据追加功能 
	 * TODO
	 * 2015/11/10 上午8:37:47, F3859385
	 */
	@Test
	public void addDataTOFile() {
		String path = "src/copyFile/demo.txt";
		File file = new File(path);
		PrintWriter pw = null;	
		try {
			//利用FileWriter方式构建PrintWriter对象，实现追加 
			pw = new PrintWriter(new FileWriter(file,true));
			pw.println("-----追加数据-----");
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
	/**
	 * 实例6：System类中的写入 
	 * TODO
	 * 2015/11/10 上午8:39:49, F3859385
	 */
	@Test
	public void systemWrite() {
		//别忘了，OutputStream是所有字节写入流的父类 
		OutputStream out = System.out;
		
		try {
			//写入数据，只能是数组，所以用getBytes()方法 
			out.write("Hello,world!\r\n".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 实例7：System类中的读取 
	 * TODO
	 * 2015/11/10 上午8:44:39, F3859385
	 */
	@Test
	public void systemRead() {
		//别忘了InputStream是所有字节输入流的父类 
		InputStream in = System.in;
		System.out.println("请输入文字:");
		byte[] buf = new byte[1024];
		int len = 0;
		
		try {
			//将输入的数据保证到数组中，len记录输入的长度 
			len = in.read(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//用字符串的方式打印数组中的数据 
		System.out.println("你输入的是：" + new String(buf,0,len));
	}
	/**
	 * 实例8：利用BufferedReader实现对键盘的读取 
	 * TODO
	 * 2015/11/10 上午8:50:16, F3859385
	 */
	@Test
	public void readKeyByBuffered() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入文本：");
		
		String str;
		try {
			str = br.readLine();
			System.out.println("你输入的是：" + str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//循环读取方式 
		/*while (true) {
			System.out.println("请输入文本：");
			String str = null;
			
			try {
				str = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if ("over".equals(str)) {
				break;
			}
			System.out.println("你输入的是:" + str);
		}
		*/
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
