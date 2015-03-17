package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import tool.UpdateTextBean;

public class UpdateWord {
	private UpdateTextBean textBean = null;
	private PreparedStatement pstmt = null;
	private Connection conn = null;
	
	public UpdateWord(UpdateTextBean textBean) {
		this.textBean = textBean;
		conn = new NewConnect().getConnection();
		
		String updateLan = this.textBean.getUpdateLan();
		String ori = this.textBean.getOri();
		if (ori.equals("jp_ori")) {
			if (updateLan.equals("jp"))
				jpOriUpdateJp();
			else
				jpOriUpdateCh();
		} else {
			if (updateLan.equals("jp"))
				chOriUpdateJp();
			else 
				chOriUpdateCh();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void jpOriUpdateJp() {
		String update = "UPDATE " + textBean.getUpdateTable() + " SET " +
						"jp_text = ? WHERE jp_id = ?";
		try {
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, textBean.getText_content());
			pstmt.setInt(2, Integer.parseInt(textBean.getText_id()));
			pstmt.executeUpdate();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void jpOriUpdateCh() {
		String update = "UPDATE " + textBean.getUpdateTable() + " SET " +
						"ct_text = ? WHERE ct_id = ?";
		try {
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, textBean.getText_content());
			pstmt.setInt(2, Integer.parseInt(textBean.getText_id()));
			pstmt.executeUpdate();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void chOriUpdateJp() {
		String update = "UPDATE " + textBean.getUpdateTable() + " SET " +
				"jt_text = ? WHERE jt_id = ?";
		try {
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, textBean.getText_content());
			pstmt.setInt(2, Integer.parseInt(textBean.getText_id()));
			pstmt.executeUpdate();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void chOriUpdateCh() {
		String update = "UPDATE " + textBean.getUpdateTable() + " SET " +
				"ch_text = ? WHERE ch_id = ?";
		try {
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, textBean.getText_content());
			pstmt.setInt(2, Integer.parseInt(textBean.getText_id()));
			pstmt.executeUpdate();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
