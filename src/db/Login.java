package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tool.BCrypt;

public class Login {
	private boolean login_result;
	
	private Connection conn;
	
	private String username;
	private String psw;
	private String username_enter;
	private String psw_enter;
	private String user_id;
	private int active;
	private String role;
	private int percent;
	
	public Login(String username, String password) {
		conn = new NewConnect().getConnection();
		this.username_enter = username;
		this.psw_enter = password;
		String query = "SELECT * FROM users WHERE name = '" + username + "'";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				this.user_id = rs.getString("user_id");
				this.username = rs.getString("name");
				this.psw = rs.getString("password");
				this.active = rs.getInt("activated");
				this.role = rs.getString("role");
				this.percent = rs.getInt("percent");
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (this.username == null || this.psw == null) {
			this.login_result = false;
		} else if (this.username.equals(this.username_enter) && BCrypt.checkpw(this.psw_enter, this.psw) && this.active == 1) {
			this.login_result = true;
		}
	}
	
	public boolean getLogin_result() {
		return this.login_result;
	}
	
	public String getUserid() {
		return this.user_id;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public int getPercent() {
		return this.percent;
	}
	
	public static void main(String[] args) {
		String psw = "mashuaihh";

		String salt = BCrypt.gensalt();
//		System.out.println(BCrypt.checkpw("mashuaihh",hash )==true);
		System.out.println(BCrypt.hashpw("cjkvword", salt));
		System.out.println(salt);
		System.out.println();
	}
}
