package HomeWork;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.Random;


public class HomeWorkBingoGame {

	static JPanel panelNorth; //���� �� view
	static JPanel panelCenter; //�߰� view
	static JLabel labelMessage; //�ؽ�Ʈ ǥ��
	static JButton[] buttons = new JButton[16];
	static String[] images = {
		"./image/chullo.png", "./image/coca-tea.png", "./image/llama.png", "./image/cemetery.png",
		"./image/hat.png", "./image/chicha-morada.png", "./image/lucuma.png", "./image/chakana.png",
		"./image/chullo.png", "./image/coca-tea.png", "./image/llama.png", "./image/cemetery.png",
		"./image/hat.png", "./image/chicha-morada.png", "./image/lucuma.png", "./image/chakana.png",
			//���� ���ӿ� �� �̹����� ����2���� �ʿ��ϹǷ� 8��X2
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
			this.setLayout(new BorderLayout()); //�����¿� ������ ����������
            this.setSize(400, 500); //���� ũ��
            this.setVisible(true); 
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            UIThings(this); // ȭ�鿡 ��Ÿ�� UI�޼ҵ�
            mixArt(); //Mix image card
            
            this.pack(); //����ִ� �������� �����
		}
		@Override
		public void actionPerformed(ActionEvent e) { //��ư Ŭ��
			
			if(openCount == 2) {
				return;//���� 2�� �̻� Ŭ���ϸ� ���̻� ������ �ʵ���
			}
			
			JButton btn = (JButton)e.getSource();  //��������ư 
			int index = getButtonIndex(btn); //�� ��° ĭ�� ������������ ��Ÿ��
			//System.out.println(index);
			btn.setIcon(changeImage(images[index])); //��ư�� ������ �̹����� ȣ���� 
			
			openCount++;
			if(openCount == 1) { //ù��° ī������
				buttonIndexSave1 = index;
			}
			else if(openCount == 2) { //�ι��� ī������
				buttonIndexSave2 = index;
				tryCount++;
				labelMessage.setText("Look for the same pictures! " + "  tryCount : " + tryCount);
				
				//Judge Logic ���� ����
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
				if(buttons[i] == btn) { // ���� �ν��Ͻ�?
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
		panelNorth.setPreferredSize(new Dimension(400, 100)); //ũ�⸦ �����ϴ� ��ɾ�
		panelNorth.setBackground(Color.magenta);
		labelMessage = new JLabel("Look for the same pictures! " + "  tryCount : " + tryCount);
		labelMessage.setPreferredSize(new Dimension(400, 100));
		labelMessage.setForeground(Color.white); //���ڻ� ����
		labelMessage.setFont(new Font("NanumBarunpenR", Font.BOLD, 17));
		labelMessage.setHorizontalAlignment(JLabel.CENTER); //���� ���� ��ġ
		panelNorth.add(labelMessage);
		myFrame.add("North", panelNorth);
		
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(4, 4)); //�׸��� �� ����
		panelCenter.setPreferredSize(new Dimension(400,400));
		for(int i = 0; i<16 ; i++) {
			buttons[i] = new JButton();
			buttons[i].setPreferredSize(new Dimension(100,100)); //��ĭ�� ũ��
			buttons[i].setIcon(changeImage("./image/help.png")); 
			buttons[i].addActionListener(myFrame);
			panelCenter.add(buttons[i]);
		}
		myFrame.add("Center", panelCenter);
		
	}

	static ImageIcon changeImage(String filename) { //imageIcon�� ���ϰ�
		ImageIcon icon = new ImageIcon("./" + filename);
		Image originImage = icon.getImage();
		Image changedImage = originImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH); //�̹��� ũ�� ����
		ImageIcon icon_new = new ImageIcon(changedImage);
		return icon_new;
	} 
	
	public static void main(String[] args) {
		new MyFrame("Bingo Game");
	}

}
