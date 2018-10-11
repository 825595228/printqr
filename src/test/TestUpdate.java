package test;

import printqr.Database;

public class TestUpdate {

	public static void main(String[] args) {
		
//		String id = UUID.randomUUID() + "";
		Database.update("INSERT INTO qrcode(uid,batch,date,destination,username) "
		        + "VALUES (?, ?, ?, ?, ?)", "12", "12", null, null, "测试");
	}
}
