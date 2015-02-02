package wordSearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import wordSearch.MyConnect;

public class DB_Chinese {
	static Connection conn;
	static Statement st;

	public String first_try = null;
	private String ch = null;
	private String jp = null;
	private ArrayList<String> chs = new ArrayList<String>();
	private ArrayList<String> jps = new ArrayList<String>();

	public DB_Chinese(String str) {
		Connection conn = new MyConnect().getConnection();
		String subquery1 = "%" + str + "%";
		this.jp = str;
//		String query = "select ch,jp from duiyi where ch like ?";
		String query = "select ch,jp from duiyi where jp like ?";

		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, subquery1);
//			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				this.ch = rs.getString("ch");
//				this.jp = rs.getString("jp");
				this.chs.add(rs.getString("ch"));
				this.jps.add(rs.getString("jp"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getCh() {
		return this.ch;
	}

	public String getJp() {
		return this.jp;
	}
	
	public ArrayList<String> getChs() {
		return this.chs;
	}
	
	public ArrayList<String> getJps() {
		return this.jps;
	}

}
