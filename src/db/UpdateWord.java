package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateWord {
	private static Connection conn = null;
	private static Statement st = null;
	
	private String nation = null;
	private String text_id = null;
	private String text = null;
	private String author = null;
	private String publisher = null;
	private String pub_date = null;
	private String trans = null;
	
	public UpdateWord(String nation, String text_id, String text, String author,
			String publisher, String pub_date, String trans) {
		this.nation = nation;
		this.author = author;
		this.text = text;
		this.text_id = text_id;
		this.author = author;
		this.publisher = publisher;
		this.pub_date = pub_date;
		this.trans = trans;
		
		conn = new NewConnect().getConnection();
		
		if (this.nation.equals("ch")) 
			chUpdate();
		else
			jpUpdate();
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void chUpdate() {
		try {
			st = (Statement) conn.createStatement();
			String updateStr = "UPDATE ch SET ch_text = '" + this.text + "' ," +
					"author = '" + this.author + "' ," +
					"publisher = '" + this.publisher + "' ," +
					"pub_date = '" + this.pub_date + "' ," +
					"trans = '" + this.trans + "'" + 
					"WHERE ch_id = " + this.text_id;
			st.executeUpdate(updateStr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void jpUpdate() {
		try {
			st = (Statement) conn.createStatement();
			String updateStr = "UPDATE jp SET jp_text = '" + this.text + "' ," +
										"author = '" + this.author + "' ," +
										"publisher = '" + this.publisher + "' ," +
										"pub_date = '" + this.pub_date + "' ," +
										"trans = '" + this.trans + "'" + 
										"WHERE jp_id = " + this.text_id;
			st.executeUpdate(updateStr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
