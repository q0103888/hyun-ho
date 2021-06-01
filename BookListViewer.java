package m5d27;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BookListViewer extends JFrame implements ActionListener {
   private JTextField idField, titleField, publisherField, yearField, priceField;
   private JButton previousBtn, nextBtn, insertBtn, finishBtn;
   private ResultSet rs;
   private Connection con;
//���⼭ ���� �Է¹޾� DB�� ������ ��
//������ å ������ �Է��ϴ� �ڵ�

   public BookListViewer() throws Exception {
      // ��ü�� ������ �� DB�� �����ϰ� �� ���� �����´�

      Class.forName("org.mariadb.jdbc.Driver");

      con = DriverManager.getConnection("jdbc:mysql://localhost:3307/oop", "root", "1111");
      String sql = "select * from books order by book_id desc";
      PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      rs = pstmt.executeQuery();




      // select ���� ������ ���� executeQuery() �޼��鸣 ����ϰ�
      // �� ��, insert, delete, update ���� ������ ����
      // executeUpdate() �޼��带 �����

      // ������Ʈ���� �����Ѵ�
      // ������ ������Ʈ���� JFrame ��ü(this)�� �߰��Ѵ�
      // �� JFrame ��ü(this)�� Layout�� GridLayout���� �����Ѵ�

      this.setLayout(new GridLayout(0,2));

      this.add(new JLabel("ID", JLabel.CENTER));
      idField = new JTextField(10);
      this.add(idField);

      this.add(new JLabel("Title", JLabel.CENTER));
      titleField = new JTextField(10);
      this.add(titleField);

      this.add(new JLabel("Publisher", JLabel.CENTER));
      publisherField = new JTextField(10);
      this.add(publisherField);

      this.add(new JLabel("Year", JLabel.CENTER));
      yearField = new JTextField(10);
      this.add(yearField);

      this.add(new JLabel("Price", JLabel.CENTER));
      priceField = new JTextField(10);
      this.add(priceField);



      // ��ư ������Ʈ���� ActionListener�� �� ��ü(this)�� �����Ѵ�.

      previousBtn = new JButton("Previous");
      previousBtn.addActionListener(this);
      this.add(previousBtn);

      nextBtn = new JButton("Next");
      nextBtn.addActionListener(this);
      this.add(nextBtn);

      insertBtn = new JButton("Insert");
      insertBtn.addActionListener(this);
      this.add(insertBtn);

      finishBtn = new JButton("Finish");
      finishBtn.addActionListener(this);
      this.add(finishBtn);

      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setResizable(false);
      this.setSize(350, 200);
      this.setVisible(true);
   }

   public static void main(String[] args) throws Exception{
      new BookListViewer();
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      try {
         if(e.getSource() == previousBtn) {
            System.out.println("previous Button");
            // ��� ������ Ŀ���� �������� �̵��ϰ�
            // �� Ŀ���� ����Ű�� ��� ���ڵ��� �÷� ���� �о� �ͼ�
            // �� �ؽ�Ʈ �ʵ��� �ؽ�Ʈ�� �����Ѵ�
            rs.previous();
            setCurrentBookInfo2TextField();
         } else if(e.getSource() == nextBtn) {
            System.out.println("next Button");
            // ��� ������ Ŀ���� �������� �̵��ϰ�
            // �� Ŀ���� ����Ű�� ��� ���ڵ��� �÷� ���� �о� �ͼ�
            // ��� �ؽ�Ʈ �ʵ��� �ؽ�Ʈ�� �����Ѵ�
            rs.next();
            setCurrentBookInfo2TextField();
         } else if(e.getSource() == insertBtn) {
            System.out.println("insert Button");
         } else if(e.getSource() == finishBtn) {
            System.out.println("���α׷��� �����մϴ�.");
            con.close();
            System.exit(0);
         }
      } catch (Exception err) {
         System.out.println(err.getMessage());
      }

   }
   
   private void setCurrentBookInfo2TextField() throws Exception {
      // ���� ���ڵ��� book_id, title, publisher, year, price
      int bookId = rs.getInt(1);
      String title = rs.getString(2);
      String publisher = rs.getString(3);
      Date year = rs.getDate(4);
      int price = rs.getInt(5);
      
      idField.setText(String.valueOf(bookId));
      titleField.setText(title);
      publisherField.setText(publisher);
      yearField.setText(year.toString());
      priceField.setText(String.valueOf(price));
   }


}
