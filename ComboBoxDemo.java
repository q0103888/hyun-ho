package WD_J;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ComboBoxDemo extends JFrame implements ActionListener{

	private JLabel label;
	private JComboBox animalList;
	
	public ComboBoxDemo() {
		
	this.setTitle("�޺� �ڽ�");
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	this.setSize(500, 400);
	
	String[] animals = {"dog", "lion", "tiger", "cat"};
	animalList = new JComboBox(animals);
	animalList.setSelectedIndex(0);
	animalList.addActionListener(this);
	
	label = new JLabel();
	label.setHorizontalAlignment(SwingConstants.CENTER);
	changePicture(animals[animalList.getSelectedIndex()]);
	
	this.add(animalList, BorderLayout.NORTH);
	this.add(label, BorderLayout.CENTER);
	this.setVisible(true);
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		String name = (String) animalList.getSelectedItem();
		changePicture(name);
	}
	
	protected void changePicture(String name) {
		ImageIcon icon = new ImageIcon("download.jpg");
		label.setIcon(icon);
		label.setText(null);
	}
	public static void main(String[] args) {
		new ComboBoxDemo();
	}	
}
