package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DeleteWord {
	private static Connection conn = null;
	private static Statement st = null;
	private String ch_id = null;
	private String jp_id = null;
	private String user_id = null;
	private ArrayList<String> resultArray = new ArrayList<String>();
	
	public DeleteWord(String ch_id, String jp_id, String user_id) {
		conn = new NewConnect().getConnection();
		this.ch_id = ch_id;
		this.jp_id = jp_id;
		this.user_id = user_id;
	}
	
	private void chDelete() {
		//search db, if ch : jp == 1:1, set relate table's ch_id = 1;
		//			 if ch : jp > 1:1, delete the row in relate table
	}
	
//	private boolean hasMoreRows(String nation) {
		// select rows from user_ch_jp, if has more than 1 row, return 
		// parameter = "ch" or "jp"
		// 1ɾ��ch,����Ҷ�Ӧjp_id�Ƿ��ж��У����ж��У���ͬһ��JP_ID��Ӧ���CH_ID����jpΪԭ�ģ�chΪ���ģ�����汾��
		// ���ɾ���������ch���İ汾
		// 2��
//		st = (Statement) conn.createStatement();
//		String query = "SELECT * FROM user_ch_jp WHERE " +
//				nation + "_num = " + (nation.equals("ch")? this.ch_id : this.jp_id) + 
//				" AND user_num = " + this.user_id;
//		ResultSet rs = st.executeQuery(query);
//		while (rs.next()) {
//			resultArray.add(rs.getString("ch_num"));
//			
//		}
//	}
}
