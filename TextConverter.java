package WD_J;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextConverter extends JFrame implements ActionListener{

   private JButton convertBtn;
   private JButton cancelBtn;
   private JTextArea textIn;
   private JTextArea textOut;
   
   public TextConverter() {
      this.setTitle("�ؽ�Ʈ ��ȯ");
      
      textIn = new JTextArea(10, 14);
      textOut = new JTextArea(10, 14);
      textIn.setLineWrap(true); //�ٹٲ�
      textOut.setLineWrap(true); //�ٹٲ�
      textOut.setEditable(false); // ����x
      
      JPanel textAreaPanel = new JPanel(new GridLayout(1, 2, 20, 20));
      textAreaPanel.add(textIn);
      textAreaPanel.add(textOut);
      
      convertBtn = new JButton("��ȯ");
      cancelBtn = new JButton("���");
      convertBtn.addActionListener(this);
      cancelBtn.addActionListener(this);
      
      
      JPanel btnPanel = new JPanel();
      btnPanel.add(convertBtn);
      btnPanel.add(cancelBtn);
      
      JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
      mainPanel.add(textAreaPanel, BorderLayout.CENTER);
      mainPanel.add(btnPanel, BorderLayout.SOUTH);
      
      this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
      this.add(mainPanel);
      this.pack();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // ��ȯ ��ư�� Ŭ���Ǿ��ٸ�
      // ���� textArea (textIn)�� text�� �о ����� ��ȯ�ϰ�
      // �� ����� ���� textArea (textOut)�� append�Ѵ�
      // ��� ��ư�� Ŭ�� �Ǿ��ٸ� ���� (textOut)�� �� ���ڿ��� ����
      if (e.getSource() == convertBtn) {
    	  String str = textIn.getText();
    	  String result = toEnglish(str);
    	  textOut.setText(result);
      }else {
    	  textOut.setText("");
      }
   }
   
   private String toEnglish(String korean) {
      // korean ���ڿ��� ����� ��ȯ�ؼ� ��ȯ�Ѵ�
	   /*
	    * �ؽ�Ʈ -> text
	    * ���� -> english
	    */
	   String result = null;
	   result = korean.replace("�ؽ�Ʈ" , "text");
	   result = result.replace("����", "english");
	   return result;
   }
   public static void main(String[] args) {
	   JFrame text = new TextConverter();
   }

   
}
