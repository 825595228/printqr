package test;
import printqr.Qrcode;

import java.text.SimpleDateFormat;
import java.util.Date;
public class Tests {

	public static void main(String[] args) {
		String uid = Qrcode.gets(10,10);
		System.out.println(uid);
		Date date = new Date();
		System.out.println(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
		System.out.println(sdf);
		String dt = sdf.format(date).replace("-","").substring(2);
		System.out.println(dt);

	}
}
