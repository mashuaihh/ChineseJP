package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tool.UserDataBean;

public class SelectUser {
	private ArrayList<UserDataBean> userList = new ArrayList<UserDataBean>();
	private Connection conn = null;
	
	public SelectUser() {
		conn = new NewConnect().getConnection();
		String sql = "SELECT * FROM users ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs  = pstmt.executeQuery();
			while (rs.next()) {
				UserDataBean each = new UserDataBean();
				each.setUser_id(rs.getInt("user_id"));
				each.setUsername(rs.getString("name"));
				each.setEmail(rs.getString("email"));
				each.setRole(rs.getString("role"));
				each.setActivated(rs.getInt("activated"));
				each.setInstitute(rs.getString("institute"));
				String memo = rs.getString("memo");
				each.setMemo(memo);
				each.setPercent(rs.getInt("percent"));
				this.userList.add(each);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<UserDataBean> getUserlist() {
		return this.userList;
	}
}
