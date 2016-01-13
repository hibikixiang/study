package com.wx.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class FileUtil {

	/**
	 * 实例1：字符流的写入 
	 * TODO
	 * 2015/11/9 下午3:00:55, F3859385
	 */
	@Test
	public void inputStreamWrite(){
		String path = "src/file/test.txt";
		//由于IO操作会抛出异常，因此在try语句块的外部定义FileWriter的引用 
		FileWriter w = null;
		try {
			//以path为路径创建一个新的FileWriter对象
			//如果需要追加数据，而不是覆盖，则使用FileWriter（path，true）构造方法 
			w = new FileWriter(path);
			//将字符串写入到流中，\r\n表示换行想有好的 
			w.write("anatanokodogasikidesu\r\n");
			//如果想马上看到写入效果，则需要调用w.flush()方法 
			w.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(w != null) {
				try {
					w.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 实例2：字符流的读取 
	 * TODO
	 * 2015/11/9 下午3:01:06, F3859385
	 */
	@Test
	public void inputStreamRead() {
		String path = "src/file/test.txt";
		FileReader r = null;
		try {
			r = new FileReader(path);
			//方式一：读取单个字符的方式
			//每读取一次，向下移动一个字符单位 
			/*int temp1 = r.read();
			System.out.print((char)temp1);
			int temp2 = r.read();
			System.out.print((char)temp2);*/
			//方式二：循环读取
			//read()方法读到文件末尾会返回-1 
			/*while (true) {
				int temp = r.read();
				if (temp == -1) {
					break;
				}
				System.out.print((char)temp);
			}*/
			//方式三：循环读取的简化操作
			//单个字符读取，当temp不等于-1的时候打印字符 
			/*int temp = 0;
			while ((temp = r.read()) != -1) {
				System.out.print((char)temp);
			}*/
			//方式四：读入到字符数组 
			/*char[] buf = new char[1024];
			int temp = r.read(buf);
			//将数组转化为字符串打印,后面参数的意思是
			//如果字符数组未满，转化成字符串打印后尾部也许会出现其他字符
			//因此，读取的字符有多少个，就转化多少为字符串 
			System.out.print(new String(buf,0,temp));*/
			
			//方式五：读入到字符数组的优化
			//由于有时候文件太大，无法确定需要定义的数组大小
			//因此一般定义数组长度为1024，采用循环的方式读入 
			char[] buf = new char[1024];
			int temp = 0;
			while ((temp = r.read(buf)) != -1) {
				System.out.print(new String(buf,0,temp));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(r != null) {
				try {
					r.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 实例3：文本文件的复制 
	 * TODO
	 * 2015/11/9 下午3:01:17, F3859385
	 */
	@Test
	public void copyText() {
		String doc = "src/file/test.txt";
		String copy = "src/file/lrc.txt";
		
		FileReader r = null;
		FileWriter w = null;
		
		try {
			r = new FileReader(doc);
			w = new FileWriter(copy);
			//方式一：单个字符写入 
			int temp = 0;
			while ((temp = r.read()) != -1) {
				w.write(temp);
			}
			//方式二：字符数组方式写入 
			/*char[] buf = new char[1024];
			int temp = 0;
			while ((temp = r.read(buf)) != -1) {
				w.write(new String(buf,0,temp));
			}	*/		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(r != null) {
				try {
					r.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(w != null) {
				try {
					w.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * 实例4：利用字符流的缓冲区来进行文本文件的复制 
	 * TODO
	 * 2015/11/9 下午3:01:30, F3859385
	 */
	@Test
	public void bufferCopyText(){
		String doc = "src/file/test.txt";
		String copy = "src/file/lrc.txt";
		
		FileReader r = null;
		FileWriter w = null;
		//创建缓冲区的引用 
		BufferedReader br = null;
		BufferedWriter bw = null;		
		try {
			r = new FileReader(doc);
			w = new FileWriter(copy);
			//创建缓冲区对象
			//将需要提高效率的FileReader和FileWriter对象放入其构造函数内
			//当然，也可以使用匿名对象的方式 br = new BufferedReader(new FileReader(doc));
			br = new BufferedReader(r);
			bw = new BufferedWriter(w);
			String line = null;
			//读取行，直到返回null
			//readLine()方法只返回换行符之前的数据 
			while ((line = br.readLine()) != null) {
				bw.write(line);
				//写完文件内容之后换行
				//newLine()方法依据平台而定
				//windows下的换行是\r\n
				//Linux下则是\n 
				bw.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					r.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 实例5：字节流的写入 
	 * TODO
	 * 2015/11/9 下午3:18:35, F3859385
	 */
	@Test
	public void fileOutputStreamWrite() {
		String path = "src/file/demo.txt";
		
		FileOutputStream o = null;
		String str = "发生的发生地方";
		
		try {
			o = new FileOutputStream(path);
			byte[] buf = str.getBytes();
			o.write(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (o != null) {
				try {
					o.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * 实例6：字节流的读取 
	 * TODO
	 * 2015/11/9 下午3:11:11, F3859385
	 */
	@Test
	public void fileInputStreamRead() {
		String path = "src/file/demo.txt";
		
		FileInputStream i = null;
		
		try {
			i = new FileInputStream(path);
			//方式一：单个字符读取
			//需要注意的是，此处我用英文文本测试效果良好
			//但中文就悲剧了，不过下面两个方法效果良好 
			/*int ch = 0;
			while ((ch = i.read()) != -1) {
				System.out.print((char)ch);
			}*/
			//方式二：数组循环读取 
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = i.read(buf)) != -1) {
				System.out.println(new String(buf,0,len));
			}
			//方式三：标准大小的数组读取 
			/*byte[] buf = new byte[i.available()];
			i.read(buf);
			System.out.println(new String(buf));*/
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (i != null) {
				try {
					i.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 实例7：二进制文件的复制 
	 * TODO
	 * 2015/11/9 下午3:27:54, F3859385
	 */
	@Test
	public void byteFileCopy() {
		String bin = "src/file/demo.txt";
		String copy = "src/copyFile/demo.txt";
		FileInputStream i = null;
		FileOutputStream o = null;
		
		try {
			i = new FileInputStream(bin);
			o = new FileOutputStream(copy);
			//循环的方式读入写出文件，从而完成复制 
			byte[] buf = new byte[1024];
			int temp = 0;
			while ((temp = i.read(buf)) != -1) {
				o.write(buf,0,temp);
			}
 		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (i != null) {
				try {
					i.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (o != null) {
				try {
					o.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 实例8：利用字节流的缓冲区进行二进制文件的复制 
	 * TODO
	 * 2015/11/9 下午3:36:08, F3859385
	 */
	@Test
	public void byteFileCopyByBuffer() {
		String bin = "src/file/lrc.txt";
		String copy = "src/copyFile/lrc.txt";
		
		FileInputStream i = null;
		FileOutputStream o = null;
		
		BufferedInputStream bi = null;
		BufferedOutputStream bo = null;
		
		try {
			i = new FileInputStream(bin);
			o = new FileOutputStream(copy);
			
			bi = new BufferedInputStream(i);
			bo = new BufferedOutputStream(o);
			
			byte[] buf = new byte[1024];
			int temp = 0;
			while ((temp = bi.read(buf)) != -1) {
				bo.write(buf,0,temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(bi != null) {
				try {
					bi.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bo != null) {
				try {
					bo.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
