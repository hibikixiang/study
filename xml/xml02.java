package com.wx.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class Demo2 {

	@Test
	public void test() throws Exception {
		// 创建saxreader对象 
		SAXReader reader = new SAXReader();
		// 读取一个文件，把这个文件转换成Document对象
		Document document = reader.read(new File("src//c.xml"));
		// 获取根元素
		Element root = document.getRootElement();
		// 把文档转换字符串 
		String docXmlText = document.asXML();
		System.out.println(docXmlText);
		System.out.println("-------------------------");
		// csdn元素标签根转换的内容 
		String rootXmlText = root.asXML();
		System.out.println(rootXmlText);
		System.out.println("-------------------------");
		// 获取java元素标签 内的内容
		Element e = root.element("java");
		System.out.println(e.asXML());
	}
	 /** 
     * 创建一个document对象 往document对象中添加节点元素 转存为xml文件 
     *  
     * @throws Exception 
     */  
	@Test
	public void test2() throws Exception {
		Document document = DocumentHelper.createDocument();// 创建根节点
		Element root = document.addElement("csdn");
		Element java = root.addElement("java");
		java.setText("java班");
		Element ios = root.addElement("ios");
		ios.setText("ios班");
		
		writer(document);
	}
	private void writer(Document document) throws Exception{
		// 紧凑的格式  
//		OutputFormat format = OutputFormat.createCompactFormat();
		// 排版缩进的格式  
		OutputFormat format = OutputFormat.createPrettyPrint();
		
		format.setEncoding("UTF-8");
		// 创建XMLWriter对象,指定了写出文件及编码格式  
		//XMLWriter writer = new XMLWriter(new FileWriter(new File("src//a.xml"),"UTF-8"), format);
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(new File("src//c.xml")), "UTF-8"), format);
		// 写入
		writer.write(document);
		// 立即写入
		writer.flush();
		// 关闭操作
		writer.close();
		
	}
	 /** 
     * 把一个文本字符串转换Document对象 
     *  
     * @throws Exception 
     */  
	@Test
	public void test1() throws Exception {
		String text = "<csdn><java>java班</java><net>Net班</net></csdn>";
		Document document = DocumentHelper.parseText(text);
		Element element = document.getRootElement();
		System.out.println(element.getName());
		writer(document);
	}
}
