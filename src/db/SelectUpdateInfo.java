package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import tool.ChOriBean;
import tool.JpOriBean;

public class SelectUpdateInfo {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	private String ch_id = null;
	private String jp_id = null;
	private ArrayList<ChOriBean> chOriList = new ArrayList<ChOriBean>();
	private ArrayList<JpOriBean> jpOriList = new ArrayList<JpOriBean>();
	
	public SelectUpdateInfo(String ori, String ch_id, String jp_id) {
		conn = new NewConnect().getConnection();
		
		this.ch_id = ch_id;
		this.jp_id = jp_id;
		if (ori.equals("ch_ori")) {
			//ori = "ch_ori"
			SelectChOri();
		} else {
			// ori = "jp_ori"
			SelectJpOri();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void SelectChOri() {
		try {
			if (ch_id == null) {
				//select jp text from jp_trans
				String query = "SELECT * FROM jp_trans WHERE "
								+ "jt_id = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, Integer.parseInt(this.jp_id));
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					ChOriBean jpBean = new ChOriBean();
					jpBean.setJt_id(this.jp_id);
					jpBean.setJt_text(rs.getString("jt_text"));
					jpBean.setJt_translator(rs.getString("jt_translator"));
					jpBean.setJt_publisher(rs.getString("jt_publisher"));
					jpBean.setJt_pub_date(rs.getString("jt_pub_date"));
					this.chOriList.add(jpBean);
				}
			} else {
				//select ch text from ch_ori
				String query = "SELECT * FROM ch_ori WHERE " 
						   		+ "ch_id = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, Integer.parseInt(this.ch_id));
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					ChOriBean chBean = new ChOriBean();
					chBean.setCh_id(this.ch_id);
					chBean.setCh_text(rs.getString("ch_text"));
					chBean.setCh_author(rs.getString("author"));
					chBean.setCh_publisher(rs.getString("publisher"));
					chBean.setCh_pub_date(rs.getString("pub_date"));
					this.chOriList.add(chBean);
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void SelectJpOri() {
		try {
			if (this.ch_id == null) {
				//select jp text from jp_ori
				String query = "SELECT * FROM jp_ori WHERE " 
								+ "jp_id = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, Integer.parseInt(this.jp_id));
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					JpOriBean jpBean = new JpOriBean();
					jpBean.setJp_id(this.jp_id);
					jpBean.setJp_text(rs.getString("jp_text"));
					jpBean.setJp_author(rs.getString("author"));
					jpBean.setJp_publisher(rs.getString("publisher"));
					jpBean.setJp_pub_date(rs.getString("pub_date"));
					this.jpOriList.add(jpBean);
				}
			} else {
				//select ch text from ch_trans
				String query = "SELECT * FROM ch_trans WHERE "
								+ "ct_id = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, Integer.parseInt(this.ch_id));
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					JpOriBean chBean = new JpOriBean();
					chBean.setCt_id(this.ch_id);
					chBean.setCt_text(rs.getString("ct_text"));
					chBean.setCt_translator(rs.getString("ct_translator"));
					chBean.setCt_publisher(rs.getString("ct_publisher"));
					chBean.setCt_pub_date(rs.getString("ct_pub_date"));
					this.jpOriList.add(chBean);
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<ChOriBean> getChOriList() {
		return this.chOriList;
	}
	
	public ArrayList<JpOriBean> getJpOriList() {
		return this.jpOriList;
	}
	
}
