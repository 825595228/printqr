package printqr;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;


public class JFrame {
	private Frame f;
	private JButton b;
	private JButton ok;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	private JLabel lab;
	private Dialog d;
	
	
	JFrame() {
		init();
	}
	
	
	/**
	 * 窗体设计
	 */
	public void init() {
		f = new Frame();
				
		f.setTitle("打印二维码");
		f.setSize(400,280);
		f.setLocationRelativeTo(null);		
		f.setLayout(new FlowLayout());
				
		
		d = new Dialog(f,"错误", true);
		d.setLocationRelativeTo(null);
		d.setSize(200,140);
		d.setLayout(new FlowLayout());
		lab = new JLabel();
		ok = new JButton("确定");
		ok.setFont(new java.awt.Font("黑体", 0, 15));
		d.add(lab);
		d.add(ok);
		
		
		JPanel jp = new JPanel(new GridLayout(5, 2));


		//第一栏
		JPanel jp01 = new JPanel();
		l1 = new JLabel("输入打印数量");
		t1 = new JTextField(8);
		l1.setFont(new java.awt.Font("黑体", 0, 15)); 
		
		//第二栏
		JPanel jp02 = new JPanel();
		l2 = new JLabel("输入今日批次");
		t2 = new JTextField(8);
		l2.setFont(new java.awt.Font("黑体", 0, 15)); 
		
		//第三行
		JPanel jp03 = new JPanel();
		l3 = new JLabel("输入发向医院");
		t3 = new JTextField(8);
		l3.setFont(new java.awt.Font("黑体", 0, 15)); 
		
		//第四栏
		JPanel jp04 = new JPanel();
		l4 = new JLabel("请输入领取人");
		t4 = new JTextField(8);
		l4.setFont(new java.awt.Font("黑体", 0, 15)); 
		
		//第五栏按钮
		JPanel jp05 = new JPanel();
		b = new JButton("打印");
		b.setFont(new java.awt.Font("黑体", 0, 15)); 
		
		f.add(jp);
		jp.add(jp01);
		jp.add(jp02);
		jp.add(jp03);
		jp.add(jp04);
		jp.add(jp05);
		
		jp01.add(l1);
		jp01.add(t1);
		jp02.add(l2);
		jp02.add(t2);
		jp03.add(l3);
		jp03.add(t3);
		jp04.add(l4);
		jp04.add(t4);
		jp05.add(b);
		
		
		
		f.setVisible(true);
		dealwithEvent();//监听


	}
	
	
	private void dealwithEvent() {
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}	
		});
		
		
		
		/**
		 * 限制输入内容
		 */
        l1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent m) {
                char c = m.getKeyChar();	
                if (!(c > '0' && c < '9')) {
                    m.consume();
                }
            }
        });
        
        
        
        /**
         * 提示框按钮
         */
        ok.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		d.setVisible(false);
        	}
        });
        
        
        /**
         * 提示框关闭
         */
        
        d.addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		d.setVisible(false);
        	}
        });
        
        
        
        /**
         * 点击打印
         */
        b.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		try {
					show();
				} catch (UnsupportedEncodingException | InterruptedException e1) {
					e1.printStackTrace();
				}
        	}
        });
	}
	
	
	/**
	 * 输入内容判断
	 * @throws UnsupportedEncodingException
	 * @throws InterruptedException
	 */
	
	public void show() throws UnsupportedEncodingException, InterruptedException {
		String num = t1.getText();
		String bat = t2.getText();
		String destination = t3.getText();
		String username = t4.getText();
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = df.format(date);
		
		
		if(num == null || num.equals("") || bat == null || bat.equals("")) {
			String info = "数量或批次不能为空" ;
			lab.setText(info);
			lab.setFont(new java.awt.Font("黑体", 0, 15));
			d.setVisible(true);
		}
		
		else 
			
		{
			int number = Integer.parseInt(num);
			int batch = Integer.parseInt(bat);
			if(number < 1 || batch < 1) {
				String info = "数量和批次不能小于0";
				lab.setText(info);
				lab.setFont(new java.awt.Font("黑体", 0, 15));
				d.setVisible(true);
			}
			
			else 
				
			{
				int i = 1;
				while(i<=number) {
					String uid = Qrcode.getuid();  //生成UID                                                                                                      
					System.out.print(uid);
					String s = Qrcode.gets(i,batch);	//生成批次号
					System.out.print(s);
					Qrcode.Prcode(number, batch , uid, s);   //生成打印二维码
					
					//插入数据库
					Database.update("INSERT INTO qrcode(uid,batch,date,destination,username) "
					        + "VALUES (?, ?, ?, ?, ?)",uid,s,dateTime,destination,username);
					i++;
				}
				
			}
		}
		
		
	}		

}
