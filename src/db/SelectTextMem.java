package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tool.ChOriBean;
import tool.JpOriBean;

public class SelectTextMem {
	private String user_id = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ArrayList<JpOriBean>  jpOriList = new ArrayList<JpOriBean>();
	private ArrayList<ChOriBean>  chOriList = new ArrayList<ChOriBean>();
	
	public SelectTextMem(String user_id) {
		this.user_id = user_id;
		this.conn = new NewConnect().getConnection();
		
		selectJpOri();
		selectChOri();
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<JpOriBean> getJpOriList() {
		return this.jpOriList;
	}
	
	public ArrayList<ChOriBean> getChOriList() {
		return this.chOriList;
	}
	
	private void selectJpOri() {
		try {
			String sql = "SELECT * FROM jp_ori AS jp, ch_trans AS ch " + 
						"WHERE jp.jp_id = ch.jp_num AND jp.user_num_jp = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(this.user_id));
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String jp_id = rs.getString("jp_id");
				String jp_text = rs.getString("jp_text");
				String jp_author = rs.getString("author");
				String jp_publisher = rs.getString("publisher");
				String jp_pub_date = rs.getString("pub_date");
				String ct_id = rs.getString("ct_id");
				String ct_text = rs.getString("ct_text");
				String ct_translator = rs.getString("ct_translator");
				String ct_publisher = rs.getString("ct_publisher");
				String ct_pub_date = rs.getString("ct_pub_date");
				String ct_jp_num = rs.getString("jp_num");
				JpOriBean jpOriContent = new JpOriBean(jp_id,
						jp_text,
						jp_author,
						jp_publisher,
						jp_pub_date,
						ct_id,
						ct_text,
						ct_translator,
						ct_publisher,
						ct_pub_date,
						ct_jp_num);
				jpOriList.add(jpOriContent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void selectChOri() {
		try {
			String sql = "SELECT * FROM ch_ori AS ch, jp_trans AS jp " + 
						"WHERE ch.ch_id = jp.ch_num AND ch.user_num_ch = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(this.user_id));
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String ch_id = rs.getString("ch_id");
				String ch_text = rs.getString("ch_text");
				String ch_author = rs.getString("author");
				String ch_publisher = rs.getString("publisher");
				String ch_pub_date = rs.getString("pub_date");
				String jt_id = rs.getString("jt_id");
				String jt_text = rs.getString("jt_text");
				String jt_translator = rs.getString("jt_translator");
				String jt_publisher = rs.getString("jt_publisher");
				String jt_pub_date = rs.getString("jt_pub_date");
				String jt_ch_num = rs.getString("ch_num");
				ChOriBean chOriContent = new ChOriBean(ch_id,
						ch_text,
						ch_author,
						ch_publisher,
						ch_pub_date,
						jt_id,
						jt_text,
						jt_translator,
						jt_publisher,
						jt_pub_date,
						jt_ch_num);
				chOriList.add(chOriContent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
