package wordSearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnect {
	private Connection conn = null;
	
	public MyConnect() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/xywy";
		String user = "root";
		String password = "mashuaihh";

		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.conn = con;
	}
	
	public Connection getConnection() {
		return this.conn;
	}
}
