package WD_J;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class FileChooseDemo extends JFrame implements ActionListener{

	private JButton openBtn, saveBtn;
	JFileChooser fileChooser;
	
	public FileChooseDemo() {
		this.setTitle("���� ���ñ� �׽�Ʈ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(250, 200);
		fileChooser = new JFileChooser();
		
		JLabel label = new JLabel("���� ���ñ� ������Ʈ �׽�Ʈ�Դϴ�.");
		openBtn = new JButton("���Ͽ���");
		openBtn.addActionListener(this);
		
		saveBtn = new JButton("��������");
		saveBtn.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(label);
		panel.add(openBtn);
		panel.add(saveBtn);
		
		this.add(panel);
		this.setVisible(true);
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == openBtn) {
				int returnVal = fileChooser.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					System.out.println("open file : "+ file.getAbsolutePath());
				}else {
					System.out.println("No file selected");
				}
			}else if (e.getSource() == saveBtn) {
				int returnVal = fileChooser.showSaveDialog(this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					System.out.println("save to : "+ file.getAbsolutePath());
				}else {
					System.out.println("Save Canceled...");
				}
			}
		}
		public static void main(String[] args) {
			new FileChooseDemo();
		}
	}
	

