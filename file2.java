package com.wx.file;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class Demo2 {

	/**
	 * 实例1：创建文件对象 
	 * TODO
	 * 2015/11/9 下午4:29:47, F3859385
	 */
	@Test
	public void createFileObject(){
		//创建要操作的文件路径和名称
		String path = "src/file/";
		String childPath = "src/file/demo.txt";
		//用父目录和子文件分隔的方式构造File对象
		//也可以写成 new File("/home/siu/work","test.txt"); 
		File f1 = new File(path,"test.txt");
		//使用绝对路径来构造File对象
		//也可以写成new File("/home/siu/work/demo.txt"); 
		File f2 = new File(childPath);
		//创建父目录的文件对象 
		File d = new File(path);
		//使用已有父目录对象和子文件构建新的File对象 
		File f3 = new File(d,"hello.txt");
		
		System.out.println("f1的路径" + f1);
		System.out.println("f2的路径" + f2);
		System.out.println("f3的路径" + f3);
	}
	
	/**
	 * 实例2：创建和删除文件 
	 * TODO
	 * 2015/11/9 下午4:36:17, F3859385
	 */
	@Test
	public void createDelFile() {
		String path = "src/file/hello.txt";
		
		File f = new File(path);
		
		try {
			/*因为创建和删除文件涉及到底层操作，所以有可能会引发异常*/
			//如果创建成功则会返回true
			//如果已存在该文件，则创建不成功，返回flase，别以为会覆盖 
			System.out.println("创建文件：" + f.createNewFile());
			//删除文件，成功返回true，否则返回flase 
			System.out.println("删除文件：" + f.delete());
			//此方法表示在虚拟机退出时删除文件
			//原因在于：程序运行时有可能发生异常造成直接退出
			//清理残余很有必要～！ 
			f.deleteOnExit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 实例3：文件的判断和测试 
	 * TODO
	 * 2015/11/9 下午4:44:41, F3859385
	 */
	@Test
	public void checkTestFile() {
		String path = "src/file/demo.txt";
		
		File f = new File(path);
		
		System.out.println("f是否可执行" + f.canExecute());
		
		System.out.println("f是否存在" + f.exists());
		
		System.out.println("f是否可读" + f.canRead());
		
		System.out.println("f是否可写" + f.canWrite());
		
		System.out.println("f是否绝对路径" + f.isAbsolute());
		
		System.out.println("f是否为标准文件" + f.isFile());
		
		System.out.println("f是否为目录" + f.isDirectory());
		
		System.out.println("f是否隐藏" + f.isHidden());
	}
	/**
	 * 实例4：创建目录 
	 * TODO
	 * 2015/11/9 下午4:49:04, F3859385
	 */
	@Test
	public void createDirectory() {
		String path = "src/file/work";
		//path在此处作为父目录存在 
		File f1 = new File(path,"/abc");
		
		File f2 = new File(path,"/d/e/f/g");
		//创建一个目录 
		System.out.println(f1.mkdir());
		//递归创建目录 
		System.out.println(f2.mkdirs());
	}
	/**
	 * 实例5：获取文件信息
	 * TODO
	 * 2015/11/9 下午4:56:46, F3859385
	 */
	@Test
	public void getFileInformation() {
		String path = "src/file/demo.txt";
		
		File f = new File(path);
		//返回文件的绝对路径
		//此处返回值为String 
		System.out.println("f的绝对路径名：" + f.getAbsolutePath());
		//返回文件的绝对路径
		//此处返回值为File 
		System.out.println("f的绝对路径对象：" + f.getAbsoluteFile());
		//返回文件或目录的名称 
		System.out.println("f的名称" + f.getName());
		//返回文件的相对路径
		//构造函数中封装的是什么路径，就返回什么路径 
		System.out.println("f的路径" + f.getPath());
		//返回父目录的路径
		//如果在构造函数中的路径不是绝对路径，那么此处返回null 
		System.out.println("f的父目录" + f.getParent());
	}
	/**
	 * 实例6：列出文件系统的根目录 
	 * TODO
	 * 2015/11/9 下午5:02:59, F3859385
	 */
	@Test
	public void getFileRoot() {
		//listRoots()是一个静态方法，返回文件数组 
		File[] files = File.listRoots();
		//foreach循环打印File对象 
		for (File file : files) {
			System.out.println(file);
		}
	}
	/**
	 * 实例7：列出目录下的所有文件 
	 * TODO
	 * 2015/11/9 下午5:05:27, F3859385
	 */
	@Test
	public void listAllFile() {
		String path = "src";
		File f = new File(path);
		//方式一：list()
		//返回一个包含指定目录下所有文件名的字符串数组
		//如果不是一个目录则返回null 
		String[] files = f.list();
		for (String file : files) {
			System.out.println(file);
		}
		//方式二：listFiles()
		//返回File数组 
		/*File[] files = f.listFiles();
		for (File file : files) {
			System.out.println(file.getName());
		}*/
	}
	/**
	 * 实例8：递归列出目录下所有文件
	 * TODO
	 * 2015/11/9 下午5:08:29, F3859385
	 */
	@Test
	public void listDirectoryFiles() {
		String path = "src";
		File f = new File(path);
		
		print(f);
	}
	
	public static void print(File f) {
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (File file : files) {
				print(file);
			}
		} else {
			System.out.println(f);
		}
	}

}
