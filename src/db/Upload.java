package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Upload {
	private Connection conn;
	private Statement st;
	
	public Upload(String[] chs, String[] jps) {
// jps = {jp_text 0 , jp_author 1, jp_trans 2, jp_publisher 3, jp_pubdate 4}
// chs = same as jps
		conn = new NewConnect().getConnection();
		if (jps[0].equals("") && chs[0].equals(""))
			chjpInsert(chs, jps);
		else if (jps[0].equals(""))
			chInsert(chs);
		else 
			jpInsert(jps);
	}
	
	private void jpInsert(String[] jps) {
		try {
			st = (Statement) conn.createStatement();
			String jpSQL = "INSERT INTO jp(jp_text, author, publisher, pub_date, trans) " + 
			"VALUES ('" + 
					jps[0] + "','" +
					jps[1] + "','" +
					jps[2] + "','" +
					jps[3] + "','" +
					jps[4] + "')";
			st.executeUpdate(jpSQL);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void chInsert(String[] chs) {
		try {
			st = (Statement) conn.createStatement();
			String chSQL = "INSERT INTO ch(ch_text, author, publisher, pub_date, trans) " + 
			"VALUES ('" + 
					chs[0] + "','" +
					chs[1] + "','" +
					chs[2] + "','" +
					chs[3] + "','" +
					chs[4] + "')";
			st.executeUpdate(chSQL);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void chjpInsert(String[] chs, String[] jps) {
		try {
			st = (Statement) conn.createStatement();
			String chSQL = "INSERT INTO ch(ch_text, author, publisher, pub_date, trans) " + 
			"VALUES ('" + 
					chs[0] + "','" +
					chs[1] + "','" +
					chs[2] + "','" +
					chs[3] + "','" +
					chs[4] + "')";
			st.executeUpdate(chSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			st = (Statement) conn.createStatement();
			String jpSQL = "INSERT INTO jp(jp_text, author, trans, publisher, pub_date) " + 
			"VALUES ('" + 
					jps[0] + "','" +
					jps[1] + "','" +
					jps[2] + "','" +
					jps[3] + "','" +
					jps[4] + "')";
			st.executeUpdate(jpSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

