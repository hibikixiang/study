package com.wx.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;

public class Demo4 implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("serial")
	class Person implements Serializable {
		private String name;
		private int age;
		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}
	}
	/**
	 * 实例1：对象的序列化 
	 * TODO
	 * 2015/11/10 上午9:04:16, F3859385
	 */
	@Test
	public void serializableObject() {
		String path = "src/copyFile/lrc.txt";
		Person p1 = new Person("zhangsan", 12);
		Person p2 = new Person("lisi", 15);
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(p1);
			oos.writeObject(p2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 实例2：对象的反序列化 
	 * TODO
	 * 2015/11/10 上午9:14:08, F3859385
	 */
	@Test
	public void unSerializable() {
		String path = "src/copyFile/lrc.txt";
		//好吧，这里代码写得着实有点长了，还要抛异常什么的
		//如果你也看的烦，那就在主方法上抛吧，构造方法里用匿名对象就好了
		//什么？别告诉我你不知道匿名对象 
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(path);
			ois = new ObjectInputStream(fis);
			//这里返回的其实是一个Object类对象
			//因为我们已知它是个Person类对象
			//所以，就地把它给向下转型了 
			Person p = (Person) ois.readObject();
			
			System.out.println(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
