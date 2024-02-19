import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class game_guess_ab {

	//程式畫面
	private JFrame game_guess_ab;
	//建立程式物件guessab，使得本程式可以使用guess_ab.java的方法
	private guess_ab guessab;
	
	private JTextField txtInput;
	private JTextArea txtOutput;
	private JLabel guesscount;
	private JButton run;
	private JButton restart;
	//全域變數count-計算共玩了幾次遊戲，每一回合限制最多只能玩10次
	static int count=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					game_guess_ab window = new game_guess_ab();
					window.game_guess_ab.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public game_guess_ab() {
		//程式畫面
		initialize();
		//建立guessab物件，使得此window程式可以使用guess_ab.java的方法
		guessab=new guess_ab();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		game_guess_ab = new JFrame("幾A幾B猜數字遊戲");
		game_guess_ab.getContentPane().setBackground(Color.PINK);
		game_guess_ab.setBounds(450, 50,500, 600);
		game_guess_ab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_guess_ab.getContentPane().setLayout(null);
		
		JLabel Label_hint = new JLabel("\u8ACB\u8F38\u5165\u56DB\u500B\u6578\u5B57\uFF1A");
		Label_hint.setFont(new Font("微软雅黑", Font.BOLD, 20));
		Label_hint.setBounds(27, 28, 169, 57);
		game_guess_ab.getContentPane().add(Label_hint);
		
		JLabel Label_Intro = new JLabel("(\u6578\u5B57\u5C0D\u4E14\u4F4D\u7F6E\u6B63\u78BA\u5F97A\uFF0C\u4F4D\u7F6E\u932F\u8AA4\u5F97B)");
		Label_Intro.setFont(new Font("微软雅黑", Font.BOLD, 16));
		Label_Intro.setBounds(27, 84, 302, 46);
		game_guess_ab.getContentPane().add(Label_Intro);
		
		guesscount = new JLabel("\u5DF2\u731C\u4E860\u6B21");
		guesscount.setFont(new Font("微软雅黑", Font.BOLD, 18));
		guesscount.setBounds(334, 516, 107, 23);
		game_guess_ab.getContentPane().add(guesscount);
		
		txtInput = new JTextField();
		txtInput.setFont(new Font("微软雅黑", Font.BOLD, 15));
		txtInput.setBounds(195, 35, 134, 47);
		game_guess_ab.getContentPane().add(txtInput);
		txtInput.setColumns(10);
		
		txtOutput = new JTextArea();
		txtOutput.setFont(new Font("微软雅黑", Font.BOLD, 16));
		txtOutput.setBounds(53, 163, 371, 319);
		game_guess_ab.getContentPane().add(txtOutput);
		
		//重新開始按紐，重設程式，並重新開始一次新的遊戲
		restart = new JButton("重新開始");
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtOutput.setText("");
				txtInput.setText("");
				count=0;
				guesscount.setText("已猜了0次");
				restart.setVisible(false);
				run.setVisible(true);
				guessab=new guess_ab();
			}
		});
		restart.setForeground(Color.RED);
		restart.setBackground(Color.YELLOW);
		restart.setFont(new Font("微软雅黑", Font.BOLD, 16));
		restart.setBounds(373, 38, 101, 38);
		restart.setVisible(false);
		game_guess_ab.getContentPane().add(restart);
		
		run = new JButton("Run");
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//一回合的遊戲最多只能玩10次，如果沒有猜超過10次，則能繼續玩遊戲
				if(count<10)
				{
					btnRun_Click();
				}
				//若玩了10次，則顯示不能再繼續猜，遊戲結束，並顯示此回合遊戲的正確答案，隱藏run按紐，並顯示重新開始按鈕，若按下重新開始按鈕則開始一次新的遊戲
				if(count==10) 
				{
					txtOutput.append("您已猜了10次，不能再繼續猜了，遊戲結束"+"\n"+"正確答案為："+guessab.getAnswer());
					run.setVisible(false);
					restart.setVisible(true);
				}
			}	
		});
		run.setForeground(Color.RED);
		run.setBackground(Color.YELLOW);
		run.setFont(new Font("微软雅黑", Font.BOLD, 15));
		run.setBounds(373, 38, 101, 38);
		game_guess_ab.getContentPane().add(run);
		
		
		
	}
		//run按鈕，執行幾a幾b遊戲
		protected void btnRun_Click() {
			//ans字串變數，存放輸入的數字
			String ans=txtInput.getText();
			//result整數陣列，存放使用guess_ab.java的guess方法，把ans變數(輸入的數字)放進去，判斷幾A幾B的結果
			int result[]=guessab.guess(ans);
			//計算玩遊戲的次數
			count++;
		    
			//result[0]為A，result[1]為B，如果輸入的數字為正解則為4A0B，顯示答對，並結束遊戲，隱藏run按鈕，並顯示重新開始按鈕，重新開始一個新的遊戲
			if(result[0]==4&&result[1]==0) 
			{
				this.txtOutput.append("恭喜你，答對了!!!"+"\n"+"遊戲結束");
				run.setVisible(false);
				restart.setVisible(true);
			}
			//若輸入的數字非正解，則顯示判斷結果為幾A幾B
			else
			{
				this.txtOutput.append("判斷結果為："+ result[0] +"A" + result[1] +"B"+"\n");
			}
			//顯示已猜了幾次
			guesscount.setText("已猜了"+count+"次");
			
	}
}