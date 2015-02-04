package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
	final int SUCCESS = 1;
	final int FAILURE = 0;
	private int login_result;
	
	static Connection conn;
	static Statement st;
	
	private String username;
	private String psw;
	private String username_enter;
	private String psw_enter;
	
	public Login(String username, String password) {
		conn = new MyConnect().getConnection();
		this.username_enter = username;
		this.psw_enter = password;
		String query = "SELECT username, password FROM users WHERE username = '" + username + "'";
		
		try {
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				this.username = rs.getString("username");
				this.psw = rs.getString("password");
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (this.username.equals(this.username_enter) && this.psw.equals(this.psw_enter))
			this.login_result = SUCCESS;
		else
			this.login_result = FAILURE;
	}
	
	public int getLogin_result() {
		return this.login_result;
	}
}
