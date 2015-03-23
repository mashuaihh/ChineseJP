package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tool.AddTextContent;

public class AddText {
	private AddTextContent chContent = null;
	private AddTextContent jpContent = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Integer gen_id = null;
	
	public AddText(AddTextContent ch, AddTextContent jp, String lan) {
		this.chContent = ch;
		this.jpContent = jp;
		conn = new NewConnect().getConnection();
		
		if (lan.equals("ch")) {
			insertChOri();
		} else {
			insertJpOri();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void insertJpOri() {
		try {
			String sql = "INSERT INTO jp_ori(jp_text, author, publisher, pub_date, user_num_jp) " +
					"VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, this.jpContent.getText());
			pstmt.setString(2, this.jpContent.getAuthor());
			pstmt.setString(3, this.jpContent.getPublisher());
			pstmt.setString(4, this.jpContent.getPub_date());
			pstmt.setInt(5, Integer.parseInt( this.jpContent.getUser_id()) );
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				this.gen_id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		insertChTrans();
	}
	
	private void insertChTrans() {
			try {
				String sql = "INSERT INTO ch_trans(ct_text, ct_translator, ct_publisher, ct_pub_date, jp_num) " +
							"VALUES (?, ?, ?, ?, ?)";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, this.chContent.getText());
				pstmt.setString(2, this.chContent.getTranslator());
				pstmt.setString(3, this.chContent.getPublisher());
				pstmt.setString(4, this.chContent.getPub_date());
				pstmt.setInt(5, this.gen_id );
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	private void insertChOri() {
		try {
			String sql = "INSERT INTO ch_ori(ch_text, author, publisher, pub_date, user_num_ch) " +
					"VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, this.chContent.getText());
			pstmt.setString(2, this.chContent.getAuthor());
			pstmt.setString(3, this.chContent.getPublisher());
			pstmt.setString(4, this.chContent.getPub_date());
			pstmt.setInt(5, Integer.parseInt( this.chContent.getUser_id()) );
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				this.gen_id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		insertJpTrans();
	}
	
	private void insertJpTrans() {
		try {
			String sql = "INSERT INTO jp_trans(jt_text, jt_translator, jt_publisher, jt_pub_date, ch_num) " +
						"VALUES (?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, this.jpContent.getText());
			pstmt.setString(2, this.jpContent.getTranslator());
			pstmt.setString(3, this.jpContent.getPublisher());
			pstmt.setString(4, this.jpContent.getPub_date());
			pstmt.setInt(5, this.gen_id );
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
