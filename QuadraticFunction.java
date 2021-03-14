package WD_J;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class QuadraticFunction extends JPanel implements ActionListener{

	private JTextField aField, bField, cField;
	private double aCE = 1.0, bCE = -5.0, cCE = 6.0; //coefficient of
	
	public QuadraticFunction() {
		aField = new JTextField("1.0", 10);
		bField = new JTextField("-5.0", 10);
		cField = new JTextField("6.0", 10);
		
		this.add(aField);
		this.add(bField);
		this.add(cField);
		
		JButton drawButton = new JButton("Draw");
		this.add(drawButton);
		drawButton.addActionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawLine(100, 200, 500, 200);
		g2.drawLine(300, 0, 300, 400);
		
		g2.setPaint(Color.magenta);
		System.out.println("!aCE:" + aCE + "bCE:" +bCE+"cCE:"+cCE);
		for(int i = -20; i < 20; i++) {
			int x = i;
			int y = (int)(aCE * x * x - bCE * x + cCE);
			Shape s = new Ellipse2D.Float(300+x-2,200-y+x,4,4);
		    //g2,fillOval(300+x-2, 200-y+2,4,4)
			g2.fill(s);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		aCE = Double.parseDouble(aField.getText());
		bCE = Double.parseDouble(aField.getText());
		cCE = Double.parseDouble(aField.getText());
		
		System.out.println("aCE:" + aCE + "bCE:" +bCE+"cCE:"+cCE);
		repaint();
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new QuadraticFunction());
		frame.setSize(600, 400);
		frame.setVisible(true);
	}
}
