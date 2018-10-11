package printqr;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Scanner;
import java.util.UUID;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public class Qrcode
{
	public interface TscLibDll extends StdCallLibrary
	{
		TscLibDll INSTANCE = (TscLibDll) Native.loadLibrary("TSCLIB", TscLibDll.class);

		int about();

		int openport(String pirnterName);

		int closeport();

		int sendcommand(String printerCommand);

		int setup(String width, String height, String speed, String density, String sensor, String vertical, String offset);

		int downloadpcx(String filename, String image_name);

		int barcode(String x, String y, String type, String height, String readable, String rotation, String narrow, String wide, String code);

		int printerfont(String x, String y, String fonttype, String rotation, String xmul, String ymul, String text);

		int clearbuffer();

		int printlabel(String set, String copy);

		int formfeed();
		
		int nobackfeed();

		int windowsfont(int x, int y, int fontheight, int rotation, int fontstyle, int fontunderline, String szFaceName, String content);
	}

	public static void Prcode(int num, int bn ,String uuid ,String s) throws UnsupportedEncodingException, InterruptedException
	{

			System.setProperty("jna.encoding", "GBK");// 支持中文
			TscLibDll.INSTANCE.openport("Gprinter  GP-3120TL");//端口名称			
			TscLibDll.INSTANCE.setup("25", "20", "5", "15", "0", "1", "0");//打印设置
			TscLibDll.INSTANCE.sendcommand("SET TEAR ON");
			TscLibDll.INSTANCE.clearbuffer();
			String command =String.format("QRCODE 62,15,L,5,A,0,M2,S7,\"%s\"", uuid) ;// 打印二维码
//			System.out.println(command);
			TscLibDll.INSTANCE.sendcommand(command);
//			String s=String.valueOf(i);
			//生成批次号

//			String s = String.valueOf(i);
			
			
			int len = s.length();
			if(len < 9){ 
				TscLibDll.INSTANCE.windowsfont(10, 150, 38, 90, 0, 0, "Consolas", s);
				} 
			else {
				TscLibDll.INSTANCE.windowsfont(10, 150, 38, 90, 0, 0, "Consolas", s.substring(0,8));
				TscLibDll.INSTANCE.windowsfont(32, 150, 38, 90, 0, 0, "Consolas", s.substring(8));
				}
			TscLibDll.INSTANCE.windowsfont(10, 150, 38, 90, 0, 0, "Consolas",s);//打印编号
			TscLibDll.INSTANCE.printlabel("1", "1");
			TscLibDll.INSTANCE.closeport();
	}
	
	/**
	 * 生成UID
	 * @return
	 */
	public static String getuid() {
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();//生成UUID
//		System.out.println(uuid);
		return uuid;
	}
	
	/**
	 * 生成批次号
	 * @param i
	 * @param bn
	 * @return
	 */
	public static String gets(int i ,int bn) {
		Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dt = sdf.format(date).replace("-", "").substring(2);   
		String s = String.format("FY%s%d-%03d",dt,bn,i); 
//		System.out.println(s);
		return s;
	}
}
