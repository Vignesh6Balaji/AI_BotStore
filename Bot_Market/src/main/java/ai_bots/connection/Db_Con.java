package ai_bots.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db_Con {
	private static Connection con=null;

	public static Connection getCon() throws SQLException, ClassNotFoundException {
		if(con==null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bot_market","root","admin");
			System.out.println("Connection Established Successfully");
		}
		return con;
	}
	
	
}
