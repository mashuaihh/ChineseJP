package tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class UploadExcel {
	private FileInputStream in = null;
	private HSSFWorkbook wb = null;
	private ArrayList<AddTextContent> jpList = new ArrayList<AddTextContent>();
	private ArrayList<AddTextContent> chList = new ArrayList<AddTextContent>();
	private String language = null;
	private String user_id = null;

	/**
	 * Init the class using the excel uploaded by users, initializing the 
	 * HSSFWorkbook this.wb in the class.
	 * @param input FileInputStream get from user uploading
	 * @param lan from the form radio option, only can be "ch" or "jp", indicating
	 * the excel's type (ch_ori or jp_ori)
	 * 
	 */
	public UploadExcel(FileInputStream input) {
		this.in = input;
		try {
			this.wb = new HSSFWorkbook(new POIFSFileSystem(input));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public UploadExcel(FileInputStream input, String lan, String user_id) {
		this.in = input;
		this.language = lan;
		this.user_id = user_id;
		
		try {
			this.wb = new HSSFWorkbook(new POIFSFileSystem(input));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Process the HSSFWorkbook and store the values in AddTextContent. Column 1 and Column 2
	 * are required. If (column1.isEmpty() || column2.isEmpty) == true, then this row will not
	 * be used.
	 * @return true: successful processing, false: bad try, return to upload page asking 
	 * the user to check Excel file and upload again.
	 */
	public Boolean readExcel() {
		Sheet sheet1 = wb.getSheetAt(0);
		String[] column = new String[8];
		for (Row row : sheet1) {
			for (int i = 0; i < 8; i++) {
				Cell cell = row.getCell(i);
				if (cell == null) {
					row.createCell(i);
					cell = row.getCell(i);
				}
				String content = cell.getRichStringCellValue().getString();
				column[i] = content;
			}
			
			//make sure column1 and column2 are not null
			//when column is empty, the returned string is "", not null.
			if (column[0].equals("") || column[1].equals(""))
				continue;
//			excel format
//			1 日译中
//	日文原文，中文译文，日文原作者，翻译者，日文原文出版社，中文译文出版社，日文出版时间，中文出版时间
//			2 中译日
//	中文原文，日文译文，中文原作者，翻译者，中文原文出版社，日文译文出版社，中文出版时间，日文出版时间
			if (this.language.equals("jp")) {
				String jp_text = column[0];
				String ct_text = column[1];
				String jp_author = column[2];
				String ch_trans = column[3];
				String jp_publisher = column[4];
				String ch_publisher = column[5];
				String jp_pub_date = column[6];
				String ch_pub_date = column[7];
				
//				String text, String author, String translator,
//				String publisher, String pub_date, String user_id
				AddTextContent jp_each = new AddTextContent();
				jp_each.setText(jp_text);
				jp_each.setAuthor(jp_author);
				jp_each.setPub_date(jp_pub_date);
				jp_each.setPublisher(jp_publisher);
				jp_each.setUser_id(this.user_id);
				jpList.add(jp_each);
				
				AddTextContent ch_each = new AddTextContent();
				ch_each.setText(ct_text);
				ch_each.setTranslator(ch_trans);
				ch_each.setPub_date(ch_pub_date);
				ch_each.setPublisher(ch_publisher);
				ch_each.setUser_id(this.user_id);
				chList.add(ch_each);
				
			} else {
				String ch_text = column[0];
				String jt_text = column[1];
				String ch_author = column[2];
				String jp_trans = column[3];
				String ch_publisher = column[4];
				String jp_publisher = column[5];
				String ch_pub_date = column[6];
				String jp_pub_date = column[7];
				
				AddTextContent jp_each = new AddTextContent();
				jp_each.setText(jt_text);
				jp_each.setTranslator(jp_trans);
				jp_each.setPub_date(jp_pub_date);
				jp_each.setPublisher(jp_publisher);
				jp_each.setUser_id(this.user_id);
				jpList.add(jp_each);
				
				AddTextContent ch_each = new AddTextContent();
				ch_each.setText(ch_text);
				ch_each.setAuthor(ch_author);
				ch_each.setPub_date(ch_pub_date);
				ch_each.setPublisher(ch_publisher);
				ch_each.setUser_id(this.user_id);
				chList.add(ch_each);
			}
		}
		return true;
	}
	
	public ArrayList<AddTextContent> getChList() {
		return this.chList;
	}
	
	public ArrayList<AddTextContent> getJpList() {
		return this.jpList;
	}
	
	@SuppressWarnings("unused")
	public void show() {
		Sheet sheet1 = wb.getSheetAt(0);
		for (Row row : sheet1) {
			for (Cell cell : row) {
				CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
	            System.out.print(cellRef.formatAsString());
	            System.out.print(" - ");
	            String o = cell.getRichStringCellValue().getString();
                System.out.println("xxxx" + o + "xxxx");
                if (o.equals("")) {
                	System.out.println(" is \" and \"");
                } else if (o == null) {
                	System.out.println(" is null");
                } else {
                	System.out.println("nooo");
                }

	            switch (cell.getCellType()) {
	                case Cell.CELL_TYPE_STRING:
	                	String o1 = cell.getRichStringCellValue().getString();
	                    System.out.println("xxxx" + o1 + "xxxx");
	                    if (o1.equals("")) {
	                    	System.out.println(" is \" and \"");
	                    } else if (o1 == null) {
	                    	System.out.println(" is null");
	                    } else {
	                    	System.out.println("nooo");
	                    }
	                    break;
	                case Cell.CELL_TYPE_NUMERIC:
	                    if (DateUtil.isCellDateFormatted(cell)) {
	                        System.out.println(cell.getDateCellValue());
	                    } else {
	                        System.out.println(cell.getNumericCellValue());
	                    }
	                    break;
	                case Cell.CELL_TYPE_BOOLEAN:
	                    System.out.println(cell.getBooleanCellValue());
	                    break;
	                case Cell.CELL_TYPE_FORMULA:
	                    System.out.println(cell.getCellFormula());
	                    break;
	                default:
	                    System.out.println();
			}
		}
		}
	}
	
	public void showArr() {
		for(AddTextContent c : this.jpList) {
			System.out.println(c.getText());
		}
	}
	
	public static void main(String[] args) {
		File file = new File("F:\\mf.xls");
		try {
			FileInputStream in = new FileInputStream(file);
			UploadExcel ex = new UploadExcel(in, "jp", "1");
			ex.readExcel();
			ex.showArr();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
