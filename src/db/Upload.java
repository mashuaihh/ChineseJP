package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Upload {
	private Connection conn;
	private Statement st;
	private int ch_id = 1;
	private int jp_id = 1;
	private int user_id = -1;
	
	public Upload(String[] chs, String[] jps, String user_id) {
// jps = {jp_text 0 , jp_author 1, jp_trans 2, jp_publisher 3, jp_pubdate 4}
// chs = same as jps
		this.user_id = Integer.parseInt(user_id);
		conn = new NewConnect().getConnection();
		if (!jps[0].equals("") && !chs[0].equals(""))
			chjpInsert(chs, jps);
		else if (jps[0].equals(""))
			chInsert(chs);
		else 
			jpInsert(jps);
	}
	
	private void jpInsert(String[] jps) {
		try { //insert new jp text
			st = (Statement) conn.createStatement();
			String jpSQL = "INSERT INTO jp(jp_text, author, publisher, pub_date, trans) " + 
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
		
		try { // find the new insert jp text's id
			st = (Statement) conn.createStatement();
			String query = "SELECT jp_id FROM jp WHERE jp_text = " +
						"'" + jps[0] + "'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String jp_num = rs.getString(1);
				this.jp_id = Integer.parseInt(jp_num);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			st = (Statement) conn.createStatement();
			String jp_insert = "INSERT INTO user_ch_jp " +
							"VALUES (" + this.user_id +
							"," + this.ch_id +
							"," + this.jp_id + ")";
			st.executeUpdate(jp_insert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		try { // insert new ch text
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
		
		try { // find the new insert ch text's id
			st = (Statement) conn.createStatement();
			String query = "SELECT ch_id FROM ch WHERE ch_text = " +
						"'" + chs[0] + "'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String ch_num = rs.getString(1);
				this.ch_id = Integer.parseInt(ch_num);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try { // insert new jp text
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
		
		try { // find the new insert jp text's id
			st = (Statement) conn.createStatement();
			String query = "SELECT jp_id FROM jp WHERE jp_text = " +
						"'" + jps[0] + "'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String jp_num = rs.getString(1);
				this.jp_id = Integer.parseInt(jp_num);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void ChInsertNew(String[] chs) {
		try { // insert new ch text
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
			System.out.println("中文新条目插入失败");
		}
	}
	
	private void JpInsertNew(String[] jps) {
		try { // insert new jp text
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
	}
	
	private void ChIdNew(String[] chs) {
		try { // find the new insert ch text's id
			st = (Statement) conn.createStatement();
			String query = "SELECT ch_id FROM ch WHERE ch_text = " +
						"'" + chs[0] + "'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String ch_num = rs.getString(1);
				this.ch_id = Integer.parseInt(ch_num);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void JpIdNew(String[] jps) {
		try { // find the new insert jp text's id
			st = (Statement) conn.createStatement();
			String query = "SELECT jp_id FROM jp WHERE jp_text = " +
						"'" + jps[0] + "'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String jp_num = rs.getString(1);
				this.jp_id = Integer.parseInt(jp_num);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void ChJpRelateInsert() {
		try {
			st = (Statement) conn.createStatement();
			String jp_insert = "INSERT INTO user_ch_jp " +
							"VALUES (" + this.user_id +
							"," + this.ch_id +
							"," + this.jp_id + ")";
			st.executeUpdate(jp_insert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

