import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class BMI_HealthCareSystem{
    
	//�{���e��
	private JFrame BMI_HealthCareSystem;              
	
	//�إߵ{������bmi�A�ϥ��{������ϥ�bmi.java����k
	private bmi bmi;
	
	//��r(��J)��
	private JTextField Inputheight; //��J����
	private JTextField Inputweight; //��J�魫
	private JTextField Inputyear;   //��J�~��
	
	// 1.�п�ܱz���ʧO�G 2.�п�J�z�������G 3.�п�J�z���魫�G  4.�п�J�z���~�֡G  5.��`���ʱj�׻P���ơG
	private JLabel LabelSex,Labelheight,Labelweight,Labelyear,Labelactivity;
	//1.CM 2.KG 3.��
	private JLabel Label_cm,Label_kg,Label_year;
	//1.�z��������ĳ  2.�z��������ĳ
	private JLabel Labelfoodcomment,Label_fitness;
	
	//���s�p����s�A�^��{����l�e���A�í��s�}�l�@���s���p��
	private JButton reset;
	//�p����s�A�p�⨭��q��
	private JButton calculate;
	
	//�k�k�ʧO��ܤU�Ԧ����
	private JComboBox ComboBoxSex = new JComboBox();
	
	//TDEE(�C���`���Ӽ��q) ������ĳ��
	private JLabel cal1200=new JLabel();    //TDEE(�C���`���Ӽ��q) <1500
	private JLabel cal1500=new JLabel();    //TDEE(�C���`���Ӽ��q) 1500-1800(�p��)
	private JLabel cal1800=new JLabel();    //TDEE(�C���`���Ӽ��q) 1800-2000(�p��)
	private JLabel cal2000=new JLabel();    //TDEE(�C���`���Ӽ��q) 2000-2200(�p��)
	private JLabel cal2200=new JLabel();    //TDEE(�C���`���Ӽ��q) 2200-2500(�p��)
	private JLabel cal2500=new JLabel();    //TDEE(�C���`���Ӽ��q) 2500-2700(�p��)
	private JLabel cal2700=new JLabel();    //TDEE(�C���`���Ӽ��q) >=2700
	
	//��r��-��ܭp��X��BMI�B���d�魫�BBMR(��¦�N�²v)�ΨC��d�����ݨD�q
	private JTextArea txtOutput = new JTextArea();
	//��r��-�ھ�BMI�ƭȽd����ܰ�����ĳ
	private JTextArea comments_fitness;
	
	//�ﶵ����-�ھڬ��ʵ{�׿�ܤ�`���ʱj�׫��ơA�H�p��TDEE(�C���`���Ӽ��q)
	private JRadioButton b0,b1,b2,b3,b4;
	//�ﶵ����s��-��ﶵ����b0.b1.b2.b3.b4��i�h�A��b0.b1.b2.b3.b4�@�������s
	private ButtonGroup g= new ButtonGroup();
	
	//�����ܼ�activity-�s����`���ʱj�׫���
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
		//�{���e��
		initialize();
		//�إ�bmi����A�ϱo��window�{���i�H�ϥ�bmi.java����k
		bmi = new bmi();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		BMI_HealthCareSystem = new JFrame("���d���@�p��t��");
		BMI_HealthCareSystem.getContentPane().setBackground(Color.PINK);
		BMI_HealthCareSystem.setBounds(100, 50,1100, 650);
		BMI_HealthCareSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BMI_HealthCareSystem.getContentPane().setLayout(null);
		
		Labelheight = new JLabel("\u8ACB\u8F38\u5165\u60A8\u7684\u8EAB\u9AD8\uFF1A");
		Labelheight.setFont(new Font("�з���", Font.PLAIN, 22));
		Labelheight.setBounds(74, 315, 182, 47);
		BMI_HealthCareSystem.getContentPane().add(Labelheight);
		
		Inputheight = new JTextField();
		Inputheight.setBounds(285, 323, 129, 32);
		BMI_HealthCareSystem.getContentPane().add(Inputheight);
		Inputheight.setColumns(10);
		Inputheight.setFont(new Font("�з���", Font.PLAIN, 20));
		
		Labelweight = new JLabel("\u8ACB\u8F38\u5165\u60A8\u7684\u9AD4\u91CD\uFF1A");
		Labelweight.setFont(new Font("�з���", Font.PLAIN, 22));
		Labelweight.setBounds(74, 362, 182, 47);
		BMI_HealthCareSystem.getContentPane().add(Labelweight);
		
		Inputweight = new JTextField();
		Inputweight.setFont(new Font("�з���", Font.PLAIN, 20));
		Inputweight.setColumns(10);
		Inputweight.setBounds(285, 370, 129, 32);
		BMI_HealthCareSystem.getContentPane().add(Inputweight);
		
		LabelSex = new JLabel("\u8ACB\u9078\u64C7\u60A8\u7684\u6027\u5225\uFF1A");
		LabelSex.setFont(new Font("�з���", Font.PLAIN, 22));
		LabelSex.setBounds(74, 10, 182, 47);
		BMI_HealthCareSystem.getContentPane().add(LabelSex);
		
		//�U�Կ�榳�k�B�k����ܡA��ܩʧO�ӭp��өʧO�ҹ���������q�Ƽƭ�
		ComboBoxSex.setFont(new Font("�з���", Font.PLAIN, 20));
		ComboBoxSex.addItem("�k");
		ComboBoxSex.addItem("�k");
		ComboBoxSex.setBounds(285, 18, 129, 32);
		BMI_HealthCareSystem.getContentPane().add(ComboBoxSex);
		
		Label_cm = new JLabel("CM");
		Label_cm.setFont(new Font("�з���", Font.PLAIN, 20));
		Label_cm.setBounds(424, 327, 47, 24);
		BMI_HealthCareSystem.getContentPane().add(Label_cm);
		
		Label_kg = new JLabel("KG");
		Label_kg.setFont(new Font("�з���", Font.PLAIN, 20));
		Label_kg.setBounds(424, 374, 47, 24);
		BMI_HealthCareSystem.getContentPane().add(Label_kg);
		
		//�����s�p����s�����_��{���}�l���e���A���s�A�p��@������q�ơA�õ����A�������ΰ�����ĳ
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
		reset.setFont(new Font("�з���", Font.PLAIN, 20));
		reset.setBounds(123, 419, 119, 35);
		BMI_HealthCareSystem.getContentPane().add(reset);
		
		//�p����s�A�P�_���L��J���T�A�Y��J�ҥ��T�N�}�l�p�⨭��q�ơA�õ����A�������ΰ�������
		calculate = new JButton("\u8A08\u7B97");
		calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�p�G�S����ܤ�`���ʱj�׻P���ơA���"�п�ܤ�`���ʱj�׻P����"�����~�T��
				if(g.isSelected(null))
				{
					JOptionPane.showMessageDialog(null,"�п�ܤ�`���ʱj�׻P����!!!");
				}
				//�p�G�魫�B�����B�~�֬ҨS����J�A���"�п�J�z�������B�魫�P�~��!!!"�����~�T��
				if(Inputweight.getText().equals("")&&Inputheight.getText().equals("")&&Inputyear.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"�п�J�z�������B�魫�P�~��!!!");
				}
				//�p�G�魫�B�����ҨS����J�A���"�п�J�z�������P�魫!!!"�����~�T��
				else if(Inputweight.getText().equals("")&&Inputheight.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"�п�J�z�������P�魫!!!");
				}
				//�p�G�����B�~�֬ҨS����J�A���"�п�J�z�������P�~��!!!"�����~�T��
				else if(Inputheight.getText().equals("")&&Inputyear.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"�п�J�z�������P�~��!!!");
				}
				//�p�G�魫�B�~�֬ҨS����J�A���"�п�J�z���魫�P�~��!!!"�����~�T��
				else if(Inputweight.getText().equals("")&&Inputyear.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"�п�J�z���魫�P�~��!!!");
				}
				//�p�G�S����J�魫�A���"�п�J�z���魫!!!"�����~�T��
				else if(Inputweight.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"�п�J�z���魫!!!");
				}
				//�p�G�S����J�����A���"�п�J�z������!!!"�����~�T��
				else if(Inputheight.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"�п�J�z������!!!");
				}
				//�p�G�S����J�~�֡A���"�п�J�z���~��!!!"�����~�T��
				else if(Inputyear.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"�п�J�z���~��!!!");
				}
				//�p�G�Ҧ���J�A�h�}�l�p�⨭��q�ơA�õ����A�������ΰ�������
				else
				{
					bmi = new bmi();
					btnRun_Click();
				}
			}
		});
		calculate.setForeground(Color.RED);
		calculate.setFont(new Font("�з���", Font.PLAIN, 20));
		calculate.setBackground(Color.YELLOW);
		calculate.setBounds(285, 419, 85, 35);
		BMI_HealthCareSystem.getContentPane().add(calculate);
		txtOutput.setFont(new Font("�з���", Font.PLAIN, 20));
		
		
		txtOutput.setBounds(74, 464, 360, 120);
		BMI_HealthCareSystem.getContentPane().add(txtOutput);
		
		Labelyear = new JLabel("\u8ACB\u8F38\u5165\u60A8\u7684\u5E74\u9F61\uFF1A");
		Labelyear.setFont(new Font("�з���", Font.PLAIN, 22));
		Labelyear.setBounds(74, 265, 182, 47);
		BMI_HealthCareSystem.getContentPane().add(Labelyear);
		
		Inputyear = new JTextField();
		Inputyear.setFont(new Font("�з���", Font.PLAIN, 20));
		Inputyear.setColumns(10);
		Inputyear.setBounds(285, 273, 129, 32);
		BMI_HealthCareSystem.getContentPane().add(Inputyear);
		
		Label_year = new JLabel("\u6B72");
		Label_year.setFont(new Font("�з���", Font.PLAIN, 20));
		Label_year.setBounds(424, 274, 47, 31);
		BMI_HealthCareSystem.getContentPane().add(Label_year);
		
		Labelactivity = new JLabel("\u65E5\u5E38\u6D3B\u52D5\u5F37\u5EA6\u8207\u6307\u6578\uFF1A");
		Labelactivity.setFont(new Font("�з���", Font.PLAIN, 22));
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
		b0.setFont(new Font("�з���", Font.BOLD, 18));
		b0.setBounds(262, 67, 419, 23);
		BMI_HealthCareSystem.getContentPane().add(b0);
		
		//��`���ʱj�׻P���ƿ�ܨC�P���q�B��1-3�� -> �����ܼ�activity�]��1.375�A�H�K���U�Ӫ��p��
		b1 = new JRadioButton("\u6BCF\u5468\u8F15\u91CF\u904B\u52D51-3\u5929  TDEE=BMR x 1.375");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(b1.isSelected())
				{
					activity=1.375;
				}
			}
		});
		b1.setFont(new Font("�з���", Font.BOLD, 18));
		b1.setBounds(262, 106, 419, 23);
		BMI_HealthCareSystem.getContentPane().add(b1);
		
		//��`���ʱj�׻P���ƿ�ܨC�P���q�B��3-5�� -> �����ܼ�activity�]��1.55�A�H�K���U�Ӫ��p��
		b2 = new JRadioButton("\u6BCF\u5468\u4E2D\u91CF\u904B\u52D53-5\u5929  TDEE=BMR x 1.55");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(b2.isSelected())
				{
					activity=1.55;
				}
			}
		});
		b2.setFont(new Font("�з���", Font.BOLD, 18));
		b2.setBounds(262, 145, 419, 23);
		BMI_HealthCareSystem.getContentPane().add(b2);
		
		//��`���ʱj�׻P���ƿ�ܨC�P���j�B��6-7�� -> �����ܼ�activity�]��1.725�A�H�K���U�Ӫ��p��
		b3 = new JRadioButton("\u6BCF\u5468\u9AD8\u5F37\u904B\u52D56-7\u5929  TDEE=BMR x 1.725");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(b3.isSelected())
				{
					activity=1.725;
				}
			}
		});
		b3.setFont(new Font("�з���", Font.BOLD, 18));
		b3.setBounds(262, 187, 419, 23);
		BMI_HealthCareSystem.getContentPane().add(b3);
		
		//��`���ʱj�׻P���ƿ�ܨC�ѹB�ʰV�m�⦸ -> �����ܼ�activity�]��1.9�A�H�K���U�Ӫ��p��
		b4 = new JRadioButton("\u6BCF\u5929\u904B\u52D5\u8A13\u7DF4\u5169\u6B21   TDEE=BMR x 1.9");
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(b4.isSelected())
				{
					activity=1.9;
				}
			}
		});
		b4.setFont(new Font("�з���", Font.BOLD, 18));
		b4.setVerticalAlignment(SwingConstants.TOP);
		b4.setBounds(262, 224, 419, 24);
		BMI_HealthCareSystem.getContentPane().add(b4);
		
		//�إߤ@��ButtonGroup()����A���`���ʱj�׻P���ƪ��ﶵ��i�ӡA���ϥΪ̨C���p��u���ܤ@�ӿﶵ
		g= new ButtonGroup();
		g.add(b0);g.add(b1);g.add(b2);g.add(b3);g.add(b4);
		
		//TDEE�� <1500 ��������ĳ��
		cal1200 = new JLabel("New label");
		cal1200.setIcon(new ImageIcon("C:\\img\\cal1200.jpg"));
		cal1200.setBounds(728, 52, 345, 297);
		cal1200.setVisible(false);
		BMI_HealthCareSystem.getContentPane().add(cal1200);
		
		//TDEE�� 1500-1800(�p��) ��������ĳ��
		cal1500 = new JLabel("New label");
		cal1500.setIcon(new ImageIcon("C:\\img\\cal1500.jpg"));
		cal1500.setBounds(728, 52, 345, 297);
		cal1500.setVisible(false);
		BMI_HealthCareSystem.getContentPane().add(cal1500);
		
		//TDEE�� 1800-2000(�p��) ��������ĳ��
		cal1800 = new JLabel("New label");
		cal1800.setIcon(new ImageIcon("C:\\img\\cal1800.jpg"));
		cal1800.setBounds(728, 52, 345, 297);
		cal1800.setVisible(false);
		BMI_HealthCareSystem.getContentPane().add(cal1800);
		
		//TDEE�� 2000-2200(�p��) ��������ĳ��
		cal2000 = new JLabel("New label");
		cal2000.setIcon(new ImageIcon("C:\\img\\cal2000.jpg"));
		cal2000.setBounds(728, 52, 345, 297);
		cal2000.setVisible(false);
		BMI_HealthCareSystem.getContentPane().add(cal2000);
		
		//TDEE�� 2200-2500(�p��) ��������ĳ��
		cal2200 = new JLabel("New label");
		cal2200.setIcon(new ImageIcon("C:\\img\\cal2200.jpg"));
		cal2200.setBounds(728, 52, 345, 297);
		cal2200.setVisible(false);
		BMI_HealthCareSystem.getContentPane().add(cal2200);
		
		//TDEE�� 2500-2700(�p��) ��������ĳ��
		cal2500 = new JLabel("New label");
		cal2500.setIcon(new ImageIcon("C:\\img\\cal2500.jpg"));
		cal2500.setBounds(728, 52, 345, 297);
		cal2500.setVisible(false);
		BMI_HealthCareSystem.getContentPane().add(cal2500);
		
		//TDEE�� >=2700 ��������ĳ��
		cal2700 = new JLabel("New label");
		cal2700.setIcon(new ImageIcon("C:\\img\\\u71B1\u91CF\u5927\u5361\u8868-2700-1.jpg"));
		cal2700.setBounds(728, 52, 345, 297);
		cal2700.setVisible(false);
		BMI_HealthCareSystem.getContentPane().add(cal2700);
		
		Labelfoodcomment = new JLabel("\u60A8\u7684\u98F2\u98DF\u5EFA\u8B70\uFF1A");
		Labelfoodcomment.setFont(new Font("�з���", Font.BOLD, 22));
		Labelfoodcomment.setBounds(728, 10, 182, 34);
		BMI_HealthCareSystem.getContentPane().add(Labelfoodcomment);
		
		Label_fitness = new JLabel("\u60A8\u7684\u5065\u8EAB\u5EFA\u8B70\uFF1A");
		Label_fitness.setFont(new Font("�з���", Font.BOLD, 22));
		Label_fitness.setBounds(728, 418, 182, 34);
		BMI_HealthCareSystem.getContentPane().add(Label_fitness);
		
		comments_fitness = new JTextArea();
		comments_fitness.setFont(new Font("�з���", Font.PLAIN, 20));
		comments_fitness.setBounds(728, 464, 345, 120);
		BMI_HealthCareSystem.getContentPane().add(comments_fitness);
}		
	    //�p����s�A����p�⨭��q�ơA�õ����A�������ΰ�����ĳ
		protected void btnRun_Click() {
			
			//personal_height�ܼƦs���J�������A�r��ϥ�Double.parseDouble()�ର����ׯB�I�ƪ���ƫ��A
			double personal_height = Double.parseDouble(Inputheight.getText());
			//personal_weight�ܼƦs���J���魫�A�r��ϥ�Double.parseDouble()�ର����ׯB�I�ƪ���ƫ��A
			double personal_weight = Double.parseDouble(Inputweight.getText());
			//year�ܼƦs���J���~�֡A�r��ϥ�Double.parseDouble()�ର����ׯB�I�ƪ���ƫ��A
			double year = Double.parseDouble(Inputyear.getText());
	        
			//result_bmi�ܼơA�s��ϥ�bmi.java��count_bmi��k�A���J���������魫��i�h�A�p��bmi
			double result_bmi = bmi.count_bmi(personal_height,personal_weight);
			//result_healthyweight�ܼơA�s��ϥ�bmi.java��count_healthyweight��k�A���J��������i�h�A�p�ⰷ�d�魫
			double result_healthyweight=bmi.count_healthyweight(personal_height);
			//result_bmr_man�ܼơA�s��ϥ�bmi.java��count_bmr_man��k�A���J���~�֡B�魫�B������i�h�A�p��k�ͪ���¦�N�²v
			double result_bmr_man = bmi.count_bmr_man(year,personal_weight,personal_height);
			//result_bmr_girl�ܼơA�s��ϥ�bmi.java��count_bmr_girl��k�A���J���~�֡B�魫�B������i�h�A�p��k�ͪ���¦�N�²v
			double result_bmr_girl= bmi.count_bmr_girl(year,personal_weight,personal_height);
			//result_cal_man�ܼơA�s��ϥ�bmi.java��count_cal��k�A��p��X���k�ͪ���¦�N�²v�ο�ܪ���`���ʱj�׫��Ʃ�i�h�A�p��k�ͪ�TDEE(�C���`���Ӽ��q)
			double result_cal_man=bmi.count_cal(result_bmr_man,activity);
			//result_cal_girl�ܼơA�s��ϥ�bmi.java��count_cal��k�A��p��X���k�ͪ���¦�N�²v�ο�ܪ���`���ʱj�׫��Ʃ�i�h�A�p��k�ͪ�TDEE(�C���`���Ӽ��q)
			double result_cal_girl=bmi.count_cal(result_bmr_girl,activity);
			
			//�p�G�U�Կ���ܨk�ͫh����U�C�P�_��
			if(ComboBoxSex.getSelectedItem() == "�k")
			{
	            //��ܭ��p��X������q�ƼƭȡA�|�ˤ��J��ܨ�p���I��2��
				this.txtOutput.setText("�z��BMI���G"+Math.round(result_bmi*100.0)/100.0+"\n"+"�z��BMR(��¦�N�²v)���G"+Math.round(result_bmr_man*100.0)/100.0+"\n"+
				                       "�z�����d�魫���G"+Math.round(result_healthyweight*100.0)/100.0+"\n"+"�z��TDEE(�C���`���Ӽ��q)���G"+Math.round(result_cal_man*100.0)/100.0);
				//�ھڭp��X��TDEE(�C���`���Ӽ��q)�A��ܶ�����ĳ��
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
			//�p�G�U�Կ���ܤk�ͫh����U�C�P�_��
			else if(ComboBoxSex.getSelectedItem() == "�k")
			{
				//��ܭ��p��X������q�ƼƭȡA�|�ˤ��J��ܨ�p���I��2��
				this.txtOutput.setText("�z��BMI���G"+Math.round(result_bmi*100.0)/100.0+"\n"+"�z��BMR(��¦�N�²v)���G"+Math.round(result_bmr_girl*100.0)/100.0+"\n"+
						               "�z�����d�魫���G"+Math.round(result_healthyweight*100.0)/100.0+"\n"+"�z��TDEE(�C���`���Ӽ��q)���G"+Math.round(result_cal_girl*100.0)/100.0);
				//�ھڭp��X��TDEE(�C���`���Ӽ��q)�A��ܶ�����ĳ��
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
			
			//������ĳ�������ϥΤU�C�P�_���A��X�n��ܪ����e
			if(result_bmi<18.5)
			{
				this.comments_fitness.setText("�u�魫�L���v�A�ݭn�h�B�ʡA���Ŷ����A"+"\n"+"�H�W�[�魫�A�������d�I");
			}
			else if(result_bmi>=18.5&&result_bmi<24)
			{
				this.comments_fitness.setText("���ߡI�u���d�魫�v�A�n�~��O���@�I");
			}
			else if(result_bmi>=24&&result_bmi<27)
			{
				this.comments_fitness.setText("�@�I�u�魫�L���v�F�A�n�p���o�A����"+"\n"+"�O��u���d�魫�޲z�v�I");
			}
			else if(result_bmi>27)
			{
				this.comments_fitness.setText("��~�u�έD�v�A�ݭn�ߨ�O��"+"\n"+"�u���d�魫�޲z�v�o�I");
			}
		}
}
