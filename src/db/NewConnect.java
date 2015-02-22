package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NewConnect {
	private Connection con;
	
	public NewConnect() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/chjp";
		String user = "root";
		String password = "mashuaihh";
		
		try {
			Class.forName(driver);
			this.con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.con;
	}
}
