package application;

import java.io.FileWriter;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Property {
	public void updateFlag(String flg){
		 SAXBuilder sb = new SAXBuilder();
	     Document doc = null;
	     try {
	         doc = sb.build("cfg.xml");
	         Element root = doc.getRootElement();
	         Element fl = root.getChild("flag");
	         fl.setText(flg);
//	         System.out.println("updateFlg:"+flg);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    saveXML(doc);
	}
	public Integer readFlag(){
		SAXBuilder sb = new SAXBuilder();
		Document doc = null;
		String f = null;
		try {
			doc = sb.build("cfg.xml");
			Element root = doc.getRootElement();
			Element bt = root.getChild("flag");
			f = bt.getText();
//			System.out.println("flag:" + f);
		}catch(Exception e){
			e.printStackTrace();
		}
		return Integer.parseInt(f);
	}
	public void updateBreak(String brk){
		SAXBuilder sb = new SAXBuilder();
	     Document doc = null;
	     try {
	         doc = sb.build("cfg.xml");
	         Element root = doc.getRootElement();
	         Element fl = root.getChild("breakTime");
	         fl.setText(brk);
	     } catch (Exception e) {
		        e.printStackTrace();
		 }
		 saveXML(doc);
	}
	public Integer readBreak(){
		SAXBuilder sb = new SAXBuilder();
		Document doc = null;
		String t = null;
		try {
			doc = sb.build("cfg.xml");
			Element root = doc.getRootElement();
			Element bt = root.getChild("breakTime");
			t = bt.getText();
//			System.out.println("breakTime:" + t);
		}catch(Exception e){
			e.printStackTrace();
		}
		return Integer.parseInt(t);
	}
	public Integer get_sharpFlag(){
		SAXBuilder sb = new SAXBuilder();
		Document doc = null;
		String t = null;
		try {
			doc = sb.build("cfg.xml");
			Element root = doc.getRootElement();
			Element bt = root.getChild("sharpFlag");
			t = bt.getText();
//			System.out.println("breakTime:" + t);
		}catch(Exception e){
			e.printStackTrace();
		}
		return Integer.parseInt(t);
	}
	public void set_sharpFlag(String flag){
		SAXBuilder sb = new SAXBuilder();
	     Document doc = null;
	     try {
	         doc = sb.build("cfg.xml");
	         Element root = doc.getRootElement();
	         Element fl = root.getChild("sharpFlag");
	         fl.setText(flag);
	     } catch (Exception e) {
		        e.printStackTrace();
		 }
		 saveXML(doc);
	}
	public Integer get_halfFlag(){
		SAXBuilder sb = new SAXBuilder();
		Document doc = null;
		String t = null;
		try {
			doc = sb.build("cfg.xml");
			Element root = doc.getRootElement();
			Element bt = root.getChild("halfFlag");
			t = bt.getText();
//			System.out.println("breakTime:" + t);
		}catch(Exception e){
			e.printStackTrace();
		}
		return Integer.parseInt(t);
	}
	public void set_halfFlag(String flag){
		SAXBuilder sb = new SAXBuilder();
	     Document doc = null;
	     try {
	         doc = sb.build("cfg.xml");
	         Element root = doc.getRootElement();
	         Element fl = root.getChild("halfFlag");
	         fl.setText(flag);
	     } catch (Exception e) {
		        e.printStackTrace();
		 }
		 saveXML(doc);
	}
	public Integer get_soundFlag(){
		SAXBuilder sb = new SAXBuilder();
		Document doc = null;
		String t = null;
		try {
			doc = sb.build("cfg.xml");
			Element root = doc.getRootElement();
			Element bt = root.getChild("soundFlag");
			t = bt.getText();
//			System.out.println("breakTime:" + t);
		}catch(Exception e){
			e.printStackTrace();
		}
		return Integer.parseInt(t);
	}
	public void set_soundFlag(String flag){
		SAXBuilder sb = new SAXBuilder();
	     Document doc = null;
	     try {
	         doc = sb.build("cfg.xml");
	         Element root = doc.getRootElement();
	         Element fl = root.getChild("soundFlag");
	         fl.setText(flag);
	     } catch (Exception e) {
		        e.printStackTrace();
		 }
		 saveXML(doc);
	}
	public static void saveXML(Document doc) {
         // 将doc对象输出到文件
         try {
             // 创建xml文件输出流
            XMLOutputter xmlopt = new XMLOutputter();
             // 创建文件输出流
            FileWriter writer = new FileWriter("cfg.xml");
            // 指定文档格式
            Format fm = Format.getPrettyFormat();
            // fm.setEncoding("GB2312");
            xmlopt.setFormat(fm);
            // 将doc写入到指定的文件中
            xmlopt.output(doc, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
