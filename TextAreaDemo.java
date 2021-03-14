package WD_J;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextAreaDemo extends JFrame implements ActionListener{

	private JTextField textField;
	private JTextArea textArea;
	
	public TextAreaDemo() {
		this.setTitle("Text Area Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField(30); // textfield의 창 크기 설정
		textField.addActionListener(this);
		
		textArea = new JTextArea(10, 30);// textfield의 창 크기 설정
		textArea.setEditable(false); //사용자가 텍스트를 입력할 수 있는지 없는지를 설정하고 반환한다.
		
		this.add(textField, BorderLayout.NORTH); //입력창의 위치를 설정 위쪽으로
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		this.add(scrollPane, BorderLayout.CENTER); //표시창의 위치를 설정 중간
		
		this.pack();
		this.setVisible(true);
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			String text = textField.getText();
			textArea.append(text + "\n");
			
			textField.selectAll();
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
		
		public static void main(String[] args) {
			new TextAreaDemo();
		}
	}

		
	

