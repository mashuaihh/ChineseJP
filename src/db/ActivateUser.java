package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActivateUser {
	private Connection conn = null;
	
	public ActivateUser(String user_id) {
		String sql = "UPDATE users " + 
					"SET activated = ? " + 
					"WHERE user_id = ?";
		conn = new NewConnect().getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, Integer.parseInt(user_id));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
