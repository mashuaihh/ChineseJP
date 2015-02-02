package wordSearch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Login {
	static Connection conn;
	static Statement st;
	
	final int SUCCESS = 1;
	final int FAILURE = 0;

	private String username = null;
	private String password = null;
	private String username_enter = null;
	private String password_enter = null;
	 //1 for successful login, 0 for failure

	public DB_Login(String user, String psw) {
		Connection conn = new MyConnect().getConnection();
		this.username_enter = user;
		this.password_enter = psw;
		String subquery1 = "SELECT username, password FROM users WHERE username = '";
		String subquery2 = user + "'";
		String query = subquery1 + subquery2;

		try {
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				this.username = rs.getString("username");
				this.password = rs.getString("password");
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public int getSuccess() {  //1 for successful login, 0 for failure
		if (this.username.equals(this.username_enter) && this.password.equals(this.password_enter))
			return SUCCESS;
		else 
			return FAILURE;
	}
}
