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
		
		textField = new JTextField(30); // textfield�� â ũ�� ����
		textField.addActionListener(this);
		
		textArea = new JTextArea(10, 30);// textfield�� â ũ�� ����
		textArea.setEditable(false); //����ڰ� �ؽ�Ʈ�� �Է��� �� �ִ��� �������� �����ϰ� ��ȯ�Ѵ�.
		
		this.add(textField, BorderLayout.NORTH); //�Է�â�� ��ġ�� ���� ��������
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		this.add(scrollPane, BorderLayout.CENTER); //ǥ��â�� ��ġ�� ���� �߰�
		
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

		
	

