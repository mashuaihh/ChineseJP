package wordSearch;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Upload {
	public DB_Upload(String jp_text, String jp_author, String jp_trans, String jp_date) {
		Connection conn = new MyConnect().getConnection();
		
		try {
			Statement st = conn.createStatement();
			String jp_input = "INSERT INTO jp_info (jp_text, author, trans, pub_date)" +
								"VALUES ('" + jp_text +
								"', '" + jp_author +
								"', '" + jp_trans +
								"', '" + jp_date +
								"')";
			st.executeUpdate(jp_input);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
							
	}
}
