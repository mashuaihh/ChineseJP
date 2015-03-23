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
	
	public InsertUser() {
		
	}
	
	public void updateUser() {
//		Connection conn = getConnection();
		Connection conn = new NewConnect().getConnection();
		String sql = "INSERT INTO users"
				+ "(name, password, email, role, activated) " + 
					"VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, this.username);
			pstmt.setString(2, this.psw);
			pstmt.setString(3, this.email);
			pstmt.setString(4, this.role);
			pstmt.setInt(5, this.activated);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Connection getConnection() {
		String dsName = "java:comp/env/jdbc/dbTest";
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(dsName);
			conn = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
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
