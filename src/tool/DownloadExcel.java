package tool;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import db.SelectRegex;

public class DownloadExcel {
	private ArrayList<JpOriBean> jpOriList;
	private ArrayList<ChOriBean> chOriList;
	private String[] columnJpOri = {
	"日文原文","中文译文","日文原作者","翻译者","日文原文出版社","中文译文出版社","日文出版时间","中文出版时间",
	};
	private String[] columnChOri = {
	"中文原文","日文译文","中文原作者","翻译者","中文原文出版社","日文译文出版社","中文出版时间","日文出版时间",
	};
	private int colNum = 8;
	private Workbook wb;
	
	public DownloadExcel(ArrayList<JpOriBean> jpOriList, 
			ArrayList<ChOriBean> chOriList) {
		this.jpOriList = jpOriList;
		this.chOriList = chOriList;
		
		createExcel();
	}
	
	private void createExcel() {
		Workbook wb = new HSSFWorkbook();  // or new XSSFWorkbook();
	    Sheet jpOriSheet = wb.createSheet("日文原文");
	    Sheet chOriSheet = wb.createSheet("中文原文");
	    
	    Row jpRow0 = jpOriSheet.createRow(0);
	    for (int i = 0; i < columnJpOri.length; i++) {
	    	String each = columnJpOri[i];
	    	Cell cell = jpRow0.createCell(i);
	    	cell.setCellValue(each);
	    }
	    
	    Row chRow0 = chOriSheet.createRow(0);
	    for (int i = 0; i < columnChOri.length; i++) {
	    	String each = columnChOri[i];
	    	Cell cell = chRow0.createCell(i);
	    	cell.setCellValue(each);
	    }
	    
	   for (int i = 0; i < this.jpOriList.size(); i++) {
		   JpOriBean bean = jpOriList.get(i);
		   Row row = jpOriSheet.createRow(i+1);
		   
		   List<String> contentList = new ArrayList<String>();
		   contentList.add(bean.getJp_text());
		   contentList.add(bean.getCt_text());
		   contentList.add(bean.getJp_author());
		   contentList.add(bean.getCt_translator());
		   contentList.add(bean.getJp_publisher());
		   contentList.add(bean.getCt_publisher());
		   contentList.add(bean.getJp_pub_date());
		   contentList.add(bean.getCt_pub_date());
	
		   for (int j = 0; j < this.colNum; j++) {
			   Cell cell = row.createCell(j);
			   String content = contentList.get(j);
			   cell.setCellValue(content);
		   }
	   }

	   for (int i = 0; i < this.chOriList.size(); i++) {
		   ChOriBean bean = chOriList.get(i);
		   Row row = chOriSheet.createRow(i+1);
		   
		   List<String> contentList = new ArrayList<String>();
		   contentList.add(bean.getCh_text());
		   contentList.add(bean.getJt_text());
		   contentList.add(bean.getCh_author());
		   contentList.add(bean.getJt_translator());
		   contentList.add(bean.getCh_publisher());
		   contentList.add(bean.getJt_publisher());
		   contentList.add(bean.getCh_pub_date());
		   contentList.add(bean.getJt_pub_date());
	
		   for (int j = 0; j < this.colNum; j++) {
			   Cell cell = row.createCell(j);
			   String content = contentList.get(j);
			   cell.setCellValue(content);
		   }
	   }
	   this.wb = wb;
	    
//	    try {
//			OutputStream fileOut = new BufferedOutputStream(
//										new FileOutputStream("c://eeeaxasd.xls"));
//			wb.write(fileOut);
//			fileOut.flush();
//			fileOut.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public Workbook getWorkbook() {
		return this.wb;
	}
	
	public static void main(String[] args) {
		String keyword = "";
		String language = "ch";
		Boolean status = true;
		System.out.println("sr before");
		SelectRegex sr = new SelectRegex(keyword, language, status);
		System.out.println("sr after");
		sr.selectAllContent();
		System.out.println("after select");
		ArrayList<JpOriBean> jpOriList = sr.getJpOriList();
		ArrayList<ChOriBean> chOriList = sr.getChOriList();
		
		System.out.println("before downloadExcel");
		DownloadExcel excel = new DownloadExcel(jpOriList, chOriList);
	}
}
