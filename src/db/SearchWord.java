package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchWord {
	static Connection conn;
	static Statement st;
	final int loginYes = 1;
	final int loginNo = 0;
	final int allowedNumber = 10;
	
	private ArrayList<String> chs = new ArrayList<String>();
	private ArrayList<String> jps = new ArrayList<String>();
	
	public SearchWord(String keyword, String language, Integer status) {
		conn = new MyConnect().getConnection();
		String subquery1 = "%" + keyword + "%";
		String query = "SELECT ch, jp FROM duiyi WHERE " + language + " LIKE ?";
		
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, subquery1);
			ResultSet rs = st.executeQuery();
			
			if (status.equals(loginNo)) {
				int i = 0;
				while (rs.next() && i < allowedNumber) {
					this.chs.add(rs.getString("ch"));
					this.jps.add(rs.getString("jp"));
					i++;
				}
			}
			else {
				while (rs.next()) {
					this.chs.add(rs.getString("ch"));
					this.jps.add(rs.getString("jp"));
				}
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getChs() {
		return this.chs;
	}
	
	public ArrayList<String> getJps() {
		return this.jps;
	}
}
