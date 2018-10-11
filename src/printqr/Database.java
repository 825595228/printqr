package printqr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
//	private static String username;
//	private static String password;
//	private static String dataBaseName;
//	static String username = "root";
//	static String password = "123456";
//	static String dataBaseName = "froeasy";
//	static String ip = "//127.0.0.1:3306/";
	
	static String username = "wpc_qrcode";
	static String password = "Fyadmin123";
	static String dataBaseName = "wpc";
	static String ip = "//rm-bp13a64mgzhd7l012o.mysql.rds.aliyuncs.com/";
	
	
	/**
	 * 开启数据库连接
	 * @return
	 */
	public static Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql:" + ip + dataBaseName + 
					"?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8",
					username,password);			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return connection;
		
	}
	
	
	
	/**
	 * 关闭数据库连接
	 */
	public static void closeConnection(Connection connection, PreparedStatement statement,ResultSet rs) {
		try {
			if(rs!=null)rs.close();
			if(statement!=null)statement.cancel();
			if(connection!=null)connection.close();
		}catch(Exception e) {
			e.fillInStackTrace();
		}
		
	}
	
	
	/**
	 * 更新数据库
	 * @param sql
	 * @param objects
	 */
	public static void update(String sql,Object...objects) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = (PreparedStatement) connection.prepareStatement(sql);
			for (int i = 0; i <objects.length; i++) {
				statement.setObject(i+1, objects[i]);
			}
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(connection, statement, null);
		}


	}
}
