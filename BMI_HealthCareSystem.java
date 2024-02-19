import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class BMI_HealthCareSystem{
    
	//程式畫面
	private JFrame BMI_HealthCareSystem;              
	
	//建立程式物件bmi，使本程式能夠使用bmi.java的方法
	private bmi bmi;
	
	//文字(輸入)框
	private JTextField Inputheight; //輸入身高
	private JTextField Inputweight; //輸入體重
	private JTextField Inputyear;   //輸入年齡
	
	// 1.請選擇您的性別： 2.請輸入您的身高： 3.請輸入您的體重：  4.請輸入您的年齡：  5.日常活動強度與指數：
	private JLabel LabelSex,Labelheight,Labelweight,Labelyear,Labelactivity;
	//1.CM 2.KG 3.歲
	private JLabel Label_cm,Label_kg,Label_year;
	//1.您的飲食建議  2.您的健身建議
	private JLabel Labelfoodcomment,Label_fitness;
	
	//重新計算按鈕，回到程式初始畫面，並重新開始一次新的計算
	private JButton reset;
	//計算按鈕，計算身體量數
	private JButton calculate;
	
	//男女性別選擇下拉式選單
	private JComboBox ComboBoxSex = new JComboBox();
	
	//TDEE(每日總消耗熱量) 飲食建議圖
	private JLabel cal1200=new JLabel();    //TDEE(每日總消耗熱量) <1500
	private JLabel cal1500=new JLabel();    //TDEE(每日總消耗熱量) 1500-1800(小於)
	private JLabel cal1800=new JLabel();    //TDEE(每日總消耗熱量) 1800-2000(小於)
	private JLabel cal2000=new JLabel();    //TDEE(每日總消耗熱量) 2000-2200(小於)
	private JLabel cal2200=new JLabel();    //TDEE(每日總消耗熱量) 2200-2500(小於)
	private JLabel cal2500=new JLabel();    //TDEE(每日總消耗熱量) 2500-2700(小於)
	private JLabel cal2700=new JLabel();    //TDEE(每日總消耗熱量) >=2700
	
	//文字區-顯示計算出的BMI、健康體重、BMR(基礎代謝率)及每日卡路里需求量
	private JTextArea txtOutput = new JTextArea();
	//文字區-根據BMI數值範圍顯示健身建議
	private JTextArea comments_fitness;
	
	//選項元件-根據活動程度選擇日常活動強度指數，以計算TDEE(每日總消耗熱量)
	private JRadioButton b0,b1,b2,b3,b4;
	//選項元件群組-把選項元件b0.b1.b2.b3.b4放進去，使b0.b1.b2.b3.b4作為單選按鈕
	private ButtonGroup g= new ButtonGroup();
	
	//全域變數activity-存取日常活動強度指數
	static double activity;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BMI_HealthCareSystem window = new BMI_HealthCareSystem();
					window.BMI_HealthCareSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BMI_HealthCareSystem() {
		//程式畫面
		initialize();
		//建立bmi物件，使得此window程式可以使用bmi.java的方法
		bmi = new bmi();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		BMI_HealthCareSystem = new JFrame("健康照護計算系統");
		BMI_HealthCareSystem.getContentPane().setBackground(Color.PINK);
		BMI_HealthCareSystem.setBounds(100, 50,1100, 650);
		BMI_HealthCareSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BMI_HealthCareSystem.getContentPane().setLayout(null);
		
		Labelheight = new JLabel("\u8ACB\u8F38\u5165\u60A8\u7684\u8EAB\u9AD8\uFF1A");
		Labelheight.setFont(new Font("標楷體", Font.PLAIN, 22));
		Labelheight.setBounds(74, 315, 182, 47);
		BMI_HealthCareSystem.getContentPane().add(Labelheight);
		
		Inputheight = new JTextField();
		Inputheight.setBounds(285, 323, 129, 32);
		BMI_HealthCareSystem.getContentPane().add(Inputheight);
		Inputheight.setColumns(10);
		Inputheight.setFont(new Font("標楷體", Font.PLAIN, 20));
		
		Labelweight = new JLabel("\u8ACB\u8F38\u5165\u60A8\u7684\u9AD4\u91CD\uFF1A");
		Labelweight.setFont(new Font("標楷體", Font.PLAIN, 22));
		Labelweight.setBounds(74, 362, 182, 47);
		BMI_HealthCareSystem.getContentPane().add(Labelweight);
		
		Inputweight = new JTextField();
		Inputweight.setFont(new Font("標楷體", Font.PLAIN, 20));
		Inputweight.setColumns(10);
		Inputweight.setBounds(285, 370, 129, 32);
		BMI_HealthCareSystem.getContentPane().add(Inputweight);
		
		LabelSex = new JLabel("\u8ACB\u9078\u64C7\u60A8\u7684\u6027\u5225\uFF1A");
		LabelSex.setFont(new Font("標楷體", Font.PLAIN, 22));
		LabelSex.setBounds(74, 10, 182, 47);
		BMI_HealthCareSystem.getContentPane().add(LabelSex);
		
		//下拉選單有男、女的選擇，選擇性別來計算該性別所對應的身體量數數值
		ComboBoxSex.setFont(new Font("標楷體", Font.PLAIN, 20));
		ComboBoxSex.addItem("男");
		ComboBoxSex.addItem("女");
		ComboBoxSex.setBounds(285, 18, 129, 32);
		BMI_HealthCareSystem.getContentPane().add(ComboBoxSex);
		
		Label_cm = new JLabel("CM");
		Label_cm.setFont(new Font("標楷體", Font.PLAIN, 20));
		Label_cm.setBounds(424, 327, 47, 24);
		BMI_HealthCareSystem.getContentPane().add(Label_cm);
		
		Label_kg = new JLabel("KG");
		Label_kg.setFont(new Font("標楷體", Font.PLAIN, 20));
		Label_kg.setBounds(424, 374, 47, 24);
		BMI_HealthCareSystem.getContentPane().add(Label_kg);
		
		//按重新計算按鈕能夠恢復到程式開始的畫面，重新再計算一次身體量數，並給予適當的飲食及健身建議
		reset = new JButton("\u91CD\u65B0\u8A08\u7B97");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtOutput.setText("");
				Inputheight.setText("");
				Inputweight.setText("");
				Inputyear.setText("");
				g.clearSelection();
				ComboBoxSex.setSelectedIndex(0);
				cal1200.setVisible(false);
				cal1500.setVisible(false);
				cal1800.setVisible(false);
				cal2000.setVisible(false);
				cal2200.setVisible(false);
				cal2500.setVisible(false);
				cal2700.setVisible(false);
				comments_fitness.setText("");
				bmi = new bmi();
			}
		});
		reset.setForeground(Color.RED);
		reset.setBackground(Color.YELLOW);
		reset.setFont(new Font("標楷體", Font.PLAIN, 20));
		reset.setBounds(123, 419, 119, 35);
		BMI_HealthCareSystem.getContentPane().add(reset);
		
		//計算按鈕，判斷有無輸入正確，若輸入皆正確就開始計算身體量數，並給予適當的飲食及健身評估
		calculate = new JButton("\u8A08\u7B97");
		calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//如果沒有選擇日常活動強度與指數，顯示"請選擇日常活動強度與指數"的錯誤訊息
				if(g.isSelected(null))
				{
					JOptionPane.showMessageDialog(null,"請選擇日常活動強度與指數!!!");
				}
				//如果體重、身高、年齡皆沒有輸入，顯示"請輸入您的身高、體重與年齡!!!"的錯誤訊息
				if(Inputweight.getText().equals("")&&Inputheight.getText().equals("")&&Inputyear.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"請輸入您的身高、體重與年齡!!!");
				}
				//如果體重、身高皆沒有輸入，顯示"請輸入您的身高與體重!!!"的錯誤訊息
				else if(Inputweight.getText().equals("")&&Inputheight.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"請輸入您的身高與體重!!!");
				}
				//如果身高、年齡皆沒有輸入，顯示"請輸入您的身高與年齡!!!"的錯誤訊息
				else if(Inputheight.getText().equals("")&&Inputyear.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"請輸入您的身高與年齡!!!");
				}
				//如果體重、年齡皆沒有輸入，顯示"請輸入您的體重與年齡!!!"的錯誤訊息
				else if(Inputweight.getText().equals("")&&Inputyear.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"請輸入您的體重與年齡!!!");
				}
				//如果沒有輸入體重，顯示"請輸入您的體重!!!"的錯誤訊息
				else if(Inputweight.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"請輸入您的體重!!!");
				}
				//如果沒有輸入身高，顯示"請輸入您的身高!!!"的錯誤訊息
				else if(Inputheight.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"請輸入您的身高!!!");
				}
				//如果沒有輸入年齡，顯示"請輸入您的年齡!!!"的錯誤訊息
				else if(Inputyear.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"請輸入您的年齡!!!");
				}
				//如果皆有輸入，則開始計算身體量數，並給予適當的飲食及健身評估
				else
				{
					bmi = new bmi();
					btnRun_Click();
				}
			}
		});
		calculate.setForeground(Color.RED);
		calculate.setFont(new Font("標楷體", Font.PLAIN, 20));
		calculate.setBackground(Color.YELLOW);
		calculate.setBounds(285, 419, 85, 35);
		BMI_HealthCareSystem.getContentPane().add(calculate);
		txtOutput.setFont(new Font("標楷體", Font.PLAIN, 20));
		
		
		txtOutput.setBounds(74, 464, 360, 120);
		BMI_HealthCareSystem.getContentPane().add(txtOutput);
		
		Labelyear = new JLabel("\u8ACB\u8F38\u5165\u60A8\u7684\u5E74\u9F61\uFF1A");
		Labelyear.setFont(new Font("標楷體", Font.PLAIN, 22));
		Labelyear.setBounds(74, 265, 182, 47);
		BMI_HealthCareSystem.getContentPane().add(Labelyear);
		
		Inputyear = new JTextField();
		Inputyear.setFont(new Font("標楷體", Font.PLAIN, 20));
		Inputyear.setColumns(10);
		Inputyear.setBounds(285, 273, 129, 32);
		BMI_HealthCareSystem.getContentPane().add(Inputyear);
		
		Label_year = new JLabel("\u6B72");
		Label_year.setFont(new Font("標楷體", Font.PLAIN, 20));
		Label_year.setBounds(424, 274, 47, 31);
		BMI_HealthCareSystem.getContentPane().add(Label_year);
		
		Labelactivity = new JLabel("\u65E5\u5E38\u6D3B\u52D5\u5F37\u5EA6\u8207\u6307\u6578\uFF1A");
		Labelactivity.setFont(new Font("標楷體", Font.PLAIN, 22));
		Labelactivity.setBounds(31, 52, 225, 47);
		BMI_HealthCareSystem.getContentPane().add(Labelactivity);
		
		
		b0  = new JRadioButton("\u5B8C\u5168\u6C92\u904B\u52D5         TDEE=BMR x 1.2");
		b0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(b0.isSelected())
				{
					activity=1.2;
				}
			}
		});
		b0.setFont(new Font("標楷體", Font.BOLD, 18));
		b0.setBounds(262, 67, 419, 23);
		BMI_HealthCareSystem.getContentPane().add(b0);
		
		//日常活動強度與指數選擇每周輕量運動1-3天 -> 全域變數activity設為1.375，以便接下來的計算
		b1 = new JRadioButton("\u6BCF\u5468\u8F15\u91CF\u904B\u52D51-3\u5929  TDEE=BMR x 1.375");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(b1.isSelected())
				{
					activity=1.375;
				}
			}
		});
		b1.setFont(new Font("標楷體", Font.BOLD, 18));
		b1.setBounds(262, 106, 419, 23);
		BMI_HealthCareSystem.getContentPane().add(b1);
		
		//日常活動強度與指數選擇每周中量運動3-5天 -> 全域變數activity設為1.55，以便接下來的計算
		b2 = new JRadioButton("\u6BCF\u5468\u4E2D\u91CF\u904B\u52D53-5\u5929  TDEE=BMR x 1.55");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(b2.isSelected())
				{
					activity=1.55;
				}
			}
		});
		b2.setFont(new Font("標楷體", Font.BOLD, 18));
		b2.setBounds(262, 145, 419, 23);
		BMI_HealthCareSystem.getContentPane().add(b2);
		
		//日常活動強度與指數選擇每周高強運動6-7天 -> 全域變數activity設為1.725，以便接下來的計算
		b3 = new JRadioButton("\u6BCF\u5468\u9AD8\u5F37\u904B\u52D56-7\u5929  TDEE=BMR x 1.725");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(b3.isSelected())
				{
					activity=1.725;
				}
			}
		});
		b3.setFont(new Font("標楷體", Font.BOLD, 18));
		b3.setBounds(262, 187, 419, 23);
		BMI_HealthCareSystem.getContentPane().add(b3);
		
		//日常活動強度與指數選擇每天運動訓練兩次 -> 全域變數activity設為1.9，以便接下來的計算
		b4 = new JRadioButton("\u6BCF\u5929\u904B\u52D5\u8A13\u7DF4\u5169\u6B21   TDEE=BMR x 1.9");
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(b4.isSelected())
				{
					activity=1.9;
				}
			}
		});
		b4.setFont(new Font("標楷體", Font.BOLD, 18));
		b4.setVerticalAlignment(SwingConstants.TOP);
		b4.setBounds(262, 224, 419, 24);
		BMI_HealthCareSystem.getContentPane().add(b4);
		
		//建立一個ButtonGroup()物件，把日常活動強度與指數的選項放進來，讓使用者每次計算只能選擇一個選項
		g= new ButtonGroup();
		g.add(b0);g.add(b1);g.add(b2);g.add(b3);g.add(b4);
		
		//TDEE為 <1500 的飲食建議圖
		cal1200 = new JLabel("New label");
		cal1200.setIcon(new ImageIcon("C:\\img\\cal1200.jpg"));
		cal1200.setBounds(728, 52, 345, 297);
		cal1200.setVisible(false);
		BMI_HealthCareSystem.getContentPane().add(cal1200);
		
		//TDEE為 1500-1800(小於) 的飲食建議圖
		cal1500 = new JLabel("New label");
		cal1500.setIcon(new ImageIcon("C:\\img\\cal1500.jpg"));
		cal1500.setBounds(728, 52, 345, 297);
		cal1500.setVisible(false);
		BMI_HealthCareSystem.getContentPane().add(cal1500);
		
		//TDEE為 1800-2000(小於) 的飲食建議圖
		cal1800 = new JLabel("New label");
		cal1800.setIcon(new ImageIcon("C:\\img\\cal1800.jpg"));
		cal1800.setBounds(728, 52, 345, 297);
		cal1800.setVisible(false);
		BMI_HealthCareSystem.getContentPane().add(cal1800);
		
		//TDEE為 2000-2200(小於) 的飲食建議圖
		cal2000 = new JLabel("New label");
		cal2000.setIcon(new ImageIcon("C:\\img\\cal2000.jpg"));
		cal2000.setBounds(728, 52, 345, 297);
		cal2000.setVisible(false);
		BMI_HealthCareSystem.getContentPane().add(cal2000);
		
		//TDEE為 2200-2500(小於) 的飲食建議圖
		cal2200 = new JLabel("New label");
		cal2200.setIcon(new ImageIcon("C:\\img\\cal2200.jpg"));
		cal2200.setBounds(728, 52, 345, 297);
		cal2200.setVisible(false);
		BMI_HealthCareSystem.getContentPane().add(cal2200);
		
		//TDEE為 2500-2700(小於) 的飲食建議圖
		cal2500 = new JLabel("New label");
		cal2500.setIcon(new ImageIcon("C:\\img\\cal2500.jpg"));
		cal2500.setBounds(728, 52, 345, 297);
		cal2500.setVisible(false);
		BMI_HealthCareSystem.getContentPane().add(cal2500);
		
		//TDEE為 >=2700 的飲食建議圖
		cal2700 = new JLabel("New label");
		cal2700.setIcon(new ImageIcon("C:\\img\\\u71B1\u91CF\u5927\u5361\u8868-2700-1.jpg"));
		cal2700.setBounds(728, 52, 345, 297);
		cal2700.setVisible(false);
		BMI_HealthCareSystem.getContentPane().add(cal2700);
		
		Labelfoodcomment = new JLabel("\u60A8\u7684\u98F2\u98DF\u5EFA\u8B70\uFF1A");
		Labelfoodcomment.setFont(new Font("標楷體", Font.BOLD, 22));
		Labelfoodcomment.setBounds(728, 10, 182, 34);
		BMI_HealthCareSystem.getContentPane().add(Labelfoodcomment);
		
		Label_fitness = new JLabel("\u60A8\u7684\u5065\u8EAB\u5EFA\u8B70\uFF1A");
		Label_fitness.setFont(new Font("標楷體", Font.BOLD, 22));
		Label_fitness.setBounds(728, 418, 182, 34);
		BMI_HealthCareSystem.getContentPane().add(Label_fitness);
		
		comments_fitness = new JTextArea();
		comments_fitness.setFont(new Font("標楷體", Font.PLAIN, 20));
		comments_fitness.setBounds(728, 464, 345, 120);
		BMI_HealthCareSystem.getContentPane().add(comments_fitness);
}		
	    //計算按鈕，執行計算身體量數，並給予適當的飲食及健身建議
		protected void btnRun_Click() {
			
			//personal_height變數存放輸入的身高，字串使用Double.parseDouble()轉為雙精度浮點數的資料型態
			double personal_height = Double.parseDouble(Inputheight.getText());
			//personal_weight變數存放輸入的體重，字串使用Double.parseDouble()轉為雙精度浮點數的資料型態
			double personal_weight = Double.parseDouble(Inputweight.getText());
			//year變數存放輸入的年齡，字串使用Double.parseDouble()轉為雙精度浮點數的資料型態
			double year = Double.parseDouble(Inputyear.getText());
	        
			//result_bmi變數，存放使用bmi.java的count_bmi方法，把輸入的身高及體重放進去，計算bmi
			double result_bmi = bmi.count_bmi(personal_height,personal_weight);
			//result_healthyweight變數，存放使用bmi.java的count_healthyweight方法，把輸入的身高放進去，計算健康體重
			double result_healthyweight=bmi.count_healthyweight(personal_height);
			//result_bmr_man變數，存放使用bmi.java的count_bmr_man方法，把輸入的年齡、體重、身高放進去，計算男生的基礎代謝率
			double result_bmr_man = bmi.count_bmr_man(year,personal_weight,personal_height);
			//result_bmr_girl變數，存放使用bmi.java的count_bmr_girl方法，把輸入的年齡、體重、身高放進去，計算女生的基礎代謝率
			double result_bmr_girl= bmi.count_bmr_girl(year,personal_weight,personal_height);
			//result_cal_man變數，存放使用bmi.java的count_cal方法，把計算出的男生的基礎代謝率及選擇的日常活動強度指數放進去，計算男生的TDEE(每日總消耗熱量)
			double result_cal_man=bmi.count_cal(result_bmr_man,activity);
			//result_cal_girl變數，存放使用bmi.java的count_cal方法，把計算出的女生的基礎代謝率及選擇的日常活動強度指數放進去，計算女生的TDEE(每日總消耗熱量)
			double result_cal_girl=bmi.count_cal(result_bmr_girl,activity);
			
			//如果下拉選單選擇男生則執行下列判斷式
			if(ComboBoxSex.getSelectedItem() == "男")
			{
	            //顯示剛剛計算出的身體量數數值，四捨五入顯示到小數點後2位
				this.txtOutput.setText("您的BMI為："+Math.round(result_bmi*100.0)/100.0+"\n"+"您的BMR(基礎代謝率)為："+Math.round(result_bmr_man*100.0)/100.0+"\n"+
				                       "您的健康體重為："+Math.round(result_healthyweight*100.0)/100.0+"\n"+"您的TDEE(每日總消耗熱量)為："+Math.round(result_cal_man*100.0)/100.0);
				//根據計算出的TDEE(每日總消耗熱量)，顯示飲食建議圖
				if(result_cal_man<1500)
				{
					this.cal1200.setVisible(true);
				}
				else if(result_cal_man<1800)
				{
					this.cal1500.setVisible(true);
				}
				else if(result_cal_man<2000)
				{
					this.cal1800.setVisible(true);
				}
				else if(result_cal_man<2200)
				{
					this.cal2000.setVisible(true);
				}
				else if(result_cal_man<2500)
				{
					this.cal2200.setVisible(true);
				}
				else if(result_cal_man<2700)
				{
					this.cal2500.setVisible(true);
				}
				else if(result_cal_man>=2700)
				{
					this.cal2700.setVisible(true);
				}
			}
			//如果下拉選單選擇女生則執行下列判斷式
			else if(ComboBoxSex.getSelectedItem() == "女")
			{
				//顯示剛剛計算出的身體量數數值，四捨五入顯示到小數點後2位
				this.txtOutput.setText("您的BMI為："+Math.round(result_bmi*100.0)/100.0+"\n"+"您的BMR(基礎代謝率)為："+Math.round(result_bmr_girl*100.0)/100.0+"\n"+
						               "您的健康體重為："+Math.round(result_healthyweight*100.0)/100.0+"\n"+"您的TDEE(每日總消耗熱量)為："+Math.round(result_cal_girl*100.0)/100.0);
				//根據計算出的TDEE(每日總消耗熱量)，顯示飲食建議圖
				if(result_cal_girl<1500)
				{
					this.cal1200.setVisible(true);
				}
				else if(result_cal_girl<1800)
				{
					this.cal1500.setVisible(true);
				}
				else if(result_cal_girl<2000)
				{
					this.cal1800.setVisible(true);
				}
				else if(result_cal_girl<2200)
				{
					this.cal2000.setVisible(true);
				}
				else if(result_cal_girl<2500)
				{
					this.cal2200.setVisible(true);
				}
				else if(result_cal_girl<2700)
				{
					this.cal2500.setVisible(true);
				}
				else if(result_cal_girl>=2700)
				{
					this.cal2700.setVisible(true);
				}
			}
			
			//健身建議的部分使用下列判斷式，選出要顯示的內容
			if(result_bmi<18.5)
			{
				this.comments_fitness.setText("「體重過輕」，需要多運動，均衡飲食，"+"\n"+"以增加體重，維持健康！");
			}
			else if(result_bmi>=18.5&&result_bmi<24)
			{
				this.comments_fitness.setText("恭喜！「健康體重」，要繼續保持哦！");
			}
			else if(result_bmi>=24&&result_bmi<27)
			{
				this.comments_fitness.setText("哦！「體重過重」了，要小心囉，趕快"+"\n"+"力行「健康體重管理」！");
			}
			else if(result_bmi>27)
			{
				this.comments_fitness.setText("啊~「肥胖」，需要立刻力行"+"\n"+"「健康體重管理」囉！");
			}
		}
}
