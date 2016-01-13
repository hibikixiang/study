package com.wx.file;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.junit.Test;

public class Demo5 {
	//实现Runnable接口，实现一个读的线程 
	class Read implements Runnable {
		
		private PipedInputStream in; 
		//将需要读的管道流传入到构造函数中 
		public Read(PipedInputStream in) {
			this.in = in;
		}

		@Override
		public void run() {
			try {
				byte[] buf = new byte[1024];
				int temp = 0;
				//循环读取
				//read是一个阻塞方法，需要抛异常
				//此处把打印流的代码也加入进来
				//是因为如果没有读取到数据，那么打印的代码也无效 
				while ((temp = in.read(buf)) != -1) {
					String str = new String(buf,0,temp);
					System.out.println(str);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	class Write implements Runnable {

		private PipedOutputStream out;
		
		//将管道输入流传进来 
		public Write(PipedOutputStream out) {
			this.out = out;
		}


		@Override
		public void run() {
			try {
				//这里开始写出数据 
				out.write("管道输出".getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	/**
	 * 实例3：线程的通信 
	 * TODO
	 * 2015/11/10 上午9:34:03, F3859385
	 */
	@Test
	public void pipedOutput() {
		PipedInputStream in = new PipedInputStream();
		PipedOutputStream out = new PipedOutputStream();
		
		try {
			//连接管道 
			in.connect(out);
			//创建对象，开启线程
			//此处同样放进try...catch里面
			//因为如果没有链接管道，下面操作无意义 
			Read read = new Read(in);
			Write write = new Write(out);
			//把已经实现好run方法的对象放入线程中执行 
			new Thread(read).start();
			new Thread(write).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 实例4：基本数据类型的写入 
	 * TODO
	 * 2015/11/10 上午9:39:42, F3859385
	 */
	@Test
	public void basicDataWrite() {
		String path = "src/copyFile/demo.txt";
		DataOutputStream dos = null;
		
		try {
			//此处需要传入一个OutputStream类的对象 
			dos = new DataOutputStream(new FileOutputStream(path));
			//开始写入基本数据类型 
			dos.writeInt(12);
			dos.writeBoolean(true);
			dos.writeDouble(23.33333);
			dos.writeChar(97);
			//刷新流 
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 实例5：基本数据类型的读取 
	 * TODO
	 * 2015/11/10 上午9:42:23, F3859385
	 */
	@Test
	public void basicDataRead() {
		String path = "src/copyFile/demo.txt";
		DataInputStream dis = null;
		
		try {
			dis = new DataInputStream(new FileInputStream(path));
			//按存储顺序读取基本数据类型 
			System.out.println(dis.readInt());
			System.out.println(dis.readBoolean());
			System.out.println(dis.readDouble());
			System.out.println(dis.readChar());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
