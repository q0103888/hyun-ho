package WD_J;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PIzzaOrderDemo extends JFrame implements ActionListener{
	private int sum, temp1, temp2, temp3;
	
	private JButton orderBtn, cancelBtn;
	private JPanel orderPanel;
	private JTextField priceField;
	
	JPanel welcomePanel = new WelcomePanel();
	
	JPanel typePanel = new TypePanel();
	JPanel toppingPanel = new ToppingPanel();
	JPanel sizePanel = new SizePanel();

	public PIzzaOrderDemo() {
		this.setSize(500, 200);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("���� �ֹ�");
		
		orderBtn = new JButton("�ֹ�");
		orderBtn.addActionListener(this);
		
		cancelBtn = new JButton("���");
		cancelBtn.addActionListener(this);
		
		priceField = new JTextField();
		priceField.setEditable(false);
		priceField.setColumns(6);
		
		orderPanel = new JPanel();
		orderPanel.add(orderBtn);
		orderPanel.add(cancelBtn);
		orderPanel.add(priceField);
		
		this.add(welcomePanel, BorderLayout.NORTH);
		this.add(orderPanel, BorderLayout.SOUTH);
		this.add(sizePanel, BorderLayout.EAST);
		this.add(typePanel, BorderLayout.WEST);
		this.add(toppingPanel, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == orderBtn) {
			 //System.out.println("activity");
			sum = (temp1+temp2+temp3);
			priceField.setText(String.valueOf(sum+"��"));
			//System.out.println("temp1 :"+temp1 +", temp2 :"+temp2+", temp3 :"+temp3 );
			System.out.println("���� : " + (temp1+temp2+temp3));
		}else if (e.getSource()== cancelBtn) {
			temp1 = 0;
			temp2 = 0;
			temp3 = 0;
			sum = 0;
			priceField.setText(String.valueOf(sum));
		}
	}
	
	class WelcomePanel extends JPanel{
		private JLabel message;
		
		public WelcomePanel() {
			message = new JLabel("�ڹ� ���ڿ� ���Ű��� ȯ���մϴ�.");
			this.add(message);
		}
	}
	class TypePanel extends JPanel implements ChangeListener {
		private JRadioButton combo, potato, bulgogi;
		private ButtonGroup btnGroup;
		
		public TypePanel() {
			this.setLayout(new GridLayout(3, 1));
			combo = new JRadioButton("�޺�",true);
			combo.addChangeListener(this);
			potato = new JRadioButton("��������");
			potato.addChangeListener(this);
			bulgogi = new JRadioButton("�Ұ��");
			bulgogi.addChangeListener(this);
			
			btnGroup = new ButtonGroup();
			btnGroup.add(combo);
			btnGroup.add(potato);
			btnGroup.add(bulgogi);
			
			this.setBorder(BorderFactory.createTitledBorder("����"));
			
			this.add(combo);
			this.add(potato);
			this.add(bulgogi);
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			if(e.getSource() == combo) {
				temp1 = 13000;
			}else if (e.getSource() == potato) {
				temp1 = 14000;
			}else {
				temp1 = 15000;
			}
		}
	}
	
	class ToppingPanel extends JPanel implements ChangeListener {
		private JRadioButton pepper, cheese, peperoni, bacon;
		
		private ButtonGroup btnGroup;
		
		public ToppingPanel() {
			this.setLayout(new GridLayout(4,1));
			
			pepper = new JRadioButton("�Ǹ�",true);
			pepper.addChangeListener(this);
			cheese = new JRadioButton("ġ��");
			cheese.addChangeListener(this);
			peperoni = new JRadioButton("���۷δ�");
			peperoni.addChangeListener(this);
			bacon = new JRadioButton("������");
			bacon.addChangeListener(this);
			
			btnGroup = new ButtonGroup();
			btnGroup.add(pepper);
			btnGroup.add(cheese);
			btnGroup.add(peperoni);
			btnGroup.add(bacon);
			
			this.setBorder(BorderFactory.createTitledBorder("�߰�����"));
				
			this.add(pepper);
			this.add(cheese);
			this.add(peperoni);
			this.add(bacon);
			
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			if(e.getSource() == pepper) {
				temp2 = 0;
			}else if (e.getSource() == cheese) {
				temp2 = 2000;
			}else if (e.getSource() == peperoni) {
			    temp2 = 1500;
			}else if (e.getSource() == bacon) {
				temp2 = 2000;
			}
		}
	}
	
	class SizePanel extends JPanel implements ChangeListener {
		private JRadioButton small, medium, large;
		private ButtonGroup btnGroup;
		
		public SizePanel() {
			this.setLayout(new GridLayout(3, 1));
			small = new JRadioButton("Small", true);
			small.addChangeListener(this);
			medium = new JRadioButton("Medium");
			medium.addChangeListener(this);
			large = new JRadioButton("Large");
			large.addChangeListener(this);
			
			btnGroup = new ButtonGroup();
			btnGroup.add(small);
			btnGroup.add(medium);
			btnGroup.add(large);
			
			this.setBorder(BorderFactory.createTitledBorder("ũ��"));
			
			this.add(small);
			this.add(medium);
			this.add(large);
		}
		@Override
		public void stateChanged(ChangeEvent e) {
			if(e.getSource() == small) {
				temp3 = 0;
			}else if (e.getSource() == medium) {
				temp3 = 1000;
			}else if (e.getSource() == large) {
				temp3 = 2000;
			}
		}
	}
	
	public static void main(String[] args) {
		new PIzzaOrderDemo();
	}
}
