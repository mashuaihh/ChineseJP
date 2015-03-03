package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchUpdateInfo {
	private static Connection conn = null;
	private static Statement st = null;
	private ArrayList<String> infoList = new ArrayList<String>();
	private String nation = null;
	private String textId = null;
	
	public SearchUpdateInfo(String ch_id, String jp_id) {
		conn = new NewConnect().getConnection();
		if (ch_id == null) 
			jpSearch(jp_id);
		else
			chSearch(ch_id);
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void jpSearch(String id) {
		try {
			st = (Statement) conn.createStatement();
			String query = "SELECT * FROM jp WHERE jp_id = " + id;
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				// jp_id, jp_text, author, publisher, pub_date, trans
				this.infoList.add(rs.getString("jp_id"));
				this.infoList.add(rs.getString("jp_text"));
				this.infoList.add(rs.getString("author"));
				this.infoList.add(rs.getString("publisher"));
				this.infoList.add(rs.getString("pub_date"));
				this.infoList.add(rs.getString("trans"));
			}
			this.nation = "jp";
			this.textId = id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void chSearch(String id) {
		try {
			st = (Statement) conn.createStatement();
			String query = "SELECT * FROM ch WHERE ch_id = " + id;
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				// ch_id, ch_text, author, publisher, pub_date, trans
				this.infoList.add(rs.getString("ch_id"));
				this.infoList.add(rs.getString("ch_text"));
				this.infoList.add(rs.getString("author"));
				this.infoList.add(rs.getString("publisher"));
				this.infoList.add(rs.getString("pub_date"));
				this.infoList.add(rs.getString("trans"));
			}
			this.nation = "ch";
			this.textId = id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getInfoList() {
		return this.infoList;
	}
	
	public String getNation() {
		return this.nation;
	}
	
	public String getTextId() {
		return this.textId;
	}
}
