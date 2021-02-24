package HomeWork;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.Random;


public class HomeWorkBingoGame {

	static JPanel panelNorth; //제일 위 view
	static JPanel panelCenter; //중간 view
	static JLabel labelMessage; //텍스트 표시
	static JButton[] buttons = new JButton[16];
	static String[] images = {
		"./image/chullo.png", "./image/coca-tea.png", "./image/llama.png", "./image/cemetery.png",
		"./image/hat.png", "./image/chicha-morada.png", "./image/lucuma.png", "./image/chakana.png",
		"./image/chullo.png", "./image/coca-tea.png", "./image/llama.png", "./image/cemetery.png",
		"./image/hat.png", "./image/chicha-morada.png", "./image/lucuma.png", "./image/chakana.png",
			//빙고 게임에 들어갈 이미지들 각각2개가 필요하므로 8개X2
	};
	static int openCount = 0; //Opened Count
	static int buttonIndexSave1 = 0; //First Opened Card Index: 0~15
	static int buttonIndexSave2 = 0; //Second Opened Card Index: 0~15
	static Timer timer;
	static int tryCount = 0; //Try Count
	static int successCount = 0; //Bingo Count : 0~8
	
	static class MyFrame extends JFrame implements ActionListener{
		public MyFrame(String title) {
			super(title); 
			this.setLayout(new BorderLayout()); //상하좌우 구역이 나누어진것
            this.setSize(400, 500); //게임 크기
            this.setVisible(true); 
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            UIThings(this); // 화면에 나타날 UI메소드
            mixArt(); //Mix image card
            
            this.pack(); //비어있는 공간들을 잡아줌
		}
		@Override
		public void actionPerformed(ActionEvent e) { //버튼 클릭
			
			if(openCount == 2) {
				return;//연속 2번 이상 클릭하면 더이상 열리지 않도록
			}
			
			JButton btn = (JButton)e.getSource();  //눌려진버튼 
			int index = getButtonIndex(btn); //몇 번째 칸이 눌려졌는지를 나타냄
			//System.out.println(index);
			btn.setIcon(changeImage(images[index])); //버튼을 누르면 이미지를 호출함 
			
			openCount++;
			if(openCount == 1) { //첫번째 카드인지
				buttonIndexSave1 = index;
			}
			else if(openCount == 2) { //두번쨰 카드인지
				buttonIndexSave2 = index;
				tryCount++;
				labelMessage.setText("Look for the same pictures! " + "  tryCount : " + tryCount);
				
				//Judge Logic 판정 로직
				boolean isBingo = checkCard(buttonIndexSave1, buttonIndexSave2);
				if(isBingo == true) {
					openCount = 0;
					successCount++;
					if(successCount == 8) {
						labelMessage.setText("Game Over " + " Try " + tryCount);
					}
				}else {
					backToQuestion();
				}
			}
		}
		
		public void backToQuestion() {
			//timer 1Second
			timer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Timer.");
					
					openCount = 0;
					buttons[buttonIndexSave1].setIcon(changeImage("./image/help.png"));
					buttons[buttonIndexSave2].setIcon(changeImage("./image/help.png"));
					timer.stop();
				}
			});
			timer.start();
		}
		
		public boolean checkCard(int index1, int index2) {
			if(index1 == index2) {
				return false;
			}
			if(images[index1].equals(images[index2])) {
				return true;
			}else {
				return false;
			}
		}
		
		public int getButtonIndex(JButton btn) { //
			int index = 0;
			for(int i = 0; i < 16; i++) {
				if(buttons[i] == btn) { // 같은 인스턴스?
					index = i;
				}
			}
			return index;
		}
	}
	static void mixArt() {
		Random rand = new Random();
		for(int i = 0; i<1000; i++) {
			int random = rand.nextInt(16); //1~16 card
			String temp = images[0]; //swap
			images[0] = images[random];
			images[random] = temp;
		}
	}
	
	static void UIThings(MyFrame myFrame) {
		panelNorth = new JPanel();
		panelNorth.setPreferredSize(new Dimension(400, 100)); //크기를 지정하는 명령어
		panelNorth.setBackground(Color.magenta);
		labelMessage = new JLabel("Look for the same pictures! " + "  tryCount : " + tryCount);
		labelMessage.setPreferredSize(new Dimension(400, 100));
		labelMessage.setForeground(Color.white); //글자색 변경
		labelMessage.setFont(new Font("NanumBarunpenR", Font.BOLD, 17));
		labelMessage.setHorizontalAlignment(JLabel.CENTER); //수평 정렬 배치
		panelNorth.add(labelMessage);
		myFrame.add("North", panelNorth);
		
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(4, 4)); //그림이 들어갈 공간
		panelCenter.setPreferredSize(new Dimension(400,400));
		for(int i = 0; i<16 ; i++) {
			buttons[i] = new JButton();
			buttons[i].setPreferredSize(new Dimension(100,100)); //한칸당 크기
			buttons[i].setIcon(changeImage("./image/help.png")); 
			buttons[i].addActionListener(myFrame);
			panelCenter.add(buttons[i]);
		}
		myFrame.add("Center", panelCenter);
		
	}

	static ImageIcon changeImage(String filename) { //imageIcon은 리턴값
		ImageIcon icon = new ImageIcon("./" + filename);
		Image originImage = icon.getImage();
		Image changedImage = originImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH); //이미지 크기 설정
		ImageIcon icon_new = new ImageIcon(changedImage);
		return icon_new;
	} 
	
	public static void main(String[] args) {
		new MyFrame("Bingo Game");
	}

}
