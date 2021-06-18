package ToDayStudy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

public class JTest extends JFrame {

	TetrisPanel TP = new TetrisPanel();
	JLabel scoreInt = new JLabel();
	JLabel scoreString = new JLabel();
	TetrisThread tThread;

	//JDialog JD = new JDialog(); // �絵��

	private Image background = new ImageIcon("KakaoTalk_20210604_203448786.png").getImage();

	static int blocksize = 30;
	static int garoKan = 15;
	static int seroKan = 19;
	static int scoreSum = 0;

	int randomColor;

	int End = 0;
	int random = 0, random2 = (int) (Math.random() * 7);

	private Color[] colors = {Color.decode("#FF5675"), Color.decode("#3DFF92"), Color.decode("#FFEB5A"), 
			Color.decode("#B2FA5C"), Color.decode("#00D7FF"), Color.decode("#FFA98F"), Color.decode("#00FFFF")};
	int score = 0;

	int wid = 100;
	int hgt = 0;
	int rotation = 0;

	boolean limit = false;

	// ��ϵ��� ��ǥ ����
	int curX[] = new int[4], curY[] = new int[4];

	int blocks[][][][] = { {
		// ��
		// ����
		{ { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
		{ { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
		{ { 1, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } },
		{ { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } } },

			{

			// ��
			// ����
			{ { 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
			{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
			{ { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 1, 0, 0, 0 }, { 0, 0, 0, 0 } },
			{ { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } },},

			{
				// ���
				// ���
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } } },

			{
					// �����
					{ { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } },
					{ { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } },
					{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } } },

			{
						// ��
						// ����
						{ { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
						{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
						{ { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
						{ { 0, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } } },

			{
							// ���
							// ���
							{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
							{ { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
							{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
							{ { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } } },

			{
								// ���
								// ���
								{ { 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
								{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } },
								{ { 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
								{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } } } };

	int[][] gameboard = { 

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },

			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } 

	};

	JButton btn = new JButton("�絵��");
	JLabel lbl = new JLabel();
	JLabel lbl2 = new JLabel();

	JTest() {
		setTitle("��Ʈ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setVisible(true);

//		JD.setTitle("����");
//		JD.setSize(250,190);
//		JD.setLayout(new FlowLayout(FlowLayout.CENTER, 1050, 40));

		lbl.setFont(new Font("arial",Font.PLAIN,15));
		lbl2.setText("��  ��");
		lbl2.setFont(new Font("�������",Font.PLAIN,15));

		tThread = new TetrisThread();

		// �г� ������
		TP.setSize(1280, 720);
		add(TP);

		scoreInt.setFont(new Font("arial", Font.PLAIN, 40));
		scoreInt.setForeground(Color.white);

		// Ű �Է�
		TP.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				int keyCode = e.getKeyCode();

				if (keyCode == KeyEvent.VK_UP) {
					TP.moveUp();
				}

				if (keyCode == KeyEvent.VK_DOWN) {
					TP.moveDown();
				}

				if (keyCode == KeyEvent.VK_LEFT) {
					TP.moveLeft();
				}

				if (keyCode == KeyEvent.VK_RIGHT) {
					TP.moveRight();
				}

			}
		});

		btn.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				limit = false;
				for(int y=0; y<19;y++)
					for(int x=1; x<12; x++)
						gameboard[y][x] =0 ;
				score =0;
				wid =100; hgt = 0;
			}
		});
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		//JD.setLocation((screenSize.width - frameSize.width)/2 + 220, (screenSize.height - frameSize.height)/2 +220);

		// �г��� ������ �� ���, Ű �Է��� ���� �޵��� ����
		TP.requestFocus(true);

		// ������ ����
		tThread.start();
	}

	class TetrisPanel extends JPanel {
		public void paintComponent(Graphics g) {

			int cnt = 0, cnt2 = 0;
			TP.requestFocus(true);
			super.paintComponent(g);

			scoreInt.setLocation(1050, 190);
			TP.add(scoreInt);
			scoreInt.setText(Integer.toString(scoreSum+score)); 


			switch (randomColor) {
			case 0 : {
				g.setColor(Color.decode("#FF5675")); // ���� �������� ��,�̸����� �� ����
				break;
			}
			case 1 : {
				g.setColor(Color.decode("#3DFF92")); 
				break;
			}case 2 : {
				g.setColor(Color.decode("#FFEB5A")); 
				break;
			}case 3 : {
				g.setColor(Color.decode("#B2FA5C")); 
				break;
			}case 4 : {
				g.setColor(Color.decode("#00D7FF")); 
				break;
			}case 5 : {
				g.setColor(Color.decode("#FFA98F")); 
				break;
			}case 6 : {
				g.setColor(Color.decode("#00FFFF")); 
				break;
			}

			}

			//         lbl.setLocation(360,145);
			//         TP.add(lbl);
			//         lbl.setText(Integer.toString(score*200));

			//g.setColor(Color.blue); // ���� �������� ��,�̸����� �� ����
			g.drawImage(background, 0, 0, null);

			// ���� ���� ���� ���
			blockLookAhead(g);

			// ���� õ�忡 ������ ���� ����
			gameOverCheck();

			// �� ���� ��� ������� ä���� ��� ��ϵ� ����(ä���������� ��� ��� ����������)
			removeBlock(cnt, cnt2, g);

			// ����� ���� �����ϸ� ���->������ ��ȯ(�������� ��� �ʱ�ȭ)
			blockToWall();

			// ���� ����
			makeBlock(g);

			// �׵θ� ����
			makeBorder(g);

			if (End == 1) {
				random2 = (int) (Math.random() * 7);
				End = 0;
			}
		}


		// ���� ���� ���� ���
		public void blockLookAhead(Graphics g) {
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					if (blocks[random2][0][a][b] == 1) {
						g.fill3DRect(b * blocksize + 1050, a * blocksize + 425, blocksize, blocksize, true);
						randomColor = (int) (Math.random() * 7) ;
					}
				}
			}
		}


		// ���� õ�忡 ������ ���� ����  �̺κ� ����
		public void gameOverCheck() {
			for (int x = 1; x < 14 ; x++) {
				if (gameboard[2][x] == 1) {
					limit = true;
					// ���ӿ��� �г� �̹���
					//lbl.setLocation(50,50);
					//JD.add(lbl);
					//JD.add(btn);
					//btn.setLocation(50,30);
					//JD.setVisible(true);

				}
			}

		}

		// �� ���� ��� ������� ä���� ��� ��ϵ� ����(ä���������� ��� ��� ����������)
		public void removeBlock(int cnt, int cnt2, Graphics g) {
			for (int y = 0; y < 19 ; y++) {
				for (int x = 1; x < 16 ; x++) {
					if (gameboard[y][x] == 1) {
						cnt2++;
					}
				}
				if (cnt2 == 15) {
					for (int i = y ; i > 1 ; i--)
						for (int j = 1; j < 16 ; j++) {
							gameboard[i][j] = 0;
							gameboard[i][j] = gameboard[i - 1][j];
						}
					//score++;
					scoreSum += 500;

					// ����� ���� ���ϴ� �� ���� ����


					// ���� ��� ���� �� �� ���ؾ� ��

				} else {
					blockDown(cnt, g); // �� ���� ��� ������� ä������ ���� ���� ����� ���������� ��
				}
				cnt2 = 0;
			}
		}


		// ���� �� ��� 
		public void makeBlock(Graphics g) {

			g.setColor(Color.GRAY);
			for (int y = 0 ; y < 19 ; y++) {
				for (int x = 1 ; x <= 15 ; x++) {
					if (gameboard[y][x] == 1) {
						g.fill3DRect( ( x * blocksize ) + 380, y * blocksize + 75, blocksize, blocksize, true);
					}
				}
			}


			//scoreSum += 100;
		}




		// �������� ���
		public void blockDown(int cnt, Graphics g) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					if (blocks[random][rotation][j][k] == 1) {
						curX[cnt] = ((k * blocksize) + wid) / blocksize;
						curY[cnt] = ((j * blocksize) + hgt) / blocksize;// curX,Y[0][1][2][3]�� ��ǥ 4�� ����

						// ��ġ ����

						g.fill3DRect(( curX[cnt] * blocksize ) + 380, curY[cnt] * blocksize + 75, blocksize, blocksize,
								true);

						cnt++;
					}
				}
			}
		}




		// ����� ���� �����ϸ� ���->������ ��ȯ(�������� ��� �ʱ�ȭ)
		// �������� ����� ���� �Ǵ��� �˻�
		// ���� �Ǹ� wid=120, hgt=0 ���� ��� �ʱ�ȭ, rotation�� �ʱ�ȭ
		public void blockToWall() {
			try {
				for (int z = 0; z < 4; z++)
					if (gameboard[curY[z] + 1][curX[z]] == 1)
						for (int j = 0; j < 4; j++) {

							gameboard[curY[j]][curX[j]] = 1;
							wid = 100;
							hgt = 0;
							End = 1;
							rotation = 0;
							random = random2;

						}

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Error");

				for (int i = 0; i < 4; i++) {
					System.out.print("(" + curY[i] + "," + curX[i] + ")");
				}

				System.out.println("yal");
			}

		}

		// ���� ���� �浹�ϸ� �������̵���
		public int collision_LEFT() {
			for (int i = 0; i < 4; i++) {
				if (gameboard[curY[i]][curX[i] - 1] == 1) // �浹�� 1 ��ȯ
					return 1;
			}
			return 0; // �浹���� ������ 0 ��ȯ
		}

		// ������ ���� �浹�ϸ� �� �����̵���
		public int collision_RIGHT() {

			for (int i = 0; i < 4; i++) {
				if (gameboard[curY[i]][curX[i] + 1] == 1) // �浹�� 1��ȯ
					return 1;
			}
			return 0; // �浹���� ������ 0��ȯ
		}

		// curX,Y�� ���� ȸ�� ������ ������ǥ�� ��� ����صΰ�, ���� �������̳� ���� X��ǥ1,2ĭ �ȿ� ���� ������ �׸�ŭ ������ Ȥ��
		// �������� �о ��ġ
		public void rotationCheck() {
			// curX,Y�� ���� ȸ�� ������ ������ǥ�� ��� ����صΰ�, �ؿ� �������� �� ������ǥ�� ���� ���� ����� �Ǵ�
			int cnt2 = 0;
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					int rotation2 = (rotation % 4) + 1;
					if (rotation2 == 4)
						rotation2 = 0;
					if (blocks[random][rotation2][j][k] == 1) {
						curX[cnt2] = ((k * blocksize) + wid) / blocksize;
						curY[cnt2] = ((j * blocksize) + hgt) / blocksize;
						cnt2++;
					}
				}
			}

			// curX,Y�� ����� ��ǥ�� �̿�
			int chk = 0;
			int blank = 0;
			int error = 0;

			// ���� ��
			if (gameboard[curY[0]][curX[0]] == 1 || (random == 6 && gameboard[curY[2]][curX[2]] == 1)
					|| (random == 1 && gameboard[curY[1]][curX[1]] == 1)) {
				chk = 1; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=1�� ǥ����
				error++;
				System.out.println("chk1");

				if (random == 3) { // ���ڸ����� ��� ȸ���� ������ �ִ� ������ ������ ȸ������
					for (int i = 1; i < 5; i++) {
						if (gameboard[curY[0]][curX[0] + i] == 0) {
							blank++;
						}
					}

					if (blank < 4) {
						chk = 4;
					}

					System.out.println(blank);
				} else { // �� ���� ��� ȸ���� ������ ���� ������ ������ ȸ�� ����
					for (int i = 1; i < 4; i++)
						if (gameboard[curY[0]][curX[0] + i] == 0)
							blank++;
					if (blank < 3)
						chk = 4;
					System.out.println("blank2");
					System.out.println(blank);
				}

			}

			// ������ ��
			else if (gameboard[curY[2]][curX[2]] == 1) {
				error++;
				chk = 2; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=2�� ǥ����
				System.out.println("chk2");

				for (int i = 1; i < 5; i++)
					if (gameboard[curY[2]][curX[2] - i] == 0)
						blank++;
				if (blank < 4)
					chk = 4;
				System.out.println("blank2");
				System.out.println(blank);

			} else if (gameboard[curY[3]][curX[3]] == 1) {
				error++;
				chk = 3; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� chk=3�� ǥ����
				System.out.println("chk3");
				for (int i = 0; i < 5; i++)
					if (gameboard[curY[3]][curX[3] - i] == 0)
						blank++;
				if (blank < 4)
					chk = 4;
				System.out.println(blank);

			}

			if (chk == 1) {
				// chk = 1(���� ȸ���� ������ ��ġ�� ���� �ߺ��Ǹ�)�� wid(����)�� 30�̵�
				wid += blocksize;
				rotation++;
				rotation = rotation % 4;
			} else if (chk == 2) {
				wid -= blocksize * 2;
				rotation++;
				rotation = rotation % 4;
			} else if (chk == 3) {
				wid -= blocksize;
				rotation++;
				rotation = rotation % 4;
			} else if (chk == 4) {
				System.out.println("ban");
			} else {
				rotation++;
				rotation = rotation % 4;
			}

		}


		// ��ġ ���� �ٽ��ؾ���

		// board �׵θ�
		public void makeBorder(Graphics g) {
			//         g.setColor(Color.GRAY);
			//
			//         for(int row =  0 ; row <= seroKan ; row++) { // ������ x 30 ���� ���� (��1�� ��)
			//            g.drawLine(410, blocksize * row + 105, blocksize * 22 + 210, blocksize * row + 105 );
			//         }
			//
			//         for(int col = 0; col <= garoKan ; col++) { // ������, y 180 ���� ���� (��6�� ��)
			//            g.drawLine((col * blocksize) + 410, blocksize * 3, (col * blocksize) + 410, (blocksize * 3) + (blocksize *seroKan) );
			//         }
			g.setColor(Color.GRAY);
			for(int row =  0 ; row < seroKan; row++) { // ������ x 30 ���� ���� (��1�� ��)
				g.drawLine(410, blocksize * row + 105 , blocksize * 15 + 410, blocksize * row + 105);
			}

			for(int col = 0; col < garoKan+1; col++) { // ������, y 180 ���� ���� (��6�� ��)
				g.drawLine(col * blocksize + 410, 105, col * blocksize + 410, blocksize * 17 + 135);
			}
		}

		// ���η� �� �� �� ���� 15ĭ
		// ���η� �� �� �� ���� 19 ĭ

		void down() {
			hgt += blocksize;
			TP.repaint();
		}

		void moveUp() {
			rotationCheck();
			if (limit == false) {
				repaint();
			}
		}

		void moveDown() {
			if (limit == false) {
				hgt += blocksize;
				TP.repaint();
			}
		}

		void moveLeft() {
			int sel = collision_LEFT();

			// sel�� 1�̸� �浹, 0�̸� �浹X
			if (sel == 0 && limit == false) {
				wid -= blocksize;
				TP.repaint();
			}
		}

		void moveRight() {
			int sel = collision_RIGHT();

			// sel�� 1�̸� �浹, 0�̸� �浹X
			if (sel == 0 && limit == false) {
				wid += blocksize;
				TP.repaint();
			}
		}
	}

	class TetrisThread extends Thread {
		TetrisPanel TP = new TetrisPanel();

		public void run() {
			while (true) {
				try {
					sleep(500);
					if (limit == false) // limit�� false�� ��쿡�� �۵�. true�� �Ǹ� ��Ʈ���� �۵�����
						TP.down();
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		new JTest();

	} }