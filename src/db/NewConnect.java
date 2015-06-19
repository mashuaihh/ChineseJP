package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NewConnect {
	private Connection con;
	private String url;
	private String password;
	private String user;
	private String driver;
	
	public NewConnect() {
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://mysql2732-mashuaihh."
				+ "cloudplatform.hk/chjp?useUnicode=yes&characterEncoding=UTF-8" ;    
	    user = "root" ; 
	    password = "RSOmkc88926" ;   
	    
	    setDevelopment();
	    
		
		try {
			Class.forName(driver);
			this.con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setDevelopment() {
		url = "jdbc:mysql://localhost:3306/chjp";
        user = "root" ;  
        password = "mashuaihh";
	}
	
	public Connection getConnection() {
		return this.con;
	}
}
