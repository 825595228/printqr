package test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/lemon";
		String user = "root";
		String password = "123456";
		
		java.sql.Connection conn = DriverManager.getConnection(url, user, password);
		System.out.print(conn);
	}

	

}
