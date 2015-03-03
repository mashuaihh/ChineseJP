package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberWord {
	private static Connection conn;
	private static Statement st;
	private String user_id;
	private ArrayList<Integer> chList = new ArrayList<Integer>();
	private ArrayList<Integer> jpList = new ArrayList<Integer>();
	private ArrayList<String> chTextList = new ArrayList<String>();
	private ArrayList<String> jpTextList = new ArrayList<String>();
	
	public MemberWord(String user_id) {
		conn = new NewConnect().getConnection();
		this.user_id = user_id;
		findId();
		findChText();
		findJpText();
	}
	
	private void findId() {
		try {
			st = (Statement) conn.createStatement();
//			int id = Integer.parseInt(user_id);
			String query = "SELECT ch_num, jp_num FROM user_ch_jp WHERE " +
							"user_num = " + this.user_id;
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int ch_num = rs.getInt("ch_num");
				int jp_num = rs.getInt("jp_num");
				this.chList.add(ch_num);
				this.jpList.add(jp_num);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void findChText() {
		String ch_query = "SELECT ch_text FROM ch WHERE ch_id = ?";
		try {
			PreparedStatement preSt = conn.prepareStatement(ch_query);
			for (int i = 0; i < this.chList.size(); i++) {
				preSt.setInt(1, this.chList.get(i));
				ResultSet rs = preSt.executeQuery();
				while (rs.next()) {
					String ch_text = rs.getString(1);
					this.chTextList.add(ch_text);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void findJpText() {
		String ch_query = "SELECT jp_text FROM jp WHERE jp_id = ?";
		try {
			PreparedStatement preSt = conn.prepareStatement(ch_query);
			for (int i = 0; i < this.jpList.size(); i++) {
				preSt.setInt(1, this.jpList.get(i));
				ResultSet rs = preSt.executeQuery();
				while (rs.next()) {
					String jp_text = rs.getString(1);
					this.jpTextList.add(jp_text);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> getChList() {
		return this.chList;
	}
	
	public ArrayList<Integer> getJpList() {
		return this.jpList;
	}
	
	public ArrayList<String> getChTextList() {
		return this.chTextList;
	}
	
	public ArrayList<String> getJpTextList() {
		return this.jpTextList;
	}
}
