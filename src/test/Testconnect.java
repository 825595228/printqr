package test;
import java.sql.Connection;

import printqr.Database;;
public class Testconnect {

	public static void main(String[] args) {

		Connection conn = Database.getConnection();
		System.out.println(conn);		
	
	}

}
