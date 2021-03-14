package WD_J;

import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;

public class SliderDemo extends JFrame implements ChangeListener{
	
	static final int INIT_VALUE = 15;
	private JButton OKBtn;
	private JSlider slider;
	private JButton btn;
	
	public SliderDemo() {
		JPanel panel;
		
		this.setTitle("�����̴� �׽�Ʈ");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		JLabel label = new JLabel("�����̴��� ������������.", SwingConstants.CENTER);
		// label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(label);
		
		slider = new JSlider(0, 30, INIT_VALUE);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(this);
		panel.add(slider);
		
		btn = new JButton(" ");
		ImageIcon icon = new ImageIcon("download.jpg");
		btn.setIcon(icon);
		btn.setSize(INIT_VALUE *10, INIT_VALUE*10);
		panel.add(btn);
		
		this.add(panel);
		this.setSize(300, 500);
		this.setVisible(true);
	}
		@Override 
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider) e.getSource();
			if (!source.getValueIsAdjusting()) {
				int value = source.getValue();
				btn.setSize(value * 10, value * 10);
			}
		} 
		public static void main(String[] args) {
			new SliderDemo();
		}
	}



