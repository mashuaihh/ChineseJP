package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class InsertUser {
	private String username = null;
	private String psw = null;
	private String email = null;
	private String role = "member";
	private Integer activated = null;
	private String institute = null;
	private String memo = null;
	
	public InsertUser() {
		
	}
	
	public void updateUser() {
//		Connection conn = getConnection();
		Connection conn = new NewConnect().getConnection();
		String sql = "INSERT INTO users"
				+ "(name, password, email, role, activated, institute, memo) " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, this.username);
			pstmt.setString(2, this.psw);
			pstmt.setString(3, this.email);
			pstmt.setString(4, this.role);
			pstmt.setInt(5, this.activated);
			pstmt.setString(6, this.institute);
			pstmt.setString(7, this.memo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setUsername(String str) {
		this.username = str;
	}
	
	public void setPsw(String str) {
		this.psw = str;
	}
	
	public void setEmail(String str) {
		this.email = str;
	}
	
	public void setInstitute(String str) {
		this.institute = str;
	}

	public void setMemo (String str) {
		this.memo = str;
	}
	
	public void setRole(String str) {
		this.role = str;
	}
	
	public void setActivated(Integer str) {
		this.activated = str;
	}
	
	public static void main(String[] args) {
		String a = "$2a$10$FaWuJ5lqp5DhJKKiM8wK7e/osSFk7.D3/0TkRFPVgVZAT9tBKS6eu";
		System.out.println(a.length());
	}
}
